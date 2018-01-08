package com.hynu.utils;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.Date;
import java.util.List;

import com.hynu.biz.Data;


public class IOUtils {
	
	/**
	 * 文件读取的封装
	 * 用buffer来缓存所有的数据
	 * @param file
	 * @return
	 * @throws IOException
	 */
	static URL url=IOUtils.class.getClassLoader().getResource("");
	static String path=url.getPath();
	public static String filePath=path+File.separator+"record";
	
	public static byte[] readFile(File file) throws IOException{
		byte[] buffer=null;
		ByteArrayOutputStream baos=new ByteArrayOutputStream();
		BufferedInputStream bis=null;
		try {
			bis=new BufferedInputStream(new FileInputStream(file));
			byte[] car=new byte[1024];
			int len=-1;
			while(-1!=(len=bis.read(car,0,car.length))){
				baos.write(car,0,len);
			}
			baos.flush();
			buffer=baos.toByteArray();
		} catch (FileNotFoundException e) {
			Log.error(e);
			throw e;
		} catch (IOException e) {
			Log.error(e);
			throw e;
		}finally{
			if(bis!=null){
				bis.close();
			}
			if(baos!=null){
				baos.close();
			}
		}
		return buffer;
	}
	
	// 获得文件容量
	public static String toStringSize(long len) {
		if (len / 1024 / 1024 / 1024 / 1024 / 1024 > 0)
			return len / 1024 / 1024 / 1024 / 1024 / 1024 + "p";

		else if (len / 1024 / 1024 / 1024 / 1024 > 0) {
			return len / 1024 / 1024 / 1024 / 1024 + "T";
		} else if (len / 1024 / 1024 / 1024 > 0) {
			return len / 1024 / 1024 / 1024 + "G";
		} else if (len / 1024 / 1024 > 0) {
			return len / 1024 / 1024 + "M";
		} else if (len / 1024 > 0) {
			return len / 1024 + "K";
		} else {
			return len + "B";
		}
	}
	
	//int n  1  表示存的雷对象     2表示存的时间和皮肤图片
	public static void writeObject(Object obj, Integer no, int n){
		ObjectOutputStream oos=null;
		String name=no+"_"+n;
		try {
			File file=new File(filePath);
			if(!file.exists()){
				file.mkdirs();
			}
			oos=new ObjectOutputStream(new FileOutputStream(new File(filePath+File.separator+name+"_tmp.data")));
			oos.writeObject(obj);
			oos.flush();
			
		} catch (Exception e){
			UIUtils.showMessageDialog(Data.gameUI.shell, "存档失败", "");
			e.getMessage();
			e.printStackTrace();
		}finally{
			try { 
				oos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static Object readObject(Integer no,int n){
		Object obj=null;
		ObjectInputStream ois=null;
		try {
			ois=new ObjectInputStream(new FileInputStream(filePath+File.separator+no+"_"+n+"_tmp.data"));
			obj=ois.readObject();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}finally{
			if(ois!=null){
				try {
					ois.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}  
		}
		return obj;
	}

}
	

