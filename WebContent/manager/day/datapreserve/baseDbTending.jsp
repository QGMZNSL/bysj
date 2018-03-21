<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>数据库维护</title>
		<link href="<%=request.getContextPath()%>/common/style/style.css"
			rel="stylesheet" type="text/css" />
		<link href="<%=request.getContextPath()%>/common/js/ui.datepicker.css"
			rel="stylesheet" type="text/css" />
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/common/js/jquery-1.3.2.min.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/common/js/ui.datepicker-zh-CN.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/common/js/ui.datepicker.js"></script>


		<script type="text/javascript">
		$(document).ready( function() {
			$("#startDate").datepicker($.extend({ dateFormat: 'yyyy-MM-dd', showOn: 'both', buttonImage: '<%=request.getContextPath()%>/common/js/calendarNull.gif', buttonImageOnly: true},$.datepicker.regional['zh-CN']));
			$("#endDate").datepicker($.extend({ dateFormat: 'yyyy-MM-dd', showOn: 'both', buttonImage: '<%=request.getContextPath()%>/common/js/calendarNull.gif', buttonImageOnly: true},$.datepicker.regional['zh-CN']));
		});
		
		function showPwd( )
		{    
			document.getElementById("pwd").style.display="block";	 
		}
		function closePwd( )
		{    
			document.getElementById("pwd").style.display="none";	 
		}
		</script>
		
		<STYLE type="text/css">
		/*如果去掉定位的样式，将出现内容动态隐藏显示的效果*/
		#pwd {
			position: absolute;
			left: 200px;
			top: 110px;
			width: 320px;
			height: 57px;
			z-index: 2;
		}
		</STYLE>
	</head>

	<body>
		<!--当前位置---开始-->
		<div class="dqwz">
			<span>帮助</span>日常管理>>数据维护>>数据库备份
		</div>
		<!--当前位置---结束-->
		<!--查询条件开始-->
		<div class="tjcx">
			<form method="post" name="form1">
				<dl>
					<dt>
						备份时间：
					</dt>
					<dd>
						<input class="inputText inputTextS" id="startDate" type="text" />
						至
						<input class="inputText inputTextS" id="endDate" type="text" />
					</dd>
				</dl>
			</form>
		</div>
		<div class="button">
			<input class="inputButton" type="button" value="查 询" />
			<input class="inputButton" type="button" value="备 份" onClick="showPwd()" />
		</div>
		<div class="clear"></div>
		<!--查询条件end-->
		<!--列表样式---表格----开始-->
		<div class="list">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<thead>
					<tr>
						<th width="30px">
							序号
						</th>
						<th>
							备份文件名称
						</th>
						<th width="150px">
							备份时间
						</th>
						<th width="80">
							备份人
						</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td align="center">
							1
						</td>
						<td>&nbsp;
							
					  </td>
						<td>&nbsp;
							
					  </td>
						<td>&nbsp;
							
					  </td>
					</tr>
					<tr>
						<td align="center">&nbsp;
							
					  </td>
						<td>&nbsp;
							
					  </td>
						<td>&nbsp;
							
					  </td>
						<td>&nbsp;
							
					  </td>
					</tr>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="4">
							<span> 首页 上一页 下一页 尾页 <input type="text"
									class="inputText inputPage" value="1" /> <input
									class="inputButton inputButtonS" type="button" value="GO" />
							</span>
						</td>
					</tr>
				</tfoot>
			</table>
		</div>
		<!--列表样式---表格----end-->

			<!--层---开始-->	
<div class="list layer" id="pwd" style="display: none">					
 <table border="0" cellpadding="0" cellspacing="0" width="100%"  >
    <tbody>
    <tr>
        <td width="119" align="right">文件存放路径：</td>
        <td width="248"><input type="text" /></td>            
    </tr>
     <tr>
        <td align="right">操作人密码：</td>      
        <td><input type="text" /></td>     
    </tr>
     <tr>   
        <td  colspan="2" align="center">
			<input class="inputButton" type="button" onClick="closePwd()"value="确定" />
	    </td>          
    </tr>
    </tbody>   
</table> 
  </div>	
<!--层---结束-->
	</body>
</html>
