<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>准考证合并登记</title>
<link href="<%=request.getContextPath() %>/common/style/style.css" rel="stylesheet" type="text/css" />
</head>
  
  <body>
  <!--当前位置---开始-->
<div class="dqwz">
	<span>帮助</span>日常维护>>日常管理>>准考证合并登记
</div>
<!--当前位置---结束-->
<!--查询条件开始-->
<div class="tjcx">
<form method="post" name="form1"> 
	<dl>
		<dt>市区：</dt>
		<dd><select class="inputText inputTextM">
    				<option>西安市</option>
                    <option>汉中市</option>
                    </select></dd>
	</dl>
	<dl>
		<dt>准考证号：</dt>
		<dd><input class="inputText inputTextM" type="text" /></dd>
	</dl>
	<dl>
		<dt>证件号码：</dt>
		<dd><input class="inputText inputTextM" type="text" /></dd>
	</dl>
	<dl>
		<dt>状态：</dt>
		<dd><select class="inputText inputTextM">
    				<option>全部</option>
    				<option>未登记</option>
                    <option>已登记</option>
                    </select></dd>
	</dl>
</form>    
</div>
    <div class="button"><input class="inputButton" type="button" value="查 询" /></div>
<div class="clear"></div>
<!--查询条件end-->

<!--列表样式---表格----开始-->
<div class="list">
<table width="100%" border="0" cellpadding="0" cellspacing="0">
<caption></caption>
<thead>
  <tr>
  	<th align="center">序号</th>
    <th>准考证号</th>
    <th>姓名</th>
   　<th>证件号码</th>
   　<th>性别</th>
    <th>被合并准考证号</th>
    <th>不一致信息列表</th>
    <th>状态</th>
    <th>操作</th>
   
  </tr>
  </thead>
  <tbody>
  <tr>
  	<td width="30" align="center">1</td>
    <td align="center"><a target="_blank" href="ticketsMergeAuditShow.jsp">610452012410</a></td>
    <td align="center">杨森</td>   
    <td align="center">610130198205060228</td>
    <td align="center">男</td> 
    <td align="center">613201550</td>
    <td align="center">姓名不一致</td>
    <td align="center">未登记</td>
    <td align="center"><a href="materialsRegistration.jsp">证明材料登记</a></td>
  </tr>
  <tr>
  	<td width="30" align="center">
							2
						</td>
    <td align="center">&nbsp;</td>
    <td align="center">&nbsp;</td>
    <td align="center">&nbsp;</td>
    <td align="center">&nbsp;</td>
    <td align="center">&nbsp;</td>
    <td align="center">&nbsp;</td>
    <td align="center">&nbsp;</td>
    <td align="center">&nbsp;</td>
  </tr>
  </tbody>
  <tfoot>
  <tr>
    <td colspan="9"><span> 首页 上一页 下一页 尾页 <input type="text"
									class="inputText inputPage" value="1" /> <input
									class="inputButton inputButtonS" type="button" value="GO" />
					</span>
</td>
  </tr>
  </tfoot>
</table>
</div>
<!--列表样式---表格----end-->
  </body>
</html>
