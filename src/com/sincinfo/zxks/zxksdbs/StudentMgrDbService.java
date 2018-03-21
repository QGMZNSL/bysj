/**
 * Copyright(c) 2012 西安云海信息技术有限责任公司 All Rights Reserved<br>
 * @Title: com.sincinfo.zxks.zxksdbs.StudentMgrDbService.java<br>
 * @Description: 考生信息管理 <br>
 * <br>
 * @author litian<br>
 * @date Feb 6, 2012 9:13:20 AM 
 * @version V1.0   
 */
package com.sincinfo.zxks.zxksdbs;

import java.util.ArrayList;
import java.util.List;

import com.sincinfo.zxks.bean.BaseCity;
import com.sincinfo.zxks.bean.BaseStudentInfo;
import com.sincinfo.zxks.bean.BaseStudinfoChange;
import com.sincinfo.zxks.bean.BaseStudinfoChangeType;
import com.sincinfo.zxks.bean.BaseUser;
import com.sincinfo.zxks.common.db.DbUtil;
import com.sincinfo.zxks.common.util.MD5;
import com.sincinfo.zxks.common.util.Page;
import com.sincinfo.zxks.common.util.Power;
import com.sincinfo.zxks.common.util.StringTool;
import com.sincinfo.zxks.core.day.dailywork.busi.HistoryScore;

/**
 * @ClassName: StudentMgrDbService
 * @Description: 考生信息管理 <br>
 *               <br>
 * @author litian
 * @date Feb 6, 2012 9:13:20 AM<br>
 * 
 */
public class StudentMgrDbService {
	private DbUtil dbUtil = null;

	public StudentMgrDbService() {
		this.dbUtil = new DbUtil();
	}

	public DbUtil getDbUtil() {
		return this.dbUtil;
	}

	/**
	 * 根据用户的角色及所属地市获取地市列表
	 * 
	 * @param userRole
	 *            用户角色
	 * @param optRegion
	 *            地区代码
	 * @return List<BaseCity>
	 */
	public List<BaseCity> qryCityList(String userRole, String optRegion) {
		List<BaseCity> citys = null;
		StringBuilder sql = new StringBuilder();
		sql.append("select *");
		sql.append(" from base_city c");
		if (!Power.checkRole(userRole, Power.USER_ROLE_PROV)) {
			sql.append(String.format(" where c.city_code = '%1$s'", optRegion));
			sql
					.append(String.format(" or c.parent_region = '%1$s'",
							optRegion));
		}
		sql.append(" order by c.city_code asc");
		citys = dbUtil.getObjList(sql.toString(), BaseCity.class);
		return citys;
	}
	
	public List<BaseCity> qryCityList1(String userRole, String optRegion) {
		List<BaseCity> citys = null;
		StringBuilder sql = new StringBuilder();
		sql.append("select *");
		sql.append(" from base_city c");
		if (!Power.checkRole(userRole, Power.USER_ROLE_PROV) && !Power.checkRole(userRole, Power.USER_ROLE_CITY)) {
			sql.append(String.format(" where c.city_code = '%1$s'", optRegion));
			sql
					.append(String.format(" or c.parent_region = '%1$s'",
							optRegion));
		}
		sql.append(" order by c.city_code asc");
		citys = dbUtil.getObjList(sql.toString(), BaseCity.class);
		return citys;
	}


