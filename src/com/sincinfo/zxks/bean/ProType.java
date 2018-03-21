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
 * @Description: 专业类型设置 <br>
 *               <br>
 * @author yuansh
 * @date 2012-01-26 13:36 PM<br>
 * 
 */
public class ProType {
	//专业类型代码
	private String proTypeCode;

	//专业类型名称
	private String proTypeName;
	
	//层次代码
	private String levelCode;

	//是否启用
	private String isUse;
	
	//备注
	private String remarks;
	
	/*扩展*/
	private String levelName;

   @Validate  
	public String getProTypeCode() {
		return proTypeCode;
	}

	public void setProTypeCode(String proTypeCode) {
		this.proTypeCode = proTypeCode;
	}

    @Validate 
	public String getProTypeName() {
		return proTypeName;
	}

	public void setProTypeName(String proTypeName) {
		this.proTypeName = proTypeName;
	}

	@Validate  
	public String getLevelCode() {
		return levelCode;
	}

	public void setLevelCode(String levelCode) {
		this.levelCode = levelCode;
	}

  
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
	@Validate
	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

}
