package de.htwsaar.flashcards.properties;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.border.Border;

public class Dimensions {
	
	private static final String BUNDLE_NAME = "de.htwsaar.flashcards.properties.dimensions_small";

	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);

	    public static Font getFont(String key){
	        Font value = null;
	        try {
	            String fontName = (String) RESOURCE_BUNDLE.getString(key+ ".name");
	            int fontStyle = Integer.parseInt(RESOURCE_BUNDLE.getString(key+ ".style"));
	            int fontSize = Integer.parseInt(RESOURCE_BUNDLE.getString(key+ ".size"));
	            value = new Font(fontName, fontStyle, fontSize);
	        } catch (MissingResourceException e) {
	            e.printStackTrace();
	        }
	        return value;
	    }

	    public static Insets getInsets(String key){
	        Insets value = null;
	        try {
	            int top = Integer.parseInt(RESOURCE_BUNDLE.getString(key+ ".top"));
	            int left = Integer.parseInt(RESOURCE_BUNDLE.getString(key+ ".left"));
	            int bottom = Integer.parseInt(RESOURCE_BUNDLE.getString(key+ ".bottom"));
	            int right = Integer.parseInt(RESOURCE_BUNDLE.getString(key+ ".right"));
	            value = new Insets(top, left, bottom, right);
	        } catch (MissingResourceException e) {
	        	e.printStackTrace();
	        }
	        return value;
	    }
	    
	    public static Border getBorder(String key){
	        Border value = null;
	        try {
	            int top = Integer.parseInt(RESOURCE_BUNDLE.getString(key+ ".top"));
	            int left = Integer.parseInt(RESOURCE_BUNDLE.getString(key+ ".left"));
	            int bottom = Integer.parseInt(RESOURCE_BUNDLE.getString(key+ ".bottom"));
	            int right = Integer.parseInt(RESOURCE_BUNDLE.getString(key+ ".right"));
	            value = BorderFactory.createEmptyBorder(top, left, bottom, right);
	        } catch (MissingResourceException e) {
	        	e.printStackTrace();
	        }
	        return value;
	    }

	 public static Dimension getDimension(String key){
	        Dimension value = null;
	        try {
	            int X = Integer.parseInt(RESOURCE_BUNDLE.getString(key+ ".x"));
	            int Y = Integer.parseInt(RESOURCE_BUNDLE.getString(key+ ".y"));
	            value = new Dimension(X, Y);
	        } catch (MissingResourceException e) {
	        	e.printStackTrace();
	        }
	        return value;
	 }
	 
	 public static int getInteger(String key){
	        int value = 0;
	        try {
	            value = Integer.parseInt(RESOURCE_BUNDLE.getString(key));
	        } catch (MissingResourceException e) {
	        	e.printStackTrace();
	        }
	        return value;
	 }
}






