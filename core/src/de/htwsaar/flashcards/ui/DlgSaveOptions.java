package de.htwsaar.flashcards.ui;

import java.awt.BorderLayout;
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
import de.htwsaar.flashcards.service.GameOptionServiceImpl;
import de.htwsaar.flashcards.service.interfaces.GameOptionService;
import de.htwsaar.flashcards.ui.component.GradientPanel;
import de.htwsaar.flashcards.util.FlashCardButtonFactory;
import de.htwsaar.flashcards.util.size.DlgSaveOptionsSizes;

/**
 * <code>DlgSaveOptions</code> - Kleiner Dialog zum Speichern von Spieloptionen
 * in der Datenbank. Der Option kann hierbei ein Name sowie eine Beschreibung gegeben
 * werden.
 * 
 * @author Marek Kohn
 * @see FrmGameOptions, DlgGameOptions
 */
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
		optionService = new GameOptionServiceImpl();
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
		pnlName.setBorder(BorderFactory.createTitledBorder(Messages.getString("name")));
		pnlName.setMaximumSize(DlgSaveOptionsSizes.DIM_NAME_AREA_MAX);
	}
	
	private void initDescArea() {
		pnlDesc = new JPanel(new BorderLayout());
		pnlDesc.setOpaque(false);
		txtDesc = new JTextArea(option.getDescription());
		txtDesc.setLineWrap(true);
		txtDesc.setWrapStyleWord(true);
		pnlDesc.add(txtDesc);
		pnlDesc.setBorder(BorderFactory.createTitledBorder(Messages.getString("description")));
		pnlDesc.setMaximumSize(DlgSaveOptionsSizes.DIM_DESC_AREA_MAX);
	}
	
	private void initConfirmArea() {
		pnlConfirm = new JPanel(new GridLayout(1, 2,10,10));
		pnlConfirm.setOpaque(false);
		btnOk = FlashCardButtonFactory.createColouredButton(Messages.getString("save"), FlashCardButtonFactory.BTN_GREEN);
		btnCancel = FlashCardButtonFactory.createColouredButton(Messages.getString("cancel"), FlashCardButtonFactory.BTN_RED);
		pnlConfirm.add(btnOk);
		pnlConfirm.add(btnCancel);
		pnlConfirm.setBorder(DlgSaveOptionsSizes.BORDER_CONFIRM_AREA);
		pnlConfirm.setMaximumSize(DlgSaveOptionsSizes.DIM_CONFIRM_AREA_MAX);
		
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
		pnlCaption.setMaximumSize(DlgSaveOptionsSizes.DIM_CAPTION_MAX);
		JLabel lblCaption = new JLabel(Messages.getString("save_config"));
		pnlCaption.add(lblCaption);
		mainPanel.add(pnlCaption);
		mainPanel.add(pnlName);
		mainPanel.add(pnlDesc);
		mainPanel.add(pnlConfirm);
		mainPanel.setBorder(DlgSaveOptionsSizes.BORDER_MAIN);
		add(mainPanel);
		setTitle(Messages.getString("options")); 
		setSize(430, 340);
		setResizable(false);
		setLocationRelativeTo(getOwner());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
	}
}
