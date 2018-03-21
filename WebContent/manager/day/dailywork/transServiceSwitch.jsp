<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>转考条件设置</title>
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
			<script type="text/javascript"">
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
		function chkSumit() {
			if ( !confirm("确定要保存以下配置吗？")) {
				return false;
			}
		
			if ( $('#tout_localSyllabusNum').val() == "") {
				alert("考籍转出设定中，请填写本省应通过课程数！");
				return false;
			}
			if ( $('#tout_startDate').val() == "") {
				alert("考籍转出设定中，请选择申请开始时间！");
				return false;
			}
			if ( $('#tout_endDate').val() == "") {
				alert("考籍转出设定中，请选择申请结束时间！");
				return false;
			}
			if ( $('#tout_noticeTitle').val() == "") {
				alert("考籍转出设定中，请选择考籍转出须知！");
				return false;
			}
			
			if ( $('#tin_localSyllabusNum').val() == "") {
				alert("考籍转入设定中，请填写本省应通过课程数！");
				return false;
			}
			if ( $('#tin_startDate').val() == "") {
				alert("考籍转入设定中，请选择申请开始时间！");
				return false;
			}
			if ( $('#tin_endDate').val() == "") {
				alert("考籍转入设定中，请选择申请结束时间！");
				return false;
			}
			if ( $('#tin_noticeTitle').val() == "") {
				alert("考籍转入设定中，请选择考籍转入须知！");
				return false;
			}
		
			return true;
		}
		
		$(document).ready( function() {
			$('#closeTransOutApply').click( function() {
				if ( confirm('该操作将设置申请开启时间段为昨日，确认继续吗？')) {
					var total = new Date();
					var year = total.getYear();
					var month = "0" + (parseInt(total.getMonth()) + 1);
					month = month.substr(month.length-2);
					var date = "0" + (parseInt(total.getDate()) - 1);
					date = date.substr(date.length-2);
					if ( month == "01" && date == "00") {
						year = parseInt(year) - 1;
						month = "12";
						date = "31";
					}
					$("#tout_startDate").val(year + "-" + month + "-" + date);
					$("#tout_endDate").val(year + "-" + month + "-" + date);
				}
			});
			
			
			$('#closeTransInApply').click( function() {
				if ( confirm('该操作将设置申请开启时间段为昨日，确认继续吗？')) {
					var total = new Date();
					var year = total.getYear();
					var month = "0" + (parseInt(total.getMonth()) + 1);
					month = month.substr(month.length-2);
					var date = "0" + (parseInt(total.getDate()) - 1);
					date = date.substr(date.length-2);
					if ( month == "01" && date == "00") {
						year = parseInt(year) - 1;
						month = "12";
						date = "31";
					}
					$("#tin_startDate").val(year + "-" + month + "-" + date);
					$("#tin_endDate").val(year + "-" + month + "-" + date);
				}
			});
		
			$("#tout_startDate").datepicker($.extend({ dateFormat: 'yyyy-MM-dd', showOn: 'both', buttonImage: '<%=request.getContextPath()%>/common/js/calendarNull.gif', buttonImageOnly: true},$.datepicker.regional['zh-CN']));			
			$("#tout_endDate").datepicker($.extend({ dateFormat: 'yyyy-MM-dd', showOn: 'both', buttonImage: '<%=request.getContextPath()%>/common/js/calendarNull.gif', buttonImageOnly: true},$.datepicker.regional['zh-CN']));
			$("#tin_startDate").datepicker($.extend({ dateFormat: 'yyyy-MM-dd', showOn: 'both', buttonImage: '<%=request.getContextPath()%>/common/js/calendarNull.gif', buttonImageOnly: true},$.datepicker.regional['zh-CN']));			
			$("#tin_endDate").datepicker($.extend({ dateFormat: 'yyyy-MM-dd', showOn: 'both', buttonImage: '<%=request.getContextPath()%>/common/js/calendarNull.gif', buttonImageOnly: true},$.datepicker.regional['zh-CN']));
		});
		</script>
	</head>

	<body>
		<!--导航---start-->
		<div class="dqwz">
	<div  style="margin-right: 10px;">
<span style="cursor:pointer; " id="helpSpan" onclick="javascript:showHelpDiv('Z42009');">- 帮助 -</span>
		<span class="pageCode">No.Z42009</span>${currNavigation}
	</div>
