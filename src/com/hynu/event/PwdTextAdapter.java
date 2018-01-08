package com.hynu.event;

import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;

import com.hynu.ui.LoginUI;

public class PwdTextAdapter extends KeyAdapter{
	private LoginUI login;
	
	public PwdTextAdapter() {
		// TODO Auto-generated constructor stub
	}
	
	public PwdTextAdapter( LoginUI login ) {
		this.login=login;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(login.txtSa.getText().trim()!=null && !"".equals(login.txtSa.getText())){
			if(e.keyCode==13 || e.keyCode==16777296){
				try {
					login.addLoginListener(login.loginButton);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();              
				}
			}
			
		}
	}
	

	
}
