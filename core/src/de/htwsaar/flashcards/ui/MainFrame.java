package de.htwsaar.flashcards.ui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class MainFrame extends JFrame{
	
	private static final long serialVersionUID = 1146483853074764310L;
	
	private JButton btnNewGame;
	private JButton btnCreateCards;
	private JFrame self;
	
	public MainFrame() {
		
		self = this;
		
		setTitle("MainFrame");
		setSize(150,100);
		setLayout(new GridLayout(2, 1));
		
		btnNewGame = new JButton("Neues Spiel");
		btnNewGame.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				self.setVisible(false);
				try {
					new StudyWindow();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		btnCreateCards = new JButton("Karten erstellen");
		btnCreateCards.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				self.setVisible(false);
				try {
					new EditStackWindow("Test");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		add(btnNewGame);
		add(btnCreateCards);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
}
