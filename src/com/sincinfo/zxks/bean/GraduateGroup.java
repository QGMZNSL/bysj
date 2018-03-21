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
 * @Description: 毕业分组条件设置
 * @author yuansh
 * @date 2012-01-26 13:36 PM<br>
 */
public class GraduateGroup {

	//毕业分组代码
	private String graduateGroupCode;
	
	//专业代码
	private String proCode;
	
	//课程分组代码
	private String syllabusGroupCode;
	
	//毕业分组类型
	private String graduateGroupTypeCode;
	
	//课程代码
	private String syllabusCode;
	
	//毕业分组学分（学分或课程限制）
	private String graduateGroupCredit;
	
	//扩展：课程名称
	private String syllabusName;

	@Validate 
	public String getGraduateGroupCode() {
		return graduateGroupCode;
	}

	public void setGraduateGroupCode(String graduateGroupCode) {
		this.graduateGroupCode = graduateGroupCode;
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

	public String getGraduateGroupTypeCode() {
		return graduateGroupTypeCode;
	}

	public void setGraduateGroupTypeCode(String graduateGroupTypeCode) {
		this.graduateGroupTypeCode = graduateGroupTypeCode;
	}

	public String getSyllabusCode() {
		return syllabusCode;
	}

	public void setSyllabusCode(String syllabusCode) {
		this.syllabusCode = syllabusCode;
	}

	public String getGraduateGroupCredit() {
		return graduateGroupCredit;
	}

	public void setGraduateGroupCredit(String graduateGroupCredit) {
		this.graduateGroupCredit = graduateGroupCredit;
	}

	public String getSyllabusName() {
		return syllabusName;
	}

	public void setSyllabusName(String syllabusName) {
		this.syllabusName = syllabusName;
	}

}
