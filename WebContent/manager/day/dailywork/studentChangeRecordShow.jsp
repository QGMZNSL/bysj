<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>考生信息变更记录查看</title>
		<link href="<%=request.getContextPath()%>/common/style/style.css"
			rel="stylesheet" type="text/css" />
	</head>

	<body>
		<!--导航---start-->
		<div class="dqwz">
			<span>帮助</span><span class="pageCode">功能编号:${ currFunctionId}</span>${currNavigation}
		</div>
		<!--导航---end-->
		<div class="xsxx">
			<table border="0" cellpadding="0" cellspacing="0" width="100%">
				<caption>
					陕西省自学考试考生基本信息变更记录表
				</caption>
				<tbody>
					<tr>
						<th>
							姓名：
						</th>
						<td>
							${stud.studName}&nbsp;
						</td>
						<th>
							性别：
						</th>
						<td>
							${stud.genderName}&nbsp;
						</td>
					</tr>
					<tr>
						<th>
							证件号码：
						</th>
						<td>
							${stud.studIdnum}（${stud.studIdnoTypeName}）&nbsp;
						</td>
						<th>
							准考证号：
						</th>
						<td>
							${stud.studExamCode}&nbsp;
						</td>
					</tr>
					<tr>
						<th>
							报考专业：
						</th>
						<td>
							${stud.proCode}|${stud.proName}&nbsp;
						</td>
						<th>
							报考层次：
						</th>
						<td>
							${stud.levelName}&nbsp;
						</td>
					</tr>
					<tr>
						<th>
							出生日期：
						</th>
						<td>
							${stud.studBirthday}&nbsp;
						</td>
						<th>
							民族：
						</th>
						<td>
							${stud.studFolkName}&nbsp;
						</td>
					</tr>
					<tr>
						<th>
							政治面貌：
						</th>
						<td>
							${stud.studPoliticsName}&nbsp;
						</td>
						<th>
							文化程度：
						</th>
						<td>
							${stud.studSchoolAgeName}&nbsp;
						</td>
					</tr>
					<tr>
						<th>
							户籍：
						</th>
						<td>
							${stud.studHukouCharacterName}&nbsp;
						</td>
						<th>
							户籍所在地：
						</th>
						<td>
							${stud.studHukouLocationName}&nbsp;
						</td>
					</tr>
					<tr>
						<th>
							电子邮箱：
						</th>
						<td>
							${stud.studEmail}&nbsp;
						</td>
						<th>
							职业：
						</th>
						<td>
							${stud.studOccupationName}&nbsp;
						</td>	
					</tr>
					<tr>
					　　<th>
							联系电话：
						</th>
						<td>
							${stud.studTelephone}&nbsp;
						</td>
						<th>
							移动电话：
						</th>
						<td>
							${stud.studMobilePhone}&nbsp;
						</td>
					</tr>
					<tr>
						<th>
							通讯地址：
						</th>
						<td>
							${stud.studPostalAddress}&nbsp;
						</td>
						<th>
							邮政编码：
						</th>
						<td>
							${stud.studPostalCode}&nbsp;
						</td>
					</tr>
					<tr>
						<th>
							报考市区：
						</th>
						<td>
							${stud.cityName}&nbsp;
						</td>
						<th>
							报考考区：
						</th>
						<td>
							${stud.examAreaName}&nbsp;
						</td>
					</tr>
					<tr>
						<th>
							考生类别 ：
						</th>
						<td>
							<c:if test="${stud.studTypeCode=='0'}">
								社会考生
							</c:if>
							<c:if test="${stud.studTypeCode=='1'}">
								助学单位考生
							</c:if>&nbsp;
						</td>
						<th>
							助学单位：
						</th>
						<td>
						<c:if test="${stud.studTypeCode=='1'}">
							${stud.stipendUnitCode}-${stud.stipendUnitName}
						</c:if>&nbsp;
						</td>
					</tr>
				</tbody>
			</table>
		</div>

		<!--显示详细页面----结束-->
		<!--列表样式---表格----开始-->
		<div class="list">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<thead>
					<tr>
						<th colspan="4" align="left">
							基本信息变更记录：
						</th>
					</tr>
					<tr>
						<th>
							信息变更时间
						</th>
						<th>
							信息变更字段
						</th>
						<th>
							变更前信息
						</th>
						<th>
							变更后信息
						</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach var="bean" items="${dataList}">
					<tr>
						<td align="center">
							${ bean.changeApplyTime}
						</td>
						<td align="center">
							${ bean.changeTypeName}
						</td>
						<td align="center">
							${ bean.oldInfo}
						</td>
						<td align="center">
							${ bean.newInfo}
						</td>
					</tr>
				</c:forEach>
				</tbody>
				<tfoot>
				</tfoot>
			</table>
		</div>
		<!--列表样式---表格----end-->
		<input type="button" value="关 闭" class="inputButton"
			onclick="window.close();" />
	</body>
</html>
