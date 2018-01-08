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
	
	//�ڴ��������洴��������ʵ��ʱ����this��Ϊ���������ʵ��ʱ�Ĳ���
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
