<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>陕西省高等教育自学考试考务考籍管理系统</title>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/common/js/jquery-1.3.2.min.js"></script>
	</head>
	<frameset cols="230,*" frameborder="no" border="0" framespacing="0">
		<frame src="<%=request.getContextPath() %>/menu/menuAndTree_initLeftTree.do?topMenu.menuId=1" name="leftFrame" scrolling="no"
			noresize="noresize" id="leftFrame" />
		<frame src="<%=request.getContextPath() %>/manager/front/welcome.jsp" name="contentFrame" id="contentFrame"/>
	</frameset>
</html>
