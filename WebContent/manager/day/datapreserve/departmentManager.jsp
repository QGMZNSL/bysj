<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>职位部门管理</title>
		<link href="<%=request.getContextPath()%>/common/style/style.css"
			rel="stylesheet" type="text/css" />
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/common/js/jquery-1.3.2.min.js"></script>
		<script type="text/javascript">
		var init = function() {
			$('#departmentGrade').val("${ department.departmentGrade}");
		}
		
		var goOperate = function( type, departmentCode, departGrade) {
			var url;
			var gotoFlag = true;
			if ( type == 'del') {
				url = '<%=request.getContextPath()%>/day/datapreserve/pstMgr_departmentsDel.do?department.departmentCode=' + departmentCode;
				gotoFlag = confirm("确定要删除该部门吗？（如果部门已经有下属职位，则无法删除！）");
			} else if ( type == 'edit') {
				url = '<%=request.getContextPath()%>/day/datapreserve/pstMgr_departmentsEditPre.do?department.departmentCode=' + departmentCode;
			} else if ( type == 'setPosition') {
				url = '<%=request.getContextPath()%>/day/datapreserve/pstMgr_qryPositions.do?department.departmentCode=' + departmentCode + '&departGrade=' + departGrade;
			} else {
				return;
			}
			
			if ( gotoFlag) {
				location.href = url;
			}
		}
		
		$(function() {
			init();
			
			<c:if test="${ empty departmentList}">
				alert("尚未设置用户部门，请先进行设置！");
			</c:if>
		});
		</script>
	</head>

	<body>
		<!--导航---start-->
		<div class="dqwz">
			<div style="margin-right: 10px;">
				<span style="cursor: pointer;" id="helpSpan"
					onclick="javascript:showHelpDiv('Z53001');">- 帮助 -</span>
				<span class="pageCode">No.Z53001</span>${currNavigation}
			</div>
		</div>
		<jsp:include page="/help/helpJs.jsp" />
		<iframe name="ifm" id="helpIfm" scrolling="no" frameborder="0"
			width="100%" style="display: none;"
			onload="this.height=this.contentWindow.document.body.scrollHeight+13"></iframe>
		<!--导航---end-->

		<!--查询条件开始-->
		<form method="post" name="departmentSearch"
			action="<%=request.getContextPath()%>/day/datapreserve/pstMgr_departments.do">
			<div class="tjcx">
				<dl>
					<dt>
						部门级别：
					</dt>
					<dd>
						<select class="inputText inputTextM"
							name="department.departmentGrade" id="departmentGrade">
							<c:forEach items="${ grades}" var="grd">
								<option value="${ grd.entityCode}">
									${ grd.entityName}
								</option>
							</c:forEach>
						</select>
					</dd>
				</dl>
			</div>
			<div class="button">
				<input class="inputButton" type="submit" value="查 询" />
				<input class="inputButton" type="button" value="添 加"
					onclick="location.href='<%=request.getContextPath()%>/day/datapreserve/pstMgr_departmentsAddPre.do'" />
			</div>
		</form>
		<div class="clear"></div>
		<!--查询条件end-->

		<c:if test="${ not empty departmentList}">
			<div class="list">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					style="text-align: center">
					<caption></caption>
					<thead>
						<tr>
							<th>
								序号
							</th>
							<th>
								部门名称
							</th>
							<th>
								部门级别
							</th>
							<th>
								职位个数
							</th>
							<th>
								操作
							</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${ departmentList}" var="depart"
							varStatus="ctNum">
							<tr>
								<td width="31" align="center">
									${ ctNum.count}
								</td>
								<td align="center">
									${ depart.departmentName}
								</td>
								<td align="center">
									<c:choose>
										<c:when test="${ depart.departmentGrade == 1}">省级</c:when>
										<c:when test="${ depart.departmentGrade == 2}">市级</c:when>
										<c:otherwise>&nbsp;</c:otherwise>
									</c:choose>
								</td>
								<td align="center">
									${ depart.postionCount}
								</td>
								<td align="center">
									<a href="#"
										onclick="goOperate( 'setPosition', '${ depart.departmentCode}', '${ depart.departmentGrade}');">设置职位</a>&nbsp;&nbsp;
									<a href="#"
										onclick="goOperate( 'edit', '${ depart.departmentCode}');">修改</a>&nbsp;&nbsp;
									<a href="#"
										onclick="goOperate( 'del', '${ depart.departmentCode}');">删除</a>
								</td>
							</tr>
						</c:forEach>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="6">
								<span>${ page.pageInfo}</span>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
		</c:if>
	</body>
</html>
