package com.sincinfo.zxks.zxksdbs;

import java.util.ArrayList;
import java.util.List;

import com.sincinfo.zxks.bean.LogLogin;
import com.sincinfo.zxks.common.db.DbUtil;
import com.sincinfo.zxks.common.util.Page;
import com.sincinfo.zxks.common.util.StringTool;

public class LogLoaderService {
	// 日志登陆方法
	private List<LogLogin> logLoginList = null;
	private LogLogin logLogin;
	DbUtil dbUtil = new DbUtil();;

	public List<LogLogin> getAllLog(Page page) {
		// 获得全部的日志信息
		logLoginList = new ArrayList<LogLogin>();
		String selectSQL = "select * from (select t1.* from log_login_failure t1 union all select t2.*,'' as login_password from log_login_success t2) t order by t.log_login_id desc";
		StringBuilder sb = new StringBuilder();
		sb.append("select count(*)");
		sb
				.append("from (select t1.* from log_login_failure t1 union all select t2.*,'' as login_password from log_login_success t2) t order by t.log_login_id desc");
		String sql = page.setPagecount(dbUtil.getNum(sb.toString()), selectSQL
				.toString());
		logLoginList = dbUtil.getObjList(sql, LogLogin.class);
		return logLoginList;
	}

	public List<LogLogin> getAllLogByDemand(Page page, String logType,
			String loginName, String loginIp, String beginTime, String endTime) {
		// 获得所有登陆成功或者失败的用户的日志
		logLoginList = new ArrayList<LogLogin>();
		// // 根据logType判断是登陆全部失败日志还是登陆全部成功日志
		// if ("success".equals(logType)) {
		// // 如果成功执行成功日志的sql语句
		// String selectSQL = "select to_char(ls.login_time, 'yyyy-MM-dd'),ls.*
		// from log_login_success ls where to_char(ls.login_time, 'yyyy-MM-dd')
		// between '"
		// + beginTime
		// + "' and '"
		// + endTime
		// + "' and ls.user_name = '"
		// + loginName.trim()
		// + "' and ls.login_ip = '" + loginIp.trim() + "' ";
		// StringBuilder sb = new StringBuilder();
		// sb.append("select count(*)");
		// sb.append("from log_login_success t ");
		// // 分页
		// String sql = page.setPagecount(dbUtil.getNum(sb.toString()),
		// selectSQL.toString());
		// logLoginList = dbUtil.getObjList(sql, LogLogin.class);
		// return logLoginList;
		// } else if ("failure".equals(logType)) {
		// // 如果失败执行失败日志的sql语句
		// String selectSQL = "select to_char(ls.login_time, 'yyyy-MM-dd'),ls.*
		// from log_login_failure ls where to_char(ls.login_time, 'yyyy-MM-dd')
		// between '"
		// + beginTime
		// + "' and '"
		// + endTime
		// + "' and ls.user_name = '"
		// + loginName.trim()
		// + "' and ls.login_ip = '" + loginIp.trim() + "' ";
		// StringBuilder sb = new StringBuilder();
		// sb.append("select count(*)");
		// sb.append("from log_login_failure t1");
		// // 分页
		// String sql = page.setPagecount(dbUtil.getNum(sb.toString()),
		// selectSQL.toString());
		// logLoginList = dbUtil.getObjList(sql, LogLogin.class);
		// return logLoginList;
		// } else {
		// logLoginList = new ArrayList<LogLogin>();
		// String selectSQL = "select tt.* from (select t1.* from
		// log_login_failure t1 union all select t2.*,'' as login_password from
		// log_login_success t2) tt where to_char(tt.login_time, 'yyyy-MM-dd')
		// between '"
		// + beginTime
		// + "' and '"
		// + endTime
		// + "' and tt.user_name = '"
		// + loginName.trim()
		// + "' and tt.login_ip = '" + loginIp.trim() + "'";
		// StringBuilder sb = new StringBuilder();
		// sb.append("select count(1)");
		// sb
		// .append("from (select t1.* from log_login_failure t1 union all select
		// t2.*,'' as login_password from log_login_success t2) tt where
		// to_char(tt.login_time, 'yyyy-MM-dd') between '"
		// + beginTime
		// + "' and '"
		// + endTime
		// + "' and tt.user_name = '"
		// + loginName.trim()
		// + "' and tt.login_ip = '" + loginIp.trim() + "' ");
		// String sql = page.setPagecount(dbUtil.getNum(sb.toString()),
		// selectSQL.toString());
		// logLoginList = dbUtil.getObjList(sql, LogLogin.class);

		StringBuilder sql = new StringBuilder();
		sql
				.append("select t.log_login_id,t.user_name,t.login_password, t.login_ip, to_char(t.login_time,'yyyy-mm-dd hh24:mi:ss') as loginTime");
		sql.append(" from (select t1.*");
		sql.append(" from log_login_failure t1");
		sql.append(" union all");
		sql
				.append(" select t2.*, '' as login_password from log_login_success t2) t");
		sql.append(" where 1=1");
		if (!"".equals(loginName)) {
			sql.append(String.format(" and t.user_name='%1$s'", loginName));
		}
		if (!"".equals(loginIp)) {
			sql.append(String.format(" and t.login_ip='%1$s'", loginIp));
		}
		if (!"".equals(beginTime)) {
			sql
					.append(String
							.format(
									" and t.login_time >= to_date('%1$s 00:00:00','yyyy-mm-dd hh24:mi:ss')",
									beginTime));
		}
		if (!"".equals(endTime)) {
			sql
					.append(String
							.format(
									" and t.login_time <= to_date('%1$s 23:59:59','yyyy-mm-dd hh24:mi:ss')",
									endTime));
		}
		if ("".equals(logType)) {
		} else if ("success".equals(logType)) {
			sql
					.append(" and t.log_login_id in (select ls.log_login_id from log_login_success ls)");

		} else if ("failure".equals(logType)) {
			sql
					.append(" and t.log_login_id in (select lf.log_login_id from log_login_failure lf)");
		}
		String sqlCount = String.format("select count(*) from (%1$s)", sql
				.toString());
		sql.append(" order by t.log_login_id desc");

		// 分页查询
		String sqlPage = page.setPagecount(dbUtil.getNum(sqlCount), sql
				.toString());
		logLoginList = dbUtil.getObjList(sqlPage, LogLogin.class);

		return logLoginList;
	}

