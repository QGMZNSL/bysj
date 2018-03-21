package com.sincinfo.zxks.common.util;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

public class StringTool {

	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	/**
	 * 判断输入字符串是否为空
	 * 
	 * @param src
	 *            原字符串
	 * @return false: 不为空 true:为空
	 */
	public static boolean isEmpty(String src) {
		return (src == null || "".equals(src.trim()));
	}

	/**
	 * 判断对象是否为null
	 * 
	 * @param obj
	 *            对象
	 * @return false: 不为null true:为null
	 */
	public static boolean isEmpty(Object obj) {
		return (obj == null || "".equals(obj));

	}

	/**
	 * 将null转换成空字符串""
	 * 
	 * @param src
	 *            原字符串
	 * @return 处理结果
	 */
	public static String nullToEmpty(String src) {
		return src == null ? "" : src.trim();
	}

	/**
	 * 截取src右边的len长度的子字符串
	 * 
	 * @param src
	 *            源字符串
	 * @param len
	 *            截取长度
	 * @return 结果字符串
	 */
	public static String rightSubString(String src, int len) {
		String result = "";
		if (isEmpty(src)) {
			result = "";
		} else if (len >= src.length()) {
			result = src;
		} else {
			result = src.substring(src.length() - len, src.length());
		}
		return result;
	}

	/**
	 * 截取字符串
	 * 
	 * @param srcStr
	 *            源字符串
	 * @param length
	 *            长度
	 * @return 处理结果
	 */
	public static String subString(String srcStr, int length) {
		if (srcStr == null || "".equals(srcStr)) {
			return "";
		}

		char[] cs = srcStr.toCharArray();
		int charLen = 0;
		for (int i = 0; i < srcStr.length(); i++) {
			if ((int) cs[i] > 255) { // 非英数字
				charLen += 2;
			} else { // 英数字
				charLen++;
			}
		}

		if (charLen <= length) {
			return srcStr;
		} else {
			StringBuilder sb = new StringBuilder();
			int index = 0;

			for (int i = 0; i < length - 3;) {
				sb.append(cs[index]);
				if ((int) cs[index] > 255) { // 非英数字
					i += 2;
				} else { // 英数字
					i++;
				}
				index++;
			}
			sb.append("...");
			return sb.toString();
		}

	}

	/**
	 * 格式化日期，格式为：2009年08月28日
	 * 
	 * @param date
	 *            日期格式为2009-08-28 12:12:12.0或2009/08/28 12:12:12.0
	 * @param len
	 *            月，日长度
	 * @return 格式化结果
	 */
	public static String formatDate(String date, int len) {
		StringBuilder result;
		try {
			result = new StringBuilder();
			if (!isEmpty(date)) {
				String day = date.split(" ")[0];
				String[] ss;

				// 判断日期格式:2009-08-28 or 2009/08/28
				if (day.indexOf("-") != -1) {
					ss = day.split("-");
				} else {
					ss = day.split("/");
				}

				// 设置年月日
				result.append(LFill(ss[0], 4));
				result.append("年");
				if (ss[1].startsWith("0")) {
					result.append(LFill(ss[1].replaceFirst("0", ""), len));
				} else {
					result.append(LFill(ss[1], len));
				}
				result.append("月");
				if (ss[2].startsWith("0")) {
					result.append(LFill(ss[2].replaceFirst("0", ""), len));
				} else {
					result.append(LFill(ss[2], len));
				}
				result.append("日");
			}
		} catch (Exception e) {
			System.out.println("日期格式错误");
			result = new StringBuilder(date);
		}

		return result.toString();
	}

	/**
	 * 字符串左填充<br>
	 * 
	 * @param src
	 *            原字符串
	 * @param len
	 *            长度
	 * @return 如果src长度大于或等于len，返回src<br>
	 *         如果src长度小于len，将空格填充到src的左边
	 */
	public static String LFill(String src, int len) {
		StringBuilder sb;
		if (src.length() < len) {
			sb = new StringBuilder();
			int count = len - src.length();
			for (int i = 0; i < count; i++) {
				sb.append(" ");
			}
			sb.append(src);
		} else {
			sb = new StringBuilder(src);
		}
		return sb.toString();
	}

	public static String getStringDate() {

		return sdf.format(new Date());
	}

