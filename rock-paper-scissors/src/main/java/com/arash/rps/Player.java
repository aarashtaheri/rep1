package com.arash.rps;

import java.util.Random;

public class Player {
	
	private String name;
	private boolean human;
	private Shape selectedShape;
	private int winns;
	
	public Player(String name, boolean human) {
		this.name = name;
		this.human = human;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isHuman() {
		return human;
	}

	public void setHuman(boolean human) {
		this.human = human;
	}

	public Shape getSelectedShape() {
		return selectedShape;
	}

	public void setSelectedShape(Shape selectedShape) {
		this.selectedShape = selectedShape;
	}
	
	public void setSelectedShape(int shapeNumber) {
		switch (shapeNumber) {
		case 0:
			setSelectedShape(Shape.Rock);
			break;
		case 1:
			setSelectedShape(Shape.Paper);
			break;
		case 2:
			setSelectedShape(Shape.Scissors);
			break;
		// TODO
		case 4:
			selectRandomShape();
			break;
		}
	}
	
	
	public void selectRandomShape() {
		Random random = new Random();
		int value = random.nextInt(2);
		setSelectedShape(value); 
	}

	public int getWinns() {
		return winns;
	}

	public void setWinns(int winns) {
		this.winns = winns;
	}
}
