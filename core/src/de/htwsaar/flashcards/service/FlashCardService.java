package de.htwsaar.flashcards.service;

import java.util.List;

import de.htwsaar.flashcards.dao.FlashCardDaoImpl;
import de.htwsaar.flashcards.dao.interfaces.FlashCardDao;
import de.htwsaar.flashcards.model.FlashCard;
//Feick Martin & Marek Kohn
public class FlashCardService {
	
	public static final int BOX1_OPTION   		   = 1;
	public static final int BOX2_OPTION  		   = 2;
	public static final int BOX3_OPTION  		   = 3;
	public static final int BOX4_OPTION   		   = 4;
	public static final int SHUFFLED_OPTION		   = 5;
	public static final int SORTED_OPTION 		   = 6;
	public static final int SORTED_OPTION_COMPLETE = 7;
	
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
	
	public void reset(int stackId) {
		cardDao.resetBoxCounter(stackId);
	}
}