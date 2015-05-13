package com.arash.rps;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Console  {

	public void write(String msg) {
	}

	public String getInput() {
		java.io.Console c = System.console();
		c.readLine();
		
		try {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

			String input = br.readLine();
			return input;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	return null;
	}

	public void writeOutput(String msg) {
		System.out.println(msg);		
	}
	
	
}