	/**
	 * 根据条件，分页查询考生信息列表
	 * 
	 * @param cityCode
	 *            地市代码
	 * @param studExamCode
	 *            考生准考证号
	 * @param studName
	 *            考生姓名
	 * @param studIdnum
	 *            考生证件号
	 * @param page
	 *            分页对象
	 * @return List<BaseStudentInfo>
	 */
	public List<BaseStudentInfo> qryStudentList(String cityCode,
			String studExamCode, String studName, String studIdnum,String preapplyCode, Page page) {
		List<BaseStudentInfo> studList = null;
		String sqlSelect = new BaseStudentInfo().createSqlWithoutWhere();
		StringBuilder sql = new StringBuilder();
		sql.append(sqlSelect);
//		sql.append(" where i.stud_information_confirm_sign = '1'");
		sql.append(" where 1=1");
		if ("610000".equals(StringTool.trim(cityCode))) {
		} else {
			sql.append(String.format(" and i.city_code = '%1$s'", cityCode));
		}
		if (!"".equals(StringTool.trim(studExamCode)))
			sql.append(String.format(" and i.stud_exam_code = '%1$s'",
					studExamCode.trim()));
		if (!"".equals(StringTool.trim(studIdnum)))
			sql.append(String.format(" and i.stud_idnum = '%1$s'", studIdnum
					.trim()));
		if (!"".equals(StringTool.trim(studName)))
			sql.append(String.format(" and i.stud_name like '%%%1$s%%'",
					studName.trim()));
		if (!StringTool.isEmpty(preapplyCode))
			sql.append(String.format(" and i.preapply_code like '%%%1$s%%'",
					preapplyCode.trim()));
		String sqlCount = String.format("select count(*) from (%1$s)", sql
				.toString());
		sql.append(" order by i.stud_exam_code asc");

		// 分页查询
		String sqlPage = page.setPagecount(dbUtil.getNum(sqlCount), sql
				.toString());
		studList = dbUtil.getObjList(sqlPage, BaseStudentInfo.class);
		return studList;
	}

	/**
	 * 根据考生准考证号，查询一个考生
	 * 
	 * @param studExamCode
	 *            考生准考证号
	 * @return BaseStudentInfo
	 */
	public BaseStudentInfo qryStudent(String studExamCode) {
		String sqlSelect = new BaseStudentInfo().createSqlWithoutWhere();
		StringBuilder sql = new StringBuilder();
		sql.append(sqlSelect);
		sql.append(" where i.stud_exam_code = '");
		sql.append(studExamCode.trim());
		sql.append("'");
		BaseStudentInfo student = dbUtil.getObject(sql.toString(),
				BaseStudentInfo.class);
		return student;
	}
	
	public List<BaseStudinfoChange> lBaseStudinfoChange(String studExamCode){
		String sql="select t.*,a.CHANGE_TYPE_NAME,a.CHANGE_STRING_TEMPLATE as CHANGE_TEMPLATE from base_studinfo_change t left join base_studinfo_change_type a on t.CHANGE_TYPE_CODE=a.change_type_code where t.STUD_EXAM_CODE='"+studExamCode+"' order by t.change_apply_time desc";
		List<BaseStudinfoChange> lBaseStudinfoChange=dbUtil.getObjList(sql,BaseStudinfoChange.class);
		return formart(lBaseStudinfoChange);
	}
	
	/**
	 * @see 格式化要显示的数据
	 */
	public List<BaseStudinfoChange> formart(List<BaseStudinfoChange> dataList) {
		if (dataList == null) {
			return null;
		}
		List<BaseStudinfoChange> list = new ArrayList<BaseStudinfoChange>();
		for (BaseStudinfoChange bean : dataList) {
			bean.setChangeApplyTime(bean.getChangeApplyTime().substring(0, 10));

			if (bean.getChangeTypeCode().equals("4")) {// 性别
				bean.setOldInfo(this.getName("sys_code_gender", bean
						.getOldInfo()));
				bean.setNewInfo(this.getName("sys_code_gender", bean
						.getNewInfo()));
			} else if (bean.getChangeTypeCode().equals("6")) {// 民族
				bean.setOldInfo(this
						.getName("sys_code_folk", bean.getOldInfo()));
				bean.setNewInfo(this
						.getName("sys_code_folk", bean.getNewInfo()));
			} else if (bean.getChangeTypeCode().equals("2")) {// 证件类型
				bean.setOldInfo(this.getName("sys_code_idno_type", bean
						.getOldInfo()));
				bean.setNewInfo(this.getName("sys_code_idno_type", bean
						.getNewInfo()));
			}
			String str = bean.getChangeTemplate();
			bean.setChangeTemplate(String.format(str, bean.getChangeTypeName(),
					bean.getOldInfo(), bean.getNewInfo()));
			list.add(bean);
		}
		return list;
	}
	
