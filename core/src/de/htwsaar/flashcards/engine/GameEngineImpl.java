package de.htwsaar.flashcards.engine;

import java.util.Collections;
import java.util.List;

import de.htwsaar.flashcards.dao.FlashCardDaoImpl;
import de.htwsaar.flashcards.dao.interfaces.FlashCardDao;
import de.htwsaar.flashcards.engine.interfaces.GameEngine;
import de.htwsaar.flashcards.model.FlashCard;

public class GameEngineImpl implements GameEngine {
	
	private static final int START_PHASE = 1;
	
	public static final int BOX1_OPTION    = 1;
	public static final int BOX2_OPTION    = 2;
	public static final int BOX3_OPTION    = 3;
	public static final int BOX4_OPTION    = 4;
	public static final int SHUFFLED_OPTION = 5;
	public static final int SORTED_OPTION  = 6;
	
	private List<FlashCard> flashcards;
	private FlashCardDao cardDao;
	private FlashCard currentCard;
	
	private int mode = 5;
	
	public GameEngineImpl() throws ClassNotFoundException {
		cardDao = new FlashCardDaoImpl();
		loadFlashCards();
		currentCard = getNextCard();
	}
	
	@Override
	public void loadFlashCards() {
		switch(mode) {
		case BOX1_OPTION:
		case BOX2_OPTION:
		case BOX3_OPTION:
		case BOX4_OPTION:
			flashcards = cardDao.getFlashCards(mode);
			break;
		case SHUFFLED_OPTION:
			flashcards = cardDao.getFlashCards();
			Collections.shuffle(flashcards);
			break;
		case SORTED_OPTION:
		default:
			flashcards = cardDao.getFlashCards();
			Collections.sort(flashcards);
		}
		
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
		//System.out.println(currentCard.getBoxCounter());
		cardDao.update(currentCard);
		
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
