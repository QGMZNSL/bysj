<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>开考专业课程校对</title>
		<link href="<%=request.getContextPath()%>/common/style/style.css"
			rel="stylesheet" type="text/css" />
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/common/js/jquery-1.3.2.min.js">
		</script>
		<script type="text/javascript">
		function change(){
			var cckey = document.getElementById("ccKey");
             cckey.value = 1;
		}
		</script>
		
			
	</head>

	<body>
		<!-- 页面导航 -->
		<div class="dqwz">
			<div style="margin-right: 10px;">
				<span style="cursor: pointer;" id="helpSpan"
					onclick="javascript:showHelpDiv('Z22005');">- 帮助 -</span>
				<span class="pageCode">No.Z22005</span>${currNavigation}
			</div>
		</div>
		<jsp:include page="/help/helpJs.jsp" />
		<iframe name="ifm" id="helpIfm" scrolling="no" frameborder="0"
			width="100%" style="display: none;"
			onload="this.height=this.contentWindow.document.body.scrollHeight+13"></iframe>


		<!-- 查询条件 -->
		<form method="post" name="form1"
			action="<%=request.getContextPath()%>/manager/plan/examarrange/collate_getSyllabus.do">
			<div class="tjcx">
				<dl>
					<dt>
						考试年月：
					</dt>
					<dd>
						<select class="inputText inputTextM" name="examinationCode"
							id="examinationCode">
							<option value="">
								--- 请选择 ---
							</option>
							<c:forEach items="${ ymList}" var="ym">
								<option value="${ ym.entityCode}"
									<c:if test="${ ym.entityCode eq examinationCode}" >selected="selected"</c:if>>
									${ ym.entityName}
								</option>
							</c:forEach>
						</select>
					</dd>
				</dl>
				<dl>
					<dt>
						课程代码：
					</dt>
					<dd>
						<input class="inputText inputTextM" type="text"
							name="syllabusCode" value="${syllabusCode}" />
					</dd>
				</dl>
				<dl>
					<dt>
						课程名称：
					</dt>
					<dd>
						<input class="inputText inputTextM" type="text"
							name="syllabusName" value="${syllabusName}" />
					</dd>
				</dl>
			</div>
			<div class="button">
				<input type="hidden" id="ccKey" name="ccKey" value="0"/>
				<input class="inputButton" type="submit" value="查询重复课程" />
				<input class="inputButton" type="submit" value="查询重复考次" onclick="change()"/>
			</div>
			<div class="clear"></div>
		</form>

		<!-- 结果集 -->
		<c:if test="${dataList != null}">
			<c:if test="${not empty dataList}">
				<div class="list">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<caption>
						</caption>
						<thead>
							<tr>
								<th width="10%">
									序号
								</th>
								<th width="15%">
									考试日期
								</th>
								<th width="10%">
									考试时间
								</th>
								<th width="20%">
									课程代码
								</th>
								<th width="45%">
									课程名称
								</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${ dataList}" var="data" varStatus="ls">
								<tr>
									<td align="center">
										${ ls.index+1}
									</td>
									<td align="center">
										${ data[0]}
									</td>
									<td align="center">
										<c:if test="${ data[1] eq '1'}">上午</c:if>
										<c:if test="${ data[1] eq '2'}">下午</c:if>
									</td>
									<td align="center">
										${ data[2]}
									</td>
									<td align="left">
										${ data[3]}
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</c:if>
			<c:if test="${empty dataList}">
				<c:if test="${ not empty syllabusCode ||  not empty syllabusName}">
					<div align="center">
						您所查课程非本次开考课程
					</div>
				</c:if>
				<c:if test="${ empty syllabusCode && empty syllabusName}">
					<div align="center">
						没有重复课程
					</div>
				</c:if>
			</c:if>
		</c:if>
	</body>