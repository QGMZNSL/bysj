<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://nbf.river.org/vxds" prefix="v"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>证书查询</title>
		<link href="<%=request.getContextPath()%>/common/style/style.css"
			rel="stylesheet" type="text/css" />
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/common/js/riverdefine.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/common/js/riverajax.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/common/js/clientfuns.js"></script>
		<script type="text/javascript">
		</script>
	</head>
	<body>
		<!--导航---start-->
		<div class="dqwz">
			<div style="margin-right: 10px;">
				<span style="cursor: pointer;" id="helpSpan"
					onclick="javascript:showHelpDiv('Z51006');">- 帮助 -</span>
				<span class="pageCode">No.Z51006</span>${currNavigation}
			</div>
		</div>
		<jsp:include page="/help/helpJs.jsp" />
		<iframe name="ifm" id="helpIfm" scrolling="no" frameborder="0"
			width="100%" style="display: none;"
			onload="this.height=this.contentWindow.document.body.scrollHeight+13"></iframe>

		<div class="list">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<caption>
					2003年前高等教育自学考试成绩查询
				</caption>
				<thead>
					<tr>
						<th>
							旧准考证号
						</th>
						<th>
							新准考证号
						</th>
						<th>
							姓名
						</th>
						<th>
							旧课程代码
						</th>
						<th>
							旧课程名称
						</th>
						<th>
							成绩
						</th>
						<th>
							合格时间
						</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${ oldSyllabusList}" var="osl" varStatus="ctNum">
						<tr>
							<td align="center">
								&nbsp;${ osl[0]}
							</td>
							<td align="center">
								&nbsp;${ osl[1]}
							</td>
							<td align="center">
								&nbsp;${ osl[2]}
							</td>
							<td align="center">
								&nbsp;${ osl[3]}
							</td>
							<td align="left">
								&nbsp;${ osl[4]}
							</td>
							<td align="center">
								&nbsp;${ osl[5]}
							</td>
							<td align="center">
								&nbsp;${ osl[6]}
							</td>
						</tr>
					</c:forEach>
				</tbody>
				<tfoot>
				</tfoot>
			</table>
		</div>
		<div class="button">
			<input type="button" class="inputButton" value="返 回"
				onclick="location.href='<%=request.getContextPath()%>/manager/day/dailywork/c_go2003CJCX.do'" />
		</div>
	</body>
</html>