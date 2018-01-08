package com.hynu.utils;

public class MathUtil {
	
	//������λС��
	public static Double getXS(Integer a, Integer  b){
		Double c= ((double)a/b);
		int c1=(int) ((c*1000+5)/10);
		
		Double c2= ((double) c1/100);
		return c2;
	}
	
	//�ж�һ���ַ����Ƿ�Ϊ����
	public static boolean isNumber(String str){
		final String reg="\\d+\\.{0,1}\\d*";
		boolean flag=str.matches(reg);
		return flag;
	}
	
	//�ж�һ���ַ����Ƿ�Ϊ����
	public static boolean isNum(String str){
		final String reg="[0-9]+";
		boolean flag=str.matches(reg);
		return flag;
	}
	
}
