<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>用户权限组修改</title>
		<link href="<%=request.getContextPath()%>/common/style/style.css"
			rel="stylesheet" type="text/css" />
	</head>

	<body>
		<!--当前位置---开始-->
		<div class="dqwz">
			<span>帮助</span>日常管理>>数据维护>>职位部门管理>>用户权限组修改
		</div>
		<!--当前位置---结束-->
		<!--编辑页面----开始-->
<!--编辑页面----开始-->
		<div id="addArea" style="margin-top: 10px;">
			<form action="#" method="post">
				<div class="dqwz">
					用户权限组添加
				</div>
				<div class="infoedit">
					<dl>
						<dt>
							权限组名称：
						</dt>
						<dd>
							<input class="inputText inputTextM" type="text"  value="XXXXXXX"/>
						</dd>
					</dl>
				</div>
				<div class="clear"></div>
				<div class="infoedit">
					<h1>
						设置权限
					</h1>
					<dl>
						<dt>
							考务管理：
						</dt>
						<dd>
							（<a href="javascript:void(0);">反选</a>）
						</dd>
					</dl>
					<dl>
						<dt>
							&nbsp;
						</dt>
						<dd>
							<table border="0" cellspacing="0" cellpadding="0" class="allTdWidth">
								<tr>
									<td><input type="checkbox"  checked="checked"/>考试管理</td>
									<td><input type="checkbox" />开考专业设置</td>
									<td><input type="checkbox" checked="checked"/>开考课程设置</td>
									<td><input type="checkbox" />开考专业审核</td>
								</tr>
								<tr>
									<td><input type="checkbox" checked="checked"/>当次考区设置</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
								</tr>
							</table>
						</dd>
					</dl>
					<dl>
						<dt>
							考籍管理：
						</dt>
						<dd>
							（<a href="javascript:void(0);">反选</a>）
						</dd>
					</dl>
					<dl>
						<dt>
							&nbsp;
						</dt>
						<dd>
							<table border="0" cellspacing="0" cellpadding="0" class="allTdWidth">
								<tr>
									<td><input type="checkbox" checked="checked"/>考试管理</td>
									<td><input type="checkbox" />开考专业设置</td>
									<td><input type="checkbox" checked="checked"/>开考课程设置</td>
									<td><input type="checkbox" />开考专业审核</td>
								</tr>
								<tr>
									<td><input type="checkbox" />当次考区设置</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
								</tr>
							</table>
						</dd>
					</dl>
					<dl>
						<dt>
							日常操作：
						</dt>
						<dd>
							（<a href="javascript:void(0);">反选</a>）
						</dd>
					</dl>
					<dl>
						<dt>
							&nbsp;
						</dt>
						<dd>
							<table border="0" cellspacing="0" cellpadding="0" class="allTdWidth">
								<tr>
									<td><input type="checkbox" checked="checked"/>考试管理</td>
									<td><input type="checkbox" />开考专业设置</td>
									<td><input type="checkbox" checked="checked"/>开考课程设置</td>
									<td><input type="checkbox" checked="checked"/>开考专业审核</td>
								</tr>
								<tr>
									<td><input type="checkbox" />当次考区设置</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
								</tr>
							</table>
						</dd>
					</dl>
				</div>
				<div class="button">
					<input class="inputButton" type="submit" value="保 存" />
					<input class="inputButton" type="button" id="hideAddArea" value="返 回" onclick="location.href='userPowerGroup.jsp'"/>
				</div>
			</form>
		</div>
<!--编辑页面----end-->

	</body>
</html>
