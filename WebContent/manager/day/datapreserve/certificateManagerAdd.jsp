<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://nbf.river.org/vxds" prefix="v"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>证书查询管理-添加</title>
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
				var curl=document.getElementById("curl").value;
				if(isNull(curl)){
					alert("请填写访问地址！");
					return false;
				}else if(!IsURL(curl)){
					alert("请正确填写访问地址！");
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
					onclick="javascript:showHelpDiv('Z53015');">- 帮助 -</span>
				<span class="pageCode">No.Z53015</span>${currNavigation}
			</div>
		</div>
		<jsp:include page="/help/helpJs.jsp"/>
		<iframe name="ifm" id="helpIfm" scrolling="no" frameborder="0"
			width="100%" style="display: none;"
			onload="this.height=this.contentWindow.document.body.scrollHeight+13"></iframe>
		<!--导航---end-->
		<form action="qc_doAddCert.do" method="post" name="addform" onsubmit="return check()">
		<v:bean clazz="com.sincinfo.zxks.core.day.datapreserve.CertificateManagerAction"
												form="addform" >
		<div class="infoedit">
			<dl>
				<dt>
					证书类型：
				</dt>
				<dd>
					<select class="inputText inputTextM" name="ctype" >
						<option>
							---请选择---
						</option>
						<c:forEach var="btype" items="${codeList}">
								<option value="${btype.code }" >
								${btype.name }
								</option>
						</c:forEach>
					</select><v:v input="ctype">请选择证书类型！</v:v>
				</dd>
			</dl>
			<dl>
				<dt>
					证书名称：
				</dt>
				<dd>
					<input class="inputText" type="text" name="cname"/><v:v input="cname">请正确填写证书名称！</v:v>
				</dd>
			</dl>
			<dl>
				<dt>
					访问地址：
				</dt>
				<dd>
					<input class="inputText" type="text"  name="curl" id="curl"/>
					
				</dd>
			</dl>
			<dl>
				<dt>
					是否可用：
				</dt>
				<dd>
					<input type="radio" name="isuse" value="0"/>禁用<input type="radio" name="isuse" value="1" checked/>可用
				</dd>
			</dl>
			<div class="clear"></div>
		</div>
		<div class="button">
			<input class="inputButton" type="submit" value="保 存" />
			<input class="inputButton" type="button"
				onclick="location.href='qc_manager.do';" value="返 回" />
		</div>
		</v:bean>
	</form>
	</body>
</html>
