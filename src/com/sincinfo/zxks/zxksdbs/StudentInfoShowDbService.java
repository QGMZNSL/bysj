package com.sincinfo.zxks.zxksdbs;

import java.util.ArrayList;
import java.util.List;

import com.sincinfo.zxks.bean.BaseStudentInfo;
import com.sincinfo.zxks.bean.PassSyllabusQueryBean;
import com.sincinfo.zxks.common.db.DbUtil;
import com.sincinfo.zxks.common.util.StringTool;

/**
 * @see 考生信息查询显示 业务类
 * @author guanm
 * 
 */
public class StudentInfoShowDbService extends DbUtil {
	/**
	 * @see 根据考生 获得该考生下所考所有合格课程信息（包括课程编号，课程名称，学分，成绩等）
	 */
	public List<PassSyllabusQueryBean> getDataList(BaseStudentInfo stu) {
		StringBuilder sql = new StringBuilder();
		sql.append("select bs.syllabus_code");
		sql.append(" ,(select bp.syllabus_name from base_syllabus bp where bp.syllabus_code = bs.syllabus_code) as syllabus_name");
		sql.append("  ,bs.stud_pass_reason");
		sql.append(" ,(select sr.name from sys_code_pass_reason sr where sr.code=bs.stud_pass_reason) as stud_pass_reason_content");
		sql.append(" ,to_char(bs.stud_pass_time,'yyyy-mm-dd') as stud_pass_time");
		sql.append(" ,bs.stud_pass_remark");
		// 此处原为bs.stud_pass_reason，出现了2个bs.stud_pass_reason，
		// group by里有bs.stud_pass_remark，这里没有，所以改为bs.stud_pass_remark，zhangjb
		// 2012-11-23
		sql.append(" ,max(bs.stud_score) as stud_score");
		sql.append(" from base_student_pass_syllabus bs");
		sql.append(" where bs.stud_exam_code = ?");
		sql.append(" and bs.stud_score_disabled_status='0'");
		sql.append(" group by bs.syllabus_code ,bs.stud_pass_reason,stud_pass_time,bs.stud_pass_remark");
		// sql.append(" order by bs.syllabus_code"); // delete by litian
		// 2012-06-08
		sql.append(" order by stud_pass_time"); // add by litian 2012-06-08
		return super.getObjList(sql.toString(), PassSyllabusQueryBean.class,
				stu.getStudExamCode());
	}

	/**
	 * @see 格式化考生照片地址
	 */
	public String getStudPhoto(String photo) {
		String basePath = super.getPaths()[1];
		String subPath = super.getConfig("21");// 毕业证照片存放路径 id=21
		String fileUrl = basePath + subPath
				+ System.getProperty("file.separator") + photo;
		return fileUrl;
	}

	// add by litian 2012-05-14 for 丰富详细列表信息
	/**
	 * 根据准考证号获取该考生报考信息
	 * 
	 * @param studExamCode
	 *            准考证号
	 * @return List<String[]>
	 */
	public List<String[]> qryStudSiteUpInfo(String studExamCode) {
		StringBuilder sql = new StringBuilder();
		sql.append("select substr(su.examination_code, 1, 4) as siteYear,");
		sql.append(" (select su.syllabus_code || '-' || s.syllabus_name");
		sql.append(" from base_syllabus s");
		sql.append(" where s.syllabus_code = su.syllabus_code) as syllabusName,");
		sql.append(" (select et.examination_date || ' ' || et.examination_start_time || '～' ||");
		sql.append(" et.examination_end_time");
		sql.append(" from base_examination_time et");
		sql.append(" where et.examination_time_code = su.examination_time_code) as examTime,");
		sql.append(" su.stud_apply_payoff");
		sql.append(" from base_student_site_up su");
		sql.append(" where su.stud_exam_code = '%1$s'");
		sql.append(" order by su.examination_code desc, su.examination_time_code asc");
		List<String[]> siteUpList = super.getRsArrayList(
				String.format(sql.toString(), studExamCode), 4);
		return siteUpList;
	}

