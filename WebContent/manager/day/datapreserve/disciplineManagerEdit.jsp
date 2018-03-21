<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>违纪管理</title>
<link href="<%=request.getContextPath() %>/common/style/style.css" rel="stylesheet" type="text/css" />
</head>

<body>
<!--当前位置---开始-->
<div class="dqwz">
	<span>帮助</span>日常管理>>数据维护>>添加违纪
</div>
<!--当前位置---结束-->

<!--编辑页面----开始-->
<div class="infoedit">
<dl>
	<dt>违纪情况描述：</dt>
    <dd><input class="inputText" type="text" name="" value="" /><span>(必须填写)</span></dd>
</dl>
<dl>
	<dt>违纪情况处理：</dt>	
	<dd>
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td><input type="checkbox"/> 扣分</td>
        <td><input type="radio"/>a-所有课程 <input type="radio"/> b-当次所有课程 <input type="radio"/> c-当科
	    <input class="inputText inputTextS" type="text"/>%<span>(必须填写)</span></td>
      </tr>
      <tr>
        <td> <input type="checkbox"/>取消成绩</td>
        <td><input type="radio"/>a-所有课程 <input type="radio"/> b-当次所有课程 <input type="radio"/> c-当科
        </td>
      </tr>
      <tr>
        <td> <input type="checkbox"/>停考</td>
        <td><input class="inputText inputTextS" type="text"/>年<span>(必须填写)</span></td>
      </tr>
      <tr>
        <td><input type="checkbox"/>推迟毕业</td>
        <td><input class="inputText inputTextS" type="text"/>年<span>(必须填写)</span></td>
      </tr>
      <tr>
        <td><input type="checkbox"/> 取消考籍</td>
        <td>&nbsp;</td>
      </tr>
    </table>
</dd>
</dl>
<div class="clear"></div>
</div>
<div class="button"><input class="inputButton" type="button" value="保 存" />
<input class="inputButton" type="button" value="返 回"  onclick="location.href='disciplineManager.jsp';" />
<!--编辑页面----end-->

  </body>
</html>
