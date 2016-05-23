package org.luisyang.integration.clock.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
	
	
	public static String  Defulte = "yyyyMMddhhmm";
	public static SimpleDateFormat sdf = new SimpleDateFormat(Defulte);
	
	public static Date getDate(){
		return new Date();
	}
	
	public static Date formDate(String date,SimpleDateFormat sdf1) {
		if(sdf1 ==null){
			sdf1 = sdf;
		}
		try {
			return sdf1.parse(date);
		} catch (ParseException e) {
			System.out.println("ʱ���ʽ������ȷ��"+date);
			e.printStackTrace();
		}
		return null;
	}

	public static String getStrforDate(Date date){
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		
		StringBuffer sb = new StringBuffer();
		sb.append(calendar.get(Calendar.YEAR))
		.append("-").append(calendar.get(Calendar.MONTH)+1)
		.append("-").append(calendar.get(Calendar.DAY_OF_MONTH))
		.append("   ").append(calendar.get(Calendar.HOUR_OF_DAY))
		.append(":").append(calendar.get(Calendar.MINUTE));
		
		return sb.toString();
		
	}
}
