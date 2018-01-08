package com.hynu.bean;

import java.util.Date;

public class Users_Table {
	
	private Integer id;
	private String uname;
	private String passwd;
	private Integer lv_user ;
	private Date registerTime;
	
	public Users_Table() {
		// TODO Auto-generated constructor stub
	}

	public Users_Table(Integer id, String uname, String passwd, Integer lv_user, Date registerTime) {
		this.id = id;
		this.uname = uname;
		this.passwd = passwd;
		this.lv_user = lv_user;
		this.registerTime = registerTime;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public Integer getLv_user() {
		return lv_user;
	}

	public void setLv_user(Integer lv_user) {
		this.lv_user = lv_user;
	}

	public Date getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}

	@Override
	public String toString() {
		return "Users_Table [id=" + id + ", uname=" + uname + ", passwd=" + passwd + ", lv_user=" + lv_user
				+ ", registerTime=" + registerTime + "]";
	}
	
}
