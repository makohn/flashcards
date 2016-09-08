package de.htwsaar.flashcards.ui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Callable;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import de.htwsaar.flashcards.model.FlashCard;
import de.htwsaar.flashcards.properties.Messages;
import de.htwsaar.flashcards.util.ButtonFactory;
import de.htwsaar.flashcards.util.FlashCardConstants;
import de.htwsaar.flashcards.util.FlashCardUtils;

public class SelfEvalUIFactory implements StudyTypeUIFactory {
	
	private static final ImageIcon ICN_CORRECT = new ImageIcon("res/images/true.png");
	private static final ImageIcon ICN_INCORRECT = new ImageIcon("res/images/false.png");
	private static final Insets[] INSETS_INFO = {new Insets(0, 10, 0, 120), new Insets(0, 45, 0, 0), 
				new Insets(5, 160, 0, 0)};
	 
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
		btnShowAnswer = ButtonFactory.createColouredButton(Messages.getString("answer"), new Color(0, 163, 204));
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
		btnCorrect = ButtonFactory.createImageButton(ICN_CORRECT);
		btnCorrect.setEnabled(false);
		cos.gridx = 0;
		cos.gridy = 2;
		cos.insets = new Insets(0, 0, 0, 100);
		pnlEval.add(btnCorrect, cos);
		btnInCorrect = ButtonFactory.createImageButton(ICN_INCORRECT);
		btnInCorrect.setEnabled(false);
		cos.gridx = 2;
		cos.gridy = 2;
		cos.insets = new Insets(0, 100, 0, 0);
		pnlEval.add(btnInCorrect, cos);
		return pnlEval;
	}
	
	@Override
	public void setListeners(Callable<FlashCard> handler) {

		btnShowAnswer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnCorrect.setEnabled(true);
				btnInCorrect.setEnabled(true);
				btnShowAnswer.setVisible(false);
				scrlAnswer.setVisible(true);
			}
		});

		btnCorrect.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				nextQuestion(handler);
			}
		});

		btnInCorrect.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				nextQuestion(handler);	
			}
		});
	}
	
	private void nextQuestion(Callable<FlashCard> handler) {
		btnCorrect.setEnabled(false);
	    btnInCorrect.setEnabled(false);
	    scrlAnswer.setVisible(false);
	    btnShowAnswer.setVisible(true);
	    try {
			updateCard(handler.call());
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
		return new Dimension(650, 780);
	}

	@Override
	public Insets[] getSpacing() {
		return INSETS_INFO;
	}
	

}
