package de.htwsaar.flashcards.ui.component;

import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class FilterComboBox extends JComboBox<String> {
    
	private static final long serialVersionUID = -8872018845431448667L;
	
	private String[] array;

    public FilterComboBox(String[] array) {
    	super(array);
        this.array = array;
        this.setEditable(true);
        this.setPreferredSize(new Dimension(150, this.getPreferredSize().height));
        final JTextField textfield = (JTextField) this.getEditor().getEditorComponent();
        textfield.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent ke) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        comboFilter(textfield.getText());
                    }
                });
            }
        });
    }
    
    public void comboFilter(String enteredText) {
        if (!this.isPopupVisible()) {
            this.showPopup();
        }

        List<String> filterArray= new ArrayList<String>();
        for (int i = 0; i < array.length; i++) {
            if (array[i].toLowerCase().contains(enteredText.toLowerCase())) {
                filterArray.add(array[i]);
            }
        }
        if (filterArray.size() > 0) {
            DefaultComboBoxModel<String> model = (DefaultComboBoxModel<String>) this.getModel();
            model.removeAllElements();
            for (String s: filterArray)
                model.addElement(s);

           JTextField textfield = (JTextField) this.getEditor().getEditorComponent();
           textfield.setText(enteredText);
        }
    }
}