	/**
	 * @see 根据表 和code 获取name
	 */
	public String getName(String tableName, String code) {
		StringBuilder sql = new StringBuilder();
		sql.append("select b.name from " + tableName + " b where b.code=?");
		return dbUtil.getFirstCol(sql.toString(), code);
	}

	/**
	 * 重置考生密码为初始密码
	 * 
	 * @param studExamCode
	 *            考生准考证号
	 * @return boolean
	 */
	public boolean reseJtStudPwd(String studExamCode) {
		String pwd=dbUtil.getFirstCol(String.format("select STUD_IDNUM from BASE_STUDENT_INFO where stud_exam_code = '%1$s'",studExamCode));
		if(StringTool.isEmpty(pwd)) return false;
		String sql = String.format("update base_student_info i set i.stud_password = '%1$s' where i.stud_exam_code = '%2$s'",MD5.getMD5String(pwd),studExamCode);
		return dbUtil.saveOrUpdate(sql) == 1;
	}

	/**
	 * 获取考生信息变更的变更类型列表
	 * 
	 * @return List<BaseStudinfoChangeType>
	 */
	public List<BaseStudinfoChangeType> qryChangeTypeList() {
		List<BaseStudinfoChangeType> changeTypeList = null;
		String sql = "select t.* from base_studinfo_change_type t order by t.change_type_code";
		changeTypeList = dbUtil.getObjList(sql, BaseStudinfoChangeType.class);
		return changeTypeList;
	}

	/**
	 * 保存考生申请变更的记录
	 * 
	 * @param studExamCode
	 *            考生准考证号
	 * @param changeTypeCode
	 *            变更类型编号
	 * @param oldInfo
	 *            原始数据
	 * @param newInfo
	 *            新数据
	 * @param changeReason
	 *            申请变更的原因
	 * @param changeProve
	 *            变更证明材料
	 * @param changeApplyUser
	 *            填写变更申请的操作员
	 * @return boolean
	 */
	public boolean saveStudInfoChange(String studExamCode,
			String changeTypeCode, String oldInfo, String newInfo,
			String changeReason, String changeProve, String changeApplyUser,
			int fillinBy) {
		boolean saveFlag = false;
		StringBuilder sql = new StringBuilder();
		sql.append("insert into base_studinfo_change");
		sql.append(" (change_id,");
		sql.append(" stud_exam_code,");
		sql.append(" change_type_code,");
		sql.append(" old_info,");
		sql.append(" new_info,");
		sql.append(" change_reason,");
		sql.append(" change_prove,");
		sql.append(" change_apply_user,");
		sql.append(" change_apply_time,");
		sql.append(" fillin_by)");
		sql.append(" values");
		sql.append(" (Seq_Change.Nextval,");
		sql.append(" '%1$s',");
		sql.append(" '%2$s',");
		sql.append(" '%3$s',");
		sql.append(" '%4$s',");
		sql.append(" '%5$s',");
		sql.append(" '%6$s',");
		sql.append(" '%7$s',");
		sql.append(" sysdate,");
		sql.append(" '%8$s')");
		if ("".equals(StringTool.trim(oldInfo))) {
			oldInfo = "没有照片";
		}
		String changeSql = String.format(sql.toString(), studExamCode,
				changeTypeCode, oldInfo, newInfo, changeReason, changeProve,
				changeApplyUser, fillinBy);
		saveFlag = dbUtil.saveOrUpdate(changeSql) == 1;
		return saveFlag;
	}

