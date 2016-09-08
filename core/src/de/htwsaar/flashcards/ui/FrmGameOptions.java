package de.htwsaar.flashcards.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import de.htwsaar.flashcards.model.GameOption;
import de.htwsaar.flashcards.properties.Messages;
import de.htwsaar.flashcards.service.GameOptionService;
import de.htwsaar.flashcards.ui.component.GradientPanel;
import de.htwsaar.flashcards.ui.component.JSwitchBox;
import de.htwsaar.flashcards.util.ButtonFactory;
import de.htwsaar.flashcards.util.FlashCardUtils;

public class FrmGameOptions  {
	
	private static final int NR_OF_BOXES = 4;
	
	private JPanel pnlSelectSlot;
	private JPanel pnlSelectMode;
	private JPanel pnlModeButtons;
	private JPanel pnlSorted;
	private JPanel pnlTime;
	private JPanel pnlConfirm;
	private JPanel pnlLimit;
	private JPanel pnlDate;
	private JPanel pnlEval;
	
	private JComboBox<GameOption> cmbSlotSelection;
	private ButtonGroup grpModeButtons;

	private JRadioButton[] btnBox;
	private JSwitchBox switchSorted;
	private JSlider sldTime;
	private JCheckBox chkUnlimitedTime;
	private JCheckBox chkUnlimitedCards;
	private JCheckBox chkAllCards;
	
	private JSwitchBox switchConsiderDate;
	
	private JButton btnOk;
	private JButton btnCancel;
	private JSpinner spnLimit;
	
	private JComboBox<String> cmbEvaluationType;
	
	private JFrame frmGameOptions;

	private GameOptionService optionService;
	
	public FrmGameOptions() {
		frmGameOptions = new JFrame();
		optionService = new GameOptionService();
		
		initSelectSlotArea();
		initSelectModeArea();
		initSelectSortedArea();
		initTimeSelectionArea();
		initLimitArea();
		initDateArea();
		initEvaluationTypeArea();
		initConfirmationArea();
		initFrame();
		initListeners();
		configElements((GameOption)cmbSlotSelection.getSelectedItem());
	}
	
	private void initSelectSlotArea() {
		pnlSelectSlot = new JPanel(new GridBagLayout());
		pnlSelectSlot.setOpaque(false);
		cmbSlotSelection = new JComboBox<GameOption>(optionService.getGameOptionArray());
		GridBagConstraints gc = new GridBagConstraints();
		gc.anchor = GridBagConstraints.NORTHWEST;
		gc.insets = new Insets(5, 5, 0, 80);
		gc.gridx = gc.gridy = 0;
		pnlSelectSlot.add(new JLabel("Überschreibe: "), gc);
		gc.insets = new Insets(0, 20, 0, 120);
		gc.gridx = 1;
		pnlSelectSlot.add(cmbSlotSelection, gc);
		pnlSelectSlot.setBorder(BorderFactory.createTitledBorder("Welche Option überschreiben ?"));
	}
	
	private void initSelectModeArea() {
		pnlSelectMode = new JPanel(new GridLayout(2,1,0,5));
		pnlSelectMode.setOpaque(false);
		pnlModeButtons = new JPanel(new GridLayout(1,5, 10, 10));
		pnlModeButtons.setOpaque(false);
		grpModeButtons = new ButtonGroup();
		pnlModeButtons.setEnabled(false);
		btnBox = new JRadioButton[NR_OF_BOXES+1];
		btnBox[0] = ButtonFactory.createColouredRadioButton("1", ButtonFactory.BTN_RED);
		btnBox[1] = ButtonFactory.createColouredRadioButton("2", ButtonFactory.BTN_YELLOW);
		btnBox[2] = ButtonFactory.createColouredRadioButton("3", ButtonFactory.BTN_BLUE);
		btnBox[3] = ButtonFactory.createColouredRadioButton("4", ButtonFactory.BTN_GREEN);
		btnBox[4] = ButtonFactory.createColouredRadioButton("5", ButtonFactory.BTN_PURPLE);
		
		for (JRadioButton btn : btnBox) {
			grpModeButtons.add(btn);
			pnlModeButtons.add(btn);
			btn.setActionCommand(btn.getText());
		}
		
		chkAllCards = new JCheckBox("Alle Boxen");
		pnlSelectMode.add(pnlModeButtons);
		pnlSelectMode.add(chkAllCards);
	}
	
