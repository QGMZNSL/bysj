<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>考生综合查询</title>
		<link href="<%=request.getContextPath()%>/common/style/style.css"
			rel="stylesheet" type="text/css" />
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/common/js/jquery-1.3.2.min.js"></script>
		<script type="text/javascript">
		function delTableRow( currObj) {
			if ( confirm("删除查询条件？")) {
				$(currObj.parentNode.parentNode).remove();
			}
		}
		
		$(document).ready( function() {
			$('#addAline').click( function() {
				var trLine = '<tr>'
							+ '<td align="center">'
							+ '<select class="inputText inputTextS">'
							+ '<option></option>'
							+ '<option>'
							+ '（'
							+ '</option>'
							+ '</select>'
							+ '</td>'
							+ '<td align="center">'
							+ '<select class="inputText inputTextM">'
							+ '<option>'
							+ '文化层次'
							+ '</option>'
							+ '</select>'
							+ '</td>'
							+ '<td align="center">'
							+ '<select class="inputText inputTextM">'
							+ '<option>'
							+ '大于等于'
							+ '</option>'
							+ '</select>'
							+ '</td>'
							+ '<td align="center">'
							+ '<select class="inputText inputTextM">'
							+ '<option>'
							+ '高中'
							+ '</option>'
							+ '</select>'
							+ '</td>'
							+ '<td align="center">'
							+ '<select class="inputText inputTextS">'
							+ '<option></option>'
							+ '<option>'
							+ '）'
							+ '</option>'
							+ '</select>'
							+ '</td>'
							+ '<td align="center">'
							+ '<select class="inputText inputTextS">'
							+ '<option>'
							+ '并且'
							+ '</option>'
							+ '<option>'
							+ '或者'
							+ '</option>'
							+ '</select>'
							+ '</td>'
							+ '<td align="center">'
							+ '<input class="inputButton" onclick="delTableRow(this);" type="button" value="删　除" />'
							+ '</td>'
							+ '</tr>';
				$('#conditionTable').append(trLine);
			});
		})
		</script>
	</head>

	<body>
		<!--当前位置---开始-->
		<div class="dqwz">
			<span>帮助</span>日常维护>>日常管理>>考生综合查询
		</div>
		<!--查询条件开始-->

		<div class="clear"></div>
		<!--查询条件end-->
		<!--列表样式---表格----开始-->
		<div class="list">
			<table width="100%" border="0" cellpadding="0" cellspacing="0" id="conditionTable">
				<caption>
					查询条件列表
				</caption>
				<thead>
					<tr>
						<th>
							括号
						</th>
						<th>
							列
						</th>
						<th>
							判断
						</th>
						<th>
							值
						</th>
						<th>
							括号
						</th>
						<th>
							并且/或者
						</th>
						<th>
							操作
						</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td align="center">
							<select class="inputText inputTextS">
								<option></option>
								<option>
									（
								</option>
							</select>
						</td>
						<td align="center">
							<select class="inputText inputTextM">
								<option>
									报考市区
								</option>
							</select>
						</td>
						<td align="center">
							<select class="inputText inputTextM">
								<option>
									等于
								</option>
							</select>
						</td>
						<td align="center">
							<select class="inputText inputTextM">
								<option>
									榆林市
								</option>
							</select>
						</td>
						<td align="center">
							<select class="inputText inputTextS">
								<option></option>
								<option>
									）
								</option>
							</select>
						</td>
						<td align="center">
							<select class="inputText inputTextS">
								<option>
									并且
								</option>
								<option>
									或者
								</option>
							</select>
						</td>
						<td align="center">
							<input class="inputButton" disabled="disabled" type="button"
								value="删　除" />
						</td>
					</tr>
					<tr>
						<td align="center">
							<select class="inputText inputTextS">
								<option></option>
								<option>
									（
								</option>
							</select>
						</td>
						<td align="center">
							<select class="inputText inputTextM">
								<option>
									文化层次
								</option>
							</select>
						</td>
						<td align="center">
							<select class="inputText inputTextM">
								<option>
									大于等于
								</option>
							</select>
						</td>
						<td align="center">
							<select class="inputText inputTextM">
								<option>
									高中
								</option>
							</select>
						</td>
						<td align="center">
							<select class="inputText inputTextS">
								<option></option>
								<option>
									）
								</option>
							</select>
						</td>
						<td align="center">
							<select class="inputText inputTextS">
								<option>
									并且
								</option>
								<option>
									或者
								</option>
							</select>
						</td>
						<td align="center">
							<input class="inputButton" onclick="delTableRow( this);" type="button" value="删　除" />
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="button">
			<input class="inputButton inputButtonL" type="button" value="增加查询字段"
				id="addAline" />
			<input class="inputButton" type="button" value="查　询" />
			<input class="inputButton" type="button" value="导　出" />
		</div>
		<!--列表样式---表格----end-->
		<!--列表样式---表格----开始-->
		<div class="list">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<thead>
					<tr>
						<th>
							序号
						</th>
						<th>
							准考证号
						</th>
						<th>
							姓名
						</th>
						<th>
							证件号码
						</th>
						<th>
							性别
						</th>
						
						<th>
							报考市区
						</th>
						<th>
							报考县区
						</th>
						<th>
							报考层次
						</th>
						<th>
							报考专业
						</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td align="center">
							1
						</td>
						<td align="center">
							<a target="_blank"
								href="/ZK_CORE/manager/commoninfo/studentInfo.jsp">010112345678</a>
						</td>
						<td align="center">
							蒋丽
						</td>
						<td align="center">
							31001245678912345
						</td>
						<td align="center">
							女
						</td>
						<td align="center">
							延安市
						</td>
						<td align="center">
							宝塔区
						</td>
						<td align="center">
							大专
						</td>
						<td align="center">
							01011-计算机应用基础
						</td>
					</tr>
					<tr>
						<td align="center">
							2
						</td>
						<td align="center">
							<a target="_blank"
								href="/ZK_CORE/manager/commoninfo/studentInfo.jsp">010112345679</a>
						</td>
						<td align="center">
							卓俊
						</td>
						<td align="center">
							31001245678912346
						</td>
						<td align="center">
							男
						</td>
						<td align="center">
							汉中市
						</td>
						<td align="center">
							汉台区
						</td>
						<td align="center">
							本科
						</td>
						<td align="center">
							01022-大学英语（一）
						</td>
					</tr>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="9">
							<span>首页 上一页 下一页 尾页<input type="text"
									class="inputText inputPage" value="1" /> <input type="button"
									class="inputButton inputButtonS" value="Go" /> </span>
						</td>
					</tr>
				</tfoot>
			</table>
		</div>
		<!--列表样式---表格----end-->
	</body>
</html>
