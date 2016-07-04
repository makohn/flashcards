package de.htwsaar.flashcards.ui;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
 
// Damit Objekte der Klasse StackWindow
// zum ActionListener werden kann, muss das Interface
// ActionListener implementiert werden
public class StacksWindow extends JFrame implements ActionListener
{
    JButton button1;
    JButton button2;
    JButton button3;
    JButton button4;
    JButton button5;
    JButton button6;
    JButton button7;
    JButton button8;
    JLabel label;
    JPanel panel;
 
    public StacksWindow(){
        this.setTitle("stacks");//Name des Fensters
        this.setSize(800, 400); // Groesse des Fensters
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Fenster schliesst bei Anklicken des Kreuz
        panel = new JPanel();
 
        // Leeres JLabel-Objekt wird erzeugt
        label = new JLabel();
 
        //Buttons werden erstellt
        button1 = new JButton("stackname");
        button2 = new JButton ("edit");
        button3 = new JButton ("delete");
        button4 = new JButton ("export");
        button5 = new JButton ("study");
        button6 = new JButton ("study stack");
        button7 = new JButton ("create new stack");
        button8 = new JButton ("import stack");
        //Buttons werden dem Listener zugeordnet
        button1.addActionListener(this);
        button2.addActionListener(this);
        button3.addActionListener(this);
        button4.addActionListener(this);
        button5.addActionListener(this);
        button6.addActionListener(this);
        button7.addActionListener(this);
        button8.addActionListener(this);
        //Buttons werden dem JPanel hinzugefügt
        panel.add(button1);
        panel.add(button2);
        panel.add(button3);
        panel.add(button4);
        panel.add(button5);
        panel.add(button6);
        panel.add(button7);
        panel.add(button8);
        //JLabel wird dem Panel hinzugefügt
        panel.add(label);
        this.add(panel);
    }
 
    public static void main(String[] args)
    {
        // Ein neues Objekt der Klasse StacksWindow wird erzeugt
        // und sichtbar gemacht
    	StacksWindow sw = new StacksWindow();
        sw.setVisible(true);
    }
 
    public void actionPerformed (ActionEvent ae){
        // Die Quelle wird mit getSource() abgefragt und mit den
        // Buttons abgeglichen. Wenn die Quelle des ActionEvents einer
        // der Buttons ist, wird der Text des JLabels entsprechend geändert
        if(ae.getSource() == this.button1){
            label.setText(("Button 1 wurde betätigt"));
        }
        else if(ae.getSource() == this.button2){
            label.setText("Button 2 wurde betätigt");
        }
        else if (ae.getSource() == this.button3){
            label.setText(("Button 3 wurde betätigt"));
        }
        
        else if (ae.getSource() == this.button4){
            label.setText(("Button 4 wurde betätigt"));
        }
        
        else if (ae.getSource() == this.button5){
            label.setText(("Button 5 wurde betätigt"));
        }
        
        else if (ae.getSource() == this.button6){
            label.setText(("Button 6 wurde betätigt"));
        }
        
        else if (ae.getSource() == this.button7){
            label.setText(("Button 7 wurde betätigt"));
        }
        
        else if (ae.getSource() == this.button8){
            label.setText(("Button 8 wurde betätigt"));
        }
    }
}

