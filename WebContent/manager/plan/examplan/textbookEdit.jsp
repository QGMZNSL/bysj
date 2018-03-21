<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="v" uri="http://nbf.river.org/vxds"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>教材设置</title>
		<link href="<%=request.getContextPath()%>/common/style/style.css"
			rel="stylesheet" type="text/css" />
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/common/js/riverdefine.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/common/js/riverajax.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/common/js/jquery-1.3.2.min.js"></script>
		<script type="text/javascript">
		function doSyllabusCode(syllabusCode){
			document.getElementById("textbookName_span").innerHTML=syllabusCode+"-";
		}
		</script>
	</head>

	<body>
		<!-- 页面导航 -->
		<div class="dqwz">
			<div style="margin-right: 10px;">
				<span style="cursor: pointer;" id="helpSpan"
					onclick="javascript:showHelpDiv('Z21006');">- 帮助 -</span>
				<span class="pageCode">No.Z21006</span>${currNavigation}
			</div>
		</div>
		<jsp:include page="/help/helpJs.jsp" />
		<iframe name="ifm" id="helpIfm" scrolling="no" frameborder="0"
			width="100%" style="display: none;"
			onload="this.height=this.contentWindow.document.body.scrollHeight+13"></iframe>

		<!-- 编辑区域 -->
		<form action="<%=request.getContextPath()%>/plan/textbook_Edit.do"	method="post" name="addForm">
			<v:bean	clazz="com.sincinfo.zxks.core.plan.TextbookAction" form="addForm">
			<div class="infoedit">
				<h1>
					修改教材
				</h1>
				<dl>
					<dt>
						课程代码：
					</dt>
					<dd>
						<input class="inputText" type="text" name="textbook.syllabusCode" id="syllabusCode" value="${textbook.syllabusCode}" onchange='doSyllabusCode(this.value)'/>
						<v:v input="textbook.syllabusCode" exp="s(1,10)">请输入课程代码！(不能超过10位)</v:v>
					</dd>
				</dl>
				<dl>
					<dt>
						教材代码：
					</dt>
					<dd>
						<span id='textbookName_span' style='color:#000;'>${textbook.syllabusCode}-</span>
						<input class="inputTextS" type="text" name="textbook.textbookCode" id="textbookCode" value="${textbook.textbookCode}"/>
						<br/><span style='color:#000;'>（此处仅填写教材代码的后半段）</span>
					</dd>
				</dl>
				<dl>
					<dt>
						教材名称：
					</dt>
					<dd>
						<input class="inputText" type="text" name="textbook.textbookName" id="textbookName" value="${textbook.textbookName}" />
						<v:v input="textbook.textbookName" exp="s(1,40)">请输入教材名称！(不能超过40位)</v:v>
					</dd>
				</dl>
				<dl>
					<dt>
						教材主编：
					</dt>
					<dd>
						<input class="inputText" type="text" name="textbook.textbookEditor" id="textbookEditor" value="${textbook.textbookEditor}" />
						<v:v input="textbook.textbookEditor" exp="s(1,20)">请输入教材主编！(不能超过20位)</v:v>
					</dd>
				</dl>
				<dl>
					<dt>
						出版社：
					</dt>
					<dd>
						<input class="inputText" type="text" name="textbook.textbookPublisher" id="textbookPublisher" value="${textbook.textbookPublisher}" />
						<v:v input="textbook.textbookPublisher" exp="s(1,20)">请输入出版社！(不能超过20位)</v:v>
					</dd>
				</dl>
				<dl>
					<dt>
						出版时间：
					</dt>
					<dd>
						<input class="inputText" type="text" name="textbook.publishTime" id="publishTime" value="${textbook.publishTime}" />
						<v:v input="textbook.publishTime" exp="s(1,20)">请输入出版时间！(不能超过20位)</v:v>
					</dd>
				</dl>
				<dl>
					<dt>
						零售价：
					</dt>
					<dd>
						<input class="inputText" type="text" name="textbook.textbookPrice" id="textbookPrice" value="${textbook.textbookPrice}" />
						<v:v input="textbook.textbookPrice" exp="d(0.01,9999.99)">零售价请填写数字，最小值0.01，最大值9999.99！</v:v>
					</dd>
				</dl>
				<dl>
					<dt>
						是否统编教材：
					</dt>
					<dd>
						<select name="textbook.unifiedBook" id="unifiedBook">
						<option value="">请选择……</option>
						<option value='0' <c:if test="${textbook.unifiedBook=='0'}">selected</c:if>>否</option>
						<option value='1' <c:if test="${textbook.unifiedBook=='1'}">selected</c:if>>是</option>
						</select>
						<v:v input="textbook.unifiedBook" exp="">请选择是否统编教材!</v:v>
					</dd>
				</dl>
				<div class="clear"></div>
				<dl>
					<dt>
						备注：
					</dt>
					<dd>
						<input class="inputText" type="text" name="textbook.remarks" id="remarks" value="${textbook.remarks}" />
					</dd>
				</dl>
				<div class="clear"></div>
			</div>
			<div class="button">
				<input class="inputButton" type="submit" value="确 定" />
				<input class="inputButton" type="reset"  value="重 置" />
				<input class="inputButton" type="button" value="返 回"
					onclick="location.href='<%=request.getContextPath()%>/plan/textbook_Show.do';" />
			</div>
			</v:bean>
		</form>
	</body>
</html>
