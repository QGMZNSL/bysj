<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="v" uri="http://nbf.river.org/vxds"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>考生信息确认</title>
		<link href="<%=request.getContextPath()%>/common/style/style.css"
			rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="<%=request.getContextPath()%>/common/js/clientfuns.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/common/js/calendar.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/common/js/riverdefine.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/common/js/riverajax.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/common/js/common.js"></script>
	</head>

	<body>
		<!--当前位置---开始-->
		<div class="dqwz">
			<span>帮助</span>摄像点管理>>摄像点管理>>考生信息确认
		</div>
		<!--当前位置---结束-->
		<!--查询条件开始-->
		<div class="tjcx">
<form action="infoconfirm_list.do" method="post" name="form1">
				<dl>
					<dt>
						新生报名号：
					</dt>
					<dd>
						<input name="preapplyCode" class="inputText inputTextM" type="text"  value="${preapplyCode }"/>
					</dd>
				</dl>
				<dl>
					<dt>
						证件号码：
					</dt>
					<dd>
						<input name="idno" class="inputText inputTextM" type="text" value="${idno }" />
					</dd>
				</dl>
			</form>
		</div>
		<div class="button">
			<input class="inputButton" type="button" value="查 询" onclick="form1.submit();"/>
		</div>
		<div class="clear"></div>
		<!--查询条件end-->
		<!--列表样式---表格----开始-->
		<div class="list">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<caption></caption>
				<thead>
					<tr>
						<th width="30">
							序号
						</th>
						<th>
							新生报名号
						</th>
						<th>
							姓名
						</th>
						<th>
							证件号码
						</th>
						<th>
							性别
						</th>
						<th>
							状态
						</th>
						<th>
							操作
						</th>
					</tr>
				</thead>
				<tbody>
<c:forEach var="it" items="${students }" varStatus="vs">
					<tr>
						<td align="center">
							${vs.index+1+page.pagenum*page.pagesize }
						</td>
						<td align="center">
							${it.preapplyCode }
						</td>
						<td align="center">
							${it.studName }
						</td>
						<td align="center">
							${it.studIdnum }
						</td>
						<td align="center">
							${it.genderName }
						</td>
						<td align="center">
							<c:choose>
							<c:when test="${it.studInformationConfirmSign==1 }">
							已确认
							</c:when>
							<c:when test="${it.studInformationConfirmSign==0 }">
							未确认
							</c:when>
							</c:choose>
						</td>
						<td align="center">
							<c:choose>
							<c:when test="${it.studInformationConfirmSign==1 }">
							<c:if test="${canConfirm==1 }">
							<a href='javascript:if(confirm("是否确实撤销确认?")){location.href="infoconfirm_deconfirm.do?preapplyCode=${it.preapplyCode }";}'>撤销确认</a>&nbsp;&nbsp;&nbsp;
							</c:if>
							<c:if test="${canConfirm!=1 }">
							<font color="gray">撤销确认</font>&nbsp;&nbsp;&nbsp;
							</c:if>
							</c:when>
							<c:when test="${it.studInformationConfirmSign==0 }">
							<c:if test="${canConfirm==1}">
							<a href='javascript:if(confirm("是否确认该考生个人信息吗?")){location.href="infoconfirm_confirm.do?preapplyCode=${it.preapplyCode }";}'>确认</a>&nbsp;&nbsp;&nbsp;
							</c:if>
							<c:if test="${canConfirm!=1 }">
							<font color="gray">确认</font>&nbsp;&nbsp;&nbsp;
							</c:if>
							</c:when>
							</c:choose>
							<c:choose>
							<c:when test="${it.studInformationConfirmSign==1 }">
							<a target="_blank" href="infoconfirm_print.do?printtype=1&preapplyCode=${it.preapplyCode }">打印回执单</a>
							</c:when>
							<c:when test="${it.studInformationConfirmSign==0 }">
							<a href="javascript:alert('请先确认，再打印！');">打印回执单</a>
							</c:when>
							</c:choose>
							<c:choose>
							<c:when test="${empty it.studPhotoFile1 or it.studPhotoFile1==''}">
								<a href="javascript:alert('请先采集照片，再打印！');">打印确认单</a>
							</c:when>
							<c:otherwise>
								<a target="_blank" href="infoconfirm_print.do?preapplyCode=${it.preapplyCode}">打印确认单</a>
							</c:otherwise>
							</c:choose>
							
						</td>
					</tr>
</c:forEach>

				</tbody>
				<tfoot>
					<tr>
						<td colspan="7">
							<span> ${page.pageInfo } </span>
						</td>
					</tr>
				</tfoot>
			</table>
		</div>
		<!--列表样式---表格----end-->

	</body>
</html>
