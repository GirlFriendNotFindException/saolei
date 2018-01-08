package com.hynu.event;


import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.wb.swt.SWTResourceManager;

import com.hynu.bean.Bomb;
import com.hynu.biz.Data;
import com.hynu.biz.SL_Biz;
import com.hynu.dao.GameRecord;
import com.hynu.ui.GameUI;
import com.hynu.ui.ResultUI;
import com.hynu.utils.UIUtils;

public class LeftClickAdapter extends SelectionAdapter{
	private SL_Biz sl=null;
	
	public LeftClickAdapter(SL_Biz sl) {
		this.sl=sl;
	}
	
	@Override
	public void widgetSelected(SelectionEvent e) {
		//左键点击开始计时   生成雷和计算周围雷数
			sl.jishi = true;
			Button button = ((Button) e.getSource());
			Bomb bomb = (Bomb) button.getData();
			if(!sl.startThread  && sl.t!=null){
				sl.bulei(bomb);
				sl.t.start(); 
				sl.startThread=true;
			}
			if (bomb.isClicked() == false && bomb.isRight() == false) { 
				if (!bomb.isBomb() ) {
					sl.turn(button);
					sl.isWin();
				} else {
					sl.jishi=false;
					for (int i = 0; i < sl.rowNum; i++) {
						for (int j = 0; j < sl.colNum; j++) {
							if (sl.bomb[i][j].isBomb() == true) {
								sl.button[i][j].setImage(SWTResourceManager.getImage(GameUI.class,"/image/3.jpg"));
							}
							if (sl.bomb[i][j].isClicked() == false) {
								sl.bomb[i][j].setClicked(true);
							}
						}
					}
					sl.flag=false;
					sl.writeResult(sl.flag);
					GameRecord gr=new GameRecord();
					gr.deleteRecord();
//					UIUtils.showMessageDialog(Data.frame.shell, "GAME OVER!", "");
						new ResultUI();
				}
		}
	}
}
