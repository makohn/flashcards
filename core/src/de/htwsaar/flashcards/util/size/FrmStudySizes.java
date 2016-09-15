package de.htwsaar.flashcards.util.size;

import java.awt.Dimension;

import de.htwsaar.flashcards.util.FlashCardConstants;

public class FrmStudySizes {
	
	private static boolean large = FlashCardConstants.LARGE;
	
	public static final Dimension DIM_IMAGE = large ? new Dimension(200,200) : new Dimension(130,130);
}
