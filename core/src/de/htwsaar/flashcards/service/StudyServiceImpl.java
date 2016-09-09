package de.htwsaar.flashcards.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import de.htwsaar.flashcards.builder.DaoObjectBuilder;
import de.htwsaar.flashcards.dao.interfaces.FlashCardDao;
import de.htwsaar.flashcards.model.FlashCard;
import de.htwsaar.flashcards.model.GameOption;
import de.htwsaar.flashcards.model.Stack;
import de.htwsaar.flashcards.service.interfaces.StudyService;

/**
 * <code>StudyServiceImpl</code> - Dient der Steuerung des Spielablaufs. 
 * Anhand von einer uebergeben <code>GameOption</code> Instanz werden Karten mit
 * einer bestimmten Konfiguration aus der Datenbank geladen. 
 * Es koennen zudem Karten je nach Wahrheitsgehalt der gegebenen Antwort aktualisiert  
 * werden, insb. <code>BoxCount</code>,<code>Asked</code> und <code>AnsweredCorrect</code>.
 * 
 * @see GameOption, FrmStud
 * @author Marek Kohn, Martin Feick
 *
 */
public class StudyServiceImpl implements StudyService {
	
	private static final int BACK_TO_FIRST_BOX = 0;
	private static final int DECREMENT_BY_ONE = 1;
	private static final int STAY_IN_BOX = 2;
	
	private GameOption options;
	private Stack stack;
	private FlashCardDao cardDao;
	private List<FlashCard> flashcards;
	
	/**
	 * Erstellt eine neue Instanz fuer die <code>StudyServiceImpl</code>
	 * Klasse.
	 * @param options - uebergebene Spieloptionen.
	 * @param stack - der Stack der gespielt werden soll.
	 */
	public StudyServiceImpl(GameOption options, Stack stack) {
		cardDao = DaoObjectBuilder.getFlashCardDao();
		this.options = options;
		this.stack = stack;
		this.flashcards = loadFlashCards();
	}
	
	/**
	 * Laedt eine Liste von Karteikarten anhand der uebergebenen
	 * Optionen. Merkt sich diese Liste in dem entsprechenden Attribut
	 * <code>flashcards</code>
	 * @return
	 */
	private List<FlashCard> loadFlashCards() {
		int stackId = stack.getStackId();
		int box = options.getBoxOption();
		int limit = options.getLimit();
		boolean sorted = options.isSorted();
		boolean dateSensitive = options.isDateSensitive();
		
		List<FlashCard> cards;
//##### Sollen die Karten aus einer bestimmten Box geladen werden ?
		if(box == 0) {
			cards = cardDao.getFlashCards(stackId);
		}
		else {
			cards = cardDao.getFlashCards(stackId, box);
		}
//##### Gibt es eine maximale Anzahl an Karten ?
		if(limit > 0 && limit < cards.size()) {
			cards = cards.subList(0, limit);
		}
//##### Sollen die Karten sortiert werden ?
		if(sorted) {
			Collections.sort(cards);
		}
		else {
			Collections.shuffle(cards);
		}
//##### Spielt das Datum bei der Sortierung eine Rolle ?
		if(dateSensitive) {
			timedCard(cards);
		}
		return cards;
	}
	
	/**
	 * Gibt die Zeitbeschraenkung aus, die in den Optionen
	 * eingestellt wurde. Ist dieser Wert 0, so ist die Zeit
	 * unbegrenzt.
	 * @return - die Zeit pro Spielzug
	 */
	@Override
	public int getTime() {
		return options.getTime();
	}
	
	/**
	 * Gibt die Anzahl der Karten aus, die geladen wurden.
	 */
	@Override
	public int getNrOfCards() {
		return flashcards.size();
	}
	
	/**
	 * Gibt die Liste der Karten zurueck, die durch 
	 * <code>loadFlashCards()</code> geladen wurde.
	 * @return
	 */
	@Override
	public List<FlashCard> getFlashCards() {
		return flashcards;
	}
	
	/**
	 * Gibt an ob sich ueberhaupt Karteikarten in der Liste
	 * befinden.
	 */
	@Override
	public boolean noFlashCardsInList() {
		return flashcards.isEmpty();
	}
	
	/**
	 * Speichert eine gespielte Karte. Je nach Antwort werden hierbei
	 * unterschiedliche Werte fuer 
	 *		- <code>BoxCount</code>,
	 *		- <code>Asked</code> und 
	 * 		- <code>AnsweredCorrect</code>
	 * gesetzt. 
	 * @param card - Die Karte die beantwortet wurde.
	 * @param answer - Der Wahrheitsgehalt der Antwort.
	 */
	@Override
	public void saveCard(FlashCard card, boolean answer) {
		int boxCount = card.getBoxCounter();
		if(!answer) { 
			switch(options.getEvalType()) {
			case BACK_TO_FIRST_BOX:
				boxCount = 1;
				break;
			case DECREMENT_BY_ONE:
				if(boxCount > 1) boxCount--;
				break;
			default:
			case STAY_IN_BOX:
				break;
			}
		}
		else {
			if(boxCount < 5) boxCount++;
			card.incrementAnsweredCorrect();
		}
		card.incrementAsked();
		card.setBoxCounter(boxCount);
		cardDao.updateCard(card);
	}
	
	/**
	 * Hilfsmethode zum zeitlichen Sortieren einer Karte, bei gesetzter
	 * Option
	 * @param flashcards - die Liste von Karteikarten, die sortiert werden soll.
	 */
	private void timedCard(List<FlashCard> flashcards) {		
		Collections.sort(flashcards, new Comparator<FlashCard>() {
			  public int compare(FlashCard flashcard1, FlashCard flashcard2) {
			      return flashcard1.getCardLastAccessDate().compareTo(flashcard2.getCardLastAccessDate());}
			});
	}	
}
