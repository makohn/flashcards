package de.htwsaar.flashcards.engine.interfaces;

import de.htwsaar.flashcards.model.FlashCard;

public interface GameEngine {
	public void loadFlashCards();
	public FlashCard getNextCard();
	public boolean evaluateAnswer(boolean isCorrect);
	public FlashCard getCurrentCard();
}
