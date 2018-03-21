/*
 * 下拉框排序向上移
 */
function doup() {
	var s = document.getElementById("sob");
	var va = s.value;
	var array = new Array();
	for ( var i = 0; i < s.options.length; i++) {
		if (s.options[i].value == va && i != 0 && s.selectedIndex != 0) {
			var o = s.options[i - 1];// 上一个option
			var co = s.options[i];// 当前的option
			var ttem = "";// 临时text值
			var o_values = o.value.split(",");// 将下一个option的value值裁为数组
			var co_values = co.value.split(",");// 将当前option的value值裁为数组
			var o_index = o.index;
			var co_index = co.index;
			ttem = co.text;// 将当前option的text赋给临时变量
			co.value = o_values[0] + "," + (parseInt(co_index) + 1);
			o.value = co_values[0] + "," + (parseInt(o_index) + 1);
			co.text = o.text;
			o.text = ttem;
			o.selected = true;
		} else {
			s.options[i].value = s.options[i].value + ","
					+ (parseInt(s.options[i].index) + 1);
		}
	}
}

/*
 * 下拉框排序向下移
 */
function dodown() {
	var s = document.getElementById("sob");
	var va = s.value;
	var array = new Array();
	var count = 0;
	for ( var i = 0; i < s.options.length; i++) {
		count++;
	}
	for ( var i = 0; i < s.options.length; i++) {
		if (s.options[i].value == va && i != count - 1
				&& s.selectedIndex != count - 1) {
			var o = s.options[i + 1];// 上一个option
			var co = s.options[i];// 当前的option
			var ttem = "";// 临时text值
			var o_values = o.value.split(",");// 将下一个option的value值裁为数组
			var co_values = co.value.split(",");// 将当前option的value值裁为数组
			var o_index = o.index;
			var co_index = co.index;
			ttem = co.text;// 将当前option的text赋给临时变量
			co.value = o_values[0] + "," + (parseInt(co_index) + 1);
			o.value = co_values[0] + "," + (parseInt(o_index) + 1);
			co.text = o.text;
			o.text = ttem;
			o.selected = true;
		} else {
			s.options[i].value = s.options[i].value + ","
					+ (parseInt(s.options[i].index) + 1);
		}
	}
}

/*
 * 排序后提交
 */
function dosubmit() {
	var s = document.getElementById("sob");
	var str = "";
	var wz = "";
	for ( var i = 0; i < s.options.length; i++) {
		wz = s.options[i].value.indexOf(",");
		str += "<input type='hidden' name='proSyllabus.substituteCode' value='"
				+ s.options[i].value.substring(0, wz) + "'>";
	}
	document.getElementById("sort").innerHTML = str;
	return true;
}

/*
 * checkbox全选 id:全选时要点的checkbox name:要全选项的checkbox
 */
function selectAll(id, name) {
	var id_ = document.getElementById(id);
	var names = document.getElementsByName(name);
	for ( var i = 0; i < names.length; i++) {
		if (id_.checked) {
			names[i].checked = true;
		} else {
			names[i].checked = false;
		}
	}
}

/*
 * checkbox反选 checkbox:checkbox的name
 */
function fanXuan(checkbox) {
	var names = document.getElementsByName(name);
	for ( var i = 0; i < names.length; i++) {
		if (names[i].checked) {
			names[i].checked = false;
		} else {
			names[i].checked = true;
		}
	}
}
