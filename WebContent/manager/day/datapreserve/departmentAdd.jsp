<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="v" uri="http://nbf.river.org/vxds"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>部门添加</title>
		<link href="<%=request.getContextPath()%>/common/style/style.css"
			rel="stylesheet" type="text/css" />
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/common/js/riverdefine.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/common/js/riverajax.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/common/js/jquery-1.3.2.min.js"></script>
		<script type="text/javascript">
		</script>
	</head>

	<body>
		<!--导航---start-->
		<div class="dqwz">
			<div style="margin-right: 10px;">
				<span style="cursor: pointer;" id="helpSpan"
					onclick="javascript:showHelpDiv('Z53001');">- 帮助 -</span>
				<span class="pageCode">No.Z53001</span>${currNavigation}
			</div>
		</div>
		<jsp:include page="/help/helpJs.jsp" />
		<iframe name="ifm" id="helpIfm" scrolling="no" frameborder="0"
			width="100%" style="display: none;"
			onload="this.height=this.contentWindow.document.body.scrollHeight+13"></iframe>
		<!--导航---end-->

		<!--编辑页面----开始-->
		<form
			action="<%=request.getContextPath()%>/day/datapreserve/pstMgr_departmentsAdd.do"
			method="post" name="addForm">
			<v:bean
				clazz="com.sincinfo.zxks.core.day.datapreserve.PositionAction"
				form="addForm">
				<div class="infoedit">
					<dl>
						<dt>
							部门名称：
						</dt>
						<dd>
							<input class="inputText" type="text"
								name="department.departmentName" id="departmentName" value="" />
							<v:v input="department.departmentName">请输入部门名称！</v:v>
						</dd>
					</dl>
					<dl>
						<dt>
							部门级别：
						</dt>
						<dd>
							<select class="inputText inputTextM"
								name="department.departmentGrade" id="departmentGrade">
								<c:forEach items="${ grades}" var="grd">
									<option value="${ grd.entityCode}">
										${ grd.entityName}
									</option>
								</c:forEach>
							</select>
							<v:v input="department.departmentGrade">请选择部门级别！</v:v>
						</dd>
					</dl>
				</div>
				<div class="clear"></div>
				<div class="button">
					<input class="inputButton" type="submit" value="保 存" />
					<input class="inputButton" type="button" value="返 回"
						onclick="location.href='<%=request.getContextPath()%>/day/datapreserve/pstMgr_departments.do';" />
				</div>
			</v:bean>
		</form>
	</body>
</html>
