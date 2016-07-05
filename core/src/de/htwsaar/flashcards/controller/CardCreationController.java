package de.htwsaar.flashcards.controller;

import de.htwsaar.flashcards.dao.FlashCardDaoImpl;
import de.htwsaar.flashcards.dao.interfaces.FlashCardDao;
import de.htwsaar.flashcards.model.FlashCard;

/**
 * Dient als Fassade fuer die Geschaeftslogik beim Erstellen einer
 * Karteikarte und kapselt den Zugriff auf die Datenbank aus Sicht der GUI.
 * 
 * @author mkohn
 *
 */
public class CardCreationController {

	private FlashCardDao flashCardDao;

	public CardCreationController() throws ClassNotFoundException {
		flashCardDao = new FlashCardDaoImpl();
	}
	/**
	 * Gibt in der UI erzeugte Werte an die Datenbankzugriffsschicht
	 * weiter.
	 * @param name - Die Bezeichnung einer Karteikarte
	 * @param question - Die Frage der Karteikarte
	 * @param answer - Die Antwort der Kartiekarte
	 */
	public void saveCard(String name, String question, String answer) {
		FlashCard flashcard = new FlashCard(name, question, answer, 1, null);
		flashCardDao.saveCard(flashcard);
	}
}
