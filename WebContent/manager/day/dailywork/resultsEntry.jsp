<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>补办成绩证明/学历证明</title>
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
			$("#birthday").datepicker($.extend({ dateFormat: 'yyyy-MM-dd', showOn: 'both', buttonImage: '<%=request.getContextPath()%>/common/js/calendarNull.gif', buttonImageOnly: true},$.datepicker.regional['zh-CN']));
		});
		</script>
	</head>

	<body>
		<!--当前位置---开始-->
		<div class="dqwz">
			<span>帮助</span>日常维护>>日常管理>>补办成绩证明/学历证明
		</div>
		<!--当前位置---结束-->

		<!--编辑页面----开始-->
		<div class="infoedit">
				　<dl>
					<dt>准考证号：</dt>
					<dd>6201201552</dd>
				</dl>　　　　
		　　　　　<dl>
					<dt>证件类型：</dt>
					<dd><select class="inputText inputTextM">
						<option>--- 请选择 ---</option>
						<option>身份证</option>
						<option>士官证</option>
					</select></dd>
				</dl>
				<dl>
					<dt>证件号码：</dt>
					<dd><input class="inputText" type="text" value="610120198010120556" /></dd>
				</dl>		
				<dl>
					<dt>
						姓名：
					</dt>
					<dd>
						<input class="inputText" type="text" name="" value="杨森" />
						<span>(必须填写)</span>
					</dd>
				</dl>
				<dl>
					<dt>
						性别：
					</dt>
					<dd>
						<input name="1" type="radio" value="1" checked="checked" />
						男
						<input name="2" type="radio" value="2" />
						女
						<span>(必须选择)</span>
					</dd>
				</dl>
				<dl>
					<dt>
						出生日期：
					</dt>
					<dd>
						<input class="inputText" id="birthday" type="text"
							value="1980-10-12" />
					</dd>
				</dl>
				<dl>
					<dt>
						民族：
					</dt>
					<dd>
						<select class="inputText">
							<option>
								汉族
							</option>
							<option>
								朝鲜族
							</option>
						</select>
					</dd>
				</dl>
				<div class="photo">
				<img src="" height="150" width="120" />
				<span><a href='#'>照片采集</a> </span>
			</div>
 	<div class="clear"></div>

		<!--编辑页面----end-->
		<!--列表样式---表格----开始-->
		<div class="list">
			<table width="100%" border="0" cellpadding="0" cellspacing="0" style="text-align:center">
				<caption></caption>
				<thead>
					<tr>
						<th>
							课程代码
						</th>
						<th>
							课程名称
						</th>
						<th>
							学分
						</th>
						<th>
							成绩
						</th>
						<th>
							合格时间
						</th>
						<th>
							合格原因
						</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>
							<input class="inputText inputTextM" type="text" />
						</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>
							<input class="inputText inputTextM" type="text" />
						</td>
						<td>
							<input class="inputText inputTextM" id="passDate1" type="text" />
						</td>
						<td>
							<input class="inputText inputTextM" type="text" />
						</td>
					</tr>
					<tr>
						<td>
							<input class="inputText inputTextM" type="text" />
						</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>
							<input class="inputText inputTextM" type="text" />
						</td>
						<td>
							<input class="inputText inputTextM" id="passDate2" type="text" />
						</td>
						<td>
							<input class="inputText inputTextM" type="text" />
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="button">
			<input class="inputButton" type="button" value="保 存" />
							<input class="inputButton" type="button" value="返 回"
								onclick="location.href='certifySupply.jsp'" />
</div>
		<!--列表样式---表格----end-->
	</body>
</html>

