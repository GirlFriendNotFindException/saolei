package com.hynu.event;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.TreeItem;

import com.hynu.dao.GameMessege;
import com.hynu.ui.MessegeUI;

public class Messege_TreeAdapter extends SelectionAdapter{
	private MessegeUI mf=null;
	public GameMessege gm=new GameMessege();
	
	public Messege_TreeAdapter() {
		// TODO Auto-generated constructor stub
	}
	
	public Messege_TreeAdapter( MessegeUI mf) {
		this.mf=mf;
	}
	
	@Override
	public void widgetSelected(SelectionEvent e) {
			TreeItem[] tris = mf.tree.getSelection();
			TreeItem tri = tris[0];
			String difficulty = tri.getText();
			try {
				gm.showTable(mf.table, difficulty);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	}
	
	
	
}
