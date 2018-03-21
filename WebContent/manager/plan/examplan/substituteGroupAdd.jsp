<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="v" uri="http://nbf.river.org/vxds"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>课程顶替组设置</title>
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
			<span>帮助</span><span class="pageCode">功能编号:${ currFunctionId}</span>${currNavigation}
		</div>

		<!-- 编辑区域 -->
		<form action="<%=request.getContextPath()%>/plan/substituteGroup_Add.do" method="post" name="addForm">
			<v:bean clazz="com.sincinfo.zxks.core.plan.SubstituteGroupAction" form="addForm">
			<div class="infoedit">
				<h1>
					添加课程顶替组
				</h1>
				<dl>
					<dt>
						组代码：
					</dt>
					<dd>
						<input class="inputText" type="text" name="substituteGroup.substituteGroupId" id="substituteGroupId" />
						<v:v input="substituteGroup.substituteGroupId" exp="s(10)">请输入组名称！(长度最长10位)</v:v>
					</dd>
				</dl>
				<dl>
					<dt>
						组名称：
					</dt>
					<dd>
						<input class="inputText" type="text" name="substituteGroup.substituteGroupName" id="substituteGroupName" />
						<v:v input="substituteGroup.substituteGroupName" exp="s(20)">请输入组名称！(长度最长20位)</v:v>
					</dd>
				</dl>
				<div class="clear"></div>
			</div>
			<div class="button">
				<input class="inputButton" type="submit" value="确 定" />
				<input class="inputButton" type="reset"  value="重 置" />
				<input class="inputButton" type="button" value="返 回" onclick="history.back();" />
			</div>
		</v:bean>
		</form>
	</body>
</html>