	/**
	 * 查询符合条件的考生申请记录
	 * 
	 * @param cityCode
	 *            地市代码
	 * @param studExamCode
	 *            考生准考证号 不作为条件则传递""
	 * @param studIdnum
	 *            考生证件号 不作为条件则传递""
	 * @param changeAuditStatus
	 *            审核状态 不作为条件则传递""
	 * @param startDate
	 *            开始时间 不作为条件则传递""
	 * @param endDate
	 *            结束时间 不作为条件则传递""
	 * @param page
	 *            分页对象
	 * @return List<BaseStudinfoChange>
	 */
	public List<BaseStudinfoChange> qryStudChanges(String cityCode,
			String studExamCode, String studIdnum, String changeAuditStatus,
			String startDate, String endDate, int fillinBy, Page page) {
		List<BaseStudinfoChange> infoChangeList = null;
		StringBuilder sql = new StringBuilder();
		sql.append("select c.change_id,");
		sql.append(" c.stud_exam_code,");
		sql.append(" c.change_type_code,");
		sql.append(" c.old_info,");
		sql.append(" c.new_info,");
		sql.append(" c.change_reason,");
		sql.append(" c.change_prove,");
		sql.append(" c.change_audit_reason,");
		sql
				.append(" to_char(c.change_apply_time, 'yyyy-mm-dd') as changeApplyTime,");
		sql.append(" c.change_audit_status,");
		sql.append(" i.stud_name,");
		sql.append(" i.stud_idnum,");
		sql.append(" ct.change_type_name as changeTypeName,");
		sql.append(" ct.change_string_template as changeTemplate,");
		sql.append(" (select u.real_name");
		sql.append(" from base_user u");
		sql
				.append(" where u.user_name = c.change_apply_user) as changeApplyUser,");
		sql.append(" (select u.real_name");
		sql.append(" from base_user u");
		sql
				.append(" where u.user_name = c.change_audit_user) as changeAuditUser,");
		sql.append(" i.first_pro_code as proCode,");
		sql.append(" (select p.pro_name");
		sql.append(" from base_pro p");
		sql.append(" where p.pro_code = i.first_pro_code) as proName,");
		sql.append(" (select case");
		sql
				.append(" when (c.change_type_code = '1' or c.change_type_code = '3') and");
		sql.append(" count(1) >= 1 and c.change_audit_status <> '1' then");
		sql.append(" 1");
		sql.append(" else");
		sql.append(" 0");
		sql.append(" end");
		sql.append(" from base_studinfo_change ch");
		sql.append(" where ch.stud_exam_code = c.stud_exam_code");
		sql
				.append(" and ch.change_type_code in ('1', '3') and ch.change_audit_status <> '1'");
		sql
				.append(" and ch.change_type_code <> c.change_type_code) as isPeopleChange");
		sql.append(" from base_studinfo_change      c,");
		sql.append(" base_student_info         i,");
		sql.append(" base_studinfo_change_type ct");
		sql.append(" where c.stud_exam_code = i.stud_exam_code");
		sql.append(" and c.change_type_code = ct.change_type_code");
		sql.append(String.format(" and c.fillin_by = '%1$s'", fillinBy));
		sql.append(String.format(" and i.city_code = '%1$s'", cityCode));
		if (!"".equals(StringTool.trim(studExamCode)))
			sql.append(String.format(" and i.stud_exam_code = '%1$s'",
					studExamCode));
		if (!"".equals(StringTool.trim(studIdnum)))
			sql.append(String.format(" and i.stud_idnum = '%1$s'", studIdnum));
		if (!"".equals(StringTool.trim(changeAuditStatus)))
			sql.append(String.format(" and c.change_audit_status = '%1$s'",
					changeAuditStatus));
		if (!"".equals(StringTool.trim(startDate)))
			sql
					.append(String
							.format(
									" and c.change_apply_time >= to_date('%1$s 00:00:00', 'yyyy-mm-dd hh24:mi:ss')",
									startDate));
		if (!"".equals(StringTool.trim(endDate)))
			sql
					.append(String
							.format(
									" and c.change_apply_time <= to_date('%1$s 23:59:59', 'yyyy-mm-dd hh24:mi:ss')",
									endDate));
		String sqlCount = String.format("select count(*) from (%1$s)", sql
				.toString());
		sql.append(" order by c.change_apply_time asc");

		// 分页查询
		String sqlPage = page.setPagecount(dbUtil.getNum(sqlCount), sql
				.toString());
		infoChangeList = dbUtil.getObjList(sqlPage, BaseStudinfoChange.class);
		return infoChangeList;
	}

