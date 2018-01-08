package com.hynu.ui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;

import com.hynu.utils.UIUtils;

public class GameRuleUI {

	protected Shell shell;
    public GameRuleUI(){
    	open();
    }
	/**
	 * Launch the application.
	 * @param args
	 */
//	public static void main(String[] args) {
//		try {
//			Wanfajieshao window = new Wanfajieshao();
//			window.open();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

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
		shell = new Shell(SWT.MIN | SWT.Close);
		shell.setSize(677, 467);
		UIUtils.centerWindows(shell);
		shell.setText("\u626B\u96F7\u73A9\u6CD5\u4E0E\u9690\u85CF\u529F\u80FD");
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setFont(SWTResourceManager.getFont("Î¢ÈíÑÅºÚ", 12, SWT.NORMAL));
		lblNewLabel.setBounds(36, 21, 131, 27);
		lblNewLabel.setText("\u626B\u96F7\u600E\u4E48\u73A9\uFF1F");
		
		Label lblNewLabel_1 = new Label(shell, SWT.NONE);
		lblNewLabel_1.setText("\u626B\u96F7\u5C31\u662F\u8981\u628A\u6240\u6709\u975E\u5730\u96F7\u7684\u683C\u5B50\u63ED\u5F00\u5373\u80DC\u5229\uFF1B\u8E29\u5230\u5730\u96F7\u683C\u5B50\u5C31\u7B97\u5931\u8D25\u3002");
		lblNewLabel_1.setBounds(46, 54, 507, 17);
		
		Label lblNewLabel_2 = new Label(shell, SWT.NONE);
		lblNewLabel_2.setFont(SWTResourceManager.getFont("Î¢ÈíÑÅºÚ", 12, SWT.NORMAL));
		lblNewLabel_2.setBounds(36, 181, 134, 27);
		lblNewLabel_2.setText("\u6709\u4EC0\u4E48\u9690\u85CF\u529F\u80FD\uFF1F");
		
		Label lblNewLabel_3 = new Label(shell, SWT.NONE);
		lblNewLabel_3.setBounds(45, 77, 563, 17);
		lblNewLabel_3.setText("\u6E38\u620F\u4E3B\u533A\u57DF\u7531\u5F88\u591A\u4E2A\u65B9\u683C\u7EC4\u6210\u3002\u4F7F\u7528\u9F20\u6807\u5DE6\u952E\u968F\u673A\u70B9\u51FB\u4E00\u4E2A\u65B9\u683C\uFF0C\u65B9\u683C\u5373\u88AB\u6253\u5F00\u5E76\u663E\u793A\u51FA\u65B9\u683C\u4E2D\u7684\u6570\u5B57\uFF1B");
		
		Label lblNewLabel_4 = new Label(shell, SWT.NONE);
		lblNewLabel_4.setBounds(46, 100, 507, 17);
		lblNewLabel_4.setText("\u65B9\u683C\u4E2D\u6570\u5B57\u5219\u8868\u793A\u5176\u5468\u56F4\u76848\u4E2A\u65B9\u683C\u9690\u85CF\u4E86\u51E0\u9897\u96F7\uFF1B");
		
		Label lblNewLabel_5 = new Label(shell, SWT.NONE);
		lblNewLabel_5.setBounds(46, 125, 507, 17);
		lblNewLabel_5.setText("\u5728\u4F60\u8BA4\u4E3A\u6709\u96F7\u7684\u683C\u5B50\u4E0A\uFF0C\u70B9\u51FB\u53F3\u952E\u5373\u53EF\u6807\u8BB0\u96F7\uFF1B");
		
		Label lblNewLabel_7 = new Label(shell, SWT.NONE);
		lblNewLabel_7.setBounds(46, 214, 543, 17);
		lblNewLabel_7.setText("\uFF08\u91CD\u70B9\u5B9E\u6218\u529F\u80FD\uFF0C\u91CD\u70B9\u5B9E\u6218\u529F\u80FD\uFF0C\u91CD\u70B9\u5B9E\u6218\u529F\u80FD\uFF0C\u91CD\u8981\u7684\u4E8B\u60C5\u8BF4\u4E09\u904D\uFF01\uFF01\uFF01\uFF09");
		
		Label lblNewLabel_8 = new Label(shell, SWT.NONE);
		lblNewLabel_8.setBounds(10, 237, 687, 17);
		lblNewLabel_8.setText("\u63D0\u793A\u529F\u80FD\uFF1A\u5F53\u4F60\u5B9E\u5728\u5224\u65AD\u4E0D\u51FA\u6765\u54EA\u4E2A\u662F\u96F7\u7684\u65F6\u5019\uFF0C\u53EF\u4EE5\u9009\u62E9\u5728\u5DF2\u7ECF\u7FFB\u5F00\u7684\u5E26\u6709\u6570\u5B57\u7684\u6309\u94AE\u4E0A\u6309\u4E0B\u9F20\u6807\u5DE6\u952E\u548C\u53F3\u952E\u4E2D\u95F4\u7684\u952E\u3002");
		
		Label lblNewLabel_9 = new Label(shell, SWT.NONE);
		lblNewLabel_9.setBounds(56, 262, 533, 17);
		lblNewLabel_9.setText("\u8BD5\u8BD5\u5C31\u77E5\u9053\u4E86\u3002");

	}
}
