<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>二代身份证校验</title>
<link href="<%=request.getContextPath()%>/common/style/style.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="<%=request.getContextPath()%>/common/js/clientfuns.js"></script>
<script type="text/javascript">
var runSign = 0;
var t;
window.onload = function(){
	init();
};


function init(){
	var GT2ICROCX=document.getElementById("GT2ICROCX");
	var v=GT2ICROCX.GetState();
	if(v==0){
		document.getElementById("showInfo").innerHTML = "准备就绪！";
	}
	else if(v==-4){
		document.getElementById("showInfo").innerHTML = "未检测到设备！";
	}
	else if(v<0){
		document.getElementById("showInfo").innerHTML = "设备错误！";
	}
	chkRunSign(runSign);
}
		
function chkRunSign(runSign){
	var readBtn = document.getElementById("readBtn");
	var stopBtn = document.getElementById("stopBtn");
	if ( runSign == 1) {
		readBtn.disabled = true;
		stopBtn.disabled = false;
	} else {
		readBtn.disabled = false;
		stopBtn.disabled = true;
	}
}
		
function startRead() {
	document.getElementById("showInfo").innerHTML = "正在读取，请放上您的二代身份证！";
	runSign = 1;
	chkRunSign(runSign);
	forRead();
}

/* 姓名 Name
 * 性别 Sex
 * 民族 Nation
 * 出生日期 BornDate
 * 地址 Address
 * 身份证号 IDNo
 * 签发机关 SignGov
 * 有效期开始日期 StartDate
 * 有效期结束日期 EndDate
 * 照片文件 PhotoBuffer
 */

function forRead(){
	var GT2ICROCX=document.getElementById("GT2ICROCX");
	alert(GT2ICROCX.ReadCard());
	var reader = GT2ICROCX.ReadCard();
	if ( reader==0){
		// 保存值
		document.getElementById("sName").value = GT2ICROCX.Name;
		document.getElementById("sSex").value = parseInt(GT2ICROCX.Sex);
		document.getElementById("sBirthday").value = GT2ICROCX.Born.substring(0,4) + "-" + GT2ICROCX.Born.substring(4,6) + "-" + GT2ICROCX.Born.substring(6,8);
		document.getElementById("sIdno").value = GT2ICROCX.CardNo;
		document.getElementById("idcardPhotoBuff").value = GT2ICROCX.GetPhotoBuffer();
		document.getElementById("nextsubmit").disabled=false;
		document.getElementById("checkForm").submit();
	} else {
		document.getElementById("showInfo").innerHTML = "正在读取，请放上您的二代身份证！";
		t=setTimeout("startRead()",100);
	}
}
		
function endRead(){
	document.getElementById("showInfo").innerHTML = "准备就绪";
	runSign = 0;
	chkRunSign(runSign);
	clearTimeout(t);
}
</script>
</head>
<body> 
<!--导航---start-->
<div class="dqwz">
	<span>帮助</span><span class="pageCode">功能编号:${ currFunctionId}</span>${currNavigation}
</div>
<!--导航---end-->
<!--显示详细页面----开始-->
<br/>
<div class="xsxx" style="width:100%">
<br/>
<object name="GT2ICROCX" width="102" height="126" classid="CLSID:220C3AD1-5E9D-4B06-870F-E34662E2DFEA" codebase="<%=request.getContextPath()%>/idcard/IdrOcx.cab#version=1,0,1,2"></object>
			<br/><br/>
<form name='checkForm' action="<%=request.getContextPath()%>/manager/camera/pho_checkMess.do"
 method="post">
				<input type="button" id="readBtn" class="inputButton" value="开始读取"  onclick="startRead();"/><!-- onclick="startRead();" -->
				<input type="button" id="stopBtn" class="inputButton" value="停止读取" onclick="endRead();" />
				<input type='hidden' name='ssIdno' id='ssIdno' />
				<input type='hidden' name='ssName' id='ssName'/>
				<input type='hidden' name='ssSex' id='ssSex'/>
				<input type='hidden' name='ssBirthday' id='ssBirthday'/>
				<input type='hidden' name='idcardPhotoBuff' id='idcardPhotoBuff'/>
				<input class="inputButton" type="button" value="手工输入"
					onclick="location.href='pho_hand.do';" />
				<input id='nextsubmit' class="inputButton" type="submit" value="下一步"
					 disabled/>
			<!--显示详细页面----结束-->
		</form>
		<br/><br/>
		</div>
<div id="showInfo" style='color:green;width:100%;' align='left'></div>
		<br/>
	</body>
</html>