<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="v" uri="http://nbf.river.org/vxds"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>主考院校设置</title>
		<link href="<%=request.getContextPath()%>/common/style/style.css"
			rel="stylesheet" type="text/css" />
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/common/js/riverdefine.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/common/js/riverajax.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/common/js/jquery-1.3.2.min.js"></script>
	</head>

	<body>
		<!-- 页面导航 -->
		<div class="dqwz">
			<div style="margin-right: 10px;">
				<span style="cursor: pointer;" id="helpSpan"
					onclick="javascript:showHelpDiv('Z21005');">- 帮助 -</span>
				<span class="pageCode">No.Z21005</span>${currNavigation}
			</div>
		</div>
		<jsp:include page="/help/helpJs.jsp" />
		<iframe name="ifm" id="helpIfm" scrolling="no" frameborder="0"
			width="100%" style="display: none;"
			onload="this.height=this.contentWindow.document.body.scrollHeight+13"></iframe>	
		<!-- 编辑区域 -->
		<form action="<%=request.getContextPath()%>/plan/baseacademy_Edit.do" method="post" name="addForm">
		<v:bean	clazz="com.sincinfo.zxks.core.plan.AcademyAction" form="addForm">		
		<div class="infoedit">
			<h1>修改主考院校</h1>
			<dl>
				<dt>主考院校代码：</dt>
			    <dd>
					<input class="inputText" type="text" name="baseacademy.academyCode" id="academyCode" value="${baseacademy.academyCode}" />
					<v:v input="baseacademy.academyCode" exp="s(1,5)">请输入主考院校代码！(长度不能超过5位)</v:v>
		    	</dd>
		    </dl>
		    <dl>
		    <dt>主考院校名称：</dt>
			    <dd>
					<input class="inputText" type="text" name="baseacademy.academyName" id="academyName" value="${baseacademy.academyName}" />
					<v:v input="baseacademy.academyName" exp="s(1,20)">请输入主考院校名称！(长度不能超过20位)</v:v>
		    	</dd>
		    </dl>
		    <dl>
		    	<dt>所属市区：</dt>
			    <dd>			    	
				    <select class="inputText" name="baseacademy.cityCode" id="cityCode">
				    	<option>--- 请选择 ---</option>
				    	<c:forEach items="${basecityList}"  var="bl">
								<option value="${bl.cityCode}" <c:if test="${bl.cityCode==baseacademy.cityCode}">selected</c:if>>
									${bl.cityName}
								</option>
						</c:forEach>
				    </select>
		    	</dd>
		    </dl>
		    <dl>
		    	<dt>地址：</dt>
			    <dd>
						<input class="inputText" type="text" name="baseacademy.academyAddress" id="academyAddress" value="${baseacademy.academyAddress}" />
						<v:v input="baseacademy.academyAddress" exp="s(1,20)">请输入联系地址！(长度不能超过20位)</v:v>
		    	</dd>
		    </dl>
		    <dl>
		    	<dt>联系人：</dt>
			    <dd>
						<input class="inputText" type="text" name="baseacademy.academyLinkman" id="academyLinkman" value="${baseacademy.academyLinkman}" />
						<v:v input="baseacademy.academyLinkman" exp="s(1,20)">请输入联系人！(长度不能超过20位)</v:v>
		    	</dd>
		    </dl>
		    <dl>
		    	<dt>联系方式：</dt>
			    <dd>
						<input class="inputText" type="text" name="baseacademy.academyTelephone" id="academyTelephone" value="${baseacademy.academyTelephone}" />
						<v:v input="baseacademy.academyTelephone" exp="s(1,20)">请输入联系方式！(长度不能超过20位)</v:v>
		    	</dd>
		    </dl>
		    <dl>
		    	<dt>启用状态：</dt>
			    <dd>
				    <select class="inputText" name="baseacademy.isUse" id="isUse">
				    	<option value="1" <c:if test="${baseacademy.isUse==1}">selected</c:if> >启用</option>
				    	<option value="0" <c:if test="${baseacademy.isUse==0}">selected</c:if> >禁用</option>
				    </select>
			    </dd>
		    </dl>
		        <div class="clear"></div>
		    <dl>
			    <dt>备注：</dt>
			    <dd>
					<input class="inputText" type="text" name="baseacademy.remarks" id="remarks" value="${baseacademy.remarks}" />			
				</dd>
		    </dl>
			<div class="clear"></div>			
		</div>
			<div class="button">
			<input class="inputButton" type="submit" value="确 定" />
			<input class="inputButton" type="reset"  value="重 置" />
			<input class="inputButton" type="button" value="返 回" 
			onclick="location.href='<%=request.getContextPath()%>/plan/baseacademy_Show.do';" />
		</div>
		</v:bean>
		</form>
	</body>
</html>
