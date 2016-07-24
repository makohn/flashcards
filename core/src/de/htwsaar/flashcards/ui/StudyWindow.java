package de.htwsaar.flashcards.ui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
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
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.border.Border;

import de.htwsaar.flashcards.engine.GameEngineImpl;
import de.htwsaar.flashcards.engine.interfaces.GameEngine;
import de.htwsaar.flashcards.ui.component.GradientPanel;
import de.htwsaar.flashcards.ui.component.JProgressCircle;
import de.htwsaar.flashcards.util.SwingUtils;

public class StudyWindow {
	
    private static final Font FONT_CARD = new Font("SansSerif", 0, 20);
    private static final Font FONT_COUNTER = new Font("SansSerif", 2, 20);
    private static final ImageIcon ICN_CORRECT = new ImageIcon("res/images/true.png");
    private static final ImageIcon ICN_INCORRECT = new ImageIcon("res/images/false.png");
    private static final ImageIcon ICN_QUEST_IMG = new ImageIcon("res/images/questionmarks.png");
    private static final Border INNER_CARD_BORDER = BorderFactory.createLineBorder(Color.GRAY);
    private static final Border OUTER_CARD_BORDER = BorderFactory.createEmptyBorder(10, 10, 10, 10);
    
    private GameEngine engine;
    
    private JTextArea txtCard;
    private JTextArea txtAnswer;
    private JLabel lblStackname;
    private JLabel lblCardCounter;
    private JProgressBar progressbar;
    private JProgressCircle progresscircle;
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
    private JFrame studyWindow;

    public StudyWindow() throws ClassNotFoundException {
    	engine = new GameEngineImpl();
    	studyWindow = new JFrame();
    	
    	studyWindow.setResizable(false);
    	
        this.initInfoArea();
        this.initQuestionArea();
        this.initImageArea();
        this.initAnswerArea();
        this.initEvaluationArea();
        this.initFrame();
        this.initListener();
    }

    private void initInfoArea() {
    	FlowLayout layout = new FlowLayout();
        this.pnlInfo = new JPanel(layout);
        this.pnlInfo.setOpaque(false);
        this.pnlInfo.setBorder(OUTER_CARD_BORDER);
        this.progressbar = new JProgressBar(0, this.engine.getNrCards());
        this.progresscircle = new JProgressCircle(20);
        this.progresscircle.setPreferredSize(new Dimension(50, 50));
        this.lblCardCounter = new JLabel("Q1");
        this.lblCardCounter.setFont(new Font("SansSerif", 1, 20));
        this.lblStackname = new JLabel("Test");
        this.lblStackname.setFont(FONT_COUNTER);
        this.pnlInfo.add(this.lblCardCounter);
        layout.setHgap(110);
        this.pnlInfo.add(this.progressbar);
        layout.setHgap(110);
        this.pnlInfo.add(progresscircle);
    }

    private void initQuestionArea() {
        this.pnlFlashcard = new JPanel(new CardLayout());
        this.pnlFlashcard.setOpaque(false);
        this.pnlFlashcard.setBorder(OUTER_CARD_BORDER);
        this.txtCard = new JTextArea();
        this.txtCard.setLineWrap(true);
        this.txtCard.setWrapStyleWord(true);
        this.txtCard.setEditable(false);
        this.txtCard.setBorder(INNER_CARD_BORDER);
        this.txtCard.setFont(FONT_CARD);
        this.txtCard.setText(""/*this.engine.getCurrentCard().getCardQuestion()*/);
        this.pnlFlashcard.add(this.txtCard);
    }

