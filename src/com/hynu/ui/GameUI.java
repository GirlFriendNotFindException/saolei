package com.hynu.ui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import com.hynu.bean.Bomb;
import com.hynu.bean.GamePerperty;
import com.hynu.biz.Data;
import com.hynu.biz.SL_Biz;
import com.hynu.biz.SaveAndLoadBiz;
import com.hynu.constant.Constant;
import com.hynu.dao.GameRecord;
import com.hynu.utils.UIUtils;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;

public class GameUI {
	public Shell shell;
	public SL_Biz sl = new SL_Biz(GameUI.this);
	public Button[][] bt;
	public AudioClip ac;
	public int width = 400;
	public int height = 480;
	public int colNum;
	public int rowNum;
	public Button startButton = null;
	public MenuItem startItem = null;
	public MenuItem nanduItem = null;
	public MenuItem messageItem = null;
	public MenuItem wgItem = null;
	public MenuItem closeItem = null;
	public MenuItem mntmNewItem = null;
	public MenuItem acItem;
	public MenuItem save = null;
	public MenuItem read = null;
	public Label timeLabel = null;
	public Label bombImgLabel = null;
	public Label bombNumLabel = null;
	public int marginHeight;
	public int buttonSize = 37;
	public Label label;
	public MenuItem help;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */

	// 开始游戏的构造函数
	public GameUI() {
		open();
	}

	// 继续游戏的构造函数
	public GameUI(String str) {
		openContinue();
	}

	// 将应用存放到Data类中
	private void storeRefernce(GameUI gameUI, SL_Biz sl) {
		Data.sl = sl;
		Data.gameUI = gameUI;
	}

	/**
	 * Open the window.
	 */
	// 开始游戏的打开界面方法
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */

	// 开始游戏的创建界面和添加事件
	protected void createContents() {
		addjiemain();
		sl.initBomb();
		sl.addItemEvent();
		sl.addEvent(rowNum, colNum);
	}

	// 继续游戏时的打开界面操作
	public void openContinue() {
		Display display = Display.getDefault();
		createContentsContinue();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	// 继续游戏时的初始化界面和添加事件 以及还原替换雷对象属性
	protected void createContentsContinue() {
		SaveAndLoadBiz sal = new SaveAndLoadBiz();
		Data.sal = sal;
		setPro(sal);
		addjiemain();
		sl.addItemEvent();
		sl.addEvent(rowNum, colNum);
		// sl.initBomb();
		Bomb[][] b = sal.getBombObject();
		sl.createContent(b);
	}

	// 布置界面
	public void addjiemain() {
		storeRefernce(this, sl);
		shell = new Shell(SWT.MIN | SWT.CLOSE);
		shell.setImage(SWTResourceManager.getImage(GameUI.class,"/image/18.jpg"));
		sl.setProperty();
		shell.setSize(width, height);
		UIUtils.centerWindows(shell);
		shell.setText("扫雷");
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));

		SashForm sashForm = new SashForm(shell, SWT.VERTICAL);

