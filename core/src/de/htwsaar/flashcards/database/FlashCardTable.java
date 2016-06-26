package de.htwsaar.flashcards.database;

import java.util.ArrayList;
import java.util.List;

import de.htwsaar.flashcards.builder.ModelObjectBuilder;
import de.htwsaar.flashcards.model.FlashCard;

public class FlashCardTable {
	
	private static final int FLASHCARD_ID_1 = 1111;
	private static final String FLASHCARD_Q_1 = "Wie hieß der erste Bundeskanzler der Bundesrepublik Deutschland?";
	private static final String FLASHCARD_A_1 = "Konrad Adenauer";
	private static final int FLASHCARD_POS_1 = 0;
	
	private static final int FLASHCARD_ID_2 = 2222;
	private static final String FLASHCARD_Q_2 = "Herbst ist redensartlich, wenn Männer sich anziehen, als wäre Sommer, und Frauen, als wäre ...?";
	private static final String FLASHCARD_A_2 = "Winter";
	private static final int FLASHCARD_POS_2 = 0;
	
	private static final int FLASHCARD_ID_3 = 3333;
	private static final String FLASHCARD_Q_3 = "Welchen Teil des Körpers meint ein Arzt, wenn er vom Kolon spricht?";
	private static final String FLASHCARD_A_3 = "(Grimm-) Darm, Dickdarm";
	private static final int FLASHCARD_POS_3 = 0;
	
	private static final int FLASHCARD_ID_4 = 4444;
	private static final String FLASHCARD_Q_4 = "Welchem Land ist die Osterinsel politisch zugehörig?";
	private static final String FLASHCARD_A_4 = "Chile";
	private static final int FLASHCARD_POS_4 = 0;
	
	private static final int FLASHCARD_ID_5 = 5555;
	private static final String FLASHCARD_Q_5 = "Die Kaaba ist das zentrale Heiligtum welcher Religion?";
	private static final String FLASHCARD_A_5 = "Islam";
	private static final int FLASHCARD_POS_5 = 0;
	
	public static FlashCard getFlashCard1() {
		return ModelObjectBuilder.getFlashCardObject(FLASHCARD_ID_1, FLASHCARD_Q_1, FLASHCARD_A_1, FLASHCARD_POS_1);
	}
	
	public static FlashCard getFlashCard2() {
		return ModelObjectBuilder.getFlashCardObject(FLASHCARD_ID_2, FLASHCARD_Q_2, FLASHCARD_A_2, FLASHCARD_POS_2);
	}
	
	public static FlashCard getFlashCard3() {
		return ModelObjectBuilder.getFlashCardObject(FLASHCARD_ID_3, FLASHCARD_Q_3, FLASHCARD_A_3, FLASHCARD_POS_3);
	}
	
	public static FlashCard getFlashCard4() {
		return ModelObjectBuilder.getFlashCardObject(FLASHCARD_ID_4, FLASHCARD_Q_4, FLASHCARD_A_4, FLASHCARD_POS_4);
	}
	
	public static FlashCard getFlashCard5() {
		return ModelObjectBuilder.getFlashCardObject(FLASHCARD_ID_5, FLASHCARD_Q_5, FLASHCARD_A_5, FLASHCARD_POS_5);
	}
	
	public static List<FlashCard> getFlashCardList()  {
		List<FlashCard> flashCards = new ArrayList<FlashCard>();
		flashCards.add(getFlashCard1());
		flashCards.add(getFlashCard2());
		flashCards.add(getFlashCard3());
		flashCards.add(getFlashCard4());
		flashCards.add(getFlashCard5());
		return flashCards;
	}
}
