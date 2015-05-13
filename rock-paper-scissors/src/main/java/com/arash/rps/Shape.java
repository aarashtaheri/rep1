package com.arash.rps;

public enum Shape {
	Rock(0),
	Paper(1),
	Scissors(2);
	
	private int value;
	public int getValue () {
		return value;
	}
	Shape(int value) {
		this.value = value;
	}
}
