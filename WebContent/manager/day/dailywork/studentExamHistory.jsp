<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>考生成绩历史记录</title>
		<link href="<%=request.getContextPath()%>/common/style/style.css"
			rel="stylesheet" type="text/css" />
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/common/js/jquery-1.3.2.min.js"></script>
		<script type="text/javascript">
		</script>
	</head>
	<body>
		<!--导航---start-->
		<div class="dqwz">
			<div style="margin-right: 10px;">
				<span style="cursor: pointer;" id="helpSpan"
					onclick="javascript:showHelpDiv('Z51001');">- 帮助 -</span>
				<span class="pageCode">No.Z51001</span>${currNavigation}
			</div>
		</div>
		<jsp:include page="/help/helpJs.jsp" />
		<iframe name="ifm" id="helpIfm" scrolling="no" frameborder="0"
			width="100%" style="display: none;"
			onload="this.height=this.contentWindow.document.body.scrollHeight+13"></iframe>
		<!--导航---end-->

		<div class="list">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<caption>
					考生：${ studName} &nbsp;&nbsp; （历史考试记录）
				</caption>
				<thead>
					<tr>
						<th width="30" align="center">
							序号
						</th>
						<th>
							考试年月
						</th>
						<th>
							课程
						</th>
						<th>
							成绩
						</th>
						<th>
							状态
						</th>
						<th>
							违纪事实
						</th>
						<th>
							违纪惩罚
						</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${ historyScores}" var="hs" varStatus="ctNum">
						<tr>
							<td align="center">
								${ ctNum.count}
							</td>
							<td align="center" width="8%">
								&nbsp;${ hs.examName}
							</td>
							<td align="left" width="20%">
								&nbsp;${ hs.syllabusCode}-${ hs.syllabusName}
							</td>
							<td align="center" width="5%">
								&nbsp;${ hs.studScore}
							</td>
							<td align="center" width="5%">
								&nbsp;
								<c:choose>
									<c:when test="${ hs.lackCode eq '1'}">
										缺考
									</c:when>
									<c:when test="${ not empty hs.deciplineFactCode}">
										违纪
									</c:when>
									<c:otherwise>
										正常
									</c:otherwise>
								</c:choose>
							</td>
							<td align="left" width="20%">
								&nbsp;${ hs.deciplineFactDescribe}
							</td>
							<td align="left">
								&nbsp;
								<c:if test="${ not empty hs.deciplineFactCode}">
								${ hs.deciplinePMDescript}
								</c:if>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</body>
</html>
