package com.sincinfo.zxks.bean;

public class BaseSyllabusArrange {
	private String syllabusArrangeId;//SYLLABUS_ARRANGE_ID
	private String year;
	private String month;
	private String day;
	private String week;
	private String examinationTime;//EXAMINATION_TIME
	private String examinationStartTime;//EXAMINATION_START_TIME
	private String examinationEndTime;
	
	public String getSyllabusArrangeId() {
		return syllabusArrangeId;
	}
	public void setSyllabusArrangeId(String syllabusArrangeId) {
		this.syllabusArrangeId = syllabusArrangeId;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getWeek() {
		return week;
	}
	public void setWeek(String week) {
		this.week = week;
	}
	public String getExaminationTime() {
		return examinationTime;
	}
	public void setExaminationTime(String examinationTime) {
		this.examinationTime = examinationTime;
	}
	public String getExaminationStartTime() {
		return examinationStartTime;
	}
	public void setExaminationStartTime(String examinationStartTime) {
		this.examinationStartTime = examinationStartTime;
	}
	public String getExaminationEndTime() {
		return examinationEndTime;
	}
	public void setExaminationEndTime(String examinationEndTime) {
		this.examinationEndTime = examinationEndTime;
	}
}