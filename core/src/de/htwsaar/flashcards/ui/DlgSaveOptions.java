package de.htwsaar.flashcards.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import de.htwsaar.flashcards.model.GameOption;
import de.htwsaar.flashcards.properties.Messages;
import de.htwsaar.flashcards.service.GameOptionService;
import de.htwsaar.flashcards.ui.component.GradientPanel;
import de.htwsaar.flashcards.util.ButtonFactory;

public class DlgSaveOptions extends JDialog {
	
	private static final long serialVersionUID = 5825052235347813043L;
	
	private JPanel pnlName;
	private JPanel pnlDesc;
	private JPanel pnlConfirm;
	
	private JTextField txtName;
	private JTextArea txtDesc;
	
	private JButton btnOk;
	private JButton btnCancel;
	
	private GameOptionService optionService;
	private GameOption option;
	
	private JDialog self;

	public DlgSaveOptions(Frame owner, boolean modal, GameOption option) {
		super(owner, modal);
		self = this;
		this.option = option;
		optionService = new GameOptionService();
		initNameArea();
		initDescArea();
		initConfirmArea();
		initGui();
	}
	
	private void initNameArea() {
		pnlName = new JPanel(new BorderLayout());
		pnlName.setOpaque(false);
		txtName = new JTextField(option.getName());
		pnlName.add(txtName);
		pnlName.setBorder(BorderFactory.createTitledBorder("Name"));
		pnlName.setMaximumSize(new Dimension(350,70));
	}
	
	private void initDescArea() {
		pnlDesc = new JPanel(new BorderLayout());
		pnlDesc.setOpaque(false);
		txtDesc = new JTextArea(option.getDescription());
		txtDesc.setLineWrap(true);
		txtDesc.setWrapStyleWord(true);
		pnlDesc.add(txtDesc);
		pnlDesc.setBorder(BorderFactory.createTitledBorder("Beschreibung"));
		pnlDesc.setMaximumSize(new Dimension(350,210));
	}
	
	private void initConfirmArea() {
		pnlConfirm = new JPanel(new GridLayout(1, 2,10,10));
		pnlConfirm.setOpaque(false);
		btnOk = ButtonFactory.createColouredButton("Speichern", ButtonFactory.BTN_GREEN);
		btnCancel = ButtonFactory.createColouredButton("Abbrechen", ButtonFactory.BTN_RED);
		pnlConfirm.add(btnOk);
		pnlConfirm.add(btnCancel);
		pnlConfirm.setBorder(BorderFactory.createEmptyBorder(30,50,30,50));
		pnlConfirm.setMaximumSize(new Dimension(350,100));
		
		btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				option.setName(txtName.getText());
				option.setDescription(txtDesc.getText());
				optionService.saveOption(option);
				self.dispose();
			}
		});
		
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				self.dispose();
			}
		});
	}
	
	private void initGui() {
		JPanel mainPanel = new GradientPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
		JPanel pnlCaption = new JPanel();
		pnlCaption.setOpaque(false);
		pnlCaption.setMaximumSize(new Dimension(350, 30));
		JLabel lblCaption = new JLabel("Konfiguration speichern: ");
		pnlCaption.add(lblCaption);
		mainPanel.add(pnlCaption);
		mainPanel.add(pnlName);
		mainPanel.add(pnlDesc);
		mainPanel.add(pnlConfirm);
		mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 30, 0, 30));
		add(mainPanel);
		setTitle(Messages.getString("options")); 
		setSize(430, 340);
		setResizable(false);
		setLocation((getOwner().getX()), (getOwner().getY()));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
	}
}
