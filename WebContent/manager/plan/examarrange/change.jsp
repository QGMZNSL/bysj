<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="com.sincinfo.zxks.zxksdbs.ExamArrangeService"%>
<%@page import="com.sincinfo.zxks.bean.BaseSyllabusTime"%>
<%@page import="com.sincinfo.zxks.bean.BaseProSyllabus"%>
<%@page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>统考课程考试安排对比</title>
		<%String rgPath=request.getContextPath();%>
		<link href="<%=rgPath%>/common/style/style.css"
			rel="stylesheet" type="text/css" />
<script type="text/javascript" language="javascript">
function windowsload(){
	window.opener.location.reload();
}

function delete1(syllabusCode,proCode,examinationTimeCode){
	if (confirm("确认要删除吗？")){
		window.location="exam_delete.do?baseSyllabusTime.syllabusCode="+syllabusCode+
			"&baseSyllabusTime.proCode="+proCode+
			"&baseSyllabusTime.examinationTimeCode="+examinationTimeCode+
			"&baseSyllabusTime.countryProvince=1";
	}
}

function delete2(syllabusCode,proCode,examinationTimeCode){
	if (confirm("确认要删除吗？")){
		window.location="exam_delete.do?baseSyllabusTime.syllabusCode="+syllabusCode+
			"&baseSyllabusTime.proCode="+proCode+
			"&baseSyllabusTime.examinationTimeCode="+examinationTimeCode+
			"&baseSyllabusTime.countryProvince=2";
	}
}
</script>
	</head>
	<body onload="windowsload()">
<%
String proCode=request.getParameter("proCode");
String examTimeCode=request.getParameter("examTimeCode");
ExamArrangeService examService=new ExamArrangeService();
List<BaseSyllabusTime> lg=examService.lBaseSyllabusTime(proCode,examTimeCode,"1");
List<BaseSyllabusTime> lg2=examService.lBaseSyllabusTime2(proCode,examTimeCode);
List<BaseSyllabusTime> ls=examService.lBaseSyllabusTime(proCode,examTimeCode,"2");
String proName=examService.getProName(proCode);
%>
		<div class="clear"></div>
		<!-- 查询条件 -->
<div>专业：<%//1为专业“公共课”，2为专业“教育类公共课”，专业名称就是这么叫的
if(proCode.equals("1")) out.print("公共课");
else if(proCode.equals("2")) out.print("教育类公共课");
else out.print(proName+"（"+proCode+"）");
%>
<br/>
<br/>
<br/>
国家级课程：
<br/>
<br/>
</div>

		<div class="list">
			<table border="0" cellpadding="0" cellspacing="0">
				<caption>
				</caption>
				<thead>
					<tr>
						<th>
							序号
						</th>
						<th>
							课程代码
						</th>
						<th>
							课程名称
						</th>
						<th>
							删除
						</th>
					</tr>
					</thead>
					<tbody>
					
<%
BaseSyllabusTime baseSyllabusTime;
int i,l=lg.size();
for(i=0;i<l;i++){
	baseSyllabusTime=lg.get(i);
	out.print("</tr><td width='50px' align='center'>"+(i+1)+"</td>"+
		"<td width='100px' align='center'>"+baseSyllabusTime.getSyllabusCode()+"</td>"+
		"<td width='150px'>"+baseSyllabusTime.getSyllabusName()+"</td>"+
		"<td width='120px' align='center'>"+
		"<a href='#' onclick=\"delete1('"+baseSyllabusTime.getSyllabusCode()+"','"+baseSyllabusTime.getProCode()+"','"+baseSyllabusTime.getExaminationTimeCode()+"')\">删除</a>"+
		"</td>"+
		"<tr>");
}
l=lg2.size();
for(i=0;i<l;i++){
	baseSyllabusTime=lg2.get(i);
	out.print("</tr><td align='center'>"+(i+1)+"</td>"+
		"<td align='center'>"+baseSyllabusTime.getSyllabusCode()+"</td>"+
		"<td>"+baseSyllabusTime.getSyllabusName()+"</td>"+
		"<td align='center'>"+
		"【其它国家级专业】"+
		"</td>"+
		"<tr>");
}
%>
				</tbody>
			</table>
		</div>
<br/>
<br/>
<div>
省级课程：
</div>
<br/>
<br/>
<div class="list">
			<table border="0" cellpadding="0" cellspacing="0">
				<caption>
				</caption>
				<thead>
					<tr>
						<th>
							序号
						</th>
						<th>
							课程代码
						</th>
						<th>
							课程名称
						</th>
						<th>
							删除
						</th>
					</tr>
					</thead>
					<tbody>
					
<%
l=ls.size();
for(i=0;i<l;i++){
	baseSyllabusTime=ls.get(i);
	out.print("</tr><td width='50px' align='center'>"+(i+1)+"</td>"+
		"<td width='100px' align='center'>"+baseSyllabusTime.getSyllabusCode()+"</td>"+
		"<td width='150px'>&nbsp;");
	if(baseSyllabusTime.getSyllabusName()!=null && !baseSyllabusTime.getSyllabusName().equals("0")){
		out.print(baseSyllabusTime.getSyllabusName());
	}
	out.print("</td>"+
		"<td width='100px' align='center'>"+
		"<a href='#' onclick=\"delete2('"+baseSyllabusTime.getSyllabusCode()+"','"+baseSyllabusTime.getProCode()+"','"+baseSyllabusTime.getExaminationTimeCode()+"')\">删除</a>"+
		"</td>"+
		"<tr>");
}
%>
				</tbody>
			</table>
		</div>
<br/>
<br/>
<div style='width:500px' align='center'>
<input class="inputButton" type='button' value='新增' 
onclick="window.location='exam_add.do?baseSyllabusTime.proCode=<%=proCode%>&baseSyllabusTime.examinationTimeCode=<%=examTimeCode%>'"/>
</div>
<br/>
<br/>
<div>
专业计划库中本专业的课程明细：
</div>
<br/>
<br/>
<div class="list">
			<table border="0" cellpadding="0" cellspacing="0">
				<caption>
				</caption>
				<thead>
					<tr>
						<th>
							序号
						</th>
						<th>
							课程代码
						</th>
						<th>
							课程名称
						</th>
						<th>
							新增
						</th>
					</tr>
					</thead>
					<tbody>
<%
List<BaseProSyllabus> lBPS=examService.lBaseProSyllabus(proCode,examTimeCode);
BaseProSyllabus	baseProSyllabus;
l=lBPS.size();
for(i=0;i<l;i++){
	baseProSyllabus=lBPS.get(i);
	out.print("<tr><td width='50px' align='center'>"+(i+1)+"</td>"+
		"<td width='100px' align='center'>"+baseProSyllabus.getSyllabusCode()+"</td>"+
		"<td width='150px'>"+baseProSyllabus.getSyllabusName()+"</td>"+
		"<td>");
	out.print("<a href='exam_save.do?baseSyllabusTime.proCode="+proCode+"&baseSyllabusTime.syllabusCode="+baseProSyllabus.getSyllabusCode()+"&baseSyllabusTime.examinationTimeCode="+examTimeCode+"'>新增</a>");
	out.print("</td></tr>");
}
%>
				</tbody>
			</table>
		</div>

			<div class="clear"></div>
		<!-- 结果集 -->
	</body>
</html>