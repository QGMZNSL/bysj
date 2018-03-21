<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>专业课程信息汇编</title>
		<link href="<%=request.getContextPath()%>/common/style/style.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="<%=request.getContextPath()%>/common/js/jquery-1.3.2.min.js"></script>
		<script type="text/javascript">
		$(document).ready( function() {
			$('#ckType').change( function() {
				if ( this.value == "1") {
					$('#professionArea').show("slow");
				} else {
					$('#professionArea').hide("slow");
				}
			});
			$('#qryBtn').click( function() {
				if ( $('#ckType').val() == "1") {
					$('#byAll').hide("slow");
					$('#byProfession').css("display", "block");
					$('#byProfession').show("slow");
				} else {
				    $('#byProfession').css("display", "none");
					$('#byProfession').hide("slow");
					$('#byAll').show("slow");
				}
			});
			if ( $('#ckType').val() == "1") 
			  $('#professionArea').css("display", "inline");
			else
			  $('#professionArea').css("display", "none");
		});
		</script>
	</head>

	<body>
		<!-- 页面导航 -->
		<div class="dqwz">
			<div style="margin-right: 10px;">
				<span style="cursor: pointer;" id="helpSpan"
					onclick="javascript:showHelpDiv('Z21012');">- 帮助 -</span>
				<span class="pageCode">No.Z21012</span>${currNavigation}
			</div>
		</div>
		<jsp:include page="/help/helpJs.jsp" />
		<iframe name="ifm" id="helpIfm" scrolling="no" frameborder="0"
			width="100%" style="display: none;"
			onload="this.height=this.contentWindow.document.body.scrollHeight+13"></iframe>
		
		<!-- 查询条件 -->
		<form method="post" name="form1" action="<%=request.getContextPath()%>/plan/planInfos_qry.do">
			<div class="tjcx">
			<dl>
				<dt>查询方式：</dt>
				<dd><select class="inputText inputTextM" name="planInfos.ckType" id="ckType">
					<option value="0" <c:if test="${planInfos.ckType==0}">selected</c:if> >全部</option>
					<option value="1" <c:if test="${planInfos.ckType==1}">selected</c:if> >按专业</option>
				</select></dd>
			</dl>
			<dl id="professionArea">
				<dt>专业：</dt>
				<dd><input type="text" name="planInfos.ckProName" id="proName" value="${planInfos.ckProName}" class="inputText inputTextM" value="点击选择专业"  style="cursor:hand;" onclick="window.open('<%=request.getContextPath() %>/plan/baseacademy_SelPro.do','_blank', 'top=150,left=300,height=600,width=500,resizable=0,toolbar=0,menubar=0,location=0');" />
				    <input type="hidden" name="planInfos.ckProCode" id="proCode" value="${planInfos.ckProCode}" />
				</dd>
			</dl> 
		</div>
		    <div class="button">
		    <input class="inputButton" type="submit" value="查 询" id="qryBtn" />
		    <input class="inputButton" type="button" value="导 出" 
		    onclick="location.href='<%=request.getContextPath()%>/plan/planInfos_expPdf.do?planInfos.ckType='+$('#ckType').val()+'&planInfos.ckProCode='+$('#proCode').val()+''" /></div>
		<div class="clear"></div>
		</form>   

		<!-- 结果集,全部 -->
		<c:if test="${ planInfos.ckType==0}">
		<c:if test="${ not empty planInfosList}">
		<div class="list" id="byAll">
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
							课程代码
						</th>
						<th>
							课程名称
						</th>
						<th>
							是否必考
						</th>
						<th>
							课程选修<br/>说&nbsp;&nbsp;明
						</th>
						<th>
							学分
						</th>
						<th>
							是否<br/>全国<br/>统考
						</th>
						<th>
							是否<br/>全国<br/>统编<br/>教材
						</th>
						<th>
							教材名称
						</th>
						<th>
							教材主编
						</th>
						<th>
							出版社
						</th>
						<th>
							出版<br/>时间
						</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach items="${ planInfosList}" var="pl" varStatus="ctNum">
					<tr>
						<td align="center">
							${ page.pagesize * page.pagenum + ctNum.count}
						</td>
						<td align="center">
							${ pl.proCode}
						</td>
						<td align="left">
							${ pl.proName}
						</td>
						<td align="center">
							${ pl.syllabusCode}
						</td>
						<td align="left">
							${ pl.syllabusName}
						</td>
						<td align="center">
							必考课${ pl.syllabusGroupCode}
						</td>
						<td align="center">
							${ pl.syllabusGroupCode}&nbsp;
						</td>
						<td align="center">
							${ pl.syllabusCredit}
						</td>
						<td align="center">
							<c:if test="${ pl.examUnitary==1}">√</c:if>
						</td>
						<td align="center">
							<c:if test="${ pl.textbookUnitary==1}">√</c:if>
						</td>
						<td align="left">
							${ pl.textbookName}&nbsp;
						</td>						
						<td align="left">
							${ pl.textbookEditor}&nbsp;
						</td>
						<td align="left">
							${ pl.textbookPublisher}&nbsp;
						</td>
						<td align="center">
							${ pl.publishTime}&nbsp;
						</td>						
					</tr>
				</c:forEach>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="14">
							<span>${ page.pageInfo}</span>
						</td>
					</tr>
				</tfoot>
			</table>
		</div>
		</c:if></c:if>
		
		<!-- 结果集,按专业 -->
		<c:if test="${ planInfos.ckType==1}">
		<c:if test="${ not empty planInfosList}">
		<div class="list" id="byProfession">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<caption>
					课程设置与指定教材<br/>
					<span style="font-size:14px;">${basePro.proName }(${basePro.proTypeName})专业代码:${basePro.proCode}&nbsp;&nbsp;&nbsp;</span>
					<span style="font-size:14px;">&nbsp;&nbsp;&nbsp;主考院校:${basePro.academyName}</span>
				</caption>
				<thead>
					<tr>
						<th>
							序号
						</th>
						<th>
							课程代码
						</th>
						<th>
							课程名称
						</th>
						<th>
							是否必考
						</th>
						<th>
							课程选修<br/>说&nbsp;&nbsp;明
						</th>
						<th>
							学分
						</th>
						<th>
							是否<br/>全国<br/>统考
						</th>
						<th>
							是否<br/>全国<br/>统编<br/>教材
						</th>
						<th>
							教材名称
						</th>
						<th>
							教材主编
						</th>
						<th>
							出版社
						</th>
						<th>
							出版<br/>时间
						</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach items="${ planInfosList}" var="pl" varStatus="ctNum">
					<tr>
						<td align="center">
							${ page.pagesize * page.pagenum + ctNum.count}
						</td>
						<td align="center">
							${ pl.syllabusCode}
						</td>
						<td align="left">
							${ pl.syllabusName}
						</td>
						<td align="center">
							必考课
						</td>
						<td align="center">
							${ pl.syllabusGroupCode}&nbsp;
						</td>
						<td align="center">
							${ pl.syllabusCredit}
						</td>
						<td align="center">
							<c:if test="${ pl.examUnitary==1}">√</c:if>
						</td>
						<td align="center">
							<c:if test="${ pl.textbookUnitary==1}">√</c:if>
						</td>
						<td align="left">
							${ pl.textbookName}&nbsp;
						</td>						
						<td align="left">
							${ pl.textbookEditor}&nbsp;
						</td>
						<td align="left">
							${ pl.textbookPublisher}&nbsp;
						</td>
						<td align="center">
							${ pl.publishTime}&nbsp;
						</td>
					</tr>
				</c:forEach>
				</tbody>
				<tfoot>
					<tr>
						<td style="text-align: left;">
							说明：
						</td>
						<td style="text-align: left;" colspan="11">
							${ graduateCondition.graduateConditionDescribe}&nbsp;
						</td>						
					</tr>
				</tfoot>
			</table>
		</div>
		</c:if></c:if>
	</body>
</html>
