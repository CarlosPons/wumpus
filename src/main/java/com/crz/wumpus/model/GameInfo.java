package com.crz.wumpus.model;

import com.crz.wumpus.controller.ActionController;
import com.crz.wumpus.controller.BoardGameController;

public class GameInfo {
	
	private BoardGame boardGame;
	private BoardGameController boardGameController;
	private ActionController actionController;
	private GameConfigParams gameConfigParams;
	
	public BoardGame getBoardGame() {
		return boardGame;
	}
	public void setBoardGame(BoardGame boardGame) {
		this.boardGame = boardGame;
	}
	public BoardGameController getBoardGameController() {
		return boardGameController;
	}
	public void setBoardGameController(BoardGameController boardGameController) {
		this.boardGameController = boardGameController;
	}
	public ActionController getActionController() {
		return actionController;
	}
	public void setActionController(ActionController actionController) {
		this.actionController = actionController;
	}
	public GameConfigParams getGameConfigParams() {
		return gameConfigParams;
	}
	public void setGameConfigParams(GameConfigParams gameConfigParams) {
		this.gameConfigParams = gameConfigParams;
	}
	
	
}
