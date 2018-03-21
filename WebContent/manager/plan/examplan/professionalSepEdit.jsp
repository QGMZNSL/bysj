<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="v" uri="http://nbf.river.org/vxds"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>专业分类设置</title>
		<link href="<%=request.getContextPath()%>/common/style/style.css"
			rel="stylesheet" type="text/css" />
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/common/js/riverdefine.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/common/js/riverajax.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/common/js/jquery-1.3.2.min.js"></script>			
	</head>

	<body>
		<!-- 页面导航 -->
		<div class="dqwz">
			<div style="margin-right: 10px;">
				<span style="cursor: pointer;" id="helpSpan"
					onclick="javascript:showHelpDiv('Z21003');">- 帮助 -</span>
				<span class="pageCode">No.Z21003</span>${currNavigation}
			</div>
		</div>
		<jsp:include page="/help/helpJs.jsp" />
		<iframe name="ifm" id="helpIfm" scrolling="no" frameborder="0"
			width="100%" style="display: none;"
			onload="this.height=this.contentWindow.document.body.scrollHeight+13"></iframe>

		<!-- 编辑区域 -->
		<form action="<%=request.getContextPath()%>/plan/proseq_Edit.do"	method="post" name="addForm">
			<v:bean	clazz="com.sincinfo.zxks.core.plan.ProseqAction" form="addForm">
			<div class="infoedit">
				<h1>
					修改专业分类
				</h1>
				<dl>
					<dt>
						专业分类代码：
					</dt>
					<dd>
						<input class="inputText" type="text" name="proseq.proPartCode" id="proPartCode" value="${proseq.proPartCode}" />
						<v:v input="proseq.proPartCode" exp="s(1,2)">请输入专业分类代码！(长度不能超过2位)</v:v>
					</dd>
				</dl>
				<dl>
					<dt>
						专业分类名称：
					</dt>
					<dd>
						<input class="inputText" type="text" name="proseq.proPartName" id="proPartName" value="${proseq.proPartName}" />
						<v:v input="proseq.proPartName" exp="name(1,50)|cname(1,50)|ename(1,50)">请输入专业分类名称！(长度不能超过50位，不能输入怪字符)</v:v>
					</dd>
				</dl>
				<div class="clear"></div>
				<dl>
					<dt>
						备注：
					</dt>
					<dd>
						<input class="inputText" type="text" name="proseq.remarks" id="remarks" value="${proseq.remarks}" />
					</dd>
				</dl>
				<div class="clear"></div>
			</div>
			<div class="button">
				<input class="inputButton" type="submit" value="确 定" />
				<input class="inputButton" type="reset"  value="重 置" />
				<input class="inputButton" type="button" value="返 回"
					onclick="location.href='<%=request.getContextPath()%>/plan/proseq_Show.do';" />
			</div>
			</v:bean>
		</form>
	</body>
</html>
