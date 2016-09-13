package de.htwsaar.flashcards.service;

import java.util.ListIterator;

import de.htwsaar.flashcards.dao.FlashCardDaoImpl;
import de.htwsaar.flashcards.dao.interfaces.FlashCardDao;
import de.htwsaar.flashcards.model.FlashCard;
import de.htwsaar.flashcards.service.interfaces.EditFlashCardService;

/**
 * <code>EditFlashCardService</code> - dient dem Navigieren durch eine gegebene
 * Liste von Karteikarten, dem Bearbeiten und dem Speichern von einzelnen Karten.
 * 
 * @see FrmEditStack
 * @author Marek Kohn
 *
 */
public class EditFlashCardServiceImpl implements EditFlashCardService {
	
	private ListIterator<FlashCard> flashcardIterator;
	private FlashCardDao cardDao;
	private FlashCard currentCard;
	private int stackId;
	
	public EditFlashCardServiceImpl(ListIterator<FlashCard> flashcardIterator, int stackId) {
		this.flashcardIterator = flashcardIterator;
		this.cardDao = new FlashCardDaoImpl();
		this.stackId = stackId;
		setCurrentCard();
	}
	
	/**
	 * Iteriert vorwaerts durch die Liste. Merkt sich die entsprechende Karte.
	 */
	@Override
	public void nextCard() {
		if(flashcardIterator.hasNext())
			currentCard = flashcardIterator.next();
	}
	
	/**
	 * Iteriert rueckwaerts durch die Liste. Merkt sich die entsprechende Karte.
	 */
	@Override
	public void previousCard() {
		if(flashcardIterator.hasPrevious())
			currentCard = flashcardIterator.previous();
	}
	
	/**
	 * Fuegt der Liste eine neue Karte hinzu. 
	 */
	@Override
	public void addCard() {
		currentCard = new FlashCard();
		flashcardIterator.add(currentCard);
	}
	
	/**
	 * Speichert eine neu erstellte in der Datenbank.
	 * @param cardName - Der Name der neuen Karte
	 * @param cardQuestion - Die Frage der neuen Karte
	 * @param cardAnswer - Die Antwort der neuen Karte
	 * @param cardPicture - Das Bild der neuen Karte
	 */
	@Override
	public void saveCard(String cardName, String cardQuestion, String cardAnswer, String cardPicture) {
		currentCard.setCardName(cardName);
		currentCard.setCardQuestion(cardQuestion);
		currentCard.setCardAnswer(cardAnswer);
		currentCard.setStack(stackId);
		currentCard.setCardPicture(cardPicture);
		if(currentCard.getCardId() == 0)
			cardDao.saveCard(currentCard);
		else
			cardDao.updateCard(currentCard);
	}
	
	/**
	 * Gibt die aktuelle Karte aus.
	 */
	@Override
	public FlashCard getCurrentCard() {
		return currentCard;
	}
	
	/**
	 * Hilfsmethode zum initialisieren der ersten Karte.
	 * Falls die Liste eine Karte enthaelt, setze diese als aktuelle Karte.
	 * Ansonsten erstelle eine neue Karte.
	 */
	private void setCurrentCard() {
		if (flashcardIterator.hasNext()) 
			this.currentCard = flashcardIterator.next();
		else addCard();
	}
}