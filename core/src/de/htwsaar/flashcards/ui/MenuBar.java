package de.htwsaar.flashcards.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ResourceBundle;
import java.util.concurrent.Callable;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;

import de.htwsaar.flashcards.properties.Dimensions;
import de.htwsaar.flashcards.properties.Messages;
import de.htwsaar.flashcards.ui.listeners.ImportFileListener;
import de.htwsaar.flashcards.util.FlashCardConstants;
import de.htwsaar.flashcards.util.FlashCardUtils;

public class MenuBar {
	
	public static JMenuBar createMenuBar(JFrame owner) {
		return createMenuBar(owner, null);
	}
	
	public static JMenuBar createMenuBar(JFrame owner, Callable<Void> handler) {
		JMenuBar menuBar = new JMenuBar();
		
		JMenu fileMenu = new JMenu(Messages.getString("file"));
		JMenuItem itemImport = new JMenuItem(Messages.getString("import"));
		JMenuItem itemExport = new JMenuItem(Messages.getString("export"));
		JMenuItem itemPreferences = new JMenuItem(Messages.getString("settings"));
		fileMenu.add(itemImport);
		fileMenu.add(itemExport);
		fileMenu.addSeparator();
		fileMenu.add(itemPreferences);
		
		JMenu windowMenu = new JMenu(Messages.getString("window"));
		JMenu showViewSubMenu = new JMenu(Messages.getString("view"));
		JMenuItem itemGameOptions = new JMenuItem(Messages.getString("gameOptions"));
		JMenuItem itemStatistics = new JMenuItem(Messages.getString("stats"));
		showViewSubMenu.add(itemGameOptions);
		showViewSubMenu.add(itemStatistics);
		windowMenu.add(showViewSubMenu);
		
		JMenu helpMenu = new JMenu(Messages.getString("help"));
		JMenuItem itemOnlineHelp = new JMenuItem(Messages.getString("onlinehelp"));
		helpMenu.add(itemOnlineHelp);
		
		ButtonGroup group = new ButtonGroup();
		JMenu viewMenu = new JMenu(Messages.getString("prefs"));
		JMenuItem itemLowRes = new JRadioButtonMenuItem(Messages.getString("lowres"));
		JMenuItem itemHighRes = new JRadioButtonMenuItem(Messages.getString("highres"));
		group.add(itemLowRes);
		group.add(itemHighRes);
		
		if(Dimensions.getResource().equals(ResourceBundle.getBundle(Dimensions.BUNDLE_NAME_LARGE))) 
			itemHighRes.setSelected(true);
		else
			itemLowRes.setSelected(true);
		
		viewMenu.add(itemLowRes);
		viewMenu.add(itemHighRes);
		
		menuBar.add(fileMenu);
		menuBar.add(windowMenu);
		menuBar.add(viewMenu);
		menuBar.add(helpMenu);
		
		/**
		 * Oeffnet den Einstellungsframe
		 */
		itemPreferences.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new DlgSettings(owner, true);
			}
		});
		
		/**
		 * Oeffnet den Statistikframe
		 */
		itemStatistics.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				new DlgStatistic(owner, true);
			}
		});
		
		/**
		 * Oeffnet eine FileChooser um eine CSV Datei zu importieren
		 */
		itemImport.addActionListener(new ImportFileListener(owner, handler));
		
		itemGameOptions.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new FrmGameOptions();
			}
		});
		
		/**
		 * Reguliert die Darstellung des Frames bei niedriger Auflösung
		 */
		itemLowRes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Dimensions.setResource(Dimensions.BUNDLE_NAME_SMALL);
				FlashCardUtils.restart(owner);
			}
		});
		
		/**
		 * Reguliert die Darstellung des Frames bei hoher Auflösung
		 */
		itemHighRes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Dimensions.setResource(Dimensions.BUNDLE_NAME_LARGE);
				FlashCardUtils.restart(owner);
			}
		});
		
		/**
		 * Oeffnet die Online Hilfe im Standardbrowser des Anwenders
		 */
		itemOnlineHelp.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				URI urlHelp;
				try {
					urlHelp = new URI(FlashCardConstants.HELP_WEBPAGE_PATH);
					FlashCardUtils.openWebpage(urlHelp);
				} catch (URISyntaxException e1) {
					e1.printStackTrace();
				}
			}
		});

		return menuBar;
	}
}
