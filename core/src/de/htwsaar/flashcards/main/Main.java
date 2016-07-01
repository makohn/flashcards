package de.htwsaar.flashcards.main;

import javax.swing.SwingUtilities;

import de.htwsaar.flashcards.dao.FlashCardDaoImpl;
import de.htwsaar.flashcards.dao.SQLiteJDBC;
import de.htwsaar.flashcards.model.FlashCard;
import de.htwsaar.flashcards.ui.MainFrame;

public class Main {
	
	public static void main(String[] args) throws ClassNotFoundException {
		
	
		
		FlashCard card = new FlashCard(2, "Test", "Warum teste ich ?", "Weiﬂ ich nicht", 0, 0, null);
		System.out.println(card);
		FlashCardDaoImpl test = new FlashCardDaoImpl();
		test.save(card);
		
		
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				new MainFrame();
			}
		});
		

		
		
	}
}
