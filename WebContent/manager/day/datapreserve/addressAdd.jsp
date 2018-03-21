<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="v" uri="http://nbf.river.org/vxds"%>
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
		<form
			action="<%=request.getContextPath()%>/manager/day/message/address_addAddress.do"
			id="form1" method="post" name="form1">
			<v:bean
				clazz="com.sincinfo.zxks.core.day.datapreserve.MainTenanceAddressAction"
				form="form1">
				<!--当前位置---开始-->
				<div class="dqwz">
					<div style="margin-right: 10px;">
						<span style="cursor: pointer;" id="helpSpan"
							onclick="javascript:showHelpDiv('Z53016');">- 帮助 -</span>
						<span class="pageCode">No.Z53016</span>日常管理>数据维护>省市联系地址维护>添加维护
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
							<select class="inputText inputTextM" id="operCode"
								name="operCode">
								<option value="01">
									报名
								</option>
								<option value="11">
									毕业证申请
								</option>
								<option value="12">
									转档
								</option>
							</select>
						</dd>
					</dl>
					<dl>
						<dt>
							详细地址：
						</dt>
						<dd>
							<input class="inputText" type="text" name="studPostalAddress"
								id="studPostalAddress" value="" />
							<span>(必须填写)</span>
							<v:v input="studPostalAddress" />
						</dd>
					</dl>
					<dl>
						<dt>
							乘车路线：
						</dt>
						<dd>
							<input class="inputText" type="text" id="byBus" name="byBus"
								value="" />
						</dd>
					</dl>
					<dl>
						<dt>
							联系电话：
						</dt>
						<dd>
							<input class="inputText" type="text" id="linkTelephone"
								name="linkTelephone" value="" />
							<span><span>(必须填写)</span> <v:v input="linkTelephone" /> </span>
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
