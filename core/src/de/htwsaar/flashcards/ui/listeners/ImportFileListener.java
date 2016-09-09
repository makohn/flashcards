package de.htwsaar.flashcards.ui.listeners;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Callable;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import de.htwsaar.flashcards.builder.FileHandlerObjectBuilder;
import de.htwsaar.flashcards.files.interfaces.ImporterCSV;
import de.htwsaar.flashcards.properties.Messages;

public class ImportFileListener implements ActionListener {
	private Frame caller;
	private Callable<Void> handler;
	
	public ImportFileListener(Frame caller, Callable<Void> handler) {
		this.caller = caller;
		this.handler = handler;
	}
	
	public ImportFileListener(Frame caller) {
		this(caller, null);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser chooser = new JFileChooser(); 
	    chooser.setCurrentDirectory(new java.io.File("."));
	    chooser.setDialogTitle(Messages.getString("chooseafile"));
	    chooser.setFileFilter(new FileNameExtensionFilter("CSV", "csv"));   
	    if (chooser.showOpenDialog(caller) == JFileChooser.APPROVE_OPTION) {
	    	 ImporterCSV importer = FileHandlerObjectBuilder.getImporterCSVObject();
	    	 importer.importCSV(chooser.getSelectedFile());
	    	 if(handler != null)
				try {handler.call();}catch(Exception e1){}
	    }
	}
}

