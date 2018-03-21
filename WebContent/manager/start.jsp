<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>在线评测系统</title>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/common/js/jquery-1.3.2.min.js"></script>
		<script type="text/javascript">
		function treeAutoHt() {
			window.frames["mainFrame"].frames["leftFrame"].treeAutoHeight();
		}
		
		$(document).ready( function() {
			$(window).resize( treeAutoHt);
		});
		</script>
	</head>
	<frameset rows="128,*,40" frameborder="no" border="0" framespacing="0">
		<frame
			src="<%=request.getContextPath()%>/menu/menuAndTree_initTopTree.do"
			name="topFrame" scrolling="no" noresize="noresize" id="topFrame"
			title="topFrame" />
		<frame src="<%=request.getContextPath()%>/manager/main.jsp"
			name="mainFrame" id="mainFrame" title="mainFrame" />
		<frame src="<%=request.getContextPath()%>/manager/bottom.html"
			name="bottomFrame" scrolling="no" noresize="noresize"
			id="bottomFrame" title="bottomFrame" />
	</frameset>
</html>
