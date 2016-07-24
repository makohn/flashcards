package de.htwsaar.flashcards.ui.component;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class JProgressCircle extends JPanel {
   
	private static final long serialVersionUID = 7588646195596150575L;

	private static final int FRACTION = 5;
	private static final int DELAY = 50;
	private static final Font FONT_COUNTER = new Font("SansSerif", Font.PLAIN, 20);
    
    private Timer timer;
    private int prgValue = 0;
    private int maxProgress;
    private JLabel lblTime;

    public JProgressCircle(int seconds) {
    	  this.maxProgress = seconds*20;
    	  this.lblTime = new JLabel(""+seconds);
    	  lblTime.setHorizontalAlignment(SwingUtilities.CENTER);
    	  lblTime.setFont(FONT_COUNTER);
    	  super.setLayout(new BorderLayout());
    	  super.setOpaque(false);
    	  super.add(lblTime);
          timer = new Timer(DELAY, new MyChangeListener());
          timer.start();
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

   class MyChangeListener implements ActionListener {
       @Override
       public void actionPerformed(ActionEvent arg0) {
    	   lblTime.setText(""+(1+(maxProgress - prgValue)/20));
           prgValue++;
           repaint();
           if (prgValue >= maxProgress) {
                timer.stop();
                lblTime.setText("0");
           }
      }
  }
}