	/**
	 * 字符串左填充<br>
	 * 将c填充到src的左边
	 * 
	 * @param src
	 *            原字符串
	 * @param len
	 *            长度
	 * @param c
	 *            填充字符
	 * @return 如果src长度大于或等于len，返回src<br>
	 *         如果src长度小于len，将空格填充到src的左边
	 */
	public static String LFill(String src, int len, String c) {
		StringBuilder sb;
		src = src == null ? "" : src.trim();
		if (src.length() < len) {
			sb = new StringBuilder();
			int count = len - src.length();
			for (int i = 0; i < count; i++) {
				sb.append(c);
			}
			sb.append(src);
		} else {
			sb = new StringBuilder(src);
		}
		return sb.toString();
	}

	/**
	 * 字符串右填充<br>
	 * 将c填充到src的右边
	 * 
	 * @param src
	 *            原字符串
	 * @param len
	 *            长度
	 * @param c
	 *            填充字符
	 * @return 如果src长度大于或等于len，返回src<br>
	 *         如果src长度小于len，将空格填充到src的右边
	 */
	public static String RFill(String src, int len, String c) {
		StringBuilder sb;
		src = src == null ? "" : src.trim();
		if (src.length() < len) {
			sb = new StringBuilder();
			int count = len - src.length();
			sb.append(src);
			for (int i = 0; i < count; i++) {
				sb.append(c);
			}
		} else {
			sb = new StringBuilder(src);
		}
		return sb.toString();
	}

	/**
	 * 汉字非空判断
	 * 
	 * @param str
	 *            源字符串
	 * @return true:空 false:不为空
	 */
	public static boolean isEmptyChar(String str) {
		if (isEmpty(str)) {
			return true;
		} else {
			str = str.replace("　", "");
			return isEmpty(str.trim());
		}
	}

	/**
	 * 根据名称返回代号
	 * 
	 * @param str
	 *            源字符串
	 * @return 代号
	 */
	public static String getCode(String str) {
		String result = "";
		if (!isEmpty(str)) {
			if (str.indexOf("-") != -1) {
				result = str.trim().split("-")[0];
			}
		}
		return result;
	}

