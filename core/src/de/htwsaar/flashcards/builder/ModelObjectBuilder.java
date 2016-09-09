package de.htwsaar.flashcards.builder;

import java.sql.Date;

import de.htwsaar.flashcards.model.FlashCard;
import de.htwsaar.flashcards.model.GameOption;
import de.htwsaar.flashcards.model.Stack;

public class ModelObjectBuilder {

	/*
	 * FlashCard 
	 */
	public static FlashCard getFlashCardObject(int cardId, String cardName, String cardQuestion, String cardAnswer, int boxCounter, int stackId, Date cardLastAccessDate ,
			  Date cardNextAccessDate ,	String cardPicture, int cardAsked, int cardAnswerCorrect) {
		return new FlashCard(cardId, cardName, cardQuestion, cardAnswer ,boxCounter ,stackId, cardLastAccessDate, cardNextAccessDate, cardPicture, cardAsked, cardAnswerCorrect);
	}
	
	public static FlashCard getFlashCardObject (String cardName, String cardQuestion, String cardAnswer, int stackId, String cardPicture, Date cardLastAccessDate,
			 Date cardNextAccessDate, int cardAsked, int cardAnswerCorrect) {
		return new FlashCard(cardName, cardQuestion,cardAnswer, stackId, cardPicture, cardLastAccessDate
				 , cardNextAccessDate, cardAsked, cardAnswerCorrect);
	}
	
	/*
	 * GameOption 
	 */
	public static GameOption getGameOptionObject (int id, String name, String description, int time, int boxOption, boolean sorted, int limit, boolean dateSensitive, int evalType) {
		return new GameOption(id, name, description, time, boxOption, sorted, limit, dateSensitive, evalType);
	}
	
	public static GameOption getGameOptionObject (int time, int boxOption, boolean sorted, int limit, boolean dateSensitive, int evalType) {
		return new GameOption(time, boxOption, sorted, limit, dateSensitive, evalType);
	}
	
	/*
	 * Stack
	 */
	public static Stack getStackObject(int stackId, String stackName, int typ, String subject, Date creationDate, Date lastEditDate) {
		return new Stack(stackId,stackName, typ, subject, creationDate, lastEditDate);
	}
	
	public static Stack getStackObject(String stackName, int typ, String subject) {
		return new Stack(stackName, typ, subject);
	}	
}
