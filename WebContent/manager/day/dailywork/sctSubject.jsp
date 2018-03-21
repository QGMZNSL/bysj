<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>选择专业</title>
		<link href="<%=request.getContextPath()%>/common/style/style.css"
			rel="stylesheet" type="text/css" />
	</head>

	<body>
		<!-- 页面导航 -->
		<div class="dqwz">
			>>选择课程
		</div>

		<!-- 查询条件 -->
		<form method="post" name="form1" action="ssd_seleSyll.do">
			<div class="tjcx">
				<dl>
					<dt>
						课程代码：
					</dt>
					<dd>
						<input class="inputText inputTextM" type="text" name="ssyllCode" value="${ssyllCode }"/>
					</dd>
				</dl>
				<dl>
					<dt>
						课程名称：
					</dt>
					<dd>
						<input class="inputText inputTextM" type="text" name="ssyllName"  value="${ssyllName }"/>
					</dd>
				</dl>
			</div>
			<div class="button">
				<input type="submit" value="查 询" class="inputButton" />
			</div>
		</form>
		<div class="clear"></div>

		<!-- 结果集 -->
		<div class="list">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<caption>
				</caption>
				<thead>
					<tr>
						<th width="26">
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
					<c:forEach var="bean" items="${syllabusList}" varStatus="h">
						<tr>
							<td align="center">
								${page.pagesize*page.pagenum+ h.index+1}
							</td>
							<td align="center">
								${bean.syllabusCode }
							</td>
							<td align="center">
								<a
									href='javascript:opener.document.setForm.syllCodeName.value="${bean.syllabusCode }-${bean.syllabusName }";window.close();'>${bean.syllabusName }</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="3">
							<span>${page.pageInfo }
							</span>
						</td>
					</tr>
				</tfoot>
			</table>
		</div>
	</body>
</html>
