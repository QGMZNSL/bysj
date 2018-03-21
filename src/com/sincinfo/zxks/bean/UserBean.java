package com.sincinfo.zxks.bean;

public class UserBean {
	String username;
	String userpass;
	public UserBean(String username, String userpass) {
		super();
		this.username = username;
		this.userpass = userpass;
	}
	public UserBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUserpass() {
		return userpass;
	}
	public void setUserpass(String userpass) {
		this.userpass = userpass;
	}
	

}
