package com.hynu.biz;


import org.eclipse.swt.widgets.Table;

import com.hynu.bean.Bomb;
import com.hynu.bean.GamePerperty;
import com.hynu.utils.IOUtils;

public class SaveAndLoadBiz {
	/**
	 * 存档和读档
	 */

	private SL_Biz sl = Data.sl;
	public Integer id;

	public void save(Integer no) {
		IOUtils.writeObject(sl.bomb, no, 1);
		GamePerperty gp = new GamePerperty();
		gp.setGameTime(sl.gameTime);
		gp.setImageNO(sl.bomd);
		gp.setRestBomb(sl.restBomb);
		gp.setRightBomb(sl.rightBomb);
		gp.setDifficulty_id(Data.difficulty_id);
		if(Data.difficulty_id==4){
			gp.setRowNum(sl.rowNum);
			gp.setColNum(sl.colNum);
			gp.setBombNum(sl.BombNum);
		}
		IOUtils.writeObject(gp, no, 2);
	}

	public Object load(Integer no, int n) {
		return IOUtils.readObject(no, n);
	}

	// 读档继续游戏得到雷的对象
	// SaveAndLoadBiz sal = new SaveAndLoadBiz();

	public Bomb[][] getBombObject() {
		id = getRecordID();
		Bomb[][] b = (Bomb[][]) load(id, 1);
		return b;
	}

	//
	public Integer getRecordID() {
		Table table = Data.ra.lf.table;
		Integer cd_id = (Integer) table.getItem(table.getSelectionIndex()).getData("cd_id");
		return cd_id;
	}

	// 读档得到GamePerperty 对象
	public GamePerperty getGpObj() {
		id = getRecordID();
		GamePerperty gp = (GamePerperty) load(id, 2);
		return gp;
	}
	
	//存档后结束游戏
	public void endGame(){
		Bomb[][] bomb=sl.bomb;
		for(int i=0; i<sl.rowNum; i++){
			for(int j=0; j<sl.colNum; j++){
				if(!bomb[i][j].isClicked()){
					bomb[i][j].setClicked(true);
				}
			}
		}
	}
	
	

}
