package de.htwsaar.flashcards.files.interfaces;

import de.htwsaar.flashcards.model.Stack;

/**
 * <code>ExporterCSV</code> Dient dem Serialisieren von Karteikarten in eine
 * CSV Datei.
 * 
 * @author mkohn
 * @see ExporterCSVImpl, ImporterCSV
 */
public interface ExporterCSV {
	public void exportCSV(Stack stack, String filePath);
}
