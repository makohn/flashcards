package de.htwsaar.flashcards.service;

import java.util.Collections;
import java.util.Comparator;
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
		return getFlashCards(stackId, FlashCardService.SORTED_OPTION);
	}
	
	public List<FlashCard> getFlashCards(int stackId, int box) {
		switch(box) {
		case BOX1_OPTION:
			
		case BOX2_OPTION:
			
		case BOX3_OPTION:

		case BOX4_OPTION: 
			//Karten aus bestimmter Box laden
			flashcards = cardDao.getFlashCards(stackId, box);
			break;
		case SHUFFLED_OPTION:
			//Zufällig Karten aus Stack laden (aus verschiedene Boxen)
			flashcards = cardDao.getFlashCards(stackId);
			Collections.shuffle(flashcards);
			break;
		case SORTED_OPTION:
			//Karten aus bestimmter Box laden mit zeitlicher Komponente 
			flashcards = cardDao.getFlashCards(stackId, box);
			timedCard(flashcards);
			break;
		case SORTED_OPTION_COMPLETE:	
			//Alle Karten aus Stack laden mit zeitlicher Komponente 
			flashcards = cardDao.getFlashCards(stackId);
			timedCard(flashcards);
			break;
		default:
			flashcards = cardDao.getFlashCards(stackId);
			Collections.sort(flashcards);
		}	
		return flashcards;
	}
	
	public List<FlashCard> getFlashCards() {
		return flashcards;
	}
	
	private void timedCard(List<FlashCard> flashcards) {		
		Collections.sort(flashcards, new Comparator<FlashCard>() {
			  public int compare(FlashCard flashcard1, FlashCard flashcard2) {
			      return flashcard1.getCardLastAccessDate().compareTo(flashcard2.getCardLastAccessDate());}
			});
	}	
}