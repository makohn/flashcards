package de.htwsaar.flashcards.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import de.htwsaar.flashcards.util.ButtonFactory;

public class MenuBar {
	
	private static final ImageIcon ICN_BACK = new ImageIcon("res/images/back.png");
	
	public static JMenuBar createMenuBar(JFrame owner) {
		JMenuBar menuBar = new JMenuBar();
		
		JButton btnBack = ButtonFactory.createImageButton(ICN_BACK);
		btnBack.setToolTipText("Zurück");
		
		JMenu fileMenu = new JMenu("Datei");
		JMenuItem itemImport = new JMenuItem("Importieren");
		JMenuItem itemExport = new JMenuItem("Exportieren");
		JMenuItem itemPreferences = new JMenuItem("Einstellungen");
		fileMenu.add(itemImport);
		fileMenu.add(itemExport);
		fileMenu.addSeparator();
		fileMenu.add(itemPreferences);
		
		JMenu windowMenu = new JMenu("Fenster");
		JMenu showViewSubMenu = new JMenu("Anzeigen...");
		JMenuItem itemSelectStack = new JMenuItem("Stackauswahl");
		JMenuItem itemGameOptions = new JMenuItem("Spieloptionen");
		JMenuItem itemStatistics = new JMenuItem("Statistik");
		showViewSubMenu.add(itemSelectStack);
		showViewSubMenu.add(itemGameOptions);
		showViewSubMenu.add(itemStatistics);
		windowMenu.add(showViewSubMenu);
		
		JMenu navigateMenu = new JMenu("Navigieren");
		
		JMenu helpMenu = new JMenu("Hilfe");
			
		menuBar.add(btnBack);
		menuBar.add(fileMenu);
		menuBar.add(windowMenu);
		menuBar.add(navigateMenu);
		menuBar.add(helpMenu);
		
		itemPreferences.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new DlgSettings(owner, true);
			}
		});

		return menuBar;
	}
}
