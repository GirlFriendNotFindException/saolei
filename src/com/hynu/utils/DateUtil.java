package com.hynu.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	private static DateFormat df=new SimpleDateFormat("yyyy-MM-dd"); 
	
	public static Date parseStringToDate(String datestr,String format) throws ParseException{
		Date d=null;
		if(format!=null){
			df=new SimpleDateFormat(format);
		}
		try {
			d=df.parse(datestr);
		} catch (ParseException e) {
			Log.error(e);
			throw e;
		}
		return d;
	}
	
	public static String parseDateToString(Date d,String format){
		if(format!=null){
			df=new SimpleDateFormat(format);
		}
		String str=df.format(d);
		return str;
	}
}
