package de.htwsaar.flashcards.model;

import java.sql.Date;

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
	private String stack;
	private Date cardLastAccessDate;
	private Date cardNextAccessDate;
	private String cardPicture;

	public FlashCard(String cardName, String cardQuestion, String cardAnswer, String stack, String cardPicture, Date cardLastAccessDate
	 , Date cardNextAccessDate) {

		this.cardName = cardName;
		this.cardQuestion = cardQuestion;
		this.cardAnswer = cardAnswer;
		this.stack = stack;
		// this.owner = owner;
		this.cardLastAccessDate = cardLastAccessDate;
		this.cardNextAccessDate = cardNextAccessDate;
		this.cardPicture = cardPicture;
	}

	public FlashCard(int cardId, String cardName, String cardQuestion, String cardAnswer, int boxCounter, String stack, Date cardLastAccessDate ,
			  Date cardNextAccessDate ,	String cardPicture)
	{
		this(cardName, cardQuestion, cardAnswer, stack, cardPicture, cardNextAccessDate , cardLastAccessDate);
		this.cardId = cardId;
		this.boxCounter = boxCounter;
	}

	/*
	 * public int getOwner() { return owner; }
	 * 
	 * public void setOwner(int owner) { this.owner = owner; }
	 */
	
	public FlashCard() {
		// TODO Auto-generated constructor stub
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

	public String getStack() {
		return stack;
	}

	public void setStack(String stack) {
		this.stack = stack;
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



	@Override
	public String toString() {
		return "FlashCard [cardId=" + cardId + ", cardName=" + cardName + ", cardQuestion=" + cardQuestion
				+ ", cardAnswer=" + cardAnswer + ", boxCounter=" + boxCounter + ", stack=" + stack
				+ ", cardLastAccessDate=" + cardLastAccessDate + ", cardNextAccessDate=" + cardNextAccessDate + ", cardPicture="
				+ cardPicture + "]";
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
