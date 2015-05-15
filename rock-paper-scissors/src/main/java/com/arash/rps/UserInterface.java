package com.arash.rps;

public interface UserInterface {
	public Integer selectInput(String msg, String title, String[] options) throws BadInputException;
	void showOutput(String msg);
	
}
