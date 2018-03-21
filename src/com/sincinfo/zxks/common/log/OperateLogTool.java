package com.sincinfo.zxks.common.log;

import java.sql.SQLException;
import java.util.List;

import com.sincinfo.zxks.common.db.DbConn;
import com.sincinfo.zxks.common.db.DbObjPool;
import com.sincinfo.zxks.common.util.Log;

/**
 * @ClassName: OperateLogTool
 * @Description: 保存操作日志到数据库 <br>
 *               <br>
 * @author litian
 * @date Jan 12, 2012 10:27:08 AM<br>
 * 
 */
public class OperateLogTool {
	
	/**
	 * 保存单个OperatLog对象
	 * 
	 * @param operatLog
	 *            操作日志对象
	 * @return 成功标记
	 */
	public static boolean saveOptLog(OperatLog operatLog) {
		boolean saveSign = false;
		
		// 连接sql
		String sql = createSql();
		
		// 获取连接对象
		DbObjPool dbPool = DbObjPool.getInstance();
		DbConn dbConn = null;
		try {
			dbConn = dbPool.getDbConn();
			dbConn.pstmt = dbConn.conn.prepareStatement(sql);
			dbConn.pstmt.setString(1, operatLog.getUserName());
			dbConn.pstmt.setString(2, operatLog.getProjectName());
			dbConn.pstmt.setString(3, operatLog.getNodeId());
			dbConn.pstmt.setString(4, operatLog.getLogOptIp());
			dbConn.pstmt.setString(5, operatLog.getLogOptMethod());
			dbConn.pstmt.setString(6, operatLog.getLogOptSql());
			dbConn.pstmt.setString(7, operatLog.getRemarks());
			saveSign = dbConn.pstmt.execute();
		} catch (Exception e) {
			new Log().error(OperateLogTool.class, "保存操作日志对象失败！", e);
		} finally {
			dbPool.released(dbConn);
		}
		
		return saveSign;
	}

	/**
	 * 保存多个OperatLog对象
	 * 
	 * @param operLogList
	 *            操作日志对象列表
	 * @return 执行成功条数
	 */
	public static int saveOptLogs(List<OperatLog> operLogList) {
		int succRow = 0;
		
		// 连接sql
		String sql = createSql();

		// 获取连接对象
		DbObjPool dbPool = DbObjPool.getInstance();
		DbConn db = null;
		try {
			db = dbPool.getDbConn();
			db.conn.setAutoCommit( false);
			db.conn.setTransactionIsolation( db.conn.TRANSACTION_READ_COMMITTED);
			db.pstmt = db.conn.prepareStatement(sql);
			boolean saveSign = false;
			for ( OperatLog operatLog : operLogList) {
				db.pstmt = db.conn.prepareStatement(sql);
				db.pstmt.setString(1, operatLog.getUserName());
				db.pstmt.setString(2, operatLog.getProjectName());
				db.pstmt.setString(3, operatLog.getNodeId());
				db.pstmt.setString(4, operatLog.getLogOptIp());
				db.pstmt.setString(5, operatLog.getLogOptMethod());
				db.pstmt.setString(6, operatLog.getLogOptSql());
				db.pstmt.setString(7, operatLog.getRemarks());
				saveSign = db.pstmt.execute();
				if ( saveSign) succRow++;
			}
			db.conn.commit();
		} catch (Exception e) {
			new Log().error(OperateLogTool.class, "保存操作日志对象失败！", e);
			try {
				db.conn.rollback();
			} catch (SQLException e1) {
				new Log().error(OperateLogTool.class, "事务回滚失败！", e1);
			}
		} finally {
			if ( db != null) {
				try {
					db.conn.setAutoCommit(true);
				} catch (SQLException e) {
					new Log().error(OperateLogTool.class, "设置提交模式autoCommint(true)失败！", e);
					e.printStackTrace();
				}
				dbPool.released(db);
			}
		}
		
		return succRow;
	}

	/**
	 * 连接sql
	 * 
	 * @return 连接后的sql
	 */
	protected static String createSql() {
		StringBuilder sqlBuf = new StringBuilder();
		sqlBuf.append("insert into log_operator");
		sqlBuf.append(" (log_opt_id, ");
		sqlBuf.append(" user_name,");
		sqlBuf.append(" project_name,");
		sqlBuf.append(" node_id, ");
		sqlBuf.append(" log_opt_ip,");
		sqlBuf.append(" log_opt_time,");
		sqlBuf.append(" log_opt_method,");
		sqlBuf.append(" log_opt_sql,");
		sqlBuf.append(" remarks");
		sqlBuf.append(" ) values( seq_log_opt.nextVal,?,?,?,?,sysdate,?,?,?)");
		return sqlBuf.toString();
	}
}
