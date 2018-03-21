<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>证书查询</title>
<link href="<%=request.getContextPath() %>/common/style/style.css" rel="stylesheet" type="text/css" />
</head>
  <body>
		<div class="dqwz">
			<span>帮助</span>日常维护>>日常管理>>证书查询
		</div>
		<!--显示详细页面----开始-->
  <div style="border-left:1px #abc7ec solid;border-right:1px #abc7ec solid;">
   <div style=" background-image:url(<%=request.getContextPath()%>/common/style/zs_02.gif); background-position:center; background-repeat:no-repeat;width:888px;height:500px;margin:6px auto">
       <div style="margin:5px auto;width:800px">  <table width="819" height="478" border="0" align="center" cellpadding="0" cellspacing="0" style="border:0">
              <tr>
                <td width="22%" rowspan="4"><span style="text-align: center;"><img src="${graduateBean.studPhotoFile1}" width="150" height="200" /></span></td>
                <td width="7%" height="122" align="center"><img src="<%=request.getContextPath()%>/common/style/logo2.gif" width="55" height="58" /></td>
                <td colspan="2" align="center" valign="bottom"><p align="left"><img src="<%=request.getContextPath()%>/common/style/zs_01.gif" width="600" height="100" /></p>                </td>
              </tr>
              <tr>
                <td height="45" rowspan="2">&nbsp;</td>
                <td height="30" align="right">姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：</td>
                <td height="30" align="left">${graduateBean.studName}&nbsp;</td>
              </tr>
              <tr>
                <td height="30" align="right">身份证号：</td>
                <td height="30" align="left">${graduateBean.studIdnum}&nbsp;</td>
              </tr>
              <tr>
                <td height="42">&nbsp;</td>
                <td width="13%" height="42" align="right">证书编号：</td>
                <td width="58%" height="42" align="left">${graduateBean.diplomaNum}&nbsp;</td>
              </tr>
              <tr>
                <td height="38" colspan="4"><h5>参加
                  <input name="textfield" type="text" value="   ${graduateBean.proName}   "   style="border:0"/>
专业
<input name="textfield2" type="text" value="    ${graduateBean.levelName}   "  style="border:0"/>
高等教育自学生试，全部课程成绩合格，经审定，准予毕业</h5></td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td colspan="3" rowspan="4"><table width="100%" height="178" border="0" cellpadding="0" cellspacing="0">
                  <tr>
                    <td width="38%" height="178" align="center" background="<%=request.getContextPath()%>/common/style/z_01.gif" style="background-position:center; background-repeat:no-repeat"><h3>高等教育自学考试委员会</h3>
                   
                     <h3>${fn:substring(graduateBean.graduateDate,0,4) }年${fn:substring(graduateBean.graduateDate,5,7) }月${fn:substring(graduateBean.graduateDate,8,10) }日</h3></td>
                    <td width="30%" align="center">&nbsp;</td>
                    <td width="32%" align="center"><h3>${graduateBean.academyProName}</h3>
                   <h3> ${fn:substring(graduateBean.graduateDate,0,4) }年${fn:substring(graduateBean.graduateDate,5,7) }月${fn:substring(graduateBean.graduateDate,8,10) }日</h3></td>
                  </tr>
                </table></td>
              </tr>
              <tr>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td height="109">&nbsp;</td>
              </tr>
              <tr>
                <td height="50" colspan="4" align="center">中华人民共和国教育部高等教育自学考试办公室监制</td>
              </tr>
            </table>
       </div></div>
		</div>
		<div class="clear"></div>
			<div class="button">
				<input class="inputButton" type="button" value="返  回" onclick="history.back()" />
			</div>
		<div class="clear"></div>
		<%--姓名：${graduateBean.studName}<br />
		身份证号：${graduateBean.studIdnum}<br />
		证件编号：${graduateBean.diplomaNum}<br />
		专业：${graduateBean.proName}<br />
		毕业院校：${graduateBean.academyProName}<br />
		照片：<img src="${graduateBean.studPhotoFile1}">
		毕业时间：${fn:substring(graduateBean.graduateDate,0,10) }
	--%></body>
</html>