	/**
	 * 查询考生的免考记录
	 * 
	 * @param studExamCode
	 *            准考证号
	 * @return List<String[]>
	 */
	public List<String[]> qryStudAvoidList(String studExamCode) {
		StringBuilder sql = new StringBuilder();
		sql.append("select (select s.syllabus_code || '-' || s.syllabus_name");
		sql.append(" from base_syllabus s");
		sql.append(" where s.syllabus_code = r.syllabus_code) as syllabusName,");
		sql.append(" r.file_desc,");
		sql.append(" (select na.article_sub_title");
		sql.append(" from base_news_article na");
		sql.append(" where na.article_id = r.file_id) as article,");
		sql.append(" r.avoid_city_audit_status,");
		sql.append(" (select u.real_name from base_user u where u.user_name=r.avoid_city_audit_user) as auditUser,");
		sql.append(" to_char(r.avoid_city_audit_time, 'yyyy-mm-dd hh24:mi:ss'),");
		sql.append(" r.avoid_city_audit_reason");
		sql.append(" from base_avoid_record r");
		sql.append(" where r.stud_exam_code = '%1$s'");
		sql.append(" order by r.avoid_record_id asc");
		List<String[]> avoidList = super.getRsArrayList(
				String.format(sql.toString(), studExamCode), 7);
		return avoidList;
	}

	/**
	 * 查询考生的替换记录
	 * 
	 * @param studExamCode
	 *            准考证号
	 * @return List<String[]>
	 */
	public List<String[]> qryStudSubstituteList(String studExamCode) {
		StringBuilder sql = new StringBuilder();
		sql.append("select (select s.syllabus_code || '-' || s.syllabus_name");
		sql.append(" from base_syllabus s");
		sql.append(" where s.syllabus_code = r.syllabus_code) as syllabusName,");
		sql.append(" r.file_desc,");
		sql.append(" (select na.article_sub_title");
		sql.append(" from base_news_article na");
		sql.append(" where na.article_id = r.file_id) as article,");
		sql.append(" r.substitute_city_audit_status,");
		sql.append(" (select u.real_name");
		sql.append(" from base_user u");
		sql.append(" where u.user_name = r.substitute_city_audit_user) as auditUser,");
		sql.append(" to_char(r.substitute_city_audit_time, 'yyyy-mm-dd hh24:mi:ss'),");
		sql.append(" r.substitute_city_audit_reason");
		sql.append(" from base_substitute_record r");
		sql.append(" where r.stud_exam_code = '%1$s'");
		sql.append(" order by r.substitute_record_code asc");
		List<String[]> substituteList = super.getRsArrayList(
				String.format(sql.toString(), studExamCode), 7);
		return substituteList;
	}

