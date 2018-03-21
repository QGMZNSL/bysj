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
		<form action="payStat_bulkPay.do" method="post" enctype="multipart/form-data" name="f1">
		<!--显示详细页面----开始-->
		<div class="xsxx">
			<table border="0" cellpadding="0" cellspacing="0" width="100%">
				<tbody>
				<tr>
					<th>
						助学单位编号：
					</th>
					<td>
						${units[0].stipendUnitCode }&nbsp;
						<input type="hidden" name="unitCode" value="${units[0].stipendUnitCode }"/>
					</td>
				</tr>
				<tr>
					<th>
						助学单位名称：
					</th>
					<td>
						${units[0].stipendUnitName }&nbsp;
					</td>
				</tr>
				<tr>
					<th>
						助学单位详细地址：
					</th>
					<td>
						${units[0].stipendUnitAddress }&nbsp;
					</td>
				</tr>
				<tr>
					<th>
						助学单位负责人：
					</th>
					<td>
						${units[0].stipendUnitDuty }&nbsp;
					</td>
				</tr>
				<tr>
					<th>
						助学单位负责人联系电话：
					</th>
					<td>
						${units[0].stipendUnitDutyTelephone }&nbsp;
					</td>
				</tr>
				<!-- 
				<tr>
					<th>
						助学单位考生总数：
					</th>
					<td>
						${units[0].studNum }&nbsp;
					</td>
				</tr>
				 -->
				<tr>
					<th>
						未交费人数：
					</th>
					<td>
						<b><font color="red">${studentCount }&nbsp;</font></b>
					</td>
				</tr>
				<tr>
					<th>
						未交费人科数：
					</th>
					<td>
						<b><font color="red">${subjectCount }&nbsp;
						<input type="hidden" name="subjectCount" value="${subjectCount }"/></font></b>
					</td>
				</tr>
				<tr>
					<th>
						合计需交费金额：
					</th>
					<td>
						<b><font color="red">${totalMoney }元&nbsp;
						<input type="hidden" name="totalMoney" value="${totalMoney }"/></font></b>
					</td>
				</tr>
				</tbody>
			</table>
		</div>
		<!--显示详细页面----结束-->
		<!-- 
		<div class="infoedit">		
				<dl>
					<dt>
						助学单位编号：
					</dt>
					<dd>
						${units[0].stipendUnitCode }
						<input type="hidden" name="unitCode" value="${units[0].stipendUnitCode }"/>
					</dd>
				</dl>
				<dl>
					<dt>
						助学单位名称：
					</dt>
					<dd>
						${units[0].stipendUnitName }
					</dd>
				</dl>
				<dl>
					<dt>
						助学单位详细地址：
					</dt>
					<dd>
						${units[0].stipendUnitAddress }
					</dd>
				</dl>
				<dl>
					<dt>
						助学单位负责人：
					</dt>
					<dd>
						${units[0].stipendUnitDuty }
					</dd>
				</dl>
				<dl>
					<dt>
						助学单位负责人联系电话：
					</dt>
					<dd>
						${units[0].stipendUnitDutyTelephone }
					</dd>
				</dl>
				<dl>
					<dt>
						助学单位考生总数：
					</dt>
					<dd>
						${units[0].studNum }
					</dd>
				</dl>
				<dl>
					<dt>
						未交费人数：
					</dt>
					<dd>
						${studentCount }
					</dd>
				</dl>
				<dl>
					<dt>
						未交费人科数：
					</dt>
					<dd>
						${subjectCount }
						<input type="hidden" name="subjectCount" value="${subjectCount }"/>
					</dd>
				</dl>
				<dl>
					<dt>
						合计需交费金额：
					</dt>
					<dd>
						${totalMoney }元
						<input type="hidden" name="totalMoney" value="${totalMoney }"/>
					</dd>
				</dl>
				<div class="clear"></div>
		</div>		
		 -->
		<div class="button">
			<input class="inputButton" type="button" value="返回" onclick="history.go(-1);"/>
			<input class="inputButton" type="submit" value="确认支付" onclick="return confirm('您确实要为当前助学单位支付 ${totalMoney } 元吗？');" <c:if test="${subjectCount==0 }">disabled='disabled'</c:if>/>
		</div>
		</form>
		<div class="clear"></div>
		<!--编辑页面end-->

		<!--显示详细页面----开始-->
		<!--显示详细页面----结束-->

		<!--显示详细页面----结束-->
	</body>
</html>
