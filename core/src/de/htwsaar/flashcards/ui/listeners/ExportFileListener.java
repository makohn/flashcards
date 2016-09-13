package de.htwsaar.flashcards.ui.listeners;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;

import de.htwsaar.flashcards.files.ExporterCSVImpl;
import de.htwsaar.flashcards.files.interfaces.ExporterCSV;
import de.htwsaar.flashcards.model.Stack;

public class ExportFileListener implements ActionListener {
	private Stack stack;
	private Frame caller;
	
	public ExportFileListener(Stack stack, Frame caller) {
		this.stack = stack;
		this.caller = caller;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String filePath = "";
		JFileChooser chooser = new JFileChooser(); 
	    chooser.setCurrentDirectory(new java.io.File("."));
	    chooser.setDialogTitle("Wähle einen Speicherort");
	    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	    chooser.setAcceptAllFileFilterUsed(false);   
	    if (chooser.showOpenDialog(caller) == JFileChooser.APPROVE_OPTION) {
	    	 filePath += chooser.getSelectedFile() + File.separator + stack.getStackName();
	    	 ExporterCSV exporter = new ExporterCSVImpl();
	    	 exporter.exportCSV(stack, filePath); 
	    }
	}
}
