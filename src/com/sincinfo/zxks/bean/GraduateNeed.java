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
 * @Description: 毕业须持有证明 <br>
 * @author yuansh
 * @date 2012-01-26 13:36 PM<br>
 */
public class GraduateNeed {

	//毕业必要证明代码
	private String graduateNeedCode;
	
	//专业代码
	private String proCode;
	
	//证明名称
	private String graduateNeedName;
	
	//是否必要
	private String isMust;

	@Validate 
	public String getGraduateNeedCode() {
		return graduateNeedCode;
	}

	public void setGraduateNeedCode(String graduateNeedCode) {
		this.graduateNeedCode = graduateNeedCode;
	}

	public String getProCode() {
		return proCode;
	}

	public void setProCode(String proCode) {
		this.proCode = proCode;
	}

	public String getGraduateNeedName() {
		return graduateNeedName;
	}

	public void setGraduateNeedName(String graduateNeedName) {
		this.graduateNeedName = graduateNeedName;
	}

	public String getIsMust() {
		return isMust;
	}

	public void setIsMust(String isMust) {
		this.isMust = isMust;
	}

}
