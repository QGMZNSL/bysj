package com.sincinfo.zxks.common.db;

import java.io.Reader;
import java.io.StringReader;
import java.lang.reflect.Method;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sincinfo.zxks.common.util.Log;

public class DbUtil {
	private Log log = new Log();
	private DbObjPool dbp = null;

	/**
	 * 构造函数
	 */
	public DbUtil() {
		dbp = DbObjPool.getInstance();
	}

	/**
	 * 获取DbConn对象
	 * 
	 * @return
	 */
	public DbConn getDbConn() {
		DbConn db = null;
		try {
			db = this.dbp.getDbConn();
		} catch (DbObjException e) {
			log.error(this.getClass(), "获取数据源失败！", e);
		}
		return db;
	}

	/**
	 * 执行Sql语句，包括Update，Insert，Delete
	 * 
	 * @param sql
	 *            SQL语句，允许含?
	 * @param args
	 *            传入对应预编译?的值
	 * @return int 返回执行影响行数 -1表示执行异常
	 */
	public int saveOrUpdate(String sql, String... args) {
		DbConn db = getDbConn();
		int excNum = -1;
		try {
			db.pstmt = db.conn.prepareStatement(sql);
			for (int i = 1; i <= args.length; i++) {// 为sql语句中的？赋值
				db.pstmt.setString(i, args[i - 1]);
			}
			excNum = db.pstmt.executeUpdate();
		} catch (Exception e) {
			log.error(this.getClass(), sql, e);
		} finally {
			this.dbp.released(db);
		}
		return excNum;
	}

	/**
	 * sql语句多个条件,条件为多个参数
	 * 
	 * @param sql
	 *            ：sql查询语句
	 * @param num
	 *            ：返回的集合的的String[]数组的存放变量个数
	 * @param args
	 *            ：可变参数，预编译sql语句的条件
	 * @return 返回num个值的字符串数组的String[]对象数组
	 */
	public String[] getRsArray(String sql, int num, String... args) {
		String[] values = null;
		DbConn db = getDbConn();
		try {
			db.pstmt = db.conn.prepareStatement(sql);
			for (int i = 1; i <= args.length; i++) {// 为sql语句中的？赋值
				db.pstmt.setString(i, args[i - 1]);
			}
			db.rs = db.pstmt.executeQuery();
			if (db.rs.next()) {
				values = new String[num];
				for (int j = 0; j < num; j++) {
					values[j] = db.rs.getString(j + 1);
				}
			}
		} catch (Exception e) {
			log.error(this.getClass(), sql, e);
		} finally {
			this.dbp.released(db);
		}

		return values;
	}

	/**
	 * sql语句多个条件,返回查询的结果的字符串数组的集合
	 * 
	 * @param sql
	 *            ：sql查询语句
	 * @param num
	 *            ：返回的集合的的String[]数组的存放变量个数
	 * @param args
	 *            ：可变参数，预编译sql语句的条件
	 * @return 返回num个值的字符串数组的List集合
	 */
	public List<String[]> getRsArrayList(String sql, int num, String... args) {
		List<String[]> list = new ArrayList<String[]>();
		String[] values = null;
		DbConn db = getDbConn();
		try {
			db.pstmt = db.conn.prepareStatement(sql);
			if (args != null) {
				for (int i = 1; i <= args.length; i++) {// 为sql语句中的？赋值
					db.pstmt.setString(i, args[i - 1]);
				}
			}
			db.rs = db.pstmt.executeQuery();
			while (db.rs.next()) {
				values = new String[num];
				for (int j = 0; j < num; j++) {
					values[j] = db.rs.getString(j + 1);
				}
				list.add(values);
			}
		} catch (Exception e) {
			log.error(this.getClass(), sql, e);
		} finally {
			this.dbp.released(db);
		}
		return list;
	}

