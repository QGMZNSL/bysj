package com.sincinfo.zxks.bean;

import org.river.nbf.validate.annotation.Validate;

/**
 * @ClassName: BaseCameraPlace
 * @Description: 摄像点 <br>
 *               <br>
 * @author litian
 * @date Jan 22, 2012 12:36:09 AM<br>
 * 
 */
public class BaseCameraPlace {
	// 摄像点编号
	private String cameraPlaceCode;
	// 摄像点名称
	private String cameraPlaceName;
	// 所属地区代码
	private String cityCode;
	// 摄像点地址
	private String cameraPlaceAddress;
	// 摄像点联系人
	private String cameraPlaceLinkMan;
	// 摄像点联系电话
	private String cameraPlaceLinkTelephon;
	// 摄像点用户名
	private String cameraPlaceUsername;
	// 是否启用
	private String isUse;
	// 乘车路线
	private String byBus;
	//考区名称
	String examAreaName;
	String examAreaCode;
	String particularAreaCode;
	private String maxPeople;
	private String examAreaAll;

	@Validate(fname="摄像点编号",exp="s(20b)")
	public String getCameraPlaceCode() {
		return cameraPlaceCode;
	}

	public void setCameraPlaceCode(String cameraPlaceCode) {
		this.cameraPlaceCode = cameraPlaceCode;
	}

	@Validate(fname="摄像点名称",exp="s(50b)")
	public String getCameraPlaceName() {
		return cameraPlaceName;
	}

	public void setCameraPlaceName(String cameraPlaceName) {
		this.cameraPlaceName = cameraPlaceName;
	}

	@Validate(fname="地市编号",exp="s(2b)")
	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	@Validate(fname="详细地址",exp="s(200b)")
	public String getCameraPlaceAddress() {
		return cameraPlaceAddress;
	}

	public void setCameraPlaceAddress(String cameraPlaceAddress) {
		this.cameraPlaceAddress = cameraPlaceAddress;
	}

	@Validate(fname="摄像点联系人",exp="s(50b)")
	public String getCameraPlaceLinkMan() {
		return cameraPlaceLinkMan;
	}

	public void setCameraPlaceLinkMan(String cameraPlaceLinkMan) {
		this.cameraPlaceLinkMan = cameraPlaceLinkMan;
	}

	@Validate(fname="摄像点联系电话",exp="p(30)")
	public String getCameraPlaceLinkTelephon() {
		return cameraPlaceLinkTelephon;
	}

	public void setCameraPlaceLinkTelephon(String cameraPlaceTelephone) {
		this.cameraPlaceLinkTelephon = cameraPlaceTelephone;
	}

//	@Validate(fname="摄像点编号",exp="")
	public String getCameraPlaceUsername() {
		return cameraPlaceUsername;
	}

	public void setCameraPlaceUsername(String cameraPlaceUsername) {
		this.cameraPlaceUsername = cameraPlaceUsername;
	}

	@Validate(fname="状态",exp="sin(0,1)")
	public String getIsUse() {
		return isUse;
	}

	public void setIsUse(String isUse) {
		this.isUse = isUse;
	}

	@Validate(fname="乘车路线",exp="s(200b)")
	public String getByBus() {
		return byBus;
	}

	public void setByBus(String byBus) {
		this.byBus = byBus;
	}

	public String getExamAreaName() {
		return examAreaName;
	}

	public void setExamAreaName(String examAreaName) {
		this.examAreaName = examAreaName;
	}

	public String getParticularAreaCode() {
		return this.cityCode+"-"+this.examAreaCode;
	}

	public void setParticularAreaCode(String particularAreaCode) {
		this.particularAreaCode = particularAreaCode;
	}

	public String getExamAreaCode() {
		return examAreaCode;
	}

	public void setExamAreaCode(String examAreaCode) {
		this.examAreaCode = examAreaCode;
	}

	@Validate(fname="每日容纳人数",exp="i(0,999999)")
	public String getMaxPeople() {
		return maxPeople;
	}

	public void setMaxPeople(String maxPeople) {
		this.maxPeople = maxPeople;
	}

	@Validate(fname="所有考区均使用该摄像点",exp="sin(0,1)")
	public String getExamAreaAll() {
		return examAreaAll;
	}

	public void setExamAreaAll(String examAreaAll) {
		this.examAreaAll = examAreaAll;
	}

}
