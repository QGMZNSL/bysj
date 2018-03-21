<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="v" uri="http://nbf.river.org/vxds"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>用户管理</title>
		<link href="<%=request.getContextPath()%>/common/style/style.css"
			rel="stylesheet" type="text/css" />
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/common/js/jquery-1.3.2.min.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/common/js/riverdefine.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/common/js/riverajax.js"></script>
		<script type="text/javascript">
		function switchArea( userRole, speed) {
			$('.disNone').hide();
			$('.area' + userRole).show( speed);
			$('#cityCode').val("");
			
			if ( userRole == 1 ) {
				$('#academyCode').val("");
				$('#cameraPlaceCode').val("");
				$('#unitCode').val("");
				ajaxFitSelect( userRole);
			} else if ( userRole == 2 ) {
				$('#academyCode').val("");
				$('#cameraPlaceCode').val("");
				$('#unitCode').val("");
				ajaxFitSelect( userRole);
			} else if ( userRole == 8) {
				$('#positionCode').val("");
				$('#cameraPlaceCode').val("");
				$('#unitCode').val("");
			} else if ( userRole == 16) {
				$('#positionCode').val("");
				$('#academyCode').val("");
				$('#unitCode').val("");
			}else if(userRole == 32){
				$('#academyCode').val("");
				$('#cameraPlaceCode').val("");
			}
		}
		
		function ajaxFitSelect( userRole) {
			var chgSelectUrl = "<%=request.getContextPath()%>/day/datapreserve/usrMgr_ajaxChgSelect.do?ajaxType=";
			var position = 'positionSct';
			var academy = 'academySct';
			var cameraPlace = 'cameraPlaceSct';
			var unit='unitSct';
			
			var positionShow = "positionDD";
			var academyShow = "academyDD";
			var cameraPlaceShow = "cameraDD";
			var unitShow="unitDD";
			
			var url;
			var param;
			var showId;
			if ( userRole == 1 || userRole == 2) {
				// 省市用户通过userRole来决定
				url = chgSelectUrl + position;
				param = "&ajaxUserRole=" + userRole;
				showId = positionShow;
			} else if ( userRole == 8) {
				url = chgSelectUrl + academy;
				param = "&ajaxCityCode=" + $('#cityCode').val();
				showId = academyShow;
			} else if ( userRole == 16) {
				url = chgSelectUrl + cameraPlace;
				param = "&ajaxCityCode=" + $('#cityCode').val();
				showId = cameraPlaceShow;
			}else if(userRole == 32){
				url = chgSelectUrl + unit;
				param = "&ajaxCityCode=" + $('#cityCode').val();
				showId = unitShow;
			} else {
				alert("客户端异常！code error: 45r！");
				return;
			}
			
			$.ajax({
			     type: "post", // 传值方式
			     url: url,    // 接收后台地址
			     data: "t=" + new Date().getTime() + param,   // 数据
			     async: false,  // 是否异步	
			     success: function( msg) { 
			     	$('#' + showId).html( msg);
			     }
			});
		}
		
		function init() {
			var usrRole = '${ optUser.userRole}';
			$($('.userGender')[0]).attr("checked", true);
			$('.disNone').hide();
			
			// 初始化地市
			$('#cityCode').val('${ optUser.cityCode}');
				
			// 设置用户角色及显示
			var usrRoleCls = $('.userRole');
			for ( var i = 0; usrRoleCls.length; i++) {
				if ( $(usrRoleCls[i]).val() == usrRole) {
					$(usrRoleCls[i]).attr("checked", true);
					break;
				}
			}
			switchArea( usrRole, "");
			
			// 控制disabled
			if ( usrRole == 1) {
				// 省级用户		
			} else if ( usrRole == 2) {
				// 地市用户无法设置省级操作员
				$($('.userRole')[0]).attr("disabled", true);
			} else {
				// 暂无权限
			}
		}
		
		function chkSubmit() {
			// 地市联动主考院校或者摄像点
			var userRoleCheckboxes = $('.userRole');
			var usrRole;
			for ( var i = 0; i < userRoleCheckboxes.length; i++) {
				if ( $(userRoleCheckboxes[i]).attr("checked") == true 
						|| $(userRoleCheckboxes[i]).attr("checked") == "checked") {
					usrRole = $(userRoleCheckboxes[i]).val();
					break;
				}
			}
			
			// 如果是省市用户，则必须选择职位，如果为主考院校或者摄像点用户，则必须选择对应的字段
			if ( usrRole == 1) {
				// 省级用户
				if ( $('#positionCode').val() == "" || $('#positionCode').val() == null) {
					alert("请选择职位！（添加省市用户时，必须选择职位！）");
					return false;
				}
			} else if ( usrRole == 2) {
				// 市级用户
				if ( $('#positionCode').val() == "" || $('#positionCode').val() == null) {
					alert("请选择职位！（添加省市用户时，必须选择职位！）");
					return false;
				}
			} else if ( usrRole == 8) {
				// 主考院校用户
				if ( $('#academyCode').val() == "" || $('#academyCode').val() == null) {
					alert("请选择主考院校！");
					return false;
				}
			} else if ( usrRole == 16) {
				// 摄像点用户
				if ( $('#cameraPlaceCode').val() == "" || $('#cameraPlaceCode').val() == null) {
					alert("请选择摄像点用户！");
					return false;
				}
			} else if(usrRole==32){
				//合作开考单位用户
				if ( $('#unitCode').val() == "" || $('#unitCode').val() == null) {
					alert("请选择合作开考单位！");
					return false;
				}
			
			}
			return true;
		}
		
		$(document).ready( function() {
			$('.userRole').click( function() {
				switchArea( this.value, "slow");
			});
			
			$('#cityCode').change( function() {
				// 地市联动主考院校或者摄像点
				var userRoleCheckboxes = $('.userRole');
				var usrRole;
				for ( var i = 0; i < userRoleCheckboxes.length; i++) {
					if ( $(userRoleCheckboxes[i]).attr("checked") == true 
							|| $(userRoleCheckboxes[i]).attr("checked") == "checked") {
						usrRole = $(userRoleCheckboxes[i]).val();
						break;
					}
				}
				
				// 调用ajax
				if ( usrRole == 8 || usrRole == 16||usrRole == 32) {
					ajaxFitSelect( usrRole);
				}
			});
		
			init();
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

		<!--编辑页面----开始-->
		<form
			action="<%=request.getContextPath()%>/day/datapreserve/usrMgr_saveUser.do?userSaveMethod=forNew"
			method="post" name="userAddForm" onsubmit="return chkSubmit();">
			<v:bean clazz="com.sincinfo.zxks.core.day.datapreserve.UserMgrAction"
				form="userAddForm">
				<div class="infoedit">
					<dl>
						<dt>
							登录名：
						</dt>
						<dd>
							<input class="inputText inputTextM" type="text"
								name="cuser.userName" id="userName" value="" maxlength='16'/>
							<span>(必须填写)</span>
							<v:v input="cuser.userName">请输入登录名！</v:v>
						</dd>
					</dl>
					<dl>
						<dt>
							真实姓名：
						</dt>
						<dd>
							<input class="inputText inputTextM" type="text"
								name="cuser.realName" value="" />
							<span>(必须填写)</span>
							<v:v input="cuser.realName">请输入真实姓名！</v:v>
						</dd>
					</dl>
					<dl>
						<dt>
							性别：
						</dt>
						<dd>
							<c:forEach items="${ cuser.genderList}" var="gender">
								<input type="radio" name="cuser.userGender" class="userGender"
									value="${ gender.code}" />${ gender.name}
							</c:forEach>
							<v:v input="cuser.userGender">请选择性别！</v:v>
						</dd>
					</dl>
					<dl>
						<dt>
							密码：
						</dt>
						<dd>
							<input class="inputText inputTextM" type="password"
								name="cuser.userPassword" value="" />
							<span>(必须填写)</span>
							<v:v input="cuser.userPassword">密码填写不正确，请重新填写！</v:v>
						</dd>
					</dl>
					<dl>
						<dt>
							密码确认：
						</dt>
						<dd>
							<input class="inputText inputTextM" type="password"
								name="passwordRepeat" value="" />
							<span>(必须填写)</span>
							<v:v input="passwordRepeat" property="" exp="_null&se(*cuser.userPassword)">两次输入的密码不一致，请重新填写！</v:v>
						</dd>
					</dl>
					<dl>
						<dt>
							联系电话：
						</dt>
						<dd>
							<input class="inputText inputTextM" type="text"
								name="cuser.telephone" value="" />
							<span>(必须填写)</span>
							<v:v input="cuser.telephone">联系电话格式输入有误，请重新填写！</v:v>
						</dd>
					</dl>
					<dl>
						<dt>
							通讯地址：
						</dt>
						<dd>
							<input class="inputText inputTextM" type="text"
								name="cuser.postalAddress" value="" />
							<span>(必须填写)</span>
							<v:v input="cuser.postalAddress">通讯地址格式输入有误，请重新填写！</v:v>
						</dd>
					</dl>
					<dl>
						<dt>
							用户类型：
						</dt>
						<dd>
							<input type="radio" name="cuser.userRole" class="userRole"
								value="1" />
							省级用户
							<input type="radio" name="cuser.userRole" class="userRole"
								value="2" />
							市级用户
							<input type="radio" name="cuser.userRole" class="userRole"
								value="4" />
							考区用户
							<input type="radio" name="cuser.userRole" class="userRole"
								value="8" />
							主考院校用户
							<input type="radio" name="cuser.userRole" class="userRole"
								value="16" />
							摄像点用户
							<input type="radio" name="cuser.userRole" class="userRole"
								value="32" />
							合作开考单位用户
							<v:v input="cuser.userRole">请选择用户角色！</v:v>
						</dd>
					</dl>
					<dl class="area2 area8 area16 area32 disNone">
						<dt>
							市区名称：
						</dt>
						<dd id="cityDD">
							<select class="inputText inputTextM" name="cuser.cityCode"
								id="cityCode">
								<option value="">
									---请选择---
								</option>
								<c:forEach items="${ cuser.cityList}" var="city">
									<option value="${ city.cityCode}">
										${ city.cityName}
									</option>
								</c:forEach>
							</select>
						</dd>
					</dl>
					<dl class="area1 area2 disNone">
						<dt>
							职位：
						</dt>
						<dd id="positionDD">
							<select class="inputText inputTextM" name="cuser.positionCode"
								id="positionCode">
								<option value="">
									---请选择---
								</option>
								<c:forEach items="${ cuser.positionList}" var="position">
									<option value="${ position.positionCode}">
										${ position.positionName}
									</option>
								</c:forEach>
							</select>
						</dd>
					</dl>
					<dl class="area8 disNone">
						<dt>
							主考院校名称：
						</dt>
						<dd id="academyDD">
							<select class="inputText inputTextM" name="cuser.academyCode"
								id="academyCode">
								<option value="">
									---请选择---
								</option>
								<c:forEach items="${ cuser.academyList}" var="academy">
									<option value="${ academy.academyCode}">
										${ academy.academyName}
									</option>
								</c:forEach>
							</select>
						</dd>
					</dl>
					<dl class="area16 disNone">
						<dt>
							摄像点名称：
						</dt>
						<dd id="cameraDD">
							<select class="inputText inputTextM" name="cuser.cameraPlaceCode"
								id="cameraPlaceCode">
								<option value="">
									---请选择---
								</option>
								<c:forEach items="${ cuser.cameraList}" var="camera">
									<option value="${ camera.cameraPlaceCode}">
										${ camera.cameraPlaceName}
									</option>
								</c:forEach>
							</select>
						</dd>
					</dl>
					<dl class="area32 disNone">
						<dt>
							合作开考单位：
						</dt>
						<dd id="unitDD">
							<select class="inputText inputTextM" name="cuser.unitCode"
								id="unitCode">
								<option value="">
									---请选择---
								</option>
								<c:forEach items="${cuser.unitList}" var="bean">
									<option value="${ bean.unitCode}">
										${ bean.unitName}
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
							<input type="checkbox" name="cuser.userLock" id="userLock"
								value="1" />
							锁定
						</dd>
					</dl>
					<div class="clear"></div>
				</div>

				<div class="button">
					<input class="inputButton" type="submit" value="保 存" />
					<input class="inputButton" type="button"
						onclick="location.href='<%=request.getContextPath()%>/day/datapreserve/usrMgr_userMgr.do';"
						value="返 回" />
				</div>
			</v:bean>
		</form>
		<!--编辑页面----end-->
	</body>
</html>
