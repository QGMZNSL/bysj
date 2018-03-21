<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://nbf.river.org/vxds" prefix="v"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>陕西省高等教育自学考试考务考籍管理系统</title>
		<link href="<%=request.getContextPath()%>/common/style/login.css"
			rel="stylesheet" type="text/css" />
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/common/js/riverdefine.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/common/js/riverajax.js"></script>
	</head>
	<body>

		<div class="warp">
			<div class="top">

			</div>
			<div class="bot_bg">
				<img
					src="<%=request.getContextPath()%>/common/style/images/logo.jpg" />
				<div class="login">
					<div class="login-1">
						<div class="login-2">
							<form name="loginFrame" id="loginFrame"
								action="<%=request.getContextPath()%>/sys/core_login.do"
								method="post">
								<v:bean clazz="com.sincinfo.zxks.core.sys.LoginAction"
									form="loginFrame">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td width="80" height="35" align="right">
												<strong>用户名：</strong>
											</td>
											<td>
												<input type="text" name="user.userName" class="inputText"
													size="20" maxlength="20" />
												<v:v input="user.userName" exp="ename(4,20)">请填写用户名！</v:v>
											</td>
										</tr>
										<tr>
											<td height="35" align="right">
												<strong>密 码：</strong>
											</td>
											<td>
												<input type="password" name="user.userPassword"
													class="inputText" size="20" maxlength="20" />
												<v:v input="user.userPassword">请输入密码！</v:v>
											</td>
										</tr>
										<tr>
											<td height="35" align="right">
												<strong>验证码：</strong>
											</td>
											<td>
												<input type="text" name="checkCode" class="inputText s"
													size="6" maxlength="6" style="width: 80px" />
												<v:v input="checkCode">请输入验证码！</v:v>
												<img src="<%=request.getContextPath()%>/imagecode.gif"
													style="vertical-align: middle;" />


											</td>
										</tr>
										<tr>
											<td height="20" align="right">
												&nbsp;
											</td>
											<td>
												&nbsp;
											</td>
										</tr>
										<tr>
											<td height="30" colspan="3" align="center">
												<input name="loginBtn" class="button" type="image"
													id="button"
													src="<%=request.getContextPath()%>/common/style/images/button-1.jpg" />
													&nbsp;&nbsp;
												<img style="cursor: hand;"
													src="<%=request.getContextPath()%>/common/style/images/button-2.jpg"
													onclick="document.getElementById('loginFrame').reset();" />
											</td>
										</tr>
									</table>
								</v:bean>
							</form>
						</div>
					</div>
				</div>
			</div>

		</div>

				<%@ include file="Dibutu.jsp" %>
	</body>
</html>