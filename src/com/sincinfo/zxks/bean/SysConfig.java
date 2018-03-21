package com.sincinfo.zxks.bean;

public class SysConfig {
	private String sysCfgId;//配置编号
	private String sysCfgName;//配置别名
	private String sysCfgType;//配置类型
	private String sysCfgDescribe;//配置说明
	private String sysCfgContent;//配置内容
	public String getSysCfgId() {
		return sysCfgId;
	}
	public void setSysCfgId(String sysCfgId) {
		this.sysCfgId = sysCfgId;
	}
	public String getSysCfgName() {
		return sysCfgName;
	}
	public void setSysCfgName(String sysCfgName) {
		this.sysCfgName = sysCfgName;
	}
	public String getSysCfgType() {
		return sysCfgType;
	}
	public void setSysCfgType(String sysCfgType) {
		this.sysCfgType = sysCfgType;
	}
	public String getSysCfgDescribe() {
		return sysCfgDescribe;
	}
	public void setSysCfgDescribe(String sysCfgDescribe) {
		this.sysCfgDescribe = sysCfgDescribe;
	}
	public String getSysCfgContent() {
		return sysCfgContent;
	}
	public void setSysCfgContent(String sysCfgContent) {
		this.sysCfgContent = sysCfgContent;
	}
	public SysConfig(String sysCfgId, String sysCfgName, String sysCfgType,
			String sysCfgDescribe, String sysCfgContent) {
		super();
		this.sysCfgId = sysCfgId;
		this.sysCfgName = sysCfgName;
		this.sysCfgType = sysCfgType;
		this.sysCfgDescribe = sysCfgDescribe;
		this.sysCfgContent = sysCfgContent;
	}
	public SysConfig() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
