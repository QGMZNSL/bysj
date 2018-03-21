<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="v" uri="http://nbf.river.org/vxds"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>专业设置--考籍</title>
		<link href="<%=request.getContextPath()%>/common/style/style.css"
			rel="stylesheet" type="text/css" />
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/common/js/riverdefine.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/common/js/riverajax.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/common/js/jquery-1.3.2.min.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/common/js/clientfuns.js"></script>
		<script type="text/javascript">
			function getProName(){
				var proCode=$('#proCode').val();
				if(isNull(proCode)){
					return ;
				}
				var str="proCode="+proCode;
				var url="<%=request.getContextPath()%>/plan/baseprokj_getProName.do";
				$.ajax({
				   type: "post",
				   url: url,
				   data: str,
				   success: function(msg){
				    	if(msg=="nocode"){
				    		alert("请填写专业代码！");
				    		document.getElementById("proName").value="";
				    	}else if(msg=="nodata"){
				    		alert("专业代码填写有误！");
				    		document.getElementById("proName").value="";
				    	}else{
				    		document.getElementById("proName").value=msg;
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
					onclick="javascript:showHelpDiv('Z21013');">- 帮助 -</span>
				<span class="pageCode">No.Z21013</span>${currNavigation}
			</div>
		</div>
		<jsp:include page="/help/helpJs.jsp" />
		<iframe name="ifm" id="helpIfm" scrolling="no" frameborder="0"
			width="100%" style="display: none;"
			onload="this.height=this.contentWindow.document.body.scrollHeight+13"></iframe>

		<!-- 编辑区域 -->
			<form action="<%=request.getContextPath()%>/plan/baseprokj_Add.do" method="post" name="addForm">
					<v:bean clazz="com.sincinfo.zxks.core.plan.BaseproKjAction" form="addForm">
			<div class="infoedit">
				<h1>
					添加专业
				</h1>
				<dl>
					<dt>
						专业代码：
					</dt>
					<dd>
						<input class="inputText" type="text" name="proCode" id="proCode" onblur="getProName()" />
						<v:v input="proCode" exp="s(1,10)">请输入专业代码！(最长不能超过10位)</v:v>
					</dd>
				</dl>
				<dl>
					<dt>
						专业名称：
					</dt>
					<dd>
						<input class="inputText" type="text" readonly id="proName" value="${basepro.proName}" />						
					</dd>
				</dl>
				<div class="clear"></div>
			</div>
			<div class="button">
				<input class="inputButton" type="submit" value="确 定" />
				<input class="inputButton" type="reset"  value="重 置" />
				<input class="inputButton" type="button" value="返 回"
					onclick="location.href='<%=request.getContextPath()%>/plan/baseprokj_Show.do'" />
			</div>
			</v:bean>
		</form>
	</body>
</html>
