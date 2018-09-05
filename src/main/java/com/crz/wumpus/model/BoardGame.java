package com.crz.wumpus.model;
import java.util.ArrayList;
import java.util.List;

public class BoardGame {

	private int boardSize;
	private Wumpus wumpus;
	private Gold gold;
	private Hunter hunter;
	private List<Pit> pitList;

	public BoardGame() {
		this.setPitList(new ArrayList<Pit>());
	}
	public Wumpus getWumpus() {
		return wumpus;
	}
	public void setWumpus(Wumpus wumpus) {
		this.wumpus = wumpus;
	}
	public Gold getGold() {
		return gold;
	}
	public void setGold(Gold gold) {
		this.gold = gold;
	}
	public List<Pit> getPitList() {
		return pitList;
	}
	public void setPitList(List<Pit> pitList) {
		this.pitList = pitList;
	}
	public Hunter getHunter() {
		return hunter;
	}
	public void setHunter(Hunter hunter) {
		this.hunter = hunter;
	}
	public int getBoardSize() {
		return boardSize;
	}
	public void setBoardSize(int boardSize) {
		this.boardSize = boardSize;
	}
	
}
