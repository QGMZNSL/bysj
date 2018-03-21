package com.sincinfo.zxks.bean;

/**
 * @ClassName: SysModule
 * @Description: 权限位定义 <br>
 *               <br>
 * @author litian
 * @date Jan 20, 2012 3:44:39 PM<br>
 * 
 */
public class SysModule {
	// 模块代码（权限位）
	private String moduleId;
	// 模块名称（权限名称）
	private String moduleName;
	// 分类代码
	private String classId;
	// 分类名称
	private String className;
	// 启用状态
	private String moduleUse;
	// 用户角色
	private String userRole;

	public String getModuleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getModuleUse() {
		return moduleUse;
	}

	public void setModuleUse(String moduleUse) {
		this.moduleUse = moduleUse;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

}
