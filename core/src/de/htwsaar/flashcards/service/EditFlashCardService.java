package de.htwsaar.flashcards.service;

import java.util.ListIterator;

import de.htwsaar.flashcards.dao.FlashCardDaoImpl;
import de.htwsaar.flashcards.dao.interfaces.FlashCardDao;
import de.htwsaar.flashcards.model.FlashCard;

public class EditFlashCardService {
	
	private ListIterator<FlashCard> flashcardIterator;
	private FlashCardDao cardDao;
	private FlashCard currentCard;
	private int stackId;
	
	public EditFlashCardService(ListIterator<FlashCard> flashcardIterator, int stackId) {
		this.flashcardIterator = flashcardIterator;
		this.cardDao = new FlashCardDaoImpl();
		this.stackId = stackId;
		setCurrentCard();
	}
	
	public void nextCard() {
		if(flashcardIterator.hasNext())
			currentCard = flashcardIterator.next();
	}
	
	public void previousCard() {
		if(flashcardIterator.hasPrevious())
			currentCard = flashcardIterator.previous();
	}
	
	public void addCard() {
		currentCard = new FlashCard();
		flashcardIterator.add(currentCard);
	}
	
	public void saveCard(String cardName, String cardQuestion, String cardAnswer, String cardPicture) {
		currentCard.setCardName(cardName);
		currentCard.setCardQuestion(cardQuestion);
		currentCard.setCardAnswer(cardAnswer);
		currentCard.setStackId(stackId);
		currentCard.setCardPicture(cardPicture);
		if(currentCard.getCardId() == 0)
			cardDao.saveCard(currentCard);
		else
			cardDao.updateCard(currentCard);
	}
	
	public FlashCard getCurrentCard() {
		return currentCard;
	}
	
	private void setCurrentCard() {
		if (flashcardIterator.hasNext()) 
			this.currentCard = flashcardIterator.next();
		else addCard();
	}
}