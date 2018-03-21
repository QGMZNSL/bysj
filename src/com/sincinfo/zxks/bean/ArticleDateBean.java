package com.sincinfo.zxks.bean;

import java.util.Date;

public class ArticleDateBean {
	private int articleId;//编号
	private String articleTitle;//标题
	private String articleSubTitle;//副标题
	private String articleTitleStyle;//标题所采用的样式
	private String articleContent;//内容
	private String articleKeyWord;//关键字
	private String articleFile;//附件
	private String articleAddUser;//添加人
	private Date articleAddTime;//添加时间
	private String articleAuthor;//发布单位
	private String articleAuditStatus;//审核状态   默认0： 0未发布 1已发布 2审核不通过
	private String articleAuditUser;//审核人
	private Date articleAuditTime;//审核时间
	private String articleUnAuditReason;//审核不通过原因 
	private int hits;//点击数
	private int classId;// 信息分类编号
	private String className;// 信息分类名称
	
	
	public int getArticleId() {
		return articleId;
	}
	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}
	public String getArticleTitle() {
		return articleTitle;
	}
	public void setArticleTitle(String articleTitle) {
		this.articleTitle = articleTitle;
	}
	public String getArticleSubTitle() {
		return articleSubTitle;
	}
	public void setArticleSubTitle(String articleSubTitle) {
		this.articleSubTitle = articleSubTitle;
	}
	public String getArticleTitleStyle() {
		return articleTitleStyle;
	}
	public void setArticleTitleStyle(String articleTitleStyle) {
		this.articleTitleStyle = articleTitleStyle;
	}
	public String getArticleContent() {
		return articleContent;
	}
	public void setArticleContent(String articleContent) {
		this.articleContent = articleContent;
	}
	public String getArticleKeyWord() {
		return articleKeyWord;
	}
	public void setArticleKeyWord(String articleKeyWord) {
		this.articleKeyWord = articleKeyWord;
	}
	public String getArticleFile() {
		return articleFile;
	}
	public void setArticleFile(String articleFile) {
		this.articleFile = articleFile;
	}
	public String getArticleAddUser() {
		return articleAddUser;
	}
	public void setArticleAddUser(String articleAddUser) {
		this.articleAddUser = articleAddUser;
	}
	public Date getArticleAddTime() {
		return articleAddTime;
	}
	public void setArticleAddTime(Date articleAddTime) {
		this.articleAddTime = articleAddTime;
	}
	public String getArticleAuthor() {
		return articleAuthor;
	}
	public void setArticleAuthor(String articleAuthor) {
		this.articleAuthor = articleAuthor;
	}
	public String getArticleAuditStatus() {
		return articleAuditStatus;
	}
	public void setArticleAuditStatus(String articleAuditStatus) {
		this.articleAuditStatus = articleAuditStatus;
	}
	public String getArticleAuditUser() {
		return articleAuditUser;
	}
	public void setArticleAuditUser(String articleAuditUser) {
		this.articleAuditUser = articleAuditUser;
	}
	public Date getArticleAuditTime() {
		return articleAuditTime;
	}
	public void setArticleAuditTime(Date articleAuditTime) {
		this.articleAuditTime = articleAuditTime;
	}
	public String getArticleUnAuditReason() {
		return articleUnAuditReason;
	}
	public void setArticleUnAuditReason(String articleUnAuditReason) {
		this.articleUnAuditReason = articleUnAuditReason;
	}
	public int getHits() {
		return hits;
	}
	public void setHits(int hits) {
		this.hits = hits;
	}
	public int getClassId() {
		return classId;
	}
	public void setClassId(int classId) {
		this.classId = classId;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	
	
	
	public ArticleDateBean(int articleId, String articleTitle,
			String articleSubTitle, String articleTitleStyle,
			String articleContent, String articleKeyWord, String articleFile,
			String articleAddUser, Date articleAddTime, String articleAuthor,
			String articleAuditStatus, String articleAuditUser,
			Date articleAuditTime, String articleUnAuditReason, int hits,
			int classId, String className) {
		super();
		this.articleId = articleId;
		this.articleTitle = articleTitle;
		this.articleSubTitle = articleSubTitle;
		this.articleTitleStyle = articleTitleStyle;
		this.articleContent = articleContent;
		this.articleKeyWord = articleKeyWord;
		this.articleFile = articleFile;
		this.articleAddUser = articleAddUser;
		this.articleAddTime = articleAddTime;
		this.articleAuthor = articleAuthor;
		this.articleAuditStatus = articleAuditStatus;
		this.articleAuditUser = articleAuditUser;
		this.articleAuditTime = articleAuditTime;
		this.articleUnAuditReason = articleUnAuditReason;
		this.hits = hits;
		this.classId = classId;
		this.className = className;
	}
	public ArticleDateBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