		Composite composite = new Composite(sashForm, SWT.NONE);
		composite.addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent arg0) {
				sl.jishi = false;
				// ac.stop();
			}
		});
		GridLayout gd = new GridLayout();
		gd.numColumns = colNum;
		gd.makeColumnsEqualWidth = true;
		gd.marginWidth = (width - colNum * buttonSize) / 2;
		marginHeight = (width - colNum * buttonSize) / 2 - 10;
		gd.marginHeight = marginHeight;
		gd.horizontalSpacing = -1;
		gd.verticalSpacing = -1;
		composite.setLayout(gd);

		Composite composite_1 = new Composite(sashForm, SWT.None);
		composite_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
		composite_1.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 20, SWT.BOLD));

		startButton = new Button(composite_1, SWT.NONE);
		int y = location(27);
		startButton.setBounds((width - 80 - 20) / 2, y, 80, 27);
		startButton.setText("\u5F00\u59CB");

		timeLabel = new Label(composite_1, SWT.NONE);
		timeLabel.setBounds(36, location(20), 45, 25);
		timeLabel.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		timeLabel.setText("\u65F6\u95F4\uFF1A");

		bombImgLabel = new Label(composite_1, SWT.NONE);
		bombImgLabel.setImage(SWTResourceManager.getImage(GameUI.class,"/image/18.jpg"));
		bombImgLabel.setBounds(width - 20 - 37 - marginHeight, location(36) - 3, 37, 36);

		bombNumLabel = new Label(composite_1, SWT.NONE);
		bombNumLabel.setFont(SWTResourceManager.getFont("微软雅黑", 20, SWT.NORMAL));
		bombNumLabel.setAlignment(SWT.CENTER);
		bombNumLabel.setBounds(width - 60 - marginHeight - 36, location(30) - 3, 40, 30);
		// bombNumLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GRAY));

		label = new Label(composite_1, SWT.NONE);
		label.setBounds(85, location(25) + 4, 60, 25);
		label.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));

		sashForm.setWeights(new int[] { 415, 58 });

		Menu menu = new Menu(shell, SWT.BAR);
		shell.setMenuBar(menu);

		MenuItem fileSubmenu = new MenuItem(menu, SWT.CASCADE);
		fileSubmenu.setText("\u6587\u4EF6");

		acItem = new MenuItem(menu, SWT.NONE);
		acItem.setText("\u97F3\u6548\u5F00\u5173");

		Menu menu_1 = new Menu(fileSubmenu);
		menu_1.setVisible(true);
		fileSubmenu.setMenu(menu_1);

		startItem = new MenuItem(menu_1, SWT.NONE);
		startItem.setText("\u65B0\u6E38\u620F");

		nanduItem = new MenuItem(menu_1, SWT.NONE);
		nanduItem.setText("\u9009\u62E9\u96BE\u5EA6");

		messageItem = new MenuItem(menu_1, SWT.NONE);
		messageItem.setText("\u7EDF\u8BA1\u4FE1\u606F");

		wgItem = new MenuItem(menu_1, SWT.NONE);
		wgItem.setText("\u66F4\u6539\u5916\u89C2");

		closeItem = new MenuItem(menu_1, SWT.NONE);
		closeItem.setText("\u9000\u51FA");

		save = new MenuItem(menu, SWT.NONE);
		save.setText("\u5B58\u6863");

		read = new MenuItem(menu, SWT.NONE);
		read.setText("\u8BFB\u6863");

		help = new MenuItem(menu, SWT.CASCADE);
		help.setText("\u5E2E\u52A9");
		GridData gridData = new GridData(buttonSize, buttonSize);
		bt = new Button[rowNum][colNum];
		for (int i = 0; i < rowNum; i++) {
			for (int j = 0; j < colNum; j++) {
				bt[i][j] = new Button(composite, SWT.None);
				bt[i][j].setLayoutData(gridData);
				bt[i][j].setData(new Bomb(i, j));
			}
		}
		
		sl.addAC();
	}

	

	// 调整菜单栏里面的主键的位置使其居中显示
	private int location(int h) {
		int y1 = (height * 415 / 473 - marginHeight - rowNum * (buttonSize + 1) - 15);
		int y2 = (height * 58 / 473 - 10);
		int y = ((y1 + y2 - h) - y1) / 2;
		return y;
	}

	// 继续游戏时设置界面宽高和雷数等属性
	private void setPro(SaveAndLoadBiz sal) {
		GamePerperty gp = sal.getGpObj();
		if (gp.getDifficulty_id() == 1) {
			setProperty(Constant.rowNum_1, Constant.colNum_1, Constant.BombNum_1, Constant.width_1, Constant.height_1,
					Constant.difficulty_id_1);
		} else if (gp.getDifficulty_id() == 2) {
			setProperty(Constant.rowNum_2, Constant.colNum_2, Constant.BombNum_2, Constant.width_2, Constant.height_2,
					Constant.difficulty_id_2);
		} else if (gp.getDifficulty_id() == 3) {
			setProperty(Constant.rowNum_3, Constant.colNum_3, Constant.BombNum_3, Constant.width_3, Constant.height_3,
					Constant.difficulty_id_3);
		} else if (gp.getDifficulty_id() == 4) {
			Rectangle r = getWH(gp.getRowNum(), gp.getColNum());
			// System.out.println(gp.getRowNum()+"\t"+gp.getColNum());
			setProperty(gp.getRowNum(), gp.getColNum(), gp.getBombNum(), r.width, r.height, Constant.difficulty_id_4);
		}
	}

	private void setProperty(int rowNum, int colNum, int BombNum, int width, int height, int difficulty_id) {
		// Data.BlockNum = BlockNum;
		Data.rowNum_1 = rowNum;
		Data.colNum_1 = colNum;
		Data.BombNum = BombNum;
		Data.width = width;
		Data.height = height;
		Data.difficulty_id = difficulty_id;
	}

	// 根据行列数计算自定义的宽高
	public Rectangle getWH(Integer rowNum, Integer colNum) {
		Rectangle r = new Rectangle(0, 0, 0, 0);
		r.width = colNum * 38 + 50;
		r.height = rowNum * 38 + 20 + 20 + 100;
		return r;
	}

	public static void main(String[] args) {
		GameUI frame = new GameUI();
	}
}
