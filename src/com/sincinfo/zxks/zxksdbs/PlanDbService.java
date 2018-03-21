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

import com.sincinfo.zxks.bean.PlanLevel;
import com.sincinfo.zxks.common.db.DbUtil;
import com.sincinfo.zxks.common.util.Page;
import com.sincinfo.zxks.common.util.StringTool;

/**
 * @ClassName: PlanDbService
 * @Description: 报考层次管理 <br>
 *               <br>
 * @author yuansh
 * @date 2012-01-26 15:58<br>
 * 
 */
public class PlanDbService {
	private DbUtil dbUtil = null;

	/**
	 * 构造
	 */
	public PlanDbService() {
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
	public List<PlanLevel> qry(String code, Page page) {
		List<PlanLevel> planlevels = null;
		StringBuilder sql = new StringBuilder();
		sql.append("select d.*,");
		sql.append(" from base_level d");
		String sqlCount = "select count(*) from base_level d";
		sql.append(" order by d.level_code asc");

		// 分页查询
		String sqlPage = page.setPagecount(dbUtil.getNum(sqlCount), sql.toString());
		planlevels = dbUtil.getObjList(sqlPage, PlanLevel.class);
		return planlevels;
	}

	/**
	 * 根据编号，查询报考层次
	 * 
	 * @param levelCode
	 *        层次编号
	 * @return
	 */
	public PlanLevel qry(String levelCode) {
		PlanLevel planlevel = null;
		String sql = String.format("select * from base_level d where d.level_code = '%1$s'",levelCode);
		planlevel = dbUtil.getObject(sql, PlanLevel.class);
		return planlevel;
	}

	/**
	 * 保存部门对象到数据库
	 * 
	 * @param planlevel
	 *            报考层次
	 * @param type
	 *            类型 0-insert, 1-update
	 * @return boolean
	 */
	public boolean save(PlanLevel planlevel, int type) {
		boolean saveFlag = false;
		StringBuilder sql = new StringBuilder();
		switch (type) {
		case 0:
			sql.append("insert into base_level");
			sql.append("(level_code,level_name,is_use)");
			sql.append(" values");
			sql.append(String.format(" ('%1$s',%2$s,%3$s)",
					planlevel.getLevelCode(),planlevel.getLevelName(), planlevel.getIsUse()));
			break;
		case 1:
			sql.append("update base_level");
			sql.append(String.format(" set level_name = '%1$s',",planlevel.getLevelName()));
			sql.append(String.format(" is_use = '%1$s'", planlevel.getIsUse()));
			sql.append(String.format(" where level_code = '%1$s'", planlevel.getLevelCode()));
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
	 * @param levelCode
	 * @return
	 */
	public boolean del(String levelCode) {
		boolean delFlag = false;
		StringBuilder sql = new StringBuilder();
		sql.append("delete from base_level");
		sql.append(String.format(" where level_code = '%1$s'",levelCode));
		delFlag = dbUtil.saveOrUpdate(sql.toString()) == 1;
		return delFlag;
	}
	

	/**
	 * 保存一个职位对象
	 * @param planlevel 职位对象
	 * @return boolean
	 */
	public boolean add(PlanLevel planlevel) {
		boolean addFlag = false;
		StringBuilder sql = new StringBuilder();
		if ("".equals(StringTool.trim(planlevel.getLevelCode()))) {
			sql.append("insert into level_code");
			sql.append(" (level_code, level_name, is_use)");
			sql.append(" values");
			sql.append(String.format("('%1$s', '%2$s', '%3$s')", planlevel.getLevelName(),planlevel.getIsUse()));
		} else {
			sql.append("update level_code");
			sql.append(String.format(" set level_name = '%1$s', ", planlevel.getLevelName()));
			sql.append(String.format(" is_use = '%1$s'", planlevel.getIsUse()));
			sql.append(String.format(" where level_code = '%1$s'", planlevel.getLevelCode()));
		}
		addFlag = dbUtil.saveOrUpdate(sql.toString()) == 1;
		return addFlag;
	}
}
