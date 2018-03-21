<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>个人资料修改</title>
		<link href="<%=request.getContextPath()%>/common/style/style.css"
			rel="stylesheet" type="text/css" />
<script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/common/js/calendar1.js"></script>
<script type="text/javascript" language="javascript">
function trim(str){  //删除左右两端的空格 
	return str.replace(/(^\s*)|(\s*$)/g,"");
}

function strLength(str){
	str=str.replace(/[^\x00-\xff]/g, '**');
	return str.length;
}
			
function CheckMess(checkForm){
	var strAlert="＊以下原因导致不能提交:\n\n";
	var ii=0;
	var studIdnum=document.getElementById("studentInfo.studIdnum").value;
	studIdnum=trim(studIdnum);
	if(studIdnum==""){
		if(ii==1) strAlert=strAlert+"，\n";
		strAlert=strAlert+"“证件号码”不能为空";
		ii=1;			
	}
	else if(strLength(studIdnum)>20){
		if(ii==1) strAlert=strAlert+"，\n";
		strAlert=strAlert+"“证件号码”输入过长";
		ii=1;
	}
	var studBirthday=document.getElementById("studentInfo.studBirthday").value;
	if(studBirthday==""){
		if(ii==1) strAlert=strAlert+"，\n";
		strAlert=strAlert+"“出生日期”不能为空";
		ii=1;			
	}
	var studName=document.getElementById("studentInfo.studName").value;
	studName=trim(studName);
	if(studName==""){
		if(ii==1) strAlert=strAlert+"，\n";
		strAlert=strAlert+"“姓名”不能为空";
		ii=1;			
	}
	else if(strLength(studName)>64){
		if(ii==1) strAlert=strAlert+"，\n";
		strAlert=strAlert+"“姓名”输入过长";
		ii=1;
	}
	var studGender=document.getElementById("studentInfo.studGender").value;
	if(studGender==""){
		if(ii==1) strAlert=strAlert+"，\n";
		strAlert=strAlert+"“性别”未选择";
		ii=1;			
	}
	var studFolk=document.getElementById("studentInfo.studFolk").value;
	if(studFolk==""){
		if(ii==1) strAlert=strAlert+"，\n";
		strAlert=strAlert+"“民族”未选择";
		ii=1;			
	}
	var studPolitics=document.getElementById("studentInfo.studPolitics").value;
	if(studPolitics==""){
		if(ii==1) strAlert=strAlert+"，\n";
		strAlert=strAlert+"“政治面貌”未选择";
		ii=1;			
	}
	var studSchoolAge=document.getElementById("studentInfo.studSchoolAge").value;
	if(studSchoolAge==""){
		if(ii==1) strAlert=strAlert+"，\n";
		strAlert=strAlert+"“文化程度”未选择";
		ii=1;			
	}
	var studEmail=document.getElementById("studentInfo.studEmail").value;
	studEmail=trim(studEmail);
	if(studEmail==""){
			if(ii==1) strAlert=strAlert+"，\n";
			strAlert=strAlert+"“电子邮箱”不能为空";
			ii=1;			
	}
	else if (studEmail.match(/^(.+)@(.+)$/)==null){
			if(ii==1) strAlert=strAlert+"，\n";
			strAlert=strAlert+"“电子邮箱”输入格式错误";
			ii=1;
	}
	else if(strLength(studEmail)>200){
			if(ii==1) strAlert=strAlert+"，\n";
			strAlert=strAlert+"“电子邮箱”输入过长";
			ii=1;
	}
	var studOccupation=document.getElementById("studentInfo.studOccupation").value;
	if(studOccupation==""){
		if(ii==1) strAlert=strAlert+"，\n";
		strAlert=strAlert+"“职业”未选择";
		ii=1;			
	}
	var studHukouCharacter=document.getElementById("studentInfo.studHukouCharacter").value;
	if(studHukouCharacter==""){
		if(ii==1) strAlert=strAlert+"，\n";
		strAlert=strAlert+"“户籍”未选择";
		ii=1;			
	}
	var studHukouLocation=document.getElementById("studentInfo.studHukouLocation").value;
	if(studHukouLocation==""){
		if(ii==1) strAlert=strAlert+"，\n";
		strAlert=strAlert+"“户籍所在地”未选择";
		ii=1;			
	}
	var studTelephone=document.getElementById("studentInfo.studTelephone").value;
	studTelephone=trim(studTelephone);
	if(studTelephone==""){
		if(ii==1) strAlert=strAlert+"，\n";
		strAlert=strAlert+"“联系电话”不能为空";
		ii=1;			
	}
	else if(strLength(studTelephone)>64){
		if(ii==1) strAlert=strAlert+"，\n";
		strAlert=strAlert+"“联系电话”输入过长";
		ii=1;
	}
	var studMobilePhone=document.getElementById("studentInfo.studMobilePhone").value;
	studMobilePhone=trim(studMobilePhone);
	if(studMobilePhone==""){
		if(ii==1) strAlert=strAlert+"，\n";
		strAlert=strAlert+"“移动电话”不能为空";
		ii=1;			
	}
	else if(strLength(studMobilePhone)>30){
		if(ii==1) strAlert=strAlert+"，\n";
		strAlert=strAlert+"“移动电话”输入过长";
		ii=1;
	}
	var studPostalCode=document.getElementById("studentInfo.studPostalCode").value;
	studPostalCode=trim(studPostalCode);
	if(studPostalCode==""){
		if(ii==1) strAlert=strAlert+"，\n";
		strAlert=strAlert+"“邮政编码”不能为空";
		ii=1;			
	}
	else if(strLength(studPostalCode)>8){
		if(ii==1) strAlert=strAlert+"，\n";
		strAlert=strAlert+"“邮政编码”输入过长";
		ii=1;
	}
	var studPostalAddress=document.getElementById("studentInfo.studPostalAddress").value;
	studPostalAddress=trim(studPostalAddress);
	if(studPostalAddress==""){
		if(ii==1) strAlert=strAlert+"，\n";
		strAlert=strAlert+"“通讯地址”不能为空";
		ii=1;			
	}
	else if(strLength(studPostalAddress)>256){
		if(ii==1) strAlert=strAlert+"，\n";
		strAlert=strAlert+"“通讯地址”输入过长";
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
		<!--导航---start-->
		<div class="dqwz">
			<span>帮助</span><span class="pageCode">功能编号:${ currFunctionId}</span>${currNavigation}
		</div>
		<!--导航---end-->

		<!--查询条件开始-->
		<div class="clear"></div>
		<!--查询条件end-->
<form method="post" name='checkForm' action="pho_update.do" onsubmit="return CheckMess(this.form);">
<table border="0" cellpadding="0" cellspacing="0" width="100%">
<tr><td align='right' height='25px' width='20%'>证件号码：</td><td align='left' width='80%'>
<input type='text' class='inputText' value='${studentInfo.studIdnum}' name='studentInfo.studIdnum'/>
</td></tr>
<tr><td align='right' height='25px'>出生日期：</td><td align='left'>
<input type='text' class='inputText' value='${studentInfo.studBirthday}' name='studentInfo.studBirthday' readonly onclick="calendar()"/>
</td></tr>
<tr><td align='right' height='25px'>姓名：</td><td align='left'>
<input type='text' class='inputText' value='${studentInfo.studName}' name='studentInfo.studName'/>
</td></tr>
<tr>
<td align='right' height='25px'>性别：</td>
<td align='left'>
<select name='studentInfo.studGender'>
<option value=''>请选择……</option>
<c:forEach var="ft" items="${lGenderName}">
<option value='${ft[0]}'
<c:if test='${ft[0]==studentInfo.studGender}'> selected='selected'</c:if>
>${ft[1]}</option>
</c:forEach>
</select>
</td>
</tr>
<tr><td align='right' height='25px'>民族：</td><td align='left'>
<select name='studentInfo.studFolk'>
<option value='9'>请选择……</option>
<c:forEach var="ft" items="${lStudFolk}">
<option value='${ft[0]}'
<c:if test='${ft[0]==studentInfo.studFolk}'> selected='selected'</c:if>
>${ft[1]}</option>
</c:forEach>
</select>
</td></tr>
<tr><td align='right' height='25px'>政治面貌：</td><td align='left'>
<select name='studentInfo.studPolitics'>
<option value=''>请选择……</option>
<c:forEach var="ft" items="${lStudPolitics}">
<option value='${ft[0]}'
<c:if test='${ft[0]==studentInfo.studPolitics}'> selected='selected'</c:if>
>${ft[1]}</option>
</c:forEach>
</select>
</td></tr>
<tr><td align='right' height='25px'>文化程度：</td><td align='left'>
<select name='studentInfo.studSchoolAge'>
<option value=''>请选择……</option>
<c:forEach var="ft" items="${lStudSchoolAge}">
<option value='${ft[0]}'
<c:if test='${ft[0]==studentInfo.studSchoolAge}'> selected='selected'</c:if>
>${ft[1]}</option>
</c:forEach>
</select>
</td></tr>
<tr><td align='right' height='25px'>电子邮箱：</td><td align='left'>
<input type='text' class='inputText' value='${studentInfo.studEmail}' name='studentInfo.studEmail'/>
</td></tr>
<tr><td align='right' height='25px'>职业：</td><td align='left'>
<select name='studentInfo.studOccupation'>
<option value=''>请选择……</option>
<c:forEach var="ft" items="${lStudOccupation}">
<option value='${ft[0]}'
<c:if test='${ft[0]==studentInfo.studOccupation}'> selected='selected'</c:if>
>${ft[1]}</option>
</c:forEach>
</select>
</td></tr>
<tr><td align='right' height='25px'>户籍：</td><td align='left'>
<select name='studentInfo.studHukouCharacter'>
<option value=''>请选择……</option>
<c:forEach var="ft" items="${lStudHukouCharacter}">
<option value='${ft[0]}'
<c:if test='${ft[0]==studentInfo.studHukouCharacter}'> selected='selected'</c:if>
>${ft[1]}</option>
</c:forEach>
</select>
</td></tr>
<tr><td align='right' height='25px'>户籍所在地：</td><td align='left'>
<select name='studentInfo.studHukouLocation'>
<option value=''>请选择……</option>
<c:forEach var="ft" items="${lStudHukouLocation}">
<option value='${ft[0]}'
<c:if test='${ft[0]==studentInfo.studHukouLocation}'> selected='selected'</c:if>
>${ft[1]}${ft[2]}</option>
</c:forEach>
</select>
</td></tr>
<tr><td align='right' height='25px'>联系电话：</td><td align='left'>
<input type='text' class='inputText' value='${studentInfo.studTelephone}' name='studentInfo.studTelephone'/>
</td></tr>
<tr><td align='right' height='25px'>移动电话：</td><td align='left'>
<input type='text' class='inputText' value='${studentInfo.studMobilePhone}' name='studentInfo.studMobilePhone'/>
</td></tr>
<tr><td align='right' height='25px'>邮政编码：</td><td align='left'>
<input type='text' class='inputText' value='${studentInfo.studPostalCode}' name='studentInfo.studPostalCode'/>
</td></tr>
<tr><td align='right' height='25px'>通讯地址：</td><td align='left'>
<input type='text' class='inputText' value='${studentInfo.studPostalAddress}' name='studentInfo.studPostalAddress'/>
</td></tr>
<tr><td align='center' colspan='2' height='50px'>
<input type='hidden' name='studentInfo.preapplyCode' value='${studentInfo.preapplyCode}'/>
<input type='submit' class="inputButton" value='提 交'/>
<input type='button' class="inputButton" value='取 消' onclick="location.href='<%=request.getContextPath()%>/manager/camera/pho_studShow.do?preapplyCode=${studentInfo.preapplyCode}';"/>
</td></tr>
</table>
</form>
	</body>
</html>