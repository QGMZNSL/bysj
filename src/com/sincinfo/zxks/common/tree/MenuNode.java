package com.sincinfo.zxks.common.tree;

/**
 * @ClassName: MenuNode
 * @Description: 头部菜单节点 <br>
 *               <br>
 * @author litian
 * @date Jan 18, 2012 4:43:18 PM<br>
 * 
 */
public class MenuNode {
	// 菜单分类编号
	private String menuId;

	// 菜单分类名称
	private String menuName;

	// 菜单树分类
	private String menuType;

	// 锁定状态
	private String menuLock;

	// 菜单显示顺序
	private String menuOrder;

	// 用户角色
	private String userRole;

	// 父菜单
	private String parentMenu;

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

	public String getParentMenu() {
		return parentMenu;
	}

	public void setParentMenu(String parentMenu) {
		this.parentMenu = parentMenu;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

}