	private void initSelectSortedArea() {
		pnlSorted = new JPanel(new GridLayout(1, 1));
		pnlSorted.setOpaque(false);
		switchSorted = new JSwitchBox(Messages.getString("sorted"), Messages.getString("mixed")); 
		switchSorted.setEnabled(false);
		pnlSorted.add(switchSorted);
	}
	
	private void initTimeSelectionArea() {
		pnlTime = new JPanel(new GridLayout(2,1,10,0));
		pnlTime.setOpaque(false);
		sldTime = new JSlider(10, 130, 10);
		sldTime.setPreferredSize(new Dimension(205,50));
		sldTime.setMinorTickSpacing(10);
		sldTime.setMajorTickSpacing(20);
		sldTime.setPaintTicks(true);
		sldTime.setPaintLabels(true);
		sldTime.setLabelTable(sldTime.createStandardLabels(20));
		pnlTime.add(sldTime);
		chkUnlimitedTime = new JCheckBox(Messages.getString("without_timelimit")); 
		pnlTime.add(chkUnlimitedTime);
		chkUnlimitedTime.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				sldTime.setEnabled(chkUnlimitedTime.isSelected() ? false : true);
			}
		});
	}
	
	private void initLimitArea() {
		pnlLimit = new JPanel();
		chkUnlimitedCards = new JCheckBox("Unbegrenzt");
		pnlLimit.setOpaque(false);
		spnLimit = new JSpinner();
		SpinnerNumberModel model = new SpinnerNumberModel(5, 1, 50, 1);
		spnLimit.setModel(model);
		spnLimit.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
		pnlLimit.add(spnLimit);
		pnlLimit.add(chkUnlimitedCards);
	}
	
	private void initDateArea() {
		pnlDate = new JPanel();
		pnlDate.setOpaque(false);
		switchConsiderDate = new JSwitchBox("Ja", "Nein");
		pnlDate.add(switchConsiderDate);
	}
	
	private void initEvaluationTypeArea() {
		pnlEval = new JPanel();
		pnlEval.setOpaque(false);
		String[] types = {"Zurück in Box 1", "Eine Box zurück", "In Box bleiben"};
		cmbEvaluationType = new JComboBox<String>(types);
		pnlEval.add(cmbEvaluationType);
	}
	
	private void initConfirmationArea() {
		pnlConfirm = new JPanel(new GridLayout(1, 2, 20, 15));
		pnlConfirm.setOpaque(false);
		btnOk = ButtonFactory.createColouredButton(Messages.getString("ok"), ButtonFactory.BTN_GREEN); 
		btnOk.addActionListener(new OkButtonClickedListener());
		btnCancel = ButtonFactory.createColouredButton(Messages.getString("discard"), ButtonFactory.BTN_RED); 
		pnlConfirm.add(btnOk);
		pnlConfirm.add(btnCancel);
		pnlConfirm.setBorder(BorderFactory.createEmptyBorder(10, 100, 10, 100));
	}
	
	
	
	private void initFrame() {	
		JPanel containingPanel = new GradientPanel();
		containingPanel.setLayout(new BorderLayout());
		JPanel mainPanel = new JPanel();
		mainPanel.setOpaque(false);
		mainPanel.setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.gridx = gc.gridy = 0;
		gc.insets = new Insets(5, 0, 0, 20);
		mainPanel.add(new JLabel(Messages.getString("which_box")), gc); 
		gc.gridx = 1;
		gc.insets = new Insets(0, 20, 20, 0);
		mainPanel.add(pnlSelectMode, gc);
		gc.gridx = 0;
		gc.gridy++;
		gc.insets = new Insets(10, 0, 0, 20);
		mainPanel.add(new JLabel(Messages.getString("which_order")), gc); 
		gc.gridx = 1;
		gc.insets = new Insets(10, 20, 20, 0);
		mainPanel.add(pnlSorted, gc);
		gc.gridx = 0;
		gc.gridy++;
		gc.insets = new Insets(10, 0, 0, 20);
		mainPanel.add(new JLabel(Messages.getString("how_much_time")), gc); 
		gc.gridx = 1;
		gc.insets = new Insets(5, 17, 10, 0);
		mainPanel.add(pnlTime, gc);
		gc.gridx = 0;
		gc.gridy++;
		gc.insets = new Insets(10, 0, 0, 20);
		mainPanel.add(new JLabel("Anzahl Karten ?"), gc); 
		gc.gridx = 1;
		gc.insets = new Insets(0, 10, 20, 0);
		mainPanel.add(pnlLimit, gc);
		gc.gridx = 0;
		gc.gridy++;
		gc.insets = new Insets(10, 0, 0, 20);
		mainPanel.add(new JLabel("Datum berücksichtigen ?"), gc); 
		gc.gridx = 1;
		gc.insets = new Insets(5, 10, 20, 0);
		mainPanel.add(pnlDate, gc);
		gc.gridx = 0;
		gc.gridy++;
		gc.insets = new Insets(15, 0, 0, 20);
		mainPanel.add(new JLabel("Bei falscher Antwort ?"), gc); 
		gc.gridx = 1;
		gc.insets = new Insets(5, 7, 20, 0);
		mainPanel.add(pnlEval, gc);
		
		mainPanel.setBorder(BorderFactory.createTitledBorder("Wie möchtest du lernen ?"));
		containingPanel.add(pnlSelectSlot, BorderLayout.NORTH);
		containingPanel.add(mainPanel, BorderLayout.CENTER);
		containingPanel.add(pnlConfirm, BorderLayout.SOUTH);
		containingPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		frmGameOptions.add(containingPanel);
		frmGameOptions.setTitle(Messages.getString("options")); 
		frmGameOptions.setSize(550, 630);
		frmGameOptions.setResizable(false);
		frmGameOptions.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmGameOptions.setVisible(true);
	}
	
	private void initListeners() {
		chkAllCards.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean selected = chkAllCards.isSelected();
				for (JRadioButton button : btnBox) {
					button.setEnabled(selected ? false : true);
				}
				switchSorted.setEnabled(selected ? true : false);
				grpModeButtons.clearSelection();
			}
		});
		
		chkUnlimitedCards.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				spnLimit.setEnabled(chkUnlimitedCards.isSelected() ? false : true);
			}
		});
		
		cmbSlotSelection.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				configElements((GameOption)cmbSlotSelection.getSelectedItem());
			}
		});
		
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frmGameOptions.dispose();
				new FrmSelectStack();
			}
		});
	}
	
	private void configElements(GameOption option) {
		int box = option.getBoxOption();
		int time = option.getTime();
		int limit = option.getLimit();
		chkAllCards.setSelected(box == 0);
		for (int i = 0; i < btnBox.length; i++) {
			btnBox[i].setEnabled(box != 0);
			if (box != 0 && box == i) 
				grpModeButtons.setSelected(btnBox[i-1].getModel(), true);
		}
		if(time == 0) {
			chkUnlimitedTime.setSelected(true);
			sldTime.setEnabled(false);
			sldTime.setValue(time);
		}
		else {
			sldTime.setEnabled(true);
			sldTime.setValue(time);
		}
		if(limit == 0) {
			chkUnlimitedCards.setSelected(true);
			spnLimit.setEnabled(false);
		}
		else {
			spnLimit.setEnabled(true);
			spnLimit.setValue(limit);
		}
		switchSorted.setEnabled(box == 0);
		switchConsiderDate.setSelected(option.isDateSensitive());
		cmbEvaluationType.setSelectedIndex(option.getEvalType());
	}
	
	private class OkButtonClickedListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			GameOption selection = (GameOption)cmbSlotSelection.getSelectedItem();
			int id = selection.getId();
			String name = selection.getName();
			String desc = selection.getDescription();
			int boxOption = chkAllCards.isSelected() ? 0 : FlashCardUtils.getSelectedButtonIndex(btnBox);
			int time = chkUnlimitedTime.isSelected() ? 0 : sldTime.getValue();
			boolean sorted = switchSorted.isSelected();
			int limit = chkUnlimitedCards.isSelected() ? 0 : (int) spnLimit.getValue();
			boolean dateSensitive = switchConsiderDate.isSelected();
			int evalType = cmbEvaluationType.getSelectedIndex();
			
			GameOption option = new GameOption(id, name, desc, time, boxOption, sorted, limit, dateSensitive, evalType);
			new DlgSaveOptions(frmGameOptions, true, option);
		}
	}
}