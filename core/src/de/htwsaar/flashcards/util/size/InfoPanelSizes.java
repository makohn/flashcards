package de.htwsaar.flashcards.util.size;

import java.awt.Dimension;
import java.awt.Font;

import de.htwsaar.flashcards.util.FlashCardConstants;

public class InfoPanelSizes {
	
	private static boolean large = FlashCardConstants.LARGE;
	
	public static final Dimension DIM_CIRCLE = large ? new Dimension(50,50) : new Dimension(30,30) ;
	public static final Font FONT_LBL_CARD_COUNTER = large ? new Font("SansSerif", 1, 20) : new Font("SansSerif", 1, 15);
	public static final Dimension DIM_MAXIMUM_SIZE = large ? new Dimension(650,200) : new Dimension(350, 100);
}
