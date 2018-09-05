package com.crz.wumpus;

import java.util.ArrayList;

import com.crz.wumpus.controller.ActionController;
import com.crz.wumpus.controller.BoardGameController;
import com.crz.wumpus.model.BoardGame;
import com.crz.wumpus.model.GameConfigParams;
import com.crz.wumpus.model.GameInfo;
import com.crz.wumpus.model.Hunter;
import com.crz.wumpus.view.BoardGameView;

public class Game {

	private BoardGame boardGame;
	private BoardGameController boardGameController;
	private ActionController actionController;
	private BoardGameView boardGameView;
	
	private static final int DEFAULT_MIN_BOARDGAME_SIZE = 4;
	private static final int DEFAULT_MAX_BOARDGAME_SIZE = 9;
	
	private static final String MSG_WUMPUS_IS_HERE = "Oh-oh, un Wumpus hambriento está aquí!";
	private static final String MSG_PIT_IS_HERE = "Caes en un pozo del que no puedes salir!";
	private static final String MSG_GOLD_IS_HERE = "Hemos encontrado el tesoro!";
	private static final String MSG_WUMPUS_IS_NEAR_HERE = "Huele a Wumpus, no debe andar muy lejos.";
	private static final String MSG_PIT_IS_NEAR_HERE = "Siento la corriente de aire de un pozo cercano.";
	private static final String MSG_WUMPUS_WAS_KILLED = "Alguien lanza un chillido, la flecha ha acabado con al Wumpus.";
	private static final String MSG_ARROW_WITHOUT_TARGET = "La flecha que lanzaste no alcanzó su objetivo.";
	private static final String MSG_BLOCKED_MOVEMENT = "No se puede avanzar en esa dirección.";
	private static final String MSG_HUNTER_IS_DEAD = "Lo siento, tu goblin ha muerto sin conseguir su objetivo.";
	private static final String MSG_HUNTER_WINS = "Has escapado vivo con el tesoro: HAS GANADO!";
	private static final String MSG_HUNTER_IS_GOING_OUT = "Tu goblin sale de la cueva y decide buscar fortuna en otro lugar.";
	
	private static final String MENU_FORWARD = "A. Avanzar";
	private static final String MENU_TURN_RIGHT = "D. Giro derecha";
	private static final String MENU_TURN_LEFT = "I. Giro izquierda";
	private static final String MENU_THROW_AN_ARROW = "F. Lanzar flecha";
	private static final String MENU_EXIT = "S. Salir";
	
	private static final String MENU_PLAY = "1. Jugar";
	private static final String MENU_CONFIGURE = "2. Configurar";

	private static final String MENU_CONFIG_BOARD_SIZE = "T[4-9]. Tablero nxn";
	private static final String MENU_CONFIG_ARROWS = "F[1-9]. Flechas";
	private static final String MENU_CONFIG_PITS = "P[1-9]. Pozos";
	private static final String MENU_CONFIG_PLAY = "OK. Empezar a jugar";
	
	private static final String MENU_HELP_1 = "El juego consiste en ir avanzando por las celdas de la cueva sin";
	private static final String MENU_HELP_2 = "caer en los pozos, ni ser devorado por el temible Wumpus. Cuando";
	private static final String MENU_HELP_3 = "notes la brisa o el hedor del peludo enemigo sabrás que el";
	private static final String MENU_HELP_4 = "peligro se encuentra en una casilla adyacente. Recuerda que el";
	private static final String MENU_HELP_5 = "Wumpus puede morir con una certera flecha.";
	private static final String MENU_HELP_6 = "Suerte y no salgas sin el tesoro!";
	
	private static final String MENU_CONFIG_1 = "Para elegir las diferentes configuraciones, marca la letra y";
	private static final String MENU_CONFIG_2 = "el numero seguido de la tecla intro. Cuando termines de";
	private static final String MENU_CONFIG_3 = "configurar marca 'OK' para empezar a jugar.";
	
