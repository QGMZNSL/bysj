package com.sincinfo.zxks.common.filter;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sincinfo.zxks.common.util.StringTool;

public class SetCharacterEncodingFilter implements Filter {

	protected String encoding = null;
	protected FilterConfig filterConfig = null;
	protected String orgencoding = null;

	public void destroy() {
		this.encoding = null;
		this.filterConfig = null;
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
		this.encoding = filterConfig.getInitParameter("encoding");
		this.orgencoding = filterConfig.getInitParameter("orgencoding");
	}

	public void doFilter(ServletRequest svlrequest,
			ServletResponse svlresponse, FilterChain chain) throws IOException,
			ServletException {
		HttpServletRequest request = (HttpServletRequest) svlrequest;
		HttpServletResponse response = (HttpServletResponse) svlresponse;
		String method = request.getMethod();
		if (method.toUpperCase().equals("POST")) {// post方法
			Enumeration paramNames = request.getParameterNames();
			while (paramNames.hasMoreElements()) {
				String name = paramNames.nextElement().toString();
				String[] values = request.getParameterValues(name);
				if (values != null) {
					for (int i = 0; i < values.length; i++) {
						values[i] = valueFilter(new String(values[i].trim()
								.getBytes(orgencoding), encoding));
					}
				}
			}
		} else if (method.toUpperCase().equals("GET")) {// get方法
			Enumeration paramNames = request.getParameterNames();
			while (paramNames.hasMoreElements()) {
				String name = paramNames.nextElement().toString();
				String[] values = request.getParameterValues(name);
				if (values != null) {
					for (int i = 0; i < values.length; i++) {
						values[i] = valueFilter(new String(values[i].trim()
								.getBytes(orgencoding), encoding));
					}
				}
			}
		}
		response.setCharacterEncoding(encoding);

		/*
		 * modified by liaodc on 2010-4-7<br> 加入参数校验
		 * 
		 */
//		if (hasIllChars(request)) {
//			PostJs("alert('您提交的数据含有非法字符！');history.go(-1);", response);
//		} else {
//			// response.setHeader("pragma", "no-cache");
//			// response.setHeader("cache-control", "no-cache");
//		}
		chain.doFilter(request, response);
	}

	/**
	 * 过滤非法字符
	 */
	private String valueFilter(String v) {
		v = v.replace("&", "&#x26;");
		// v = v.replace(":", "：");
		v = v.replace("=", "&#x3D;");
		v = v.replace("<", "&#x3C;");
		v = v.replace(">", "&#x3E;");
		// v = v.replace(" ", "&#x20;");
		v = v.replace("\"", "&#34;");
		return v;
	}

	/**
	 * 校验是否含有非法字符
	 * 
	 * @param values
	 *            参数集合
	 * @return true:has false:not contain
	 */
	@SuppressWarnings("unchecked")
	private boolean hasIllChars(HttpServletRequest request) throws IOException {
		boolean flag = false;

		// 排除不需要过滤的uri
		String uri = request.getRequestURI().toLowerCase();
		Enumeration paramNames = request.getParameterNames();
		// 遍历所有参数
		while (paramNames.hasMoreElements()) {
			if (!flag) {
				String name = paramNames.nextElement().toString();
				String[] values = request.getParameterValues(name);
				if (values != null) {
					for (int i = 0; i < values.length; i++) {
						// 逐个判断
						flag = checkStr(values[i]);
						if (flag) {
							break;
						}
					}
				}
			} else {
				break;
			}
		}
		return flag;
	}

	private boolean checkStr(String value) {
		boolean flag = false;
		value = StringTool.trim(value).toLowerCase();

		/*
		 * 所有非法字符 TODO 继续添加
		 */

		// 数据库相关
		if (value.contains("insert") || value.contains("update")
				|| value.contains("delete") || value.contains("create")
				|| value.contains("drop") || value.contains("where")
				|| value.contains("having")) {
			flag = true;
		} else if (value.contains("<script") || value.contains("script>")
				|| value.contains("script") || value.contains("<iframe")
				|| value.contains("javascript:") || value.contains("=\"")
				|| value.contains("='")) {
			// 页面、脚本相关
			flag = true;
		} else if (false) {
			// TODO 文字相关
		}

		return flag;
	}

	/**
	 * 向客户端输出角本语句
	 * 
	 * @param str
	 *            要输出的角本内容
	 */
	private void PostJs(String str, HttpServletResponse response) {
		try {
			response.getWriter().print("<script language='javascript'>");
			response.getWriter().print(str);
			response.getWriter().print("</script>");
			response.getWriter().flush();
		} catch (IOException e) {
		}
	}
}
