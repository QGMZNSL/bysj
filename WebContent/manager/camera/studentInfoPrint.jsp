<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="v" uri="http://nbf.river.org/vxds"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>考生信息确认签字</title>
		<link href="<%=request.getContextPath()%>/manager/style/pint.css"
			rel="stylesheet" type="text/css" />
		<style type="text/css">
<!--
.inner_table {
	border-top: #000000 1px solid;
	border-right: #000000 1px solid;
	font-family: "宋体";
	font-size: 12px;
	line-height: 28px;
}

.left_table {
	border-top: #000000 1px solid;
	border-right: #000000 1px solid;
	border-bottom: #000000 1px solid;
	font-family: "宋体";
	font-size: 12px;
	line-height: 28px;
}

.out_table {
	border-top: #000000 1px solid;
	border-right: #000000 1px solid;
	border-left: #000000 2px solid;
	border-bottom: #000000 1px solid;
	font-family: "宋体";
	font-size: 12px;
	line-height: 28px;
}

.inner_table td {
	border-left: #000000 1px solid;
	border-bottom: #000000 1px solid;
}

.table_data_left {
	text-align: center;
}

.table_data_left_v {
	text-align: center;
	line-height: 14px;
}

.title_font {
	font-size: 22px;
	font-weight: bold;
	font-family: "宋体";
	text-align: center;
}

.bianhao {
	font-family: "宋体";
	font-size: 12px;
}

.an {
	width: 79px;
	height: 30px;
	background-image: url(../images/an3.gif);
	border: 0px;
	font-family: "宋体";
	font-size: 12px;
	line-height: 30px;
	color: #ffffff;
}

.red {
	color: #FF0000;
}

input {
	border-top-style: none;
	border-right-style: none;
	border-bottom-style: none;
	border-left-style: none;
}

#pri tr td {
	line-height: 22px;
}

.bx_tb {
	border: 1px solid #000;
	border-collapse: collapse;
}

.bx_tb tr {
	border: 1px solid #000;
	border-collapse: collapse;
}

.bx_tb tr td {
	border: 1px solid #000;
	border-collapse: collapse;
	line-height: 22px;
	padding: 15px 10px;
}

