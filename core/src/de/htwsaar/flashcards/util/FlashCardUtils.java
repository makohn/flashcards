package de.htwsaar.flashcards.util;

import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Frame;
import java.net.URI;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JTextArea;

import de.htwsaar.flashcards.main.Main;
import de.htwsaar.flashcards.properties.Dimensions;

/**
 * Hilfsklasse fuer das <code>flashcards</code> Projekt.
 * @author Marek Kohn
 *
 */
public class FlashCardUtils {
	
	/**
	 * Skaliert einen Bild (ImageIcon) auf eine uebergebene Groesse
	 */
	public static ImageIcon scale(ImageIcon image, Dimension dim) {
		return new ImageIcon(image.getImage()
				.getScaledInstance(dim.width, dim.height, java.awt.Image.SCALE_SMOOTH));
	}
	
	/**
	 * Erzeugt eine 'Standard-Karten-Textarea'.
	 * @param editable - Soll die Textarea bearbeitbar sein ?
	 * @return
	 */
	public static JTextArea createCardTextArea(boolean editable) {
		JTextArea txtArea = new JTextArea(4,10);
        txtArea.setLineWrap(true);
        txtArea.setWrapStyleWord(true);
        txtArea.setEditable(editable);
        txtArea.setFont(Dimensions.getFont("default.font_card"));
        return txtArea;
	}
	
	/**
	 * Gibt den Button aus einer Menge von Buttons zurueck, welcher
	 * ausgewaehlt wurde (sich im Zustand 'isSelected' befindet.
	 * @param buttons - eine Menge von Buttons
	 * @return
	 */
	public static int getSelectedButtonIndex(AbstractButton[] buttons) {
		for(int i = 0; i < buttons.length; i++) {
			if (buttons[i].isSelected())
				return i+1;
		}
		return -1;
	}
	
	/**
	 * Oeffnet eine Internetseite ueber den Standardbrowser des Anwenders.
	 * @param uri - Die URI der zu oeffnenden Seite
	 */
	public static void openWebpage(URI uri) {
	    Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
	    if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
	        try {
	            desktop.browse(uri);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	}
	
	/**
	 * Startet das Programm neu und schliesst den aufrufenden Frame. Dabei werden
	 * alle Konfigurationen neu geladen, sodass es moeglich ist bestimmte Einstellungen
	 * zu aendern.
	 * @param owner - der aufrufende Frame der geschlossen wird
	 */
	public static void restart(Frame owner) {
		owner.dispose();
		try {Main.main(null);} catch (ClassNotFoundException e1) {
			e1.printStackTrace();}
	}
}
