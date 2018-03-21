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
	<span>帮助</span>日常管理>>数据维护>>字典维护
</div>
<!--当前位置---结束-->

 <!--查询条件开始-->
<div class="tjcx">
<form method="post" name="form1">
	<dl>
		<dt>代码类型：</dt>
		<dd><select class="inputText inputTextM">
    				<option>性别</option>
                    <option>民族</option>
                    <option>政治面貌</option>
                    <option>选项</option>
                    <option>选项</option>
    			</select></dd>
	</dl>
</form>    
</div>
    <div class="button">
    <input class="inputButton" type="button" onclick="location.href='dictManagerAdd.jsp';" value="添 加" />
    <input class="inputButton" type="button" value="启 用" />
    <input class="inputButton" type="button" value="禁 用" />
    </div>
<div class="clear"></div>
<!--查询条件end-->

<!--列表样式---表格----开始-->
<div class="list">
<table width="100%" border="0" cellpadding="0" cellspacing="0">
<thead>
  <tr>
  	<th width="30">反选</th>
    <th width="60">代码</th>
    <th>名称</th>
    <th width="80">状态</th>
    <th width="100">操作</th>
  </tr>
  </thead>
  <tbody>
  <tr>
  	<td align="center"><input type="checkbox" name="1" /></td>
    <td align="center">101</td>
    <td align="center">男</td>
    <td align="center">启用</td>
    <td align="center"><a href="dictManagerEdit.jsp">修改</a> 删除</td>
  </tr>
  <tr>
  	<td align="center"><input type="checkbox" name="1" /></td>
    <td align="center">102</td>
    <td align="center">女</td>
    <td align="center">启用</td>
    <td align="center"><a href="dictManagerEdit.jsp">修改</a> 删除</td>
  </tr>
  </tbody>
  <tfoot>
  <tr>
    <td colspan="5"><span> 首页 上一页 下一页 尾页 <input type="text"
									class="inputText inputPage" value="1" /> <input
									class="inputButton inputButtonS" type="button" value="GO" />
							</span></td>
  </tr>
  </tfoot>
</table>
</div>
<!--列表样式---表格----end-->

  </body>
</html>
