package com.sincinfo.zxks.core.day.message;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import org.bouncycastle.jce.provider.symmetric.Grain128.Base;

import com.sincinfo.zxks.bean.ArticleDateBean;
import com.sincinfo.zxks.bean.BaseDepartment;
import com.sincinfo.zxks.bean.BaseNewsArticle;
import com.sincinfo.zxks.bean.BaseNewsClass;
import com.sincinfo.zxks.bean.BaseUser;
import com.sincinfo.zxks.common.Constants;
import com.sincinfo.zxks.common.action.WebActionSupport;
import com.sincinfo.zxks.common.db.DbUtil;
import com.sincinfo.zxks.common.log.OperatLog;
import com.sincinfo.zxks.common.log.OperateLogTool;
import com.sincinfo.zxks.common.util.Page;
import com.sincinfo.zxks.zxksdbs.DayDbService;
import com.sincinfo.zxks.zxksdbs.MessageDbService;
import com.sun.corba.se.impl.orbutil.closure.Constant;

/**
 * @see 信息管理
 * @author guanm
 * 
 */
public class MessageAct extends WebActionSupport {
	private static final long serialVersionUID = 1L;
	private MessageDbService db;// 信息发布业务类
	private DayDbService daydb;// 日常操作业务类
	private Page page;// 分页

	private List<BaseNewsClass> allClass;// 所有栏目(根据用户权限)

	// 增加新闻
	private BaseNewsArticle message;// 信息类
	private File messageFile;// 文件附件
	private String messageFileFileName;// 附件名称
	private String ornoFile;//附件是否被删除  用于修改

	// 搜索
	private String title;// 标题
	private String classid;// 栏目
	private String status;// 状态
	private String lasttime;// 最后修改时间
	private List<ArticleDateBean> artlist;// 分页新闻list
	
	private String articleAuthor;//根据用户得到职位名称

	// 得到一条新闻
	private Integer artid;// 新闻id
	private BaseNewsArticle article;// 新闻对象

	// 审核通过
	private Integer[] artids;// 新闻id数组

	private String articleUnAuditReason;// 审核不通过原因

	public MessageAct() {
		db = new MessageDbService();
		daydb = new DayDbService();
	}

	/**
	 * @see to信息管理默认页面
	 * @return
	 */
	public String manager() {
		BaseUser user = super.getCOperUser();
		allClass = db.getAllClass(user.getUserRole());
		
		if (page == null) {
			page = new Page();
		}
		page.setPagecount(this.getSelectResCount(null, db.getClassStr(allClass), null, null,user.getUserRole(),user.getCityCode()));
		page.setPath("message_manager.do");
		String sql = page.getSql(this.getSelectSql(null, db.getClassStr(allClass), null, null,user.getUserRole(),user.getCityCode()));
		artlist = db.getObjList(sql, ArticleDateBean.class);

		return "manager";
	}

	/**
	 * @see do信息管理查询
	 */
	public String select() {

		BaseUser user = super.getCOperUser();
		allClass = db.getAllClass(user.getUserRole());

		// 如果为空 赋默认值""
		title = title == null ? "" : title.trim();
		classid = classid == null ||classid.equals("")? db.getClassStr(allClass) : classid;//如果为空 则默认为该用户下的有拥有权限的所有栏目
		status = status == null ? "" : status.trim();
		lasttime = lasttime == null ? "" : lasttime.trim();
		if (page == null) {
			page = new Page();
		}
		page.setPagecount(this.getSelectResCount(title, classid, status,
				lasttime,user.getUserRole(),user.getCityCode()));
		page
				.setPath(String
						.format(
								"message_select.do?title=%1$s&classid=%2$s&status=%3$s&lasttime=%4$s",
								title, classid, status, lasttime));
		String sql = page.getSql(this.getSelectSql(title, classid, status,
				lasttime,user.getUserRole(),user.getCityCode()));
		artlist = db.getObjList(sql, ArticleDateBean.class);
		return "select";
	}

