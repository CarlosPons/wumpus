package com.crz.wumpus;

public class GameException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public static final String EXCEPTION_BOARD_DIMENSION_NOT_VALID = "The boardgame dimension is not valid.";
	public static final String EXCEPTION_NOT_ENOUGH_SPACE_ON_BOARD = "Not enough space on board for so many creatures and pits.";
	public static final String EXCEPTION_POSITION_IS_OUTSIDE_BOARDGAME = "The position is outside the boardgame.";

	public GameException(String msg) {
        super(msg);
    }
	
}
