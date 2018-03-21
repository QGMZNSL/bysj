/**
 * Copyright(c) 2012 西安云海信息技术有限责任公司 All Rights Reserved<br>
 * @Title: com.sincinfo.zxks.bean.BaseUser.java<br>
 * @Description: 管理端操作员对象 <br>
 * <br>
 * @author litian<br>
 * @date Jan 12, 2012 2:04:59 PM 
 * @version V1.0   
 */
package com.sincinfo.zxks.bean;

/**
 * @ClassName: PlanLevel
 * @Description: 课程设置 <br>
 *               <br>
 * @author yuansh
 * @date 2012-01-26 13:36 PM<br>
 * 
 */
public class Syllabus {
	//课程代码
	private String syllabusCode;

	//课程名称
	private String syllabusName;
	
	//课程英文名
	private String syllabusenglishname;
	
	//教材代码
	private String textbookCode;
	
	//是否国标
	private String isgb;
	
	//默认学分
	private String syllabuscredit;
	
	//总分
	private String syllabustotalscore;
	
	//及格分  
	private String syllabuspassscore;
	
	//备注
	private String remarks;

	//是否启用
	private String isUse;
	
	//课程分类
	private String syllabusType;
	
	//教材名称
	private String textbookName;

	//扩展：状态（免考）
	private String state;
	
	//扩展：状态1（顶替）
	private String state1;
	
	public String getTextbookName() {
		return textbookName;
	}

	public void setTextbookName(String textbookName) {
		this.textbookName = textbookName;
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

	public String getSyllabusenglishname() {
		return syllabusenglishname;
	}

	public void setSyllabusenglishname(String syllabusenglishname) {
		this.syllabusenglishname = syllabusenglishname;
	}

	public String getTextbookCode() {
		return textbookCode;
	}

	public void setTextbookCode(String textbookCode) {
		this.textbookCode = textbookCode;
	}

	public String getIsgb() {
		return isgb;
	}

	public void setIsgb(String isgb) {
		this.isgb = isgb;
	}

	public String getSyllabuscredit() {
		return syllabuscredit;
	}

	public void setSyllabuscredit(String syllabuscredit) {
		this.syllabuscredit = syllabuscredit;
	}

	public String getSyllabustotalscore() {
		return syllabustotalscore;
	}

	public void setSyllabustotalscore(String syllabustotalscore) {
		this.syllabustotalscore = syllabustotalscore;
	}

	public String getSyllabuspassscore() {
		return syllabuspassscore;
	}

	public void setSyllabuspassscore(String syllabuspassscore) {
		this.syllabuspassscore = syllabuspassscore;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getIsUse() {
		return isUse;
	}

	public void setIsUse(String isUse) {
		this.isUse = isUse;
	}

	public String getSyllabusType() {
		return syllabusType;
	}

	public void setSyllabusType(String syllabusType) {
		this.syllabusType = syllabusType;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getState1() {
		return state1;
	}

	public void setState1(String state1) {
		this.state1 = state1;
	}

}
