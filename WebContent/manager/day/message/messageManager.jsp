<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html" />
		<title>信息管理</title>
		<link href="<%=request.getContextPath()%>/common/style/style.css"
			rel="stylesheet" type="text/css" />
		<link href="<%=request.getContextPath()%>/common/js/ui.datepicker.css"
			rel="stylesheet" type="text/css" />
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/common/js/jquery-1.3.2.min.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/common/js/ui.datepicker-zh-CN.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/common/js/ui.datepicker.js"></script>

		<script type="text/javascript">
		$(document).ready( function() {
			$("#startDate").datepicker($.extend({ dateFormat: 'yyyy-MM-dd', showOn: 'both', buttonImage: '<%=request.getContextPath()%>/common/js/calendarNull.gif', buttonImageOnly: true},$.datepicker.regional['zh-CN']));
		});
		function changecursor(obj){
				obj.style.cursor="hand";
			}
		</script>
	</head>

	<body>
		<!--导航---start-->
		<div class="dqwz">
			<div style="margin-right: 10px;">
				<span style="cursor: pointer;" id="helpSpan"
					onclick="javascript:showHelpDiv('Z52002');">- 帮助 -</span>
				<span class="pageCode">No.Z52002</span>${currNavigation}
			</div>
		</div>
		<jsp:include page="/help/helpJs.jsp" />
		<iframe name="ifm" id="helpIfm" scrolling="no" frameborder="0"
			width="100%" style="display: none;"
			onload="this.height=this.contentWindow.document.body.scrollHeight+13"></iframe>
		<!--导航---end-->
		<!--查询条件开始-->
		<form method="post" name="form1" action="message_select.do">
			<div class="tjcx">
				<dl>
					<dt>
						标题：
					</dt>
					<dd>
						<input class="inputText inputTextM" type="text" name="title"
							value="${title }" />
					</dd>
				</dl>
				<dl>
					<dt>
						分类：
					</dt>
					<dd>
						<select class="inputText" name="classid">
							<option value="">
								---请选择---
							</option>
							<c:forEach var="class" items="${allClass}">
								<option value="${class.classId }"
									<c:if test="${classid==class.classId }">selected</c:if>>
									${class.className }
								</option>
							</c:forEach>
						</select>
					</dd>
				</dl>
				<dl>
					<dt>
						状态：
					</dt>
					<dd>
						<select class="inputText inputTextM" name="status">
							<option value="">
								全部
							</option>
							<option value="0" <c:if test="${status=='0' }">selected</c:if>>
								未审核
							</option>
							<option value="1" <c:if test="${status=='1' }">selected</c:if>>
								审核通过
							</option>
							<option value="2" <c:if test="${status=='2' }">selected</c:if>>
								审核不通过
							</option>
						</select>
					</dd>
				</dl>
				<dl>
					<dt>
						最后修改时间：
					</dt>
					<dd>
						<input class="inputText inputTextM" id="startDate" type="text"
							name="lasttime" value="${lasttime }" />
					</dd>
				</dl>
			</div>
			<div class="button">
				<input class="inputButton" type="submit" value="查 询" />
				<input class="inputButton" type="button"
					onclick="location.href='/ZK_CORE/manager/day/message/message_toadd.do;'"
					value="添 加" />
			</div>
		</form>
		<div class="clear"></div>
		<!--查询条件end-->

		<!--列表样式---表格----开始-->
		<div class="list">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				style="text-align: center">
				<caption></caption>
				<thead>
					<tr>
						<th>
							序号
						</th>
						<th>
							分类
						</th>
						<th>
							标题
						</th>
						<th>
							添加单位
						</th>
						<th>
							增加人
						</th>
						<th>
							最后修改时间
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
				   <c:forEach var="art" items="${artlist}" varStatus="h">
				     <tr>
						<td width="30">
							${page.pagesize*page.pagenum+ h.index+1}
						</td>
						<td>
							${art.className }
						</td>
						<td align="left">
							<a href="message_query.do?artid=${art.articleId }">${fn:substring(art.articleTitle,0,38) }</a>
						</td>
						<td align="left">
							${art.articleAuthor }
						</td>
						<td>
							${art.articleAddUser }
						</td>
						<td>
							${art.articleAddTime }
						</td>
						<td>
							<c:if test="${art.articleAuditStatus=='0'}">
							 未审核
							</c:if>
							<c:if test="${art.articleAuditStatus=='1'}">
							审核通过
							</c:if>
							<c:if test="${art.articleAuditStatus=='2'}">
							 审核未通过
							</c:if>
						</td>
						<td>
						    <c:if test="${art.articleAuditStatus=='1'}">
							 修改 &nbsp;&nbsp;删除
							</c:if>
							<c:if test="${art.articleAuditStatus=='0'||art.articleAuditStatus=='2'}">
							<a href='message_toedit.do?artid=${art.articleId }'>修改</a>&nbsp;&nbsp;
							<span  onclick="deleteinfo(${art.articleId})" onmouseenter="changecursor(this)" id="dspan"><a >删除</a></span>
							<script type="text/javascript">
								function deleteinfo(artid){
									if(confirm("您确定删除该信息吗？")){
									 location.href="message_delete.do?artid="+artid;
									}
								}
							</script>
							</c:if>							
						</td>
					</tr>
				   </c:forEach>					
				</tbody>
				<tfoot>
					<tr>
						<td colspan="8">
							<span> ${page.pageInfo }</span>
						</td>
					</tr>
				</tfoot>
			</table>
		</div>
		<!--列表样式---表格----end-->
	</body>
</html>