	/**
	 * 审核考生基础信息变更
	 * 
	 * @param changeId
	 *            申请编号
	 * @param optUser
	 *            操作员用户
	 * @param auditStatus
	 *            审核状态
	 * @param unAuditReason
	 *            审核不通过原因
	 * @return boolean
	 */
	public boolean auditInfoChangeApply(BaseStudinfoChange sic,
			BaseUser optUser, String auditStatus, String unAuditReason) {
		boolean auditFlag = false;
		ArrayList<String> sqlList = new ArrayList<String>();
		StringBuilder sql = null;

		// 更新审核状态
		sql = new StringBuilder();
		sql.append("update base_studinfo_change c");
		if ("1".equals(sic.getIsPeopleChange())) {
			// 换人
			if ("1".equals(optUser.getUserRole())) {
				sql.append(String.format(" set c.change_audit_status='%1$s',",
						auditStatus));
			} else {
				sql.append(String.format(" set c.change_audit_status='%1$s',",
						"9".equals(auditStatus) ? "8" : auditStatus));
			}
		} else {
			sql.append(String.format(" set c.change_audit_status='%1$s',",
					auditStatus));
		}
		sql.append(" c.change_audit_time = sysdate,");
		sql.append(String.format(" c.change_audit_user = '%1$s',", optUser
				.getUserName()));
		sql.append(String.format(" c.change_audit_reason = '%1$s'",
				unAuditReason == null ? "" : unAuditReason));
		sql.append(String.format(" where c.change_id = '%1$s'", sic
				.getChangeId()));
		sqlList.add(sql.toString());

		// 更新基础数据
		// 非换人 或者 是换人并且省级用户
		if ("0".equals(sic.getIsPeopleChange())
				|| ("1".equals(sic.getIsPeopleChange()) && "1".equals(optUser
						.getUserRole()))) {

			if (!"1".equals(auditStatus)) {
				sql = new StringBuilder();
				sql.append("update base_student_info i");
				if ("1".equals(sic.getChangeTypeCode())) {
					sql.append(String.format(" set i.stud_name='%1$s'", sic
							.getNewInfo()));
					sql.append(String.format(" where i.stud_name='%1$s'", sic
							.getOldInfo()));
				} else if ("2".equals(sic.getChangeTypeCode())) {
					sql.append(String.format(" set i.stud_idno_type = '%1$s'",
							sic.getNewInfo()));
					sql.append(String.format(" where i.stud_idno_type='%1$s'",
							sic.getOldInfo()));
				} else if ("3".equals(sic.getChangeTypeCode())) {
					sql.append(String.format(" set i.stud_idnum = '%1$s'", sic
							.getNewInfo()));
					sql.append(String.format(" where i.stud_idnum='%1$s'", sic
							.getOldInfo()));
				} else if ("4".equals(sic.getChangeTypeCode())) {
					sql.append(String.format(" set i.stud_gender = '%1$s'", sic
							.getNewInfo()));
					sql.append(String.format(" where i.stud_gender='%1$s'", sic
							.getOldInfo()));
				} else if ("5".equals(sic.getChangeTypeCode())) {
					sql.append(String.format(" set i.stud_birthday = '%1$s'",
							sic.getNewInfo()));
					sql.append(String.format(" where i.stud_birthday='%1$s'",
							sic.getOldInfo()));
				} else if ("6".equals(sic.getChangeTypeCode())) {
					sql.append(String.format(" set i.stud_folk = '%1$s'", sic
							.getNewInfo()));
					sql.append(String.format(" where i.stud_folk='%1$s'", sic
							.getOldInfo()));
				} else if ("7".equals(sic.getChangeTypeCode())) {
					sql.append(String.format(
							" set i.stud_photo_file_1 = '%1$s'", sic
									.getNewInfo()));
					sql.append(String.format(
							" where i.stud_photo_file_1='%1$s'", sic
									.getOldInfo()));
				}
				sql.append(String.format(" and i.stud_exam_code = '%1$s'", sic
						.getStudExamCode()));
				sqlList.add(sql.toString());
			}
		}

		// 执行并返回状态
		auditFlag = dbUtil.transExeSqls(sqlList) >= 0;
		return auditFlag;
	}

