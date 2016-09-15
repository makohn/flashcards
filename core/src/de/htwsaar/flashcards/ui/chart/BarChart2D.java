package de.htwsaar.flashcards.ui.chart;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

/**
 * <code>BarChart2D</code> Sehr einfacher SWT Barchart, zugeschnitten auf die Anforderungen
 * im <code>flashcards</code> Projekt. Hat eine Legende und besteht aus einem Doppel-
 * balken, wessen Werte individuell gesetzt werden koennen.
 * @author Marek Kohn
 *
 */
public class BarChart2D extends JPanel {
	
	private static final long serialVersionUID = 2516307935611072266L;
	
	//Die abwechselnden Farben des Hintergrunds
	private static final Color LIGHTGREY = new Color(230, 230, 230, 80);
	private static final Color DARKGREY = new Color(217, 217, 217, 80);
	//Der Abstand zum Ursprung (links oben)
	private static final int ORIGIN_X = 40;
	private static final int ORIGIN_Y = 40;
	//Die Farbe der Balken
	private static final Color COLOR_RED = new Color(138, 30, 0);
	private static final Color COLOR_BLUE = new Color(87,148,171);
	//Die Luecke zwischen den Balken
	private static int GAP;
	
	private int[] values1;
	private int[] values2;
	
	private int size_y;
	private int size_x;
	private int legend_height;
	private int legend_width;
	private int bar_width;
	
	public BarChart2D(int[] values1, int[] values2, int height, int barwidth) {
		super.setOpaque(false);
		this.values1 = values1;
		this.values2 = values2;
		this.size_y = height;
		this.bar_width = barwidth;
		GAP = barwidth + 10;
		this.size_x = values1.length * (2*barwidth+GAP) + GAP;
	}
	
	/*
	 * Zeichnet einen Barchart.
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		 Graphics2D g2 = (Graphics2D) g;
		    RenderingHints rh = new RenderingHints(null);
		    rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		    rh.put(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
		    g2.addRenderingHints(rh);
		
		    float max = getMax(values1, values2);
		    
		    // Gitter 
		    g2.setStroke(new BasicStroke(.4f));
		    // y-Achse
		    g2.setColor(Color.black);
		    g2.drawLine(ORIGIN_X,ORIGIN_Y, ORIGIN_X, ORIGIN_Y +size_y);
		    // x-Achse
		    g2.drawLine(ORIGIN_X,ORIGIN_Y + size_y, ORIGIN_X + size_x, ORIGIN_Y + size_y);
		    // Schrittweite
		    int step = (int)size_y/10;
		    // Zeichne kleine Striche entlang der y-Achse
		    int j=0;
		    for(int i = ORIGIN_Y; i<ORIGIN_Y+size_y; i=i+step) {
		    	g2.setColor(j%2 == 0 ? LIGHTGREY : DARKGREY);
		    	g2.fillRect(ORIGIN_X+2, i, size_x, step-2);
		    	g2.setColor(Color.black);
		    	g2.drawLine(ORIGIN_X-5, i, ORIGIN_X, i);
//		    	g2.setColor(Color.lightGray);
//		    	g2.drawLine(ORIGIN_X, i, ORIGIN_X+size_x, i);
		    	++j;
		    }
		    
		    // Zeichne kleine Striche entlang der x-Achse
		    step = 2*bar_width + GAP;
		    int y = ORIGIN_Y+size_y;
		    j = 1;
		    for(int i = ORIGIN_X+step-bar_width; i<=size_x; i=i+step) {
		    	g2.setColor(Color.black);
		    	g2.drawLine(i, y+7, i, y);
		    	g2.drawString("" + j, i-4, y+25);
		    	g2.setColor(Color.lightGray);
		    	g2.drawLine(i, y, i, y-size_y);
		    	j++;
		    }
		    
		    // Bars [1]
		    for(int i = 0; i<values1.length;i++ ) {
		    	int height = Math.round(values1[i]/max * 0.9f*size_y);
		    	int start_X = ORIGIN_X+GAP+i*(2*bar_width+GAP);
		    	int start_Y = ORIGIN_Y+size_y-height-1;
		   
		    	GradientPaint gp = new GradientPaint(start_X, start_Y, COLOR_BLUE.brighter(), 
		    			start_X+bar_width, start_Y+height, COLOR_BLUE);
		    	g2.setPaint(gp);
		    	g2.fillRect(start_X, start_Y , bar_width, height);
		    	g2.setColor(COLOR_BLUE.darker());
		    	g2.drawRect(start_X, start_Y , bar_width, height);
		    	
		    	g2.drawString(values1[i]+"", start_X, start_Y-5);
		    }
		    // Bars [2]
		    for(int i = 0; i<values2.length;i++ ) {	
		    	int height = Math.round(values2[i]/max * 0.9f*size_y);
		    	int start_X = ORIGIN_X+GAP+i*(2*bar_width+GAP);
		    	int start_Y = ORIGIN_Y+size_y-height-1;
		    	GradientPaint gp = new GradientPaint(start_X+bar_width, start_Y, COLOR_RED.brighter(), 
		    			start_X+bar_width, start_Y+height, COLOR_RED);
		    	g2.setPaint(gp);
		    	g2.fillRect(start_X+bar_width, start_Y , bar_width, height);
		    	g2.setColor(COLOR_RED.darker());
		    	g2.drawRect(start_X+bar_width, start_Y , bar_width, height);
		    	g2.drawString(values2[i]+"", start_X+bar_width, start_Y-5);
		    } 
		    
		    drawLegend(g2);
	}
	
	/*
	 * Zeichnet die Legende eines Barcharts.
	 * TODO: Auslagern.
	 */
	private void drawLegend(Graphics2D g2) {
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		Color color = new Color(193, 215, 215,90);
		legend_height = 25;
		legend_width = size_x;
		int start_x = ORIGIN_X;
		int start_y = ORIGIN_Y + size_y + 35;
		g2.setColor(color);
		g2.fillRect(start_x, start_y , legend_width, legend_height);
		g2.setColor(color.darker());
		g2.drawRect(start_x, start_y , legend_width, legend_height);
		g2.setColor(COLOR_BLUE);
		g2.fillRect(start_x+10, start_y+10, 8, 8);
		g2.setColor(Color.black);
		g2.drawString("Stack 1", start_x+25, start_y+18);
		g2.setColor(COLOR_RED);
		g2.fillRect(start_x+100, start_y+10, 8, 8);
		g2.setColor(Color.black);
		g2.drawString("Stack 2", start_x+120, start_y+18);
		
	}
	
	private int getMax(int[] array1, int array2[]) {
		int max = array1[0];
		for (int i : array1) {
			max = i > max ? i : max;
		}
		for (int i : array2) {
			max = i > max ? i : max;
		}
		return max;
	}
	
	@Override
	public Dimension getPreferredSize() {
		return(new Dimension(ORIGIN_X+size_x+30, ORIGIN_Y+size_y+65+legend_height));
	}
	
	public void setValues1(int[] values1) {
		this.values1 = values1;
		repaint();
	}
	
	public void setValues2(int[] values2) {
		this.values2 = values2;
		repaint();
	}
}