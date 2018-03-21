<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="v" uri="http://nbf.river.org/vxds"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>课程设置</title>
		<link href="<%=request.getContextPath()%>/common/style/style.css"
			rel="stylesheet" type="text/css" />
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/common/js/riverdefine.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/common/js/riverajax.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/common/js/jquery-1.3.2.min.js"></script>
		<!-- <script type="text/javascript">
			$(document).ready(function(){
				$("input#textbook_code").click(
					$("div").hide(1000)
			);
		});
		</script> -->

	</head>

	<body>
		<!-- 页面导航 -->
			<div class="dqwz">
			<div style="margin-right: 10px;">
				<span style="cursor: pointer;" id="helpSpan"
					onclick="javascript:showHelpDiv('Z21007');">- 帮助 -</span>
				<span class="pageCode">No.Z21007</span>${currNavigation}
			</div>
		</div>
		<jsp:include page="/help/helpJs.jsp" />
		<iframe name="ifm" id="helpIfm" scrolling="no" frameborder="0"
			width="100%" style="display: none;"
			onload="this.height=this.contentWindow.document.body.scrollHeight+13"></iframe>
		<!-- 编辑区域 -->
		<form action="<%=request.getContextPath()%>/plan/syllabus_Add.do"
			method="post" name="addForm">
			<v:bean clazz="com.sincinfo.zxks.core.plan.SyllabusAction"
				form="addForm">
				<div class="infoedit">
					<h1>
						添加课程
					</h1>
					<dl>
						<dt>
							课程代码：
						</dt>
						<dd>
							<input class="inputText" type="text" name="syllabus.syllabusCode"
								id="syllabusCode" value="" />
							<v:v input="syllabus.syllabusCode" exp="s(1,10)">请输入课程代码！(长度不能超过10位)</v:v>
						</dd>
					</dl>
					<dl>
						<dt>
							课程名称：
						</dt>
						<dd>
							<input class="inputText" type="text" name="syllabus.syllabusName"
								id="syllabusName" value="" />
							<v:v input="syllabus.syllabusName" exp="s(1,20)">请输入课程名称！(长度不能超过20位)</v:v>
						</dd>
					</dl>
					<dl>
						<dt>
							课程分类：
						</dt>
						<dd>
							<select class="inputText" name="syllabus.syllabusType"
								id="syllabusType">
								<option>
									--- 请选择 ---
								</option>
								<option value="0">
									笔试课程
								</option>
								<option value="1">
									实践课程
								</option>
								<option value="2">
									论文答辩
								</option>
							</select>
						</dd>
					</dl>
					<dl>
						<dt>
							学分：
						</dt>
						<dd>
							<input class="inputText" type="text"
								name="syllabus.syllabuscredit" id="syllabuscredit" value="" />
							<v:v input="syllabus.syllabuscredit" exp="d(0,99)">请输入默认学分！(长度不能超过2位)</v:v>
						</dd>
					</dl>
					<dl>
						<dt>
							指定教材：
						</dt>
						<dd>
							<input class="inputText" type="text" name="syllabus.textbookName"
								id="textbookName" value="点击选择教材" name="textbook"
								style="cursor: hand;" readonly="readonly"
								onclick="window.open('<%=request.getContextPath()%>/plan/syllabus_TBooksetPre.do','_blank','top=150,left=300,height=600,width=800,resizable=0,toolbar=0,menubar=0,location=0');" />
							<input class="inputText" type="hidden"
								name="syllabus.textbookCode" id="textbookCode" />
						</dd>
					</dl>
					<dl>
						<dt>
							是否全国统考：
						</dt>
						<dd>
							<input type="radio" checked="checked" name="syllabus.isgb"
								id="isgb" value="1" />
							是
							<input type="radio" name="syllabus.isgb" id="isgb" value="0" />
							否
						</dd>
					</dl>
					<dl>
						<dt>
							备注：
						</dt>
						<dd>
							<input class="inputTextarea" type="text" name="syllabus.remarks"
								id="remarks" value="" />
						</dd>
					</dl>
					<div class="clear"></div>
				</div>
				<div class="button">
					<input class="inputButton" type="submit" value="确 定" />
					<input class="inputButton" type="reset" value="重 置" />
					<input class="inputButton" type="button" value="返 回"
						onclick="location.href='<%=request.getContextPath()%>/plan/syllabus_Show.do'" />
				</div>
			</v:bean>
		</form>
	</body>
</html>
