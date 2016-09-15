package de.htwsaar.flashcards.util.size;

import java.awt.Dimension;
import java.awt.Insets;

import de.htwsaar.flashcards.util.FlashCardConstants;

public class DlgStatisticSizes {
	
	private static boolean large = FlashCardConstants.LARGE;
	
	public static final int SIZE_PIE_CHART = large ? 100 : 50;
	public static final Insets INSETS_LEFT_PIE =  large ? new Insets(0,70,0,0) : new Insets(0,35,0,0);
	public static final Insets INSETS_RIGHT_PIE = large ? new Insets(0,130,0,60) : new Insets(0,65,0,30);
	public static final Dimension DIM_FRAME = large ? new Dimension(600, 660) : new Dimension(500, 550); 
	public static final int SIZE_BAR_CHART = large ? 200 : 100;
	public static final int WIDTH_BAR = large ? 25 : 15;
}
