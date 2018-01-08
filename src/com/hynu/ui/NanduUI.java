package com.hynu.ui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Group;
import org.eclipse.wb.swt.SWTResourceManager;

import com.hynu.biz.Data;
import com.hynu.event.Select_NanduAdapter;
import com.hynu.utils.UIUtils;

import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class NanduUI {

	public Shell shell;
	public Text rowText;
	public Text colText;
	public Text bombText;
	public Button sureButton=null;
	public Button cancleButton=null; 
	Label bombLabel ;
	Label rowLabel ;
	Label colLabel ;
	public Button btnRadioButton=null; 
	public Button btnRadioButton_1=null; 
	public Button btnRadioButton_2=null; 
	public Button btnRadioButton_3=null; 
	public Group group=null;
	public Button btnCheckButton=null;
	public Button btnCheckButton_1=null;
	public Button btnCheckButton_2=null;
	public Button btnCheckButton_3=null;
    

	/**
	 * Launch the application.
	 * @param args
	 */
//	public static void main(String[] args) {
//		try {
//			NanduFrame window = new NanduFrame();
//			window.open();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	
	public NanduUI() {
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
		shell = new Shell(SWT.MIN | SWT.Close| SWT.APPLICATION_MODAL);
		shell.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		shell.setSize(521, 410);
		UIUtils.centerWindows(shell);
		shell.setText("一点难度都没有");
		
		sureButton = new Button(shell, SWT.NONE);
		sureButton.setBounds(279, 329, 80, 27);
		sureButton.setText("确定");
		sureButton.addSelectionListener(new Select_NanduAdapter(this));
		
		cancleButton = new Button(shell, SWT.NONE);
		cancleButton.setBounds(383, 329, 80, 27);
		cancleButton.setText("返回");
		
		group = new Group(shell, SWT.NONE);
		group.setText("难度");
		group.setBounds(45, 23, 420, 258);
		
		btnRadioButton = new Button(group, SWT.RADIO|SWT.MULTI);
		btnRadioButton.setLocation(31, 41);
		btnRadioButton.setSize(121, 33);
		btnRadioButton.setText("初级  \n 9*9\r\n");
		
		btnRadioButton_1 = new Button(group, SWT.RADIO);
		btnRadioButton_1.setLocation(31, 114);
		btnRadioButton_1.setSize(97, 17);
		btnRadioButton_1.setText("中级  12*12");
		
		btnRadioButton_2 = new Button(group, SWT.RADIO);
		btnRadioButton_2.setLocation(31, 182);
		btnRadioButton_2.setSize(97, 17);
		btnRadioButton_2.setText("高级  16*16");
		
		btnRadioButton_3 = new Button(group, SWT.RADIO);
		btnRadioButton_3.setLocation(269, 49);
		btnRadioButton_3.setSize(97, 17);
		btnRadioButton_3.setText("自定义");
		
		bombText = new Text(group, SWT.BORDER);
		bombText.setText("80");
		bombText.setEnabled(false);
		bombText.setBounds(320, 179, 73, 23);

		rowText = new Text(group, SWT.BORDER);
		rowText.setText("13");
		rowText.setEnabled(false);
		rowText.setLocation(320, 89);
		rowText.setSize(73, 23);

		colText = new Text(group, SWT.BORDER);
		colText.setText("30");
		colText.setEnabled(false);
		colText.setBounds(320, 135, 73, 23);

		
		bombLabel = new Label(group, SWT.NONE);
		bombLabel.setText("雷数(10-600):");
		bombLabel.setEnabled(false);
		bombLabel.setBounds(239, 182, 75, 23);

		rowLabel = new Label(group, SWT.NONE);
		rowLabel.setLocation(239, 92);
		rowLabel.setSize(70, 23);
		rowLabel.setEnabled(false);
		rowLabel.setText("\u884C\u6570(9-15):");
		
		colLabel = new Label(group, SWT.NONE);
		colLabel.setEnabled(false);
		colLabel.setBounds(239, 138, 75, 23);
		colLabel.setText("\u5217\u6570(9-35):");
		
		addEvent();
	}

	private void addEvent() {
		btnRadioButton_1.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				addFocusListener(false);
			}
		});
		btnRadioButton_2.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				addFocusListener(false);
			}
		});
		btnRadioButton.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				addFocusListener( false);
			}
		});
		btnRadioButton_3.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				addFocusListener(true);
			}
		});
		cancleButton.addSelectionListener(new SelectionAdapter(){
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.dispose();
			}
		});

	}
	
	private void addFocusListener(boolean flag) {
		rowLabel.setEnabled(flag);
		rowText.setEnabled(flag);
		colLabel.setEnabled(flag);
		colText.setEnabled(flag);
		bombLabel.setEnabled(flag);
		bombText.setEnabled(flag);
	}

	// 改变Data缓冲池中的数据
	public void setProperty(int rowNum, int colNum, int BombNum, int width, int height, int difficulty_id) {
		Data.rowNum_1=rowNum;
		Data.colNum_1=colNum;
		Data.BombNum = BombNum;
		Data.width = width;
		Data.height = height;
		Data.difficulty_id = difficulty_id;
		shell.dispose();
		Data.gameUI.shell.dispose();
		GameUI newframe = new GameUI();

	}
}
