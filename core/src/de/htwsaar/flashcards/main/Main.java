package de.htwsaar.flashcards.main;


import javax.swing.SwingUtilities;

import de.htwsaar.flashcards.dao.FlashCardDaoImpl;
import de.htwsaar.flashcards.dao.SQLiteJDBC;
import de.htwsaar.flashcards.ui.StudyFrame;

 /** 
 * Dient dem Starten des Programms.
 * @author mkohn, Feick Martin
 *
 */
public class Main {

	
	public static void main(String[] args) throws ClassNotFoundException {
		// Um konkurenten Zugriff auf die als thread-unsicheren geltenden 
		// Swing Datenstrukturen zu verhindern, wird die GUI auf einem seperaten
		// Thread (dem ATW Event-Dispatching Thread) ausgefuehrt.
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				new StudyFrame();
			}
		});
		

    FlashCardDaoImpl dao = new FlashCardDaoImpl(SQLiteJDBC.getConnection());
    System.out.println(dao.getCard(3));
//		
		
	}
}