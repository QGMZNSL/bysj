<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://nbf.river.org/vxds" prefix="v"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>选择专业</title>
		<link href="<%=request.getContextPath()%>/common/style/style.css"
			rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="<%=request.getContextPath() %>/common/js/riverdefine.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/common/js/riverajax.js"></script>
	</head>

	<body>
		<!-- 页面导航 -->
		<div class="dqwz">
			<div  style="margin-right: 10px;">
				<span style="cursor:pointer; " id="helpSpan" onclick="javascript:showHelpDiv('Z11001');">- 帮助 -</span>
				<span class="pageCode">No.Z11001</span>${currNavigation}
			</div>
		</div>
		<jsp:include page="/help/helpJs.jsp"/>
		<iframe name="ifm" id="helpIfm" scrolling="no" frameborder="0" width="100%" style="display: none;" onload="this.height=this.contentWindow.document.body.scrollHeight+13"></iframe>


		<!-- 编辑区域 -->
		<form action="<%=request.getContextPath()%>/sys/core_modifyPwd.do"
			method="post" name="modifyPassword" id="modifyPassword">
			<v:bean clazz="com.sincinfo.zxks.core.sys.LoginAction"
				form="modifyPassword">
				<div class="infoedit">
					<h1>
						修改密码
					</h1>
					
					<dl>
						<dt>
							旧密码：
						</dt>
						<dd>
							<input type="password" class="inputText" name="oldPwd" type="text" size="20" maxlength="20" />
							<v:v input="oldPwd">请输入旧密码！（长度在6-20位）</v:v>
						</dd>
					</dl>
					<dl>
						<dt>
							新密码：
						</dt>
						<dd>
							<input type="password" class="inputText" name="newPwd" type="text" />
							<v:v input="newPwd">请输入新密码！（长度在6-20位）</v:v>
						</dd>
					</dl>
					<dl>
						<dt>
							确认新密码：
						</dt>
						<dd>
							<input type="password" class="inputText" name="newPwdRepeat" type="text" />
							<v:v input="newPwdRepeat">重复输入新密码错误！</v:v>
						</dd>
					</dl>
					<div class="clear"></div>
				</div>
				<div class="button">
					<input class="inputButton" type="submit" value="确 定" />
					<input class="inputButton" type="reset" value="重 置" />
				</div>
			</v:bean>
		</form>
	</body>
</html>
