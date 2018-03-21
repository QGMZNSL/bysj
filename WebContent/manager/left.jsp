<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<link href="<%=request.getContextPath()%>/common/style/left.css"
			rel="stylesheet" type="text/css" />
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>在线评测系统</title>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/common/js/jquery-1.3.2.min.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/common/js/ltTree.js"></script>
		<script type="text/javascript">
		function treeAutoHeight() {
			$('#treeArea').css("overflow-y","auto");
			var finalCssHeight = parseInt(window.parent.self.document.body.offsetHeight) - parseInt($('#topMenu').css("height")) - 20;
			$('#treeArea').css("height", finalCssHeight + "px");
		}
		
		$(document).ready( function() {
			init( '<%=request.getContextPath()%>', 'treeArea');
			
			<c:forEach items="${ treeNodes}" var="treeNode" varStatus="countNum">
				add( '${ treeNode.nodeId}', ${ treeNode.parentNode}, '${ treeNode.nodeName}', "contentFrame", '${ treeNode.projectUrl}${ treeNode.linkUrl}');
			</c:forEach>
			
			// 自适应高度
			treeAutoHeight();
			
			// about();			
		});
		</script>
	</head>

	<body>
		<div class="main" id="leftBar">
			<div class="main_left" id="topMenu">
				${ topMenu.menuName}
			</div>

			<div class="nav" id="treeArea" style="overflow-x : hidden;">
			</div>
		</div>
	</body>
</html>