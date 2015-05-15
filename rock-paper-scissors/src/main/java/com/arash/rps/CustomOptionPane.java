package com.arash.rps;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class CustomOptionPane implements UserInterface {

	JFrame frame = new JFrame();

	public Integer selectInput(String msg, String title, String[] options) {
		int selected = JOptionPane.showOptionDialog(frame, msg , title , JOptionPane.YES_NO_CANCEL_OPTION,
				JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
		
		return selected;
	}

	public void showOutput(String msg) {
		JOptionPane.showMessageDialog(frame, msg);
	}

}
