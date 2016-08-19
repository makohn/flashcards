package de.htwsaar.flashcards.main;


import javax.swing.SwingUtilities;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import de.htwsaar.flashcards.dao.FlashCardDaoImpl;
import de.htwsaar.flashcards.ui.MainFrame;

 /** 
 * Dient dem Starten des Programms.
 * @author mkohn, Feick Martin
 *
 */
public class Main {

	private static ClassPathXmlApplicationContext cpac;
	
	public static void main(String[] args) throws ClassNotFoundException {
		// Um konkurenten Zugriff auf die als thread-unsicheren geltenden 
		// Swing Datenstrukturen zu verhindern, wird die GUI auf einem seperaten
		// Thread (dem ATW Event-Dispatching Thread) ausgefuehrt.
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				new MainFrame();
			}
		});
		
		
//	    FlashCardDaoImpl dao = new FlashCardDaoImpl(dataSource);
//	    dao.deleteCard(1);
//		
		
	}
	

}
