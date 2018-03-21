<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>考生成绩作废</title>
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
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/common/js/clientfuns.js"></script>
		<script type="text/javascript">
				function subform(str){
					if(checkBoxs("psIds")==false){
						alert("请至少选择一条记录！");
						return ;
					}
					if(str=="1"){//作废成绩
						var disabledReason=$("#sdisabledReason").val();
						if(isNull(disabledReason)){
							alert("请填写作废原因！");
							return ;
						}
						if(confirm("您确认此次操作吗？")){											
							$("#disabledReason").val(disabledReason);
							$("#sform").attr("action","ssd_disScore.do");
							$("#sform").submit();
						}
					}else if(str=="2"){//恢复成绩
						if(confirm("您确认此次操作吗？")){
							$("#sform").attr("action","ssd_reScore.do");
							$("#sform").submit();
						}
					}
				}
				
				function inline(id){
					document.getElementById(id).style.display="inline";
				}
				
				function none(id){
					document.getElementById(id).style.display="none";
				}
		</script>
		<c:if test="${not empty info}">
			<script type="text/javascript">
					alert("${info}");
			</script>
		</c:if>
	</head>

	<body>
		<!--导航---start-->
		<div class="dqwz">
			<div style="margin-right: 10px;">
				<span style="cursor: pointer;" id="helpSpan"
					onclick="javascript:showHelpDiv('Z51005');">- 帮助 -</span>
				<span class="pageCode">No.Z51005</span>日常维护 > 日常管理 > 违纪考生处理 
			</div>
		</div>
		<jsp:include page="/help/helpJs.jsp" />
		<iframe name="ifm" id="helpIfm" scrolling="no" frameborder="0"
			width="100%" style="display: none;"
			onload="this.height=this.contentWindow.document.body.scrollHeight+13"></iframe>
		<!--导航---end-->

		<!--查询条件开始-->
		<form method="post" name="setForm" action="ssd_select.do">
			<div class="tjcx">
				<dl>
					<dt>
						准考证号：
					</dt>
					<dd>
						<input class="inputText inputTextM" type="text"
							name="studExamCode" value="${studExamCode}" />
					</dd>
				</dl>
				<dl>
					<dt>
						考试名称：
					</dt>
					<dd>
						<select class="inputText inputTextM" name="examCode">
							<option value="">
								--请选择--
							</option>
							<c:forEach var="eBean" items="${examList}">
								<option value="${eBean.examinationCode}"
									<c:if test="${eBean.examinationCode==examCode}">selected</c:if>>
									${eBean.examinationYear}年${eBean.examinationMonth}月自学考试
								</option>
							</c:forEach>
						</select>
					</dd>
				</dl>
				<dl>
					<dt>
						状态：
					</dt>
					<dd>
						<select class="inputText inputTextM" name="status">
							<option value="0" <c:if test="${status=='0'}">selected</c:if>>
								未作废
							</option>
							<option value="1" <c:if test="${status=='1'}">selected</c:if>>
								已作废
							</option>
						</select>
					</dd>
				</dl>
			</div>
			<div class="button">
				<input class="inputButton" type="submit" value="查 询" />
			</div>
		</form>
		<div class="clear"></div>
		<!--查询条件end-->

		<!--列表样式---表格----开始-->
		<c:if test="${not empty dataList}">
			<div class="list">
				<form method="post" id="sform">
					<input type="hidden" name="examCode" value="${examCode }" />
					<input type="hidden" name="studExamCode" value="${ studExamCode}" />
					<input type="hidden" name="studName" value="${studName }" />
					<input type="hidden" name="studIdnum" value="${studIdnum }" />
					<input type="hidden" name="status" value="${status}" />
					<input type="hidden" name="cityCode" value="${cityCode}" />
					<input type="hidden" name="syllCode" value="${syllCode}" />
					<input type="hidden" name="disabledReason" id="disabledReason" />
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<thead>
							<tr>
								<th align="center">
									<a href="#" onclick="cboxCheckOpposite('psIds')">反选</a>
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
									课程名称
								</th>
								<th>
									成绩
								</th>
								<th>
									状态
								</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="bean" items="${dataList}">
								<tr>
									<td width="30" align="center">
										<input type="checkbox" name="psIds" value="${bean.studPassId}" />
									</td>
									<td align="center">
										<a target="_blank"
											href="/ZK_CORE/studinfo_show.do?studExamCode=${bean.studExamCode}">${bean.studExamCode}</a>
									</td>
									<td align="center">
										${bean.studName}
									</td>
									<td align="center">
										${bean.studIdnum}&nbsp;
									</td>
									<td align="center">
										${bean.syllabusName}&nbsp;
									</td>
									<td align="center">
										${bean.studScore}&nbsp;
									</td>
									<td align="center">
										<c:if test="${bean.studScoreDisabledStatus=='0'}">未作废</c:if>
										<c:if test="${bean.studScoreDisabledStatus=='1'}">已作废</c:if>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</form>
				<table width="100%" border="0" cellpadding="0">
					<tfoot>
						<tr>
							<td colspan="7">
								<span>${page.pageInfo } </span> 作废原因：
								<input type="text" class="inputText" id="sdisabledReason" />
								<input type="button" class="inputButton" value="成绩作废"
									onclick="subform('1')"
									<c:if test="${status=='1'}">disabled</c:if> />
								<input type="button" class="inputButton" value="恢复成绩"
									onclick="subform('2')"
									<c:if test="${status=='0'}">disabled</c:if> />
							</td>
						</tr>
					</tfoot>
				</table>

			</div>
		</c:if>
		<!--列表样式---表格----end-->
	</body>

</html>

