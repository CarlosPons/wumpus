package com.crz.wumpus.model;

public class Hunter extends Position {
	
	private int forwardX;
	private int forwardY;
	private int arrowsOnQuiver;
	private int piecesOfGold;
	private boolean isArrowShooted;
	private boolean isWumpusKilled;
	private boolean isMovementBlocked;
	private boolean isDead;
	private boolean isGoingOut;

	public int getForwardX() {
		return forwardX;
	}
	public void setForwardX(int forwardX) {
		this.forwardX = forwardX;
	}
	public int getForwardY() {
		return forwardY;
	}
	public void setForwardY(int forwardY) {
		this.forwardY = forwardY;
	}
	public int getArrowsOnQuiver() {
		return arrowsOnQuiver;
	}
	public void setArrowsOnQuiver(int arrowsOnQuiver) {
		this.arrowsOnQuiver = arrowsOnQuiver;
	}
	public int getPiecesOfGold() {
		return piecesOfGold;
	}
	public void setPiecesOfGold(int piecesOfGold) {
		this.piecesOfGold = piecesOfGold;
	}
	public boolean isArrowShooted() {
		return isArrowShooted;
	}
	public void setArrowShooted(boolean isArrowShooted) {
		this.isArrowShooted = isArrowShooted;
	}
	public boolean isWumpusKilled() {
		return isWumpusKilled;
	}
	public void setWumpusKilled(boolean isWumpusKilled) {
		this.isWumpusKilled = isWumpusKilled;
	}
	public boolean isMovementBlocked() {
		return isMovementBlocked;
	}
	public void setMovementBlocked(boolean isMovementBlocked) {
		this.isMovementBlocked = isMovementBlocked;
	}
	public boolean isDead() {
		return isDead;
	}
	public void setDead(boolean isDead) {
		this.isDead = isDead;
	}
	public boolean isGoingOut() {
		return isGoingOut;
	}
	public void setGoingOut(boolean isGoingOut) {
		this.isGoingOut = isGoingOut;
	}

	
}
