<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="v" uri="http://nbf.river.org/vxds"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>毕业条件设置</title>
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
			$('.chkType').click( function() {
				var val = this.value;
				$('.disNone').hide("slow");
				$('#' + val).show("slow");
			});
			
			$('#sepAreaSwitch').click( function() {
				$('#needArea').hide("slow");
				$('#sepArea').show("slow");
			});
			
			$('#needAreaSwitch').click( function() {
				$('#sepArea').hide("slow");
				$('#needArea').show("slow");
			});
			
			$('#sepCancel').click( function() {
				$('#sepArea').hide("slow");
			});
			
			$('#needCancel').click( function() {
				$('#needArea').hide("slow");
			});
			
			$('.disNone').hide();
			
			$('#subjectRadio').attr("checked", "checked");
			$('#chkTypeSubject').show();
			
			$('#needArea').hide();
			$('#sepArea').hide();
		});
		</script>
	</head>

	<body>
		<!-- 页面导航 -->
		<div class="dqwz">
			<div style="margin-right: 10px;">
				<span style="cursor: pointer;" id="helpSpan"
					onclick="javascript:showHelpDiv('Z21008');">- 帮助 -</span>
				<span class="pageCode">No.Z21008</span>${currNavigation}
			</div>
		</div>
		<jsp:include page="/help/helpJs.jsp" />
		<iframe name="ifm" id="helpIfm" scrolling="no" frameborder="0"
			width="100%" style="display: none;"
			onload="this.height=this.contentWindow.document.body.scrollHeight+13"></iframe>
		<!-- 设置学分 -->
		<div class="infoedit">
			<form
				action="<%=request.getContextPath()%>/plan/plan/proSyllabus_graCondEdit.do"
				method="post" name="addForm">
				<input type="hidden" name="graduateCondition.saveType" id="saveType"
					value="${graduateCondition.saveType}" />
				<input type="hidden" name="graduateCondition.proCode" id="proCode"
					value="${graduateCondition.proCode}" />
				<v:bean clazz="com.sincinfo.zxks.core.plan.GraduateConditionAction"
					form="addForm">
					<h1 style="height: 30px; line-height: 30px">
						&nbsp;毕业条件设置
					</h1>
					<dl>
						<dt>
							毕业学分：
						</dt>
						<dd>
							<input class="inputText" type="text"
								name="graduateCondition.graduateConditionCredit"
								id="graduateConditionCredit"
								value="${graduateCondition.graduateConditionCredit}" />
							<v:v input="graduateCondition.graduateConditionCredit"
								exp="d(1,1000)">请输入学分（必须是大于0的数字）！</v:v>
						</dd>
					</dl>
					<div class="clear"></div>
					<dl>
						<dt>
							毕业条件说明：
						</dt>
						<dd>
							<textarea class="inputTextarea"
								name="graduateCondition.graduateConditionDescribe"
								id="graduateConditionDescribe" rows="5" cols="30">${graduateCondition.graduateConditionDescribe}</textarea>
						</dd>
					</dl>
					<div class="clear"></div>
					<div
						style="width: 100%; background-color: #D8E6F6; margin: 0; padding: 3px 0 0 0; height: 26px">
						<input class="inputButton" type="submit" value="保 存" />
						&nbsp;
						<input class="inputButton" type="button" value="返 回"
							onclick="location.href='<%=request.getContextPath()%>/plan/proSyllabus_qry.do'" />
					</div>
				</v:bean>
			</form>
		</div>
	</body>
</html>