	/**
	 * @see toadd信息
	 * @return
	 */
	public String toadd() {
		BaseUser user = super.getCOperUser();
		allClass = db.getAllClass(user.getUserRole());
		
		// 根据用户职位编号 获得该用户下的部门
		BaseDepartment bd = daydb.getDepartmentByPositionId(user
				.getPositionCode());
		 articleAuthor = bd.getDepartmentName();
		
		return "toadd";
	}

	/**
	 * @see 执行添加新闻
	 * @return
	 */
	public String add() {
		if(message.getArticleContent()==null||message.getArticleContent().equals("")){
			String alertStr=String
			.format(
					"alert('内容不能为空！');location.href='%1$s/manager/day/message/message_toadd.do';",
					request.getContextPath());
			super.PostJs(alertStr);
			return null;
		}
		BaseUser user = super.getCOperUser();
		message.setArticleAddUser(user.getUserName());
		message.setArticleAddTime(new Date());
		if (messageFile != null) {
			message.setArticleFile(messageFileFileName);
			db.savefile(messageFile, message);
		}
		// 根据用户职位编号 获得该用户下的部门
//		BaseDepartment bd = daydb.getDepartmentByPositionId(user
//				.getPositionCode());
//		String articleAuthor = bd.getDepartmentName();
//		if (bd.getDepartmentGrade().equals("1")) {
//			articleAuthor = "省级" + articleAuthor;
//		} else if (bd.getDepartmentGrade().equals("2")) {
//			articleAuthor = "市级" + articleAuthor;
//		}
//		message.setArticleAuthor(articleAuthor);
		message.setArticleAuditStatus("0");// 默认0 未通过
		OperatLog log=this.getOptLog(OperatLog.DB_INSERT, "添加信息，title="+message.getArticleTitle()+",classid="+message.getClassId());
		boolean isok = db.saveMessage(message,log);
		if (isok) {			
			super.Alert("添加成功！");
			return toadd();
		} else {
			return "error";
		}
	}

	/**
	 * @see 根据新闻id 查看新闻信息
	 * @return
	 */
	public String query() {
		if (artid == null) {
			return "error";
		}
		article = db.getBaseNewsArticleById(artid);

		return "query";
	}

	/**
	 * @see to修改新闻
	 * @return
	 */
	public String toedit() {
		if (artid == null) {
			return "error";
		}

		BaseUser user = super.getCOperUser();
		allClass = db.getAllClass(user.getUserRole());
		article = db.getBaseNewsArticleById(artid);
		return "toedit";
	}

	/**
	 * @see do修改新闻
	 */
	public String doedit() {
		// if(artidd==null){
		// return "error";
		// }
		Integer artidd=message.getArticleId();
		BaseNewsArticle bna=db.getBaseNewsArticleById(artidd);
		if(bna.getArticleAuditStatus().equals("1")){
			String alertStr=String
			.format(
					"alert('审核通过的信息不能修改');location.href='%1$s/manager/day/message/message_manager.do';",
					request.getContextPath());
			super.PostJs(alertStr);
			return null;
		}
		String addName=bna.getArticleAddUser();
		BaseUser addUser=db.getBaseUserByName(addName);
		BaseUser user = super.getCOperUser();
		Integer addUserRole=Integer.parseInt(addUser.getUserRole());
		Integer editUserRole=Integer.parseInt(user.getUserRole());
		if(editUserRole>addUserRole){
			String alertStr=String
			.format(
					"alert('权限不够');location.href='%1$s/manager/day/message/message_manager.do';",
					request.getContextPath());
			super.PostJs(alertStr);
			return null;
		}
		if(message.getArticleContent()==null||message.getArticleContent().equals("")){
			String alertStr=String
			.format(
					"alert('内容不能为空！');location.href='%1$s/manager/day/message/message_toedit.do?artid="+message.getArticleId()+"';",
					request.getContextPath());
			super.PostJs(alertStr);
			return null;
		}
		message.setArticleAddUser(user.getUserName());
		
		if (messageFile != null) {
			message.setArticleFile(messageFileFileName);
			db.savefile(messageFile, message);
		}
		OperatLog log=this.getOptLog(OperatLog.DB_UPDATE, "修改信息，id="+message.getArticleId());
		boolean isok = db.updateArt(message,ornoFile,log);
		if (isok) {
			super.request.setAttribute("info", "修改成功");
			artid = message.getArticleId();
			return toedit();
		} else {
			return "error";
		}
	}

