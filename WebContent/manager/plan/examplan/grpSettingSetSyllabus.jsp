<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>课程分组设置</title>
		<link href="<%=request.getContextPath()%>/common/style/style.css"
			rel="stylesheet" type="text/css" />
		<script type="text/javascript">
		
		</script>
	</head>
	<body>
		<!-- 页面导航 -->
		<div class="dqwz">
			<div style="margin-right: 10px;">
				<span style="cursor: pointer;" id="helpSpan"
					onclick="javascript:showHelpDiv('Z21009');">- 帮助 -</span>
				<span class="pageCode">No.Z21009</span>${currNavigation}
			</div>
		</div>
		<jsp:include page="/help/helpJs.jsp" />
		<iframe name="ifm" id="helpIfm" scrolling="no" frameborder="0"
			width="100%" style="display: none;"
			onload="this.height=this.contentWindow.document.body.scrollHeight+13"></iframe>
			<div class="dqwz">
				专业名称：${ basepro.proName}专业
			</div>
		    <!-- 结果集 -->
			<c:if test="${ not empty proSyllabusList}">
			<form method="post" name="form1" action="<%=request.getContextPath()%>/plan/proSyllabus_EditGrpSyllabus.do">
			<!-- <input type="text" name="proSyllabus.dataChecked" id="dataChecked" value="" /> -->
			<input type="hidden" name="proSyllabus.syllabusGroupCode" id="syllabusGroupCode" value="${grpSet.syllabusGroupCode}" />
			<input type="hidden" name="proSyllabus.proCode" id="proCode" value="${basepro.proCode}"/>
		    <div class="list">			
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<caption>
				</caption>
				<thead>
					<tr>
						<th width="26">
							反选
						</th>
						<th>
							课程代码
						</th>
						<th>
							课程名称
						</th>
						<th>
							状态
						</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach items="${ proSyllabusList}" var="proSyllabus" varStatus="ctNum">
					<tr>
						<td align="center">
							<input type="checkbox" name="proSyllabus.syllabusCode" id="syllabusCode" value="${proSyllabus.syllabusCode}" <c:if test="${grpSet.syllabusGroupCode==proSyllabus.syllabusGroupCode}">checked</c:if>  /><!-- disabled="disabled" -->
						</td>
						<td align="center">
							${ proSyllabus.syllabusCode}
						</td>
						<td align="center">
							${ proSyllabus.syllabusName}
						</td>
						<td align="center">
							${ proSyllabus.syllabusGroupName}&nbsp;
						</td>
					</tr>
                </c:forEach>
				</tbody>
			</table>
		</div>
		<div class="clear"></div>
		<div class="button">
		<input class="inputButton" type="submit" value="确 定" />
		<input class="inputButton" type="button" value="返 回" onclick="history.back();" /></div>
		<div class="clear"></div>
		</form>
		</c:if>
	</body>
</html>
