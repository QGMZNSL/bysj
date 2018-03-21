<%@page contentType='text/html; charset=GBK' language='java'%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.0 Transitional//EN' 'http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd'>
<html xmlns='http://www.w3.org/1999/xhtml'>
<head>
<title></title>
<%String rgPath=request.getContextPath();%>
<link href="<%=rgPath%>/common/style/style.css"
			rel="stylesheet" type="text/css" />
<script type="text/javascript">
function trim(str){  //ɾ���������˵Ŀո� 
	return str.replace(/(^\s*)|(\s*$)/g,"");
}

function goBack(){
	var examYear=document.getElementById("examYear2").value;
	examYear=trim(examYear);
	location.href='exam_show.do?menutype=2&baseSyllabusTime.applyYear='+examYear;
}

function checkSub(checkForm){
var strAlert="������ԭ���²����ύ��\n\n";
	var ii=0;
	var examYear1=checkForm.examYear1.value;
	examYear1=trim(examYear1);
	if(examYear1==""){
		if(ii==1) strAlert=strAlert+"��\n";
		strAlert=strAlert+"�������꣨�ӣ���δ��д";
		ii=1;
	}
	else{
		if(/^[0-9]*$/.test(examYear1)){
			if(parseInt(examYear1)<1000){
				if(ii==1) strAlert=strAlert+"��\n";
				strAlert=strAlert+"�������꣨�ӣ��������С";
				ii=1;
			}
			if(parseInt(examYear1)>3000){
				if(ii==1) strAlert=strAlert+"��\n";
				strAlert=strAlert+"�������꣨�ӣ����������";
				ii=1;
			}
			
		}
		else{
			if(ii==1) strAlert=strAlert+"��\n";
			strAlert=strAlert+"�������꣨�ӣ��������밢������������";
			ii=1;
		}
	}
	var examYear2=checkForm.examYear2.value;
	examYear2=trim(examYear2);
	if(examYear2==""){
		if(ii==1) strAlert=strAlert+"��\n";
		strAlert=strAlert+"�������꣨������δ��д";
		ii=1;
	}
	else{
		if(/^[0-9]*$/.test(examYear2)){
			if(parseInt(examYear2)<1000){
				if(ii==1) strAlert=strAlert+"��\n";
				strAlert=strAlert+"�������꣨�����������С";
				ii=1;
			}
			if(parseInt(examYear2)>3000){
				if(ii==1) strAlert=strAlert+"��\n";
				strAlert=strAlert+"�������꣨�������������";
				ii=1;
			}
			
		}
		else{
			if(ii==1) strAlert=strAlert+"��\n";
			strAlert=strAlert+"�������꣨�����������밢������������";
			ii=1;
		}
	}
	if(examYear1==examYear2){
		if(ii==1) strAlert=strAlert+"��\n";
		strAlert=strAlert+"�������꣨�ӣ����롰�����꣨��������ͬ";
		ii=1;
	}
	if(ii==1){
		alert(strAlert+"��");
		return false;
	}
	else{
		if(confirm("ȷ��Ҫ����������")){
			checkForm.action="exam_hisSave.do";
		}
	}
}
</script>
</head>
<body>
<div class="dqwz">
			<span>����</span><span class="pageCode">���ܱ��:${currFunctionId}</span>
			רҵ�ƻ� > ���԰��� > ��ȫ��ͳ���γ̿��԰���
</div>
<form method='post' name='checkForm' onsubmit='return checkSub(this);'>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
<tr>
	<td height='10px' colspan='4'/>
</tr>
<tr>
	<td width="10%">
	</td>
    <td width="20%" align="left">
    	������ӣ�
		<input type='text' style='width:30px;' name='examYear1' id='examYear1'/>������
	</td>
	<td align="left" width="20%">
		���Ƶ���
		<input type='text' style='width:30px;' name='examYear2' id='examYear2'/>
		��
	</td>
	<td width="50%">
	</td>
</tr>
<tr>
	<td height='10px' colspan='4'/>
</tr>
<tr>
	<td height='25px' colspan='4' style="color:red;">
	ע���ù��ܣ��ǽ�������ʡ��ȫ��ͳ���γ̿��԰��š���������е����ݸ��Ƶ�����Ҫ����ȡ�
	</td>
</tr>
<tr>
	<td height='10px' colspan='4'/>
</tr>
<tr>
	<td align="center" bgcolor="#FFFFFF" height='40px' colspan='4'>
		<input type="submit" class="an" value="�� ��"/>
		<input type="button" class="an" onclick="goBack()" value="�� ��"/>
	</td>
</tr>
</table>
</form>
</body>
</html>