package com.crz.wumpus.controller;

import java.util.Scanner;

import com.crz.wumpus.model.BoardGame;
import com.crz.wumpus.model.Gold;
import com.crz.wumpus.model.Hunter;
import com.crz.wumpus.model.Pit;
import com.crz.wumpus.model.Wumpus;

public class ActionController {
	
	private BoardGame boardGame;
	private Scanner reader;
	
	// ActionController needs a KeyboardReader and a BoardGame
	public ActionController(BoardGame boardGame) {
		reader = new Scanner (System.in);
		this.boardGame = boardGame;
	}

	public void moveForward() {
		int destX = boardGame.getHunter().getX()+boardGame.getHunter().getForwardX();
		int destY = boardGame.getHunter().getY()+boardGame.getHunter().getForwardY();
		if ((destX < 0 || destX >= boardGame.getBoardSize()) || (destY < 0 || destY >= boardGame.getBoardSize())) {
			boardGame.getHunter().setMovementBlocked(true);
		} else {
			boardGame.getHunter().setX(destX);
			boardGame.getHunter().setY(destY);
		}		
	}
	
	public void turnLeft() {
		int forwardX = boardGame.getHunter().getForwardX();
		int forwardY = boardGame.getHunter().getForwardY();
		if (forwardX == 1 && forwardY == 0) {
			boardGame.getHunter().setForwardX(0);
			boardGame.getHunter().setForwardY(-1);
		}
		if (forwardX == -1 && forwardY == 0){
			boardGame.getHunter().setForwardX(0);
			boardGame.getHunter().setForwardY(1);
		}
		if (forwardX == 0 && forwardY == 1) {
			boardGame.getHunter().setForwardX(1);
			boardGame.getHunter().setForwardY(0);
		}
		if (forwardX == 0 && forwardY == -1) {
			boardGame.getHunter().setForwardX(-1);
			boardGame.getHunter().setForwardY(0);
		}
	}
	
	public void turnRight() {
		int forwardX = boardGame.getHunter().getForwardX();
		int forwardY = boardGame.getHunter().getForwardY();
		if (forwardX == 1 && forwardY == 0) {
			boardGame.getHunter().setForwardX(0);
			boardGame.getHunter().setForwardY(1);
		}
		if (forwardX == -1 && forwardY == 0){
			boardGame.getHunter().setForwardX(0);
			boardGame.getHunter().setForwardY(-1);
		}
		if (forwardX == 0 && forwardY == 1) {
			boardGame.getHunter().setForwardX(-1);
			boardGame.getHunter().setForwardY(0);
		}
		if (forwardX == 0 && forwardY == -1) {
			boardGame.getHunter().setForwardX(1);
			boardGame.getHunter().setForwardY(0);			
		}
	}
	
	public void throwArrow() {
		boardGame.getHunter().setArrowsOnQuiver(boardGame.getHunter().getArrowsOnQuiver()-1);
		boardGame.getHunter().setArrowShooted(true);
		boolean isInsideMap = true;
		int x = boardGame.getHunter().getX();
		int y = boardGame.getHunter().getY();
		do {
			x = x + boardGame.getHunter().getForwardX();
			y = y + boardGame.getHunter().getForwardY();
			if (x < 0 || x >= boardGame.getBoardSize() || y < 0 || y >= boardGame.getBoardSize()) {
				isInsideMap = false;
			} else {
				if (boardGame.getWumpus().getX() == x && boardGame.getWumpus().getY() == y) {					
					boardGame.getHunter().setWumpusKilled(true);
				}
			}
		} while (isInsideMap && !boardGame.getHunter().isWumpusKilled());
	}
	
	public boolean isWumpusHere() {
		Hunter hunter = boardGame.getHunter();
		Wumpus wumpus = boardGame.getWumpus();
		return (hunter.getX() == wumpus.getX() && hunter.getY() == wumpus.getY()); 
	}
	
	public boolean isGoldHere() {
		Hunter hunter = boardGame.getHunter();
		Gold gold = boardGame.getGold();
		return (hunter.getX() == gold.getX() && hunter.getY() == gold.getY()); 
	}
	
	public boolean isPitHere() {
		Hunter hunter = boardGame.getHunter();
		for (int i=0; i<boardGame.getPitList().size(); i++){
			Pit pit = boardGame.getPitList().get(i);
			if (hunter.getX() == pit.getX() && hunter.getY() == pit.getY()) return true;
		}
		return false;
	}
	
	public boolean isWumpusNearHere() {
		Hunter hunter = boardGame.getHunter();
		Wumpus wumpus = boardGame.getWumpus();
		if (hunter.getX() == wumpus.getX() && Math.abs(hunter.getY() - wumpus.getY())<=1) return true;
		return (hunter.getY() == wumpus.getY() && Math.abs(hunter.getX() - wumpus.getX())<=1);
	}
	
	public boolean isPitNearHere() {
		Hunter hunter = boardGame.getHunter();
		for (int i=0; i<boardGame.getPitList().size(); i++){
			Pit pit = boardGame.getPitList().get(i);
			if (hunter.getX() == pit.getX() && Math.abs(hunter.getY() - pit.getY())<=1) return true;
			if (hunter.getY() == pit.getY() && Math.abs(hunter.getX() - pit.getX())<=1) return true;
		}
		return false;
	}

	public void chooseAnOption() {
		if (boardGame.getHunter().isDead()) return;
		String option = reader.next();
		switch(option.toUpperCase().charAt(0)) {
			case 'A':
				moveForward();
				break;
			case 'D':
				turnRight();
				break;
			case 'I':
				turnLeft();
				break;
			case 'F':
				if (boardGame.getHunter().getArrowsOnQuiver() > 0) throwArrow();
				break;
			case 'S':
				if ((boardGame.getHunter().getX() == 0 &&  boardGame.getHunter().getY() == 0)) boardGame.getHunter().setGoingOut(true);
				break;
			default:
		}
	}
	
	public String readKeyboardInput() {
		return reader.next();
	}
	
	public BoardGame getBoardGame() {
		return boardGame;
	}
}
