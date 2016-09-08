package de.htwsaar.flashcards.ui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Callable;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import de.htwsaar.flashcards.model.FlashCard;
import de.htwsaar.flashcards.properties.Messages;
import de.htwsaar.flashcards.util.ButtonFactory;
import de.htwsaar.flashcards.util.FlashCardConstants;

public class VocabUIFactory implements StudyTypeUIFactory {

	private static final Insets[] INSETS_INFO = {new Insets(0, 10, 0, 60), new Insets(0, 15, 0, 0), 
				new Insets(5, 80, 0, 0)};
	
	private JTextField txtVocab;
	private JTextField txtAnswer;
	private JButton btnShowAnswer;
	private JTextField txtShowAnswer;
	private JButton btnNextQuestion;
	
	private FlashCard currentCard;
	
	@Override
	public JPanel createQuestionPanel(boolean editable) {
		JPanel pnlFlashcard = new JPanel();
		pnlFlashcard.setOpaque(false);
		pnlFlashcard.setBorder(FlashCardConstants.OUTER_CARD_BORDER);
		txtVocab = new JTextField();
		txtVocab.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtVocab.setEditable(false);
		txtVocab.setFocusable(false);
		txtVocab.setPreferredSize(new Dimension(400, 50));
		txtVocab.setHorizontalAlignment(SwingConstants.CENTER);
		pnlFlashcard.add(txtVocab);
		return pnlFlashcard;
	}

	@Override
	public JPanel createAnswerPanel(boolean editable) {
		JPanel pnlAnswer = new JPanel(new GridLayout(2, 1));
		pnlAnswer.setOpaque(false);
		pnlAnswer.setBorder(FlashCardConstants.OUTER_CARD_BORDER);
		txtAnswer = new JTextField();
		txtAnswer.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtAnswer.setPreferredSize(new Dimension(400, 50));
		txtAnswer.setHorizontalAlignment(SwingConstants.CENTER);
		pnlAnswer.add(txtAnswer);
		
		JPanel pnlShow = new JPanel(new CardLayout());
		pnlShow.setOpaque(false);
		btnShowAnswer = ButtonFactory.createColouredButton(Messages.getString("answer"), new Color(0, 163, 204));
		pnlShow.add(btnShowAnswer);
		txtShowAnswer = new JTextField();
		txtShowAnswer.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtShowAnswer.setHorizontalAlignment(SwingConstants.CENTER);
		txtShowAnswer.setPreferredSize(new Dimension(400, 50));
		pnlShow.add(txtShowAnswer);
		pnlAnswer.setMaximumSize(new Dimension(420, 100));
		pnlAnswer.add(pnlShow);
		return pnlAnswer;
	}

	@Override
	public JPanel createEvaluationPanel() {
		JPanel pnlEval = new JPanel();
		pnlEval.setOpaque(false);
		btnNextQuestion = ButtonFactory.createColouredButton("Weiter", new Color(0, 163, 204));
		btnNextQuestion.setEnabled(false);
		pnlEval.add(btnNextQuestion);
		return pnlEval;
	}

	@Override
	public void setListeners(Callable<FlashCard> handler) {
		
		btnShowAnswer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnShowAnswer.setVisible(false);
				txtShowAnswer.setVisible(true);
				btnNextQuestion.setEnabled(true);
				txtAnswer.setEditable(false);
				if(txtAnswer.getText().trim().equals(currentCard.getCardAnswer().trim())) {
					txtAnswer.setBackground(new Color(217, 242, 217));
				}
				else {
					txtAnswer.setBackground(new Color(255, 204, 204));
				}
			}
		});
		
		btnNextQuestion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnShowAnswer.setVisible(true);
				txtShowAnswer.setVisible(false);
				txtAnswer.setEditable(true);
				btnNextQuestion.setEnabled(false);
				txtAnswer.setBackground(Color.white);
				txtAnswer.setText("");
				try {
					updateCard(handler.call());
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
	}

	@Override
	public void updateCard(FlashCard newCard) {
		txtVocab.setText(newCard.getCardQuestion());
		txtShowAnswer.setText(newCard.getCardAnswer());
		currentCard = newCard;
	}
	
	@Override
	public Dimension getFrameSize() {
		return new Dimension(450, 580);
	}

	@Override
	public Insets[] getSpacing() {
		return INSETS_INFO;
	}

}
