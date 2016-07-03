package de.htwsaar.flashcards.main;

import javax.swing.SwingUtilities;

import de.htwsaar.flashcards.ui.MainFrame;

public class Main {
	
	public static void main(String[] args) throws ClassNotFoundException {
		
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				new MainFrame();
			}
		});
		

		
		
	}
}
