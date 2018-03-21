<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>转出申请设置</title>
		<link href="<%=request.getContextPath()%>/common/style/style.css"
			rel="stylesheet" type="text/css" />
		<link href="<%=request.getContextPath()%>/common/js/ui.datepicker.css"
			rel="stylesheet" type="text/css" />
	</head>

	<body>
		<!-- 页面导航 -->
		<div class="dqwz">
			<span>帮助</span>日常维护>>日常管理>>转出申请设置
		</div>
		<!--查询条件开始-->
		<form method="post" name="setForm">
			<div class="infoedit">
				<h1>转出条件（在本省考试通过的最少课程数）</h1>
				<dl>
					<dt>
						课程数：
					</dt>
					<dd>
						<input type="text" class="inputText inputTextM" value="1" />
					</dd>
				</dl>
			</div>
			<div class="clear"></div>
			<div class="infoedit">
				<h1>转出须知</h1>
				<dl>
					<dt>
						转出须知：
					</dt>
					<dd>
						<input type="text" id="noticeText" readonly="readonly"
						name="noticeText" value="点击选择转出须知" style="cursor: hand;"
						onclick="window.open('/ZK_STATUS/status/sctGraduateNotice.jsp','_blank', 'top=150,left=300,height=600,width=500,resizable=0,toolbar=0,menubar=0,location=0');" />
					</dd>
				</dl>
				<dl>
					<dt>开启：</dt>
					<dd><input type="radio" name="radio" value="radio"  checked="checked"/>是
						<input type="radio" name="radio" value="radio" />否
					</dd>
				</dl>
			</div>
			<div class="button">
				<input class="inputButton" type="button" value="保 存" />
				<input class="inputButton" type="button" value="返 回" onclick="location.href='toutAddtinCondition.jsp'"/>
			</div>
		</form>

	</body>
</html>

