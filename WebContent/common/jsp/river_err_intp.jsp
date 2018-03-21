<%@ page language="java" pageEncoding="GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>RiverInterceptor Error</title>
	</head>

	<body>
		[
		<s:property value="#request.river_intrceptor_validate_fail"/>
		]
		<br />
		<%=request.getAttribute("river_intrceptor_validate_fail")%>
		<br />
		<c:out value="${river_intrceptor_validate_fail}"></c:out>
	</body>
</html>