	/**
	 * @see 删除新闻
	 * @return
	 */
	public String delete() {
		if (artid == null) {
			return "error";
		}
		OperatLog log=this.getOptLog(OperatLog.DB_DELETE, "删除信息，id="+artid);
		boolean isok = db.delete(artid,log);
		if (isok) {
			return "deleteok";
		} else {
			return "error";
		}
	}

	/**
	 * @see 信息审核默认页
	 */
	public String issue() {
		BaseUser user = super.getCOperUser();
		if(!user.getUserRole().equals("1")){
			String alertStr=String
			.format(
					"alert('只有省级用户才能审核！');location.href='%1$s/manager/day/message/message_manager.do';",
					request.getContextPath());
			super.PostJs(alertStr);
			return null;
		}
		
		allClass = db.getAllClass(user.getUserRole());
		
		status="0";//默认未审核
		
		if (page == null) {
			page = new Page();
		}
		page.setPagecount(this.getSelectResCount2(null, db.getClassStr(allClass), "0", null));// 默认未审核
		page.setPath("message_issue.do");
		String sql = page.getSql(this.getSelectSql2(null, db.getClassStr(allClass), "0", null));
		artlist = db.getObjList(sql, ArticleDateBean.class);
		return "issue";
	}

	/**
	 * @see do信息审核查询
	 */
	public String issueselect() {

		BaseUser user = super.getCOperUser();
		allClass = db.getAllClass(user.getUserRole());

		// 如果为空 赋默认值""
		classid = classid == null ? "" : classid;
		status = status == null ? "" : status;
		if (page == null) {
			page = new Page();
		}
		page.setPagecount(this.getSelectResCount2(null, classid, status, null));
		page.setPath(String.format(
				"message_issueselect.do?classid=%1$s&status=%2$s", classid,
				status));
		String sql = page.getSql(this
				.getSelectSql2(null, classid, status, null));
		artlist = db.getObjList(sql, ArticleDateBean.class);
		return "issueselect";
	}

	/**
	 * @see do审核通过
	 */
	public String onissue() {
		if (artids == null || artids.length == 0) {
			return "error";
		}
		//判断artids中是否有已审核通过的记录
		if(db.orHave(artids)){//有审核通过的记录
			super.request.setAttribute("info", "操作失败！您选择了已通过的信息");
			super.request.setAttribute("url", "message_issue.do");
			return "ok";
		}
		OperatLog log=this.getOptLog(OperatLog.DB_UPDATE, "审核通过信息，id="+artids);
		boolean isok = db.onissue(artids,log);
		if (isok) {
			super.request.setAttribute("info", "审核成功");
			super.request.setAttribute("url", "message_issue.do");
			return "ok";
		} else {
			return "error";
		}

	}

	/**
	 * @sse do撤销审核
	 */
	public String noissue() {
		if (artids == null || artids.length == 0) {
			return "error";
		}
		OperatLog log=this.getOptLog(OperatLog.DB_UPDATE, "撤销审核，id="+artids);
		boolean isok = db.noissue(artids,log);
		if (isok) {
			super.request.setAttribute("info", "撤审成功");
			super.request.setAttribute("url", "message_issue.do");
			return "ok";
		} else {
			return "error";
		}
	}

