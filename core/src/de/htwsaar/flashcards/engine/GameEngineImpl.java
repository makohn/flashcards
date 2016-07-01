package de.htwsaar.flashcards.engine;

import java.util.Collections;
import java.util.List;

import de.htwsaar.flashcards.dao.FlashCardDaoImpl;
import de.htwsaar.flashcards.dao.interfaces.FlashCardDao;
import de.htwsaar.flashcards.engine.interfaces.GameEngine;
import de.htwsaar.flashcards.model.FlashCard;

public class GameEngineImpl implements GameEngine {
	
	private static final int START_PHASE = 0;
	
	private List<FlashCard> flashcards;
	private FlashCardDao cardDao;
	private FlashCard currentCard;
	
	public GameEngineImpl() throws ClassNotFoundException {
		cardDao = new FlashCardDaoImpl();
		loadFlashCards();
		currentCard = getNextCard();
	}
	
	@Override
	public void loadFlashCards() {
		flashcards = cardDao.getFlashCards();
		Collections.shuffle(flashcards);
	}

	@Override
	public FlashCard getNextCard() {
		if (flashcards.size() == 0)
			return null;
		else {
			return flashcards.remove(0);
		}
	}

	@Override
	public boolean evaluateAnswer(boolean isCorrect) {
		if(isCorrect) {
			currentCard.incrementBoxCounter();
		}
		else {
			currentCard.setBoxCounter(START_PHASE);
		}	
		cardDao.save(currentCard);
		
		if ((currentCard = getNextCard()) == null) {
			return false;
		}
		return true;
	}
	
	@Override
	public FlashCard getCurrentCard() {
		return currentCard;
	}
}
