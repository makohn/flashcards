package de.htwsaar.flashcards.ui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import de.htwsaar.flashcards.model.FlashCard;
import de.htwsaar.flashcards.properties.Dimensions;
import de.htwsaar.flashcards.properties.Messages;
import de.htwsaar.flashcards.util.FlashCardButtonFactory;
import de.htwsaar.flashcards.util.FlashCardConstants;
import de.htwsaar.flashcards.util.Handler;

/**
 * <code>VocabUIFactory</code> - Konkrete Fabrikimplementierung des Interfaces 
 * <code>StudyTypeUIFactory</code>. Erzeugt die Komponenten so, dass eine Evaluierung
 * mittels ActionListener erfolgen kann. Statt TextAreas werden hier TextFields fuer die
 * Darstellung der Frage/Antwort Bereiche gewaehlt.
 * 
 * @author Marek Kohn, Marco Becker
 */
public class VocabUIFactory implements StudyTypeUIFactory {
	
	private JTextField txtVocab;
	private JTextField txtAnswer;
	private JButton btnShowAnswer;
	private JTextField txtShowAnswer;
	private JButton btnNextQuestion;
	
	private FlashCard currentCard;
	private boolean answer;
	
	@Override
	public JPanel createQuestionPanel(boolean editable) {
		JPanel pnlFlashcard = new JPanel();
		pnlFlashcard.setOpaque(false);
		pnlFlashcard.setBorder(FlashCardConstants.OUTER_CARD_BORDER);
		txtVocab = new JTextField();
		txtVocab.setFont(Dimensions.getFont("vocab.font_vocab"));
		txtVocab.setEditable(false);
		txtVocab.setFocusable(false);
		txtVocab.setPreferredSize(Dimensions.getDimension("vocab.dim_vocab_field"));
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
		txtAnswer.setFont(Dimensions.getFont("vocab.font_vocab"));
		txtAnswer.setPreferredSize(Dimensions.getDimension("vocab.dim_vocab_field"));
		txtAnswer.setHorizontalAlignment(SwingConstants.CENTER);
		pnlAnswer.add(txtAnswer);
		
		JPanel pnlShow = new JPanel(new CardLayout());
		pnlShow.setOpaque(false);
		btnShowAnswer = FlashCardButtonFactory.createColouredButton(Messages.getString("answer"), new Color(0, 163, 204));
		pnlShow.add(btnShowAnswer);
		txtShowAnswer = new JTextField();
		txtShowAnswer.setFont(Dimensions.getFont("vocab.font_vocab"));
		txtShowAnswer.setHorizontalAlignment(SwingConstants.CENTER);
		txtShowAnswer.setPreferredSize(Dimensions.getDimension("vocab.dim_vocab_field"));
		pnlShow.add(txtShowAnswer);
		pnlAnswer.setMaximumSize(Dimensions.getDimension("vocab.dim_answ_panel"));
		pnlAnswer.add(pnlShow);
		return pnlAnswer;
	}

	@Override
	public JPanel createEvaluationPanel() {
		JPanel pnlEval = new JPanel();
		pnlEval.setOpaque(false);
		btnNextQuestion = FlashCardButtonFactory.createColouredButton(Messages.getString("next"), new Color(0, 163, 204));
		btnNextQuestion.setEnabled(false);
		pnlEval.add(btnNextQuestion);
		return pnlEval;
	}

	@Override
	public void setListeners(Handler<FlashCard> nextQuestion,
							 Handler<Void> stopTimer) {
		
		btnShowAnswer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				stopTimer.call(true);
				btnShowAnswer.setVisible(false);
				txtShowAnswer.setVisible(true);
				btnNextQuestion.setEnabled(true);
				txtAnswer.setEditable(false);
				if(answer = txtAnswer.getText().trim().equals(currentCard.getCardAnswer().trim())) {
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
					updateCard(nextQuestion.call(answer));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		
		txtAnswer.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent evt) {
				boolean answerVisible = txtShowAnswer.isVisible();
				if(answerVisible) btnNextQuestion.doClick();
				else if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
	                btnShowAnswer.doClick();
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
		return Dimensions.getDimension("vocab.dim_frame");
	}

	@Override
	public Insets[] getSpacing() {
		return new Insets[] { Dimensions.getInsets("vocab.insets_info_1"),
							  Dimensions.getInsets("vocab.insets_info_2"),
							  Dimensions.getInsets("vocab.insets_info_3")
		};
	}

}
