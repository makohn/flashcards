package de.htwsaar.flashcards.dao.interfaces;

import java.util.List;

import de.htwsaar.flashcards.model.FlashCard;

public interface FlashCardDao {
	public void delete(FlashCard flashcard);
	public void save(FlashCard flashcard);
	public List<FlashCard> getFlashCards(); //TODO: Add the deck as a parameter
	public FlashCard get(int id);
	public void update(FlashCard flashCard);
}
