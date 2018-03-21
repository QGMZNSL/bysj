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
	<span>帮助</span>日常管理>>数据维护>>违纪管理
</div>
<!--当前位置---结束-->
 
 <!--查询条件开始-->
 <div class="button">
 	<input class="inputButton" type="button" value="添加违纪" onclick="location.href='disciplineManagerAdd.jsp';" />
 </div>
 <div class="clear"></div>
 
 <!--查询条件开始-->
 
 <!--列表样式---表格----开始-->
<div class="list">
<table width="100%" border="0" cellpadding="0" cellspacing="0">
<thead>
  <tr>
    <th width="30px">序号</th>
    <th>违纪情况</th>
    <th>违纪处理</th>
    <th width="80">操作</th>
  </tr>
  </thead>
  <tbody>
  <tr>
  	<td align="center">1</td>
    <td>001-书写潦草</td>
    <td>扣除所答科目20%分数</td>
    <td align="center"><a href="disciplineManagerEdit.jsp">修改</a>删除</td>
  </tr>
  <tr>
  	<td align="center">2</td>
    <td>002-另附纸答题</td>
    <td>扣除所答科目20%分数</td>
    <td align="center"><a href="disciplineManagerEdit.jsp">修改</a>删除</td>
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
