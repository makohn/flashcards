package de.htwsaar.flashcards.files.interfaces;

import java.io.File;

/**
 * <code>ImporterCSV</code> Dient dem Deserialisieren von Karteikarten und 
 * der anschliessenden Speicherung in der Datenbank
 * 
 * @author Marek Kohn
 * @see ImporterCSVImpl, ExporterCSV
 */
public interface ImporterCSV {
	public void importCSV(File file);
}
