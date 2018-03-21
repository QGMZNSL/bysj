<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="v" uri="http://nbf.river.org/vxds"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>照片采集</title>
		<link href="<%=request.getContextPath()%>/common/style/style.css"
			rel="stylesheet" type="text/css" />
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/common/js/jquery-1.3.2.min.js"></script>
		<script type="text/javascript">
		function qryStudentSubmitChk() {
			if ( $('#preApplyCode').val() == "") {
				alert("请输入考生预报名号！");
				return false;
			}
		
			return true;
		}
		
		function init() {
			$('.disNone').hide();
			<c:if test="${ not empty preApplyCode && empty studentInfo}">
				alert("没有查询到对应的考生信息！");
			</c:if>
		}
		
		$( function() {
			init();
		});
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
<table border="0" cellpadding="0" cellspacing="0" width="100%">
<tr><td align='right' width='20%' height='25px'>姓名：</td><td align='left' width='80%'><input type='text' class='inputText'/></td></tr>
<tr><td align='right' height='25px'>性别：</td><td align='left'>
<select>
<option>请选择……</option>
<option>男</option>
<option>女</option>
</select>
</td></tr>
<tr><td align='right' height='25px'>证件号码：</td><td align='left'>
<input type='text' class='inputText'/>
</td></tr>
<tr><td align='right' height='25px'>新生报名号：</td><td align='left'>
<input type='text' class='inputText'/>
</td></tr>
<tr><td align='right' height='25px'>报考专业：</td><td align='left'>
<input type='text' class='inputText'/>
</td></tr>
<tr><td align='right' height='25px'>报考层次：</td><td align='left'>
<select>
<option>请选择……</option>
<option>本科</option>
<option>专科</option>
</select>
</td></tr>
<tr><td align='right' height='25px'>出生日期：</td><td align='left'>
<input type='text' class='inputText'/>
</td></tr>
<tr><td align='right' height='25px'>民族：</td><td align='left'>
<input type='text' class='inputText'/>
</td></tr>
<tr><td align='right' height='25px'>政治面貌：</td><td align='left'>
<input type='text' class='inputText'/>
</td></tr>
<tr><td align='right' height='25px'>文化程度：</td><td align='left'>
<select>
<option>请选择……</option>
<option>本科</option>
<option>专科</option>
</select>
</td></tr>
<tr><td align='right' height='25px'>电子邮箱：</td><td align='left'>
<input type='text' class='inputText'/>
</td></tr>
<tr><td align='right' height='25px'>职业：</td><td align='left'>
<input type='text' class='inputText'/>
</td></tr>
<tr><td align='right' height='25px'>户籍：</td><td align='left'>
<select>
<option>请选择……</option>
<option>城镇</option>
<option>农村</option>
</select>
</td></tr>
<tr><td align='right' height='25px'>户籍所在地：</td><td align='left'>
<input type='text' class='inputText'/>
</td></tr>
<tr><td align='right' height='25px'>联系电话：</td><td align='left'>
<input type='text' class='inputText'/>
</td></tr>
<tr><td align='right' height='25px'>移动电话：</td><td align='left'>
<input type='text' class='inputText'/>
</td></tr>
<tr><td align='right' height='25px'>邮政编码：</td><td align='left'>
<input type='text' class='inputText'/>
</td></tr>
<tr><td align='right' height='25px'>通讯地址：</td><td align='left'>
<input type='text' class='inputText'/>
</td></tr>
<tr><td align='right' height='25px'>报考市区：</td><td align='left'>
<input type='text' class='inputText'/>
</td></tr>
<tr><td align='right' height='25px'>报考考区：</td><td align='left'>
<input type='text' class='inputText'/>
</td></tr>
<tr><td align='right' height='25px'>考生类别：</td><td align='left'>
<select>
<option>请选择……</option>
<option>社会考生</option>
</select>
</td></tr>
<tr><td align='right' height='25px'>助学单位：</td><td align='left'>
<input type='text' class='inputText'/>
</td></tr>
<tr><td align='center' colspan='2' height='50px'>
<input id="btnread" disabled type='button' class="inputButton" value='提 交'/>
</td></tr>
</table>
<script>
		var n = 60;
		function ReadSecond()
		{
			if (n>0){
				document.getElementById("btnread").value = "提 交" + " (" + n + ") ";
				document.getElementById("btnread").disabled = true;
				n--;
			}else{
				document.getElementById("btnread").value = "提 交";
				document.getElementById("btnread").disabled = false;
			}
		}
		setInterval("ReadSecond()",1000);
		</script>
	</body>
</html>