	/**
	 * 根据日期返回指定格式的日期字符串
	 * 
	 * @param aDate
	 *            日期
	 * @param format
	 *            格式
	 * @return 日期字符串
	 */
	public static String getDate(Date aDate, String format) {
		String result;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			result = sdf.format(aDate);
		} catch (Exception e) {
			result = "";
		}
		return result;
	}

	/**
	 * 根据日期返回特定格式的日期字符串
	 * 
	 * @param aDate
	 *            日期
	 * @return 日期字符串
	 */
	public static String getDate(Date aDate) {
		String result;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			result = sdf.format(aDate);
		} catch (Exception e) {
			result = "";
		}
		return result;
	}

	/**
	 * 去掉str两边的空格
	 * 
	 * @param str
	 *            原字符串
	 * @return 处理后的字符串
	 */
	public static String trim(String str) {
		str = nullToEmpty(str);
		return str.trim();
	}

	/**
	 * 将Object转换为字符串
	 * 
	 * @param obj
	 * @return
	 */
	public static String trim(Object obj) {
		return obj == null ? "" : obj.toString().trim();
	}

	/**
	 * 返回date距今天的天数
	 * 
	 * @param date
	 *            要比较的日期
	 * @return 天数
	 */
	@SuppressWarnings("deprecation")
	public static int compareDate(java.sql.Date date) {

		return (int) (new Date().getTime() - date.getTime())
				/ (24 * 60 * 60 * 1000);
	}

	/**
	 * 过滤字符串数组中重复的字符串
	 * 
	 * @param strs
	 *            字符串数组
	 * @return 处理结果
	 */
	public static List<String> sortStrs(String[] strs) {
		List<String> list = new ArrayList<String>();
		for (String s : strs) {
			if (!list.contains(s)) {
				list.add(s);
			}
		}

		return list;
	}

	/**
	 * 截取日期
	 * 
	 * @param dateTime：日期+时间
	 * @return
	 */
	public static String getSubDateStr(String dateTime) {
		String date = "";
		if (dateTime != null && dateTime.length() >= 10) {
			date = dateTime.substring(0, 10);
		}
		return date;
	}

	/**
	 * 截取固定长度的字符串
	 * 
	 * @param src
	 *            原字符串
	 * @param begin
	 *            开始下标
	 * @param end
	 *            结束下标
	 * @return
	 */
	public static String subString(String src, int begin, int end) {
		String result = nullToEmpty(src);
		if (result.length() >= end) {
			result = result.substring(begin, end);
		}
		return result;
	}

	/**
	 * 字符串转换为日期
	 * 
	 * @param value
	 *            日期格式的字符串
	 * @return
	 */
	public static Date strToDate(String value) {
		Date d = null;
		if (!isEmpty(value)) {
			try {
				d = sdf.parse(value);
			} catch (Exception e) {
				d = null;
			}
		}
		return d;
	}

	/**
	 * 字符串指定位置替换字符
	 * 
	 * @param str
	 *            原字符串
	 * @param i
	 *            位置
	 * @param newChar
	 *            需要替换的字符
	 * @return 替换过的字符串
	 */
	public static String strReplace(String str, int i, String newChar) {
		StringBuilder startStr = new StringBuilder();
		char[] a = str.toCharArray();
		a[i - 1] = newChar.charAt(0);
		for (int j = 0; j < a.length; j++) {
			startStr.append(a[j]);
		}
		return startStr.toString();
	}

	/**
	 * 判断dest是否有元素和src中的任意元素相同
	 * 
	 * @param src
	 * @param dest
	 * @return true:有 false:没有
	 */
	public static boolean contains(String[] src, String[] dest) {
		boolean result = false;
		boolean flag = true;

		// 非空判断
		if (src != null && dest != null) {
			for (int i = 0; i < dest.length; i++) {
				if (flag) {
					for (int j = 0; j < src.length; j++) {
						// 相等，则跳出循环
						if (dest[i].equals(src[j])) {
							result = true;
							flag = false;
							break;
						}
					}
				} else {
					break;
				}
			}
		}

		return result;
	}

	/**
	 * 判断src中是否有重复的元素
	 * 
	 * @param src
	 * @return
	 */
	public static boolean contains(String[] src) {
		boolean result = false;
		boolean flag = true;

		if (src != null) {
			for (int i = 0; i < src.length; i++) {
				if (i < src.length - 1 && flag) {
					for (int j = i + 1; j < src.length; j++) {
						// 如果有相等的，则跳出循环
						if (src[i].equals(src[j])) {
							result = true;
							break;
						}
					}
				} else {
					break;
				}
			}
		}
		return result;
	}

	/**
	 * 将list元素数组中指定下标的值转换为数组
	 * 
	 * @param src
	 * @param index
	 * @return
	 */
	public static String[] listToArray(List<String[]> src, int index) {
		String[] result = null;
		if (src != null) {
			if (index >= src.size()) {
				index = src.size() - 1;
			}

			result = new String[src.size()];
			for (int i = 0; i < src.size(); i++) {
				result[i] = src.get(i)[index];
			}
		}
		return result;
	}

	/**
	 * 将字符串转换为List<String>
	 * 
	 * @param src
	 *            原字符串
	 * @param split
	 *            一级分隔符
	 * @param subSplit
	 *            二级分隔符
	 * @return
	 */
	public static List<String[]> strToArray(String src, String split,
			String subSplit) {
		List<String[]> result = new ArrayList<String[]>();
		if (!StringTool.isEmpty(src)) {
			String[] strs = src.split(split);
			if (strs.length > 0) {
				for (String item : strs) {
					result.add(item.split(subSplit));
				}
			}
		}
		return result;
	}

	/**
	 * 转换大小为数字
	 * 
	 * @param size
	 * @return
	 */
	public static String FileSize(long fileS) {
		DecimalFormat df = new DecimalFormat("#.00");
		String fileSizeString = "";
		if (fileS < 1024) {
			fileSizeString = df.format((double) fileS) + "B";
		} else if (fileS < 1048576) {
			fileSizeString = df.format((double) fileS / 1024) + "K";
		} else if (fileS < 1073741824) {
			fileSizeString = df.format((double) fileS / 1048576) + "M";
		} else {
			fileSizeString = df.format((double) fileS / 1073741824) + "G";
		}
		return fileSizeString;

		// 此方法在WebLogic下得到的文件大小数据后很多0，采用上面这种方法解决
		// double sz = size;
		// String unit = "";
		//
		// if (size < 1024) {
		// unit = "Byte";
		// } else if (size < 0x100000) {
		// sz = sz / 1024;
		// unit = "KB";
		// } else if (size < 0x40000000) {
		// sz = sz / 0x100000;
		// unit = "MB";
		// } else {
		// sz = sz / 0x40000000;
		// unit = "G";
		// }
		//
		// sz = ((double) ((long) (sz * 100))) / 100;
		//
		// return String.format("%1$s%2$s ", sz, unit);
	}

	/**
	 * 将最后一个toPlace替换成item
	 * @param src
	 * @param toPlace
	 * @param item
	 * @return
	 */
	public static String replaceLast(String src, String toPlace, String item) {
		String result = trim(src);
		int index = src.lastIndexOf(toPlace);
		if (index > -1) {
			result = result.substring(0, index) + trim(item)
					+ result.substring(index+1);
		}

		return result;
	}
	
	// 计算图片比例
	public static float calculate(float actualSize,float targetSize) {
		return targetSize * 100 / actualSize;
	}

	public static void main(String[] args) {

		System.out.println(StringTool.getDate(new Date(), "yyyyMMddHHmmssSSS"));
		
		String b = "2010-8-22 8:30-11:00";
		System.out.println(replaceLast(b, "-", "至"));
	}
	
	/**
	 * 打印日期字符串
	 * @return
	 */
	public static String getPrintDateStr() {
//		星期五 2010年9月17日 17:00:50
		StringBuffer printDate = new StringBuffer();
		//获得星期
		Calendar c = Calendar.getInstance();
		c.setTime(new Date(System.currentTimeMillis()));
		int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
		switch (dayOfWeek) {
			case 1:
				printDate.append("星期日 ");
				break;
			case 2:
				printDate.append("星期一 ");
				break;
			case 3:
				printDate.append("星期二 ");
				break;
			case 4:
				printDate.append("星期三 ");
				break;
			case 5:
				printDate.append("星期四 ");
				break;
			case 6:
				printDate.append("星期五 ");
				break;	
			case 7:
				printDate.append("星期六 ");
				break; 
		}

		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
		printDate.append(format.format(new Date()));		
		return printDate.toString();
	}
	
	/**
	 * 获得金额数字，将指定格式金额字符串转换为指定格式： 0.01   51.10  115.52
	 * Add By Wucf 20101008
	 * @return
	 */
	public static String getMoneyNum(String money){
		DecimalFormat df = new DecimalFormat("#0.00");		
		return df.format(Double.valueOf(money));
	}
	
	public static String arrayToString(String[] array) {
	    String str = "";
	    if (str.length() > 0) {
	        for (int i = 0; i < str.length(); i++) {
	            str += "," + array[i];
	        }
	        str = str.substring(1);
	    }
	    return str;
	}
	
	/**
	 * List集合是否为空或大小为空
	 * @param list
	 * @return
	 */
	public static boolean isEmptyList(List list) {
	    if (list == null || list.size() == 0) {
	        return true;
	    } else {
	        return false;
	    }
	}
	
	public static String join(List<String> ss,String spliter){
		StringBuilder r=new StringBuilder();
		
		for(String s:ss){
			if(r.length()>0)
				r.append(spliter);
			r.append(s);
		}
		
		return r.toString();
	}
	
	public static String join(String[] ss,String spliter){
		StringBuilder r=new StringBuilder();
		
		for(String s:ss){
			if(r.length()>0)
				r.append(spliter);
			r.append(s);
		}
		
		return r.toString();
	}
	/**
	 * 字符串链接
	 * */
	public static String getListStr(List<String> fileList,String str){
		StringBuffer sb = new StringBuffer();
		int l=fileList.size(),i;
		String strfile;
		for(i=0;i<l;i++){
			strfile=fileList.get(i);
			if(strfile!=null && !strfile.equals("null") && strfile.trim().length()>0){
				if(i==(l-1)) sb.append(fileList.get(i).trim());
				else sb.append(fileList.get(i).trim()+str);
			}
		}
		return sb.toString();
	}
	/**
	 * 截取字符串
	 * */
	public static String[] stringSpilit(String str,String spilit_sign){
		String[] spilit_string=str.split(spilit_sign);
		if(spilit_string[0].equals("")){
			String[] new_string=new String[spilit_string.length-1];
			for(int i=1;i<spilit_string.length;i++) new_string[i-1]=spilit_string[i];
			return new_string;
		}
		else return spilit_string;
	}
	
	public static boolean testInt(String str){
		 Pattern pt=Pattern.compile("^[0-9]*]*$");
		 if(str=="" || str==null) return false;
		 return pt.matcher(str).matches();
	 }
}