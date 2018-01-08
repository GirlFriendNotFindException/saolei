package com.hynu.biz;

import com.hynu.bean.DJ_Table;
import com.hynu.bean.Users_Table;
import com.hynu.constant.Constant;
import com.hynu.event.Record_Adapter;
import com.hynu.ui.GameRuleUI;
import com.hynu.ui.GameUI;
import com.hynu.ui.LoadUI;
import com.hynu.ui.NanduUI;
import com.hynu.ui.RegisterUI;
import com.hynu.ui.ResultUI;

public class Data {
	public static int rowNum_1=9;
	public static int colNum_1=9;
	public static int BombNum=Constant.BombNum_1;
	public static int width=Constant.width_1;
	public static int height=Constant.height_1;
	public static int difficulty_id=Constant.difficulty_id_1;
	
	public static GameUI gameUI=null;
	
	public static SL_Biz sl=null;
	
	public static NanduUI ndf=null;
	
	public static Users_Table user=null;
	
	public static DJ_Table dj=null;
	
	public static LoadUI lf=null;
	
	public static Record_Adapter ra;
	
	public static SaveAndLoadBiz sal;
	
	public static GameRuleUI ii=null;
	
	public static ResultUI iswin=null;
	
	public static int bomd;
	
	public static RegisterUI regis;
}
