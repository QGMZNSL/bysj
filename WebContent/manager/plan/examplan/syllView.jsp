<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>课程信息</title>
		<link href="<%=request.getContextPath()%>/common/style/style.css"
			rel="stylesheet" type="text/css" />
		<style>
.suj_notice {
	padding: 20px;
	font-size: 13px;
	background-color: #eeeeee;
	height: auto;
	border: 1px solid #ccc;
	margin-bottom: 20px;
}
</style>
	</head>
	<body>
		<!-- 页面导航 -->
		<div class="clear"></div>
		<!-- 结果集 -->
		<div>
			<span class="code"> <strong>专业代码：</strong>${graduateCond.proCode
				}&nbsp;<br /> <strong>专业名称：</strong>${graduateCond.proName }&nbsp;<br />
				<strong>主考学校：</strong> <c:forEach var="aca" items="${academyList }">
						${aca.academyName }
					</c:forEach> <br /> <br /> </span>
		</div>
		<c:if test="${not empty graduateCond.graduateConditionDescribe}">
			<div class="suj_notice">
				考生报考说明：${graduateCond.graduateConditionDescribe }
			</div>
		</c:if>
		<div class="clear"></div>
		<div class="list">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<caption>
				</caption>
				<thead>
					<tr>
						<th width="5%">
							序号
						</th>
						<th width="8%">
							课程代码
						</th>
						<th width="30%">
							课程名称
						</th>
						<th width="12%">
							是否必考
						</th>
						<th>
							课程选修说明
						</th>
						<th>
							学分
						</th>
						<th width="10%">
							是否全国统考
						</th>
						<th width="10%">
							课程备注
						</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="proSyll" items="${proSyllList }" varStatus="vs">
						<tr>
							<td align="center">
								${vs.count }
							</td>
							<td align="center">
								${proSyll.syllabusCode }
							</td>
							<td align="left">
								${proSyll.syllabusName }
							</td>
							<td align="center">
								<c:if test="${empty proSyll.syllabusGroupName}">必考课</c:if>
								<c:if test="${not empty proSyll.syllabusGroupName}">${proSyll.syllabusGroupName}</c:if>
							</td>
							<td align="left">
								<c:choose>
									<c:when test="${empty proSyll.remarks}">
												&nbsp;
											</c:when>
									<c:otherwise>
												${proSyll.remarks}
											</c:otherwise>
								</c:choose>

							</td>
							<td align="center">
								&nbsp;${proSyll.syllabusCredit }&nbsp;
							</td>
							<td align="center">
								<c:choose>
									<c:when test="${proSyll.examUnitary eq '1' }">
											√
										</c:when>
									<c:otherwise>
											&nbsp;
										</c:otherwise>
								</c:choose>
							</td>
							<td align="center">
								${proSyll.syllabusRemark }&nbsp;
							</td>
						</tr>
					</c:forEach>
					<tr>
						<td colspan="8">
							总学分：${graduateCond.graduateConditionCredit }
						</td>
					</tr>
				</tbody>
			</table>
			<br />
		</div>
	</body>
</html>