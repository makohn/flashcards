package de.htwsaar.flashcards.ui.listeners;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import de.htwsaar.flashcards.files.ImporterCSVImpl;
import de.htwsaar.flashcards.files.interfaces.ImporterCSV;

public class ImportFileListener implements ActionListener {
	private Frame caller;
	
	public ImportFileListener(Frame caller) {
		this.caller = caller;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser chooser = new JFileChooser(); 
	    chooser.setCurrentDirectory(new java.io.File("."));
	    chooser.setDialogTitle("Welche Datei importieren ?");
	    chooser.setFileFilter(new FileNameExtensionFilter("CSV", "csv"));   
	    if (chooser.showOpenDialog(caller) == JFileChooser.APPROVE_OPTION) {
	    	 ImporterCSV importer = new ImporterCSVImpl();
	    	 importer.importCSV(chooser.getSelectedFile());
	    }
	}
}

