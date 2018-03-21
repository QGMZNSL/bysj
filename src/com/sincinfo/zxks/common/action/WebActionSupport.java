package com.sincinfo.zxks.common.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;

import com.opensymphony.xwork2.ActionSupport;
import com.sincinfo.zxks.bean.BaseUser;
import com.sincinfo.zxks.common.Constants;
import com.sincinfo.zxks.common.log.OperatLog;

/**
 * 所有action必须继承此类
 * 
 * @author Administrator
 * 
 */
public class WebActionSupport extends ActionSupport implements
		ServletRequestAware, ServletResponseAware, ServletContextAware,
		SessionAware {

	private static final long serialVersionUID = 1L;
	protected HttpServletRequest request = null;
	protected ServletContext context = null;
	protected HttpServletResponse response = null;
	protected Map<String, Object> session = null;
	
	/**
	 * @return 获取操作员对象
	 */
	protected BaseUser getCOperUser() {
		Object userObj = this.session.get(Constants.ZK_OPERATOR);
//		System.out.println("userObj="+userObj);
		return userObj == null ? new BaseUser() : (BaseUser) userObj;
	}
	
	/**
	 * 返回一个具备用户信息及功能信息的log对象
	 * @param logOptMethod
	 * @param describe
	 * @return
	 */
	protected OperatLog getOptLog( String logOptMethod, String describe) {
		// 构造操作日志
		BaseUser optUser = this.getCOperUser();
		OperatLog optLog = new OperatLog(optUser.getUserName(), request
				.getRemoteAddr(), session.get(Constants.TREE_NODE_ID)
				.toString(), request.getContextPath(), logOptMethod, "", describe);
		return optLog;
	}

	/**
	 * 向客户端输出角本弹出框
	 * 
	 * @param msg
	 *            要弹出的消息
	 */
	protected void Alert(String msg) {
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		try {
			response.getWriter().println("<script language='javascript'>");
			response.getWriter().print("alert('" + msg + "');");
			response.getWriter().print("</script>");
			response.getWriter().flush();
		} catch (IOException e) {
		}
	}

	/**
	 * 向客户端输出角本弹出框,并返回到上一个页面
	 * 
	 * @param msg
	 *            要弹出的消息
	 */
	protected void GoBack(String msg) {
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		try {
			response.getWriter().println("<script language='javascript'>");
			response.getWriter().print("alert('" + msg + "');history.back()");
			response.getWriter().print("</script>");
			response.getWriter().flush();
		} catch (IOException e) {
		}
	}

	/**
	 * 向客户端输出角本语句
	 * 
	 * @param str
	 *            要输出的角本内容
	 */
	protected void PostJs(String str) {
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

	protected void PostJs(ArrayList<String> list) {
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		try {
			response.getWriter().print("<script type='text/javascript'>");
			for (int i = 0; i < list.size(); i++) {
				response.getWriter().print(
						list.get(i) == null ? "" : list.get(i));
			}
			response.getWriter().print("</script>");
			response.getWriter().flush();
		} catch (IOException e) {
		}
	}

	protected void PostJs(String[] args) {
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		try {
			response.getWriter().print("<script type='text/javascript'>");
			for (int i = 0; i < args.length; i++) {
				response.getWriter().print((args[i] == null ? "" : args[i]));
			}
			response.getWriter().print("</script>");
			response.getWriter().flush();
		} catch (IOException e) {
		}
	}
	
	public HttpServletRequest getRequest() {
		return request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setServletRequest(HttpServletRequest arg0) {
		this.request = arg0;
	}

	public void setServletContext(ServletContext arg0) {
		this.context = arg0;
	}

	public void setServletResponse(HttpServletResponse arg0) {
		this.response = arg0;
	}

	public void setSession(Map<String, Object> arg0) {
		this.session = arg0;
	}
}
