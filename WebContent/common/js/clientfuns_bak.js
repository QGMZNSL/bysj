//js去掉字符串的空格
//去左空格;
function ltrim(s)
{
	return s.replace(/(^\s*)/g, "");
}
//去右空格;
function rtrim(s)
{
	return s.replace(/(\s*$)/g, "");
}
//去左右空格;
function trim(s){
	return rtrim(ltrim(s)); 
}


//验证空
function isNull(str)
{
    var reg = /^\s*$/ ;
    return reg.test(str);   
}

//检验用户名,取值范围为a-z,A-Z,0-9
function CheckAccountStr(str)
{   
	var reg = /^[a-zA-Z]+[A-Za-z0-9]*$/;
	return reg.test(str);
}

//验证用户密码,允许有A-Z,a-z,0-9,!@#$%^&*(){}"':;><,./\~`+=_- ,5-16位
function CheckPassword(str)
{
	var reg = /^\S{6,16}$/;
	return reg.test(str);
}

//图片验证码,4位数字验证码
function CheckImageCode(str)
{
	var reg = /^\d{4,4}$/;
	return reg.test(str);
}
//验证国内电话号码
//格式：029-87676767 或029-87888822 或 029-44055520-555 或 (029)4405222 或 02981343434
//区号长度3-4位，必须以"0"开头，号码是7-8位     
function CheckPhoneNum(str)
{
	//var reg=/^(([0]\d{2,3})|[0]\d{2,3}|[0]\d{2,3}-){1}\d{7,8}(-\d{3,4})*$/;
	var reg = /^\d{3,4}-\d{7,8}$/;
	return reg.test(str);
}

//验证国内电话号码(手机)   
//格式：13125698625，共11位，不能是"0"开头 
function CheckMovePhone(str)
{
	var reg=/^[1][\d]{10}$/;
	var reg1=/^[\d]{8}$/
	if (reg.test(str)){
		return reg.test(str);
	}else{
		return reg1.test(str);
	}
} 

// 验证身份证号码：15或18位，由数字组成，不能以0开头
function CheckIdCard(str)
{
	var reg=/^[1-9](\d{14}|\d{17}|\d{16}(X|x|S|s))$/ ;
	return reg.test(str);
} 

//网址验证  
//http://www.test.com 或 http://163.com
function CheckWebSite(str)
{
	var reg = /^([\w-]+\.)+[\w-]+(\/[\w- .\/?%&=]*)?$/ ;
	return reg.test(str);
}

//验证邮政编码，6位
function CheckPostalcode(str)
{
	var reg = /^\d{6}$/;
	return reg.test(str);
}

//检查email邮箱 
function isEmail(str)
{
	var reg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/; 
 	return reg.test(str); 
}

//验证日期，2008-01-03 或 08-01-03
function CheckDate(str)
{
	var reg = /^(\d{4})-((0([1-9]{1}))|(1[0|1|2]))-((([0][1-9]{1})|([1-2]([0-9]{1})))|(3[0|1]))$/;
	return reg.test(str);
}

//查看IP地址是否合法 
function CheckIP(str)
{
	var reg = /^[1-2]\d{0,2}\.[0-2]\d{0,2}.[0-2]\d{0,2}\.[1-9]\d{0,2}(,[1-2]\d{0,2}\.[0-2]\d{0,2}.[0-2]\d{0,2}\.[1-9]\d{0,2})*$/;
	
	return reg.test(str);
} 

//真实姓名,必须为汉字，字间不空
function CheckRealName(str)
{
	var reg=/^\S(([\u4e00-\u9fa5]{1,})|([?]{3,})|([\u4e00-\u9fa5]{0,}([?]{1,}))|(([?]{1,})([\u4e00-\u9fa5]{0,}))|(([?]{1,})([\u4e00-\u9fa5]{0,})([?]{1,})))$/;
	return reg.test(str);
}