	/**
	 * @see do 审核不通过
	 */
	public String unissue() {
		if (artids == null || artids.length == 0) {
			return "error";
		}
		if (articleUnAuditReason == null || articleUnAuditReason.equals("")) {
			return "error";
		}
		//判断artids中是否有已审核通过的记录
		if(db.orHave(artids)){//有审核通过的记录
			super.request.setAttribute("info", "操作失败！您选择了已通过的信息");
			super.request.setAttribute("url", "message_issue.do");
			return "ok";
		}
		OperatLog log=this.getOptLog(OperatLog.DB_UPDATE, "审核不通过信息，id="+artids[0]);
		boolean isok = db.unissue(artids[0], articleUnAuditReason,log);
		if (isok) {
			super.request.setAttribute("info", "操作成功");
			super.request.setAttribute("url", "message_issue.do");
			return "ok";
		} else {
			return "error";
		}
	}

	/**
	 * @see 获得搜索新闻结果的总条数
	 * @param title
	 * @param classid
	 * @param status
	 * @param lasttime
	 * @return
	 */
	private long getSelectResCount(String title, String classid, String status,
			String lasttime,String userRole,String cityCode) {
		StringBuilder sql = new StringBuilder();
		sql.append("select count(*) from ("+db.view+") t where 1=1");
		if (title != null && !title.trim().equals("")) {
			sql.append(" and t.article_title like '%" + title.trim() + "%'");
		}
		if (classid != null && !classid.equals("")) {
			sql.append(" and t.class_id in (" + classid+")");
		}
		if (status != null && !status.equals("")) {
			if (status.equals("2")) {// 条件为审核不通过
				sql
						.append(" and t.article_audit_status='2' and t.article_unaudit_reason is not null");
			} else {// 条件为 未审核 审核通过
				sql.append(" and t.article_audit_status='" + status + "'");
			}
		}
		if (lasttime != null && !lasttime.equals("")) {
			sql.append(" and to_char(t.article_add_time,'yyyy-mm-dd')='"
					+ lasttime + "'");
		}
		sql.append(" and bitand(t.user_role, "+ userRole + ") = " + userRole );
		if(!userRole.equals("1")){			
			sql.append(" and t.city_code='"+cityCode+"' ");
		}
		return db.getNum(sql.toString());
	}

	/**
	 * @see 根据条件 获得搜索sql 按时间倒序
	 * @return
	 */
	private String getSelectSql(String title, String classid, String status,
			String lasttime,String userRole,String cityCode) {
		StringBuilder sql = new StringBuilder();
		sql.append("select t.* from ("+db.view+") t where 1=1");
		if (title != null && !title.trim().equals("")) {
			sql.append(" and t.article_title like '%" + title.trim() + "%'");
		}
		if (classid != null && !classid.equals("")) {
			sql.append(" and t.class_id in (" + classid+")");
		}
		if (status != null && !status.equals("")) {
			sql.append(" and t.article_audit_status='" + status + "'");
		}
		if (lasttime != null && !lasttime.equals("")) {
			sql.append(" and to_char(t.article_add_time,'yyyy-mm-dd')='"
					+ lasttime + "'");
		}
		sql.append(" and bitand(t.user_role, "+ userRole + ") = " + userRole );
		if(!userRole.equals("1")){			
			sql.append(" and t.city_code='"+cityCode+"' ");
		}
		String sql2 = "select t1.*,t2.class_name from ("
				+ sql.toString()
				+ ") t1,base_news_class t2 where t1.class_id=t2.class_id order by t1.article_add_time desc";
//		System.out.println(sql2);
		return sql2;
	}

