package com.hynu.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

import com.hynu.bean.Message_Table;
import com.hynu.bean.ND_Table;
import com.hynu.biz.Data;

public class GameMessege {

	/**
	 * 操作统计信息界面的数据类
	 * 
	 */

	public GameMessege() {
		// TODO Auto-generated constructor stub
	}

	public void showTable(Table table, String difficulty) throws Exception {
		table.removeAll();
		List<Message_Table> list = getMessageList(difficulty);
		if (list != null && list.size() > 0) {
			for (Message_Table m : list) {
//				System.out.println(m);
				String[] d = new String[6];
				TableItem item = new TableItem(table, SWT.NONE);
				d[0] = m.getUname();
				d[1] = m.getLv_user().toString();
				d[2] = m.getGscores().toString();
				d[3] = m.getGametime() / 1000 + "s";
				d[4] = m.getWin_count().toString();
				if (m.getWin_count() == 0) {
					d[3] = "无";
				}
				d[5] = m.getPlay_count().toString();
				item.setText(d);
			}
		}

	}

	public List<Message_Table> getMessageList(String difficulty) throws Exception {
		DBHelper<Message_Table> db = new DBHelper<>();
		List<Object> params = new ArrayList<>();
		
		String sql = "select *from sl_dj, sl_nd, sl_users where iswin='获胜' and sl_dj.difficulty_id=sl_nd.difficulty_id and sl_users.id=sl_dj.id";
		if (difficulty != null && !"".equals(difficulty)) {
			sql = sql + " and difficulty=? and uname=?";
			params.add(difficulty);
			params.add(Data.user.getUname());
		}
		List<Map<String, String>> list_1 = db.queryAll(sql, params);
		if (list_1 != null && list_1.size() > 0) {
			sql = "select a.uname, a.GSCORES,a.difficulty, a.lv_user,  a.gametime, a.win_COUNT,  (a.win_COUNT+b.won_count) as play_count from "
					+ "(select uname,sum(scores) as GSCORES, difficulty, lv_user, min(gametime) as gametime, count(*) as win_COUNT from sl_users, sl_nd, sl_scores, sl_dj "
					+ "where sl_users.id=sl_dj.id and sl_dj.difficulty_id=sl_nd.difficulty_id and sl_dj.difficulty_id=sl_scores.difficulty_id and iswin='获胜' "
					+ "group by uname, difficulty, lv_user)a, "
					+ "(select uname, count(*) as won_COUNT from sl_users, sl_nd, sl_scores, sl_dj "
					+ "where sl_users.id=sl_dj.id and sl_dj.difficulty_id=sl_nd.difficulty_id and sl_dj.difficulty_id=sl_scores.difficulty_id and iswin='失败' "
					+ "group by uname)b "
					+ "where a.uname=b.uname and a.difficulty=?";
		} else {
			sql = "select uname,0 as GSCORES, difficulty, lv_user, 0 as gametime, 0 as win_count ,count(*) as PLAY_COUNT from sl_users, sl_nd, sl_scores, sl_dj "
					+ "where sl_users.id=sl_dj.id and sl_dj.difficulty_id=sl_nd.difficulty_id and sl_dj.difficulty_id=sl_scores.difficulty_id and difficulty=?"
					+ "group by uname, difficulty, lv_user";
		}
		if (difficulty != null && !"".equals(difficulty)) {
			params.removeAll(params);
//			sql = sql + " and difficulty=?";
			params.add(difficulty);
		}
		List<Message_Table> list = db.select(sql, params, Message_Table.class);
		return list;
	}

	public void showTree(Tree tree) throws Exception {
		final TreeItem tri = new TreeItem(tree, SWT.None);
		// tri.setData("flag","false");
		tri.setText("选择难度");
		showTreeNode(tri, null);
	}

	private void showTreeNode(TreeItem tri, String params) throws Exception {
		DBHelper<ND_Table> db = new DBHelper<>();
		String sql = "select difficulty from sl_nd where '1'='1' ";
		List<Object> paramslist = new ArrayList<>();
		if (params != null && !"".equals(params)) {
			sql = sql + " and difficulty=?";
			paramslist.add(params);
		}
		List<ND_Table> list = db.select(sql, paramslist, ND_Table.class);
//		tri.removeAll();
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				TreeItem tri_1 = new TreeItem(tri, SWT.None);
				ND_Table nd = list.get(i);
				tri_1.setText(nd.getDifficulty().trim());
				// tri_1.setData("flag","false");
			}
		}
	}

}
