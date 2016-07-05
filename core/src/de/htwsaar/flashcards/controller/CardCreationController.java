package de.htwsaar.flashcards.controller;

import de.htwsaar.flashcards.dao.FlashCardDaoImpl;
import de.htwsaar.flashcards.dao.interfaces.FlashCardDao;
import de.htwsaar.flashcards.model.FlashCard;

public class CardCreationController {

	private FlashCardDao flashCardDao;

	public CardCreationController() throws ClassNotFoundException {
		flashCardDao = new FlashCardDaoImpl();
	}

	public void saveCard(String name, String question, String answer) {
		FlashCard flashcard = new FlashCard(name, question, answer, 1, null);
		flashCardDao.saveCard(flashcard);
	}
}
