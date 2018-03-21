<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>二代身份证信息手工输入</title>
	<link href="<%=request.getContextPath()%>/common/style/style.css"
		rel="stylesheet" type="text/css" />
<script type="text/javascript" language="javascript">
function trim(str){  //删除左右两端的空格 
	return str.replace(/(^\s*)|(\s*$)/g,"");
}

function strLength(str){
	str=str.replace(/[^\x00-\xff]/g, '**');
	return str.length;
}

function checkBSNoIdno(checkForm){
	var strAlert="＊以下原因导致不能提交：\n\n";
	var ii=0;
	var preapplyCode=document.getElementById("baseStudentNoIdno.preapplyCode").value;
	preapplyCode=trim(preapplyCode);
	if(preapplyCode==""){
		if(ii==1) strAlert=strAlert+"，\n";
		strAlert=strAlert+"“新生报名号”不能为空";
		ii=1;
	}
	else if(strLength(preapplyCode)>20){
		if(ii==1) strAlert=strAlert+"，\n";
		strAlert=strAlert+"“新生报名号”输入过长";
		ii=1;
	}
	var noIdnoReason=document.getElementById("baseStudentNoIdno.noIdnoReason").value;
	alert(noIdnoReason);
	noIdnoReason=trim(noIdnoReason);
	if(noIdnoReason==""){
		if(ii==1) strAlert=strAlert+"，\n";
		strAlert=strAlert+"“未采集身份证原因”不能为空";
		ii=1;
	}
	else if(strLength(noIdnoReason)>600){
		if(ii==1) strAlert=strAlert+"，\n";
		strAlert=strAlert+"“未采集身份证原因”输入过长";
		ii=1;
	}
	var yy_document=document.getElementById("baseStudentNoIdno.document").value;
	yy_document=trim(yy_document);
	if(yy_document==""){
		if(ii==1) strAlert=strAlert+"，\n";
		strAlert=strAlert+"“证明材料”不能为空";
		ii=1;
	}
	else if(strLength(yy_document)>600){
		if(ii==1) strAlert=strAlert+"，\n";
		strAlert=strAlert+"“证明材料”输入过长";
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
<br/>
<form method="post" name='checkForm' action="pho_manual.do" onsubmit="return checkBSNoIdno(this.form);">
<table border='0' cellpadding='0' cellspacing='0' width='100%'>
	<tr>
		<td align='right' width='20%'>
			新生报名号：
		</td>
		<td align='left' width='80%'>
			<input class="inputText inputTextM" type="text" name="baseStudentNoIdno.preapplyCode" value="${baseStudentNoIdno.preapplyCode}" />
			<font style='color:red;'>*</font>
		</td>
	</tr>
	<tr>
		<td align='right'>
			未采集身份证原因：
		</td>
		<td align='left'>
			<textarea class="bke" name="noIdnoReason" id="baseStudentNoIdno.noIdnoReason" style='width:500px;height:100px;'>${baseStudentNoIdno.noIdnoReason}</textarea>
			<font style='color:red;'>*</font>
		</td>
	</tr>
	<tr>
		<td align='right'>
			证明材料：
		</td>
		<td align='left'>
			<textarea class="bke" name="document" style='width:500px;height:100px;'>${baseStudentNoIdno.document}</textarea>
			<font style='color:red;'>*</font>
		</td>
	</tr>
</table>
<div class="button" style='width:100%; height:50px;' align='center'>
<br/><input class="inputButton" type="submit" value="提 交"/>
<input class="inputButton" type="button" value="返 回" onclick="location.href='pho_view.do'"/>
</div>
</form>
	</body>
</html>