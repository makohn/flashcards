package de.htwsaar.flashcards.ui;

import java.util.ListIterator;
import java.util.concurrent.Callable;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import de.htwsaar.flashcards.model.FlashCard;
import de.htwsaar.flashcards.properties.Messages;
import de.htwsaar.flashcards.service.interfaces.StudyService;
import de.htwsaar.flashcards.ui.component.GradientPanel;
import de.htwsaar.flashcards.ui.component.InfoPanel;
import de.htwsaar.flashcards.util.FlashCardUtils;

/**
 * <code>FrmStudy</code> - Wrapper Frame fuer ein Spiel. Enthaelt die Basis-
 * funktionalitaet. Ordnet die mittels einer Fabrik (<code>StudyTypeUIFactory</code>)
 * erzeugten Kompontenten im Frame an. Besteht dementsprechend aus:
 * 		- Infobereich
 * 		- Fragebereich 		   <- Erzeugung durch Fabrik
 * 		- Bildbereich  
 * 		- Antwortbereich       <- Erzeugung durch Fabrik
 * 		- Evaluationsbereich   <- Erzeugung durch Fabrik
 * 
 * @author Marco Becker, mkohn
 * @see StudyTypeUIFactory, SelfEvalFactory, VocabUIFactory
 */
public class FrmStudy {
	
    private static final ImageIcon ICN_QUEST_IMG = new ImageIcon("res/images/questionmarks.png");
 											 
    private JLabel lblQuestionImage;
    private String imagePath;
    private InfoPanel pnlInfo;
    private JPanel pnlFlashcard;
    private JPanel pnlImage;
    private JPanel pnlAnswer;
    private JPanel pnlEval;
    private JFrame studyFrame;
    
    private FlashCard currentCard;
    private ListIterator<FlashCard> cardIterator;
    private int nrOfCards;
    
    private StudyTypeUIFactory uiFactory;
    private StudyService studyService;
    
    public FrmStudy(StudyService studyService, StudyTypeUIFactory uiFactory) {
    	this.uiFactory = uiFactory;
    	this.studyService = studyService;
    	cardIterator = studyService.getFlashCards().listIterator();
    	currentCard = cardIterator.next();
    	nrOfCards = studyService.getNrOfCards();
    	
    	studyFrame = new JFrame();
    	studyFrame.setResizable(false);
    	
    	initInfoArea();
        pnlFlashcard = uiFactory.createQuestionPanel(false); 
        pnlAnswer = uiFactory.createAnswerPanel(false);
        initImageArea();
        pnlEval = uiFactory.createEvaluationPanel();
        initFrame();
        uiFactory.updateCard(currentCard);
        uiFactory.setListeners((answer) -> {return nextQuestion(answer);},
        					   (timerStopped) -> {return stopTimer();});
    }
    
    private void initInfoArea() {
    	int time = studyService.getTime();
    	pnlInfo = new InfoPanel(nrOfCards, time, uiFactory.getSpacing());
    	pnlInfo.update(currentCard.getCardName(), currentCard.getBoxCounter());
    	pnlInfo.setHandler(new Callable<Void>() {
			@Override
			public Void call() throws Exception {
				JOptionPane.showMessageDialog(studyFrame, Messages.getString("times_up"));
				nextQuestion(false);
				return null;
			}
		});
    }

    private void initImageArea() {
        pnlImage = new JPanel();
        pnlImage.setOpaque(false);
        lblQuestionImage = new JLabel();
        imagePath = "";
        loadImage();
        pnlImage.add(this.lblQuestionImage);
    }

    private void initFrame() {
        GradientPanel mainPanel = new GradientPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
        mainPanel.add(pnlInfo);
        mainPanel.add(pnlFlashcard);
        mainPanel.add(pnlImage); 
        mainPanel.add(pnlAnswer);
        mainPanel.add(pnlEval);
        
        studyFrame.add(mainPanel);
        studyFrame.setTitle(Messages.getString("study"));
        studyFrame.setSize(uiFactory.getFrameSize());
        studyFrame.setLocationRelativeTo(null);
        studyFrame.setVisible(true);
        studyFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void loadImage() {
        imagePath =  currentCard.getCardPicture();
        ImageIcon icon = this.imagePath != null ? new ImageIcon(this.imagePath) : ICN_QUEST_IMG;
        lblQuestionImage.setIcon(FlashCardUtils.scale((ImageIcon)icon, 200, 200));
    }
    
    private FlashCard nextQuestion(boolean answer) {
    	studyService.saveCard(currentCard, answer);
    	if(cardIterator.hasNext()) {
			currentCard = cardIterator.next();
		    loadImage();
		    pnlInfo.update(currentCard.getCardName(), currentCard.getBoxCounter());
    	}
    	else {
			pnlInfo.finish();
			JOptionPane.showMessageDialog(studyFrame, Messages.getString("finished")); 
			studyFrame.dispose();
			new FrmSelectStack();
    	}
    	return currentCard;
    }
    
    private Void stopTimer() {
    	pnlInfo.stopTimer();
    	return null;
    }
}
