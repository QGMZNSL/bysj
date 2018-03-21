/**
 * Copyright(c) 2012 西安云海信息技术有限责任公司 All Rights Reserved<br>
 * @Title: com.sincinfo.zxks.bean.BaseDepartment.java<br>
 * @Description: 用户部门 <br>
 * <br>
 * @author litian<br>
 * @date Jan 20, 2012 8:53:15 AM 
 * @version V1.0   
 */
package com.sincinfo.zxks.bean;

import org.river.nbf.validate.annotation.Validate;

/**
 * @ClassName: BaseDepartment
 * @Description: 用户部门 <br>
 *               <br>
 * @author litian
 * @date Jan 20, 2012 8:53:15 AM<br>
 * 
 */
public class BaseDepartment {

	// 部门编号
	private String departmentCode;
	// 部门名称
	private String departmentName;
	// 部门级别（ 1省2市）
	private String departmentGrade;

	/*- 扩展部分 -*/
	// 该部门下所有职位数量
	private String postionCount;

	public String getDepartmentCode() {
		return departmentCode;
	}

	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}

	@Validate
	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	@Validate
	public String getDepartmentGrade() {
		return departmentGrade;
	}

	public void setDepartmentGrade(String departmentGrade) {
		this.departmentGrade = departmentGrade;
	}

	public String getPostionCount() {
		return postionCount;
	}

	public void setPostionCount(String postionCount) {
		this.postionCount = postionCount;
	}

}
