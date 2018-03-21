package com.sincinfo.zxks.bean;

import org.river.nbf.validate.annotation.Validate;

public class BaseNewsClass {
	private int classId;// 信息分类编号
	private String className;// 信息分类名称
	private int parentId;// 父编号 默认0
	private String customUrl;// 站外链接
	private int userRole;//发布权限
	private String isShow;//是否在前台显示   1显示   0不显示 

	
	public BaseNewsClass(int classId, String className, int parentId,
			String customUrl) {
		super();
		this.classId = classId;
		this.className = className;
		this.parentId = parentId;
		this.customUrl = customUrl;
	}


	public int getClassId() {
		return classId;
	}


	public void setClassId(int classId) {
		this.classId = classId;
	}


	public String getClassName() {
		return className;
	}


	public void setClassName(String className) {
		this.className = className;
	}


	public int getParentId() {
		return parentId;
	}


	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	
	public String getCustomUrl() {
		return customUrl;
	}


	public void setCustomUrl(String customUrl) {
		this.customUrl = customUrl;
	}


	public int getUserRole() {
		return userRole;
	}


	public void setUserRole(int userRole) {
		this.userRole = userRole;
	}


	public BaseNewsClass() {
		super();
		// TODO Auto-generated constructor stub
	}


	public String getIsShow() {
		return isShow;
	}


	public void setIsShow(String isShow) {
		this.isShow = isShow;
	}
	

}
