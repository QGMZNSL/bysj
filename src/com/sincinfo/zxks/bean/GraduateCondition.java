/**
 * Copyright(c) 2012 西安云海信息技术有限责任公司 All Rights Reserved<br>
 * @Title: com.sincinfo.zxks.bean.BaseUser.java<br>
 * @Description: 管理端操作员对象 <br>
 * @author litian<br>
 * @date Jan 12, 2012 2:04:59 PM 
 * @version V1.0   
 */
package com.sincinfo.zxks.bean;

import org.river.nbf.validate.annotation.Validate;

/**
 * @ClassName: PlanLevel
 * @Description: 毕业条件设置 <br>
 * @author yuansh
 * @date 2012-01-26 13:36 PM<br>
 * 
 */
public class GraduateCondition {
	
	//专业代码
	private String proCode;
	
	//毕业学分
	private String graduateConditionCredit;
	
	//毕业条件说明
	private String graduateConditionDescribe;
	
	//保存类型
	private String saveType;

	@Validate
	public String getProCode() {
		return proCode;
	}

	public void setProCode(String proCode) {
		this.proCode = proCode;
	}

	public String getGraduateConditionCredit() {
		return graduateConditionCredit;
	}

	public void setGraduateConditionCredit(String graduateConditionCredit) {
		this.graduateConditionCredit = graduateConditionCredit;
	}

	public String getGraduateConditionDescribe() {
		return graduateConditionDescribe;
	}

	public void setGraduateConditionDescribe(String graduateConditionDescribe) {
		this.graduateConditionDescribe = graduateConditionDescribe;
	}

	public String getSaveType() {
		return saveType;
	}

	public void setSaveType(String saveType) {
		this.saveType = saveType;
	}
	
}
