package de.htwsaar.flashcards.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import de.htwsaar.flashcards.engine.GameEngineImpl;
import de.htwsaar.flashcards.engine.interfaces.GameEngine;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = -1710201380532955329L;
	
	private JTextArea txtCard;
	private JPanel pnlEval;
	private JButton btnCorrect;
	private JButton btnInCorrect;
	private JButton btnShowAnswer;
	
	private GameEngine engine;
	
	public MainFrame() {
		
		engine = new GameEngineImpl();
		
		setTitle("MainFrame");
		setSize(500,500);
		setLayout(new BorderLayout());
		
		initComponents();
		
		add(txtCard, BorderLayout.CENTER);
		add(pnlEval, BorderLayout.SOUTH);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	private void initComponents() {
		
		// Initialize the Q/A Area
		txtCard = new JTextArea(engine.getNextCard().getQuestion());
		txtCard.setLineWrap(true);
		txtCard.setWrapStyleWord(true);
		txtCard.setEditable(false);
		txtCard.setOpaque(false);
		
		// Initialize the evaluation panel
		pnlEval = new JPanel();
		btnCorrect = new JButton("Correct!");
		btnInCorrect = new JButton("False!");
		btnShowAnswer = new JButton("Show Answer!");
		pnlEval.add(btnCorrect);
		pnlEval.add(btnInCorrect);
		pnlEval.add(btnShowAnswer);
		
		btnCorrect.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				engine.evaluateAnswer(true);
				txtCard.setText(engine.getNextCard().getQuestion());
			}
		});
		
		btnShowAnswer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				txtCard.setText(engine.getCurrentCard().getAnswer());
			}
		});
	}
	
	
}
