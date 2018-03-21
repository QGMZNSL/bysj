package com.sincinfo.zxks.bean;

public class SysCodeCertificate {
	private String code;//
	private String name;//
	private String isUse;//默认1；0禁用1启用
	private String isDefault;//默认0；0否1是
	private String listOrder;// 默认0	暂不用
	private String remarks;
	private String rStatus;
	private String rMask;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIsUse() {
		return isUse;
	}
	public void setIsUse(String isUse) {
		this.isUse = isUse;
	}
	public String getIsDefault() {
		return isDefault;
	}
	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}
	public String getListOrder() {
		return listOrder;
	}
	public void setListOrder(String listOrder) {
		this.listOrder = listOrder;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getRStatus() {
		return rStatus;
	}
	public void setRStatus(String status) {
		rStatus = status;
	}
	public String getRMask() {
		return rMask;
	}
	public void setRMask(String mask) {
		rMask = mask;
	}
	
}
