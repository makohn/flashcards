package de.htwsaar.flashcards.service;

import java.util.Collections;
import java.util.List;

import de.htwsaar.flashcards.dao.FlashCardDaoImpl;
import de.htwsaar.flashcards.dao.interfaces.FlashCardDao;
import de.htwsaar.flashcards.model.FlashCard;

public class FlashCardService {
	
	public static final int BOX1_OPTION    = 1;
	public static final int BOX2_OPTION    = 2;
	public static final int BOX3_OPTION    = 3;
	public static final int BOX4_OPTION    = 4;
	public static final int SHUFFLED_OPTION = 5;
	public static final int SORTED_OPTION  = 6;
	
	private FlashCardDao cardDao;
	private List<FlashCard> flashcards;
	
	public FlashCardService() {
		this.cardDao = new FlashCardDaoImpl();
	}
	
	public List<FlashCard> getFlashCards(int stackId) {
		return getFlashCards(stackId, FlashCardService.SORTED_OPTION);
	}
	
	public List<FlashCard> getFlashCards(int stackId, int box) {
		switch(box) {
		case BOX1_OPTION:
		case BOX2_OPTION:
		case BOX3_OPTION:
		case BOX4_OPTION:
			flashcards = cardDao.getFlashCards(stackId, box);
			break;
		case SHUFFLED_OPTION:
			flashcards = cardDao.getFlashCards(stackId);
			Collections.shuffle(flashcards);
			break;
		case SORTED_OPTION:
		default:
			flashcards = cardDao.getFlashCards(stackId);
			Collections.sort(flashcards);
		}	
		return flashcards;
	}
	
	public List<FlashCard> getFlashCards() {
		return flashcards;
	}
	
}
