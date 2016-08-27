package de.htwsaar.flashcards.engine;

import java.util.Iterator;
import java.util.List;

import de.htwsaar.flashcards.dao.FlashCardDaoImpl;
import de.htwsaar.flashcards.dao.interfaces.FlashCardDao;
import de.htwsaar.flashcards.engine.interfaces.GameEngine;
import de.htwsaar.flashcards.model.FlashCard;

/**
 * Die Klasse GameEngineImpl steuert den Spielfluss und dient als
 * Controller zwischen GUI und Datenmodell.
 * Mittels einer DAO werden Karteikarten aus der Datenbank geladen.
 * Dies kann auf verschiedene Arten geschehen (sortiert, gemischt ..)
 * Zudem werden die Antworten ausgewertet und die Karten dementsprechend
 * einer neuen Box zugeteilt.
 * 
 * @author mkohn
 *
 */
public class GameEngineImpl implements GameEngine {
	
	private static final int START_PHASE = 1;

	private List<FlashCard> flashcards;
	private Iterator<FlashCard> cardIterator;
	private FlashCard currentCard;
	private FlashCardDao cardDao;
	
	public GameEngineImpl(List<FlashCard> flashcards) {
		this.flashcards = flashcards;
		cardIterator = flashcards.listIterator();
		cardDao = new FlashCardDaoImpl();
		currentCard = getNextCard();
	}
	
	/**
	 * Lädt die nächste Karte aus der Liste.
	 * @returns die zu spielende Karte.
	 */
	@Override
	public FlashCard getNextCard() {
		if (! cardIterator.hasNext())
			return null;
		else {
			return cardIterator.next();
		}
	}

	/**
	 * Wertet die Antwort aus und wendet die entsprechende
	 * Massnahme auf die gespielte Karteikarte an.
	 * richtig - Karten-Counter wird inkrementiert (bis 5).
	 * falsch - Karten-Counter wird auf 1 zurueckgesetzt.
	 * @params isCorrect - der Wahrheitsgehalt der Antwort.
	 */
	@Override
	public boolean evaluateAnswer(boolean isCorrect) {
		if(isCorrect) {
			currentCard.incrementBoxCounter();
		}
		else {
			currentCard.setBoxCounter(START_PHASE);
		}	
		cardDao.updateCard(currentCard);
		
		if ((currentCard = getNextCard()) == null) {
			return false;
		}
		return true;
	}
	
	@Override
	public FlashCard getCurrentCard() {
		return currentCard;
	}
	
	@Override
	public int getNrCards() {
		return flashcards.size();
	}
}
