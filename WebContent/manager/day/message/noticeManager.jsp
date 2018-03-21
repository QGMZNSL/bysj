<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>须知管理</title>
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
			<div style="margin-right: 10px;">
				<span style="cursor: pointer;" id="helpSpan"
					onclick="javascript:showHelpDiv('Z52004');">- 帮助 -</span>
				<span class="pageCode">No.Z52004</span>${currNavigation}
			</div>
		</div>
		<jsp:include page="/help/helpJs.jsp" />
		<iframe name="ifm" id="helpIfm" scrolling="no" frameborder="0"
			width="100%" style="display: none;"
			onload="this.height=this.contentWindow.document.body.scrollHeight+13"></iframe>
		<!--导航---end-->
		<!--列表样式---表格----开始-->
		
		<div class="button">
			<input class="inputButton" type="button"
				onclick="location.href='information_toadd.do';"
				value="添 加" />
		</div>
		<div class="list">
		<table width="100%" border="0" cellpadding="0" cellspacing="0" style="text-align:center">
			<caption></caption>
				<thead>
					<tr>
						<th>
							序号
						</th>
						<th>
							须知分类
						</th>
						<th>
							标题
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
				<c:forEach var="infor" items="${inforList}" varStatus="hh">
					<tr>
						<td width="30">
							${page.pagesize*page.pagenum+ hh.index+1}
						</td>
						<td>
							${infor.className}
						</td>
						<td><a href="information_query.do?inforid=${infor.informationId }">${infor.informationTitle}</a></td>
						<td>
							${infor.informationAddUser}
						</td>
						<td>
							${infor.informationAddTime}
						</td>
						<td>
							<a href='information_toedit.do?inforid=${infor.informationId }'>修改</a>&nbsp;&nbsp;
							<span onclick="deleteinfo(${infor.informationId})" onmouseenter="changecursor(this)"><a>删除</a></span>
						</td>
					</tr>
				
				</c:forEach>				
				</tbody>
				<tfoot>
 				 <tr>
    				<td colspan="6"><span> ${page.pageInfo }</span></td>
  				</tr> 
  				</tfoot>
			</table>
		</div>
		<!--列表样式---表格----end-->
	</body>
</html>
