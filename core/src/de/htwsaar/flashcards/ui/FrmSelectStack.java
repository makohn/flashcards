package de.htwsaar.flashcards.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;

import de.htwsaar.flashcards.model.FlashCard;
import de.htwsaar.flashcards.model.Stack;
import de.htwsaar.flashcards.properties.Messages;
import de.htwsaar.flashcards.service.EditFlashCardService;
import de.htwsaar.flashcards.service.FlashCardService;
import de.htwsaar.flashcards.service.StackService;
import de.htwsaar.flashcards.ui.component.GradientPanel;
import de.htwsaar.flashcards.ui.tableModels.FlashCardsTableModel;
import de.htwsaar.flashcards.util.ButtonFactory;

public class FrmSelectStack {
	
	private static final ImageIcon ICN_CREATE_STACK = new ImageIcon("res/images/create_stack.png");
	
	private JButton btnEdit;
	private JButton btnDelete;
	private JButton btnExport;
	private JButton btnStudy;
	private JButton btnCreateStack;
	private JComboBox<Stack> cmbStackSelector;
	private JPanel pnlSelection;
	private JScrollPane scrlPreview;
	private JPanel pnlButtons;
	private JTable tblStacksPreview;
	private FlashCardsTableModel tableModel;
	private JFrame selectStackFrame;
	private StackService stackService;
	private FlashCardService cardService;
	
	public FrmSelectStack() {
		stackService = new StackService();
		cardService = new FlashCardService();
		selectStackFrame = new JFrame();
		
		initSelectionArea();
		initPreviewArea();
		initButtonArea();
		initFrame();
		initListeners();
	}
	
	private void initSelectionArea() {
		pnlSelection = new JPanel();
		pnlSelection.setOpaque(false);
		
		cmbStackSelector = new JComboBox<Stack>(stackService.getStackArray());
		cmbStackSelector.addActionListener(new UpdateTableActionListener());
		
		btnCreateStack = ButtonFactory.createImageButton(ICN_CREATE_STACK);
		
		pnlSelection.add(cmbStackSelector);
		pnlSelection.add(btnCreateStack);
	}
	
	private void initPreviewArea() {
		tblStacksPreview = new JTable();
		List<FlashCard> flashcards = cardService.getFlashCards(getSelectedStack().getStackId());
		tableModel = new FlashCardsTableModel(flashcards);
		tblStacksPreview.setModel(tableModel);
		tblStacksPreview.setRowHeight(20);
		tblStacksPreview.setFillsViewportHeight(true);
		scrlPreview = new JScrollPane(tblStacksPreview);
		scrlPreview.setMinimumSize(new Dimension(570,300));
		scrlPreview.setBackground(new Color(230, 230, 230));
	}
	
	private void initButtonArea() {
		pnlButtons = new JPanel();
		pnlButtons.setOpaque(false);
		pnlButtons.setLayout(new GridLayout(1,4,5,5));
		
		btnStudy = ButtonFactory.createColouredButton(Messages.getString("play"), ButtonFactory.BTN_GREEN);
		btnEdit = ButtonFactory.createColouredButton(Messages.getString("edit"), ButtonFactory.BTN_BLUE);
		btnExport = ButtonFactory.createColouredButton(Messages.getString("export"), ButtonFactory.BTN_YELLOW);
		btnDelete = ButtonFactory.createColouredButton(Messages.getString("delete"), ButtonFactory.BTN_RED);
		
		pnlButtons.add(btnStudy);
		pnlButtons.add(btnEdit);
		pnlButtons.add(btnExport);
		pnlButtons.add(btnDelete);
		
		pnlButtons.setBorder(BorderFactory.createEmptyBorder(10,0,10,10));
	}

	private void initFrame() {
		UIManager.put("ToolTip.background", Color.white);
		JPanel mainPanel = new GradientPanel();
		
		mainPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		new Insets(0, 0, 0, 10);
		c.anchor = GridBagConstraints.BASELINE_LEADING;
		//-------------------------------------
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(15, 0, 0, 18);
		mainPanel.add(new JLabel(Messages.getString("choose_a_stack")), c);
		//-------------------------------------
		c.insets = new Insets(0,0,0,0);
		c.gridx = 1;
		mainPanel.add(pnlSelection, c);
		//-------------------------------------
		c.insets = new Insets(0, 0, 0, 20);
		c.gridx = 0;
		c.gridy = 1;
		mainPanel.add(new JLabel(Messages.getString("preview")), c);
		//-------------------------------------
		c.insets = new Insets(0,0,0,0);
		c.gridx = 1;
		c.gridy = 1;
		mainPanel.add(scrlPreview,c);
		//-------------------------------------
		c.gridx = 1;
		c.gridy = 2;
		mainPanel.add(pnlButtons,c);
		//-------------------------------------
		
		selectStackFrame.add(mainPanel);
		selectStackFrame.setVisible(true);
		selectStackFrame.setMinimumSize(new Dimension(730, 500));
		selectStackFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private Stack getSelectedStack() {
		return ((Stack)cmbStackSelector.getSelectedItem());
	}
	
	private void initListeners() {
		/*
		 * Listener: Bei Betätitgung des 'Spielen' Buttons öffnet sich das Fenster
		 * 			 zum Lernen der ausgewählten FlashCards
		 */
		btnStudy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new DlgGameOptions(selectStackFrame, true, getSelectedStack());
			}
		});
		
		/*
		 * Listener: Bei Betätitgung des 'Bearbeiten' Buttons öffnet sich eine Maske
		 * 			 zum Bearbeiten von FlashCards
		 */
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<FlashCard> cards = cardService.getFlashCards();	
				if (cards == null) cards = new ArrayList<FlashCard>();
				new FrmEditStack(new EditFlashCardService(cards.listIterator(), getSelectedStack().getStackId()));
				selectStackFrame.dispose();
			}
		});
		
		/*
		 * Listener: Wird eine Zeile in der Vorschautabelle per Doppelklick ausgewählt
		 * 			 so öffnet sich die Bearbeitungsmaske
		 */
		tblStacksPreview.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent me) {
				JTable table = (JTable) me.getSource();
				Point p = me.getPoint();
				int row = table.rowAtPoint(p);
				if (me.getClickCount() == 2 && row != -1) {
					new FrmEditStack(
							new EditFlashCardService(cardService.getFlashCards().listIterator(row), 
									getSelectedStack().getStackId()));
				}
			}
		});
	}
	
	private class UpdateTableActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			tableModel.setFlashCards(cardService.getFlashCards(getSelectedStack().getStackId()));
			tblStacksPreview.repaint();
		}
		
	}
}