	/**
	 * 是否有已被审核的记录
	 * 
	 * @param changeIds
	 *            主键
	 * @return boolean true则存在已被审核的记录 false不存在
	 */
	public boolean isAnyAudited(String[] changeIds) {
		boolean auditFlag = false;
		StringBuilder sql = new StringBuilder();
		sql.append("select count(*)");
		sql.append(" from base_studinfo_change c");
		sql.append(" where c.change_audit_status = '9'");
		sql.append(" and c.change_id in (");
		for (int i = 0; i < changeIds.length; i++) {
			sql.append("'");
			sql.append(changeIds[i]);
			sql.append("'");
			if (i != changeIds.length - 1)
				sql.append(",");
		}
		sql.append(")");
		auditFlag = dbUtil.getNum(sql.toString()) >= 1;
		return auditFlag;
	}

	/**
	 * 是否有已被审核的记录
	 * 
	 * @param changeIds
	 *            主键
	 * @return boolean true则存在已被审核的记录 false不存在
	 */
	public boolean isAnyAuditedUnpass(String[] changeIds) {
		boolean auditFlag = false;
		StringBuilder sql = new StringBuilder();
		sql.append("select count(*)");
		sql.append(" from base_studinfo_change c");
		sql.append(" where c.change_audit_status = '1'");
		sql.append(" and c.change_id in (");
		for (int i = 0; i < changeIds.length; i++) {
			sql.append("'");
			sql.append(changeIds[i]);
			sql.append("'");
			if (i != changeIds.length - 1)
				sql.append(",");
		}
		sql.append(")");
		auditFlag = dbUtil.getNum(sql.toString()) >= 1;
		return auditFlag;
	}

	/**
	 * 更新照片申请记录的oldInfo字段
	 * 
	 * @param studExamCode
	 *            考生准考证号
	 * @param bakFile
	 *            原始信息
	 * @return boolean
	 */
	public boolean updatePhoOldInfo(String changeId, String bakFile) {
		boolean flag = false;
		String sql = "update base_studinfo_change c set c.old_info = '%2$s' where c.change_id = '%1$s'";
		sql = String.format(sql, changeId, bakFile);
		flag = dbUtil.saveOrUpdate(sql) == 1;
		return flag;
	}

	/**
	 * 根据标示获取一个考生基础信息变更申请
	 * 
	 * @param changeId
	 *            主键标示
	 * @return BaseStudinfoChange
	 */
	public BaseStudinfoChange qryStudinfoChange(String changeId) {
		BaseStudinfoChange bsc = new BaseStudinfoChange();
		StringBuilder sqlBuf = new StringBuilder();
		sqlBuf.append("select c.*,");
		sqlBuf.append(" (select case");
		sqlBuf
				.append(" when (c.change_type_code = '1' or c.change_type_code = '3') and");
		sqlBuf.append(" count(1) >= 1 and c.change_audit_status <> '1' then");
		sqlBuf.append(" 1");
		sqlBuf.append(" else");
		sqlBuf.append(" 0");
		sqlBuf.append(" end");
		sqlBuf.append(" from base_studinfo_change ch");
		sqlBuf.append(" where ch.stud_exam_code = c.stud_exam_code");
		sqlBuf
				.append(" and ch.change_type_code in ('1', '3') and ch.change_audit_status <> '1'");
		sqlBuf
				.append(" and ch.change_type_code <> c.change_type_code) as isPeopleChange");
		sqlBuf.append(" from base_studinfo_change c where c.change_id='%1$s'");
		bsc = dbUtil.getObject(String.format(sqlBuf.toString(), changeId),
				BaseStudinfoChange.class);
		return bsc;
	}

