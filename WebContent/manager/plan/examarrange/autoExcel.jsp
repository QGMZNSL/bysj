<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title></title>
		<link href="<%=request.getContextPath()%>/common/style/style.css"
			rel="stylesheet" type="text/css" />
<script type="text/javascript" language="javascript">
function trim(str){  //删除左右两端的空格 
	return str.replace(/(^\s*)|(\s*$)/g,"");
}

function checkFile(checkForm){
	var strAlert="＊以下原因导致不能提交：\n\n";
	var ii=0;
	var sheet=document.getElementById("sheet").value;
	sheet=trim(sheet);
	if(sheet==""){
		if(ii==1) strAlert=strAlert+"，\n";
		strAlert=strAlert+"“输入导入的Excel工作表名”未填写";
		ii=1;
	}
	var excelFile=document.getElementById("excelFile").value;
	if(excelFile==""){
		if(ii==1) strAlert=strAlert+"，\n";
		strAlert=strAlert+"“选择导入的Excel文件”未选择";
		ii=1;
	}
	if(ii==1){
		alert(strAlert+"。");
		return false;
	}
	else{
		checkForm.action="exam_autoup.do";
	}
}
</script>
</head>
	<body>
		<!-- 页面导航 -->
		<div class="dqwz">
			<span>帮助</span><span class="pageCode">功能编号:${currFunctionId}</span>
			专业计划 > 考试安排 > <c:if test="${menutype=='1'}">全国统考课程考试安排</c:if><c:if test="${menutype=='2'}">非全国统考课程考试安排</c:if>
		</div>
		<!-- 编辑区域 -->
<table border='0' cellpadding='0' cellspacing='0' width='100%'>
		<tr>
		<td height='10px'>
		</td>
		</tr>
		<tr>
		<td align='left' style='font-size:14px;font-weight:bolder;'>
<c:if test="${menutype=='1'}">全国统考课程考试安排</c:if><c:if test="${menutype=='2'}">非全国统考课程考试安排</c:if>
		</td>
		</tr>
		<tr>
		<td height='10px'/>
		</tr>
</table>
<form method='post' name='checkForm' enctype="multipart/form-data" onsubmit='return checkFile(this);'><!--  onsubmit='return checkFile(this);' -->
<table border='0' cellpadding='0' cellspacing='0' width='100%'>
<tr><td align="right" style='height:25px;'>
输入导入的Excel工作表名：</td><td align="left">
<input name="sheet" id="sheet" type="text" class="inputTextL" value='Sheet1'/></td></tr>
<tr><td align="right">
选择导入的Excel文件：</td><td align="left">
<input name="excelFile" id="excelFile" type="file" class="inputText"/>
</td></tr>
<tr><td height='10px' colspan='2'/></tr>
<tr><td align="center" height='50px' colspan='2' valign='middle'>
<input type="hidden" name='menutype' id='menutype' value="${menutype}"/>
<input type="submit" class="inputButton" value="导 入"/>
<input class="inputButton" type="reset"  value="重 置" />
<input class="inputButton" type="button" value="返 回"
		onclick="location.href='<%=request.getContextPath()%>/manager/plan/examarrange/exam_show.do?menutype=${menutype}';" />
</td></tr>
</table>
</form>
	</body>
</html>
