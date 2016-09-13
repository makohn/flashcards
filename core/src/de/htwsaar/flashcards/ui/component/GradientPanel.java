package de.htwsaar.flashcards.ui.component;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

/**
 * <code>GradientPanel</code> Eine Unterklasse von JPanel. Rendert dieses mit einem
 * Farbverlauf als Hintergrund.
 * @author mkohn
 *
 */
public class GradientPanel extends JPanel {

	private static final long serialVersionUID = -5828241851827054583L;
	private static Color backgroundColor = new Color(230, 247, 255);
	
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        int w = getWidth();
        int h = getHeight();
        Color color1 = Color.white;
        Color color2 = backgroundColor;
        GradientPaint gp = new GradientPaint(0, 0, color1, 0, h, color2);
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, w, h);
    }
	
	/*
	 * Aendere die Hintergrundfarbe
	 */
	public static void changeBackground(Color bg) {
		backgroundColor = bg;
	}
}
