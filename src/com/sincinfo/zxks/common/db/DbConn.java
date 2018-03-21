package com.sincinfo.zxks.common.db;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DbConn {
	protected String dbsourcename = "jndiszxksds"; // weblogic jndi
	// protected String dbsourcename = "java:comp/env/jndiszxksds"; // tomcat
	// jndi
	protected static InitialContext ctx = null;

	public Connection conn = null;
	public PreparedStatement pstmt = null;
	public CallableStatement cstmt = null;
	public Statement st = null;
	public ResultSet rs = null;
	public Exception ex = null;

	/**
	 * 根据指定连接池的jndi进行构造
	 * 
	 * @param sourcename
	 * @throws NamingException
	 * @throws SQLException
	 */
	protected DbConn() throws NamingException, SQLException {
		ex = null;
		if (ctx == null) {
			ctx = new InitialContext();
		}
		DataSource ds = (DataSource) ctx.lookup(dbsourcename);
		conn = ds.getConnection();
	}

	protected DbConn(String sourcename) throws NamingException, SQLException {
		ex = null;
		if (ctx == null) {
			ctx = new InitialContext();
		}
		DataSource ds = (DataSource) ctx.lookup(sourcename);
		conn = ds.getConnection();
	}

	/**
	 * 得到某个表的所有列名
	 */
	public ArrayList<String> getColsByTableName(String tablename) {
		ArrayList<String> r = new ArrayList<String>();
		try {
			this.pstmt = this.conn.prepareStatement("select * from "
					+ tablename + " where rownum<2");
			this.rs = this.pstmt.executeQuery();
			java.sql.ResultSetMetaData rsmd = this.rs.getMetaData();
			for (int i = 1; i <= rsmd.getColumnCount(); i++) {
				r.add(rsmd.getColumnName(i));
			}
		} catch (Exception e) {
			ex = new Exception(e);
		} finally {
			this.Close();
		}
		return r;
	}

	/**
	 * 重写Object的finalize()
	 */
	protected void finalize() throws Throwable {
		super.finalize();
		this.Close();
	}

	/**
	 * 关闭连接
	 */
	public void Close() {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
			}
		}
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
			}
		}
		if (cstmt != null) {
			try {
				cstmt.close();
			} catch (SQLException e) {
			}
		}
		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
			}
		}
	}

	/**
	 * 关闭临时对象
	 */
	public void CloseTemp() {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
			}
		}
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
			}
		}
		if (cstmt != null) {
			try {
				cstmt.close();
			} catch (SQLException e) {
			}
		}
		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
			}
		}
	}

}
