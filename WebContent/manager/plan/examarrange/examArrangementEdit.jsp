<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>教材设置</title>
		<link href="<%=request.getContextPath()%>/common/style/style.css"
			rel="stylesheet" type="text/css" />
	</head>
<%
int menutype=Integer.parseInt(request.getParameter("menutype"));
%>
	<body>
		<!-- 页面导航 -->
		<div class="dqwz">
			<span>帮助</span><span class="pageCode">功能编号:${ currFunctionId}</span>
			专业计划 > 考试安排 > <%
if(menutype==1) out.print("全国统考课程考试安排");
else if(menutype==2) out.print("非全国统考课程考试安排");
%>
		</div>
		<!-- 编辑区域 -->
		<form action="<%=request.getContextPath()%>/plan/textbook_Edit.do"	method="post" name="addForm">
		<table border='0' cellpadding='0' cellspacing='0'>
		<tr>
		<td colspan='2' height='10px'>
		</td>
		</tr>
		<tr>
		<td colspan='2' align='left' style='font-size:14px;font-weight:bolder;'>
<%
if(menutype==1) out.print("全国统考课程考试安排");
else if(menutype==2) out.print("非全国统考课程考试安排");
%>
		</td>
		</tr>
		<tr>
		<td colspan='2' height='10px'>
		</td>
		</tr>
		<tr>
		<td style="width:20%;" align='right'>
		年度：
		</td>
		<td style="width:80%;" align='left'>
		<select>
		<option>请选择……</option>
		</select>
		</td>
		</tr>
		<tr>
		<td align='right'>
		考试批次：
		</td>
		<td align='left'>
		<select>
		<option>请选择……</option>
		</select>
		</td>
		</tr>
		<tr>
		<td align='right'>
		专业代码：
		</td>
		<td align='left'>
		<input class="inputText" type="text" name="textbook.textbookEditor" id="textbookEditor" value="${textbook.textbookEditor}" />
		</td>
		</tr>
		<tr>
		<td align='right'>
		课程代码：
		</td>
		<td align='left'>
		<input class="inputText" type="text" name="textbook.textbookEditor" id="textbookEditor" value="${textbook.textbookEditor}" />
		</td>
		</tr>
		<tr>
		<td align='right'>
		考试日期：
		</td>
		<td align='left'>
		<input class="inputText" type="text" name="textbook.textbookEditor" id="textbookEditor" value="${textbook.textbookEditor}" />
		</td>
		</tr>
		<tr>
		<td align='right'>
		考试时间段：
		</td>
		<td align='left'>
		<select>
		<option>请选择……</option>
		<option>上午</option>
		<option>下午</option>
		</select>
		</td>
		</tr>
		<tr>
		<td colspan='2' height='15px'/>
		</tr>
		<tr>
		<td align='center' colspan='2'>
		<input class="inputButton" type="submit" value="确 定" />
		<input class="inputButton" type="reset"  value="重 置" />
		<input class="inputButton" type="button" value="返 回"
		onclick="location.href='<%=request.getContextPath()%>/manager/plan/examarrange/examArrangement.jsp?menutype=<%=menutype%>';" />
		</td>
		</tr>
		</table>
		</form>
	</body>
</html>
