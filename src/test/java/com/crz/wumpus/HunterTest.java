package com.crz.wumpus;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.crz.wumpus.controller.ActionController;
import com.crz.wumpus.model.BoardGame;
import com.crz.wumpus.model.Hunter;
import com.crz.wumpus.model.Wumpus;

public class HunterTest {
	
	private Hunter hunter;
	private Wumpus wumpus;
	private ActionController actionController;
	private BoardGame boardGame;
	
	@Before
	public void onceExecuteBeforeAll() {
		boardGame = new BoardGame();
		boardGame.setBoardSize(5);		
		actionController = new ActionController(boardGame);
	}
	
    @Test
    public void testTurningHunterRightFourTimes() {
    	hunter = new Hunter();
		hunter.setX(0);
		hunter.setY(0);
		hunter.setForwardX(1);
		hunter.setForwardY(0);
		boardGame.setHunter(hunter);
		actionController.turnRight();
		actionController.turnRight();
		actionController.turnRight();
		actionController.turnRight();
        assertEquals(hunter.getForwardX(), 1);
        assertEquals(hunter.getForwardY(), 0);
    }
    
    @Test
    public void testTurningHunterLeftFourTimes() {
    	hunter = new Hunter();
		hunter.setX(0);
		hunter.setY(0);
		hunter.setForwardX(1);
		hunter.setForwardY(0);
		boardGame.setHunter(hunter);
		actionController.turnLeft();
		actionController.turnLeft();
		actionController.turnLeft();
		actionController.turnLeft();
        assertEquals(hunter.getForwardX(), 1);
        assertEquals(hunter.getForwardY(), 0);
    }  
    
    @Test
    public void testHunterMoveForward() {
    	hunter = new Hunter();
		hunter.setX(0);
		hunter.setY(0);
		hunter.setForwardX(1);
		hunter.setForwardY(0);
		boardGame.setHunter(hunter);
		actionController.moveForward();
        assertEquals(boardGame.getHunter().getX(), 1);
        assertEquals(boardGame.getHunter().getY(), 0);
    }
	
    @Test
    public void testHunterTryMoveForwardOutCave() {
    	hunter = new Hunter();
		hunter.setX(0);
		hunter.setY(0);
		hunter.setForwardX(-1);
		hunter.setForwardY(0);
		boardGame.setHunter(hunter);
		actionController.moveForward();
        assertEquals(boardGame.getHunter().getX(), 0);
        assertEquals(boardGame.getHunter().getY(), 0);
		hunter.setX(0);
		hunter.setY(0);        
		hunter.setForwardX(0);
		hunter.setForwardY(-1);
		boardGame.setHunter(hunter);
		actionController.moveForward();
        assertEquals(boardGame.getHunter().getX(), 0);
        assertEquals(boardGame.getHunter().getY(), 0);
		hunter.setX(boardGame.getBoardSize()-1);
		hunter.setY(boardGame.getBoardSize()-1);        
		hunter.setForwardX(0);
		hunter.setForwardY(1);
		boardGame.setHunter(hunter);
		actionController.moveForward();
        assertEquals(boardGame.getHunter().getX(), boardGame.getBoardSize()-1);
        assertEquals(boardGame.getHunter().getY(), boardGame.getBoardSize()-1);
		hunter.setX(boardGame.getBoardSize()-1);
		hunter.setY(boardGame.getBoardSize()-1);        
		hunter.setForwardX(1);
		hunter.setForwardY(0);
		boardGame.setHunter(hunter);
		actionController.moveForward();
        assertEquals(boardGame.getHunter().getX(), boardGame.getBoardSize()-1);
        assertEquals(boardGame.getHunter().getY(), boardGame.getBoardSize()-1);
    }  
    
