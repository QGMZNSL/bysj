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

import java.util.ArrayList;
import java.util.List;

import com.sincinfo.zxks.bean.BasePro;
import com.sincinfo.zxks.bean.PlanLevel;
import com.sincinfo.zxks.bean.ProSeq;
import com.sincinfo.zxks.bean.ProSyllabus;
import com.sincinfo.zxks.bean.ProType;
import com.sincinfo.zxks.bean.Syllabus;
import com.sincinfo.zxks.common.db.DbConn;
import com.sincinfo.zxks.common.db.DbUtil;
import com.sincinfo.zxks.common.util.Page;
import com.sincinfo.zxks.common.util.StringTool;

/**
 * @ClassName: PlanDbService
 * @Description: 专业管理 <br>
 *                       <br>
 * @author yuansh
 * @date 2012-01-26 15:58<br>
 * 
 */
public class BaseProKjDbService {
	
	
	private DbUtil dbUtil = null;

	/**
	 * 构造
	 */
	public BaseProKjDbService() {
		this.dbUtil = new DbUtil();
	}

	/**
	 * 根据条件查询对应的所有专业设置列表，若不加限制则传递“0”
	 * 
	 * @param code
	 *            暂未用不用传值
	 * @param page
	 *            分页对象
	 * @return
	 */
	public List<BasePro> qry(BasePro basepro, Page page) {
		List<BasePro> basepros = null;
		StringBuilder sql = new StringBuilder();
		StringBuilder sqlCount = new StringBuilder();
		StringBuilder url=new StringBuilder();
		url.append(page.getPath());
		sql.append("select d.*,(select l.pro_type_name from base_pro_type l where l.pro_type_code=d.pro_type_code) as pro_type_name," +
				"(select m.pro_part_name from base_pro_partitions m where m.pro_part_code=d.pro_part_code) as pro_part_name,(select count(*) from base_pro_syllabus s where s.pro_code=d.pro_code)syllabus_set from base_pro d where 1=1 ");
		sqlCount.append("select count(*) from base_pro d where 1=1 ");
		if(!StringTool.isEmpty(basepro)){
			if(!StringTool.isEmpty(basepro.getProCode())){
				sql.append(String.format(" and pro_code like '%%%1$s%%'",basepro.getProCode()));
				sqlCount.append(String.format(" and pro_code like '%%%1$s%%'",basepro.getProCode()));
				url.append(String.format("&basepro.proCode=%1$s", basepro.getProCode()));
			}
			if(!StringTool.isEmpty(basepro.getProName())){
				sql.append(String.format(" and pro_name like '%%%1$s%%'",basepro.getProName()));
				sqlCount.append(String.format(" and pro_name like '%%%1$s%%'",basepro.getProName()));
				url.append(String.format("&basepro.proName=%1$s", basepro.getProName()));
			}
			if(!StringTool.isEmpty(basepro.getProTypecode())){
				sql.append(String.format(" and d.pro_type_code = '%1$s'",basepro.getProTypecode()));
				sqlCount.append(String.format(" and d.pro_type_code = '%1$s'",basepro.getProTypecode()));
				url.append(String.format("&basepro.proTypecode=%1$s", basepro.getProTypecode()));
			}
			if(!StringTool.isEmpty(basepro.getProPartcode())){
				sql.append(String.format(" and d.pro_part_code = '%1$s'",basepro.getProPartcode()));
				sqlCount.append(String.format(" and d.pro_part_code = '%1$s'",basepro.getProPartcode()));
				url.append(String.format("&basepro.proPartcode=%1$s", basepro.getProPartcode()));
			}
			if(!StringTool.isEmpty(basepro.getLevelCode())){
				sql.append(String.format(" and level_code = '%1$s'",basepro.getLevelCode()));
				sqlCount.append(String.format(" and level_code = '%1$s'",basepro.getLevelCode()));
				url.append(String.format("&basepro.levelCode=%1$s", basepro.getLevelCode()));
			}	
//			if(!StringTool.isEmpty(basepro.getIsUse())){
//				sql.append(String.format(" and is_use = '%1$s'",basepro.getIsUse()));
//				sqlCount.append(String.format(" and is_use = '%1$s'",basepro.getIsUse()));
//			}	
			if(!StringTool.isEmpty(basepro.getAllowGraduate())){
				sql.append(String.format(" and allow_graduate = '%1$s'",basepro.getAllowGraduate()));
				sqlCount.append(String.format(" and allow_graduate = '%1$s'",basepro.getAllowGraduate()));
				url.append(String.format("&basepro.allowGraduate=%1$s", basepro.getAllowGraduate()));
			}
		}else{
			sql.append(" and allow_graduate = '1'");
			sqlCount.append(" and allow_graduate = '1'");
		}
		sql.append(" order by d.pro_code asc");

		/*
		 * 分页查询
		 * */ 
		page.setPath(url.toString());
		String sqlPage = page.setPagecount(dbUtil.getNum(sqlCount.toString()), sql.toString());
		basepros = dbUtil.getObjList(sqlPage, BasePro.class);
		return basepros;
	}

