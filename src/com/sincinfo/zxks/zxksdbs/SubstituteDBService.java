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
import com.sincinfo.zxks.bean.Substitute;
import com.sincinfo.zxks.bean.Syllabus;
import com.sincinfo.zxks.common.db.DbUtil;
import com.sincinfo.zxks.common.util.Page;
import com.sincinfo.zxks.common.util.StringTool;

/**
 * @ClassName: PlanDbService
 * @Description: 课程顶替设置 <br>
 * @author yuansh
 * @date 2012-01-26 15:58<br>
 * 
 */
public class SubstituteDBService {
	private DbUtil dbUtil = null;

	/**
	 * 构造
	 */
	public SubstituteDBService() {
		this.dbUtil = new DbUtil();
	}

	/**
	 * 根据条件查询课程顶替列表
	 * @param 
	 * @param page 分页对象
	 * @return
	 */
	public List<Substitute> qry(Substitute substitute, Page page) {
		List<Substitute> substitutes = null;
		StringBuilder sql = new StringBuilder();
		StringBuilder sqlCount = new StringBuilder();
		sql.append("select d.*,(select s.syllabus_name from base_syllabus s where s.syllabus_code=d.substitute_syllabus)syllabus_name from base_substitute d where 1=1 ");
		sqlCount.append("select count(*) from base_substitute d where 1=1 ");
		if(!StringTool.isEmpty(substitute)){
			if(!StringTool.isEmpty(substitute.getSyllabuscode())){
				sql.append(String.format(" and d.syllabus_code = '%1$s'",substitute.getSyllabuscode()));
				sqlCount.append(String.format(" and d.syllabus_code = '%1$s'",substitute.getSyllabuscode()));
			}			
			if(!StringTool.isEmpty(substitute.getSyllabusName())){
				sql.append(String.format(" and d.syllabus_name like '%%%1$s%%'",substitute.getSyllabusName()));
				sqlCount.append(String.format(" and d.syllabus_name like '%%%1$s%%'",substitute.getSyllabusName()));
			}

		}
		sql.append(" order by d.syllabus_code asc");

		// 分页查询
		String sqlPage = page.setPagecount(dbUtil.getNum(sqlCount.toString()), sql.toString());
		substitutes = dbUtil.getObjList(sqlPage, Substitute.class);
		return substitutes;
	}

	/**
	 * 根据课程代码，查询课程表
	 * 
	 * @param avoidCode
	 *        层次编号
	 * @return
	 */
	public Substitute qry(String syllabuscode) {
		Substitute substitute = null;
		String sql = String.format("select * from base_substitude d where d.syllabus_code = '%1$s'",syllabuscode);
		substitute = dbUtil.getObject(sql, Substitute.class);
		return substitute;
	}

	/**
	 * 保存部门对象到数据库
	 * @param avoidExamSet 报考层次
	 * @param type         类型 0-insert, 1-update
	 * @return boolean
	 */
	public boolean save(Substitute substitute, int type) {
		boolean saveFlag = false;
		StringBuilder sql = new StringBuilder();
		String xh= dbUtil.getString("select seq_substitute.nextval from dual");
		switch (type) {
		case 0:
			sql.append("insert into base_substitute");
			sql.append("(substitute_code,syllabus_code,substitute_type,substitute_syllabus,avoid_state,remarks)");
			sql.append(" values");
			sql.append(String.format(" ('%1$s','%2$s','%3$s','%4$s','%5$s','%6$s')",
					xh,substitute.getSyllabuscode(), substitute.getSubstitutetype(),substitute.getSubstitutesyllabus(),substitute.getAvoidstate(),substitute.getRemarks()));
			break;
		case 1:
			sql.append("update base_substitute");
			sql.append(String.format(" set substitute_type = '%1$s',",substitute.getSubstitutetype()));
			sql.append(String.format(" substitute_syllabus = '%1$s',", substitute.getSubstitutesyllabus()));
			sql.append(String.format(" syllabus_code = '%1$s',", substitute.getSyllabuscode()));
			sql.append(String.format(" remarks = '%1$s'", substitute.getRemarks()));
			sql.append(String.format(" where syllabus_code = '%1$s'", substitute.getSyllabuscode()));
			break;
		default:
			break;
		}
		saveFlag = dbUtil.saveOrUpdate(sql.toString()) == 1;
		return saveFlag;
	}

	/**
	 * 删除课程顶替信息
	 * @param avoidCode
	 * @return
	 */
	public boolean del(String substitute_code) {
		boolean delFlag = false;
		StringBuilder sql = new StringBuilder();
		sql.append("delete from BASE_SUBSTITUTE");
		sql.append(String.format(" where substitute_code = '%1$s'",substitute_code));
		delFlag = dbUtil.saveOrUpdate(sql.toString()) > 0;
		return delFlag;
	}
	
	/**
	 * 清除课程顶替信息
	 * @param avoidCode
	 * @return
	 */
	public boolean Clear(String syllabuscode) {
		boolean delFlag = false;
		StringBuilder sql = new StringBuilder();
		sql.append("delete from BASE_SUBSTITUTE");
		sql.append(String.format(" where syllabus_code = '%1$s'",syllabuscode));
		delFlag = dbUtil.saveOrUpdate(sql.toString()) > 0;
		return delFlag;
	}	
	
}