</div>
<iframe name="ifm" id="helpIfm" scrolling="no" frameborder="0" width="100%" style="display: none;" onload="this.height=this.contentWindow.document.body.scrollHeight+18">
	</iframe>
		<!--导航---end-->

		<form method="post" name="statusSettingForm" id="statusSettingForm"
			action="<%=request.getContextPath()%>/day/dailywork/staSwch_saveSwitches.do"
			onsubmit="return chkSumit();">
			<div style="margin-top:3px;">
			  <div class="infoedit">
					<h1 style="height:30px;line-height:30px">
						&nbsp;&nbsp;转出条件设定
					</h1>
					<dl>
						<dt>
							本省课程数：
						</dt>
						<dd>
							<input type="text" class="inputText inputTextM"
								name="tout_localSyllabusNum" id="tout_localSyllabusNum"
								value="${ tout_localSyllabusNum}"
								onkeydown='if((event.keyCode<96 || event.keyCode>105) && (event.keyCode<48 || event.keyCode>57) && event.keyCode!=8 && event.keyCode!=13 && event.keyCode!=46 && (event.keyCode<37 || event.keyCode>40)){return false;}' />
						</dd>
					</dl>
					<dl>
						<dt>
							转出申请：
						</dt>
						<dd>
							<input type="text" class="inputText inputTextS" readonly
								style="cursor: hand;" name="tout_startDate" id="tout_startDate"
								value="${ tout_startDate}" id="startDate" length="10"
								maxlength="10" />
							-
							<input type="text" class="inputText inputTextS" readonly
								style="cursor: hand;" name="tout_endDate" id="tout_endDate"
								value="${ tout_endDate}" id="endDate" length="10" maxlength="10" />
							<input type="button" class="inputButton inputButtonL"
								value="关闭转出申请" id="closeTransOutApply" />
						</dd>
					</dl>
					<dl>
						<dt>
							转出须知：
						</dt>
						<dd>
							<input type="text" id="tout_noticeTitle" readonly
								style="cursor: hand;width:327px" class="inputText" name="tout_noticeTitle"
								value="${ tout_noticeTitle}"
								onclick="window.open('<%=request.getContextPath()%>/day/dailywork/staSwch_qryNotices.do?noticeType=transout&noticeTextId=tout_noticeId&noticeTextName=tout_noticeTitle','_blank', 'top=150,left=300,height=600,width=500,resizable=0,toolbar=0,menubar=0,location=0');" />
							<input type="hidden" name="tout_noticeId" id="tout_noticeId"
								value="${ tout_noticeId}" />
						</dd>
					</dl>
				</div>
			</div>

			<div style="margin-top: 10px;">
			  <div class="infoedit">
					<h1 style="height:30px;line-height:30px">
						&nbsp;&nbsp;转入条件设定
					</h1>
					<dl>
						<dt>
							本省课程数：
						</dt>
						<dd>
							<input type="text" class="inputText inputTextM"
								name="tin_localSyllabusNum" id="tin_localSyllabusNum"
								value="${ tin_localSyllabusNum}"
								onkeydown='if((event.keyCode<96 || event.keyCode>105) && (event.keyCode<48 || event.keyCode>57) && event.keyCode!=8 && event.keyCode!=13 && event.keyCode!=46 && (event.keyCode<37 || event.keyCode>40)){return false;}' />
						</dd>
					</dl>
					<dl>
						<dt>
							转入申请：
						</dt>
						<dd>
							<input type="text" class="inputText inputTextS" readonly
								style="cursor: hand;" name="tin_startDate"
								value="${ tin_startDate}" id="tin_startDate" length="10"
								maxlength="10" />
							-
							<input type="text" class="inputText inputTextS" readonly
								style="cursor: hand;" name="tin_endDate" value="${ tin_endDate}"
								id="tin_endDate" length="10" maxlength="10" />
							<input type="button" class="inputButton inputButtonL"
								value="关闭转入申请" id="closeTransInApply" />
						</dd>
					</dl>
					<dl>
						<dt>
							转入须知：
						</dt>
						<dd>
							<input type="text" id="tin_noticeTitle" readonly
								style="cursor: hand;width:327px" class="inputText" name="tin_noticeTitle"
								value="${ tin_noticeTitle}"
								onclick="window.open('<%=request.getContextPath()%>/day/dailywork/staSwch_qryNotices.do?noticeType=transin&noticeTextId=tin_noticeId&noticeTextName=tin_noticeTitle','_blank', 'top=150,left=300,height=600,width=500,resizable=0,toolbar=0,menubar=0,location=0');" />
							<input type="hidden" name="tin_noticeId" id="tin_noticeId"
								value="${ tin_noticeId}" />
						</dd>
					</dl>
				</div>
			</div>

			<div class="button" style="margin-top: 10px;">
				<input class="inputButton" type="submit" value="保 存" />
				<input class="inputButton" type="reset" value="重 置"
					onclick="return confirm('确认重置为先前设置的数据吗？');" />
			</div>
		</form>
	</body>
</html>

