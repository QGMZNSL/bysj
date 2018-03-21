<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>免考替换文件查看</title>
		<link href="<%=request.getContextPath()%>/common/style/style.css"
			rel="stylesheet" type="text/css" />
	</head>

	<body>
		<!--导航---start-->
		<div class="dqwz">
			<span>帮助</span>日常维护》信息发布》免考替换文件管理
		</div>
		<!--导航---end-->
		<!--通知声明须知----开始-->
		<div class="tz">
			<h1>
				2003年3号文件
			</h1>
			<div>
				 以下是综合计划管理系统中的合同编辑功能需求中的合同增加用例描述：   
  描述对象 描述内容   
  标识符 IPMS0101   
  说明 增加一条合同记录   
  参与者 合同编辑人员--熟悉合同管理业务   
  频度   
  状态 通过审查   
  前置条件 1. 参与者具有合同增加的权限2. 参与者已选取对应的计划记录3. 当前计划总投资≥SUM(该计划下已签合同价)   
  后置条件 1. 数据库中更加一条合同纪律2. 可执行合同原件扫描用例3. 可执行合同付款增加用例4. 可执行合同修改和合同删除用例   
  被扩展的用例 无   

			</div>
			<c:if test="${not empty information.informationFile }">
					附件：${information.informationFile }
	       </c:if>
		</div>
		<div class="button">
			<input class="inputButton" type="button" value='返 回'
				onclick="history.back()" />
		</div>
		<!--通知声明须知----end-->
	</body>
</html>
