package com.hynu.constant;

public class cc {

	int v=2;      // ��������

	int sign;  // ���Ž����0��ʾ��������0��-1��ʾ����
	 // -1, 0, or +1
	
	public void a() {
		sign = (v >> 0) - (v << 0);
		System.out.println(sign);
    }
	
	public static void main(String[] args) {
		new cc().a();
	}
}
