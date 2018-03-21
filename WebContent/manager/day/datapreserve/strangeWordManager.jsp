<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>生僻字管理</title>
		<link href="<%=request.getContextPath()%>/common/style/style.css"
			rel="stylesheet" type="text/css" />
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/common/js/clientfuns.js"></script>
		<style type="text/css">
			.spanMargin{
				margin-right:5px;
			}
		</style>
		<script type="text/javascript">
			function changecursor(obj){
				obj.style.cursor="hand";
			}
			function sele(data){
				location.href="strangeword_select.do?selmethod=shouzimu&where="+data;
			}
			function check(){
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

		<!--查询条件开始-->
		<form action="strangeword_select.do" method="post"
			onsubmit="return check()">
			<input type="hidden" name="selmethod" value="pinyin" />
			<div class="tjcx">
				<dl class="widthL">
					<dt>
						<span onclick="sele('1')" onmouseenter="changecursor(this)" class="spanMargin"><a>全部</a></span>：
					</dt>
					<dd>
						<span onclick="sele('a')" onmouseenter="changecursor(this)" class="spanMargin"><a>A</a></span>
						<span onclick="sele('b')" onmouseenter="changecursor(this)" class="spanMargin"><a>B</a></span>
						<span onclick="sele('c')" onmouseenter="changecursor(this)" class="spanMargin"><a>C</a></span>
						<span onclick="sele('d')" onmouseenter="changecursor(this)" class="spanMargin"><a>D</a></span>
						<span onclick="sele('e')" onmouseenter="changecursor(this)" class="spanMargin"><a>E</a></span>
						<span onclick="sele('f')" onmouseenter="changecursor(this)" class="spanMargin"><a>F</a></span>
						<span onclick="sele('g')" onmouseenter="changecursor(this)" class="spanMargin"><a>G</a></span>
						<span onclick="sele('h')" onmouseenter="changecursor(this)" class="spanMargin"><a>H</a></span>
						<span onclick="sele('i')" onmouseenter="changecursor(this)" class="spanMargin"><a>I</a></span>
						<span onclick="sele('j')" onmouseenter="changecursor(this)" class="spanMargin"><a>J</a></span>
						<span onclick="sele('k')" onmouseenter="changecursor(this)" class="spanMargin"><a>K</a></span>
						<span onclick="sele('l')" onmouseenter="changecursor(this)" class="spanMargin"><a>L</a></span>
						<span onclick="sele('m')" onmouseenter="changecursor(this)" class="spanMargin"><a>M</a></span>
						<span onclick="sele('n')" onmouseenter="changecursor(this)" class="spanMargin"><a>N</a></span>
						<span onclick="sele('o')" onmouseenter="changecursor(this)" class="spanMargin"><a>O</a></span>
						<span onclick="sele('p')" onmouseenter="changecursor(this)" class="spanMargin"><a>P</a></span>
						<span onclick="sele('q')" onmouseenter="changecursor(this)" class="spanMargin"><a>Q</a></span>
						<span onclick="sele('r')" onmouseenter="changecursor(this)" class="spanMargin"><a>R</a></span>
						<span onclick="sele('s')" onmouseenter="changecursor(this)" class="spanMargin"><a>S</a></span>
						<span onclick="sele('t')" onmouseenter="changecursor(this)" class="spanMargin"><a>T</a></span>
						<span onclick="sele('u')" onmouseenter="changecursor(this)" class="spanMargin"><a>U</a></span>
						<span onclick="sele('v')" onmouseenter="changecursor(this)" class="spanMargin"><a>V</a></span>
						<span onclick="sele('w')" onmouseenter="changecursor(this)" class="spanMargin"><a>W</a></span>
						<span onclick="sele('x')" onmouseenter="changecursor(this)" class="spanMargin"><a>X</a></span>
						<span onclick="sele('y')" onmouseenter="changecursor(this)" class="spanMargin"><a>Y</a></span>
						<span onclick="sele('z')" onmouseenter="changecursor(this)" class="spanMargin"><a>Z</a>
						</span>
					</dd>
				</dl>
				<dl>
					<dt>
						拼音：
					</dt>
					<dd>
						<input class="inputText inputTextM" type="text" id="pinyin"
							name="where"
							<c:if test="${selmethod=='pinyin'}">value="${where }"</c:if> />
					</dd>
				</dl>
			</div>
			<div class="button">
				<input class="inputButton" type="submit" value="查 询" />
				<input class="inputButton" type="button" value="添 加"
					onclick="location.href='strangeword_toadd.do';" />
			</div>
		</form>

		<div class="clear"></div>
		<!--查询条件end-->

		<!--列表样式---表格----开始-->
		<c:if test="${not empty beanList}">
			<div class="list">
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
					<caption>
						<c:if test="${where=='1'}">
						全部
					</c:if>
						<c:if test="${where!='1'}">
						${fn:toUpperCase(where)}
					</c:if>
					</caption>
					<thead>
						<tr>
							<th>
								拼音
							</th>
							<th width="80">
								生僻字
							</th>
							<th>
								拼音
							</th>
							<th width="80">
								生僻字
							</th>
							<th>
								拼音
							</th>
							<th width="80">
								生僻字
							</th>
							<th>
								拼音
							</th>
							<th width="80">
								生僻字
							</th>
							<th>
								拼音
							</th>
							<th width="80">
								生僻字
							</th>
							<th>
								拼音
							</th>
							<th width="80">
								生僻字
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<c:forEach var="bean" items="${beanList}" varStatus="h">
								<td align="center">
									${bean.strangeWholeWord}
								</td>
								<td align="center">
									<a
										href="strangeword_toedit.do?strangeid=${bean.strangeWordId }">${bean.strangeWord}</a>
								</td>
								<c:if test="${(h.index+1)%6==0}">
						</tr>
						<tr>
							</c:if>
							</c:forEach>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="12">
								<span> ${page.pageInfo } </span>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
		</c:if>
		<!--列表样式---表格----end-->
		<!--上下页面----开始-->
		<c:if test="${(not empty selmethod)&&(empty beanList ) }">
			<div class="list">
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
					<caption>
						<c:if test="${where=='1'}">
						全部
					</c:if>
						<c:if test="${where!='1'}">
						${fn:toUpperCase(where)}
					</c:if>
					</caption>
					<tr>
						<td align="center">
							没有您要搜索的内容
						</td>
					</tr>
				</table>
			</div>
		</c:if>
		<!--上下页面----结束-->
	</body>
</html>
