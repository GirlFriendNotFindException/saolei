package com.hynu.event;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import com.hynu.biz.Data;
import com.hynu.biz.SL_Biz;
import com.hynu.ui.NanduUI;

public class Item_NanduAdapter extends SelectionAdapter{
	private SL_Biz sl=null;
	private NanduUI ndf=null;
	
	public Item_NanduAdapter(SL_Biz sl) {
		this.sl=sl;
	}
	
	@Override
	public void widgetSelected(SelectionEvent e) {
		ndf=new NanduUI();
		Data.ndf=ndf;
	}
	
	
}
