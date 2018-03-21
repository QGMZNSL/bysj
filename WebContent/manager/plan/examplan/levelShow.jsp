<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>报考层次设置</title>
		<link href="<%=request.getContextPath()%>/common/style/style.css"
			rel="stylesheet" type="text/css" />
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/common/js/jquery-1.3.2.min.js"></script>
		<script type="text/javascript">
		var init = function() {
			
		}		
		var goOperate = function( type, levelCode) {
			var url;
			var gotoFlag = true;
			if ( type == 'del') {
				url = '<%=request.getContextPath()%>/plan/level_Del.do?planlevel.levelCode=' + levelCode;
				gotoFlag = confirm("确定要删除该层次吗？（如果层次已经被用，则无法删除！）");
			}
			 else if ( type == 'isUseDel') {
				url = '<%=request.getContextPath()%>/plan/level_isUseDel.do?planlevel.levelCode=' + levelCode;
				gotoFlag = confirm("确定要删除该信息吗？");
			} else if ( type == 'edit') {
				url = '<%=request.getContextPath()%>/plan/level_EditPre.do?planlevel.levelCode=' + levelCode;
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
			<div  style="margin-right: 10px;">
				<span style="cursor:pointer; " id="helpSpan" onclick="javascript:showHelpDiv('Z21001');">- 帮助 -</span>
				<span class="pageCode">No.Z21001</span>${currNavigation}
			</div>
		</div>
		<jsp:include page="/help/helpJs.jsp"/>
		<iframe name="ifm" id="helpIfm" scrolling="no" frameborder="0" width="100%" style="display: none;" onload="this.height=this.contentWindow.document.body.scrollHeight+13"></iframe>
		<div class="clear"></div>
			<div class="button">
				<input class="inputButton" type="button" value="添 加"
					onclick="location.href='<%=request.getContextPath()%>/plan/level_AddPre.do'" />
			</div>
			<div class="clear"></div>
		<!-- 结果集 -->
		<c:if test="${ not empty planlevelList}">
		<div class="list">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<caption>
				</caption>
				<thead>
					<tr>
						<th>
							序号
						</th>
						<th>
							层次代码
						</th>
						<th>
							层次名称
						</th>
						<th width='80px' align='center'>
							操作
						</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${ planlevelList}" var="level" varStatus="ctNum">
					<tr>
						<td align="center">
							${ page.pagesize * page.pagenum + ctNum.count}
						</td>
						<td align="center">
							${ level.levelCode}
						</td>
						<td align="center">
							${ level.levelName}
						</td>
						<td align="center">
							<a href="#" onclick="goOperate( 'edit', '${ level.levelCode}');">编辑</a>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<a href="#" onclick="goOperate( 'isUseDel', '${ level.levelCode}');">删除</a>
							
						</td>
					</tr>
					</c:forEach>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="5">
							<span>${ page.pageInfo}</span>
						</td>
					</tr>
				</tfoot>
			</table>
		</div>
		</c:if>
	</body>