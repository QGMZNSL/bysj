<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>选择课程</title>
		<link href="<%=request.getContextPath()%>/common/style/style.css"
			rel="stylesheet" type="text/css" />
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/common/js/jquery-1.3.2.min.js"></script>
	</head>

	<body>
		<!-- 页面导航 -->
		<div class="dqwz">
			>>选择课程
		</div>

		<!-- 查询条件 -->
		<form method="post" name="form1"
			action="<%=request.getContextPath()%>/plan/proSyllabus_selProSyllabus.do">
			<div class="tjcx">
				<dl>
					<dt>
						课程代码：
					</dt>
					<dd>
						<input class="inputText inputTextM" type="text"
							name="syllabus.syllabusCode" id="syllabusCode"
							value="${syllabus.syllabusCode}" />
					</dd>
				</dl>
				<dl>
					<dt>
						课程名称：
					</dt>
					<dd>
						<input class="inputText inputTextM" type="text"
							name="syllabus.syllabusName" id="syllabusName"
							value="${syllabus.syllabusName}" />
					</dd>
				</dl>
			</div>
			<div class="button">
				<input type="submit" value="查 询" class="inputButton" />
			</div>
		</form>
		<div class="clear"></div>

		<!-- 结果集 -->
		<c:if test="${ not empty syllabusList}">
			<div class="list">
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
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
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${ syllabusList}" var="syllabus"
							varStatus="ctNum">
							<tr>
								<td align="center">
									${ page.pagesize * page.pagenum + ctNum.count}
								</td>
								<td align="center">
									${ syllabus.syllabusCode}
								</td>
								<td align="center">
									<a href="#"
										onclick='$("#syllabusCode",window.opener.document).val("${syllabus.syllabusCode}");$("#syllabusName",window.opener.document).val("${syllabus.syllabusName}");$("#syllabusCredit",window.opener.document).val("${syllabus.syllabuscredit}");<c:if test="${syllabus.isgb eq '1'}">$("#examUnitary1",window.opener.document).attr("checked", "checked");$("#examUnitary0",window.opener.document).removeAttr("checked");</c:if><c:if test="${syllabus.isgb eq '0'}">$("#examUnitary0",window.opener.document).attr("checked", "checked");$("#examUnitary1",window.opener.document).removeAttr("checked");</c:if>;window.close();'>${
										syllabus.syllabusName}</a>
								</td>
							</tr>
						</c:forEach>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="3">
								<span>${page.pageInfo}</span>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
		</c:if>
	</body>
</html>
