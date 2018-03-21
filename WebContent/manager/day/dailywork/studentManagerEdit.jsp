<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>考生管理</title>
		<link href="<%=request.getContextPath()%>/common/style/style.css"
			rel="stylesheet" type="text/css" />
		<link href="<%=request.getContextPath()%>/common/js/ui.datepicker.css"
			rel="stylesheet" type="text/css" />
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/common/js/jquery-1.3.2.min.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/common/js/ui.datepicker-zh-CN.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/common/js/ui.datepicker.js"></script>
		<script type="text/javascript">
		function chkModifyProve() {
			if ( $('#changeTypeCode').val() == '') {
				alert("信息读取异常！");
				return false;
			}
			if ( $('#modifyReason').val() == '') {
				alert("请填写修改原因！");
				return false;
			}
			if ( $('#proveMaterial').val() == '') {
				alert("请填写对应证明材料来证明该考生符合修改条件！");
				return false;
			}
			return true;
		}
		
		function chkOldNewInfos() {
			var changeTypeCode = $('#changeTypeCode').val();
			switch ( parseInt(changeTypeCode)) {
				case 1: 
					// 姓名
					if ( $('#oldStudName').val() == "") {
						return false;
					}
					if ( $('#newStudName').val() == "") {
						alert("请填写新姓名！");
						return false;
					}
					break;
				case 2: 
					// 证件类型
					if ( $('#oldIdnoType').val() == "") {
						return false;
					}
					if ( $('#newIdnoType').val() == "") {
						alert("请选择新证件类型！");
						return false;
					}
					break;
				case 3: 
					// 证件号码
					if ( $('#oldIdnum').val() == "") {
						return false;
					}
					if ( $('#newIdnum').val() == "") {
						alert("请填写新证件号码！");
						return false;
					}
					break;
				case 4: 
					// 性别
					if ( $('#oldGender').val() == "") {
						return false;
					}
					var newGenders = $('.newGender');
					for ( var i = 0; i < newGenders.length; i++) {
						if ( $(newGenders[i]).attr("checked") == true) {
							return true;
						};
					}
					alert("请选择性别！");
					return false;
				case 5: 
					// 出生日期
					if ( $('#oldBirthday').val() == "") {
						return false;
					}
					if ( $('#newBirthday').val() == "") {
						alert("请填写新出生日期！");
						return false;
					}
					break;
				case 6: 
					// 民族
					if ( $('#oldStudFolk').val() == "") {
						return false;
					}
					if ( $('#newStudFolk').val() == "") {
						alert("请选择新民族！");
						return false;
					}
					break;
				case 7: 
					// 照片只修改原因及材料
					break;
				default:
					break;
			}
			return true;
		}
		
		function chkMdInfos() {
			if ( !chkOldNewInfos()) {
				return false;
			}
			
			if ( !chkModifyProve()) {
				return false;
			}
			
			return true;
		}
		
		function chkSubmit() {
			var url = "#";
			
			// 照片的提交地址以及新开页面
			if ( $('#changeTypeCode').val() == '7') {
				$('#modifyContentForm').attr('target', '_blank');
				url = "<%=request.getContextPath() %>/day/dailywork/studMgr_applyPhotoChange.do";
			} else {
				$('#modifyContentForm').attr('target', '_self');
				url = "<%=request.getContextPath() %>/day/dailywork/studMgr_saveModifyApply.do";
			}
			
			if ( chkMdInfos()) {
				$('#modifyContentForm').attr('action', url);
				$('#modifyContentForm').submit();
			}
		}
		
		$(document).ready( function() {
			$('#changeTypeCode').change( function() {
				$('.disNone').slideUp("slow");
				
				var changeType;
				switch ( parseInt(this.value)) {
					case 1: 
						changeType = 'names';
						break;
					case 2: 
						changeType = 'idnumType';
						break;
					case 3: 
						changeType = 'idnum';
						break;
					case 4: 
						changeType = 'gender';
						break;
					case 5: 
						changeType = 'birthday';
						break;
					case 6: 
						changeType = 'fork';
						break;
					case 7: 
						changeType = 'photo';
						break;
					default:
						break;
				}
				$('.' + changeType).slideDown("slow");
			});
			
			$('#saveBtn').click( chkSubmit);
		
			$('.disNone').hide();
			$('#changeTypeCode').val("1");
			$('.names').show();
			
			$("#newBirthday").datepicker($.extend({ dateFormat: 'yyyy-MM-dd', showOn: 'both', buttonImage: '<%=request.getContextPath()%>/common/js/calendarNull.gif', buttonImageOnly: true},$.datepicker.regional['zh-CN']));
		});
		</script>
	</head>

	<body>
		<!--导航---start-->
		<div class="dqwz">
			<span>帮助</span><span class="pageCode">功能编号:${ currFunctionId}</span>${currNavigation}
		</div>
		<!--导航---end-->

		<!--编辑页面----开始-->
		
		<form action="" target="" method="post" name="modifyContentForm" id="modifyContentForm">
			<div class="infoedit">
			<table border='0' cellpadding='0' cellspacing='0' style="word-wrap:break-word;word-break:break-all;overflow:hidden;table-layout:fixed;">
			<tr><td valign='top' align='left' width='40%'>
		
				<!--查询条件end-->
				<h1>
					修改基本信息
				</h1>
				<dl>
					<dt>
						准考证号：
					</dt>
					<dd>
						${ student.studExamCode}
						<input type="hidden" name="studExamCode" value="${ student.studExamCode}" />
						<input type="hidden" name="cityCode" value="${ student.cityCode}" />
					</dd>
				</dl>
				<dl>
					<dt>
						修改内容：
					</dt>
					<dd>
						<select id="changeTypeCode" name="changeTypeCode" style="width:200px;height:20px;line-height:20px;">
							<c:forEach items="${ changeTypeList}" var="ctype">
								<option value="${ ctype.changeTypeCode}">${ ctype.changeTypeName}</option>									
							</c:forEach>
						</select>
					</dd>
				</dl>
				<dl class="disNone idnumType">
					<dt>
						原证件类型：
					</dt>
					<dd>
						${ student.studIdnoTypeName}
						<input type="hidden" name="oldIdnoType" value="${ student.studIdNoType}" />
					</dd>
				</dl>
				<dl class="disNone idnumType">
					<dt>
						新证件类型：
					</dt>
					<dd>
						<select style="width:200px;height:20px;line-height:20px;margin:1px 0;" name="newIdnoType" id="newIdnoType">
							<option value="">--- 请选择 ---</option>
							<c:forEach items="${ idNoTypeList}" var="idNoType">
								<option value="${ idNoType.code}">${ idNoType.name}</option>
							</c:forEach>
						</select>
					</dd>
				</dl>
				<dl class="disNone idnum">
					<dt>
						原证件号码：
					</dt>
					<dd>
						<input style="width:193px;height:20px;line-height:20px;margin:1px 0;" type="text" name="oldIdnum" id="oldIdnum" readonly="readonly" style="border: 0px;" value="${ student.studIdnum}" />
					</dd>
				</dl>
				<dl class="disNone idnum">
					<dt>
						新证件号码：
					</dt>
					<dd>
						<input style="width:193px;height:20px;line-height:20px;margin:1px 0;" type="text" name="newIdnum" id="newIdnum" />
					</dd>
				</dl>
				<dl class="disNone names">
					<dt>
						原姓名：
					</dt>
					<dd>
						<input style="width:193px;height:20px;line-height:20px;margin:1px 0;" type="text" name="oldStudName" id="oldStudName" readonly="readonly" style="border: 0px;" value="${ student.studName}" />
					</dd>
				</dl>
				<dl class="disNone names">
					<dt>
						新姓名：
					</dt>
					<dd>
						<input style="width:193px;height:20px;line-height:20px;margin:1px 0;" type="text" name="newStudName" id="newStudName" />
					</dd>
				</dl>
				<dl class="disNone gender">
					<dt>
						原性别：
					</dt>
					<dd>
						${ student.genderName}
						<input type="hidden" name="oldGender" id="oldGender" value="${ student.studGender}" />
					</dd>
				</dl>
				<dl class="disNone gender">
					<dt>
						新性别：
					</dt>
					<dd>
						<c:forEach items="${ genderList}" var="gender">
							<input type="radio" name="newGender" class="newGender"
								<c:if test="${ gender.code == student.studGender}">checked="checked"</c:if>
								value="${ gender.code}" />${ gender.name}
						</c:forEach>
					</dd>
				</dl>
				<dl class="disNone birthday">
					<dt>
						原出生日期：
					</dt>
					<dd>
						<input style="width:193px;height:20px;line-height:20px;margin:1px 0;" type="text" name="oldBirthday" id="oldBirthday" readonly="readonly" style="border: 0px;" value="${ student.studBirthday}" />
					</dd>
				</dl>
				<dl class="disNone birthday">
					<dt>
						新出生日期：
					</dt>
					<dd>
						<input style="width:193px;height:20px;line-height:20px;margin:1px 0;" type="text" id="newBirthday" name="newBirthday" readonly="readonly" />
					</dd>
				</dl>
				<dl class="disNone fork">
					<dt>
						原民族：
					</dt>
					<dd>
						${ student.studFolkName}
						<input type="hidden" name="oldStudFolk" id="oldStudFolk" value="${ student.studFolk}" />
					</dd>
				</dl>
				<dl class="disNone fork">
					<dt>
						新民族：
					</dt>
					<dd>
						<select style="width:200px;height:20px;line-height:20px;margin:1px 0;" name="newStudFolk" id="newStudFolk">
							<option value="">--- 请选择 ---</option>
							<c:forEach items="${ forkList}" var="fork">
								<option value="${ fork.code}">${ fork.name}</option>
							</c:forEach>
						</select>
					</dd>
				</dl>
				<dl class="disNone photo">
					<dt>
						原照片：
					</dt>
					<dd>
						<div>
							<img src="${ student.studPhotoFile1}" width="120" height="150" />
						</div>
					</dd>
				</dl>
				<dl>
					<dt>
						修改原因：
					</dt>
					<dd>
						<select style="width:200px;height:20px;line-height:20px;margin:1px 0;" id="modifyReason" name="modifyReason">
						<option value="">--- 请选择 ---</option>
						<option value="姓名变更">姓名变更</option>
						<option value="证件类型变更">证件类型变更</option>
						<option value="证件号码变更">证件号码变更</option>
						<option value="性别变更">性别变更</option>
						<option value="出生日期变更">出生日期变更</option>
						<option value="民族变更">民族变更</option>
						<option value="照片变更">照片变更</option>
						</select>
					</dd>
				</dl>
				<dl>
					<dt>
						证明材料：
					</dt>
					<dd>
						<input type="text" style="width:193px;height:20px;line-height:20px;margin:1px 0;" id="proveMaterial" name="proveMaterial"></input>
					</dd>
				</dl>
				</td>
			
				<td valign='top' align='left' style='color:red;'>
			
					<table border='0' cellpadding='0' cellspacing='0' style="word-wrap:break-word;word-break:break-all;overflow:hidden;table-layout:fixed;">
							<tr><td>
						<c:forEach var="bean" items="${changeReasons}" varStatus="h">
							<b>信息变更内容:</b>${bean.changeTemplate}&nbsp;&nbsp;&nbsp;&nbsp;
							<b>信息变更时间:</b>${bean.changeApplyTime}<br/>
							<b>信息变更原因:</b>${bean.changeReason}
							<hr/>
						</c:forEach>
					
						
						</td></tr>
					</table>
			
				</td></tr>
				</table>
				<div class="clear"></div>
			</div>
			<div class="button">
				<input type="hidden" name="fillinBy" value="${ fillinBy}" />
				<input class="inputButton" type="button" id="saveBtn" value="保 存" />
				<input class="inputButton" type="button" id="backBtn" value="返 回"
					onclick="history.back();" />
			</div>
		</form>
		<!--编辑页面----end-->
	</body>
</html>
