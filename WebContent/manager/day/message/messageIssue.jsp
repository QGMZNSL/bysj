<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>信息发布</title>
		<link href="<%=request.getContextPath()%>/common/style/style.css"
			rel="stylesheet" type="text/css" />
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/common/js/clientfuns.js"></script>
<script type="text/javascript"
			src="<%=request.getContextPath()%>/common/js/jquery-1.3.2.min.js"></script>
			
			<script type="text/javascript">
			//审核通过
			 function onissue(){
			 	var issuef=document.getElementById("issueform");
			 	var artids=document.getElementsByName("artids");
			 	var tmpBox;
			 	var isok=false;
			 	for ( var i = 0; i < artids.length; i++) {
					tmpBox = artids[i];
					if ( tmpBox.checked == true || tmpBox.checked == "checked") {
						isok = true;
						break;
					} 
				}				
			 	if(isok){
			 		if(confirm("你确定审核通过吗！")){
						issuef.action="message_onissue.do";
				 	    issuef.submit();
					}
			 	}else{
			 		alert("请至少选择一条信息！");
			 	}
			 }
			 //审核不通过
			  function unissue(){
			 	var issuef=document.getElementById("issueform");
			 	var reason=document.getElementById("reason").value;
			 	if(isNull(trim(reason))){
			 		alert("请填写审核未通过原因！");
			 		return ;
			 	}
			 	if(getStrLen(reason)>200){
			 		alert("审核未通过原因不能超过100字！");
			 		return ;
			 	}
			 	
			 	var artids=document.getElementsByName("artids");
			 	var tmpBox;
			 	var k=0;
			 	for ( var i = 0; i < artids.length; i++) {
					tmpBox = artids[i];
					if ( tmpBox.checked == true || tmpBox.checked == "checked") {
						k++;
					} 
					if(k>1){
						break;
					}
				}
				if(k==1){
					var reasons=document.getElementById("reasons");
					reasons.value=reason;
					issuef.action="message_unissue.do";
				 	issuef.submit();				
				}else if(k==0){
					alert("请选择一条信息！");
				}else{
					alert("只能选择一条信息！");
				}		
			 	
			 }
			 //撤销审核
			 function noissue(){
			 	var issuef=document.getElementById("issueform");
			 	var artids=document.getElementsByName("artids");
			 	var tmpBox;
			 	var isok=false;
			 	for ( var i = 0; i < artids.length; i++) {
					tmpBox = artids[i];
					if ( tmpBox.checked == true || tmpBox.checked == "checked") {
						isok = true;
						break;
					} 
				}				
			 	if(isok){
			 		if(confirm("你确定撤销审核吗！")){
						issuef.action="message_noissue.do";
			 			issuef.submit();
					}
			 	}else{
			 		alert("请至少选择一条信息！");
			 	}
			 	
			 }
			
			</script>
	</head>

	<body>
		<!--导航---start-->
	<div class="dqwz">
			<div style="margin-right: 10px;">
				<span style="cursor: pointer;" id="helpSpan"
					onclick="javascript:showHelpDiv('Z52003');">- 帮助 -</span>
				<span class="pageCode">No.Z52003</span>${currNavigation}
			</div>
		</div>
		<jsp:include page="/help/helpJs.jsp" />
		<iframe name="ifm" id="helpIfm" scrolling="no" frameborder="0"
			width="100%" style="display: none;"
			onload="this.height=this.contentWindow.document.body.scrollHeight+13"></iframe>
		<!--导航---end-->
		<!--查询条件开始-->
		<form method="post" name="form1" action="message_issueselect.do">
			<div class="tjcx">
				<dl>
					<dt>
						分类：
					</dt>
					<dd>
						<select class="inputText inputTextM" name="classid">
							<option value="">
								---请选择---
							</option>
							<c:forEach var="class" items="${allClass}">
								<option value="${class.classId }"
									<c:if test="${classid==class.classId }">selected</c:if>>
									${class.className }
								</option>
							</c:forEach>
						</select>
					</dd>
				</dl>
				<dl>
					<dt>
						状态：
					</dt>
					<dd>
						<select class="inputText inputTextM" name="status">
							<option value="">
								全部
							</option>
							<option value="0" <c:if test="${status=='0' }">selected</c:if>>
								未审核
							</option>
							<option value="1" <c:if test="${status=='1' }">selected</c:if>>
								审核通过
							</option>
							<option value="2" <c:if test="${status=='2' }">selected</c:if>>
								审核不通过
							</option>
						</select>
					</dd>
				</dl>
			</div>
			<div class="button">
				<input class="inputButton" type="submit" value="查 询" />
			</div>
		</form>
		<div class="clear"></div>
		<!--查询条件end-->


		<!--列表样式---表格----开始-->
		<div class="list">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				style="text-align: center">
				<caption></caption>
				<thead>
					<tr>
						<th>
							<span onclick="cboxCheckOpposite('artids')">反选</span>
						</th>
						<th>
							分类
						</th>
						<th>
							标题
						</th>
						<th>
							发布单位
						</th>
						<th>
							发布人
						</th>
						<th>
							发布时间
						</th>
						<th>
							状态
						</th>
					</tr>
				</thead>
				<tbody>
					<form method="post" id="issueform">
						<c:forEach var="art" items="${artlist}" varStatus="h">
							<tr>
								<td width="30">
									<input type="checkbox" name="artids" value="${art.articleId }" />
									<br />
								</td>
								<td>
									${art.className }
									<br />
								</td>
								<td align="left">
									<a href="message_query.do?artid=${art.articleId }">${fn:substring(art.articleTitle,0,38)
										}</a>
									<br />
								</td>
								<td align="left">
									${art.articleAuthor }
									<br />
								</td>
								<td>
									${art.articleAddUser }
									<br />
								</td>
								<td>
									${art.articleAddTime }
									<br />
								</td>
								<td>
									<c:if test="${art.articleAuditStatus=='0'}">
							 未审核
							</c:if>
									<c:if test="${art.articleAuditStatus=='1'}">
							 审核通过
							</c:if>
									<c:if test="${art.articleAuditStatus=='2'}">
							 审核未通过
							</c:if>
								</td>

							</tr>
						</c:forEach>
						<input type="hidden" value="" id="reasons" name="articleUnAuditReason"/>
					</form>
				</tbody>
			</table>
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tfoot>
					<tr>
						<td colspan="11">
							<span> ${page.pageInfo } </span>
							<input class="inputButton" type="button" value="审核通过"
								onclick="onissue()"></input>
							审核不通过原因：
							<input class="inputText" type="text" 
								id="reason"></input>
							<input class="inputButton" type="button" value="审核不通过"
								onclick="unissue()"></input>
							&nbsp;&nbsp;
							<input class="inputButton" type="button" value="撤销审核"
								onclick="noissue()"></input>

						</td>
					</tr>
				</tfoot>
			</table>
				
		</div>
		<!--列表样式---表格----end-->
	</body>
</html>
