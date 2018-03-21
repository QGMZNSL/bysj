/**
 * Copyright(c) 2012 西安云海信息技术有限责任公司 All Rights Reserved<br>
 * @Title: com.sincinfo.zxks.bean.Level.java<br>
 * @Description: 专业计划库层次对象 <br>
 * <br>
 * @author yuansh<br>
 * @date 2012-01-24
 * @version V1.0   
 */
package com.sincinfo.zxks.bean;

import org.river.nbf.validate.annotation.Validate;

/**
 * @ClassName: Level
 * @Description: 管理端操作员对象 <br>
 *               <br>
 * @author yuansh
 * @date Jan 12, 2012 2:04:59 PM<br>
 * 
 */
public class Level {

	private String levelCode = null; //层次代码
	private String levelName = null; //层次名称
	private String isUse = null;     //启用状态

	public Level() {
		this.levelCode = new String();
		this.levelName = new String();
		this.isUse = "1";
	}
	
	@Validate 
	public String getLevelCode() {
		return levelCode;
	}

	public void setLevelCode(String levelCode) {
		this.levelCode = levelCode;
	}

	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	public String getIsUse() {
		return isUse;
	}

	public void setIsUse(String isUse) {
		this.isUse = isUse;
	}
	

}
