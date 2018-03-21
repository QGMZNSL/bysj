package com.sincinfo.zxks.core.day.datapreserve.busi;

import java.util.List;

import org.river.nbf.validate.annotation.Validate;

import com.sincinfo.zxks.bean.BaseAcademy;
import com.sincinfo.zxks.bean.BaseCameraPlace;
import com.sincinfo.zxks.bean.BaseCity;
import com.sincinfo.zxks.bean.BaseJoinhandsUnit;
import com.sincinfo.zxks.bean.BasePosition;
import com.sincinfo.zxks.bean.SysCode;
import com.sincinfo.zxks.zxksdbs.DayDbService;
import com.sincinfo.zxks.zxksdbs.SysDbService;

/**
 * @ClassName: UserPre
 * @Description: 添加/修改用户，页面初始化结构 <br>
 *               <br>
 * @author litian
 * @date Jan 20, 2012 4:17:11 PM<br>
 * 
 */
public class UserPre {
	/*-----传值列表-----*/
	// 性别列表
	private List<SysCode> genderList;
	// 城市列表
	private List<BaseCity> cityList;
	// 职位列表
	private List<BasePosition> positionList;
	// 主考院校列表
	private List<BaseAcademy> academyList;
	// 摄像点列表
	private List<BaseCameraPlace> cameraList;
	//合作开考单位列表
	private List<BaseJoinhandsUnit> unitList;
	
	/*-----用户信息-----*/
	// 用户名
	private String userName;
	// 用户密码
	private String userPassword;
	// 性别
	private String userGender;
	// 真实姓名
	private String realName;
	// 联系电话
	private String telephone;
	// 通讯地址
	private String postalAddress;
	// 用户角色
	private String userRole;
	// 地市代码
	private String cityCode;
	// 职位代码
	private String positionCode;
	// 主考院校代码
	private String academyCode;
	// 摄像点代码
	private String cameraPlaceCode;
	//合作开考单位代码
	private String unitCode;
	// 用户锁定状态
	private String userLock;
	// 用户权限
	private String powerArray;

	public UserPre() {
	}

	/**
	 * 初始化列表 参数 userRole为用户级别，仅用来初始化职位
	 * 
	 * @param userRole
	 *            "1"-省级用户 "2"-地市用户 "4"-区用户
	 */
	public void initLists(String usrRole, String cityCode) {
		DayDbService dds = new DayDbService();
		SysDbService sds = new SysDbService();
		this.genderList = sds.qrySysDict(SysDbService.SYS_DICT_GENDER);
		this.cityList = dds.qryCitys(usrRole, cityCode);
		this.academyList = sds.qryAcademys(cityCode);
		this.cameraList = sds.qryCameraPlaces(cityCode);
		this.positionList = dds.qryPostionByGrade(usrRole);
		this.unitList=sds.qryJoinHandsUnit(cityCode);
	}

	/**
	 * 获取性别列表
	 * 
	 * @return List<SysCode>
	 */
	public List<SysCode> qryGenderList() {
		SysDbService sds = new SysDbService();
		this.genderList = sds.qrySysDict(SysDbService.SYS_DICT_GENDER);
		return this.genderList;
	}

	/**
	 * 地市列表
	 * 
	 * @param usrRole
	 *            用户角色
	 * @param cityCode
	 *            地市代码
	 * @return List<BaseCity>
	 */
	public List<BaseCity> qryCityList(String usrRole, String cityCode) {
		DayDbService dds = new DayDbService();
		this.cityList = dds.qryCitys(usrRole, cityCode);
		return this.cityList;
	}

	/**
	 * 职位列表
	 * 
	 * @param usrRole
	 *            用户角色
	 * @return List<BasePosition>
	 */
	public List<BasePosition> qryPositionList(String usrRole) {
		DayDbService dds = new DayDbService();
		this.positionList = dds.qryPostionByGrade(usrRole);
		return this.positionList;

	}