	/**
	 * 根据指定sql语句和参数条件获取指定的对象
	 * 
	 * @param <T>
	 * @param sql
	 * @param typeCode
	 * @param cls
	 * @return
	 */
	public <T> T getObject(String sql, Class<T> cls, String... args) {
		Object obj = null;
		Method[] ms = cls.getDeclaredMethods();
		DbConn db = getDbConn();
		try {
			db.pstmt = db.conn.prepareStatement(sql);
			for (int i = 0; i < args.length; i++) {
				db.pstmt.setString(i + 1, args[i]);
			}
			db.rs = db.pstmt.executeQuery();
			ResultSetMetaData rsmd = db.rs.getMetaData();
			int colCount = rsmd.getColumnCount();
			if (db.rs.next()) {
				obj = cls.newInstance();
				for (int i = 1; i <= colCount; i++) {
					String colName = rsmd.getColumnName(i);
					colName = colName.replaceAll("_", "");
					for (int j = 0; j < ms.length; j++) {
						Method method = ms[j];
						Class[] cs = method.getParameterTypes();
						if (method.getName().equalsIgnoreCase("set" + colName)) {
							if (cs[0] == int.class) {
								method.invoke(obj, db.rs.getInt(i));
								break;
							} else if (cs[0] == Date.class) {
								method.invoke(obj, db.rs.getDate(i));
								break;
							} else {
								method.invoke(obj, db.rs.getString(i));
								break;
							}
						}
					}
				}
			}
		} catch (Exception e) {
			log.error(this.getClass(), sql, e);
		} finally {
			this.dbp.released(db);
		}
		return (T) obj;
	}

	/**
	 * 根据指定sql语句和参数条件获取指定的对象集合
	 * 
	 * @param <T>
	 * @param sql
	 * @param typeCode
	 * @param cls
	 * @return
	 */
	public <T> List<T> getObjList(String sql, Class<T> cls, String... args) {
		// long start = System.currentTimeMillis(); // add by litian 2011-05-12
		List<T> list = new ArrayList<T>();
		Method[] ms = cls.getDeclaredMethods();
		DbConn db = getDbConn();
		try {
			db.pstmt = db.conn.prepareStatement(sql);
			if (args != null) {
				for (int i = 0; i < args.length; i++) {
					db.pstmt.setString(i + 1, args[i]);
				}
			}
			db.rs = db.pstmt.executeQuery();
			ResultSetMetaData rsmd = db.rs.getMetaData();
			int colCount = rsmd.getColumnCount();
			while (db.rs.next()) {
				Object obj = cls.newInstance();
				for (int i = 1; i <= colCount; i++) {
					String colName = rsmd.getColumnName(i);
					colName = colName.replaceAll("_", "");
					for (int j = 0; j < ms.length; j++) {
						Method method = ms[j];
						Class[] cs = method.getParameterTypes();
						if (method.getName().equalsIgnoreCase("set" + colName)) {
							if (cs[0] == int.class) {
								method.invoke(obj, db.rs.getInt(i));
								break;
							} else if (cs[0] == Date.class) {
								method.invoke(obj, db.rs.getDate(i));
								break;
							} else {
								method.invoke(obj, db.rs.getString(i));
								break;
							}
						}
					}
				}
				list.add((T) obj);
			}
		} catch (Exception e) {
			log.error(this.getClass(), sql, e);
		} finally {
			this.dbp.released(db);
		}
		return list;
	}

	/**
	 * 查询结果个数
	 * 
	 * @param sqlCount
	 * @return
	 */
	public long getNum(String sqlCount, String... args) {
		// 初始化
		long count = -1;
		DbConn db = getDbConn();

		// 查询结果个数
		try {
			db.pstmt = db.conn.prepareStatement(sqlCount);
			for (int i = 0; i < args.length; i++) {
				db.pstmt.setString(i + 1, args[i]);
			}

			db.rs = db.pstmt.executeQuery();
			if (db.rs.next()) {
				count = db.rs.getLong(1);
			}
		} catch (Exception e) {
			// 统计失败
			log.error(this.getClass(), sqlCount, e);
		} finally {
			this.dbp.released(db);
		}
		return count;
	}

	/**
	 * 
	 * 获得第一行的信息
	 * 
	 * @param sql
	 * @param objs
	 * @return
	 */
	public String getFirstCol(String sql, Object... objs) {
		String result = "0";
		DbConn db = getDbConn();
		try {
			db.pstmt = db.conn.prepareStatement(sql);
			if (objs != null) {
				for (int i = 0; objs != null && i < objs.length; i++) {
					db.pstmt.setObject(i + 1, objs[i]);
				}
			}
			db.rs = db.pstmt.executeQuery();
			if (db.rs.next()) {
				result = db.rs.getString(1);
			}
			return result;
		} catch (Exception e) {
			log.error(this.getClass(), sql, e);
		} finally {
			this.dbp.released(db);
		}
		return null;
	}

