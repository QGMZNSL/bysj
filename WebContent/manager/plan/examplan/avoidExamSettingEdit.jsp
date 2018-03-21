<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>免考课程设置</title>
		<link href="<%=request.getContextPath()%>/common/style/style.css"
			rel="stylesheet" type="text/css" />
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/common/js/jquery-1.3.2.min.js"></script>	
		<script type="text/javascript">
		var init = function() {
			
		};	
		var goOperate = function( type, avoidCode,syllabusCode) {
			var url;
			var gotoFlag = true;
			if ( type == 'del') {
				url = '<%=request.getContextPath()%>/plan/avoidExamSet_Del.do?syllabus.syllabusCode='+syllabusCode+'&avoidExamSet.avoidCode=' + avoidCode;
				gotoFlag = confirm("确定要删除该免考信息吗？（如果免考信息已经被用，则无法删除！）");
			}  else {
				return;
			}
			
			if ( gotoFlag) {
				location.href = url;
			}
		};
		$(function() {
			init();			
		});
		</script>			
	</head>

	<body>
		<!-- 页面导航 -->
		<div class="dqwz">
			<div style="margin-right: 10px;">
				<span style="cursor: pointer;" id="helpSpan"
					onclick="javascript:showHelpDiv('Z21011');">- 帮助 -</span>
				<span class="pageCode">No.Z21011</span>${currNavigation}
			</div>
		</div>
		<jsp:include page="/help/helpJs.jsp" />
		<iframe name="ifm" id="helpIfm" scrolling="no" frameborder="0"
			width="100%" style="display: none;"
			onload="this.height=this.contentWindow.document.body.scrollHeight+13"></iframe>

		<!-- 结果集 -->
		<c:if test="${ not empty avoidExamSetList}">
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
							课程代码
						</th>
						<th>
							课程名称
						</th>
						<th>
							免考说明
						</th>
						<th>
							备注
						</th>
						<th width="80">
							操作
						</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach items="${ avoidExamSetList}" var="avoidExamSet" varStatus="ctNum">
					<tr>
						<td align="center">
							${ page.pagesize * page.pagenum + ctNum.count}
						</td>
						<td align="center">
							${avoidExamSet.syllabusCode}
						</td>
						<td align="center">
							${avoidExamSet.syllabusName}
						</td>
						<td align="center">
							${avoidExamSet.avoidState}
						</td>
						<td align="center">
							${avoidExamSet.remarks}&nbsp;
						</td>
						<td align="center">
							<a href="#" onclick="goOperate('del','${ avoidExamSet.avoidCode}','${ avoidExamSet.syllabusCode}');">删除</a>
						</td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		</c:if>

		<!-- 添加免考事实 -->
		<div>
			<div class="dqwz">
				添加免考关系：（课程名称：${syllabus.syllabusName}）
			</div>
			<form action="<%=request.getContextPath()%>/plan/avoidExamSet_Add.do" method="post" name="addForm">
			<v:bean clazz="com.sincinfo.zxks.core.plan.SyllabusAction"	form="addForm">
			<input type="hidden" name="avoidExamSet.syllabusCode" id="syllabusCode" value="${syllabus.syllabusCode}"/>
				<div class="infoedit">
					<h1>
						添加免考关系：（课程名称：${syllabus.syllabusName}）
					</h1>
					<dl>
						<dt>
							免考说明：
						</dt>
						<dd>
							<input class="inputText" type="text" name="avoidExamSet.avoidState" id="avoidState" />
						</dd>
					</dl>
					<dl>
						<dt>
							备注：
						</dt>
						<dd>
							<input class="inputText" type="text" name="avoidExamSet.remarks" id="remarks" />
						</dd>
					</dl>
					<div class="clear"></div>
				</div>
				<div class="button">
					<input class="inputButton" type="submit" value="确 定" />
					<input class="inputButton" type="button" value="返 回"
						onclick="location.href='<%=request.getContextPath()%>/plan/avoidExamSet_Show.do';" />
				</div>
			</v:bean>
			</form>
		</div>
		
	</body>
</html>
