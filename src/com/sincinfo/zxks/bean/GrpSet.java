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
 * @Description: 课程分组设置 <br>
 * @author yuansh
 * @date 2012-01-26 13:36 PM<br>
 */
public class GrpSet {
	//分组代码
	private String syllabusGroupCode;

	//分组名称
	private String syllabusGroupName;
	
	//分类代码
	private String syllabusSepCode; 
	
	//是否群组
	private String isGroup;
	
	//群组代码
	private String groupCode;
	
	//专业代码
	private String proCode;

	//备注
	private String remarks;
	
	//专业名称
	private String proName;	

	@Validate
	public String getSyllabusGroupCode() {
		return syllabusGroupCode;
	}

	public void setSyllabusGroupCode(String syllabusGroupCode) {
		this.syllabusGroupCode = syllabusGroupCode;
	}

	public String getSyllabusGroupName() {
		return syllabusGroupName;
	}

	public void setSyllabusGroupName(String syllabusGroupName) {
		this.syllabusGroupName = syllabusGroupName;
	}

	public String getSyllabusSepCode() {
		return syllabusSepCode;
	}

	public void setSyllabusSepCode(String syllabusSepCode) {
		this.syllabusSepCode = syllabusSepCode;
	}

	public String getIsGroup() {
		return isGroup;
	}

	public void setIsGroup(String isGroup) {
		this.isGroup = isGroup;
	}

	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	public String getProCode() {
		return proCode;
	}

	public void setProCode(String proCode) {
		this.proCode = proCode;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}


}
