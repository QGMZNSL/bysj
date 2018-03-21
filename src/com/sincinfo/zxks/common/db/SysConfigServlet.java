package com.sincinfo.zxks.common.db;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 读取数据库连接池名称
 * 
 * @author liaodc
 * 
 */
public class SysConfigServlet extends HttpServlet {

	private static final long serialVersionUID = 4411052345603586130L;
	public static String JNDINAME;

	public void init(ServletConfig cfg) throws ServletException {
		JNDINAME = cfg.getInitParameter("jndi");
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO code here
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO code here
	}
}
