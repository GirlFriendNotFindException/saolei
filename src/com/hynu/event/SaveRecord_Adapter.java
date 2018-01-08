package com.hynu.event;


import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import com.hynu.biz.Data;
import com.hynu.biz.SL_Biz;
import com.hynu.biz.SaveAndLoadBiz;
import com.hynu.dao.GameRecord;
import com.hynu.utils.UIUtils;

public class SaveRecord_Adapter extends SelectionAdapter{
	
	private SL_Biz sl;
	
	public SaveRecord_Adapter() {
		// TODO Auto-generated constructor stub
	}
	
	public SaveRecord_Adapter(SL_Biz sl) {
		this.sl=sl;
	}
	
	@Override
	public void widgetSelected(SelectionEvent e) {
		boolean flag=sl.jishi;
		sl.jishi=false;
		if(flag==true){
			GameRecord gr=new GameRecord();
			if(Data.sal!=null){
				gr.deleteRecord();
			}
			int r=gr.writeGameRecord();
			if(r>0){
				SaveAndLoadBiz sal=new SaveAndLoadBiz();
				Integer no=gr.getRecordID();
				sal.save(no);
				sal.endGame();
				UIUtils.showMessageDialog(Data.gameUI.shell, "存档成功", "");
			}
		}else{
			UIUtils.showMessageDialog(Data.gameUI.shell, "游戏未开始或已结束,无法存档!", "提示");
		}
	}
	
}
