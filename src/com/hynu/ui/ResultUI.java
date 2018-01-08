package com.hynu.ui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;

import java.util.Date;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.wb.swt.SWTResourceManager;

import com.hynu.bean.Message_Table;
import com.hynu.biz.Data;
import com.hynu.dao.GameMessege;
import com.hynu.event.Start_SelectionAdapter;
import com.hynu.utils.DateUtil;
import com.hynu.utils.MathUtil;
import com.hynu.utils.UIUtils;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

public class ResultUI {

	public Shell shell;
	Label resultLabel;
	Label timeLabel;
	Label shortTimeLabel;
	Label play_countLabel;
	Label win_countLabel;
	Button start;
	Button exit;
	Label gameDateLabel;
	Label SLLabel;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	// public static void main(String[] args) {
	// try {
	// IswinFrame window = new IswinFrame();
	// window.open();
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }

	public ResultUI() {
		Data.iswin=this;
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
		shell.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
		shell.setSize(450, 314);
		UIUtils.centerWindows(shell);
		shell.setText("胜利!");

		resultLabel = new Label(shell, SWT.NONE);
		resultLabel.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
		resultLabel.setBounds(168, 22, 154, 21);
		resultLabel.setText("\u606D\u559C! \u60A8\u8D62\u4E86!");

		timeLabel = new Label(shell, SWT.NONE);
		timeLabel.setBounds(33, 62, 90, 17);
		timeLabel.setText("\u65F6\u95F4\uFF1A");

		shortTimeLabel = new Label(shell, SWT.NONE);
		shortTimeLabel.setBounds(33, 106, 101, 17);
		shortTimeLabel.setText("\u6700\u4F73\u65F6\u95F4\uFF1A");

		play_countLabel = new Label(shell, SWT.NONE);
		play_countLabel.setBounds(33, 148, 101, 17);
		play_countLabel.setText("\u5DF2\u73A9\u6E38\u620F\uFF1A");

		win_countLabel = new Label(shell, SWT.NONE);
		win_countLabel.setBounds(33, 187, 101, 17);
		win_countLabel.setText("\u5DF2\u8D62\u6E38\u620F\uFF1A");

		exit = new Button(shell, SWT.NONE);
		exit.setBounds(333, 224, 80, 27);
		exit.setText("\u8FD4\u56DE");

		gameDateLabel = new Label(shell, SWT.NONE);
		gameDateLabel.setBounds(241, 106, 113, 17);
		gameDateLabel.setText("\u65E5\u671F\uFF1A");

		SLLabel = new Label(shell, SWT.NONE);
		SLLabel.setBounds(241, 187, 81, 17);
		SLLabel.setText("\u80DC\u7387\uFF1A");

		start = new Button(shell, SWT.NONE);
		start.setBounds(212, 224, 80, 27);
		start.setText("\u518D\u6765\u4E00\u5C40");
		setGameResult();
		addEvent();
	}

	private void addEvent() {
		start.addSelectionListener(new Start_SelectionAdapter(this));
		exit.addSelectionListener(new SelectionAdapter(){
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.dispose();
			}
		});
	}

	public void setGameResult() {
		GameMessege gm = new GameMessege();
		String difficulty = null;
		if (Data.sl.difficuity_id == 1) {
			difficulty = "简单";
		} else if (Data.sl.difficuity_id == 2) {
			difficulty = "正常";
		} else if (Data.sl.difficuity_id == 3) {
			difficulty = "困难";
		} else if (Data.sl.difficuity_id == 4) {
			difficulty = "自定义";
		}
		Message_Table m = null;
		List<Message_Table> list = null;
		try {
			list = gm.getMessageList(difficulty);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (list != null && list.size() > 0) {
			for (Message_Table t : list) {
				if (t.getUname().equals(Data.user.getUname())) {
					m = t;
				}
			}
		}
		if (m != null) {
			if (Data.sl.flag) {
				resultLabel.setText("恭喜! 你赢了!");
			} else {
				resultLabel.setText("很遗憾!! 你输了!");
			}
			timeLabel.setText("时间：" + Data.sl.gameTime / 1000 + " s");
			
			
			String date = DateUtil.parseDateToString(new Date(), "yyyy-MM-dd");
			gameDateLabel.setText("日期：" + date);
			
			
		}
	}
}
