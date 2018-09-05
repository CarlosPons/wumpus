package com.crz.wumpus.view;
import java.util.List;

import com.crz.wumpus.model.BoardGame;
import com.crz.wumpus.model.Hunter;

public class BoardGameView {

	private BoardGame boardGame;
	
	public BoardGameView(BoardGame boardGame) {
		this.boardGame = boardGame;
	}

	//View tile HEADER/MAP
	public void tileHeaderMap() {		
		System.out.print("\033[H\033[2J");  
		System.out.flush(); 
        System.out.println(""); 
        
		Hunter hunter = boardGame.getHunter();
		System.out.println("              .,+* Hunt the Wumpus *+,.");
		System.out.println("              ────────────┬────────────");
		System.out.println("   ┌───────────────┐      │");
		System.out.println("   │               ├──────┘");
		System.out.println("   │  !!!    ╀   * │░");
		System.out.println("   │ <o.O> " + hunter.getArrowsOnQuiver() + "x│ " + hunter.getPiecesOfGold() + "x▒ │░");
		System.out.println("   │ [^^^]   ╛   ▀ │░");
		System.out.println("   │               ╞" + getDrawing("═══", boardGame.getBoardSize()-4) + "╗");
		System.out.println("   └───╥───────────┘" + getDrawing("   ", boardGame.getBoardSize()-4) + "║");
		
		for (int y=0;y<boardGame.getBoardSize();y++) {
			System.out.print("       ║");
			for (int x=0; x<boardGame.getBoardSize(); x++) {
				if (boardGame.getHunter().getX() == x && boardGame.getHunter().getY() == y) {
					System.out.print("[" + getHunterDrawing() + "]");
				} else {
					System.out.print("[ ]");
				}
			}
			System.out.println("║░");
		}
		System.out.println("┌──────╚" + getDrawing("═══", boardGame.getBoardSize()) + "╝─────────────────────────" + getDrawing("───", (9 - boardGame.getBoardSize()) ) + "──────┐");
	}
	
	//View tile INFO
	public void tileInfo(List<String> text) {
		System.out.println("│" + getDrawing(" ",66) + "│");
		for (int i=0; i<text.size(); i++) {
			System.out.println(text.get(i));
		}
		System.out.println("│" + getDrawing(" ",66) + "│░");
		System.out.println("└──┬──────────────────────────────────────┬────────────────────────┘░");
		System.out.println("   │     ╔══════════════════════╗         │░░░░░░░░░░░░░░░░░░░░░░░░░░");
		System.out.println("   └─────╢                      ╟─────────┘░");
	}
	
	//View tile MENU
	public void tileMenu(List<String> menu) {
		System.out.println("         ║                      ║░░░░░░░░░░░");
		for (int i=0; i<menu.size(); i++) {
			System.out.println(menu.get(i));
		}
		System.out.println("         ║                      ║");
	}
	
	//View tile EXITMESSAGE
	public void tileExitMessage(String msgHunterIsDead) {
		System.out.println("         ║");
		System.out.println("         ║ "+ msgHunterIsDead);
		System.out.println("         ║");
	}
	
	
	
	//Get Hunter "image"
	private String getHunterDrawing() {
		int forwardX = boardGame.getHunter().getForwardX();
		int forwardY = boardGame.getHunter().getForwardY();
		if (forwardX == 1 && forwardY == 0) return ">";
		if (forwardX == -1 && forwardY == 0) return "<";
		if (forwardX == 0 && forwardY == 1) return "V";
		if (forwardX == 0 && forwardY == -1) return "^";
		return "X";
	}
	
	private String getDrawing(String singleSpace, int times) {
		return new String(new char[times]).replace("\0", singleSpace);
	}
	
	public String getInfoDrawing(String text) {
		return "│ ".concat(String.format("%1$-".concat("65").concat("s"), text)).concat("│░");  
	}
	
	public String getMenuDrawing(String text) {
		return "         ║ ".concat(String.format("%1$-".concat("21").concat("s"), text)).concat("║░");  
	}
	
}
