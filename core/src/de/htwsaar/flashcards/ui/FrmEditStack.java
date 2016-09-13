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
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;

import de.htwsaar.flashcards.model.FlashCard;
import de.htwsaar.flashcards.properties.Messages;
import de.htwsaar.flashcards.service.interfaces.EditFlashCardService;
import de.htwsaar.flashcards.ui.component.GradientPanel;
import de.htwsaar.flashcards.util.FlashCardButtonFactory;
import de.htwsaar.flashcards.util.FlashCardUtils;

/**
 * <code>FrmEditStack</code> - Dient der Bearbeitung von Karteikarten eines ausgewaehlten
 * Stacks. Ueber Pfeiltasten kann durch die Liste der Karten iteriert werden. Eigenschaften 
 * der Karten koennen jeweils ueber die entsprechende Maske veraendert werden. Zudem kann ueber einen
 * Button eine neue Karte hinzugefuegt werden.
 * 
 * @author Marek Kohn, David Berres
 * @see EditStackService, EditStackServiceImpl
 *
 */
public class FrmEditStack {

	private static final String VERTICAL_LINE = Messages.getString("vertical_line");
	private static final String FRAME_TITLE = Messages.getString("edit_stack"); 
	private static final String LABELSTRING_QUESTION = VERTICAL_LINE +  Messages.getString("question") + VERTICAL_LINE; 
	private static final String LABELSTRING_ANSWER = VERTICAL_LINE + Messages.getString("answer") + VERTICAL_LINE;
	
	private static final ImageIcon ICN_ARROW_RIGHT = new ImageIcon("res/images/arrow-right.png");
	private static final ImageIcon ICN_ARROW_LEFT = new ImageIcon("res/images/arrow-left.png");
	private static final ImageIcon ICN_ADD_PICTURE= new ImageIcon("res/images/add-picture.png");
	private static final ImageIcon ICN_DEL_PICTURE= new ImageIcon("res/images/rem-picture.png");
	private static final ImageIcon ICN_ADD_CARD= new ImageIcon("res/images/add-card.png");
	
	private static final Border INNER_CARD_BORDER = BorderFactory.createLineBorder(Color.GRAY);
	private static final Border OUTER_CARD_BORDER = BorderFactory.createEmptyBorder(10,10,10,10);
	
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
	private JButton btnDelPicture;
	private JButton btnCancel;
	private JButton btnDeleteCurrentCard;
	private JButton btnSaveStack;
	private JButton btnAddCard;

	// Panels
	private JPanel pnlNavigation;
	private JPanel pnlSaveDelete;
	private JPanel pnlQuestion;
	private JPanel pnlAnswer;
	
	private JFileChooser chooser;
	private String imagePath;
	
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
		update();
	}
	
	private void initNavigationArea() {
		txtCardName = new JTextField();
		txtCardName.setBorder(BorderFactory.createEmptyBorder());
		txtCardName.setOpaque(false);
		txtCardName.setPreferredSize(new Dimension(250,60));
		txtCardName.setHorizontalAlignment(JTextField.CENTER);
		btnCardForward = FlashCardButtonFactory.createImageButton(ICN_ARROW_RIGHT);
		btnCardBackward = FlashCardButtonFactory.createImageButton(ICN_ARROW_LEFT);
		btnAddCard = FlashCardButtonFactory.createImageButton(ICN_ADD_CARD);
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
		
		btnAddPicture = FlashCardButtonFactory.createImageButton(ICN_ADD_PICTURE);
		btnAddPicture.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnAddPicture.setVisible(false); 
		btnDelPicture = FlashCardButtonFactory.createImageButton(ICN_DEL_PICTURE);
		btnDelPicture.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnDelPicture.setVisible(false); 
		
		JScrollPane scrlQuestion = new JScrollPane(txtQuestion);
		scrlQuestion.setOpaque(false);
		scrlQuestion.setBorder(INNER_CARD_BORDER);
		
		pnlQuestion.add(lblQuestion);
		pnlQuestion.add(scrlQuestion);
		pnlQuestion.add(btnAddPicture);
		pnlQuestion.add(btnDelPicture);
	}
	
	private void initAnswerArea() {
		pnlAnswer = new JPanel();
		pnlAnswer.setOpaque(false);
		pnlAnswer.setLayout(new BoxLayout(pnlAnswer, BoxLayout.PAGE_AXIS));
		pnlAnswer.setBorder(OUTER_CARD_BORDER);
		
		lblAnswer = new JLabel(LABELSTRING_ANSWER);
		lblAnswer.setAlignmentX(Component.CENTER_ALIGNMENT);
		txtAnswer = FlashCardUtils.createCardTextArea(true);
		
		JScrollPane scrlAnswer = new JScrollPane(txtAnswer);
        scrlAnswer.setOpaque(false);
        scrlAnswer.setBorder(INNER_CARD_BORDER);
		
		pnlAnswer.add(lblAnswer);
		pnlAnswer.add(scrlAnswer);
	}
	
	private void initSaveDeleteArea() {
		pnlSaveDelete = new JPanel(new GridLayout(1, 3, 10, 30));
		pnlSaveDelete.setOpaque(false);
		
		btnCancel = FlashCardButtonFactory.createColouredButton(Messages.getString("cancel"), FlashCardButtonFactory.BTN_BLUE); 
		btnDeleteCurrentCard = FlashCardButtonFactory.createColouredButton(Messages.getString("delete"), FlashCardButtonFactory.BTN_RED);
		btnSaveStack = FlashCardButtonFactory.createColouredButton(Messages.getString("save"), FlashCardButtonFactory.BTN_GREEN);
		
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
		editStackWindow.setMinimumSize(new Dimension(650, 780));
		editStackWindow.setResizable(false);
		editStackWindow.setTitle(FRAME_TITLE);
		editStackWindow.setLocationRelativeTo(null);
		editStackWindow.setVisible(true);
		editStackWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void initListeners() {
		btnCardForward.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cardService.nextCard();
				update();
			}
		});
		
		btnCardBackward.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cardService.previousCard();
				update();
			}
		});
		
		btnAddCard.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cardService.addCard();
				update();
				txtCardName.requestFocus();
			}
		});
		
		btnSaveStack.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				cardService.saveCard(
						txtCardName.getText(), 
						txtQuestion.getText(),
						txtAnswer.getText(),
						imagePath);
			}
		});
		
		btnAddPicture.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				chooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter(Messages.getString("image_files"), "jpg", "png");
				chooser.setFileFilter(filter);
				int returnVal = chooser.showOpenDialog(editStackWindow);
				if(returnVal == JFileChooser.APPROVE_OPTION) {
				      imagePath = chooser.getSelectedFile().getPath(); 
				 }
			}
		});
		
		btnDelPicture.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				imagePath = null;
				btnAddPicture.setVisible(true);
				btnDelPicture.setVisible(false);
			}
		});
		
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				editStackWindow.dispose();
				new FrmSelectStack();
			}
		});
	}
	
	private void update() {
		FlashCard card = cardService.getCurrentCard();
		txtCardName.setText(card != null ? card.getCardName() : "");
		txtQuestion.setText(card != null ? card.getCardQuestion() : "");
		txtAnswer.setText(card != null ? card.getCardAnswer() : "");
		if(card.hasPic()) {
			btnAddPicture.setVisible(false);
			btnDelPicture.setVisible(true);
		}
		else {
			btnAddPicture.setVisible(true);
			btnDelPicture.setVisible(false);
		}
	}
}
