/**
 * Copyright(c) 2012 西安云海信息技术有限责任公司 All Rights Reserved<br>
 * @Title: com.sincinfo.zxks.common.SessionMap.java<br>
 * @Description: 用来存储session <br>
 * <br>
 * @author litian<br>
 * @date Aug 17, 2012 12:30:57 PM 
 * @version V1.0   
 */
package com.sincinfo.zxks.common;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

/**
 * @ClassName: SessionMap
 * @Description: 用来存储session <br>
 *               <br>
 * @author litian
 * @date Aug 17, 2012 12:30:57 PM<br>
 * 
 */
public class SessionMap {
	private static SessionMap singleEntity = null;
	private HashMap<String, HttpSession> sessionMap = null;

	/**
	 * 对外隐藏构造方法
	 */
	private SessionMap() {
		this.sessionMap = new HashMap<String, HttpSession>();
	}

	/**
	 * 获得SessionMap对象
	 * 
	 * @return SessionMap
	 */
	synchronized public static SessionMap getInstance() {
		if (SessionMap.singleEntity == null) {
			SessionMap.singleEntity = new SessionMap();
		}
		return SessionMap.singleEntity;
	}

	/**
	 * 该用户是否已登陆
	 * 
	 * @param userName
	 *            用户名
	 * @return boolean
	 */
	public boolean isExists(String userName) {
		HttpSession currSession = getSession(userName);
		return !(currSession == null);
	}

	/**
	 * 获取session
	 * 
	 * @param userName
	 *            用户名
	 * @return HttpSession 记录上次登陆的会话
	 */
	public HttpSession getSession(String userName) {
		return this.sessionMap.get(userName);
	}

	/**
	 * 先前登陆的session与现在使用的session是否一致
	 * 
	 * @param userName
	 *            userName 用户名
	 * @param session
	 *            HttpSession 操作会话
	 * @return boolean
	 */
	public boolean isSame(String userName, HttpSession session) {
		HttpSession currSession = getSession(userName);
		return currSession == session || session.equals(currSession);
	}

	/**
	 * 登陆时记录session
	 * 
	 * @param userName
	 *            用户名
	 * @param session
	 *            会话
	 */
	public void regSession(String userName, HttpSession session) {
		this.sessionMap.put(userName, session);
	}

	/**
	 * 将session注销掉
	 * 
	 * @param userName
	 *            用户名
	 */
	public void invalidateSession(String userName) {
		HttpSession currSession = getSession(userName);
		if (currSession != null) {
			currSession.invalidate();
		}
		this.sessionMap.remove(userName);
	}
}
