<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="v" uri="http://nbf.river.org/vxds"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>专业类型设置</title>
		
		<link href="<%=request.getContextPath()%>/common/style/style.css"
			rel="stylesheet" type="text/css" />
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/common/js/jquery-1.3.2.min.js"></script>
		<script type="text/javascript">
		var init = function() {
			
		};
		var goOperate = function( type, proTypeCode) {
			var url;
			var gotoFlag = true;
			if ( type == 'del') {
				url = '<%=request.getContextPath()%>/plan/protype_Del.do?protype.proTypeCode=' + proTypeCode;
				gotoFlag = confirm("确定要删除该类型吗？（如果类型已经被用，则无法删除！）");
			}
			else if ( type == 'isUseDel') {
				url = '<%=request.getContextPath()%>/plan/protype_isUseDel.do?protype.proTypeCode=' + proTypeCode;
				gotoFlag = confirm("确定要删除该信息吗？");
			} else if ( type == 'edit') {
				url = '<%=request.getContextPath()%>/plan/protype_EditPre.do?protype.proTypeCode=' + proTypeCode;
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
	<div  style="margin-right: 10px;">
<span style="cursor:pointer; " id="helpSpan" onclick="javascript:showHelpDiv('Z21002');">- 帮助 -</span>
		<span class="pageCode">No.Z21002</span>${currNavigation}
	</div>
</div>
<jsp:include page="/help/helpJs.jsp"/>
<iframe name="ifm" id="helpIfm" scrolling="no" frameborder="0" width="100%" style="display: none;" onload="this.height=this.contentWindow.document.body.scrollHeight+13"></iframe>
		
		<!-- 查询条件 -->
		<form method="post" name="form1" action="<%=request.getContextPath()%>/plan/protype_qry.do">
		<div class="tjcx">
			<dl>
				<dt>专业类型代码：</dt>
				<dd><input class="inputText inputTextM" type="text" name="protype.proTypeCode" id="proTypeCode" value="${protype.proTypeCode}" /></dd>
			</dl>
			<dl>
				<dt>专业类型名称：</dt>
				<dd><input class="inputText inputTextM" type="text" name="protype.proTypeName" id="proTypeName" value="${protype.proTypeName}" /></dd>
			</dl>		   
		</div>			
		<div class="button">
		<input class="inputButton" type="submit" value="查 询" />&nbsp;
		<input class="inputButton" type="button" value="添 加" onclick="location.href='<%=request.getContextPath()%>/plan/protype_AddPre.do'" /></div>
		</form>	
		<div class="clear"></div>

		<!-- 结果集 -->
		<c:if test="${ not empty protypeList}">
		<div class="list">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<caption>
				</caption>
				<thead>
					<tr>
						<th width="30">
							序号
						</th>
						<th>
							专业类型代码
						</th>
						<th>
							专业类型名称
						</th>
						<th>
							所属层次
						</th>
						<th>
							备注
						</th>
						<th width="80">
							操作
						</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach items="${ protypeList}" var="protype" varStatus="ctNum">
					<tr>
						<td align="center">
							${ page.pagesize * page.pagenum + ctNum.count}
						</td>
						<td align="center">
							${ protype.proTypeCode}
						</td>
						<td align="left">
							${ protype.proTypeName}
						</td>
						<td align="center">
							${ protype.levelName}
						</td>
						<td align="center">
							${ protype.remarks}&nbsp;
						</td>
						<td align="center">
							<a href="#" onclick="goOperate( 'edit', '${ protype.proTypeCode}');">编辑</a>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<a href="#" onclick="goOperate( 'isUseDel', '${ protype.proTypeCode}');">删除</a>
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
