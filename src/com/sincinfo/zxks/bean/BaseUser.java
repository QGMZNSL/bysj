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
 * @ClassName: BaseUser
 * @Description: 管理端操作员对象 <br>
 *               <br>
 * @author litian
 * @date Jan 12, 2012 2:04:59 PM<br>
 * 
 */
public class BaseUser {
	// 用户名
	private String userName;

	// 真实姓名
	private String realName;

	// 性别
	private String gender;

	// 用户类型
	private String userRole;

	// 地市代码
	private String cityCode;

	// 考区代码
	private String examAreaCode;

	// 职位代码
	private String positionCode;

	// 联系电话
	private String telephone;

	// 通讯地址
	private String postalAddress;

	// 最后登陆时间
	private String lastLoginTime;

	// 登陆次数
	private String loginNum;

	// 锁定状态
	private String userLock;

	// 锁定原因
	private String userLockReason;

	// 用户权限码
	private String powerArray;

	// 所属主考院校
	private String academyCode;

	// 所属摄像点
	private String cameraPlaceCode;

	//合作开考单位
	private String unitCode;
	
	// 登录密码
	private String userPassword;

	// 初始密码
	private String initPassWord;

	// 跨包登陆md5串
	private String loginAcross;

	/*--扩展字段--*/
	// 地市名称
	private String cityName;
	// 主考院校名称
	private String academyName;
	// 摄像点名称
	private String cameraPlaceName;

	public BaseUser() {
	}

	@Validate (fname="用户名",exp="ename(5,16)")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Validate (fname="真实姓名",exp="cname(4,20)")
	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	@Validate (fname="性别",exp="null")
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Validate (fname="用户角色",exp="null")
	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getExamAreaCode() {
		return examAreaCode;
	}

	public void setExamAreaCode(String examAreaCode) {
		this.examAreaCode = examAreaCode;
	}

	public String getPositionCode() {
		return positionCode;
	}

	public void setPositionCode(String positionCode) {
		this.positionCode = positionCode;
	}

	@Validate (fname="联系方式",exp="p(13)")
	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	@Validate (fname="通讯地址",exp="null")
	public String getPostalAddress() {
		return postalAddress;
	}

	public void setPostalAddress(String postalAddress) {
		this.postalAddress = postalAddress;
	}

	public String getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getLoginNum() {
		return loginNum;
	}

	public void setLoginNum(String loginNum) {
		this.loginNum = loginNum;
	}

	public String getUserLock() {
		return userLock;
	}

	public void setUserLock(String userLock) {
		this.userLock = userLock;
	}

	public String getUserLockReason() {
		return userLockReason;
	}

	public void setUserLockReason(String userLockReason) {
		this.userLockReason = userLockReason;
	}

	public String getPowerArray() {
		return powerArray;
	}

	public void setPowerArray(String powerArray) {
		this.powerArray = powerArray;
	}

	public String getAcademyCode() {
		return academyCode;
	}

	public void setAcademyCode(String academyCode) {
		this.academyCode = academyCode;
	}

	public String getCameraPlaceCode() {
		return cameraPlaceCode;
	}

	public void setCameraPlaceCode(String cameraPlaceCode) {
		this.cameraPlaceCode = cameraPlaceCode;
	}

	@Validate (fname="用户密码",exp="password(6,20)")
	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getInitPassWord() {
		return initPassWord;
	}

	public void setInitPassWord(String initPassWord) {
		this.initPassWord = initPassWord;
	}

	public String getLoginAcross() {
		return loginAcross;
	}

	public void setLoginAcross(String loginAcross) {
		this.loginAcross = loginAcross;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getAcademyName() {
		return academyName;
	}

	public void setAcademyName(String academyName) {
		this.academyName = academyName;
	}

	public String getCameraPlaceName() {
		return cameraPlaceName;
	}

	public void setCameraPlaceName(String cameraPlaceName) {
		this.cameraPlaceName = cameraPlaceName;
	}

	public String getUnitCode() {
		return unitCode;
	}

	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
	}

}
