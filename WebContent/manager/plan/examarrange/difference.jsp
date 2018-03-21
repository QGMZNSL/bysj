<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>全部课程考试安排</title>
		<%
			String rgPath = request.getContextPath();
		%>
		<link href="<%=rgPath%>/common/style/style.css" rel="stylesheet"
			type="text/css" />
		<script type="text/javascript"
			src="<%=rgPath%>/common/js/calendar1.js"></script>
		<script type="text/javascript"
			src="<%=rgPath%>/manager/plan/examarrange/ajax.js"></script>
		<script type="text/javascript">		
function openChange(examTimeCode,proCode,proName){
	window.open("change.jsp?examTimeCode="+examTimeCode+"&proCode="+proCode+"&proName="+proName,"","height=600px, width=840px, top=50px,left=100px,scrollbars=yes,resizable=yes,location=no, status=yes"); 
}

function url(){//这样做的目的是：后面弹出页面的修改和这个页面有个实时刷新功能，直接用form+submit无法实现查询，只能用get传值的方法
	var applyYear=document.getElementById("baseSyllabusTime.applyYear").value;
	var examinationDate=document.getElementById("baseSyllabusTime.examinationDate").value;
	var examinationTime=document.getElementById("baseSyllabusTime.examinationTime").value;
	var proCode=document.getElementById("baseSyllabusTime.proCode").value;
	var proName=document.getElementById("baseSyllabusTime.proName").value;
	var syllabusCode=document.getElementById("baseSyllabusTime.syllabusCode").value;
	var syllabusName=document.getElementById("baseSyllabusTime.syllabusName").value;
	var examUnitary=document.getElementById("baseSyllabusTime.examUnitary").value;
	window.location="exam_show.do?baseSyllabusTime.applyYear="+applyYear+"&baseSyllabusTime.examinationDate="+examinationDate+
		"&baseSyllabusTime.examinationTime="+examinationTime+"&baseSyllabusTime.proCode="+proCode+
		"&baseSyllabusTime.proName="+proName+"&baseSyllabusTime.syllabusCode="+syllabusCode+
		"&baseSyllabusTime.syllabusName="+syllabusName+"&menutype=3"+"&baseSyllabusTime.examUnitary="+examUnitary;
}

