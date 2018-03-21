<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>用户信息明细</title>
	<%String rgPath=request.getContextPath();%>
	<link href="<%=rgPath%>/common/style/style.css"
		rel="stylesheet" type="text/css" />
	</head>
<body>
<!--导航---start-->
<div class="dqwz">
<span>帮助</span><span class="pageCode">功能编号:${currFunctionId}</span>${currNavigation}
</div>
<!--导航---end-->
<br/>
<div class="xsxx" style="width:100%">
<br/>
<form name="checkForm" method="post" action="<%=request.getContextPath()%>/manager/camera/pho_changeMess.do">
<table border="0" cellpadding="0" cellspacing="0" width="100%">
<tr>
	<th width="25%">
		证件号码：
	</th>
	<td width="25%">
${sIdno}（ 身份证 ）
<input type='hidden' name='sIdno' value='${sIdno}'/>
	</td>
	<th width="25%">
		出生日期：
	</th>
	<td width="25%">
${messBirthday}
<input type='hidden' name='sBirthday' value='${sBirthday}'/>
	</td>
	<td rowspan="5" width="126" align="center">
		<img src="${idcardnetPhotoPath}" height="150" width="120"/>
	</td>
</tr>
<tr>
	<th>
		姓名：
	</th>
	<td>
${messName}
<input type='hidden' name='sName' value='${sName}'/>
	</td>
	<th>
		性别：
	</th>
	<td>
${messSex}
<input type='hidden' name='sSex' value='${sSex}'/>
	</td>
</tr>
</table>
<div style='height:50px;width:100%;' align='center'>
<br/>
<input class="inputButton" type="submit" value="修 改"/>
<input class="inputButton" type="button" value="返 回"
					onclick="location.href='<%=request.getContextPath()%>/manager/camera/pho_view.do';" />				
</div>
</div>
	</body>
</html>