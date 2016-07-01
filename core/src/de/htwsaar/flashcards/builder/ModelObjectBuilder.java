package de.htwsaar.flashcards.builder;

import de.htwsaar.flashcards.model.FlashCard;

public class ModelObjectBuilder {

	public static FlashCard getFlashCardObject(int cardId, String cardName, String cardQuestion, String cardAnswer, int boxCounter, int stackId,
			String cardPicture) {
		return new FlashCard (cardId, cardName, cardQuestion, cardAnswer, boxCounter, stackId,
				cardPicture);
	}
}
