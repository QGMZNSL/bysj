package com.sincinfo.zxks.bean;

public class Substitute {
	//替考代码			
	private String substitutecode;
	//课程代码
	private String syllabuscode;
	
	//课程名称
	private String syllabusName;
	//替考课程类型
	private String substitutetype;
	//替考课程代码
	private String substitutesyllabus;
	//状态
	private String avoidstate;
	//备注
	private String remarks;

	public String getSubstitutecode() {
		return substitutecode;
	}
	public void setSubstitutecode(String substitutecode) {
		this.substitutecode = substitutecode;
	}
	public String getSyllabuscode() {
		return syllabuscode;
	}
	public void setSyllabuscode(String syllabuscode) {
		this.syllabuscode = syllabuscode;
	}
	public String getSyllabusName() {
		return syllabusName;
	}
	public void setSyllabusName(String syllabusName) {
		this.syllabusName = syllabusName;
	}
	public String getSubstitutetype() {
		return substitutetype;
	}
	public void setSubstitutetype(String substitutetype) {
		this.substitutetype = substitutetype;
	}
	public String getSubstitutesyllabus() {
		return substitutesyllabus;
	}
	public void setSubstitutesyllabus(String substitutesyllabus) {
		this.substitutesyllabus = substitutesyllabus;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getAvoidstate() {
		return avoidstate;
	}
	public void setAvoidstate(String avoidstate) {
		this.avoidstate = avoidstate;
	}

}
