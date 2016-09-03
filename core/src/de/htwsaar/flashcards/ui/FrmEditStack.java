package de.htwsaar.flashcards.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

import de.htwsaar.flashcards.service.EditFlashCardService;
import de.htwsaar.flashcards.ui.component.GradientPanel;
import de.htwsaar.flashcards.util.ButtonFactory;
import de.htwsaar.flashcards.util.FlashCardUtils;

public class FrmEditStack {

	private static final String FRAME_TITLE = Messages.getString("edit_stack"); 
	private static final String LABELSTRING_QUESTION = Messages.getString("question"); 
	private static final String LABELSTRING_ANSWER = Messages.getString("answer");
	
	private static final ImageIcon ICN_ARROW_RIGHT = new ImageIcon("res/images/arrow-right.png");
	private static final ImageIcon ICN_ARROW_LEFT = new ImageIcon("res/images/arrow-left.png");
	private static final ImageIcon ICN_ADD_PICTURE= new ImageIcon("res/images/add-picture.png");
	private static final ImageIcon ICN_ADD_CARD= new ImageIcon("res/images/add-card.png");
	
	private static final Border INNER_CARD_BORDER = BorderFactory.createLineBorder(Color.GRAY);
	private static final Border OUTER_CARD_BORDER = BorderFactory.createEmptyBorder(10,10,10,10);
	
	// Labels
	//private JLabel lblStackName;
	private JLabel lblQuestion;
	private JLabel lblAnswer;
	private JTextField txtCardName;

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
	private JButton btnAddCard;

	// Panels
	private JPanel pnlNavigation;
	private JPanel pnlSaveDelete;
	private JPanel pnlQuestion;
	private JPanel pnlAnswer;
	
	private EditFlashCardService cardService;
	
	private JFrame editStackWindow;
	
	public FrmEditStack(EditFlashCardService cardService) {
		this.cardService = cardService;
		editStackWindow = new JFrame();
		
		initNavigationArea();
		initQuestionArea();
		initAnswerArea();
		initSaveDeleteArea();
		initFrame();
		initListeners();
	}
	
	private void initNavigationArea() {
		txtCardName = new JTextField(cardService.getCurrentCard().getCardName());
		txtCardName.setBorder(BorderFactory.createEmptyBorder());
		txtCardName.setOpaque(false);
		txtCardName.setPreferredSize(new Dimension(250,60));
		txtCardName.setHorizontalAlignment(JTextField.CENTER);
		btnCardForward = ButtonFactory.createImageButton(ICN_ARROW_RIGHT);
		btnCardBackward = ButtonFactory.createImageButton(ICN_ARROW_LEFT);
		btnAddCard = ButtonFactory.createImageButton(ICN_ADD_CARD);
		btnAddCard.setToolTipText(Messages.getString("add_card"));
		pnlNavigation = new JPanel(new GridBagLayout());
		pnlNavigation.setOpaque(false);
		GridBagConstraints gc = new GridBagConstraints();
		gc.gridx = gc.gridy = 0;
		gc.insets = new Insets(0, 103, 0, 0);
		pnlNavigation.add(btnCardBackward,gc);
		gc.gridx++;
		gc.insets = new Insets(0, 10, 0, 0);
		pnlNavigation.add(txtCardName,gc);
		gc.gridx++;
		gc.insets = new Insets(0, 10, 0, 0);
		pnlNavigation.add(btnCardForward,gc);
		gc.gridx++;
		gc.insets = new Insets(0, 40, 0, 0);
		pnlNavigation.add(btnAddCard,gc);
		pnlNavigation.setMaximumSize(new Dimension(650,200));
	}
	
	private void initQuestionArea() {
		pnlQuestion = new JPanel();
		pnlQuestion.setOpaque(false);
		pnlQuestion.setLayout(new BoxLayout(pnlQuestion, BoxLayout.PAGE_AXIS));
		pnlQuestion.setBorder(OUTER_CARD_BORDER);
		
		lblQuestion = new JLabel(LABELSTRING_QUESTION);
		lblQuestion.setAlignmentX(Component.CENTER_ALIGNMENT);
		txtQuestion = FlashCardUtils.createCardTextArea(true);
		txtQuestion.setText(cardService.getCurrentCard().getCardQuestion());
		
		btnAddPicture = ButtonFactory.createImageButton(ICN_ADD_PICTURE);
		btnAddPicture.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JScrollPane scrlQuestion = new JScrollPane(txtQuestion);
		scrlQuestion.setOpaque(false);
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
		txtAnswer = FlashCardUtils.createCardTextArea(true);
		txtAnswer.setText(cardService.getCurrentCard().getCardAnswer());
		
		JScrollPane scrlAnswer = new JScrollPane(txtAnswer);
        scrlAnswer.setOpaque(false);
        scrlAnswer.setBorder(INNER_CARD_BORDER);
		
		pnlAnswer.add(lblAnswer);
		pnlAnswer.add(scrlAnswer);
	}
	
	private void initSaveDeleteArea() {
		pnlSaveDelete = new JPanel(new GridLayout(1, 3, 10, 30));
		pnlSaveDelete.setOpaque(false);
		
		btnCancel = ButtonFactory.createColouredButton(Messages.getString("discard"), ButtonFactory.BTN_BLUE); 
		btnDeleteCurrentCard = ButtonFactory.createColouredButton(Messages.getString("delete"), ButtonFactory.BTN_RED);
		btnSaveStack = ButtonFactory.createColouredButton(Messages.getString("save"), ButtonFactory.BTN_GREEN);
		
		pnlSaveDelete.add(btnSaveStack);
		pnlSaveDelete.add(btnDeleteCurrentCard);
		pnlSaveDelete.add(btnCancel);
		
		pnlSaveDelete.setBorder(BorderFactory.createEmptyBorder(40, 0, 40, 0));
		pnlSaveDelete.setMaximumSize(new Dimension(400,200));
	}
	
	private void initFrame() {
		JPanel mainPanel = new GradientPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
		mainPanel.add(pnlNavigation);
		mainPanel.add(pnlQuestion);
		mainPanel.add(pnlAnswer);
		mainPanel.add(pnlSaveDelete);
		editStackWindow.add(mainPanel);
		editStackWindow.setVisible(true);
		editStackWindow.setMinimumSize(new Dimension(650, 780));
		editStackWindow.setResizable(false);
		editStackWindow.setTitle(FRAME_TITLE);
		editStackWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void initListeners() {
		btnCardForward.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cardService.nextCard();
				updateTextFields();
			}
		});
		
		btnCardBackward.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cardService.previousCard();
				updateTextFields();
			}
		});
		
		btnAddCard.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cardService.addCard();
				txtCardName.setText("");
				txtQuestion.setText("");
				txtAnswer.setText("");
			}
		});
		
	}
	
	private void updateTextFields() {
		txtCardName.setText(cardService.getCurrentCard().getCardName());
		txtQuestion.setText(cardService.getCurrentCard().getCardQuestion());
		txtAnswer.setText(cardService.getCurrentCard().getCardAnswer());
	}
}
