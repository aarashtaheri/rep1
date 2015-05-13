package com.arash.rps;


public class Game {
	
	private UserInterface ui;
	
	public Game(UserInterface ui) {
		this.ui = ui;
	}
	
	private int gameMode;
	
	private Player p1;
	private Player p2;
	

	private void selectGameMode() {
	    String[] options = {"Player vs. Player", "Player vs. Computer", "Computer vs. Computer"};
	    String msg = "Please select game mode.";
		
	    gameMode = ui.selectInput(msg, "Game mode", options);
	}
	
	public void startNewGame() {
		selectGameMode();
		initPlayers();	
		play();
	}
	
	public void play() {
	    selectShape(p1);
		selectShape(p2);
		
		Player winner = whoWins(p1, p2);
		showWinner(winner);
	}

	private void showWinner(Player winner) {
		StringBuilder msg =new StringBuilder();
		msg.append(p1.getName()).append(" selects :").append(p1.getSelectedShape());
		msg.append("\n");
		msg.append(p2.getName()).append(" selects :").append(p2.getSelectedShape());
		msg.append("\n");
		if (winner != null) {
			msg.append(winner.getName());
			winner.setWinns(winner.getWinns()+1);			
		} else {
			msg.append("No one");			
		}
		msg.append(" wins.");
		
		
		String[] options = {"Continue game" , "Show statistics" ,"New game" , "Exit"};
		int answer = ui.selectInput(msg.toString(), "Winner", options);
		switch (answer) {
		case 0:
			play();
			break;
		case 1:
			if( showStats() == 0) {
				play();
			}
			break;
		case 2:
			startNewGame();
			break;
		case 3:
			System.exit(0);
			break;

		default:
			break;
		}
	}

	private int showStats() {
		StringBuilder stats = new StringBuilder();
		stats.append(p1.getName()).append(" won ").append(p1.getWinns()).append(" times.");
		stats.append("\n"); 
		stats.append(p2.getName()).append(" won ").append(p2.getWinns()).append(" times.");
		String[] options = {"Continue ...", "Exit"};
		int answer = ui.selectInput(stats.toString(), "Statistics", options );
		return answer;
	}

	private void selectShape(Player player) {
		if (player.isHuman()) {
			String[] options = {"Rock", "Paper", "Scissors"};
		    String message = "Please make your choice " + player.getName();
			int shape =  ui.selectInput(message, player.getName(), options);
			player.setSelectedShape(shape);
		}else {
			player.selectRandomShape();
		}
	}

	private void initPlayers() {
		switch (gameMode) {
		case 0:
			p1 = new Player("Player 1", true);
			p2 = new Player("Player 2", true);			
			break;
		case 1:
			p1 = new Player("Player 1", true);
			p2 = new Player("Computer", false);			
			break;
		case 2:
			p1 = new Player("Computer 1", false);
			p2 = new Player("Computer 2", false);			
			break;
		}
	}
	
	
	private Player whoWins(Player player1, Player player2) {
		int p1 = player1.getSelectedShape().getValue();
		int p2 = player2.getSelectedShape().getValue();
		if (p1 == p2) {
			return null;							
		} 
		else if  (Math.abs(p1-p2) == 2 ) {
			if(p1 == 2) {
				return player1;
			}
			else {
				return player2;
			}
		} else {
			if(p1 > p2) {
				return player1;
			} 
			else {
				return player2;
			}
		}
	}
}