//检验整数,适用于正整数、0，正整数不能以0开头   
function CheckInteger(str)
{
	var reg=/^([1-9]+\d*)|(0{1})$/ ;
	return reg.test(str);
}
//验证工作进度yyyy-MM-dd至yyyy-MM-dd
function CheckDate1(str){
	var reg = /[1-9]\d\d\d-\d\d-\d\d至[1-9]\d\d\d-\d\d-\d\d/;
	return reg.test(str);
}
// 默认时间区间设置
function defaultDate(starDate, endDate) {
	var date = new Date();
	var defEndDate = date.getYear() + '-';
	if (date.getMonth() < 9) {
		defEndDate = defEndDate + '0' + (date.getMonth() + 1);
	} else {
		defEndDate = defEndDate + (date.getMonth() + 1);
	}
	defEndDate = defEndDate + '-';
	if (date.getDate() < 10) {
		defEndDate = defEndDate + '0' + date.getDate();
	} else {
		defEndDate = defEndDate + date.getDate();
	}
	document.getElementById(endDate).value = defEndDate;
	
	date.setDate(date.getDate() - 6);
	defStarDate = date.getYear() + '-';
	if (date.getMonth() < 9) {
		defStarDate = defStarDate + '0' + (date.getMonth() + 1);
	} else {
		defStarDate = defStarDate + (date.getMonth() + 1);
	}
	var defStarDate = defStarDate + '-';
	if (date.getDate() < 10) {
		defStarDate = defStarDate + '0' + date.getDate();
	} else {
		defStarDate = defStarDate + date.getDate();
	}
	document.getElementById(starDate).value = defStarDate;
}

// 获取字符串长度，每个汉字的长度为2
function getStrLen(str){
	var len = 0;
	for(var i = 0; i < str.length; i++){
		if(str.charCodeAt(i) > 255){
			len += 2;
		}else{
			len += 1;
		}					
	}
	return len;
}

//验证数据字符是否合法 
function CheckStr(str)
{
	var reg = /[\'\"\\\/?<>\;&]+/;
	
	return reg.test(str);
}

//验证正浮点数
function CheckFloat(str)
{
	var reg=/^[1-9]\d{0,}(\.\d+)?$/;
	return reg.test(str);
}

/**检测单选框中是否选择了值   **/
/** radioName:传递页面要校验的单选框的名称 name="radioName"  返回结果   true:已经有选择  false:没有选择单选框的值    **/
function checkRadio(radioName,msg) {
	var radioValues = document.getElementsByName(radioName);
	var checkValue = '';
	for(var i=0;i<radioValues.length;i++) {
		if(radioValues[i].checked) {
			return true;//有单选框选择了，则返回true
		}
	}
	alert(msg);
	return false;//没有选择单选框，则返回false
}

/*
* aDate：需要比较的日期；curDate：当前日期，days：间隔天数
* true：不通过，false：通过
*/
function ckDate(aDate,curDate,days){
	// 指定间隔时间的毫秒数
	var timeLimit = (days)*24*60*60*1000;
	
	aDate = aDate.replace('-','/');
	curDate = curDate.replace('-','/');
	var from = Date.parse(aDate);
	var to = Date.parse(curDate);
	
	var times = to-from;			
	if(timeLimit >= times){
		return true;
	}else{
		return false;
	}
}

/*
* aDate：需要比较的日期；curDate：当前日期，years：间隔年份
* true：小于间隔年份，false：大于间隔年份
*/
function checkDate(aDate,curDate,years){
	// 指定间隔时间的毫秒数
	var timeLimit = years*365*24*60*60*1000;
	
	aDate = aDate.replace('-','/');
	curDate = curDate.replace('-','/');
	var from = Date.parse(aDate);
	var to = Date.parse(curDate);
	
	var times = to-from;				
	if(timeLimit >= times){
		return true;
	}else{
		return false;
	}
}

/**四舍五入函数   Dight:要处理的数值   how:保留的小树位数   **/
function forDight(Dight,how) {
	Dight = Math.round(Dight*Math.pow(10,how))/Math.pow(10,how);
	return Dight;
}

/*
* 根据身份证号码设置出生日期
*/
function setBirthday(idno,idtype,birthday){
	try{
		var no = trim(document.getElementById(idno).value);
		var birth = document.getElementById(birthday);
		var type = document.getElementsByName(idtype);
		var birthdayValue = "";
		var cardType = '';
		for(var t = 0;t<type.length;t++){
			if(type[t].checked){
				cardType = type[t].value.substring(0,1);
			}
		}
		if('1' == cardType){ // 是身份证
			if(no.length == 15){
				birthdayValue = '19'+no.substring(6,8)+'-'+no.substring(8,10)+'-'+no.substring(10,12); 
			} else if (no.length == 18) {
				birthdayValue = no.substring(6,10)+'-'+no.substring(10,12)+'-'+no.substring(12,14);
			}	
		}
		
		birth.value = birthdayValue;
	}catch(e){
		alert(e);
	}
}

// 