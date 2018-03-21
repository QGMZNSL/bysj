<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>二代身份证信息手工查询</title>
	<link href="<%=request.getContextPath()%>/common/style/style.css"
		rel="stylesheet" type="text/css" />
	</head>
<body>
<!--导航---start-->
<div class="dqwz">
<span>帮助</span><span class="pageCode">功能编号:${ currFunctionId}</span>${currNavigation}
</div>
<!--导航---end-->
<br/>
<form method="post" name='checkForm' action="pho_dosearch.do">
<table border='0' cellpadding='0' cellspacing='0' width='100%'>
	<tr>
		<td align='right' width='20%'>
			新生报名号：
		</td>
		<td align='left' width='80%'>
			<input class="inputText inputTextM" type="text" name="preapplyCode" value="${preapplyCode}" />
		</td>
	</tr>
</table>
<div class="button" style='width:100%; height:50px;' align='center'>
<br/>
<input class="inputButton" type="submit" value="查 询"/>
<input class="inputButton" type="button" value="返 回" onclick="location.href='pho_view.do'"/>
</div>
</form>
	</body>
</html>