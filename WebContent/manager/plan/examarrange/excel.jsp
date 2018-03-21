<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
<%String rgPath=request.getContextPath();%>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title></title>
		<link href="<%=request.getContextPath()%>/common/style/style.css"
			rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=rgPath%>/manager/plan/examarrange/findTime.js"></script>
<script type="text/javascript" language="javascript">
function trim(str){  //删除左右两端的空格 
	return str.replace(/(^\s*)|(\s*$)/g,"");
}

function checkFile(checkForm){
	var strAlert="＊以下原因导致不能提交：\n\n";
	var ii=0;
	var examYear=document.getElementById("examYear").value;
	examYear=trim(examYear);
	if(examYear==""){
		if(ii==1) strAlert=strAlert+"，\n";
		strAlert=strAlert+"“考试年”未填写";
		ii=1;
	}
	else{
		if(/^[0-9]*$/.test(examYear)){
			if(parseInt(examYear)<1000){
				if(ii==1) strAlert=strAlert+"，\n";
				strAlert=strAlert+"“考试年”输入过小";
				ii=1;
			}
			if(parseInt(examYear)>3000){
				if(ii==1) strAlert=strAlert+"，\n";
				strAlert=strAlert+"“考试年”输入过大";
				ii=1;
			}
			
		}
		else{
			if(ii==1) strAlert=strAlert+"，\n";
			strAlert=strAlert+"“考试年”请输入阿拉伯数字整数";
			ii=1;
		}
	}
	var lie_procode=document.getElementById("lie_procode").value;
	if(lie_procode==""){
		if(ii==1) strAlert=strAlert+"，\n";
		strAlert=strAlert+"“专业所在列”未选择";
		ii=1;
	}
	var lienum=document.getElementById("lienum");
	if(lienum==null){
		if(ii==1) strAlert=strAlert+"，\n";
		strAlert=strAlert+"没有点击页面上的“显示月份”按钮";
		ii=1;
	}
	else{
		lienum=document.getElementById("lienum").value;
		lienum=trim(lienum);
		if(!/^[0-9]*$/.test(lienum)){
			if(ii==1) strAlert=strAlert+"，\n";
			strAlert=strAlert+"没有点击页面上的“显示月份”按钮";
			ii=1;
		}
		else{
			var i,month,day,examinationTime,beginTime,endTime,dui=0,liedui=0;
			for(i=1;i<lienum;i++){
				month=document.getElementById("month"+i).value;
				month=trim(month);
				day=document.getElementById("day"+i).value;
				day=trim(day);
				examinationTime=document.getElementById("examinationTime"+i).value;
				examinationTime=trim(examinationTime);
				beginTime=document.getElementById("beginTime"+i).value;
				beginTime=trim(beginTime);
				endTime=document.getElementById("endTime"+i).value;
				endTime=trim(endTime);
				lie=document.getElementById("lie"+i).value;
				if(month!="" && /^[0-9]*$/.test(month) && day!="" && /^[0-9]*$/.test(day)
				 && examinationTime!="" && /^[0-9]*$/.test(examinationTime)
				 && beginTime!="" && /^(.+):(.+)$/.test(beginTime)
				 && endTime!="" && /^(.+):(.+)$/.test(endTime)){}
				else{
					dui=1;
				}
				if(lie!=""){
					liedui=1;
				}
			}
			if(dui==1){
				if(ii==1) strAlert=strAlert+"，\n";
				strAlert=strAlert+"“所在列”左边的信息填写有误";
				ii=1;
			}
			if(liedui==0){
				if(ii==1) strAlert=strAlert+"，\n";
				strAlert=strAlert+"未选择“所在列”";
				ii=1;
			}
		}
	}
	var beginline=document.getElementById("beginline").value;
	beginline=trim(beginline);
	if(beginline==""){
		if(ii==1) strAlert=strAlert+"，\n";
		strAlert=strAlert+"“数据行（从）”未填写";
		ii=1;
	}
	else{
		if(/^[0-9]*$/.test(beginline)){
			if(parseInt(beginline)<1){
				if(ii==1) strAlert=strAlert+"，\n";
				strAlert=strAlert+"“数据行（从）”输入过小";
				ii=1;
			}
		}
		else{
			if(ii==1) strAlert=strAlert+"，\n";
			strAlert=strAlert+"“数据行（从）”请输入阿拉伯数字整数";
			ii=1;
		}
	}
	var endline=document.getElementById("endline").value;
	endline=trim(endline);
	if(endline==""){
		if(ii==1) strAlert=strAlert+"，\n";
		strAlert=strAlert+"“数据行（到）”未填写";
		ii=1;
	}
	else{
		if(/^[0-9]*$/.test(endline)){
			if(parseInt(endline)<1){
				if(ii==1) strAlert=strAlert+"，\n";
				strAlert=strAlert+"“数据行（到）”输入过小";
				ii=1;
			}
			if(parseInt(endline)<parseInt(beginline)){
				if(ii==1) strAlert=strAlert+"，\n";
				strAlert=strAlert+"“数据行（到）”输入不能小于“数据行（从）”";
				ii=1;
			}
			
		}
		else{
			if(ii==1) strAlert=strAlert+"，\n";
			strAlert=strAlert+"“数据行（到）”请输入阿拉伯数字整数";
			ii=1;
		}
	}
	var sheet=document.getElementById("sheet").value;
	sheet=trim(sheet);
	if(sheet==""){
		if(ii==1) strAlert=strAlert+"，\n";
		strAlert=strAlert+"“输入导入的Excel工作表名”未填写";
		ii=1;
	}
	var excelFile=document.getElementById("excelFile").value;
	if(excelFile==""){
		if(ii==1) strAlert=strAlert+"，\n";
		strAlert=strAlert+"“选择导入的Excel文件”未选择";
		ii=1;
	}
	if(ii==1){
		alert(strAlert+"。");
		return false;
	}
	else{
		checkForm.action="exam_up.do";
	}
}
</script>
</head>
	<body>
		<!-- 页面导航 -->
		<div class="dqwz">
			<span>帮助</span><span class="pageCode">功能编号:${currFunctionId}</span>
			专业计划 > 考试安排 > <c:if test="${menutype=='1'}">全国统考课程考试安排</c:if><c:if test="${menutype=='2'}">非全国统考课程考试安排</c:if>
		</div>
		<!-- 编辑区域 -->
