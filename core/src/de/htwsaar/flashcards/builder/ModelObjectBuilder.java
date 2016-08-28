package de.htwsaar.flashcards.builder;

import java.sql.Date;

import de.htwsaar.flashcards.model.FlashCard;

public class ModelObjectBuilder {

	public static FlashCard getFlashCardObject(int cardId, String cardName, String cardQuestion, String cardAnswer, int boxCounter, int stackId, Date cardLastAccessDate ,
			  Date cardNextAccessDate ,	String cardPicture, int cardAsked, int cardAnswerCorrect) {
		return new FlashCard(cardId, cardName, cardQuestion, cardAnswer ,boxCounter ,stackId, cardLastAccessDate, cardNextAccessDate, cardPicture, cardAsked, cardAnswerCorrect);
	}
}
