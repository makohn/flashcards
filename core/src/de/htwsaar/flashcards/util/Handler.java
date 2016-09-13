package de.htwsaar.flashcards.util;

/**
 * <code>Handler</code> - Imitiert das uebergeben einer Rueckruffunktion 
 * in Java (Callback Handler). Statt der Funktion kann so eine anonyme Instanz
 * dieser Klasse uebergeben werden. (vgl. <code>Callable</code> )
 * 
 * In diesem speziellen Handler muss die Funktion allerdings immer einen
 * boolschen Wert zurueckgeben.
 * 
 * @author mkohn
 *
 * @param <V> - Der Rueckgabewert der Funktion
 */
public interface Handler<V> {
	public V call(boolean arg);
}
