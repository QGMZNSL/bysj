<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>字典维护</title>
<link href="<%=request.getContextPath() %>/common/style/style.css" rel="stylesheet" type="text/css" />
</head>

<body>
<!--当前位置---开始-->
<div class="dqwz">
	<span>帮助</span>日常管理>>数据维护>>修改字典
</div>
<!--当前位置---结束-->

<!--编辑页面----开始-->
<div class="infoedit">
<form action="" method="post" name="">
<dl>
	<dt>代码类型：</dt>
    <dd><input class="inputText" type="text" name="" value="" /><span>(必须填写)</span></dd>
</dl>
<dl>
	<dt>名称：</dt>
	<dd><input class="inputText" type="text" name="" value="" /><span>(必须填写)</span></dd>
</dl>
<dl>
	<dt>状态：</dt>
	 <dd><input type="radio" name="" value="" />启用<input type="radio" name="" value="" />禁用<span>(必须填写)</span></dd>
</dl>
<div class="clear"></div>
</form>
</div>
<div class="button"><input class="inputButton" type="submit" value="保 存" /><input class="inputButton" type="reset" value="重 置" /></div>
<!--编辑页面----end-->
  
  </body>
</html>
