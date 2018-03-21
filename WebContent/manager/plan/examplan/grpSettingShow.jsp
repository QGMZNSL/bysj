<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>课程分组设置</title>
		<link href="<%=request.getContextPath()%>/common/style/style.css"
			rel="stylesheet" type="text/css" />
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/common/js/jquery-1.3.2.min.js"></script>
		<script type="text/javascript">
		var init = function() {
		<c:choose>
			<c:when test="${  not empty basepro.proCode}">$('#addButton').css("display", "inline");</c:when>							
			<c:otherwise>$('#addButton').css("display", "none");</c:otherwise>
		</c:choose>
		};
		var goOperate = function( type, syllabusGroupCode,proCode) {
			var url;
			var gotoFlag = true;
			if ( type == 'del') {
				url = '<%=request.getContextPath()%>/plan/grpSet_Del.do?grpSet.syllabusGroupCode=' + syllabusGroupCode;
				gotoFlag = confirm("确定要删除该分组吗？（如果分组已经被用，则无法删除！）");
			} else if ( type == 'edit') {
				url = '<%=request.getContextPath()%>/plan/grpSet_EditPre.do?grpSet.syllabusGroupCode=' + syllabusGroupCode;
			} else if ( type == 'grpSettSyllabus'){
				url = '<%=request.getContextPath()%>/plan/grpSet_GrpSettSyllabus.do?grpSet.syllabusGroupCode='+syllabusGroupCode+'&basepro.proCode=' + proCode;
			}else if ( type == 'GrpSetGroup'){
				url = '<%=request.getContextPath()%>/plan/grpSet_GrpSetGroup.do?grpSet.syllabusGroupCode='+syllabusGroupCode+'&basepro.proCode=' + proCode;
			} else {
				return;
			}			
			if ( gotoFlag) {
				location.href = url;
			}
		};
		$(function() {
			init();			
		});
		<c:if test="${ not empty basepro.proCode}">
		   $('#addButton').css("display", "inline");
		   $('#addButton').show("slow");
		</c:if>
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
		<!-- 查询条件 -->
		<form method="post" name="form1" action="<%=request.getContextPath()%>/plan/grpSet_qry.do">
			<div class="tjcx">
				<dl>
					<dt>
						专业代码：
					</dt>
					<dd>
						<input class="inputText inputTextM" type="text" name="grpSet.proCode" id="proCode" value="${grpSet.proCode}" />
					</dd>
				</dl>
				<dl>
					<dt>
						专业名称：
					</dt>
					<dd>
						<input class="inputText inputTextM" type="text" name="grpSet.proName" id="proName" value="${grpSet.proName}" />
					</dd>
				</dl>
			</div>
			<div class="button">
				<input class="inputButton" type="submit" value="查 询" />
				<input class="inputButton" type="button" value="添 加" id="addButton"
					onclick="location.href='/ZK_CORE/plan/grpSet_AddPre.do?basepro.proCode=${ grpSet.proCode}';" />
			</div>
		</form>
		<div class="clear"></div>

		<div class="dqwz">
			专业名称：${ basepro.proName}专业
		</div>
		<!-- 结果集 -->
		<c:if test="${ not empty grpSetList}">
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
							分组名称
						</th>
						<th>
							分组类型
						</th>
						<th>
							所属组群
						</th>
						<th>
							是否群组<!-- 状态 -->
						</th>
						<th>
							备注
						</th>
						<th width="120">
							操作
						</th>
					</tr>
				</thead>
				<tbody>
				    <c:forEach items="${ grpSetList}" var="gs" varStatus="ctNum">
					<tr>
						<td align="center">
							${ page.pagesize * page.pagenum + ctNum.count}
						</td>
						<td align="center">
							${ gs.syllabusGroupName}
						</td>
						<td align="center">
							<c:choose>
								<c:when test="${ gs.syllabusSepCode == 1}">选考</c:when>							
								<c:when test="${ gs.syllabusSepCode == 2}">加考</c:when>
								<c:otherwise>&nbsp;</c:otherwise>
							</c:choose>
						</td>
						<td align="center">
							${ gs.groupCode}
						</td>
						<td align="center">
							<c:choose>
								<c:when test="${ gs.isGroup == 1}">是</c:when>							
								<c:when test="${ gs.isGroup == 0}">否</c:when>
								<c:otherwise>&nbsp;</c:otherwise>
							</c:choose>
						</td>
						<td align="center">
							${ gs.remarks}&nbsp;
						</td>
						<td align="center">
							<a href="#" onclick="goOperate('edit','${ gs.syllabusGroupCode}','${ gs.proCode}');">编辑</a>&nbsp;
							<c:choose>
								<c:when test="${ gs.isGroup == 0}">
								<a href="#" onclick="goOperate('grpSettSyllabus','${ gs.syllabusGroupCode}','${ gs.proCode}');">设置课程</a>&nbsp;
								</c:when>							
								<c:otherwise>
								<a href="#" onclick="goOperate('GrpSetGroup','${ gs.syllabusGroupCode}','${ gs.proCode}');">设置子组</a>&nbsp;
								</c:otherwise>
							</c:choose>							
							<a href="#" onclick="goOperate('del','${ gs.syllabusGroupCode}','${ gs.proCode}');">删除</a>
						</td>
					</tr>
					</c:forEach>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="7">
							<span>${ page.pageInfo}</span>
						</td>
					</tr>
				</tfoot>
			</table>
		</div>
		</c:if>
	</body>
</html>
