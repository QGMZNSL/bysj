<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>基础功能添加</title>
<link href="<%=request.getContextPath() %>/common/style/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
		
</script>
</head>

<body>
<!--当前位置---开始-->
<div class="dqwz">
	<span>帮助</span>日常管理>>数据维护>>基础功能添加
</div>
<!--当前位置---结束-->


<div class="infoedit">
	<dl>
		<dt>功能类型：</dt>
		<dd><input class="inputText" type="text" value="交费"/></dd>
	</dl>
	<dl>
		<dt>功能代码：</dt>
		<dd><input class="inputText" type="text"/></dd>
	</dl>	
	<dl>
		<dt>功能名称：</dt>
		<dd><input class="inputText" type="text"/></dd>
	</dl>
	<dl>
		<dt>市区名称：</dt>
		<dd><select class="inputText inputTextM">
    				<option>--请选择--</option>
                    <option>610100-西安市</option>
                    <option>选项</option>
    			</select>
    	</dd>
	</dl>
	<dl>
		<dt>&nbsp;</dt>
		<dd> 
		<div id="dishi"> 
		<!-- 全省市区  --> 	
		<table width="440" border="0" cellpadding="0" cellspacing="0">
          <tr>
            <td align="center"><input type="checkbox" checked="checked"/></td>
            <td align="center">西安市</td>
            <td align="center"><input type="checkbox" checked="checked"/></td>
            <td align="center"> 铜川市</td>
            <td align="center"><input type="checkbox" checked="checked"/></td>
            <td align="center"> 宝鸡市</td>
            <td align="center"><input type="checkbox" checked="checked"/></td>
            <td align="center"> 咸阳市</td>
          </tr>
          <tr>
            <td align="center"><input type="checkbox" checked="checked"/></td>
            <td align="center"> 渭南市</td>
            <td align="center"><input type="checkbox" checked="checked"/></td>
            <td align="center"> 榆林市</td>
            <td align="center"><input type="checkbox" checked="checked"/></td>
            <td align="center"> 延安市</td>
            <td align="center"><input type="checkbox" checked="checked"/></td>
            <td align="center"> 汉中市</td>
          </tr>
          <tr>
            <td align="center"><input type="checkbox" checked="checked"/></td>
            <td align="center"> 安康市</td>
            <td align="center"><input type="checkbox" checked="checked"/></td>
            <td align="center"> 商洛市</td>
            <td align="center"><input type="checkbox" checked="checked"/></td>
            <td align="center">杨凌市</td>
            <td align="center">&nbsp;</td>
            <td align="center">&nbsp;</td>
          </tr> 
      </table>
      </div> 
      <!-- 市区考区 -->
      <div id="quxian">  	
		<table width="440" border="0" cellpadding="0" cellspacing="0">
          <tr>
            <td align="center"><input type="checkbox" checked="checked"/></td>
            <td align="center">城六区</td>
            <td align="center"><input type="checkbox" checked="checked"/></td>
            <td align="center">临潼区</td>
            <td align="center"><input type="checkbox" checked="checked"/></td>
            <td align="center">长安区</td>
            <td align="center"><input type="checkbox" checked="checked"/></td>
            <td align="center">周至县</td>
          </tr>
          <tr>
            <td align="center"><input type="checkbox" checked="checked"/></td>
            <td align="center">户县</td>
            <td align="center"><input type="checkbox" checked="checked"/></td>
            <td align="center">蓝田县</td>
            <td align="center"><input type="checkbox" checked="checked"/></td>
            <td align="center">阎良区</td>
          </tr>
      </table>
      </div> 
	</dd>
	</dl>
	<dl>
	<dt>状态：</dt>
		<dd><input  type="radio" checked="checked"/>开 启
		<input  type="radio" />关 闭
		</dd>
	</dl>	
					<div class="clear"></div>
</div>
		   <div class="button">
				<input class="inputButton" type="button" value="保 存" />
				<input class="inputButton" type="button"
					onclick="location.href='baseFunctionManager.jsp';" value="返 回" />
			</div>



  </body>
</html>
