package com.hynu.event;



import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import com.hynu.biz.Data;
import com.hynu.biz.SL_Biz;
import com.hynu.ui.GameRuleUI;

public class GameRule_Adapter extends SelectionAdapter{
	private SL_Biz sl=null;
//	public Wanfajieshao ii;
	
	public GameRule_Adapter(SL_Biz sl) {
		this.sl=sl;
	}
	
	@Override
	public void widgetSelected(SelectionEvent e) {
		GameRuleUI ii=new GameRuleUI();
		Data.ii=ii;
	}

}
