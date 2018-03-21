//jsȥ���ַ����Ŀո�
//ȥ��ո�;
function ltrim(s)
{
	return s.replace(/(^\s*)/g, "");
}
//ȥ�ҿո�;
function rtrim(s)
{
	return s.replace(/(\s*$)/g, "");
}
//ȥ���ҿո�;
function trim(s){
	return rtrim(ltrim(s)); 
}


//��֤��
function isNull(str)
{
    var reg = /^\s*$/ ;
    return reg.test(str);   
}

//�����û���,ȡֵ��ΧΪa-z,A-Z,0-9
function CheckAccountStr(str)
{   
	var reg = /^[a-zA-Z]+[A-Za-z0-9]*$/;
	return reg.test(str);
}

//��֤�û�����,������A-Z,a-z,0-9,!@#$%^&*(){}"':;><,./\~`+=_- ,5-16λ
function CheckPassword(str)
{
	var reg = /^\S{6,16}$/;
	return reg.test(str);
}

//ͼƬ��֤��,4λ������֤��
function CheckImageCode(str)
{
	var reg = /^\d{4,4}$/;
	return reg.test(str);
}
//��֤���ڵ绰����
//��ʽ��029-87676767 ��029-87888822 �� 029-44055520-555 �� (029)4405222 �� 02981343434
//���ų���3-4λ��������"0"��ͷ��������7-8λ     
function CheckPhoneNum(str)
{
	//var reg=/^(([0]\d{2,3})|[0]\d{2,3}|[0]\d{2,3}-){1}\d{7,8}(-\d{3,4})*$/;
	var reg = /^\d{3,4}-\d{7,8}$/;
	return reg.test(str);
}

//��֤���ڵ绰����(�ֻ�)   
//��ʽ��13125698625����11λ��������"0"��ͷ 
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

// ��֤���֤���룺15��18λ����������ɣ�������0��ͷ
function CheckIdCard(str)
{
	var reg=/^[1-9](\d{14}|\d{17}|\d{16}(X|x|S|s))$/ ;
	return reg.test(str);
} 

//��ַ��֤  
//http://www.test.com �� http://163.com
function CheckWebSite(str)
{
	var reg = /^([\w-]+\.)+[\w-]+(\/[\w- .\/?%&=]*)?$/ ;
	return reg.test(str);
}

//��֤�������룬6λ
function CheckPostalcode(str)
{
	var reg = /^\d{6}$/;
	return reg.test(str);
}

//���email���� 
function isEmail(str)
{
	var reg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/; 
 	return reg.test(str); 
}

//��֤���ڣ�2008-01-03 �� 08-01-03
function CheckDate(str)
{
	var reg = /^(\d{4})-((0([1-9]{1}))|(1[0|1|2]))-((([0][1-9]{1})|([1-2]([0-9]{1})))|(3[0|1]))$/;
	return reg.test(str);
}

//�鿴IP��ַ�Ƿ�Ϸ� 
function CheckIP(str)
{
	var reg = /^[1-2]\d{0,2}\.[0-2]\d{0,2}.[0-2]\d{0,2}\.[1-9]\d{0,2}(,[1-2]\d{0,2}\.[0-2]\d{0,2}.[0-2]\d{0,2}\.[1-9]\d{0,2})*$/;
	
	return reg.test(str);
} 

//��ʵ����,����Ϊ���֣��ּ䲻��
function CheckRealName(str)
{
	var reg=/^\S(([\u4e00-\u9fa5]{1,})|([?]{3,})|([\u4e00-\u9fa5]{0,}([?]{1,}))|(([?]{1,})([\u4e00-\u9fa5]{0,}))|(([?]{1,})([\u4e00-\u9fa5]{0,})([?]{1,})))$/;
	return reg.test(str);
}

//��������,��������������0��������������0��ͷ   
function CheckInteger(str)
{
	var reg=/^([1-9]+\d*)|(0{1})$/ ;
	return reg.test(str);
}
//��֤��������yyyy-MM-dd��yyyy-MM-dd
function CheckDate1(str){
	var reg = /[1-9]\d\d\d-\d\d-\d\d��[1-9]\d\d\d-\d\d-\d\d/;
	return reg.test(str);
}
// Ĭ��ʱ����������
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

// ��ȡ�ַ������ȣ�ÿ�����ֵĳ���Ϊ2
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

//��֤�����ַ��Ƿ�Ϸ� 
function CheckStr(str)
{
	var reg = /[\'\"\\\/?<>\;&]+/;
	
	return reg.test(str);
}

//��֤��������
function CheckFloat(str)
{
	var reg=/^[1-9]\d{0,}(\.\d+)?$/;
	return reg.test(str);
}

/**��ⵥѡ�����Ƿ�ѡ����ֵ   **/
/** radioName:����ҳ��ҪУ��ĵ�ѡ������� name="radioName"  ���ؽ��   true:�Ѿ���ѡ��  false:û��ѡ��ѡ���ֵ    **/
function checkRadio(radioName,msg) {
	var radioValues = document.getElementsByName(radioName);
	var checkValue = '';
	for(var i=0;i<radioValues.length;i++) {
		if(radioValues[i].checked) {
			return true;//�е�ѡ��ѡ���ˣ��򷵻�true
		}
	}
	alert(msg);
	return false;//û��ѡ��ѡ���򷵻�false
}

/*
* aDate����Ҫ�Ƚϵ����ڣ�curDate����ǰ���ڣ�days���������
* true����ͨ����false��ͨ��
*/
function ckDate(aDate,curDate,days){
	// ָ�����ʱ��ĺ�����
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
* aDate����Ҫ�Ƚϵ����ڣ�curDate����ǰ���ڣ�years��������
* true��С�ڼ����ݣ�false�����ڼ�����
*/
function checkDate(aDate,curDate,years){
	// ָ�����ʱ��ĺ�����
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

/**�������뺯��   Dight:Ҫ�������ֵ   how:������С��λ��   **/
function forDight(Dight,how) {
	Dight = Math.round(Dight*Math.pow(10,how))/Math.pow(10,how);
	return Dight;
}

/*
* �������֤�������ó�������
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
		if('1' == cardType){ // �����֤
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