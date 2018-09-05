package com.crz.wumpus;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.crz.wumpus.controller.ActionController;
import com.crz.wumpus.controller.BoardGameController;
import com.crz.wumpus.model.BoardGame;
import com.crz.wumpus.model.Gold;
import com.crz.wumpus.model.Hunter;
import com.crz.wumpus.model.Pit;
import com.crz.wumpus.model.Wumpus;

public class ActionTest {
	
	private Hunter hunter;
	private Wumpus wumpus;
	private Gold gold;
	private Pit pit;
	private ActionController actionController;
	private BoardGameController boardGameController;
	private BoardGame boardGame;
	
	@Before
	public void onceExecuteBeforeAll() {
		boardGame = new BoardGame();
		boardGame.setBoardSize(5);		
		boardGameController = new BoardGameController(boardGame);		
		actionController = new ActionController(boardGame);
	}
	
    @Test
    public void testIsWumpusHere() {
    	hunter = new Hunter();
		hunter.setX(0);
		hunter.setY(0);
		boardGame.setHunter(hunter);
		wumpus = new Wumpus();
		wumpus.setX(0);
		wumpus.setY(0);
		boardGame.setWumpus(wumpus);
        assertEquals(actionController.isWumpusHere(), true);
    }
    
    @Test
    public void testIsNotWumpusHere() {
    	hunter = new Hunter();
		hunter.setX(1);
		hunter.setY(1);
		boardGame.setHunter(hunter);
		wumpus = new Wumpus();
		wumpus.setX(0);
		wumpus.setY(0);
		boardGame.setWumpus(wumpus);
        assertEquals(actionController.isWumpusHere(), false);
    }  
    
    @Test
    public void testIsGoldHere() {
    	hunter = new Hunter();
		hunter.setX(0);
		hunter.setY(0);
		boardGame.setHunter(hunter);
		gold = new Gold();
		gold.setX(0);
		gold.setY(0);
		boardGame.setGold(gold);
		assertEquals(actionController.isGoldHere(), true);
    }

    @Test
    public void testIsNotGoldHere() {
    	hunter = new Hunter();
		hunter.setX(0);
		hunter.setY(0);
		boardGame.setHunter(hunter);
		gold = new Gold();
		gold.setX(1);
		gold.setY(1);
		boardGame.setGold(gold);
		assertEquals(actionController.isGoldHere(), false);
    }
    
    @Test
    public void testIsPitHere() {
    	hunter = new Hunter();
		hunter.setX(0);
		hunter.setY(0);
		boardGame.setHunter(hunter);
		boardGameController.addPitOnBoard();
		pit = boardGame.getPitList().get(0);
		pit.setX(0);
		pit.setY(0);
		assertEquals(actionController.isPitHere(), true);   	
    }
    
    @Test
    public void testIsNotPitHere() {
    	hunter = new Hunter();
		hunter.setX(0);
		hunter.setY(0);
		boardGame.setHunter(hunter);
		boardGameController.addPitOnBoard();
		pit = boardGame.getPitList().get(0);
		pit.setX(1);
		pit.setY(0);
		assertEquals(actionController.isPitHere(), false);   	
    }
    
    @Test
    public void testIsWumpusNearHere() {
    	hunter = new Hunter();
		hunter.setX(1);
		hunter.setY(1);
		boardGame.setHunter(hunter);
		wumpus = new Wumpus();
		wumpus.setX(1);
		wumpus.setY(0);
		boardGame.setWumpus(wumpus);
		assertEquals(actionController.isWumpusNearHere(), true);
		wumpus.setX(1);
		wumpus.setY(2);
		assertEquals(actionController.isWumpusNearHere(), true);
		wumpus.setX(2);
		wumpus.setY(1);
		assertEquals(actionController.isWumpusNearHere(), true);
		wumpus.setX(0);
		wumpus.setY(1);
		assertEquals(actionController.isWumpusNearHere(), true);
    }
    
    @Test
    public void testIsNotWumpusNearHere() {
    	hunter = new Hunter();
		hunter.setX(0);
		hunter.setY(0);
		boardGame.setHunter(hunter);
		wumpus = new Wumpus();
		wumpus.setX(1);
		wumpus.setY(1);
		boardGame.setWumpus(wumpus);
		assertEquals(actionController.isWumpusNearHere(), false);
		hunter.setX(2);
		hunter.setY(0);
		assertEquals(actionController.isWumpusNearHere(), false);
		hunter.setX(0);
		hunter.setY(2);
		assertEquals(actionController.isWumpusNearHere(), false);
		hunter.setX(2);
		hunter.setY(2);
		assertEquals(actionController.isWumpusNearHere(), false);
    }
    
    @Test
    public void testIsPitNearHere() {
    	hunter = new Hunter();
		hunter.setX(1);
		hunter.setY(1);
		boardGame.setHunter(hunter);
		boardGameController.addPitOnBoard();
		pit = boardGame.getPitList().get(0);
		pit.setX(1);
		pit.setY(0);
		assertEquals(actionController.isPitNearHere(), true);
		pit.setX(1);
		pit.setY(2);
		assertEquals(actionController.isPitNearHere(), true);
		pit.setX(2);
		pit.setY(1);
		assertEquals(actionController.isPitNearHere(), true);
		pit.setX(0);
		pit.setY(1);
		assertEquals(actionController.isPitNearHere(), true);
    }
    
    @Test
    public void testIsNotPitNearHere() {
    	hunter = new Hunter();
		hunter.setX(1);
		hunter.setY(1);
		boardGame.setHunter(hunter);
		boardGameController.addPitOnBoard();
		pit = boardGame.getPitList().get(0);
		pit.setX(0);
		pit.setY(0);
		assertEquals(actionController.isPitNearHere(), false);
    }
}
