package com.hynu.bean;

import java.io.Serializable;

public class Bomb implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3961379979154768198L;
	
	private int num_x, num_y;
	private int BombRoundCount;
	private boolean isBomb; 
	private boolean isClicked;
	private int BombFlag;
	private boolean isRight;
	
	int v;      // 待检测的数

	int sign;  // 符号结果，0表示正数或者0，-1表示负数
	 // -1, 0, or +1
	
	public boolean isWen() {
		sign = (v >> 0) - (v << 0);
		return isWen;
	}

	public void setWen(boolean isWen) {
		this.isWen = isWen;
	}

	private boolean isWen;
	
	public Bomb() {
		// TODO Auto-generated constructor stub
	}
	
	public Bomb(int num_x, int num_y) {
		this.num_x = num_x;
		this.num_y = num_y;
		BombRoundCount = 9;
		this.isBomb = false;
		this.isClicked = false;
		BombFlag = 0;
		this.isRight = false;
	}
	public int getNum_x() {
		return num_x;
	}
	public void setNum_x(int num_x) {
		this.num_x = num_x;
	}
	public int getNum_y() {
		return num_y;
	}
	public void setNum_y(int num_y) {
		this.num_y = num_y;
	}
	public int getBombRoundCount() {
		return BombRoundCount;
	}
	public void setBombRoundCount(int bombRoundCount) {
		BombRoundCount = bombRoundCount;
	}
	public boolean isBomb() {
		return isBomb;
	}
	public void setBomb(boolean isBomb) {
		this.isBomb = isBomb;
	}
	public boolean isClicked() {
		return isClicked;
	}
	public void setClicked(boolean isClicked) {
		this.isClicked = isClicked;
	}
	public int getBombFlag() {
		return BombFlag;
	}
	public void setBombFlag(int bombFlag) {
		BombFlag = bombFlag;
	}
	public boolean isRight() {
		return isRight;
	}
	public void setRight(boolean isRight) {
		this.isRight = isRight;
	}

	@Override
	public String toString() {
		return "Bomb [num_x=" + num_x + ", num_y=" + num_y + ", BombRoundCount=" + BombRoundCount + ", isBomb=" + isBomb
				+ ", isClicked=" + isClicked + ", BombFlag=" + BombFlag + ", isRight=" + isRight + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + BombFlag;
		result = prime * result + BombRoundCount;
		result = prime * result + (isBomb ? 1231 : 1237);
		result = prime * result + (isClicked ? 1231 : 1237);
		result = prime * result + (isRight ? 1231 : 1237);
		result = prime * result + (isWen ? 1231 : 1237);
		result = prime * result + num_x;
		result = prime * result + num_y;
		result = prime * result + sign;
		result = prime * result + v;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bomb other = (Bomb) obj;
		if (BombFlag != other.BombFlag)
			return false;
		if (BombRoundCount != other.BombRoundCount)
			return false;
		if (isBomb != other.isBomb)
			return false;
		if (isClicked != other.isClicked)
			return false;
		if (isRight != other.isRight)
			return false;
		if (isWen != other.isWen)
			return false;
		if (num_x != other.num_x)
			return false;
		if (num_y != other.num_y)
			return false;
		if (sign != other.sign)
			return false;
		if (v != other.v)
			return false;
		return true;
	}
	
	
	
}
