/**
 * Copyright(c) 2012 西安云海信息技术有限责任公司 All Rights Reserved<br>
 * @Title: com.sincinfo.zxks.common.db.DbObjPool.java<br>
 * @Description: 数据源对象池，防止weblogic爆管 <br>
 * <br>
 * @author litian<br>
 * @date Jan 12, 2012 12:03:46 PM 
 * @version V1.0   
 */
package com.sincinfo.zxks.common.db;

import java.sql.SQLException;

import javax.naming.NamingException;

import org.river.nbf.log.Log;

/**
 * @ClassName: DbObjPool
 * @Description: 数据源对象池，防止weblogic爆管 <br>
 *               <br>
 * @author litian
 * @date Jan 12, 2012 12:03:46 PM<br>
 * 
 */
public class DbObjPool {

	// /**
	// * @ClassName: PoolMonitor
	// * @Description: 用来启动线程来监视池中对象数量等 <br>
	// * <br>
	// * @author litian
	// * @date Jan 17, 2012 2:56:10 PM<br>
	// *
	// */
	// class PoolMonitor extends Thread {
	// final static long sleepMillis = 1000 * 60 * 60 * 5;
	// DbObjPool dbObjPool = null;
	// Log log = new Log();
	//		
	// PoolMonitor() {
	// dbObjPool = DbObjPool.getInstance();
	// }
	//		
	// @Override
	// public void run() {
	// super.run();
	// while( true) {
	// try {
	// dbObjPool.balance();
	// this.sleep(sleepMillis);
	// } catch (InterruptedException e) {
	// log.error(this.getClass(), "监听连接对象DbObjPool出错！", e);
	// }
	// }
	// }
	// }
	//
	// /**
	// * @Fields DB_OBJ_NORMAL : 连接对象池正常情况下保持对象数
	// */
	// private final static int DB_OBJ_NORMAL = 2;
	//
	// /**
	// * @Fields DB_OBJ_LIMIT : 最大容纳对象数
	// */
	// private final static int DB_OBJ_LIMIT = 100;
	//
	// /**
	// * @Fields AUTO_RELEASE_PERCENT : 自动释放百分比（剩余连接数/正常保持的连接数）。
	// */
	// private final static int AUTO_RELEASE_PERCENT = 30;
	//	
	// /**
	// * @Fields AUTO_RELEASE_SURPLUS_PERCENT :
	// 最少连接数剩余（剩余连接数/声场保持连接数），只有清理后仍然总连接数大于正常水平，此项才会生效
	// */
	// private final static int AUTO_RELEASE_SURPLUS_PERCENT = 10;
	//	
	// /**
	// * @Fields plMonitor : 用来监听对象池状态
	// */
	// private static PoolMonitor plMonitor = null;

	/**
	 * @Fields instance : 连接对象池唯一实例
	 */
	private static DbObjPool instance = null;

	// /**
	// * @Fields connList : 用来保存数据连接
	// */
	// private Vector<DbConn> connList = null;
	//
	// /**
	// * @Fields currNum : 当前使用的连接数
	// */
	// private int inUseNum = 0;
	//	
	// /**
	// * 私有，初始化
	// */
	// private DbObjPool() {
	// if (connList == null)
	// connList = new Vector<DbConn>();
	//		
	// // 创建正常连接数，并放入对象池
	// int currNum = connList.size();
	// for (; currNum < DB_OBJ_NORMAL; currNum++) {
	// try {
	// connList.add(new DbConn());
	// } catch (Exception e) {
	// new Log().error(this.getClass(), "创建数据库连接失败！", e);
	// }
	// }
	// inUseNum = 0;
	// }
	//
	// /**
	// * 生成一个链接，放入列表
	// */
	// private boolean createDbConn() {
	// try {
	// connList.add(new DbConn());
	// return true;
	// } catch (Exception e) {
	// new Log().error(this.getClass(), "创建数据库连接失败！", e);
	// return false;
	// }
	// }
	//	
	// /**
	// * 启动自动释放线程
	// */
	// private void initialBalanceThread() {
	// if (plMonitor == null)
	// plMonitor = new PoolMonitor();
	//		
	// if ( !plMonitor.isAlive())
	// plMonitor.start();
	// }
	//
	/**
	 * 获取本类一个实例
	 * 
	 * @return DbObjPool
	 */
	public static synchronized DbObjPool getInstance() {
		if (instance == null)
			instance = new DbObjPool();

		return instance;
	}

