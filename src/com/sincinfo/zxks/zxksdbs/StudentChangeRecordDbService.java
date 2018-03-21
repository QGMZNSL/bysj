package com.sincinfo.zxks.zxksdbs;

import java.util.ArrayList;
import java.util.List;

import com.sincinfo.zxks.bean.BaseCity;
import com.sincinfo.zxks.bean.BaseStudentInfo;
import com.sincinfo.zxks.bean.BaseStudinfoChange;
import com.sincinfo.zxks.bean.BaseSwitch;
import com.sincinfo.zxks.common.db.DbUtil;
import com.sincinfo.zxks.common.util.StringTool;

/**
 * @see 考生基本信息变更 业务类
 * @author guanm
 * 
 */
public class StudentChangeRecordDbService extends DbUtil {

	// 视图sql
	private final String viewSql = "select c.*,(select u.real_name from base_user u where u.user_name=c.change_apply_user) as change_apply_user_real_name,(select u.real_name from base_user u where u.user_name=c.change_audit_user) as change_audit_user_real_name,(select ct.change_type_name from base_studinfo_change_type ct where ct.change_type_code = c.change_type_code) as change_type_name ,(select ct.change_string_template from base_studinfo_change_type ct where ct.change_type_code = c.change_type_code) as change_template ,(select s.stud_name from base_student_info s where s.stud_exam_code=c.stud_exam_code) as stud_name ,(select s.stud_idnum from base_student_info s where s.stud_exam_code=c.stud_exam_code) as stud_idnum,(select s.pro_code from base_student_info s where s.stud_exam_code=c.stud_exam_code) as pro_code,(select p.pro_name from base_pro p where p.pro_code=(select s.pro_code from base_student_info s where s.stud_exam_code=c.stud_exam_code)) as pro_name,(select s.city_code from base_student_info s where s.stud_exam_code=c.stud_exam_code) as city_code from base_studinfo_change c where 1=1 %1$s order by c.change_apply_time desc";
	private final String inView = " and c.change_audit_status='%1$s'";

	/**
	 * @see 查询 获得sql
	 */
	public String getChangeRecordListSql(String studExamCode, String cityCode, 
			String startDate, String endDate, String auditStatus, int fillinBy) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from (");
		auditStatus = StringTool.trim( auditStatus);
		if ( fillinBy == -1) {
			// 不分考务考籍
			if ( "".equals(auditStatus)) {
				sql.append(String.format(viewSql, auditStatus));
			} else {
				sql.append(String.format(viewSql, String.format(inView, auditStatus)));
			}
		} else {
			// 分考务考籍
			if ( "".equals(auditStatus)) {
				sql.append(String.format(viewSql, String.format(" and c.fillin_by = '%1$s'", fillinBy) + auditStatus));
			} else {
				sql.append(String.format(viewSql, String.format(" and c.fillin_by = '%1$s'", fillinBy) + String.format(inView, auditStatus)));
			}
		}
		sql.append(") n");
		sql.append(" where 1=1");
		if (studExamCode != null && !studExamCode.equals("")) {
			sql.append(" and n.stud_exam_code='%1$s'");
		}
		if (cityCode != null && !cityCode.equals("")) {
			sql.append(" and n.city_code='%2$s'");
		}
		if (startDate != null && !startDate.equals("")) {
			sql.append(" and n.change_apply_time>=to_date('%3$s'||' 00:00:00','yyyy-mm-dd hh24:mi:ss')");
		}
		if (endDate != null && !endDate.equals("")) {
			sql.append(" and n.change_apply_time<=to_date('%4$s'||' 23:59:59','yyyy-mm-dd hh24:mi:ss')");
		}
		// System.out.println(String.format(sql.toString(),
		// studExamCode,cityCode,startDate,endDate));
		return String.format(sql.toString(), studExamCode, cityCode, startDate,
				endDate);
	}

	/**
	 * @see 查询 获得count
	 * @param studExamCode
	 * @param cityCode
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public long getChangeRecordCount(String studExamCode, String cityCode,
			String startDate, String endDate, String auditStatus, int fillinBy) {
		StringBuilder sql = new StringBuilder();
		sql.append("select count(*) from (");
		sql.append(this.getChangeRecordListSql(studExamCode, cityCode,
				startDate, endDate, auditStatus, fillinBy));
		sql.append(")");
		return super.getNum(sql.toString());
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
	 * @see 获得地市对象
	 */
	public BaseCity getBaseCity(String cityCode) {
		String sql = "select t.* from base_city t where t.city_code=?";
		return super.getObject(sql, BaseCity.class, cityCode);
	}

	/**
	 * @see 获得所有地市
	 */
	public List<BaseCity> getAllCity() {
		String sql = "select t.* from base_city  t order by t.city_code";
		return super.getObjList(sql, BaseCity.class);
	}

	/**
	 * @see 根据 考生准考证号 获得考生信息
	 */
	public BaseStudentInfo getStudByCode(String studCode) {
		BaseStudentInfo stud = new BaseStudentInfo();
		StringBuilder sql = new StringBuilder();
		sql.append("select bean.* from (");
		sql.append(stud.createSqlWithoutWhere());
		sql.append(") bean");
		sql.append(" where bean.stud_exam_code=?");
		return super.getObject(sql.toString(), BaseStudentInfo.class, studCode);
	}

	/**
	 * @see 根据表 和code 获取name
	 */
	public String getName(String tableName, String code) {
		StringBuilder sql = new StringBuilder();
		sql.append("select b.name from " + tableName + " b where b.code=?");
		return super.getFirstCol(sql.toString(), code);
	}
	
	/**
	 * 
	 * @param code
	 * @return
	 */
	public BaseSwitch getSwitch(String code) {
	    return super.getObject("select * from base_switch where switch_code=?", BaseSwitch.class, code);
	}

}