<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>信息分类管理</title>
		<link href="<%=request.getContextPath()%>/common/style/style.css"
			rel="stylesheet" type="text/css" />
		<link href="<%=request.getContextPath()%>/common/js/ui.datepicker.css"
			rel="stylesheet" type="text/css" />
		<script type="text/javascript">
			function deleteclass(classid){
				if(confirm("您确定删除此信息分类吗？")){
				 location.href="class_delete.do?classid="+classid;
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
			<div style="margin-right: 10px;">
				<span style="cursor: pointer;" id="helpSpan"
					onclick="javascript:showHelpDiv('Z52001');">- 帮助 -</span>
				<span class="pageCode">No.Z52001</span>${currNavigation}
			</div>
		</div>
		<jsp:include page="/help/helpJs.jsp" />
		<iframe name="ifm" id="helpIfm" scrolling="no" frameborder="0"
			width="100%" style="display: none;"
			onload="this.height=this.contentWindow.document.body.scrollHeight+13"></iframe>
		<!--导航---end-->
		<!--列表样式---表格----开始-->
		<div class="list">
			<div class="button">
				<input class="inputButton" type="button"
					onclick="location.href='class_toadd.do'"
					value="添 加" />
			</div>
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				style="text-align: center">
				<thead>
					<tr>
						<th>
							编号
						</th>
						<th>
							信息分类名称
						</th>
						<th>
							显示
						</th>
						<th>
							操作
						</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="class" items="${allClass}">
						<tr>
							<td width="30">
								${class.classId }
							</td>
							<td>
								${class.className }
							</td>
							<td>
								<c:if test="${class.isShow=='1'}">
									是
								</c:if>
								<c:if test="${class.isShow=='0'}">
									否
								</c:if>
							</td>
							<td>
								<a href='class_toedit.do?classid=${class.classId }'>修改</a>&nbsp;&nbsp;
								<span onclick="deleteclass(${class.classId})" onmouseenter="changecursor(this)"><a>删除</a></span>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<!--列表样式---表格----end-->
	</body>
</html>
