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
 * @Description: 课程顶替分组 
 * @author yuansh
 * @date 2012-01-26 13:36 PM
 */
public class SubstituteGroup {
	
	//顶替分组代码
	private String substituteGroupId;

	//顶替分组名称
	private String substituteGroupName;
	
	//顶替分组代码	
	private String syllabusCode;

	@Validate
	public String getSubstituteGroupId() {
		return substituteGroupId;
	}

	public void setSubstituteGroupId(String substituteGroupId) {
		this.substituteGroupId = substituteGroupId;
	}

	public String getSubstituteGroupName() {
		return substituteGroupName;
	}

	public void setSubstituteGroupName(String substituteGroupName) {
		this.substituteGroupName = substituteGroupName;
	}

	public String getSyllabusCode() {
		return syllabusCode;
	}

	public void setSyllabusCode(String syllabusCode) {
		this.syllabusCode = syllabusCode;
	}
	
}
