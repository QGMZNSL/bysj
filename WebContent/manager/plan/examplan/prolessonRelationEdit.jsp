<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="v" uri="http://nbf.river.org/vxds"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>专业与课程关系设置</title>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/common/js/jquery-1.3.2.min.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/common/js/riverdefine.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/common/js/riverajax.js"></script>
		<link href="<%=request.getContextPath()%>/common/style/style.css"
			rel="stylesheet" type="text/css" />
		<script type="text/javascript">
	var goOperate = function(type, substituteCode, proCode) {
		var url;
		var gotoFlag = true;
		if (type == 'del') {
			url = '<%=request.getContextPath()%>/plan/proSyllabus_Del.do?proSyllabus.substituteCode='
					+ substituteCode + '&proSyllabus.proCode=' + proCode;
			gotoFlag = confirm("确定要删除吗？（如果已经被用，则无法删除！）");
		} else if (type == 'edit') {
			url = '<%=request.getContextPath()%>/plan/proSyllabus_EditPre.do?proSyllabus.substituteCode='
					+ substituteCode + '&proSyllabus.proCode=' + proCode;
		} else {
			return;
		}
		if (gotoFlag) {
			location.href = url;
		}
	};

	function initForm(sign) {
		if (sign == 0) {
			$('#substituteCode').val("");
			$('#syllabusCode').val("");
			$('#syllabusName').val("点击选择课程");
			$('#syllabusCredit').val("");
			$('#textbookCode').val("");
			$('#textbookName').val("点击选择教材");
			$('#textbookUnitary1').removeAttr("checked");
			$('#textbookUnitary0').attr("checked", "checked");
			$('#examUnitary1').removeAttr("checked");
			$('#examUnitary0').removeAttr("checked");
		}
	}

	function editSyllabus(subCode, proCode) {
		var ajaxUrl = "<%=request.getContextPath()%>/plan/proSyllabus_edit.do";
		var param = "proSyllabus.substituteCode=" + subCode;
		param += "&proSyllabus.proCode=" + proCode;
		$.ajax({
			type : "post", // 传值方式
			url : ajaxUrl, // 接收后台地址
			data : param + "&t=" + new Date().getTime(), // 数据
			async : false, // 是否异步	
			success : function(msg) {
				// ajax返回，更新页面显示效果，并提示
				if ("" != msg) {
					eval("msgval=" + msg);
					$('#substituteCode').val(msgval[0]);
					$('#proCode').val(msgval[1]);
					$('#syllabusCode').val(msgval[2]);
					$('#syllabusName').val(msgval[3]);
					$('#syllabusCredit').val(msgval[4]);
					if (msgval[5] != 'null') {
						$('#textbookCode').val(msgval[5]);
					} else {
						$('#textbookCode').val("");
					}
					if (msgval[6] != 'null') {
						$('#textbookName').val(msgval[6]);
					} else {
						$('#textbookName').val("点击选择教材");
					}
					if (msgval[7] == '0') {
						$('#textbookUnitary0').attr("checked", "checked");
						$('#textbookUnitary1').removeAttr("checked");
					} else {
						$('#textbookUnitary1').attr("checked", "checked");
						$('#textbookUnitary0').removeAttr("checked");
					}
					if (msgval[8] == '0') {
						$('#examUnitary0').attr("checked", "checked");
						$('#examUnitary1').removeAttr("checked");
					} else {
						$('#examUnitary1').attr("checked", "checked");
						$('#examUnitary0').removeAttr("checked");
					}
					if(msgval[9]!='null'){
						$('#syllabusRemark').val(msgval[9]);
					}else{
						$('#syllabusRemark').val("");
					}
					if (msgval[10] == '0') {
						$('#certSyllabus0').attr("checked", "checked");
						$('#certSyllabus1').removeAttr("checked");
					} else {
						$('#certSyllabus1').attr("checked", "checked");
						$('#certSyllabus0').removeAttr("checked");
					}
					if ($('#kcAdd').css("display") == "none"
							|| $('#kcAdd').css("display") == "NONE") {
						$('#kcAdd').show("slow");
						$('#saveBtn').attr("disabled", "");
					}
				} else {
					alert("非法操作！");
				}
			}
		});
	}
	
	function checkAdd(){
		if($('#syllabusName').val() == '点击选择课程'){
			alert("请选择课程！");
			return false;
		} else {
			document.addForm.action = "<%=request.getContextPath()%>/plan/proSyllabus_save.do";
			document.addForm.submit();
		}
	}

	$(document).ready(
			function() {
				$('#addSubject').click(
						function() {
							initForm(0);
							$('#kcAdd').show("slow");
							$('#saveBtn').attr("disabled", "");
						});

				$('#cancel').click(function() {
					$('#kcAdd').hide("slow");
					$('#saveBtn').attr("disabled", "disabled");
				});

				$('#orderby').click(function() {
					document.addForm.action = "<%=request.getContextPath()%>/plan/proSyllabus_getPSList.do";
					document.addForm.submit();
				});
				$('#kcAdd').attr("style", "display:none;");
				$('#saveBtn').attr("disabled", "disabled");
			});
