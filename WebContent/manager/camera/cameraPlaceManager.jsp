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
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/common/js/clientfuns.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/common/js/riverdefine.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/common/js/riverajax.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/common/js/common.js"></script>
		<script type="text/javascript">
			function checkqry() {
				/*
				var city = document.getElementsByName('city')[0];
				if (city.value=='') {
					alert('请选择市区名称！');
					return false;
				}
				*/
				
				return true;
			}
			
			function addCamera() {
				var city = document.getElementsByName('city')[0];
				if (city.value=='') {
					alert('请选择市区名称！');
					return;
				}
				
				location.href='cpm_insertPre.do?city=' + city.value;
			}
		</script>
	</head>

	<body>
		<form action="cpm_list.do" method="post" name="form1">
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

			<!--查询条件开始-->
			<div class="tjcx">
				<dl>
					<dt>
						市区名称：
					</dt>
					<dd>
						<select name="city" class="inputText inputTextS">
							<c:forEach var="it" items="${citys }" varStatus="vs">
								<option value="${it.id }"
									<c:if test="${it.id==city }">selected='selected'</c:if>>
									${it.name }
								</option>
							</c:forEach>
						</select>
					</dd>
				</dl>
				<dl>
					<dt>
						摄像点编号：
					</dt>
					<dd>
						<input name="cameraPlaceCode" value="${cameraPlaceCode }"
							class="inputText inputTextM" type="text" />
					</dd>
				</dl>
				<dl>
					<dt>
						摄像点名称：
					</dt>
					<dd>
						<input name="cameraPlaceName" value="${cameraPlaceName }"
							class="inputText inputTextM" type="text" />
					</dd>
				</dl>
			</div>
			<div class="button">
				<input class="inputButton" type="submit" value="查 询" onclick="return checkqry()" />
				<input class="inputButton" type="button" value="添 加" onclick="addCamera()"/>
			</div>
			<div class="clear"></div>
		</form>
		<!--查询条件end-->

		<!--列表样式---表格----开始-->
		<div class="list">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<thead>
					<tr>
						<th width="30">
							序号
						</th>
						<th>
							考区名称
						</th>
						<th>
							摄像点编号
						</th>
						<th>
							摄像点名称
						</th>
						<th>
							联系人
						</th>
						<th>
							联系电话
						</th>
						<th>
							状态
						</th>
						<th width="80">
							操作
						</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="it" items="${places }" varStatus="vs">
						<tr>
							<td align="center">
								${vs.index+1+page.pagenum*page.pagesize }
							</td>
							<td align="center">
								<c:choose>
									<c:when test="${empty it.examAreaName }">
										无
									</c:when>
									<c:otherwise>
										<label title="${it.examAreaName }">${fn:substring(it.examAreaName,0,10) }...</label>
									</c:otherwise>
								</c:choose>
							</td>
							<td align="center">
								${it.cameraPlaceCode }
							</td>
							<td align="center">
								${it.cameraPlaceName }
							</td>
							<td align="center">
								${it.cameraPlaceLinkMan }
							</td>
							<td align="center">
								${it.cameraPlaceLinkTelephon }
							</td>
							<td align="center">
								<c:choose>
									<c:when test="${it.isUse==1 }">
    	已启用
    </c:when>
									<c:otherwise>
    	未启用
    </c:otherwise>
								</c:choose>
							</td>
							<td align="center">
								<a
									href="cpm_modifyPre.do?cameraPlaceCode=${it.cameraPlaceCode }">修改</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="9">
							<span> ${page.pageInfo } </span>
						</td>
					</tr>
				</tfoot>
			</table>
		</div>
		<!--列表样式---表格----end-->


	</body>
</html>
