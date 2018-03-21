<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
  String info=(String)request.getAttribute("info");
   String url=(String)request.getAttribute("url");
  if(info!=null){%>
  <script type="text/javascript">
		alert("<%=info%>");
	</script>
  
  <%
  }
  if(url!=null){%>
  <script type="text/javascript">
		location.href="<%=url%>";
	</script>
  <%
  }
 %>