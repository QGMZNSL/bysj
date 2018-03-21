<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>用户信息明细</title>
	<%String rgPath=request.getContextPath();%>
	<link href="<%=rgPath%>/common/style/style.css"
		rel="stylesheet" type="text/css" />
	</head>
<body>
<!--导航---start-->
<div class="dqwz">
<span>帮助</span><span class="pageCode">功能编号:${currFunctionId}</span>${currNavigation}
</div>
<!--导航---end-->
<br/>
<div class="xsxx" style="width:100%">
<br/>
<c:if test="${not empty studentInfo.studInformationConfirmSign and studentInfo.studInformationConfirmSign=='0'}">
<c:choose>
<c:when test="${empty netPhotoPath}">
<input type="button" class="inputButton" value="采集照片"
	onclick="javascript:window.open('<%=rgPath%>/manager/camera/pho_preGather.do?preapplyCode=${studentInfo.preapplyCode}');"/>
</c:when>
<c:otherwise>
<input class="inputButton" type="button" id="cancelBtn" value="撤销照片"
	onclick="location.href='<%=rgPath%>/manager/camera/pho_cancelPhoto.do?preapplyCode=${studentInfo.preapplyCode}';" />
</c:otherwise>
</c:choose>
<input type="button" id="savePhotoBtn" class="inputButton" value="修 改" 
	onclick="location.href='<%=rgPath%>/manager/camera/pho_modify.do?ssIdno=${studentInfo.studIdnum}';"/>
<input type="button" id="goBackBtn" class="inputButton"
	onclick="location.href='<%=rgPath%>/manager/camera/infoconfirm_confirm.do?preapplyCode=${studentInfo.preapplyCode}'" value="确认信息" />
</c:if>
<input class="inputButton" type="button" id="printBtn" value="打印确认单"
	onclick="javascript:window.open('<%=rgPath%>/manager/camera/infoconfirm_print.do?preapplyCode=${studentInfo.preapplyCode}');"
<c:if test="${empty netPhotoPath}"> disabled</c:if>/>
<input class="inputButton" type="button" value="返 回"
					onclick="location.href='<%=request.getContextPath()%>/manager/camera/pho_view.do';" />
<br/><br/>
<table border="0" cellpadding="0" cellspacing="0" width="100%">
<tr>
	<th width="25%">
		证件号码：
	</th>
	<td width="25%">
		&nbsp;${studentInfo.studIdnum}（${studentInfo.studIdnoTypeName}）
	</td>
	<th width="25%">
		出生日期：
	</th>
	<td width="25%">
		&nbsp;${studentInfo.studBirthday}
	</td>
	<td rowspan="6" width="126" align="center">
	新采集到的照片：<br/>
<c:choose>
<c:when test="${empty netPhotoPath}">
<img src="<%=rgPath%>/noPhoto.jpg"/>
</c:when>
<c:otherwise>
<img src="${netPhotoPath}"  height="150" width="121"/><!--height="150" width="120"   -->
</c:otherwise>
</c:choose>
	</td>
</tr>
<tr>
	<th>
		姓名：
	</th>
	<td>
&nbsp;${studentInfo.studName}
	</td>
	<th>
		性别：
	</th>
	<td>
&nbsp;${studentInfo.genderName}
	</td>
</tr>
<tr>
	<th>
		报考专业：
	</th>
	<td>
&nbsp;${studentInfo.proName}
	</td>
	<th>
		报考层次：
	</th>
	<td>
&nbsp;${studentInfo.levelName}
	</td>
</tr>
<tr>
	<th>
		新生报名号：
	</th>
	<td>
&nbsp;${studentInfo.preapplyCode}
	</td>
	<th>
		民族：
	</th>
	<td>
&nbsp;${studentInfo.studFolkName}
	</td>
</tr>
<tr>
	<th>
		政治面貌：
	</th>
	<td>
&nbsp;${studentInfo.studPoliticsName}
	</td>
	<th>
		文化程度：
	</th>
	<td>
&nbsp;${studentInfo.studSchoolAgeName}
	</td>
</tr>
<tr>
	<th>
		电子邮箱：
	</th>
	<td>
&nbsp;${studentInfo.studEmail}
	</td>
	<th>
		职业：
	</th>
	<td>
&nbsp;${studentInfo.studOccupationName}
	</td>
</tr>
<tr>
	<th>
		户籍：
	</th>
	<td>
&nbsp;${studentInfo.studHukouCharacterName}
	</td>
	<th>
		户籍所在地：
	</th>
	<td>
&nbsp;${studentInfo.studHukouLocation}
	</td>
	<td rowspan="5" align="center">
	原身份证上照片：<br/>
<c:choose>
<c:when test="${empty studentInfo.studPhotoFile3}">
<img src="<%=rgPath%>/noPhoto.jpg"/>
</c:when>
<c:otherwise>
<img src="${idcardnetPhotoPath}"/>
</c:otherwise>
</c:choose>	
	</td>
</tr>
<tr>
	<th>
		联系电话：
	</th>
	<td>
&nbsp;${studentInfo.studTelephone}
	</td>
	<th>
		移动电话：
	</th>
	<td>
&nbsp;${studentInfo.studMobilePhone}
	</td>
</tr>
<tr>
	<th>
		邮政编码：
	</th>
	<td>
&nbsp;${studentInfo.studPostalCode}
	</td>
	<th>
		通讯地址：
	</th>
	<td>
&nbsp;${studentInfo.studPostalAddress}
	</td>
</tr>
<tr>
	<th>
		报考市区：
	</th>
	<td>
&nbsp;${studentInfo.cityName}
	</td>
	<th>
		报考考区：
	</th>
	<td>
&nbsp;${studentInfo.examAreaName}
	</td>
</tr>
<tr>
	<th>
		考生类别：
	</th>
	<td>
&nbsp;
<c:if test="${studentInfo.studTypeCode==0}">社会考生</c:if>
<c:if test="${studentInfo.studTypeCode==1}">助学单位考生</c:if>
	</td>
	<th>
		助学单位：
	</th>
	<td>
&nbsp;${studentInfo.stipendUnitName}
	</td>
</tr>
<c:if test="${studentInfo.manualInput=='2'}">
<tr>
	<th>
		未采集身份证原因：
	</th>
	<td colspan='4'>
&nbsp;${baseStudentNoIdno.noIdnoReason}
	</td>
</tr>
<tr>
	<th>
		证明材料：
	</th>
	<td colspan='4'>
&nbsp;${baseStudentNoIdno.document}
	</td>
</tr>
</c:if>
</table>
</div>
	</body>
</html>