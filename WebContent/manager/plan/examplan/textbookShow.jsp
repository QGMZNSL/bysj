<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>教材设置</title>
		<link href="<%=request.getContextPath()%>/common/style/style.css"
			rel="stylesheet" type="text/css" />
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/common/js/jquery-1.3.2.min.js"></script>
		<script type="text/javascript">
		var init = function() {
			
		}		
		var goOperate = function( type, textbookCode) {
			var url;
			var gotoFlag = true;
			if ( type == 'del') {
				url = '<%=request.getContextPath()%>/plan/textbook_Del.do?textbook.textbookCode=' + textbookCode;
				gotoFlag = confirm("确定要删除该对象吗？（如果已经被用，则无法删除！）");
			} else if ( type == 'isUseDel') {
				url = '<%=request.getContextPath()%>/plan/textbook_isUseDel.do?textbook.textbookCode=' + textbookCode;
				gotoFlag = confirm("确定要删除该信息吗？");
			} else if ( type == 'edit') {
				url = '<%=request.getContextPath()%>/plan/textbook_EditPre.do?textbook.textbookCode=' + textbookCode;
			} else {
				return;
			}
			
			if ( gotoFlag) {
				location.href = url;
			}
		}
		
		$(function() {
			init();			
		});
		</script>	
	</head>

	<body>
		<!-- 页面导航 -->
		<div class="dqwz">
			<div style="margin-right: 10px;">
				<span style="cursor: pointer;" id="helpSpan"
					onclick="javascript:showHelpDiv('Z21006');">- 帮助 -</span>
				<span class="pageCode">No.Z21006</span>${currNavigation}
			</div>
		</div>
		<jsp:include page="/help/helpJs.jsp" />
		<iframe name="ifm" id="helpIfm" scrolling="no" frameborder="0"
			width="100%" style="display: none;"
			onload="this.height=this.contentWindow.document.body.scrollHeight+13"></iframe>
		
		<!-- 查询条件 -->
		<form method="post" name="form1" action="<%=request.getContextPath()%>/plan/textbook_qry.do">
			<div class="tjcx">
			<dl>
				<dt>课程代码：</dt>
				<dd><input class="inputText inputTextM" type="text" name="textbook.syllabusCode" value="${textbook.syllabusCode}" /></dd>
			</dl>
			<dl>
				<dt>课程名称：</dt>
				<dd><input class="inputText inputTextM" type="text" name="textbook.syllabusName" value="${textbook.syllabusName}" /></dd>
			</dl>
			<dl>
				<dt>教材代码：</dt>
				<dd><input class="inputText inputTextM" type="text" name="textbook.textbookCode" value="${textbook.textbookCode}" /></dd>
			</dl>
			<dl>
				<dt>教材名称：</dt>
				<dd><input class="inputText inputTextM" type="text" name="textbook.textbookName" value="${textbook.textbookName}" /></dd>
			</dl>
			<dl>
				<dt>出版社：</dt>
				<dd><input class="inputText inputTextM" type="text" name="textbook.textbookPublisher" value="${textbook.textbookPublisher}" /></dd>
			</dl>
			<dl>
				<dt>出版时间：</dt>
				<dd>
				<input class="inputText inputTextM" type="text" name="textbook.publishTime" value="${textbook.publishTime}" />
				</dd>
			</dl>
		</div>
		    <div class="button">
		    <input class="inputButton" type="submit" value="查 询" />
		    <input class="inputButton" type="button" value="添 加" onclick="location.href='<%=request.getContextPath()%>/plan/textbook_AddPre.do'" />
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
						<th width="26">
							序号
						</th>
						<th>
							课程代码
						</th>
						<th>
							课程名称
						</th>
						<th>
							教材代码
						</th>
						<th>
							教材名称
						</th>
						<th>
							是否统编教材
						</th>
						<th>
							教材主编
						</th>
						<th>
							出版社
						</th>
						<th>
							出版时间
						</th>
						<th>
							零售价
						</th>
						<th width="80">
							操作
						</th>
					</tr>
				</thead>
				<tbody>
				    <c:forEach items="${ textbookList}" var="textbook" varStatus="ctNum">
					<tr>
						<td align="center">
							${ page.pagesize * page.pagenum + ctNum.count}
						</td>
						<td align="center">
							&nbsp;${textbook.syllabusCode}
						</td>
						<td align="left">
							&nbsp;${textbook.syllabusName}
						</td>
						<td align="center">
							${textbook.syllabusCode}-${textbook.textbookCode}
						</td>
						<td align="left">
							${ textbook.textbookName}
						</td>
						<td align="center">
						&nbsp;<c:if test="${textbook.unifiedBook=='0'}">否</c:if>
						<c:if test="${textbook.unifiedBook=='1'}">是</c:if>
						</td>
						<td align="center">
							${ textbook.textbookEditor}
						</td>
						<td align="left">
							${ textbook.textbookPublisher}
						</td>
						<td align="center">
							${ textbook.publishTime}
						</td>
						<td align="center">
							${textbook.textbookPrice}
						</td>
						<td align="center">
							<a href="#" onclick="goOperate( 'edit', '${textbook.textbookCode}');">编辑</a>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<a href="#" onclick="goOperate( 'isUseDel', '${textbook.textbookCode}');">删除</a>
						</td>
					</tr>
					</c:forEach>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="11">
							<span>${ page.pageInfo}</span>
						</td>
					</tr>
				</tfoot>
			</table>
		</div>
		</c:if>
	</body>
</html>
