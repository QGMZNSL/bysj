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
			src="<%=request.getContextPath()%>/common/js/jquery-1.3.2.min.js"></script>
		<script type="text/javascript">
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
		<div class="infoedit">
			<h1>
				添加课程分组(专业名称：${basepro.proName})
			</h1>
			<form action="<%=request.getContextPath()%>/plan/grpSet_Add.do"
				method="post" name="addForm">
				<v:bean clazz="com.sincinfo.zxks.core.plan.GrpSetAction"
					form="addForm">
					<input type="hidden" name="grpSet.proCode" id="proCode"
						value="${grpSet.proCode}" />
					<dl>
						<dt>
							分组名称：
						</dt>
						<dd>
							<input class="inputText" type="text"
								name="grpSet.syllabusGroupName" id="syllabusGroupName" value="" />
							<v:v input="grpSet.syllabusGroupName" exp="s(1,50)">请输入分组名称（最长输入50位）！</v:v>
						</dd>
					</dl>
					<dl>
						<dt>
							分组类型：
						</dt>
						<dd>
							<select class="inputText" name="grpSet.syllabusSepCode"
								id="syllabusSepCode">
								<option value="1" selected="selected">
									选考
								</option>
								<option value="2">
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
							<input type="checkbox" checked="checked" name="grpSet.isGroup"
								id="isGroup" value="1" />
						</dd>
					</dl>
					<dl id="belongToGroup">
						<dt>
							所属组群：
						</dt>
						<dd>
							<select class="inputText" name="grpSet.groupCode" id="groupCode">
								<option value="0" selected="selected">
									---请选择---
								</option>
								<option value="1">
									选考群组一
								</option>
								<option value="2">
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
								id="remarks" value="" />
						</dd>
					</dl>
					<div class="clear"></div>
					<div class="button">
						<input class="inputButton" type="submit" value="确 定" />
						<input class="inputButton" type="reset" value="重 置" />
						<input class="inputButton" type="button" value="返 回"
							onclick="history.back();" />
					</div>
				</v:bean>
			</form>
		</div>
	</body>
</html>
