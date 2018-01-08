package com.hynu.biz;

import java.applet.Applet;
import java.net.URL;
import java.util.Random;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.wb.swt.SWTResourceManager;

import com.hynu.event.CenterClickAdapter;
import com.hynu.event.Color_SelectionAdapter;
import com.hynu.event.Item_NanduAdapter;
import com.hynu.event.LeftClickAdapter;
import com.hynu.event.MouseTrack_Adapter;
import com.hynu.event.ReadRecord_Adapter;
import com.hynu.event.RightClickAdapter;
import com.hynu.event.SaveRecord_Adapter;
import com.hynu.event.Start_SelectionAdapter;
import com.hynu.ui.GameUI;
import com.hynu.ui.MessegeUI;
import com.hynu.ui.ResultUI;
import com.hynu.event.GameRule_Adapter;
import com.hynu.bean.Bomb;
import com.hynu.bean.DJ_Table;
import com.hynu.bean.GamePerperty;
import com.hynu.dao.GameRecord;
import com.hynu.dao.GameResult;
import com.hynu.utils.Log;
import com.hynu.utils.UIUtils;

public class SL_Biz {
	public GameUI gameUI;
	public int BombNum = Data.BombNum;
	public int rowNum = Data.rowNum_1;
	public int colNum = Data.colNum_1;
	public int difficuity_id = Data.difficulty_id;
	public int rightBomb = 0, restBomb, restBlock;
	public int width;
	public int height;
	public Bomb[][] bomb = null;
	public Button[][] button;
	public boolean jishi;
	public boolean startThread;
	public Long gameTime = 0L;
	public Thread t = null;
	public int bomd = Data.bomd;
	public boolean flag=false;
	public boolean acFlag=false;
	public boolean tsFlag=false;

	public SL_Biz(GameUI gameUI) {
		this.gameUI = gameUI;
	}

	public SL_Biz() {
		// TODO Auto-generated constructor stub
	}

	// 将Data缓冲池中的数据作为难度数据
	public void setProperty() {
		if (true) {
			gameUI.colNum = Data.colNum_1;
			gameUI.rowNum = Data.rowNum_1;
			gameUI.width = Data.width;
			gameUI.height = Data.height;
		}
	}

	// 添加事件
	public void addEvent(int rowNum, int colNum) {
		button = gameUI.bt;
		for (int i = 0; i < rowNum; i++) {
			for (int j = 0; j < colNum; j++) {
				button[i][j].addSelectionListener(new LeftClickAdapter(this));
				button[i][j].addMouseListener(new RightClickAdapter(this));
//				button[i][j].addMouseTrackListener(new MouseTrack_Adapter(this));
				button[i][j].addMouseListener(new CenterClickAdapter(this));
			}
		}
	}

