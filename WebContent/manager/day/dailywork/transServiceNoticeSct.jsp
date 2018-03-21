<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>转考条件设置</title>
		<link href="<%=request.getContextPath()%>/common/style/style.css"
			rel="stylesheet" type="text/css" />
		<link href="<%=request.getContextPath()%>/common/js/ui.datepicker.css"
			rel="stylesheet" type="text/css" />
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/common/js/jquery-1.3.2.min.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/common/js/ui.datepicker-zh-CN.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/common/js/ui.datepicker.js"></script>
		<script type="text/javascript">
		function setNotice( informationId, informationTitle) {
			opener.statusSettingForm.${ noticeTextId}.value = informationId;
			opener.statusSettingForm.${ noticeTextName}.value = informationTitle;
			window.close();
		}
		
		$(document).ready( function() {
		});
		</script>
	</head>

	<body>
		<!--导航---start-->
		<div class="dqwz">
			<span>帮助</span><span class="pageCode">功能编号:${ currFunctionId}</span>${currNavigation}
		</div>
		<!--导航---end-->

		<form method="post" name="setForm" action="<%=request.getContextPath() %>/day/dailywork/staSwch_qryNotices.do">
			<div class="tjcx">
				<dl>
					<dt>
						须知名称：
					</dt>
					<dd>
						<input class="inputText inputTextM" type="text" id="noticeTitle"
							name="noticeTitle" value="${ noticeTitle}" />
						<input type="hidden" name="noticeType" id="noticeType"
							value="${ noticeType}" />
						<input type="hidden" name="noticeTextId" id="noticeTextId"
							value="${ noticeTextId}" />
						<input type="hidden" name="noticeTextName" id="noticeTextName"
							value="${ noticeTextName}" />
					</dd>
				</dl>
			</div>
			<div class="button">
				<input class="inputButton" type="submit" value="查 询" />
			</div>
		</form>
		<div class="clear"></div>

		<!-- 内容 列表  -->
		<c:if test="${ not empty noticeList}">
			<div class="list">
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
					<caption>
						&nbsp;>&nbsp;须知列表
					</caption>
					<thead>
						<tr>
							<th width="30" align="center">
								序号
							</th>
							<th>
								须知标题
							</th>
							<th>
								须知添加时间
							</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${ noticeList}" var="notice" varStatus="ctNum">
							<tr>
								<td align="center">
									&nbsp;${ ctNum.count}
								</td>
								<td align="center">
									&nbsp;
									<a href="#"
										onclick="setNotice('${ notice.informationId}','${ notice.informationTitle}');">${
										notice.informationTitle}</a>
								</td>
								<td align="center">
									&nbsp;${ fn:substring(notice.informationAddTime,0,19)}
								</td>
							</tr>
						</c:forEach>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="3">
								<span>${ page.pageInfo}</span>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
		</c:if>
	</body>
</html>

 