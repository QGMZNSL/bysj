package com.sincinfo.zxks.common.util;

import java.util.*;
import java.text.*; 

public class WeekCalendar{
	public Calendar CalendarSetTime(String sCurrDate){ 
		Calendar oCalendar = Calendar.getInstance(); 
		Date oDate = new Date(); 
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd"); 
		if(!sCurrDate.equals("")&&sCurrDate!=null){ 
			try{ 
				oCalendar.setTime(sdf.parse(sCurrDate)); 
			}catch(Exception ex){ 
				ex.printStackTrace(System.err); 
				System.out.println("日期格式转换错误：="+sCurrDate ); 
			} 
		} 
		else{
			oCalendar.setTime(oDate);
		}
		return oCalendar;
	}
	
	@SuppressWarnings({ "unchecked", "static-access" })
	public String getWeek(String sCurrDate){
		String[] oneWeekDay={"周日","周一","周二","周三","周四","周五","周六"}; 
		Calendar oCal=CalendarSetTime(sCurrDate);//重置时间
		
		System.out.println(oCal.YEAR+"="+oCal.DAY_OF_MONTH+"-"+oCal.DAY_OF_WEEK);
		return oneWeekDay[(oCal.DAY_OF_WEEK-1)];
	}
	
	public static void main(String[] args){
		WeekCalendar weekCalendar=new WeekCalendar();
		System.out.println(weekCalendar.getWeek("2012-09-23"));
	}
}