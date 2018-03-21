<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="v" uri="http://nbf.river.org/vxds"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>照片采集</title>
		<link href="<%=request.getContextPath()%>/common/style/style.css"
			rel="stylesheet" type="text/css" />
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/common/js/jquery-1.3.2.min.js"></script>
		<script type="text/javascript">
		function qryStudentSubmitChk() {
			if ( $('#preapplyCode').val() == "") {
				alert("请输入考生预报名号！");
				return false;
			}
		
			return true;
		}
		
		function init() {
			$('.disNone').hide();
			<c:if test="${ not empty preapplyCode && empty studentInfo}">
				alert("没有查询到对应的考生信息！");
			</c:if>
		}
		
		$( function() {
			init();
		});
		</script>
	</head>

	<body>
		<!--导航---start-->
		<div class="dqwz">
			<span>帮助</span><span class="pageCode">功能编号:${ currFunctionId}</span>${currNavigation}
		</div>
		<!--导航---end-->

		<!--查询条件开始-->
		<div class="clear"></div>
		<!--查询条件end-->

		<c:if test="${ not empty studentInfo}">
			<!--显示详细页面----开始-->
			<div class="xsxx1">
				<table border="0" cellpadding="0" cellspacing="0" width="100%">
					<tbody>
						<tr>
							<th width="25%">
								姓名：
							</th>
							<td width="25%">
								&nbsp;${ studentInfo.studName}
							</td>
							<th width="25%">
								性别：
							</th>
							<td>
								&nbsp;${ studentInfo.genderName}
							</td>
							<td rowspan="5" width="126" align="center">
								<img src="${ studentInfo.studPhotoFile1}" height="150"
									width="120" />
							</td>
						</tr>
						<tr>

							<th>
								证件号码：
							</th>
							<td>
								&nbsp;${ studentInfo.studIdnum} （ ${
								studentInfo.studIdnoTypeName} ）
							</td>
							<th>
								新生报名号：
							</th>
							<td>
								&nbsp;${ studentInfo.preapplyCode}
							</td>
						</tr>
						<tr>
							<th>
								报考专业：
							</th>
							<td>
								&nbsp;${ studentInfo.firstProName}
							</td>
							<th>
								报考层次：
							</th>
							<td>
								&nbsp;${ studentInfo.levelName}
							</td>
						</tr>
						<tr>
							<th>
								出生日期：
							</th>
							<td>
								&nbsp;${ studentInfo.studBirthday}
							</td>
							<th>
								民族：
							</th>
							<td>
								&nbsp;${ studentInfo.studFolkName}
							</td>
						</tr>
						<tr>
							<th>
								政治面貌：
							</th>
							<td>
								&nbsp;${ studentInfo.studPoliticsName}
							</td>
							<th>
								文化程度：
							</th>
							<td>
								&nbsp;${ studentInfo.studSchoolAgeName}
							</td>
						</tr>
						<tr>
							<th>
								电子邮箱：
							</th>
							<td>
								&nbsp;${ studentInfo.studEmail}
							</td>
							<th>
								职业：
							</th>
							<td colspan="2">
								&nbsp;${ studentInfo.studOccupationName}
							</td>
						</tr>
						<tr>
							<th>
								户籍：
							</th>
							<td>
								&nbsp;${ studentInfo.studHukouCharacterName}
							</td>
							<th>
								户籍所在地：
							</th>
							<td colspan="2">
								&nbsp;${ studentInfo.studHukouLocationName}
							</td>
						</tr>
						<tr>

							<th>
								联系电话：
							</th>
							<td>
								&nbsp;${ studentInfo.studTelephone}
							</td>
							<th>
								移动电话：
							</th>
							<td colspan="2">
								&nbsp;${ studentInfo.studMobilePhone}
							</td>
						</tr>
						<tr>
							<th>
								邮政编码：
							</th>
							<td>
								&nbsp;${ studentInfo.studPostalCode}
							</td>
							<th>
								通讯地址：
							</th>
							<td colspan="2">
								&nbsp;${ studentInfo.studPostalAddress}
							</td>
						</tr>
						<tr>
							<th>
								报考市区：
							</th>
							<td>
								&nbsp;${ studentInfo.cityName}
							</td>
							<th>
								报考考区：
							</th>
							<td colspan="2">
								&nbsp;${ studentInfo.examAreaName}
							</td>
						</tr>
						<tr>
							<th>
								考生类别：
							</th>
							<td>
								&nbsp;
								<c:choose>
									<c:when test="${ studentInfo.studTypeCode == 0}">社会考生</c:when>
									<c:when test="${ studentInfo.studTypeCode == 1}">助学单位考生</c:when>
									<c:otherwise>&nbsp;</c:otherwise>
								</c:choose>
							</td>
							<th>
								助学单位：
							</th>
							<td colspan="2">
								&nbsp;${ studentInfo.stipendUnitName}
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="5">
								<input class="inputButton" type="button" id="cancelBtn"
									value="内容修改"
									onclick="location.href='<%=request.getContextPath()%>/manager/camera/photoGatherEdit.jsp';" />
								<form target="_blank" id="toGather"
									action="<%=request.getContextPath()%>/manager/camera/pho_preGather.do"
									method="post">
									<input class="inputButton" type="button" id="gatherBtn"
										value="采集照片"
										onclick="document.getElementById('toGather').submit();" />
									<input type="hidden" name="preapplyCode"
										value="${ preapplyCode}" />
								</form>
								<input class="inputButton" type="button" id="cancelBtn"
									value="撤销照片"
									onclick="location.href='<%=request.getContextPath()%>/manager/camera/pho_cancelPhoto.do?preapplyCode=${ preapplyCode}';" />
								<form target="_blank" id="toPrint"
									action="<%=request.getContextPath()%>/manager/camera/infoconfirm_print.do"
									method="post">
									<input class="inputButton" type="button" id="printBtn"
										value="打 印"
										onclick="document.getElementById('toPrint').submit();" />
									<input type="hidden" name="preapplyCode"
										value="${ preapplyCode}" />
								</form>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
			<!--显示详细页面----结束-->
		</c:if>
	</body>
</html>
