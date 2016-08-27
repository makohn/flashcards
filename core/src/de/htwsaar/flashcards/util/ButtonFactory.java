package de.htwsaar.flashcards.util;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicButtonUI;

public class ButtonFactory {
	
	public final static Color BTN_RED = new Color(179, 0, 0);
	public final static Color BTN_BLUE = new Color(0, 102, 204);
	public final static Color BTN_YELLOW = new Color(255, 153, 0);
	public final static Color BTN_GREEN = new Color(0, 153, 0);
	public final static Color BTN_PURPLE = new Color(153, 51, 153);
	
	private static void setColour(Color color, AbstractButton btn) {
		btn.setFont(new Font("Tahoma", Font.BOLD, 14));
		btn.setBackground(color);
		btn.setForeground(Color.white);
		btn.setOpaque(true);
		btn.setBorderPainted(false);
		btn.setUI(new StyledButtonUI());
	}
	
	public static JButton createColouredButton(String text, Color color) {
		JButton btn = new JButton(text);
		setColour(color, btn);
		return btn;
	}
	
	public static JRadioButton createColouredRadioButton(String text, Color color) {
		JRadioButton btn = new JRadioButton(text);
		setColour(color, btn);
		return btn;
	}
	
	public static JButton createImageButton(ImageIcon image) {
		JButton btn = new JButton(image);
	    btn.setContentAreaFilled(false);
	    btn.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
	    return btn;
	}
	
	public static JCheckBox createStyledCheckBox(Color color) {
		JCheckBox checkbox = new JCheckBox();
		checkbox.setUI(new StyledCheckBoxUI());
		checkbox.setBackground(color);
		checkbox.setMinimumSize(new Dimension(20,20));
		return checkbox;
	}
	
	static class StyledButtonUI extends BasicButtonUI {

	    @Override
	    public void installUI (JComponent c) {
	        super.installUI(c);
	        AbstractButton button = (AbstractButton) c;
	        button.setOpaque(false);
	        button.setBorder(new EmptyBorder(5, 15, 5, 15));
	    }

	    @Override
	    public void paint (Graphics g, JComponent c) {
	        AbstractButton b = (AbstractButton) c;
	        if(c instanceof JRadioButton) {
	        	paintBackground(g, b, b.getModel().isSelected() ? 2 : 0);
	        }
	        else {
	        	paintBackground(g, b, b.getModel().isPressed() ? 2 : 0);
	        }
	        super.paint(g, c);
	    }

	    private void paintBackground (Graphics g, JComponent c, int yOffset) {
	        Dimension size = c.getSize();
	        Graphics2D g2 = (Graphics2D) g;
	        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	        g.setColor(c.getBackground().darker());
	        g.fillRoundRect(0, yOffset, size.width, size.height - yOffset, 10, 10);
	        g.setColor(c.getBackground());
	        g.fillRoundRect(0, yOffset, size.width, size.height + yOffset - 5, 10, 10);
	    }
	}
	
	static class StyledCheckBoxUI extends BasicButtonUI {

	    @Override
	    public void installUI (JComponent c) {
	        super.installUI(c);
	        AbstractButton button = (AbstractButton) c;
	        button.setOpaque(false);
	        button.setBorder(new EmptyBorder(5, 15, 5, 15));
	    }

	    @Override
	    public void paint (Graphics g, JComponent c) {
	        AbstractButton b = (AbstractButton) c;
	        paintBackground(g, b);
	        super.paint(g, c);
	    }

	    private void paintBackground (Graphics g, JComponent c) {
	        Dimension size = c.getSize();
	        Graphics2D g2 = (Graphics2D) g;
	        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	        g.setColor(c.getBackground().darker());
	        g.fillRoundRect(2, 2, size.width, size.height - 2, 10, 10);
	        g.setColor(c.getBackground());
	        g.fillRoundRect(0, 0, size.width, size.height - 5, 10, 10);
	    }
	}
}
