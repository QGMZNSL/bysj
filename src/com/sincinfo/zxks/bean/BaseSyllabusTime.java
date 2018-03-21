package com.sincinfo.zxks.bean;

public class BaseSyllabusTime {
	private String syllabusCode;// SYLLABUS_CODE课程代码
	private String applyYear;// APPLY_YEAR考试年月
	private String proCode;// PRO_CODE专业代码
	private String proName;// PRO_NAME专业名称
	private String syllabusName;// SYLLABUS_NAME课程名称
	private String examinationTimeCode;// EXAMINATION_TIME_CODE考试时间编号
	private String countryProvince;// COUNTRY_PROVINCE国家还是省，1国家，2省
	private String commSysllabus;// COMM_SYLLABUS是否公共课：0、公共课，1、共同课
	// 以下为增加Bean
	private String examinationDate;// EXAMINATION_DATE考试日期---查询用
	private String examinationTime;// EXAMINATION_TIME考试时间段---查询用
	private String commView;// 是否显示公共课 0显示，1不显示，要遍历公共课到各专业中---功能模块用

	private String examUnitary;// 是否全国统考课程

	public BaseSyllabusTime() {
	}

	public String getSyllabusCode() {
		return syllabusCode;
	}

	public void setSyllabusCode(String syllabusCode) {
		this.syllabusCode = syllabusCode;
	}

	public String getApplyYear() {
		return applyYear;
	}

	public void setApplyYear(String applyYear) {
		this.applyYear = applyYear;
	}

	public String getProCode() {
		return proCode;
	}

	public void setProCode(String proCode) {
		this.proCode = proCode;
	}

	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	public String getSyllabusName() {
		return syllabusName;
	}

	public void setSyllabusName(String syllabusName) {
		this.syllabusName = syllabusName;
	}

	public String getExaminationTimeCode() {
		return examinationTimeCode;
	}

	public void setExaminationTimeCode(String examinationTimeCode) {
		this.examinationTimeCode = examinationTimeCode;
	}

	public String getCountryProvince() {
		return countryProvince;
	}

	public void setCountryProvince(String countryProvince) {
		this.countryProvince = countryProvince;
	}

	public String getCommSysllabus() {
		return commSysllabus;
	}

	public void setCommSysllabus(String commSysllabus) {
		this.commSysllabus = commSysllabus;
	}

	public String getExaminationDate() {
		return examinationDate;
	}

	public void setExaminationDate(String examinationDate) {
		this.examinationDate = examinationDate;
	}

	public String getExaminationTime() {
		return examinationTime;
	}

	public void setExaminationTime(String examinationTime) {
		this.examinationTime = examinationTime;
	}

	public String getCommView() {
		return commView;
	}

	public void setCommView(String commView) {
		this.commView = commView;
	}

	public String getExamUnitary() {
		return examUnitary;
	}

	public void setExamUnitary(String examUnitary) {
		this.examUnitary = examUnitary;
	}
}