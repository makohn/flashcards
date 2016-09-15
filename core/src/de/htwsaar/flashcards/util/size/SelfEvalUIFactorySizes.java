package de.htwsaar.flashcards.util.size;

import java.awt.Dimension;
import java.awt.Insets;

import de.htwsaar.flashcards.util.FlashCardConstants;

public class SelfEvalUIFactorySizes {
	
	private static boolean large = FlashCardConstants.LARGE;
	
	public static final Insets INSETS_INFO[] = large ? 
		new Insets[] {new Insets(0, 10, 0, 120), new Insets(0, 45, 0, 0), new Insets(5, 160, 0, 0)} :
		new Insets[] {new Insets(0, 5, 0, 30), new Insets(0, 3, 0, 5), new Insets(2, 35, 0, 10)};
	public static final Insets INSETS_EVAL_PANEL_R = new Insets(0, 0, 0, 100) ;
	public static final Insets INSETS_EVAL_PANEL_L = new Insets(0, 100, 0, 0);
	public static final Dimension DIM_FRAME = large ? new Dimension(650, 780) : new Dimension(450, 580);
}
