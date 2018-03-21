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
		function getDataChecked(){
			var cboxes = $('.' + cboxClassName);
			var dataChecked="";
			for ( var i = 0; i < cboxes.length; i++) {
				if ( $(cboxes[i]).attr("checked") == true ||  $(cboxes[i]).attr("checked") == "checked") {
                   dataChecked=dataChecked+","+$(cboxes[i]).value;
				}
			}
			dataChecked=dataChecked.substr(1);
			$('dataChecked').val=dataChecked;
			alert($('dataChecked').val);
		}
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
	
		<!-- 结果集 -->
			<div class="dqwz">
				专业名称：${ basepro.proName}专业
			</div>
			<c:if test="${ not empty GrpSetList}">
			<form method="post" name="form1" action="<%=request.getContextPath()%>/plan/grpSet_EditGroupGrp.do" onsubmit="getDataChecked();">
			<input type="hidden" name="proSyllabus.dataChecked" id="dataChecked" />
			<input type="hidden" name="grpSet.groupCode" id="groupCode" value="${grpSet.groupCode}" />
			<input type="hidden" name="grpSet.proCode" id="proCode" value="${basepro.proCode}"/>			
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
							分组名称
						</th>
						<th>
							分组类型
						</th>
						<th>
							备注
						</th>
						<th>
							所属群组
						</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach items="${ GrpSetList}" var="gs" varStatus="ctNum">
					<tr>
						<td align="center">
							<input type="checkbox" name="grpSet.syllabusGroupCode" id="syllabusGroupCode" value="${gs.syllabusGroupCode}" <c:if test="${gs.groupCode==grpSet.groupCode}">checked</c:if> />
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
							${ gs.remarks}&nbsp;
						</td>
						<td align="center">
							${ gs.groupCode}
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
