package de.htwsaar.flashcards.ui.component;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Callable;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import de.htwsaar.flashcards.properties.Dimensions;
import de.htwsaar.flashcards.properties.Messages;

/**
 * <code>JProgressCircle</code> - eine visuelle Darstellung eines Timers. Besteht aus einem
 * sich fuellenden Kreis und einer textuellen Anzeige der verbleibenden Zeit.
 * @author Marek Kohn
 *
 */
public class ProgressCircle extends JPanel {
   
	private static final long serialVersionUID = 7588646195596150575L;

	private static final int FRACTION = 5;
	private static final int DELAY = 50;
    
    private Timer timer;
    private int prgValue = 0;
    private int maxProgress;
    private JLabel lblTime;
    private Callable<Void> handler;

    public ProgressCircle(int seconds) {
    	  this.maxProgress = seconds*20;
    	  this.lblTime = new JLabel(seconds == 0 ? Messages.getString("infinity") : ""+seconds);
    	  lblTime.setHorizontalAlignment(SwingUtilities.CENTER);
    	  lblTime.setFont(Dimensions.getFont("circle.font_counter"));
    	  super.setLayout(new BorderLayout());
    	  super.setOpaque(false);
    	  super.add(lblTime);
          timer = new Timer(DELAY, new MyChangeListener());
    }
    
    /*
     * Zugriffsschnittstelle fuer Signale von aussen.
     */
    public void setHandler(Callable<Void> handler) {
    	this.handler = handler;
    }

    @Override
    protected void paintComponent(Graphics g) {
           Graphics2D g2 = (Graphics2D) g;
          
           g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
           if (prgValue <= maxProgress) {
             g.setColor(new Color(255, 153, 51));
             int angle = -(int) (((float) prgValue / maxProgress) * 360);
             g.fillArc(0, 0, getWidth(), getHeight(), 90, angle);
             g2.setColor(Color.WHITE);
             g.fillArc(getWidth() / FRACTION / 2, getHeight() / FRACTION / 2,
    		  getWidth() * (FRACTION - 1) / FRACTION, getHeight() * (FRACTION - 1) / FRACTION, 90, angle-5);
          }
   }
    
    public void restart() {
    	prgValue = 0;
    	timer.start();
    }
    
    public void stop() {
    	timer.stop();
    }

   /*
    * Erzeugt die Animation.
    */
   class MyChangeListener implements ActionListener {
       @Override
       public void actionPerformed(ActionEvent arg0) {
    	   lblTime.setText(""+(1+(maxProgress - prgValue)/20));
           prgValue++;
           repaint();
           if (prgValue >= maxProgress) {
                timer.stop();
                lblTime.setText("0");
                try {
					handler.call();
				} catch (Exception e) {
					e.printStackTrace();
				}
           }
      }
  }
}