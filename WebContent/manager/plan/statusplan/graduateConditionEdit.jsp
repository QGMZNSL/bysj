<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="v" uri="http://nbf.river.org/vxds"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>毕业条件设置</title>
		<link href="<%=request.getContextPath()%>/common/style/style.css"
			rel="stylesheet" type="text/css" />
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/common/js/riverdefine.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/common/js/riverajax.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/common/js/jquery-1.3.2.min.js"></script>
		<script type="text/javascript">
		var goOperate = function( type,rid,proCode) {
			var url;
			var gotoFlag = true;
			if ( type == 'del1') {
				url = '<%=request.getContextPath()%>/plan/graduateCondition_Del1.do?graduateGroup.proCode='+proCode+'&graduateGroup.graduateGroupCode=' + rid;
				gotoFlag = confirm("确认删除对该课程分组的毕业限制吗？");
			} else if ( type == 'del2') {
				url = '<%=request.getContextPath()%>/plan/graduateCondition_Del2.do?graduateNeed.proCode='+proCode+'&graduateNeed.graduateNeedCode=' + rid;
				gotoFlag = confirm("确认删除相关证明限制吗？");
			} else {
				return;
			}
			if ( gotoFlag) {
				location.href = url;
			}
		};		
		$(document).ready( function() {
			$('.chkType').click( function() {
				var val = this.value;
				$('.disNone').hide("slow");
				$('#' + val).show("slow");
			});
			
			$('#sepAreaSwitch').click( function() {
				$('#needArea').hide("slow");
				$('#sepArea').show("slow");
			});
			
			$('#needAreaSwitch').click( function() {
				$('#sepArea').hide("slow");
				$('#needArea').show("slow");
			});
			
			$('#sepCancel').click( function() {
				$('#sepArea').hide("slow");
			});
			
			$('#needCancel').click( function() {
				$('#needArea').hide("slow");
			});
			
			$('.disNone').hide();
			
			$('#subjectRadio').attr("checked", "checked");
			$('#chkTypeSubject').show();
			
			$('#needArea').hide();
			$('#sepArea').hide();
		});
		</script>
	</head>

	<body>
		<!-- 页面导航 -->
			<div class="dqwz">
	<div  style="margin-right: 10px;">
<span style="cursor:pointer; " id="helpSpan" onclick="javascript:showHelpDiv('Z42007');">- 帮助 -</span>
		<span class="pageCode">No.Z42007</span>${currNavigation}
	</div>
