<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>用户管理</title>
		<link href="<%=request.getContextPath()%>/common/style/style.css"
			rel="stylesheet" type="text/css" />
		<style type="text/css">
.allTdWidth td {
	width: 160px;
}
</style>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/common/js/riverdefine.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/common/js/riverajax.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/common/js/jquery-1.3.2.min.js"></script>
		<script type="text/javascript">
		function sctAll( classId) {
			var chboxes = $('#' + classId + " :checkbox");
			for ( var i = 0; i < chboxes.length; i++) {
				if ( $(chboxes[i]).attr("checked") == true || $(chboxes[i]).attr("checked") == "checked") {
					$(chboxes[i]).attr("checked", false);
				} else {
					$(chboxes[i]).attr("checked", true);
				}
			}
		}
		
		$( function() {
			// 获取初始化文本
			var power = "${ cuser.powerArray}";
			
			// 初始化多选框
			var chboxes = $(":checkbox");
			chboxes.attr("checked", false);
			var ptVal = 0;
			for ( var i = 0; i < chboxes.length; i++) {
				ptVal = power.substring($(chboxes[i]).val() - 1, $(chboxes[i]).val());
				if ( ptVal == 1) $(chboxes[i]).attr("checked", true);
			}
		});
		</script>
	</head>

	<body>
		<!--当前位置---开始-->
		<div class="dqwz">
			<div style="margin-right: 10px;">
				<span style="cursor: pointer;" id="helpSpan"
					onclick="javascript:showHelpDiv('Z53002');">- 帮助 -</span>
				<span class="pageCode">No.Z53002</span>${currNavigation}
			</div>
		</div>
		<jsp:include page="/help/helpJs.jsp" />
		<iframe name="ifm" id="helpIfm" scrolling="no" frameborder="0"
			width="100%" style="display: none;"
			onload="this.height=this.contentWindow.document.body.scrollHeight+13"></iframe>
		<!--当前位置---结束-->
		<form
			action="<%=request.getContextPath()%>/day/datapreserve/usrMgr_savePower.do"
			method="post" name="powerForm" id="powerForm">
			<div class="infoedit">
				<h1>
					设置权限
				</h1>
				<c:forEach items="${ msList}" var="module">
					<c:if test="${ not empty module.modules}">
						<dl>
							<dt>
								${ module.clazz.className}
							</dt>
							<dd>
								（
								<a href="#" onclick="sctAll('${ module.clazz.classId}');">反选</a>）
							</dd>
						</dl>
						<dl>
							<dt>
								&nbsp;
							</dt>
							<dd>
								<table border="0" cellspacing="0" cellpadding="0"
									class="allTdWidth" id="${ module.clazz.classId}">
									<tr>
										<c:forEach items="${ module.modules}" var="sysM"
											varStatus="ctNum">
											<td>
												${ sysM.moduleId}.
												<input type="checkbox" name="pwrIndexes"
													value="${ sysM.moduleId}" />
												${ sysM.moduleName}
											</td>
											<c:if test="${ctNum.last && (ctNum.count % 4 != 0)}">
												<c:forEach begin="${ctNum.count % 4}" end="3">
													<td>
														&nbsp;
													</td>
												</c:forEach>
											</c:if>
										<c:if test="${ ctNum.count % 4 == 0 && !ctNum.last}">
									</tr>
									<tr>
										</c:if>
										</c:forEach>
									</tr>
								</table>
							</dd>
						</dl>
						<div class="clear"></div>
					</c:if>
				</c:forEach>
			</div>
			<div class="clear"></div>
			<div class="button">
				<input class="inputButton" type="submit" value="保 存" />
				<input class="inputButton" type="button"
					onclick="location.href='<%=request.getContextPath()%>/day/datapreserve/usrMgr_userMgr.do';"
					value="返 回" />
				<input type="hidden" name="cuser.userName"
					value="${ cuser.userName}" />
			</div>
		</form>
	</body>
</html>
