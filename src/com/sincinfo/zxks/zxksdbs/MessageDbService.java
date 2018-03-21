package com.sincinfo.zxks.zxksdbs;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.sincinfo.zxks.bean.BaseNewsArticle;
import com.sincinfo.zxks.bean.BaseNewsClass;
import com.sincinfo.zxks.bean.BaseUser;
import com.sincinfo.zxks.bean.SysConfig;
import com.sincinfo.zxks.common.db.DbUtil;
import com.sincinfo.zxks.common.log.OperatLog;
import com.sincinfo.zxks.common.log.OperateLogTool;
import com.sincinfo.zxks.common.util.Log;

public class MessageDbService extends DbUtil {
	public final String view = "select a.* ,u.user_role,u.city_code from base_news_article a,base_user u where a.article_add_user=u.user_name";
	
	/**
	 * @see 获得一级导航栏栏目 根据用户所拥有的栏目权限
	 */
	public List<BaseNewsClass> getAllClass(String userrole) {
		String sql = "select t.* from base_news_class t where bitand(t.user_role, "
				+ userrole + ") = " + userrole + " order by t.class_id";
		return super.getObjList(sql, BaseNewsClass.class);
	}

	/**
	 * @see 获得栏目id字符串 方便sql 如：1,2,3,4
	 * 
	 */
	public String getClassStr(List<BaseNewsClass> classes) {
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < classes.size();) {
			BaseNewsClass bnc = classes.get(i);
			if (i == classes.size() - 1) {
				str.append(bnc.getClassId());
			} else {
				str.append(bnc.getClassId() + ",");
			}
			i++;
		}
		return str.toString();
	}

	/**
	 * @see 保存一个新闻对象
	 */
	public boolean saveMessage(BaseNewsArticle message,OperatLog log) {
		boolean isok = false;
		StringBuilder sql = new StringBuilder();
		sql
				.append("insert into base_news_article(article_id,article_title,article_sub_title,class_id,article_content,article_key_word,article_file,article_add_user,article_add_time,article_author,article_audit_status) ");
		sql.append(" values(seq_article.nextval,'" + message.getArticleTitle()
				+ "','" + message.getArticleSubTitle() + "',"
				+ message.getClassId() + ",?,'" + message.getArticleKeyWord()
				+ "'");
		if (message.getArticleFile() != null) {
			sql.append(",'" + message.getArticleFile() + "'");
		} else {
			sql.append(",null");
		}
		sql.append(",'" + message.getArticleAddUser() + "',to_date('"
				+ this.dateFormat(message.getArticleAddTime())
				+ "','yyyy-mm-dd hh24:mi:ss'),'" + message.getArticleAuthor()
				+ "','" + message.getArticleAuditStatus() + "')");
		// System.out.println(sql.toString());
		int k = super.saveOrUpdateClob(sql.toString(), message
				.getArticleContent());
		if (k > 0) {
			isok = true;
		}
		log.setLogOptSql(sql.toString());
		OperateLogTool.saveOptLog(log);
		return isok;
	}

	/**
	 * @see 根据新闻id 得到该新闻对象
	 */
	public BaseNewsArticle getBaseNewsArticleById(Integer articleid) {
		String sql = "select * from base_news_article where article_id="
				+ articleid;
		
		return super.getObject(sql, BaseNewsArticle.class);
	}

	/**
	 * @see 更新新闻对象
	 * @param message
	 * @return
	 */
	public boolean updateArt(BaseNewsArticle message,String ornoFile,OperatLog log) {
//		BaseNewsArticle bna=this.getBaseNewsArticleById(message.getArticleId());
//		Date date=bna.getArticleAddTime();//获得添加时间 以便于获取该添加时间的时分秒
		boolean isok = false;
		StringBuilder sql = new StringBuilder();
		sql.append("update base_news_article set ");
		sql.append(" article_title='" + message.getArticleTitle() + "'");
		sql
				.append(" ,article_sub_title='" + message.getArticleSubTitle()
						+ "'");
		sql.append(" ,class_id=" + message.getClassId());
		sql.append(" ,article_content=?");
		sql.append(" ,article_key_word='" + message.getArticleKeyWord() + "'");
		sql.append(" ,article_author='" + message.getArticleAuthor() + "'");

		if (ornoFile==null&&message.getArticleFile() != null) {
			sql.append(" ,article_file='" + message.getArticleFile() + "'");
		}else if(ornoFile!=null&&message.getArticleFile()!=null){
			sql.append(" ,article_file='" + message.getArticleFile() + "'");
		}else if(ornoFile!=null&&ornoFile.equals("1")){
			sql.append(" ,article_file=null");
		}
		if (message.getArticleAuditStatus().equals("2")) {// 如果状态为审核未通过，则修改后改为未审核
			sql.append(" ,article_audit_status='0'");
		}
		sql.append(" ,article_add_user='" + message.getArticleAddUser() + "'");
		sql.append(" ,article_add_time=to_date('"
				+ this.dateFormat3(message.getArticleAddTime()) + " "
				+ this.dateFormat2(new Date()) + "','yyyy-mm-dd hh24:mi:ss')");
//		sql.append(" ,article_add_time=to_date('"
//				+ this.dateFormat(message.getArticleAddTime())+ "','yyyy-mm-dd hh24:mi:ss')");
		sql.append(" where article_id=" + message.getArticleId());
		int k = super.saveOrUpdateClob(sql.toString(), message
				.getArticleContent());
		if (k > 0) {
			isok = true;
		}
		log.setLogOptSql(sql.toString());
		OperateLogTool.saveOptLog(log);
		return isok;
	}

	/**
	 * @see 删除新闻
	 */
	public boolean delete(Integer artid,OperatLog log) {
		boolean isok = false;
		String sql = "delete from base_news_article where article_id=" + artid;
		int k = super.saveOrUpdate(sql);
		if (k > 0) {
			isok = true;
		}
		log.setLogOptSql(sql);
		OperateLogTool.saveOptLog(log);
		return isok;
	}

	/**
	 * @see 获取配置表中的文件存储路径
	 * @param id=1表示文件根目录
	 *            id=24表示新闻附件存放地址 返回 以“/”结尾
	 * 
	 */
	public SysConfig getConfigFileserver(String id) {
		String sql = "select * from sys_config where sys_cfg_id=" + id;
		return super.getObject(sql, SysConfig.class);
	}

	/**
	 * @see 存放附件
	 */
	public void savefile(File file, BaseNewsArticle message) {
		String[] paths=super.getPaths();
		File pfile = new File(paths[0]);// 文件根目录
//		File pfile = new File(this.getConfigFileserver("1").getSysCfgContent());// 文件根目录
		File artfile = new File(pfile, this.getConfigFileserver("24")
				.getSysCfgContent());// 新闻附件存放地址
		File outfile = new File(artfile, message.getArticleFile());
		FileInputStream fis = null;
		FileOutputStream fos = null;
		try {
			if (!artfile.exists()) {
				artfile.mkdirs();
			}
			System.out.println(artfile.getPath());
			if (!outfile.exists()) {
				outfile.createNewFile();
			}
			fis = new FileInputStream(file);
			fos = new FileOutputStream(outfile);
			byte[] b = new byte[10 * 1024];
			int k;
			while ((k = fis.read(b)) > 0) {
				fos.write(b, 0, k);
			}
		} catch (IOException e) {
			new Log().error(this.getClass(),"存放信息附件有误",e);
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					new Log().error(this.getClass(),"存放信息附件有误",e);
				}
			}
			if (fos != null) {
				try {
					fos.flush();
					fos.close();
				} catch (IOException e) {
					new Log().error(this.getClass(),"存放信息附件有误",e);
				}
			}
		}
	}

	/**
	 * @see do审核通过
	 * @param artids
	 * @return
	 */
	public boolean onissue(Integer[] artids,OperatLog log) {
		boolean isok = false;
		StringBuilder sql = new StringBuilder();
		sql.append("update base_news_article set article_audit_status='1'");
		sql.append(",article_audit_time=to_date('"
				+ this.dateFormat(new Date()) + "','yyyy-mm-dd hh24:mi:ss')");
		sql.append(" where article_id in (");
		for (int i = 0; i < artids.length;) {
			Integer artid = artids[i];
			i++;
			if (i == artids.length) {
				sql.append(artid);
			} else {
				sql.append(artid + ",");
			}
		}
		sql.append(")");
		int k = super.saveOrUpdate(sql.toString());
		if (k > 0) {
			isok = true;
		}
		log.setLogOptSql(sql.toString());
		OperateLogTool.saveOptLog(log);
		return isok;
	}

	/**
	 * @see do撤销审核
	 * @param artids
	 * @return
	 */
	public boolean noissue(Integer[] artids,OperatLog log) {
		boolean isok = false;
		StringBuilder sql = new StringBuilder();
		sql.append("update base_news_article set article_audit_status='0'");
		sql.append(",article_audit_time=to_date('"
				+ this.dateFormat(new Date()) + "','yyyy-mm-dd hh24:mi:ss')");
		sql.append(" where article_id in (");
		for (int i = 0; i < artids.length;) {
			Integer artid = artids[i];
			i++;
			if (i == artids.length) {
				sql.append(artid);
			} else {
				sql.append(artid + ",");
			}
		}
		sql.append(")");
		int k = super.saveOrUpdate(sql.toString());
		if (k > 0) {
			isok = true;
		}
		log.setLogOptSql(sql.toString());
		OperateLogTool.saveOptLog(log);
		return isok;
	}

	/**
	 * @see do审核不通过
	 */
	public boolean unissue(Integer artid, String articlereason,OperatLog log) {
		boolean isok = false;
		String sql = "update base_news_article set article_audit_status='2',article_unaudit_reason='"
				+ articlereason
				+ "',article_audit_time=to_date('"
				+ this.dateFormat(new Date())
				+ "','yyyy-mm-dd hh24:mi:ss') where article_id=" + artid;
		int k = super.saveOrUpdate(sql);
		if (k > 0) {
			isok = true;
		}
		log.setLogOptSql(sql);
		OperateLogTool.saveOptLog(log);
		return isok;
	}

	/**
	 * @see 判断artids中是否有已审核通过的记录 true 有审核通过记录 false 没有什么通过的记录
	 */
	public boolean orHave(Integer[] artids) {
		boolean isok = false;
		StringBuilder sql = new StringBuilder();
		sql.append("select * from  base_news_article ");
		sql.append(" where article_id in (");
		for (int i = 0; i < artids.length;) {
			Integer artid = artids[i];
			i++;
			if (i == artids.length) {
				sql.append(artid);
			} else {
				sql.append(artid + ",");
			}
		}
		sql.append(")");
		sql.append(" and article_audit_status='1'");
		List<BaseNewsArticle> list = super.getObjList(sql.toString(),
				BaseNewsArticle.class);
		if (list != null && list.size() > 0) {
			isok = true;
		}
		return isok;
	}

	/**
	 * @see 根据用户名 获得用户对象
	 */
	public BaseUser getBaseUserByName(String name) {
		String sql = "select * from base_user where user_name=?";
		return super.getObject(sql, BaseUser.class, name);
	}

	/**
	 * @see 根据日期 获得该日期字符串 "yyyy-MM-dd hh:mm:ss"
	 */
	private String dateFormat(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
	}

	/**
	 * @see 根据日期 获得当前时间字符串 "hh:mm:ss"
	 */
	private String dateFormat2(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		return sdf.format(date);
	}

	/**
	 * @see 根据日期 获得字符串 "yyyy-MM-dd"
	 */
	private String dateFormat3(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);
	}
}
