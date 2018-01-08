package com.hynu.event;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.TableItem;

import com.hynu.bean.CD_Table;
import com.hynu.dao.GameRecord;
import com.hynu.ui.LoadUI;
import com.hynu.utils.DateUtil;

public class Combo_SelectionAdapter extends SelectionAdapter{
	private LoadUI load;
	
	public Combo_SelectionAdapter() {
		// TODO Auto-generated constructor stub
	}
	
	public Combo_SelectionAdapter(LoadUI load) {
		this.load=load;
	}
	
	
	/**
	 * private Integer cd_id;
	private String uname;
	private Integer difficulty_id;
	private Double schedule;
	private Date gameDate;
	 */
	@Override
	public void widgetSelected(SelectionEvent e) {
		
		String  nd=load.combo.getText();
		load.showTable(nd);
	}

	
}