.bx_tb tr th {
	border: 1px solid #000;
	border-collapse: collapse;
	line-height: 22px;
	padding: 15px 10px;
	text-align: center;
	font-weight: normal;
}
-->
</style>
	</head>

	<body>
		<!--显示详细页面----开始-->
		<div style="height: 30px; line-height: 30px; font-size: 12px;">
			<table width="650" border="0" cellpadding="0" cellspacing="0"
				bgcolor="#F7F7F7" style="border-bottom: solid 1px #333333;"
				align="center">
				<tr>
					<td width="353" height="30" align="center" style="font-size: 12px;">
						&nbsp;请在菜单【文件】的【页面设置】中将页边距全部设为
						<span class="red">15</span>毫米
					</td>
					<td width="100" align="center">
						<input name="Submit22" type="button" value="" onclick="printb()"
							style="background: url(/ZK_CORE/manager/style/images/print.gif) no-repeat; width: 80px; height: 20px;" />
					</td>
					<td width="100" align="center" style="font-size: 12px;">
						<input type="button" name="button" id="button" value=""
							onclick="javascript:window.opener=null;window.open('','_self');window.close();"
							style="width: 64px; height: 20px; background: url(/ZK_CORE/manager/style/images/close.gif) no-repeat;" />
					</td>
					<td width="30" align="center">
						&nbsp;

					</td>
				</tr>
			</table>
		</div>
		<div id="pri" style="line-height: 22px;">
			<h2 align="center">
				${year }年${month }月陕西省高等教育自学考试注册
				<c:choose>
					<c:when test="${printtype==1}">信息回执单</c:when>
					<c:otherwise>信息确认单</c:otherwise>
				</c:choose>
			</h2>
			&nbsp;&nbsp;
			<c:choose>
				<c:when test="${printtype==1}">
  准考证号：${student.studExamCode}
  </c:when>
				<c:otherwise>
  新生报名号：${student.preapplyCode}
  </c:otherwise>
			</c:choose>
			<br />
			<table width="100%" border="1" align="center" cellpadding="6"
				cellspacing="0" class="bx_tb"
				style="border-collapse: collapse; line-height: 22px;">
				<tr>
					<th width="13%">
						姓名
					</th>
					<td width="27%">
						${student.studName}
					</td>
					<th width="15%">
						性别
					</th>
					<td width="25%">
						${student.genderName}
					</td>
					<td width="20%" rowspan="4" style="text-align: center">
						<img src="${photoPath }${student.studPhotoFile1}" width="129"
							height="162" class="pic" />
					</td>
				</tr>
				<tr>
					<th>
						证件类型
					</th>
					<td>
						${student.idTypeName}
					</td>
					<th>
						证件号码
					</th>
					<td>
						${student.studIdnum}
					</td>
				</tr>
				<tr>
					<th>
						报考专业
					</th>
					<td>
						${student.firstProName}
					</td>
					<th>
						报考层次
					</th>
					<td>
						${student.levelName}
					</td>
				</tr>
				<tr>
					<th>
						出生日期
					</th>
					<td>
						${student.studBirthday}
					</td>
					<th>
						民族
					</th>
					<td>
						${student.studFolkName}
					</td>
				</tr>
				<tr>
					<th>
						政治面貌
					</th>
					<td>
						${student.studPoliticsName}
					</td>
					<th>
						文化程度
					</th>
					<td colspan="2">
						${student.studSchoolAgeName}
					</td>
				</tr>
				<tr>
					<th>
						户籍
					</th>
					<td>
						${student.studHukouCharacterName}
					</td>
					<th>
						户籍所在地
					</th>
					<td colspan="2">
						${student.studHukouLocationName}
					</td>
				</tr>
				<tr>
					<th>
						电子邮箱
					</th>
					<td>
						${student.studEmail}
					</td>
					<th>
						职业
					</th>
					<td colspan="2">
						${student.studOccupationName}
					</td>
				</tr>
				<tr>
					<th>
						联系电话
					</th>
					<td>
						${student.studTelephone}
					</td>
					<th>
						移动电话
					</th>
					<td colspan="2">
						${student.studMobilePhone}
					</td>
				</tr>
				<tr>
					<th>
						通讯地址
					</th>
					<td>
						${student.studPostalAddress}
					</td>
					<th>
						邮政编码
					</th>
					<td colspan="2">
						${student.studPostalCode}
					</td>
				</tr>
				<tr>
					<th>
						报考地区
					</th>
					<td>
						${student.cityName}
					</td>
					<th>
						报考考区
					</th>
					<td colspan="2">
						${student.examAreaName}
					</td>
				</tr>
				<tr>
					<th>
						考生类别
					</th>
					<td>
						<c:if test="${student.studTypeCode==0}">社会考生</c:if>
						<c:if test="${student.studTypeCode==1}">助学单位考生</c:if>
					</td>
					<th>
						助学单位
					</th>
					<td colspan="2">
						${student.stipendUnitName}
					</td>
				</tr>
				<c:if test="${printtype!=1}">
					<tr>
						<td colspan="5" height="149" valign="top">
							&nbsp;&nbsp;本人承诺对所填报内容及证明材料的真实性负责。
							<br />
							<br />
							<br />
							<br />
							<span
								style="width: 400px; text-align: right; display: block; float: left;">考生签名：</span><span
								style="height: 25px; width: 130px; text-align: center; display: block; float: right;">年
								&nbsp; &nbsp; &nbsp;月 &nbsp; &nbsp; &nbsp;日</span>
						</td>
					</tr>
				</c:if>
			</table>
			<br />
<c:if test="${printtype!=1}">
  注：本表一式两份，考生保留一份，交市区考办一份。
  </c:if>
<c:if test="${printtype==1}">
注：考生凭此回执单领取准考证。<br/>
<c:if test="${student.cityCode=='01'}"> 
领证地点：与本人现场采集照片和确认信息的地址一致</br>
领证时间：2018年4月9日9时——4月13日17时（周六周日休息），集体单位所报新生由集体单位统一发放
</c:if>
</c:if>
		</div>
		<div style="height: 30px; line-height: 30px; font-size: 12px;">
			<table width="650" border="0" cellpadding="0" cellspacing="0"
				bgcolor="#F7F7F7" style="border-top: solid 1px #333333;"
				align="center">
				<tr>
					<td width="353" height="30" align="center" style="font-size: 12px;">
						&nbsp;请在菜单【文件】的【页面设置】中将页边距全部设为
						<span class="red">15</span>毫米
					</td>
					<td width="100" align="center">
						<input name="Submit22" type="button" value="" onclick="printb()"
							style="background: url(/ZK_CORE/manager/style/images/print.gif) no-repeat; width: 80px; height: 20px;" />
					</td>
					<td width="100" align="center" style="font-size: 12px;">
						<input type="button" name="button" id="button" value=""
							onclick="javascript:window.close();"
							style="width: 64px; height: 20px; background: url(/ZK_CORE/manager/style/images/close.gif) no-repeat;" />
					</td>
					<td width="30" align="center">
						&nbsp;

					</td>
				</tr>
			</table>
		</div>

		<!--显示详细页面----结束-->

	</body>
</html>
<iframe id="prifrm" src="" style="width: 0px; height: 0px;"></iframe>
<SCRIPT LANGUAGE="JavaScript">
<!--
	function printb() { 
    var printF = window.frames["prifrm"]; 
	//alert(printF);
    printF.focus(); //注意，这句代码不能少。   
    printF.document.body.innerHTML =document.getElementById("pri").innerHTML;   
    printF.print();  
} 
//-->
</SCRIPT>