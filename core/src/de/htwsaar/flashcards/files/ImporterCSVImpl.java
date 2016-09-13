package de.htwsaar.flashcards.files;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

import de.htwsaar.flashcards.dao.FlashCardDaoImpl;
import de.htwsaar.flashcards.dao.StackDaoImpl;
import de.htwsaar.flashcards.dao.interfaces.FlashCardDao;
import de.htwsaar.flashcards.dao.interfaces.StackDao;
import de.htwsaar.flashcards.files.interfaces.ImporterCSV;
import de.htwsaar.flashcards.model.FlashCard;
import de.htwsaar.flashcards.model.Stack;

/**
 * <code>ImporterCSVImpl</code> - Importiert die Karten einer uebergebenen CSV Datei 
 * (bzw. deren Pfad) in die Datenbank.
 * 
 * @author mkohn
 */
public class ImporterCSVImpl implements ImporterCSV {
	
	private static final String CSV_SEPARATOR = ",";
	private static final int NR_CARD_FIELDS = 5;
	private static final int NR_STACK_FIELDS = 4;
	
	private FlashCardDao cardDao;
	private StackDao stackDao;
	
	public ImporterCSVImpl() {
		this.cardDao = new FlashCardDaoImpl();
		this.stackDao = new StackDaoImpl();
	}
	
	/**
	 * Liest eine Datei gekennzeichnet durch die uebergebene <code>File</code> Klasse 
	 * zeilenweise ein. Jede Zeile wird dann an die Hilfsmethode <code>deserialize</code>
	 * uebergeben, um daraus eine <code>Flashcard</code> Instanz zu konstruieren.
	 * @param path - der Pfad der Datei die importiert werden soll.
	 */
	@Override
	public void importCSV(File f) {
		try {
			check(f);
			FileInputStream fis = new FileInputStream(f);
			BufferedReader br = new BufferedReader(new InputStreamReader(fis));
		 
			String line = br.readLine();
			deserializeStack(line);
			while ((line = br.readLine()) != null) {
				deserializeCards(line);
			}
			br.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Ueberprueft ob die uebergebene Datei existiert, lesbar ist und es sich
	 * bei der Datei um eine CSV Datei handelt.
	 * @param f - Die zu ueberpruefende Datei
	 * @throws Exception
	 */
	private void check(File f) throws Exception{
		if(! f.exists()) { 
		    throw new FileNotFoundException("Datei konnte nicht gefunde werden.");
		}
		else if (f.isDirectory() || ! f.canRead()) {
			throw new Exception("Datei konnte nicht gelesen werden.");
		}
		else if (! f.getPath().endsWith(".csv")) {
			throw new Exception("Keine .csv Datei!");
		}
	}
	
	/**
	 * Deserialisiert eine CSV Datei, indem die Werte jeder Zeile anhand der Kommata 
	 * getrennt werden um damit eine <code>Flashcard</code> Instanz zu initialisieren.
	 * Mittels einem <code>FlashcardDao</code> wird diese dann persistent gespeichert.
	 * @param line - die zu parsende Zeile 
	 */
	private void deserializeCards(String line) {
		FlashCard card = new FlashCard();
		String[] values = line.split(CSV_SEPARATOR);
		if (values.length != NR_CARD_FIELDS) {
			System.err.println("Fehler beim Parsen der Zeile (Card)");
			return;
		}
		try {
			card.setCardId(Integer.valueOf(values[0]));
			card.setCardName(values[1]);
			card.setCardQuestion(values[2]);
			card.setCardAnswer(values[3]);
			card.setStack(Integer.valueOf(values[4]));
			cardDao.saveCard(card);
		} catch (Exception e) {
			System.err.println("Fehler beim Erstellen der Karteikarte");
			e.printStackTrace();
		}
	}
	
	private void deserializeStack(String line) {
		Stack stack = new Stack();
		String[] values = line.split(CSV_SEPARATOR);
		if (values.length != NR_STACK_FIELDS) {
			System.err.println("Fehler beim Parsen der Zeile (Stack)");
			return;
		}
		try {
			stack.setStackId(Integer.valueOf(values[0]));
			stack.setStackName(values[1]);
			stack.setSubject(values[2]);
			stack.setTyp(Integer.valueOf(values[3]));
			stackDao.addStack(stack);
		} catch (Exception e) {
			System.err.println("Fehler beim Erstellen des Stacks.");
			e.printStackTrace();
		}
	}
}
