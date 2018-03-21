<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://nbf.river.org/vxds" prefix="v"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>证书查询</title>
		<link href="<%=request.getContextPath()%>/common/style/style.css"
			rel="stylesheet" type="text/css" />
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/common/js/riverdefine.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/common/js/riverajax.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/common/js/clientfuns.js"></script>
		<script type="text/javascript">
			function check(){
				var studExamCode=document.getElementById("studExamCode").value;
				if(isNull(studExamCode)){
					alert("请填写查询条件！");
					return false;
				}else{
					return true;
				}
			}
		</script>
	</head>
	<body>
		<!--导航---start-->
		<div class="dqwz">
			<div style="margin-right: 10px;">
				<span style="cursor: pointer;" id="helpSpan"
					onclick="javascript:showHelpDiv('Z51006');">- 帮助 -</span>
				<span class="pageCode">No.Z51006</span>${currNavigation}
			</div>
		</div>
		<jsp:include page="/help/helpJs.jsp" />
		<iframe name="ifm" id="helpIfm" scrolling="no" frameborder="0"
			width="100%" style="display: none;"
			onload="this.height=this.contentWindow.document.body.scrollHeight+13"></iframe>
		<!--导航---end-->
		<!--查询条件开始-->
		<h2 align="center" style="color: #048CAE">
			2003年前高等教育自学考试成绩查询
		</h2>
		<div
			style="width: 600px; margin: 5px auto; border: 1px #cccccc solid; padding: 5px; background-color: #FDF8DF">
			<form method="post" name="form1" action="c_qry2003CJCX.do"
				onsubmit="return check()">
				<div style="margin-top: 20px; float: left">
					<dl>
						<dt style="font-size: 16px; font-weight: bold;">
							旧准考证号：
						</dt>
						<dd style="font-size: 16px; font-weight: bold;">
							<input class="inputText inputTextM" type="text"
								name="studOldCode" id="studOldCode" />
						</dd>
					</dl>
				</div>
				<div style="float: left">
					<input type="submit" value=""
						style="border: 0; width: 71px; height: 71px; cursor: hand; background: url(<%=request.getContextPath()%>/common/style/one-button-bg2.gif );" />
				</div>
			</form>
			<div class="clear"></div>
			<!--查询条件end-->
		</div>
		<hr style="border: 1px #cccccc dashed" size="1" />
		<div style="font-size: 18px">
			<p>
				本系统查询：
				<a
					href="<%=request.getContextPath()%>/manager/day/dailywork/c_manager.do">自考毕业证书</a>
				<a
					href="<%=request.getContextPath()%>/manager/day/dailywork/c_goXLWP.do">学历文凭毕业证</a>
				<a
					href="<%=request.getContextPath()%>/manager/day/dailywork/c_go2003CJCX.do">2003年前自考成绩查询</a>
			</p>
			<c:forEach var="codeBean" items="${codeList}">
				<p>
					${codeBean.name}：
					<c:forEach var="cBean" items="${certList}">
						<c:if test="${codeBean.code==cBean.certificatType}">
							<a href="${cBean.certificatQueryUrl }" target="_blank">${cBean.certificatQueryName
								}</a>
						</c:if>
					</c:forEach>
				</p>
			</c:forEach>
		</div>

	</body>
</html>