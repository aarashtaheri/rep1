package com.arash.rps;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

public class TestGame {

	private Game game;
	private Player player1;
	private Player player2;
	private Player winner;
	
	@Before
	public void setup() {
		game = new Game(null);
		player1 = new Player("player 1", false);
		player2 = new Player("player 2", false);

		game.setPlayer1(player1);
		game.setPlayer1(player2);
	}
	
	@Test
	public void test1_Paper_beats_Rock() {
		
		player1.setSelectedShape(0);
		player2.setSelectedShape(1);
		
		Assert.assertEquals(player1.getSelectedShape(), Shape.Rock);
		Assert.assertEquals(player2.getSelectedShape(), Shape.Paper);
		
		winner = game.whoWins(player1, player2);
		Assert.assertEquals(player2, winner);

		player1.setSelectedShape(1);
		player2.setSelectedShape(0);
		
		Assert.assertEquals(player1.getSelectedShape(), Shape.Paper);
		Assert.assertEquals(player2.getSelectedShape(), Shape.Rock);
		
		winner = game.whoWins(player1, player2);
		Assert.assertEquals(player1, winner);
	}
	
	@Test
	public void test2_Scissors_beats_Paper() {
		
		player1.setSelectedShape(1);
		player2.setSelectedShape(2);
		
		Assert.assertEquals(player1.getSelectedShape(), Shape.Paper);
		Assert.assertEquals(player2.getSelectedShape(), Shape.Scissors);
		
		winner = game.whoWins(player1, player2);
		Assert.assertEquals(player2, winner);

		player1.setSelectedShape(2);
		player2.setSelectedShape(1);
		
		Assert.assertEquals(player1.getSelectedShape(), Shape.Scissors);
		Assert.assertEquals(player2.getSelectedShape(), Shape.Paper);
		
		winner = game.whoWins(player1, player2);
		Assert.assertEquals(player1, winner);
	}

	@Test
	public void test3_Rock_beats_Scissors() {
		
		player1.setSelectedShape(0);
		player2.setSelectedShape(2);
		
		Assert.assertEquals(player1.getSelectedShape(), Shape.Rock);
		Assert.assertEquals(player2.getSelectedShape(), Shape.Scissors);
		
		winner = game.whoWins(player1, player2);		
		Assert.assertEquals(player1, winner);
		
		player1.setSelectedShape(2);
		player2.setSelectedShape(0);
		
		Assert.assertEquals(player1.getSelectedShape(), Shape.Scissors);
		Assert.assertEquals(player2.getSelectedShape(), Shape.Rock);
		
		winner = game.whoWins(player1, player2);		
		Assert.assertEquals(player2, winner);
	}
	
	
	@Test
	public void test3_NoWinner() {
		
		player1.setSelectedShape(0);
		player2.setSelectedShape(0);	
		winner = game.whoWins(player1, player2);		
		Assert.assertEquals(null, winner);
		
		player1.setSelectedShape(1);
		player2.setSelectedShape(1);
		winner = game.whoWins(player1, player2);		
		Assert.assertEquals(null, winner);

		player1.setSelectedShape(2);
		player2.setSelectedShape(2);
		winner = game.whoWins(player1, player2);		
		Assert.assertEquals(null, winner);
	}


	
}
