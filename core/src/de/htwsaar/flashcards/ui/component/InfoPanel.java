package de.htwsaar.flashcards.ui.component;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.concurrent.Callable;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.Border;

import de.htwsaar.flashcards.util.size.InfoPanelSizes;

/**
 * <code>InfoPanel</code> - Dient der Darstellung von Informationen ueber den
 * Spielverlauf unabhaengig von der Art des Spiels (Selbstevaluation, Vokabel,..)
 * Enthaelt Informationen ueber:
 * 	- die verbleibende Zeit eines Spielzugs
 * 	- den Fortschritt innerhalb einer gespielten Liste
 * 	- die Nummer der gerade gespielten Karte
 *  - den Namen der gerade gespielten Karte
 * @author Marek Kohn
 *
 */
public class InfoPanel extends JPanel {

	private static final long serialVersionUID = -2951701270136199284L;

	private static final Border OUTER_CARD_BORDER = BorderFactory.createEmptyBorder(10, 10, 10, 10);

	private JLabel lblBoxCounter;
	private JLabel lblCardname;
	private JLabel lblCardCounter;
	private JProgressBar progressbar;
	private ProgressCircle progresscircle;
	
	private int round;
	private int time;
	
	public InfoPanel(int nrOfCards, int time, Insets[] insets) {
			this.round = 0;
			this.time = time;
		 	this.setLayout(new GridBagLayout());
	        this.setOpaque(false);
	        this.setBorder(OUTER_CARD_BORDER);
	        progressbar = new JProgressBar(0, nrOfCards);
	        progresscircle = new ProgressCircle(time);
	        progresscircle.setPreferredSize(InfoPanelSizes.DIM_CIRCLE);
	        progresscircle.setMinimumSize(getPreferredSize());
	        lblCardCounter = new JLabel();
	        lblCardCounter.setFont(InfoPanelSizes.FONT_LBL_CARD_COUNTER);
	        lblCardname = new JLabel();
	        lblBoxCounter = new JLabel();
	        GridBagConstraints gc = new GridBagConstraints();
	        //-------------------------------------
			gc.gridx = 0; gc.gridy = 0;
			gc.insets = insets[0];
			this.add(lblBoxCounter, gc);
			//-------------------------------------
			gc.gridx++;
			gc.insets = insets[1];
			this.add(lblCardname,gc);
			//-------------------------------------
			gc.gridx = 0; gc.gridy++;
			gc.insets = insets[0];
			this.add(lblCardCounter,gc);
			//-------------------------------------
			gc.gridx++;
			gc.insets = insets[1];
			this.add(progressbar,gc);
			//-------------------------------------
			gc.gridx++;
			gc.gridy = 0;
			gc.gridheight = 2;
			gc.insets = insets[2];
			//-------------------------------------
			this.add(progresscircle,gc);
			this.setMaximumSize(InfoPanelSizes.DIM_MAXIMUM_SIZE);
	}
	
	/*
	 * Aktualisiert das Infopanel nach Beenden eines Spielzugs. 
	 */
	public void update(String cardName, int box) {
		round++;
		lblCardname.setText(cardName);
		lblBoxCounter.setText("Box: " + box);
		lblCardCounter.setText("Q" + round);
		progressbar.setValue(round-1);
		if (time > 0)
			progresscircle.restart();
	}
	
	public void finish() {
		progressbar.setValue(progressbar.getMaximum());
		if (time > 0)
			progresscircle.stop();
	}
	
	public void stopTimer() {
		if (time > 0)
			progresscircle.stop();
	}
	
	/*
	 * Bietet eine Schittstelle zum Aktualisieren der Zeitanzeige
	 * durch eine Elterkomponente.
	 * Insbesondere interessant fuer das Stoppen der Zeit nach Beantwortung 
	 * einer Frage.
	 */
	public void setHandler(Callable<Void> handler) {
		if (time > 0)
			progresscircle.setHandler(handler);
	}
}
