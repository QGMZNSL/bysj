/**
 * Copyright(c) 2012 西安云海信息技术有限责任公司 All Rights Reserved<br>
 * @Title: com.sincinfo.zxks.core.day.datapreserve.BaseStudinfoChange.java<br>
 * @Description: 考生基本信息变更记录 <br>
 * <br>
 * @author litian<br>
 * @date Feb 6, 2012 9:13:20 AM 
 * @version V1.0   
 */
package com.sincinfo.zxks.bean;

/**
 * @ClassName: BaseStudinfoChange
 * @Description: 考生基本信息变更记录 <br>
 *               <br>
 * @author litian
 * @date Feb 6, 2012 9:13:20 AM<br>
 * 
 */
public class BaseStudinfoChange {
	// 记录id
	private String changeId;

	// 考生准考证号
	private String studExamCode;

	// 变更类型
	private String changeTypeCode;

	// 旧数据
	private String oldInfo;

	// 新数据
	private String newInfo;

	// 变更原因
	private String changeReason;

	// 证明材料
	private String changeProve;

	// 填写申请操作员
	private String changeApplyUser;

	// 申请时间
	private String changeApplyTime;

	// 审核状态
	private String changeAuditStatus;

	// 审核人
	private String changeAuditUser;

	// 审核时间
	private String changeAuditTime;

	// 审核原因
	private String changeAuditReason;

	// 上报状态
	private String recordReportSign;

	/* 扩展信息 */
	//如果为字典表中的code  则查出相应的name
	private String codeName;
	//地市code
	private String cityCode;
	// 变更类型信息数据
	private String changeTypeName;
	private String changeTemplate;

	// 申请人审核人信息
	private String changeApplyUserRealName;
	private String changeAuditUserRealName;

	// 考生个人信息
	private String studName;
	private String studIdnum;
	private String proCode;
	private String proName;
	
	// 是否此修改导致换人
	private String isPeopleChange; 

	public void setChangeId(String changeId) {
		this.changeId = changeId;
	}

	public String getChangeId() {
		return this.changeId;
	}

	public void setStudExamCode(String studExamCode) {
		this.studExamCode = studExamCode;
	}

	public String getStudExamCode() {
		return this.studExamCode;
	}

	public void setChangeTypeCode(String changeTypeCode) {
		this.changeTypeCode = changeTypeCode;
	}

	public String getChangeTypeCode() {
		return this.changeTypeCode;
	}

	public void setOldInfo(String oldInfo) {
		this.oldInfo = oldInfo;
	}

	public String getOldInfo() {
		return this.oldInfo;
	}

	public void setNewInfo(String newInfo) {
		this.newInfo = newInfo;
	}

	public String getNewInfo() {
		return this.newInfo;
	}

	public void setChangeReason(String changeReason) {
		this.changeReason = changeReason;
	}

	public String getChangeReason() {
		return this.changeReason;
	}

	public void setChangeProve(String changeProve) {
		this.changeProve = changeProve;
	}

	public String getChangeProve() {
		return this.changeProve;
	}

	public void setChangeApplyUser(String changeApplyUser) {
		this.changeApplyUser = changeApplyUser;
	}

	public String getChangeApplyUser() {
		return this.changeApplyUser;
	}

	public void setChangeApplyTime(String changeApplyTime) {
		this.changeApplyTime = changeApplyTime;
	}

	public String getChangeApplyTime() {
		return this.changeApplyTime;
	}

	public void setChangeAuditStatus(String changeAuditStatus) {
		this.changeAuditStatus = changeAuditStatus;
	}

	public String getChangeAuditStatus() {
		return this.changeAuditStatus;
	}

	public void setChangeAuditUser(String changeAuditUser) {
		this.changeAuditUser = changeAuditUser;
	}

	public String getChangeAuditUser() {
		return this.changeAuditUser;
	}

	public void setChangeAuditTime(String changeAuditTime) {
		this.changeAuditTime = changeAuditTime;
	}

	public String getChangeAuditTime() {
		return this.changeAuditTime;
	}

	public void setChangeAuditReason(String changeAuditReason) {
		this.changeAuditReason = changeAuditReason;
	}

	public String getChangeAuditReason() {
		return this.changeAuditReason;
	}

	public void setRecordReportSign(String recordReportSign) {
		this.recordReportSign = recordReportSign;
	}

	public String getRecordReportSign() {
		return this.recordReportSign;
	}

	public String getChangeTypeName() {
		return changeTypeName;
	}

	public void setChangeTypeName(String changeTypeName) {
		this.changeTypeName = changeTypeName;
	}

	public String getChangeTemplate() {
		return changeTemplate;
	}

	public void setChangeTemplate(String changeTemplate) {
		this.changeTemplate = changeTemplate;
	}

	public String getChangeApplyUserRealName() {
		return changeApplyUserRealName;
	}

	public void setChangeApplyUserRealName(String changeApplyUserRealName) {
		this.changeApplyUserRealName = changeApplyUserRealName;
	}

	public String getChangeAuditUserRealName() {
		return changeAuditUserRealName;
	}

	public void setChangeAuditUserRealName(String changeAuditUserRealName) {
		this.changeAuditUserRealName = changeAuditUserRealName;
	}

	public String getStudName() {
		return studName;
	}

	public void setStudName(String studName) {
		this.studName = studName;
	}

	public String getStudIdnum() {
		return studIdnum;
	}

	public void setStudIdnum(String studIdnum) {
		this.studIdnum = studIdnum;
	}

	public String getProCode() {
		return proCode;
	}

	public void setProCode(String proCode) {
		this.proCode = proCode;
	}

	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	public String getCodeName() {
		return codeName;
	}

	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getIsPeopleChange() {
		return isPeopleChange;
	}

	public void setIsPeopleChange(String isPeopleChange) {
		this.isPeopleChange = isPeopleChange;
	}
	
}
