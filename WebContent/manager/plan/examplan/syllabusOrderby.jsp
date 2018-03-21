<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>专业课程排序</title>
		<link href="<%=request.getContextPath()%>/common/style/style.css"
			rel="stylesheet" type="text/css" />
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/common/js/jquery-1.3.2.min.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/common/js/px.js"></script>
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
		<form
			action="<%=request.getContextPath()%>/plan/proSyllabus_editOrderby.do"
			method="post" name="orderByFrom" onsubmit="dosubmit()">
			<input type="hidden" name="proSyllabus.proCode" id="proCode"
				value="${ basepro.proCode}" />
			<div class="infoedit">
				<dl>
					<dt>
						专业代码：
					</dt>
					<dd>
						${ basepro.proCode}
					</dd>
				</dl>
				<dl>
					<dt>
						专业名称：
					</dt>
					<dd>
						${ basepro.proName}
					</dd>
				</dl>
				<dl>
					<dt>
						专业分类：
					</dt>
					<dd>
						${ basepro.proPartName}
					</dd>
				</dl>
				<dl>
					<dt>
						专业类型：
					</dt>
					<dd>
						${ basepro.proTypeName}
					</dd>
				</dl>
			</div>
			<div class="clear"></div>
			<!-- 结果集 -->
			<div class="list">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td>
							<select size="20" name="sob" id="sob" style="width: 300px;">
								<s:iterator id="psl" value="proSyllabusList">
									<option value="${psl.substituteCode }"
										<c:if test="${psl.substituteCode eq substituteCode}">selected="selected"</c:if>>
										${psl.syllabusName }
									</option>
								</s:iterator>
							</select>
						</td>
						<td width="100%" align="left" valign="middle">
							&nbsp;
							<input class="inputButton" type="button" value="↑"
								onclick="doup()" />
							<br />
							<br />
							&nbsp;
							<input class="inputButton" type="button" value="↓"
								onclick="dodown()" />
						</td>
						<td id="sort">
						</td>
					</tr>
				</table>
			</div>
			<div class="button">
				<input class="inputButton" type="submit" id="orderbySave" value="保存" />
				<input class="inputButton" type="button" value="返 回"
					onclick="location.href='<%=request.getContextPath()%>/plan/proSyllabus_getProSList.do?basepro.proCode=${ basepro.proCode}';" />
			</div>
		</form>
	</body>
</html>
