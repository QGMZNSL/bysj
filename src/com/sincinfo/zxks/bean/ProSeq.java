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
 * @ClassName: ProSeq
 * @Description: 专业分类设置 <br>
 *               <br>
 * @author yuansh
 * @date 2012-01-26 13:36 PM<br>
 * 
 */
public class ProSeq {
	//专业分类代码
	private String proPartCode;

	//专业分类名称
	private String proPartName;

	//是否启用
	private String isUse;
	
	//备注
	private String remarks;

	@Validate
	public String getProPartCode() {
		return proPartCode;
	}

	public void setProPartCode(String proPartCode) {
		this.proPartCode = proPartCode;
	}
    
	@Validate
	public String getProPartName() {
		return proPartName;
	}

	public void setProPartName(String proPartName) {
		this.proPartName = proPartName;
	}

	@Validate
	public String getIsUse() {
		return isUse;
	}

	public void setIsUse(String isUse) {
		this.isUse = isUse;
	}

	@Validate
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	


}
