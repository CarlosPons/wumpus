package com.crz.wumpus.controller;

import java.util.Random;

import com.crz.wumpus.GameException;
import com.crz.wumpus.model.BoardGame;
import com.crz.wumpus.model.Gold;
import com.crz.wumpus.model.Hunter;
import com.crz.wumpus.model.Pit;
import com.crz.wumpus.model.Position;
import com.crz.wumpus.model.Wumpus;

public class BoardGameController {

	private Random rnd;
	private BoardGame boardGame;
	
	// BoardGameController needs a RandomGenerator and a BoardGame
	public BoardGameController(BoardGame boardGame) {
		rnd = new Random();
		this.boardGame = boardGame;
	}

	public Object whoIsThere(Position pos) throws GameException {
		if ((pos.getX() < 0 || pos.getX() >= boardGame.getBoardSize()) && (pos.getY() < 0 || pos.getY() >= boardGame.getBoardSize())) {
			throw new GameException (GameException.EXCEPTION_POSITION_IS_OUTSIDE_BOARDGAME);
		}
		int currrentPitsOnBoard = boardGame.getPitList().size();
		for (int n = 0; n < currrentPitsOnBoard ; n++) {
			Pit pit = boardGame.getPitList().get(n);
			if ((pit.getX() == pos.getX()) && (pit.getY() == pos.getY())) return pit;
		}		
		if ((boardGame.getWumpus() != null) && (boardGame.getWumpus().getX() == pos.getX()) && (boardGame.getWumpus().getY() == pos.getY())) return boardGame.getWumpus();
		if ((boardGame.getGold() != null) && (boardGame.getGold().getX() == pos.getX()) && (boardGame.getGold().getY() == pos.getY())) return boardGame.getGold();
		if ((boardGame.getHunter() != null) && (boardGame.getHunter().getX() == pos.getX()) && (boardGame.getHunter().getY() == pos.getY())) return boardGame.getHunter();
		return null;	
	}
	
	public boolean isOccupied(Position pos) throws GameException {
		return (whoIsThere(pos) != null);
	}

	public void addHunterOnBoard() {
		Hunter hunter = new Hunter();
		hunter.setX(0);
		hunter.setY(0);
		hunter.setForwardX(1);
		hunter.setForwardY(0);
		hunter.setPiecesOfGold(0);
		hunter.setArrowShooted(false);
		hunter.setWumpusKilled(false);
		hunter.setMovementBlocked(false);
		hunter.setDead(false);
		boardGame.setHunter(hunter);
	}
	
	public void addPitOnBoard() {		
		Pit pit = new Pit();
		Position pitPosition = getValidPosition();
		pit.setPosition(pitPosition);
		boardGame.getPitList().add(pit);
	}
		
	public void addWumpusOnBoard() {		
		Wumpus wumpus = new Wumpus();
		Position wumpusPosition = getValidPosition();
		wumpus.setPosition(wumpusPosition);
		boardGame.setWumpus(wumpus);
	}
	
	public void addGoldOnBoard() {		
		Gold gold = new Gold();
		Position goldPosition = getValidPosition();
		gold.setPosition(goldPosition);
		boardGame.setGold(gold);
	}
	
	private Position getValidPosition() {
		Position pos;
		boolean isOccupied = false;
		do {
			pos = getRndPosition();
			try {
				isOccupied = isOccupied(pos);
			}
			catch (GameException ex) {
				//getRndPosition guarantees a valid position
			}
		} while (isOccupied);
		return pos;
	}
	
	private Position getRndPosition() {
		int currentBoardGameSize = boardGame.getBoardSize();
		int x = rnd.nextInt(currentBoardGameSize);
		int y = rnd.nextInt(currentBoardGameSize);
		Position pos = new Position();
		pos.setX(x);
		pos.setY(y);
		return pos;
	}
	
	public BoardGame getBoardGame() {
		return boardGame;
	}
	
}
