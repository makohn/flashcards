package de.htwsaar.flashcards.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;

import javax.swing.*;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.border.Border;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

// Damit Objekte der Klasse StackWindow
// zum ActionListener werden kann, muss das Interface
// ActionListener implementiert werden
public class StacksWindow extends JFrame //implements ActionListener 
{

	private static final long serialVersionUID = 3603735413969513435L;

	// Buttons
	private JButton bntEdit;
	private JButton bntDelete;
	private JButton bntExport;
	private JButton bntStudy;
	private JButton bntStudyStack;
	private JButton bntCreateStack;
	private JButton bntImportStack;

	// Label
	private JLabel lstSelectStack;

	// Panel
	private JPanel pnlNorth;
	private JPanel pnlCenter;
	private JPanel pnlSouth;

	// ComboBox
	private JComboBox<String> jcoStackName;

	// Stringarray zum Testen der Combobox
	String stackslist[] = { "stack1", "stack2", "stack3", "stack4", "stack5", "stack6", "stack7" };

	// Konstruktor
	public StacksWindow(){
        setTitle("stacks");//Name des Fensters
        setSize(500, 500); // Groesse des Fensters
        setLayout(new BorderLayout()); 

       
        
        //3 Panelobjekte werden erzeugt
        pnlNorth = new JPanel();
        pnlCenter = new JPanel();
        pnlSouth = new JPanel();
        
        // die Panels werden in das BorderLayout integriert
        add(pnlNorth, BorderLayout.NORTH);		
      	add(pnlCenter, BorderLayout.CENTER);
      	add(pnlSouth, BorderLayout.SOUTH);
      	
      	
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Fenster schliesst bei Anklicken des Kreuz
        setVisible(true);
     
      	
      
        //Komponenten werden erstellt
      	jcoStackName = new JComboBox<String>(stackslist);
        bntEdit = new JButton ("edit");
        bntDelete = new JButton ("delete");
        bntExport = new JButton ("export");
        bntStudy = new JButton ("study");
        bntStudyStack = new JButton ("study stack");
        bntCreateStack = new JButton ("create stack");
        bntImportStack = new JButton ("import stack");
        lstSelectStack = new JLabel("Select Stack:");
        

        
        //Buttons werden dem JPanel hinzugefügt
        pnlNorth.add(lstSelectStack);
        pnlNorth.add(jcoStackName);
        pnlNorth.add(bntEdit);
        pnlNorth.add(bntDelete);
        pnlNorth.add(bntExport);
        pnlNorth.add(bntStudy);
        pnlNorth.add(bntStudyStack);
        pnlCenter.add(bntCreateStack);
        pnlSouth.add(bntImportStack);
      
   
		// Listener
        jcoStackName.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
			}
		});
        
		bntEdit.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
			}
		});

		bntDelete.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
			}
		});
        
		bntExport.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
			}
		});
		
		bntStudy.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
			}
		});
		
		bntStudyStack.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
			}
		});
		
		bntCreateStack.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
			}
		});
		
		bntImportStack.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
			}
		});
		
		
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Fenster schliesst bei Anklicken des Kreuz
        setVisible(true);
	}
	public static void main(String[] args) {
		new StacksWindow();
	}
}
