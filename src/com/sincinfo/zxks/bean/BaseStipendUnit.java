/**
 * Copyright(c) 2012 西安云海信息技术有限责任公司 All Rights Reserved<br>
 * @Title: com.sincinfo.zxks.bean.BaseStipendUnit.java<br>
 * @Description: 助学单位 <br>
 * <br>
 * @author litian<br>
 * @date Jan 31, 2012 9:29:07 AM 
 * @version V1.0   
 */
package com.sincinfo.zxks.bean;

import org.river.nbf.validate.annotation.Validate;

/**
 * @ClassName: BaseStipendUnit
 * @Description: 助学单位 <br>
 *               <br>
 * @author litian
 * @date Jan 31, 2012 9:29:07 AM<br>
 * 
 */
public class BaseStipendUnit {
	// 助学单位代码
	private String stipendUnitCode;

	// 助学单位名称
	private String stipendUnitName;

	// 助学单位所属地市
	private String cityCode;

	// 助学单位详细地址
	private String stipendUnitAddress;

	// 助学单位负责人
	private String stipendUnitDuty;

	// 助学单位负责人联系电话
	private String stipendUnitDutyTelephone;

	// 助学单位联系人
	private String stipendUnitLinkMan;

	// 助学单位联系人联系电话
	private String stipendUnitLinkTelephone;

	// 助学单位添加人
	private String stipendUnitAddUser;

	// 助学单位添加时间
	private String stipendUnitAddTime;

	// 是否大助学单位
	private String isBig;

	// 大助学单位设定人
	private String stipendUnitSettingUser;

	// 大助学单位设定时间
	private String stipendUnitSettingTime;

	// 启用状态
	private String isUse;

	/* 扩展 */
	private String oldUnitCode;
	
	// 助学单位考生总数
	private String studNum;
	
	// 指定考区
	private String examAreaCode;

	@Validate(fname = "助学单位代码", exp = "null")
	public String getStipendUnitCode() {
		return stipendUnitCode;
	}

	public void setStipendUnitCode(String stipendUnitCode) {
		this.stipendUnitCode = stipendUnitCode;
	}

	@Validate(fname = "助学单位代码", exp = "cname(2,25)")
	public String getStipendUnitName() {
		return stipendUnitName;
	}

	public void setStipendUnitName(String stipendUnitName) {
		this.stipendUnitName = stipendUnitName;
	}

	@Validate(fname = "地市代码", exp = "null")
	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	@Validate(fname = "助学单位地址", exp = "null")
	public String getStipendUnitAddress() {
		return stipendUnitAddress;
	}

	public void setStipendUnitAddress(String stipendUnitAddress) {
		this.stipendUnitAddress = stipendUnitAddress;
	}

	@Validate(fname = "负责人", exp = "cname(2,10)")
	public String getStipendUnitDuty() {
		return stipendUnitDuty;
	}

	public void setStipendUnitDuty(String stipendUnitDuty) {
		this.stipendUnitDuty = stipendUnitDuty;
	}

	@Validate(fname = "负责人联系方式", exp = "p(13)")
	public String getStipendUnitDutyTelephone() {
		return stipendUnitDutyTelephone;
	}

	public void setStipendUnitDutyTelephone(String stipendUnitDutyTelephone) {
		this.stipendUnitDutyTelephone = stipendUnitDutyTelephone;
	}

	@Validate(fname = "联系人", exp = "cname(2,10)")
	public String getStipendUnitLinkMan() {
		return stipendUnitLinkMan;
	}

	public void setStipendUnitLinkMan(String stipendUnitLinkMan) {
		this.stipendUnitLinkMan = stipendUnitLinkMan;
	}

	@Validate(fname = "联系人联系方式", exp = "p(13)")
	public String getStipendUnitLinkTelephone() {
		return stipendUnitLinkTelephone;
	}

	public void setStipendUnitLinkTelephone(String stipendUnitLinkTelephone) {
		this.stipendUnitLinkTelephone = stipendUnitLinkTelephone;
	}

	public String getStipendUnitAddUser() {
		return stipendUnitAddUser;
	}

	public void setStipendUnitAddUser(String stipendUnitAddUser) {
		this.stipendUnitAddUser = stipendUnitAddUser;
	}

	public String getStipendUnitAddTime() {
		return stipendUnitAddTime;
	}

	public void setStipendUnitAddTime(String stipendUnitAddTime) {
		this.stipendUnitAddTime = stipendUnitAddTime;
	}

	@Validate(fname = "是否大助学单位", exp = "null")
	public String getIsBig() {
		return isBig;
	}

	public void setIsBig(String isBig) {
		this.isBig = isBig;
	}

	public String getStipendUnitSettingUser() {
		return stipendUnitSettingUser;
	}

	public void setStipendUnitSettingUser(String stipendUnitSettingUser) {
		this.stipendUnitSettingUser = stipendUnitSettingUser;
	}

	public String getStipendUnitSettingTime() {
		return stipendUnitSettingTime;
	}

	public void setStipendUnitSettingTime(String stipendUnitSettingTime) {
		this.stipendUnitSettingTime = stipendUnitSettingTime;
	}

	@Validate(fname = "启用状态", exp = "null")
	public String getIsUse() {
		return isUse;
	}

	public void setIsUse(String isUse) {
		this.isUse = isUse;
	}

	public String getOldUnitCode() {
		return oldUnitCode;
	}

	public void setOldUnitCode(String oldUnitCode) {
		this.oldUnitCode = oldUnitCode;
	}

    public String getStudNum() {
        return studNum;
    }

    public void setStudNum(String studNum) {
        this.studNum = studNum;
    }

    public String getExamAreaCode() {
        return examAreaCode;
    }

    public void setExamAreaCode(String examAreaCode) {
        this.examAreaCode = examAreaCode;
    }

}
