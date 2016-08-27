package de.htwsaar.flashcards.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.Border;

import de.htwsaar.flashcards.engine.interfaces.GameEngine;
import de.htwsaar.flashcards.ui.component.GradientPanel;
import de.htwsaar.flashcards.util.ButtonFactory;

public class FrmEditStack {

	private static final String FRAME_TITLE = "Stack bearbeiten";
	private static final String LABELSTRING_QUESTION = "--- Frage ---";
	private static final String LABELSTRING_ANSWER = "--- Antwort ---";
	
	private static final ImageIcon ICN_ARROW_RIGHT = new ImageIcon("res/images/arrow-right.png");
	private static final ImageIcon ICN_ARROW_LEFT = new ImageIcon("res/images/arrow-left.png");
	private static final ImageIcon ICN_ADD_PICTURE= new ImageIcon("res/images/add-picture.png");
	
	private static final Border INNER_CARD_BORDER = BorderFactory.createLineBorder(Color.GRAY);
	private static final Border OUTER_CARD_BORDER = BorderFactory.createEmptyBorder(10,10,10,10);
	
	// Labels
	//private JLabel lblStackName;
	private JLabel lblQuestion;
	private JLabel lblAnswer;
	private JLabel lblCardName;

	// Input
	private JTextArea txtQuestion;
	private JTextArea txtAnswer;

	// Buttons
	private JButton btnCardForward;
	private JButton btnCardBackward;
	private JButton btnAddPicture;
	private JButton btnCancel;
	private JButton btnDeleteCurrentCard;
	private JButton btnSaveStack;

	// Panels
	private JPanel pnlNavigation;
	private JPanel pnlSaveDelete;
	private JPanel pnlQuestion;
	private JPanel pnlAnswer;
	
	private GameEngine engine;
	
	private JFrame editStackWindow;
	
	public FrmEditStack(GameEngine engine) {
		this.engine = engine;
		editStackWindow = new JFrame();
		
		initNavigationArea();
		initQuestionArea();
		initAnswerArea();
		initSaveDeleteArea();
		initFrame();
		initListeners();
	}
	
	private void initNavigationArea() {
		lblCardName = new JLabel(engine.getCurrentCard().getCardName());
		btnCardForward = new JButton(ICN_ARROW_RIGHT);
		btnCardForward.setContentAreaFilled(false);
		btnCardForward.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		btnCardBackward = new JButton(ICN_ARROW_LEFT);
		btnCardBackward.setContentAreaFilled(false);
		btnCardBackward.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		
		pnlNavigation = new JPanel();
		pnlNavigation.setOpaque(false);
		pnlNavigation.setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		gc.gridx = 0;
		pnlNavigation.add(btnCardBackward, gc);
		gc.gridx = 1;
		gc.insets = new Insets(0, 40, 0, 40);
		pnlNavigation.add(lblCardName, gc);
		gc.insets = new Insets(0, 0, 0, 0);
		gc.gridx = 2;
		pnlNavigation.add(btnCardForward, gc);	
	}
	
	private void initQuestionArea() {
		pnlQuestion = new JPanel();
		pnlQuestion.setOpaque(false);
		pnlQuestion.setLayout(new BoxLayout(pnlQuestion, BoxLayout.PAGE_AXIS));
		pnlQuestion.setBorder(OUTER_CARD_BORDER);
		
		lblQuestion = new JLabel(LABELSTRING_QUESTION);
		lblQuestion.setAlignmentX(Component.CENTER_ALIGNMENT);
		txtQuestion = new JTextArea(10,20);
		txtQuestion.setLineWrap(true);
		txtQuestion.setText(engine.getCurrentCard().getCardQuestion());
		
		btnAddPicture = new JButton(ICN_ADD_PICTURE);
		btnAddPicture.setContentAreaFilled(false);
		btnAddPicture.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		btnAddPicture.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JScrollPane scrlQuestion = new JScrollPane(txtQuestion);
		scrlQuestion.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrlQuestion.setBorder(INNER_CARD_BORDER);
		
		pnlQuestion.add(lblQuestion);
		pnlQuestion.add(scrlQuestion);
		pnlQuestion.add(btnAddPicture);
	}
	
	private void initAnswerArea() {
		pnlAnswer = new JPanel();
		pnlAnswer.setOpaque(false);
		pnlAnswer.setLayout(new BoxLayout(pnlAnswer, BoxLayout.PAGE_AXIS));
		pnlAnswer.setBorder(OUTER_CARD_BORDER);
		
		lblAnswer = new JLabel(LABELSTRING_ANSWER);
		lblAnswer.setAlignmentX(Component.CENTER_ALIGNMENT);
		txtAnswer = new JTextArea(10,20);
		txtAnswer.setLineWrap(true);
		txtAnswer.setText(engine.getCurrentCard().getCardAnswer());
		
		JScrollPane scrlAnswer = new JScrollPane(txtAnswer);
		scrlAnswer.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrlAnswer.setBorder(BorderFactory.createCompoundBorder(OUTER_CARD_BORDER, INNER_CARD_BORDER));
		scrlAnswer.setBorder(INNER_CARD_BORDER);
		
		pnlAnswer.add(lblAnswer);
		pnlAnswer.add(scrlAnswer);
	}
	
	private void initSaveDeleteArea() {
		pnlSaveDelete = new JPanel(new FlowLayout(FlowLayout.CENTER));
		pnlSaveDelete.setOpaque(false);
		
		btnCancel = ButtonFactory.createColouredButton("Abbrechen", ButtonFactory.BTN_BLUE);
		btnDeleteCurrentCard = ButtonFactory.createColouredButton("Löschen", ButtonFactory.BTN_RED);
		btnSaveStack = ButtonFactory.createColouredButton("Speichern", ButtonFactory.BTN_GREEN);
		
		pnlSaveDelete.add(btnCancel);
		pnlSaveDelete.add(btnDeleteCurrentCard);
		pnlSaveDelete.add(btnSaveStack);	
	}
	
	private void initFrame() {
		JPanel mainPanel = new GradientPanel();
		mainPanel.setLayout(new GridBagLayout());
		
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		//-------------------------------------
		c.gridy=0;
		mainPanel.add(pnlNavigation,c);
		//-------------------------------------
		c.gridwidth=3; 
		c.gridy++;
		mainPanel.add(pnlQuestion, c);
		//-------------------------------------
		c.gridy++;
		mainPanel.add(pnlAnswer, c);
		c.gridwidth=1;
		//-------------------------------------
		c.gridy++;
		mainPanel.add(pnlSaveDelete,c);
		
		editStackWindow.add(mainPanel);
		editStackWindow.setVisible(true);
		editStackWindow.setMinimumSize(new Dimension(400, 620));
		editStackWindow.setTitle(FRAME_TITLE);
		editStackWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void initListeners() {
		
	}
}
