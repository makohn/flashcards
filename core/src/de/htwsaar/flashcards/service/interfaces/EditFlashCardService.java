package de.htwsaar.flashcards.service.interfaces;

import de.htwsaar.flashcards.model.FlashCard;

/**
 * <code>EditFlashCardService</code> -Dienst fuer die Erstellung und Bearbeitung von Karteikarten.
 * @author Marek Kohn
 *
 */
public interface EditFlashCardService {
	
	public void nextCard();
	public void previousCard();
	public void addCard();
	public void deleteCard();
	public void saveCard(String cardName, String cardQuestion, String cardAnswer, String cardPicture);
	public FlashCard getCurrentCard();
}
