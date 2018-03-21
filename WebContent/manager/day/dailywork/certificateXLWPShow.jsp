<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://nbf.river.org/vxds" prefix="v"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>证书查询</title>
		<link href="<%=request.getContextPath()%>/common/style/style.css"
			rel="stylesheet" type="text/css" />
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/common/js/riverdefine.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/common/js/riverajax.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/common/js/clientfuns.js"></script>
		<script type="text/javascript">
			function check(){
				var diplomaNum=document.getElementById("diplomaNum").value;
				var studIdnum=document.getElementById("studIdnum").value;
				if(isNull(diplomaNum)&&isNull(studIdnum)){
					alert("请填写查询条件！");
					return false;
				}else{
					return true;
				}
			}
		</script>
	</head>
	<body>
		<!--导航---start-->
		<div class="dqwz">
			<div style="margin-right: 10px;">
				<span style="cursor: pointer;" id="helpSpan"
					onclick="javascript:showHelpDiv('Z51006');">- 帮助 -</span>
				<span class="pageCode">No.Z51006</span>${currNavigation}
			</div>
		</div>
		<jsp:include page="/help/helpJs.jsp" />
		<iframe name="ifm" id="helpIfm" scrolling="no" frameborder="0"
			width="100%" style="display: none;"
			onload="this.height=this.contentWindow.document.body.scrollHeight+13"></iframe>
		<!--导航---end-->
		
		<div class="xsxx">
			<table border="0" cellpadding="0" cellspacing="0" width="100%">
				<caption>
					陕西省高等教育学历文凭毕业证书查询
				</caption>
				<tbody>
					<tr>
						<th>
							证件号码：
						</th>
						<td>
							&nbsp;${ diplomaInfo[0]}
						</td>
						<th>
							姓名：
						</th>
						<td>
							&nbsp;${ diplomaInfo[1]}
						</td>
					</tr>
					<tr>
						<th>
							性别：
						</th>
						<td>
							&nbsp;${ diplomaInfo[2]}
						</td>
						<th>
							身份证号：
						</th>
						<td>
							&nbsp;${ diplomaInfo[3]}
						</td>
					</tr>
					<tr>
						<th>
							毕业院校：
						</th>
						<td>
							&nbsp;${ diplomaInfo[4]}
						</td>
						<th>
							毕业时间：
						</th>
						<td>
							&nbsp;${ diplomaInfo[5]}
						</td>
					</tr>
					<tr>
						<th>
							毕业证号：
						</th>
						<td colspan="3">
							&nbsp;${ diplomaInfo[6]}
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="clear"></div>

		<div class="button">
			<input type="button" class="inputButton" value="返 回"
				onclick="location.href='<%=request.getContextPath()%>/manager/day/dailywork/c_goXLWP.do'" />
		</div>
	</body>
</html>