<table border='0' cellpadding='0' cellspacing='0' width='100%'>
		<tr>
		<td height='10px'>
		</td>
		</tr>
		<tr>
		<td align='left' style='font-size:14px;font-weight:bolder;'>
<c:if test="${menutype=='1'}">全国统考课程考试安排</c:if><c:if test="${menutype=='2'}">非全国统考课程考试安排</c:if>
		</td>
		</tr>
		<tr>
		<td height='10px'/>
		</tr>
</table>
<form method='post' name='checkForm' enctype="multipart/form-data" onsubmit='return checkFile(this);'><!--  onsubmit='return checkFile(this);' -->
<table border='0' cellpadding='0' cellspacing='0' width='100%'>
<tr><td align="right" style='height:25px;' width="15%">
考试年：</td><td align="left" width="85%">
<input name='examYear' id='examYear' type='text' class="inputButtonS"/>
<input type='button' value='显示月份' onclick='showLie()'/>
</td></tr>
<tr><td align="right" style='height:25px;'>
专业所在列：</td><td align="left">
<select name="lie_procode" id="lie_procode">
<option value=''>请选择…</option>
<c:forEach var="lie" items="${lie}" varStatus="xh">
<option value='${xh.index}'>${lie}</option>
</c:forEach>
</select></td></tr>
</table>
<div style='width:100%;' id='div_in'>
</div>
<table border='0' cellpadding='0' cellspacing='0' width='100%'>
<tr><td align="right" style='height:25px;' width="15%">
数据行：</td><td align="left" width="85%">数据从
<input name="beginline" id="beginline" type="text" class="inputButtonS" value='2'/>到第
<input name="endline" id="endline" type="text" class="inputButtonS"/>行</td></tr>
<tr><td align="right" style='height:25px;'>
输入导入的Excel工作表名：</td><td align="left">
<input name="sheet" id="sheet" type="text" class="inputTextL" value='Sheet1'/></td></tr>
<tr><td align="right">
选择导入的Excel文件：</td><td align="left">
<input name="excelFile" id="excelFile" type="file" class="inputText"/>
</td></tr>
<tr><td height='10px' colspan='2'/></tr>
<tr><td align="center" height='50px' colspan='2' valign='middle'>
<input type="hidden" name='menutype' id='menutype' value="${menutype}"/>
<input type="submit" class="inputButton" value="导 入"/>
<input class="inputButton" type="reset"  value="重 置" />
<input class="inputButton" type="button" value="返 回"
		onclick="location.href='<%=request.getContextPath()%>/manager/plan/examarrange/exam_show.do?menutype=${menutype}';" />
</td></tr>
</table>
</form>
	</body>
</html>
