package de.htwsaar.flashcards.util;

import javax.swing.ImageIcon;

public class SwingUtils {

	
	public static ImageIcon scale(ImageIcon image, int width, int height) {
		return new ImageIcon(image.getImage()
				.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH));
	}
}
