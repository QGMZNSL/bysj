<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="v" uri="http://nbf.river.org/vxds"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>职位部门管理</title>
		<link href="<%=request.getContextPath()%>/common/style/style.css"
			rel="stylesheet" type="text/css" />
		<style type="text/css">
.allTdWidth td {
	width: 160px;
}
</style>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/common/js/riverdefine.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/common/js/riverajax.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/common/js/jquery-1.3.2.min.js"></script>
		<script type="text/javascript">
		function sctAll( classId) {
			var chboxes = $('#' + classId + " :checkbox");
			for ( var i = 0; i < chboxes.length; i++) {
				if ( $(chboxes[i]).attr("checked") == true || $(chboxes[i]).attr("checked") == "checked") {
					$(chboxes[i]).attr("checked", false);
				} else {
					$(chboxes[i]).attr("checked", true);
				}
			}
		}
		
		// 添加设置/清空值
		function areaClear() {
			$('#positionCode').val("");
			$('#positionName').val("");
			$(':checkbox').attr("checked", false);
		}
		
		// 修改设置
		function setEditInfo( clickedObj) {
			// 获取修改需要显示的内容
			var hiddens = $(clickedObj).parent(".optCol").children(".minfo");
		
			// 获取初始化文本
			var pcode,pname,power;
			for ( var i = 0; i < hiddens.length; i++) {
				if ( $(hiddens[i]).attr("name") == "pname") { pname = $(hiddens[i]).val(); continue;}
				if ( $(hiddens[i]).attr("name") == "power") { power = $(hiddens[i]).val(); continue;}
				if ( $(hiddens[i]).attr("name") == "pcode") { pcode = $(hiddens[i]).val(); continue;}
			}
			
			// 初始化名称
			$('#positionCode').val( pcode);
			$('#positionName').val( pname);
			
			// 初始化多选框
			var chboxes = $(":checkbox");
			chboxes.attr("checked", false);
			var ptVal = 0;
			for ( var i = 0; i < chboxes.length; i++) {
				ptVal = power.substring($(chboxes[i]).val() - 1, $(chboxes[i]).val());
				if ( ptVal == 1) $(chboxes[i]).attr("checked", true);
			}
		}
		
		function delPstn( pstnCode) {
			if ( confirm("删除该职位将清除所有该职位用户的权限，确定进行该操作码？")) {
				var url = "<%=request.getContextPath()%>/day/datapreserve/pstMgr_delPosition.do?"
				url += "department.departmentCode=${ department.departmentCode}";
				url += "&position.positionCode=" + pstnCode;
				location.href = url;
			}
		}
		
		var addShowSign = false;
		var editShowSign = false;
			
		$(document).ready( function() {
			$('#showAddArea').click( function() {
				// 判断修改是否在显示
				if ( editShowSign) {
					$('#addArea').hide("fast");	
					editShowSign = false;
				}
			
				// 设值
				areaClear();
			
				// 控制显示
				if ( !addShowSign) {
					$('#positionSaveType').val("0");
					$('.showText').html("添加职位");
					$('#addArea').show("slow");
					addShowSign = true;
				} else {
					$('#positionSaveType').val("-1");
					$('#addArea').hide("slow");	
					$('.showText').html("");
					addShowSign = false;
				}
			});
		
			$('#hideAddArea').click( function() {
				// 设值
				areaClear();
				
				// 控制显示
				$('#addArea').hide("slow");	
				$('#positionSaveType').val("-1");
					$('.showText').html("");
				addShowSign = false;
			});
			
			$('.modify').click( function() {
				// 判断添加是否在显示
				if ( addShowSign) {
					$('#addArea').hide("fast");	
					addShowSign = false;
				}
			
				// 设值
				setEditInfo( this);
			
				// 控制显示
				if ( !editShowSign) {
					$('#positionSaveType').val("1");
					$('.showText').html("修改职位");
					$('#addArea').show("slow");
					editShowSign = true;
				} else {
					$('#positionSaveType').val("-1");
					$('.showText').html("");
					$('#addArea').hide("slow");	
					editShowSign = false;
				}
			});
		
			$('#addArea').hide();
		});
		</script>
	</head>

	<body>
		<!--导航---start-->
		<div class="dqwz">
			<div style="margin-right: 10px;">
				<span style="cursor: pointer;" id="helpSpan"
					onclick="javascript:showHelpDiv('Z53001');">- 帮助 -</span>
				<span class="pageCode">No.Z53001</span>${currNavigation}
			</div>
		</div>
		<jsp:include page="/help/helpJs.jsp" />
		<iframe name="ifm" id="helpIfm" scrolling="no" frameborder="0"
			width="100%" style="display: none;"
			onload="this.height=this.contentWindow.document.body.scrollHeight+13"></iframe>
		<!--导航---end-->

		<div class="list">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<caption>
					部门级别：
					<c:choose>
						<c:when test="${ department.departmentGrade == 1}">省级</c:when>
						<c:when test="${ department.departmentGrade == 2}">市级</c:when>
						<c:otherwise>&nbsp;</c:otherwise>
					</c:choose>
					，部门名称：${ department.departmentName}
				</caption>
				<thead>
					<tr>
						<th>
							序号
						</th>
						<th>
							职位名称
						</th>
						<th>
							设置状态
						</th>
						<th>
							操作
						</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${ positionList}" var="postition"
						varStatus="ctNum">
						<tr>
							<td width="30" align="center">
								${ ctNum.count}
							</td>
							<td align="center">
								${ postition.positionName}
							</td>
							<td align="center">
								<c:choose>
									<c:when test="${ postition.powerSetNum == 0}">
										未设置
									</c:when>
									<c:otherwise>
										已设置
									</c:otherwise>
								</c:choose>
							</td>
							<td align="center" class="optCol">
								<a href='#' class="modify">修改</a>
								<input type="hidden" class="minfo" style="display: inline;"
									name="pname" value="${ postition.positionName}" />
								<input type="hidden" class="minfo" style="display: inline;"
									name="power" value="${ postition.defaultPowerArray}" />
								<input type="hidden" class="minfo" style="display: inline;"
									name="pcode" value="${ postition.positionCode}" />
								&nbsp;
								<a href="#" onclick="delPstn( '${ postition.positionCode}');">删除</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="6">
							<span>${ page.pageInfo}</span>
							<input type="button" class="inputButton" id="showAddArea"
								value="添加职位" />
							<input type="button" class="inputButton" id="goBackBtn"
								value="返 回"
								onclick="location.href='<%=request.getContextPath()%>/day/datapreserve/pstMgr_departments.do';" />
						</td>
					</tr>
				</tfoot>
			</table>
		</div>
		<div class="clear"></div>

		<!--编辑页面----开始-->
		<form
			action="<%=request.getContextPath()%>/day/datapreserve/pstMgr_savePosition.do"
			method="post" name="positionForm" id="positionForm">
			<v:bean
				clazz="com.sincinfo.zxks.core.day.datapreserve.PositionAction"
				form="positionForm">
				<div id="addArea" style="margin-top: 10px;">
					<div class="dqwz showText">
					</div>
					<div class="infoedit">
						<h1 class="showText">
						</h1>
						<dl>
							<dt>
								部门级别：
							</dt>
							<dd>
								<c:choose>
									<c:when test="${ department.departmentGrade == 1}">省级</c:when>
									<c:when test="${ department.departmentGrade == 2}">市级</c:when>
									<c:otherwise>&nbsp;</c:otherwise>
								</c:choose>
							</dd>
						</dl>
						<dl>
							<dt>
								部门名称：
							</dt>
							<dd>
								${ department.departmentName}
							</dd>
						</dl>
						<dl>
							<dt>
								职位名称：
							</dt>
							<dd>
								<input type="hidden" name="positionSaveType"
									id="positionSaveType" value="-1" />
								<input type="hidden" name="department.departmentCode"
									value="${ department.departmentCode}" />
								<input type="hidden" name="position.departmentCode"
									value="${ department.departmentCode}" />
								<input class="inputText" type="text" id="positionName"
									name="position.positionName" value="" />
								<span>(必须填写)</span>
								<v:v input="position.positionName">请输入职位名称！</v:v>
								<input type="hidden" name="position.positionCode"
									id="positionCode" value="" />
							</dd>
						</dl>
					</div>
					<div class="clear"></div>
					<div class="infoedit">
						<h1>
							设置权限
						</h1>
						<c:forEach items="${ msList}" var="module">
							<c:if test="${ not empty module.modules}">
								<dl>
									<dt>
										${ module.clazz.className}
									</dt>
									<dd>
										（
										<a href="#" onclick="sctAll('${ module.clazz.classId}');">反选</a>）
									</dd>
								</dl>
								<dl>
									<dt>
										&nbsp;
									</dt>
									<dd>
										<table border="0" cellspacing="0" cellpadding="0"
											class="allTdWidth" id="${ module.clazz.classId}">
											<tr>
												<c:forEach items="${ module.modules}" var="sysM"
													varStatus="ctNum">
													<td>
														${ sysM.moduleId}.
														<input type="checkbox" name="pwrIndexes"
															value="${ sysM.moduleId}" />
														${ sysM.moduleName}
													</td>
													<c:if test="${ctNum.last && (ctNum.count % 4 != 0)}">
														<c:forEach begin="${ctNum.count % 4}" end="3">
															<td>
																&nbsp;
															</td>
														</c:forEach>
													</c:if>
													<c:if test="${ ctNum.count % 4 == 0 && !ctNum.last}">
														</tr>
														<tr>
													</c:if>
												</c:forEach>
											</tr>
										</table>
									</dd>
								</dl>
							</c:if>
						</c:forEach>
					</div>
					<div class="clear"></div>
					<div class="button">
						<input class="inputButton" type="submit" value="保 存" />
						<input class="inputButton" type="button" id="hideAddArea"
							value="取 消" />
						<input type="hidden" name="departGrade" value="${ departGrade}" />
					</div>
				</div>
			</v:bean>
		</form>
	</body>
</html>
