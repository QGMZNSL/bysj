<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>考生信息查看</title>
		<link href="<%=request.getContextPath()%>/common/style/style.css"
			rel="stylesheet" type="text/css" />
		<style type="text/css">
.topMargin {
	margin-top: 10px;
}

.topMargin td {
	text-align: center;
}
</style>
	</head>

	<body>
		<!--当前位置---开始-->
		<div class="dqwz">
	<div  style="margin-right: 10px;">
<span style="cursor:pointer; " id="helpSpan" onclick="javascript:showHelpDiv('Z41002');">- 帮助 -</span>
		<span class="pageCode">No.Z41002</span>${currNavigation}
	</div>
</div>
<jsp:include page="/help/helpJs.jsp"/>
<iframe name="ifm" id="helpIfm" scrolling="no" frameborder="0" width="100%" style="display: none;" onload="this.height=this.contentWindow.document.body.scrollHeight+13"></iframe>

		<!--显示详细页面----开始-->
		<div class="xsxx">
			<table border="0" cellpadding="0" cellspacing="0" width="100%">
				<caption>
					考生基本信息
				</caption>
				<tbody>
					<tr>
						<th>
							姓名：
						</th>
						<td>
							${stud.studName}&nbsp;
						</td>
						<th>
							性别：
						</th>
						<td>
							${stud.genderName}&nbsp;
						</td>
						<td rowspan="5" width="160" style="text-align: center;">
							<img src="${stud.studPhotoFile1 }" width="120" height="160" />
						</td>
					</tr>
					<tr>
						<th>
							证件号码：
						</th>
						<td>
							${stud.studIdnum}（${stud.studIdnoTypeName}）&nbsp;
						</td>
						<th>
							准考证号：
						</th>
						<td>
							${stud.studExamCode}&nbsp;
						</td>
					</tr>
					<tr>
						<th>
							报考专业：
						</th>
						<td>
							${stud.proCode}|${stud.proName}&nbsp;
						</td>
						<th>
							报考层次：
						</th>
						<td>
							${stud.levelName}&nbsp;
						</td>
					</tr>
					<tr>
						<th>
							出生日期：
						</th>
						<td>
							${stud.studBirthday}&nbsp;
						</td>
						<th>
							民族：
						</th>
						<td>
							${stud.studFolkName}&nbsp;
						</td>
					</tr>
					<tr>
						<th>
							政治面貌：
						</th>
						<td>
							${stud.studPoliticsName}&nbsp;
						</td>
						<th>
							文化程度：
						</th>
						<td>
							${stud.studSchoolAgeName}&nbsp;
						</td>
					</tr>
					<tr>
						<th>
							户籍：
						</th>
						<td>
							${stud.studHukouCharacterName}&nbsp;
						</td>
						<th>
							户籍所在地：
						</th>
						<td colspan="2">
							${stud.studHukouLocationName}&nbsp;
						</td>
					</tr>
					<tr>
						<th>
							电子邮箱：
						</th>
						<td>
							${stud.studEmail}&nbsp;
						</td>
						<th>
							职业：
						</th>
						<td colspan="2">
							${stud.studOccupationName}&nbsp;
						</td>
					</tr>
					<tr>
						<th>
							联系电话：
						</th>
						<td>
							${stud.studTelephone}&nbsp;
						</td>
						<th>
							移动电话：
						</th>
						<td colspan="2">
							${stud.studMobilePhone}&nbsp;
						</td>
					</tr>
					<tr>
						<th>
							邮政编码：
						</th>
						<td>
							${stud.studPostalCode}&nbsp;
						</td>
						<th>
							通讯地址：
						</th>
						<td colspan="2">
							${stud.studPostalAddress}&nbsp;
						</td>
					</tr>
					<tr>
						<th>
							报考市区：
						</th>
						<td>
							${stud.cityName}&nbsp;
						</td>
						<th>
							报考考区：
						</th>
						<td colspan="2">
							${stud.examAreaName}&nbsp;
						</td>
					</tr>
					<tr>
						<th>
							考生类别 ：
						</th>
						<td>
							<c:if test="${stud.studTypeCode=='0'}">
								社会考生
							</c:if>
							<c:if test="${stud.studTypeCode=='1'}">
								助学单位考生
							</c:if>
							&nbsp;
						</td>
						<th>
							助学单位：
						</th>
						<td colspan="2">
							<c:if test="${stud.studTypeCode=='1'}">
							${stud.stipendUnitCode}-${stud.stipendUnitName}
							</c:if>
							&nbsp;
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="clear"></div>
		<!--列表样式---表格----开始-->
		<div class="list topMargin">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<caption>
					考生合格课程
				</caption>
				<thead>
					<tr>
						<th width="30">
							序号
						</th>
						<th>
							课程代码
						</th>
						<th>
							课程名称
						</th>
						<th>
							成绩
						</th>
						<th>
							成绩来源
						</th>
						<th>
							合格时间
						</th>
					</tr>
				</thead>
				<tbody>
					<c:choose>
						<c:when test="${empty stuSyllabusDataList}">
							<tr>
								<td colspan="6" align="center">
									没有记录
								</td>
							</tr>
						</c:when>
						<c:otherwise>
							<c:forEach var="bean" items="${stuSyllabusDataList}"
								varStatus="h">
								<tr>
									<td>
										${h.index+1 }
									</td>
									<td>
										${bean.syllabusCode}
									</td>
									<td align='left'>
										${bean.syllabusName}
									</td>
									<td>
										${bean.studScore}&nbsp;
									</td>
									<td align='left'>
										${bean.studPassReasonContent}
									</td>
									<td>
										${fn:substring(bean.studPassTime,0,11) }
									</td>
								</tr>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
		</div>
		<div class="clear"></div>

		<!--考生免考课程信息 有待开发-->
		<div class="list topMargin">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<caption>
					考生免考课程信息
				</caption>
				<thead>
					<tr>
						<th width="32">
							序号
						</th>
						<th>
							免考课程
						</th>
						<th>
							免考描述
						</th>
						<th>
							免考依据
						</th>
						<th>
							审核状态
						</th>
						<th>
							审核人
						</th>
						<th>
							审核时间
						</th>
					</tr>
				</thead>
				<tbody>
					<c:choose>
						<c:when test="${ empty stuAvoidList}">
							<tr>
								<td colspan="7" align="center">
									没有记录
								</td>
							</tr>
						</c:when>
						<c:otherwise>
							<c:forEach items="${ stuAvoidList}" var="avoid" varStatus="ctNum">
								<tr>
									<td>
										${ ctNum.count}
									</td>
									<td style="text-align: left;">
										&nbsp;${ avoid[0]}
									</td>
									<td style="text-align: left;">
										&nbsp;${ avoid[1]}
									</td>
									<td style="text-align: left;">
										&nbsp;${ avoid[2]}
									</td>
									<td>
										&nbsp;
										<c:choose>
											<c:when test="${ avoid[3] == '-1'}">待查</c:when>
											<c:when test="${ avoid[3] == '0'}">未审核</c:when>
											<c:when test="${ avoid[3] == '1'}">已审核</c:when>
											<c:when test="${ avoid[3] == '2'}">
												<label title="${ avoid[6]}">
													审核不通过
												</label>
											</c:when>
											<c:otherwise></c:otherwise>
										</c:choose>
									</td>
									<td>
										&nbsp;${ avoid[4]}
									</td>
									<td>
										&nbsp;${ avoid[5]}
									</td>
								</tr>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
		</div>
		<div class="clear"></div>
		<!--考生免考课程信息----end-->


		<!--考生替代课程信息----开始-->
		<div class="list topMargin">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<caption>
					考生替换课程信息
				</caption>
				<thead>
					<tr>
						<th width="32">
							序号
						</th>
						<th>
							替换课程
						</th>
						<th>
							替换描述
						</th>
						<th>
							替换依据
						</th>
						<th>
							审核状态
						</th>
						<th>
							审核人
						</th>
						<th>
							审核时间
						</th>
					</tr>
				</thead>
				<tbody>
					<c:choose>
						<c:when test="${ empty stuSubstituteList}">
							<tr>
								<td colspan="7" align="center">
									没有记录
								</td>
							</tr>
						</c:when>
						<c:otherwise>
							<c:forEach items="${ stuSubstituteList}" var="sub"
								varStatus="ctNum">
								<tr>
									<td>
										${ ctNum.count}
									</td>
									<td style="text-align: left;">
										&nbsp;${ sub[0]}
									</td>
									<td style="text-align: left;">
										&nbsp;${ sub[1]}
									</td>
									<td style="text-align: left;">
										&nbsp;${ sub[2]}
									</td>
									<td>
										&nbsp;
										<c:choose>
											<c:when test="${ sub[3] == '-1'}">待查</c:when>
											<c:when test="${ sub[3] == '0'}">未审核</c:when>
											<c:when test="${ sub[3] == '1'}">已审核</c:when>
											<c:when test="${ sub[3] == '2'}">
												<label title="${ sub[6]}">
													审核不通过
												</label>
											</c:when>
											<c:otherwise></c:otherwise>
										</c:choose>
									</td>
									<td>
										&nbsp;${ sub[4]}
									</td>
									<td>
										&nbsp;${ sub[5]}
									</td>
								</tr>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
		</div>
		<div class="clear"></div>
		<!--考生替代课程信息---end-->

		<!--考生基本信息变更记录---开始-->
		<div class="list topMargin">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<caption>
					考生基本信息变更记录
				</caption>
				<thead>
					<tr>
						<th width="30">
							序号
						</th>
						<th>
							变更内容
						</th>
						<th>
							原信息
						</th>
						<th>
							新信息
						</th>
						<th>
							审核人
						</th>
						<th>
							审核时间
						</th>
					</tr>
				</thead>
				<tbody>
					<c:choose>
						<c:when test="${empty stuChangeInfodataList}">
							<tr>
								<td colspan="6" align="center">
									没有记录
								</td>
							</tr>
						</c:when>
						<c:otherwise>
							<c:forEach var="bean" items="${stuChangeInfodataList}"
								varStatus="h">
								<tr>
									<td>
										${h.index+1 }
									</td>
									<td>
										${bean.changeTypeName }
									</td>
									<td>
										${bean.oldInfo }
									</td>
									<td>
										${bean.newInfo }
									</td>
									<td>
										${bean.changeApplyUserRealName }
									</td>
									<td>
										${fn:substring(bean.changeAuditTime,0,11) }
									</td>
								</tr>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
		</div>
		<div class="clear"></div>
		<!--考生基本信息变更记录--end-->

		<!--考生考籍转移信息---开始-->
		<div class="list topMargin">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<caption>
					考生考籍转移信息
				</caption>
				<thead>
					<tr>
						<th width="30">
							序号
						</th>
						<th>
							转移类型
						</th>
						<th>
							转出时间
						</th>
						<th>
							转出地
						</th>
						<th>
							转入时间
						</th>
						<th>
							转入地
						</th>
						<th>
							转移课程数
						</th>
						<th>
							是否完全转出
						</th>
					</tr>
				</thead>
				<tbody>
					<c:choose>
						<c:when test="${ empty transInfoList}">
							<tr>
								<td colspan="8" align="center">
									没有记录
								</td>
							</tr>
						</c:when>
						<c:otherwise>
							<c:forEach items="${ transInfoList}" var="trs" varStatus="ctNum">
								<tr>
									<td>
										&nbsp;${ ctNum.count}
									</td>
									<td>
										&nbsp;${ trs[0]}
									</td>
									<td>
										&nbsp;${ trs[1]}
									</td>
									<td>
										&nbsp;${ trs[2]}
									</td>
									<td>
										&nbsp;${ trs[3]}
									</td>
									<td>
										&nbsp;${ trs[4]}
									</td>
									<td>
										&nbsp;${ trs[5]}
									</td>
									<td>
										&nbsp;
										<c:choose>
											<c:when test="${ trs[6] == '1'}">是</c:when>
											<c:when test="${ trs[6] == '0'}">否</c:when>
											<c:otherwise>${ trs[6]}</c:otherwise>
										</c:choose>
									</td>
								</tr>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
		</div>
		<div class="clear"></div>
		<!--考生考籍转移信息---end-->

		<!--考生持有证书信息--开始-->
		<div class="list topMargin">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<caption>
					考生持有证书信息
				</caption>
				<thead>
					<tr>
						<th width="30">
							序号
						</th>
						<th>
							证书名称
						</th>
						<th>
							证书编号
						</th>
						<th>
							证书取得时间
						</th>
					</tr>
				</thead>
				<tbody>
					<c:choose>
						<c:when test="${ empty certList}">
							<tr>
								<td colspan="4" align="center">
									没有记录
								</td>
							</tr>
						</c:when>
						<c:otherwise>
							<c:forEach items="${ certList}" var="cert" varStatus="ctNum">
								<tr>
									<td>
										&nbsp;${ ctNum.count}
									</td>
									<td>
										&nbsp;${ cert[0]}
									</td>
									<td>
										&nbsp;${ cert[1]}
									</td>
									<td>
										&nbsp;${ cert[2]}
									</td>
								</tr>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
		</div>
		<div class="clear"></div>
		<!--考生持有证书信息--end-->

		<!--考生毕业信息--开始-->
		<div class="list topMargin">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<caption>
					考生毕业信息
				</caption>
				<thead>
					<tr>
						<th width="30">
							序号
						</th>
						<th>
							毕业证书编号
						</th>
						<th>
							层次
						</th>
						<th>
							专业
						</th>
						<th>
							毕业时间
						</th>
						<th>
							主考院校
						</th>
					</tr>
				</thead>
				<tbody>
					<c:choose>
						<c:when test="${ empty graduateList}">
							<tr>
								<td colspan="6" align="center">
									没有记录
								</td>
							</tr>
						</c:when>
						<c:otherwise>
							<c:forEach items="${ graduateList}" var="gra" varStatus="ctNum">
								<tr>
									<td>
										&nbsp;${ ctNum.count}
									</td>
									<td>
										&nbsp;${ gra[0]}
									</td>
									<td>
										&nbsp;${ gra[1]}
									</td>
									<td style="text-align: left;">
										&nbsp;${ gra[2]}
									</td>
									<td>
										&nbsp;${ gra[3]}
									</td>
									<td style="text-align: left;">
										&nbsp;${ gra[4]}
									</td>
								</tr>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
		</div>
		<div class="clear"></div>
		<!--考生毕业信息---end-->

		<!--考生毕业信息--开始-->
		<div class="list topMargin">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<caption>
					考生报考记录
				</caption>
				<thead>
					<tr>
						<th width="30">
							序号
						</th>
						<th>
							报考年度
						</th>
						<th>
							报考课程
						</th>
						<th>
							考试时间
						</th>
						<th>
							交费情况
						</th>
					</tr>
				</thead>
				<tbody>
					<c:choose>
						<c:when test="${ empty stuSiteUpList}">
							<tr>
								<td colspan="5" align="center">
									没有记录
								</td>
							</tr>
						</c:when>
						<c:otherwise>
							<c:forEach items="${ stuSiteUpList}" var="su" varStatus="ctNum">
								<tr>
									<td>
										${ ctNum.count}
									</td>
									<td>
										&nbsp;${ su[0]}
									</td>
									<td style="text-align: left;">
										&nbsp;${ su[1]}
									</td>
									<td>
										&nbsp;${ su[2]}
									</td>
									<td>
										&nbsp;
										<c:choose>
											<c:when test="${ su[3] == '1'}">已交费</c:when>
											<c:otherwise>未交费</c:otherwise>
										</c:choose>
									</td>
								</tr>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
		</div>
		<div class="clear"></div>
		<!--考生毕业信息---end-->
		<br />

		<!--考生课程违纪记录---start-->
		<div class="list topMargin">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<caption>
					考生考试情况记录
				</caption>
				<thead>
					<tr>
						<th width="30">
							序号
						</th>
						<th>
							考试年月
						</th>
						<th>
							课程代码
						</th>
						<th>
							课程名称
						</th>
						<th>
							考试时间
						</th>
						<th>
							违规事实
						</th>
						<th>
							处理结果
						</th>
					</tr>
				</thead>
				<tbody>
					<c:choose>
						<c:when test="${ empty deciplineList}">
							<tr>
								<td colspan="6" align="center">
									没有记录
								</td>
							</tr>
						</c:when>
						<c:otherwise>
							<c:forEach items="${ deciplineList}" var="dl" varStatus="ctNum">
								<tr>
									<td>
										${ ctNum.count}
									</td>
									<td>
										${ dl[0]}
									</td>
									<td>
										${ dl[1]}
									</td>
									<td>
										${ dl[2]}
									</td>
									<td>
										${ dl[3]}
									</td>
									<td style="text-align: left;">
										&nbsp;${ dl[5]}
									</td>
									<td style="text-align: left;">
										<c:choose>
											<c:when test="${ dl[6] eq '当次考试各科成绩无效'}">（作弊）${ dl[6]}</c:when>
											<c:when test="${ dl[6] eq '取消该科目的考试成绩'}">（违纪）${ dl[6]}</c:when>
											<c:otherwise>${ dl[6]}</c:otherwise>
										</c:choose>
									</td>
								</tr>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
		</div>
		<div class="clear"></div>
		<!--考生课程违纪记录---end-->
		<br />

		<!-- 毕业申请记录 -->
		<iframe name="gapprec"
			src="/ZK_STATUS/status/graduate/gapprec_manager.do?studExamCode=${stud.studExamCode}"
			border="0" frameborder="0" style="width: 100%; padding: 0px;"></iframe>

		<div class="button topMargin">
			<input type="button" value="关 闭" class="inputButton"
				onclick="window.close();" />
		</div>
	</body>
</html>
