<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>免考替换文件管理</title>
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
		});
		</script>
		<script type="text/javascript">
			function deleteinfo(inforid){
				if(confirm("你确定删除吗！")){
				 location.href="information_delete.do?inforid="+inforid;
				}
			}
			function changecursor(obj){
				obj.style.cursor="hand";
			}
		</script>
	</head>

	<body>
		<!--导航---start-->
		<div class="dqwz">
			<span>帮助</span>日常维护》信息发布》免考替换文件管理
		</div>
		<!--导航---end-->
		<!--查询条件开始-->
		<form method="post" name="form1" action="message_select.do">
			<div class="tjcx">
				<dl>
					<dt>
						文件编号：
					</dt>
					<dd>
						<input class="inputText inputTextM" type="text" name="title"
							value="${title }" />
					</dd>
				</dl>
				<dl>
					<dt>
						文件名称：
					</dt>
					<dd>
						<input class="inputText inputTextM" type="text" name="title"
							value="${title }" />
					</dd>
				</dl>
				<dl>
					<dt>
						文件分类：
					</dt>
					<dd>
						<select class="inputText" name="classid">
							<option value="">
								---请选择---
							</option>
							<option value="">
								免考
							</option>
							<option value="">
								替换
							</option>
						</select>
					</dd>
				</dl>
				<dl>
					<dt>
						证明材料：
					</dt>
					<dd>
						<input class="inputText inputTextM" type="text" name="title"
							value="${title }" />
					</dd>
				</dl>
				<dl>
					<dt>
						最后修改时间：
					</dt>
					<dd>
						<input class="inputText inputTextM" id="startDate" type="text"
							name="lasttime" value="${lasttime }" />
					</dd>
				</dl>
			</div>
			<div class="button">
				<input class="inputButton" type="submit" value="查 询" />
				<input class="inputButton" type="button"
					onclick="location.href='/ZK_CORE/manager/day/message/fileManagerAdd.jsp;'"
					value="添 加" />
			</div>
		</form>
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
							文件编号
						</th>
						<th>
							文件分类
						</th>
						<th>
							证明材料
						</th>
						<th>
							文件名称
						</th>
						<th>
							增加人
						</th>
						<th>
							最后修改时间
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
							201204
						</td>
						<td>
							免考
						</td>
						<td>
							计算机等级证书
						</td>
						<td><a href="fileQuery.jsp">2003年3号文件</a></td>
						<td>
							张三
						</td>
						<td>
							2012-01-01
						</td>
						<td>
							<a href="fileManagerEdit.jsp">修改</a>&nbsp;&nbsp;
							<span onclick="deleteinfo(${infor.informationId})" onmouseenter="changecursor(this)"><a>删除</a></span>
						</td>
					</tr>
							
				</tbody>
				<tfoot>
					<tr>
						<td colspan="8">
							<span> 首页 上一页 下一页 尾页 <input type="text"
									class="inputText inputPage" value="1" /> <input
									class="inputButton inputButtonS" type="button" value="GO" /> </span>
						</td>
					</tr>
				</tfoot>
			</table>
		</div>
		<!--列表样式---表格----end-->
	</body>
</html>
