<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>选择教材</title>
		<link href="<%=request.getContextPath()%>/common/style/style.css"
			rel="stylesheet" type="text/css" />
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/common/js/jquery-1.3.2.min.js"></script>
	</head>

	<body>
		<!-- 页面导航 -->
		<div class="dqwz">
			>>选择教材
		</div>

		<!-- 查询条件 -->
		<form method="post" name="form1"
			action="<%=request.getContextPath()%>/plan/syllabus_qryTBook.do">
			<div class="tjcx">
				<dl>
					<dt>
						出版时间：
					</dt>
					<dd>
						<select class="inputText inputTextM" name="textBook.publishTime"
							id="publishTime">
							<option value="">
								--- 请选择 ---
							</option>
							<c:forEach items="${ publistTimes}" var="et">
								<option value="${ et.entityCode}"
									<c:if test="${textBook.publishTime==et.entityCode}">selected</c:if>>
									${ et.entityName}
								</option>
							</c:forEach>
						</select>
					</dd>
				</dl>
				<dl>
					<dt>
						出版社：
					</dt>
					<dd>
						<select class="inputText inputTextM"
							name="textBook.textbookPublisher" id="textbookPublisher">
							<option value="">
								--- 请选择 ---
							</option>
							<c:forEach items="${ publists}" var="et">
								<option value="${ et.entityCode}"
									<c:if test="${textBook.textbookPublisher==et.entityCode}">selected</c:if>>
									${ et.entityName}
								</option>
							</c:forEach>
						</select>
					</dd>
				</dl>
				<dl>
					<dt>
						教材名称：
					</dt>
					<dd>
						<input class="inputText inputTextM" type="text"
							name="textBook.textbookName" id="textbookName"
							value="${ textBook.textbookName}" />
					</dd>
				</dl>
			</div>
			<div class="button">
				<input class="inputButton" type="submit" value="查 询" />
			</div>
			<div class="clear"></div>
		</form>

		<!-- 结果集 -->
		<c:if test="${ not empty textbookList}">
			<div class="list">
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
					<caption>
					</caption>
					<thead>
						<tr>
							<th width="30">
								序号
							</th>
							<th>
								教材名称
							</th>
							<th>
								主编
							</th>
							<th>
								出版社
							</th>
							<th>
								出版时间
							</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${ textbookList}" var="textbook"
							varStatus="ctNum">
							<tr>
								<td align="center">
									${ ctNum.count}
								</td>
								<td align="center">
									<a href="#"
										onclick='$("#textbookName",window.opener.document).val("${ textbook.textbookName}");$("#textbookCode",window.opener.document).val("${ textbook.textbookCode}");window.close();'>${
										textbook.textbookName}</a>
								</td>
								<td align="center">
									${ textbook.textbookEditor}
								</td>
								<td align="center">
									${ textbook.textbookPublisher}
								</td>
								<td align="center">
									${ textbook.publishTime}
								</td>
							</tr>
						</c:forEach>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="5">
								<span>${ page.pageInfo}</span>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
		</c:if>
	</body>
</html>
