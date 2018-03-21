<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>信息分类添加</title>
		<link href="<%=request.getContextPath()%>/common/style/style.css"
			rel="stylesheet" type="text/css" />
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/common/js/clientfuns.js"></script>
		<script type="text/javascript">
			function check(){
				var classid=document.getElementById("classid").value;
		    	if(isNull(classid)){
		    		alert("栏目编号不能为空！");
		    		return false;
		    	}else if(!CheckInteger(classid)){
		    	 	alert("栏目编号只能是正整数！");
		    	 	return false;
		    	}else if(classid>10000){
		    	 	alert("栏目编号值过大！");
		    	 	return false;
		    	}
		    	
		    	var classname=document.getElementById("classname").value;
		    	if(isNull(classname)){
		    		alert("栏目名称不能为空！");
		    		return false;
		    	}else if(getStrLen(classname)>100){
		    	 	alert("您所填写的栏目名称过长(不能超过50个字)！");
		    	 	return false;
		    	}else if(CheckStr(classname)){
		    	  	alert("栏目名称中含有非法字符！");
		    	  	return false;
		    	}
		    	
		    	var customurl=document.getElementById("customurl").value;
		    	if(!isNull(customurl)){
		    		var reg=/[<>"]/;
		    		if(reg.test(customurl)){
		    			alert("无效链接");
		    			return false;
		    		}
		    	}
		    	
		    	if(!checkRadio("userRole")){
		    		alert("请选择栏目权限！");
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
					onclick="javascript:showHelpDiv('Z52001');">- 帮助 -</span>
				<span class="pageCode">No.Z52001</span>${currNavigation}
			</div>
		</div>
		<jsp:include page="/help/helpJs.jsp" />
		<iframe name="ifm" id="helpIfm" scrolling="no" frameborder="0"
			width="100%" style="display: none;"
			onload="this.height=this.contentWindow.document.body.scrollHeight+13"></iframe>
		<!--导航---end-->
		<!--编辑页面----开始-->
		<form action="class_doedit.do" method="post" onsubmit="return check()">
		<div class="infoedit">			
				<input type="hidden" value="${newsClass.classId}" name="classid"/>
					<dl>
						<dt>
							栏目编号：
						</dt>
						<dd>
							<input class="inputText" style="width: 50px;" type="text"
								name="newsClass.classId" id="classid" value="${newsClass.classId }" readonly/>
							<span>(必须填写)</span>
						</dd>
					</dl>
					<dl>
						<dt>
							信息分类名称：
						</dt>
						<dd>
							<input class="inputText" type="text" name="newsClass.className"
								id="classname" value="${newsClass.className }"/>
							<span>(必须填写)</span>
						</dd>
					</dl>
					<dl>
						<dt>
							显示：
						</dt>
						<dd>
							<input name="newsClass.isShow" type="radio" value="1"
								<c:if test="${newsClass.isShow=='1' }">checked</c:if> />
							是
							<input name="newsClass.isShow" type="radio" value="0" <c:if test="${newsClass.isShow=='0' }">checked</c:if> />
							否
						</dd>
					</dl>
					<dl>
						<dt>
							外部链接：
						</dt>
						<dd>
							<input class="inputText" type="text" name="newsClass.customUrl"
								id="customurl" value="${newsClass.customUrl}"/>
							<span>直接使用该地址作为url</span>
						</dd>
					</dl>
					<dl>
						<dt>
							信息管理权限：
						</dt>
						<dd>
							<input type="checkbox" name="userRole" value="1" <c:if test="${newsClass.userRole=='1'||newsClass.userRole=='3'||newsClass.userRole=='5'||newsClass.userRole=='7' }">checked</c:if>/>
							省考办
							<input type="checkbox" name="userRole" value="2" <c:if test="${newsClass.userRole=='2'||newsClass.userRole=='3'||newsClass.userRole=='6'||newsClass.userRole=='7' }">checked</c:if>/>
							市考办
							<input type="checkbox" name="userRole" value="4" <c:if test="${newsClass.userRole=='4'||newsClass.userRole=='5'||newsClass.userRole=='6'||newsClass.userRole=='7'}">checked</c:if>/>
							主考院校
						</dd>
					</dl>
					<div class="clear"></div>
		</div>
		<div class="button">
			<input class="inputButton" type="submit" value='保 存' />
			<input class="inputButton" type="reset" value='重 置' />
			<input class="inputButton" type="button" value='返 回'
				onclick="location.href='class_manager.do'" />
		</div>
		</form>
		<!--编辑页面----end-->
	</body>
</html>
