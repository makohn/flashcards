package de.htwsaar.flashcards.ui.component;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.AbstractButton;
import javax.swing.DefaultButtonModel;

/**
 * <code>JSwitchBox</code> imitiert einen einfachen ON/OFF Switch in Java SWT.
 * ------------------------
 * |     ON    ###########|
 * ------------------------
 * @author olihoz, modifiziert von Marek Kohn
 * @link http://stackoverflow.com/questions/7304017/how-to-add-sexy-on-off-sliders
 *
 */
public class JSwitchBox extends AbstractButton {

	private static final long serialVersionUID = 9499207356234037L;
	
	private Color colorBright = new Color(220,220,220);
    private Color colorDark = new Color(150,150,150);
    private Color black  = new Color(0,0,0,100);
    private Color trueWhite = Color.white;
    private Color transWhite = new Color(255,255,255,100);
    private Color white = transWhite;
    private Color light = new Color(220,220,220,100);
    private Color red = new Color(179, 0, 0);
    private Color green = new Color(0, 153, 0);
    private Font font = new Font("Tahoma", Font.BOLD, 14);
    private int gap = 5;
    private int globalWitdh = 0;
    private final String trueLabel;
    private final String falseLabel;
    private Dimension thumbBounds;
    private int max;
    private MouseSwitchAdapter mouseAdapter;


    public JSwitchBox(String trueLabel, String falseLabel) {
        this.trueLabel = trueLabel;
        this.falseLabel = falseLabel;
        double trueLenth = getFontMetrics( getFont() ).getStringBounds( trueLabel, getGraphics() ).getWidth();
        double falseLenght = getFontMetrics( getFont() ).getStringBounds( falseLabel, getGraphics() ).getWidth();
        max = (int)Math.max( trueLenth, falseLenght );
        gap =  Math.max( 5, 5+(int)Math.abs(trueLenth - falseLenght ) ); 
        thumbBounds  = new Dimension(max+gap*2,20);
        globalWitdh =  max + thumbBounds.width+gap*2;
        setModel( new DefaultButtonModel() );
        setSelected( false );
        mouseAdapter = new MouseSwitchAdapter();
        addMouseListener(mouseAdapter);
    }

    @Override
    public void setEnabled(boolean b) {
    	if(b) {
    		if (getMouseListeners() != null) {
    			setBackground(isSelected() ? green : red);
    			addMouseListener(mouseAdapter);
    			trueWhite = Color.white;
    			repaint();
    		}
    	}
    	else {
    		setBackground(Color.lightGray);
    		trueWhite = transWhite;
    		removeMouseListener(mouseAdapter);
    		repaint();
    	}
    }
    
    @Override
    public boolean isEnabled() {
    	return trueWhite.equals(Color.white);
    }
    
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(globalWitdh, thumbBounds.height);
    }

    @Override
    public void setSelected( boolean b ) {
        if(b){
            setText( trueLabel );
            setBackground( green );
        } else {
            setBackground( red );
            setText( falseLabel );
        }
        super.setSelected( b );
    }
    @Override
    public void setText( String text ) {
        super.setText( text );
    }

    @Override
    public int getHeight() {
        return getPreferredSize().height;
    }

    @Override
    public int getWidth() {
        return getPreferredSize().width;
    }

    @Override
    public Font getFont() {
        return font;
    }
    
    @Override
    public boolean isSelected() {
    	return getText().equals(trueLabel);
    }

    @Override
    protected void paintComponent( Graphics g ) {
        g.setColor( getBackground() );
        g.fillRoundRect( 1, 1, getWidth()-2 - 1, getHeight()-2 ,2 ,2 );
        Graphics2D g2 = (Graphics2D)g;

        g2.setColor( black );
        g2.drawRoundRect( 1, 1, getWidth()-2 - 1, getHeight()-2 - 1, 2,2 );
        g2.setColor( white );
        g2.drawRoundRect( 1 + 1, 1 + 1, getWidth()-2 - 3, getHeight()-2 - 3, 2,2 );

        int x = 0;
        int lx = 0;
        if(isSelected()) {
            lx = thumbBounds.width;
        } else {
            x = thumbBounds.width;
        }
        int y = 0;
        int w = thumbBounds.width;
        int h = thumbBounds.height;

        g2.setPaint( new GradientPaint(x, (int)(y-0.1*h), colorDark , x, (int)(y+1.2*h), light) );
        g2.fillRect( x, y, w, h );
        g2.setPaint( new GradientPaint(x, (int)(y+.65*h), light , x, (int)(y+1.3*h), colorDark) );
        g2.fillRect( x, (int)(y+.65*h), w, (int)(h-.65*h) );

        if (w>14){
            int size = 10;
            g2.setColor( colorBright );
            g2.fillRect(x+w/2-size/2,y+h/2-size/2, size, size);
            g2.setColor( new Color(120,120,120));
            g2.fillRect(x+w/2-4,h/2-4, 2, 2);
            g2.fillRect(x+w/2-1,h/2-4, 2, 2);
            g2.fillRect(x+w/2+2,h/2-4, 2, 2);
            g2.setColor( colorDark );
            g2.fillRect(x+w/2-4,h/2-2, 2, 6);
            g2.fillRect(x+w/2-1,h/2-2, 2, 6);
            g2.fillRect(x+w/2+2,h/2-2, 2, 6);
            g2.setColor( new Color(170,170,170));
            g2.fillRect(x+w/2-4,h/2+2, 2, 2);
            g2.fillRect(x+w/2-1,h/2+2, 2, 2);
            g2.fillRect(x+w/2+2,h/2+2, 2, 2);
        }

        g2.setColor( black );
        g2.drawRoundRect( x, y, w - 1, h - 1, 2,2 );
        g2.setColor( white );
        g2.drawRoundRect( x + 1, y + 1, w - 3, h - 3, 2,2 );

        g2.setColor( trueWhite );
        g2.setRenderingHint( RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON );
        g2.setFont( getFont() );
        g2.drawString( getText(), lx+gap, y+h/2+h/4 );
    }
    
    private class MouseSwitchAdapter extends MouseAdapter {
    	@Override
        public void mouseReleased( MouseEvent e ) {
            if(new Rectangle( getPreferredSize() ).contains( e.getPoint() )) {
                setSelected( !isSelected() );
            }
        }
    }
}

