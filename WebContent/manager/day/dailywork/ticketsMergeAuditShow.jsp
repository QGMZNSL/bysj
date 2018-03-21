
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>审核合并准考证信息查看</title>
<link href="<%=request.getContextPath() %>/common/style/style.css" rel="stylesheet" type="text/css" />
</head>
<body>
<!--显示详细页面----开始-->
<div class="xsxx-1">
<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<caption>陕西省高等教育自学考试准考证合并申请信息</caption>
    <tbody>
    <tr>
    	<th class="width"></th>
        <th>准考证信息</th>
        <th>被合并准考证信息</th>
    </tr>
    <tr>
    	<th>准考证号</th>
        <td>610452012410</td>
        <td>613201550</td>
    </tr>
    <tr>
    	<th>姓名</th>
        <td>蒋俊</td>
        <td>蒋俊</td>
    </tr>
    <tr>
    	<th>证件号码</th>
        <td>6251018369（士官证）</td>
        <td>123456199001018369（身份证）</td>
    </tr>
    <tr>
    	<th>性别</th>
        <td>男</td>
        <td>男</td>
    </tr>
    <tr>
    	<th>民族</th>
        <td>汉</td>
        <td>汉</td>
    </tr>
    <tr>
    	<th>出生日期</th>
        <td>1990-01-01</td>
        <td>1990-01-01</td>
    </tr>
    </tbody>
    
</table>
</div>

<!--显示详细页面----结束-->
<!--显示详细页面----开始-->
<div class="list">
<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<caption>被合并准考证课程信息：</caption>
    <tbody>
    <tr>
    	<th>课程名称</th>
        <th>课程成绩</th>
        <th>合格时间</th>
        <th>合格原因</th>
        <th>是否违纪</th>
    </tr>
    <tr>
    	<td align="center">01022-大学英语（一）</td>
        <td align="center">65</td>
        <td align="center">2005-06</td>
        <td align="center">正常考试</td>
        <td align="center">是</td>
    </tr>
     <tr>
    	<td align="center">01220-基础护理</td>
        <td align="center">75</td>
        <td align="center">2008-06</td>
        <td align="center">转入</td>
        <td align="center">否</td>
    </tr>
    </tbody>
    
</table>
</div>

<!--显示详细页面----结束-->

<input class="inputButton" type="button" value="关 闭" onclick="window.close();" />

</body>