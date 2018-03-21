<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="v" uri="http://nbf.river.org/vxds"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>课程分组设置</title>
		<link href="<%=request.getContextPath()%>/common/style/style.css"
			rel="stylesheet" type="text/css" />
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/common/js/riverdefine.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/common/js/riverajax.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/common/js/jquery-1.3.2.min.js">
		</script><script type="text/javascript">
		$(document).ready( function() {
			$('#isGroup').click( function() {
				if ( $('#isGroup').attr("checked") == "checked" || $('#isGroup').attr("checked") == true) {
					$('#belongToGroup').css("display", "block");
				} else {
					$('#belongToGroup').css("display", "none");
				}
			});
		});
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
		<!-- 编辑区域 -->
		<form action="<%=request.getContextPath()%>/plan/grpSet_Edit.do"
			method="post" name="addForm">
			<div class="infoedit">
				<h1>
					添加课程分组（专业名称：${basepro.proName}）
					<input type="hidden""
							name="grpSet.proCode" id="proCode"
							value="${basepro.proCode}" />
				</h1>

				<dl>
					<dt>
						分组名称：
					</dt>
					<dd>
						<input class="inputText" type="text"
							name="grpSet.syllabusGroupName" id="syllabusGroupName"
							value="${grpSet.syllabusGroupName}" />
						<input type="hidden"
							name="grpSet.syllabusGroupCode" id="syllabusGroupCode"
							value="${grpSet.syllabusGroupCode}" />
					</dd>
				</dl>
				<dl>
					<dt>
						分组类型：
					</dt>
					<dd>
						<select class="inputText" name="grpSet.syllabusSepCode"
							id="syllabusSepCode">
							<option value="1"
								<c:if test="${grpSet.syllabusSepCode==1}">selected="selected"</c:if>>
								选考
							</option>
							<option value="2"
								<c:if test="${grpSet.syllabusSepCode==2}">selected="selected"</c:if>>
								加考
							</option>
						</select>
					</dd>
				</dl>
				<dl>
					<dt>
						是否组群：
					</dt>
					<dd>
						<input type="checkbox" name="grpSet.isGroup"
							<c:if test="${grpSet.isGroup==1}">checked="checked"</c:if> id="isGroup" />
					</dd>
				</dl>
				<dl id="belongToGroup"
					<c:choose>
						<c:when test="${grpSet.isGroup eq '0' }">style="display:none;"</c:when>
					<c:otherwise></c:otherwise>
					</c:choose>>
					<dt>
						所属组群：
					</dt>
					<dd>
						<select class="inputText" name="grpSet.groupCode" id="groupCode">
							<option value="0"
								<c:if test="${grpSet.groupCode eq '0' }">selected="selected"</c:if>>
								---请选择---
							</option>
							<option value="1"
								<c:if test="${grpSet.groupCode eq '1' }">selected="selected"</c:if>>
								选考群组一
							</option>
							<option value="2"
								<c:if test="${grpSet.groupCode eq '2' }">selected="selected"</c:if>>
								选考群组二
							</option>
						</select>
					</dd>
				</dl>
				<div class="clear"></div>
				<dl>
					<dt>
						课程选修说明：
					</dt>
					<dd>
						<input class="inputTextarea" type="text" name="grpSet.remarks"
							id="remarks" value="${grpSet.remarks}" />
					</dd>
				</dl>
				<div class="clear"></div>
				<div class="clear"></div>
			</div>
			<div class="button">
				<input class="inputButton" type="submit" value="确 定" />
				<input class="inputButton" type="reset" value="重 置" />
				<input class="inputButton" type="button" value="返 回"
					onclick="history.back();" />
			</div>
		</form>
	</body>
</html>
