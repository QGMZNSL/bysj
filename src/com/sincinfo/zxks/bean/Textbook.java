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
 * @Description: 教材设置 <br>
 *               <br>
 * @author yuansh
 * @date 2012-01-26 13:36 PM<br>
 * 
 */
public class Textbook {
	//教材代码    
	private String textbookCode;

	//教材名称
	private String textbookName;

	//主编
	private String textbookEditor;
	
	//出版社
	private String textbookPublisher;
	
	//出版时间
	private String publishTime;
	
	//价格
	private String textbookPrice;
	
	//备注
	private String remarks;
	private String isUse;//0删除1使用中
	private String unifiedBook;//是否统编教材
	private String syllabusCode;//课程代码
	private String syllabusName;// 课程名称

	@Validate 
	public String getTextbookCode() {
		return textbookCode;
	}

	public void setTextbookCode(String textbookCode) {
		this.textbookCode = textbookCode;
	}

	@Validate
	public String getTextbookName() {
		return textbookName;
	}

	public void setTextbookName(String textbookName) {
		this.textbookName = textbookName;
	}

	@Validate
	public String getTextbookEditor() {
		return textbookEditor;
	}


	public void setTextbookEditor(String textbookEditor) {
		this.textbookEditor = textbookEditor;
	}

	@Validate
	public String getTextbookPublisher() {
		return textbookPublisher;
	}


	public void setTextbookPublisher(String textbookPublisher) {
		this.textbookPublisher = textbookPublisher;
	}

	@Validate
	public String getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(String publishTime) {
		this.publishTime = publishTime;
	}

	@Validate
	public String getTextbookPrice() {
		return textbookPrice;
	}

	public void setTextbookPrice(String textbookPrice) {
		this.textbookPrice = textbookPrice;
	}

	@Validate
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getIsUse() {
		return isUse;
	}

	public void setIsUse(String isUse) {
		this.isUse = isUse;
	}

	public String getUnifiedBook() {
		return unifiedBook;
	}

	public void setUnifiedBook(String unifiedBook) {
		this.unifiedBook = unifiedBook;
	}

	public String getSyllabusCode() {
		return syllabusCode;
	}

	public void setSyllabusCode(String syllabusCode) {
		this.syllabusCode = syllabusCode;
	}

	public String getSyllabusName() {
		return syllabusName;
	}

	public void setSyllabusName(String syllabusName) {
		this.syllabusName = syllabusName;
	}
}