<%@page import="com.sincinfo.zxks.bean.BaseUser"%>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>帮助</title>

<link href="<%=request.getContextPath()%>/common/style/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/common/js/jquery-1.3.2.min.js"></script>
<script language="javascript">
	function checkAudit(hid,help_id){
		if(confirm("会覆盖当前显示的帮助内容，是否继续？")){
		location.href="<%=request.getContextPath()%>/zk/help/help_audit.do?hid="+hid+"&help_id="+help_id;
		}
	}
</script>
</head>
<body>
	<div id="helpDiv"
		style="z-index: 300;border:1px #BFD0CE solid;background-color:#F5F9FB;">
		<table cellspacing="0" cellpadding="0" style="width: 100%">
			<tr>
				<td style="padding-top: 5px;">
					<div style="float:left; margin-left:16px;font-size: 15px;color: blue;padding-bottom: 10px; ">正在使用的帮助</div>
					<div style="float:right; margin-right:16px;">
						<c:set var="grade" value='<%= ((BaseUser)session.getAttribute("zkOperator")).getUserRole()%>'></c:set>
							<c:if test="${'1' eq  grade}">
								<a style="font-size: 15px;text-decoration: none;" href="<%=request.getContextPath()%>/zk/help/help_edit.do?hid=${helpInfo[0]}&help_id=${help_id}&isCurrent=1" id="edit">编辑</a>
							</c:if>
							&nbsp;&nbsp;&nbsp;&nbsp;
							更新日期：<span id="span1">${helpInfo[2] }</span>
					</div>
				</td>
			</tr>
			<tr>
				<td id="helpFram">
					<table width="100%" cellspacing="0" cellpadding="0">
						<tr>
							<td align="left">
								<div style="margin-left:16px;color:#333333;">
									<div style="font-size:15px;padding-bottom: 10px;">功能说明</div>
									<div id="div1">${helpInfo[3] }</div>
									<div style="font-size:15px;padding-bottom: 10px;">操作说明</div>
									<div id="div2">${helpInfo[4] }</div>
									<div style="font-size:15px;padding-bottom: 10px;">注释说明</div>
									<div id="div3">${helpInfo[5] }</div>
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div id="div4" style="font-size: 15px;color: blue;width:100%;text-align:left;border-top:1px #BFD0CE solid;padding-top:5px; margin-top: 10px;">
									<div style="margin-left:16px;font-size: 15px;color: blue;text-align: left; padding-bottom: 10px;">可选帮助</div>
								</div>
							</td>
						</tr>
					</table>
					
				</td>
			</tr>
			<tr>
				<td>
					<c:forEach var="hhList" items="${helpList}">
					<div id="div4" style="margin-left:16px;margin-right:16px; padding-bottom:5px;text-align:left;border-top:1px #BFD0CE dotted;"></div>
						<table width="100%" cellspacing="0" cellpadding="0">
							<tr>
								<td align="left">
									<div style="float:right;margin-right: 16px;">
											<c:if test="${'1' eq  grade}">
												<a style="font-size: 15px;text-decoration: none;" href="<%=request.getContextPath()%>/zk/help/help_edit.do?hid=${hhList[0]}&help_id=${help_id}" id="edit">编辑</a>&nbsp;&nbsp;
												<a style="font-size: 15px;text-decoration: none;" href="javascript:checkAudit('${hhList[0]}','${hhList[1]}');" id="edit">审核</a>
											</c:if>
											&nbsp;&nbsp;&nbsp;&nbsp;编辑人：<span>${hhList[6] }</span>&nbsp;&nbsp;
											编辑日期：<span>${hhList[2] }</span>
									</div>
									<div style="margin-left:16px; margin-right:16px; color:#333333;">
										<div style="font-size:15px;padding-bottom: 10px;font-weight: bold;">功能说明</div>
										<div>${hhList[3] }</div>
										<div style="font-size:15px;padding-bottom: 10px;font-weight: bold;">操作说明</div>
										<div>${hhList[4] }</div>
										<div style="font-size:15px;padding-bottom: 10px;font-weight: bold;">注释说明</div>
										<div>${hhList[5] }</div>
									</div>
								</td>
							</tr>
						</table>
					</c:forEach>
				</td>
			</tr>
				<tfoot class="list" align="center">
					<tr>
						<td colspan="5">
							<span>${ page.pageInfo}</span>
						</td>
					</tr>
				</tfoot>
		</table>
	</div>
</body>
</html>