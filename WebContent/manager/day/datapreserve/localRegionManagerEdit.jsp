<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>市县维护</title>
<link href="<%=request.getContextPath() %>/common/style/style.css" rel="stylesheet" type="text/css" />
</head>

<body>
<!--当前位置---开始-->
<div class="dqwz">
	<span>帮助</span>日常管理>>数据维护>>修改市县维护
</div>
<!--当前位置---结束-->
    
<!--编辑页面----开始-->
<div class="infoedit">
<dl>
	<dt>编号：</dt>
    <dd><input class="inputText" type="text" name="" value="" /><span>(必须填写)</span></dd>
</dl>
<dl>
	<dt>名称：</dt>
	<dd><input class="inputText" type="text" name="" value="" /><span>(必须填写)</span></dd>
</dl>  
    <div class="clear"></div>
</div>

    <div class="button"><input class="inputButton" type="button" value="保 存" />
    <input class="inputButton" type="button" value="返 回" onclick="location.href='localRegionManager.jsp';"/>
    </div>
<div class="clear"></div>
<!--编辑页面----end-->  
  </body>
</html>
