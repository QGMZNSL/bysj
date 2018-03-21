/**
 * Copyright(c) 2012 西安云海信息技术有限责任公司 All Rights Reserved<br>
 * @Title: com.sincinfo.zxks.bean.BasePosition.java<br>
 * @Description: 用户职位 <br>
 * <br>
 * @author litian<br>
 * @date Jan 20, 2012 8:49:59 AM 
 * @version V1.0   
 */
package com.sincinfo.zxks.bean;

import org.river.nbf.validate.annotation.Validate;

/**
 * @ClassName: BasePosition
 * @Description: 用户职位 <br>
 *               <br>
 * @author litian
 * @date Jan 20, 2012 8:49:59 AM<br>
 * 
 */
public class BasePosition {
	// 职位编号
	private String positionCode;
	// 职位名称
	private String positionName;
	// 默认权限码串
	private String defaultPowerArray;
	// 部门编号
	private String departmentCode;

	/*- 扩展部分 -*/
	// 权限设置状态，如果是0则为未设置，>0为已设置
	private String powerSetNum;
	// 部门名称 
	private String departmentName;
	
	/**
	 * 判断权限是否设置
	 */
	public void setPowerSetedCount() {
		int setedNum = 0;
		for ( int idx = 0; idx < defaultPowerArray.length(); idx++) {
			if ( defaultPowerArray.charAt(idx) == '1')
				setedNum += 1;
		}
		
		this.powerSetNum = String.valueOf( setedNum);
	}
 
	public String getPositionCode() {
		return positionCode;
	}

	public void setPositionCode(String positionCode) {
		this.positionCode = positionCode;
	}

	@Validate (fname="职位名称", exp="null")
	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	public String getDefaultPowerArray() {
		return defaultPowerArray;
	}

	public void setDefaultPowerArray(String defaultPowerArray) {
		this.defaultPowerArray = defaultPowerArray;
	}

	public String getDepartmentCode() {
		return departmentCode;
	}

	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}

	public String getPowerSetNum() {
		return powerSetNum;
	}

	public void setPowerSetNum(String powerSetNum) {
		this.powerSetNum = powerSetNum;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

}
