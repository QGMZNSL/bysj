package com.sincinfo.zxks.bean;

public class SysFunctionManagerPo {
	private String menuId;
//	菜单分类编号
	private String menuName;
//	菜单分类名称
	private String menuType;
//	菜单树分类    默认0： 0管理端 1考生端
	private String menuLock;
//	锁定状态     默认0： 0未锁定 
	private String menuOrder;
//	显示顺序
	private String userRole;
//	用户角色
	private String parentMenu;
//	默认0, 0表示根节点
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getMenuType() {
		return menuType;
	}
	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}
	public String getMenuLock() {
		return menuLock;
	}
	public void setMenuLock(String menuLock) {
		this.menuLock = menuLock;
	}
	public String getMenuOrder() {
		return menuOrder;
	}
	public void setMenuOrder(String menuOrder) {
		this.menuOrder = menuOrder;
	}
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	public String getParentMenu() {
		return parentMenu;
	}
	public void setParentMenu(String parentMenu) {
		this.parentMenu = parentMenu;
	}
}
