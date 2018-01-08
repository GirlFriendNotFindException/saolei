package com.hynu.event;


import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import com.hynu.biz.Data;
import com.hynu.biz.SL_Biz;
import com.hynu.ui.GameUI;
import com.hynu.ui.ResultUI;

public class Start_SelectionAdapter extends SelectionAdapter {
	private SL_Biz sl;
	private ResultUI iswin;
	
	//在窗口类里面创建这个类的实例时，用this作为创建的类的实例时的参数
	public Start_SelectionAdapter(SL_Biz sl) {
		this.sl=sl;
	}
	
	public Start_SelectionAdapter(ResultUI iswin) {
		this.iswin=iswin;
	}
	
	@Override
	public void widgetSelected(SelectionEvent e) {
		Data.sl.gameTime=0L;
		Data.sl.jishi=false;
		Data.sl.initBomb();
		if(iswin!=null){
			iswin.shell.dispose();
		}
	}
	
}
