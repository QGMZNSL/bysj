<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>日志查询</title>
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
		<!--当前位置---开始-->
		<div class="dqwz">
			<span>帮助</span>日常管理>>数据维护>>操作日志管理
		</div>
		<!--当前位置---结束-->
		<!--查询条件开始-->
		<div class="tjcx">
			<form method="post" name="form1">
				<dl>
					<dt>
						登录名：
					</dt>
					<dd>
						<input class="inputText inputTextM" type="text" />
					</dd>
				</dl>
				<dl>
					<dt>
						登录IP：
					</dt>
					<dd>
						<input class="inputText inputTextM" type="text" />
					</dd>
				</dl>
				<dl>
					<dt>
						操作类型：
					</dt>
					<dd>
						<select class="inputText inputTextS">
						    <option>
								全部
							</option>
							<option>
								添加
							</option>
							<option>
								修改
							</option>
							<option>
								删除
							</option>
						</select>
					</dd>
				</dl>
				<dl>
					<dt>
						操作时间：
					</dt>
					<dd>
						<input class="inputText inputTextS" id="startDate" type="text" />
					至<input class="inputText inputTextS" id="endDate" type="text" />
					</dd>
				</dl>
			</form>
		</div>
		<div class="button">
			<input class="inputButton" type="button" value="查 询" />
			<input class="inputButton" type="button" value="导 出" />
		</div>
		<div class="clear"></div>
		<!--查询条件end-->

		<!--列表样式---表格----开始-->
		<div class="list">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<thead>
					<tr>
						<th width="30px">
							序号
						</th>
						<th>
							日志ID
						</th>
						<th>
							登录名
						</th>
						<th>
							操作类型
						</th>
						<th>
							操作页面
						</th>
						<th>
							操作IP
						</th>
						<th>
							操作时间
						</th>
						<th width="60px" >
							详细
						</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td align="center">
							1
						</td>
						<td>
							&nbsp;
						</td>
						<td>
							&nbsp;
						</td>
						<td>
							&nbsp;
						</td>
						<td>
							&nbsp;
						</td>
						<td>
							&nbsp;
						</td>
						<td>
							&nbsp;
						</td>
						<td align="center">
							详细
						</td>
					</tr>
					<tr>
						<td align="center">
							2
						</td>
						<td>
							&nbsp;
						</td>
						<td>
							&nbsp;
						</td>
						<td>
							&nbsp;
						</td>
						<td>
							&nbsp;
						</td>
						<td>
							&nbsp;
						</td>
						<td>
							&nbsp;
						</td>
						<td align="center">
							<a href="LogDetail.jsp">详细</a>
						</td>
					</tr>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="8">
							<span> 首页 上一页 下一页 尾页 <input type="text"
									class="inputText inputPage" value="1" /> <input
									class="inputButton inputButtonS" type="button" value="GO" />
							</span>
						</td>
					</tr>
				</tfoot>
			</table>
		</div>
		<!--列表样式---表格----end-->

	</body>
</html>
