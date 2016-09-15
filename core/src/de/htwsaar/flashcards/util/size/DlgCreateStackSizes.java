package de.htwsaar.flashcards.util.size;

import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.border.Border;

import de.htwsaar.flashcards.util.FlashCardConstants;

public class DlgCreateStackSizes {
	
	private static boolean large = FlashCardConstants.LARGE;
	
	public static final Dimension DIM_FRAME = large ? new Dimension(340, 330) : new Dimension(170, 265)  ;
	public static final Dimension DIM_STACK_NAME = large ? new Dimension(230,30) : new Dimension(115, 15) ;
	public static final Dimension DIM_STACK_SUBJECT = large ? new Dimension(230,130) : new Dimension(115,65);
	public static final Dimension DIM_LBL_NAME = large ? new Dimension(60,25) : new Dimension(30, 13);
	public static final Border BORDER_CONIRM_AREA = large ? BorderFactory.createEmptyBorder(10,40,10,40) : 
															BorderFactory.createEmptyBorder(5,20,5,20);
	public static final Dimension DIM_CONFIRM_AREA = large ? new Dimension(340,160) : new Dimension(170, 80);
}
