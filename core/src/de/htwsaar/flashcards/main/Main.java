package de.htwsaar.flashcards.main;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Insets;

import javax.swing.JFileChooser;
import javax.swing.Painter;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import de.htwsaar.flashcards.ui.FrmSelectStack;

 /** 
 * Dient dem Starten des Programms.
 * @author mkohn, Feick Martin
 *
 */
public class Main {
	
	public static void main(String[] args) throws ClassNotFoundException {
		// Um konkurenten Zugriff auf die als thread-unsicheren geltenden 
		// Swing Datenstrukturen zu verhindern, wird die GUI auf einem seperaten
		// Thread (dem ATW Event-Dispatching Thread) ausgefuehrt.
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				configLookAndFeel();
				new FrmSelectStack();
			}
		});		
	}
	
	private static void configLookAndFeel() {
		try {
			UIManager.setLookAndFeel(new NimbusLookAndFeel());
			UIManager.getLookAndFeelDefaults()
	        .put("defaultFont", new Font("Tahoma", Font.PLAIN, 14));
			 UIManager.getLookAndFeelDefaults()
			 .put("nimbusOrange", (new Color(0, 163, 204)));
			 UIManager.getLookAndFeelDefaults()
			 .put("info", Color.white);
			 UIManager.getLookAndFeelDefaults()
			 .put("OptionPane.background", new Color(230, 247, 255));
			 UIManager.getLookAndFeelDefaults()
			 .put("Panel.background", new Color(230, 247, 255));
			 UIManager.getLookAndFeelDefaults()
			 .put("FileChooser.background", new Color(230, 247, 255));
			 UIManager.getLookAndFeelDefaults()
			 .put("MenuBar:Menu.contentMargins", new Insets(1, 8, 2, 8));
			 UIManager.getLookAndFeelDefaults()
			 .put("FileChooser[Enabled].backgroundPainter", new Painter<JFileChooser>() {
                @Override
                public void paint(Graphics2D g, JFileChooser object, int width, int height)
                {
                    g.setColor(new Color(230, 247, 255));
                    g.draw(object.getBounds());
                }
            });
		} catch (UnsupportedLookAndFeelException e1) {
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (Exception e2) {
				System.err.println("Fatal Error: Can't load Look and Feel defaults");
			}
		}	
	}
}