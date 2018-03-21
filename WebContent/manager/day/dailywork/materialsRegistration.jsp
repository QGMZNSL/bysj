<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>准考证合并证明材料登记</title>
<link href="<%=request.getContextPath() %>/common/style/style.css" rel="stylesheet" type="text/css" />
</head>
  
  <body>
  <!--当前位置---开始-->
<div class="dqwz">
	<span>帮助</span>日常维护>>日常管理>>准考证合并证明材料登记
</div>
<!--当前位置---结束-->
<!--编辑页面----开始-->
		<form action="" method="post" name="setForm">
			<div class="infoedit">
				<!--查询条件end-->
				<h1>
					准考证合并证明材料登记
				</h1>
				<dl>
					<dt>
						修改原因：
					</dt>
					<dd>
						<input type="text" class="inputText"></input>
					</dd>
				</dl>
				<dl>
					<dt>
						证明材料：
					</dt>
					<dd>
						<input type="text" class="inputText"></input>
					</dd>
				</dl>
				<div class="clear"></div>
			</div>
			<div class="button">
				<input class="inputButton" type="button" value="保 存" />
				<input class="inputButton" type="button" value="返 回"
					onclick="history.back();" />
			</div>
		</form>
		<!--编辑页面----end-->
  </body>
</html>
