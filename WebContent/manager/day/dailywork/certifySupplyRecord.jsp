<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>补办学历证明记录</title>
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
			<span>帮助</span>日常维护>>日常管理>>补办学历证明记录
		</div>
		<!--查询条件开始-->
		<div class="tjcx">
			<form method="post" name="form1">
				<dl>
					<dt>
						补办证明类型：
					</dt>
					<dd>
						<select class="inputText inputTextM">
							<option>
								成绩证明
							</option>
							<option>
								学历证明
							</option>
						</select>
					</dd>
				</dl>
				<dl>
					<dt>
						补办时间：
					</dt>
					<dd>
						<input type="text" class="inputText inputTextS" id="startDate" />
						-
						<input type="text" class="inputText inputTextS" id="endDate" />
					</dd>
				</dl>
				<dl>
					<dt>
						报考专业：
					</dt>
					<dd>
						<select class="inputText inputTextM">
							<option>
								0256-企业管理
							</option>
							<option>
								0232-计算机信息技术
							</option>
						</select>
					</dd>
				</dl>
				<dl>
					<dt>
						登记人：
					</dt>
					<dd>
						<input class="inputText inputTextM" type="text" />
					</dd>
				</dl>
				
			</form>
		</div>
		<div class="button">
			<input class="inputButton" type="button" value="查 询" />
			<input class="inputButton" type="button" value="导　出" />
		</div>
		<div class="clear"></div>
		<!--查询条件end-->

		<!--列表样式---表格----开始-->
		<div class="list">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<caption></caption>
				<thead>
					<tr>
						<th width="30" align="center">
							序号
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
							报考专业
						</th>
						<th>
							补办证明类型
						</th>
						<th>
							毕业证编号
						</th>
						<th>
							补办时间
						</th>
						<th>
							登记人
						</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td width="30" align="center">
							1
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
							0232-企业管理
						</td>					
						<td align="center">
							学历证明
						</td>
						<td align="center">
							61002451200120
						</td>
						<td align="center">
							2010-12-20
						</td>
						<td align="center">
							俞飞鸿
						</td>
					</tr>
					<tr>
						<td width="30" align="center">
							2
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
									class="inputText inputPage" value="1"> <input class="inputButton inputButtonS"
									type="button" value="Go" />
						</span>
						</td>
					</tr>
				</tfoot>
			</table>
		</div>
		<!--列表样式---表格----end-->
	</body>
</html>