	public List<String[]> getAllLogByDemandArr(String logType,
			String loginName, String loginIp, String beginTime, String endTime) {
		List<String[]> logLoginList = null;
		StringBuilder sql = new StringBuilder();
		sql
				.append("select t.log_login_id,t.user_name,t.login_password, t.login_ip, to_char(t.login_time,'yyyy-mm-dd hh24:mi:ss') as loginTime");
		sql.append(" from (select t1.*");
		sql.append(" from log_login_failure t1");
		sql.append(" union all");
		sql
				.append(" select t2.*, '' as login_password from log_login_success t2) t");
		sql.append(" where 1=1");
		if (!"".equals(loginName)) {
			sql.append(String.format(" and t.user_name='%1$s'", loginName));
		}
		if (!"".equals(loginIp)) {
			sql.append(String.format(" and t.login_ip='%1$s'", loginIp));
		}
		if (!"".equals(beginTime)) {
			sql
					.append(String
							.format(
									" and t.login_time >= to_date('%1$s 00:00:00','yyyy-mm-dd hh24:mi:ss')",
									beginTime));
		}
		if (!"".equals(endTime)) {
			sql
					.append(String
							.format(
									" and t.login_time <= to_date('%1$s 23:59:59','yyyy-mm-dd hh24:mi:ss')",
									endTime));
		}
		if ("".equals(logType)) {
		} else if ("success".equals(logType)) {
			sql
					.append(" and t.log_login_id in (select ls.log_login_id from log_login_success ls)");

		} else if ("failure".equals(logType)) {
			sql
					.append(" and t.log_login_id in (select lf.log_login_id from log_login_failure lf)");
		}
		sql.append(" order by t.log_login_id desc");
		logLoginList = dbUtil.getRsArrayList(sql.toString(), 5);
		return logLoginList;
	}

	public List<LogLogin> getLogLoginList() {
		return logLoginList;
	}

	public void setLogLoginList(List<LogLogin> logLoginList) {
		this.logLoginList = logLoginList;
	}

	public LogLogin getLogLogin() {
		return logLogin;
	}

	public void setLogLogin(LogLogin logLogin) {
		this.logLogin = logLogin;
	}
}
