package de.htwsaar.flashcards.database;

import java.util.List;

import de.htwsaar.flashcards.model.FlashCard;

public class Database {

	private static List<FlashCard> flashCardTable;
	
	static {
		flashCardTable = FlashCardTable.getFlashCardList();
	}
	
	public static List<FlashCard> selectSternFromFlashcard() {
		return flashCardTable;
	}
	
}
