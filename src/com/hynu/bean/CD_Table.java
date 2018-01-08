package com.hynu.bean;

import java.util.Date;

public class CD_Table {
	/**
	 * ”Œœ∑µµ∞∏±Ì
	 */
	
	private Integer cd_id;
	private String uname;
	private String difficulty;
	private Double schedule;
	private Date gameDate;
	
	public CD_Table() {
		// TODO Auto-generated constructor stub
	}

	public CD_Table(Integer cd_id, String uname, String difficulty, Double schedule, Date gameDate) {
		this.cd_id = cd_id;
		this.uname = uname;
		this.difficulty = difficulty;
		this.schedule = schedule;
		this.gameDate = gameDate;
	}

	public Integer getCd_id() {
		return cd_id;
	}

	public void setCd_id(Integer cd_id) {
		this.cd_id = cd_id;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}

	public Double getSchedule() {
		return schedule;
	}

	public void setSchedule(Double schedule) {
		this.schedule = schedule;
	}

	public Date getGameDate() {
		return gameDate;
	}

	public void setGameDate(Date gameDate) {
		this.gameDate = gameDate;
	}

	@Override
	public String toString() {
		return "CD_Table [cd_id=" + cd_id + ", uname=" + uname + ", difficulty=" + difficulty + ", schedule=" + schedule
				+ ", gameDate=" + gameDate + "]";
	}

}
