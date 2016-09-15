package de.htwsaar.flashcards.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import java.util.concurrent.Callable;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;

import de.htwsaar.flashcards.main.Main;
import de.htwsaar.flashcards.properties.Dimensions;
import de.htwsaar.flashcards.properties.Messages;
import de.htwsaar.flashcards.ui.listeners.ImportFileListener;

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
		JMenuItem itemSelectStack = new JMenuItem(Messages.getString("selectStack"));
		JMenuItem itemGameOptions = new JMenuItem(Messages.getString("gameOptions"));
		JMenuItem itemStatistics = new JMenuItem(Messages.getString("stats"));
		showViewSubMenu.add(itemSelectStack);
		showViewSubMenu.add(itemGameOptions);
		showViewSubMenu.add(itemStatistics);
		windowMenu.add(showViewSubMenu);
		
		JMenu navigateMenu = new JMenu(Messages.getString("navigate"));
		
		JMenu helpMenu = new JMenu(Messages.getString("help"));
		
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
		menuBar.add(navigateMenu);
		menuBar.add(viewMenu);
		menuBar.add(helpMenu);
		
		itemPreferences.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new DlgSettings(owner, true);
			}
		});
		
		itemStatistics.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				new DlgStatistic(owner, true);
			}
		});
		
		itemImport.addActionListener(new ImportFileListener(owner, handler));
		
		itemLowRes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Dimensions.setResource(Dimensions.BUNDLE_NAME_SMALL);
				owner.dispose();
				try {Main.main(null);} catch (ClassNotFoundException e1) {
					e1.printStackTrace();}
			}
		});
		
		itemHighRes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Dimensions.setResource(Dimensions.BUNDLE_NAME_LARGE);
				owner.dispose();
				try {Main.main(null);} catch (ClassNotFoundException e1) {
					e1.printStackTrace();}
			}
		});

		return menuBar;
	}
}
