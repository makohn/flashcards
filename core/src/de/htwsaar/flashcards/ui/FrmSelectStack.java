package de.htwsaar.flashcards.ui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;

import de.htwsaar.flashcards.model.FlashCard;
import de.htwsaar.flashcards.model.Stack;
import de.htwsaar.flashcards.properties.Messages;
import de.htwsaar.flashcards.service.EditFlashCardServiceImpl;
import de.htwsaar.flashcards.service.FlashCardServiceImpl;
import de.htwsaar.flashcards.service.StackServiceImpl;
import de.htwsaar.flashcards.service.interfaces.FlashCardService;
import de.htwsaar.flashcards.service.interfaces.StackService;
import de.htwsaar.flashcards.ui.component.GradientPanel;
import de.htwsaar.flashcards.ui.listeners.ExportFileListener;
import de.htwsaar.flashcards.ui.tableModels.FlashCardsTableModel;
import de.htwsaar.flashcards.util.FlashCardButtonFactory;
import de.htwsaar.flashcards.util.size.FrmSelectStackSizes;

/**
 * <code>FrmSelectStack</code> - Frame zum Auswaehlen eines Stacks. Zeigt eine
 * Vorschau der darin enthaltenen Karten und bietet folgende Optionen:
 * 		- Spielen
 * 		- Bearbeiten
 * 		- Exportieren
 * 		- Loeschen
 * 
 * @author Nora Sommer, Marek Kohn
 *
 */
public class FrmSelectStack {
	
	private static final ImageIcon ICN_CREATE_STACK = new ImageIcon("res/images/add_small.png");
	private static final ImageIcon ICN_EDIT_STACK = new ImageIcon("res/images/edit_small.png");
	
	private JButton btnEdit;
	private JButton btnDelete;
	private JButton btnExport;
	private JButton btnStudy;
	private JButton btnCreateStack;
	private JButton btnEditStack;
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
		stackService = new StackServiceImpl();
		cardService = new FlashCardServiceImpl();
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
		cmbStackSelector.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				updateTableView();
			}
		});
		
		btnCreateStack = FlashCardButtonFactory.createImageButton(ICN_CREATE_STACK);
		btnEditStack =  FlashCardButtonFactory.createImageButton(ICN_EDIT_STACK);
		pnlSelection.add(cmbStackSelector);
		pnlSelection.add(btnCreateStack);
		pnlSelection.add(btnEditStack);
	}
	
	private void initPreviewArea() {
		tblStacksPreview = new JTable();
		List<FlashCard> flashcards = cardService.getFlashCards(getSelectedStack().getStackId());
		tableModel = new FlashCardsTableModel(flashcards);
		tblStacksPreview.setModel(tableModel);
		tblStacksPreview.setRowHeight(FrmSelectStackSizes.TABLE_ROW_HEIGHT);
		tblStacksPreview.setFillsViewportHeight(true);
		scrlPreview = new JScrollPane(tblStacksPreview);
		scrlPreview.setMinimumSize(FrmSelectStackSizes.DIM_PREVIEW_AREA_MIN);
		scrlPreview.setBackground(new Color(230, 230, 230));
	}
	
	private void initButtonArea() {
		pnlButtons = new JPanel();
		pnlButtons.setOpaque(false);
		pnlButtons.setLayout(new GridLayout(1,4,5,5));
		
		btnStudy = FlashCardButtonFactory.createColouredButton(Messages.getString("play"), FlashCardButtonFactory.BTN_GREEN);
		btnEdit = FlashCardButtonFactory.createColouredButton(Messages.getString("edit"), FlashCardButtonFactory.BTN_BLUE);
		btnExport = FlashCardButtonFactory.createColouredButton(Messages.getString("export"), FlashCardButtonFactory.BTN_YELLOW);
		btnDelete = FlashCardButtonFactory.createColouredButton(Messages.getString("delete"), FlashCardButtonFactory.BTN_RED);
		
		pnlButtons.add(btnStudy);
		pnlButtons.add(btnEdit);
		pnlButtons.add(btnExport);
		pnlButtons.add(btnDelete);
		
		pnlButtons.setBorder(FrmSelectStackSizes.BORDER_PNL_BUTTONS);
	}

	private void initFrame() {
		UIManager.put("ToolTip.background", Color.white);
		JPanel mainPanel = new GradientPanel();
		
		mainPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.BASELINE_LEADING;
		//-------------------------------------
		c.gridx = 0;
		c.gridy = 0;
		c.insets = FrmSelectStackSizes.INSETS_MARGIN_TOPLEFT;
		mainPanel.add(new JLabel(Messages.getString("choose_a_stack")), c);
		//-------------------------------------
		c.insets = FrmSelectStackSizes.INSETS_RESET;
		c.gridx = 1;
		mainPanel.add(pnlSelection, c);
		//-------------------------------------
		c.insets = FrmSelectStackSizes.INSETS_MARGIN_LEFT;
		c.gridx = 0;
		c.gridy = 1;
		mainPanel.add(new JLabel(Messages.getString("preview")), c);
		//-------------------------------------
		c.insets = FrmSelectStackSizes.INSETS_RESET;
		c.gridx = 1;
		c.gridy = 1;
		mainPanel.add(scrlPreview,c);
		//-------------------------------------
		c.gridx = 1;
		c.gridy = 2;
		mainPanel.add(pnlButtons,c);
		//-------------------------------------
		
		selectStackFrame.add(mainPanel);
		selectStackFrame.setMinimumSize(FrmSelectStackSizes.DIM_FRAME);
		selectStackFrame.setJMenuBar(MenuBar.createMenuBar(selectStackFrame, () -> update()));
		selectStackFrame.setLocationRelativeTo(null);
		selectStackFrame.setVisible(true);
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
				new FrmEditStack(new EditFlashCardServiceImpl(cards.listIterator(), getSelectedStack().getStackId()));
				selectStackFrame.toBack();
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
							new EditFlashCardServiceImpl(cardService.getFlashCards().listIterator(row), 
									getSelectedStack().getStackId()));
				}
			}
		});
		
		btnCreateStack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new DlgCreateStack(selectStackFrame, true);
				cmbStackSelector.setModel(new DefaultComboBoxModel<Stack>(stackService.getStackArray()));
			}
		});
		
		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int option = JOptionPane.showConfirmDialog(selectStackFrame, "Diesen Stack wirklich löschen ?");
				if(option == JOptionPane.YES_OPTION) {
					stackService.deleteStack((Stack)cmbStackSelector.getSelectedItem());
					cmbStackSelector.setModel(new DefaultComboBoxModel<Stack>(stackService.getStackArray()));
					updateTableView();
				}
			}
		});
		
		btnExport.addActionListener(new ExportFileListener(cmbStackSelector, selectStackFrame));
	}
	
	private void updateTableView() {
		tableModel.setFlashCards(cardService.getFlashCards(getSelectedStack().getStackId()));
		tblStacksPreview.repaint();
	}
	
	private Void update() {
		cmbStackSelector.setModel(new DefaultComboBoxModel<Stack>(stackService.getStackArray()));
		updateTableView();
		return null;
	}
}


