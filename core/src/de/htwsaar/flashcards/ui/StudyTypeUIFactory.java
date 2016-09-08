package de.htwsaar.flashcards.ui;

import java.awt.Dimension;
import java.awt.Insets;
import java.util.concurrent.Callable;

import javax.swing.JPanel;

import de.htwsaar.flashcards.model.FlashCard;

public interface StudyTypeUIFactory {
	
	public  JPanel 		createQuestionPanel(boolean editable);
	public  JPanel 		createAnswerPanel(boolean editable);
	public  JPanel 		createEvaluationPanel();
	public  void   		setListeners(Callable<FlashCard> handler);
	public  void   		updateCard(FlashCard newCard);
	public  Dimension 	getFrameSize();
	public  Insets[]	getSpacing();
}
