<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>主考院校设置</title>
		<link href="<%=request.getContextPath()%>/common/style/style.css"
			rel="stylesheet" type="text/css" />
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/common/js/jquery-1.3.2.min.js"></script>
		<script type="text/javascript">
		var goOperate = function(type,proCode,academyCode) {
			var url;
			var gotoFlag = true;
			if ( type == 'del') {
				url = '<%=request.getContextPath()%>/plan/baseacademy_Del.do?baseacademy.proCode='+proCode+'&baseacademy.academyCode=' + academyCode;
				gotoFlag = confirm('确认移除该主考院校的${ basepro.proName}专业吗？');
			} else {
				return;
			}
			
			if ( gotoFlag) {
				location.href = url;
			}
		};		
		$(document).ready( function() {
			$('#addShow').click( function() {
				$('#addArea').show("slow");
			});
			
			$('#addNone').click( function() {
				$('#addArea').hide("slow");
			});
			
			$('#addArea').css("display", "none");
		});
		</script>
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

		<!-- 显示区域 -->
		<form action="" method="post" name="">
			
			<div class="infoedit">
				<h1>
					设置主考院校专业
				</h1>
				<dl>
					<dt>
						主考院校代码：
					</dt>
					<dd>
						${ baseacademy.academyCode}
					</dd>
				</dl>
				<dl>
					<dt>
						主考院校名称：
					</dt>
					<dd>
						${ baseacademy.academyName}
					</dd>
				</dl>
				<dl>
					<dt>
						所属市区：
					</dt>
					<dd>
						${ baseacademy.cityName}
					</dd>
				</dl>
				<dl>
					<dt>
						启用状态：
					</dt>
					<dd>
							<c:choose>
								<c:when test="${ baseacademy.isUse == 0}">禁用</c:when>							
								<c:when test="${ baseacademy.isUse == 1}">启用</c:when>
								<c:otherwise>&nbsp;</c:otherwise>
							</c:choose>	
					</dd>
				</dl>
				<div class="clear"></div>
				<dl>
					<dt>
						备注：
					</dt>
					<dd>
						${ baseacademybaseacademy.remarks}&nbsp;
					</dd>
				</dl>
				<div class="clear"></div>
			</div>
			<div class="button">
				<input class="inputButton" type="button" id="addShow" value="添加专业" />
				<input class="inputButton" type="button" value="返 回"
					onclick="location.href='<%=request.getContextPath()%>/plan/baseacademy_Show.do';" />
			</div>
		</form>
		<!-- 显示专业列表 -->
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
						<th width="80">
							操作
						</th>
					</tr>
				</thead>
				<tbody>
				    <c:forEach items="${ baseproList}" var="basepro" varStatus="ctNum">
					<tr>
						<td align="center">
							${ ctNum.count}
						</td>
						<td align="center">
							${ basepro.proCode}
						</td>
						<td align="center">
							${ basepro.proName}
						</td>
						<td align="center">
							${ basepro.proPartcode}&nbsp;
						</td>
						<td align="center">
							${ basepro.proTypeName}&nbsp;
						</td>
						<td align="center">
							<a href="#" onclick="goOperate('del','${ basepro.proCode}','${ baseacademy.academyCode}');">删除</a>
						</td>
					</tr>
					</c:forEach>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="6">
							<span>${ page.pageInfo}</span>
						</td>
					</tr>
				</tfoot>
			</table>
		</div>
		</c:if>

		<!-- 编辑专业区域 -->
		<div id="addArea">
			<div class="dqwz">
				设置专业
			</div>
			<div class="infoedit">
				<form method="post" name="setForm" action="<%=request.getContextPath()%>/plan/baseacademy_AddPro.do">
					<dl>
						<dt>
							选择专业：
						</dt>
						<dd>
							<input class="inputText" width="300" type="text" id="proName"
							readonly="readonly" value="点击选择专业" name="professionName" 	style="cursor:hand;"
							onclick="this.value='',window.open('<%=request.getContextPath()%>/plan/baseacademy_SelPro.do','_blank', 'top=150,left=300,height=600,width=500,resizable=0,toolbar=0,menubar=0,location=0');" />
							<input type="hidden" name="baseacademy.proCode" id="proCode" value="${baseacademy.proCode}" />
							<input type="hidden" name="baseacademy.academyCode" id="academyCode" value="${baseacademy.academyCode}"/>
						</dd>
						<div class="clear"></div>
					</dl>
					<div class="clear"></div>
				<div class="button">
					<input class="inputButton" type="submit" value="添 加" />
					<input class="inputButton" type="button" value="取 消" id="addNone" />
				</div>
				</form>
			</div>
		</div>

	</body>
</html>
