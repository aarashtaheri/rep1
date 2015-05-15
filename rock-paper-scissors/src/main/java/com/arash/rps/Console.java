package com.arash.rps;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Console implements UserInterface {

	public Integer selectInput(String msg, String title, String[] options) throws BadInputException {
		StringBuilder msgToShow = new StringBuilder();
		try {
			msgToShow.append(msg).append(": ");
			for (int i=0; i< options.length; i++) {
				msgToShow.append("<").append(i).append(">").append(" for ").append(options[i]).append(" ");
			}
			System.out.print(msgToShow.toString());
			
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

			Integer input =Integer.parseInt( br.readLine());
			if (input > options.length-1) {
				throw new BadInputException("Number not in options");
			}
			return input;
		} catch (NumberFormatException e) {
			throw new BadInputException("Please enter a number");
		} catch (IOException e) {
			throw new BadInputException(e.getLocalizedMessage());
		}
	}

	public void showOutput(String msg) {
		System.out.println(msg);
	}
	
}
