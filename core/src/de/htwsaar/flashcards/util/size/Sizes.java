package de.htwsaar.flashcards.util.size;

import java.awt.Font;

import de.htwsaar.flashcards.util.FlashCardConstants;

public class Sizes {
	
	private static boolean large = FlashCardConstants.LARGE;
	
	public static final Font FONT_COUNTER = large ? new Font("SansSerif", Font.PLAIN, 20) : new Font("SansSerif", Font.PLAIN, 15);
	public static final Font FONT_CARD = large ? new Font("Tahoma", Font.PLAIN, 20) : new Font("Tahoma", Font.PLAIN, 15);
	public static final int CARD_TXTAREA_ROWS = large ? 4 : 2;
	public static final int CARD_TXTAREA_COLUMNS = large ? 10 : 5;
}
