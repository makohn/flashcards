package de.htwsaar.flashcards.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import de.htwsaar.flashcards.service.GameOptionService;

public class MenuBar {
	
	public static JMenuBar createMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		
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
		JMenuItem itemBack = new JMenuItem("Zurück");
		navigateMenu.add(itemBack);
		
		JMenu helpMenu = new JMenu("Hilfe");
				
		menuBar.add(fileMenu);
		menuBar.add(windowMenu);
		menuBar.add(navigateMenu);
		menuBar.add(helpMenu);

		return menuBar;
	}

}
