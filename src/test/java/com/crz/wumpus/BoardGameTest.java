package com.crz.wumpus;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.crz.wumpus.controller.BoardGameController;
import com.crz.wumpus.model.BoardGame;
import com.crz.wumpus.model.Gold;
import com.crz.wumpus.model.Hunter;
import com.crz.wumpus.model.Pit;
import com.crz.wumpus.model.Position;
import com.crz.wumpus.model.Wumpus;

public class BoardGameTest {
	
	private Hunter hunter;
	private Wumpus wumpus;
	private Gold gold;
	private Pit pit;
	private Position pos;
	private BoardGameController boardGameController;
	private BoardGame boardGame;
	
	private final String RUNTIME_ERROR = "RUNTIME_ERROR: ";
	
	@Before
	public void onceExecuteBeforeAll() {
		boardGame = new BoardGame();
		boardGame.setBoardSize(5);		
		boardGameController = new BoardGameController(boardGame);		
	}
	
    @Test
    public void testWhoIsTherePit() {
		boardGameController.addPitOnBoard();
		pit = boardGame.getPitList().get(0);
		pit.setX(0);
		pit.setY(0);
		pos = new Position();
		pos.setX(0);
		pos.setY(0);
		Object whoIsThere = null;
		try {
			whoIsThere = boardGameController.whoIsThere(pos);
		} catch (GameException ex) {
			System.out.println(RUNTIME_ERROR.concat(ex.getMessage()));
		}
        assertEquals(whoIsThere, pit);
    }
    
    @Test
    public void testWhoIsThereWumpus() {
		boardGameController.addWumpusOnBoard();
		wumpus = boardGame.getWumpus();
		wumpus.setX(0);
		wumpus.setY(0);
		pos = new Position();
		pos.setX(0);
		pos.setY(0);
		Object whoIsThere = null;
		try {
			whoIsThere = boardGameController.whoIsThere(pos);
		} catch (GameException ex) {
			System.out.println(RUNTIME_ERROR.concat(ex.getMessage()));
		}
        assertEquals(whoIsThere, wumpus);
    }
    
    @Test
    public void testWhoIsThereGold() {
		boardGameController.addGoldOnBoard();
		gold = boardGame.getGold();
		gold.setX(0);
		gold.setY(0);
		pos = new Position();
		pos.setX(0);
		pos.setY(0);
		Object whoIsThere = null;
		try {
			whoIsThere = boardGameController.whoIsThere(pos);
		} catch (GameException ex) {
			System.out.println(RUNTIME_ERROR.concat(ex.getMessage()));
		}
        assertEquals(whoIsThere, gold);
    }
    
    @Test
    public void testWhoIsThereHunter() {
		boardGameController.addHunterOnBoard();
		hunter = boardGame.getHunter();
		hunter.setX(0);
		hunter.setY(0);
		pos = new Position();
		pos.setX(0);
		pos.setY(0);
		Object whoIsThere = null;
		try {
			whoIsThere = boardGameController.whoIsThere(pos);
		} catch (GameException ex) {
			System.out.println(RUNTIME_ERROR.concat(ex.getMessage()));
		}
        assertEquals(whoIsThere, hunter);
    }   
    
    @Test
    public void testWhoNotIsThere() {
		boardGameController.addHunterOnBoard();
		hunter = boardGame.getHunter();
		hunter.setX(0);
		hunter.setY(0);
		pos = new Position();
		pos.setX(1);
		pos.setY(1);
		Object whoIsThere = null;
		try {
			whoIsThere = boardGameController.whoIsThere(pos);
		} catch (GameException ex) {
			System.out.println(RUNTIME_ERROR.concat(ex.getMessage()));
		}
        assertEquals(whoIsThere, pit);
    } 
    
    @Test
    public void testIsOccupied() {
		boardGameController.addHunterOnBoard();
		hunter = boardGame.getHunter();
		hunter.setX(0);
		hunter.setY(0);
		pos = new Position();
		pos.setX(0);
		pos.setY(0);
		boolean isOccupied = false;
		try {
			isOccupied = boardGameController.isOccupied(pos);
		} catch (GameException ex) {
			System.out.println(RUNTIME_ERROR.concat(ex.getMessage()));
		}
		assertEquals(isOccupied, true);
    }
    
    @Test
    public void testIsNotOccupied() {
		boardGameController.addHunterOnBoard();
		hunter = boardGame.getHunter();
		hunter.setX(0);
		hunter.setY(0);
		pos = new Position();
		pos.setX(1);
		pos.setY(1);
		boolean isOccupied = true;
		try {
			isOccupied = boardGameController.isOccupied(pos);
		} catch (GameException ex) {
			System.out.println(RUNTIME_ERROR.concat(ex.getMessage()));
		}
		assertEquals(isOccupied, false);
    }
    
    @Test
    public void testIsOccupiedException() {
    	pos = new Position();
		pos.setX(boardGame.getBoardSize());
		pos.setY(boardGame.getBoardSize());
    	try {
    		boardGameController.isOccupied(pos);
        } catch (GameException ex) {
        	assertEquals(ex.getMessage(), GameException.EXCEPTION_POSITION_IS_OUTSIDE_BOARDGAME);
        }
    }
}
