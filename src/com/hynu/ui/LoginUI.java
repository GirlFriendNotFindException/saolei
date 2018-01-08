package com.hynu.ui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;
import com.hynu.bean.Users_Table;
import com.hynu.biz.Data;
import com.hynu.biz.LoginBiz;
import com.hynu.event.PwdTextAdapter;
import com.hynu.utils.ImageUtil;
import com.hynu.utils.UIUtils;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Composite;

public class LoginUI {

	public Shell shell;
	private Text text;
	public Text txtSa;
	public Button loginButton = null;

	/**
	 * Launch the application.a
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			LoginUI window = new LoginUI();                                                            
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
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
	// 标题
	protected void createContents() {

		shell = new Shell();
		shell.setImage(SWTResourceManager.getImage(LoginUI.class, "/image/boom.jpg"));
		shell.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
		shell.setSize(450, 300);
		UIUtils.centerWindows(shell);
		shell.setText("扫雷");
		shell.setBackgroundMode(SWT.INHERIT_FORCE);

		Composite composite = new Composite(shell, SWT.NONE);
		composite.setBounds(0, 0, 434, 261);
		ImageUtil.setImageAdapte(shell, composite, "image\\lg.jpg");

		Label lblNewLabel = new Label(composite, SWT.NONE);
		lblNewLabel.setLocation(109, 86);
		lblNewLabel.setSize(32, 20);
		lblNewLabel.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
		lblNewLabel.setText("账号");
		text = new Text(composite, SWT.BORDER);
		text.setBounds(169, 87, 121, 23);
		text.setTextLimit(8);
		Label lblNewLabel_1 = new Label(composite, SWT.NONE);
		lblNewLabel_1.setLocation(109, 131);
		lblNewLabel_1.setSize(32, 24);
		lblNewLabel_1.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
		lblNewLabel_1.setText("密码");
		txtSa = new Text(composite, SWT.BORDER | SWT.PASSWORD);
		txtSa.setBounds(169, 132, 121, 23);
		txtSa.setTextLimit(8);
		txtSa.addKeyListener(new PwdTextAdapter(this));

		// 登录按钮
		loginButton = new Button(composite, SWT.NONE);
		loginButton.setImage(null);
		loginButton.setLocation(109, 197);
		loginButton.setSize(80, 27);
		loginButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				addLoginListener(loginButton);
			}
		});
		loginButton.setText("\u767B\u5F55");

		// 重置按钮
		Button btnNewButton_1 = new Button(composite, SWT.NONE);
		btnNewButton_1.setLocation(254, 197);
		btnNewButton_1.setSize(80, 27);
		btnNewButton_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				text.setText("");
				txtSa.setText("");
			}
		});
		btnNewButton_1.setText("\u91CD\u7F6E");
		// 注册文本
		Label label = new Label(composite, SWT.NONE);
		label.setBounds(311, 90, 24, 17);
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				RegisterUI rs = new RegisterUI(LoginUI.this);
				LoginUI.this.shell.setVisible(false);
				rs.open();
			}
		});
		label.setText("\u6CE8\u518C");

	}

	// 登录按钮判断
	public void addLoginListener(final Button btnNewButton_2) {
		String uname = text.getText();
		String pwd = txtSa.getText();
		if (uname == null || "".equals(uname)) {
			UIUtils.showMessageDialog(shell, "用户名不能为空", "提示");
			return;
		} else if (pwd != null && !"".equals(pwd)) {
			LoginBiz lb = new LoginBiz();
			Users_Table u = lb.login(uname, pwd);
			if (u != null) {
				UIUtils.showMessageDialog(shell, "欢迎您：" + uname, "提示");
				Data.user = u;
				btnNewButton_2.setEnabled(true);// 查询有，则登录按钮启用
				LoginUI.this.shell.dispose();
				GameUI fm = new GameUI();
			} else {
				text.setSelection(0, text.getText().length());
				text.setFocus();
				txtSa.setText("");
				text.setText("");
				UIUtils.showMessageDialog(shell, "用户名或密码错误", "提示");
			}
		} else {
			UIUtils.showMessageDialog(shell, "密码不能为空!", "提示");
		}
	}
}
