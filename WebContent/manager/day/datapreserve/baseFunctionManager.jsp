<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="v" uri="http://nbf.river.org/vxds"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>基础功能维护</title>
		<link href="<%=request.getContextPath()%>/common/style/style.css"
			rel="stylesheet" type="text/css" />
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/common/js/riverdefine.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/common/js/riverajax.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/common/js/jquery-1.3.2.min.js"></script>
		<script type="text/javascript">
		function doChange( pmId, methodCode, switchFlag) {
			// 显示内容
			var openSign = "<img src='<%=request.getContextPath()%>/common/style/right.gif' />";
			var closeSign = "<img src='<%=request.getContextPath()%>/common/style/error.gif' />";
			var showArea;
			var flagHide;
			var showInner;
		
			// 提示内容
			var methodName;
			if ( methodCode == 1) {
				methodName = "网上交费"
				showArea = $('#nSwitch_' + pmId);
				flagHide = $('#m' + pmId + '_pm1');
			} else if ( methodCode == 2) {
				methodName = "现场交费"
				showArea = $('#sSwitch_' + pmId);
				flagHide = $('#m' + pmId + '_pm2');
			} else if ( methodCode == 3) {
				methodName = "银行交费"
				showArea = $('#bSwitch_' + pmId);
				flagHide = $('#m' + pmId + '_pm3');
			}
			
			// 开启状态
			var flagName;
			if ( switchFlag == 1) {
				flagName = "开启";
				showInner = openSign;
			} else {
				flagName = "关闭";
				showInner = closeSign;
			}
			
			// ajax与后台交互
			var ajaxUrl = "<%=request.getContextPath()%>/day/datapreserve/funcMgr_changeSwitch.do";
			// 参数
			var param = "&placePmethodId=" + pmId;
			param += "&payMethod=" + methodCode;
			param += "&methodFlag=" + switchFlag;
			$.ajax({
			     type: "post", // 传值方式
			     url: ajaxUrl,    // 接收后台地址
			     data: "t=" + new Date().getTime() + param,   // 数据
			     async: false,  // 是否异步	
			     success: function( msg) { 
					// ajax返回，更新页面显示效果，并提示
					if ( "success" == msg) {
						showArea.html( showInner);
						flagHide.val( switchFlag);
						alert(methodName + "已被" + flagName + "！");
					} else {
						alert("操作失败！");
					}
			     }
			});
		}
		
		function changeSwitch( pmId, methodCode) {
			var switchFlag = 0;
			if ( methodCode == 1) {
				// 获取当前标志
				switchFlag = $('#m' + pmId + '_pm1').val();
				// 返回后的标记
				switchFlag = (parseInt(switchFlag) + 1) % 2;
				// 调用后台
				doChange( pmId, methodCode, switchFlag);
			} else if ( methodCode == 2) {
				// 获取当前标志
				switchFlag = $('#m' + pmId + '_pm2').val();
				// 返回后的标记
				switchFlag = (parseInt(switchFlag) + 1) % 2;
				// 调用后台
				doChange( pmId, methodCode, switchFlag);
			} else if ( methodCode == 3) {
				// 获取当前标志
				switchFlag = $('#m' + pmId + '_pm3').val();
				// 返回后的标记
				switchFlag = (parseInt(switchFlag) + 1) % 2;
				// 调用后台
				doChange( pmId, methodCode, switchFlag);
			} 
		}
		
		$(document).ready( function() {
			// 初始化查询条件
			$('#cityCode').val("${ cityCode}");
			
			// 提示查询结果
			<c:if test="${ empty payMethodList &&  not empty cityCode}">
				alert("没有查询到结果！");
			</c:if>
		});
		</script>
	</head>

	<body>
		<!--导航---start-->
		<div class="dqwz">
			<div style="margin-right: 10px;">
				<span style="cursor: pointer;" id="helpSpan"
					onclick="javascript:showHelpDiv('Z53013');">- 帮助 -</span>
				<span class="pageCode">No.Z53013</span>${currNavigation}
			</div>
		</div>
		<jsp:include page="/help/helpJs.jsp" />
		<iframe name="ifm" id="helpIfm" scrolling="no" frameborder="0"
			width="100%" style="display: none;"
			onload="this.height=this.contentWindow.document.body.scrollHeight+13"></iframe>
		<!--导航---end-->

		<!--查询条件开始-->
		<form method="post" name="searchForm"
			action="<%=request.getContextPath()%>/day/datapreserve/funcMgr_qryCityAreas.do">
			<div class="tjcx">
				<dl>
					<dt>
						市区名称：
					</dt>
					<dd>
						<select class="inputText inputTextM" name="cityCode" id="cityCode">
							<option value="">
								--- 请选择 ---
							</option>
							<c:forEach items="${ cityList}" var="city">
								<option value="${ city.cityCode}">
									${ city.cityName}
								</option>
							</c:forEach>
						</select>
					</dd>
				</dl>
				<dl>
					<dt>
						功能类型：
					</dt>
					<dd>
						<select class="inputText inputTextM" id="functionClass"
							name="functionClass">
							<option>
								交费
							</option>
						</select>
					</dd>
				</dl>
			</div>
			<div class="button">
				<input class="inputButton" type="submit" value="查 询" />
			</div>
		</form>
		<div class="clear"></div>
		<!--查询条件end-->

		<!--列表样式---表格----开始-->
		<c:if test="${ not empty payMethodList}">
			<div class="list">
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
					<thead>
						<tr>
							<th>
								序号
							</th>
							<th>
								考区名称
							</th>
							<th>
								网上交费
							</th>
							<th>
								现场交费
							</th>
							<th>
								银行交费
							</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${ payMethodList}" var="pMethod"
							varStatus="ctNum">
							<tr>
								<th align="center" width="30px">
									${ ctNum.count}
								</th>
								<td align="center">
									${ pMethod.examAreaName}
									<input type="hidden" name="m${ pMethod.placePmethodId}_pm1"
										id="m${ pMethod.placePmethodId}_pm1"
										value="${ pMethod.payMethod1}" />
									<input type="hidden" name="m${ pMethod.placePmethodId}_pm2"
										id="m${ pMethod.placePmethodId}_pm2"
										value="${ pMethod.payMethod2}" />
									<input type="hidden" name="m${ pMethod.placePmethodId}_pm3"
										id="m${ pMethod.placePmethodId}_pm3"
										value="${ pMethod.payMethod3}" />
								</td>
								<td align="center" width="15%">
									<span id="nSwitch_${ pMethod.placePmethodId}" style="cursor: hand;" onclick="changeSwitch( '${ pMethod.placePmethodId}', 1)"> <c:choose>
											<c:when test="${ pMethod.payMethod1 == 1}">
												<img
													src="<%=request.getContextPath()%>/common/style/right.gif" />
											</c:when>
											<c:otherwise>
												<img
													src="<%=request.getContextPath()%>/common/style/error.gif" />
											</c:otherwise>
										</c:choose> </span>
								</td>
								<td align="center" width="15%">
									<span id="sSwitch_${ pMethod.placePmethodId}" style="cursor: hand;" onclick="changeSwitch( '${ pMethod.placePmethodId}', 2)"> <c:choose>
											<c:when test="${ pMethod.payMethod2 == 1}">
												<img
													src="<%=request.getContextPath()%>/common/style/right.gif" />
											</c:when>
											<c:otherwise>
												<img
													src="<%=request.getContextPath()%>/common/style/error.gif" />
											</c:otherwise>
										</c:choose> </span>
								</td>
								<td align="center" width="15%">
									<span id="bSwitch_${ pMethod.placePmethodId}" style="cursor: hand;" onclick="changeSwitch( '${ pMethod.placePmethodId}', 3)"> <c:choose>
											<c:when test="${ pMethod.payMethod2 == 1}">
												<img
													src="<%=request.getContextPath()%>/common/style/right.gif" />
											</c:when>
											<c:otherwise>
												<img
													src="<%=request.getContextPath()%>/common/style/error.gif" />
											</c:otherwise>
										</c:choose> </span>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<!--列表样式---表格----end-->
		</c:if>
	</body>
</html>
