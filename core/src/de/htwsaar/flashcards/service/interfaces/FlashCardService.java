package de.htwsaar.flashcards.service.interfaces;

import java.util.List;

import de.htwsaar.flashcards.model.FlashCard;
import de.htwsaar.flashcards.model.Stack;

/**
 * <code>FlashCardService</code> -Dienst fuer die (normale) Datenbankinteraktion bzgl. 
 * der Entitaet <code>FlashCard</code>.
 * @author Marek Kohn
 *
 */
public interface FlashCardService {
	public List<FlashCard> getFlashCards(int stackId);
	public List<FlashCard> getFlashCards();
	public List<FlashCard> getFlashCards(int stackId, int box);
	public void reset(Stack stack);
	public int[] getCardsInBox(Stack stack);
}
