package com.sincinfo.zxks.bean;

public class BaseStudentNoIdno{
	private String preapplyCode;//新生报名号
	private String noIdnoReason;//未采集身份证原因NO_IDNO_REASON
	private String document;//证明材料DOCUMENT
	public BaseStudentNoIdno(){}
	public String getPreapplyCode() {
		return preapplyCode;
	}
	public void setPreapplyCode(String preapplyCode) {
		this.preapplyCode = preapplyCode;
	}
	public String getNoIdnoReason() {
		return noIdnoReason;
	}
	public void setNoIdnoReason(String noIdnoReason) {
		this.noIdnoReason = noIdnoReason;
	}
	public String getDocument() {
		return document;
	}
	public void setDocument(String document) {
		this.document = document;
	}
}