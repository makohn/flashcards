package de.htwsaar.flashcards.model;

/**
 * Basic Model Class representing a Flashcard Entity.
 * 
 * @author mkohn & mfeick
 */
public class FlashCard implements Comparable<FlashCard> {

	private int cardId;
	private String cardName;
	private String cardQuestion;
	private String cardAAnswer;
	private int boxCounter;  // The phase in which the card is situated
	private int stackId;
	// private int owner;
	private String cardPicture;

	public FlashCard(int cardId, String cardName, String cardQuestion, String cardAAnswer, int boxCounter, int stackId,
			String cardPicture) {
		super();
		this.cardId = cardId;
		this.cardName = cardName;
		this.cardQuestion = cardQuestion;
		this.cardAAnswer = cardAAnswer;
		this.boxCounter = boxCounter;
		this.stackId = stackId;
		this.cardPicture = cardPicture;
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

	public String getCardAAnswer() {
		return cardAAnswer;
	}

	public void setCardAAnswer(String cardAAnswer) {
		this.cardAAnswer = cardAAnswer;
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

	public String getCardPicture() {
		return cardPicture;
	}

	public void setCardPicture(String cardPicture) {
		this.cardPicture = cardPicture;
	}

	@Override
	public String toString() {
		return "FlashCard [cardId=" + cardId + ", cardName=" + cardName + ", cardQuestion=" + cardQuestion
				+ ", cardAAnswer=" + cardAAnswer + ", boxCounter=" + boxCounter + ", stackId=" + stackId
				+ ", cardPicture=" + cardPicture + "]";
	}

	@Override
	public int compareTo(FlashCard o) {
		if (this.getBoxCounter() <= o.getBoxCounter())
			return -1;
		else if (this.getBoxCounter() > o.getBoxCounter())
			return 1;

		return 0;
	}


}
