<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>在线评测系统</title>
		<style type="text/css">
body,dl,dd,ul,li {
	margin: 0px;
	padding: 0px;
	list-style: none;
}

body {
	font-size: 12px;
}

.nav {
	background: #3d80ad;
	font-size: 14px;
	line-height: 33px;
	padding-left: 40px;
	height: 33px;
	margin: 0px 5px 0px 5px;
}

.nav dd {
	background: url(style/nav_line.gif) no-repeat 0px 9px;
}

.nav span {
	color: #fff;
	float: right;
	padding-right: 5px;
}

.subNav {
	border-left: #ccc 1px solid;
	border-right: #ccc 1px solid;
	border-bottom: #ccc 1px solid;
	background: #f2f3f6;
	height: 30px;
	margin: 0px 5px;
	line-height: 30px;
}

.subNav ul {
	overflow: hidden;
}

.subNav li {
	background: url(style/subnav_line.gif) no-repeat 0px 8px;
	display: inline;
	float: left;
	padding: 0px 8px;
	margin-top: 3px;
}

.subNav li a {
	outline: none;
	text-decoration: none;
	color: #333;
	height: 22px;
	display: block;
	text-align: centet;
	padding: 0px 11px;
	margin-top: 2px;
	line-height: 22px;
	float: left;
}

a.selected {
	background: #f2f3f6;
	color: #3d80ad;
	margin: 10px;
	padding: 6px 10px 9px 10px;
}

a {
	outline: none;
	padding: 0px 20px;
	text-decoration: none;
	color: #fff;
}

dd {
	font-weight: bold;
	display: inline-block; *
	float: left;
}

#sub2 {
	margin-left: 70px;
}

#sub3 {
	margin-left: 140px;
}

#sub4 {
	margin-left: 260px;
}

#sub5 {
	margin-left: 390px;
}

#sub6 {
	margin-left: 570px;
}

.ccc {
	background: #;
}

.hide {
	
}

.subhide {
	display: none;
}
</style>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/common/js/jquery-1.3.2.min.js"></script>
		<script language="javascript">
		//导航切换
		function Nav(o,n){
			o.className="selected";
			var t;
			var id;
			var s;
			for(var i=1;i<=n;i++){
				id ="nav"+i;
				t = document.getElementById(id);
				s = document.getElementById("sub"+i);
				if ( t != null && s != null) {
					if(id != o.id){
						t.className="hide";
						s.style.display = "none";
					}
					else{
						s.style.display = "block";
					}
				}
			}
		}
		//二级菜单选中
		function checked(obj,n,m){
			var id;
			var objt;
			for(var i=1;i<=6;i++){
			  for(var j=1;j<=6;j++){
			  	if(i==n&&j==m){
			  		continue;
			  	}
			  	id="sub"+i+"sp"+j;
				objt = document.getElementById(id);
				if(objt==null){
					continue;
				}
				objt.style.backgroundColor ="#f2f3f6";
				objt.style.color ="#000000";
			  }
			}
			obj.style.backgroundColor ="#579ECE";
			obj.style.color ="#fff";
		}

		// 安全退出
		function safeExit() {
			if( confirm('确定退出系统吗？')){ 
				// 发送销毁请求
				var logoutUrls = ['<%=request.getContextPath()%>/sys/core_logout.do',
										'/ZK_EXAM/sys/exam_logout.do',
										'/ZK_STATUS/sys/status_logout.do',
										'/ZK_CORE_PATCH/loginout.jsp'];
				var destroyNum = 0;
				for ( var i = 0; i < logoutUrls.length; i++) {
					$.ajax({
					     type: "post", // 传值方式
					     url: logoutUrls[i],    // 接收后台地址
					     data: "t=" + new Date().getTime(),   // 数据
					     async: false,  // 是否异步	
					     success: function( msg) { 
					     	destroyNum += (msg == 'succeed in exit') ? 1 : 0;
					     }
					});
				}
				
				// 提示并返回页面
				if ( destroyNum >= 1) {
					alert("您已安全退出！");
				} else {
					alert("安全退出异常！");
				}
				top.location.href='<%=request.getContextPath()%>/manager/login.jsp';
			}
		}

		$(document).ready( function() {
			$('#exitLink').click( function() {
				safeExit();
			});
		});
		</script>
	</head>

	<body>
		<div style="margin: 0px 5px 0px 20px;">
			<div style="float: right; width: 250px;">
				<div style="text-align: right; padding-top: 10px;">
					<a style="color: #333;" href="#"
						onclick="window.external.AddFavorite(window.parent.location.href,'陕西省自学考试管理中心');">加入收藏
					</a> |
					<a style="color: #333;" href="#" id="exitLink">安全退出</a>
				</div>

				<div></div>
			</div>
			<img src="<%=request.getContextPath()%>/common/style/logo.jpg" />
		</div>
		<div>
			<div class="nav">
				<span>欢迎您，${ sessionScope.zkOperator.realName}（${
					sessionScope.zkOperator.userName}）/ <c:choose>
						<c:when test="${ sessionScope.zkOperator.userRole == 1}">省用户</c:when>
						<c:when test="${ sessionScope.zkOperator.userRole == 2}">市用户</c:when>
						<c:when test="${ sessionScope.zkOperator.userRole == 4}">考区用户</c:when>
						<c:when test="${ sessionScope.zkOperator.userRole == 8}">主考院校用户</c:when>
						<c:when test="${ sessionScope.zkOperator.userRole == 16}">摄像点用户</c:when>
					</c:choose> </span>
				<dl>
					<c:forEach items="${ headMenu}" var="menu" varStatus="countNum">
						<c:choose>
							<c:when test="${ empty menu.cndHeadList}">
								<dd>
									<a id="nav${countNum.count}"
										href="<%=request.getContextPath()%>/menu/menuAndTree_initLeftTree.do?topMenu.menuId=${ menu.topHead.menuId}"
										target="leftFrame">${ menu.topHead.menuName}</a>
								</dd>
							</c:when>
							<c:otherwise>
								<dd>
									<a id="nav${countNum.count}" onclick="Nav(this, 6)"
										href="#">${ menu.topHead.menuName}</a>
								</dd>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</dl>
			</div>
			<div class="subNav">
				<c:forEach items="${ headMenu}" var="menu" varStatus="countNum">
					<ul id="sub${ countNum.count}"
						<c:if test="${ countNum.count ne 1}">class="subhide"</c:if>>
						<c:forEach items="${ menu.cndHeadList}" var="cnd"
							varStatus="ctNum">
							<li style="background: #;">
								<a
									href="<%=request.getContextPath()%>/menu/menuAndTree_initLeftTree.do?topMenu.menuId=${ cnd.menuId}"
									target="leftFrame"><span
									id="sub${countNum.count }sp${ctNum.count }" style="padding:5px;margin:0px;"
									 onclick="checked(this,${countNum.count },${ctNum.count })">${ cnd.menuName}</span>
								</a>
							</li>
						</c:forEach>
					</ul>
				</c:forEach>
			</div>
		</div>
	</body>
</html>

