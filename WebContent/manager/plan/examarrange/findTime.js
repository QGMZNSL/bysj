var xmlHttp;
function createXMLHttpRequest() {
    if (window.ActiveXObject) {
        xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
    } 
    else if (window.XMLHttpRequest) {
        xmlHttp = new XMLHttpRequest();
    }
}

function handleState(){
    if(xmlHttp.readyState == 4){
    	if(xmlHttp.status == 200){
    		document.getElementById("div_in").innerHTML=xmlHttp.responseText;
    	}
    }
}

function doSelect(examYear){
	if(examYear==""){
		document.getElementById("div_in").innerHTML="";
		return false;
	}
	else{
		createXMLHttpRequest();
		xmlHttp.onreadystatechange = handleState;
		xmlHttp.open("POST", "ExcelAjax", false);
		xmlHttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		xmlHttp.send("examYear="+examYear);
	}
}

function showLie(){
	var strAlert="＊以下原因导致不能提交：\n\n";
	var ii=0;
	var examYear=document.getElementById("examYear").value;
	examYear=trim(examYear);
	if(examYear==""){
		if(ii==1) strAlert=strAlert+"，\n";
		strAlert=strAlert+"“考试年”未填写";
		ii=1;
	}
	else{
		if(/^[0-9]*$/.test(examYear)){
			if(parseInt(examYear)<1000){
				if(ii==1) strAlert=strAlert+"，\n";
				strAlert=strAlert+"“考试年”输入过小";
				ii=1;
			}
			if(parseInt(examYear)>3000){
				if(ii==1) strAlert=strAlert+"，\n";
				strAlert=strAlert+"“考试年”输入过大";
				ii=1;
			}
			
		}
		else{
			if(ii==1) strAlert=strAlert+"，\n";
			strAlert=strAlert+"“考试年”请输入阿拉伯数字整数";
			ii=1;
		}
	}
	if(ii==0){
		doSelect(examYear);
	}
	else{
		alert(strAlert);
	}
}