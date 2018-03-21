<%@page import="com.sincinfo.zxks.bean.BaseUser"%>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="no-cache"/>
<title>帮助</title>

<script type="text/javascript" src="<%=request.getContextPath()%>/common/js/jquery-1.3.2.min.js"></script>
<script type="text/javascript">
	function checkForm(){
		var gUserGrade = "<%= ((BaseUser)session.getAttribute("zkOperator")).getUserRole()%>";
		if(gUserGrade == '1'){
			if(confirm("保存后将直接使用在帮助页面，是否继续？")){
				return true;
			}else{
				return false;
			}
		}else{
			if(confirm("需要管理员审核后才能在页面帮助中显示，是否继续？")){
				return true;
			}else{
				return false;
			}
		}
	}
	function back(help_id){
		if(confirm("确定要放弃编辑？")){
			location.href="<%=request.getContextPath()%>/zk/help/help_getHelp.do?help_id="+help_id
		}
	}
</script>
</head>
<body>
	<div id="helpDiv"
		style="z-index: 300;border:1px #BFD0CE solid;background-color:#F5F9FB;">
		<table cellspacing="0" cellpadding="0" align="left" style="width: 100%">
			<tr>
				<td id="helpFram">
					<table width="100%" cellspacing="0" cellpadding="0">
						<tr>
							<td align="left">
								<form name="form1" method="post" action="<%=request.getContextPath()%>/zk/help/help_save.do" onsubmit="return checkForm();">
									<div style="margin-left:16px;color:#333333;">
											<input type="hidden" name="hid" value="${helpInfo[0] }" />
											<input type="hidden" name="help_id" value="${help_id}" />
											<input type="hidden" name="isCurrent" value="${isCurrent}" />
											<div style="font-size:15px;padding-bottom: 10px;padding-top: 5px;">功能说明</div>
											<input name="functionDesc" type="text" value='${helpInfo[2] }'id="funcDesc" style="display: none" />
											<iframe id="ew1" src="<%=request.getContextPath()%>/common/eWebEditor/mini.htm?id=funcDesc" 
											frameborder="0" scrolling="no" width="80%" height="200"></iframe>
											<div style="font-size:15px;padding-bottom: 10px;padding-top: 5px;">操作说明</div>
											<input name="optDesc" type="text" value='${helpInfo[3] }' id="optratorDesc" style="display: none" />
											<iframe id="ew2" src="<%=request.getContextPath()%>/common/eWebEditor/mini.htm?id=optratorDesc" 
											frameborder="0" scrolling="no" width="80%" height="200"></iframe>
											<div style="font-size:15px;padding-bottom: 10px;padding-top: 5px;">注释说明</div>
											<input name="annDesc" type="text" value='${helpInfo[4] }' id="annotationDesc" style="display: none" />
											<iframe id="ew3" src="<%=request.getContextPath()%>/common/eWebEditor/mini.htm?id=annotationDesc" 
											frameborder="0" scrolling="no" width="80%" height="200"></iframe>
									</div>
									<div style="top: 10px; padding-top: 5px;padding-bottom: 5px; text-align: center;">
										<input type="submit" id="save" value=" 保存 " />&nbsp;&nbsp;&nbsp;&nbsp;
										<input type="button" id="cancel" value=" 返回 " onclick="javascript:back('${helpInfo[1]}')" />
									</div>
								</form>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</div>
</body>
</html>