<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>日志查询</title>
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
		$(document).ready( function() {
			$('#expBtn').click( function() {
				var expUrl = "<%=request.getContextPath()%>/manager/day/datapreserve/logLoader_expXLS.do";
				var param = "&loginName=";
				param += $('#loginName').val();
				param += "&loginIp=";
				param += $('#loginIp').val();
				param += "&startDate=";
				param += $('#startDate').val();
				param += "&endDate=";
				param += $('#endDate').val();
				param += "&logType=";
				param += $('#logType').val();
				
				$.ajax({
				     type: "post", // 传值方式
				     url: expUrl,    // 接收后台地址
				     data: "t=" + new Date().getTime() + param,   // 数据
				     async: true,  // 是否异步	
				     success: function( msg) { 
				     	$('#downArea').html("");
				     	if ( msg.substring(0,2) == "<a") {
				     		alert("导出成功！请点击下载链接进行下载！");
				     		$('#downArea').html(msg);
				     	} else if ( msg == "error") {
				     		alert("导出失败！");
				     	} else {
				     		alert("未取得参数，系统异常！");
				     	}
				     }
			    });
			});
		
			$("#startDate").datepicker($.extend({ dateFormat: 'yyyy-MM-dd', showOn: 'both', buttonImage: '<%=request.getContextPath()%>/common/js/calendarNull.gif', buttonImageOnly: true},$.datepicker.regional['zh-CN']));
			$("#endDate").datepicker($.extend({ dateFormat: 'yyyy-MM-dd', showOn: 'both', buttonImage: '<%=request.getContextPath()%>/common/js/calendarNull.gif', buttonImageOnly: true},$.datepicker.regional['zh-CN']));
		});
		</script>
	</head>

	<body>
		<!--导航---start-->
		<div class="dqwz">
			<div style="margin-right: 10px;">
				<span style="cursor: pointer;" id="helpSpan"
					onclick="javascript:showHelpDiv('Z53004');">- 帮助 -</span>
				<span class="pageCode">No.Z53004</span>${currNavigation}
			</div>
		</div>
		<iframe name="ifm" id="helpIfm" scrolling="no" frameborder="0"
			width="100%" style="display: none;"
			onload="this.height=this.contentWindow.document.body.scrollHeight+13"></iframe>
		<!--导航---end-->

		<!--查询条件开始-->
		<form method="post" name="form1"
			action="<%=request.getContextPath()%>/manager/day/datapreserve/logLoader_getAllLogByDemand.do">

			<div class="tjcx">
				<dl>
					<dt>
						登录名：
					</dt>
					<dd>
						<input class="inputText inputTextM" type="text" name="loginName"
							id="loginName" value="${loginName }" />
					</dd>
				</dl>
				<dl>
					<dt>
						登录IP：
					</dt>
					<dd>
						<input class="inputText inputTextM" type="text" name="loginIp"
							id="loginIp" value="${ loginIp}" />
					</dd>
				</dl>
				<dl>
					<dt>
						登录时间：
					</dt>
					<dd>
						<input class="inputText inputTextS" type="text" id="startDate"
							size="10" maxlength="10" readonly="readonly"
							style="cursor: hand;" name="beginTime" value="${beginTime }" />
						至
						<input class="inputText inputTextS" type="text" id="endDate"
							size="10" maxlength="10" readonly="readonly"
							style="cursor: hand;" name="endTime" value="${endTime }" />
					</dd>
				</dl>
				<dl>
					<dt>
						日志类型：
					</dt>
					<dd>
						<select class="inputText inputTextM" name="logType" id="logType">
							<option value="">
								全部登录日志
							</option>
							<option value="success">
								登录成功日志
							</option>
							<option value="failure">
								登录失败日志
							</option>
						</select>
					</dd>
				</dl>
			</div>
			<div class="button">
				<input class="inputButton" type="submit" value="查 询" />
				<input class="inputButton" id="expBtn" type="button" value="导 出" />
				<span id="downArea"></span>
			</div>
		</form>

		<div class="clear"></div>
		<!--查询条件end-->

		<!--列表样式---表格----开始-->
		<div class="list">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<thead>
					<tr>
						<th width="30px">
							序号
						</th>
						<th>
							日志ID
						</th>
						<th>
							登录名
						</th>
						<th>
							登录密码
						</th>
						<th>
							登录IP
						</th>
						<th width="150">
							登录时间
						</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td align="center">
						</td>
						<td align="center">
						</td>
						<td align="center">
						</td>
						<td align="center">
						</td>
						<td align="center">
						</td>
					</tr>
					<c:forEach var="log" items="${logLoginList}" varStatus="countN">
						<tr>
							<td>
								&nbsp;${countN.count}
							</td>
							<td>
								&nbsp;${log.logLoginId}
							</td>
							<td>
								&nbsp;${log.userName }
							</td>
							<td>
								&nbsp;${log.loginPassword }
							</td>
							<td>
								&nbsp;${log.loginIp }
							</td>
							<td>
								&nbsp;${log.loginTime }
							</td>
						</tr>
					</c:forEach>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="6" align="right">
							${page.pageInfo }
						</td>
					</tr>
				</tfoot>
			</table>
		</div>
		<!--列表样式---表格----end-->

	</body>
</html>