	/**
	 * 事务批量处理Sql，返回-1表示执行失败
	 * 
	 * @param sqls
	 * @return
	 */
	public int transExeSqls(ArrayList<String> sqls) {
		int succNum = 0;
		DbConn db = getDbConn();
		try {
			db.conn.setAutoCommit(false);
			db.st = db.conn.createStatement();

			for (int i = 0; i < sqls.size(); i++) {
				if (db.st.executeUpdate(sqls.get(i)) >= 0) {
					succNum++;
				} else {
					throw new Exception(String.format("事务执行失败SQL：%1$s",
							sqls.get(i)));
				}
			}
			db.conn.commit();
		} catch (Exception e) {
			log.error(this.getClass(), e.getMessage());
			for (String s : sqls) {
				log.error(this.getClass(), s);
			}
			try {
				db.conn.rollback();
			} catch (SQLException e1) {
				log.error(this.getClass(), "异常时，事务回滚失败！", e1);
			}
			succNum = -1;
		} finally {
			try {
				db.conn.setAutoCommit(true);
			} catch (SQLException e) {
				log.error(this.getClass(), "设置自动提交失败！", e);
			}
			this.dbp.released(db);
		}
		return succNum;
	}

	/**
	 * 获取保存文件地址
	 * 
	 * @return String[] [0]-物理地址 [1]-网络地址
	 */
	public String[] getPaths() {
		// 提取操作系统名
		String cfgTypeOs = System.getProperty("os.name").split(" ")[0]
				.toLowerCase();

		// 查询
		StringBuilder sql = new StringBuilder();
		sql.append("select (select c.sys_cfg_content from sys_config c");
		sql.append(" where c.sys_cfg_type='%1$s' and (c.sys_cfg_id = '1' or c.sys_cfg_id = '3')) as phyPath,");
		sql.append(" (select c.sys_cfg_content from sys_config c");
		sql.append(" where c.sys_cfg_type='%1$s' and (c.sys_cfg_id = '2' or c.sys_cfg_id = '4')) as netPath");
		sql.append(" from dual");
		DbUtil dbUtil = new DbUtil();
		String[] paths = dbUtil.getRsArray(
				String.format(sql.toString(), cfgTypeOs), 2);
		return paths;
	}

	/**
	 * 根据配置sys_config表主键，取得对应的content
	 * 
	 * @param cfgId
	 *            主键
	 * @return String 配置内容content
	 */
	public String getConfig(String cfgId) {
		String sql = String
				.format("select c.sys_cfg_content from sys_config c where c.sys_cfg_id = '%1$s'",
						cfgId);
		DbUtil dbUtil = new DbUtil();
		return dbUtil.getFirstCol(sql);
	}

	/**
	 * @see 保存或更新对象（insert,update） 并 插入clob
	 * @param sql
	 * @param clob
	 * @return
	 */

	public int saveOrUpdateClob(String sql, String clob) {
		DbConn db = getDbConn();
		int excNum = -1;
		try {
			Reader rd = new StringReader(clob);
			db.pstmt = db.conn.prepareStatement(sql);
			db.pstmt.setCharacterStream(1, rd, clob.length());
			excNum = db.pstmt.executeUpdate();
		} catch (Exception e) {
			log.error(this.getClass(), sql, e);
		} finally {
			this.dbp.released(db);
		}
		return excNum;
	}

	/**
	 * yuansh 2012-02-06 获得数据库的信息
	 * 
	 * @param sql
	 * @return
	 */
	public String getString(String sql) {
		String result = "";
		DbConn db = getDbConn();
		try {
			db.pstmt = db.conn.prepareStatement(sql);
			db.rs = db.pstmt.executeQuery();
			if (db.rs.next()) {
				result = db.rs.getString(1);
			}
		} catch (Exception e) {
			log.error(this.getClass(), sql, e);
		} finally {
			this.dbp.released(db);
		}
		return result;
	}

	/**
	 * 查询数据得到数组：yuansh
	 * 
	 * @param sql1
	 *            :查询语句
	 * @return 返回num个值的字符串数组的String[]对象数组
	 */
	public String[] getRsArray(String sql, String sql1) {
		String[] values = null;
		DbConn db = getDbConn();
		try {
			int length = 0;
			db.pstmt = db.conn.prepareStatement(sql);
			db.rs = db.pstmt.executeQuery();
			if (db.rs.next()) {
				length = db.rs.getInt(1);
			}
			db.rs.close();
			db.pstmt.close();
			db.pstmt = db.conn.prepareStatement(sql1);
			db.rs = db.pstmt.executeQuery();
			values = new String[length];
			int j = 0;
			while (db.rs.next()) {
				values[j] = db.rs.getString(1);
				j++;
			}
		} catch (Exception e) {
			log.error(this.getClass(), sql, e);
		} finally {
			this.dbp.released(db);
		}
		return values;
	}
}
