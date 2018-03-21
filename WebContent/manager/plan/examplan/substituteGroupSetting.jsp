<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>课程顶替组设置</title>
		<link href="<%=request.getContextPath()%>/common/style/style.css"
			rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="<%=request.getContextPath() %>/common/js/jquery-1.3.2.min.js" ></script>
		<script type="text/javascript">
		$(document).ready( function() {
			$('#queryBtn').click( function() {
				location.href = "#" + $('#subjectCode').val();
			});
		});
		</script>
		<script type="text/javascript">
		function getDataChecked(){
		    var dataChecked;
			var cboxes = $('.' + cboxClassName);
			for ( var i = 0; i < cboxes.length; i++) {
				if ( $(cboxes[i]).attr("checked") == true ||  $(cboxes[i]).attr("checked") == "checked") {
                   dataChecked=dataChecked+","+$(cboxes[i]).val;
				}
			}
			dataChecked=dataChecked.substr(1);
			$('dataChecked').val=dataChecked;
			alert($('dataChecked').val());
		}
		</script>		
	</head>

	<body>
		<!-- 页面导航 -->
		<div class="dqwz">
			<span>帮助</span><span class="pageCode">功能编号:${ currFunctionId}</span>${currNavigation}
		</div>
		 
		<!-- 查询条件 -->
		<form method="post" name="form1" action="<%=request.getContextPath()%>/plan/substituteGroup_SubStitutePre.do" >
		<div class="tjcx">
			<dl>
				<dt>课程代码：</dt>
				<dd><input class="inputText inputTextM" type="text" id="subjectCode" /></dd>
			</dl>
		</div>
	    <div class="button"><input class="inputButton" id="queryBtn" type="submit" value="查 询" /></div>
		<div class="clear"></div>
		</form>  

		<!-- 结果集 -->
		<c:if test="${ not empty syllabusList}">
		<form method="post" name="form1" action="<%=request.getContextPath()%>/plan/substituteGroup_SubStituteSet.do" onsubmit="getDataChecked();">		
		<div class="list">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<caption>
					替代课程组名称：${substituteGroup.substituteGroupName}<input type="hidden" name="substituteGroup.substituteGroupId" id="substituteGroupId" value="${substituteGroup.substituteGroupId}" />
				</caption>
				<thead>
					<tr>
						<th>
							选择
						</th>
						<th>
							课程代码
						</th>
						<th>
							课程名称
						</th>
						<th>
							选择
						</th>
						<th>
							课程代码
						</th>
						<th>
							课程名称
						</th>
						<th>
							选择
						</th>
						<th>
							课程代码
						</th>
						<th>
							课程名称
						</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${syllabusList }" var="sg" varStatus="vs">
						<c:if test="${vs.index % 3 == 0 }">
							<c:out value="<tr>" escapeXml="false" />
						</c:if>
						<td align="center">
							<input type="checkbox" name="substituteGroup.syllabusCode" id="syllabusCode" value="${sg.syllabusCode}" />
                            
						</td>
						<td align="center">
							${sg.syllabusCode }
						</td>
						<td>
							${sg.syllabusName}
						</td>
						<c:if test="${vs.index % 3 == 2 }">
							<c:out value="</tr>" escapeXml="false" />
						</c:if>
					</c:forEach>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="9">
							<input type="submit" class="inputButton" value="保 存" />
							<input type="button" class="inputButton" value="返 回" onclick="history.back();" />
						</td>
					</tr>
				</tfoot>
			</table>
		</div>
		</form>
		</c:if>
	</body>
</html>
