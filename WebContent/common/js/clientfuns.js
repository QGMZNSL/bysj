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
//得到字符串长度 
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
//检测网址
function CheckWebSite(str)
{

	var reg = /^([\w-]+\.)+[\w-]+(\/[\w- .\/?%&=]*)?$/ ;
	return reg.test(str);
}
function IsURL(str_url){
     var tomatch= /http:\/\/[A-Za-z0-9\.-]{3,}\.[A-Za-z]{3}/ ;   
     if (tomatch.test(str_url)){ 
        return true; //符合
      }else {  
       return false;//不符合
   }  
}

//检测是否有非法字符
function CheckStr(str)
{
	var reg = /[\'\"\\\/?<>\;&]+/;
	
	return reg.test(str);
}
// 检查单选按钮组是否有选中
// 若有选中返回true，否则返回false
function checkRadio(name) {
	var result = false;
	var radios = document.getElementsByName(name);
	for (var i = 0; i < radios.length; i++) {
		if (radios[i].checked == true) {
			result = true;
			break;
		}
	}
	return result;
}

// 检查单选按钮组是否有选中
// 若有选中返回true，否则返回false
function checkBoxs(name) {
	var result = false;
	var boxs = document.getElementsByName(name);
	for (var i = 0; i < boxs.length; i++) {
		if (boxs[i].checked == true) {
			result = true;
			break;
		}
	}
	return result;
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

//验证空
function isNull(str)
{
    var reg = /^\s*$/ ;
    return reg.test(str);   
}

//检验整数,适用于正整数、0，正整数不能以0开头   
function CheckInteger(str)
{
	var reg=/^(([1-9][0-9]*)|0)$/ ;
	return reg.test(str);
}

// 全选和全不选功能，通过一个checkbox控制多个checkbox的状态
// id-主控checkbox属性id，name-被控checkbox属性name
function selectAll(id,name) {
	var a = document.getElementById(id);
	var aresult = a.checked;
	var checkbox = document.getElementsByName(name);
	for (var i = 0; i < checkbox.length; i++) {
		// 全选/全不选
		//checkbox[i].checked=aresult;
		
		// 反选
		checkbox[i].checked=!checkbox[i].checked;
	}
	
}

/* 
 * 限制文本框输入，只允许输入 0 - 99.99之间的数值
 * 传入参数：input框的value
 * 用法示例：<input type="text" onkeydown="return numValidate(this.value);" />
 */
function  numValidate( content) {
	var ret = false;
	var regZero = /^0{1}(\.[0-9]{0,2})?$/;	// 以0开头时
	var regex = /^[0-9]{1,2}?(\.[0-9]{0,2})?$/;	// 匹配0-99.99
	if ( event.keyCode == 8)
	{
		ret = true;
	} else {
		if ((event.keyCode >= 96 && event.keyCode <= 105)) {
			var val = content + String.fromCharCode((event.keyCode - 48));
		} else if ( event.keyCode == 110 || event.keyCode == 190) {
			var val = content + ".";
		} else {
			var val = content + String.fromCharCode(event.keyCode);
		}
		
		if ( val.match(regex)) {
			// 如果以0开头，则匹配regZero
			if ( "0" == val.substring(0,1)) {
				if ( val.match(regZero)) {
					ret = true;
				} else {
					ret = false;
				}
			} else {
				ret = true;
			}
		} else {
			ret = false;
		}
	}
	return ret;
}

/*
 * 多选框的反选实现
 * cboxName 多选框的name属性
 */
function cboxCheckOpposite( cboxName) {
	var cboxes = document.getElementsByName( cboxName);
	var tmpBox;
	for ( var i = 0; i < cboxes.length; i++) {
		tmpBox = cboxes[i];
		if ( tmpBox.disabled == true || tmpBox.disabled == "disabled") continue;
		tmpBox.checked ^= true;
	}
}

/*
 * 多选框的反选实现（这个方法必须依赖于jquery-1.3.2.min.js）
 * cboxClassName 多选框的class名
 */
function cboxCheckOppositeByClassName( cboxClassName) {
	var cboxes = $('.' + cboxClassName);
	for ( var i = 0; i < cboxes.length; i++) {
		if ( $(cboxes[i]).attr("disabled") == true) continue;
		$(cboxes[i]).attr("checked", $(cboxes[i]).attr("checked") ^ true);
	}
}