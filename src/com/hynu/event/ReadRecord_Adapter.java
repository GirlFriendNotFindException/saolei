package com.hynu.event;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import com.hynu.biz.Data;
import com.hynu.biz.SL_Biz;
import com.hynu.ui.LoadUI;

public class ReadRecord_Adapter extends SelectionAdapter{
private SL_Biz sl;
	
	public ReadRecord_Adapter() {
		// TODO Auto-generated constructor stub
	}
	
	public ReadRecord_Adapter(SL_Biz sl) {
		this.sl=sl;
	}
	
	@Override
		public void widgetSelected(SelectionEvent e) {
		if(Data.lf!=null){
			Data.lf.shell.dispose();
		}
		LoadUI lf = new LoadUI();
//		Data.lf=lf;
		}
}
