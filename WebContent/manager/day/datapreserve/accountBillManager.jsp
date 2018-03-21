<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>订单综合管理</title>
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
		
		<!--javascript---开始-->
		<SCRIPT language="javascript">
		function showModify( )
		{    
			document.getElementById("modify").style.display="block";	 
		}
		function CloseModify( )
		{    
			document.getElementById("modify").style.display="none";	 
		}
		$(document).ready( function() {
			$("#payTime").datepicker($.extend({ dateFormat: 'yyyy-MM-dd', showOn: 'both', buttonImage: '<%=request.getContextPath()%>/common/js/calendarNull.gif', buttonImageOnly: true},$.datepicker.regional['zh-CN']));
		});
		</SCRIPT>
		<!--javascript---结束-->
	</head>

	<body>
		<!--当前位置---开始-->
		<div class="dqwz">
			<span>帮助</span>日常管理>>数据维护>>订单综合管理
		</div>
		<!--当前位置---结束-->

		<!--查询条件开始-->
		<div class="tjcx">
			<form method="post" name="form1">
				<dl>
					<dt>
						考试年月：
					</dt>
					<dd>
						<select class="inputText inputTextS">
							<option>
								2011-11
							</option>
							<option>
								选项
							</option>
						</select>
					</dd>
				</dl>				
				<dl>
					<dt>
						市区名称：
					</dt>
					<dd>
						<select class="inputText inputTextM">
							<option>
								610100-西安市
							</option>
							<option>
								选项
							</option>
							<option>
								选项
							</option>
							<option>
								选项
							</option>
							<option>
								选项
							</option>
						</select>
					</dd>
				</dl>
				<dl>
					<dt>
						考区名称：
					</dt>
					<dd>
						<select class="inputText inputTextM">
							<option>
								0101-碑林区
							</option>
							<option>
								选项
							</option>
							<option>
								选项
							</option>
							<option>
								选项
							</option>
							<option>
								选项
							</option>
						</select>
					</dd>
				</dl>
						
				<dl>
						<dt>
							课程名称：
						</dt>
						<dd>
							<input class="inputText inputTextM" type="text" id="subjectCode"
								readonly="readonly" value="点击选择课程" name="subjectCode"
								style="cursor: hand;"
								onclick="window.open('<%=request.getContextPath() %>/examrelated/sctSubject.jsp','_blank', 'top=150,left=300,height=600,width=500,resizable=0,toolbar=0,menubar=0,location=0');"/>
						</dd>
					</dl>	
				<dl>
					<dt>
						准考证号：
					</dt>
					<dd>
						<input class="inputText inputTextM" type="text" />
					</dd>
				</dl>
				<dl>
					<dt>
						身份证号：
					</dt>
					<dd>
						<input class="inputText inputTextM" type="text" />
					</dd>
				</dl>
				<dl>
					<dt>
						交费时间：
					</dt>
					<dd>
						<input class="inputText inputTextM" type="text" id="payTime" />
					</dd>
				</dl>
			</form>
		</div>
		<div class="button">
			<input class="inputButton" type="button" value="查 询" />
			<input class="inputButton" type="button" value="批量修改"
				onClick="showModify()" />
		</div>
		<div class="clear"></div>
		<!--查询条件end-->

		<!--列表样式---表格----开始-->
		<div class="list">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<thead>
					<tr>
						<th width="30px">
							反选
						</th>
						<th>
							准考证号
						</th>
						<th>
							姓名
						</th>
						<th>
							课程名称
						</th>
						<th>
							报考费
						</th>
						<th>
							是否交费
						</th>
						<th>
							交费时间
						</th>
						<th width="80">
							操作
						</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td align="center">
							<input type="checkbox" name="1" />
						</td>
						<td align="center">&nbsp;						</td>
						<td>&nbsp;
							
					  </td>
						<td>&nbsp;
							
					  </td>
					  <td align="center">&nbsp;					  </td>
						<td align="center">&nbsp;					  </td>
						<td align="center">&nbsp;					  </td>
						<td align="center">&nbsp;					  </td>
					</tr>
					<tr>
						<td align="center">
							<input type="checkbox" name="1" />
						</td>
						<td align="center">&nbsp;						</td>
						<td>&nbsp;
							
					  </td>
						<td>&nbsp;
							
					  </td>
					  <td align="center">&nbsp;					  </td>
						<td align="center">&nbsp;					  </td>
						<td align="center">&nbsp;					  </td>
						<td align="center">&nbsp;					  </td>
					</tr>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="8">
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
		<div id="modify" style="display: none">
			<form method="post" name="form1">
				<div>
					是否交费：
					<input type="radio" />
					是
					<input type="radio" />
					否
				</div>
				<div>
					<input type="button" value="保存" />
					<input type="button" value="取消" onClick="CloseModify()" />
				</div>
			</form>
		</div>
		<!--层---结束-->

	</body>
</html>
