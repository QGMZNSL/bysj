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
 * @ClassName: ProSyllabus
 * @Description: 专业课程设置 <br>
 * @author yuansh
 * @date 2012-01-26 13:36 PM<br>
 * 
 */
public class ProSyllabus {
	// 关系序号
	private String substituteCode;

	// 专业代码
	private String proCode;

	// 课程代码
	private String syllabusCode;

	// 课程名称
	private String syllabusName;

	// 学分
	private String syllabusCredit;

	// 课程分组代码
	private String syllabusGroupCode;

	// 课程分组名称
	private String syllabusGroupName;

	// 扩展：多选提交使用
	private String dataChecked;
	// 备注
	private String syllabusRemark;
	// 是否证书课程
	private String certSyllabus;

	public String getSyllabusGroupName() {
		return syllabusGroupName;
	}

	public void setSyllabusGroupName(String syllabusGroupName) {
		this.syllabusGroupName = syllabusGroupName;
	}

	// 教材代码
	private String textbookCode;

	// 扩展：教材名称
	private String textbookName;

	// 是否全国统编教材
	private String textbookUnitary;

	// 是否全国统考
	private String examUnitary;

	// 课程类型
	private String proSyllabusTypeCode;

	@Validate
	public String getSubstituteCode() {
		return substituteCode;
	}

	public void setSubstituteCode(String substituteCode) {
		this.substituteCode = substituteCode;
	}

	public String getProCode() {
		return proCode;
	}

	public void setProCode(String proCode) {
		this.proCode = proCode;
	}

	public String getSyllabusGroupCode() {
		return syllabusGroupCode;
	}

	public void setSyllabusGroupCode(String syllabusGroupCode) {
		this.syllabusGroupCode = syllabusGroupCode;
	}

	public String getTextbookCode() {
		return textbookCode;
	}

	public void setTextbookCode(String textbookCode) {
		this.textbookCode = textbookCode;
	}

	public String getTextbookUnitary() {
		return textbookUnitary;
	}

	public void setTextbookUnitary(String textbookUnitary) {
		this.textbookUnitary = textbookUnitary;
	}

	public String getExamUnitary() {
		return examUnitary;
	}

	public void setExamUnitary(String examUnitary) {
		this.examUnitary = examUnitary;
	}

	public String getProSyllabusTypeCode() {
		return proSyllabusTypeCode;
	}

	public void setProSyllabusTypeCode(String proSyllabusTypeCode) {
		this.proSyllabusTypeCode = proSyllabusTypeCode;
	}

	public String getSyllabusCode() {
		return syllabusCode;
	}

	public void setSyllabusCode(String syllabusCode) {
		this.syllabusCode = syllabusCode;
	}

	@Validate
	public String getSyllabusCredit() {
		return syllabusCredit;
	}

	public void setSyllabusCredit(String syllabusCredit) {
		this.syllabusCredit = syllabusCredit;
	}

	public String getTextbookName() {
		return textbookName;
	}

	public void setTextbookName(String textbookName) {
		this.textbookName = textbookName;
	}

	public String getSyllabusName() {
		return syllabusName;
	}

	public void setSyllabusName(String syllabusName) {
		this.syllabusName = syllabusName;
	}

	public String getDataChecked() {
		return dataChecked;
	}

	public void setDataChecked(String dataChecked) {
		this.dataChecked = dataChecked;
	}

	public String getSyllabusRemark() {
		return syllabusRemark;
	}

	public void setSyllabusRemark(String syllabusRemark) {
		this.syllabusRemark = syllabusRemark;
	}

	public String getCertSyllabus() {
		return certSyllabus;
	}

	public void setCertSyllabus(String certSyllabus) {
		this.certSyllabus = certSyllabus;
	}

}