	/**
	 * 根据编号，查询报考层次
	 * @param proCode 专业编号
	 * @return
	 */
	public BasePro qry(String proCode) {
		BasePro basepro = null;
		String sql = String.format("select d.*,(select l.pro_type_name from base_pro_type l where l.pro_type_code=d.pro_type_code) as pro_type_name," +
				"(select m.pro_part_name from base_pro_partitions m where m.pro_part_code=d.pro_part_code) as pro_part_name," +
				"(select a.academy_name from base_academy_pro ap,base_academy a where ap.academy_code=a.academy_code and ap.pro_code=d.pro_code)academy_name from base_pro d where d.pro_code = '%1$s'",proCode);
		basepro = dbUtil.getObject(sql, BasePro.class);
		return basepro;
	}	
	
	/**
	 * 根据编号，查询报考层次
	 * @param proCode 专业编号
	 * @return
	 */
	public BasePro qry(BasePro bp) {
		BasePro basepro = null;
		StringBuilder sql = new StringBuilder();
		sql.append("select d.*,(select l.pro_type_name from base_pro_type l where l.pro_type_code=d.pro_type_code) as pro_type_name," +
				"(select m.pro_part_name from base_pro_partitions m where m.pro_part_code=d.pro_part_code) as pro_part_name," +
				"(select a.academy_name from base_academy_pro ap,base_academy a where ap.academy_code=a.academy_code and ap.pro_code=d.pro_code)academy_name from base_pro d where 1=1  ");
		if(!StringTool.isEmpty(bp)){
			if(!StringTool.isEmpty(bp.getProCode())){
				sql.append(String.format(" and pro_code = '%1$s'",bp.getProCode()));
			}
			if(!StringTool.isEmpty(bp.getProName())){
				sql.append(String.format(" and pro_name like '%%%1$s%%'",bp.getProName()));
			}
		}
		basepro = dbUtil.getObject(sql.toString(), BasePro.class);
		return basepro;
	}		

	/**
	 * 根据编号，查询专业课程
	 * 
	 * @param proCode
	 *        专业编号
	 * @return
	 */
	public List<ProSyllabus> qryProSyllabus(BasePro basepro, Page page) {
		List<ProSyllabus> syls = null;
		StringBuilder sql = new StringBuilder();
		StringBuilder sqlCount = new StringBuilder();
		sql.append(String.format("select d.*,(select s.syllabus_name from base_syllabus s where s.syllabus_code=d.syllabus_code)syllabus_name,(select t.textbook_name from base_textbook t where t.textbook_code=d.textbook_code)textbook_name from base_pro_syllabus d where d.pro_code= '%1$s'",basepro.getProCode()));
		sqlCount.append(String.format("select count(*) from base_pro_syllabus d where 1=1 and d.pro_code= '%1$s'",basepro.getProCode()));
		sql.append(" order by d.substitute_code asc");

		/**
		 * 分页查询
		 * */ 
		String sqlPage = page.setPagecount(dbUtil.getNum(sqlCount.toString()), sql.toString());
		syls = dbUtil.getObjList(sqlPage, ProSyllabus.class);
		return syls;
	}	

	/**
	 * @see 添加允许的申请的专业
	 */
	public boolean doAllowGraduate(String proCode){
		String sql="update base_pro set allow_graduate='1' where pro_code=?";
		int k=dbUtil.saveOrUpdate(sql, proCode);
		if(k>0){
			return true;
		}else{
			return false;
		}
	}
	/**
	 * 保存部门对象到数据库
	 * 
	 * @param basepro
	 *            专业
	 * @param type
	 *            类型 0-insert, 1-update
	 * @return boolean
	 */
	public boolean save(BasePro basepro, int type) {
		boolean saveFlag = false;
		StringBuilder sql = new StringBuilder();
		String level_code= getLeveCode(basepro.getProTypecode());
		
		switch (type) {
		case 0:
			sql.append("insert into base_pro");
			sql.append("(pro_code,pro_name,pro_part_code, pro_type_code,is_gb, is_use,level_code)");
			sql.append(" values");
			sql.append(String.format(" ('%1$s','%2$s','%3$s','%4$s','%5$s','%6$s','%7$s')",
					basepro.getProCode(),basepro.getProName(),basepro.getProPartcode(),basepro.getProTypecode(),basepro.getIsGb(),basepro.getIsUse(),level_code));
			
			break;
		case 1:
			sql.append("update base_pro");
			sql.append(String.format(" set pro_name = '%1$s',",basepro.getProName()));
			sql.append(String.format(" pro_part_code = '%1$s',", basepro.getProPartcode()));
			sql.append(String.format(" pro_type_code = '%1$s',", basepro.getProTypecode()));
			sql.append(String.format(" is_GB = '%1$s',", basepro.getIsGb()));
			sql.append(String.format(" is_use = '%1$s', ", basepro.getIsUse()));
			sql.append(String.format(" level_code = '%1$s'",level_code));
			sql.append(String.format(" where pro_code = '%1$s'", basepro.getProCode()));
	
			break;
		default:
			break;
		}
		saveFlag = dbUtil.saveOrUpdate(sql.toString()) == 1;
		return saveFlag;
	}
	
