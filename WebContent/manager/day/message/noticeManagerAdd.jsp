<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>须知添加</title>
		<link href="<%=request.getContextPath()%>/common/style/style.css"
			rel="stylesheet" type="text/css" />
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/common/kindeditor-3.4/kindeditor.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/common/js/clientfuns.js"></script>
		<script type="text/javascript">
		    KE.show({
		        id : 'content',
		        cssPath : '<%=request.getContextPath()%>/common/kindeditor-3.4/index.css'
		    });
        </script>
        <script type="text/javascript">
		    function check(){
		    	var title=document.getElementById("title").value;
		    	if(isNull(title)){
		    		alert("标题不能为空！");
		    		return false;
		    	}else if(getStrLen(title)>100){
		    	 	alert("您所填写的标题过长(不能超过50字)！");
		    	 	return false;
		    	}else if(CheckStr(title)){
		    	  	alert("标题中含有非法字符！");
		    	  	return false;
		    	}
		    	
		    	var classid=document.getElementById("classid").value;
		    	if(isNull(classid)){
		    		alert("请选择分类！");
		    		return false;
		    	}
		    	
		    	return true;   	
    		}
        </script>
	</head>

	<body>
		<!--导航---start-->
		<div class="dqwz">
			<div style="margin-right: 10px;">
				<span style="cursor: pointer;" id="helpSpan"
					onclick="javascript:showHelpDiv('Z52004');">- 帮助 -</span>
				<span class="pageCode">No.Z52004</span>${currNavigation}
			</div>
		</div>
		<jsp:include page="/help/helpJs.jsp" />
		<iframe name="ifm" id="helpIfm" scrolling="no" frameborder="0"
			width="100%" style="display: none;"
			onload="this.height=this.contentWindow.document.body.scrollHeight+13"></iframe>
		<!--导航---end-->
		<!--编辑页面----开始-->
		<form action="information_doadd.do" method="post" enctype="multipart/form-data" onsubmit="return check()">
		<div class="infoedit">
				<dl>
					<dt>
						标题：
					</dt>
					<dd>
						<input class="inputText" type="text" name="information.informationTitle" id="title" />
						<span>(必须填写)</span>
					</dd>
				</dl>
				<dl>
					<dt>
						所属分类：
					</dt>
					<dd>
						<select class="inputText" name="information.classId" id="classid">
							<option>
								---请选择---
							</option>
							<c:forEach var="class" items="${infoClassList}">
								<option value="${class.classId }">
									${class.className }
								</option>
							</c:forEach>
						</select>
						<span>(必须填写)</span>
					</dd>
				</dl>
				<dl>
					<dt>
						内容：
					</dt>
					<dd>
						<textarea class="inputTextarea" name="information.informationMainContent"
							id="content"></textarea>
					</dd>
				</dl>
				<dl>
					<dt>
						附件：
					</dt>
					<dd>
						<input type="file" name="infoFile"  />
					</dd>
				</dl>
				<div class="clear"></div>
		</div>
		<div class="button">
			<input class="inputButton" type="submit" value='保 存' />
			<input class="inputButton" type="reset" value='重 置' />
			<input class="inputButton" type="button"  value='返 回'
				onclick="location.href='information_manager.do'" />
		</div>
			</form>
		<!--编辑页面----end-->
	</body>
</html>
