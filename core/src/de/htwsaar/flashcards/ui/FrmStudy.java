package de.htwsaar.flashcards.ui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;

import de.htwsaar.flashcards.engine.interfaces.GameEngine;
import de.htwsaar.flashcards.properties.Messages;
import de.htwsaar.flashcards.ui.component.GradientPanel;
import de.htwsaar.flashcards.ui.component.ProgressCircle;
import de.htwsaar.flashcards.util.ButtonFactory;
import de.htwsaar.flashcards.util.FlashCardUtils;

public class FrmStudy {
	
    private static final ImageIcon ICN_CORRECT = new ImageIcon("res/images/true.png");
    private static final ImageIcon ICN_INCORRECT = new ImageIcon("res/images/false.png");
    private static final ImageIcon ICN_QUEST_IMG = new ImageIcon("res/images/questionmarks.png");
    private static final Border INNER_CARD_BORDER = BorderFactory.createLineBorder(Color.GRAY);
    private static final Border OUTER_CARD_BORDER = BorderFactory.createEmptyBorder(10, 10, 10, 10);
    
    private GameEngine engine;
    
    private JTextArea txtCard;
    private JTextArea txtAnswer;
    private JLabel lblBoxCounter;
    private JLabel lblCardname;
    private JLabel lblCardCounter;
    private JProgressBar progressbar;
    private ProgressCircle progresscircle;
    private JButton btnCorrect;
    private JButton btnInCorrect;
    private JButton btnShowAnswer;
    private JLabel lblQuestionImage;
    private String imagePath;
    private JPanel pnlInfo;
    private JPanel pnlFlashcard;
    private JPanel pnlImage;
    private JPanel pnlAnswer;
    private JPanel pnlEval;
    private JScrollPane scrlCard;
    private JScrollPane scrlAnswer;
    private JFrame studyFrame;

    public FrmStudy(GameEngine engine) {
    	this.engine = engine;
    	studyFrame = new JFrame();
    	
    	studyFrame.setResizable(false);
    	
        initInfoArea();
        initQuestionArea();
        initImageArea();
        initAnswerArea();
        initEvaluationArea();
        initFrame();
        initListener();
    }

    private void initInfoArea() {
        pnlInfo = new JPanel(new GridBagLayout());
        pnlInfo.setOpaque(false);
        pnlInfo.setBorder(OUTER_CARD_BORDER);
        progressbar = new JProgressBar(0, engine.getNrCards());
        progresscircle = new ProgressCircle(20);
        progresscircle.setPreferredSize(new Dimension(50, 50));
        progresscircle.setMinimumSize(new Dimension(50, 50));
        lblCardCounter = new JLabel("Q1");
        lblCardCounter.setFont(new Font("SansSerif", 1, 20));
        lblCardname = new JLabel(engine.getCurrentCard().getCardName());
        lblBoxCounter = new JLabel("Box: " + engine.getCurrentCard().getBoxCounter());
        GridBagConstraints gc = new GridBagConstraints();
        //-------------------------------------
		gc.gridx = 0; gc.gridy = 0;
		gc.insets = new Insets(0, 10, 0, 120);
		pnlInfo.add(lblBoxCounter, gc);
		//-------------------------------------
		gc.gridx++;
		gc.insets = new Insets(0, 45, 0, 0);
		pnlInfo.add(lblCardname,gc);
		//-------------------------------------
		gc.gridx = 0; gc.gridy++;
		gc.insets = new Insets(0, 10, 0, 120);
		pnlInfo.add(lblCardCounter,gc);
		//-------------------------------------
		gc.gridx++;
		gc.insets = new Insets(0, 45, 0, 0);
		pnlInfo.add(progressbar,gc);
		//-------------------------------------
		gc.gridx++;
		gc.gridy = 0;
		gc.gridheight = 2;
		gc.insets = new Insets(5, 160, 0, 0);
		//-------------------------------------
		pnlInfo.add(progresscircle,gc);
		pnlInfo.setMaximumSize(new Dimension(650,200));
    }

    private void initQuestionArea() {
        pnlFlashcard = new JPanel(new CardLayout());
        pnlFlashcard.setOpaque(false);
        pnlFlashcard.setBorder(OUTER_CARD_BORDER);
        txtCard = FlashCardUtils.createCardTextArea(false);
        txtCard.setText(engine.getCurrentCard().getCardQuestion());
        scrlCard = new JScrollPane(txtCard);
        scrlCard.setBorder(INNER_CARD_BORDER);
        pnlFlashcard.add(scrlCard);
    }

