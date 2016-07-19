package de.htwsaar.flashcards.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class SelectStackFrame {
	
	private static final ImageIcon ICN_EDIT = new ImageIcon("res/images/edit.png");
	private static final ImageIcon ICN_EXPORT = new ImageIcon("res/images/export.png");
	private static final ImageIcon ICN_DELETE = new ImageIcon("res/images/delete.png");
	private static final ImageIcon ICN_STUDY = new ImageIcon("res/images/play.png");
	private static final ImageIcon ICN_CREATE_STACK = new ImageIcon("res/images/create_stack.png");
	private static final ImageIcon ICN_IMPORT = new ImageIcon("res/images/import.png");
	
	private JButton btnEdit;
	private JButton btnDelete;
	private JButton btnExport;
	private JButton btnStudy;
	private JButton btnCreateStack;
	private JButton btnImportStack;
	
	private JComboBox<String> cmbStackSelector;
	
	private JPanel pnlSelection;
	private JPanel pnlStackOptions;
	
	private JFrame selectStackFrame;
	
	/*tmp--------------------------*/
	private String[] stacks = {"Englisch", "Französisch", "Spanisch", "Russisch", "Rumänisch", "Deutsch" };
	/*-----------------------------*/
	
	public SelectStackFrame() {
		//lblStackName = new JLabel(stackName);
		selectStackFrame = new JFrame();
		initSelectionArea();
		initStackOptionsArea();
		initFrame();
	}
	
	private void initSelectionArea() {
		pnlSelection = new JPanel();
		pnlSelection.setOpaque(false);
		
		cmbStackSelector = new FilterComboBox(stacks);
		
		btnEdit = new JButton();
		btnEdit.setIcon(ICN_EDIT);
		btnEdit.setToolTipText("Bearbeiten");

		btnDelete = new JButton();
		btnDelete.setIcon(ICN_DELETE);
		btnDelete.setToolTipText("Löschen");
		
		btnExport = new JButton();
		btnExport.setIcon(ICN_EXPORT);
		btnExport.setToolTipText("Exportieren");
		
		btnStudy = new JButton();
		btnStudy.setIcon(ICN_STUDY);
		btnStudy.setToolTipText("Spielen");
		
		pnlSelection.add(cmbStackSelector);
		pnlSelection.add(btnEdit);
		pnlSelection.add(btnDelete);
		pnlSelection.add(btnExport);
		pnlSelection.add(btnStudy);
		pnlSelection.setBorder(BorderFactory.createEtchedBorder());
	}
	
	private void initStackOptionsArea() {
		pnlStackOptions = new JPanel();
		pnlStackOptions.setOpaque(false);
		
		btnCreateStack = new JButton();
		btnCreateStack.setIcon(ICN_CREATE_STACK);
		btnCreateStack.setToolTipText("Stack erstellen");
		
		btnImportStack = new JButton();
		btnImportStack.setIcon(ICN_IMPORT);
		btnImportStack.setToolTipText("Stack importieren");
		
		pnlStackOptions.add(btnCreateStack);
		pnlStackOptions.add(btnImportStack);
		pnlStackOptions.setBorder(BorderFactory.createEmptyBorder(10,0,10,10));
	}
	
	private void initFrame() {
		UIManager.put("ToolTip.background", Color.white);
		JPanel mainPanel = new GradientPanel();
		
		mainPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		//-------------------------------------
		c.gridx = 0;
		c.gridy = 0;
		c.weighty = 2;
		mainPanel.add(new JLabel("Wähle einen Stack:"), c);
		//-------------------------------------
		c.weighty = 1;
		c.gridy = 1;
		mainPanel.add(pnlSelection,c);
		//-------------------------------------
		c.gridy++;
		mainPanel.add(pnlStackOptions,c);
		//-------------------------------------
		
		selectStackFrame.add(mainPanel);
		selectStackFrame.setVisible(true);
		selectStackFrame.setMinimumSize(new Dimension(500, 200));
		selectStackFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		new SelectStackFrame();
	}
}

