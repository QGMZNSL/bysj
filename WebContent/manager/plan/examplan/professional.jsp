<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>专业设置</title>
		<link href="<%=request.getContextPath()%>/common/style/style.css"
			rel="stylesheet" type="text/css" />
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/common/js/jquery-1.3.2.min.js"></script>
		<script type="text/javascript">
		var init = function() {
			
		}		
		var goOperate = function( type, proCode) {
			var url;
			var gotoFlag = true;
			if ( type == 'del') {
				url = '<%=request.getContextPath()%>/plan/basepro_Del.do?basepro.proCode=' + proCode;
				gotoFlag = confirm("确定要删除该层次吗？（如果层次已经被用，则无法删除！）");
			}
			else if ( type == 'isUseDel') {
				url = '<%=request.getContextPath()%>/plan/basepro_isUseDel.do?basepro.proCode=' + proCode;
				gotoFlag = confirm("确定要删除该层次吗？");
			} else if ( type == 'edit') {
				url = '<%=request.getContextPath()%>/plan/basepro_EditPre.do?basepro.proCode=' + proCode;
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
		
		// 导出Excel
		function doExcel() {
			var levelCode = $("#levelCode").val();
			var proTypecode = $("#proTypecode").val();
			var proPartcode = $("#proPartcode").val();
			var proCode = $("#proCode").val();
			var proName = $("#proName").val();
			
			var str = "basepro.levelCode=" + levelCode + "&basepro.proTypecode=" + proTypecode
				 + "&basepro.proPartcode=" + proPartcode + "&basepro.proCode=" + proCode
				 + "&basepro.proName=" + proName;
				 
			var ajaxurl="<%=request.getContextPath() %>/plan/basepro_doExcel.do?t=" + new Date().getTime();
			$.ajax({
				type:"post",	
				url:ajaxurl,
				data:str,
				async:false,
				success:function(msg){
					if ( "error" == msg) {
						$('#expdown').html("");
						alert("导出失败！");
						$('#expdown').html("");
					} else if ( "noData" == msg) {
						alert("没有记录！")
						$('#expdown').html("");
					} else {
						alert("导出成功！请点击下载链接进行下载！");
						$('#expdown').html(msg);
					}
				}
			});
		}
		</script>			
	</head>

	<body>
		<!-- 页面导航 -->
		<div class="dqwz">
			<div style="margin-right: 10px;">
				<span style="cursor: pointer;" id="helpSpan"
					onclick="javascript:showHelpDiv('Z21004');">- 帮助 -</span>
				<span class="pageCode">No.Z21004</span>${currNavigation}
			</div>
		</div>
		<jsp:include page="/help/helpJs.jsp" />
		<iframe name="ifm" id="helpIfm" scrolling="no" frameborder="0"
			width="100%" style="display: none;"
			onload="this.height=this.contentWindow.document.body.scrollHeight+13"></iframe>

		<!-- 查询条件 -->
		<form method="post" name="form1" action="<%=request.getContextPath()%>/plan/basepro_qry.do">
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
				<!-- 
				<dl>
					<dt>
						状态：
					</dt>
					<dd>
						<select class="inputText" name="basepro.isUse" id="isUse">
						    <option value="">--- 请选择 ---</option>
							<option value="0" <c:if test="${basepro.isUse==0}">selected</c:if>>停考</option>
							<option value="1" <c:if test="${basepro.isUse==1}">selected</c:if>>开考</option>
						</select>						
					</dd>
				</dl>
				 -->				
			</div>
			<div class="clear"></div>
			<div class="button">
				<input class="inputButton" type="submit" value="查 询" />
				<input class="inputButton" type="button" value="添 加"
					onclick="location.href='<%=request.getContextPath()%>/plan/basepro_AddPre.do'" />
				<input class="inputButton" type="button" value="导出EXCEL"
					onclick="doExcel()" />
				<span id="expdown"></span>
			</div>
			<div class="clear"></div>
		</form>

		<!-- 结果集 -->
		<c:if test="${ not empty baseproList}">
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
							专业分类
						</th>
						<th>
							专业类型
						</th>
						<th>
							是否全国统考计划
						</th>
						<th>
							状态
						</th>
						<th>
							是否允许新生报考
						</th>
						<th width="80">
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
							${ basepro.proName}
						</td>
						<td align="center">
							${ basepro.proPartName}&nbsp;
						</td>
						<td align="center">
							${ basepro.proTypeName}&nbsp;
						</td>
						<td align="center">
							<c:choose>
								<c:when test="${ basepro.isGb == 0}">否</c:when>							
								<c:when test="${ basepro.isGb == 1}">是</c:when>
								<c:otherwise>&nbsp;</c:otherwise>
							</c:choose>
						</td>
						<td align="center">
							<c:choose>
								<c:when test="${ basepro.isUse == 0}">停考</c:when>							
								<c:when test="${ basepro.isUse == 1}">开考</c:when>
								<c:otherwise>&nbsp;</c:otherwise>
							</c:choose>
						</td>
						<td align="center">
							<c:choose>
								<c:when test="${ basepro.isAllowNewStu == 1}">允许</c:when>							
								<c:when test="${ basepro.isAllowNewStu == 0}">不允许</c:when>
								<c:otherwise>&nbsp;</c:otherwise>
							</c:choose>
						</td>
						<td align="center">
							<a href="#" onclick="goOperate( 'edit', '${basepro.proCode}');">编辑</a>
						</td>
					</tr>
				</c:forEach>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="9">
							<span>${ page.pageInfo}</span>
						</td>
					</tr>
				</tfoot>
			</table>
		</div>
		</c:if>
	</body>
</html>
