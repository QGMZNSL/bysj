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
		<form action="<%=request.getContextPath()%>/plan/basepro_Edit.do"	method="post" name="addForm">
		<v:bean	clazz="com.sincinfo.zxks.core.plan.BaseproAction" form="addForm">		
			<div class="infoedit">
				<h1>
					修改专业
				</h1>
				<dl>
					<dt style='width:150px;'>
						专业代码：
					</dt>
					<dd>
						<input class="inputText" type="text" name="basepro.proCode" id="proCode" value="${basepro.proCode}" />
						<v:v input="basepro.proCode" exp="s(1,10)">请输入专业代码！(长度不能超过10位)</v:v>
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
									<option value="${pp.levelCode}" <c:if test="${pp.levelCode==basepro.levelCode}">selected</c:if>>
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
						<select class="inputText" name="basepro.proPartcode" id="proPartcode" >
							<option value="">
								--- 请选择 ---
							</option>
							<c:forEach items="${ proseqList}" var="pp">
									<option value="${ pp.proPartCode}" <c:if test="${pp.proPartCode==basepro.proPartcode}">selected</c:if>>
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
						<select class="inputText" name="basepro.proTypecode" id="proTypecode" >
							<option value="">
								--- 请选择 ---
							</option>
								<c:forEach items="${ protypelList}" var="pt">
									<option value="${ pt.proTypeCode}" <c:if test="${pt.proTypeCode==basepro.proTypecode}">selected</c:if>>
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
						<input type="radio"  name="basepro.isGb" value="1" <c:if test="${basepro.isGb==1}">checked</c:if>/>是
						<input type="radio"  name="basepro.isGb" value="0" <c:if test="${basepro.isGb==0}">checked</c:if>/>否
					</dd>
				</dl>
				<dl>
					<dt style='width:150px;'>
						状态：
					</dt>
					<dd>
						<input type="radio" id="isUse" name="basepro.isUse" value="1" <c:if test="${basepro.isUse==1}">checked</c:if>/>开考
						<input type="radio" id="isUse" name="basepro.isUse" value="0" <c:if test="${basepro.isUse==0}">checked</c:if>/>停考
					</dd>
				</dl>
				<dl>
					<dt style='width:150px;'>
						是否允许新生报考：
					</dt>
					<dd>
						<input type="radio" id="isAllowNewStu" name="basepro.isAllowNewStu" value="1" <c:if test="${basepro.isAllowNewStu==1}">checked</c:if>/>允许
						<input type="radio" id="isAllowNewStu" name="basepro.isAllowNewStu" value="0" <c:if test="${basepro.isAllowNewStu==0}">checked</c:if>/>不允许
					</dd>
				</dl>
				<div class="clear"></div>
			</div>
			<div class="button">
				<input class="inputButton" type="submit" value="确 定" />
				<input class="inputButton" type="reset"  value="重 置" />
				<input class="inputButton" type="button" value="返 回"
					onclick="location.href='<%=request.getContextPath()%>/plan/basepro_Show.do';" />
			</div>
			</v:bean>
		</form>
	</body>
</html>
