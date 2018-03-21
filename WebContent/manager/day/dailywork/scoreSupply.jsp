<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>开具成绩证明</title>
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
			 $("#startDate").datepicker($.extend({ dateFormat: 'yyyy-MM-dd', showOn: 'both', buttonImage: '<%=request.getContextPath()%>/common/js/calendarNull.gif', buttonImageOnly: true},$.datepicker.regional['zh-CN']));
			 $("#endDate").datepicker($.extend({ dateFormat: 'yyyy-MM-dd', showOn: 'both', buttonImage: '<%=request.getContextPath()%>/common/js/calendarNull.gif', buttonImageOnly: true},$.datepicker.regional['zh-CN']));
		});
		</script>
	</head>

	<body>
		<div class="dqwz">
			<span>帮助</span>考籍管理>>日常考籍管理>>开具成绩证明
		</div>
		<!--查询条件开始-->
		<div class="tjcx">
			<form method="post" name="form1">
				<dl>
					<dt>
						毕业时间：
					</dt>
					<dd>
						<input type="text" class="inputText inputTextS" id="startDate" />
						-
						<input type="text" class="inputText inputTextS" id="endDate" />
					</dd>
				</dl>
				<dl>
					<dt>
						专业：
					</dt>
					<dd>
						<input class="inputText inputTextM" type="text"
							id="professionCode" readonly="readonly" value="点击选择专业"
							name="professionCode" style="cursor: hand;" />
					</dd>
				</dl>
				<dl>
					<dt>
						准考证号：
					</dt>
					<dd>
						<input class="inputText inputTextM" type="text" />
					</dd>
				</dl>
				<dl>
					<dt>
						证件号码：
					</dt>
					<dd>
						<input class="inputText inputTextM" type="text" />
					</dd>
				</dl>
			</form>
		</div>
		<div class="button">
			<input class="inputButton" type="button" value="查 询" />
			<input class="inputButton inputButtonL" type="button" value="生成成绩证明" /></div>
		<div class="clear"></div>
		<!--查询条件end-->

		<!--列表样式---表格----开始-->
		<div class="list">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<caption></caption>
				<thead>
					<tr>
						<th width="30" align="center">
							反选
						</th>
						<th>
							准考证号
						</th>
						<th>
							姓名
						</th>
						<th>
							证件号码
						</th>
						<th>
							性别
						</th>
						<th>
							毕业证编号
						</th>
						<th>
							专业
						</th>
						<th>
							毕业院校
						</th>
						<th>
							毕业时间
						</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td align="center">
							<input type="checkbox" name="1" />
						</td>
						<td align="center">
						<a target="_blank" href="/ZK_CORE/manager/commoninfo/studentInfo.jsp">51002211</a>	
						</td>
						<td align="center">
							杨森
						</td>
						<td align="center">
							610130198205060228
						</td>
						<td align="center">
							男
						</td>					
						<td align="center">
							610000120503566231
						</td>
						<td align="center">
							计算机应用
						</td>
						<td align="center">
							西安科技大学
						</td>
						<td align="center">
							2006-01
						</td>
					</tr>
					<tr>
						<td align="center">
							<input type="checkbox" name="1" />
						</td>
						<td align="center">
							&nbsp;
						</td>
						<td align="center">
							&nbsp;
						</td>
						<td align="center">
							&nbsp;
						</td>
						<td align="center">
							&nbsp;
						</td>
						<td align="center">
							&nbsp;
						</td>
						<td align="center">
							&nbsp;
						</td>
						<td align="center">
							&nbsp;
						</td>
						<td align="center">
							&nbsp;
						</td>
					</tr>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="9">
							<span>首页 上一页 下一页 尾页<input type="text"
									class="inputText inputPage" value="1"/> <input class="inputButton inputButtonS"
									type="button" value="Go"/>
							</span>
						</td>
					</tr>
				</tfoot>
			</table>
		</div>
		<!--列表样式---表格----end-->
	</body>
</html>