</script>
	</head>

	<body>
		<!-- 页面导航 -->
	<div class="dqwz">
			<div style="margin-right: 10px;">
				<span style="cursor: pointer;" id="helpSpan"
					onclick="javascript:showHelpDiv('Z21008');">- 帮助 -</span>
				<span class="pageCode">No.Z21008</span>${currNavigation}
			</div>
		</div>
		<jsp:include page="/help/helpJs.jsp" />
		<iframe name="ifm" id="helpIfm" scrolling="no" frameborder="0"
			width="100%" style="display: none;"
			onload="this.height=this.contentWindow.document.body.scrollHeight+13"></iframe>

		<!-- 编辑区域 -->
		<form action="<%=request.getContextPath()%>/plan/proSyllabus_save.do"
			method="post" name="addForm">
			<v:bean clazz="com.sincinfo.zxks.core.plan.ProSyllabusAction"
				form="addForm">
				<input type="hidden" name="proSyllabus.proCode" id="proCode"
					value="${ basepro.proCode}" />
				<div class="infoedit">
					<dl>
						<dt>
							专业代码：
						</dt>
						<dd>
							${ basepro.proCode}
						</dd>
					</dl>
					<dl>
						<dt>
							专业名称：
						</dt>
						<dd>
							${ basepro.proName}
						</dd>
					</dl>
					<dl>
						<dt>
							专业分类：
						</dt>
						<dd>
							${ basepro.proPartName}
						</dd>
					</dl>
					<dl>
						<dt>
							专业类型：
						</dt>
						<dd>
							${ basepro.proTypeName}
						</dd>
					</dl>
				</div>
				<div class="clear"></div>

				<!-- 结果集 -->
				<c:if test="${ not empty proSyllabusList}">
					<div class="list">
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<caption>
							</caption>
							<thead>
								<tr>
									<th width="30">
										序号
									</th>
									<th>
										课程代码
									</th>
									<th>
										课程名称
									</th>
									<th>
										课程学分
									</th>
									<th>
										是否统考
									</th>
									<th>
										是否统编教材
									</th>
									<th>
										教材名称
									</th>
									<th width="80">
										操作
									</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${ proSyllabusList}" var="proSyllabus"
									varStatus="ctNum">
									<tr>
										<td align="center">
											${ page.pagesize * page.pagenum + ctNum.count}
										</td>
										<td align="center">
											${ proSyllabus.syllabusCode}
										</td>
										<td align="center">
											${ proSyllabus.syllabusName}
										</td>
										<td align="center">
											${ proSyllabus.syllabusCredit}&nbsp;
										</td>
										<td align="center">
											<c:choose>
												<c:when test="${ proSyllabus.examUnitary == 0}">否</c:when>
												<c:when test="${ proSyllabus.examUnitary == 1}">是</c:when>
												<c:otherwise>&nbsp;</c:otherwise>
											</c:choose>
										</td>
										<td align="center">
											<c:choose>
												<c:when test="${ proSyllabus.textbookUnitary == 0}">否</c:when>
												<c:when test="${ proSyllabus.textbookUnitary == 1}">是</c:when>
												<c:otherwise>&nbsp;</c:otherwise>
											</c:choose>
										</td>
										<td align="center">
											${ proSyllabus.textbookName}&nbsp;
										</td>
										<td align="center">
											<a style="cursor: hand;"
												onclick="editSyllabus('${ proSyllabus.substituteCode}','${ proSyllabus.proCode}');">编辑</a>
											&nbsp;
											<a href="#"
												onclick="goOperate('del','${ proSyllabus.substituteCode}','${ proSyllabus.proCode}');">删除</a>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</c:if>

				<div class="button">
					<input class="inputButton" type="button" id="addSubject"
						value="添加课程" />
					<input class="inputButton" type="button" id="orderby" value="排序" />
					<input class="inputButton" type="button" value="返 回"
						onclick="location.href='<%=request.getContextPath()%>/plan/proSyllabus_Show.do';" />
				</div>
				<div class="clear"></div>

				<div id="kcAdd">
					<div class="dqwz">
						课程设置
					</div>
					<div class="infoedit">
						<br />
						<input class="inputText" type="hidden"
							name="proSyllabus.substituteCode" id="substituteCode" value="" />
						<dl>
							<dt>
								课程名称：
							</dt>
							<dd>
								<input class="inputText" type="text"
									name="proSyllabus.syllabusName" id="syllabusName"
									readonly="readonly" value="点击选择课程" style="cursor: hand;"
									onclick="window.open('<%=request.getContextPath()%>/plan/proSyllabus_selProSyllabus.do','_blank', 'top=150,left=300,height=450,width=600,resizable=0,toolbar=0,menubar=0,location=0');" />
								<input class="inputText" type="hidden"
									name="proSyllabus.syllabusCode" id="syllabusCode"
									value="${proSyllabus.syllabusCode}" />
							</dd>
						</dl>
						<dl>
							<dt>
								课程学分：
							</dt>
							<dd>
								<input class="inputText" type="text"
									name="proSyllabus.syllabusCredit" id="syllabusCredit" />
								<v:v input="proSyllabus.syllabusCredit" exp="d(1,99)">请输入课程学分（最长输入6位）！</v:v>
							</dd>
						</dl>
						<dl>
							<dt>
								是否全国统考：
							</dt>
							<dd>
								<input type="radio"	name="proSyllabus.examUnitary"
									id="examUnitary1" value="1" />
								是
								<input type="radio" name="proSyllabus.examUnitary"
									id="examUnitary0" value="0" />
								否
							</dd>
						</dl>
						<dl>
							<dt>
								是否证书课程：
							</dt>
							<dd>
								<input type="radio"	name="proSyllabus.certSyllabus"
									id="certSyllabus1" value="1" />
								是
								<input type="radio" name="proSyllabus.certSyllabus"
									id="certSyllabus0" value="0" checked="checked" />
								否
							</dd>
						</dl>
						<dl>
							<dt>
								教材名称：
							</dt>
							<dd>
								<input class="inputText" type="text"
									name="proSyllabus.textbookName" id="textbookName"
									value="点击选择教材" value="${proSyllabus.textbookName}"
									style="cursor: hand;"
									onclick="window.open('<%=request.getContextPath()%>/plan/syllabus_getTextBooks.do','_blank','top=150,left=300,height=450,width=800,resizable=0,toolbar=0,menubar=0,location=0');" />
								<input class="inputText" type="hidden"
									name="proSyllabus.textbookCode" id="textbookCode"
									value="${proSyllabus.textbookCode}" />
							</dd>
						</dl>
						<dl>
							<dt>
								是否统编教材：
							</dt>
							<dd>
								<input type="radio"	name="proSyllabus.textbookUnitary"
								    id="textbookUnitary1"
									value="1" />
								是
								<input type="radio" name="proSyllabus.textbookUnitary"
									id="textbookUnitary0" value="0" />
								否
							</dd>
						</dl>
						<dl>
							<dt>
								备注：
							</dt>
							<dd>
								<input class="inputText" type="text"
									name="proSyllabus.syllabusRemark"
								    id="syllabusRemark" value="" />
							</dd>
						</dl>
					</div>
					<div class="button">
						<input class="inputButton" type="button" id="saveBtn" value="保 存" onclick="checkAdd();" />
						<input class="inputButton" type="button" id="cancel" value="取 消" />
					</div>
				</div>
			</v:bean>
		</form>
	</body>
</html>
