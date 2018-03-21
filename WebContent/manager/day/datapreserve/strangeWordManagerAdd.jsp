<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>生僻字管理</title>
		<link href="<%=request.getContextPath()%>/common/style/style.css"
			rel="stylesheet" type="text/css" />
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/common/js/clientfuns.js"></script>
		<script type="text/javascript">
			function check(){
				var word=document.getElementById("word").value;
				if(isNull(word)){
					alert("文字不能为空!");
					return false;
				}
				if(CheckStr(word)){
					alert("文字中含有非法字符！");
		    	  	return false;
				}
				
				var pinyin=document.getElementById("pinyin").value;
				if(isNull(pinyin)){
					alert("拼音不能为空!");
					return false;
				}
				if(CheckStr(pinyin)){
					alert("拼音中含有非法字符！");
		    	  	return false;
				}
				if(!isNaN(pinyin)){
					alert("请输入正确的拼音!");
					return false;
				}
				return true;
			}
			
			function getPinYing(){
			  var hanzi=document.getElementById("word").value;
			  if(hanzi==null||hanzi==""){
			  	alert("如果您想自动将汉字转为拼音，请您先输入汉字！");
			  }else{
			  	execute(hanzi);
			  }
			}
			
  		var  Request;
	     //创建一个xmlhttprequest 对象
	     
	     function  createXmlRequest(){
	     
	         if(window.ActiveXObject){
                  Request = new ActiveXObject("Microsoft.XMLHTTP");
	         }else{
	              Request  = new XMLHttpRequest();
	         }
	     }
	     // 利用xmlrequest对象 发出请求
	     function  execute(hanzi){
	           createXmlRequest();
	           // 创建请求
	          Request.open("post","strangeword_getPY.do");
	          // 信息的传递
	          var  usermsg = "hanzi="+hanzi;
	          // 要设置提交的类型
	          Request.setRequestHeader("Content-Type",
			    "application/x-www-form-urlencoded"); 
	          // 当请求的装填发生改变的时候 调用work
	          Request.onreadystatechange = work;
	          // 发出请求
	          Request.send(usermsg);
	     }
	
	     // 处理响应
	     function  work(){
	        // 当对象的装填只等于4 的时候 处理响应信息
	        if(Request.readyState == 4 && Request.status == 200){
	           // 得到响应的信息
	           var  msg = Request.responseText;
	           if(msg==null||msg==""){
	           		alert("转换错误!");
	           }else{					           
	            document.getElementById("pinyin").value=msg;
	           }
	            
	        }
	     }
		</script>
	</head>

	<body>
		<!--导航---start-->
		<div class="dqwz">
			<div style="margin-right: 10px;">
				<span style="cursor: pointer;" id="helpSpan"
					onclick="javascript:showHelpDiv('Z53005');">- 帮助 -</span>
				<span class="pageCode">No.Z53005</span>${currNavigation}
			</div>
		</div>
		<jsp:include page="/help/helpJs.jsp" />
		<iframe name="ifm" id="helpIfm" scrolling="no" frameborder="0"
			width="100%" style="display: none;"
			onload="this.height=this.contentWindow.document.body.scrollHeight+13"></iframe>
		<!--导航---end-->

		<!--编辑页面----开始-->
		<form action="strangeword_doadd.do" method="post"
			onsubmit="return check()">
			<div class="infoedit">
				<dl>
					<dt>
						生僻字：
					</dt>
					<dd>
						<input class="inputText inputTextM" id="word" type="text"
							name="bean.strangeWord" />
						<span>(必须填写)</span>
					</dd>
				</dl>
				<dl>
					<dt>
						拼音：
					</dt>
					<dd>
						<input class="inputText inputTextM" id="pinyin" type="text"
							name="bean.strangeWholeWord" onfocus="getPinYing()"/>
						<span>(必须填写)</span>
					</dd>
				</dl>
				<div class="clear"></div>
			</div>
			<div class="button">
				<input class="inputButton" type="submit" value="保 存" />
				<input class="inputButton" type="button" value="返 回"
					onclick="location.href='strangeword_manager.do';" />
			</div>
		</form>
		<!--编辑页面----end-->
	</body>
</html>