	public Game(GameConfigParams gameConfigParams) throws GameException {	
		boardGame = new BoardGame();
		boardGameController = new BoardGameController(boardGame);		
		actionController = new ActionController(boardGame);
		boardGameView = new BoardGameView(boardGame);
		init(gameConfigParams);
	}
	
	public Game(GameInfo gameInfo) throws GameException {	
		this.boardGame = gameInfo.getBoardGame();
		this.boardGameController = gameInfo.getBoardGameController();		
		this.actionController = gameInfo.getActionController();
		this.boardGameView = new BoardGameView(boardGame);
		init(gameInfo.getGameConfigParams());
	}

	public void init(GameConfigParams gameConfigParams) throws GameException {
		if (gameConfigParams.getBoardSize()<DEFAULT_MIN_BOARDGAME_SIZE || gameConfigParams.getBoardSize()>DEFAULT_MAX_BOARDGAME_SIZE) {
			throw new GameException (GameException.EXCEPTION_BOARD_DIMENSION_NOT_VALID);
		}
		if ((gameConfigParams.getPitAmount() + 2) > (gameConfigParams.getBoardSize()*gameConfigParams.getBoardSize())) {
			throw new GameException (GameException.EXCEPTION_NOT_ENOUGH_SPACE_ON_BOARD);
		}
		boardGame.setBoardSize(gameConfigParams.getBoardSize());
		boardGameController.addHunterOnBoard();
		boardGame.getHunter().setArrowsOnQuiver(gameConfigParams.getArrowAmount());			
		for (int pitAmount = 0; pitAmount < gameConfigParams.getPitAmount(); pitAmount++) {
			boardGameController.addPitOnBoard();
		}
		boardGameController.addWumpusOnBoard();
		boardGameController.addGoldOnBoard();
	}	

	public void viewIntroGame() {
		boardGameView.tileHeaderMap();
		ArrayList<String> infoText = new ArrayList<>();
		infoText.add(boardGameView.getInfoDrawing(MENU_HELP_1));
		infoText.add(boardGameView.getInfoDrawing(MENU_HELP_2));
		infoText.add(boardGameView.getInfoDrawing(MENU_HELP_3));
		infoText.add(boardGameView.getInfoDrawing(MENU_HELP_4));
		infoText.add(boardGameView.getInfoDrawing(MENU_HELP_5));
		infoText.add(boardGameView.getInfoDrawing(""));
		infoText.add(boardGameView.getInfoDrawing(MENU_HELP_6));
		boardGameView.tileInfo(infoText);
		ArrayList<String> menuText = new ArrayList<>();
		menuText.add(boardGameView.getMenuDrawing(MENU_PLAY));
		menuText.add(boardGameView.getMenuDrawing(MENU_CONFIGURE));
		boardGameView.tileMenu(menuText);
	}
	
	public void viewGame() {
		boardGameView.tileHeaderMap();
		//Info related with hunter's PERCEPTIONS
		ArrayList<String> infoText = new ArrayList<>();
		if (actionController.isWumpusHere() && !actionController.isWumpusHere()) {
			infoText.add(boardGameView.getInfoDrawing(MSG_WUMPUS_IS_HERE));
		}
		if (actionController.isGoldHere()) {
			infoText.add(boardGameView.getInfoDrawing(MSG_GOLD_IS_HERE));
		}
		if (actionController.isWumpusNearHere()) {
			infoText.add(boardGameView.getInfoDrawing(MSG_WUMPUS_IS_NEAR_HERE));
		}
		if (actionController.isPitNearHere() && !actionController.isPitHere()) {
			infoText.add(boardGameView.getInfoDrawing(MSG_PIT_IS_NEAR_HERE));
		}
		if (actionController.getBoardGame().getHunter().isArrowShooted() && boardGameController.getBoardGame().getHunter().isWumpusKilled() ) {
			infoText.add(boardGameView.getInfoDrawing(MSG_WUMPUS_WAS_KILLED));
		}
		if (actionController.getBoardGame().getHunter().isArrowShooted() && !boardGameController.getBoardGame().getHunter().isWumpusKilled() ) {
			infoText.add(boardGameView.getInfoDrawing(MSG_ARROW_WITHOUT_TARGET));
		}
		if (actionController.getBoardGame().getHunter().isMovementBlocked()) {
			infoText.add(boardGameView.getInfoDrawing(MSG_BLOCKED_MOVEMENT));
		}		
		if (actionController.isPitHere()) {
			infoText.add(boardGameView.getInfoDrawing(MSG_PIT_IS_HERE));
		}
		boardGameView.tileInfo(infoText);
		ArrayList<String> menuText = new ArrayList<>();
		menuText.add(boardGameView.getMenuDrawing(MENU_FORWARD));
		menuText.add(boardGameView.getMenuDrawing(MENU_TURN_RIGHT));
		menuText.add(boardGameView.getMenuDrawing(MENU_TURN_LEFT));
		menuText.add(boardGameView.getMenuDrawing(MENU_THROW_AN_ARROW));
		menuText.add(boardGameView.getMenuDrawing(MENU_EXIT));
		boardGameView.tileMenu(menuText);		
		updateGame();
	}
	
