package de.htwsaar.flashcards.dao.interfaces;

import java.util.List;

import de.htwsaar.flashcards.model.FlashCard;
import de.htwsaar.flashcards.model.Stack;

/**
 * Interface der FlashCardDaoImpl-Klasse
 * 
 * @author Martin Feick
 * 
 */
public interface FlashCardDao {
	
	public void deleteCard(FlashCard flashcard);
	public void saveCard(FlashCard flashcard);
	public void updateCard(FlashCard flashcard);
	public void resetBoxCounter(Stack stack);
	public int getAskedCount(Stack stack);
	public int getAnswerCorrectCount(Stack stack);
	public int getNrCardsInBox(Stack stack, int box);
	public List<FlashCard> getFlashCards(); 
	public List<FlashCard> getFlashCards(int stackId);
	public List<FlashCard> getFlashCards(int stackId, int box);
	public FlashCard getCard(int id);
}