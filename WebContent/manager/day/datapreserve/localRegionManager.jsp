<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>本省市区管理</title>
<link href="<%=request.getContextPath() %>/common/style/style.css" rel="stylesheet" type="text/css" />
</head>

<body>
<!--当前位置---开始-->
<div class="dqwz">
	<span>帮助</span>日常管理>>数据维护>>市县维护
</div>
<!--当前位置---结束-->
   
   <!--查询条件开始-->
<div class="tjcx">
<form method="post" name="form1"> 
	<dl>
		<dt>市区名称：</dt>
		<dd><select class="inputText inputTextM">
    				<option>--请选择--</option>
                    <option>选项</option>
                    <option>选项</option>
                    <option>选项</option>
                    <option>选项</option>
    			</select></dd>
	</dl>
</form>    
</div>
    <div class="button"> 
    <input class="inputButton" type="button" value="添 加" onclick="location.href='localRegionManagerAdd.jsp'"/>
    </div>
<div class="clear"></div>
<!--查询条件end-->
   
   <!--列表样式---表格----开始-->
<div class="list">
<table width="100%" border="0" cellpadding="0" cellspacing="0">
<thead>
  <tr>
    <th>编号</th>
    <th>名称</th>
    <th>操作</th>
  </tr>
  </thead>
  <tbody>
  <tr>
    <td align="center">610100</td>
    <td align="center">西安市</td>
    <td align="center"><a href="localRegionManagerEdit.jsp">修改</a> 删除</td>
  </tr>
   <tr>
    <td align="center">610200</td>
    <td align="center">铜川市</td>
    <td align="center">修改 删除</td>
  </tr>
  </tbody>
  <tfoot>
  <tr>
    <td colspan="3"><span> 首页 上一页 下一页 尾页 <input type="text"
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