	/**
	 * 获取一个数据源连接
	 * 
	 * @return DbConn
	 */
	public synchronized DbConn getDbConn() throws DbObjException {
		// initialBalanceThread();
		//		
		// DbConn db = null;
		// int surplusNum = connList.size();
		// if (surplusNum <= 0) {
		// // 剩余连接数为0，此时判断全部链接个数，是否超过最大限制
		// int totalNum = surplusNum + inUseNum;
		// if (totalNum == DB_OBJ_LIMIT) {
		// // 已经达到最大连接数，不能继续创建
		// throw new DbObjException("已经达到最大连接数，无法继续创建！");
		// } else {
		// // 还能继续生成
		// boolean boo = createDbConn();
		// if (!boo) {
		// throw new DbObjException("数据连接对象创建失败！");
		// }
		// }
		// }
		//
		// // 返回一个数据源对象
		// db = this.connList.get(0);
		// this.connList.remove(0);
		//
		// // 正在使用数量增加1
		// inUseNum++;
		//
		// new Log().info(this.getClass(), toString(""));
		// return db;

		DbConn db = null;
		try {
			db = new DbConn();
		} catch (NamingException e) {
			Log.error(e);
		} catch (SQLException e) {
			Log.error(e);
		}
		return db;
	}

	/**
	 * 释放一个连接
	 * 
	 * @param db
	 */
	public synchronized void released(DbConn db) {
		// // 将返回的数据连接补充在对象池中
		// db.CloseTemp();
		// this.connList.add(db);
		//
		// // 正在使用数量增加1
		// inUseNum--;
		try {
			if (db != null)
				db.Close();
		} catch (Exception e) {
			System.out.println("db released conn exception:"+e.toString());
		}
	}

	// /**
	// * 一次性释放连接对象池中指定数量的连接对象
	// *
	// * @param num 释放的连接对象数量
	// */
	// private synchronized void released(int num) {
	// DbConn db = null;
	// for ( int i = 0; i < num; i++) {
	// db = this.connList.get(i);
	// this.connList.remove(db);
	// db.Close();
	// }
	// }
	//
	// /**
	// * 如果连接总数超过正常状态下数量，<br>
	// * 并且 剩余连接 / 正常连接限制数 > AUTO_RELEASE_PERCENT 就开始自动释放<br>
	// */
	// public void balance() {
	// // 获取超出的数量
	// int larger = getTotalDbNum() - DB_OBJ_NORMAL;
	// if (larger > 0) {
	// // 超过正常保持的连接数
	// int percent = (int) (getSurplusDbNum() / DB_OBJ_NORMAL * 100);
	// // 总连接数即使超过正常连接数，只要剩余的连接小于自动释放百分比，就不进行操作。
	// if ( percent < AUTO_RELEASE_PERCENT) return;
	//			
	// // 计算需要彻底清理（即关闭掉的）连接数
	// int needMinFree = DB_OBJ_NORMAL * AUTO_RELEASE_SURPLUS_PERCENT / 100;
	// // 最少需要空闲的连接数与正在使用连接数之和若小于等于正常状态，则不需要释放
	// if ( (getCurrentDbNum() + needMinFree) <= DB_OBJ_NORMAL) return;
	// // 需要释放掉的连接数
	// int needReleaseNum = getSurplusDbNum() - needMinFree;
	// released( needReleaseNum);
	// } else if (larger < 0) {
	// // 连接数未达到正常水平，进行补充
	// for (; larger <= 0; larger++) {
	// createDbConn();
	// }
	// } else {
	// // 是否超过正常连接数，未超过则不做处理
	// }
	// }
	//
	// /**
	// * 销毁所有链接
	// */
	// public void destory() {
	// DbConn db = null;
	// for (int i = 0; i < connList.size(); i++) {
	// db = connList.get(i);
	// connList.remove(db);
	// db.Close();
	// }
	//
	// instance = null;
	// }
	//
	// /**
	// * 返回正在使用的连接数
	// *
	// * @return
	// */
	// public int getCurrentDbNum() {
	// return this.inUseNum;
	// }
	//
	// /**
	// * 返回剩余连接对象数
	// *
	// * @return
	// */
	// public int getSurplusDbNum() {
	// return this.connList.size();
	// }
	//
	// /**
	// * 返回目前总共的连接数
	// *
	// * @return
	// */
	// public int getTotalDbNum() {
	// return this.inUseNum + this.connList.size();
	// }

	/**
	 * 格式化输出
	 * 
	 * @param type
	 * @return
	 */
	public String toString(String type) {
		// String info = null;
		// if ("html".equals(type)) {
		// info = "正在使用连接数：%1$s，目前剩余连接数：%2$s，目前总连接数：%3$s，%4$s";
		// } else {
		// info = "正在使用连接数：%1$s，目前剩余连接数：%2$s，目前总连接数：%3$s\n%4$s";
		// }
		// return String.format(info, getCurrentDbNum(), getSurplusDbNum(),
		// getTotalDbNum(), toString());
		return "";
	}
}
