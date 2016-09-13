package de.htwsaar.flashcards.files;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

import de.htwsaar.flashcards.files.interfaces.ExporterCSV;
import de.htwsaar.flashcards.model.FlashCard;
import de.htwsaar.flashcards.model.Stack;
import de.htwsaar.flashcards.service.FlashCardServiceImpl;
import de.htwsaar.flashcards.service.interfaces.FlashCardService;

/**
 * <code>ExporterCSVImpl</code> - Exportiert die Karten eines uebergebenen Stacks in
 * eine CSV Datei.
 * 
 * @author mkohn
 */
public class ExporterCSVImpl implements ExporterCSV {

	private static final String CSV_SEPARATOR = ",";
	private FlashCardService cardService;

	public ExporterCSVImpl() {
		cardService = new FlashCardServiceImpl();
	}
	
	/**
	 * Exportiert eine Liste von Karteikarten eines uebergebenen Stacks in eine CSV Datei.
	 * @param stack - Der Stack der die Karteikarten beinhaltet
	 * @param filePath - Der DateiPfad unter dem die CSV-Datei abgelegt werden soll.
	 */
	@Override
	public void exportCSV(Stack stack, String filePath) {
		try {
			BufferedWriter bw = new BufferedWriter(
					new OutputStreamWriter(new FileOutputStream(filePath + ".csv"), "UTF-8"));
			List<FlashCard> flashcards = cardService.getFlashCards(stack.getStackId());
			bw.write(stacktoCSV(stack));
			bw.newLine();
			for (FlashCard card : flashcards) {
				bw.write(flashCardToCSV(card));
				bw.newLine();
			}
			bw.flush();
			bw.close();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Serialisiert eine <code>FlashCard</code> Instanz zu einer CSV Datei.
	 * @param card - die zu serialisierende Karte
	 * @return eine Zeile in der zu erstellenden CSV Datei
	 */
	private String flashCardToCSV(FlashCard card) {
		StringBuffer csvString= new StringBuffer();
		csvString.append(card.getCardId());		  //1
		csvString.append(CSV_SEPARATOR);
		csvString.append(card.getCardName());	  //2
		csvString.append(CSV_SEPARATOR);
		csvString.append(card.getCardQuestion()); //3
		csvString.append(CSV_SEPARATOR);
		csvString.append(card.getCardAnswer());	  //4
		csvString.append(CSV_SEPARATOR);          
		csvString.append(card.getStackId());      //5
		return csvString.toString();
	}
	
	private String stacktoCSV(Stack stack) {
		StringBuffer csvString= new StringBuffer();
		csvString.append(stack.getStackId());
		csvString.append(CSV_SEPARATOR);
		csvString.append(stack.getStackName());
		csvString.append(CSV_SEPARATOR);
		csvString.append(stack.getSubject());
		csvString.append(CSV_SEPARATOR);
		csvString.append(stack.getTyp());
		return csvString.toString();
	}
}
