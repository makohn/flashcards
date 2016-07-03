package de.htwsaar.flashcards.ui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.Border;

import de.htwsaar.flashcards.controller.CardCreationController;

public class CreateCardFrame extends JFrame {
	
	private static final long serialVersionUID = 3603735413969513435L;

	private static final Font FONT_CARD = new Font("SansSerif", Font.PLAIN, 20);
	
	private JTextField txtName;
	private JTextArea txtQuestion;
	private JTextArea txtAnswer;
	private JPanel pnlButtons;
	private JButton btnSave;
	private JButton btnCancel;
	
	//private JFrame self;
	
	private CardCreationController controller;
	
	public CreateCardFrame() throws ClassNotFoundException {
		
		setTitle("Create a Flashcard");
		setSize(500,500);
		setLayout(new GridBagLayout());
		
		controller = new CardCreationController();
		
		initComponents();
		
		GridBagConstraints gc = new GridBagConstraints();
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.ipady = 10;
		gc.weightx = 1;
		gc.gridy = 0;
		add(new JLabel("		Bezeichnung:"), gc);
		gc.gridy = 1;
		add(txtName, gc);
		
		gc.gridy = 2;
		gc.weightx = 5;
		add(new JLabel("		Frage: "), gc);
		gc.ipady = 110;
		gc.gridy = 3;
		add(txtQuestion, gc);
		
		gc.ipady = 10;
		gc.gridy = 4;
		add(new JLabel("		Antwort: "), gc);
		gc.ipady = 110;
		gc.gridy = 5;
		add(txtAnswer, gc);
		
		gc.ipady = 20;
		gc.gridy = 6;
		add(pnlButtons, gc);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		//self = this;
	}
	
	private void initComponents() {
		txtName = new JTextField();
		Border ninnerBorder = BorderFactory.createLineBorder(Color.GRAY);
		Border nouterBorder = BorderFactory.createLineBorder(UIManager.getColor("Panel.background"), 10);
		txtName.setBorder(BorderFactory.createCompoundBorder(nouterBorder, ninnerBorder));
		
		//initialize the question area
		txtQuestion = new JTextArea();
		txtQuestion.setLineWrap(true);
		txtQuestion.setWrapStyleWord(true);
		txtQuestion.setFont(FONT_CARD);
		txtQuestion.setBackground(Color.WHITE);
		Border qinnerBorder = BorderFactory.createLineBorder(Color.GRAY);
		Border qouterBorder = BorderFactory.createLineBorder(UIManager.getColor("Panel.background"), 10);
		txtQuestion.setBorder(BorderFactory.createCompoundBorder(qouterBorder, qinnerBorder));
		
		//initialize the answer area
		txtAnswer = new JTextArea();
		txtAnswer.setLineWrap(true);
		txtAnswer.setWrapStyleWord(true);
		txtAnswer.setFont(FONT_CARD);
		txtAnswer.setBackground(Color.WHITE);
		Border ainnerBorder = BorderFactory.createLineBorder(Color.GRAY);
		Border aouterBorder = BorderFactory.createLineBorder(UIManager.getColor("Panel.background"), 10);
		txtAnswer.setBorder(BorderFactory.createCompoundBorder(aouterBorder, ainnerBorder));
		
		//intitalize the Buttonpanel
		pnlButtons = new JPanel(new FlowLayout(FlowLayout.CENTER));
		btnSave = new JButton("Speichern");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.saveCard(txtName.getText(),
									txtQuestion.getText(),
									txtAnswer.getText());
//				System.out.println(txtName.getText());
//				System.out.println(txtQuestion.getText());
//				System.out.println(txtAnswer.getText());
			}
		});
		btnCancel = new JButton("Abbrechen");
		pnlButtons.add(btnSave);
		pnlButtons.add(btnCancel);
	}
}
