package de.htwsaar.flashcards.service.interfaces;

import java.util.List;

import de.htwsaar.flashcards.model.FlashCard;

/**
 * <code>StudyService</code> - Steuert den Spielfluss und bietet Zugriff auf spezielle
 * DAO - Methoden wie etwa dem Speichern und Aktualisieren von Karteikarten.
 * @author Marek Kohn
 *
 */
public interface StudyService {
	public int getTime();
	public int getNrOfCards();
	public List<FlashCard> getFlashCards();
	public boolean noFlashCardsInList();
	public void saveCard(FlashCard card, boolean answer);
}