	/**
	 * @see 获得搜索新闻结果的总条数 用于审核
	 * @param title
	 * @param classid
	 * @param status
	 * @param lasttime
	 * @return
	 */
	private long getSelectResCount2(String title, String classid, String status,
			String lasttime) {
		StringBuilder sql = new StringBuilder();
		sql.append("select count(*) from ("+db.view+") t where 1=1");
		if (title != null && !title.trim().equals("")) {
			sql.append(" and t.article_title like '%" + title.trim() + "%'");
		}
		if (classid != null && !classid.equals("")) {
			sql.append(" and t.class_id in (" + classid+")");
		}
		if (status != null && !status.equals("")) {
			if (status.equals("2")) {// 条件为审核不通过
				sql
						.append(" and t.article_audit_status='0' and t.article_unaudit_reason is not null");
			} else {// 条件为 未审核 审核通过
				sql.append(" and t.article_audit_status='" + status + "'");
			}
		}
		if (lasttime != null && !lasttime.equals("")) {
			sql.append(" and to_char(t.article_add_time,'yyyy-mm-dd')='"
					+ lasttime + "'");
		}
		return db.getNum(sql.toString());
	}
	/**
	 * @see 根据条件 获得搜索sql 按时间正序 用于审核
	 * @return
	 */
	private String getSelectSql2(String title, String classid, String status,
			String lasttime) {
		StringBuilder sql = new StringBuilder();
		sql.append("select t.* from ("+db.view+") t where 1=1");
		if (title != null && !title.trim().equals("")) {
			sql.append(" and t.article_title like '%" + title.trim() + "%'");
		}
		if (classid != null && !classid.equals("")) {
			sql.append(" and t.class_id in(" + classid+")");
		}
		if (status != null && !status.equals("")) {
			sql.append(" and t.article_audit_status='" + status + "'");
		}
		if (lasttime != null && !lasttime.equals("")) {
			sql.append(" and to_char(t.article_add_time,'yyyy-mm-dd')='"
					+ lasttime + "'");
		}

		String sql2 = "select t1.*,t2.class_name from ("
				+ sql.toString()
				+ ") t1,base_news_class t2 where t1.class_id=t2.class_id order by t1.article_add_time";
		return sql2;
	}

	/**
	 * get and set
	 */

	public MessageDbService getDb() {
		return db;
	}

	public void setDb(MessageDbService db) {
		this.db = db;
	}

	public List<BaseNewsClass> getAllClass() {
		return allClass;
	}

	public void setAllClass(List<BaseNewsClass> allClass) {
		this.allClass = allClass;
	}

	public BaseNewsArticle getMessage() {
		return message;
	}

	public void setMessage(BaseNewsArticle message) {
		this.message = message;
	}

	public DayDbService getDaydb() {
		return daydb;
	}

	public void setDaydb(DayDbService daydb) {
		this.daydb = daydb;
	}

	public File getMessageFile() {
		return messageFile;
	}

	public void setMessageFile(File messageFile) {
		this.messageFile = messageFile;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getMessageFileFileName() {
		return messageFileFileName;
	}

	public void setMessageFileFileName(String messageFileFileName) {
		this.messageFileFileName = messageFileFileName;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getClassid() {
		return classid;
	}

	public void setClassid(String classid) {
		this.classid = classid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLasttime() {
		return lasttime;
	}

	public void setLasttime(String lasttime) {
		this.lasttime = lasttime;
	}

	public List<ArticleDateBean> getArtlist() {
		return artlist;
	}

	public void setArtlist(List<ArticleDateBean> artlist) {
		this.artlist = artlist;
	}

	public Integer getArtid() {
		return artid;
	}

	public void setArtid(Integer artid) {
		this.artid = artid;
	}

	public BaseNewsArticle getArticle() {
		return article;
	}

	public void setArticle(BaseNewsArticle article) {
		this.article = article;
	}

	public Integer[] getArtids() {
		return artids;
	}

	public void setArtids(Integer[] artids) {
		this.artids = artids;
	}

	public String getArticleUnAuditReason() {
		return articleUnAuditReason;
	}

	public void setArticleUnAuditReason(String articleUnAuditReason) {
		this.articleUnAuditReason = articleUnAuditReason;
	}

	public String getArticleAuthor() {
		return articleAuthor;
	}

	public void setArticleAuthor(String articleAuthor) {
		this.articleAuthor = articleAuthor;
	}

	public String getOrnoFile() {
		return ornoFile;
	}

	public void setOrnoFile(String ornoFile) {
		this.ornoFile = ornoFile;
	}
	
}
