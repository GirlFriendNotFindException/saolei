package com.hynu.event;

import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseTrackAdapter;
import org.eclipse.swt.widgets.Button;
import org.eclipse.wb.swt.SWTResourceManager;

import com.hynu.bean.Bomb;
import com.hynu.biz.SL_Biz;
import com.hynu.ui.GameUI;

public class MouseTrack_Adapter extends MouseTrackAdapter{
private SL_Biz sl=null;
	
	public MouseTrack_Adapter(SL_Biz sl) {
		this.sl=sl;
	}
	
	@Override
	public void mouseExit(MouseEvent e) {
		Button button = ((Button) e.getSource());
		Bomb bomb = (Bomb) button.getData();
		if(!bomb.isClicked()  && !bomb.isRight()){
			if(bomb.getBombFlag()==0){
				if(sl.bomd==0){
					button.setImage(SWTResourceManager.getImage(GameUI.class,"/image/16.jpg"));
					
				}else if(sl.bomd==1){
				button.setImage(SWTResourceManager.getImage(GameUI.class,"/image/green.jpg"));
				}
			}
		}
	}
	@Override
	public void mouseHover(MouseEvent e) {
		Button button = ((Button) e.getSource());
		Bomb bomb = (Bomb) button.getData();
		if(!bomb.isClicked()  && !bomb.isRight()){
			if(bomb.getBombFlag()==0){
				if(sl.bomd==0){
					button.setImage(SWTResourceManager.getImage(GameUI.class,"/image/repare.jpg"));
				}else if(sl.bomd==1){
					button.setImage(SWTResourceManager.getImage(GameUI.class,"/image/repare_1.jpeg"));
				}
			}
		}
	}
//	@Override
//	public void mouseEnter(MouseEvent e) {
//		Button button = ((Button) e.getSource());
//		Bomb bomb = (Bomb) button.getData();
//		if(!bomb.isClicked()  && !bomb.isRight()){
//			button.setImage(SWTResourceManager.getImage("image/repare.jpg"));
//		}
//	}
	
}
