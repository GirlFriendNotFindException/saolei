package com.hynu.biz;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.hynu.bean.Users_Table;
import com.hynu.dao.DBHelper;
import com.hynu.utils.Encrypt;

public class LoginBiz {
	private DBHelper db=new DBHelper();
	
	//µÇÂ¼ÅÐ¶Ï
	public Users_Table login(String uname, String pwd){
		Users_Table user=null;
		String sql="select * from sl_users where uname=? and passwd=?";
		List<Object> params=new ArrayList<Object>();
		params.add(uname);
		params.add(Encrypt.md5AndSha(pwd));
		List<Map<String,String>>list=db.queryAll(sql, params);
		if(list!=null&&list.size()>0){
			Map<String,String> map=list.get(0);
			user=new Users_Table();
			user.setId(Integer.parseInt(map.get("id")));
			user.setUname( map.get("uname"));
			user.setPasswd( pwd);
			user.setLv_user(Integer.parseInt(map.get("lv_user")));
		}
		return user;
	}
	//×¢²áÅÐ¶Ï
	public boolean isNameExists(String uname){
		String sql="select * from sl_users where uname=?";
		List<Object> params=new ArrayList<Object>();
		params.add(uname);
		List<Map<String,String>>list=db.queryAll(sql, params);
		if(list==null||list.size()<=0){
			return false;
		}else{
			return true;
		}
	}
	
}
