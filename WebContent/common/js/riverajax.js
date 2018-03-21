
var keyStr = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=";
function encode64(input) {
	input = escape(input);
	var output = "";
	var chr1, chr2, chr3 = "";
	var enc1, enc2, enc3, enc4 = "";
	var i = 0;
	do {
		chr1 = input.charCodeAt(i++);
		chr2 = input.charCodeAt(i++);
		chr3 = input.charCodeAt(i++);
		enc1 = chr1 >> 2;
		enc2 = ((chr1 & 3) << 4) | (chr2 >> 4);
		enc3 = ((chr2 & 15) << 2) | (chr3 >> 6);
		enc4 = chr3 & 63;
		if (isNaN(chr2)) {
			enc3 = enc4 = 64;
		} else {
			if (isNaN(chr3)) {
				enc4 = 64;
			}
		}
		output = output + keyStr.charAt(enc1) + keyStr.charAt(enc2) + keyStr.charAt(enc3) + keyStr.charAt(enc4);
		chr1 = chr2 = chr3 = "";
		enc1 = enc2 = enc3 = enc4 = "";
	} while (i < input.length);
	return output;
}
function decode64(input) {
	var output = "";
	var chr1, chr2, chr3 = "";
	var enc1, enc2, enc3, enc4 = "";
	var i = 0;
 
     // remove all characters that are not A-Z, a-z, 0-9, +, /, or =
	var base64test = /[^A-Za-z0-9\+\/\=]/g;
	if (base64test.exec(input)) {
		alert("There were invalid base64 characters in the input text.\n" + "Valid base64 characters are A-Z, a-z, 0-9, '+', '/',and '='\n" + "Expect errors in decoding.");
	}
	input = input.replace(/[^A-Za-z0-9\+\/\=]/g, "");
	do {
		enc1 = keyStr.indexOf(input.charAt(i++));
		enc2 = keyStr.indexOf(input.charAt(i++));
		enc3 = keyStr.indexOf(input.charAt(i++));
		enc4 = keyStr.indexOf(input.charAt(i++));
		chr1 = (enc1 << 2) | (enc2 >> 4);
		chr2 = ((enc2 & 15) << 4) | (enc3 >> 2);
		chr3 = ((enc3 & 3) << 6) | enc4;
		output = output + String.fromCharCode(chr1);
		if (enc3 != 64) {
			output = output + String.fromCharCode(chr2);
		}
		if (enc4 != 64) {
			output = output + String.fromCharCode(chr3);
		}
		chr1 = chr2 = chr3 = "";
		enc1 = enc2 = enc3 = enc4 = "";
	} while (i < input.length);
	return unescape(output);
}



//msg class object
function AjaxMsg(p_code, p_msg) {
	this.code = p_code;
	this.msg = p_msg;
	this.names=null;
	this.setCode = function (p_code) {
		this.code = p_code;
	};
	this.setMsg = function (p_msg) {
		this.msg = p_msg;
	};
	this.showMsg = function (p_str) {
		alert(p_str);
	};
}

//create ajax request object
function AjaxRequestCreate() {
	if (window.XMLHttpRequest) {
		return new XMLHttpRequest();
	}
	if (window.ActiveXObject) {
		var arr_ax = new Array(8);
		arr_ax[0] = "MSXML2.XMLHTTP.6.0";
		arr_ax[1] = "MSXML2.XMLHTTP.5.0";
		arr_ax[2] = "MSXML2.XMLHTTP.4.0";
		arr_ax[3] = "MSXML2.XMLHTTP.3.0";
		arr_ax[4] = "MSXML2.XMLHTTP.2.6";
		arr_ax[5] = "MSXML2.XMLHTTP";
		arr_ax[6] = "Microsoft.XMLHTTP";
		arr_ax[7] = "MSXML.XMLHTTP";
		for (var i = 0; i < arr_ax.length; i++) {
			try {
				return new ActiveXObject(arr_ax[i]);
			}
			catch (e) {
			}
		}
	}
	return null;
}



//invoke ajax request for single
function AjaxRequestDo(name, exp, value) {
	var postData = "name=" + (name == null || name == "" ? "" : encode64(name)) + "&exp=" + (exp == null || exp == "" ? "" : encode64(exp)) + "&value=" + (value == null || value == "" ? "" : encode64(value));
	return AjaxRequestDoArray(postData);
}

