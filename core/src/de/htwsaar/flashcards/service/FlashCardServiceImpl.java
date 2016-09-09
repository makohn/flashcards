package de.htwsaar.flashcards.service;

import java.util.List;

import de.htwsaar.flashcards.builder.DaoObjectBuilder;
import de.htwsaar.flashcards.dao.interfaces.FlashCardDao;
import de.htwsaar.flashcards.model.FlashCard;
import de.htwsaar.flashcards.model.Stack;
import de.htwsaar.flashcards.service.interfaces.FlashCardService;
/**
 * <code>FlashCardServiceImpl</code> - Kapselt den Zugriff auf DAO Methoden.
 * @author Marek Kohn, Martin Feick
 *
 */
public class FlashCardServiceImpl implements FlashCardService{

	private static final int NR_OF_BOXES = 5;
	
	private FlashCardDao cardDao;
	private List<FlashCard> flashcards;
	
	public FlashCardServiceImpl() {
		this.cardDao = DaoObjectBuilder.getFlashCardDao();
	}
	
	/**
	 * Gibt eine List von Karteikarten eines bestimmten Stacks zurueck
	 * @param stackId - die ID des ausgewaehlten Stacks
	 */
	@Override
	public List<FlashCard> getFlashCards(int stackId) {
		return flashcards = cardDao.getFlashCards(stackId);
	}
	
	/**
	 * Gibt alle sich in der Datenbank befindlichen Karteikarten zurueck.
	 * @return
	 */
	@Override
	public List<FlashCard> getFlashCards() {
		return flashcards;
	}
	/**
	 * Gibt alle Karten eines Stacks zurueck, die sich in einer bestimmten
	 * Box befinden.
	 * @param stackId - der ausgewaehlte Stack
	 * @param box - die ausgewaehlte Box
	 */
	@Override
	public List<FlashCard> getFlashCards(int stackId, int box) {
		return cardDao.getFlashCards(stackId, box);
	}
	
	/**
	 * Setzt den Box Counter fuer alle Karten eines Stacks zurueck auf den
	 * Initialwert (1).
	 * @param stack
	 */
	@Override
	public void reset(Stack stack) {
		cardDao.resetBoxCounter(stack);
	}
	
	/**
	 * Gibt die Anzahl je Box von Karteikarten eines Stacks zurueck.
	 * @param stack
	 * @return
	 */
	@Override
	public int[] getCardsInBox(Stack stack) {
		int[] values = new int[NR_OF_BOXES];
		for (int i = 0; i<5; ++i) {
			values[i]=cardDao.getNrCardsInBox(stack, i+1);
		}
		return values;
	}

	/**
	 * Gibt die Gesamtanzahl der Abfragen der Karten eines Stacks zurueck.
	 */
	@Override
	public int getCountAsked(Stack stack) {
		return cardDao.getAskedCount(stack);
	}

	/**
	 * Gibt die Gesamtanzahl der richtigen Antworten eines Stack zurueck.
	 */
	@Override
	public int getCountAnsweredCorrect(Stack stack) {
		return cardDao.getAnswerCorrectCount(stack);
	}
}