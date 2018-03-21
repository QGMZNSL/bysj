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
 * @ClassName: PlanLevel
 * @Description: 主考院校设置 <br>
 *               <br>
 * @author yuansh
 * @date 2012-01-26 13:36 PM<br>
 * 
 */
public class BaseAcademy {
	//主考院校代码
	private String academyCode;

	//主考院校名称
	private String academyName;

	//城市代码
	private String cityCode;
	
	//地址
	private String academyAddress;
	
	//联系人
	private String academyLinkman;	
	
	//电话号码
	private String academyTelephone;
	
	//备注
	private String remarks;
	
	//是否启用
	private String isUse;
	
	//扩展：地市名称
	private String cityName;
	
	//扩展：专业代码
	private String proCode;

	@Validate 
	public String getAcademyCode() {
		return academyCode;
	}

	public void setAcademyCode(String academyCode) {
		this.academyCode = academyCode;
	}

	@Validate 
	public String getAcademyName() {
		return academyName;
	}

	public void setAcademyName(String academyName) {
		this.academyName = academyName;
	}

	@Validate 
	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	@Validate 
	public String getAcademyAddress() {
		return academyAddress;
	}

	public void setAcademyAddress(String academyAddress) {
		this.academyAddress = academyAddress;
	}

	@Validate 
	public String getAcademyLinkman() {
		return academyLinkman;
	}

	public void setAcademyLinkman(String academyLinkman) {
		this.academyLinkman = academyLinkman;
	}

	@Validate 
	public String getAcademyTelephone() {
		return academyTelephone;
	}

	public void setAcademyTelephone(String academyTelephone) {
		this.academyTelephone = academyTelephone;
	}

	@Validate 
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Validate
	public String getIsUse() {
		return isUse;
	}

	public void setIsUse(String isUse) {
		this.isUse = isUse;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getProCode() {
		return proCode;
	}

	public void setProCode(String proCode) {
		this.proCode = proCode;
	}

}
