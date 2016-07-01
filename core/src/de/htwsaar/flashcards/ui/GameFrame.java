package de.htwsaar.flashcards.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.border.Border;

import de.htwsaar.flashcards.engine.GameEngineImpl;
import de.htwsaar.flashcards.engine.interfaces.GameEngine;

public class GameFrame extends JFrame {

	private static final long serialVersionUID = -1710201380532955329L;
	
	private static final Font FONT_CARD = new Font("SansSerif", Font.PLAIN, 20);
	private static final Font FONT_COUNTER = new Font("SansSerif", Font.ITALIC, 20);
	
	private JTextArea txtCard;
	private JPanel pnlInfo;
	private JLabel lblCardCount;
	private JPanel pnlEval;
	private JButton btnCorrect;
	private JButton btnInCorrect;
	private JButton btnShowAnswer;
	private JFrame self;
	
	private GameEngine engine;
	
	private int count = 1;
	private boolean btnShowClicked = false;
	
	public GameFrame() throws ClassNotFoundException {
		
		engine = new GameEngineImpl();
		
		setTitle("GameFrame");
		setSize(500,500);
		setLayout(new BorderLayout());
		
		initComponents();
		
		add(pnlInfo, BorderLayout.NORTH);
		add(txtCard, BorderLayout.CENTER);
		add(pnlEval, BorderLayout.SOUTH);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		self = this;
	}
	
	private void initComponents() {
		//Initialize the Info Area
		pnlInfo = new JPanel(new FlowLayout(FlowLayout.CENTER));
		lblCardCount = new JLabel("1");
		lblCardCount.setFont(FONT_COUNTER);
		lblCardCount.setForeground(Color.BLUE);
		pnlInfo.add(lblCardCount);
		
		// Initialize the Q/A Area
		txtCard = new JTextArea(engine.getCurrentCard().getCardQuestion());
		txtCard.setLineWrap(true);
		txtCard.setWrapStyleWord(true);
		txtCard.setEditable(false);
		txtCard.setFont(FONT_CARD);
		txtCard.setBackground(Color.WHITE);
		Border innerBorder = BorderFactory.createLineBorder(Color.GRAY);
		Border outerBorder = BorderFactory.createLineBorder(UIManager.getColor("Panel.background"), 10);
		txtCard.setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
		
		// Initialize the evaluation panel
		pnlEval = new JPanel();
		ImageIcon icn_correct = new ImageIcon("res/true.png");
		ImageIcon icn_false = new ImageIcon("res/false.png");
		ImageIcon icn_rev = new ImageIcon("res/reverse.png");
		btnCorrect = new JButton(icn_correct);
		btnInCorrect = new JButton(icn_false);
		btnShowAnswer = new JButton(icn_rev);
		btnCorrect.setContentAreaFilled(false);
		btnCorrect.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		btnInCorrect.setContentAreaFilled(false);
		btnInCorrect.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		btnShowAnswer.setContentAreaFilled(false);
		btnShowAnswer.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		pnlEval.add(btnCorrect);
		pnlEval.add(btnInCorrect);
		pnlEval.add(btnShowAnswer);
		
		btnCorrect.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if (engine.evaluateAnswer(true)) {
					txtCard.setText(engine.getCurrentCard().getCardQuestion());
					count++;
					lblCardCount.setText("" + count);
				}
				else {
					JOptionPane.showMessageDialog(self, "Herzlichen Glückwunsch, du hast es für heute geschafft!");
					self.dispatchEvent(new WindowEvent(self, WindowEvent.WINDOW_CLOSING));
				}	
			}
		});
		
		btnShowAnswer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (btnShowClicked) {
					txtCard.setText(engine.getCurrentCard().getCardQuestion());
					btnShowClicked = false;
				} else {
					txtCard.setText(engine.getCurrentCard().getCardQuestion());
					btnShowClicked = true;
				}
			}
		});
	}	
}
