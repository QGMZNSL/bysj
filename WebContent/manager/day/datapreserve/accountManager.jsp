<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="v" uri="http://nbf.river.org/vxds"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>对账管理</title>
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
			<span>帮助</span>日常管理>>数据维护>>对账管理
		</div>
		<!--当前位置---结束-->

		<!--编辑页面---开始-->
		<form action="" method="post" enctype="multipart/form-data" name="f1">
		<div class="infoedit">		
				<dl>
					<dt>
						上传文件：
					</dt>
					<dd>
						<input class="inputText inputTextL" type="file" name="uploadFile"/><input class="inputButton" type="button" value="上 传" onclick="checkSubmit();"/>	
					</dd>
				</dl>
				<dl>
					<dt>
						<font color="red">注：</font>
					</dt>
					<dd>
						<font color="red">上传文件说明：上传文件类型限定为csv，文件大小不超过100M。</font>	
					</dd>
				</dl>			
				<dl>
					<dt>
						选择对账日期：
					</dt>
					<dd>
						<input class="inputText inputTextM" id="queryDate" name="queryDate" type="text" value="<%=request.getParameter("queryDate")!=null ? request.getParameter("queryDate") : "" %>"/>
					</dd>
				</dl>
				<div class="clear"></div>
		</div>		
		<div class="button">
			<input class="inputButton" type="button" value="查 询" onclick="checkQuery();"/>
			<c:if test="${excelUrl!=null && excelUrl!=''  }">
			<a href="${excelUrl }" target="_blank">下载报表</a>
			</c:if>
		</div>
		</form>
		<div class="clear"></div>
		<!--编辑页面end-->

		<!--显示详细页面----开始-->
		<c:if test="${dzTotalMoney!=null && sjTotalMoney!=null}">
		<div class="xsxx">
			<table border="0" cellpadding="0" cellspacing="0" width="100%">
				<tbody>
					<tr>
						<th>
							对账文件总金额：
						</th>
						<td>
							<font color="red" size="4"><strong>${dzTotalMoney }</strong></font>&nbsp;<font size="4">元</font>
						</td>
					</tr>
					<tr>
						<th>
							实际到帐金额：
						</th>
						<td>
							<font color="red" size="4"><strong>${sjTotalMoney }</strong></font>&nbsp;<font size="4">元</font>
						</td>
					</tr>
					<tr>
						<th>
							相差金额：
						</th>
						<td>
							<font color="red" size="4"><strong>${xcTotalMoney }</strong></font>&nbsp;<font size="4">元</font>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		</c:if>
		<!--显示详细页面----结束-->

		<c:if test="${not empty  errInfoList}">
		<div class="xsxx">
			<table border="0" cellpadding="0" cellspacing="0" width="100%">
				<tbody>
					<tr>
					    <th width="30">
							序号
						</th>
					    <th>
							错误类型
						</th>
					    <th>
							准考证号
						</th>
						<th>
							订单号
						</th>
						<th>
							本地金额（元）
						</th>
						<th>
							对账文件金额（元）
						</th>					
						<th>
							支付时间
						</th>
					</tr>
					<c:forEach items="${errInfoList }" var="pL" varStatus="v">
					<tr>
						<td align="center">
							${v.index+1 }&nbsp;
						</td>
						<td align="center">
<c:choose>
<c:when test="${pL[5]==1 }">系统不存在此订单</c:when>
<c:when test="${pL[5]==2 }">系统中金额不一致</c:when>
<c:when test="${pL[5]==3 }">系统中时间不一致</c:when>
<c:when test="${pL[5]==4 }">科目数与订单创建时不符</c:when>
</c:choose>							
&nbsp;
						</td>
						<td>
${pL[0] }						
							&nbsp;
						</td>
						<td>
							${pL[1] }&nbsp;
						</td>
						<td>
<c:choose>
<c:when test="${pL[5]==1 }">--.--</c:when>
<c:otherwise>${pL[2] }</c:otherwise>
</c:choose>							
							&nbsp;
						</td>
						<td>
							${pL[3] }&nbsp;
						</td>
						<td>
							${fn:substring(pL[4],0,19) }&nbsp;
						</td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		</c:if>
		<!--显示详细页面----结束-->
	</body>
</html>
