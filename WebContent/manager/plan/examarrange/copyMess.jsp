<%@page contentType='text/html; charset=GBK' language='java'%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.0 Transitional//EN' 'http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd'>
<html xmlns='http://www.w3.org/1999/xhtml'>
<head>
<title></title>
<%String rgPath=request.getContextPath();%>
<link href="<%=rgPath%>/common/style/style.css"
			rel="stylesheet" type="text/css" />
<script type="text/javascript">
function trim(str){  //删除左右两端的空格 
	return str.replace(/(^\s*)|(\s*$)/g,"");
}

function goBack(){
	var examYear=document.getElementById("examYear2").value;
	examYear=trim(examYear);
	location.href='exam_show.do?menutype=2&baseSyllabusTime.applyYear='+examYear;
}

function checkSub(checkForm){
var strAlert="＊以下原因导致不能提交：\n\n";
	var ii=0;
	var examYear1=checkForm.examYear1.value;
	examYear1=trim(examYear1);
	if(examYear1==""){
		if(ii==1) strAlert=strAlert+"，\n";
		strAlert=strAlert+"“考试年（从）”未填写";
		ii=1;
	}
	else{
		if(/^[0-9]*$/.test(examYear1)){
			if(parseInt(examYear1)<1000){
				if(ii==1) strAlert=strAlert+"，\n";
				strAlert=strAlert+"“考试年（从）”输入过小";
				ii=1;
			}
			if(parseInt(examYear1)>3000){
				if(ii==1) strAlert=strAlert+"，\n";
				strAlert=strAlert+"“考试年（从）”输入过大";
				ii=1;
			}
			
		}
		else{
			if(ii==1) strAlert=strAlert+"，\n";
			strAlert=strAlert+"“考试年（从）”请输入阿拉伯数字整数";
			ii=1;
		}
	}
	var examYear2=checkForm.examYear2.value;
	examYear2=trim(examYear2);
	if(examYear2==""){
		if(ii==1) strAlert=strAlert+"，\n";
		strAlert=strAlert+"“考试年（到）”未填写";
		ii=1;
	}
	else{
		if(/^[0-9]*$/.test(examYear2)){
			if(parseInt(examYear2)<1000){
				if(ii==1) strAlert=strAlert+"，\n";
				strAlert=strAlert+"“考试年（到）”输入过小";
				ii=1;
			}
			if(parseInt(examYear2)>3000){
				if(ii==1) strAlert=strAlert+"，\n";
				strAlert=strAlert+"“考试年（到）”输入过大";
				ii=1;
			}
			
		}
		else{
			if(ii==1) strAlert=strAlert+"，\n";
			strAlert=strAlert+"“考试年（到）”请输入阿拉伯数字整数";
			ii=1;
		}
	}
	if(examYear1==examYear2){
		if(ii==1) strAlert=strAlert+"，\n";
		strAlert=strAlert+"“考试年（从）”与“考试年（到）”相同";
		ii=1;
	}
	if(ii==1){
		alert(strAlert+"。");
		return false;
	}
	else{
		if(confirm("确认要复制数据吗？")){
			checkForm.action="exam_hisSave.do";
		}
	}
}
</script>
</head>
<body>
<div class="dqwz">
			<span>帮助</span><span class="pageCode">功能编号:${currFunctionId}</span>
			专业计划 > 考试安排 > 非全国统考课程考试安排
</div>
<form method='post' name='checkForm' onsubmit='return checkSub(this);'>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
<tr>
	<td height='10px' colspan='4'/>
</tr>
<tr>
	<td width="10%">
	</td>
    <td width="20%" align="left">
    	考试年从：
		<input type='text' style='width:30px;' name='examYear1' id='examYear1'/>年数据
	</td>
	<td align="left" width="20%">
		复制到：
		<input type='text' style='width:30px;' name='examYear2' id='examYear2'/>
		年
	</td>
	<td width="50%">
	</td>
</tr>
<tr>
	<td height='10px' colspan='4'/>
</tr>
<tr>
	<td height='25px' colspan='4' style="color:red;">
	注：该功能，是将“陕西省非全国统考课程考试安排”往年的已有的数据复制到你需要的年度。
	</td>
</tr>
<tr>
	<td height='10px' colspan='4'/>
</tr>
<tr>
	<td align="center" bgcolor="#FFFFFF" height='40px' colspan='4'>
		<input type="submit" class="an" value="提 交"/>
		<input type="button" class="an" onclick="goBack()" value="返 回"/>
	</td>
</tr>
</table>
</form>
</body>
</html>