package de.htwsaar.flashcards.ui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import de.htwsaar.flashcards.builder.ServiceObjectBuilder;
import de.htwsaar.flashcards.model.Stack;
import de.htwsaar.flashcards.properties.Dimensions;
import de.htwsaar.flashcards.properties.Messages;
import de.htwsaar.flashcards.service.interfaces.FlashCardService;
import de.htwsaar.flashcards.service.interfaces.StackService;
import de.htwsaar.flashcards.ui.component.GradientPanel;
import de.htwsaar.flashcards.util.FlashCardButtonFactory;
import de.htwsaar.flashcards.util.FlashCardConstants;
import de.htwsaar.flashcards.util.FlashCardUtils;

/**
 * <code>DlgSettings</code> - Dialog fuer die Uebersicht und Abaenderung von
 * Einstellungen. Bisher werden folgende Einstellungen angeboten:
 * 		- Sprache: Deutsch, Englisch, Spanisch
 * 		- Hintergrundfarbe
 * 		- BoxCount eines Stacks zuruecksetzen
 * @author Marek Kohn
 */

public class DlgSettings extends JDialog {
	
	private static final long serialVersionUID = -8072251625090894613L;
	
	private static final ImageIcon ICN_GERMAN = new ImageIcon("res/images/german.png");
	private static final ImageIcon ICN_ENGLISH = new ImageIcon("res/images/english.png");
	private static final ImageIcon ICN_SPANISH = new ImageIcon("res/images/spanish.png");
	
	private static Color BLUE[]   = {new Color(230, 247, 255), new Color(0, 163, 204)};
	private static Color RED[]    = {new Color(255, 230, 247), new Color(240, 0, 163)};
	private static Color YELLOW[] = {new Color(255, 247, 230), new Color(240, 163, 0)};
	private static Color GREEN[]  = {new Color(230, 255, 247), new Color(0, 204, 163)};
	
	private JPanel pnlLanguage;
	private JPanel pnlbackgroundColor;
	private JPanel pnlReset;
	private JPanel pnlConfirm;
	
	private JComboBox<String> cmbColor;
	private JComboBox<Stack> cmbStack;
	private String[] colors = {Messages.getString("blue"), 
							   Messages.getString("red"),
							   Messages.getString("yellow"),
							   Messages.getString("green")};
	
	private JButton btnPreview;
	private JButton btnReset;
	private JButton btnOk;
	
	private ButtonGroup grpLanguages;
	private JRadioButton btnGerman;
	private JRadioButton btnEnglish;
	private JRadioButton btnSpanish;
	
	private StackService stackService;
	private FlashCardService cardService;
	
	private JDialog self;
	
	public DlgSettings(Frame owner, boolean modal) {
		super(owner, modal);
		self = this;
		stackService = ServiceObjectBuilder.getStackService();
		cardService = ServiceObjectBuilder.getFlashCardService();
		
		initLanguageArea();
		initColorArea();
		initResetArea();
		initConfirmArea();
		initFrame();
	}
	
	private void initLanguageArea() {
		pnlLanguage = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pnlLanguage.setOpaque(false);
		grpLanguages = new ButtonGroup();
		btnGerman = new JRadioButton();
		btnEnglish = new JRadioButton();
		btnSpanish = new JRadioButton();
		grpLanguages.add(btnGerman);
		grpLanguages.add(btnEnglish);
		grpLanguages.add(btnSpanish);
		btnGerman.setSelected(true);
		pnlLanguage.add(new JLabel(Messages.getString("language")));
		pnlLanguage.add(btnGerman);
		pnlLanguage.add(new JLabel(ICN_GERMAN));
		pnlLanguage.add(btnEnglish);
		pnlLanguage.add(new JLabel(ICN_ENGLISH));
		pnlLanguage.add(btnSpanish);
		pnlLanguage.add(new JLabel(ICN_SPANISH));
		pnlLanguage.setBorder(BorderFactory.createTitledBorder(Messages.getString("chooseStack")));
		
		if(Messages.getRessource().equals(ResourceBundle.getBundle(Messages.BUNDLE_NAME_DE))) {
			btnGerman.setSelected(true);
		} 
		else if(Messages.getRessource().equals(ResourceBundle.getBundle(Messages.BUNDLE_NAME_EN))) {
			btnEnglish.setSelected(true);
		} 
		else if(Messages.getRessource().equals(ResourceBundle.getBundle(Messages.BUNDLE_NAME_ES))) {
			btnSpanish.setSelected(true);
		} 
	}
	
