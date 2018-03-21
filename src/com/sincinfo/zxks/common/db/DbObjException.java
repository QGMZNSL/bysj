package com.sincinfo.zxks.common.db;

/**
 * @ClassName: DbObjException 
 * @Description: 用于数据源对象链接 <br>
 * <br>
 * @author litian
 * @date Jan 12, 2012 2:48:37 PM<br>
 *  
 */
public class DbObjException extends Exception {
	/**
	 * @Fields serialVersionUID : 
	 */
	private static final long serialVersionUID = -1241805261615860742L;
	
	/**
	 * 定义特定错误消息的异常
	 * 
	 * @param msg
	 *            错误信息
	 */
	public DbObjException(String msg) {
		super(msg);
	}
}
