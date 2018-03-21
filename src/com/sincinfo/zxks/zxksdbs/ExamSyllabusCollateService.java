package com.sincinfo.zxks.zxksdbs;

import java.util.List;

import com.sincinfo.zxks.bean.SingleEntity;
import com.sincinfo.zxks.common.db.DbUtil;
import com.sincinfo.zxks.common.util.StringTool;

/**
 * 开考专业课程校对
 * 
 * @author duanwj 2013-4-16
 * 
 */
public class ExamSyllabusCollateService {

	private DbUtil dbUtil = null;

	/**
	 * 构造
	 */
	public ExamSyllabusCollateService() {
		this.dbUtil = new DbUtil();
	}

	/**
	 * 查询当前考试编号
	 * 
	 * @return
	 */
	public String getCurrExam() {
		String sql = "select e.examination_code from base_examination e where e.nonce = '1'";
		String currExaminationCode = dbUtil.getFirstCol(sql);
		currExaminationCode = currExaminationCode == null ? ""
				: currExaminationCode;
		return currExaminationCode;
	}

	/**
	 * 查询考试年月列表
	 * 
	 * @return List<SingleEntity>
	 */
	public List<SingleEntity> qryExamYmList() {
		List<SingleEntity> ymList = null;
		StringBuilder sql = new StringBuilder();
		sql.append("select e.examination_code as entityCode,");
		sql.append(" (e.examination_year || '年' ||");
		sql.append(" substr(('00' || e.examination_month),");
		sql.append(" length(('00' || e.examination_month)) - 1, 2) || '月'");
		sql.append(") as entityName");
		sql.append(" from base_examination e");
		sql.append(" order by e.examination_year desc, e.examination_month desc");
		ymList = dbUtil.getObjList(sql.toString(), SingleEntity.class);
		return ymList;
	}

	/**
	 * 查询重复课程
	 * 
	 * @param examinactionCode
	 *            考试编号
	 * @param syllabusCode
	 *            科目代码
	 * @param syllabusName
	 *            科目名称
	 * @return List<String[]>
	 */
	public List<String[]> getSyllabus(String examinactionCode,
			String syllabusCode, String syllabusName) {
		StringBuilder sql = new StringBuilder();
		sql.append("select bet.examination_date, bet.examination_time, bes.syllabus_code, bs.syllabus_name");
		sql.append(" from base_examination_time bet, base_examination_pro bep, base_examination_syllabus bes, base_syllabus bs");
		sql.append(" where bet.examination_code = bep.examination_code");
		sql.append(" and bet.examination_time_code = bes.examination_time_code");
		sql.append(" and bep.examination_code = bes.examination_code");
		sql.append(" and bep.pro_code = bes.pro_code");
		sql.append(" and bes.syllabus_code = bs.syllabus_code");
		sql.append(String.format(" and bet.examination_code = '%1$s'",
				examinactionCode));
		if (StringTool.isEmpty(syllabusCode)
				&& StringTool.isEmpty(syllabusName)) {
			sql.append(" and bes.syllabus_code in(");
			sql.append("select syllabus_code");
			sql.append(" from (select count(distinct bet.examination_date || '-' || bet.examination_time) as syllabus_num,");
			sql.append(" bes.syllabus_code");
			sql.append(" from base_examination_time bet, base_examination_pro bep, base_examination_syllabus bes");
			sql.append(" where bet.examination_code = bep.examination_code");
			sql.append(" and bet.examination_time_code = bes.examination_time_code");
			sql.append(" and bep.examination_code = bes.examination_code");
			sql.append(" and bep.pro_code = bes.pro_code");
			sql.append(String.format(" and bet.examination_code = '%1$s'",
					examinactionCode));
			sql.append(" group by bes.syllabus_code) t");
			sql.append(" where t.syllabus_num > 1)");
		}
		if (!StringTool.isEmpty(syllabusCode))
			sql.append(String.format(" and bes.syllabus_code = '%1$s'",
					syllabusCode));
		if (!StringTool.isEmpty(syllabusName))
			sql.append(String.format(" and bs.syllabus_name = '%1$s'",
					syllabusName));
		sql.append(" group by bet.examination_date, bet.examination_time,");
		sql.append(" bes.syllabus_code, bs.syllabus_name");
		sql.append(" order by bet.examination_date, bet.examination_time, bes.syllabus_code");
		return dbUtil.getRsArrayList(sql.toString(), 4);
	}

}