	private void initColorArea() {
		pnlbackgroundColor = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pnlbackgroundColor.setOpaque(false);
		cmbColor = new JComboBox<String>(colors);
		pnlbackgroundColor.add(new JLabel(Messages.getString("background")));
		btnPreview = FlashCardButtonFactory.createColouredButton(Messages.getString("preview"), BLUE[1]);
		pnlbackgroundColor.add(cmbColor);
		pnlbackgroundColor.add(btnPreview);
		pnlbackgroundColor.setBorder(BorderFactory.createTitledBorder(Messages.getString("chooseColor")));
		
		cmbColor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				switch (cmbColor.getSelectedIndex()) {
				case 0:
					GradientPanel.changeBackground(BLUE[0]);
					FlashCardConstants.COLOR_FOREGROUND = BLUE[1];
					break;
				case 1:
					GradientPanel.changeBackground(RED[0]);
					FlashCardConstants.COLOR_FOREGROUND = RED[1];
					break;
				case 2:
					GradientPanel.changeBackground(YELLOW[0]);
					FlashCardConstants.COLOR_FOREGROUND = YELLOW[1];
					break;
				case 3:
					GradientPanel.changeBackground(GREEN[0]);
					FlashCardConstants.COLOR_FOREGROUND = GREEN[1];
					break;
				}
				//btnPreview.setColor(FlashCardConstants.COLOR_FOREGROUND);
				self.repaint();
				self.getOwner().repaint();
				btnPreview.repaint();
			}
		});
	}
	
	private void initResetArea() {
		pnlReset = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pnlReset.setOpaque(false);
		pnlReset.add(new JLabel(Messages.getString("stackn")));
		cmbStack = new JComboBox<Stack>(stackService.getStackArray());
		btnReset = FlashCardButtonFactory.createColouredButton(Messages.getString("reset"), FlashCardButtonFactory.BTN_RED);
		pnlReset.add(cmbStack);
		pnlReset.add(btnReset);
		pnlReset.setBorder(BorderFactory.createTitledBorder(Messages.getString("resetLbl")));
		
		btnReset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cardService.reset((Stack)cmbStack.getSelectedItem());
			}
		});
	}
	
	private void initConfirmArea() {
		pnlConfirm = new JPanel(new FlowLayout(FlowLayout.CENTER));
		pnlConfirm.setOpaque(false);
		btnOk = FlashCardButtonFactory.createColouredButton(Messages.getString("ok"), FlashCardButtonFactory.BTN_GREEN);
		pnlConfirm.add(btnOk);
		btnOk.addActionListener(new UpdateSettingsListener());
	}
	
	
	private void initFrame() {
		GradientPanel mainPanel = new GradientPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
		mainPanel.add(pnlLanguage);
		mainPanel.add(pnlbackgroundColor);
		mainPanel.add(pnlReset);
		mainPanel.add(pnlConfirm);
		add(mainPanel);
		setTitle(Messages.getString("settings"));
		setSize(Dimensions.getDimension("settings.dim_frame"));
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(getOwner());
		setVisible(true);
	}
	
	private class UpdateSettingsListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(btnGerman.isSelected() == true) {
				Messages.setResource(Messages.BUNDLE_NAME_DE);
			}
			else if(btnEnglish.isSelected() == true) {
				Messages.setResource(Messages.BUNDLE_NAME_EN);
			}
			else if(btnSpanish.isSelected() == true) {
				Messages.setResource(Messages.BUNDLE_NAME_ES);
			}
			FlashCardUtils.restart((Frame) self.getOwner());
		}
	}
}
