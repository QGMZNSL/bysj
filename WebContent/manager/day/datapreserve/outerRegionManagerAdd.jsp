<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>省市管理</title>
<link href="<%=request.getContextPath() %>/common/style/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript"
			src="<%=request.getContextPath()%>/common/js/jquery-1.3.2.min.js"></script>
<script type="text/javascript">
		function switchArea( userTypeVal, speed) {
			$('.disNone').hide();
			$('.' + userTypeVal).show( speed);
		}
		
		$(document).ready( function() {
			$('.userType').click( function() {
				switchArea( this.value, "slow");
			});
		
			$('.disNone').hide();			
			$('.userType')[0].checked = true;
			switchArea( $('.userType')[0].value, "");
		})
		</script>
</head>

<body>
<!--当前位置---开始-->
<div class="dqwz">
	<span>帮助</span>日常管理>>数据维护>>添加省市
</div>
<!--当前位置---结束-->

<!--编辑页面----开始-->
<div class="infoedit">
<dl>
	<dt>编号：</dt>
    <dd><input class="inputText" type="text" name="" value="" /><span>(必须填写)</span></dd>
</dl>
<dl>
	<dt>类型：</dt>	
	<dd><input  type="radio"/>省
	<input  type="radio" name="userType" class="userType" value="sctRegion1" />市</dd>
</dl>
<dl class="sctRegion1 disNone">
    <dt>所属省：</dt>
    <dd><select class="inputText inputTextS">
    				<option>101001-湖南省</option>
                    <option>选项</option>
                    <option>选项</option>
                    <option>选项</option>
                    <option>选项</option>
    			</select></dd>
</dl>
<dl>
	<dt>名称：</dt>
	<dd><input class="inputText" type="text" name="" value="" /><span>(必须填写)</span></dd>
</dl>  
    <div class="clear"></div>
</div>
    <div class="button"><input class="inputButton" type="button" value="保 存" />
    <input class="inputButton" type="button" value="返 回" onclick="location.href='outerRegionManager.jsp'"/>
    </div>

<div class="clear"></div>
<!--编辑页面----end-->  
  </body>
</html>
