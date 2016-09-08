package de.htwsaar.flashcards.ui;

import java.awt.Dimension;
import java.awt.Insets;

import javax.swing.JPanel;

import de.htwsaar.flashcards.model.FlashCard;
import de.htwsaar.flashcards.util.Handler;

public interface StudyTypeUIFactory {
	
	public  JPanel 		createQuestionPanel(boolean editable);
	public  JPanel 		createAnswerPanel(boolean editable);
	public  JPanel 		createEvaluationPanel();
	public  void   		setListeners(Handler<FlashCard> nextQuestion,
									 Handler<Void> stopTimer);
	public  void   		updateCard(FlashCard newCard);
	public  Dimension 	getFrameSize();
	public  Insets[]	getSpacing();
}
