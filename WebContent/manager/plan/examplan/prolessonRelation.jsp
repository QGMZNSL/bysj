<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>专业与课程关系设置</title>
		<link href="<%=request.getContextPath()%>/common/style/style.css"
			rel="stylesheet" type="text/css" />
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/common/js/jquery-1.3.2.min.js"></script>	
		<script type="text/javascript">
		var init = function() {
			
		}
		var goOperate = function( type,proCode) {
			var url;
			var gotoFlag = true;
			if ( type == 'syllabusSet') {
				url = '<%=request.getContextPath()%>/plan/proSyllabus_getProSList.do?basepro.proCode=' + proCode;
			} else if ( type == 'grpSet') {
				url = '<%=request.getContextPath()%>/plan/grpSet_Show.do?basepro.proCode='+proCode;
			} else if ( type == 'clear') {
				url = '<%=request.getContextPath()%>/plan/proSyllabus_Clear.do?proSyllabus.proCode='+proCode;
				gotoFlag = confirm("确认清除该专业的课程设置及分组设置全部相关信息吗？）");
			} else if ( type == 'beizhu') {
				url = '<%=request.getContextPath()%>/plan/proSyllabus_graCondEditPre.do?graduateCondition.proCode='+proCode;
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
		
		function openWin(proCode){
			window.open("proSyllabus_findProDetail.do?basepro.proCode="+proCode,"","height=500px, width=860px, top=100px,left=100px,scrollbars=yes,resizable=yes,location=no, status=yes"); 
		}
		</script>	
	</head>

	<body>
		<!-- 页面导航 -->
			<div class="dqwz">
			<div style="margin-right: 10px;">
				<span style="cursor: pointer;" id="helpSpan"
					onclick="javascript:showHelpDiv('Z21008');">- 帮助 -</span>
				<span class="pageCode">No.Z21008</span>${currNavigation}
			</div>
		</div>
		<jsp:include page="/help/helpJs.jsp" />
		<iframe name="ifm" id="helpIfm" scrolling="no" frameborder="0"
			width="100%" style="display: none;"
			onload="this.height=this.contentWindow.document.body.scrollHeight+13"></iframe>
		
		<!-- 查询条件 -->
		<form method="post" name="form1" action="<%=request.getContextPath()%>/plan/proSyllabus_qry.do">
		<div class="tjcx">
		<dl>
			<dt>层次：</dt>
			<dd>
				<select class="inputText inputTextM" name="basepro.levelCode" id="levelCode">
						<option value="">--- 请选择 ---</option>
						<c:forEach items="${ planlevelList}" var="pl">
							<option value="${pl.levelCode}" <c:if test="${pl.levelCode==basepro.levelCode}">selected</c:if> >
								${ pl.levelName}
							</option>
						</c:forEach>
				</select>
			</dd>
		</dl>
		<dl>
			<dt>专业类型：</dt>
			<dd>
				<select class="inputText inputTextM" name="basepro.proTypecode" id="proTypecode">
						<option value="">--- 请选择 ---</option>
						<c:forEach items="${ protypelList}" var="pt">
							<option value="${pt.proTypeCode}" <c:if test="${pt.proTypeCode==basepro.proTypecode}">selected</c:if> >
								${ pt.proTypeName}
							</option>
						</c:forEach>
				</select>
			</dd>
		</dl>
		<dl>
			<dt>专业分类：</dt>
			<dd>
				<select class="inputText inputTextM" name="basepro.proPartcode" id="proPartcode" value="${basepro.proPartcode}">
						<option value="">--- 请选择 ---</option>
						<c:forEach items="${ proseqList}" var="pp">
							<option value="${pp.proPartCode}"  <c:if test="${ pp.proPartCode==basepro.proPartcode}">selected</c:if> >
								${ pp.proPartName}
							</option>
						</c:forEach>
				</select>
			</dd>
		</dl>
		<dl>
			<dt>专业代码：</dt>
			<dd><input class="inputText inputTextM" type="text" name="basepro.proCode" id="proCode" value="${basepro.proCode}" /></dd>
		</dl>
		<dl>
			<dt>专业名称：</dt>
			<dd><input class="inputText inputTextM" type="text" name="basepro.proName" id="proName" value="${basepro.proName}" /></dd>
		</dl> 
		</div>
		<div class="button"><input class="inputButton" type="submit" value="查 询" /></div>
		<div class="clear"></div>
		</form>   

		<!-- 结果集 -->
		<c:if test="${not empty baseproList}">
		<div class="list">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<caption>
				</caption>
				<thead>
					<tr>
						<th width="28">
							序号
						</th>
						<th>
							专业代码
						</th>
						<th>
							专业名称
						</th>
						<th>
							专业分类
						</th>
						<th>
							专业类型
						</th>
						<th width="200" style="text-align:center;">
							操作
						</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach items="${ baseproList}" var="basepro" varStatus="ctNum">
					<tr>
						<td align="center">
							${ page.pagesize * page.pagenum + ctNum.count}
						</td>
						<td align="center">
							${ basepro.proCode}
						</td>
						<td align="left">
							<a href='#' onclick="openWin('${basepro.proCode}')">${ basepro.proName}</a>
						</td>
						<td align="center">
							${ basepro.proPartName}&nbsp;
						</td>
						<td align="center">
							${ basepro.proTypeName}&nbsp;
						</td>
						<td align="center">
							<a href="#" onclick="goOperate('syllabusSet','${basepro.proCode}');">设置课程</a>&nbsp;
							<a href="#" onclick="goOperate('grpSet','${basepro.proCode}');">设置分组</a>&nbsp;
							<a href="#" onclick="goOperate('clear','${basepro.proCode}');" title="清除专业课程和课程分组信息">清除</a>
							<a href="#" onclick="goOperate('beizhu','${basepro.proCode}');">备注</a>
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
