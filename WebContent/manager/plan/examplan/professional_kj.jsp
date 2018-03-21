<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>专业设置--考籍</title>
		<link href="<%=request.getContextPath()%>/common/style/style.css"
			rel="stylesheet" type="text/css" />
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/common/js/jquery-1.3.2.min.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/common/js/clientfuns.js"></script>
		<script type="text/javascript">
		var init = function() {
			
		}		
		var goOperate = function( type, proCode) {
			var url;
			var gotoFlag = true;
			if ( type == 'del') {
				url = '<%=request.getContextPath()%>/plan/baseprokj_Del.do?basepro.proCode=' + proCode;
				gotoFlag = confirm("确定要删除该层次吗？（如果层次已经被用，则无法删除！）");
			} else if ( type == 'edit') {
				url = '<%=request.getContextPath()%>/plan/baseprokj_EditPre.do?basepro.proCode=' + proCode;
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
		
		function subForm(){
			if(checkBoxs('proCodes')==false){
				alert("请至少选择一条记录！");
				return;
			}else{
				var subform=document.getElementById("subform");
				subform.submit();
			}
		}
		</script>			
	</head>

	<body>
		<!-- 页面导航 -->
		<div class="dqwz">
			<div style="margin-right: 10px;">
				<span style="cursor: pointer;" id="helpSpan"
					onclick="javascript:showHelpDiv('Z21013');">- 帮助 -</span>
				<span class="pageCode">No.Z21013</span>${currNavigation}
			</div>
		</div>
		<jsp:include page="/help/helpJs.jsp" />
		<iframe name="ifm" id="helpIfm" scrolling="no" frameborder="0"
			width="100%" style="display: none;"
			onload="this.height=this.contentWindow.document.body.scrollHeight+13"></iframe>

		<!-- 查询条件 -->
		<form method="post" name="form1" action="<%=request.getContextPath()%>/plan/baseprokj_qry.do">
			<div class="tjcx">
				<dl>
					<dt>
						层次：
					</dt>
					<dd>
						<select class="inputText inputTextM" name="basepro.levelCode" id="levelCode">
								<option value="">--- 请选择 ---</option>
								<c:forEach items="${ planlevelList}" var="pl">								    
									<option value="${ pl.levelCode}" <c:if test="${pl.levelCode==basepro.levelCode}">selected</c:if> >
										${ pl.levelName}
									</option>
								</c:forEach>
						</select>
					</dd>
				</dl>
				<dl>
					<dt>
						专业类型：
					</dt>
					<dd>
						<select class="inputText inputTextM" name="basepro.proTypecode" id="proTypecode" >
								<option value="">--- 请选择 ---</option>
								<c:forEach items="${ protypelList}" var="pt">								    
									<option value="${ pt.proTypeCode}" <c:if test="${pt.proTypeCode==basepro.proTypecode}">selected</c:if> >
										${ pt.proTypeName}
									</option>
								</c:forEach>
						</select>
					</dd>
				</dl>
				<!-- 
				<dl>
					<dt>
						专业分类：
					</dt>
					<dd>
						<select class="inputText inputTextM" name="basepro.proPartcode" id="proPartcode" >
								<option value="">--- 请选择 ---</option>
								<c:forEach items="${ proseqList}" var="pp">
									<option value="${ pp.proPartCode}" <c:if test="${ pp.proPartCode==basepro.proPartcode}">selected</c:if> >
										${ pp.proPartName}
									</option>
								</c:forEach>
						</select>
					</dd>
				</dl>
				 -->
				<dl>
					<dt>
						专业代码：
					</dt>
					<dd>
						<input class="inputText inputTextM" type="text" name="basepro.proCode" id="proCode" value="${basepro.proCode}" />
					</dd>
				</dl>
				<dl>
					<dt>
						专业名称：
					</dt>
					<dd>
						<input class="inputText inputTextM" type="text" name="basepro.proName" id="proName" value="${basepro.proName}" />
					</dd>
				</dl>
				<dl>
					<dt>
						是否允许毕业：
					</dt>
					<dd>
						<select class="inputText" name="basepro.allowGraduate" id="allowGraduate">
							<option value="1" <c:if test="${basepro.allowGraduate=='1'}">selected</c:if>>允许</option>
							<option value="0" <c:if test="${basepro.allowGraduate=='0'}">selected</c:if>>禁止</option>
						</select>						
					</dd>
				</dl>				
			</div>
			<div class="clear"></div>
			<div class="button">
				<input class="inputButton" type="submit" value="查 询" />
				<input class="inputButton" type="button" value="添 加"
					onclick="location.href='<%=request.getContextPath()%>/plan/baseprokj_AddPre.do'" />
				<c:if test="${basepro.allowGraduate=='1'}">
				<input class="inputButton" type="button" value="禁止申办毕业" style="width:110px;" onclick="subForm()"/>
				</c:if>
			</div>
			<div class="clear"></div>
		</form>

		<!-- 结果集 -->
		<div class="list">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<caption>
				</caption>
				<thead>
					<tr>
						<!-- 
						<th width="30">
							序号
						</th>
						 -->
						<th >
							<span onclick="cboxCheckOpposite('proCodes')"><a href="#">反选</a></span>
						</th>
						<th>
							专业代码
						</th>
						<th>
							专业名称
						</th>
						<!-- 
						<th>
							专业分类
						</th>						
						 -->
						<th>
							专业类型
						</th>
						<th>
							是否国标
						</th>
						<th>
							是否允许申办毕业
						</th>
						<!-- 
						<th width="80">
							操作
						</th>
						 -->
					</tr>
				</thead>
				<tbody>
				<form action="<%=request.getContextPath()%>/plan/baseprokj_noAllowGraduate.do" method="post" id="subform">
				<c:forEach items="${ baseproList}" var="basepro" varStatus="ctNum">
					<tr>
					<!-- 
						<td align="center">
							${ page.pagesize * page.pagenum + ctNum.count}
						</td>
					 -->
						<td align="center">
							<input type="checkbox" name="proCodes" value="${basepro.proCode}"/>
						<br /></td>
						<td align="center">
							${ basepro.proCode}
						<br /></td>
						<td align="left">
							${ basepro.proName}
						<br /></td>
						<!-- 
						<td align="center">
							${ basepro.proPartName}&nbsp;
						</td>
						 -->
						<td align="center">
							${ basepro.proTypeName}&nbsp;
						<br /></td>
						<td align="center">
							<c:choose>
								<c:when test="${ basepro.isGb == 0}">否</c:when>							
								<c:when test="${ basepro.isGb == 1}">是</c:when>
								<c:otherwise>&nbsp;</c:otherwise>
							</c:choose>
						<br /></td>
						<td align="center">
							<c:choose>
								<c:when test="${ basepro.allowGraduate == '0'}">禁止</c:when>							
								<c:when test="${ basepro.allowGraduate == '1'}">允许</c:when>
								<c:otherwise>&nbsp;</c:otherwise>
							</c:choose>
						</td>
						<!-- 
						<td align="center">
							<a href="#" onclick="goOperate( 'edit', '${ basepro.proCode}');">编辑</a>&nbsp;
						</td>
						 -->
					</tr>
				</c:forEach>
				</form>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="8">
							<span>${ page.pageInfo}</span>
						</td>
					</tr>
				</tfoot>
			</table>
		</div>
	</body>
</html>
