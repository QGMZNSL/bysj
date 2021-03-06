<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>主考院校设置</title>
		<link href="<%=request.getContextPath()%>/common/style/style.css"
			rel="stylesheet" type="text/css" />
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/common/js/jquery-1.3.2.min.js"></script>
		<script type="text/javascript">
		var init = function() {
			
		}		
		var goOperate = function( type, academyCode) {
			var url;
			var gotoFlag = true;
			if ( type == 'del') {
				url = '<%=request.getContextPath()%>/plan/baseacademy_Del.do?baseacademy.academyCode=' + academyCode;
				gotoFlag = confirm("确定要删除吗？（如果已经被用，则无法删除！）");
			} else if ( type == 'edit') {
				url = '<%=request.getContextPath()%>/plan/baseacademy_EditPre.do?baseacademy.academyCode=' + academyCode;
			}else if ( type == 'proset') {
				url = '<%=request.getContextPath()%>/plan/baseacademy_ProsetPre.do?baseacademy.academyCode=' + academyCode;
			} else {
				return;
			}
			
			if ( gotoFlag) {
				location.href = url;
			}
		}
		
		$(function() {
			init();			
		});
		</script>	
	</head>

	<body>
		<!-- 页面导航 -->
		<div class="dqwz">
			<div style="margin-right: 10px;">
				<span style="cursor: pointer;" id="helpSpan"
					onclick="javascript:showHelpDiv('Z21005');">- 帮助 -</span>
				<span class="pageCode">No.Z21005</span>${currNavigation}
			</div>
		</div>
		<jsp:include page="/help/helpJs.jsp" />
		<iframe name="ifm" id="helpIfm" scrolling="no" frameborder="0"
			width="100%" style="display: none;"
			onload="this.height=this.contentWindow.document.body.scrollHeight+13"></iframe>
		<!-- 查询条件 -->
		<form method="post" name="form1" action="<%=request.getContextPath()%>/plan/baseacademy_qry.do">
			<div class="tjcx">
				<dl>
					<dt>
						主考院校代码：
					</dt>
					<dd>
						<input class="inputText inputTextM" type="text" name="baseacademy.academyCode" id="academyCode" value="${baseacademy.academyCode}" />
					</dd>
				</dl>
				<dl>
					<dt>
						主考院校名称：
					</dt>
					<dd>
						<input class="inputText inputTextM" type="text" name="baseacademy.academyName" id="academyName" value="${baseacademy.academyName}" />
					</dd>
				</dl>
			</div>
			<div class="clear"></div>
			<div class="button">
				<input class="inputButton" type="submit" value="查 询" />
				<input class="inputButton" type="button" value="添 加"
					onclick="location.href='<%=request.getContextPath()%>/plan/baseacademy_AddPre.do'" />
			</div>
		</form>

		<div class="clear"></div>

		<!-- 结果集 -->
		<c:if test="${ not empty baseacademyList}">
		<div class="list">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<caption>
				</caption>
				<thead>
					<tr>
						<th width="12">
							序号
						</th>
						<th>
							主考院校代码
						</th>
						<th>
							主考院校名称
						</th>
						<th>
							所属市区
						</th>
						<th>
							启用状态
						</th>
						<th>
							备注
						</th>
						<th width="120">
							操作
						</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach items="${ baseacademyList}" var="baseacademy" varStatus="ctNum">
					<tr>
						<td align="center">
							${ page.pagesize * page.pagenum + ctNum.count}
						</td>
						<td align="center">
							${ baseacademy.academyCode}
						</td>
						<td align="left">
							${ baseacademy.academyName}
						</td>
						<td align="center">
							${ baseacademy.cityName}
						</td>
						<td align="center">
							<c:choose>
								<c:when test="${ baseacademy.isUse == 0}">禁用</c:when>							
								<c:when test="${ baseacademy.isUse == 1}">启用</c:when>
								<c:otherwise>&nbsp;</c:otherwise>
							</c:choose>					
						</td>
						<td align="center">
							${ baseacademybaseacademy.remarks}&nbsp;
						</td>
						<td align="center">
							<a href="#" onclick="goOperate( 'proset', '${ baseacademy.academyCode}');">设置专业</a>&nbsp;
							<a href="#" onclick="goOperate( 'edit', '${ baseacademy.academyCode}');">编辑</a>&nbsp;
						</td>
					</tr>
					</c:forEach>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="7">
							<span>${ page.pageInfo}</span>
						</td>
					</tr>
				</tfoot>
			</table>
		</div>
		</c:if>
	</body>
</html>
