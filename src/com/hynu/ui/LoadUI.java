package com.hynu.ui;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.wb.swt.SWTResourceManager;
import com.hynu.bean.CD_Table;
import com.hynu.biz.Data;
import com.hynu.dao.GameRecord;
import com.hynu.event.Combo_SelectionAdapter;
import com.hynu.event.Record_Adapter;
import com.hynu.utils.DateUtil;
import com.hynu.utils.UIUtils;

public class LoadUI {
//	private Composite composite;
	public Shell shell;
	public Table table;
	public Combo combo;
	MenuItem refresh ;
	MenuItem deleteGame;
	Button refreshButton;
	/**
	 * Launch the application.
	 * @param args
	 */
//	public static void main(String[] args) {
//		try {
//			LoadFrame window = new LoadFrame();
//			window.open();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	
	public LoadUI() {
		Data.lf=this;             //传�?引用要放在open()的前�?  不然不会执行这句代码
		open();
	}

	/**
	 * Open the window.
	 */
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
	protected void createContents() {
		shell = new Shell(SWT.MIN | SWT.Close|SWT.APPLICATION_MODAL);
		shell.setSize(566, 427);
		UIUtils.centerWindows(shell);
		shell.setText("\u6E38\u620F\u6863\u6848");
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));
		Record_Adapter ra=new Record_Adapter(this);
		Data.ra=ra;
		Composite composite = new Composite(shell, SWT.NONE);
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm = new SashForm(composite, SWT.VERTICAL);
		
		Composite composite_1 = new Composite(sashForm, SWT.NONE);
		composite_1.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm_1 = new SashForm(composite_1, SWT.NONE);
		
		Composite composite_3 = new Composite(sashForm_1, SWT.NONE);
		composite_3.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		table = new Table(composite_3, SWT.BORDER | SWT.FULL_SELECTION);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn unamecol = new TableColumn(table, SWT.NONE);
		unamecol.setWidth(100);
		unamecol.setText("玩家名");
		
		TableColumn difficultycol = new TableColumn(table, SWT.NONE);
		difficultycol.setWidth(100);
		difficultycol.setText("\u6E38\u620F\u96BE\u5EA6");
		
		TableColumn jinducol = new TableColumn(table, SWT.NONE);
		jinducol.setWidth(100);
		jinducol.setText("\u6E38\u620F\u8FDB\u5EA6");
		
		TableColumn datecol = new TableColumn(table, SWT.NONE);
		datecol.setWidth(107);
		datecol.setText("\u5B58\u6863\u65F6\u95F4");
		
		Menu menu = new Menu(table);
		table.setMenu(menu);
		
		MenuItem continueGame = new MenuItem(menu, SWT.NONE);
		continueGame.setText("继续游戏");
		continueGame.addSelectionListener(ra);
		
		deleteGame = new MenuItem(menu, SWT.NONE);
		deleteGame.setText("删除游戏");
		
		
		refresh = new MenuItem(menu, SWT.NONE);
		refresh.setText("刷新");
		
		
//		table.addSelectionListener(ra);
		
		
		Composite composite_4 = new Composite(sashForm_1, SWT.NONE);
		
		Button button = new Button(composite_4, SWT.NONE);
		button.setBounds(25, 121, 80, 27);
		button.setText("继续游戏");
		
		Label label = new Label(composite_4, SWT.NONE);
		label.setBounds(6, 25, 59, 22);
		label.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.NORMAL));
		label.setText("\u6E38\u620F\u96BE\u5EA6 :");
		
		combo = new Combo(composite_4, SWT.READ_ONLY);
		combo.setBounds(39, 51, 66, 25);
		combo.setItems(new String[] {"简单", "正常", "困难", "自定义"});
		combo.select(0);
		
		button.addSelectionListener(ra);
		sashForm_1.setWeights(new int[] {3, 1});
		
		Composite composite_2 = new Composite(sashForm, SWT.NONE);
		
		Button btnNewButton = new Button(composite_2, SWT.NONE);
		btnNewButton.setBounds(331, 20, 80, 27);
		btnNewButton.setText("\u4E0A\u4E00\u9875");
		
		Button btnNewButton_1 = new Button(composite_2, SWT.NONE);
		btnNewButton_1.setBounds(431, 20, 80, 27);
		btnNewButton_1.setText("\u4E0B\u4E00\u9875");
		
		refreshButton = new Button(composite_2, SWT.NONE);
		refreshButton.setBounds(37, 20, 80, 27);
		refreshButton.setText("刷新");
		
		sashForm.setWeights(new int[] {307, 56});
		showTable("简单");
		
		addEvent();
	}
	
	private void addEvent(){
		Combo_SelectionAdapter cs=new Combo_SelectionAdapter(this);
		combo.addSelectionListener(cs);  
		refresh.addSelectionListener(cs);
		refreshButton.addSelectionListener(cs);
		deleteGame.addSelectionListener(new SelectionAdapter(){
			@Override
			public void widgetSelected(SelectionEvent e) {
				GameRecord gr=new GameRecord();
				gr.deleteRecord_1();
				String nd=combo.getText();
				showTable(nd);
			}
		});
	}
	
	public void showTable(String nd) {
		table.removeAll();
		GameRecord gr=new GameRecord();
		List<CD_Table> list=gr.getGameRecord(nd);
		String[] data=new String[4];
		if(list!=null && list.size()>0){
				for(CD_Table t:list){
					TableItem item=new TableItem(table, SWT.None);
					data[0]=t.getUname();
					data[1]=t.getDifficulty();
					data[2]=t.getSchedule()*100+" %";
					data[3]=DateUtil.parseDateToString(t.getGameDate(), "MM-dd HH:mm");
					item.setText(data);
					item.setData("cd_id", t.getCd_id());
				}
		}
	}
}
