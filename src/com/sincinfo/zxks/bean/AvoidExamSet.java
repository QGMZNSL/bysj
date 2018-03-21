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
 * @Description: 免考设置 <br>
 * @author yuansh
 * @date 2012-01-26 13:36 PM<br>
 * 
 */
public class AvoidExamSet {
	//免考代码
	private String avoidCode;
	
	//课程代码
	private String syllabusCode;
	
	//免考说明
	private String avoidState;

	//备注
	private String remarks;
	
	//课程名称
	private String syllabusName;

	@Validate 
	public String getAvoidCode() {
		return avoidCode;
	}

	public void setAvoidCode(String avoidCode) {
		this.avoidCode = avoidCode;
	}

	public String getSyllabusCode() {
		return syllabusCode;
	}

	public void setSyllabusCode(String syllabusCode) {
		this.syllabusCode = syllabusCode;
	}

	public String getAvoidState() {
		return avoidState;
	}

	public void setAvoidState(String avoidState) {
		this.avoidState = avoidState;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getSyllabusName() {
		return syllabusName;
	}

	public void setSyllabusName(String syllabusName) {
		this.syllabusName = syllabusName;
	}


}
