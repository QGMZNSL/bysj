package com.sincinfo.zxks.bean;

import org.river.nbf.validate.annotation.Validate;


public class MainTenanceAddress {
	String cityCode;
	//地市编号
	String operCode;
//	业务编号 11 毕业证申请 12 转档 01报名
	String studPostalAddress;
//	通讯地址
	String linkTelephone;
//	咨询电话
	String byBus;
//	乘车路线	
	String linkDetail;
//	备注说明
	String linkMan;
//	联系人  
	String masterMan;
//	负责人
	String masterTelephone;
//	负责人电话	
	String cityName;
//	城市名称
	public String getCityCode() {
		return cityCode;
	}
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	public String getOperCode() {
		return operCode;
	}
	public void setOperCode(String operCode) {
		this.operCode = operCode;
	}
	@Validate(fname="详细地址",exp="s(50b)")
	public String getStudPostalAddress() {
		return studPostalAddress;
	}
	public void setStudPostalAddress(String studPostalAddress) {
		this.studPostalAddress = studPostalAddress;
	}
	@Validate(fname="联系电话",exp="p(15)")
	public String getLinkTelephone() {
		return linkTelephone;
	}
	public void setLinkTelephone(String linkTelephone) {
		this.linkTelephone = linkTelephone;
	}
	public String getByBus() {
		return byBus;
	}
	public void setByBus(String byBus) {
		this.byBus = byBus;
	}
	public String getLinkMan() {
		return linkMan;
	}
	public void setLinkMan(String linkMan) {
		this.linkMan = linkMan;
	}
	public String getMasterMan() {
		return masterMan;
	}
	public void setMasterMan(String masterMan) {
		this.masterMan = masterMan;
	}
	public String getMasterTelephone() {
		return masterTelephone;
	}
	public void setMasterTelephone(String masterTelephone) {
		this.masterTelephone = masterTelephone;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getLinkDetail() {
		return linkDetail;
	}
	public void setLinkDetail(String linkDetail) {
		this.linkDetail = linkDetail;
	}
}
