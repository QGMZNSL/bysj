<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%String rgPath=request.getContextPath();%>
	<head>


	<script type="text/javascript"
			src="<%=request.getContextPath()%>/common/js/jquery-1.3.2.min.js"></script>
			
					<title>照片采集</title>
		<style type="text/css">
<!--
.scbj {
	background-image: url(images/phtobg.jpg);
	background-repeat: repeat-x;
	background-position: left top;
}



.examplebg {
	width: 100%;
	float: left;
	background-image: url(images/examplebg.JPG);
	background-repeat: repeat-x repeat-y;
	background-position: left top;
}

#scan {
	width: 900px;
	height: 110px;
	float: right;
	display: inline;
	background-image: url(images/po_bg.jpg);
	background-repeat: repeat-x;
	position: relative;
}

#scanniu {
	width: 209px;
	height: 54px;
	float: right;
	margin-right: 30px;
	margin-top: 0px;
	text-align: left;
	display: inline;
}

#scanniua {
	width: 184px;
	height: 54px;
	float: right;
	margin-right: 20px;
	margin-top: 20px;
	text-align: left;
	display: inline;
}

#scanniub{
	width: 70px;
	height: 54px;
	text-align: left;
	display: inline;
	font-size: 15pt;
	text-decoration: none;
	position: absolute;
	top: 1px;
	right: 20px;
}

#scanniub1 {width: 184px;
	height: 54px;
	position: absolute;
	top: 1px;
	right: 150px;
}

#scanniub2 {width: 184px;
	height: 54px;
	position: absolute;
	top: 1px;
	right:400px;
}

#scanzt {
	width: 610px;
	height: 20px;
	float: right;
	margin-top: 10px;
	text-align: right;
	line-height: 20px;
	display: inline;
}

#phto {
	width: 946px;
	height: 500px;
	float: left;
	border-top-width: 10px;
	border-top-style: solid;
	border-top-color: #F0F0F0;
	
	padding-top: 10px;
}

#phtoleft {
	width: 626px;
	height: 420px;
	float: left;
	border: 5px solid #DEEEF6;
	margin-top: 10px;
	text-align: center;
}

#phtoflash {
	width: 616px;
	height: 410px;
	margin-top: 5px;
}

#phtoright {
	width: 300px;
	height: 410px;
	float: right;
	margin-top: 10px;
	text-align: center;
	font-size:14px;
}

#phtotp {
	width: 130px;
	height: 150px;
	margin-top: 15px;
	background-color: #CCCCCC;
}

#phtowz {
	width: 305px;
	height: 80px;
	text-align: left;
	margin-top: 10px;
	font-size:14px;
}
#phtowz li{line-height:23px;list-style-type:none}
#scend {
	width: 946px;
	height: 26px;
	line-height: 26px;
	text-align: center;
	border-top-width: 10px;
	border-top-style: solid;
	border-top-color: #F0F0F0;
	margin-top: 10px;
}

#scgb {
	width: 590px;
	height: 20px;
	line-height: 20px;
	float: right;
	text-align: right;
	margin-top: 5px;
	display: inline;
}

#scgb a {
	color: #C60000;
	font-size: 14px;
	text-decoration: none;
}

.h {
	color: #0070EF;
	font-size: 12px;
}

#sysm {
	width: 946px;
	height: auto;
	float: left;
	margin-top: 5px;
	line-height: 22px;
	font-size: 13px;
}
-->
</style>
		<script type="text/JavaScript">
<!--
function MM_swapImgRestore() { //v3.0
  var i,x,a=document.MM_sr; for(i=0;a&&i<a.length&&(x=a[i])&&x.oSrc;i++) x.src=x.oSrc;
}

function MM_preloadImages() { //v3.0
  var d=document; if(d.images){ if(!d.MM_p) d.MM_p=new Array();
    var i,j=d.MM_p.length,a=MM_preloadImages.arguments; for(i=0; i<a.length; i++)
    if (a[i].indexOf("#")!=0){ d.MM_p[j]=new Image; d.MM_p[j++].src=a[i];}}
}

function MM_findObj(n, d) { //v4.01
  var p,i,x;  if(!d) d=document; if((p=n.indexOf("?"))>0&&parent.frames.length) {
    d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);}
  if(!(x=d[n])&&d.all) x=d.all[n]; for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
  for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=MM_findObj(n,d.layers[i].document);
  if(!x && d.getElementById) x=d.getElementById(n); return x;
}

