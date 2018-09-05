package com.crz.wumpus.model;

public class Position {
	
	private int x;
	private int y;
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public void setPosition (Position pos) {
		this.x = pos.getX();
		this.y = pos.getY();
	}

}
