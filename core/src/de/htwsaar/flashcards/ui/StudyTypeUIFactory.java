package de.htwsaar.flashcards.ui;

import java.awt.Dimension;
import java.awt.Insets;

import javax.swing.JPanel;

import de.htwsaar.flashcards.model.FlashCard;
import de.htwsaar.flashcards.util.Handler;

/**
 * <code>StudyTypeUIFactory</code> - Schnittstelle fuer die Erzeugung von GUI-Komponenten fuer die
 * StudyFrame Klasse. Erzeugt je nach Typ, die Komponenten in unterschiedlicher
 * Ausfuehrung (Groesse, Bestandteile, ...) und Funktion (ActionListener).
 * 
 * Bisher wird unterschieden zwischen:
 * 		- <code>SelfEvalUIFactory</code> (Selbstevaluierung)
 * 		- <code>VocabUIFactory</code>    (Vokabeln, Evalierung durch ActionListener)
 * 
 * @author Marek Kohn
 * @see SelfEvalUIFactory, VocabUIFactory
 */
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