	/**
	 * 查询考生的转考信息列表
	 * 
	 * @param studExamCode
	 *            准考证号
	 * 
	 * @return
	 */
	public List<String[]> qryStudTransInfoList(String studExamCode) {
		StringBuilder sql = new StringBuilder();
		sql.append("select trs.trans, trs.outTime, trs.outRegion, trs.inTime,");
		sql.append(" trs.inRegion, trs.subNum, trs.fullTrans");
		sql.append(" from (select '转入' as trans,");
		sql.append(" to_char(r.trans_out_date, 'yyyy-mm-dd') as outTime,");
		sql.append(" (select case");
		sql.append(" when cr.region_grade = '1' then");
		sql.append(" cr.region_name");
		sql.append(" else");
		sql.append(" cr.province_name || cr.region_name");
		sql.append(" end");
		sql.append(" from sys_code_region cr");
		sql.append(" where cr.region_code = a.trans_out_region) as outRegion,");
		sql.append(" to_char(a.stud_confirm_time, 'yyyy-mm-dd') as inTime,");
		sql.append(" (select '陕西省' || bc.city_name");
		sql.append(" from base_city bc");
		sql.append(" where bc.city_code = i.city_code) as inRegion,");
		sql.append(" (select count(1)");
		sql.append(" from base_trans_in_score tis");
		sql.append(" where tis.trans_in_record_id = r.trans_in_record_id) as subNum,");
		sql.append(" '-' as fullTrans");
		sql.append(" from base_trans_in_apply  a,");
		sql.append(" base_trans_in_record r,");
		sql.append(" base_student_info    i");
		sql.append(" where a.stud_exam_code = r.stud_exam_code");
		sql.append(" and a.original_exam_code = r.original_exam_code");
		sql.append(" and a.stud_exam_code = i.stud_exam_code");
		sql.append(" and a.stud_confirm_status = '1'");
		sql.append(" and a.stud_exam_code='%1$s'");
		sql.append(" union");
		sql.append(" select '转出' as trans,");
		sql.append(" to_char(o.trans_out_sign_time, 'yyyy-mm-dd') as outTime,");
		sql.append(" (select '陕西省' || bc.city_name");
		sql.append(" from base_city bc");
		sql.append(" where bc.city_code = i.city_code) as outRegion,");
		sql.append(" '-' as inTime,");
		sql.append(" (select case");
		sql.append(" when cr.region_grade = '1' then");
		sql.append(" cr.region_name");
		sql.append(" else");
		sql.append(" cr.province_name || cr.region_name");
		sql.append(" end");
		sql.append(" from sys_code_region cr");
		sql.append(" where cr.region_code = o.trans_out_region) as inRegion,");
		sql.append(" (select count(1)");
		sql.append(" from base_trans_out_apply_scores oas");
		sql.append(" where oas.trans_out_apply_id = o.trans_out_apply_id) as subNum,");
		sql.append(" o.trans_out_all_status as fullTrans");
		sql.append(" from base_trans_out_apply o, base_student_info i");
		sql.append(" where o.stud_exam_code = i.stud_exam_code");
		sql.append(" and o.trans_out_sign_status = '1'");
		sql.append(" and o.stud_exam_code='%1$s'");
		sql.append(" ) trs");
		sql.append(" order by trs.outTime");
		List<String[]> transInfoList = super.getRsArrayList(
				String.format(sql.toString(), studExamCode), 7);
		return transInfoList;
	}

	/**
	 * 查询考生持有证书列表
	 * 
	 * @param studExamCode
	 *            准考证号
	 * @return
	 */
	public List<String[]> qryStudCertification(String studExamCode) {
		StringBuilder sql = new StringBuilder();
		sql.append("select (select gc.name");
		sql.append(" from sys_code_graduate_certificate gc");
		sql.append(" where gc.code = r.certificat_type) as certificateType,");
		sql.append(" r.certificate_num,");
		sql.append(" to_char(r.certificate_date, 'yyyy-mm-dd') as certificateDate");
		sql.append(" from base_certificate_record r");
		sql.append(" where r.stud_exam_code = '%1$s'");
		sql.append(" order by r.certificate_date");
		List<String[]> certList = super.getRsArrayList(
				String.format(sql.toString(), studExamCode), 3);
		return certList;
	}

	/**
	 * 考生毕业信息
	 * 
	 * @param studExamCode
	 *            准考证号
	 * @return
	 */
	public List<String[]> qryStudGraduates(String studExamCode) {
		StringBuilder sql = new StringBuilder();
		sql.append("select s.diploma_num,");
		sql.append(" (select l.level_name");
		sql.append(" from base_level l");
		sql.append(" where l.level_code = s.level_code) as levelName,");
		sql.append(" (select p.pro_code || '-' || p.pro_name from base_pro p where p.pro_code = s.pro_code) as proName,");
		sql.append(" to_char(s.graduate_date, 'yyyy-mm-dd') as graduateDate,");
		sql.append(" s.academy_name as academyName");
		sql.append(" from base_graduate_student_info s");
		sql.append(" where s.stud_exam_code = '%1$s'");
		sql.append(" order by s.graduate_date");
		List<String[]> graduateList = super.getRsArrayList(
				String.format(sql.toString(), studExamCode), 5);
		return graduateList;
	}

