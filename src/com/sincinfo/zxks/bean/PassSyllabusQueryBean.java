package com.sincinfo.zxks.bean;

public class PassSyllabusQueryBean {
	private String syllabusCode;// 课程代码
	private String syllabusName;// 课程名称
	private String orno;// 是否为所选专业
	private String studPassReason;//合格原因代码
	private String studPassReasonContent;//合格原因内容
	private String studPassTime;//合格时间
	private String studPassRemark;//合格备注
	private String studScore;// 考生得合格成绩 

	public String getSyllabusCode() {
		return syllabusCode;
	}

	public void setSyllabusCode(String syllabusCode) {
		this.syllabusCode = syllabusCode;
	}

	public String getSyllabusName() {
		return syllabusName;
	}

	public void setSyllabusName(String syllabusName) {
		this.syllabusName = syllabusName;
	}

	public String getOrno() {
		return orno;
	}

	public void setOrno(String orno) {
		this.orno = orno;
	}

	public String getStudScore() {
		return studScore;
	}

	public void setStudScore(String studScore) {
		this.studScore = studScore;
	}

	public String getStudPassReason() {
		return studPassReason;
	}

	public void setStudPassReason(String studPassReason) {
		this.studPassReason = studPassReason;
	}

	public String getStudPassReasonContent() {
		return studPassReasonContent;
	}

	public void setStudPassReasonContent(String studPassReasonContent) {
		this.studPassReasonContent = studPassReasonContent;
	}

	public String getStudPassTime() {
		return studPassTime;
	}

	public void setStudPassTime(String studPassTime) {
		this.studPassTime = studPassTime;
	}

	public String getStudPassRemark() {
		return studPassRemark;
	}

	public void setStudPassRemark(String studPassRemark) {
		this.studPassRemark = studPassRemark;
	}



}
