package de.htwsaar.flashcards.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.*;


import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.border.Border;

import de.htwsaar.flashcards.engine.GameEngineImpl;
import de.htwsaar.flashcards.engine.interfaces.GameEngine;



/*
 * Ui für die Abfrage der Flashcards
 * @author=Marco Becker
 */
public class StudyWindow extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private static final Font FONT_CARD = new Font("SansSerif", Font.PLAIN, 20);
	private static final Font FONT_COUNTER = new Font("SansSerif", Font.ITALIC, 20);
	
	private GameEngine engine;
	
	private JTextArea txtCard;
	private JTextArea txtAnswer;
	private JPanel pnlInfo;
	private JLabel lblStackname;
	private JLabel lblCardCount;
	private JPanel pnlEval;
	private JPanel pnlFlashcard;
	private JPanel pnlAnswer;
	
	private JButton btnCorrect;
	private JButton btnInCorrect;
	private JButton btnShowAnswer;
	private JLabel lblImage;
	private ImageIcon image;
	
	
	
	//attribute
	

	/*
	 * Konstruktor für Study UI, initialisierung der einzelnen Komponenten
	 */
	public StudyWindow() throws ClassNotFoundException
	{		
		engine = new GameEngineImpl();
		
		setTitle("Study");
		setSize(500,500);
		setLayout(new BorderLayout());
		
		//Init der Components und Listeners
		initComponents();
		initListener();
		
		add(pnlInfo, BorderLayout.NORTH);		
		add(pnlFlashcard, BorderLayout.CENTER);		
		add(pnlEval, BorderLayout.SOUTH);
		
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
	}
	
	
	private void initComponents()
	{
			//Initialize the Info Area
			pnlInfo = new JPanel(new GridBagLayout());
			GridBagConstraints c = new GridBagConstraints();
			
			lblStackname = new JLabel();
			lblStackname.setFont(FONT_COUNTER);
			lblStackname.setForeground(Color.BLACK);
			c.gridx = 0;
			c.insets = new Insets(0,0,0,100);
			pnlInfo.add(lblStackname, c);
			
			lblCardCount = new JLabel("1/100");
			lblCardCount.setFont(FONT_COUNTER);
			lblCardCount.setForeground(Color.BLUE);
			c.gridx = 2;
			c.insets = new Insets(0,50,0,0);
			pnlInfo.add(lblCardCount, c);
			
			//JPanel für Q/A Area
			pnlFlashcard = new JPanel();
			pnlFlashcard.setLayout(new BoxLayout(pnlFlashcard, BoxLayout.Y_AXIS));
			
			// Initialize the Q/A Area
			txtCard = new JTextArea();
			txtCard.setLineWrap(true);
			txtCard.setWrapStyleWord(true);
			txtCard.setEditable(false);
			txtCard.setFont(FONT_CARD);
			txtCard.setBackground(Color.WHITE);
			Border innerBorder = BorderFactory.createLineBorder(Color.GRAY);
			Border outerBorder = BorderFactory.createLineBorder(UIManager.getColor("Panel.background"), 10);
			txtCard.setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
			
			txtCard.setText(engine.getCurrentCard().getCardQuestion());			
			pnlFlashcard.add(txtCard);
			
			
			//Init Image
			if(engine.getCurrentCard().getCardPicture() == null)
			{
				image = new ImageIcon(engine.getCurrentCard().getCardPicture());
				lblImage = new JLabel(image);
				lblImage.setVisible(true);
			}
			else
			{
				lblImage = new JLabel();
				lblImage.setVisible(false);
			}
			lblImage.setHorizontalAlignment(JLabel.CENTER);
			pnlFlashcard.add(lblImage);
			
			
			// Initialze the answer panel und Button für antwort anzeigen, sowie Antwort Feld
			pnlAnswer = new JPanel(new BorderLayout());
			btnShowAnswer = new JButton("ShowAnswer");
			btnShowAnswer.setBorder(BorderFactory.createEmptyBorder(5,5,50,5));
			btnShowAnswer.setContentAreaFilled(true);
			pnlAnswer.add(btnShowAnswer, BorderLayout.SOUTH);
			
			//Answer Component
			txtAnswer = new JTextArea();
			txtAnswer.setLineWrap(true);
			txtAnswer.setWrapStyleWord(true);
			txtAnswer.setEditable(false);
			txtAnswer.setFont(FONT_CARD);
			txtAnswer.setBackground(Color.WHITE);			
			txtAnswer.setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
			//txtAnswer.setText(engine.getCurrentCard().getCardAnswer());
			txtAnswer.setText("test");
			txtAnswer.setVisible(false);
			pnlAnswer.add(txtAnswer, BorderLayout.CENTER);
			
			pnlFlashcard.add(pnlAnswer);
			
			// Initialize the evaluation panel
			pnlEval = new JPanel(new GridBagLayout());
			GridBagConstraints cos = new GridBagConstraints();
			
			ImageIcon icn_correct = new ImageIcon("res/true.png");
			ImageIcon icn_false = new ImageIcon("res/false.png");			
			
			//btn Correct
			btnCorrect = new JButton(icn_correct);
			btnCorrect.setContentAreaFilled(false);
			btnCorrect.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
			btnCorrect.setEnabled(false);
			cos.gridx = 0;
			cos.gridy = 2;
			cos.insets = new Insets(50,0,0,50);			
			pnlEval.add(btnCorrect, cos);
			
			//Init btn InCorrect mit Position
			btnInCorrect = new JButton(icn_false);
			btnInCorrect.setContentAreaFilled(false);
			btnInCorrect.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
			btnInCorrect.setEnabled(false);
			cos.gridx = 2;
			cos.gridy = 2;
			cos.insets = new Insets(50,50,0,0);
			pnlEval.add(btnInCorrect, cos);
		
			
			
			
			
		}	
	
	//Initiert die ganzen Listener
	private void initListener()
	{
		//Initiert den Listener für den Button btnShowAnswer
		btnShowAnswer.addActionListener(new ActionListener() {			

			@Override
			public void actionPerformed(ActionEvent e) {
				
				btnCorrect.setEnabled(true);
				btnInCorrect.setEnabled(true);
				btnShowAnswer.setVisible(false);
				txtAnswer.setVisible(true);
			}
		});
		
		btnCorrect.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				//engine.evaluateAnswer(true);
				btnCorrectInCorrect_Click(e);
			}
		});
		
		btnInCorrect.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				engine.evaluateAnswer(false);
				btnCorrectInCorrect_Click(e);
			}
		});
	}
	
	//Methode für die Buttons Correct und InCorrect
	private void btnCorrectInCorrect_Click(Object e)
	{
		
		btnCorrect.setEnabled(false);
		btnInCorrect.setEnabled(false);
		txtAnswer.setVisible(false);
		btnShowAnswer.setVisible(true);
		txtCard.setText(engine.getNextCard().getCardQuestion());
		txtAnswer.setText(engine.getCurrentCard().getCardAnswer());
		
		if(engine.getCurrentCard().getCardPicture().trim().isEmpty() == true)
		{
			lblImage.setVisible(false);
		}
		else
		{
			ImageIcon newImage = new ImageIcon(engine.getCurrentCard().getCardPicture());
			lblImage.setIcon(newImage);
			lblImage.setVisible(true);
			
		}
	}
	

	public static void main(String[] args) throws ClassNotFoundException
	{
		new StudyWindow();
	}
}

