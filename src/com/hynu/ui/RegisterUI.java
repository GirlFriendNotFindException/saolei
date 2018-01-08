package com.hynu.ui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import com.hynu.biz.Data;
import com.hynu.biz.LoginBiz;
import com.hynu.biz.RegisterBiz;
import com.hynu.utils.ImageUtil;
import com.hynu.utils.Log;
import com.hynu.utils.UIUtils;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.widgets.Composite;

public class RegisterUI {

	public Shell shell;
	public Text text;
	public Text txtSa;
	public Button registerButton;
	public Label lblNewLabel_2;
	public Button btnNewButton_1;
	public LoginUI loginUI;
	private RegisterBiz rgb=new RegisterBiz(this);
	private Composite composite;
	
	public RegisterUI() {
		
	}

	public RegisterUI(LoginUI loginUI) {
		this.loginUI = loginUI;
	}

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
//	 public static void main(String[] args) {
//	 try {
//	 RegisterUI window = new RegisterUI ();
//	 window.open();
//	 } catch (Exception e) {
//	 e.printStackTrace();
//	 }
//	 }

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
	// 标题
	protected void createContents() {
		shell = new Shell();
		shell.setImage(SWTResourceManager.getImage(LoginUI.class,"/image/boom.jpg"));
		shell.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
		shell.setSize(450, 302);
		UIUtils.centerWindows(shell);
		shell.setText("扫雷");
		shell.setBackgroundMode(SWT.INHERIT_FORCE);
		
		composite = new Composite(shell, SWT.NONE);
		composite.setBounds(0, 0, 434, 263);
		ImageUtil.setImageAdapte(shell, composite, "image\\register.jpg");
		
				Label lblNewLabel = new Label(composite, SWT.NONE);
				lblNewLabel.setLocation(109, 86);
				lblNewLabel.setSize(32, 20);
				lblNewLabel.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
				lblNewLabel.setText("账号");
				// 注册文本
				text = new Text(composite, SWT.BORDER);
				text.setBounds(169, 87, 121, 23);
				text.setTextLimit(8);
				// txtSa.setTextLimit(8);
				// 注册窗口后面的提示
				lblNewLabel_2 = new Label(composite, SWT.NONE);
				lblNewLabel_2.setLocation(307, 90);
				lblNewLabel_2.setSize(92, 20);
				Label lblNewLabel_1 = new Label(composite, SWT.NONE);
				lblNewLabel_1.setLocation(109, 129);
				lblNewLabel_1.setSize(32, 24);
				lblNewLabel_1.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
				lblNewLabel_1.setText("密码");
				txtSa = new Text(composite, SWT.BORDER | SWT.PASSWORD);
				txtSa.setLocation(169, 130);
				txtSa.setSize(121, 23);
				txtSa.setTextLimit(8);
				// lblNewLabel_2.setText("\u7528\u6237\u540D\u5DF2\u88AB\u5360\u7528");

				// 注册按钮
				registerButton = new Button(composite, SWT.NONE);
				registerButton.setImage(null);
				registerButton.setLocation(109, 197);
				registerButton.setSize(80, 27);
				registerButton.setText("注册");
				
						// 返回
						btnNewButton_1 = new Button(composite, SWT.NONE);
						btnNewButton_1.setLocation(244, 197);
						btnNewButton_1.setSize(80, 27);
						btnNewButton_1.setText("返回");

		rgb.addEvent();
	}

	
}
