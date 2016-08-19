package de.htwsaar.flashcards.dao.interfaces;

import java.util.List;

import de.htwsaar.flashcards.model.FlashCard;

/**
 * Interface der FlashCardDaoImpl-Klasse
 * 
 * @author Feick Martin
 * 
 */

public interface FlashCardDao {
	
	public void deleteCard(FlashCard flashcard);
	public void saveCard(FlashCard flashcard);
	public void updateCard(FlashCard flashcard);
	public List<FlashCard> getFlashCards(); //TODO: Add the deck as a parameter
	public List<FlashCard> getFlashCards(int box);
	public FlashCard getCard(int id);
}