//invoke ajax request for array
function AjaxRequestDoArray(urlpostdata) {
	var ajaxo = AjaxRequestCreate();
	var am = new AjaxMsg(false, "");
	if (ajaxo == null) {
		am.setMsg("Failed create ajax object");
		return am;
	}
	ajaxo.open("POST", validate_address_url, false);
	var postData = urlpostdata;
	ajaxo.setRequestHeader("Content-Length", postData.length);
	ajaxo.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	ajaxo.send(postData);
	if (ajaxo.readyState != 4) {
		am.setMsg("Doing...(" + ajaxo.readyState + ")");
		return am;
	}
	if (ajaxo.status != 200) {
		am.setMsg("http request error(" + ajaxo.status + ")\r\n" + ajaxo.responseText);
		return am;
	}
	if (ajaxo.responseText == null || ajaxo.responseText == "") {
		am.setMsg("return value is null");
		return am;
	}
	var rsarr = ajaxo.responseText.split("\r\n");
	if (rsarr[0].toUpperCase() == "OK") {
		am.setCode(true);
		am.setMsg("");
		return am;
	}
	if (rsarr[0].toUpperCase() == "ERROR") {
		am.setCode(false);
		am.setMsg(rsarr[1]);
		am.names=rsarr[2];
		return am;
	}
	return am;
}


//circle single check,validate in muli
function RiverDoCheck(arr) {
	try {
		var am;
		for (var i = 0; i < arr.length; i++) {
			am = AjaxRequestDo(arr[i][0], arr[i][1], arr[i][2]);
			if (am.code == false) {
				alert(arr[i][3]);
				try {
					document.getElementsByName(arr[i][4])[0].select();//select ele by id
					document.getElementsByName(arr[i][4])[0].focus();//focus ele by id
				}
				catch (e) {
				}
				return false;
			}
		}
		return true;
	}
	catch (e) {
		alert("parameters error:" + e);
		return false;
	}
}
//array check,validate in a http request
function RiverDoCheckArray(arr,autoalert) {
	try {
		var namestr = "";
		var expstr = "";
		var valuestr = "";
		for (var i = 0; i < arr.length; i++) {
			namestr += "," + (arr[i][0] == null || arr[i][0] == "" ? "" : encode64(arr[i][0]));
			expstr += "," + (arr[i][1] == null || arr[i][1] == "" ? "" : encode64(arr[i][1]));
			valuestr += "," + (arr[i][2] == null || arr[i][2] == "" ? "" : encode64(arr[i][2]));
			setvr(arr[i][4],'');
		}
		namestr = namestr.substring(1);
		expstr = expstr.substring(1);
		valuestr = valuestr.substring(1);
		var am;
		var focused=false;
		am = AjaxRequestDoArray("name=" + namestr + "&exp=" + expstr + "&value=" + valuestr);
		if (am.code == false) {
			for (var i = 0; i < arr.length; i++) {
//				if (am.msg == arr[i][0]) {
				if (am.names.indexOf('*{'+arr[i][0]+'}*')>=0) {
					if(autoalert&&(focused==false)){
						alert(arr[i][3]);
					}
					try {
						if(focused==false){
							focused=true;
							document.getElementsByName(arr[i][4])[0].select();//select ele by id
							document.getElementsByName(arr[i][4])[0].focus();//focus ele by id
						}
						if(autoalert==false)
							setvr(arr[i][4],validate_error_prefix+arr[i][3]);
					}
					catch (e) {
					}
//					alert(arr[i][3]);
//					return false;
				}else{
					if(autoalert==false)
						setvr(arr[i][4],validate_ok_text);
				}
			}
			return false;
		}
		return true;
	}
	catch (e) {
		alert("parameters error:" + e);
		return false;
	}
}

function setvr(name,msg){
	var temp=document.getElementById('_vr_'+name);
	if(temp!=null)
		document.getElementById('_vr_'+name).innerText=msg;
}

function confirm2times(msg){
	if(confirm(msg))
		return confirm(confirm_2times_prefix+msg);
	else
		return false;
}

function confirm_delete(){
	return confirm2times(confirm_msg_delete);
}

function confirm_update(){
	return confirm2times(confirm_msg_update);
}

function confirm_insert(){
	return confirm2times(confirm_msg_insert);
}