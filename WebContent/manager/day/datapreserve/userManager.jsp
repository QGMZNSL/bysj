<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>用户管理</title>
		<link href="<%=request.getContextPath()%>/common/style/style.css"
			rel="stylesheet" type="text/css" />
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/common/js/jquery-1.3.2.min.js"></script>
		<script type="text/javascript">
		$(function() {
			/* 初始化页面 */
			function init() {
				$('#cityCode').val("${ userEntity.cityCode}");	
				$('#userRole').val("${ userEntity.userRole}");	
				$('#userLock').val("${ userEntity.userLock}");	
				$('#userName').val("${ userEntity.userName}");	
				$('#realName').val("${ userEntity.realName}");		
			}init();
		})
		</script>
	</head>

	<body>
		<!--导航---start-->
		<div class="dqwz">
			<div style="margin-right: 10px;">
				<span style="cursor: pointer;" id="helpSpan"
					onclick="javascript:showHelpDiv('Z53002');">- 帮助 -</span>
				<span class="pageCode">No.Z53002</span>${currNavigation}
			</div>
		</div>
		<jsp:include page="/help/helpJs.jsp" />
		<iframe name="ifm" id="helpIfm" scrolling="no" frameborder="0"
			width="100%" style="display: none;"
			onload="this.height=this.contentWindow.document.body.scrollHeight+13"></iframe>
		<!--导航---end-->

		<!--查询条件开始-->
		<form method="post" name="qryUserForm"
			action="<%=request.getContextPath()%>/day/datapreserve/usrMgr_userMgr.do">
			<div class="tjcx">
				<dl>
					<dt>
						市区名称：
					</dt>
					<dd>
						<select class="inputText inputTextM" name="userEntity.cityCode"
							id="cityCode">
							<c:if test="${ optUser.userRole == 1}">
								<option value="610000">
									陕西省
								</option>
							</c:if>
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
						用户类型：
					</dt>
					<dd>
						<select class="inputText inputTextM" name="userEntity.userRole"
							id="userRole">
							<c:forEach items="${ userRoleList}" var="role">
								<option value="${ role.entityCode}">
									${ role.entityName}
								</option>
							</c:forEach>
						</select>
					</dd>
				</dl>
				<dl>
					<dt>
						状态：
					</dt>
					<dd>
						<select class="inputText inputTextM" name="userEntity.userLock"
							id="userLock">
							<option value="">
								全部
							</option>
							<option value="0">
								正常
							</option>
							<option value="1">
								锁定
							</option>
						</select>
					</dd>
				</dl>
				<dl>
					<dt>
						登录名：
					</dt>
					<dd>
						<input class="inputText inputTextM" type="text" id="userName"
							name="userEntity.userName" />
					</dd>
				</dl>
				<dl>
					<dt>
						姓名：
					</dt>
					<dd>
						<input class="inputText inputTextM" type="text" id="realName"
							name="userEntity.realName" />
					</dd>
				</dl>
			</div>
			<div class="button">
				<input class="inputButton" type="submit" value="查 询" />
				<input class="inputButton" type="button"
					onclick="location.href='<%=request.getContextPath()%>/day/datapreserve/usrMgr_addPre.do';"
					value="添 加" />
			</div>
		</form>
		<div class="clear"></div>
		<!--查询条件end-->

		<c:if test="${ not empty userList}">
			<div class="list">
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
					<thead>
						<tr>
							<th width="30px">
								序号
							</th>
							<th>
								登录名
							</th>
							<th>
								真实姓名
							</th>
							<th>
								市区名称
							</th>
							<th>
								主考院校名称
							</th>
							<th>
								摄像点名称
							</th>
							<th>
								登录次数
							</th>
							<th>
								最后登录时间
							</th>
							<th>
								状态
							</th>
							<th width="100">
								操作
							</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${ userList}" var="usr" varStatus="ctNum">
							<tr>
								<td align="center">
									&nbsp;${ page.pagesize * page.pagenum + ctNum.count}
								</td>
								<td align="left">
									&nbsp;${ usr.userName}
								</td>
								<td align="left">
									&nbsp;${ usr.realName}
								</td>
								<td align="center">
									&nbsp;
									<c:choose>
										<c:when
											test="${ usr.cityCode eq '610000' || empty usr.cityCode}">
											陕西省
										</c:when>
										<c:otherwise>
											${ usr.cityName}
										</c:otherwise>
									</c:choose>
								</td>
								<td align="left">
									&nbsp;${ usr.academyName}
								</td>
								<td align="left">
									&nbsp;${ usr.cameraPlaceName}
								</td>
								<td align="center">
									&nbsp;${ usr.loginNum}
								</td>
								<td align="center">
									&nbsp;${ fn:substring(usr.lastLoginTime, 0, 19)}
								</td>
								<td align="center">
									<c:choose>
										<c:when test="${ usr.userLock == 1}">
									锁定
								</c:when>
										<c:otherwise>
									正常
								</c:otherwise>
									</c:choose>
								</td>
								<td align="center">
									<a
										href="<%=request.getContextPath()%>/day/datapreserve/usrMgr_editPre.do?usrnameForEdit=${ usr.userName}">修改</a>&nbsp;
									<a
										href="<%=request.getContextPath()%>/day/datapreserve/usrMgr_powerPre.do?usrnameForEdit=${ usr.userName}&clkUserRole=${ usr.userRole}">修改权限</a>
								</td>
							</tr>
						</c:forEach>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="10">
								<span>${ page.pageInfo}</span>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
		</c:if>
	</body>
</html>
