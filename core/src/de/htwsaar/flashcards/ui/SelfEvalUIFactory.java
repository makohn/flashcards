package de.htwsaar.flashcards.ui;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import de.htwsaar.flashcards.model.FlashCard;
import de.htwsaar.flashcards.properties.Messages;
import de.htwsaar.flashcards.util.FlashCardButtonFactory;
import de.htwsaar.flashcards.util.FlashCardConstants;
import de.htwsaar.flashcards.util.FlashCardUtils;
import de.htwsaar.flashcards.util.Handler;
import de.htwsaar.flashcards.util.size.SelfEvalUIFactorySizes;

/**
 * <code>SelfEvalUIFactory</code> - Konkrete Fabrikimplementierung des Interfaces 
 * <code>StudyTypeUIFactory</code>. Erzeugt die Komponenten so, dass eine Evaluierung
 * mittels dafuer vorgesehener Buttons erfolgen kann.
 * 
 * @author Marek Kohn, Marco Becker
 */

public class SelfEvalUIFactory implements StudyTypeUIFactory {
	
	private static final ImageIcon ICN_CORRECT = new ImageIcon("res/images/true.png");
	private static final ImageIcon ICN_INCORRECT = new ImageIcon("res/images/false.png");
	 
	private JTextArea txtCard;
	private JScrollPane scrlCard;
	
	private JTextArea txtAnswer;
	private JScrollPane scrlAnswer;
	
	private JButton btnCorrect;
	private JButton btnInCorrect;
	private JButton btnShowAnswer;

	
	@Override
	public JPanel createQuestionPanel(boolean editable) {
		JPanel pnlFlashcard = new JPanel();
		pnlFlashcard.setLayout(new CardLayout());
		pnlFlashcard.setOpaque(false);
		pnlFlashcard.setBorder(FlashCardConstants.OUTER_CARD_BORDER);
		txtCard = FlashCardUtils.createCardTextArea(false);
		scrlCard = new JScrollPane(txtCard);
		scrlCard.setBorder(FlashCardConstants.INNER_CARD_BORDER);
		pnlFlashcard.add(scrlCard);
		return pnlFlashcard;
	}

	@Override
	public JPanel createAnswerPanel(boolean editable) {
		JPanel pnlAnswer = new JPanel(new CardLayout());
		pnlAnswer.setOpaque(false);
		pnlAnswer.setBorder(FlashCardConstants.OUTER_CARD_BORDER);
		btnShowAnswer = FlashCardButtonFactory.createColouredButton(Messages.getString("answer"), FlashCardConstants.COLOR_FOREGROUND);
		pnlAnswer.add(btnShowAnswer);
		txtAnswer = FlashCardUtils.createCardTextArea(false);
		scrlAnswer = new JScrollPane(txtAnswer);
		scrlAnswer.setOpaque(false);
		scrlAnswer.setVisible(false);
		scrlAnswer.setBorder(FlashCardConstants.INNER_CARD_BORDER);
		pnlAnswer.add(scrlAnswer);
		return pnlAnswer;
	}

	@Override
	public JPanel createEvaluationPanel() {
		JPanel pnlEval = new JPanel(new GridBagLayout());
		pnlEval.setOpaque(false);
		GridBagConstraints cos = new GridBagConstraints();
		btnCorrect = FlashCardButtonFactory.createImageButton(ICN_CORRECT);
		btnCorrect.setEnabled(false);
		cos.gridx = 0;
		cos.gridy = 2;
		cos.insets = SelfEvalUIFactorySizes.INSETS_EVAL_PANEL_R;
		pnlEval.add(btnCorrect, cos);
		btnInCorrect = FlashCardButtonFactory.createImageButton(ICN_INCORRECT);
		btnInCorrect.setEnabled(false);
		cos.gridx = 2;
		cos.gridy = 2;
		cos.insets = SelfEvalUIFactorySizes.INSETS_EVAL_PANEL_L;
		pnlEval.add(btnInCorrect, cos);
		return pnlEval;
	}
	
	@Override
	public void setListeners(Handler<FlashCard> nextQuestion, 
							 Handler<Void> stopTimer ) {

		btnShowAnswer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stopTimer.call(true);
				btnCorrect.setEnabled(true);
				btnInCorrect.setEnabled(true);
				btnShowAnswer.setVisible(false);
				scrlAnswer.setVisible(true);
			}
		});

		btnCorrect.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				nextQuestion(nextQuestion, true);
			}
		});

		btnInCorrect.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				nextQuestion(nextQuestion, false);	
			}
		});
	}
	
	private void nextQuestion(Handler<FlashCard> handler, boolean arg) {
		btnCorrect.setEnabled(false);
	    btnInCorrect.setEnabled(false);
	    scrlAnswer.setVisible(false);
	    btnShowAnswer.setVisible(true);
	    try {
			updateCard(handler.call(arg));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateCard(FlashCard newCard) {
		 txtCard.setText(newCard.getCardQuestion());
		 txtAnswer.setText(newCard.getCardAnswer());
	}

	@Override
	public Dimension getFrameSize() {
		return SelfEvalUIFactorySizes.DIM_FRAME;
	}

	@Override
	public Insets[] getSpacing() {
		return SelfEvalUIFactorySizes.INSETS_INFO;
	}
	

}
