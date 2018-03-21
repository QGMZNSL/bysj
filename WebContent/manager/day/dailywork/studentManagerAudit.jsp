<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>基本信息变更审核</title>
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
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/common/js/clientfuns.js"></script>

		<script type="text/javascript">
	function showHelpDiv(pageNo) {
		var v = document.getElementById("helpIfm").contentWindow.document.getElementById("ew1");
		if(v != null){
			if(confirm("编辑的数据会丢失，是否继续？")){
				doToggle(pageNo);
			}
		}else{
			doToggle(pageNo);
		}
	}
	function doToggle(val){
		$("#helpIfm").attr("src","<%=request.getContextPath()%>/zk/help/help_getHelp.do?dt="+ new Date().getTime()+"&help_id="+val);
		$("#helpIfm").slideToggle(1000);
		var str = $("#helpSpan").html();
		if(str == "点击收起"){
			$("#helpSpan").html("- 帮助 -");
		}else{
		$("#helpSpan").html("点击收起");
		}
	}
</script>
		<script type="text/javascript">
		function isChked() {
			var acIds = $('.auditChangeIds');
			for ( var i = 0; i < acIds.length; i++) {
				if ( $(acIds[i]).attr("checked") == true || $(acIds[i]).attr("checked") == "checked") {
					return true;
				}
			}
			return false;
		}
		
		$(function() {
			$('#oppositeChk').click( function() {
				cboxCheckOppositeByClassName("auditChangeIds");
			});
			
			$('#auditBtn').click( function() {
				if ( !isChked()) {
					alert("请选择要审核的记录！");
					return;
				}
				$('#unAuditReason').val("");
				$('#auditStatus').val("9");
				$('#doAuditForm').submit();
			});
			
			$('#unAuditBtn').click( function() {
				if ( !isChked()) {
					alert("请选择要审核的记录！");
					return;
				}
				
				if ( $('#unAuditReason').val() == '') {
					alert("请填写审核不通过原因！");
					return;
				}
				
				$('#auditStatus').val("1");
				$('#doAuditForm').submit();
			});
		
			/* 初始化页面 */
			function init() {
				$('#cityCode').val("${ cityCode}");
				$('#startDate').val("${ startDate}");
				$('#endDate').val("${ endDate}");
				$('#studExamCode').val("${ studExamCode}");
				$('#idnum').val("${ idnum}");
				$('#status').val("${ status}");
			}init();
			
			$("#startDate").datepicker($.extend({ dateFormat: 'yyyy-MM-dd', showOn: 'both', buttonImage: '<%=request.getContextPath()%>/common/js/calendarNull.gif', buttonImageOnly: true},$.datepicker.regional['zh-CN']));
			$("#endDate").datepicker($.extend({ dateFormat: 'yyyy-MM-dd', showOn: 'both', buttonImage: '<%=request.getContextPath()%>/common/js/calendarNull.gif', buttonImageOnly: true},$.datepicker.regional['zh-CN']));
		})
		</script>
	</head>

	<body>
		<!--导航---start-->
		<div class="dqwz">
			<div style="margin-right: 10px;">
				<span style="cursor: pointer;" id="helpSpan"
					onclick="javascript:showHelpDiv('Z51003');">- 帮助 -</span>
				<span class="pageCode">No.Z51003</span>${currNavigation}
			</div>
		</div>
		<iframe name="ifm" id="helpIfm" scrolling="no" frameborder="0"
			width="100%" style="display: none;"
			onload="this.height=this.contentWindow.document.body.scrollHeight+13"></iframe>
		<!--导航---end-->

		<form method="post" name="auditSearchForm" id="auditSearchForm"
			action="<%=request.getContextPath()%>/day/dailywork/studAudit_qryChangeInfos.do?fillinBy=${ fillinBy}">
			<div class="tjcx">
				<dl>
					<dt>
						市区：
					</dt>
					<dd>
						<select class="inputText inputTextM" name="cityCode" id="cityCode">
							<option value="">
								--- 请选择 ---
							</option>
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
						信息变更时间：
					</dt>
					<dd>
						<input type="text" class="inputText inputTextS" name="startDate"
							id="startDate" readonly="readonly" />
						-
						<input type="text" class="inputText inputTextS" name="endDate"
							id="endDate" readonly="readonly" />
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
						证件号码：
					</dt>
					<dd>
						<input class="inputText inputTextM" type="text" id="idnum"
							name="idnum" />
					</dd>
				</dl>
				<dl>
					<dt>
						状态：
					</dt>
					<dd>
						<select class="inputText inputTextM" name="status" id="status">
							<c:forEach items="${ statusList}" var="status">
								<option value="${ status.entityCode}">
									${ status.entityName}
								</option>
							</c:forEach>
						</select>
					</dd>
				</dl>
			</div>
			<div class="button">
				<input class="inputButton" type="submit" value="查 询" />
			</div>
		</form>
		<div class="clear"></div>
		<!--查询条件end-->

		<!--列表样式---表格----开始-->
		<c:if test="${ not empty sinfoChangeList}">
			${ page.pageForm}
			<form method="post" name="doAuditForm" id="doAuditForm"
				action="<%=request.getContextPath()%>/day/dailywork/studAudit_doAudit.do?fillinBy=${ fillinBy}">
				<div class="list">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<caption>
							考生基础信息变更申请记录
						</caption>
						<thead>
							<tr>
								<th width="30" align="center">
									<a href="#" style="cursor: hand;" id="oppositeChk">反选</a>
								</th>
								<th>
									准考证号
								</th>
								<th>
									姓名
								</th>
								<th>
									证件号码
								</th>
								<th>
									报考专业
								</th>
								<th>
									信息变更内容
								</th>
								<th>
									信息变更时间
								</th>
								<th>
									信息变更原因
								</th>
								<th>
									证明材料
								</th>
								<th>
									登记人
								</th>
								<th>
									审核人
								</th>
								<th>
									状态
								</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${ sinfoChangeList}" var="sc">
								<tr
									<c:if test="${ sc.isPeopleChange == 1}">style="color:red;"</c:if>>
									<td align="center">
										<input type="checkbox" class="auditChangeIds"
											name="auditChangeIds" value="${ sc.changeId}" />
									</td>
									<td align="center">
										&nbsp;
										<a target="_blank"
											href="<%=request.getContextPath()%>/studinfo_show.do?studExamCode=${ sc.studExamCode}">
											${ sc.studExamCode}</a>
									</td>
									<td align="center">
										&nbsp;${ sc.studName}
									</td>
									<td align="center">
										&nbsp;${ sc.studIdnum}
									</td>
									<td align="center">
										&nbsp;${ sc.proName}
									</td>
									<td align="center">
										&nbsp;${ sc.changeTemplate}
									</td>
									<td align="center">
										&nbsp;${ sc.changeApplyTime}
									</td>
									<td align="center">
										&nbsp;${ sc.changeReason}
									</td>
									<td align="center">
										&nbsp;${ sc.changeProve}
									</td>
									<td align="center">
										&nbsp;${ sc.changeApplyUser}
									</td>
									<td align="center">
										&nbsp;${ sc.changeAuditUser}
									</td>
									<td align="center">
										&nbsp;${ sc.changeAuditStatus}
									</td>
								</tr>
							</c:forEach>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="12">
									<span>${ page.pageLink}</span>
									<input type="hidden" name="auditStatus" id="auditStatus"
										value="" />
									<input class="inputButton" type="button" id="auditBtn"
										value="审核通过" />
									不通过原因：
									<input class="inputText inputTextM" type="text"
										name="unAuditReason" id="unAuditReason" value="" />
									<input class="inputButton" type="button" id="unAuditBtn"
										value="审核不通过" />
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
			</form>
		</c:if>
	</body>
</html>