	// 添加Item监听
	public void addItemEvent() {
		gameUI.startButton.addSelectionListener(new Start_SelectionAdapter(this));
		gameUI.startItem.addSelectionListener(new Start_SelectionAdapter(this));
		gameUI.nanduItem.addSelectionListener(new Item_NanduAdapter(this));
		// frame.chakanHelpItem.addActionListener(new Text_HelpAdapter(this));
		gameUI.messageItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					MessegeUI mf = new MessegeUI();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		gameUI.acItem.addSelectionListener(new SelectionAdapter(){
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(acFlag==false){
					gameUI.ac.loop();
					acFlag=true;
				}else{
					gameUI.ac.stop();
					acFlag=false;
				}
			}
		});
		gameUI.closeItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				gameUI.shell.dispose();
			}
		});
		gameUI.save.addSelectionListener(new SaveRecord_Adapter(this));
		gameUI.read.addSelectionListener(new ReadRecord_Adapter(this));
		gameUI.help.addSelectionListener(new GameRule_Adapter(this));
		gameUI.wgItem.addSelectionListener(new Color_SelectionAdapter(this));
	}

	public void initBomb() {

		button = gameUI.bt;
		bomb = new Bomb[rowNum][colNum];

		for (int i = 0; i < rowNum; i++) {
			for (int j = 0; j < colNum; j++) {
				bomb[i][j] = (Bomb) button[i][j].getData();
				bomb[i][j].setBomb(false);
				bomb[i][j].setClicked(false);
				bomb[i][j].setRight(false);
				bomb[i][j].setBombFlag(0);
				bomb[i][j].setBombRoundCount(9);
				button[i][j].setEnabled(true);
				if(bomd==0){
					button[i][j].setImage(SWTResourceManager.getImage(GameUI.class,"/image/16.jpg"));
				}else{
					button[i][j].setImage(SWTResourceManager.getImage(GameUI.class,"/image/green.jpg"));
				}
				button[i][j].setText("");
			}
		}
		rightBomb = 0;
		restBomb = BombNum;
		restBlock = rowNum * colNum - BombNum;
		gameUI.bombNumLabel.setText(restBomb + "");
		tsFlag=false;
		createDJ_Obj();
		thread();
	}

	// 生成雷
	public void bulei(Bomb b) {
		Random r = new Random();
		for (int i = 0; i < BombNum;) {
			int x = r.nextInt(rowNum);
			int y = r.nextInt(colNum);
			int k1 = b.getNum_x();
			int k2 = b.getNum_y();
			if ((x < k1 - 1 || x > k1 + 1) || (y < k2 - 1 || y > k2 + 1)) {
				if (!bomb[x][y].isBomb()) {
					bomb[x][y].setBomb(true);
					i++;
				}
			}
		}
		CountRoundBomb();
	}

	// 创建对局类对象
	public void createDJ_Obj() {
		DJ_Table dj = new DJ_Table();
		Data.dj = dj;
	}

	private boolean checkBounds(int x, int y) {
		return (x >= 0 && y >= 0 && x < rowNum && y < colNum);
	}

	public void CountRoundBomb() {
		for (int i = 0; i < rowNum; i++) {
			for (int j = 0; j < colNum; j++) {
				int count = 0;
				// 当需要检测的单元格本身无地雷的情况下,统计周围的地雷个数
				if (!bomb[i][j].isBomb()) {
					for (int x = i - 1; x <= i + 1; x++) {
						for (int y = j - 1; y <= j + 1; y++) {
							if (checkBounds(x, y)) {
								if (bomb[x][y].isBomb()) {
									count++;
								}
							}
						}
					}
					bomb[i][j].setBombRoundCount(count);
				}
			}
		}
	}

	public void turn(Button clickedButton) {
		Bomb bomb = (Bomb) clickedButton.getData();
		bomb.setClicked(true);
		// restBlock--;
		if (bomb.getBombRoundCount() > 0) {
			selectNumImage(clickedButton, bomb);
		} else {
			isNull(clickedButton);
		}
	}

	// 选择数字对应的图片
	private void selectNumImage(Button clickedButton, Bomb bomb) {
		for (int i = 1; i <= 8; i++) {
			if (bomb.getBombRoundCount() == i) {
				clickedButton.setImage(SWTResourceManager.getImage(GameUI.class,"/image/11" + i + ".jpg"));
			}
		}
	}

	public void isNull(Button clickedButton) {

		int i, j;
		Bomb clickedBomb = (Bomb) clickedButton.getData();
		i = clickedBomb.getNum_x();
		j = clickedBomb.getNum_y();
		for (int x = i - 1; x < i + 2; x++) {
			for (int y = j - 1; y < j + 2; y++) {
				if (((x != i) || (y != j)) && (x >= 0) && (y >= 0) && (x < rowNum) && (y < colNum)) {
					if (!bomb[x][y].isBomb() && !bomb[x][y].isRight() && !bomb[x][y].isClicked()) {
						turn(button[x][y]);
					}
				}
			}
		}
		button[i][j].setImage(SWTResourceManager.getImage(GameUI.class,"/image/110.jpg"));
	}

	public void isWin() {
		restBlock = colNum * rowNum - BombNum;
		for (int i = 0; i < rowNum; i++) {
			for (int j = 0; j < colNum; j++) {
				if (bomb[i][j].isClicked()) {
					restBlock--;
				}
			}
		}
		if (rightBomb == BombNum) {
			jishi = false;
			flag=true;
			new ResultUI();
			//writeResult(flag);
			GameRecord gr = new GameRecord();
			gr.deleteRecord();
		}
	}

	public void writeResult(boolean iswin) {
		GameResult gr = new GameResult();
		DJ_Table dj = gr.getResult(difficuity_id, iswin);
		int r = gr.writeResult(dj);
		if (r <= 0) {
			UIUtils.showMessageDialog(gameUI.shell, "数据写入失败", "");
		}
	}

	public void keji() {
		for (int i = 0; i < rowNum; i++) {
			for (int j = 0; j < colNum; j++) {
				if (bomb[i][j].isBomb()) {
					button[i][j].setImage(SWTResourceManager.getImage(GameUI.class,"/image/3.jpg"));
				}
			}
		}
	}

	// 换色
	public void ChangeColor_Green() {
		for (int i = 0; i < rowNum; i++) {
			for (int j = 0; j < colNum; j++) {
				if (bomb[i][j].isClicked() == false && bomb[i][j].isRight() == false) {
					button[i][j].setImage(SWTResourceManager.getImage(GameUI.class,"/image/green.jpg"));
				}
				if (bomb[i][j].isRight() == true) {
					button[i][j].setImage(SWTResourceManager.getImage(GameUI.class,"/image/greenbiao.jpg"));
				}
				if (bomb[i][j].isWen() == true) {
					button[i][j].setImage(SWTResourceManager.getImage(GameUI.class,"/image/greenwen.jpg"));
				}
			}
		}
	}

	public void ChangeColor_Blue() {
		for (int i = 0; i < rowNum; i++) {
			for (int j = 0; j < colNum; j++) {
				if (bomb[i][j].isClicked() == false && bomb[i][j].isRight() == false) {
					button[i][j].setImage(SWTResourceManager.getImage(GameUI.class,"/image/16.jpg"));
				}
				if (bomb[i][j].isRight() == true) {
					button[i][j].setImage(SWTResourceManager.getImage(GameUI.class,"/image/biaoji.jpg"));
				}
				if (bomb[i][j].isWen() == true) {
					button[i][j].setImage(SWTResourceManager.getImage(GameUI.class,"/image/wen.jpg"));
				}
			}
		}
	}

	// 作弊方法
	public void zuobi(Bomb bomb1) {
		int i = bomb1.getNum_x();
		int j = bomb1.getNum_y();
		for (int x = i - 1; x <= i + 1; x++) {
			for (int y = j - 1; y <= j + 1; y++) {
				if (((x != i) || (y != j)) && (x >= 0) && (y >= 0) && (x < (rowNum)) && (y < colNum)) {
					if (bomb[x][y].isBomb() == true) {
						button[x][y].setImage(SWTResourceManager.getImage(GameUI.class,"/image/17.jpg"));
					}
				}
			}
		}
	}

	// 取消zuobi
	public void quxiaozuobi() {
		for (int i = 0; i < rowNum; i++) {
			for (int j = 0; j < colNum; j++) {
				if (bomb[i][j].isBomb() == true && bomb[i][j].isRight() == false) {
					button[i][j].setImage(SWTResourceManager.getImage(GameUI.class,"/image/16.jpg"));
				}
			}
		}
	}

	public void thread() {
//		gameTime = 0L;
		jishi = false;
		startThread = false;
		gameUI.label.setText(0 + "");
		t = new Thread() {// 线程操作
			public void run() {
				try {
					while (jishi) {
						gameTime += 1000;
						// System.out.println(miao);
						// 对Label进行实时刷新，需要加上这句
						gameUI.label.getDisplay().asyncExec(new Runnable() {
							@Override
							public void run() {
								// 设置时间 ，格式化输出时间
								gameUI.label.setText(gameTime / 1000 + " s");// 输出到Label上
							}
						});
						Thread.sleep(1000);// 每隔一秒刷新一次
					}
				} catch (Exception e) {
					Log.error(e);
					UIUtils.showMessageDialog(gameUI.shell, e.getMessage(), "错误");
				}
			}
		};
	}

	// 读档继续游戏还原游戏界面
	public void createContent(Bomb[][] b) {
		button = gameUI.bt;
		rowNum=Data.rowNum_1;
		colNum=Data.colNum_1;
		bomb = new Bomb[rowNum][colNum];
		SaveAndLoadBiz sal = new SaveAndLoadBiz();
		GamePerperty gp = sal.getGpObj();
		restBomb = gp.getRestBomb();
		rightBomb = gp.getRightBomb();
		if(Data.difficulty_id==4){
			BombNum=gp.getBombNum();
		}
		gameTime = gp.getGameTime();
		bomd = gp.getImageNO();
		gameUI.bombNumLabel.setText(restBomb + "");
		gameUI.label.setText(gameTime / 1000 + " s");

		for (int i = 0; i < rowNum; i++) {
			for (int j = 0; j < colNum; j++) {
				bomb[i][j] = b[i][j];
				button[i][j].setData(bomb[i][j]);
				button[i][j].setEnabled(true);
				if(bomd==0){
					button[i][j].setImage(SWTResourceManager.getImage(GameUI.class,"/image/16.jpg"));
				}else{
					button[i][j].setImage(SWTResourceManager.getImage(GameUI.class,"/image/green.jpg"));
				}
				if (bomb[i][j].isClicked()) {
					if (bomb[i][j].getBombRoundCount() > 0) {
						selectNumImage(button[i][j], bomb[i][j]);
					} else {
						button[i][j].setImage(SWTResourceManager.getImage(GameUI.class,"/image/110.jpg"));
					}
				} else if (bomb[i][j].getBombFlag() == 1) {
					if(bomd==0){
						button[i][j].setImage(SWTResourceManager.getImage(GameUI.class,"/image/biaoji.jpg"));
					}else{
						button[i][j].setImage(SWTResourceManager.getImage(GameUI.class,"/image/greenbiao.jpg"));
					}
				}else if(bomb[i][j].getBombFlag() == 2){
					if(bomd==0){
						button[i][j].setImage(SWTResourceManager.getImage(GameUI.class,"/image/wen.jpg"));
					}else{
						button[i][j].setImage(SWTResourceManager.getImage(GameUI.class,"/image/greenwen.jpg"));
					}
				}
			}
		}
		thread();
		jishi = true;
		t.start();
		startThread = true;
		if(Data.lf!=null){
			Data.lf.shell.dispose();
		}
	}
	
	public void addAC() {
		URL url=SL_Biz.class.getClassLoader().getResource("sound/bgmusic.wav");
		gameUI.ac = Applet.newAudioClip(url);
	}

}
