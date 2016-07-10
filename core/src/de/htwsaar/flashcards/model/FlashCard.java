package de.htwsaar.flashcards.model;

import java.sql.Date;

/**
 * Daten-Container Klasse (Model) fuer die Karteikarten Entitaet.
 * 
 * @author mkohn & mfeick
 */
public class FlashCard implements Comparable<FlashCard> {

	private int cardId;
	private String cardName;
	private String cardQuestion;
	private String cardAnswer;
	private int boxCounter; // The phase in which the card is situated
	private int stackId;
	// private int owner;
	private Date lastAccessDate;
	private Date NextAccessDate;
	private String cardPicture;

	public FlashCard(String cardName, String cardQuestion, String cardAnswer, int stackId, String cardPicture, Date lastAccessDate
	 , Date NextAccessDate) {

		this.cardName = cardName;
		this.cardQuestion = cardQuestion;
		this.cardAnswer = cardAnswer;
		this.stackId = stackId;
		// this.owner = owner;
		this.lastAccessDate = lastAccessDate;
		this.NextAccessDate = NextAccessDate;
		this.cardPicture = cardPicture;
	}

	public FlashCard(int cardId, String cardName, String cardQuestion, String cardAnswer, int boxCounter, int stackId, Date lastAccessDate ,
			  Date NextAccessDate ,	String cardPicture)
	{
		this(cardName, cardQuestion, cardAnswer, stackId, cardPicture, NextAccessDate , lastAccessDate);
		this.cardId = cardId;
		this.boxCounter = boxCounter;
	}

	/*
	 * public int getOwner() { return owner; }
	 * 
	 * public void setOwner(int owner) { this.owner = owner; }
	 */

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

	public int getStackId() {
		return stackId;
	}

	public void setStackId(int stackId) {
		this.stackId = stackId;
	}

	public Date getLastAccessDate() {
		return lastAccessDate;
	}

	public void setLastAccessDate(Date lastAccessDate) {
		this.lastAccessDate = lastAccessDate;
	}

	public Date getNextAccessDate() {
		return NextAccessDate;
	}

	public void setNextAccessDate(Date nextAccessDate) {
		NextAccessDate = nextAccessDate;
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
				+ ", cardAnswer=" + cardAnswer + ", boxCounter=" + boxCounter + ", stackId=" + stackId
				+ ", lastAccessDate=" + lastAccessDate + ", NextAccessDate=" + NextAccessDate + ", cardPicture="
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
