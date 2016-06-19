package de.htwsaar.flashcards.engine;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import de.htwsaar.flashcards.dao.FlashCardDaoImpl;
import de.htwsaar.flashcards.dao.interfaces.FlashCardDao;
import de.htwsaar.flashcards.engine.interfaces.GameEngine;
import de.htwsaar.flashcards.model.FlashCard;

public class GameEngineImpl implements GameEngine {
	
	public static final int NUMBER_OF_BUCKETS = 5;
	public static final int START_PHASE = 0;
	
	private ArrayList<Queue<FlashCard>> cardbox;
	private FlashCardDao cardDao;
	private FlashCard currentCard;
	
	public GameEngineImpl() {
		cardDao = new FlashCardDaoImpl();
		cardbox = new ArrayList<Queue<FlashCard>>(NUMBER_OF_BUCKETS);
		for (int i = 0; i < NUMBER_OF_BUCKETS; i++) {
			cardbox.add(new PriorityQueue<FlashCard>());
		}
		loadFlashCards();
		currentCard = getNextCard();
	}
	

	@Override
	public void loadFlashCards() {
		List<FlashCard> flashCards = cardDao.getFlashCards();
		for (int i = 0; i < flashCards.size(); i++) {
			FlashCard flashcard = flashCards.get(i);
			cardbox.get(flashcard.getPhase()).add(flashcard);
		}
	}

	@Override
	public FlashCard getNextCard() {
		
		if (currentCard == null && !cardbox.get(0).isEmpty()) {
			return cardbox.get(0).remove();
		}
		
		int phase = currentCard.getPhase();
		
		if (cardbox.get(phase).isEmpty()) {
			phase++;
			return cardbox.get(phase).remove();
		}
		
		return cardbox.get(phase).remove();
	}

	@Override
	public void evaluateAnswer(boolean isCorrect) {
		if(isCorrect) {
			currentCard.incrementPhase();
			cardbox.get(currentCard.getPhase()).add(currentCard);
		}
		else {
			currentCard.setPhase(START_PHASE);
			cardbox.get(START_PHASE).add(currentCard);
		}	
	}
	
	@Override
	public FlashCard getCurrentCard() {
		return currentCard;
	}
}
