package de.htwsaar.flashcards.model;

/**
 * Basic Model Class representing a Flashcard Entity.
 * @author mkohn
 */
public class FlashCard implements Comparable<FlashCard>{

	private int id;
	private String question;
	private String answer;
	private int phase; //The phase in which the card is situated
	
	public FlashCard(int id, String question, String answer, int phase) {
		this.id = id;
		this.question = question;
		this.answer = answer;
		this.phase = phase;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public int getPhase() {
		return phase;
	}

	public void setPhase(int phase) {
		this.phase = phase;
	}
	
	public void incrementPhase() {
		this.phase++;
	}
	
	@Override
	public String toString() {
		return "FlashCard [id=" + id + ", question=" + question + ", answer=" + answer + ", phase=" + phase + "]";
	}

	@Override
	public int compareTo(FlashCard o) {
		if (this.getPhase() <= o.getPhase())
			return -1;
		else if (this.getPhase() > o.getPhase())
			return 1;
			
		return 0;
	}
}