</div>
<jsp:include page="/help/helpJs.jsp"/>
<iframe name="ifm" id="helpIfm" scrolling="no" frameborder="0" width="100%" style="display: none;" onload="this.height=this.contentWindow.document.body.scrollHeight+18">
	</iframe>

		<!-- 设置学分 -->
		<div class="infoedit">
		<form action="<%=request.getContextPath()%>/plan/graduateCondition_Edit.do"	method="post" name="addForm">
		    <input type="hidden" name="graduateCondition.saveType" id="saveType" value="${graduateCondition.saveType}" />
		    <input type="hidden" name="graduateCondition.proCode" id="proCode" value="${graduateCondition.proCode}" />
			<v:bean	clazz="com.sincinfo.zxks.core.plan.GraduateConditionAction" form="addForm">
				<h1 style="height:30px;line-height:30px">
					&nbsp;毕业条件设置
				</h1>
				<dl>
					<dt>
						毕业学分：
					</dt>
					<dd>
						<input class="inputText" type="text" name="graduateCondition.graduateConditionCredit" id="graduateConditionCredit" value="${graduateCondition.graduateConditionCredit}" />
						<v:v input="graduateCondition.graduateConditionCredit" exp="d(1,1000)">请输入学分（必须是大于0的数字）！</v:v>
					</dd>
				</dl>
				<div class="clear"></div>
				<dl>
					<dt>
						毕业条件说明：
					</dt>
					<dd>
						<textarea class="inputTextarea"  name="graduateCondition.graduateConditionDescribe" id="graduateConditionDescribe" rows="5" cols="30">${graduateCondition.graduateConditionDescribe}</textarea>
					</dd>
				</dl>
				<div class="clear"></div>
		<div style="width:100%; background-color:#D8E6F6;margin:0;padding:3px 0 0 0;height:26px">
			<input class="inputButton inputButtonL" type="button" value="分组条件设置" id="sepAreaSwitch"  />&nbsp;
			<input class="inputButton inputButtonL" type="button" value="须持证明设置" id="needAreaSwitch" />&nbsp;
			<input class="inputButton" type="submit" value="保 存" />&nbsp;
			<input class="inputButton" type="button" value="返 回" onclick="location.href='<%=request.getContextPath()%>/plan/graduateCondition_Show.do'" />
		</div>
		</v:bean>
		</form>
		</div>

		<!-- 按加考组设置学分或者课程与加考组的关系 -->
		<!-- 加考/选考分组的判断 -->
		<c:if test="${ not empty graduateGroupList}">
		<div class="list">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<caption>
					分组限制条件
				</caption>
				<thead>
					<tr>
						<th width="26">
							序号
						</th>
						<th>
							分组名称
						</th>
						<th>
							判断类型
						</th>
						<th>
							限制内容
						</th>
						<th width="80">
							操作
						</th>
					</tr>
				</thead>
				<tbody>
				    <c:forEach items="${ graduateGroupList}" var="gg" varStatus="ctNum">
					<tr>
						<td align="center">
							${ ctNum.count}
						</td>
						<td align="center">
							${ gg.syllabusGroupCode}
						</td>
						<td align="center">
							<c:choose>
								<c:when test="${ gg.graduateGroupTypeCode == 1}">免考课程</c:when>							
								<c:when test="${ gg.graduateGroupTypeCode == 2}">学分限制</c:when>
								<c:when test="${ gg.graduateGroupTypeCode == 3}">课程数</c:when>
								<c:otherwise>&nbsp;</c:otherwise>
							</c:choose>
						</td>
						<td align="center">
							${ gg.syllabusName}&nbsp;
						</td>
						<td align="center">
							<a href="#" onclick="goOperate( 'del1', '${ gg.graduateGroupCode}', '${ gg.proCode}');">删除</a>&nbsp;
						</td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		</c:if>

		<!-- 毕业需持有证件 -->
		<c:if test="${ not empty graduateNeedList}">
		<div class="list">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<caption>
					毕业需持有证件
				</caption>
				<thead>
					<tr>
						<th width="26">
							序号
						</th>
						<th>
							须持证明
						</th>
						<th>
							是否必须持有
						</th>
						<th width="80">
							操作
						</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach items="${ graduateNeedList}" var="gn" varStatus="ctNum">
					<tr>
						<td align="center">
							${ ctNum.count}
						</td>
						<td align="center">
							${ gn.graduateNeedName}
						</td>
						<td align="center">
							<c:choose>
								<c:when test="${ gn.isMust == 0}">否</c:when>							
								<c:when test="${ gn.isMust == 1}">是</c:when>
								<c:otherwise>&nbsp;</c:otherwise>
							</c:choose>
						</td>
						<td align="center">
							<a href="#" onclick="goOperate( 'del2', '${ gn.graduateNeedCode}', '${ gn.proCode}');">删除</a>&nbsp;
						</td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
		</div>
		</c:if>

		<!-- 设置对应的加考/选考分组的条件 -->
		<div id="sepArea">
			<div class="dqwz">
				分组判断条件设置
			</div>
			<div class="infoedit">
				<form action="<%=request.getContextPath()%>/plan/graduateCondition_Add1.do" method="post" name="addForm">
				    <input type="hidden" name="graduateGroup.proCode" id="proCode" value="${graduateCondition.proCode}" />
					<h1>
						设置分组判断条件：
					</h1>
					<dl>
						<dt>
							选择分组：
						</dt>
						<dd>
							<select class="inputText" name="graduateGroup.syllabusGroupCode" id="syllabusGroupCode">
							  <c:forEach items="${ grpSetList}" var="gl" varStatus="ctNum">
								<option value="${gl.syllabusGroupCode}">${gl.syllabusGroupName}</option>
							  </c:forEach>
							</select>
						</dd>
					</dl>
					<dl>
						<dt>
							判断类型：
						</dt>
						<dd>
							<input type="radio" class="chkType" name="graduateGroup.graduateGroupTypeCode" value="1" checked />免考课程
							<input type="radio" class="chkType" name="graduateGroup.graduateGroupTypeCode" value="2" />学分限制
							<input type="radio" class="chkType" name="graduateGroup.graduateGroupTypeCode" value="3" />课程数
						</dd>
					</dl>
					<dl id="chkTypeSubject" class="disNone">
						<dt>
							免考课程：
						</dt>
						<dd>
							<select class="inputText" name="graduateGroup.syllabusCode" id="syllabusCode" >
								<option>--- 请选择 ---</option>
								<c:forEach items="${ proSyllabusList}" var="pl" varStatus="ctNum">
								  <option value="${pl.syllabusCode}" >${pl.syllabusName}</option>
								</c:forEach>
							</select>
						</dd>
					</dl>
					<dl id="chkTypeScoreLimit" class="disNone">
						<dt>
							学分限制：
						</dt>
						<dd>
							<input class="inputText" type="text" name="graduateGroup.graduateGroupCredit" id="graduateGroupCredit" />
						</dd>
					</dl>
					<dl id="chkTypeNumLimit" class="disNone">
						<dt>
							课程数：
						</dt>
						<dd>
							<input class="inputText" type="text" name="graduateGroup.graduateGroupCredit" id="graduateGroupCredit" />
						</dd>
					</dl>
					<div class="clear"></div>
					<div class="button">
						<input class="inputButton" type="submit" value="添 加" />
						<input class="inputButton" type="button" value="取 消" id="sepCancel" />
					</div>
				</form>
			</div>
		</div>

		<!-- 设置毕业需持有证件 -->
		<div id="needArea">
			<div class="dqwz">
				毕业须持有证件设置
			</div>
			<div class="infoedit">
				<form action="<%=request.getContextPath()%>/plan/graduateCondition_Add2.do" method="post" name="">
				    <input type="hidden" name="graduateNeed.proCode" id="proCode" value="${graduateCondition.proCode}" />
					<h1>
						毕业须持有证件设置：
					</h1>
					<dl>
						<dt>
							须持证明：
						</dt>
						<dd>
							<input class="inputText" type="text" name="graduateNeed.graduateNeedName" id="graduateNeedName"  />
						</dd>
					</dl>
					<dl>
						<dt>
							是否必须持有：
						</dt>
						<dd>
							<input type="radio" name="graduateNeed.isMust" id="isMust" value="1" />是
							<input type="radio" name="graduateNeed.isMust" id="isMust" value="0" checked="checked" />否
						</dd>
					</dl>
					<div class="clear"></div>
					<div class="button">
						<input class="inputButton" type="submit" value="添 加" />
						<input class="inputButton" type="button" value="取 消" id="needCancel" />
					</div>
					<div class="clear"></div>
				</form>
			</div>
		</div>
	</body>
</html>
