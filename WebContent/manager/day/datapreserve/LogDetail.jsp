<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>日志查询</title>
<link href="<%=request.getContextPath() %>/common/style/style.css" rel="stylesheet" type="text/css" />
</head>

<body>
<!--当前位置---开始-->
<div class="dqwz">
	<span>帮助</span>日常管理>>数据维护>>详细日志 
</div>
<!--当前位置---结束-->

<!--显示详细页面----开始-->
<div class="xsxx">
<table border="0" cellpadding="0" cellspacing="0" width="100%">
    <tbody>
    <tr>
    	<th>日志ID：</th>
        <td>&nbsp;</td>
    </tr>
    <tr>
    	<th>登录名：</th>
        <td>&nbsp;</td>
    </tr>
    <tr>
    	<th>真实姓名：</th>
        <td>&nbsp;</td>
    </tr>
    <tr>
    	<th>操作类型：</th>
        <td>&nbsp;</td>
    </tr>
    <tr>
    	<th>操作时间：</th>
        <td>&nbsp;</td>
    </tr>
    <tr>
    	<th>操作IP：</th>
        <td>&nbsp;</td>
    </tr>
    <tr>
    	<th>操作页面：</th>
        <td>&nbsp;</td>
    </tr>
    <tr>
    	<th>操作语句：</th>
        <td>&nbsp;</td>
    </tr>
    </tbody> 

</table>
</div>
<!--显示详细页面----结束-->
<div class="button">
 <input class="inputButton"  type="button" value="打 印" />
    <input class="inputButton"  type="button" value="返 回"  onclick="location.href='operateLogLoader.jsp';" />
    </div>
  </body>
</html>
