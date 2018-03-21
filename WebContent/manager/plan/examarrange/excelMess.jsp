<%@page contentType='text/html; charset=GBK' language='java'%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.0 Transitional//EN' 'http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd'>
<html xmlns='http://www.w3.org/1999/xhtml'>
<head>
<title></title>
<link href="<%=request.getContextPath()%>/common/style/style.css"
			rel="stylesheet" type="text/css" />
</head>
<body>
<div class="dqwz">
			<span>帮助</span><span class="pageCode">功能编号:${currFunctionId}</span>
			专业计划 > 考试安排 > <c:if test="${menutype=='1'}">全国统考课程考试安排</c:if><c:if test="${menutype=='2'}">非全国统考课程考试安排</c:if>
</div>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
<tr>
    <td align="left">
 		<span style='font-size:12px;'>${errortext}</span>
	</td>
</tr>
<tr>
	<td align="center" bgcolor="#FFFFFF" height='40px'>
		<input type="button" class="an" onclick="javascript:location.href='exam_excel.do?menutype=${menutype}'" value="重 新 上 传"/>
	</td>
</tr>
</table>
</body>
</html>