package com.sincinfo.zxks.bean;

import org.river.nbf.validate.annotation.Validate;

/**
 * @ClassName: BaseCity
 * @Description: 本省地市 <br>
 *               <br>
 * @author litian
 * @date Jan 22, 2012 12:36:09 AM<br>
 * 
 */
public class BaseCity {
	// 地市编号
	private String cityCode;
	// 地市名称
	private String cityName;
	// 地市简称
	private String cityShortName;
	// 上级地市代码
	private String parentRegion;
	@Validate
	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getCityShortName() {
		return cityShortName;
	}

	public void setCityShortName(String cityShortName) {
		this.cityShortName = cityShortName;
	}

	public String getParentRegion() {
		return parentRegion;
	}

	public void setParentRegion(String parentRegion) {
		this.parentRegion = parentRegion;
	}

}
