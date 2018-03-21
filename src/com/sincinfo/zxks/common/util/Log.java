package com.sincinfo.zxks.common.util;

import org.apache.log4j.Logger;

/**
 * 日志输出
 * 
 * @author liaodc
 * 
 */
public class Log {

	/**
	 * 调试级别日志输出
	 * 
	 * @param cls
	 * @param msg
	 */
	public void debug(Class<?> cls, String msg) {
		Logger.getLogger(cls).debug(msg);
	}

	/**
	 * 调试级别日志输出
	 * 
	 * @param cls
	 * @param msg
	 * @param e
	 */
	public void debug(Class<?> cls, String msg, Exception e) {
		Logger.getLogger(cls).debug(msg, e);
	}

	/**
	 * 信息级别日志输出
	 * 
	 * @param cls
	 * @param msg
	 */
	public void info(Class<?> cls, String msg) {
		Logger.getLogger(cls).info(msg);
	}

	/**
	 * 信息级别日志输出
	 * 
	 * @param cls
	 * @param msg
	 * @param e
	 */
	public void info(Class<?> cls, String msg, Exception e) {
		Logger.getLogger(cls).info(msg, e);
	}

	/**
	 * 警告级别日志输出
	 * 
	 * @param cls
	 * @param msg
	 */
	public void warn(Class<?> cls, String msg) {
		Logger.getLogger(cls).warn(msg);
	}

	/**
	 * 警告级别日志输出
	 * 
	 * @param cls
	 * @param msg
	 * @param e
	 */
	public void warn(Class<?> cls, String msg, Exception e) {
		Logger.getLogger(cls).warn(msg, e);
	}

	/**
	 * 错误级别日志输出
	 * 
	 * @param cls
	 * @param msg
	 */
	public void error(Class<?> cls, String msg) {
		Logger.getLogger(cls).error(msg);
	}

	/**
	 * 错误级别日志输出
	 * 
	 * @param cls
	 * @param msg
	 * @param e
	 */
	public void error(Class<?> cls, String msg, Exception e) {
		Logger.getLogger(cls).error(msg, e);
	}
}
