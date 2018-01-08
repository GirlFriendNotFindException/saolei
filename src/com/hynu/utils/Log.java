package com.hynu.utils;

import org.apache.log4j.Logger;

public class Log {
	public static Logger logger=Logger.getLogger(Log.class);
	
	public static void error(Exception e){
		
		logger.error("\t\t¥ÌŒÛ–≈œ¢£∫"+e.getMessage());
		System.out.println();
		StackTraceElement[] stes=e.getStackTrace();
		for(StackTraceElement ste:stes){
			logger.error("\t\t"+ste.toString());
		}
		System.out.println();
	}
	
	public static void debug(Exception e){
		logger.debug(e.getMessage());
		StackTraceElement[] stes=e.getStackTrace();
		for(StackTraceElement ste:stes){
			logger.debug("\t\t"+ste.toString());
		}
	}
	
	public static void warn(Exception e){
		logger.warn(e.getMessage());
		StackTraceElement[] stes=e.getStackTrace();
		for(StackTraceElement ste:stes){
			logger.warn("\t\t"+ste.toString());
		}
	}
	
	public static void info(Exception e){
		logger.info(e.getMessage());
		StackTraceElement[] stes=e.getStackTrace();
		for(StackTraceElement ste:stes){
			logger.info("\t\t"+ste.toString());
		}
	}
}
