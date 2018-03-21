<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>考生信息变更记录</title>
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
		$(document).ready( function() {
			 $("#startDate").datepicker($.extend({ dateFormat: 'yyyy-MM-dd', showOn: 'both', buttonImage: '<%=request.getContextPath()%>/common/js/calendarNull.gif', buttonImageOnly: true},$.datepicker.regional['zh-CN']));
			 $("#endDate").datepicker($.extend({ dateFormat: 'yyyy-MM-dd', showOn: 'both', buttonImage: '<%=request.getContextPath()%>/common/js/calendarNull.gif', buttonImageOnly: true},$.datepicker.regional['zh-CN']));
			 $('#expPdf').click( function() {
				ajaxExpPdf();
			});
			$('#auditStatus').val("${ auditStatus}");
		});
		
		function ajaxExpPdf(){
			var cityCode=document.getElementById("cityCode").value;
			var auditStatus=document.getElementById("auditStatus").value;
			var startDate=document.getElementById("startDate").value;
			var endDate=document.getElementById("endDate").value;
			var studExamCode=document.getElementById("studExamCode").value;
			var str="fillinBy=${ fillinBy}&cityCode="+cityCode+"&startDate="+startDate+"&endDate="+endDate+"&studExamCode="+studExamCode + "&auditStatus=" + auditStatus;
			//alert(str);	
			var ajaxurl="studcr_ajaxGetPdf.do";
			$.ajax({
				type:"post",	
				url:ajaxurl,
				data:str,
				async:false,
				success:function(msg){
					if ( "error" == msg) {
						$('#expdown').html("");
						alert("导出失败！");
						$('#expdown').html("");
					} else if ( "noData" == msg) {
						alert("没有记录！")
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
		<!--导航---start-->
		<div class="dqwz">
			<div style="margin-right: 10px;">
				<span style="cursor: pointer;" id="helpSpan"
					onclick="javascript:showHelpDiv('Z51004');">- 帮助 -</span>
				<span class="pageCode">No.Z51004</span>${currNavigation}
			</div>
		</div>
		<iframe name="ifm" id="helpIfm" scrolling="no" frameborder="0"
			width="100%" style="display: none;"
			onload="this.height=this.contentWindow.document.body.scrollHeight+13"></iframe>
		<!--导航---end-->

		<!--查询条件开始-->
		<form action="studcr_select.do?fillinBy=${ fillinBy}" method="post"
			name="form1">
			<div class="tjcx">
				<dl>
					<dt>
						市区：
					</dt>
					<dd>
						<select class="inputText inputTextM" name="cityCode" id="cityCode">
							<c:choose>
								<c:when test="${not empty cityList}">
									<option value="">
										各地市
									</option>
									<c:forEach var="bean" items="${cityList}">
										<option value="${bean.cityCode }"
											<c:if test="${bean.cityCode==cityCode}">selected</c:if>>
											${bean.cityName}
										</option>
									</c:forEach>
								</c:when>
								<c:otherwise>
									<option value="${city.cityCode }">
										${city.cityName}
									</option>
								</c:otherwise>
							</c:choose>
						</select>
					</dd>
				</dl>
				<dl>
					<dt>
						状态：
					</dt>
					<dd>
						<select class="inputText inputTextM" name="auditStatus"
							id="auditStatus">
							<option value="">
								全部
							</option>
							<option value="0">
								未审核
							</option>
							<option value="1">
								审核不通过
							</option>
							<option value="8">
								待省上处理
							</option>
							<option value="9">
								已审核
							</option>
						</select>
					</dd>
				</dl>
				<dl>
					<dt>
						信息变更时间：
					</dt>
					<dd>
						<input type="text" class="inputText inputTextS" id="startDate"
							name="startDate" value="${startDate}" />
						-
						<input type="text" class="inputText inputTextS" id="endDate"
							name="endDate" value="${endDate}" />
					</dd>
				</dl>
				<dl>
					<dt>
						准考证号：
					</dt>
					<dd>
						<input class="inputText inputTextM" type="text" id="studExamCode"
							name="studExamCode" value="${studExamCode}" />
					</dd>
				</dl>
			</div>
			<div class="button">
				<input class="inputButton" type="submit" value="查 询" />
				<input class="inputButton" type="button" id="expPdf" value="导　出" />
				<span id="expdown"></span>
			</div>
		</form>
		<div class="clear"></div>
		<!--查询条件end-->
		<!--列表样式---表格----开始-->
		<div class="list">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<caption>
					考生基本信息变更记录表
				</caption>
				<thead>
					<tr>
						<th>
							序号
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
							审批人
						</th>
					</tr>
				</thead>
				<c:choose>
					<c:when test="${not empty dataList}">
						<tbody>
							<c:forEach var="bean" items="${dataList}" varStatus="h">
								<tr>
									<td width="30" align="center">
										${page.pagesize*page.pagenum+ h.index+1}
									</td>
									<td align="center">
										<a target="_blank"
											href='studcr_show.do?studCode=${bean.studExamCode}'>${bean.studExamCode
											}</a>
									</td>
									<td align="center">
										${ bean.studName}
									</td>
									<td align="center">
										${ bean.studIdnum}
									</td>
									<td align="center">
										${ bean.proName}
									</td>
									<td>
										${ bean.changeTemplate}
									</td>
									<td align="center">
										${ bean.changeApplyTime}
									</td>
									<td align="center">
										${ bean.changeReason}
									</td>
									<td align="center">
										${ bean.changeProve}
									</td>
									<td align="center">
										${ bean.changeApplyUserRealName}
									</td>
									<td align="center">
										${ bean.changeAuditUserRealName}&nbsp;
									</td>
								</tr>
							</c:forEach>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="11">
									<span>${page.pageInfo } </span>
								</td>
							</tr>
						</tfoot>
					</c:when>
					<c:otherwise>
						<tr>
							<td colspan="11" align="center">
								没有记录
							</td>
						</tr>
					</c:otherwise>
				</c:choose>

			</table>
		</div>
		<!--列表样式---表格----end-->
	</body>
</html>
