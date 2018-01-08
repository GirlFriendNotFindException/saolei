package com.hynu.bean;

import java.util.Date;

public class DJ_Table {
	private Integer id;
	private Integer dj_id;
	private boolean isWin;
	private Integer difficulty_id;
	private Long gameTime;
	private Date gameDate;
	
	public DJ_Table() {
		// TODO Auto-generated constructor stub
	}

	

	public DJ_Table(Integer id, Integer dj_id, boolean isWin, Integer difficulty_id, Long gameTime, Date gameDate) {
		this.id = id;
		this.dj_id = dj_id;
		this.isWin = isWin;
		this.difficulty_id = difficulty_id;
		this.gameTime = gameTime;
		this.gameDate = gameDate;
	}



	public Date getGameDate() {
		return gameDate;
	}


	public void setGameDate(Date gameDate) {
		this.gameDate = gameDate;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getDj_id() {
		return dj_id;
	}

	public void setDj_id(Integer dj_id) {
		this.dj_id = dj_id;
	}

	public boolean isWin() {
		return isWin;
	}

	public void setWin(boolean isWin) {
		this.isWin = isWin;
	}

	public Integer getDifficulty_id() {
		return difficulty_id;
	}

	public void setDifficulty_id(Integer difficulty_id) {
		this.difficulty_id = difficulty_id;
	}

	public Long getGameTime() {
		return gameTime;
	}

	public void setGameTime(Long gameTime) {
		this.gameTime = gameTime;
	}


	@Override
	public String toString() {
		return "DJ_Table [id=" + id + ", dj_id=" + dj_id + ", isWin=" + isWin + ", difficulty_id=" + difficulty_id
				+ ", gameTime=" + gameTime + ", gameDate=" + gameDate + "]";
	}

	
}
