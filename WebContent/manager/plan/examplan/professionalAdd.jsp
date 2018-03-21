<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="v" uri="http://nbf.river.org/vxds"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>专业设置</title>
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
		<!-- 页面导航 -->
		<div class="dqwz">
			<div style="margin-right: 10px;">
				<span style="cursor: pointer;" id="helpSpan"
					onclick="javascript:showHelpDiv('Z21004');">- 帮助 -</span>
				<span class="pageCode">No.Z21004</span>${currNavigation}
			</div>
		</div>
		<jsp:include page="/help/helpJs.jsp" />
		<iframe name="ifm" id="helpIfm" scrolling="no" frameborder="0"
			width="100%" style="display: none;"
			onload="this.height=this.contentWindow.document.body.scrollHeight+13"></iframe>
		<!-- 编辑区域 -->
			<form action="<%=request.getContextPath()%>/plan/basepro_Add.do" method="post" name="addForm">
					<v:bean clazz="com.sincinfo.zxks.core.plan.BaseproAction" form="addForm">
			<div class="infoedit">
				<h1>
					添加专业
				</h1>
				<dl>
					<dt style='width:150px;'>
						专业代码：
					</dt>
					<dd>
						<input class="inputText" type="text" name="basepro.proCode" id="proCode" value="${basepro.proCode}" />
						<v:v input="basepro.proCode" exp="s(1,10)">请输入专业代码！(最长不能超过10位)</v:v>
					</dd>
				</dl>
				<dl>
					<dt style='width:150px;'>
						专业名称：
					</dt>
					<dd>
						<input class="inputText" type="text" name="basepro.proName" id="proName" value="${basepro.proName}" />
						<v:v input="basepro.proName" exp="s(1,15)">请输入专业名称！(长度不能超过15位)</v:v>
					</dd>
				</dl>
				<dl>
					<dt style='width:150px;'>
						专业层次：
					</dt>
					<dd>
						<select class="inputText" name="basepro.levelCode" id="levelCode" >
							<option value="">
								--- 请选择 ---
							</option>
							<c:forEach items="${planlevelList}" var="pp">
									<option value="${pp.levelCode}">
										${ pp.levelName}
									</option>
								</c:forEach>
						</select>
						<v:v input="basepro.levelCode" exp="">请选择专业层次!</v:v>
					</dd>
				</dl>
				<dl>
					<dt style='width:150px;'>
						专业分类：
					</dt>
					<dd>
						<select class="inputText" name="basepro.proPartcode" id="proPartcode">
							<option value="">
								--- 请选择 ---
							</option>
								<c:forEach items="${ proseqList}" var="pp">
									<option value="${ pp.proPartCode}">
										${ pp.proPartName}
									</option>
								</c:forEach>
						</select>
						<v:v input="basepro.proPartcode" exp="">请选择专业分类!</v:v>
					</dd>
				</dl>
				<dl>
					<dt style='width:150px;'>
						专业类型：
					</dt>
					<dd>
						<select class="inputText" name="basepro.proTypecode" id="proTypecode">
							<option value="">
								--- 请选择 ---
							</option>
								<c:forEach items="${ protypelList}" var="pt">
									<option value="${ pt.proTypeCode}">
										${ pt.proTypeName}
									</option>
								</c:forEach>
						</select>
						<v:v input="basepro.proTypecode" exp="">请选择专业类型!</v:v>
					</dd>
				</dl>
				<dl>
					<dt style='width:150px;'>
						是否全国统考计划：
					</dt>
					<dd>
						<input type="radio" name="basepro.isGb" id="isGb" value="1" checked="checked" />是
						<input type="radio" name="basepro.isGb" id="isGb" value="0" />否
					</dd>
				</dl>
				<dl>
					<dt style='width:150px;'>
						状态：
					</dt>
					<dd>
						<input type="radio"  name="basepro.isUse" id="isUse" value="1" checked="checked" />开考
						<input type="radio"  name="basepro.isUse" id="isUse" value="0" />停考
					</dd>
				</dl>
				<div class="clear"></div>
			</div>
			<div class="button">
				<input class="inputButton" type="submit" value="确 定" />
				<input class="inputButton" type="reset"  value="重 置" />
				<input class="inputButton" type="button" value="返 回"
					onclick="location.href='<%=request.getContextPath()%>/plan/basepro_Show.do'" />
			</div>
			</v:bean>
		</form>
	</body>
</html>