	/**
	 * 取得所有地市已核对的，属于该人的所有记录
	 * 
	 * @return
	 */
	public List<BaseStudinfoChange> qryStudinfoChangeOnlyAudByCity(
			String[] changeIds) {
		List<BaseStudinfoChange> audByCityList = null;
		StringBuilder sqlBuf = new StringBuilder();
		sqlBuf.append("select c.*,");
		sqlBuf.append(" (select case");
		sqlBuf
				.append(" when (c.change_type_code = '1' or c.change_type_code = '3') and");
		sqlBuf.append(" count(1) >= 1 and c.change_audit_status <> '1' then");
		sqlBuf.append(" 1");
		sqlBuf.append(" else");
		sqlBuf.append(" 0");
		sqlBuf.append(" end");
		sqlBuf.append(" from base_studinfo_change ch");
		sqlBuf.append(" where ch.stud_exam_code = c.stud_exam_code");
		sqlBuf
				.append(" and ch.change_type_code in ('1', '3') and ch.change_audit_status <> '1'");
		sqlBuf
				.append(" and ch.change_type_code <> c.change_type_code) as isPeopleChange");
		sqlBuf
				.append(" from base_studinfo_change c where c.change_audit_status = '8'");
		sqlBuf.append(" and c.stud_exam_code in (select bsc.stud_exam_code");
		sqlBuf
				.append(" from base_studinfo_change bsc where bsc.change_id in (");
		for (int i = 0; i < changeIds.length; i++) {
			sqlBuf.append("'");
			sqlBuf.append(changeIds[i]);
			sqlBuf.append("'");
			if (i != changeIds.length - 1) {
				sqlBuf.append(",");
			}
		}
		sqlBuf.append("))");
		audByCityList = dbUtil.getObjList(sqlBuf.toString(),
				BaseStudinfoChange.class);
		return audByCityList;
	}

	/**
	 * 性别名称
	 * 
	 * @param gender
	 * @return
	 */
	public String getGenderName(String gender) {
		String sql = String.format(
				"select g.name from sys_code_gender g where g.code='%1$s'",
				gender);
		return dbUtil.getFirstCol(sql);
	}

	/**
	 * 证件类型名称
	 * 
	 * @param idnoType
	 * @return
	 */
	public String getIdnoTypeName(String idnoType) {
		String sql = String.format(
				"select i.name from sys_code_idno_type i where i.code='%1$s'",
				idnoType);
		return dbUtil.getFirstCol(sql);
	}

	/**
	 * 民族名称
	 * 
	 * @param fork
	 * @return
	 */
	public String getForkName(String fork) {
		String sql = String.format(
				"select f.name from sys_code_folk f where f.code='%1$s'", fork);
		return dbUtil.getFirstCol(sql);
	}

	/**
	 * 根据考生准考证号查询该考生在本省考试的历史成绩
	 * 
	 * @param studExamCode
	 *            考生准考证号
	 * @return List<HistoryScore>
	 */
	public List<HistoryScore> qryScoreHistory(String studExamCode) {
		List<HistoryScore> shList = null;
		StringBuilder sql = new StringBuilder();
		sql
				.append("select (select e.examination_year || '年' || e.examination_month || '月'");
		sql.append(" from base_examination e");
		sql
				.append(" where e.examination_code = t.examination_code) as examName,");
		sql.append(" s.syllabus_code,");
		sql.append(" s.syllabus_name,");
		sql.append(" t.stud_score,");
		sql.append(" t.lack_code,");
		sql.append(" t.decipline_fact_code,");
		sql.append(" (select f.decipline_fact_describe");
		sql.append(" from base_decipline_fact f");
		sql
				.append(" where f.decipline_fact_code = t.decipline_fact_code) as decipline_fact_describe,");
		sql.append(" (select f.decipline_punish_code_array");
		sql.append(" from base_decipline_fact f");
		sql
				.append(" where f.decipline_fact_code = t.decipline_fact_code) as decipline_punish_code_array,");
		sql.append(" (select f.decipline_p_m_describe");
		sql.append(" from base_decipline_fact f");
		sql
				.append(" where f.decipline_fact_code = t.decipline_fact_code) as decipline_p_m_descript,");
		sql.append(" t.stud_exam_code");
		sql.append(" from base_student_exam_score t, base_syllabus s");
		sql.append(" where t.syllabus_code = s.syllabus_code");
		sql.append(" and t.stud_exam_code = '%1$s'");
		sql.append(" order by t.examination_code, t.stud_score_id");
		shList = dbUtil.getObjList(String.format(sql.toString(), studExamCode),
				HistoryScore.class);
		return shList;
	}

	/**
	 * 获取考生姓名
	 * 
	 * @param studExamCode
	 *            考生准考证号
	 * @return
	 */
	public String qryStudName(String studExamCode) {
		return dbUtil
				.getFirstCol(String
						.format(
								"select i.stud_name from base_student_info i where i.stud_exam_code = '%1$s'",
								studExamCode));
	}

