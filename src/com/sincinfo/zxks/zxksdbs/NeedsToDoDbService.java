/**
 * Copyright(c) 2012 西安云海信息技术有限责任公司 All Rights Reserved<br>
 * @Title: com.sincinfo.zxks.zxksdbs.NeedsToDoDbService.java<br>
 * @Description: 待办事宜数据库操作 <br>
 * <br>
 * @author litian<br>
 * @date Jun 15, 2012 8:59:07 AM 
 * @version V1.0   
 */
package com.sincinfo.zxks.zxksdbs;

import com.sincinfo.zxks.bean.BaseUser;
import com.sincinfo.zxks.common.db.DbUtil;
import com.sincinfo.zxks.common.util.Power;

/**
 * @ClassName: NeedsToDoDbService
 * @Description: 待办事宜数据库操作 <br>
 *               <br>
 * @author litian
 * @date Jun 15, 2012 8:59:07 AM<br>
 * 
 */
public class NeedsToDoDbService {

	private DbUtil dbUtil;

	public NeedsToDoDbService() {
		this.dbUtil = new DbUtil();
	}

	public NeedsToDoDbService(DbUtil dbUtil) {
		this.dbUtil = dbUtil;
	}

	public DbUtil getDbUtil() {
		return this.dbUtil;
	}

	/*------------------以下为考籍部分STATUS------------------*/
	/**
	 * 获取待初审的毕业申请内容
	 * 
	 * @param optUser
	 *            操作员对象
	 * @return String
	 */
	public String graNeedsFirstAudit(BaseUser optUser) {
		// 判断配置是否需要初审
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1)");
		sql.append(" from base_switch t");
		sql.append(" where t.switch_code = '003'");
		sql.append(" and t.switch_value like '%%%1$s%%'");
		if (dbUtil.getNum(String.format(sql.toString(), 1)) == 0)
			return "";

		// 判断用户是否有初审权限
		if (!chkPower(optUser, 61))
			return "";

