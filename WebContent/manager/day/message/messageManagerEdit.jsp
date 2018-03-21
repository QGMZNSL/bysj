<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>信息修改</title>
		<link href="<%=request.getContextPath()%>/common/style/style.css"
			rel="stylesheet" type="text/css" />
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/common/js/jquery-1.3.2.min.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/common/kindeditor-3.4/kindeditor.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/common/js/clientfuns.js"></script>
		<link href="<%=request.getContextPath()%>/common/js/ui.datepicker.css"
			rel="stylesheet" type="text/css" />
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/common/js/ui.datepicker-zh-CN.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/common/js/ui.datepicker.js"></script>
		<script type="text/javascript">
		    KE.show({
		        id : 'content',
		        cssPath : '<%=request.getContextPath()%>/common/kindeditor-3.4/index.css'
		    });
        </script>
		<script type="text/javascript">
		$(document).ready( function() {
			$("#startDate").datepicker($.extend({ dateFormat: 'yyyy-MM-dd', showOn: 'both', buttonImage: '<%=request.getContextPath()%>/common/js/calendarNull.gif', buttonImageOnly: true},$.datepicker.regional['zh-CN']));
		});
		</script>
		<script type="text/javascript">
		    function check(){
		    	var title=document.getElementById("title").value;
		    	if(isNull(title)){
		    		alert("标题不能为空！");
		    		return false;
		    	}else if(getStrLen(title)>200){
		    	 	alert("您所填写的标题过长(不能超过100字)！");
		    	 	return false;
		    	}else if(CheckStr(title)){
		    	  	alert("标题中含有非法字符！");
		    	  	return false;
		    	}
		    	
		    	var subtitle=document.getElementById("subtitle").value;
		    	if(isNull(subtitle)){
		    		alert("副标题不能为空！");
		    		return false;
		    	}else if(getStrLen(subtitle)>100){
		    	 	alert("您所填写的副标题过长(不能超过50字)！");
		    	 	return false;
		    	}else if(CheckStr(subtitle)){
		    	  	alert("副标题中含有非法字符！");
		    	  	return false;
		    	}
		    	
		    	var author=document.getElementById("author").value;
		    	if(isNull(author)){
		    		alert("发布单位不能为空！");
		    		return false;
		    	}else if(getStrLen(author)>30){
		    	 	alert("您所填写的发布单位过长(不能超过15字)！");
		    	 	return false;
		    	}else if(CheckStr(author)){
		    	  	alert("副标题中含有非法字符！");
		    	  	return false;
		    	}
		    	
		    	var classid=document.getElementById("classid").value;
		    	if(isNull(classid)){
		    		alert("请选择分类！");
		    		return false;
		    	}
		    	
		    	var keyword=document.getElementById("keyword").value;
		    	if(CheckStr(keyword)){
		    	  	alert("关键字中含有非法字符！");
		    	  	return false;
		    	}
		    	
		    	var startDate=document.getElementById("startDate").value;
		    	if(isNull(startDate)){
		    		alert("发布时间不能为空！");
		    	  	return false;
		    	}
		    	
		    	
		    	return true;   	
    		}
        </script>
        <script type="text/javascript">		
			function changecursor(obj){
				obj.style.cursor="hand";
			}
			function noneFile(){
			  var file=document.getElementById("file");
			  file.innerHTML="<dt><input type='hidden' value='1' name='ornoFile' readonly /></dt><dd></dd>";
			}
		</script>
        
	</head>

	<body>
		<!--导航---start-->
			<div class="dqwz">
			<div style="margin-right: 10px;">
				<span style="cursor: pointer;" id="helpSpan"
					onclick="javascript:showHelpDiv('Z52002');">- 帮助 -</span>
				<span class="pageCode">No.Z52002</span>${currNavigation}
			</div>
		</div>
		<jsp:include page="/help/helpJs.jsp" />
		<iframe name="ifm" id="helpIfm" scrolling="no" frameborder="0"
			width="100%" style="display: none;"
			onload="this.height=this.contentWindow.document.body.scrollHeight+13"></iframe>
		<!--导航---end-->
		<!--编辑页面----开始-->
		<form action="message_doedit.do" method="post" name="editform"
			onsubmit="return check()" enctype="multipart/form-data">
			<div class="infoedit">
				<input type="hidden" name="message.articleId"
					value="${article.articleId }" id="artid"/>
				<input type="hidden" name="message.articleAuditStatus"
					value="${article.articleAuditStatus }" />
				<dl>
					<dt>
						标题：
					</dt>
					<dd>
						<input class="inputText" type="text" name="message.articleTitle"
							value="${article.articleTitle }" id="title" maxlength="200"/>
						<span>(必须填写)</span>
					</dd>
				</dl>
				<dl>
					<dt>
						副标题：
					</dt>
					<dd>
						<input class="inputText" type="text"
							name="message.articleSubTitle" id="subtitle"
							value="${article.articleSubTitle }"  maxlength="100"/>
						<span>(必须填写)</span>
					</dd>
				</dl>
				<dl>
					<dt>
						发布单位：
					</dt>
					<dd>
						<input class="inputText" type="text" name="message.articleAuthor"
							id="author" value="${article.articleAuthor}" />
						<span>(必须填写)</span>
					</dd>
				</dl>
				<dl>
					<dt>
						所属分类：
					</dt>
					<dd>
						<select class="inputText inputTextM" name="message.classId"
							id="classid">
							<c:forEach var="class" items="${allClass}">
								<option value="${class.classId }"
									<c:if test="${article.classId==class.classId }">selected</c:if>>
									${class.className }
								</option>
							</c:forEach>
						</select>
						<span>(必须填写)</span>
					</dd>
				</dl>
				<dl>
					<dt>
						关键字：
					</dt>
					<dd>
						<input class="inputText" type="text" name="message.articleKeyWord"
							value="${article.articleKeyWord}" id="keyword" />
					</dd>
				</dl>
				<dl>
					<dt>
						发布时间：
					</dt>
					<dd>
						<input class="inputText inputTextM" id="startDate" type="text"
							name="message.articleAddTime" value="${article.articleAddTime }" />
					</dd>
				</dl>
				<dl>
					<dt>
						内容：
					</dt>
					<dd>
						<textarea class="inputTextarea" name="message.articleContent"
							id="content" >${article.articleContent }</textarea>
					</dd>
				</dl>
				<dl id="file">
					<dt>
						附件：
					</dt>
					<dd>
						<c:if
							test="${article.articleFile!=null &&(article.articleFile!='null')}">
							<input type="text" value="${article.articleFile }" name="ornoFile" readonly />
						</c:if>
						<input type="file" name="messageFile" />
						<span onclick="noneFile()" onmouseenter="changecursor(this)"><a>删除附件</a></span>
						
					</dd>
				</dl>
				<c:if test="${article.articleAuditStatus=='2'}">
					<dl>
						<dt>
							未通过原因：
						</dt>
						<dd>
							${article.articleUnAuditReason}
						</dd>
					</dl>
				</c:if>
				<div class="clear"></div>
			</div>
			<div class="button">
				<input class="inputButton" type="submit" value='保 存' />
				<input class="inputButton" type="reset" value='重 置' onclick="resetContent()"/>
				<input class="inputButton" type="button" value='返 回'
					onclick="location.href='message_manager.do'" />
			</div>
		</form>
		<!--编辑页面----end-->
		<script type="text/javascript">	
			function resetContent(){
		        var artid=document.getElementById("artid").value;
				location.href="message_toedit.do?artid="+artid;
			}
		</script>
	</body>
	<%
		String info = (String) request.getAttribute("info");
		if (info != null) {
	%>
	<script type="text/javascript">
		alert("<%=info%>");
	</script>

	<%
		}
	%>


</html>
