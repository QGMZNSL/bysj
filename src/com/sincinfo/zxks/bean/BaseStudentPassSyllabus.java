/**
 * Copyright(c) 2012 西安云海信息技术有限责任公司 All Rights Reserved<br>
 * @Title: com.sincinfo.zxks.bean.BaseStudentPassSyllabus.java<br>
 * @Description: 考生合格成绩表 <br>
 * <br>
 * @author litian<br>
 * @date Mar 30, 2012 11:16:33 AM 
 * @version V1.0   
 */
package com.sincinfo.zxks.bean;

/**
 * @ClassName: BaseStudentPassSyllabus
 * @Description: 考生合格成绩表 <br>
 *               <br>
 * @author litian
 * @date Mar 30, 2012 11:16:33 AM<br>
 * 
 */
public class BaseStudentPassSyllabus {

	// 合格编号
	private String studPassId;

	// 准考证号
	private String studExamCode;

	// 考试代码
	private String examinationCode;

	// 课程代码
	private String syllabusCode;

	// 成绩
	private String studScore;

	// 合格时间  
	private String studPassTime;

	// 成绩作废原因
	private String studScoreDisabledReason;

	// 成绩作废操作员
	private String studScoreDisabledUser;

	// 成绩作废时间
	private String studScoreDisabledTime;

	// 合格证明打印次数     默认0
	private String studScorePrintNum;

	// 合格原因代码
	private String studPassReason;

	// 合格备注
	private String studPassRemark;

	// 成绩无效 默认0,1无效0有效
	private String studScoreDisabledStatus;

	// 转出状态 默认0,1转出0未转出
	private String studScoreTransferStatus;

	/* 扩展部分 */
	// 课程名称
	private String syllabusName;
	// 课程学分
	private String syllabusCredit;
	//证件号码
	private String studIdnum;
	//考生姓名
	private String studName;

	public void setStudPassId(String studPassId) {
		this.studPassId = studPassId;
	}

	public String getStudPassId() {
		return this.studPassId;
	}

	public void setStudExamCode(String studExamCode) {
		this.studExamCode = studExamCode;
	}

	public String getStudExamCode() {
		return this.studExamCode;
	}

	public void setExaminationCode(String examinationCode) {
		this.examinationCode = examinationCode;
	}

	public String getExaminationCode() {
		return this.examinationCode;
	}

	public void setSyllabusCode(String syllabusCode) {
		this.syllabusCode = syllabusCode;
	}

	public String getSyllabusCode() {
		return this.syllabusCode;
	}

	public void setStudScore(String studScore) {
		this.studScore = studScore;
	}

	public String getStudScore() {
		return this.studScore;
	}

	public void setStudPassTime(String studPassTime) {
		this.studPassTime = studPassTime;
	}

	public String getStudPassTime() {
		return this.studPassTime;
	}

	public void setStudScoreDisabledReason(String studScoreDisabledReason) {
		this.studScoreDisabledReason = studScoreDisabledReason;
	}

	public String getStudScoreDisabledReason() {
		return this.studScoreDisabledReason;
	}

	public void setStudScoreDisabledUser(String studScoreDisabledUser) {
		this.studScoreDisabledUser = studScoreDisabledUser;
	}

	public String getStudScoreDisabledUser() {
		return this.studScoreDisabledUser;
	}

	public void setStudScoreDisabledTime(String studScoreDisabledTime) {
		this.studScoreDisabledTime = studScoreDisabledTime;
	}

	public String getStudScoreDisabledTime() {
		return this.studScoreDisabledTime;
	}

	public void setStudScorePrintNum(String studScorePrintNum) {
		this.studScorePrintNum = studScorePrintNum;
	}

	public String getStudScorePrintNum() {
		return this.studScorePrintNum;
	}

	public void setStudPassReason(String studPassReason) {
		this.studPassReason = studPassReason;
	}

	public String getStudPassReason() {
		return this.studPassReason;
	}

	public void setStudPassRemark(String studPassRemark) {
		this.studPassRemark = studPassRemark;
	}

	public String getStudPassRemark() {
		return this.studPassRemark;
	}

	public void setStudScoreDisabledStatus(String studScoreDisabledStatus) {
		this.studScoreDisabledStatus = studScoreDisabledStatus;
	}

	public String getStudScoreDisabledStatus() {
		return this.studScoreDisabledStatus;
	}

	public void setStudScoreTransferStatus(String studScoreTransferStatus) {
		this.studScoreTransferStatus = studScoreTransferStatus;
	}

	public String getStudScoreTransferStatus() {
		return this.studScoreTransferStatus;
	}

	public String getSyllabusName() {
		return syllabusName;
	}

	public void setSyllabusName(String syllabusName) {
		this.syllabusName = syllabusName;
	}

	public String getSyllabusCredit() {
		return syllabusCredit;
	}

	public void setSyllabusCredit(String syllabusCredit) {
		this.syllabusCredit = syllabusCredit;
	}

	public String getStudIdnum() {
		return studIdnum;
	}

	public void setStudIdnum(String studIdnum) {
		this.studIdnum = studIdnum;
	}

	public String getStudName() {
		return studName;
	}

	public void setStudName(String studName) {
		this.studName = studName;
	}

}
