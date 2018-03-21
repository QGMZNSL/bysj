<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>用户权限组管理</title>
		<link href="<%=request.getContextPath()%>/common/style/style.css"
			rel="stylesheet" type="text/css" />
	</head>

	<body>
		<!--当前位置---开始-->
		<div class="dqwz">
			<span>帮助</span>日常管理>>数据维护>>用户权限组管理
		</div>
		<!--当前位置---结束-->
		<!--查询条件开始-->
		<div class="tjcx">
			<form method="post" name="form1">
				<dl>
					<dt>
						权限组名称：
					</dt>
					<dd>
						<select class="inputText inputTextM">
							<option>
								---请选择---
							</option>
							<option>
								名称1
							</option>
							<option>
								名称2
							</option>
						</select>
					</dd>
				</dl>
			</form>
		</div>
		<div class="button">
			<input class="inputButton" type="button" value="查 询" />
			<input class="inputButton" type="button" value="添 加" onclick="location.href='userPowerGroupAdd.jsp'"/>
		</div>
		<div class="clear"></div>
		<!--查询条件end-->
		<!--列表样式---表格----开始-->
		<div class="list">
			<table width="100%" border="0" cellpadding="0" cellspacing="0" style="text-align:center">
				<caption></caption>
				<thead>
					<tr>
						<th>
							序号
						</th>
						<th>
							权限组名称
						</th>									
						<th>
							操作
						</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td width="31">
							1
						</td>
						<td>
							XXXXXX
						</td>						
						<td>
							<a href="userPowerGroupEdit.jsp">修改</a>&nbsp;&nbsp;<a href="">删除</a>
						</td>
					</tr>
					<tr>
						<td width="31">
							2
						</td>
						<td>
							XXXXXX
						</td>						
						<td>
							<a href="userPowerGroupEdit.jsp">修改</a>&nbsp;&nbsp;<a href="">删除</a>
						</td>
					</tr>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="6">
							<span> 首页 上一页 下一页 尾页 <input type="text"
									class="inputText inputPage" value="1" /> <input
									class="inputButton inputButtonS" type="button" value="GO" />
							</span>
						</td>
					</tr>
				</tfoot>
			</table>
		</div>
	</body>
</html>