function MM_swapImage() { //v3.0
  var i,j=0,x,a=MM_swapImage.arguments; document.MM_sr=new Array; for(i=0;i<(a.length-2);i+=3)
   if ((x=MM_findObj(a[i]))!=null){document.MM_sr[j++]=x; if(!x.oSrc) x.oSrc=x.src; x.src=a[i+2];}
}
//-->
</script>
		<script type="text/JavaScript">
function closeDiv(){
	
}


function setImg(obj){
	var f=$(obj).val();
    if(f == null || f ==undefined || f == ''){
        return false;
    }
    if(!/\.(?:png|jpg|bmp|gif|PNG|JPG|BMP|GIF)$/.test(f)){
        alertLayel("类型必须是图片(.png|jpg|bmp|gif|PNG|JPG|BMP|GIF)");
        $(obj).val('');
        return false;
    }
    var element = $("#lyurl").val();
    $("#showSelectImage").attr("src",element);
    $("#url").attr("value",element);
	}
function setphoto(obj){
	var f=$(obj).val();
    if(f == null || f ==undefined || f == ''){
        return false;
    }
    if(!/\.(?:png|jpg|bmp|gif|PNG|JPG|BMP|GIF)$/.test(f)){
        alertLayel("类型必须是图片(.png|jpg|bmp|gif|PNG|JPG|BMP|GIF)");
        $(obj).val('');
        return false;
    }
    var element = $("#PhotofileName").val();
    alert(element);
    $("#showPhotoImage").attr("src",element);
	}
function selectFile(){ 
	//触发 文件选择的click事件 
	$("#lyurl").trigger("click"); 
	//其他code如 alert($("#file").attr("value")) 
	} 
function submit(){
    document.uploadimg.submit();
}
	/* 获取 文件的路径 ，用于测试*/
</script> </script>
	</head>
	<body>
		<table width="100%" border="0" cellspacing="0" cellpadding="0"
			class="examplebg">
		</table>
		<table width="100%" border="0" cellspacing="0" cellpadding="0"
			class="scbj">
			<tr>
				<td>
					<table width="946" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td align="left" valign="top">
								<div id="sclogo"></div>
								<div id="scan">
									<img
										src="<%=rgPath%>/manager/camera/photo/po_bg.jpg"
										border="0" />
									<div id="scanniub">
										<a href="javascript:window.openner=null;window.close();"
											class="kfoe" style="text-decoration: none; font-size: 13pt;">×关闭</a>
									</div>
									<div id="scanniub1">
										<a href="#" onclick="toggle('pp1','pp2')"> <img
												src="<%=rgPath%>/manager/camera/photo/phtoZT.jpg"
												name="Image1" width="209" height="54" border="0" id="Image1" />
										</a>
									</div>
									<div id="scanniub2">
										<a href="#" onclick="toggle('pp2','pp1')"><img
												src="<%=rgPath%>/manager/camera/photo/phtoICOO.jpg"
												name="Image2" width="209" height="54" border="0" id="Image2" />
										</a>
									</div>
									<div class="kfoe" id="scanzt" style="font-size: 14px;">
										&nbsp;&nbsp;姓 名：${studentInfo.studName}
										&nbsp;&nbsp;性 别：${studentInfo.genderName}
										&nbsp;&nbsp;证件号：${studentInfo.studIdnum}
									</div>
								</div>
							</td>
						</tr>
						<tr>
							<td align="left" valign="top">
								<div id="phto">
									<div id="phtoleft">
										<div id="phtoflash">
											<table width="100%" border="1" cellpadding="0"
												cellspacing="0">
												<tr id="pp1">
													<td align="center">
														<object
															classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000"
															id="Anki_Photo_s" width="616" height="410"
															codebase="http://fpdownload.macromedia.com/get/flashplayer/current/swflash.cab">
															<param name="movie"
																value="<%=rgPath%>/manager/camera/photo/Anki_Photo.swf" />
															<param name="quality" value="high" />
															<param name="bgcolor" value="#bdb5b5" />
															<param name="allowScriptAccess" value="sameDomain" />
															<param name="FlashVars"
																value="id=${studentInfo.preapplyCode}&name=${studentInfo.studName}&url=${saveUrl }" />
															<embed
																src="<%=rgPath%>/manager/camera/photo/Anki_Photo.swf"
																quality="high" bgcolor="#bdb5b5" width="450"
																height="326" name="Anki_Photo" align="middle"
																FlashVars="id=${studentInfo.preapplyCode}&name=${studentInfo.studName}&url=${saveUrl }"
																play="true" loop="false" quality="high"
																allowScriptAccess="sameDomain"
																type="application/x-shockwave-flash"
																pluginspage="http://www.adobe.com/go/getflashplayer">
															</embed>
														</object>
													</td>
												</tr>
												<tr id="pp2" style="display: none;">
													<td>
														<object
															classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000"
															id="Anki_Photo_f" width="616" height="410"
															codebase="http://fpdownload.macromedia.com/get/flashplayer/current/swflash.cab">
															<param name="movie"
																value="<%=rgPath%>/manager/camera/photo/Anki_Choose.swf" />
															<param name="quality" value="high" />
															<param name="bgcolor" value="#bdb5b5" />
															<param name="allowScriptAccess" value="sameDomain" />
															<param name="FlashVars"
																value="id=${studentInfo.preapplyCode}&name=${studentInfo.studName}&url=${saveUrl}" />
															<embed
																src="<%=rgPath%>/manager/camera/photo/Anki_Choose.swf"
																quality="high" bgcolor="#bdb5b5" width="450"
																height="326" name="Anki_Photo" align="middle"
																FlashVars="id=${studentInfo.preapplyCode}&name=${studentInfo.studName}&url=${saveUrl }"
																play="true" loop="false" quality="high"
																allowScriptAccess="sameDomain"
																type="application/x-shockwave-flash"
																pluginspage="http://www.adobe.com/go/getflashplayer">
															</embed>
														</object>
													</td>
												</tr>
											</table>
										</div>
									</div>
									<div id="phtoright">
										<div style="text-align: left;">
											<strong>&nbsp;服务器端保存的照片：</strong>
										</div>
										<div id="phtotp">
