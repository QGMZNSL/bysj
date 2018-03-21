<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://nbf.river.org/vxds" prefix="v"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>欢迎登陆</title>
<link href="css/login.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
function check (form) 
    { 
        var reg = /^\s*|\s*$/g; 
        if (form.username.value.replace(reg,'') == "") 
        { 
            alert ("请输入用户名"); 
            form.username.focus (); 
            return false; 
        } 
        else if (form.password.value.replace(reg,'') == "") 
        { 
            alert ("请输入密码"); 
            form.password.focus (); 
            return false; 
        } 
        else  
        { 
            return true; 
        } 
    } 

</script>
</head>
<body>
<div class="warp">
	<div class="bot_bg">
		<div class="login" align="center">
		<form action="<%=request.getContextPath()%>/sys/core_login.do">
			<table>
				<tr>
					<td height="20"></td>
				</tr>
				<tr>
					<td>
						用户名：<input type="text" id="username" name="username"/>
					</td>
				</tr>
				<tr>
					<td height="20"></td>
				</tr>
				<tr>
					<td>
						密&nbsp;&nbsp;&nbsp;&nbsp;码：<input type="password" id="userpass" name="userpass"/>
					</td>
				</tr>
				<tr>
					<td height="20"></td>
				</tr>
				<tr>
					<td align="center">
						<input type="submit" id="logins" name="login" value="登录"/>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="button" id="singUp" name="singUp" value="注册"/>
					</td>
				</tr>
				<tr>
					<td height="20"></td>
				</tr>
			</table>
			</form>
		</div>
	</div>
</div>
</body>
</html>