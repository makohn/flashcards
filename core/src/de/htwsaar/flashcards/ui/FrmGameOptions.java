package de.htwsaar.flashcards.ui;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
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
import de.htwsaar.flashcards.service.GameOptionServiceImpl;
import de.htwsaar.flashcards.service.interfaces.GameOptionService;
import de.htwsaar.flashcards.ui.component.GradientPanel;
import de.htwsaar.flashcards.ui.component.JSwitchBox;
import de.htwsaar.flashcards.util.FlashCardButtonFactory;
import de.htwsaar.flashcards.util.FlashCardUtils;
import de.htwsaar.flashcards.util.size.FrmGameOptionsSizes;

/**
 * <code>FrmGameOptions</code> - Frame zum Einstellen der Spieleoptionen:
 *
 * 		- Welche Box ? Oder Alle Karten eines Stacks ?
 * 		- Gemischt vs. Sortiert
 * 		- Zeitlimit
 * 		- Anzahl der Karten
 * 		- Zeitstempel beruecksichtigen ?
 * 		- Konsequenz bei falscher Antwort 
 * 
 * Die Optionen koennen persistent in der Datenbank hinterlegt werden. Allerdings
 * koennen keine neuen Optionen erstellt werden, diese sind auf ein Maximum von 
 * vier beschraenkt. Dies wird durch einen entsprechenden Trigger sichergestellt.
 * 
 * @author Ben Meder, Marek Kohn
 * @see DlgGameOptions, DlgSaveOptions
 *
 */
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
		optionService = new GameOptionServiceImpl();
		
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
		gc.insets = FrmGameOptionsSizes.INSETS_SELECT_SLOT_LABEL;
		gc.gridx = gc.gridy = 0;
		pnlSelectSlot.add(new JLabel(Messages.getString("overwrite")), gc);
		gc.insets = FrmGameOptionsSizes.INSETS_SELECT_SLOT_COMBO;
		gc.gridx = 1;
		pnlSelectSlot.add(cmbSlotSelection, gc);
		pnlSelectSlot.setBorder(BorderFactory.createTitledBorder(Messages.getString("which_option")));
	}
	
	private void initSelectModeArea() {
		pnlSelectMode = new JPanel(new GridLayout(2,1,0,5));
		pnlSelectMode.setOpaque(false);
		pnlModeButtons = new JPanel(new GridLayout(1,5, 10, 10));
		pnlModeButtons.setOpaque(false);
		grpModeButtons = new ButtonGroup();
		pnlModeButtons.setEnabled(false);
		btnBox = new JRadioButton[NR_OF_BOXES+1];
		btnBox[0] = FlashCardButtonFactory.createColouredRadioButton("1", FlashCardButtonFactory.BTN_RED);
		btnBox[1] = FlashCardButtonFactory.createColouredRadioButton("2", FlashCardButtonFactory.BTN_YELLOW);
		btnBox[2] = FlashCardButtonFactory.createColouredRadioButton("3", FlashCardButtonFactory.BTN_BLUE);
		btnBox[3] = FlashCardButtonFactory.createColouredRadioButton("4", FlashCardButtonFactory.BTN_GREEN);
		btnBox[4] = FlashCardButtonFactory.createColouredRadioButton("5", FlashCardButtonFactory.BTN_PURPLE);
		
		for (JRadioButton btn : btnBox) {
			grpModeButtons.add(btn);
			pnlModeButtons.add(btn);
			btn.setActionCommand(btn.getText());
		}
		
		chkAllCards = new JCheckBox(Messages.getString("all_boxes"));
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
		sldTime.setPreferredSize(FrmGameOptionsSizes.DIM_TIME_SLIDER);
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
		chkUnlimitedCards = new JCheckBox(Messages.getString("unlimited"));
		pnlLimit.setOpaque(false);
		spnLimit = new JSpinner();
		SpinnerNumberModel model = new SpinnerNumberModel(5, 1, 50, 1);
		spnLimit.setModel(model);
		spnLimit.setBorder(FrmGameOptionsSizes.BORDER_LIMIT_AREA);
		pnlLimit.add(spnLimit);
		pnlLimit.add(chkUnlimitedCards);
	}
	
	private void initDateArea() {
		pnlDate = new JPanel();
		pnlDate.setOpaque(false);
		switchConsiderDate = new JSwitchBox(Messages.getString("yes"), Messages.getString("no"));
		pnlDate.add(switchConsiderDate);
	}
	
	private void initEvaluationTypeArea() {
		pnlEval = new JPanel();
		pnlEval.setOpaque(false);
		String[] types = {Messages.getString("back_to_first"), 
				Messages.getString("one_box_back"),
				Messages.getString("stay_in_box")};
		cmbEvaluationType = new JComboBox<String>(types);
		pnlEval.add(cmbEvaluationType);
	}
	
	private void initConfirmationArea() {
		pnlConfirm = new JPanel(new GridLayout(1, 2, 20, 15));
		pnlConfirm.setOpaque(false);
		btnOk = FlashCardButtonFactory.createColouredButton(Messages.getString("ok"), FlashCardButtonFactory.BTN_GREEN); 
		btnOk.addActionListener(new OkButtonClickedListener());
		btnCancel = FlashCardButtonFactory.createColouredButton(Messages.getString("cancel"), FlashCardButtonFactory.BTN_RED); 
		pnlConfirm.add(btnOk);
		pnlConfirm.add(btnCancel);
		pnlConfirm.setBorder(FrmGameOptionsSizes.BORDER_CONFIRM_AREA);
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
		gc.insets = FrmGameOptionsSizes.INSETS_MARGIN_BOXES_LBL;
		mainPanel.add(new JLabel(Messages.getString("which_box")), gc); 
		gc.gridx = 1;
		gc.insets = FrmGameOptionsSizes.INSETS_MARGIN_BOXES;
		mainPanel.add(pnlSelectMode, gc);
		gc.gridx = 0;
		gc.gridy++;
		gc.insets = FrmGameOptionsSizes.INSETS_DEFAULT_MARGIN_LBL;
		mainPanel.add(new JLabel(Messages.getString("which_order")), gc); 
		gc.gridx = 1;
		gc.insets = FrmGameOptionsSizes.INSETS_MARGIN_SORTED;
		mainPanel.add(pnlSorted, gc);
		gc.gridx = 0;
		gc.gridy++;
		gc.insets = FrmGameOptionsSizes.INSETS_DEFAULT_MARGIN_LBL;
		mainPanel.add(new JLabel(Messages.getString("how_much_time")), gc); 
		gc.gridx = 1;
		gc.insets = FrmGameOptionsSizes.INSETS_MARGIN_TIME;
		mainPanel.add(pnlTime, gc);
		gc.gridx = 0;
		gc.gridy++;
		gc.insets = FrmGameOptionsSizes.INSETS_DEFAULT_MARGIN_LBL;
		mainPanel.add(new JLabel(Messages.getString("number_cards")), gc); 
		gc.gridx = 1;
		gc.insets = FrmGameOptionsSizes.INSETS_MARGIN_LIMIT;
		mainPanel.add(pnlLimit, gc);
		gc.gridx = 0;
		gc.gridy++;
		gc.insets = FrmGameOptionsSizes.INSETS_DEFAULT_MARGIN_LBL;
		mainPanel.add(new JLabel(Messages.getString("consider_date")), gc); 
		gc.gridx = 1;
		gc.insets = FrmGameOptionsSizes.INSETS_MARGIN_DATE;
		mainPanel.add(pnlDate, gc);
		gc.gridx = 0;
		gc.gridy++;
		gc.insets = FrmGameOptionsSizes.INSETS_MARGIN_WRONG_ANS;
		mainPanel.add(new JLabel(Messages.getString("wrong_answer")), gc); 
		gc.gridx = 1;
		gc.insets = FrmGameOptionsSizes.INSETS_MARGIN_EVAL;
		mainPanel.add(pnlEval, gc);
		
		mainPanel.setBorder(BorderFactory.createTitledBorder(Messages.getString("how_to_learn")));
		containingPanel.add(pnlSelectSlot, BorderLayout.NORTH);
		containingPanel.add(mainPanel, BorderLayout.CENTER);
		containingPanel.add(pnlConfirm, BorderLayout.SOUTH);
		containingPanel.setBorder(FrmGameOptionsSizes.BORDER_MAIN);
		frmGameOptions.add(containingPanel);
		frmGameOptions.setTitle(Messages.getString("options")); 
		frmGameOptions.setSize(FrmGameOptionsSizes.DIM_FRAME);
		frmGameOptions.setResizable(false);
		frmGameOptions.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmGameOptions.setLocationRelativeTo(null);
		frmGameOptions.toFront();
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