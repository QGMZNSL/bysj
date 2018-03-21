<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>转考条件设置</title>
		<link href="<%=request.getContextPath()%>/common/style/style.css"
			rel="stylesheet" type="text/css" />
	</head>

	<body>
		<!-- 页面导航 -->
		<div class="dqwz">
			<span>帮助</span>日常维护>>日常管理>>转考条件设置
		</div>

		<!-- 结果集 -->
		<div class="list">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<caption>
				</caption>
				<thead>
					<tr>
						<th>
							序号
						</th>
						<th>
							转考类型
						</th>
						<th>
							本省至少通过课程数
						</th>
						<th>
							开启状态
						</th>
						<th>
							操作
						</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td align="center" width="31">
							1
						</td>
						<td align="center">
							本省转出
						</td>
						<td align="center">
							1
						</td>
						<td align="center">
							已开启
						</td>
						<td align="center">
							<a href="toutServiceSwitch.jsp">设置</a>
						</td>
					</tr>
					<tr>
						<td align="center" width="31">
							2
						</td>
						<td align="center">
							外省转入
						</td>
						<td align="center">
							1
						</td>
						<td align="center">
							未开启
						</td>
						<td align="center">
							<a href="tinServiceSwitch.jsp">设置</a>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</body>
</html>
