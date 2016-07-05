package de.htwsaar.flashcards.ui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

public class EditStackWindow extends JFrame {

	private static final String FRAME_TITLE = "Stack bearbeiten";
	// Label- und Button-Namen
	private static final String LABELSTRING_QUESTION = "--- Frage ---";
	private static final String LABELSTRING_ANSWER = "--- Antwort ---";
	private static final String BUTTONSTRING_CARDFORWARD = "Weiter";
	private static final String BUTTONSTRING_CARDBACKWARD = "Zurueck";
	private static final String BUTTONSTRING_DELETECURRENTCARD = "Kartei loeschen";
	private static final String BUTTONSTRING_CANCEL = "Abbrechen";
	private static final String BUTTONSTRING_ADDPICTURE = "Bild hinzufuegen";
	private static final String BUTTONSTRING_SAVESTACK = "Stack speichern";

	// Labels
	private JLabel labelStackName;
	private JLabel labelQuestion;
	private JLabel labelAnswer;
	private JLabel labelCardName;

	// Input
	private JTextArea textQuestion;
	private JTextArea textAnswer;

	// Buttons
	private JButton buttonCardForward;
	private JButton buttonCardBackward;
	private JButton buttonAddPicture;
	private JButton buttonCancel;
	private JButton buttonDeleteCurrentCard;
	private JButton buttonSaveStack;

	// Panels
	private JPanel mainPanel;
	private JPanel buttonPanel1;
	private JPanel buttonPanel2;
	private JPanel buttonPanel3;
	private JPanel questionPanel;
	private JPanel answerPanel;
	private JPanel labelStackNamePanel;
	private JPanel labelQuestionPanel;
	private JPanel labelAnswerPanel;

	// Das Stack-Objekt muss uebergeben werden.
	// Als Test mit String
	public EditStackWindow(String stackName) {
		labelStackName = new JLabel(stackName);
		labelCardName = new JLabel("Kartei 1");
		labelQuestion = new JLabel(LABELSTRING_QUESTION);
		labelAnswer = new JLabel(LABELSTRING_ANSWER);
		textQuestion = new JTextArea(10, 20);
		textAnswer = new JTextArea(10, 20);

		createButtons();
		createPanel();
		this.getContentPane().add(mainPanel);
		this.setVisible(true);
		this.setSize(400, 600);
		this.pack();
		this.setTitle(FRAME_TITLE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Platziert das Fenster mittig
		Toolkit tool = Toolkit.getDefaultToolkit();
		Dimension dim = tool.getScreenSize();
		int xPos = (dim.width / 2) - (this.getWidth() / 2);
		int yPos = (dim.height / 2) - (this.getHeight() / 2);
		this.setLocation(xPos, yPos);
	}

	// Falls kein Stack uebergeben wurde
	public EditStackWindow() {
		labelStackName = new JLabel("Erster Stack");
		labelCardName = new JLabel("Kartei 1");
		labelQuestion = new JLabel(LABELSTRING_QUESTION);
		labelAnswer = new JLabel(LABELSTRING_ANSWER);
		textQuestion = new JTextArea(10, 20);
		textAnswer = new JTextArea(10, 20);

		createButtons();
		createPanel();
		this.getContentPane().add(mainPanel);
		this.setVisible(true);
		this.setSize(400, 600);
		this.pack();
		this.setTitle(FRAME_TITLE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Platziert das Fenster mittig
		Toolkit tool = Toolkit.getDefaultToolkit();
		Dimension dim = tool.getScreenSize();
		int xPos = (dim.width / 2) - (this.getWidth() / 2);
		int yPos = (dim.height / 2) - (this.getHeight() / 2);
		this.setLocation(xPos, yPos);
	}

	// Panel vorbereiten
	private void createPanel() {
		mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

		// Um die Labels mittig zu halten
		labelStackNamePanel = new JPanel();
		labelStackNamePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		labelStackNamePanel.add(labelStackName);

		labelQuestionPanel = new JPanel();
		labelQuestionPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		labelQuestionPanel.add(labelQuestion);

		labelAnswerPanel = new JPanel();
		labelAnswerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		labelAnswerPanel.add(labelAnswer);

		// Frage- und Antwort-Panel mit Scrollbar
		questionPanel = new JPanel();
		JScrollPane scrollbar1 = new JScrollPane(textQuestion);
		scrollbar1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		questionPanel.add(scrollbar1);
		textQuestion.setLineWrap(true);

		answerPanel = new JPanel();
		JScrollPane scrollbar2 = new JScrollPane(textAnswer);
		scrollbar2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		answerPanel.add(scrollbar2);
		textQuestion.setLineWrap(true);

		mainPanel.add(labelStackNamePanel);
		mainPanel.add(buttonPanel1);
		mainPanel.add(labelQuestionPanel);
		mainPanel.add(questionPanel);
		mainPanel.add(buttonPanel2);
		mainPanel.add(labelAnswerPanel);
		mainPanel.add(answerPanel);
		mainPanel.add(buttonPanel3);
	}

	// Buttons vorbereiten
	private void createButtons() {
		buttonCardForward = new JButton(BUTTONSTRING_CARDFORWARD);
		buttonCardBackward = new JButton(BUTTONSTRING_CARDBACKWARD);
		buttonAddPicture = new JButton(BUTTONSTRING_ADDPICTURE);
		buttonCancel = new JButton(BUTTONSTRING_CANCEL);
		buttonDeleteCurrentCard = new JButton(BUTTONSTRING_DELETECURRENTCARD);
		buttonSaveStack = new JButton(BUTTONSTRING_SAVESTACK);

		buttonPanel1 = new JPanel();
		buttonPanel1.setLayout(new FlowLayout());
		buttonPanel1.add(buttonCardBackward);
		buttonPanel1.add(labelCardName);
		buttonPanel1.add(buttonCardForward);

		buttonPanel2 = new JPanel();
		buttonPanel2.setLayout(new FlowLayout());
		buttonPanel2.add(buttonAddPicture);

		buttonPanel3 = new JPanel();
		buttonPanel3.setLayout(new FlowLayout());
		buttonPanel3.add(buttonCancel);
		buttonPanel3.add(buttonDeleteCurrentCard);
		buttonPanel3.add(buttonSaveStack);
	}

	public static void main(String[] args) {
		new EditStackWindow("Test Stack");
	}

}
