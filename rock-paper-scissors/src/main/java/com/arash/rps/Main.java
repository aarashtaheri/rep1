package com.arash.rps;

public class Main {
	public static void main(String[] args) {
//		UserInterface ui = new Console();
		UserInterface ui = new CustomOptionPane();
		Game game1 = new Game(ui);
//		game1.setUserInterface(ui );
		
		game1.startNewGame();
		
	}
}
