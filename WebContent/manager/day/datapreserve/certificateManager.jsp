<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>证书维护</title>
		<link href="<%=request.getContextPath()%>/common/style/style.css"
			rel="stylesheet" type="text/css" />
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/common/js/jquery-1.3.2.min.js"></script>
		<script type="text/javascript">
			function ajaxGetCid(){
				var ctype=document.getElementById("ctype").value;
				if(ctype==""){
					return ;
				}
				var str="ctype="+ctype;
				var ajaxurl="qc_ajaxGetCid.do";
				$.ajax({
				type:"post",	
				url:ajaxurl,
				data:str,
				async:false,
				success:function(msg){
					if(msg!=""){
						document.getElementById("show").innerHTML=msg;
					}
				}
			});
			}
		</script>
	</head>

	<body>
		<br />
		<div class="dqwz">
			<div style="margin-right: 10px;">
				<span style="cursor: pointer;" id="helpSpan"
					onclick="javascript:showHelpDiv('Z53015');">- 帮助 -</span>
				<span class="pageCode">No.Z53015</span>${currNavigation}
			</div>
		</div>
		<jsp:include page="/help/helpJs.jsp"/>
		<iframe name="ifm" id="helpIfm" scrolling="no" frameborder="0"
			width="100%" style="display: none;"
			onload="this.height=this.contentWindow.document.body.scrollHeight+13"></iframe>
		<!--导航---end-->

		<!--查询条件开始-->
		<form action="qc_manager.do" method="post" name="form1">
		<div class="tjcx">
				<dl>
					<dt>
						证书类型：
					</dt>
					<dd>
						<select class="inputText inputTextM" id="ctype" name="ctype" onchange="ajaxGetCid()">
							<option	value="">
								---请选择---
							</option>
							<c:forEach var="btype" items="${codeList}">
								<option value="${btype.code }" <c:if test="${btype.code==ctype }">selected</c:if>>
								${btype.name }
								</option>
							</c:forEach>
						</select>
					</dd>
				</dl>
				<dl>
					<dt>
						证书名称：
					</dt>
					<dd>
						<span id="show">
						<select class="inputText inputTextM" name="cid">
							<option value="">
								---请选择---
							</option>
							<c:forEach var="cert" items="${certList}">
								<option value="${cert.certificatQueryId }" <c:if test="${cert.certificatQueryId==cid }">selected</c:if>>
								${cert.certificatQueryName}
								</option>
							</c:forEach>
						</select>
						</span>
					</dd>
				</dl>
				<dl>
					<dt>
						&nbsp;
					</dt>
					<dd>
						<input class="inputButton" type="submit" value="查  询" />
					</dd>
				</dl>
		</div>
		<div class="button">
			<input class="inputButton" type="button"
				onclick="location.href='qc_toAddCert.do';" value="添 加" />
		</div>
		</form>
		<div class="clear"></div>
		<!--查询条件end-->

		<!--列表样式---表格----开始-->
		<div class="list">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<thead>
					<tr>
						<th  align="center">
							序号
						</th>
						<th>
							证书类型
						</th>
						<th>
							证书名称
						</th>
						<th>
							查询地址
						</th>
						<th>
							是否可用
						</th>
						<th  align="center">
							操作
						</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach var="bean" items="${dataList}" varStatus="h">
					<tr>
						<td align="center">
						${page.pagesize*page.pagenum+ h.index+1}
						</td>
						<td align="center">
							${bean.certificatTypeName}
						</td>
						<td align="center">
							${bean.certificatQueryName}
						</td>
						<td >
							${bean.certificatQueryUrl}
						</td>
						<td  align="center">
							<c:if test="${bean.isUse=='1'}">可用</c:if>
							<c:if test="${bean.isUse=='0'}">禁用</c:if>
						</td>
						<td align="center">
							<a href="qc_toEdit.do?cid=${bean.certificatQueryId}">修改</a>&nbsp;|&nbsp;<span onclick="dele('${bean.certificatQueryId}')"><a href="#"> 删除</a></span>
						</td>
					</tr>
				</c:forEach>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="6">
							<span>${page.pageInfo }</span>
						</td>
					</tr>
				</tfoot>
			</table>
		</div>
		<!--列表样式---表格----end-->
	</body>
	<script type="text/javascript">
		function dele(str){
			if(confirm("您确定删除吗？")){
				location.href="qc_delete.do?cid="+str;
			}
		}
	</script>
</html>
