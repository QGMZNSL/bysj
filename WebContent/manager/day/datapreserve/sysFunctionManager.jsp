<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>系统功能管理</title>

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
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/common/js/clientfuns.js"></script>
		<link href="<%=request.getContextPath()%>/common/style/style.css"
			rel="stylesheet" type="text/css" />
		<script type="text/javascript">
			function expBtn(){
				var cu = document.getElementById('parentId').value;
				var expUrl = "<%=request.getContextPath()%>/manager/day/message/sysFun_checkSon.do";
				var param = "&parentId="+cu;
				$.ajax({
				     type: "post", // 传值方式
				     url: expUrl,    // 接收后台地址
				     data: "t=" + new Date().getTime() + param,   // 数据
				     async: true,  // 是否异步	
				     success: function( msg) { 
				     	$('#sndT').html(msg);
				     	$('#menuId').val("${menuId}");
				     }
			    });
			}
			function choose(){
				document.getElementsByName('ckb');
			}
			
			function openSw() {
			
				var url = '<%=request.getContextPath()%>/manager/day/message/sysFun_function.do?method=';
				var swiForm = document.getElementById("switchForm");
				var menuId = document.getElementById("menuId").value;
				var parentId = document.getElementById('parentId').value;
				swiForm.action = url + 1 +'&menuId='+menuId +'&parentId='+parentId;
				swiForm.submit();
			}
			
			
			function closeSw() {
				var url = '<%=request.getContextPath()%>/manager/day/message/sysFun_function.do?method=';
				var swiForm = document.getElementById("switchForm");
				var menuId = document.getElementById("menuId").value;
				var parentId =document.getElementById("parentId").value;
				swiForm.action = url + 0 + '&menuId='+menuId +'&parentId='+parentId;
				swiForm.submit();
			}
			
			function init() {
				expBtn();
			}
	</script>
	</head>

	<body onload="init();">
		<form
			action="<%=request.getContextPath()%>/manager/day/message/sysFun_getTreeByMenuId.do"
			method="post">
			<!--当前位置---开始-->
			<div class="dqwz">
				<div style="margin-right: 10px;">
					<span style="cursor: pointer;" id="helpSpan"
						onclick="javascript:showHelpDiv('Z53014');">- 帮助 -</span>
					<span class="pageCode">No.Z53014</span>${currNavigation}
				</div>
			</div>
			<jsp:include page="/help/helpJs.jsp" />
			<iframe name="ifm" id="helpIfm" scrolling="no" frameborder="0"
				width="100%" style="display: none;"
				onload="this.height=this.contentWindow.document.body.scrollHeight+13"></iframe>
			<!--当前位置---结束-->

			<!--查询条件开始-->
			<div class="tjcx">
				<dl>
					<dt>
						一级菜单：
					</dt>
					<dd>
						<select class="inputText inputTextM" onchange="expBtn()"
							id="parentId" name="parentId">
							<option value="${pa.menuName} ">
								--请选择--
							</option>
							<c:forEach items="${parentList}" var="pa">
								<option value="${pa.menuId}"
									<c:if test="${ pa.menuId == parentId}">selected="selected"</c:if>>
									${pa.menuName}
								</option>
							</c:forEach>
						</select>
					</dd>
				</dl>
				<dl>
					<dt>
						二级菜单：
					</dt>
					<dd id="sndT">

					</dd>
				</dl>
			</div>
			<div class="button">
				<input class="inputButton" type="submit" value="查 询" />
			</div>
		</form>

		<div class="clear"></div>
		<!--查询条件end-->


		<div class="list">
			<form action="#" method="post" name="form2" id="switchForm">
				<div class="button">
					<input type="hidden" value="" />
					<input class="inputButton" id="swOpenBtn" type="button" value="开 启"
						onclick="openSw()" />
					<input class="inputButton" id="swCloseBtn" type="button"
						value="关 闭" onclick="closeSw()" />
				</div>
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
					<thead>
						<tr>
							<th width="10%">
								<a href="#" onclick="cboxCheckOpposite('ckb')"> 反选 </a>
							</th>
							<th width="10%" align="center">
								序号
							</th>
							<th width="35%" align="center">
								功能名称
							</th>
							<th width="15%" align="center">
								状态
							</th>
						</tr>
					</thead>

					<tbody>
						<c:forEach items="${littlesonList}" var="litt" varStatus="countN">
							<tr>
								<td align="center">
									<input type="checkbox" name="ckb" value="${litt.nodeId }" />
								</td>
								<td align="center">
									${countN.count }
								</td>
								<td align="left">
									${litt.nodeName}
								</td>
								<td align="center">
									<c:if test="${litt.nodeClose==0}">
										<img src="../../../common/style/right.gif" />
									</c:if>
									<c:if test="${litt.nodeClose==1}">
										<img src="../../../common/style/error.gif" />
									</c:if>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>

			</form>
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tfoot>
					<tr>
						<td colspan="3" align="center">
							${page.pageInfo }
						</td>
					</tr>
				</tfoot>
			</table>
		</div>

		<!--列表样式---表格----end-->
	</body>
</html>
