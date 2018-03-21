<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="v" uri="http://nbf.river.org/vxds"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>报考层次设置</title>
		<link href="<%=request.getContextPath()%>/common/style/style.css"
			rel="stylesheet" type="text/css" />
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/common/js/riverdefine.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/common/js/riverajax.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/common/js/jquery-1.3.2.min.js"></script>
		<script type="text/javascript">
		</script>
	</head>

	<body>
		<!-- 页面导航 -->
		<div class="dqwz">
			<div  style="margin-right: 10px;">
				<span style="cursor:pointer; " id="helpSpan" onclick="javascript:showHelpDiv('Z21001');">- 帮助 -</span>
				<span class="pageCode">No.Z21001</span>${currNavigation}
			</div>
		</div>
		<jsp:include page="/help/helpJs.jsp"/>
		<iframe name="ifm" id="helpIfm" scrolling="no" frameborder="0" width="100%" style="display: none;" onload="this.height=this.contentWindow.document.body.scrollHeight+13"></iframe>

		<!-- 编辑区域 -->
		<form action="<%=request.getContextPath()%>/plan/level_Add.do"	method="post" name="addForm">
			<v:bean clazz="com.sincinfo.zxks.core.plan.LevelAction"	form="addForm">
			<div class="infoedit">
				<h1>
					添加报考层次
				</h1>
				<dl>
					<dt>
						层次代码：
					</dt>
					<dd>
						<input class="inputText" type="text" name="planlevel.levelCode" id="levelCode" value="" />
						<v:v input="planlevel.levelCode" exp="s(1)">请输入层次代码（只能输入1位）！</v:v>
					</dd>
				</dl>
				<dl>
					<dt>
						层次名称：
					</dt>
					<dd>
						<input class="inputText" type="text" name="planlevel.levelName" id="levelName" value="" />
						<v:v input="planlevel.levelName" exp="name(1,50)|cname(1,50)|ename(1,50)">请输入层次名称（最长输入10位，不能输入怪字符）！</v:v>
					</dd>
				</dl>
				<div class="clear"></div>
			</div>
			<div class="button">
				<input class="inputButton" type="submit" value="确 定" />
				<input class="inputButton" type="reset"  value="重 置" />
				<input class="inputButton" type="button" value="返 回"
					onclick="location.href='<%=request.getContextPath()%>/plan/level_Show.do'" />
			</div>
		  </v:bean>
		</form>
	</body>
</html>
