<%@ page language="java" pageEncoding="utf-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/common/js/jquery-1.3.2.min.js"></script>
<script language="javascript">
	function showHelpDiv(pageNo) {
		var v = document.getElementById("helpIfm").contentWindow.document.getElementById("ew1");
		if(v != null){
			if(confirm("编辑的数据会丢失，是否继续？")){
				doToggle(pageNo);
			}
		}else{
			doToggle(pageNo);
		}
	}
	function doToggle(val){
		$("#helpIfm").attr("src","<%=request.getContextPath()%>/zk/help/help_getHelp.do?dt="+ new Date().getTime()+"&help_id="+val);
		$("#helpIfm").slideToggle(1000);
		var str = $("#helpSpan").html();
		if(str == "点击收起"){
			$("#helpSpan").html("- 帮助 -");
		}else{
		$("#helpSpan").html("点击收起");
		}
	}
</script>
