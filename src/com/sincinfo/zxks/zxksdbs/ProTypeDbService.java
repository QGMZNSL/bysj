/**
 * Copyright(c) 2012 西安云海信息技术有限责任公司 All Rights Reserved<br>
 * @Title: com.sincinfo.zxks.zxksdbs.DayDbService.java<br>
 * @Description: 报考层次管理 <br>
 * <br>
 * @author yuansh<br>
 * @date 2012-01-26 15:58
 * @version V1.0   
 */
package com.sincinfo.zxks.zxksdbs;

import java.util.List;

import com.sincinfo.zxks.bean.ProType;
import com.sincinfo.zxks.bean.PlanLevel;
import com.sincinfo.zxks.common.db.DbUtil;
import com.sincinfo.zxks.common.util.Page;
import com.sincinfo.zxks.common.util.StringTool;

/**
 * @ClassName: ProTypeDbService
 * @Description: 专业类型管理 <br>
 *               <br>
 * @author yuansh
 * @date 2012-01-26 15:58<br>
 * 
 */
public class ProTypeDbService {
	private DbUtil dbUtil = null;

	/**
	 * 构造
	 */
	public ProTypeDbService() {
		this.dbUtil = new DbUtil();
	}

	/**
	 * 根据条件查询对应的所有报考层次列表，若不加限制则传递“0”
	 * 
	 * @param code
	 *            暂未用不用传值
	 * @param page
	 *            分页对象
	 * @return
	 */
	public List<ProType> qry(ProType protype, Page page) {
		List<ProType> planlevels = null;
		StringBuilder sql = new StringBuilder();
		StringBuilder sqlCount = new StringBuilder();
		sql.append("select d.*,(select l.level_name from base_level l where l.level_code=d.level_code) as level_name from base_pro_type d where d.IS_USE='1' ");
		sqlCount.append("select count(*) from base_pro_type d where d.IS_USE='1' ");
		if(!StringTool.isEmpty(protype)){
			if(!StringTool.isEmpty(protype.getProTypeCode())){
				sql.append(String.format(" and pro_type_code like '%%%1$s%%'",protype.getProTypeCode()));
				sqlCount.append(String.format(" and pro_type_code like '%%%1$s%%'",protype.getProTypeCode()));
			}
			if(!StringTool.isEmpty(protype.getProTypeName())){
				sql.append(String.format(" and pro_type_name like '%%%1$s%%'",protype.getProTypeName()));
				sqlCount.append(String.format(" and pro_type_name like '%%%1$s%%'",protype.getProTypeName()));
			}
		}
		sql.append(" order by pro_type_code asc");

		// 分页查询
		String sqlPage = page.setPagecount(dbUtil.getNum(sqlCount.toString()), sql.toString());
		planlevels = dbUtil.getObjList(sqlPage, ProType.class);
		return planlevels;
	}

	/**
	 * 根据编号，查询专业类型
	 * 
	 * @param proTypeCode
	 *        专业类型编号
	 * @return
	 */
	public ProType qry(String proTypeCode) {
		ProType protype = null;
		String sql = String.format("select * from base_pro_type d where d.pro_type_code = '%1$s'",proTypeCode);
		protype = dbUtil.getObject(sql, ProType.class);
		return protype;
	}

	/**
	 * 保存部门对象到数据库
	 * 
	 * @param protype
	 *            专业类型
	 * @param type
	 *            类型 0-insert, 1-update
	 * @return boolean
	 */
	public boolean save(ProType protype, int type) {
		boolean saveFlag = false;
		StringBuilder sql = new StringBuilder();
		switch (type) {
		case 0:
			String s="select count(*) from base_pro_type b where b.pro_type_code =?";
			Long l=dbUtil.getNum(s,protype.getLevelCode());
			if(l==0){
				sql.append("insert into base_pro_type");
				sql.append("(pro_type_code,pro_type_name,level_code,is_use,remarks)");
				sql.append(" values");
				sql.append(String.format(" ('%1$s','%2$s','%3$s','%4$s','%5$s')",
						protype.getProTypeCode(),protype.getProTypeName(),protype.getLevelCode(), "1",protype.getRemarks()));
			}
			else{
				sql.append("update base_pro_type");
				sql.append(String.format(" set pro_type_name = '%1$s',",protype.getProTypeName()));		
				sql.append(String.format(" level_code = '%1$s',", protype.getLevelCode()));
				sql.append(String.format(" is_use = '%1$s',", "1"));
				sql.append(String.format(" remarks = '%1$s' ",protype.getRemarks()));			
				sql.append(String.format(" where pro_type_code = '%1$s'", protype.getProTypeCode()));
			}
			break;
		case 1:
			sql.append("update base_pro_type");
			sql.append(String.format(" set pro_type_name = '%1$s',",protype.getProTypeName()));		
			sql.append(String.format(" level_code = '%1$s',", protype.getLevelCode()));
			sql.append(String.format(" is_use = '%1$s',", "1"));
			sql.append(String.format(" remarks = '%1$s' ",protype.getRemarks()));			
			sql.append(String.format(" where pro_type_code = '%1$s'", protype.getProTypeCode()));
			break;
		default:
			break;
		}
		saveFlag = dbUtil.saveOrUpdate(sql.toString()) == 1;
		return saveFlag;
	}
	
	public boolean isUseDel(String proTypeCode){
		boolean delFlag = false;
		StringBuilder sql = new StringBuilder();
		sql.append("update base_pro_type b set b.IS_USE='0'");
		sql.append(String.format(" where b.pro_type_code = '%1$s'",proTypeCode));
		delFlag = dbUtil.saveOrUpdate(sql.toString()) == 1;
		return delFlag;
	}

	/**
	 * 删除用户部门（如果已经设置职位，则必须先清除职位才能删除部门）
	 * 
	 * @param levelCode
	 * @return
	 */
	public boolean del(String levelCode) {
		boolean delFlag = false;
		StringBuilder sql = new StringBuilder();
		sql.append("delete from base_pro_type");
		sql.append(String.format(" where pro_type_code = '%1$s'",levelCode));
		delFlag = dbUtil.saveOrUpdate(sql.toString()) == 1;
		return delFlag;
	}
	
	/**
	 * 查询列表
	 * @return List<planlevel>
	 */
	public List<PlanLevel> qryPlanLevelClasses() {
		List<PlanLevel> pList = null;
		StringBuilder sql = new StringBuilder();
		sql.append("select level_code,level_name from base_level where IS_USE='1' order by level_code");
		pList = dbUtil.getObjList(sql.toString(), PlanLevel.class);
		return pList;
	}
	
}