<%-- 											<img width="128" height="150" id="stuPhotoShow" src="${netPhotoPath}"/> --%>
										<img width="128" height="150" id="stuPhotoShow" src="${netPhotoPath}"/>
										</div>
                                        <div id="phtowz">
											<table width="100%" border="0" align="center" cellpadding="0"
												cellspacing="0" class="n1">
												<tr>
													<td align="left"> 
										<strong>照片采集使用说明： </strong>
														<ul>
															<li>1、如果您的视频采集区左上角出现一个近似正方形的小图标，请您安装Flash Player 10（ 
										<a href="http://fpdownload.macromedia.com/get/flashplayer/current/swflash.cab">点击这里下载</a>）以上版本。 
															</li>
															<li>2、安装完成后，请刷新页面，即可进行在线视频照相或上传本地已有照片。 
															</li>
															<li> 
																3、当您点击〖开启本地视频〗按钮，弹出对话框时，请您选择〖允许〗，否则在线视频照相功能仍不能正常使用。 
															</li>
															<li> 
																4、照片上传异常可能由于以下原因：    
															</li>
															<li> 
																①：照片大小小于20kb或者大于40kb。    
															</li>
															<li> 
																②：照片规格不为480px*640px。    
															</li>
															<li> 
																③：照片分辨率不为300dpi。    
															</li>
															
														</ul>
													</td>
												</tr>
											</table>
										</div>
									</div>
									
								</div>
							</td>
						</tr>
						<tr>
							<td align="center" valign="top">
								<div id="scend">
									陕西省考试管理中心 版权所有
								</div>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</body>
	<script type="text/javascript">
		function toggle(a,b){
			//MM_swapImgRestore();
			if (a == "pp1"){
				document.getElementById("Image1").src = "<%=rgPath%>/manager/camera/photo/phtoZ.jpg";
				document.getElementById("Image2").src = "<%=rgPath%>/manager/camera/photo/phtoICO.jpg";
			}else if (a == "pp2"){
				document.getElementById("Image1").src = "<%=rgPath%>/manager/camera/photo/phtoZT.jpg";
				document.getElementById("Image2").src = "<%=rgPath%>/manager/camera/photo/phtoICOO.jpg";
			}
			if (typeof(a)=="string"){
				a = document.getElementById(a);
			}
			if (typeof(b)=="string"){
				b = document.getElementById(b);
			}
			a.style.display="none";
			b.style.display="";
		}
		function refleshPHOTO(){
			var imgsrc = "${netPhotoPath}";
			document.getElementById("stuPhotoShow").src = imgsrc + "?t=" + Math.random();
		}
		//setInterval("refleshPHOTO()",2000);
		function DoReturnFlex(str) {
			refleshPHOTO();
			opener.location.href='<%=rgPath%>/manager/camera/pho_studShow.do?preapplyCode=${studentInfo.preapplyCode}';
    	}
	</script>
</html>