package com.hynu.bean;


public class Message_Table {
//	select a.uname, a.lv_user, a.WIN_COUNT, a.GSCORES, a.gametime, b.PLAY_COUNT 
	private String uname;
	private Integer lv_user;
	private Integer win_count;
	private Integer gscores;
	private Long gametime;
	private Integer play_count;
	
	public Message_Table() {
		// TODO Auto-generated constructor stub
	}

	public Message_Table(String uname, Integer lv_user, Integer win_count, Integer gscores, Long gametime,
			Integer play_count) {
		this.uname = uname;
		this.lv_user = lv_user;
		this.win_count = win_count;
		this.gscores = gscores;
		this.gametime = gametime;
		this.play_count = play_count;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public Integer getLv_user() {
		return lv_user;
	}

	public void setLv_user(Integer lv_user) {
		this.lv_user = lv_user;
	}

	public Integer getWin_count() {
		return win_count;
	}

	public void setWin_count(Integer win_count) {
		this.win_count = win_count;
	}

	public Integer getGscores() {
		return gscores;
	}

	public void setGscores(Integer gscores) {
		this.gscores = gscores;
	}

	public Long getGametime() {
		return gametime;
	}

	public void setGametime(Long gametime) {
		this.gametime = gametime;
	}

	public Integer getPlay_count() {
		return play_count;
	}

	public void setPlay_count(Integer play_count) {
		this.play_count = play_count;
	}

	@Override
	public String toString() {
		return "Message_Table [uname=" + uname + ", lv_user=" + lv_user + ", win_count=" + win_count + ", gscores="
				+ gscores + ", gametime=" + gametime + ", play_count=" + play_count + "]";
	}
	
}
