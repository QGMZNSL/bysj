<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="v" uri="http://nbf.river.org/vxds"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>摄像点管理</title>
		<link href="<%=request.getContextPath()%>/common/style/style.css"
			rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="<%=request.getContextPath()%>/common/js/clientfuns.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/common/js/calendar.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/common/js/riverdefine.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/common/js/riverajax.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/common/js/common.js"></script>
		<script type="text/javascript">
			// 表单提交检查
			function checksubmit() {
				if (!checkBoxs("codes")) {
					alert('请选择至少1个考区名称！');
					return false;
				}
				return true;
			}
		</script>
	</head>

	<body>
		<!--当前位置---开始-->
		<div class="dqwz">
			<div style="margin-right: 10px;">
				<span style="cursor: pointer;" id="helpSpan"
					onclick="javascript:showHelpDiv('Z61001');">- 帮助 -</span>
				<span class="pageCode">No.Z61001</span>${currNavigation}
			</div>
		</div>
		<jsp:include page="/help/helpJs.jsp"/>
		<iframe name="ifm" id="helpIfm" scrolling="no" frameborder="0"
			width="100%" style="display: none;"
			onload="this.height=this.contentWindow.document.body.scrollHeight+13"></iframe>
		<!--当前位置---结束-->

		<!--编辑页面----开始-->
		<form action="cpm_insert.do" method="post" name="form1">
			<v:bean
				clazz="com.sincinfo.zxks.core.camera.CameraPlaceManageAction"
				form="form1">
			<div class="infoedit">
				<h1>
					填写摄像点信息
				</h1>
				<dl>
					<dt>
						地市名称：
					</dt>
					<dd>
						${genericEntity.name }
						<input class="inputText" type="hidden" name="place.cityCode" value="${genericEntity.id }" readonly="readonly"/>
						<span>(必须填写)</span><v:v input="place.cityCode" />
					</dd>
				</dl>
				<dl>
					<dt>
						摄像点编号：
					</dt>
					<dd>
						<input class="inputText" type="text" name="place.cameraPlaceCode" value="${place.cameraPlaceCode }" />
						<span>(必须填写)</span><v:v input="place.cameraPlaceCode" />
					</dd>
				</dl>
				<dl>
					<dt>
						摄像点名称：
					</dt>
					<dd>
						<input class="inputText" type="text" name="place.cameraPlaceName" value="${place.cameraPlaceName }" />
						<span>(必须填写)</span><v:v input="place.cameraPlaceName" />
					</dd>
				</dl>
				    <dl>
					<dt>
						详细地址：
					</dt>
					<dd>
						<input class="inputText" type="text" name="place.cameraPlaceAddress" value="${place.cameraPlaceAddress }" />
						<span>(必须填写)</span><v:v input="place.cameraPlaceAddress" />
					</dd>
				</dl>
				<dl>
					<dt>
						乘车路线：
					</dt>
					<dd>
						<input class="inputText" type="text" name="place.byBus" value="${place.byBus }" />
						<span>(必须填写)</span><v:v input="place.byBus" />
					</dd>
				</dl>
				<dl>
					<dt>
						每日容纳人数：
					</dt>
					<dd>
						<input class="inputText" type="text" name="place.maxPeople" value="${place.maxPeople }" />
						<span>(必须填写)</span><v:v input="place.maxPeople" />
					</dd>
				</dl>
				<dl>
					<dt>
						考区名称：
					</dt>
					<dd>
						<c:forEach var="it" items="${examAreas }" varStatus="vs">
							<input type="checkbox" name="codes" value="${it.id }" />${it.name }&nbsp;&nbsp;&nbsp;&nbsp;
						</c:forEach>
					</dd>
				</dl>
				<div class="clear"></div>
				<h1>
					填写摄像点联系信息
				</h1>
				<dl>
					<dt>
						联系人：
					</dt>
					<dd>
						<input class="inputText" type="text" name="place.cameraPlaceLinkMan" value="${place.cameraPlaceLinkMan }" />
						<span>(必须填写)</span><v:v input="place.cameraPlaceLinkMan" />
					</dd>
				</dl>
				<dl>
					<dt>
						联系电话：
					</dt>
					<dd>
						<input class="inputText" type="text" name="place.cameraPlaceLinkTelephon" value="${place.cameraPlaceLinkTelephon }" />
						<span>(必须填写)</span><v:v input="place.cameraPlaceLinkTelephon" />
					</dd>
				</dl>
				<dl>
					<dt>
						状态：
					</dt>
					<dd>
						<input type="radio" name="place.isUse" value="1" checked="checked"/>
						启用
						<input type="radio" name="place.isUse" value="0"/>
						禁用
						<span>(必须填写)</span><v:v input="place.isUse" />
					</dd>
				</dl>
				<div class="clear"></div>
		</div>
		<div class="clear"></div>
		<div class="button">
			<input class="inputButton" type="submit" value="保 存" onclick="return checksubmit()" />
			<input class="inputButton" type="reset"
				onclick="history.go(-1);" value="取 消" />
		</div>
		</v:bean>
		</form>
		<!--编辑页面----end-->
	</body>
</html>
