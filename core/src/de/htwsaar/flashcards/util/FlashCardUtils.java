package de.htwsaar.flashcards.util;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JTextArea;

public class FlashCardUtils {
	
	private static final Font FONT_CARD = new Font("Tahoma", Font.PLAIN, 20);
	
	public static ImageIcon scale(ImageIcon image, int width, int height) {
		return new ImageIcon(image.getImage()
				.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH));
	}
	
	public static JTextArea createCardTextArea(boolean editable) {
		JTextArea txtArea = new JTextArea(4,10);
        txtArea.setLineWrap(true);
        txtArea.setWrapStyleWord(true);
        txtArea.setEditable(editable);
        txtArea.setFont(FONT_CARD);
        return txtArea;
	}
}
