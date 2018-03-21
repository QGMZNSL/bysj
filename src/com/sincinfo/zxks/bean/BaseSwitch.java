/**
 * Copyright(c) 2012 西安云海信息技术有限责任公司 All Rights Reserved<br>
 * @Title: com.sincinfo.zxks.bean.BaseSwitch.java<br>
 * @Description: 考籍管理部分相关开关 <br>
 * <br>
 * @author litian<br>
 * @date Mar 27, 2012 8:51:36 AM 
 * @version V1.0   
 */
package com.sincinfo.zxks.bean;

/**
 * @ClassName: BaseSwitch
 * @Description: 考籍管理部分相关开关 <br>
 *               <br>
 * @author litian
 * @date Mar 27, 2012 8:51:36 AM<br>
 * 
 */
public class BaseSwitch {
	// 开关以及条件配置编号PK
	private String switchCode;

	// 开关以及条件配置的类型 0毕业 1转出2转入
	private String switchType;

	// 开关以及条件配置的内容（值)
	private String switchValue;

	// 备注说明
	private String remarks;

	// 地市代码（省内通用配置为00）
	private String cityCode;

	public String getSwitchCode() {
		return switchCode;
	}

	public void setSwitchCode(String switchCode) {
		this.switchCode = switchCode;
	}

	public String getSwitchType() {
		return switchType;
	}

	public void setSwitchType(String switchType) {
		this.switchType = switchType;
	}

	public String getSwitchValue() {
		return switchValue;
	}

	public void setSwitchValue(String switchValue) {
		this.switchValue = switchValue;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

}
