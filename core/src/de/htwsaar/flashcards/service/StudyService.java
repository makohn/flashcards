package de.htwsaar.flashcards.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import de.htwsaar.flashcards.dao.FlashCardDaoImpl;
import de.htwsaar.flashcards.dao.interfaces.FlashCardDao;
import de.htwsaar.flashcards.model.FlashCard;
import de.htwsaar.flashcards.model.GameOption;
import de.htwsaar.flashcards.model.Stack;

public class StudyService {
	
	private static final int BACK_TO_FIRST_BOX = 0;
	private static final int DECREMENT_BY_ONE = 1;
	private static final int STAY_IN_BOX = 2;
	
	private GameOption options;
	private Stack stack;
	private FlashCardDao cardDao;
	private int nrOfCards;
	
	public StudyService(GameOption options, Stack stack) {
		this.options = options;
		this.stack = stack;
		cardDao = new FlashCardDaoImpl();
	}
	
	public List<FlashCard> getFlashCards() {
		int stackId = stack.getStackId();
		int box = options.getBoxOption();
		int limit = options.getLimit();
		boolean sorted = options.isSorted();
		boolean dateSensitive = options.isDateSensitive();
		
		List<FlashCard> flashcards;
		// Sollen die Karten aus einer bestimmten Box geladen werden ?
		if(box == 0) {
			flashcards = cardDao.getFlashCards(stackId);
		}
		else {
			flashcards = cardDao.getFlashCards(stackId, box);
		}
		// Gibt es eine maximale Anzahl an Karten ?
		if(limit > 0 && limit < flashcards.size()) {
			flashcards = flashcards.subList(0, limit);
		}
		// Sollen die Karten sortiert werden ?
		if(sorted) {
			Collections.sort(flashcards);
		}
		else {
			Collections.shuffle(flashcards);
		}
		// Spielt das Datum bei der Sortierung eine Rolle ?
		if(dateSensitive) {
			timedCard(flashcards);
		}
		this.nrOfCards = flashcards.size();
		return flashcards;
	}
	
	public int getTime() {
		return options.getTime();
	}
	
	public int getNrOfCards() {
		return nrOfCards;
	}
	
	public void saveCard(FlashCard card) {
		int boxCount = card.getBoxCounter();
		
		switch(options.getEvalType()) {
		case BACK_TO_FIRST_BOX:
			boxCount = 1;
			break;
		case DECREMENT_BY_ONE:
			boxCount--;
			break;
		default:
		case STAY_IN_BOX:
			break;
		}
		card.setBoxCounter(boxCount);
		cardDao.updateCard(card);
	}
	
	private void timedCard(List<FlashCard> flashcards) {		
		Collections.sort(flashcards, new Comparator<FlashCard>() {
			  public int compare(FlashCard flashcard1, FlashCard flashcard2) {
			      return flashcard1.getCardLastAccessDate().compareTo(flashcard2.getCardLastAccessDate());}
			});
	}	
}