	/**
	 * 是否在申办时间段内
	 * 
	 * @return
	 */
	public boolean duringGraApply() {
		StringBuilder sql = new StringBuilder();
		sql.append("select case");
		sql.append(" when to_date(substr(t.switch_value, 0, 10) || ' 00:00:00',");
		sql.append(" 'yyyy-mm-dd hh24:mi:ss') <= sysdate and");
		sql.append(" to_date(substr(t.switch_value, 12, 10) || ' 23:59:59',");
		sql.append(" 'yyyy-mm-dd hh24:mi:ss')+80 >= sysdate then");  //zq 目前没有设置终审时间，默认毕业申请结束后80天
		sql.append(" 1");
		sql.append(" else");
		sql.append(" 0");
		sql.append(" end");
		sql.append(" from base_switch t");
		sql.append(" where t.switch_code = '001'");
		return 1 == dbUtil.getNum(sql.toString());
	}
	
	/**
	 * 根据条件，查询考生信息列表
	 * 
	 * @param cityCode
	 *            地市代码
	 * @param studExamCode
	 *            考生准考证号
	 * @param studName
	 *            考生姓名
	 * @param studIdnum
	 *            考生证件号
	 * @return
	 */
	public List<String[]> expStuInfo(String cityCode, String studExamCode,
			String studName, String studIdnum) {
		StringBuilder sql = new StringBuilder();
		sql.append("select i.stud_exam_code,i.stud_name,i.stud_idnum,");
		sql.append(" (select it.name from sys_code_idno_type it where it.code = i.stud_idno_type) as studIdnoTypeName,");
		sql.append(" (select cg.name from sys_code_gender cg where cg.code = i.stud_gender) as genderName,i.stud_birthday,");
		sql.append(" (select cf.name from sys_code_folk cf where cf.code=i.stud_folk) as studFolkName,");
		sql.append(" (select cp.name from sys_code_politics cp where cp.code=i.stud_politics) as studPoliticsName,");
		sql.append(" (select co.name from sys_code_occupation co where co.code=i.stud_occupation) as studOccupationName,");
		sql.append(" (select csa.name from sys_code_school_age csa where csa.code=i.stud_school_age) as studSchoolAgeName,");
		sql.append(" (select chc.name from sys_code_hukou_character chc where chc.code=i.stud_hukou_character) as studHukouCharacterName,");
		sql.append(" (select cr.region_name from sys_code_region cr where cr.region_code=i.stud_hukou_location) as studHukouLocationName,");
		sql.append(" i.stud_telephone,i.stud_mobile_phone,i.stud_postal_address,i.stud_postal_code,i.city_code,i.exam_area_code,");
		sql.append(" (select bp.pro_name from base_pro bp where bp.pro_code=i.first_pro_code) as firstProName,");
		sql.append(" (select bp.pro_name from base_pro bp where bp.pro_code=i.pro_code) as proName,");
		sql.append(" (case when i.stud_type_code = 0 then '面向社会考生' else '助学单位考生' end)as studTypeName,");
		sql.append(" (select l.level_name from base_level l where l.level_code=i.level_code) as levelName,");
		sql.append(" to_char(i.pre_site_date, 'yyyy-mm-dd') as preSiteDate,i.examination_code");
		sql.append(" from base_student_info i where 1=1");
		if ("610000".equals(StringTool.trim(cityCode))) {
		} else {
			sql.append(String.format(" and i.city_code = '%1$s'", cityCode));
		}
		if (!"".equals(StringTool.trim(studExamCode)))
			sql.append(String.format(" and i.stud_exam_code = '%1$s'",
					studExamCode.trim()));
		if (!"".equals(StringTool.trim(studIdnum)))
			sql.append(String.format(" and i.stud_idnum = '%1$s'",
					studIdnum.trim()));
		if (!"".equals(StringTool.trim(studName)))
			sql.append(String.format(" and i.stud_name like '%%%1$s%%'",
					studName.trim()));
		sql.append(" order by i.stud_exam_code asc");
		return dbUtil.getRsArrayList(sql.toString(), 24);
	}
}