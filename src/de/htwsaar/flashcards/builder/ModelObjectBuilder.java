package de.htwsaar.flashcards.builder;

import de.htwsaar.flashcards.model.FlashCard;

public class ModelObjectBuilder {
	
	public static FlashCard getFlashCardObject(int id, String quest, String ans, int phase) {
		return new FlashCard(id, quest, ans, phase);
	}
}
