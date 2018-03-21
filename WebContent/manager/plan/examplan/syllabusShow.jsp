<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>开考课程设置</title>
		<link href="<%=request.getContextPath()%>/common/style/style.css"
			rel="stylesheet" type="text/css" />
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/common/js/jquery-1.3.2.min.js"></script>
		<script type="text/javascript">
	var init = function() {

	};
	var goOperate = function(type, syllabusCode) {
		var url;
		var gotoFlag = true;
		if (type == 'del') {
			url = '<%=request.getContextPath()%>/plan/syllabus_Del.do?syllabus.syllabusCode=' + syllabusCode;
			gotoFlag = confirm("确定要删除该层次吗？（如果层次已经被用，则无法删除！）");
		} else if (type == 'isUseDel') {
			url = '<%=request.getContextPath()%>/plan/syllabus_isUseDel.do?syllabus.syllabusCode='
					+ syllabusCode;
			gotoFlag = confirm("确定要删除该信息吗？");
		} else if (type == 'edit') {
			url = '<%=request.getContextPath()%>/plan/syllabus_EditPre.do?syllabus.syllabusCode='
					+ syllabusCode;
		} else {
			return;
		}

		if (gotoFlag) {
			location.href = url;
		}
	};

	$(function() {
		init();
	});

	// 导出Excel
	function doExcel() {
		var syllabusType = $("#syllabusType").val();
		var syllabusCode = $("#syllabusCode").val();
		var syllabusName = $("#syllabusName").val();
		var isgb = $("#isgb").val();

		var str = "syllabus.syllabusType=" + syllabusType
				+ "&syllabus.syllabusCode=" + syllabusCode
				+ "&syllabus.syllabusName=" + syllabusName + "&syllabus.isgb="
				+ isgb;

		var ajaxurl = "<%=request.getContextPath()%>/plan/syllabus_doExcel.do?t="
				+ new Date().getTime();
		$.ajax({
			type : "post",
			url : ajaxurl,
			data : str,
			async : false,
			success : function(msg) {
				if ("error" == msg) {
					$('#expdown').html("");
					alert("导出失败！");
					$('#expdown').html("");
				} else if ("noData" == msg) {
					alert("没有记录！");
					$('#expdown').html("");
				} else {
					alert("导出成功！请点击下载链接进行下载！");
					$('#expdown').html(msg);
				}
			}
		});
	}
</script>
	</head>

	<body>
		<!-- 页面导航 -->
		<div class="dqwz">
			<div style="margin-right: 10px;">
				<span style="cursor: pointer;" id="helpSpan"
					onclick="javascript:showHelpDiv('Z21007');">- 帮助 -</span>
				<span class="pageCode">No.Z21007</span>${currNavigation}
			</div>
		</div>
		<jsp:include page="/help/helpJs.jsp" />
		<iframe name="ifm" id="helpIfm" scrolling="no" frameborder="0"
			width="100%" style="display: none;"
			onload="this.height=this.contentWindow.document.body.scrollHeight+13"></iframe>

		<!-- 查询条件 -->
		<form method="post" name="form1"
			action="<%=request.getContextPath()%>/plan/syllabus_qry.do">
			<div class="tjcx">
				<dl>
					<dt>
						课程类型：
					</dt>
					<dd>
						<select class="inputText" name="syllabus.syllabusType"
							id="syllabusType">
							<option value="">
								--- 请选择 ---
							</option>
							<option value="0"
								<c:if test="${syllabus.syllabusType==0}">selected</c:if>>
								笔试课程
							</option>
							<option value="1"
								<c:if test="${syllabus.syllabusType==1}">selected</c:if>>
								实践课程
							</option>
							<option value="2"
								<c:if test="${syllabus.syllabusType==2}">selected</c:if>>
								论文答辩
							</option>

						</select>
					</dd>
				</dl>
				<dl>
					<dt>
						课程代码：
					</dt>
					<dd>
						<input class="inputText inputTextM" type="text"
							name="syllabus.syllabusCode" id="syllabusCode"
							value="${syllabus.syllabusCode}" />
					</dd>
				</dl>
				<dl>
					<dt>
						课程名称：
					</dt>
					<dd>
						<input class="inputText inputTextM" type="text"
							name="syllabus.syllabusName" id="syllabusName"
							value="${syllabus.syllabusName}" />
					</dd>
				</dl>
				<dl>
					<dt>
						是否全国统考：
					</dt>
					<dd>
						<select class="inputText" name="syllabus.isgb" id="isgb">
							<option value="">
								--- 请选择 ---
							</option>
							<option value="1"
								<c:if test="${syllabus.isgb==1}">selected</c:if>>
								是
							</option>
							<option value="0"
								<c:if test="${syllabus.isgb==0}">selected</c:if>>
								否
							</option>
						</select>
					</dd>
				</dl>
			</div>
			<div class="button">
				<input class="inputButton" type="submit" value="查 询" />
				<input class="inputButton" type="button" value="添 加"
					onclick="location.href='<%=request.getContextPath()%>/plan/syllabus_AddPre.do'" />
				<input class="inputButton" type="button" value="导出EXCEL"
					onclick="doExcel()" />
				<span id="expdown"></span>
			</div>
			<div class="clear"></div>
		</form>

		<!-- 结果集 -->
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
						<!-- <th>
							课程英文名
						</th> -->
						<th>
							课程分类
						</th>
						<th>
							学分
						</th>
						<th>
							指定教材
						</th>
						<th>
							是否全国统考
						</th>
						<th width="80">
							操作
						</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${ not empty syllabusList}">
						<c:forEach items="${ syllabusList}" var="syllabus"
							varStatus="ctNum">
							<tr>
								<td align="center">
									${ page.pagesize * page.pagenum + ctNum.count}
								</td>
								<td align="center">
									${ syllabus.syllabusCode}
								</td>
								<td align="left">
									${ syllabus.syllabusName}
								</td>
								<!-- <td align="left">
									${ syllabus.syllabusenglishname}&nbsp;
								</td> -->
								<td align="center">
									<c:choose>
										<c:when test="${ syllabus.syllabusType == 0}">笔试课程</c:when>
										<c:when test="${ syllabus.syllabusType == 1}">实践课程</c:when>
										<c:when test="${ syllabus.syllabusType == 2}">论文答辩</c:when>
										<c:otherwise>&nbsp;</c:otherwise>
									</c:choose>
								</td>
								<td align="center">
									${ syllabus.syllabuscredit}&nbsp;
								</td>
								<td align="center">
									${ syllabus.textbookName}&nbsp;
								</td>
								<td align="center">
									<c:choose>
										<c:when test="${ syllabus.isgb == 0}">否</c:when>
										<c:when test="${ syllabus.isgb == 1}">是</c:when>
										<c:otherwise>&nbsp;</c:otherwise>
									</c:choose>
								</td>
								<td align="center">
									<a href="#"
										onclick="goOperate( 'edit', '${ syllabus.syllabusCode}');">编辑</a>
									&nbsp;
									<a href="#"
										onclick="goOperate( 'isUseDel', '${ syllabus.syllabusCode}');">删除</a>

								</td>
							</tr>
						</c:forEach>
					</c:if>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="10">
							<span>${ page.pageInfo}</span>
						</td>
					</tr>
				</tfoot>
			</table>
		</div>
	</body>
</html>
