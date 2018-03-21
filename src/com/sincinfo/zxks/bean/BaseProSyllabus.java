package com.sincinfo.zxks.bean;

public class BaseProSyllabus {
	private String proCode;
	private String syllabusCode;
	private String syllabusName;
	private String syllabusType;
	private String examUnitary;//EXAM_UNITARY
	
	// 扩展属性
    private String syllabusGroupName;
    private String remarks;
 // 课程学分
    private String syllabusCredit;
 // 课程备注
    private String syllabusRemark;
	
	public String getProCode() {
		return proCode;
	}
	
	public void setProCode(String proCode) {
		this.proCode = proCode;
	}
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
	public String getSyllabusType() {
		return syllabusType;
	}
	public void setSyllabusType(String syllabusType) {
		this.syllabusType = syllabusType;
	}

	public String getExamUnitary() {
		return examUnitary;
	}

	public void setExamUnitary(String examUnitary) {
		this.examUnitary = examUnitary;
	}

	public String getSyllabusGroupName() {
		return syllabusGroupName;
	}

	public void setSyllabusGroupName(String syllabusGroupName) {
		this.syllabusGroupName = syllabusGroupName;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getSyllabusCredit() {
		return syllabusCredit;
	}

	public void setSyllabusCredit(String syllabusCredit) {
		this.syllabusCredit = syllabusCredit;
	}

	public String getSyllabusRemark() {
		return syllabusRemark;
	}

	public void setSyllabusRemark(String syllabusRemark) {
		this.syllabusRemark = syllabusRemark;
	}
}