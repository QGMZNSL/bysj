/**
 * Copyright(c) 2012 西安云海信息技术有限责任公司 All Rights Reserved<br>
 * @Title: com.sincinfo.zxks.zxksdbs.DayDbService.java<br>
 * @Description: 主考院校管理 <br>
 * <br>
 * @author yuansh<br>
 * @date 2012-01-26 15:58
 * @version V1.0   
 */
package com.sincinfo.zxks.zxksdbs;

import java.util.List;

import com.sincinfo.zxks.bean.BaseAcademy;
import com.sincinfo.zxks.bean.BaseCity;
import com.sincinfo.zxks.bean.BasePro;
import com.sincinfo.zxks.common.db.DbUtil;
import com.sincinfo.zxks.common.util.Page;
import com.sincinfo.zxks.common.util.StringTool;

/**
 * @ClassName: PlanDbService
 * @Description: 主考院校设置 <br>
 *               <br>
 * @author yuansh
 * @date 2012-01-26 15:58<br>
 * 
 */
public class BaseAcademyDbService {
	private DbUtil dbUtil = null;

	/**
	 * 构造
	 */
	public BaseAcademyDbService() {
		this.dbUtil = new DbUtil();
	}

	/**
	 * 根据条件查询对应的所有主考院校列表，若不加限制则传递“0”
	 * 
	 * @param code
	 *            暂未用不用传值
	 * @param page
	 *            分页对象
	 * @return
	 */
	public List<BaseAcademy> qry(BaseAcademy baseacademy, Page page) {
		List<BaseAcademy> baseacademys = null;
		StringBuilder sql = new StringBuilder();
		StringBuilder sqlCount = new StringBuilder();
		sql.append("select d.*,(select city_name from base_city t where t.city_code=d.city_code)city_name from base_academy d where 1=1 ");
		sqlCount.append("select count(*) from base_academy d where 1=1 ");
		if(!StringTool.isEmpty(baseacademy)){
			if(!StringTool.isEmpty(baseacademy.getAcademyCode())){
				sql.append(String.format(" and academy_code like '%%%1$s%%'",baseacademy.getAcademyCode()));
				sqlCount.append(String.format(" and academy_code like '%%%1$s%%'",baseacademy.getAcademyCode()));
			}
			if(!StringTool.isEmpty(baseacademy.getAcademyName())){
				sql.append(String.format(" and academy_name like '%%%1$s%%'",baseacademy.getAcademyName()));
				sqlCount.append(String.format(" and academy_name like '%%%1$s%%'",baseacademy.getAcademyName()));
			}
		}
		sql.append(" order by d.academy_code asc");

		// 分页查询
		String sqlPage = page.setPagecount(dbUtil.getNum(sqlCount.toString()), sql.toString());
		baseacademys = dbUtil.getObjList(sqlPage, BaseAcademy.class);
		return baseacademys;
	}

	/**
     * 根据编号，查询主考院校
     * 
	 * @param academyCode
     *        主考院校编号
     * @return
	 */
	public BaseAcademy qry(String academyCode) {
		BaseAcademy baseacademy = null;
		String sql = String.format("select d.*,(select city_name from base_city t where t.city_code=d.city_code)city_name from base_academy d where d.academy_code = '%1$s'",academyCode);
		baseacademy = dbUtil.getObject(sql, BaseAcademy.class);
		return baseacademy;
	}
	
	/**
	 * 根据条件查询对应的主考院校专业列表，若不加限制则传递“0”
	 * 
	 * @param code
	 *            暂未用不用传值
	 * @param page
	 *            分页对象
	 * @return
	 */
	public List<BasePro> qryPro(BaseAcademy baseacademy, Page page) {
		List<BasePro> basepros = null;
		StringBuilder sql = new StringBuilder();
		StringBuilder sqlCount = new StringBuilder();
		sql.append(String.format("select p.pro_code,p.pro_name, pt.pro_type_name,pp.pro_part_name from base_academy_pro d" +
				" left join base_pro p on p.pro_code = d.pro_code" +
				" left join base_pro_type pt on pt.pro_type_code = p.pro_type_code" +
				" left join base_pro_partitions pp on pp.pro_part_code = p.pro_part_code where academy_code='%1$s'",baseacademy.getAcademyCode()));
		sqlCount.append(String.format("select count(*) from base_academy_pro d where 1=1 and academy_code='%1$s'",baseacademy.getAcademyCode()));
		sql.append(" order by d.academy_code asc");

		// 分页查询
		String sqlPage = page.setPagecount(dbUtil.getNum(sqlCount.toString()), sql.toString());
		basepros = dbUtil.getObjList(sqlPage, BasePro.class);
		return basepros;
	}

