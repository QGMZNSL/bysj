<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html" />
		<title>免考替换文件添加</title>
		<link href="<%=request.getContextPath()%>/common/style/style.css"
			rel="stylesheet" type="text/css" />
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/common/kindeditor-3.4/kindeditor.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/common/js/clientfuns.js"></script>
		<script type="text/javascript">
		    KE.show({
		        id : 'content',
		        cssPath : '<%=request.getContextPath()%>/common/kindeditor-3.4/index.css'
		    });
        </script>

	</head>

	<body>
		<!--导航---start-->
		<div class="dqwz">
			<span>帮助</span>日常维护》信息发布》免考替换文件管理
		</div>
		<!--导航---end-->
		<!--编辑页面----开始-->
		<form action="message_add.do" method="post" name="addform"
			enctype="multipart/form-data" onsubmit="return check()">
			<div class="infoedit">
				<dl>
					<dt>
						文件编号：
					</dt>
					<dd>
						<input class="inputText" type="text" name="message.articleTitle"
							id="title" maxlength="200"/>
						<span>(必须填写)</span>
					</dd>
				</dl>
				<dl>
					<dt>
						文件名称：
					</dt>
					<dd>
						<input class="inputText" type="text" name="message.articleTitle"
							id="title" maxlength="200"/>
						<span>(必须填写)</span>
					</dd>
				</dl>
				<dl>
					<dt>
						文件分类：
					</dt>
					<dd>
						<select class="inputText" name="classid">
							<option value="">
								---请选择---
							</option>
							<option value="">
								免考
							</option>
							<option value="">
								替换
							</option>
						</select>
						<span>(必须填写)</span>
					</dd>
				</dl>
				<dl>
					<dt>
						证明材料：
					</dt>
					<dd>
						<input class="inputText" type="text" name="message.articleTitle"
							id="title" maxlength="200"/>
						<span>(必须填写)</span>
					</dd>
				</dl>
				<dl>
					<dt>
						内容：
					</dt>
					<dd>
						<textarea class="inputTextarea" name="message.articleContent"
							id="content"></textarea>
					</dd>
				</dl>
				<dl>
					<dt>
						导入文件：
					</dt>
					<dd>
						<input class="inputText" type="text" name="message.articleTitle"
							id="title" maxlength="200"/>				
				<input class="inputButton" type="submit" value='浏 览' />				
				<input class="inputButton" type="submit" value='导 入' />
				<div style="color:red;">注：文件内容可通过输入文件“内容”或“导入文件”录入系统。</div>
					</dd>
				</dl>
				<div class="clear"></div>
			</div>
			<div class="button">
				<input class="inputButton" type="submit" value='保 存' />
				<input class="inputButton" type="reset" value='重 置' />
				<input class="inputButton" type="button" value='返 回'
					onclick="history.back();" />
			</div>
		</form>
		<!--编辑页面----end-->
	</body>
</html>
