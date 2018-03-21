<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>替代课程设置</title>
		<link href="<%=request.getContextPath()%>/common/style/style.css"
			rel="stylesheet" type="text/css" />
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/common/js/jquery-1.3.2.min.js"></script>
		<script type="text/javascript">
			var goOperate = function( type,substitutecode,syllabusCode){
				var url;
				var gotoFlag = true;
				if ( type == 'del') {
					url = '<%=request.getContextPath()%>/plan/subStitute_Del.do?syllabus.syllabusCode='+syllabusCode+'&substitute.substitutecode=' + substitutecode;
					gotoFlag = confirm("确定要删除该顶替吗？（如果顶替已经被用，则无法删除！）");
				}else{
					return;
				}
				if ( gotoFlag){
					location.href = url;
				}
			};	
			$(document).ready( function() {
			$('#substitutetype').change( function() {
				if ($('#substitutetype').val() == "1") {
					$('#subSubject').css("display", "inline");
					$('#subDescribe').css("display", "none");
				}else{
					$('#subDescribe').css("display", "inline");
					$('#subSubject').css("display", "none");
				}
			});	
			$('#addForm').submit(function() {
				if ($('#substitutetype').val() == "1") {
					$('#avoidstate').val("-");
				}else{
				   alert($('#avoidstate').val());
                   if( isNull($('#avoidstate').val()) || $('#avoidstate').val()==""){
                     alert("证书替换必须填写替代说明");
                     return false;
                   }
				}
			});
			$('#subSubject').css("display", "inline");
			$('#subDescribe').css("display", "none");
		});
		</script>
	</head>

	<body>
		<!-- 页面导航 -->
		<div class="dqwz">
			<div style="margin-right: 10px;">
				<span style="cursor: pointer;" id="helpSpan"
					onclick="javascript:showHelpDiv('Z21010');">- 帮助 -</span>
				<span class="pageCode">No.Z21010</span>${currNavigation}
			</div>
		</div>
		<jsp:include page="/help/helpJs.jsp" />
		<iframe name="ifm" id="helpIfm" scrolling="no" frameborder="0"
			width="100%" style="display: none;"
			onload="this.height=this.contentWindow.document.body.scrollHeight+13"></iframe>

		<!-- 结果集 -->
		<c:if test="${ not empty substituteList}">
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
							替考类型
						</th>
						<th>
							替考说明
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
				<c:forEach items="${ substituteList}" var="SubStituteSet" varStatus="ctNum">
					<tr>
						<td align="center">
							${ctNum.count}
						</td>
						<td align="center">
							${SubStituteSet.substitutesyllabus}
						</td>
						<td align="center">
						    ${SubStituteSet.syllabusName}
						</td>
						<td align="center">
							<c:choose>
								<c:when test="${ SubStituteSet.substitutetype == 1}">课程顶替</c:when>							
								<c:when test="${ SubStituteSet.substitutetype == 2}">证书顶替</c:when>
								<c:otherwise>&nbsp;</c:otherwise>
							</c:choose>							
						</td>
						<td align="center">
							${SubStituteSet.avoidstate}
						</td>
						<td align="center">
							${SubStituteSet.remarks}&nbsp;
						</td>
						<td align="center">
							<a href="#" onclick="goOperate('del','${ SubStituteSet.substitutecode}','${ SubStituteSet.syllabuscode}');">删除</a>
						</td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		</c:if>
		<div class="clear"></div>

		<br/>
		
		<!-- 添加免考事实 -->
		<div>
			<div class="dqwz">
				添加替代关系：（课程名称：${syllabus.syllabusName}）
			</div>
			<form action="<%=request.getContextPath()%>/plan/subStitute_Add.do" method="post" name="addForm" id="addForm" >
			    <input type="hidden" name="substitute.syllabuscode" value="${syllabus.syllabusCode}" />
				<div class="infoedit">
					<h1>
						添加替代关系：（课程名称：${syllabus.syllabusName}）
					</h1>
					<dl>
						<dt>
							替代类型：
						</dt>
						<dd>
							<select class="inputText" name="substitute.substitutetype" id="substitutetype">
								<option value="1"<c:if test="${substitute.substitutetype==1}">selected</c:if>>课程替代</option>
								<option value="2"<c:if test="${substitute.substitutetype==2}">selected</c:if>>证书替代</option>
							</select>
						</dd>
					</dl>
					<dl id="subSubject">
						<dt>
							替代课程：
						</dt>
						<dd>
							<input class="inputText" type="text" name="subjectName" id="syllabusName" readonly="readonly" value="点击选择课程" style="cursor: hand;"
							onclick="window.open('<%=request.getContextPath()%>/plan/proSyllabus_qrySyllabus.do','_blank', 'top=150,left=300,height=500,width=500,resizable=0,toolbar=0,menubar=0,location=0');" />
							<input class="inputText" type="hidden" name="substitute.substitutesyllabus" id="syllabusCode"  />
						</dd>
					</dl>
					<dl id="subDescribe">
						<dt>
							替代说明：
						</dt>
						<dd>
							<input class="inputText" type="text" name="substitute.avoidstate" id="avoidstate" value="${substitute.avoidstate}" />
						</dd>
					</dl>
					<div class="clear"></div>
					<dl>
						<dt>
							备注：
						</dt>
						<dd>
							<input class="inputTextarea" type="text" name="substitute.remarks" value="${substitute.remarks}" />
						</dd>
					</dl>
					<div class="clear"></div>
					<div class="button">
						<input class="inputButton" type="submit" value="确 定" />
						<input class="inputButton" type="button" value="返 回"
						onclick="location.href='<%=request.getContextPath()%>/plan/subStitute_Show.do';" />
					</div>
				</div>
			</form>
		</div>
	</body>
</html>
