package com.crz.wumpus;

import com.crz.wumpus.model.GameConfigParams;




/**
 * 
 * In this App I tried to emulate the game 'Hunt the Wumpus'.
 * To simplify the reading of the code I treated the map as a BoardGame 
 * so you can put the pieces (Wumpus, Treasure, Hunter...) in a given cell.
 * 
 * @author Carlos Pons
 *
 */
public class App {

	private static final int DEFAULT_BOARDGAME_SIZE = 4;
	private static final int DEFAULT_PIT_NUMBER = 3;
	private static final int DEFAULT_ARROW_NUMBER = 3;
	
	private static final String CONFIG_INVALID_SELECTION = "# Selección no válida #";
	
	private static final String RUNTIME_ERROR = "RUNTIME_ERROR: ";
	
	private static Game game;
	private static GameConfigParams gameConfigParams;
	
	public static void main(String[] args) {
		gameConfigParams = new GameConfigParams();
		gameConfigParams.setBoardSize(DEFAULT_BOARDGAME_SIZE);
		gameConfigParams.setPitAmount(DEFAULT_PIT_NUMBER);
		gameConfigParams.setArrowAmount(DEFAULT_ARROW_NUMBER);
		try {
			intro();
		}
    	catch (Exception ex){
    		System.out.println(RUNTIME_ERROR.concat(ex.getMessage()));
    	}
    }
	
    public static void intro() throws GameException {
    	game = new Game(gameConfigParams);
    	game.viewIntroGame();
    	String option = game.readKeyboardInput();
    	if (option.equals("1")) play();
    	if (option.equals("2")) config();
    }
    
    public static void config() throws GameException {
    	game = new Game(gameConfigParams);
    	game.viewConfigGame();
    	chooseConfigParams();
    	play();
    }
    
    public static void play() throws GameException {    	
    	game = new Game(gameConfigParams);
    	do {
    		game.viewGame();
    		game.chooseAnOption();
    	} while (!game.isGameOver());
    }  
    
	private static void chooseConfigParams() {
		do {
	    	boolean isValidSelection = false;
			String option = game.readKeyboardInput();
			try {
				if (option.length() != 2) throw new NumberFormatException();
				if (option.equalsIgnoreCase("OK")) return;
				isValidSelection = updateConfigParams(option);
				if (!isValidSelection) throw new NumberFormatException();
			} catch (NumberFormatException ex) {
				System.out.println(CONFIG_INVALID_SELECTION);
			}
		} while (true);
	}
	
	private static boolean updateConfigParams(String option) {
		int suboption = Integer.parseInt(option.substring(1));
		switch(option.toUpperCase().charAt(0)) {
			case 'F':
				if (suboption > 0 && suboption < 10) {
					gameConfigParams.setArrowAmount(suboption);
					return true;
				}
				break;
			case 'T':
				if (suboption >= 4 && suboption <= 9) {
					gameConfigParams.setBoardSize(suboption);
					return true;
				}
				break;
			case 'P':
				if (suboption > 0 && suboption < 10) {
					gameConfigParams.setPitAmount(suboption);
					return true;
				}
				break;
			default:
		}
		return false;
	}
    
}

