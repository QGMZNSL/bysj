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

import com.sincinfo.zxks.bean.AvoidExamSet;
import com.sincinfo.zxks.common.db.DbUtil;
import com.sincinfo.zxks.common.util.Page;
import com.sincinfo.zxks.common.util.StringTool;

/**
 * @ClassName: PlanDbService
 * @Description: 免考设置 <br>
 * @author yuansh
 * @date 2012-01-26 15:58<br>
 * 
 */
public class AvoidExamSetDbService {
	private DbUtil dbUtil = null;

	/**
	 * 构造
	 */
	public AvoidExamSetDbService() {
		this.dbUtil = new DbUtil();
	}

	/**
	 * 根据条件查询免考列表
	 * @param code 暂未用不用传值
	 * @param page 分页对象
	 * @return
	 */
	public List<AvoidExamSet> qry(AvoidExamSet avoidExamSet, Page page) {
		List<AvoidExamSet> planlevels = null;
		StringBuilder sql = new StringBuilder();
		StringBuilder sqlCount = new StringBuilder();
		sql.append("select d.*,(select s.syllabus_name from base_syllabus s where s.syllabus_code=d.syllabus_code)syllabus_name from base_avoid d where 1=1 ");
		sqlCount.append("select count(*) from base_avoid d where 1=1 ");
		if(!StringTool.isEmpty(avoidExamSet)){
			if(!StringTool.isEmpty(avoidExamSet.getSyllabusCode())){
				sql.append(String.format(" and d.syllabus_code = '%1$s'",avoidExamSet.getSyllabusCode()));
				sqlCount.append(String.format(" and d.syllabus_code = '%1$s'",avoidExamSet.getSyllabusCode()));
			}			
			if(!StringTool.isEmpty(avoidExamSet.getAvoidCode())){
				sql.append(String.format(" and d.avoid_code like '%%%1$s%%'",avoidExamSet.getAvoidCode()));
				sqlCount.append(String.format(" and d.avoid_code like '%%%1$s%%'",avoidExamSet.getAvoidCode()));
			}
			if(!StringTool.isEmpty(avoidExamSet.getAvoidState())){
				sql.append(String.format(" and d.avoid_state like '%%%1$s%%'",avoidExamSet.getAvoidState()));
				sqlCount.append(String.format(" and d.avoid_state like '%%%1$s%%'",avoidExamSet.getAvoidState()));
			}
		}
		sql.append(" order by d.avoid_code asc");

		// 分页查询
		String sqlPage = page.setPagecount(dbUtil.getNum(sqlCount.toString()), sql.toString());
		planlevels = dbUtil.getObjList(sqlPage, AvoidExamSet.class);
		return planlevels;
	}

	/**
	 * 根据编号，查询报考层次
	 * 
	 * @param avoidCode
	 *        层次编号
	 * @return
	 */
	public AvoidExamSet qry(String avoidCode) {
		AvoidExamSet avoidExamSet = null;
		String sql = String.format("select * from base_avoid d where d.avoid_code = '%1$s'",avoidCode);
		avoidExamSet = dbUtil.getObject(sql, AvoidExamSet.class);
		return avoidExamSet;
	}

	/**
	 * 保存部门对象到数据库
	 * 
	 * @param avoidExamSet
	 *            报考层次
	 * @param type
	 *            类型 0-insert, 1-update
	 * @return boolean
	 */
	public boolean save(AvoidExamSet avoidExamSet, int type) {
		boolean saveFlag = false;
		StringBuilder sql = new StringBuilder();
		String xh= dbUtil.getString("select seq_avoid.nextval from dual");
		switch (type) {
		case 0:
			sql.append("insert into base_avoid");
			sql.append("(avoid_code,syllabus_code,avoid_state,remarks)");
			sql.append(" values");
			sql.append(String.format(" ('%1$s','%2$s','%3$s','%4$s')",
					xh,avoidExamSet.getSyllabusCode(),avoidExamSet.getAvoidState(), avoidExamSet.getRemarks()));
			break;
		case 1:
			sql.append("update base_avoid");
			sql.append(String.format(" set avoid_state = '%1$s',",avoidExamSet.getAvoidState()));
			sql.append(String.format(" remarks = '%1$s'", avoidExamSet.getRemarks()));
			sql.append(String.format(" where avoid_code = '%1$s'", avoidExamSet.getAvoidCode()));
			break;
		default:
			break;
		}
		saveFlag = dbUtil.saveOrUpdate(sql.toString()) == 1;
		return saveFlag;
	}

	/**
	 * 删除免考信息
	 * @param avoidCode
	 * @return
	 */
	public boolean del(String syllabusCode) {
		boolean delFlag = false;
		StringBuilder sql = new StringBuilder();
		sql.append("delete from base_avoid");
		sql.append(String.format(" where syllabus_code = '%1$s'",syllabusCode));
		delFlag = dbUtil.saveOrUpdate(sql.toString()) > 0;
		return delFlag;
	}
	
	/**
	 * 清除免考信息
	 * @param avoidCode
	 * @return
	 */
	public boolean Clear(String avoidCode) {
		boolean delFlag = false;
		StringBuilder sql = new StringBuilder();
		sql.append("delete from base_avoid");
		sql.append(String.format(" where avoid_code = '%1$s'",avoidCode));
		delFlag = dbUtil.saveOrUpdate(sql.toString()) > 0;
		return delFlag;
	}	
	
}
