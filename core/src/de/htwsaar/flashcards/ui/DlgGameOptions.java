package de.htwsaar.flashcards.ui;

import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JSlider;

import de.htwsaar.flashcards.engine.GameEngineImpl;
import de.htwsaar.flashcards.engine.interfaces.GameEngine;
import de.htwsaar.flashcards.service.FlashCardService;
import de.htwsaar.flashcards.ui.component.GradientPanel;
import de.htwsaar.flashcards.ui.component.JSwitchBox;
import de.htwsaar.flashcards.util.ButtonFactory;

public class DlgGameOptions extends JDialog {
	

	private static final long serialVersionUID = -2839414417140882998L;
	private static final int NR_OF_BOXES = 4;
	
	private JPanel pnlModeButtons;
	private JPanel pnlSorted;
	private JPanel pnlTime;
	private JPanel pnlConfirm;
	
	private ButtonGroup grpModeButtons;

	private JRadioButton[] btnBox;
	private JSwitchBox switchSorted;
	private JSlider sldTime;
	private JCheckBox chkUnlimitedTime;
	
	private JButton btnOk;
	private JButton btnCancel;
	
	private FlashCardService cardService;
	private String stack;
	
	private JDialog self;

	public DlgGameOptions(Frame owner, boolean modal, String stack) {
		
		super(owner, modal);
		this.stack = stack;
		this.cardService = new FlashCardService();
		self = this;
		
		initSelectModeArea();
		initSelectSortedArea();
		initTimeSelectionArea();
		initConfirmationArea();
		initFrame();
	}
	
	private void initSelectModeArea() {
		pnlModeButtons = new JPanel(new GridLayout(1,4, 10, 10));
		pnlModeButtons.setOpaque(false);
		grpModeButtons = new ButtonGroup();
		btnBox = new JRadioButton[NR_OF_BOXES+1];
		btnBox[0] = ButtonFactory.createColouredRadioButton("1", ButtonFactory.BTN_RED);
		btnBox[1] = ButtonFactory.createColouredRadioButton("2", ButtonFactory.BTN_YELLOW);
		btnBox[2] = ButtonFactory.createColouredRadioButton("3", ButtonFactory.BTN_BLUE);
		btnBox[3] = ButtonFactory.createColouredRadioButton("4", ButtonFactory.BTN_GREEN);
		btnBox[4] = ButtonFactory.createColouredRadioButton("*", ButtonFactory.BTN_PURPLE);
		btnBox[4].setToolTipText("Alle");
		
		for (JRadioButton btn : btnBox) {
			grpModeButtons.add(btn);
			pnlModeButtons.add(btn);
			btn.addActionListener(new AllSelectedListener());
		}
	}
	
	private void initSelectSortedArea() {
		pnlSorted = new JPanel(new GridLayout(1, 1));
		pnlSorted.setOpaque(false);
		switchSorted = new JSwitchBox("Sortiert ", "Gemischt ");
		switchSorted.setEnabled(false);
		pnlSorted.add(switchSorted);
	}
	
	private void initTimeSelectionArea() {
		pnlTime = new JPanel(new GridLayout(2,1,10,0));
		pnlTime.setOpaque(false);
		sldTime = new JSlider(10, 160, 20);
		sldTime.setMinorTickSpacing(5);
		sldTime.setMajorTickSpacing(30);
		sldTime.setPaintTicks(true);
		sldTime.setPaintLabels(true);
		sldTime.setLabelTable(sldTime.createStandardLabels(30));
		pnlTime.add(sldTime);
		chkUnlimitedTime = new JCheckBox("ohne Zeitlimit");
		pnlTime.add(chkUnlimitedTime);
		chkUnlimitedTime.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				sldTime.setEnabled(chkUnlimitedTime.isSelected() ? false : true);
			}
		});
	}
	
	private void initConfirmationArea() {
		pnlConfirm = new JPanel(new GridLayout(1, 2, 20, 15));
		pnlConfirm.setOpaque(false);
		btnOk = ButtonFactory.createColouredButton("Ok", ButtonFactory.BTN_GREEN);
		btnOk.addActionListener(new OkButtonClickedListener());
		btnCancel = ButtonFactory.createColouredButton("Abbrechen", ButtonFactory.BTN_RED);
		pnlConfirm.add(btnOk);
		pnlConfirm.add(btnCancel);
	}
	
	private void initFrame() {
		
		JPanel mainPanel = new GradientPanel();
		mainPanel.setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.gridx = gc.gridy = 0;
		gc.insets = new Insets(5, 0, 0, 20);
		mainPanel.add(new JLabel("Welche Box ?"), gc);
		gc.gridx = 1;
		gc.insets = new Insets(0, 20, 20, 0);
		mainPanel.add(pnlModeButtons, gc);
		gc.gridx = 0;
		gc.gridy++;
		gc.insets = new Insets(10, 0, 0, 20);
		mainPanel.add(new JLabel("Welche Reihenfolge ?"), gc);
		gc.gridx = 1;
		gc.insets = new Insets(10, 20, 20, 0);
		mainPanel.add(pnlSorted, gc);
		gc.gridx = 0;
		gc.gridy++;
		gc.insets = new Insets(10, 0, 0, 20);
		mainPanel.add(new JLabel("Wie viel Zeit ?"), gc);
		gc.gridx = 1;
		gc.insets = new Insets(5, 17, 20, 0);
		mainPanel.add(pnlTime, gc);
		gc.gridx = 0;
		gc.gridy++;
		gc.gridwidth = 2;
		gc.anchor = GridBagConstraints.CENTER;
		mainPanel.add(new JSeparator(JSeparator.HORIZONTAL),gc);
		mainPanel.add(pnlConfirm, gc);
		
		add(mainPanel);
		setTitle("Optionen");
		setSize(500, 300);
		setResizable(false);
		setLocation((getOwner().getX()), (getOwner().getY()));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	
	private class AllSelectedListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			switchSorted.setEnabled(btnBox[NR_OF_BOXES].isSelected() ? true : false);	
		}
	}
	
	private class OkButtonClickedListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			cardService.getFlashCards(stack);
			GameEngine engine = new GameEngineImpl(cardService.getFlashCards());
			new FrmStudy(engine);
			self.dispose();
			self.getOwner().dispose();
		}
		
	}
}
