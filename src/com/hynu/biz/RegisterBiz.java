package com.hynu.biz;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.hynu.dao.DBHelper;
import com.hynu.ui.LoginUI;
import com.hynu.ui.RegisterUI;
import com.hynu.utils.Encrypt;
import com.hynu.utils.Log;
import com.hynu.utils.UIUtils;

public class RegisterBiz {
	
	private RegisterUI rg;
	
	
	
	public RegisterBiz(RegisterUI rg ) {
		this.rg=rg;
	}
	
	
	
	public void addEvent() {
		rg.text.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				addUnameListener(rg.lblNewLabel_2, rg.registerButton);
			}
		});
		rg.btnNewButton_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				rg.shell.setVisible(false);
				rg.loginUI.shell.setVisible(true);
			}
		});

		rg.registerButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				addRegisterListener();
				
			}
		});
	}

	private void addUnameListener(final Label lblNewLabel_2, final Button btnNewButton_2) {
		String uname = rg.text.getText().trim();
		if (uname == null || "".equals(uname)) {
			return;
		}
		LoginBiz lb = new LoginBiz();
		boolean r = lb.isNameExists(uname);
		if (!r) {
			lblNewLabel_2.setText("");
			btnNewButton_2.setEnabled(true);// 没有使用，则组册按钮启用
		} else {
			rg.text.setSelection(0, rg.text.getText().length());
			rg.text.setFocus();
			lblNewLabel_2.setText("用户名已被注册");
//			text.setText("");
			btnNewButton_2.setEnabled(false);
		}
	}

	private void addRegisterListener() {
		LoginBiz lb = new LoginBiz();
		String uname = rg.text.getText();
		String pwd = rg.txtSa.getText();
		if (uname == null || "".equals(uname)) {
			UIUtils.showMessageDialog(rg.shell, "用户名不能为空!", "提示");
			return;
		} else if(pwd==null ||"".equals(pwd)){
			UIUtils.showMessageDialog(rg.shell, "密码不能为空!", "提示");
		}
		else if (!lb.isNameExists(uname)) {
			try {
				zc(uname, pwd);
				UIUtils.showMessageDialog(rg.shell, "注册成功", "提示");
			} catch (Exception e1) {
				Log.error(e1);
				UIUtils.showMessageDialog(rg.shell,e1.getMessage(), "错误");
			}
		}
		else {
			UIUtils.showMessageDialog(rg.shell, "用户名已被注册!", "提示");
			
		}
	}
	
	public void zc(String uname,String pwd){
		
		pwd=Encrypt.md5AndSha(pwd);
		String sql="insert into sl_users(uname, passwd, registerTime, lv_user) values(?,?,sysdate(),1)";
		List<Object> params=new ArrayList<Object>();
		params.add(uname);
		params.add(pwd);
		DBHelper db=new DBHelper();
		db.update(sql, params);
	}
}
