package com.sincinfo.zxks.bean;

import java.util.Date;

import org.river.nbf.validate.annotation.Validate;

import com.sincinfo.zxks.common.util.StringTool;

public class BaseExamination {

	// Examination_Code 考试代码
	private String examinationCode;
	// Examination_Year 考试年
	private String examinationYear;
	// Examination_Month 考试月
	private String examinationMonth;
	// Examination_Money 报考费
	private String examinationMoney;
	// Exam_Room_Standard 考场规格
	private String examRoomStandard;
	// Pre_Apply_Start 预报名开始日期
	private String preApplyStart;
	// Pre_Apply_End 预报名结束日期
	private String preApplyEnd;
	// Pre_Apply_Notic 预报名须知代码[新生报名须知]
	private String preApplyNotic;
	// Gather_Start 正式报名开始日期
	private String gatherStart;
	// Gather _End 正式报名结束日期
	private String gatherEnd;
	// Gather_Notice 正式报名须知代码
	private String gatherNotice;
	// Site_Up_Start 个人报考开始日期
	private String siteUpStart;
	// Site_Up_End 个人报考结束日期
	private String siteUpEnd;
	// Site_Up_Notice 个人报考须知代码[考试须知]
	private String siteUpNotice;
	// Pay_Start 交费开始日期
	private String payStart;
	// Pay_End 交费结束日期
	private String payEnd;
	// Pay_Notice 交费须知代码
	private String payNotice;
	// Print_Inform_Start 打印考试通知单开始日期
	private String printInformStart;
	// Print_Inform_End 打印考试通知单结束日期
	private String printInformEnd;
	// Examination_Notice 考试须知代码
	private String examinationNotice;
	// Score_Issue_Stat 成绩发布状态
	private String scoreIssueStat="0";
	// Score_Issue_User 成绩发布人
	private String scoreIssueUser;
	// Score_Issue_Date 成绩发布时间
	private String scoreIssueDate;
	
	private String nonce;
	
	private String currDate;
	
    /**
     * 扩展字段
     */
	//考试状态
	private String examStatus;
	
	// 考试须知名称
	private String siteUpNoticeName;
	//新生报名须知
	private String preApplyNoticName;
	

	public String getExaminationCode() {
		return examinationCode;
	}

	public void setExaminationCode(String examinationCode) {
		this.examinationCode = examinationCode;
	}

	public String getExaminationYear() {
		return examinationYear;
	}

	public void setExaminationYear(String examinationYear) {
		this.examinationYear = examinationYear;
	}

	
	public String getExaminationMonth() {
		return examinationMonth;
	}
	

	
	public void setExaminationMonth(String examinationMonth) {
		this.examinationMonth = examinationMonth;
	}
	
	@Validate(exp = "null & f(0,99)")
	public String getExaminationMoney() {
		return examinationMoney;
	}

	public void setExaminationMoney(String examinationMoney) {
		this.examinationMoney = examinationMoney;
	}
	@Validate(exp = "null")
	public String getExamRoomStandard() {
		return examRoomStandard;
	}

	public void setExamRoomStandard(String examRoomStandard) {
		this.examRoomStandard = examRoomStandard;
	}
	@Validate(exp = "date")
	public String getPreApplyStart() {
		return preApplyStart;
	}

	public void setPreApplyStart(String preApplyStart) {
		this.preApplyStart = preApplyStart;
	}
	@Validate(exp = "date&sgt(*preApplyStart)")
	public String getPreApplyEnd() {
		return preApplyEnd;
	}

	public void setPreApplyEnd(String preApplyEnd) {
		this.preApplyEnd = preApplyEnd;
	}
	@Validate(exp = "null")
	public String getPreApplyNotic() {
		return preApplyNotic;
	}

	public void setPreApplyNotic(String preApplyNotic) {
		this.preApplyNotic = preApplyNotic;
	}
	@Validate(exp = "null")
	public String getGatherStart() {
		return gatherStart;
	}
	
	public void setGatherStart(String gatherStart) {
		this.gatherStart = gatherStart;
	}
	@Validate(exp = "null")
	public String getGatherEnd() {
		return gatherEnd;
	}

	public void setGatherEnd(String gatherEnd) {
		this.gatherEnd = gatherEnd;
	}

	public String getGatherNotice() {
		return gatherNotice;
	}

	public void setGatherNotice(String gatherNotice) {
		this.gatherNotice = gatherNotice;
	}
	@Validate(exp = "null")
	public String getSiteUpStart() {
		return siteUpStart;
	}

	public void setSiteUpStart(String siteUpStart) {
		this.siteUpStart = siteUpStart;
	}
	@Validate(exp = "null")
	public String getSiteUpEnd() {
		return siteUpEnd;
	}

	public void setSiteUpEnd(String siteUpEnd) {
		this.siteUpEnd = siteUpEnd;
	}
	
	@Validate(exp = "null")
	public String getSiteUpNotice() {
		return siteUpNotice;
	}

	public void setSiteUpNotice(String siteUpNotice) {
		this.siteUpNotice = siteUpNotice;
	}
	@Validate(exp = "null")
	public String getPayStart() {
		return payStart;
	}

	public void setPayStart(String payStart) {
		this.payStart = payStart;
	}
	@Validate(exp = "null")
	public String getPayEnd() {
		return payEnd;
	}

