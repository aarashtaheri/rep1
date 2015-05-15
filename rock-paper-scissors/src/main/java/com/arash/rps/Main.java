package com.arash.rps;

public class Main {
	public static void main(String[] args) {
		
		// There are two user interfaces implemented for this game : JOptionPane and Console
		// The application could be extended on more reach user interfaces.
		// To select a new user interface simply set the game's UI attribute 
		// to the desired user interface. e.g. game.setUI(uiConsole);
		
		UserInterface uiJ = new CustomOptionPane();
		UserInterface uiConsole = new Console();
		
		Game game = new Game(uiConsole);
		
		
		game.startNewGame();
		
	}
}
