<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>毕业条件设置</title>
		<link href="<%=request.getContextPath()%>/common/style/style.css"
			rel="stylesheet" type="text/css" />
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/common/js/jquery-1.3.2.min.js"></script>
		<script type="text/javascript">
		var init = function() {
			
		};
		var goOperate = function( type, proCode) {
			var url;
			var gotoFlag = true;
			if ( type == 'del') {
				url = '<%=request.getContextPath()%>/plan/graduateCondition_Del.do?graduateCondition.proCode=' + proCode;
				gotoFlag = confirm('确认清除该专业的毕业条件吗？');
			} else if ( type == 'edit') {
				url = '<%=request.getContextPath()%>/plan/graduateCondition_EditPre.do?graduateCondition.proCode=' + proCode;
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
<span style="cursor:pointer; " id="helpSpan" onclick="javascript:showHelpDiv('Z42007');">- 帮助 -</span>
		<span class="pageCode">No.Z42007</span>${currNavigation}
	</div>
</div>
<jsp:include page="/help/helpJs.jsp"/>
<iframe name="ifm" id="helpIfm" scrolling="no" frameborder="0" width="100%" style="display: none;" onload="this.height=this.contentWindow.document.body.scrollHeight+18">
	</iframe>
		
		<!-- 查询条件 -->
	<form method="post" name="form1" action="<%=request.getContextPath()%>/plan/graduateCondition_qry.do">
			<div class="tjcx">
			<dl>
				<dt>层次名称：</dt>
				<dd>
				<select class="inputText inputTextM" name="basePro.levelCode" id="levelCode">
					<option value="">--- 请选择 ---</option>
					<c:forEach items="${ planlevelList}" var="pl">								    
						<option value="${ pl.levelCode}" <c:if test="${pl.levelCode==basePro.levelCode}">selected</c:if> >
							${ pl.levelName}
						</option>
					</c:forEach>
				</select>
				</dd>
			</dl>
			<dl>
				<dt>专业名称：</dt>
				<dd>
					<input class="inputText inputTextM" type="text" name="basePro.proName" id="proName" value="${basePro.proName}" readonly="readonly" value="点击选择专业" name="professionCode" style="cursor:hand;" onclick="window.open('/ZK_CORE/manager/plan/sctProfession.jsp','_blank', 'top=150,left=300,height=600,width=500,resizable=0,toolbar=0,menubar=0,location=0');" />
					<input type="hidden" name="basePro.proCode" id="proCode" value="${basePro.proCode}" />
				</dd>
			</dl>
		</div>
		<div class="button"><input class="inputButton" type="submit" value="查 询" /></div>
		<div class="clear"></div>
		</form>		

		<!-- 结果集 -->
		<c:if test="${ not empty baseProList}">
		<div class="list">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<caption>
				</caption>
				<thead>
					<tr>
						<th width="26">
							序号
						</th>
						<th>
							专业代码
						</th>
						<th>
							专业名称
						</th>
						<th>
							层次名称
						</th>
						<th>
							毕业设置
						</th>
						<th width="80">
							操作
						</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach items="${ baseProList}" var="basepro" varStatus="ctNum">
					<tr>
						<td align="center">
							${ page.pagesize * page.pagenum + ctNum.count}
						</td>
						<td align="center">
							${ basepro.proCode}
						</td>
						<td align="center">
							${ basepro.proName}
						</td>
						<td align="center">
							${ basepro.proTypeName}&nbsp;
						</td>
						<td align="center">
							${ basepro.proPartName}&nbsp;
						</td>
						<td align="center">
							<a href="#" onclick="goOperate( 'edit', '${ basepro.proCode}');">设置</a>&nbsp;
							<a href="#" onclick="confirm('确认清除该专业的毕业条件吗？');">清除</a>
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
