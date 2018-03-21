<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="v" uri="http://nbf.river.org/vxds"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>二代身份证校验</title>
<link href="<%=request.getContextPath()%>/common/style/style.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="<%=request.getContextPath()%>/idcard/idcard.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/common/js/clientfuns.js"></script>
<script type="text/javascript">
var runSign = 0;
var t;
window.onload = function(){
	init();
};


function init(){
	initial("showInfo");
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
	var reader = readIdCard(runSign, "showInfo");
	if ( reader == null) {
		document.getElementById("showInfo").innerHTML = "正在读取，请放上您的二代身份证！";
		t=setTimeout("startRead()",100);
	} else {
		// 保存值
		document.getElementById("ssName").value = reader.Name;
		document.getElementById("ssSex").value = parseInt(reader.Sex);
		document.getElementById("ssBirthday").value = reader.BornDate.substring(0,4) + "-" + reader.BornDate.substring(4,6) + "-" + reader.BornDate.substring(6,8);
		document.getElementById("ssIdno").value = reader.IDNo;
		document.getElementById("idcardPhotoBuff").value = reader.PhotoBuffer;
		document.getElementById("nextsubmit").disabled=false;
		document.getElementById("checkForm").submit();
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
<object id="OCX" codebase="<%=request.getContextPath() %>/idcard/Termb.cab#version=1,0,0,1"
									classid="clsid:18EE8930-6993-4ADA-B8BB-02BA5820AC94"></object><br/><br/>
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
					  />
			<!--显示详细页面----结束-->
		</form>
		<br/><br/>
<div id="showInfo" style='color:green;width:100%;' align='left'></div>
		<br/>
<div class="informision" align='left'> <strong>浏览器设置方法如下：</strong><br/>
<!-- 
身份证阅读器的连接设置方法如下：
1、将身份证阅读器与电脑连接，安装驱动（详见操作手册）。<br/>
2、将系统访问地址加入可信站点。打开浏览器，点击“工具-Internet选项-安全”，点击“可信站点”→“站点”，将本系统访问地址添加成可信站点；将浏览器“工具”→“Internet选项”→“安全”→“自定义级别...”中“ActiveX控件和插件”下各项设置为“启用”后保存。<br/>
3、打开“身份证校验”页面，如弹出Internet Explorer控件提示“在此页上的ActiveX控件和本页上的其他部分的交互可能不安全。你想允许这种交互吗？”点击“是”，在身份证信息部分会出现一个方框，即为设置成功，可进行身份证信息采集。
 -->
<br/> 
1.将本页面(www.sneac.edu.cn)加入受信任的站点，并去掉"对该区域中的所有站点要求服务器验证(https:)"前面的勾选。 <br/>
2.将受信任的站点安全级别拉到最低。 <br/>
3.在自定义级别里面，将"对未标记为可安全执行脚本的ActiveX控件初始化并执行脚本"设置为启用。 <br/>
4.刷新网页，会出现要求安装空间的对话框，确认安装。 <br/>
5.关闭整个浏览器，重新打开。 <br/>
6.正常情况下，读卡器状态显示“准备就绪”，若无显示，插件安装不正确。 <br/>
7.每刷一下身份证，需要把身份证拿开，支持XP，不支持win7！IE6\7\8均测试通过。 <br/>
</div>
</div>
	</body>
</html>