		// 查询待初审人数
		String retString = "待初审人数：%1$s";
		sql = new StringBuilder();
		sql.append("select count(1)");
		sql.append(" from base_graduate_apply t, base_student_info i");
		sql.append(" where t.stud_exam_code = i.stud_exam_code");
		sql.append(" and t.graduate_first_audit_status = '0'");
		sql.append(" and t.graduate_second_audit_status = '0'");
		sql.append(" and t.graduate_third_audit_status = '0'");
		sql.append(" and t.graduate_final_audit_status = '0'");
		sql.append(" and t.diploma_num is null");
		sql.append(" and i.city_code = '%1$s'");
		String toDoNum = dbUtil.getFirstCol(String.format(sql.toString(),
				optUser.getCityCode()));
		return String.format(retString, toDoNum);
	}

	/**
	 * 获取待审核的毕业申请内容
	 * 
	 * @param optUser
	 *            操作员对象
	 * @return String
	 */
	public String graNeedsSecondAudit(BaseUser optUser) {
		// 判断配置是否需要审核
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1)");
		sql.append(" from base_switch t");
		sql.append(" where t.switch_code = '003'");
		sql.append(" and t.switch_value like '%%%1$s%%'");
		if (dbUtil.getNum(String.format(sql.toString(), 2)) == 0)
			return "";

		// 判断用户是否有审核权限
		if (!chkPower(optUser, 62))
			return "";

		// 查询待审核人数
		String retString = "待审核人数：%1$s";
		sql = new StringBuilder();
		sql.append("select count(1)");
		sql.append(" from base_graduate_apply t, base_student_info i");
		sql.append(" where t.stud_exam_code = i.stud_exam_code");
		if (dbUtil.getNum(String.format(sql.toString(), 1)) == 0)
			sql.append(" and t.graduate_first_audit_status = '0'");
		else
			sql.append(" and t.graduate_first_audit_status = '1'");
		sql.append(" and t.graduate_second_audit_status = '0'");
		sql.append(" and t.graduate_third_audit_status = '0'");
		sql.append(" and t.graduate_final_audit_status = '0'");
		sql.append(" and t.diploma_num is null");
		String toDoNum = dbUtil.getFirstCol(sql.toString());
		return String.format(retString, toDoNum);
	}

	/**
	 * 获取待复审的毕业申请内容
	 * 
	 * @param optUser
	 *            操作员对象
	 * @return String
	 */
	public String graNeedsThirdAudit(BaseUser optUser) {
		// 判断配置是否需要复审
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1)");
		sql.append(" from base_switch t");
		sql.append(" where t.switch_code = '003'");
		sql.append(" and t.switch_value like '%%%1$s%%'");
		if (dbUtil.getNum(String.format(sql.toString(), 3)) == 0)
			return "";

		// 判断用户是否有复审权限
		if (!chkPower(optUser, 63))
			return "";

		// 查询待复审人数
		String retString = "待复审人数：%1$s";
		sql = new StringBuilder();
		sql.append("select count(1)");
		sql.append(" from base_graduate_apply t, base_student_info i");
		sql.append(" where t.stud_exam_code = i.stud_exam_code");
		if (dbUtil.getNum(String.format(sql.toString(), 1)) == 0)
			sql.append(" and t.graduate_first_audit_status = '0'");
		else
			sql.append(" and t.graduate_first_audit_status = '1'");
		if (dbUtil.getNum(String.format(sql.toString(), 2)) == 0)
			sql.append(" and t.graduate_second_audit_status = '0'");
		else
			sql.append(" and t.graduate_second_audit_status = '1'");
		sql.append(" and t.graduate_third_audit_status = '0'");
		sql.append(" and t.graduate_final_audit_status = '0'");
		sql.append(" and t.diploma_num is null");
		String toDoNum = dbUtil.getFirstCol(sql.toString());
		return String.format(retString, toDoNum);
	}

	/**
	 * 获取待终审的毕业申请内容
	 * 
	 * @param optUser
	 *            操作员对象
	 * @return String
	 */
	public String graNeedsFinalAudit(BaseUser optUser) {
		// 判断配置是否需要终审
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1)");
		sql.append(" from base_switch t");
		sql.append(" where t.switch_code = '003'");
		sql.append(" and t.switch_value like '%%%1$s%%'");
		if (dbUtil.getNum(String.format(sql.toString(), 4)) == 0)
			return "";

		// 判断用户是否有终审权限
		if (!chkPower(optUser, 127))
			return "";

		// 查询待终审人数
		String retString = "待终审人数：%1$s";
		sql = new StringBuilder();
		sql.append("select count(1)");
		sql.append(" from base_graduate_apply t, base_student_info i");
		sql.append(" where t.stud_exam_code = i.stud_exam_code");
		if (dbUtil.getNum(String.format(sql.toString(), 1)) == 0)
			sql.append(" and t.graduate_first_audit_status = '0'");
		else
			sql.append(" and t.graduate_first_audit_status = '1'");
		if (dbUtil.getNum(String.format(sql.toString(), 2)) == 0)
			sql.append(" and t.graduate_second_audit_status = '0'");
		else
			sql.append(" and t.graduate_second_audit_status = '1'");
		if (dbUtil.getNum(String.format(sql.toString(), 3)) == 0)
			sql.append(" and t.graduate_third_audit_status = '0'");
		else
			sql.append(" and t.graduate_third_audit_status = '1'");
		sql.append(" and t.graduate_final_audit_status = '0'");
		sql.append(" and t.diploma_num is null");
		String toDoNum = dbUtil.getFirstCol(sql.toString());
		return String.format(retString, toDoNum);
	}

	/**
	 * 获取转考待课程确认人数
	 * 
	 * @param optUser
	 *            操作员对象
	 * @return String
	 */
	public String transOutForConfirm(BaseUser optUser) {
		// 判断操作员是否有转出课程确认的权限
		if (!chkPower(optUser, 76))
			return "";

		// 查询转出课程待确认的考生人数
		String retString = "课程待确认人数：%1$s";
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1)");
		sql.append(" from base_trans_out_apply t, base_student_info i");
		sql.append(" where t.stud_exam_code = i.stud_exam_code");
		sql.append(" and t.trans_out_submit_status = '1'");
		sql.append(" and t.trans_out_confirm_status = '0'");
		sql.append(" and i.city_code = '%1$s'");
		String toDoNum = dbUtil.getFirstCol(String.format(sql.toString(),
				optUser.getCityCode()));
		return String.format(retString, toDoNum);
	}

	/**
	 * 获取转考待转出登记人数
	 * 
	 * @param optUser
	 *            操作员对象
	 * @return String
	 */
	public String transOutForSign(BaseUser optUser) {
		// 判断操作员是否有转出登记（寄档案）的权限
		if (!chkPower(optUser, 77))
			return "";

		// 查询待转出登记的考生人数
		String retString = "待转出登记人数：%1$s";
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1)");
		sql.append(" from base_trans_out_apply t, base_student_info i");
		sql.append(" where t.stud_exam_code = i.stud_exam_code");
		sql.append(" and t.trans_out_submit_status = '1'");
		sql.append(" and t.trans_out_confirm_status = '1'");
		sql.append(" and t.trans_out_sign_status = '0'");
		sql.append(" and i.city_code = '%1$s'");
		String toDoNum = dbUtil.getFirstCol(String.format(sql.toString(),
				optUser.getCityCode()));
		return String.format(retString, toDoNum);
	}

	/**
	 * 获取转考档案录入后待复核的档案数
	 * 
	 * @param optUser
	 *            操作员对象
	 * @return String
	 */
	public String transInForAudit(BaseUser optUser) {
		// 判断操作员是否有转出登记的
		if (!chkPower(optUser, 135))
			return "";

		// 查询待复核的转入档案数
		String retString = "待复核的转入档案数：%1$s";
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1)");
		sql.append(" from base_trans_in_record t, base_user u");
		sql.append(" where t.trans_in_record_user = u.user_name");
		sql.append(" and t.record_status = '0'");
		sql.append(" and u.city_code = '%1$s'");
		String toDoNum = dbUtil.getFirstCol(String.format(sql.toString(),
				optUser.getCityCode()));
		return String.format(retString, toDoNum);
	}

	/*------------------以上为考籍部分STATUS------------------*/

	/*------------------以下为私有方法------------------*/
	/**
	 * 检查用户权限
	 * 
	 * @param optUser
	 *            操作员对象
	 * @param nodeId
	 *            菜单id
	 * @return boolean
	 */
	private boolean chkPower(BaseUser optUser, int nodeId) {
		return Power
				.check(
						optUser.getPowerArray(),
						(int) dbUtil
								.getNum(String
										.format(
												"select t.pow_ctrl_index from sys_tree t where t.node_id='%1$s'",
												nodeId)));
	}
}
