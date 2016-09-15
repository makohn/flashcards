package de.htwsaar.flashcards.util;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.border.Border;

public class FlashCardConstants {
	
	public static final int START_PHASE = 1;
	public static boolean LARGE = true;
    public static final Border INNER_CARD_BORDER = BorderFactory.createLineBorder(Color.GRAY);
    public static final Border OUTER_CARD_BORDER = BorderFactory.createEmptyBorder(10, 10, 10, 10);
    
    public static Color COLOR_FOREGROUND = new Color(0, 163, 204);
}
