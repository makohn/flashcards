package de.htwsaar.flashcards.model;

import java.util.Date;

/**
 * Daten-Container Klasse (Model) fuer die Karteikarten Entitaet.
 * 
 * @author mkohn & Feick Martin
 */

public class FlashCard implements Comparable<FlashCard> {

	private int cardId;
	private String cardName;
	private String cardQuestion;
	private String cardAnswer;
	private int boxCounter; // The phase in which the card is situated
	private int stackId;
	private Date cardLastAccessDate;
	private Date cardNextAccessDate;
	private String cardPicture;
	private int cardAsked;
	private int cardAnswerCorrect;

	public FlashCard(String cardName, String cardQuestion, String cardAnswer, int stackId, String cardPicture, Date cardLastAccessDate
	 , Date cardNextAccessDate, int cardAsked, int cardAnswerCorrect) {

		this.cardName = cardName;
		this.cardQuestion = cardQuestion;
		this.cardAnswer = cardAnswer;
		this.stackId = stackId;
		this.cardLastAccessDate = cardLastAccessDate;
		this.cardNextAccessDate = cardNextAccessDate;
		this.cardPicture = cardPicture;
		this.cardAsked = cardAsked;
		this.cardAnswerCorrect = cardAnswerCorrect;
	}

	public FlashCard(int cardId, String cardName, String cardQuestion, String cardAnswer, int boxCounter, int stackId, Date cardLastAccessDate ,
			  Date cardNextAccessDate ,	String cardPicture, int cardAsked, int cardAnswerCorrect) {
		
		this(cardName, cardQuestion, cardAnswer, stackId, cardPicture, cardNextAccessDate , cardLastAccessDate, cardAsked, cardAnswerCorrect);
		this.cardId = cardId;
		this.boxCounter = boxCounter;
	}
	
	public FlashCard() {
		
	}

	public void incrementBoxCounter() {
		this.boxCounter++;
	}

	public int getCardId() {
		return cardId;
	}

	public void setCardId(int cardId) {
		this.cardId = cardId;
	}

	public String getCardName() {
		return cardName;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	public String getCardQuestion() {
		return cardQuestion;
	}

	public void setCardQuestion(String cardQuestion) {
		this.cardQuestion = cardQuestion;
	}

	public String getCardAnswer() {
		return cardAnswer;
	}

	public void setCardAnswer(String cardAnswer) {
		this.cardAnswer = cardAnswer;
	}

	public int getBoxCounter() {
		return boxCounter;
	}

	public void setBoxCounter(int boxCounter) {
		this.boxCounter = boxCounter;
	}

	public int getStack() {
		return stackId;
	}

	public void setStack(int stackId) {
		this.stackId = stackId;
	}

	public Date getCardLastAccessDate() {
		return cardLastAccessDate;
	}

	public void setCardLastAccessDate(Date cardLastAccessDate) {
		this.cardLastAccessDate = cardLastAccessDate;
	}

	public Date getCardNextAccessDate() {
		return cardNextAccessDate;
	}

	public void setCardNextAccessDate(Date cardNextAccessDate) {
		this.cardNextAccessDate = cardNextAccessDate;
	}

	public String getCardPicture() {
		return cardPicture;
	}

	public void setCardPicture(String cardPicture) {
		this.cardPicture = cardPicture;
	}
	
	public int getStackId() {
		return stackId;
	}

	public void setStackId(int stackId) {
		this.stackId = stackId;
	}

	public int getCardAsked() {
		return cardAsked;
	}

	public void setCardAsked(int cardAsked) {
		this.cardAsked = cardAsked;
	}

	public int getCardAnswerCorrect() {
		return cardAnswerCorrect;
	}

	public void setCardAnswerCorrect(int cardAnswerCorrect) {
		this.cardAnswerCorrect = cardAnswerCorrect;
	}

	@Override
	public String toString() {
		return "FlashCard [cardId=" + cardId + ", cardName=" + cardName + ", cardQuestion=" + cardQuestion
				+ ", cardAnswer=" + cardAnswer + ", boxCounter=" + boxCounter + ", stackId=" + stackId
				+ ", cardLastAccessDate=" + cardLastAccessDate + ", cardNextAccessDate=" + cardNextAccessDate
				+ ", cardPicture=" + cardPicture + ", cardAsked=" + cardAsked + ", cardAnswerCorrect="
				+ cardAnswerCorrect + "]";
	}
	/**
	 * Dient dem Vergleichen von Karteikarten anhand deren aktuellem Box-Count.
	 */
	@Override
	public int compareTo(FlashCard o) {
		if (this.getBoxCounter() <= o.getBoxCounter())
			return -1;
		else if (this.getBoxCounter() > o.getBoxCounter())
			return 1;

		return 0;
	}
}