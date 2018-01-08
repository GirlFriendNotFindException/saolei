package com.hynu.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.hynu.utils.Log;
/**
 * 负责读取配置文件
 * 1.对于这样的配置文件只需要读取一次    可用单例模式 
 * 2.MyProperties extends Properties读取配置文件    load(InputStream )
 * InputStream   要指定db.properties 的位置      不能把地址写死        可用字节码反射获取地址
 *
 */
public class MyProperties extends Properties{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static MyProperties instance=new MyProperties();
	
	private MyProperties() {
		//构造方法至少会执行一次
		//InputStream iis=new FileInputStream("...");
		InputStream iis=MyProperties.class.getClassLoader().getResourceAsStream("db.properties");
		try {
			load(iis);
		} catch (IOException e) {
			Log.error(e);
		}finally{
			if(iis!=null){
				try {
					iis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public static MyProperties getInstance(){
		return instance;
	}
	
}
