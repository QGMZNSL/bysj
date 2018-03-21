package com.sincinfo.zxks.help;

import java.util.List;

import com.sincinfo.zxks.bean.BaseUser;
import com.sincinfo.zxks.common.db.DbUtil;
import com.sincinfo.zxks.common.util.Page;

/**
 * @Title:ManageHelpDao.java
 * @Package:com.wsbm.help
 * @Description:页面帮助对应DAO
 * @author:Cheng
 * @date:2013-5-28下午4:24:35
 * @version:1.0
 */
public class ManageHelpDao {
	DbUtil dbUtil = new DbUtil();

	/**
	 * 获得某个页面的帮助
	 * 
	 * @return
	 */
	public String[] getHelp(String help_id) {
		String sql = "select t.hid,t.help_id,to_char(t.audit_time,'yyyy-mm-dd') as auditTime,t.function_desc,t.opt_desc,t.annotation_desc,t.tech_support,t.audit_user from BASE_HELP t where t.help_id='%1$s' and t.audit_state=1";
		sql = String.format(sql, help_id);
		String[] help = new String[9];
		help = dbUtil.getRsArray(sql, 9);
		if (help == null) {
			help = new String[] { "", "", "", "", "", "", "", "", "" };
		}
		return help;
	}

	/**
	 * 获取待审阅数量
	 * 
	 * @param help_id
	 * @return
	 */
	public String getCount(String help_id) {
		String countSql = "select count(*) from BASE_HELP t where t.help_id=? and t.audit_state=0";
		return dbUtil.getFirstCol(countSql, help_id);
	}

	/**
	 * 根据hid获取帮助内容
	 * 
	 * @param hid
	 * @return
	 */
	public String[] getHelpByhId(String hid) {
		String sql = "select t.hid,t.help_id,t.function_desc,t.opt_desc,t.annotation_desc,t.tech_support,t.edit_user from BASE_HELP t where t.hid='%1$s'";
		sql = String.format(sql, hid);
		String[] help = new String[7];
		help = dbUtil.getRsArray(sql, 7);
		if (help == null) {
			help = new String[] { "", "", "", "", "", "", "" };
		}
		return help;
	}

	/**
	 * 获取待审核列表 audit_state:0表示未审核，2表示审核不通过
	 * 
	 * @param help_id
	 * @return
	 */
	public List<String[]> getHelpAuditList(String help_id, Page page) {
		String countSql = "select count(*) from BASE_HELP t where t.help_id='%1$s' and t.audit_state=0";
		String sql = "select t.hid,t.help_id,to_char(t.edit_time,'yyyy-mm-dd') as editTime,t.function_desc,t.opt_desc,t.annotation_desc,t.edit_user from BASE_HELP t where t.help_id='%1$s' and t.audit_state=0";
		sql = String.format(sql, help_id);
		countSql = String.format(countSql, help_id);
		String urlCondention = String.format("help_id=%1$s", help_id);
		page.setPath("/ZK_CORE/zk/help/help_getHList.do?" + urlCondention);
		String sqlPage = page.setPagecount(dbUtil.getNum(countSql), sql);
		List<String[]> list = dbUtil.getRsArrayList(sqlPage, 7);
		return list;
	}

	/**
	 * 
	 * @param hid
	 * @param help_id
	 * @param auditType
	 *            审核类型：1.通过，2不通过(暂时不要)
	 * @return
	 */
	public boolean auditHelp(String hid, String help_id, String auditUser) {
		boolean result = false;
		String updateSql = "update BASE_HELP t set t.audit_state='1',t.audit_user='"
				+ auditUser + "',t.audit_time=sysdate where t.hid=" + hid + "";
		String deleteSql = "delete from BASE_HELP t where t.help_id='"
				+ help_id + "' and t.audit_state=1";
		int del = dbUtil.saveOrUpdate(deleteSql);
		int update = 0;
		if (del > 0) {
			update = dbUtil.saveOrUpdate(updateSql);
		}
		if (update > 0) {
			result = true;
		}
		return result;
	}

	/**
	 * 保存编辑信息
	 * 
	 * @param help_id
	 * @param funDesc
	 * @param optDesc
	 * @param annoDesc
	 * @param session
	 * @return
	 */
	public boolean saveHelp(String hid, String help_id, String funDesc,
			String optDesc, String annoDesc, BaseUser baseUser, String isCurrent) {
		boolean result = false;
		String userName = baseUser.getUserName();
		String userGrade = baseUser.getUserRole();
		String sql = "";
		String deleteSql = "";
		if (userGrade.equals("1")) {// 审核状态为已通过
			if (hid.equals("")) {
				sql = "insert into BASE_HELP t (t.hid,t.help_id,t.edit_time,t.audit_time,t.function_desc,t.opt_desc,t.annotation_desc,t.audit_user,t.edit_user,t.audit_state) values (help_seq.nextval,'"
						+ help_id
						+ "',sysdate,sysdate,'"
						+ funDesc
						+ "','"
						+ optDesc
						+ "','"
						+ annoDesc
						+ "','"
						+ userName
						+ "','"
						+ userName + "',1)";
				int insert = dbUtil.saveOrUpdate(sql);
				if (insert > 0) {
					result = true;
				}
			} else {
				if (isCurrent.equals("1")) {
					sql = "update BASE_HELP t set t.audit_time=sysdate,t.function_desc='"
							+ funDesc
							+ "',t.opt_desc='"
							+ optDesc
							+ "',t.annotation_desc='"
							+ annoDesc
							+ "',t.audit_user='"
							+ userName
							+ "',t.audit_state=1 where t.hid=" + hid;
					int f = dbUtil.saveOrUpdate(sql);
					if (f > 0) {
						result = true;
					}
				} else {
					deleteSql = "delete from BASE_HELP t where t.help_id='"
							+ help_id + "' and t.audit_state=1";
					sql = "update BASE_HELP t set t.audit_time=sysdate,t.function_desc='"
							+ funDesc
							+ "',t.opt_desc='"
							+ optDesc
							+ "',t.annotation_desc='"
							+ annoDesc
							+ "',t.audit_user='"
							+ userName
							+ "',t.audit_state=1 where t.hid=" + hid;
					int d = dbUtil.saveOrUpdate(deleteSql);
					int f = 0;
					if (d > 0) {
						f = dbUtil.saveOrUpdate(sql);
					}
					if (f > 0) {
						result = true;
					}
				}
			}
		} else {// 状态为未审核
			sql = "insert into BASE_HELP t (t.hid,t.help_id,t.edit_time,t.function_desc,t.opt_desc,t.annotation_desc,t.edit_user,t.audit_state) values (help_seq.nextval,'"
					+ help_id
					+ "',sysdate,'"
					+ funDesc
					+ "','"
					+ optDesc
					+ "','" + annoDesc + "','" + userName + "',0)";
			int f = dbUtil.saveOrUpdate(sql);
			if (f > 0) {
				result = true;
			}
		}
		return result;
	}
}