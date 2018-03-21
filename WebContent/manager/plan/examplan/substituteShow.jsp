<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>替代课程设置</title>
			<link href="<%=request.getContextPath()%>/common/style/style.css"
			rel="stylesheet" type="text/css" />
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/common/js/jquery-1.3.2.min.js"></script>
		<script type="text/javascript">
		var init = function() {
			
		};
		var goOperate = function( type, syllabusCode) {
			var url;
			var gotoFlag = true;
			if ( type == 'clear') {
				url = '<%=request.getContextPath()%>/plan/subStitute_Clear.do?syllabus.syllabusCode=' + syllabusCode;
				gotoFlag = confirm("确定要清除该课程所有免考信息吗？（如果免考已经被用，则无法删除！）");
			} else if ( type == 'SubStituteSet') {
				url = '<%=request.getContextPath()%>/plan/subStitute_SubStituteSet.do?syllabus.syllabusCode=' + syllabusCode;
			} else if ( type == 'substituteGroup') {
				url = '<%=request.getContextPath()%>/plan/substituteGroup_Show.do?syllabus.syllabusCode=' + syllabusCode;
			}else {
				return;
			}
			if ( gotoFlag) {
				location.href = url;
			}
		};
		
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
					onclick="javascript:showHelpDiv('Z21010');">- 帮助 -</span>
				<span class="pageCode">No.Z21010</span>${currNavigation}
			</div>
		</div>
		<jsp:include page="/help/helpJs.jsp" />
		<iframe name="ifm" id="helpIfm" scrolling="no" frameborder="0"
			width="100%" style="display: none;"
			onload="this.height=this.contentWindow.document.body.scrollHeight+13"></iframe>
		
		<!-- 查询条件 -->
		<form method="post" name="form1" action="<%=request.getContextPath()%>/plan/subStitute_qry.do"> 
		<div class="tjcx">
		
				<dl>
					<dt>课程代码：</dt>
					<dd><input class="inputText inputTextM" type="text" name="syllabus.syllabusCode" value="${syllabus.syllabusCode}"/></dd>
				</dl>
				<dl>
					<dt>课程名称：</dt>
					<dd><input class="inputText inputTextM" type="text" name="syllabus.syllabusName" value="${syllabus.syllabusName}"/></dd>
				</dl>
				<dl>
					<dt>设置状态：</dt>
					<dd>
						<select class="inputText inputTextM" name="syllabus.state1" id="state1">
						<option value="" >全部</option>
						<option value="0" <c:if test="${syllabus.state1==0}">selected</c:if> >未设置</option>
						<option value="1" <c:if test="${syllabus.state1 >0}">selected</c:if> >已设置</option>
						</select>
					</dd>
				</dl>
			  
			</div>
				<div class="button">
					<input class="inputButton" type="submit" value="查 询" />
				</div>
			<div class="clear"></div>
		</form>  
		
		
		<!-- 结果集 -->
		<c:if test="${ not empty syllabusList}">
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
							状态
						</th>
						<th width="80">
							操作
						</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${ syllabusList}" var="syllabus" varStatus="ctNum">
					<tr>
						<td align="center">
							${page.pagesize * page.pagenum + ctNum.count}
						</td>
						<td align="center">
							${ syllabus.syllabusCode}&nbsp;
						</td>
						<td align="center">
							${ syllabus.syllabusName}&nbsp;
						</td>
						<td align="center">
							<c:choose>
								<c:when test="${ syllabus.state1 == 0}">未设置</c:when>
								<c:when test="${ syllabus.state1 > 0}">已设置</c:when>
								<c:otherwise>&nbsp;</c:otherwise>
							</c:choose>
						</td>
						<td align="center">
							<a href="#" onclick="goOperate( 'SubStituteSet', '${ syllabus.syllabusCode}');">设置</a>&nbsp;
							<a href="#" onclick="goOperate( 'clear', '${ syllabus.syllabusCode}');">清除</a>
						</td>
					</tr>
					</c:forEach>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="7">
							<span> ${ page.pageInfo}</span>
						</td>
					</tr>
				</tfoot>
			</table>
		</div>
		</c:if>
	</body>
</html>
