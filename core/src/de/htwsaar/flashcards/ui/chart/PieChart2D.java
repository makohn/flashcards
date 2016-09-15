package de.htwsaar.flashcards.ui.chart;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RadialGradientPaint;
import java.awt.RenderingHints;
import java.awt.geom.Arc2D;
import java.util.List;

import javax.swing.JPanel;

/**
 * <code>PieChart2D</code> - Sehr einfacher SWT Piechart, angepasst auf die Anforderungen
 * im <code>flashcards</code> Projekt. Besteht aus einzelnen <code>Slices</code>, die als
 * Hilfsklasse beigefuegt sind. Einzelne <code>Slices</code> koennen auch ein Offset einthalten,
 * sodass diese aus dem Kreis herausstechen.
 * @author Marek Kohn
 *
 */
public class PieChart2D extends JPanel {

	private static final long serialVersionUID = 7557010515025818114L;
	
	// Abstand zum Ursprung 
	private static final int ORIGIN_X = 20;
	private static final int ORIGIN_Y = 20;
	// Abstand der Legende
	private static final int LEGEND_MARGIN_X = ORIGIN_X -19;
	private static final int LEGEND_MARGIN_Y = ORIGIN_Y +15;

	private List<Slice> slices;
	private int size;
	private int legend_height;
	private int legend_width;

	public PieChart2D(List<Slice> slices, int size) {
		super();
		this.slices = slices;
		this.size = size;
		this.legend_height = 20 + slices.size() * 20;
		this.legend_width = 130;
		super.setOpaque(false);
	}

	/*
	 * Zeichnet einen Piechart
	 */
	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		// Setze Renderinghints fuer die verbesserte Darstellung von rundlichen Formen :
		RenderingHints rh = new RenderingHints(null);
		rh.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		rh.put(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
		rh.put(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
		rh.put(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
		g2.addRenderingHints(rh);

		// Setze die Umrandung eines Slices
		BasicStroke stroke = new BasicStroke(1.6f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL);
		g2.setStroke(stroke);
		// Initialisiere den Kreisbogen
		Arc2D.Float arc = new Arc2D.Float(Arc2D.PIE);
		int radius = size / 2;
		int x = ORIGIN_X;
		int y = ORIGIN_Y;
		arc.setFrame(x, y, size, size);
		// Zeiche die einzelnen Slices
		int signum = 1; // Fuer die Ausrichtung der Slices je nach Quadrant
		double delta = 0;
		double start = 360; // Damit der Chart "nach unten" gezeichnet wird
		double end = 360;

		for (Slice slice : slices) {
			end = -3.6 * (slice.getValue());
			if((int)end == 0) continue;
			signum = Math.abs(end) > 180 ? 1 : -1;
			arc.setAngleStart(start);
			arc.setAngleExtent(end);
			if (slice.getOffset() > 0) {
				double offset = signum * slice.getOffset() - delta;
				arc.setArcByCenter(shiftX(arc, offset), shiftY(arc, offset), radius, start, end, Arc2D.PIE);
				delta = offset;
			} else {
				delta = 0;
				arc.setFrame(x, y, size, size);
			}
			paintSlice(g2, slice.getColor(), arc);
			start = start + end;
		}
		
		drawLegend(g2);
	}
	
	/*
	 * Zeichnet die Slices eines Piecharts
	 */
	private void paintSlice(Graphics2D g2, Color color, Arc2D arc) {
		float[] dist = { 0.1f, 0.8f };
		Color[] colors = { color.brighter(), color };
		RadialGradientPaint gp = new RadialGradientPaint(
				new Point((int) (arc.getCenterX() - 0.13 * size), (int) (arc.getCenterY() - (0.17 * size))),
				(float) (0.9) * size, dist, colors);

		g2.setPaint(gp);
		g2.fill(arc);
		g2.setColor(color.darker());
		g2.draw(arc);
	}
	
	/*
	 * Zeichnet die Legende eines Piecharts
	 */
	private void drawLegend(Graphics2D g2) {
		// background
		Color color = new Color(193, 215, 215,90);
		int start_x = LEGEND_MARGIN_X;
		int start_y = size+LEGEND_MARGIN_Y;
		g2.setColor(color);
		g2.fillRect(start_x, start_y , legend_width, legend_height);
		g2.setColor(color.darker());
		g2.drawRect(start_x, start_y , legend_width, legend_height);
		
		// points
		start_y = start_y + 10;
		for(int i = 0; i < slices.size(); ++i) {
			createLegendSquare(g2, start_x+5, start_y+i*15, 8, slices.get(i).getColor());
			start_y += 9;
			g2.setColor(Color.black);
			g2.drawString(slices.get(i).toString(), start_x+20, start_y+i*15);
		}	
	}
	
	/*
	 * Erstellt kleine Quadrate fuer die Legende in der passenden Farbe
	 */
	private void createLegendSquare(Graphics2D g2, int x, int y, int size, Color color) {
		g2.setColor(color);
		g2.drawRect(x, y, size, size);
		g2.fillRect(x, y, size, size);
	}

	/*
	 * Verschiebt einen Slice in x-Richtung (falls Offset gesetzt)
	 */
	private double shiftX(Arc2D arc, double offset) {
		double o = (arc.getStartPoint().getX() + arc.getEndPoint().getX()) / 2;
		return o - (1 + offset) * (o - arc.getCenterX());
	}

	/*
	 * Verschiebt einen Slice in y-Richtung (falls Offset gesetzt)
	 */
	private double shiftY(Arc2D arc, double offset) {
		double o = (arc.getStartPoint().getY() + arc.getEndPoint().getY()) / 2;
		return o - (1 + offset) * (o - arc.getCenterY());
	}
	
	/*
	 * Aktualisiert den Piechart mit neuen Werten fuer die Slices.
	 */
	public void update(double[] values) {
		for(int i = 0; i < values.length; ++i) {
			slices.get(i).setValue(values[i]);
		}
		repaint();
	}
	
	@Override
	public Dimension getPreferredSize() {
		int x = (int)Math.round(size*1.35);
		int y = (int)Math.round(size*1.8) + legend_height;
		return new Dimension(x,y);
	}
	
	/**
	 * Hilfsklasse <code>Slice</code> (Struktur). Kapselt einen
	 * einzelnen Bestandteil eines Piecharts.
	 * @author mkohn
	 *
	 */
	public static class Slice {
		private double value;
		private String label;
		private Color color;
		private double offset = 0;

		public Slice(String label, Color color, double value) {
			this.value = value;
			this.label = label;
			this.color = color;
		}

		public Slice(String label, Color color, double value, double offset) {
			this(label, color, value);
			this.offset = offset;
		}

		public double getValue() {
			return value;
		}

		public double getOffset() {
			return offset;
		}

		public String getLabel() {
			return label;
		}

		public Color getColor() {
			return color;
		}
		
		public void setValue(double value) {
			this.value = value;
		}

		public void setOffset(double offset) {
			this.offset = offset;
		}

		public void setLabel(String label) {
			this.label = label;
		}

		public void setColor(Color color) {
			this.color = color;
		}

		@Override
		public String toString() {
			return getLabel() + " (" + Math.round(getValue()) + "%)";
		}
	}
}
