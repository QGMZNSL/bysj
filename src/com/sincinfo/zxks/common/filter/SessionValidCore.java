package com.sincinfo.zxks.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sincinfo.zxks.bean.BaseUser;
import com.sincinfo.zxks.common.Constants;
import com.sincinfo.zxks.common.SessionMap;
import com.sincinfo.zxks.common.tree.MenuTreeDbService;
import com.sincinfo.zxks.common.tree.TreeNode;
import com.sincinfo.zxks.common.util.StringTool;

public class SessionValidCore implements Filter {
	@SuppressWarnings("unused")
	private boolean urlFlag = true;

	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;
		HttpSession session = request.getSession();

		// 未登录处理脚本
		String unLoginJs = String
				.format("alert('登录超时，请重新登录！');top.location.href='%1$s/manager/login.jsp'",
						request.getContextPath());

		// 跳过登陆页面
		String uri = request.getRequestURI();
		if (uri.indexOf("/manager/sysdb/sinc.jsp") >= 0
				|| uri.indexOf("/manager/sysdb/sincexe.jsp") >= 0) {
			// 特殊部分，不处理
		} else if (uri.indexOf("/test/test_") >= 0
				|| uri.indexOf("/sys/core_login.do") >= 0
				|| uri.indexOf("/login.jsp") >= 0 || uri.endsWith("/")) {
			// 测试部分,登录部分不处理
		} else if (uri.indexOf("/manager/login.jsp") >= 0
				|| uri.indexOf("/index.jsp") >= 0) {
			// 过滤登录页面
			if (isOperOnLine(request, response)) {
				String keepOnineJs = String.format(
						"top.location.href='%1$s/manager/start.jsp';",
						request.getContextPath());
				this.PostJs(request, response, keepOnineJs);
				return;
			}
		} else {
			if (isOperOnLine(request, response)) {
				// 设置导航
				setNavigation(arg0, arg1, arg2);
			} else {
				this.PostJs(request, response, unLoginJs);
				return;
			}
		}

		arg2.doFilter(request, response);
	}

	/**
	 * 判断是否为已登录状态
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	private boolean isOperOnLine(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession sess = request.getSession();
		Object optUserObj = sess.getAttribute(Constants.ZK_OPERATOR);
		boolean onlineSign = false;
		BaseUser optUser = null;
		if (optUserObj != null && optUserObj instanceof BaseUser) {
			optUser = (BaseUser) optUserObj;
			if ("".equals(StringTool.trim(optUser.getUserName()))
					|| "".equals(StringTool.trim(optUser.getUserPassword()))) {
				onlineSign = false;
			} else {
				onlineSign = true;

				// 判断此session与当前记录的新session是否一致，如果不一致，则提示已在别处登陆，并销毁session
				SessionMap sessMap = SessionMap.getInstance();
				if (sessMap.isExists(optUser.getUserName())
						&& !sessMap.isSame(optUser.getUserName(),
								request.getSession())) {
					this.PostJs(request, response,
							"alert('您的账号已在别的地方登录，您已被迫下线！');");
					// 销毁session
					sess.removeAttribute(Constants.ZK_OPERATOR);
					onlineSign = false;
				}
			}
		}

		return onlineSign;
	}

	@SuppressWarnings("unused")
	private void checkGPurview(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;
		HttpSession session = request.getSession();

		String uri = request.getRequestURI();
		// System.out.println(uri);
	}

	/**
	 * 在菜单树中查询对应的导航
	 */
	private TreeNode setNavigation(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;
		HttpSession session = request.getSession();

		String nodeId = request.getParameter("nid");
		if (nodeId == null)
			return null;

		TreeNode tnode = new MenuTreeDbService().getTreeNode(nodeId);
		if (tnode == null)
			return null;

		if (!"".equals(StringTool.trim(tnode.getNodeId())))
			session.setAttribute(Constants.TREE_NODE_ID, tnode.getNodeId());

		if (!"".equals(StringTool.trim(tnode.getNavigator())))
			session.setAttribute(Constants.NAVIGATION_NAME,
					tnode.getNavigator());

		if (!"".equals(StringTool.trim(tnode.getFunctionId())))
			session.setAttribute(Constants.FUNCTION_ID, tnode.getFunctionId());

		return tnode;
	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

	/**
	 * 向客户端输出角本语句
	 * 
	 * @param str
	 *            要输出的角本内容
	 */
	protected void PostJs(HttpServletRequest request,
			HttpServletResponse response, String str) {
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		try {
			response.getWriter().print("<script type='text/javascript'>");
			response.getWriter().print(str);
			response.getWriter().print("</script>");
			response.getWriter().flush();
		} catch (IOException e) {
		}
	}
}