    private void initAnswerArea() {
        this.pnlAnswer = new JPanel(new CardLayout());
        this.pnlAnswer.setOpaque(false);
        this.pnlAnswer.setBorder(OUTER_CARD_BORDER);
        this.btnShowAnswer = new JButton("Antwort");
        this.btnShowAnswer.setBorder(BorderFactory.createLineBorder(new Color(0, 163, 204)));
        this.btnShowAnswer.setBackground(new Color(0, 163, 204));
        this.btnShowAnswer.setForeground(Color.WHITE);
        this.btnShowAnswer.setOpaque(true);
        this.pnlAnswer.add(this.btnShowAnswer);
        this.txtAnswer = new JTextArea();
        this.txtAnswer.setLineWrap(true);
        this.txtAnswer.setWrapStyleWord(true);
        this.txtAnswer.setEditable(false);
        this.txtAnswer.setFont(FONT_CARD);
        this.txtAnswer.setBorder(INNER_CARD_BORDER);
        this.txtAnswer.setText(""/*this.engine.getCurrentCard().getCardAnswer()*/);
        this.txtAnswer.setVisible(false);
        this.pnlAnswer.add(this.txtAnswer);
    }

    private void initImageArea() {
        this.pnlImage = new JPanel();
        this.pnlImage.setOpaque(false);
        this.lblQuestionImage = new JLabel();
        this.imagePath = "";
        this.loadImage();
        this.pnlImage.add(this.lblQuestionImage);
    }

    private void initEvaluationArea() {
        this.pnlEval = new JPanel(new GridBagLayout());
        this.pnlEval.setOpaque(false);
        GridBagConstraints cos = new GridBagConstraints();
        this.btnCorrect = new JButton(ICN_CORRECT);
        this.btnCorrect.setContentAreaFilled(false);
        this.btnCorrect.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        this.btnCorrect.setEnabled(false);
        cos.gridx = 0;
        cos.gridy = 2;
        cos.insets = new Insets(0, 0, 0, 100);
        this.pnlEval.add(btnCorrect, cos);
        this.btnInCorrect = new JButton(ICN_INCORRECT);
        this.btnInCorrect.setContentAreaFilled(false);
        this.btnInCorrect.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        this.btnInCorrect.setEnabled(false);
        cos.gridx = 2;
        cos.gridy = 2;
        cos.insets = new Insets(0, 100, 0, 0);
        this.pnlEval.add(btnInCorrect, cos);
    }

    private void initFrame() {
        UIManager.put("ToolTip.background", Color.white);
        GradientPanel mainPanel = new GradientPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
        mainPanel.add(pnlInfo);
        mainPanel.add(pnlFlashcard);
        mainPanel.add(pnlImage);
        mainPanel.add(pnlAnswer);
        mainPanel.add(this.pnlEval);
        this.studyWindow.add(mainPanel);
        this.studyWindow.setTitle("Study");
        this.studyWindow.setSize(650, 780);
        this.studyWindow.setDefaultCloseOperation(3);
        this.studyWindow.setVisible(true);
    }

    private void loadImage() {
        this.imagePath =  null /*this.engine.getCurrentCard().getCardPicture()*/;
        ImageIcon icon = this.imagePath != null ? new ImageIcon(this.imagePath) : ICN_QUEST_IMG;
        this.lblQuestionImage.setIcon(SwingUtils.scale((ImageIcon)icon, (int)200, (int)200));
    }

    private void initListener() {
        this.btnShowAnswer.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				btnShowAnswer.setVisible(false);
				txtAnswer.setVisible(true);
				btnCorrect.setVisible(true);
				btnInCorrect.setVisible(true);
			}
		});
        
        this.btnCorrect.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				engine.evaluateAnswer(true);
				btnCorrectInCorrect_Click();
			}
		});
        
        this.btnInCorrect.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				engine.evaluateAnswer(false);
				btnCorrectInCorrect_Click();
			}
		});
    }

    private void btnCorrectInCorrect_Click() {
        int currentCard = this.progressbar.getValue() + 1;
        this.btnCorrect.setEnabled(false);
        this.btnInCorrect.setEnabled(false);
        this.txtAnswer.setVisible(false);
        this.btnShowAnswer.setVisible(true);
        this.txtCard.setText(this.engine.getCurrentCard().getCardQuestion());
        this.txtAnswer.setText(this.engine.getCurrentCard().getCardAnswer());
        this.loadImage();
        this.progressbar.setValue(currentCard);
        this.lblCardCounter.setText("Q" + (currentCard + 1));
        this.progresscircle.restart();
    }
}
