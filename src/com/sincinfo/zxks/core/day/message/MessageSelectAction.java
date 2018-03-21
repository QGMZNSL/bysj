package com.sincinfo.zxks.core.day.message;

import java.util.List;

import com.sincinfo.zxks.bean.ArticleDateBean;
import com.sincinfo.zxks.bean.BaseNewsArticle;
import com.sincinfo.zxks.bean.BaseNewsClass;
import com.sincinfo.zxks.common.action.WebActionSupport;
import com.sincinfo.zxks.common.util.Page;
import com.sincinfo.zxks.zxksdbs.MessageDbService;

/**
 * @see  信息查询  该功能只有查询，没有其他任何操作 msel_*
 * @author guanm
 *
 */
public class MessageSelectAction extends WebActionSupport{
	private MessageDbService service;//业务类
	private Page page;// 分页
	private List<BaseNewsClass> allClass;// 所有栏目(根据用户权限)
	private String title;// 标题
	private String classid;// 栏目
	private String status="1";// 状态 2审核通过
	private String role="1";//默认权限  省
	private String region="";//无默认  地市
	private String lasttime;// 最后修改时间
	private List<ArticleDateBean> artlist;// 分页新闻list
	private Integer artid;// 新闻id
	private BaseNewsArticle article;// 新闻对象
	
	public MessageSelectAction(){
		service=new MessageDbService();
	}
	
	/**
	 * @see 信息查询 默认页
	 * @return
	 */
	public String  manager(){
		allClass = service.getAllClass(role);

		// 如果为空 赋默认值""
		title = title == null ? "" : title.trim();
		classid = classid == null ||classid.equals("")? service.getClassStr(allClass) : classid;//如果为空 则默认为该用户下的有拥有权限的所有栏目
		lasttime = lasttime == null ? "" : lasttime.trim();
		if (page == null) {
			page = new Page();
		}
		page.setPagecount(this.getSelectResCount(title, classid, status,
				lasttime,role,region));
		page
				.setPath(String
						.format(
								"msel_manager.do?title=%1$s&classid=%2$s&status=%3$s&lasttime=%4$s",
								title, classid, status, lasttime));
		String sql = page.getSql(this.getSelectSql(title, classid, status,
				lasttime,role,region));
		artlist = service.getObjList(sql, ArticleDateBean.class);
		return "manager";
	}

	/**
	 * @see 根据新闻id 查看新闻信息
	 * @return
	 */
	public String view() {
		if (artid == null) {
			return "error";
		}
		article = service.getBaseNewsArticleById(artid);

		return "view";
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
		sql.append("select count(*) from ("+service.view+") t where 1=1");
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
		return service.getNum(sql.toString());
	}
	/**
	 * @see 根据条件 获得搜索sql 按时间倒序
	 * @return
	 */
	private String getSelectSql(String title, String classid, String status,
			String lasttime,String userRole,String cityCode) {
		StringBuilder sql = new StringBuilder();
		sql.append("select t.* from ("+service.view+") t where 1=1");
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
	
	public MessageDbService getService() {
		return service;
	}

	public void setService(MessageDbService service) {
		this.service = service;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public List<BaseNewsClass> getAllClass() {
		return allClass;
	}

	public void setAllClass(List<BaseNewsClass> allClass) {
		this.allClass = allClass;
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
	
	
	
}