function submitMess(){
	if(confirm("点击发布后，全省考生将会看到以下的课程安排信息，确认要发布吗？")){
		var applyYear=document.getElementById("baseSyllabusTime.applyYear").value;
		var examinationDate=document.getElementById("baseSyllabusTime.examinationDate").value;
		var examinationTime=document.getElementById("baseSyllabusTime.examinationTime").value;
		var proCode=document.getElementById("baseSyllabusTime.proCode").value;
		var proName=document.getElementById("baseSyllabusTime.proName").value;
		var syllabusCode=document.getElementById("baseSyllabusTime.syllabusCode").value;
		var syllabusName=document.getElementById("baseSyllabusTime.syllabusName").value;
		window.location="exam_publicArrange.do?baseSyllabusTime.applyYear="+applyYear+"&baseSyllabusTime.examinationDate="+examinationDate+
			"&baseSyllabusTime.examinationTime="+examinationTime+"&baseSyllabusTime.proCode="+proCode+
			"&baseSyllabusTime.proName="+proName+"&baseSyllabusTime.syllabusCode="+syllabusCode+
			"&baseSyllabusTime.syllabusName="+syllabusName+"&menutype=3";
	}
}
</script>
	</head>
	<body>
		<!-- 页面导航 -->
		<div class="dqwz">
			<span style="cursor: pointer;" id="helpSpan"
				onclick="javascript:showHelpDiv('Z22004');">- 帮助 -</span>
			<span class="pageCode">No.Z22004</span> 专业计划 > 考试安排
			> 陕西省考试安排
		</div>
		<jsp:include page="/help/helpJs.jsp" />
		<iframe name="ifm" id="helpIfm" scrolling="no" frameborder="0"
			width="100%" style="display: none;"
			onload="this.height=this.contentWindow.document.body.scrollHeight+13"></iframe>
		<div class="clear"></div>
		<!-- 查询条件 -->
		<form method="post" name="checkForm">
			<!--  action='exam_show.do' -->
			<div class="tjcx">
				<dl>
					<dt>
						年度：
					</dt>
					<dd>
						<select class="inputText inputTextM"
							name="baseSyllabusTime.applyYear" id="baseSyllabusTime.applyYear">
							<c:forEach var="exam" items="${lYear}">
								<option value="${exam}"
									<c:if test="${exam==baseSyllabusTime.applyYear}">selected</c:if>>
									${exam}
								</option>
							</c:forEach>
						</select>
					</dd>
				</dl>
				<dl>
					<dt>
						是否全国统考：
					</dt>
					<dd>
						<select class="inputText inputTextM"
							name="baseSyllabusTime.examUnitary"
							id="baseSyllabusTime.examUnitary">
							<option value="">
								--- 请选择 ---
							</option>
							<option value="1"
								<c:if test="${baseSyllabusTime.examUnitary=='1'}">selected</c:if>>
								是
							</option>
							<option value="0"
								<c:if test="${baseSyllabusTime.examUnitary=='0'}">selected</c:if>>
								否
							</option>
						</select>
					</dd>
				</dl>
				<dl>
					<dt>
						考试日期：
					</dt>
					<dd>
						<input class="inputText inputTextM" type="text"
							id="baseSyllabusTime.examinationDate"
							name="baseSyllabusTime.examinationDate"
							value="${baseSyllabusTime.examinationDate}" readonly
							onclick="calendar()" />
					</dd>
				</dl>
				<dl>
					<dt>
						考试时间段：
					</dt>
					<dd>
						<select class="inputText inputTextM"
							name="baseSyllabusTime.examinationTime"
							id="baseSyllabusTime.examinationTime">
							<option value="0">
								--- 请选择 ---
							</option>
							<option value="1"
								<c:if test="${baseSyllabusTime.examinationTime=='1'}">selected</c:if>>
								上午
							</option>
							<option value="2"
								<c:if test="${baseSyllabusTime.examinationTime=='2'}">selected</c:if>>
								下午
							</option>
						</select>
					</dd>
				</dl>
				<dl>
					<dt>
						专业代码：
					</dt>
					<dd>
						<input class="inputText inputTextM" type="text"
							id="baseSyllabusTime.proCode" name="baseSyllabusTime.proCode"
							value="${baseSyllabusTime.proCode}" />
					</dd>
				</dl>
				<dl>
					<dt>
						专业名称：
					</dt>
					<dd>
						<input class="inputText inputTextM" type="text"
							id="baseSyllabusTime.proName" name="baseSyllabusTime.proName"
							value="${baseSyllabusTime.proName}" />
					</dd>
				</dl>
				<dl>
					<dt>
						课程代码：
					</dt>
					<dd>
						<input class="inputText inputTextM" type="text"
							id="baseSyllabusTime.syllabusCode"
							name="baseSyllabusTime.syllabusCode"
							value="${baseSyllabusTime.syllabusCode}" />
					</dd>
				</dl>
				<dl>
					<dt>
						课程名称：
					</dt>
					<dd>
						<input class="inputText inputTextM" type="text"
							id="baseSyllabusTime.syllabusName"
							name="baseSyllabusTime.syllabusName"
							value="${baseSyllabusTime.syllabusName}" />
					</dd>
				</dl>
			</div>
			<div class="clear"></div>
			<div class="button">
				<input class="inputButton" type="button" value="查 询" onclick="url()" />
				<input class="inputButton" type="button" value="导 出"
					onclick="doExcel(3)" />
				<input class="inputButton" type="button" value="发布信息"
					onclick="submitMess()" />
				<span id='exceldiv'></span>
			</div>
			<div class="clear"></div>
		</form>
		<!-- 结果集 -->

		<div class='dmin'>
			<div class="list">
				<table border="0" cellpadding="0" cellspacing="0"
					width='${tableLong}'
					style="word-wrap: break-word; word-break: break-all; overflow: hidden; table-layout: fixed;">
					<caption>
					</caption>
					<thead>
						<tr>
							<th rowspan='2'>
								专业
							</th>
							<c:forEach var="osec" items="${losec}">
								<th colspan="${osec[0]}">
									${osec[1]}月
								</th>
							</c:forEach>
						</tr>
						<tr>
							<c:forEach var="osec" items="${losec}">
								<c:forEach var="bean" items="${osec[2]}">
									<th>
										${bean.year}-${bean.month}-${bean.day}
										<c:if test="${bean.examinationTime=='1'}">上午</c:if>
										<c:if test="${bean.examinationTime=='2'}">下午</c:if>
									</th>
								</c:forEach>
							</c:forEach>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="oo" items="${lo}">
							<tr>
								<td width='150px'>
									${oo[0]}
								</td>
								<c:forEach var="bean" items="${oo[1]}">
									<td width='150px' id='t${bean[1]}${bean[2]}'>
										&nbsp;${bean[0]}
										<br />
										<br />
										<img src='<%=rgPath%>/manager/plan/examarrange/xiugai.gif'
											onclick="openChange('${bean[1]}','${bean[2]}','${bean[3]}')"
											style='width: 16px; height: 16px;' />
									</td>
								</c:forEach>
								<td>
							</tr>
						</c:forEach>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="${tableCol}">
								<span>${page.pageInfo}</span>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
		</div>
		<div id='bgDiv'
			style='position: absolute; z-index: 2000; display: none; top: 0; left: 0; background: #777777; filter: progid :     DXImageTransform .     Microsoft .     Alpha(style =     3, opacity =     25, finishOpacity =     75); opacity: 0.6;'></div>
		<table border='0' cellpadding='0' cellspacing='0' id='saveDiv'
			style='position: absolute; z-index: 2100; display: none; width: 200px; top: 100px; left: 100px;'>
			<tr>
				<td height='5px' style='background: #ffffff;' />
			</tr>
			<tr>
				<td style='background: #ffffff;' align='center' height='50px'>
					Excel生成中，请稍后！
				</td>
			</tr>
			<tr>
				<td height='5px' style='background: #ffffff;' />
			</tr>
		</table>
	</body>
</html>