package com.arash.rps;


public class Game {
	
	private UserInterface userInterface;
	private Integer gameMode;
	private Player player1;
	private Player player2;

	public Player getPlayer1() {
		return player1;
	}

	public void setPlayer1(Player player1) {
		this.player1 = player1;
	}

	public Player getPlayer2() {
		return player2;
	}

	public void setPlayer2(Player player2) {
		this.player2 = player2;
	}
	
	public Game(UserInterface ui) {
		this.userInterface = ui;
	}

	public void setUI(UserInterface ui) {
		this.userInterface = ui;
	}

	public void setGameMode(Integer gameMode) {
		this.gameMode = gameMode;
	}


	private void selectGameMode() {
	    String[] options = {"Player vs. Player", "Player vs. Computer", "Computer vs. Computer"};
	    String msg = "Please select game mode.";
		
	    try {
			gameMode = userInterface.selectInput(msg, "Game mode", options);
		} catch (BadInputException e) {
			System.out.println(e.getLocalizedMessage());
		}
	    
	    if(gameMode == null) {
	    	selectGameMode();
	    }
	}
	
	public void startNewGame() {
		selectGameMode();
		initPlayers();	
		play();
	}
	
	public void play() {
	    selectShape(player1);
		selectShape(player2);
		
		Player winner = whoWins(player1, player2);
		showWinner(winner);
	}

	private void showWinner(Player winner) {
		if (player1 == null || player2 == null) {
			return;
		}
		StringBuilder msg =new StringBuilder();
		msg.append(player1.getName()).append(" selects :").append(player1.getSelectedShape());
		msg.append("\n");
		msg.append(player2.getName()).append(" selects :").append(player2.getSelectedShape());
		msg.append("\n");
		if (winner != null) {
			msg.append(winner.getName());
			winner.setWinns(winner.getWinns()+1);			
		} else {
			msg.append("No one");			
		}
		msg.append(" wins.");
		
		
		String[] options = {"Continue game" , "Show statistics" ,"New game" , "Exit"};
		Integer answer = null;
		try {
			answer = userInterface.selectInput(msg.toString(), "Winner", options);
		} catch (BadInputException e) {
			System.out.println(e.getLocalizedMessage());
		}
		if (answer == null) {
			showWinner(winner);
		}
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
		stats.append(player1.getName()).append(" won ").append(player1.getWinns()).append(" times.");
		stats.append("\n"); 
		stats.append(player2.getName()).append(" won ").append(player2.getWinns()).append(" times.");
		String[] options = {"Continue ...", "Exit"};
		Integer answer = null;
		try {
			answer = userInterface.selectInput(stats.toString(), "Statistics", options );
		} catch (BadInputException e) {
			System.out.println(e.getLocalizedMessage());
		}
		if (answer == null) {
			answer = 0;
		}
		return answer;
	}

	private void selectShape(Player player) {
		if (player == null) {
			return;
		}
		if (player.isHuman()) {
			String[] options = {"Rock", "Paper", "Scissors"};
		    String message = "Please make your choice " + player.getName();
			Integer shape = null;
			try {
				shape = userInterface.selectInput(message, player.getName(), options);
			} catch (BadInputException e) {
				System.out.println(e.getLocalizedMessage());
			} 
			if(shape !=null) {
				player.setSelectedShape(shape);
			} else {
				selectShape(player);
			}
		}else {
			player.selectRandomShape();
		}
	}

	private void initPlayers() {
		switch (gameMode) {
		case 0:
			player1 = new Player("Player 1", true);
			player2 = new Player("Player 2", true);			
			break;
		case 1:
			player1 = new Player("Player 1", true);
			player2 = new Player("Computer", false);			
			break;
		case 2:
			player1 = new Player("Computer 1", false);
			player2 = new Player("Computer 2", false);			
			break;
		}
	}
	
	
	public Player whoWins(Player player1, Player player2) {
		if (player1 == null || player2 == null) {
			return null;
		}
		int p1 = player1.getSelectedShape().getValue();
		int p2 = player2.getSelectedShape().getValue();
		if (p1 == p2) {
			return null;							
		} 
		else if  (Math.abs(p1-p2) == 2 ) {
			if(p1 == 2) {
				return player2;
			}
			else {
				return player1;
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
