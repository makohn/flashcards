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
	private List<FlashCard> flashcards;
	
	public StudyService(GameOption options, Stack stack) {
		cardDao = new FlashCardDaoImpl();
		this.options = options;
		this.stack = stack;
		this.flashcards = loadFlashCards();
	}
	
	private List<FlashCard> loadFlashCards() {
		int stackId = stack.getStackId();
		int box = options.getBoxOption();
		int limit = options.getLimit();
		boolean sorted = options.isSorted();
		boolean dateSensitive = options.isDateSensitive();
		
		List<FlashCard> cards;
		// Sollen die Karten aus einer bestimmten Box geladen werden ?
		if(box == 0) {
			cards = cardDao.getFlashCards(stackId);
		}
		else {
			cards = cardDao.getFlashCards(stackId, box);
		}
		// Gibt es eine maximale Anzahl an Karten ?
		if(limit > 0 && limit < cards.size()) {
			cards = cards.subList(0, limit);
		}
		// Sollen die Karten sortiert werden ?
		if(sorted) {
			Collections.sort(cards);
		}
		else {
			Collections.shuffle(cards);
		}
		// Spielt das Datum bei der Sortierung eine Rolle ?
		if(dateSensitive) {
			timedCard(cards);
		}
		return cards;
	}
	
	public int getTime() {
		return options.getTime();
	}
	
	public int getNrOfCards() {
		return flashcards.size();
	}
	
	public List<FlashCard> getFlashCards() {
		return flashcards;
	}
	
	public boolean noFlashCardsInList() {
		return flashcards.isEmpty();
	}
	
	public void saveCard(FlashCard card, boolean answer) {
		int boxCount = card.getBoxCounter();
		if(!answer) { 
			switch(options.getEvalType()) {
			case BACK_TO_FIRST_BOX:
				boxCount = 1;
				break;
			case DECREMENT_BY_ONE:
				if(boxCount > 1) boxCount--;
				break;
			default:
			case STAY_IN_BOX:
				break;
			}
		}
		else {
			if(boxCount < 5) boxCount++;
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
