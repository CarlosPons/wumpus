package com.crz.wumpus;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.crz.wumpus.controller.ActionController;
import com.crz.wumpus.controller.BoardGameController;
import com.crz.wumpus.model.BoardGame;
import com.crz.wumpus.model.GameConfigParams;
import com.crz.wumpus.model.GameInfo;
import com.crz.wumpus.model.Gold;
import com.crz.wumpus.model.Hunter;
import com.crz.wumpus.model.Pit;
import com.crz.wumpus.model.Wumpus;

public class GameTest {

	private Hunter hunter;
	private Wumpus wumpus;
	private Gold gold;
	private Pit pit;
	private ActionController actionController;
	private BoardGameController boardGameController;
	private BoardGame boardGame;
	private Game game;
	private GameConfigParams gameConfigParams;
	
	private final String RUNTIME_ERROR = "RUNTIME_ERROR: ";
	
	@Before
	public void onceExecuteBeforeAll() {		
		gameConfigParams = new GameConfigParams();
		gameConfigParams.setBoardSize(5);
		gameConfigParams.setPitAmount(1);
		gameConfigParams.setArrowAmount(1);		
		boardGame = new BoardGame();
		boardGameController = new BoardGameController(boardGame);		
		actionController = new ActionController(boardGame);
		boardGame.setBoardSize(gameConfigParams.getBoardSize());
		boardGameController.addHunterOnBoard();
		boardGame.getHunter().setArrowsOnQuiver(gameConfigParams.getArrowAmount());
		try {
			for (int pitAmount = 0; pitAmount < gameConfigParams.getPitAmount() ; pitAmount++) {
				boardGameController.addPitOnBoard();
			}
			boardGameController.addWumpusOnBoard();
			boardGameController.addGoldOnBoard();
		}
		catch  (Exception ex) {
			System.out.println(RUNTIME_ERROR.concat(ex.getMessage()));
		}
		GameInfo gameInfo = new GameInfo();
		gameInfo.setBoardGame(boardGame);
		gameInfo.setActionController(actionController);
		gameInfo.setBoardGameController(boardGameController);
		gameInfo.setGameConfigParams(gameConfigParams);
		try {
			game = new Game (gameInfo);
		}
		catch (Exception ex) {
			System.out.println(RUNTIME_ERROR.concat(ex.getMessage()));
		}
	}
	
	@Test
	public void testUpdateGameYouAreDead() {
		//Put a pit behind the Hunter and try if he dies
		hunter = boardGame.getHunter();
		hunter.setX(0);
		hunter.setY(0);
		pit = boardGame.getPitList().get(0);
		pit.setX(0);
		pit.setY(0);		
		game.updateGame();
		assertEquals(boardGame.getHunter().isDead(), true);
		pit = boardGame.getPitList().get(0);
		pit.setX(1);
		pit.setY(1);
		boardGame.getHunter().setDead(false);
		//Put a Wumups with the Hunter and try if he dies
		wumpus = boardGame.getWumpus();
		wumpus.setX(0);
		wumpus.setY(0);
		game.updateGame();
		assertEquals(boardGame.getHunter().isDead(), true);
		
	}
	
	@Test
	public void testUpdateGameYouAreRich() {
		//Put a piece of gold with the Hunter and try if he is a rich man
		hunter = boardGame.getHunter();
		hunter.setX(0);
		hunter.setY(0);
		gold = boardGame.getGold();
		gold.setX(0);
		gold.setY(0);
		game.updateGame();
		assertEquals(boardGame.getHunter().getPiecesOfGold(), 1);
	}
	
	@Test
	public void testUpdateGameKillTheWumpus() {
		//When Wumpus is killed is put outside the BoardGame
		hunter = boardGame.getHunter();
		hunter.setWumpusKilled(true);
		game.updateGame();
		assertEquals(boardGame.getWumpus().getX(),5);
		assertEquals(boardGame.getWumpus().getX(),5);
	}
	
	@Test
	public void testBoardDimensionNotValidException() {
		gameConfigParams = new GameConfigParams();
		gameConfigParams.setBoardSize(15);
    	try {
    		game.init(gameConfigParams);
        } catch (GameException ex) {
        	assertEquals(ex.getMessage(), GameException.EXCEPTION_BOARD_DIMENSION_NOT_VALID);
        }
    	gameConfigParams.setBoardSize(1);
    	try {
    		game.init(gameConfigParams);
        } catch (GameException ex) {
        	assertEquals(ex.getMessage(), GameException.EXCEPTION_BOARD_DIMENSION_NOT_VALID);
        }
	}
	
	@Test
	public void testNotEnoughSpaceOnBoardException() {
		gameConfigParams = new GameConfigParams();
		gameConfigParams.setPitAmount(20);
		gameConfigParams.setBoardSize(4);
    	try {
    		game.init(gameConfigParams);
        } catch (GameException ex) {
        	assertEquals(ex.getMessage(), GameException.EXCEPTION_NOT_ENOUGH_SPACE_ON_BOARD);
        }
	}

}
