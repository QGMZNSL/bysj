/**
 * Copyright(c) 2012 西安云海信息技术有限责任公司 All Rights Reserved<br>
 * @Title: com.sincinfo.zxks.core.sys.LoginAction.java<br>
 * @Description: 自考系统管理端登录 <br>
 * <br>
 * @author litian<br>
 * @date Jan 18, 2012 8:47:21 AM 
 * @version V1.0   
 */
package com.sincinfo.zxks.core.sys;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.river.nbf.validate.annotation.Validate;

import com.bysj.dao.loginDao;
import com.sincinfo.zxks.bean.BaseUser;
import com.sincinfo.zxks.bean.UserBean;
import com.sincinfo.zxks.common.Constants;
import com.sincinfo.zxks.common.SessionMap;
import com.sincinfo.zxks.common.action.WebActionSupport;
import com.sincinfo.zxks.common.db.content;
import com.sincinfo.zxks.common.tree.MenuTreeDbService;
import com.sincinfo.zxks.common.tree.TreeNode;
import com.sincinfo.zxks.common.util.MD5;
import com.sincinfo.zxks.common.util.StringTool;
import com.sincinfo.zxks.zxksdbs.SysDbService;

/**
 * @ClassName: LoginAction
 * @Description: 自考系统管理端登录 <br>
 *               <br>
 * @author litian
 * @date Jan 18, 2012 8:47:21 AM<br>
 * 
 */
public class LoginAction extends WebActionSupport {

	/**
	 * @Fields serialVersionUID :
	 */
	private static final long serialVersionUID = 7814215875855771204L;

	// 登录信息
	private BaseUser user = null;
	 private String username;
	 private String userpass;

	// 验证码
	private String checkCode = null;

	// 修改密码
	private String oldPwd = null;
	private String newPwd = null;
	private String newPwdRepeat = null;

	/**
	 * 获取验证码
	 */
	private String getLoginCheckCode(String checkCodeKey) {
		Object cck = this.session.get(checkCodeKey);
		return cck == null ? null : cck.toString();
	}

	/**
	 * 获取session中的操作员
	 * 
	 * @return BaseUser
	 */
	private BaseUser getSessionOperator() {
		Object userObj = this.session.get(Constants.ZK_OPERATOR);
		BaseUser optUser = userObj == null ? new BaseUser()
				: (BaseUser) userObj;
		return optUser;
	}


	/**
	 * 登录
	 * @throws Exception 
	 */
	public String login(){
		loginDao ld = new loginDao();
		List<UserBean> userlist = new ArrayList<UserBean>();
		try {
			userlist = ld.ChekcLogin(username,userpass);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		if(userlist.size()==0){
	        return "error";
		}else{
			return "strat";
		}
	}

	/**
	 * 修改用户密码
	 */
	public void modifyPwd() {
		String alertString = "保存失败！";

		// 获取用户
		BaseUser baseUser = getSessionOperator();

		// 判断密码与旧密码是否一致
		if (baseUser.getUserPassword().equals(MD5.getMD5String(oldPwd))) {
			baseUser.setUserPassword(MD5.getMD5String(newPwd));

			// 保存，并构成提示语句
			SysDbService sysDb = new SysDbService();
			if (sysDb.saveNewPwd(baseUser))
				alertString = "保存成功！";

		} else {
			alertString = "您输入的旧密码不正确，请重新输入！";
		}

		// 提示返回
		String messageJs = String.format("alert('%1$s');", alertString);
		String locationJs = String.format(
				"location.href='%1$s/manager/front/modifySelf.jsp';", request
						.getContextPath());
		this.PostJs(messageJs + locationJs);
	}

	/**
	 * 跨包登录传参
	 */
	public void loginAcross() {
		// 生成随机字符串（用户名+密码md5+年月日时分秒）取md5，然后倒序
		BaseUser optUser = getCOperUser();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
		String fullString = optUser.getUserName() + optUser.getUserPassword()
				+ sdf.format(new Date());
		String acrossMd5 = MD5.getMD5String(fullString);
		SysDbService sysDb = new SysDbService();
		sysDb.loginAcross(optUser.getUserName(), acrossMd5);
		acrossMd5 = optUser.getUserName() + "_" + acrossMd5;

		// 查询菜单节点对象
		String nodeId = request.getParameter("nid");
		MenuTreeDbService mtds = new MenuTreeDbService();
		TreeNode tn = mtds.getTreeNode(nodeId);
		String locationJs = null;
		if ("needsToDoAutoQuery".equals(StringTool.trim(request .getParameter("needsToDo")))) {
			locationJs = String.format(
					"location.href='%1$s%2$s&%3$s=%4$s&%5$s=%6$s';", 
					tn .getProjectUrl(), tn.getLinkUrl(),
					Constants.LOGIN_ACROSS_KEY, acrossMd5, "needsToDo", "needsToDoAutoQuery");
		} else {
			locationJs = String.format("location.href='%1$s%2$s&%3$s=%4$s';",
					tn.getProjectUrl(), tn.getLinkUrl(),
					Constants.LOGIN_ACROSS_KEY, acrossMd5);
		}

		this.PostJs(locationJs);
	}

	/**
	 * ajax调用退出系统
	 */
	public void logout() {
		this.request.getSession().invalidate();
		PrintWriter pw = null;
		try {
			response.setContentType("text/html;charset=utf-8");
			pw = response.getWriter();
			pw.write("succeed in exit");
			pw.flush();
		} catch (Exception e) {
		} finally {
			if (pw != null)
				pw.close();
		}
	}
	
	/*---------get/set---------*/
	public BaseUser getUser() {
		return user;
	}

	public void setUser(BaseUser user) {
		this.user = user;
	}

	@Validate(exp = "null")
	public String getCheckCode() {
		return checkCode;
	}

	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}

	@Validate(exp = "_null&password(6,20)")
	public String getOldPwd() {
		return oldPwd;
	}

	public void setOldPwd(String oldPwd) {
		this.oldPwd = oldPwd;
	}

	@Validate(exp = "_null&password(6,20)")
	public String getNewPwd() {
		return newPwd;
	}

	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}

	@Validate(exp = "_null&se(*newPwd)")
	public String getNewPwdRepeat() {
		return newPwdRepeat;
	}

	public void setNewPwdRepeat(String newPwdRepeat) {
		this.newPwdRepeat = newPwdRepeat;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserpass() {
		return userpass;
	}

	public void setUserpass(String userpass) {
		this.userpass = userpass;
	}
	

}
