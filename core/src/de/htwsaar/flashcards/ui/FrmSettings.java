package de.htwsaar.flashcards.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import de.htwsaar.flashcards.properties.Messages;
import de.htwsaar.flashcards.ui.component.GradientPanel;
import de.htwsaar.flashcards.util.ButtonFactory;

public class FrmSettings {
	
	private static final ImageIcon ICN_GERMAN = new ImageIcon("res/images/german.png");
	private static final ImageIcon ICN_ENGLISH = new ImageIcon("res/images/english.png");
	private static final ImageIcon ICN_SPANISH = new ImageIcon("res/images/spanish.png");
	
	private static Color BLUE[]   = {new Color(230, 247, 255),new Color(0, 163, 204)};
	private static Color RED[]    = {new Color(255, 230, 247),new Color(204, 0, 163)};
	private static Color YELLOW[] = {new Color(255, 247, 230),new Color(204, 163, 0)};
	private static Color GREEN[]  = {new Color(230, 255, 247),new Color(0, 204, 163)};
	
	private JPanel pnlLanguage;
	private JPanel pnlbackgroundColor;
	private JPanel pnlReset;
	
	private JComboBox<String> cmbColor;
	private String[] colors = {"Blau", "Rot", "Gelb", "Grün"};
	private JButton btnPreview;
	
	private ButtonGroup grpLanguages;
	private JRadioButton btnGerman;
	private JRadioButton btnEnglish;
	private JRadioButton btnSpanish;
	
	private JFrame frmSettings;
	
	public FrmSettings() {
		frmSettings = new JFrame();
		frmSettings.setResizable(false);
		
		initLanguageArea();
		initColorArea();
		initFrame();
	}
	
	private void initLanguageArea() {
		pnlLanguage = new JPanel();
		pnlLanguage.setOpaque(false);
		grpLanguages = new ButtonGroup();
		btnGerman = new JRadioButton();
		btnEnglish = new JRadioButton();
		btnSpanish = new JRadioButton();
		grpLanguages.add(btnGerman);
		grpLanguages.add(btnEnglish);
		grpLanguages.add(btnSpanish);
		pnlLanguage.add(new JLabel("Sprache: "));
		pnlLanguage.add(btnGerman);
		pnlLanguage.add(new JLabel(ICN_GERMAN));
		pnlLanguage.add(btnEnglish);
		pnlLanguage.add(new JLabel(ICN_ENGLISH));
		pnlLanguage.add(btnSpanish);
		pnlLanguage.add(new JLabel(ICN_SPANISH));
		pnlLanguage.setBorder(BorderFactory.createTitledBorder("Wähle eine Sprache: "));
	}
	
	private void initColorArea() {
		pnlbackgroundColor = new JPanel();
		pnlbackgroundColor.setOpaque(false);
		cmbColor = new JComboBox<String>(colors);
		btnPreview = ButtonFactory.createColouredButton("Vorschau", GREEN[1]);
		pnlbackgroundColor.add(cmbColor);
		pnlbackgroundColor.add(btnPreview);
		cmbColor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				switch (cmbColor.getSelectedIndex()) {
				case 0:
					GradientPanel.changeBackground(BLUE[0]);
					btnPreview.setBackground(BLUE[1]);
					break;
				case 1:
					GradientPanel.changeBackground(RED[0]);
					btnPreview.setBackground(RED[1]);
					break;
				case 2:
					GradientPanel.changeBackground(YELLOW[0]);
					btnPreview.setBackground(YELLOW[1]);
					break;
				case 3:
					GradientPanel.changeBackground(GREEN[0]);
					btnPreview.setBackground(GREEN[1]);
					break;
				}
				frmSettings.repaint();
				btnPreview.setBackground(Color.black);
				btnPreview.repaint();
			}
		});
	}
	
	
	private void initFrame() {
		GradientPanel mainPanel = new GradientPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
		mainPanel.add(pnlLanguage);
		mainPanel.add(pnlbackgroundColor);
		frmSettings.add(mainPanel);
		frmSettings.setTitle(Messages.getString("study"));
		frmSettings.setSize(new Dimension(300,200));
		frmSettings.setDefaultCloseOperation(3);
		frmSettings.setVisible(true);
	}
	
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(new NimbusLookAndFeel());
			UIManager.getLookAndFeelDefaults()
	        .put("defaultFont", new Font("Tahoma", Font.PLAIN, 14));
			 UIManager.getLookAndFeelDefaults()
			 .put("nimbusOrange", (new Color(0, 163, 204)));
			 UIManager.getLookAndFeelDefaults()
			 .put("info", Color.white);
			 UIManager.getLookAndFeelDefaults()
			 .put("OptionPane.background", new Color(230, 247, 255));
			 UIManager.getLookAndFeelDefaults()
			 .put("Panel.background", new Color(230, 247, 255));
			 UIManager.getLookAndFeelDefaults()
			 .put("MenuBar:Menu.contentMargins", new Insets(1, 8, 2, 8));
		} catch (UnsupportedLookAndFeelException e1) {
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (Exception e2) {
				System.err.println("Fatal Error: Can't load Look and Feel defaults");
			}
		}	
		new FrmSettings();
	}
}
