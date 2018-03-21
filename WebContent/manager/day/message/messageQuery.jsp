<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>信息发布</title>
		<link href="<%=request.getContextPath()%>/common/style/style.css"
			rel="stylesheet" type="text/css" />
	</head>

	<body>
		<!--导航---start-->
			<div class="dqwz">
			<div style="margin-right: 10px;">
				<span style="cursor: pointer;" id="helpSpan"
					onclick="javascript:showHelpDiv('Z52002');">- 帮助 -</span>
				<span class="pageCode">No.Z52002</span>${currNavigation}
			</div>
		</div>
		<jsp:include page="/help/helpJs.jsp" />
		<iframe name="ifm" id="helpIfm" scrolling="no" frameborder="0"
			width="100%" style="display: none;"
			onload="this.height=this.contentWindow.document.body.scrollHeight+13"></iframe>
		<!--导航---end-->
		<!--通知声明须知----开始-->
		<div class="tz">
			<div style="color:#850B00;font-size:14px;margin-left:30px;width:90%;">
		<c:if test="${article.articleAuditStatus=='2'}">
			审核未通过；原因：${article. articleUnAuditReason}
			
		</c:if>
		<c:if
			test="${article.articleAuditStatus=='0' && not empty article. articleUnAuditReason}">
			
			于${article.articleAuditTime }审核未通过原因：${article. articleUnAuditReason}
		</c:if>
			</div>
			<h1>
				${article.articleTitle }
			</h1>
			<center>
			<div style="align:center">
					发布单位：${article.articleAuthor}
					&nbsp;&nbsp;发布时间：${article.articleAddTime}
					&nbsp;&nbsp;浏览次数：${article.hits }
				</div>
			</center>
			<br/>
			<div style="font-size:14px;">
				${article.articleContent }
			</div>
			<c:if
				test="${not empty requestScope.article.articleFile && (requestScope.article.articleFile!='null')}">
					附件：${requestScope.article.articleFile }
	</c:if>
		</div>		
		<div class="button">
			<input class="inputButton" type="button" value='返 回'
				onclick="history.back()" />
		</div>
		<!--通知声明须知----end-->
	</body>
</html>
