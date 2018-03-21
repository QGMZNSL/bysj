<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<HTML>
	<HEAD>
		<TITLE>文件上传</TITLE>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
		<style type="text/css">
body,a,table,div,span,td,th,input,select {
	font: 9pt;
	font-family: "宋体", Verdana, Arial, Helvetica, sans-serif;
}

body {
	padding: 0px;
	margin: 0px
}
</style>		
		<script language="JavaScript" src="dialog/dialog.js"></script>

	</head>
	<body bgcolor=menu onLoad='clos();'>

		<form action="/ACC/wsbm/kjgf/examnet/en_uploadFile.do" method=post name=myform
			enctype="multipart/form-data">
			<input type="file" name="uploadfile" id="uploadfile" size=1
				style="width: 100%">
			<input type="hidden" name="originalfile" value="">
		</form>
		<script language=javascript>

var sAllowExt = "gif,jpg,jpeg,bmp,GIF,JGP,JPEG,BMP";
// 检测上传表单
function CheckUploadForm() {
var filename=document.myform.uploadfile.value;
filename=filename.substring(filename.lastIndexOf(".")+1);

	if (filename!='gif'&&filename!='jpg'&&filename!='jpeg'&&filename!='bmp'&&filename!='GIF'&&filename!='JPG'&&filename!='JPEG'&&filename!='BMP'){
	
		parent.UploadError("提示：\n\n请选择一个有效的文件，\n支持的格式有（"+sAllowExt+"）！");
		return false;
	}
	return true
}

// 提交事件加入检测表单
var oForm = document.myform ;
oForm.attachEvent("onsubmit", CheckUploadForm) ;
if (! oForm.submitUpload) oForm.submitUpload = new Array() ;
oForm.submitUpload[oForm.submitUpload.length] = CheckUploadForm ;
if (! oForm.originalSubmit) {
	oForm.originalSubmit = oForm.submit ;
	oForm.submit = function() {
		if (this.submitUpload) {
			for (var i = 0 ; i < this.submitUpload.length ; i++) {
				this.submitUpload[i]() ;
			}
		}
		this.originalSubmit() ;
	}
}

// 上传表单已装入完成
try {
	parent.UploadLoaded();
}
catch(e){
}

function clos(){
  var f='${f}';
  if(f!=''){    
     parent.UploadSaved(f);
     }else{
     	return;
     }

}


</script>
	</body>
</html>
