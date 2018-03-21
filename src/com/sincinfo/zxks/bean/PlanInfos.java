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

import org.river.nbf.validate.annotation.Validate;

/**
 * @ClassName: PlanLevel
 * @Description:专业课程信息汇编
 * @author yuansh
 * @date 2012-01-26 13:36 PM<br>
 * 
 */
public class PlanInfos {
	
    //专业代码
	private String proCode;
	 
	//专业名称
	private String proName;
	
	//课程代码
	private String syllabusCode;
	 
	//课程名称
	private String syllabusName;
	 
	//课程组代码
	private String syllabusGroupCode;
	 
	//学分
	private String syllabusCredit;
	
	//是否全国统考
	private String examUnitary;
	  
	//是否全国统编
	private String textbookUnitary;
	  
	//教材名称
	private String textbookName;
	  
	//教材主编
	private String textbookEditor;
	  
	//教材出版社
	private String textbookPublisher;
	  
	//教材出版时间
	private String publishTime;
	
	//扩展：查询类型
	private String ckType;
	
	//扩展：查询专业代码
	private String ckProCode;
	
	//扩展：查询专业名称
	private String ckProName;
	
	@Validate 
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

	public String getSyllabusGroupCode() {
		return syllabusGroupCode;
	}

	public void setSyllabusGroupCode(String syllabusGroupCode) {
		this.syllabusGroupCode = syllabusGroupCode;
	}

	public String getSyllabusCredit() {
		return syllabusCredit;
	}

	public void setSyllabusCredit(String syllabusCredit) {
		this.syllabusCredit = syllabusCredit;
	}

	public String getExamUnitary() {
		return examUnitary;
	}

	public void setExamUnitary(String examUnitary) {
		this.examUnitary = examUnitary;
	}

	public String getTextbookUnitary() {
		return textbookUnitary;
	}

	public void setTextbookUnitary(String textbookUnitary) {
		this.textbookUnitary = textbookUnitary;
	}

	public String getTextbookName() {
		return textbookName;
	}

	public void setTextbookName(String textbookName) {
		this.textbookName = textbookName;
	}

	public String getTextbookEditor() {
		return textbookEditor;
	}

	public void setTextbookEditor(String textbookEditor) {
		this.textbookEditor = textbookEditor;
	}

	public String getTextbookPublisher() {
		return textbookPublisher;
	}

	public void setTextbookPublisher(String textbookPublisher) {
		this.textbookPublisher = textbookPublisher;
	}

	public String getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(String publishTime) {
		this.publishTime = publishTime;
	}

	public String getCkType() {
		return ckType;
	}

	public void setCkType(String ckType) {
		this.ckType = ckType;
	}

	public String getCkProCode() {
		return ckProCode;
	}

	public void setCkProCode(String ckProCode) {
		this.ckProCode = ckProCode;
	}

	public String getCkProName() {
		return ckProName;
	}

	public void setCkProName(String ckProName) {
		this.ckProName = ckProName;
	}

	
}
