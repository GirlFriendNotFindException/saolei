package com.hynu.ui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.FillLayout;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

import com.hynu.bean.Message_Table;
import com.hynu.bean.ND_Table;
import com.hynu.dao.DBHelper;
import com.hynu.event.Messege_TreeAdapter;
import com.hynu.utils.UIUtils;

import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class MessegeUI {

	protected Shell shell;
	public Table table;
	public Tree tree;
	
	
	/**
	 * Launch the application.
	 * 
	 * @param args
	 * @throws Exception
	 */
	// public static void main(String[] args) {
	// try {
	// MessegeFrame_S window = new MessegeFrame_S();
	// window.open();
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }

	public MessegeUI() throws Exception {
		this.open();
	}

	/**
	 * Open the window.
	 * 
	 * @throws Exception
	 */
	public void open() throws Exception {
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
	 * 
	 * @throws Exception
	 */
	protected void createContents() throws Exception {
		shell = new Shell(SWT.MIN | SWT.Close|SWT.APPLICATION_MODAL);
		shell.setSize(571, 434);
		UIUtils.centerWindows(shell);
		shell.setText("\u6E38\u620F\u4FE1\u606F");
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));

		SashForm sashForm = new SashForm(shell, SWT.NONE);

		Composite composite = new Composite(sashForm, SWT.NONE);
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));

		SashForm sashForm_1 = new SashForm(composite, SWT.VERTICAL);

		Composite composite_2 = new Composite(sashForm_1, SWT.NONE);
		composite_2.setLayout(new FillLayout(SWT.HORIZONTAL));

		tree = new Tree(composite_2, SWT.BORDER);
		tree.setToolTipText("");
		final Messege_TreeAdapter mt=new Messege_TreeAdapter(this);
		tree.addSelectionListener(mt);

//		Tree tree_1 = new Tree(composite_3, SWT.BORDER);
//		tree_1.setToolTipText("");
//		TreeItem tri_1 = new TreeItem(tree_1, SWT.None);
////		tri_1.setText("所有难度");
//		
//		tree_1.addSelectionListener(new SelectionAdapter() {
//			@Override
//			public void widgetSelected(SelectionEvent e) {
//				tree_1addSelectionListener(mt);
//			}
//		});

		Composite composite_1 = new Composite(sashForm, SWT.NONE);
		composite_1.setLayout(new FillLayout(SWT.HORIZONTAL));

		table = new Table(composite_1, SWT.BORDER | SWT.FULL_SELECTION);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		TableColumn tblclmnId = new TableColumn(table, SWT.NONE);
		tblclmnId.setWidth(76);
		tblclmnId.setText("ID\r\n");

		TableColumn tableColumn_1 = new TableColumn(table, SWT.NONE);
		tableColumn_1.setWidth(62);
		tableColumn_1.setText("\u7B49\u7EA7");

		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn.setWidth(66);
		tblclmnNewColumn.setText("\u79EF\u5206");

		TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_1.setWidth(79);
		tblclmnNewColumn_1.setText("\u6700\u4F73\u7EAA\u5F55");

		TableColumn tableColumn_2 = new TableColumn(table, SWT.NONE);
		tableColumn_2.setWidth(69);
		tableColumn_2.setText("\u80DC\u5229\u5C40\u6570");
		
		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setWidth(72);
		tableColumn.setText("\u603B\u5C40\u6570");
		sashForm.setWeights(new int[] { 135, 465 });
		mt.gm.showTree(tree); 
		sashForm_1.setWeights(new int[] {165});
		
	}
}