    private void initAnswerArea() {
        pnlAnswer = new JPanel(new CardLayout());
        pnlAnswer.setOpaque(false);
        pnlAnswer.setBorder(OUTER_CARD_BORDER);
        btnShowAnswer = ButtonFactory.createColouredButton(Messages.getString("answer"), new Color(0, 163, 204));
        pnlAnswer.add(btnShowAnswer);
        txtAnswer = FlashCardUtils.createCardTextArea(false);
        txtAnswer.setText(engine.getCurrentCard().getCardAnswer());
        scrlAnswer = new JScrollPane(txtAnswer);
        scrlAnswer.setOpaque(false);
        scrlAnswer.setVisible(false);
        scrlAnswer.setBorder(INNER_CARD_BORDER);
        pnlAnswer.add(scrlAnswer);
    }

    private void initImageArea() {
        pnlImage = new JPanel();
        pnlImage.setOpaque(false);
        lblQuestionImage = new JLabel();
        imagePath = "";
        loadImage();
        pnlImage.add(this.lblQuestionImage);
    }

    private void initEvaluationArea() {
        pnlEval = new JPanel(new GridBagLayout());
        pnlEval.setOpaque(false);
        GridBagConstraints cos = new GridBagConstraints();
        btnCorrect = ButtonFactory.createImageButton(ICN_CORRECT);
        btnCorrect.setEnabled(false);
        cos.gridx = 0;
        cos.gridy = 2;
        cos.insets = new Insets(0, 0, 0, 100);
        pnlEval.add(btnCorrect, cos);
        btnInCorrect = ButtonFactory.createImageButton(ICN_INCORRECT);
        btnInCorrect.setEnabled(false);
        cos.gridx = 2;
        cos.gridy = 2;
        cos.insets = new Insets(0, 100, 0, 0);
        pnlEval.add(btnInCorrect, cos);
    }

    private void initFrame() {
        GradientPanel mainPanel = new GradientPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
        mainPanel.add(pnlInfo);
        mainPanel.add(pnlFlashcard);
        mainPanel.add(pnlImage);
        mainPanel.add(pnlAnswer);
        mainPanel.add(this.pnlEval);
        studyFrame.add(mainPanel);
        studyFrame.setTitle(Messages.getString("study"));
        studyFrame.setSize(650, 780);
        studyFrame.setDefaultCloseOperation(3);
        studyFrame.setVisible(true);
    }

    private void loadImage() {
        imagePath =  this.engine.getCurrentCard().getCardPicture();
        ImageIcon icon = this.imagePath != null ? new ImageIcon(this.imagePath) : ICN_QUEST_IMG;
        lblQuestionImage.setIcon(FlashCardUtils.scale((ImageIcon)icon, 200, 200));
    }

    private void initListener() {
        btnShowAnswer.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				btnShowAnswer.setVisible(false);
				scrlAnswer.setVisible(true);
				btnCorrect.setEnabled(true);
				btnInCorrect.setEnabled(true);
			}
		});
        
        btnCorrect.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(engine.evaluateAnswer(true))
					btnCorrectInCorrect_Click();
				else {
					progressbar.setValue(progressbar.getMaximum());
					JOptionPane.showMessageDialog(studyFrame, "Fertig!");
					studyFrame.dispose();
					new FrmSelectStack();
				}
			}
		});
        
        btnInCorrect.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(engine.evaluateAnswer(false))
					btnCorrectInCorrect_Click();
			}
		});
    }

    private void btnCorrectInCorrect_Click() {
        int currentCard = progressbar.getValue() + 1;
        btnCorrect.setEnabled(false);
        btnInCorrect.setEnabled(false);
        scrlAnswer.setVisible(false);
        btnShowAnswer.setVisible(true);
        txtCard.setText(engine.getCurrentCard().getCardQuestion());
        txtAnswer.setText(engine.getCurrentCard().getCardAnswer());
        loadImage();
        progressbar.setValue(currentCard);
        lblCardCounter.setText("Q" + (currentCard + 1));
        lblBoxCounter.setText("Box: " + engine.getCurrentCard().getBoxCounter());
        lblCardname.setText(engine.getCurrentCard().getCardName());
        progresscircle.restart();
    }
}
