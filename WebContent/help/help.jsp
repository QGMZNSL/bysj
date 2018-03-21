<%@page import="com.sincinfo.zxks.bean.BaseUser"%>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>帮助</title>

<script type="text/javascript" src="<%=request.getContextPath()%>/common/js/jquery-1.3.2.min.js"></script>
<script language="javascript">
</script>
</head>
<body>
	<div id="helpDiv" style="border:1px #BFD0CE solid;background-color:#F5F9FB;">
		<table cellspacing="0" cellpadding="0" style="width: 100%">
			<tr>
				<td align="right" style="width:100%;height: 24px;">
					<div style="float:right;margin-right:16px;">
						<c:set var="grade" value='<%= ((BaseUser)session.getAttribute("zkOperator")).getUserRole()%>'></c:set>
						<c:choose>
							<c:when test="${'1' eq  grade}">
								<a style="font-size: 15px; text-decoration: none;" href="<%=request.getContextPath()%>/zk/help/help_getHList.do?help_id=${help_id}">
								审阅(<c:choose><c:when test="${count eq ''}">0</c:when><c:otherwise>${count}</c:otherwise></c:choose>)</a>
							</c:when>
							<c:otherwise>
								<a style="font-size: 15px;text-decoration: none;" href="<%=request.getContextPath()%>/zk/help/help_edit.do?hid=${helpInfo[0]}&help_id=${help_id}">编辑</a>
							</c:otherwise>
						</c:choose>
							&nbsp;&nbsp;&nbsp;&nbsp;
						更新日期：<span id="span1">${helpInfo[2] }</span></div>
					<div>
				</td>
			</tr>
			<tr>
				<td id="helpFram">
					<table width="100%" cellspacing="0" cellpadding="0">
						<tr>
							<td align="left">
								<div style="margin-left:16px;">
									<div style="font-size:15px;">功能说明</div>
									<div id="div1" style="font-size:12px;">${helpInfo[3] }</div>
									<div style="font-size:15px;">操作说明</div>
									<div id="div2" style="font-size:12px;">${helpInfo[4] }</div>
									<div style="font-size:15px;">注释说明</div>
									<div id="div3" style="font-size:12px;">${helpInfo[5] }</div>
								</div>
								<div id="div4" style="width:100%;border-bottom:1px #F5F9FB dotted;"></div>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</div>
</body>
</html>