package com.hynu.event;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;

import com.hynu.biz.Data;
import com.hynu.biz.SL_Biz;
import com.hynu.bean.Bomb;
public class Color_SelectionAdapter extends SelectionAdapter {
	private SL_Biz sl;

	
	//在窗口类里面创建这个类的实例时，用this作为创建的类的实例时的参数
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