	/**
	 * 保存部门对象到数据库
	 * 
	 * @param baseacademy
	 *            主考院校
	 * @param type
	 *            类型 0-insert, 1-update
	 * @return boolean
	 */
	public boolean save(BaseAcademy baseacademy, int type) {
		boolean saveFlag = false;
		StringBuilder sql = new StringBuilder();
		String city_code=getADEcityCode(baseacademy.getCityCode());
		switch (type) {
		case 0:
			
			sql.append("insert into base_academy");
			sql.append("(academy_code,academy_name, city_code, academy_address, academy_linkman, academy_telephone, is_use, remarks)");
			sql.append(" values");
			sql.append(String.format(" ('%1$s','%2$s','%3$s','%4$s','%5$s','%6$s','%7$s','%8$s')",
					baseacademy.getAcademyCode(),baseacademy.getAcademyName(),city_code,baseacademy.getAcademyAddress(),
					baseacademy.getAcademyLinkman(),baseacademy.getAcademyTelephone(),baseacademy.getIsUse(),baseacademy.getRemarks()));
			break;
		case 1:
			sql.append("update base_academy");
			sql.append(String.format(" set academy_name = '%1$s',",baseacademy.getAcademyName()));
			sql.append(String.format(" city_code = '%1$s',", city_code));
			sql.append(String.format(" academy_address = '%1$s',", baseacademy.getAcademyAddress()));
			sql.append(String.format(" academy_linkman = '%1$s',", baseacademy.getAcademyLinkman()));
			sql.append(String.format(" academy_telephone = '%1$s',", baseacademy.getAcademyTelephone()));
			sql.append(String.format(" is_use = '%1$s',", baseacademy.getIsUse()));
			sql.append(String.format(" remarks = '%1$s'", baseacademy.getRemarks()));
			sql.append(String.format(" where academy_code = '%1$s'", baseacademy.getAcademyCode()));
			break;
		default:
			break;
		}
		saveFlag = dbUtil.saveOrUpdate(sql.toString()) == 1;
		return saveFlag;
	}
	
	/**
	 * 添加专业到主考院校
	 * 
	 * @param baseacademy 主考院校
	 * @param academyCode 主考院校
	 * @param proCode     专业代码
	 * @return boolean
	 */
	public boolean addPro(String academyCode,String proCode) {
		boolean saveFlag = false;
		StringBuilder sql = new StringBuilder();
		sql.append("insert into base_academy_pro(academy_code, pro_code)values");
		sql.append(String.format(" ('%1$s','%2$s')",academyCode,proCode));
		saveFlag = dbUtil.saveOrUpdate(sql.toString()) == 1;
		return saveFlag;
	}

	/**
	 * 删除用户部门（如果已经设置职位，则必须先清除职位才能删除部门）
	 * 
	 * @param academyCode
	 * @return
	 */
	public boolean del(String academyCode) {
		boolean delFlag = false;
		StringBuilder sql = new StringBuilder();
		sql.append("delete from base_academy");
		sql.append(String.format(" where academy_code = '%1$s'",academyCode));
		delFlag = dbUtil.saveOrUpdate(sql.toString()) == 1;
		return delFlag;
	}
	
	/**
	 * 删除用户部门（如果已经设置职位，则必须先清除职位才能删除部门）
	 * 
	 * @param academyCode
	 * @return
	 */
	public boolean delPro(String proCode) {
		boolean delFlag = false;
		StringBuilder sql = new StringBuilder();
		sql.append("delete from base_academy_pro");
		sql.append(String.format(" where pro_code = '%1$s'",proCode));
		delFlag = dbUtil.saveOrUpdate(sql.toString()) == 1;
		return delFlag;
	}	
	
	/**
	 * 查询列表
	 * @return List<PlanLevel>
	 */
	public List<BaseCity> qryBaseCityClasses() {
		List<BaseCity> BList = null;
		StringBuilder sql = new StringBuilder();
		sql.append("select city_code,city_name from base_city order by city_code");
		BList = dbUtil.getObjList(sql.toString(), BaseCity.class);
		return BList;
	}
	
	/**
	 * 根据cityCode得到base_academy.city_code
	 * 
	 * @param cityCode
	 * @return
	 */
	public String getADEcityCode(String cityCode) {
		String city_code="";
		StringBuilder sql = new StringBuilder();
		sql.append("select city_code from base_city ");
		sql.append(String.format(" where city_code= '%1$s'",cityCode));
		city_code= dbUtil.getString(sql.toString());
		return city_code;
	}
	
}
