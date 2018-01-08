package com.hynu.constant;

public class cc {

	int v=2;      // 待检测的数

	int sign;  // 符号结果，0表示正数或者0，-1表示负数
	 // -1, 0, or +1
	
	public void a() {
		sign = (v >> 0) - (v << 0);
		System.out.println(sign);
    }
	
	public static void main(String[] args) {
		new cc().a();
	}
}
