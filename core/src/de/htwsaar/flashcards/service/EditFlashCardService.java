package de.htwsaar.flashcards.service;

import java.util.ListIterator;

import de.htwsaar.flashcards.dao.FlashCardDaoImpl;
import de.htwsaar.flashcards.dao.interfaces.FlashCardDao;
import de.htwsaar.flashcards.model.FlashCard;

public class EditFlashCardService {
	
	private ListIterator<FlashCard> flashcardIterator;
	private FlashCardDao cardDao;
	private FlashCard currentCard;
	
	public EditFlashCardService(ListIterator<FlashCard> flashcardIterator) {
		this.flashcardIterator = flashcardIterator;
		this.cardDao = new FlashCardDaoImpl();
		this.currentCard = flashcardIterator.next();
	}
	
	public FlashCard nextCard() {
		return currentCard = flashcardIterator.next();
	}
	
	public FlashCard previousCard() {
		return currentCard = flashcardIterator.previous();
	}
	
	public void addCard() {
		currentCard = new FlashCard();
		flashcardIterator.add(currentCard);
	}
	
	public void saveCard(String cardName, String cardQuestion, String cardAnswer, int stackId, String cardPicture) {
		currentCard.setCardName(cardName);
		currentCard.setCardQuestion(cardQuestion);
		currentCard.setCardAnswer(cardAnswer);
		currentCard.setStackId(stackId);
		currentCard.setCardPicture(cardPicture);
		cardDao.saveCard(currentCard);
	}
	
	public FlashCard getCurrentCard() {
		return currentCard;
	}
	
}
