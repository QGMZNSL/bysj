<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>课程顶替组设置</title>
		<link href="<%=request.getContextPath()%>/common/style/style.css"
			rel="stylesheet" type="text/css" />
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/common/js/jquery-1.3.2.min.js"></script>
		<script type="text/javascript">
		var init = function() {
			
		};		
		var goOperate = function( type, substituteGroupId) {
			var url;
			var gotoFlag = true;
			if ( type == 'del') {
				url = '<%=request.getContextPath()%>/plan/substituteGroup_Del.do?substituteGroup.substituteGroupId=' + substituteGroupId;
				gotoFlag = confirm("确定要删除该层次吗？（如果层次已经被用，则无法删除！）");
			} else if ( type == 'edit') {
				url = '<%=request.getContextPath()%>/plan/substituteGroup_EditPre.do?substituteGroup.substituteGroupId=' + substituteGroupId;
			} else if ( type == 'SubStitutePre') {
				url = '<%=request.getContextPath()%>/plan/substituteGroup_SubStitutePre.do?substituteGroup.substituteGroupId=' + substituteGroupId;
			} else {
				return;
			}
			if ( gotoFlag) {
				location.href = url;
			}
		};
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
					onclick="javascript:showHelpDiv('Z21010');">- 帮助 -</span>
				<span class="pageCode">No.Z21010</span>${currNavigation}
			</div>
		</div>
		<jsp:include page="/help/helpJs.jsp" />
		<iframe name="ifm" id="helpIfm" scrolling="no" frameborder="0"
			width="100%" style="display: none;"
			onload="this.height=this.contentWindow.document.body.scrollHeight+13"></iframe>
		 
		<!-- 查询条件 -->
		<form method="post" name="form1" action="<%=request.getContextPath()%>/plan/substituteGroup_qry.do">
		<div class="tjcx">
			<dl>
				<dt>课程顶替组名称：</dt>
				<dd><input class="inputText inputTextM" type="text" name="substituteGroup.substituteGroupName" id="substituteGroupName" value="${substituteGroup.substituteGroupName}" /></dd>
			</dl> 
		</div>
		    <div class="button">
			<input class="inputButton" type="submit" value="查 询" />
		    <input class="inputButton" type="button" value="添 加" onclick="location.href='<%=request.getContextPath()%>/plan/substituteGroup_AddPre.do';" />
			</div>
		<div class="clear"></div>
		</form>   

		<!-- 结果集 -->
		<c:if test="${ not empty substituteGroupList}">
		<div class="list">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<caption>
					课程顶替组
				</caption>
				<thead>
					<tr>
						<th>
							序号
						</th>
						<th>
							课程顶替组代码
						</th>
						<th>
							课程顶替组名称
						</th>
						<th>
							操作
						</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach items="${ substituteGroupList}" var="substituteGroup" varStatus="ctNum">
					<tr>
						<td align="center">
							${ page.pagesize * page.pagenum + ctNum.count}&nbsp;
						</td>
						<td align="center">
							${ substituteGroup.substituteGroupId}&nbsp;
						</td>
						<td align="center">
							${ substituteGroup.substituteGroupName}&nbsp;
						</td>
						<td align="center">
							<a href="#" onclick="goOperate('SubStitutePre','${substituteGroup.substituteGroupId}');">设置课程</a>
							<a href="#" onclick="goOperate('edit','${substituteGroup.substituteGroupId}');">编辑</a>&nbsp;
						</td>
					</tr>
                </c:forEach>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="8">
							<span>${page.pageInfo}</span>
						</td>
					</tr>
				</tfoot>
			</table>
		</div>
		</c:if>
	</body>
</html>
