package de.htwsaar.flashcards.engine.interfaces;

import de.htwsaar.flashcards.model.FlashCard;
/**
 * Das Interface GameEngine kapselt die Funktionalität einer
 * Spielsteuerungs-Klasse.
 * @author mkohn
 *
 */
public interface GameEngine {
	public FlashCard getNextCard();
	public boolean evaluateAnswer(boolean isCorrect);
	public FlashCard getCurrentCard();
	public int getNrCards();
}
