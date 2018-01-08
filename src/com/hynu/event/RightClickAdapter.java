package com.hynu.event;



import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.widgets.Button;
import org.eclipse.wb.swt.SWTResourceManager;

import com.hynu.bean.Bomb;
import com.hynu.biz.Data;
import com.hynu.biz.SL_Biz;
import com.hynu.dao.GameRecord;
import com.hynu.ui.GameUI;
import com.hynu.ui.ResultUI;
import com.hynu.utils.UIUtils;

public class RightClickAdapter extends MouseAdapter{
	private SL_Biz sl=null;
	
	public RightClickAdapter(SL_Biz sl) {
		this.sl=sl;
	}
	
	@Override
	public void mouseDown(org.eclipse.swt.events.MouseEvent e) {
		if (e.button==3) {
			
			Button button=((Button)e.getSource());
			Bomb bomb=(Bomb) button.getData();
			if (!bomb.isClicked() ){
				bomb.setBombFlag((bomb.getBombFlag()+1)%3);
				if(bomb.getBombFlag()==1){
					if(sl.bomd%2==0){
						button.setImage(SWTResourceManager.getImage(GameUI.class,"/image/biaoji.jpg"));
					}else{
					button.setImage(SWTResourceManager.getImage(GameUI.class,"/image/greenbiao.jpg"));
					}
					bomb.setRight(true);
					sl.restBomb--;
					if(bomb.isBomb()==true){
						sl.rightBomb=sl.rightBomb+1;
					}
					if(bomb.isBomb()==false){
						sl.rightBomb=sl.rightBomb-1;
					}
					if(sl.rightBomb==sl.BombNum) {
						sl.jishi=false;
//						sl.startThread=false;
//						UIUtils.showMessageDialog(sl.gameUI.shell, "YOU WIN!", "");
						sl.flag=true;
						new ResultUI();
						sl.writeResult(sl.flag);
					}
				}else if(bomb.getBombFlag()==2){
					if(sl.bomd%2==0){
						button.setImage(SWTResourceManager.getImage(GameUI.class,"/image/wen.jpg"));
					}else{
					button.setImage(SWTResourceManager.getImage(GameUI.class,"/image/greenwen.jpg"));
					}
					sl.restBomb++;
					if(bomb.isBomb()==true){
						sl.rightBomb=sl.rightBomb-1;
					}
					if(bomb.isBomb()==false){
						sl.rightBomb=sl.rightBomb+1;
					}
				}else {
					bomb.setBombFlag(0);
					if(sl.bomd%2==0){
						button.setImage(SWTResourceManager.getImage(GameUI.class,"/image/16.jpg"));
					}else{
					button.setImage(SWTResourceManager.getImage(GameUI.class,"/image/green.jpg"));
					}
					bomb.setRight(false);
				}
				sl.gameUI.bombNumLabel.setText(sl.restBomb+"");
			}
//			System.out.println(sl.restBomb);
//			System.out.println(sl.rightBomb);
		}
//			if(e.button==3){
//				Button button=((Button)e.getSource());
//				Bomb bomb=(Bomb) button.getData();
//				if( !bomb.isClicked()){
//					bomb.setBombFlag((bomb.getBombFlag()+1)%3);
//					if(bomb.getBombFlag()==1){
//						if(sl.restBomb>0){
////							bomb.setForeground(Color.red);
//							button.setText("F");
//							//设置已被右键点击
//							bomb.setRight(true);
//							sl.restBomb--;
//						}else{
//							bomb.setBombFlag(0);
//						}
//					}else if(bomb.getBombFlag()==2){
//						sl.restBomb++;
//						button.setText("Q");
//						bomb.setRight(false);
//					}else{
//						button.setText("");
//					}
//				}
//				if(bomb.isBomb()){
//					if(bomb.getBombFlag()==1){
//						sl.rightBomb++;
//					}else if(bomb.getBombFlag()==2){
//						sl.rightBomb--;
//					}
//				}
////				sl.getNowBomb().setText("当前雷数："+sl.restBomb);
//				sl.isWin();
//			}
		}
	}
	