	/**
	 * 获取主考院校列表
	 * 
	 * @param cityCode
	 *            地市代码
	 * @return List<BaseAcademy>
	 */
	public List<BaseAcademy> qryAcademyList(String cityCode) {
		SysDbService sds = new SysDbService();
		this.academyList = sds.qryAcademys(cityCode);
		return this.academyList;
	}

	/**
	 * 获取摄像点列表
	 * 
	 * @param cityCode
	 *            地市代码
	 * @return List<BaseCameraPlace>
	 */
	public List<BaseCameraPlace> qryCameraList(String cityCode) {
		SysDbService sds = new SysDbService();
		this.cameraList = sds.qryCameraPlaces(cityCode);
		return this.cameraList;
	}
	
	/**
	 * 获取合作开考单位列表
	 * 
	 * @param cityCode
	 *            地市代码
	 * @return List<BaseJoinhandsUnit>
	 */
	public List<BaseJoinhandsUnit> qryJoinhandsUnitList(String cityCode) {
		SysDbService sds = new SysDbService();
		this.unitList = sds.qryJoinHandsUnit(cityCode);
		return this.unitList;
	}

	/*---get/set---*/
	public List<SysCode> getGenderList() {
		return genderList;
	}

	public void setGenderList(List<SysCode> genderList) {
		this.genderList = genderList;
	}

	public List<BaseCity> getCityList() {
		return cityList;
	}

	public void setCityList(List<BaseCity> cityList) {
		this.cityList = cityList;
	}

	public List<BasePosition> getPositionList() {
		return positionList;
	}

	public void setPositionList(List<BasePosition> positionList) {
		this.positionList = positionList;
	}

	public List<BaseAcademy> getAcademyList() {
		return academyList;
	}

	public void setAcademyList(List<BaseAcademy> academyList) {
		this.academyList = academyList;
	}

	public List<BaseCameraPlace> getCameraList() {
		return cameraList;
	}

	public void setCameraList(List<BaseCameraPlace> cameraList) {
		this.cameraList = cameraList;
	}

	@Validate(fname = "用户名", exp = "ename(6,16)")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Validate(fname = "用户密码", exp = "password(6,16)")
	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	@Validate(fname = "性别", exp = "null")
	public String getUserGender() {
		return userGender;
	}

	public void setUserGender(String userGender) {
		this.userGender = userGender;
	}

	@Validate(fname = "真实姓名", exp = "cname(2,20)")
	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	@Validate(fname = "联系方式", exp = "p(13)")
	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	@Validate(fname = "通讯地址", exp = "null")
	public String getPostalAddress() {
		return postalAddress;
	}

	public void setPostalAddress(String postalAddress) {
		this.postalAddress = postalAddress;
	}

	@Validate(fname = "用户角色", exp = "null")
	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	@Validate(exp = "null")
	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	@Validate(exp = "null")
	public String getPositionCode() {
		return positionCode;
	}

	public void setPositionCode(String positionCode) {
		this.positionCode = positionCode;
	}

	@Validate(exp = "null")
	public String getAcademyCode() {
		return academyCode;
	}

	public void setAcademyCode(String academyCode) {
		this.academyCode = academyCode;
	}

	@Validate(exp = "null")
	public String getCameraPlaceCode() {
		return cameraPlaceCode;
	}

	public void setCameraPlaceCode(String cameraPlaceCode) {
		this.cameraPlaceCode = cameraPlaceCode;
	}

	@Validate(exp = "null")
	public String getUserLock() {
		return userLock;
	}

	public void setUserLock(String userLock) {
		this.userLock = userLock;
	}

	public String getPowerArray() {
		return powerArray;
	}

	public void setPowerArray(String powerArray) {
		this.powerArray = powerArray;
	}

	public List<BaseJoinhandsUnit> getUnitList() {
		return unitList;
	}

	public void setUnitList(List<BaseJoinhandsUnit> unitList) {
		this.unitList = unitList;
	}

	public String getUnitCode() {
		return unitCode;
	}

	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
	}

}
