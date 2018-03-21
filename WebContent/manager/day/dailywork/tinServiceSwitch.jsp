<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>转入申请设置</title>
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
		$(document).ready( function() {
			$('#closeGraduateApply').click( function() {
				if ( confirm('该操作将设置申请开启时间段为昨日，确认继续吗？')) {
					var total = new Date();
					var year = total.getYear();
					var month = "0" + (total.getMonth() + 1);
					month = month.substr(month.length-2);
					var date = "0" + total.getDate() - 1;
					date = month.substr(month.length-2);
					$("#startDate").val(year + "-" + month + "-" + date);
					$("#endDate").val(year + "-" + month + "-" + date);
				}
			});
		
			$("#startDate").datepicker($.extend({ dateFormat: 'yyyy-MM-dd', showOn: 'both', buttonImage: '<%=request.getContextPath()%>/common/js/calendarNull.gif', buttonImageOnly: true},$.datepicker.regional['zh-CN']));			
			$("#endDate").datepicker($.extend({ dateFormat: 'yyyy-MM-dd', showOn: 'both', buttonImage: '<%=request.getContextPath()%>/common/js/calendarNull.gif', buttonImageOnly: true},$.datepicker.regional['zh-CN']));
		});
		</script>
	</head>

	<body>
		<!-- 页面导航 -->
		<div class="dqwz">
			<span>帮助</span>日常维护>>日常管理>>转入申请设置
		</div>
		<!--查询条件开始-->
		<form method="post" name="setForm">
			<div class="infoedit">
				<h1>转入条件（在本省考试通过的最少课程数）</h1>
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
				<h1>转入开关</h1>
				<dl>
					<dt>
						转入申请：
					</dt>
					<dd>
						<input type="text" class="inputText inputTextM" value="2011-11-11" id="startDate" />
						-
						<input type="text" class="inputText inputTextM" value="2011-11-12" id="endDate" />
						<input type="button" class="inputButton inputButtonL" value="关闭转入申请" id="closeGraduateApply" />
					</dd>
				</dl>
				<dl>
					<dt>
						转入须知：
					</dt>
					<dd>
						<input type="text" id="noticeText" readonly="readonly"
						name="noticeText" value="点击选择转入须知" style="cursor: hand;"
						onclick="window.open('/ZK_STATUS/status/sctGraduateNotice.jsp','_blank', 'top=150,left=300,height=600,width=500,resizable=0,toolbar=0,menubar=0,location=0');" />
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

