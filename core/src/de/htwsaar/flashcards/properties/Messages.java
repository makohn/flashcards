package de.htwsaar.flashcards.properties;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * Dient dem Erzeugen von Strings (z.b. fuer JLabels) mithilfe von .properties
 * Dateien. So kann eine multilinguale Beschriftung realisiert werden.
 * @author Nora Sommer
 *
 */
public class Messages {
	public static final String BUNDLE_NAME_DE = "de.htwsaar.flashcards.properties.messages_de"; 
	public static final String BUNDLE_NAME_EN = "de.htwsaar.flashcards.properties.messages_en"; 
	public static final String BUNDLE_NAME_ES = "de.htwsaar.flashcards.properties.messages_es"; 

	private static ResourceBundle resource = ResourceBundle.getBundle(BUNDLE_NAME_DE);

	private Messages() {}
	
	public static String getString(String key) {
		try {
			return resource.getString(key);
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}
	
	public static void setResource(String bundle) {
		resource = ResourceBundle.getBundle(bundle);
	}
	
	public static ResourceBundle getRessource() {
		return resource;
	}
}
