<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="v" uri="http://nbf.river.org/vxds"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>市县维护</title>
		<link href="<%=request.getContextPath()%>/common/style/style.css"
			rel="stylesheet" type="text/css" />
	<script type="text/javascript"
			src="<%=request.getContextPath()%>/common/js/riverdefine.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/common/js/riverajax.js"></script>
	</head>

	<body>
		<form action="<%=request.getContextPath()%>/manager/day/message/address_upDate.do"  method="post" name="form1">
		<v:bean clazz="com.sincinfo.zxks.core.day.datapreserve.MainTenanceAddressAction"
					form="form1">
			<!--当前位置---开始-->
			<div class="dqwz">
			<div style="margin-right: 10px;">
				<span style="cursor: pointer;" id="helpSpan"
					onclick="javascript:showHelpDiv('Z53016');">- 帮助 -</span>
				<span class="pageCode">No.Z53016</span>日常管理>数据维护>省市联系地址维护>修改维护
			</div>
		</div>
		<jsp:include page="/help/helpJs.jsp"/>
		<iframe name="ifm" id="helpIfm" scrolling="no" frameborder="0"
			width="100%" style="display: none;"
			onload="this.height=this.contentWindow.document.body.scrollHeight+13"></iframe>
			<!--当前位置---结束-->

			<!--编辑页面----开始-->
			<div class="infoedit">
				<dl>
					<dt>
						办理业务名称：
					</dt>
					<dd>
						<select class="inputText inputTextM"  name="mta.operCode">
								<option value="${allAddressList[0].operCode}">
									<c:if test="${allAddressList[0].operCode eq '01'}">
										报名
									</c:if>
									<c:if test="${allAddressList[0].operCode eq '11'}">
										毕业证申请
									</c:if>
									<c:if test="${allAddressList[0].operCode eq '12'}">
										转档
									</c:if>
								</option>
							</select>
					</dd>
				</dl>
				<dl>
					<dt>
						详细地址：
					</dt>
					<dd>
						<input class="inputText" type="text" name="mta.studPostalAddress" value="${ allAddressList[0].studPostalAddress}" />
					<span>(必须填写)</span>
							<v:v input="mta.studPostalAddress" />
					</dd>
				</dl>
				<dl>
					<dt>
						乘车路线：
							<input type="hidden" name="cityCode" value="${allAddressList[0].cityCode }" />
					</dt>
					<dd>
						<input class="inputText" type="text" name="mta.byBus" value="${allAddressList[0].byBus }" />
					</dd>
				</dl>
				<dl>
					<dt>
						联系电话：
					</dt>
					<dd>
						<input class="inputText" type="text" name="mta.linkTelephone" value="${allAddressList[0].linkTelephone }" />
						<span>(必须填写)</span> <v:v input="mta.linkTelephone" /> 
					</dd>
				</dl>
				<div class="clear"></div>
			</div>

			<div class="button">
				<input class="inputButton" type="submit" value="保 存" />
				<input class="inputButton" type="button" value="返 回"
					onclick="history.back();" />
			</div>
			<div class="clear"></div>
			<!--编辑页面----end-->
			</v:bean>
		</form>
	</body>
</html>
