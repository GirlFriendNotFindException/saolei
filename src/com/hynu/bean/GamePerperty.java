package com.hynu.bean;

import java.io.Serializable;

public class GamePerperty implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7498866029344500808L;
	
	private Long gameTime;
	private Integer imageNO;
	private Integer rightBomb;
	private Integer restBomb;
	private Integer rowNum;
	private Integer colNum;
	private Integer bombNum;
	private Integer difficulty_id;
	
	public GamePerperty() {
		// TODO Auto-generated constructor stub
	}

	public GamePerperty(Long gameTime, Integer imageNO, Integer rightBomb, Integer restBomb, Integer rowNum,
			Integer colNum, Integer bombNum, Integer difficulty_id) {
		this.gameTime = gameTime;
		this.imageNO = imageNO;
		this.rightBomb = rightBomb;
		this.restBomb = restBomb;
		this.rowNum = rowNum;
		this.colNum = colNum;
		this.bombNum = bombNum;
		this.difficulty_id = difficulty_id;
	}

	public Long getGameTime() {
		return gameTime;
	}

	public void setGameTime(Long gameTime) {
		this.gameTime = gameTime;
	}

	public Integer getImageNO() {
		return imageNO;
	}

	public void setImageNO(Integer imageNO) {
		this.imageNO = imageNO;
	}

	public Integer getRightBomb() {
		return rightBomb;
	}

	public void setRightBomb(Integer rightBomb) {
		this.rightBomb = rightBomb;
	}

	public Integer getRestBomb() {
		return restBomb;
	}

	public void setRestBomb(Integer restBomb) {
		this.restBomb = restBomb;
	}

	public Integer getRowNum() {
		return rowNum;
	}

	public void setRowNum(Integer rowNum) {
		this.rowNum = rowNum;
	}

	public Integer getColNum() {
		return colNum;
	}

	public void setColNum(Integer colNum) {
		this.colNum = colNum;
	}

	public Integer getBombNum() {
		return bombNum;
	}

	public void setBombNum(Integer bombNum) {
		this.bombNum = bombNum;
	}

	public Integer getDifficulty_id() {
		return difficulty_id;
	}

	public void setDifficulty_id(Integer difficulty_id) {
		this.difficulty_id = difficulty_id;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "GamePerperty [gameTime=" + gameTime + ", imageNO=" + imageNO + ", rightBomb=" + rightBomb
				+ ", restBomb=" + restBomb + ", rowNum=" + rowNum + ", colNum=" + colNum + ", bombNum=" + bombNum
				+ ", difficulty_id=" + difficulty_id + "]";
	}

}
