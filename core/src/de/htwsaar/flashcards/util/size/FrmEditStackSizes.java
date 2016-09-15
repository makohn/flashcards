package de.htwsaar.flashcards.util.size;

import java.awt.Dimension;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.border.Border;

public class FrmEditStackSizes {
	public static final Dimension DIM_TXT_CARD_NAME = new Dimension(250,60);
	public static final Insets INSETS_NAVI_LEFT = new Insets(0, 103, 0, 0);
	public static final Insets INSETS_NAVI_CENTER= new Insets(0, 10, 0, 0);
	public static final Insets INSETS_NAVI_RIGHT = new Insets(0, 40, 0, 0);
	public static final Dimension DIM_NAVI_MAX = new Dimension(650,200);
	
	public static final Border BORDER_SAVE_DELETE = BorderFactory.createEmptyBorder(40, 0, 40, 0);
	public static final Dimension DIM_SAVE_DELETE_MAX = new Dimension(400,200);
	
	public static final Dimension DIM_FRAME = new Dimension(650, 780);
}
