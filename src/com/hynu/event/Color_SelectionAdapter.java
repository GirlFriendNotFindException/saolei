package com.hynu.event;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;

import com.hynu.biz.Data;
import com.hynu.biz.SL_Biz;
import com.hynu.bean.Bomb;
public class Color_SelectionAdapter extends SelectionAdapter {
	private SL_Biz sl;

	
	//�ڴ��������洴��������ʵ��ʱ����this��Ϊ���������ʵ��ʱ�Ĳ���
	public Color_SelectionAdapter(SL_Biz sl) {
		this.sl=sl;
	}
	public void widgetSelected(SelectionEvent e) {
		if(sl.bomd==0){
			sl.ChangeColor_Green();
			sl.bomd=1;
		}else {
			sl.ChangeColor_Blue();
			sl.bomd=0;
		}
		Data.bomd=sl.bomd;
	}
}
