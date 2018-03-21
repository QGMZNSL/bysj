<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>省市联系地址维护</title>
		<link href="<%=request.getContextPath()%>/common/style/style.css"
			rel="stylesheet" type="text/css" />
	</head>

	<body>
		<!--当前位置---开始-->
		<div class="dqwz">
			<div style="margin-right: 10px;">
				<span style="cursor: pointer;" id="helpSpan"
					onclick="javascript:showHelpDiv('Z53016');">- 帮助 -</span>
				<span class="pageCode">No.Z53016</span>${currNavigation}
			</div>
		</div>
		<jsp:include page="/help/helpJs.jsp"/>
		<iframe name="ifm" id="helpIfm" scrolling="no" frameborder="0"
			width="100%" style="display: none;"
			onload="this.height=this.contentWindow.document.body.scrollHeight+13"></iframe>
		<!--当前位置---结束-->

		<form method="post" name="form1"
			action="<%=request.getContextPath()%>/manager/day/message/address_intoAddress.do">
			<!--查询条件开始-->
			<div class="tjcx">
				<dl>
					<dt>
						市区名称：
					</dt>
					<dd>
						<select class="inputText inputTextM" name="cityCode" id="cityCode">
							<c:if test="${optUser.userRole == 1}">
								<option value="610000">
									陕西省
								</option>
							</c:if>
							<c:forEach items="${ cityList}" var="city">
								<option value="${ city.cityCode}"
									<c:if test="${ city.cityCode eq cityCode}">selected = "selected"</c:if>>
									${ city.cityName}
								</option>
							</c:forEach>
						</select>
					</dd>
				</dl>
			</div>
			<div class="button">
				<input class="inputButton" type="submit" value='查看' />
				<input class="inputButton" type="button" value="添 加"
					onclick="location.href='<%=request.getContextPath()%>/manager/day/message/address_intoAdd.do'" />
			</div>
			<div class="clear"></div>
			<!--查询条件end-->

			<!--列表样式---表格----开始-->
			<div class="list">
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
					<thead>
						<tr>
							<th>
								办理业务名称
							</th>
							<th>
								详细地址
							</th>
							<th>
								联系电话
							</th>
							<th>
								乘车路线
							</th>
							<th>
								操作
							</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="al" items="${allAddressList}">
							<tr>
								<td align="center">
									<c:if test="${ al.operCode eq '11'}">毕业证申请</c:if>
									<c:if test="${ al.operCode eq '12'}">转档</c:if>
									<c:if test="${ al.operCode eq '01'}">报名</c:if>
								</td>
								<td align="left">
									${al.studPostalAddress}
								</td>
								<td align="center">
									${al.linkTelephone }
								</td>
								<td align="center">
									${al.byBus }
								</td>




								<td align="center">
									<a href="#"
										onclick="javascript:if(confirm('您确定要删除此记录吗？')){location.href='<%=request.getContextPath()%>/manager/day/message/address_deleteInfo.do?operCode=${al.operCode }&cityCode=${ al.cityCode}';}else{return;};">
										删除 </a>
									<a
										href="<%=request.getContextPath()%>/manager/day/message/address_updateInfo.do?operCode=${al.operCode }&cityCode=${al.cityCode}">
										修改 </a>
								</td>
							</tr>
						</c:forEach>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="7">
								${page.pageInfo }
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
			<!--列表样式---表格----end-->
		</form>
	</body>
</html>
