package de.htwsaar.flashcards.builder;

import de.htwsaar.flashcards.files.ExporterCSVImpl;
import de.htwsaar.flashcards.files.ImporterCSVImpl;
import de.htwsaar.flashcards.files.interfaces.ExporterCSV;
import de.htwsaar.flashcards.files.interfaces.ImporterCSV;

public class FileHandlerObjectBuilder {
	/*
	 * ImporterCSV
	 */
	public static ImporterCSV getImporterCSVObject() {
		return new ImporterCSVImpl();
	}
	
	/*
	 * ExporterCSV
	 */
	public static ExporterCSV getExporterCSVObject() {
		return new ExporterCSVImpl();
	}
}
