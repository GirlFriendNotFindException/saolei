package com.hynu.bean;

import java.io.Serializable;

public class ND_Table implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5491727347999405284L;
	
	private String difficulty;
	
	public ND_Table() {
		// TODO Auto-generated constructor stub
	}

	public ND_Table(String difficulty) {
		this.difficulty = difficulty;
	}

	public String getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}

	@Override
	public String toString() {
		return "ND_Table [difficulty=" + difficulty + "]";
	}
	
}
