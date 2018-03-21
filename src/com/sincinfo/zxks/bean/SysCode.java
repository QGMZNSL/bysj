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


/**
 * @ClassName: SysCode
 * @Description: 对应各项基础字典 <br>
 *               <br>
 * @author litian
 * @date Jan 12, 2012 2:04:59 PM<br>
 * 
 */
public class SysCode {
	// 字典代码
	private String code;
	// 名称
	private String name;
	// 是否启用
	private String isUse;
	// 是否默认
	private String isDefault;
	// 显示顺序
	private String listOrder;
	// 备注
	private String remarks;
	// 保留状态 默认0
	private String rStatus;
	// 保留字段 默认‘-’（中划线）
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
