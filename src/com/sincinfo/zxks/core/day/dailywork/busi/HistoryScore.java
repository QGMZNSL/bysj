/**
 * Copyright(c) 2012 西安云海信息技术有限责任公司 All Rights Reserved<br>
 * @Title: com.sincinfo.zxks.core.day.dailywork.busi.HistoryScore.java<br>
 * @Description: 历史考试成绩 <br>
 * <br>
 * @author litian<br>
 * @date Aug 20, 2012 3:28:02 PM 
 * @version V1.0   
 */
package com.sincinfo.zxks.core.day.dailywork.busi;

/**
 * @ClassName: HistoryScore
 * @Description: 历史考试成绩 <br>
 *               <br>
 * @author litian
 * @date Aug 20, 2012 3:28:02 PM<br>
 * 
 */
public class HistoryScore {
	private String studExamCode; // 考生准考证号
	private String examName; // 考试年月
	private String syllabusCode; // 课程代码
	private String syllabusName; // 课程名称
	private String studScore; // 考生成绩
	private String lackCode; // 缺考状态
	private String deciplineFactCode; // 违纪代码，不为空表示违纪
	private String deciplineFactDescribe; // 违纪事实描述
	private String deciplinePunishCodeArray; // 违纪惩罚标记字符串
	private String deciplinePMDescript; // 违纪描述

	public String getStudExamCode() {
		return studExamCode;
	}

	public void setStudExamCode(String studExamCode) {
		this.studExamCode = studExamCode;
	}

	public String getExamName() {
		return examName;
	}

	public void setExamName(String examName) {
		this.examName = examName;
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

	public String getStudScore() {
		return studScore;
	}

	public void setStudScore(String studScore) {
		this.studScore = studScore;
	}

	public String getLackCode() {
		return lackCode;
	}

	public void setLackCode(String lackCode) {
		this.lackCode = lackCode;
	}

	public String getDeciplineFactCode() {
		return deciplineFactCode;
	}

	public void setDeciplineFactCode(String deciplineFactCode) {
		this.deciplineFactCode = deciplineFactCode;
	}

	public String getDeciplineFactDescribe() {
		return deciplineFactDescribe;
	}

	public void setDeciplineFactDescribe(String deciplineFactDescribe) {
		this.deciplineFactDescribe = deciplineFactDescribe;
	}

	public String getDeciplinePunishCodeArray() {
		return deciplinePunishCodeArray;
	}

	public void setDeciplinePunishCodeArray(String deciplinePunishCodeArray) {
		this.deciplinePunishCodeArray = deciplinePunishCodeArray;
	}

	public String getDeciplinePMDescript() {
		return deciplinePMDescript;
	}

	public void setDeciplinePMDescript(String deciplinePMDescript) {
		this.deciplinePMDescript = deciplinePMDescript;
	}

}
