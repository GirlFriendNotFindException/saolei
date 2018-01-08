package com.hynu.dao;

import java.util.ArrayList;
import java.util.List;

import com.hynu.bean.DJ_Table;
import com.hynu.biz.Data;

public class GameResult {
	
	private DJ_Table dj=null;
	
	public DJ_Table getResult(Integer difficulty_id ,boolean iswin){
		dj=Data.dj;
		dj.setId(Data.user.getId());
		dj.setDifficulty_id(difficulty_id);
//		Data.dj.setGameTime(sl.frame.miao*1000L);
		dj.setGameTime(Data.sl.gameTime);
		dj.setWin(iswin);
		return dj;
	}
	
	public int writeResult(DJ_Table dj){
		DBHelper<DJ_Table> db=new DBHelper<>();
		String sql="insert into sl_dj(id,difficulty_id,iswin, gameTime, gamedate ) values( ?,?,?,?,sysdate() )";
		List<Object> params =new ArrayList<>();
		if(dj!=null){
			params.add(dj.getId());
			params.add(dj.getDifficulty_id());
			if(dj.isWin()==true){
				params.add("ªÒ §");
			}
			else{
				params.add(" ß∞‹");
			}
			params.add(dj.getGameTime());
		}
		int r=db.update(sql, params);
		return r;
	}
	
}
