<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>统考课程考试安排修改</title>
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
	var countryProvince=document.getElementById("baseSyllabusTime.countryProvince").value;
	countryProvince=trim(countryProvince);
	if(countryProvince==""){
		if(ii==1) strAlert=strAlert+"，\n";
		strAlert=strAlert+"“课程级别”未选择";
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
<form method="post" name="checkForm" action='exam_update.do' onsubmit='return checkExam();'>
<table border='0' cellpadding='0' cellspacing='0'>
<tr><td height='25px'>课程级别：</td><td>
<select name='baseSyllabusTime.countryProvince'>
<option value="">请选择……</option>
<option value="1" <c:if test="${baseSyllabusTime.countryProvince=='1'}">selected</c:if>>全国统考课程</option>
<option value="2" <c:if test="${baseSyllabusTime.countryProvince=='2'}">selected</c:if>>非全国统考课程</option>
</select>
</td></tr>
<tr><td height='25px' colspan='2' align='center'>
<input type='hidden' name='menutype' value='${baseSyllabusTime.countryProvince}'/>
<input type='hidden' name='baseSyllabusTime.syllabusCode' value='${baseSyllabusTime.syllabusCode}'/>
<input type='hidden' name='baseSyllabusTime.proCode' value='${baseSyllabusTime.proCode}'/>
<input type='hidden' name='baseSyllabusTime.examinationTimeCode' value='${baseSyllabusTime.examinationTimeCode}'/>
<input class="inputButton" type='submit' value='提交'/>
</td></tr>
</table>
</form>
		<!-- 结果集 -->
	</body>
</html>