	/**
	 * 保存部门对象到数据库
	 * 
	 * @param basepro
	 *            专业
	 * @param type
	 *            类型 0-insert, 1-update
	 * @return boolean
	 */
	public boolean saveProSyllabus(BasePro basepro, int type) {
		boolean saveFlag = false;
		StringBuilder sql = new StringBuilder();
		String level_code= getLeveCode(basepro.getProTypecode());
		
		switch (type) {
		case 0:
			sql.append("insert into base_pro");
			sql.append("(pro_code,pro_name,pro_part_code, pro_type_code,is_gb, is_use,level_code)");
			sql.append(" values");
			sql.append(String.format(" ('%1$s','%2$s','%3$s','%4$s','%5$s','%6$s','%7$s')",
					basepro.getProCode(),basepro.getProName(),basepro.getProPartcode(),basepro.getProTypecode(),basepro.getIsGb(),basepro.getIsUse(),level_code));
			
			break;
		case 1:
			sql.append("update base_pro");
			sql.append(String.format(" set pro_name = '%1$s',",basepro.getProName()));
			sql.append(String.format(" pro_part_code = '%1$s',", basepro.getProPartcode()));
			sql.append(String.format(" pro_type_code = '%1$s',", basepro.getProTypecode()));
			sql.append(String.format(" is_GB = '%1$s',", basepro.getIsGb()));
			sql.append(String.format(" is_use = '%1$s', ", basepro.getIsUse()));
			sql.append(String.format(" level_code = '%1$s'",level_code));
			sql.append(String.format(" where pro_code = '%1$s'", basepro.getProCode()));
	
			break;
		default:
			break;
		}
		saveFlag = dbUtil.saveOrUpdate(sql.toString()) == 1;
		return saveFlag;
	}	
	
	/**
	 * 删除用户部门（如果已经设置职位，则必须先清除职位才能删除部门）
	 * 
	 * @param proCode
	 * @return
	 */
	public boolean del(String proCode) {
		boolean delFlag = false;
		StringBuilder sql = new StringBuilder();
		sql.append("delete from base_pro");
		sql.append(String.format(" where pro_code = '%1$s'",proCode));
		delFlag = dbUtil.saveOrUpdate(sql.toString()) == 1;
		return delFlag;
	}
	
	/**
	 * 查询专业类型列表
	 * @return List<ProType>
	 */
	public List<ProType> qryProTypeClasses() {
		List<ProType> pList = null;
		StringBuilder sql = new StringBuilder();
		sql.append("select pro_type_code, pro_type_name from base_pro_type order by pro_type_code ");
		pList = dbUtil.getObjList(sql.toString(), ProType.class);
		return pList;
	}
	
	/**
	 * 查询专业分类列表
	 * @return List<ProSeq>
	 */
	public List<ProSeq> qryProSeqClasses() {
		List<ProSeq> pList = null;
		StringBuilder sql = new StringBuilder();
		sql.append("select pro_part_code, pro_part_name from base_pro_partitions order by pro_part_code ");
		pList = dbUtil.getObjList(sql.toString(), ProSeq.class);
		return pList;
	}
	
	/**
	 * 查询层次列表
	 * @return List<PlanLevel>
	 */
	public List<PlanLevel> qryPlanLevelClasses() {
		List<PlanLevel> pList = null;
		StringBuilder sql = new StringBuilder();
		sql.append("select level_code,level_name from base_level order by level_code");
		pList = dbUtil.getObjList(sql.toString(), PlanLevel.class);
		return pList;
	}	
	
	/**
	 * 查询课程列表
	 * @return List<Syllabus>
	 */
	public List<Syllabus> qrysyllabusClasses() {
		List<Syllabus> SList = null;
		StringBuilder sql = new StringBuilder();
		sql.append("select syllabus_code,syllabus_name from base_level order by level_code");
		SList = dbUtil.getObjList(sql.toString(), Syllabus.class);
		return SList;
	}	
	
	
	/**
	 * 根据protype_code得到level_code
	 * 
	 * @param proCode
	 * @return
	 */
	public String getLeveCode(String protypeCode) {
		String leve_code="";
		StringBuilder sql = new StringBuilder();
		sql.append("select level_code from base_pro_type ");
		sql.append(String.format(" where pro_type_code= '%1$s'",protypeCode));
		leve_code= dbUtil.getString(sql.toString());
		return leve_code;
	}
	
	/**
	 * @see 执行 设置禁止毕业专业
	 */
	public boolean doNoAllowGraduate(String [] proCodes){
		ArrayList<String> list=new ArrayList<String>();
		String sql="update base_pro set allow_graduate='0' where pro_code='%1$s'";
		for(String s:proCodes){
			list.add(String.format(sql, s));
		}
		int k=dbUtil.transExeSqls(list);
		if(k>0){
			return true;
		}else{
			return false;
		}
	}
	
}
