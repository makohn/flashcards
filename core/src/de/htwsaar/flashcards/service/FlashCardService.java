package de.htwsaar.flashcards.service;

import java.util.List;

import de.htwsaar.flashcards.dao.FlashCardDaoImpl;
import de.htwsaar.flashcards.dao.interfaces.FlashCardDao;
import de.htwsaar.flashcards.model.FlashCard;
import de.htwsaar.flashcards.model.Stack;
//Feick Martin & Marek Kohn
public class FlashCardService {

	private FlashCardDao cardDao;
	private List<FlashCard> flashcards;
	
	public FlashCardService() {
		this.cardDao = new FlashCardDaoImpl();
	}
	
	public List<FlashCard> getFlashCards(int stackId) {
		return flashcards = cardDao.getFlashCards(stackId);
	}
	
	public List<FlashCard> getFlashCards() {
		return flashcards;
	}
	
	public void reset(Stack stack) {
		cardDao.resetBoxCounter(stack);
	}
}