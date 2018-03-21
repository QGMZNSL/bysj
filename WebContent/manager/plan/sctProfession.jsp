<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>选择专业</title>
		<link href="<%=request.getContextPath()%>/common/style/style.css"
			rel="stylesheet" type="text/css" />
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/common/js/jquery-1.3.2.min.js"></script>
	</head>

	<body>
		<!-- 页面导航 -->
		<div class="dqwz">
			<span>帮助</span><span class="pageCode">功能编号:${ currFunctionId}</span>${currNavigation}
		</div>
		
		<!-- 查询条件 -->
		<form method="post" name="form1" action="<%=request.getContextPath()%>/plan/baseacademy_qryPro.do">
			<div class="tjcx">
			<dl>
				<dt>专业代码：</dt>
				<dd><input class="inputText inputTextM" type="text" name="basepro.proCode" id="proCode" value="${basepro.proCode}" /></dd>
			</dl>
			<dl>
				<dt>专业名称：</dt>
				<dd><input class="inputText inputTextM" type="text" name="basepro.proName" id="proName" value="${basepro.proName}" /></dd>
			</dl> 
		    </div>
		    <div class="button"><input class="inputButton" type="submit" value="查 询" /></div>
		    <div class="clear"></div>
		</form>		

		<!-- 结果集 -->
		<c:if test="${ not empty baseproList}">
		<div class="list">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<caption>
				</caption>
				<thead>
					<tr>
						<th width="26">
							序号
						</th>
						<th>
							专业代码
						</th>
						<th>
							专业名称
						</th>
						<th>
							专业类型
						</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach items="${ baseproList}" var="basepro" varStatus="ctNum">
					<tr>
						<td align="center">
							${ ctNum.count}
						</td>
						<td align="center">
							${ basepro.proCode}
						</td>
						<td align="center">
							<a href="#" onclick='$("#proCode",window.opener.document).val("${basepro.proCode}");$("#proName",window.opener.document).val("${basepro.proName}");window.close();'>${ basepro.proName}</a>
						</td>
						<td align="center">
							${ basepro.proTypeName}&nbsp;
						</td>
					</tr>
					</c:forEach>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="4">
							<span>${ page.pageInfo}</span>
						</td>
					</tr>
				</tfoot>
			</table>
		</div>
		</c:if>
	</body>
</html>
