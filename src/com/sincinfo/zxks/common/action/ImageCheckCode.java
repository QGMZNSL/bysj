/**
 * ImageCheckCode.java
 * 功能:登录图片验证码
 * 王海辉
 * 2009-2-4
 */
package com.sincinfo.zxks.common.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sincinfo.zxks.common.util.ImageCheckCodeCreator;

public class ImageCheckCode extends HttpServlet {

	private static final long serialVersionUID = 1057112305284916185L;

	/**
	 * 请求图片处理过程
	 */
	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		ImageCheckCodeCreator checkCodeCreator = new ImageCheckCodeCreator( request, response, 5, ImageCheckCodeCreator.CCTYPE_LOWER_CHAR_ONLY);
		checkCodeCreator.setFontSize(22);
		checkCodeCreator.setHeight(22);
		ServletOutputStream responseOutputStream = checkCodeCreator.createImageCheckCode();
		responseOutputStream.flush();
		
		// 关闭输入流！
		responseOutputStream.close();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	public String getServletInfo() {
		return "图片验证码";
	}
}
