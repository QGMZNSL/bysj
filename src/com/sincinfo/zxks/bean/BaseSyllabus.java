package com.sincinfo.zxks.bean;

/**
 * @ClassName: BaseSyllabus 
 * @Description: 课程 <br>
 * <br>
 * @author litian
 * @date Apr 10, 2012 11:16:58 AM<br>
 *  
*/
public class BaseSyllabus {
	
	// 课程代码
	private String syllabusCode;
	
	// 课程名称
	private String syllabusName;

	// 课程英文名称
	private String syllabusEnglishName;

	// 课程类型
	private String syllabusType;

	// 是否国标
	private String isGb;

	// 课程学分
	private String syllabusCredit;

	// 课程对应教材
	private String textbookCode;

	// 课程总分
	private String syllabusTotalScore;

	// 课程通过分
	private String syllabusPassScore;

	// 是否启用
	private String isUse;

	// 备注
	private String remarks;

	public void setSyllabusCode(String syllabusCode) {
		this.syllabusCode = syllabusCode;
	}

	public String getSyllabusCode() {
		return this.syllabusCode;
	}

	public void setSyllabusName(String syllabusName) {
		this.syllabusName = syllabusName;
	}

	public String getSyllabusName() {
		return this.syllabusName;
	}

	public void setSyllabusEnglishName(String syllabusEnglishName) {
		this.syllabusEnglishName = syllabusEnglishName;
	}

	public String getSyllabusEnglishName() {
		return this.syllabusEnglishName;
	}

	public void setSyllabusType(String syllabusType) {
		this.syllabusType = syllabusType;
	}

	public String getSyllabusType() {
		return this.syllabusType;
	}

	public void setIsGb(String isGb) {
		this.isGb = isGb;
	}

	public String getIsGb() {
		return this.isGb;
	}

	public void setSyllabusCredit(String syllabusCredit) {
		this.syllabusCredit = syllabusCredit;
	}

	public String getSyllabusCredit() {
		return this.syllabusCredit;
	}

	public void setTextbookCode(String textbookCode) {
		this.textbookCode = textbookCode;
	}

	public String getTextbookCode() {
		return this.textbookCode;
	}

	public void setSyllabusTotalScore(String syllabusTotalScore) {
		this.syllabusTotalScore = syllabusTotalScore;
	}

	public String getSyllabusTotalScore() {
		return this.syllabusTotalScore;
	}

	public void setSyllabusPassScore(String syllabusPassScore) {
		this.syllabusPassScore = syllabusPassScore;
	}

	public String getSyllabusPassScore() {
		return this.syllabusPassScore;
	}

	public void setIsUse(String isUse) {
		this.isUse = isUse;
	}

	public String getIsUse() {
		return this.isUse;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getRemarks() {
		return this.remarks;
	}

}
