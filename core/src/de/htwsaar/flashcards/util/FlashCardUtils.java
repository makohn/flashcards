package de.htwsaar.flashcards.util;

import java.awt.Dimension;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JTextArea;

import de.htwsaar.flashcards.util.size.Sizes;

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
        txtArea.setFont(Sizes.FONT_CARD);
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
}
