/*
 * ����������������
 */
function doup() {
	var s = document.getElementById("sob");
	var va = s.value;
	var array = new Array();
	for ( var i = 0; i < s.options.length; i++) {
		if (s.options[i].value == va && i != 0 && s.selectedIndex != 0) {
			var o = s.options[i - 1];// ��һ��option
			var co = s.options[i];// ��ǰ��option
			var ttem = "";// ��ʱtextֵ
			var o_values = o.value.split(",");// ����һ��option��valueֵ��Ϊ����
			var co_values = co.value.split(",");// ����ǰoption��valueֵ��Ϊ����
			var o_index = o.index;
			var co_index = co.index;
			ttem = co.text;// ����ǰoption��text������ʱ����
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
 * ����������������
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
			var o = s.options[i + 1];// ��һ��option
			var co = s.options[i];// ��ǰ��option
			var ttem = "";// ��ʱtextֵ
			var o_values = o.value.split(",");// ����һ��option��valueֵ��Ϊ����
			var co_values = co.value.split(",");// ����ǰoption��valueֵ��Ϊ����
			var o_index = o.index;
			var co_index = co.index;
			ttem = co.text;// ����ǰoption��text������ʱ����
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
 * ������ύ
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
 * checkboxȫѡ id:ȫѡʱҪ���checkbox name:Ҫȫѡ���checkbox
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
 * checkbox��ѡ checkbox:checkbox��name
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
