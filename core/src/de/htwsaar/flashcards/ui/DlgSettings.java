package de.htwsaar.flashcards.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

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

import de.htwsaar.flashcards.model.Stack;
import de.htwsaar.flashcards.properties.Messages;
import de.htwsaar.flashcards.service.FlashCardServiceImpl;
import de.htwsaar.flashcards.service.StackServiceImpl;
import de.htwsaar.flashcards.ui.component.GradientPanel;
import de.htwsaar.flashcards.util.FlashCardButtonFactory;
import de.htwsaar.flashcards.util.FlashCardConstants;

/**
 * <code>DlgSettings</code> - Dialog fuer die Uebersicht und Abaenderung von
 * Einstellungen. Bisher werden folgende Einstellungen angeboten:
 * 		- Sprache: Deutsch, Englisch, Spanisch
 * 		- Hintergrundfarbe
 * 		- BoxCount eines Stacks zuruecksetzen
 * @author mkohn
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
	
	private JComboBox<String> cmbColor;
	private JComboBox<Stack> cmbStack;
	private String[] colors = {"Blau", "Rot", "Gelb", "Grün"};
	
	private JButton btnPreview;
	private JButton btnReset;
	
	private ButtonGroup grpLanguages;
	private JRadioButton btnGerman;
	private JRadioButton btnEnglish;
	private JRadioButton btnSpanish;
	
	private StackServiceImpl stackService;
	private FlashCardServiceImpl cardService;
	
	private JDialog self;
	
	public DlgSettings(Frame owner, boolean modal) {
		super(owner, modal);
		self = this;
		stackService = new StackServiceImpl();
		cardService = new FlashCardServiceImpl();
		
		initLanguageArea();
		initColorArea();
		initResetArea();
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
		pnlLanguage.add(new JLabel("Sprache: "));
		pnlLanguage.add(btnGerman);
		pnlLanguage.add(new JLabel(ICN_GERMAN));
		pnlLanguage.add(btnEnglish);
		pnlLanguage.add(new JLabel(ICN_ENGLISH));
		pnlLanguage.add(btnSpanish);
		pnlLanguage.add(new JLabel(ICN_SPANISH));
		pnlLanguage.setBorder(BorderFactory.createTitledBorder("Wähle eine Sprache: "));
		
		btnGerman.addItemListener(new SetLanguageListener());
		btnEnglish.addItemListener(new SetLanguageListener());
		btnSpanish.addItemListener(new SetLanguageListener());
	}
	
	private void initColorArea() {
		pnlbackgroundColor = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pnlbackgroundColor.setOpaque(false);
		cmbColor = new JComboBox<String>(colors);
		pnlbackgroundColor.add(new JLabel("Hintergrund: "));
		btnPreview = FlashCardButtonFactory.createColouredButton("Vorschau", BLUE[1]);
		pnlbackgroundColor.add(cmbColor);
		pnlbackgroundColor.add(btnPreview);
		pnlbackgroundColor.setBorder(BorderFactory.createTitledBorder("Wähle ein Farbschema: "));
		
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
		pnlReset.add(new JLabel("Stack: "));
		cmbStack = new JComboBox<Stack>(stackService.getStackArray());
		btnReset = FlashCardButtonFactory.createColouredButton("Reset", FlashCardButtonFactory.BTN_RED);
		pnlReset.add(cmbStack);
		pnlReset.add(btnReset);
		pnlReset.setBorder(BorderFactory.createTitledBorder("Boxcounter zurücksetzen: "));
		
		btnReset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cardService.reset((Stack)cmbStack.getSelectedItem());
			}
		});
	}
	
	
	private void initFrame() {
		GradientPanel mainPanel = new GradientPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
		mainPanel.add(pnlLanguage);
		mainPanel.add(pnlbackgroundColor);
		mainPanel.add(pnlReset);
		add(mainPanel);
		setTitle(Messages.getString("study"));
		setSize(new Dimension(300,300));
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(getOwner());
		setVisible(true);
	}
	
	private class SetLanguageListener implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent e) {
			JRadioButton source = (JRadioButton) e.getSource();
			if(source.equals(btnGerman) && source.isSelected()) {
				Messages.setResource(Messages.BUNDLE_NAME_DE);
			}
			else if(source.equals(btnEnglish) && source.isSelected()) {
				Messages.setResource(Messages.BUNDLE_NAME_EN);
			}
			else if(source.equals(btnSpanish) && source.isSelected()) {
				Messages.setResource(Messages.BUNDLE_NAME_ES);
			}
		}
	}
}
