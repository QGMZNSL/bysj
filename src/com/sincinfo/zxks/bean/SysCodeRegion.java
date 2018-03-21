package com.sincinfo.zxks.bean;

public class SysCodeRegion {
	private String regionCode;// 行政区划代码
	private String regionName;// 行政区划名称
	private String provinceName;// 省
	private String cityName;// 市
	private String regionGrade;// 地区级别
	private String parentRegion;// 父地市代码
	private String allowTransfer;// 是否允许转考

	public String getRegionCode() {
		return regionCode;
	}

	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getRegionGrade() {
		return regionGrade;
	}

	public void setRegionGrade(String regionGrade) {
		this.regionGrade = regionGrade;
	}

	public String getParentRegion() {
		return parentRegion;
	}

	public void setParentRegion(String parentRegion) {
		this.parentRegion = parentRegion;
	}

	public String getAllowTransfer() {
		return allowTransfer;
	}

	public void setAllowTransfer(String allowTransfer) {
		this.allowTransfer = allowTransfer;
	}

	public SysCodeRegion(String regionCode, String regionName,
			String provinceName, String cityName, String regionGrade,
			String parentRegion, String allowTransfer) {
		super();
		this.regionCode = regionCode;
		this.regionName = regionName;
		this.provinceName = provinceName;
		this.cityName = cityName;
		this.regionGrade = regionGrade;
		this.parentRegion = parentRegion;
		this.allowTransfer = allowTransfer;
	}

	public SysCodeRegion() {
		super();
		// TODO Auto-generated constructor stub
	}

}
