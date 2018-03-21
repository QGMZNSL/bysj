<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>统考课程考试安排新增</title>
		<%String rgPath=request.getContextPath();%>
		<link href="<%=rgPath%>/common/style/style.css"
			rel="stylesheet" type="text/css" />
<script type="text/javascript" language="javascript">
function trim(str){  //删除左右两端的空格 
	return str.replace(/(^\s*)|(\s*$)/g,"");
}

function strLength(str){
	str=str.replace(/[^\x00-\xff]/g, '**');
	return str.length;
}

function checkExam(){
	var strAlert="＊以下原因导致不能提交：\n\n";
	var ii=0;
	var syllabusCode=document.getElementById("baseSyllabusTime.syllabusCode").value;
	syllabusCode=trim(syllabusCode);
	if(syllabusCode==""){
		if(ii==1) strAlert=strAlert+"，\n";
		strAlert=strAlert+"“课程代码”不能为空";
		ii=1;
	}
	else if(strLength(syllabusCode)>10){
		if(ii==1) strAlert=strAlert+"，\n";
		strAlert=strAlert+"“课程代码”输入过长";
		ii=1;
	}
	if(ii==1){
		alert(strAlert+"。");
		return false;
	}
}
</script>
	</head>
	<body>
		<div class="clear"></div>
		<!-- 查询条件 -->
<form method="post" name="checkForm" action='exam_save.do' onsubmit='return checkExam();'>
<table border='0' cellpadding='0' cellspacing='0'>
<tr><td height='25px'>课程代码：</td>
<td><input type='text' name='baseSyllabusTime.syllabusCode' id='baseSyllabusTime.syllabusCode' value='${baseSyllabusTime.syllabusCode}'/></td></tr>
<tr><td height='15px' colspan='2'/></tr>
<tr><td height='25px' colspan='2' align='center'>
<input type='hidden' name='baseSyllabusTime.proCode' id='baseSyllabusTime.proCode' value='${baseSyllabusTime.proCode}'/>
<input type='hidden' name='baseSyllabusTime.examinationTimeCode' id='baseSyllabusTime.examinationTimeCode' value='${baseSyllabusTime.examinationTimeCode}'/>
<input class="inputButton" type='submit' value='提交'/>
</td></tr>
</table>
</form>
<br/>
<br/>

		<!-- 结果集 -->
	</body>
</html>