package com.sincinfo.zxks.common.tree;


public class HeadMenu {
	private int menuId;
	private String menuName;
	public int getMenuId() {
		return menuId;
	}
	
	public HeadMenu() {
		super();
		// TODO Auto-generated constructor stub
	}

	public HeadMenu(int menuId, String menuName) {
		super();
		this.menuId = menuId;
		this.menuName = menuName;
	}

	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	
	
	
}
