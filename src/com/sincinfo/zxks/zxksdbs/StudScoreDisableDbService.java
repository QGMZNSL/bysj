package com.sincinfo.zxks.zxksdbs;

import java.util.ArrayList;
import java.util.List;

import com.sincinfo.zxks.bean.BaseCity;
import com.sincinfo.zxks.bean.BaseExamination;
import com.sincinfo.zxks.bean.BaseStudentPassSyllabus;
import com.sincinfo.zxks.bean.BaseSyllabus;
import com.sincinfo.zxks.bean.BaseUser;
import com.sincinfo.zxks.common.db.DbUtil;
import com.sincinfo.zxks.common.util.Page;

/**
 * @see 违纪考生处理 业务类
 * @author guanm
 * 
 */
public class StudScoreDisableDbService extends DbUtil {
	/**
	 * @see 获取所有考试的考试年月
	 */
	public List<BaseExamination> getALlExamination() {
		String sql = "select t.* from base_examination t order by examination_year desc,examination_month desc";
		return super.getObjList(sql, BaseExamination.class);
	}

	/**
	 * @see 获得所有地市
	 */
	public List<BaseCity> getAllCity() {
		String sql = "select t.* from base_city  t order by t.city_code";
		return super.getObjList(sql, BaseCity.class);
	}

	/**
	 * @see 获得地市对象
	 */
	public BaseCity getBaseCity(String cityCode) {
		String sql = "select t.* from base_city t where t.city_code=?";
		return super.getObject(sql, BaseCity.class, cityCode);
	}

	/**
	 * 查询课程
	 * 
	 * @param syllabusCode
	 *            课程代码
	 * @param syllabusName
	 *            课程名称
	 * @param page
	 *            分页对象
	 * @return List<BaseSyllabus>
	 */
	public List<BaseSyllabus> qrySctSyllabuses(String syllabusCode,
			String syllabusName, Page page) {
		List<BaseSyllabus> syllabusLise = null;
		StringBuilder sql = new StringBuilder();
		sql.append("select *");
		sql.append(" from base_syllabus s");
		sql.append(" where 1=1");
		if (!"".equals(syllabusCode))
			sql.append(String.format(" and s.syllabus_code = '%1$s'",
					syllabusCode));
		if (!"".equals(syllabusName))
			sql.append(String.format(" and s.syllabus_name like '%%%1$s%%'",
					syllabusName));
		String sqlCount = String.format("select count(*) from (%1$s)", sql
				.toString());
		sql.append(" order by s.syllabus_code asc");

		// 分页查询
		String sqlPage = page.setPagecount(super.getNum(sqlCount), sql
				.toString());
		syllabusLise = super.getObjList(sqlPage, BaseSyllabus.class);
		return syllabusLise;
	}

	/**
	 * @see 根据条件 获得违纪情况 sql
	 */
	public String getDataSql(String examCode,
			String cityCode, String syllCode, String studExamCode,
			String studName, String studIdnum, String status) {
		StringBuilder sql=new StringBuilder();
		sql.append("select s.stud_exam_code,s.stud_name,s.stud_idnum,p.syllabus_code");
		sql.append(",(select sy.syllabus_name from base_syllabus sy where sy.syllabus_code = p.syllabus_code) as syllabus_name");
		sql.append(",p.stud_score,p.stud_score_disabled_status,p.stud_pass_id,p.examination_code");
		sql.append(" from base_student_pass_syllabus p");
		sql.append(" left join base_student_info s on p.stud_exam_code = s.stud_exam_code");
		sql.append(" where 1=1");
//		sql.append(String.format(" where p.examination_code = '%1$s'",examCode));
		if(studExamCode!=null&&!studExamCode.equals("")){
			sql.append(String.format(" and p.stud_exam_code='%1$s'",studExamCode));
		}
		
		StringBuilder sql2=new StringBuilder();
		sql2.append("select * from (");
		sql2.append(sql.toString());
		sql2.append(") v");
		sql2.append(" where 1=1");
		if(examCode!=null&&!examCode.equals("")){
			sql2.append(String.format(" and v.examination_code='%1$s'",examCode));		
		}
//		if(syllCode!=null&&!syllCode.equals("")){
//			sql.append(String.format(" and p.syllabus_code='%1$s'",syllCode));		
//		}
//		if(cityCode!=null&&!cityCode.equals("")){
//			sql.append(String.format(" and s.city_code='%1$s'",cityCode));
//		}	
//		if(studName!=null&&!studName.equals("")){
//			sql.append(String.format(" and s.stud_name like '%%%1$s%%'",studName));
//		}
//		if(studIdnum!=null&&!studIdnum.equals("")){
//			sql.append(String.format(" and s.stud_idnum='%1$s'",studIdnum));
//		}
		if(status!=null&&!status.equals("")){
			sql2.append(String.format(" and v.stud_score_disabled_status='%1$s'",status));
		}
		sql2.append(" order by v.stud_pass_id");
		return sql2.toString();
	}
	/**
	 * @see 获得违纪情况 count
	 */
	public long getDataCount(String examCode,
			String cityCode, String syllCode, String studExamCode,
			String studName, String studIdnum, String status){
		StringBuilder sql=new StringBuilder();
		sql.append("select count(*) from (");
		sql.append(this.getDataSql(examCode, cityCode, syllCode, studExamCode, studName, studIdnum, status));
		sql.append(")");
		return super.getNum(sql.toString());
	}
	/**
	 * @see do 撤销时间
	 */
	public boolean disScore(String[] psIds,String disabled,BaseUser user){
		String format="update base_student_pass_syllabus set stud_score_disabled_status='1',stud_score_disabled_reason='%1$s',stud_score_disabled_user='%2$s',stud_score_disabled_time=sysdate where stud_pass_id=%3$s";
		ArrayList<String> sqls=new ArrayList<String>();
		for(String id:psIds){
			sqls.add(String.format(format, disabled,user.getUserName(),id));
		}
		int k=super.transExeSqls(sqls);
		if(k==psIds.length){
			return true;
		}else{
			return false;
		}
	}
	/**
	 * @see  恢复成绩
	 */
	public boolean reScore(String [] psIds){
		String format="update base_student_pass_syllabus set stud_score_disabled_status='0',stud_score_disabled_reason=null,stud_score_disabled_user=null,stud_score_disabled_time=null where stud_pass_id=%1$s";
		ArrayList<String> sqls=new ArrayList<String>();
		for(String id:psIds){
			sqls.add(String.format(format,id));
		}
		int k=super.transExeSqls(sqls);
		if(k==psIds.length){
			return true;
		}else{
			return false;
		}
	}
	
}