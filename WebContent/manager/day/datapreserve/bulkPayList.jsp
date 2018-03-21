<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="v" uri="http://nbf.river.org/vxds"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>助学单位支付</title>
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
		function checkSubmit(){
			var uploadFile = document.getElementsByName('uploadFile')[0];
			var houzhui = uploadFile.value.substr(uploadFile.value.lastIndexOf(".")+1,
					uploadFile.value.length).toLowerCase();
						
			if(uploadFile.value==''){
				alert('请选择上传文件！');
				uploadFile.focus();
				return;
			}else if(houzhui!= 'csv'){
				alert('请选择后缀名为csv的文件！');
				uploadFile.focus();
				return;
			}
			
			if(confirm("确定上传吗？")){
				document.f1.action = "payStat_upload.do?uploadpath="+uploadFile.value;
				f1.submit();
			}
		}
		
		/**查询提交验证**/
		function checkQuery(){
		/*
			var queryDate = document.getElementById("queryDate");
			var arB = new Array(1);
			arB[0] = new Array(5);
			arB[0][0] = "queryDate";//标签name名称
			arB[0][1] = "date";//验证表达式
			arB[0][2] = document.getElementsByName("queryDate")[0].value;//要验证的值
			arB[0][3] = "查询日期格式不正确！例：1980-01-01(必须为半角数字及字符)";//验证失败要提示的信息
			arB[0][4] = "queryDate";//标签ID
			if(!RiverDoCheckArray(arB)){								
				return false;
			}
		*/
			
			document.f1.action = "payStat_queryPay.do?type=1";
			f1.submit();
			return true;
		}

			$(document).ready( function() {
			$("#queryDate").datepicker($.extend({ dateFormat: 'yyyy-MM-dd', showOn: 'both', buttonImage: '<%=request.getContextPath()%>/common/js/calendarNull.gif', buttonImageOnly: true},$.datepicker.regional['zh-CN']));
		});
		</script>
	</head>

	<body>
		<!--当前位置---开始-->
		<div class="dqwz">
			<span>帮助</span>日常管理>>数据维护>>助学单位支付
		</div>
		<!--当前位置---结束-->

		<!--编辑页面---开始-->
		<form action="payStat_bulkPreview.do" method="post" enctype="multipart/form-data" name="f1">
		<div class="infoedit">		
				<dl>
					<dt>
						助学单位：
					</dt>
					<dd>
						<select name="unitCode">
						<c:forEach var="it" items="${units }">
							<option value="${it.stipendUnitCode }" <c:if test="${it.stipendUnitCode==unitCode }">selected='selected'</c:if>>${it.stipendUnitName }</option>
						</c:forEach>
						</select>
						<input class="inputButton" type="submit" value="查 询"/>
					</dd>
				</dl>
				<div class="clear"></div>
		</div>		
		</form>
		<div class="clear"></div>
		<!--编辑页面end-->

		<!--显示详细页面----开始-->
		<!--显示详细页面----结束-->
	</body>
</html>