    @Test
    public void testHunterThrowArrowKillWumpus() {
    	hunter = new Hunter();
		hunter.setX(0);
		hunter.setY(1);
		hunter.setForwardX(1);
		hunter.setForwardY(0);
		boardGame.setHunter(hunter);
		wumpus = new Wumpus();
		wumpus.setX(1);
		wumpus.setY(1);
		boardGame.setWumpus(wumpus);
		actionController.throwArrow();
		assertEquals(boardGame.getHunter().isArrowShooted(), true);
		assertEquals(boardGame.getHunter().isWumpusKilled(), true);
		hunter.setWumpusKilled(false);
		hunter.setArrowShooted(false);
		hunter.setX(2);
		hunter.setY(1);
		hunter.setForwardX(-1);
		hunter.setForwardY(0);
		boardGame.setHunter(hunter);
		wumpus = new Wumpus();
		wumpus.setX(1);
		wumpus.setY(1);
		boardGame.setWumpus(wumpus);
		actionController.throwArrow();
		assertEquals(boardGame.getHunter().isArrowShooted(), true);
		assertEquals(boardGame.getHunter().isWumpusKilled(), true);
		hunter.setWumpusKilled(false);
		hunter.setArrowShooted(false);
		hunter.setX(1);
		hunter.setY(0);
		hunter.setForwardX(0);
		hunter.setForwardY(1);
		boardGame.setHunter(hunter);
		wumpus = new Wumpus();
		wumpus.setX(1);
		wumpus.setY(1);
		boardGame.setWumpus(wumpus);
		actionController.throwArrow();
		assertEquals(boardGame.getHunter().isArrowShooted(), true);
		assertEquals(boardGame.getHunter().isWumpusKilled(), true);
		hunter.setWumpusKilled(false);
		hunter.setArrowShooted(false);
		hunter.setX(1);
		hunter.setY(2);
		hunter.setForwardX(0);
		hunter.setForwardY(-1);
		boardGame.setHunter(hunter);
		wumpus = new Wumpus();
		wumpus.setX(1);
		wumpus.setY(1);
		boardGame.setWumpus(wumpus);
		actionController.throwArrow();
		assertEquals(boardGame.getHunter().isArrowShooted(), true);
		assertEquals(boardGame.getHunter().isWumpusKilled(), true);
    }
    
    @Test
    public void testHunterThrowArrowNotKillWumpus() {
    	hunter = new Hunter();
		hunter.setX(0);
		hunter.setY(0);
		hunter.setForwardX(1);
		hunter.setForwardY(0);
		hunter.setWumpusKilled(false);
		hunter.setArrowShooted(false);
		boardGame.setHunter(hunter);
		wumpus = new Wumpus();
		wumpus.setX(1);
		wumpus.setY(1);
		boardGame.setWumpus(wumpus);
		actionController.throwArrow();
		assertEquals(boardGame.getHunter().isArrowShooted(), true);
		assertEquals(boardGame.getHunter().isWumpusKilled(), false);
		hunter.setForwardX(-1);
		hunter.setForwardY(0);
		hunter.setWumpusKilled(false);
		hunter.setArrowShooted(false);
		boardGame.setHunter(hunter);
		actionController.throwArrow();
		assertEquals(boardGame.getHunter().isArrowShooted(), true);
		assertEquals(boardGame.getHunter().isWumpusKilled(), false);
		hunter.setForwardX(0);
		hunter.setForwardY(1);
		hunter.setWumpusKilled(false);
		hunter.setArrowShooted(false);
		boardGame.setHunter(hunter);
		actionController.throwArrow();
		assertEquals(boardGame.getHunter().isArrowShooted(), true);
		assertEquals(boardGame.getHunter().isWumpusKilled(), false);
		hunter.setForwardX(0);
		hunter.setForwardY(-1);
		hunter.setWumpusKilled(false);
		hunter.setArrowShooted(false);
		boardGame.setHunter(hunter);
		actionController.throwArrow();
		assertEquals(boardGame.getHunter().isArrowShooted(), true);
		assertEquals(boardGame.getHunter().isWumpusKilled(), false);
    }
}