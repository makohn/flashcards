package de.htwsaar.flashcards.properties;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Toolkit;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.border.Border;

public class Dimensions {
	
	public static final String BUNDLE_NAME_LARGE = "de.htwsaar.flashcards.properties.dimensions_large";
	public static final String BUNDLE_NAME_SMALL = "de.htwsaar.flashcards.properties.dimensions_small";

	private static int height = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	
	private static ResourceBundle resource = height > 900 ? 
			ResourceBundle.getBundle(BUNDLE_NAME_LARGE) :
			ResourceBundle.getBundle(BUNDLE_NAME_SMALL);

	    public static Font getFont(String key){
	        Font value = null;
	        try {
	            String fontName = (String) resource.getString(key+ ".name");
	            int fontStyle = Integer.parseInt(resource.getString(key+ ".style"));
	            int fontSize = Integer.parseInt(resource.getString(key+ ".size"));
	            value = new Font(fontName, fontStyle, fontSize);
	        } catch (MissingResourceException e) {
	            e.printStackTrace();
	        }
	        return value;
	    }

	    public static Insets getInsets(String key){
	        Insets value = null;
	        try {
	            int top = Integer.parseInt(resource.getString(key+ ".top"));
	            int left = Integer.parseInt(resource.getString(key+ ".left"));
	            int bottom = Integer.parseInt(resource.getString(key+ ".bottom"));
	            int right = Integer.parseInt(resource.getString(key+ ".right"));
	            value = new Insets(top, left, bottom, right);
	        } catch (MissingResourceException e) {
	        	e.printStackTrace();
	        }
	        return value;
	    }
	    
	    public static Border getBorder(String key){
	        Border value = null;
	        try {
	            int top = Integer.parseInt(resource.getString(key+ ".top"));
	            int left = Integer.parseInt(resource.getString(key+ ".left"));
	            int bottom = Integer.parseInt(resource.getString(key+ ".bottom"));
	            int right = Integer.parseInt(resource.getString(key+ ".right"));
	            value = BorderFactory.createEmptyBorder(top, left, bottom, right);
	        } catch (MissingResourceException e) {
	        	e.printStackTrace();
	        }
	        return value;
	    }

	 public static Dimension getDimension(String key){
	        Dimension value = null;
	        try {
	            int X = Integer.parseInt(resource.getString(key+ ".x"));
	            int Y = Integer.parseInt(resource.getString(key+ ".y"));
	            value = new Dimension(X, Y);
	        } catch (MissingResourceException e) {
	        	e.printStackTrace();
	        }
	        return value;
	 }
	 
	 public static int getInteger(String key){
	        int value = 0;
	        try {
	            value = Integer.parseInt(resource.getString(key));
	        } catch (MissingResourceException e) {
	        	e.printStackTrace();
	        }
	        return value;
	 }
	 
	 public static void setResource(String bundle) {
			resource = ResourceBundle.getBundle(bundle);
	 } 
	 
	 public static ResourceBundle getResource() {
		 return resource;
	 }
}






