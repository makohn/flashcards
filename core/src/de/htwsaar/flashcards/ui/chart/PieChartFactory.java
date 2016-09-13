package de.htwsaar.flashcards.ui.chart;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import de.htwsaar.flashcards.ui.chart.PieChart2D.Slice;

/**
 * <code>PieChartFactory</code> - Dient dem Erstellen von Piecharts aus uebergebenen
 * Wertetripeln (Label, Wert, Farbe).
 * @author mkohn
 *
 */
public class PieChartFactory  {
	
	/**
	 * Fabrikmethode - Erstellt Slices (Stuecke) eines Piecharts aus den uebergebenen
	 * Wertetupeln.
	 * @param labels - die Bezeichnungen der Slices
	 * @param values - die Werte (Winkel) der SLices
	 * @param colors - die Farben der Slices
	 * @param size - die Groesse des Piecharts
	 * @return
	 */
	public static PieChart2D createPieChart(String[] labels,double[] values, Color[] colors, int size) {
		List<Slice> slices = new ArrayList<Slice>();
		if ((labels.length != values.length) || (labels.length  != colors.length)) {
			
		}
		for(int i = 0; i < labels.length; ++i) {
			Slice slc = new Slice(labels[i], colors[i], values[i]);
			slices.add(slc);
		}
		return new PieChart2D(slices, size);
	}
}