	/**
	 * 查看考生考试科目违纪信息
	 * 
	 * @param studExamCode
	 *            准考证号
	 * @return
	 */
	public List<String[]> qryStudDeciplines(String studExamCode) {
		StringBuilder sql = new StringBuilder();
		StringBuilder sql1 = new StringBuilder();
		sql.append("select s.examination_code, s.syllabus_code,");
		sql.append(" (select b.syllabus_name from base_syllabus b where b.syllabus_code = s.syllabus_code)");
		sql.append(" as syllabus_name,");
		sql.append(" (select t.examination_date from base_examination_time t where t.examination_code = s.examination_code");
		sql.append(" and rownum < 2) as examination_date,");
		sql.append(" s.decipline_fact_code,");
		sql.append(" (select f.decipline_fact_describe from base_decipline_fact f where f.decipline_fact_code = s.decipline_fact_code");
		sql.append(" and f.examination_code = s.examination_code) as decipline_fact_describe,");
		sql.append(" (select f.decipline_p_m_describe from base_decipline_fact f where f.decipline_fact_code = s.decipline_fact_code");
		sql.append(" and f.examination_code = s.examination_code) as decipline_p_m_describe");
		sql.append(" from base_student_exam_score s where s.decipline_fact_code is not null and");
		sql.append(String.format(" s.stud_exam_code = '%1$s'", studExamCode));
		sql.append(" order by s.examination_code");
		List<String[]> decipline = super.getRsArrayList(sql.toString(), 7);
		List<String[]> deciplineList = new ArrayList<String[]>();
		List<String[]> deciplines = null;
		sql1.append("select s.examination_code, s.syllabus_code,");
		sql1.append(" (select b.syllabus_name from base_syllabus b where b.syllabus_code = s.syllabus_code)");
		sql1.append(" as syllabus_name,");
		sql1.append(" (select t.examination_date from base_examination_time t where t.examination_code = s.examination_code");
		sql1.append(" and rownum < 2) as examination_date,");
		sql1.append(" s.decipline_fact_code,");
		sql1.append(" (select f.decipline_fact_describe from base_decipline_fact f where f.decipline_fact_code = s.decipline_fact_code");
		sql1.append(" and f.examination_code = s.examination_code) as decipline_fact_describe,");
		sql1.append(" (select f.decipline_p_m_describe from base_decipline_fact f where f.decipline_fact_code = s.decipline_fact_code");
		sql1.append(" and f.examination_code = s.examination_code) as decipline_p_m_describe");
		sql1.append(" from base_student_exam_score s where s.decipline_fact_code is null and");
		sql1.append(String.format(" s.stud_exam_code = '%1$s'", studExamCode));
		sql1.append(" and s.examination_code = '%1$s'");
		String[] temp = null;
		int count = 0;
		for (String[] ss : decipline) {
			deciplineList.add(ss);
			if (temp == null) {
				temp = ss;
			}
			if (ss[6].equals("当次考试各科成绩无效")) {
				if (temp[0].equals(ss[0])) {
					count++;
				} else {
					count = 1;
				}
				if (count < 2) {
					deciplines = super.getRsArrayList(
							String.format(sql1.toString(), ss[0]), 7);
					for (String[] sss : deciplines) {
						if (StringTool.isEmpty(sss[6])) {
							sss[6] = ss[6];
						}
						deciplineList.add(sss);
					}
				}
				temp = ss;
			}
		}
		return deciplineList;
	}
}