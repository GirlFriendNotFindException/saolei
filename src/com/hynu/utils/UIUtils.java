package com.hynu.utils;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

public class UIUtils {

	public static void maxWindows(Shell shell) {
		Display display = shell.getDisplay();
		Rectangle b = display.getBounds();
		int height = b.height;
		int width = b.width;
		shell.setSize(width, height);
		shell.setLocation(0, 0);
	}

	public static void showMessageDialog(Shell shell, String title, String messege) {
		if (shell.isDisposed() == false) {
			MessageBox m = new MessageBox(shell, SWT.None);
			m.setMessage(title);
			m.setText(messege);
			m.open();
		}
	}

	// 注意这个方法要放在 setSize() 的后面
	public static void centerWindows(Shell shell) {
		Display display = shell.getDisplay();
		Rectangle disBounds = display.getBounds();
		Rectangle shellBounds = shell.getBounds();
		int x = (disBounds.width - shellBounds.width) / 2;
		int y = (disBounds.height - shellBounds.height) / 2;
		shell.setLocation(x, y);
	}

	// bug
	public static void closeWindow(final Shell shell) {
		shell.addDisposeListener(new DisposeListener() {
			@Override
			public void widgetDisposed(DisposeEvent arg0) {
				MessageBox mb = new MessageBox(shell, SWT.YES | SWT.NO);
				mb.setText("退出操作？");
				mb.setMessage("确定退出？");
				int r = mb.open();
				if (r == SWT.YES) {
					System.exit(1);
				}
			}
		});
	}

}
