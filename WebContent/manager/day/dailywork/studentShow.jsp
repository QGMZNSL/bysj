<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>考生信息管理</title>
		<link href="<%=request.getContextPath()%>/common/style/style.css"
			rel="stylesheet" type="text/css" />
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/common/js/jquery-1.3.2.min.js"></script>
		<script type="text/javascript">
		$(function() {
			/* 初始化页面 */
			function init() {
				$('#cityCode').val("${ cityCode}");
				$('#studExamCode').val("${ studExamCode}");
				$('#studIdnum').val("${ studIdnum}");
				$('#studName').val("${ studName}");
			}init();
		});
		
		function expDBF(){
			var ajaxUrl = "<%=request.getContextPath()%>/day/dailywork/studQry_expDBF.do";
			var param = "cityCode=" + $('#cityCode').val();
				param += "&studExamCode=" + $('#studExamCode').val();
				param += "&studIdnum=" + $('#studIdnum').val();
				param += "&studName=" + $('#studName').val();
			$.ajax({
			     type: "post", // 传值方式
			     url: ajaxUrl,    // 接收后台地址
			     data: param + "&t=" + new Date().getTime(),   // 数据
			     async: false,  // 是否异步	
			     success: function( msg) { 
					// ajax返回，更新页面显示效果，并提示
					if ( "error" == msg) {
						alert("导出失败！");
					} else if ( "errorno" == msg) {
						alert("没有符合当前查询条件的数据！");
					} else if ( "no" == msg) {
						alert("非法操作！");
					}else {
						alert("导出成功！请点击下载链接进行下载！");
						$('#expDown').html(msg);
					}
				}
			});
		}
		</script>
	</head>
	<body>
		<!--导航---start-->
		<div class="dqwz">
			<div style="margin-right: 10px;">
				<span style="cursor: pointer;" id="helpSpan"
					onclick="javascript:showHelpDiv('Z51001');">- 帮助 -</span>
				<span class="pageCode">No.Z51001</span>${currNavigation}
			</div>
		</div>
		<jsp:include page="/help/helpJs.jsp" />
		<iframe name="ifm" id="helpIfm" scrolling="no" frameborder="0"
			width="100%" style="display: none;"
			onload="this.height=this.contentWindow.document.body.scrollHeight+13"></iframe>
		<!--导航---end-->

		<form method="post" name="setForm"
			action="<%=request.getContextPath()%>/day/dailywork/studQry_doStudentQry.do?fillinBy=${ fillinBy}">
			<div class="tjcx">
				<dl>
					<dt>
						市区：
					</dt>
					<dd>
						<select class="inputText inputTextM" name="cityCode" id="cityCode">
							<c:if test="${ optUser.userRole == '1'}">
								<option value="610000">
									陕西省
								</option>
							</c:if>
							<c:forEach items="${ cityList}" var="city">
								<option value="${ city.cityCode}">
									${ city.cityName}
								</option>
							</c:forEach>
						</select>
					</dd>
				</dl>
				<dl>
					<dt>
						准考证号：
					</dt>
					<dd>
						<input class="inputText inputTextM" type="text" id="studExamCode"
							name="studExamCode" />
					</dd>
				</dl>
				<dl>
					<dt>
						姓名：
					</dt>
					<dd>
						<input class="inputText inputTextM" type="text" id="studName"
							name="studName" />
					</dd>
				</dl>
				<dl>
					<dt>
						证件号码：
					</dt>
					<dd>
						<input class="inputText inputTextM" type="text" id="studIdnum"
							name="studIdnum" />
					</dd>
				</dl>
			</div>
			<div class="button">
				<input class="inputButton" type="submit" value="查 询" />
				<input class="inputButton" type="button" value="导出DBF" onclick="expDBF();" />
				&nbsp;&nbsp;
				<span id="expDown"></span>
			</div>
		</form>
		<div class="clear"></div>

		<!-- 内容 列表  -->
		<c:if test="${ not empty studList}">
			<div class="list">
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
					<caption>
					</caption>
					<thead>
						<tr>
							<th width="30" align="center">
								序号
							</th>
							<th>
								准考证
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
								报考市区
							</th>
							<th>
								专业
							</th>
							<c:if test="${ fillinBy == 1}">
								<th>
									操作
								</th>
							</c:if>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${ studList}" var="stud" varStatus="ctNum">
							<tr>
								<td align="center">
									${ page.pagenum * page.pagesize + ctNum.count}
								</td>
								<td align="center">

									<a target="_blank"
										href="<%=request.getContextPath()%>/studinfo_show.do?studExamCode=${stud.studExamCode}">${
										stud.studExamCode} </a>
								</td>
								<td align="center">
									${ stud.studName}
								</td>
								<td align="center">
									${ stud.studIdnum}
								</td>
								<td align="center">
									${ stud.genderName}
								</td>
								<td align="center">
									${ stud.cityName}
								</td>
								<td align="center">
									${ stud.proName}
								</td>
								<c:if test="${ fillinBy == 1}">
									<td align="center">
										<a target="_blank"
											href="<%=request.getContextPath()%>/day/dailywork/studQry_studExamHistory.do?studExamCode=${
										stud.studExamCode}">历史成绩</a>
									</td>
								</c:if>
							</tr>
						</c:forEach>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="8">
								<span>${ page.pageInfo}</span>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
		</c:if>
	</body>
</html>