	public void setPayEnd(String payEnd) {
		this.payEnd = payEnd;
	}

	public String getPayNotice() {
		return payNotice;
	}

	public void setPayNotice(String payNotice) {
		this.payNotice = payNotice;
	}
	@Validate(exp = "null")
	public String getPrintInformStart() {
		return printInformStart;
	}

	public void setPrintInformStart(String printInformStart) {
		this.printInformStart = printInformStart;
	}
	@Validate(exp = "null")
	public String getPrintInformEnd() {
		return printInformEnd;
	}

	public void setPrintInformEnd(String printInformEnd) {
		this.printInformEnd = printInformEnd;
	}

	public String getExaminationNotice() {
		return examinationNotice;
	}

	public void setExaminationNotice(String examinationNotice) {
		this.examinationNotice = examinationNotice;
	}

	public String getScoreIssueStat() {
		return scoreIssueStat;
	}

	public void setScoreIssueStat(String scoreIssueStat) {
		this.scoreIssueStat = scoreIssueStat;
	}

	public String getScoreIssueUser() {
		return scoreIssueUser;
	}

	public void setScoreIssueUser(String scoreIssueUser) {
		this.scoreIssueUser = scoreIssueUser;
	}

	public String getScoreIssueDate() {
		return scoreIssueDate;
	}

	public void setScoreIssueDate(String scoreIssueDate) {
		this.scoreIssueDate = scoreIssueDate;
	}

	public String getExamStatus() {

	      //this.setExamStatus(x);
		
		return examStatus;
	}

	public void setExamStatus(String examStatus) {
			
	      String status = "新生未开始报名";		
	       String currTime = StringTool.getDate(new Date(), "yyyy-MM-dd");
	      if(StringTool.strToDate(currTime).before(StringTool.strToDate(this.preApplyStart)))
	      {
//		      系统时间小于新生报名开始时间	新生未开始报名
	    	  status = "新生未开始报名";
	      }else if (StringTool.strToDate(currTime).after(StringTool.strToDate(this.gatherStart)) && StringTool.strToDate(currTime).after(StringTool.strToDate(this.preApplyEnd)) ){
//		      系统时间等于大于正式报名开始日期 且 小于等于新生报名截止日期	新生开始报名、正式报名
	    	  status = "新生开始报名、正式报名";
	      }else if(StringTool.strToDate(currTime).before(StringTool.strToDate(this.preApplyEnd)) && StringTool.strToDate(currTime).before(StringTool.strToDate(this.gatherEnd)) ){
//		      系统时间大于新生报名截止日期且小于等于正式报名截止日期	正式报名
	    	  status = "正式报名";
	      }else if(StringTool.strToDate(currTime).after(StringTool.strToDate(this.siteUpStart)) && StringTool.strToDate(currTime).before(StringTool.strToDate(this.gatherStart))){
//		      系统时间等于大于个人报考开始日期且小于正式报名截止日期	个人报考、正式报名
             status = "个人报考、正式报名";
	      }else if(StringTool.strToDate(currTime).after(StringTool.strToDate(this.gatherEnd)) && StringTool.strToDate(currTime).before(StringTool.strToDate(this.siteUpEnd))){
//		      系统时间大于正式报名截止日期且小于等于个人报名截止日期	个人报考
	    	  status = "个人报考";
	      }else if(StringTool.strToDate(currTime).after(StringTool.strToDate(this.payStart)) && StringTool.strToDate(currTime).before(StringTool.strToDate(this.siteUpEnd))){
//		      系统时间等于大于交费开始日期且小于个人报名截止日期	个人报考、考生交费
	    	  status = "个人报考、考生交费";
	      }else if(StringTool.strToDate(currTime).after(StringTool.strToDate(this.siteUpEnd)) && StringTool.strToDate(currTime).before(StringTool.strToDate(this.payEnd))){
//		      系统时间大于个人报考截止日期且小于等于交费截止日期	考生交费
	    	  status = "考生交费";
	      }else if(StringTool.strToDate(currTime).after(StringTool.strToDate(this.printInformStart)) && StringTool.strToDate(currTime).before(StringTool.strToDate(this.printInformEnd))){
//		      系统时间等于大于打印通知单开始日期且小于等于打印通知单截止日期	
	    	  status = "打印通知单";
	      }else if(false){
//		      系统时间大于打印通知单截止日期且小于考试开始时间	考试未开始
	    	  status = "考试未开始";
	      }else if(false){
//		      系统时间等于大于考试开始时间	
	    	  status = "开始考试";
	      }else if(false){
//		      系统时间大于考试结束时间	
	    	  status = "考试结束";
	      }
		this.examStatus = status;
	}

	public String getSiteUpNoticeName() {
		return siteUpNoticeName;
	}

	public void setSiteUpNoticeName(String siteUpNoticeName) {
		this.siteUpNoticeName = siteUpNoticeName;
	}

	public String getPreApplyNoticName() {
		return preApplyNoticName;
	}

	public void setPreApplyNoticName(String preApplyNoticName) {
		this.preApplyNoticName = preApplyNoticName;
	}

	public String getNonce() {
		return nonce;
	}

	public void setNonce(String nonce) {
		this.nonce = nonce;
	}

	public String getCurrDate() {
		return currDate;
	}

	public void setCurrDate(String currDate) {
		this.currDate = currDate;
	}
	
	
	

}