	public void viewConfigGame() {
		boardGameView.tileHeaderMap();
		ArrayList<String> infoText = new ArrayList<>();
		infoText.add(boardGameView.getInfoDrawing(MENU_CONFIG_1));
		infoText.add(boardGameView.getInfoDrawing(MENU_CONFIG_2));
		infoText.add(boardGameView.getInfoDrawing(MENU_CONFIG_3));
		boardGameView.tileInfo(infoText);
		ArrayList<String> menuText = new ArrayList<>();
		menuText.add(boardGameView.getMenuDrawing(MENU_CONFIG_BOARD_SIZE));
		menuText.add(boardGameView.getMenuDrawing(MENU_CONFIG_ARROWS));
		menuText.add(boardGameView.getMenuDrawing(MENU_CONFIG_PITS));
		menuText.add(boardGameView.getMenuDrawing(MENU_CONFIG_PLAY));		
		boardGameView.tileMenu(menuText);
	}

	//Remove from the boardgame creatures+treasures after taken
	public void updateGame() {
		Hunter hunter = boardGame.getHunter();
		if (actionController.isWumpusHere() || actionController.isPitHere()) {
			hunter.setDead(true);
		}
		if (actionController.isGoldHere()) {
			//If the Goblin get the Gold we put in our pocket outside the BoardGame
			hunter.setPiecesOfGold(1);
			boardGame.getGold().setX(boardGame.getBoardSize());
			boardGame.getGold().setY(boardGame.getBoardSize());
		}
		if (actionController.getBoardGame().getHunter().isWumpusKilled()) {
			//If the Goblin kill the Wumpus we put outside the BoardGame
			boardGame.getWumpus().setX(boardGame.getBoardSize());
			boardGame.getWumpus().setY(boardGame.getBoardSize());
		}		
		hunter.setArrowShooted(false);
		hunter.setWumpusKilled(false);
		hunter.setMovementBlocked(false);
	}
	
	public String readKeyboardInput() {
		return actionController.readKeyboardInput();
	}
	
	public void chooseAnOption() { 
		actionController.chooseAnOption();
	}
		
	public boolean isGameOver() {
		if (boardGame.getHunter().isDead()) {
			boardGameView.tileExitMessage(MSG_HUNTER_IS_DEAD);
			return true;
		}
		if (boardGame.getHunter().getX() == 0 && boardGame.getHunter().getY() == 0 && boardGame.getHunter().getPiecesOfGold() > 0) {
			boardGameView.tileExitMessage(MSG_HUNTER_WINS);
			return true;
		}
		if (boardGame.getHunter().getX() == 0 && boardGame.getHunter().getY() == 0 && boardGame.getHunter().isGoingOut()) {
			boardGameView.tileExitMessage(MSG_HUNTER_IS_GOING_OUT);
			return true;
		}
		return false;
	}
}
