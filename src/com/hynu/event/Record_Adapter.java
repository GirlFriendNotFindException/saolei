package com.hynu.event;

import java.util.Date;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Table;

import com.hynu.biz.Data;
import com.hynu.ui.GameUI;
import com.hynu.ui.LoadUI;
import com.hynu.utils.UIUtils;

public class Record_Adapter extends SelectionAdapter{
	public LoadUI lf;
	
	
	
	public Record_Adapter() {
		// TODO Auto-generated constructor stub
	}
	
	public Record_Adapter(LoadUI lf) {
		this.lf=lf;
	}
	
	@Override
	public void widgetSelected(SelectionEvent e) {
		Table table = Data.ra.lf.table;
		if(table.getSelectionIndex()>-1){
			
			
			Data.gameUI.shell.dispose();
			new GameUI("continue");
		}else{
			UIUtils.showMessageDialog(Data.lf.shell, "请选择要继续的游戏", "");
		}
	}
	
//	private void addTableEvent(){
//		lf.table.addSelectionListener(new SelectionAdapter(){
//			@Override
//			public void widgetSelected(SelectionEvent e) {
////				TableItem ti=(TableItem)e.getSource();
////				Data.frame.shell.dispose();
//				new Frame("continue");
//			}
//		});
//	}
}
