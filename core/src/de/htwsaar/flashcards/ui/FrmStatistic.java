//package de.htwsaar.flashcards.ui;
//
//import java.awt.BorderLayout;
//import java.awt.Component;
//import java.awt.Dimension;
//import java.awt.Graphics;
//import java.awt.GridBagConstraints;
//import java.awt.GridBagLayout;
//import java.awt.GridLayout;
//import java.awt.Insets;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.util.List;
//
//import javax.swing.BorderFactory;
//import javax.swing.JButton;
//import javax.swing.JComboBox;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JPanel;
//import javax.swing.JTextArea;
//
//import org.jfree.chart.ChartFactory;
//import org.jfree.chart.ChartPanel;
//import org.jfree.chart.JFreeChart;
//import org.jfree.chart.plot.PiePlot3D;
//import org.jfree.data.general.DefaultPieDataset;
//import org.jfree.util.Rotation;
//
//import de.htwsaar.flashcards.model.FlashCard;
//import de.htwsaar.flashcards.model.Stack;
//import de.htwsaar.flashcards.properties.Messages;
//import de.htwsaar.flashcards.service.FlashCardService;
//import de.htwsaar.flashcards.service.StackService;
//import de.htwsaar.flashcards.ui.component.GradientPanel;
//
//public class FrmStatistic {
//
//	private JPanel pnlStack1Select;
//	private JPanel pnlStack2Select;
//	private JComboBox<Stack> cmbStack1Select;
//	private JComboBox<Stack> cmbStack2Select;
//	private JPanel pnlStack1Stat;
//	private JPanel pnlStack2Stat;
//	private JTextArea txtAboxCountStack1;
//	private JTextArea txtAboxCountStack2;
//	private JTextArea txtAQuoteStack1;
//	private JTextArea txtAQuoteStack2;
//	private JFrame stackStatistic;
//	private ChartPanel CP1;	
//	private ChartPanel CP2;
//	private StackService stackService;
//	private FlashCardService cardService;
//	public FrmStatistic(){
//		stackService = new StackService();
//		cardService = new FlashCardService();
//		stackStatistic = new JFrame();
//	
//		
//		
//		initSelectArea1();
//		initStack1Statistic();
//		initSelectArea2();
//		initStack2Statistic();
//		initFrame();
//	}
//	
//	public void initSelectArea1(){
//		pnlStack1Select = new JPanel();
//		pnlStack1Select.setOpaque(false);
//		
//		cmbStack1Select = new JComboBox<Stack>(stackService.getStackArray());
//		cmbStack1Select.addActionListener(new UpdateTableActionListener());
//		
//		pnlStack1Select.add(cmbStack1Select);
//	}
//	
//	public void initSelectArea2(){
//		pnlStack2Select = new JPanel();
//		pnlStack2Select.setOpaque(false);
//		
//		cmbStack2Select = new JComboBox<Stack>(stackService.getStackArray());
//		cmbStack2Select.addActionListener(new UpdateTableActionListener());
//		
//		pnlStack2Select.add(cmbStack2Select);
//	}
//	
//	public void initStack1Statistic(){
//		pnlStack1Stat = new JPanel();
//		txtAboxCountStack1 = new JTextArea();
//		txtAboxCountStack1.setEditable(false);
//		//txtAboxCountStack1.setLineWrap(true);
//		txtAboxCountStack1.setOpaque(false);
//		
//		txtAQuoteStack1 = new JTextArea();
//		txtAQuoteStack1.setEditable(false);
//		txtAQuoteStack1.setOpaque(false);
//		int id = getSelectedStack1ID();
//		System.out.println(id);
//		int box1count = 0;
//		int box2count = 0;
//		int box3count = 0;
//		int box4count = 0;
//		int correctAnswer = 0;
//		int asked = 0;
//		
//		for(int i = 1; i<=4;i++ ){
//			List<FlashCard> flashcards = cardService.getFlashCards(getSelectedStack1ID(),i);
//			for(FlashCard fc : flashcards){
//				System.out.println(fc.toString());
//				switch (i) {
//				case 1:
//					box1count++;
//					break;
//				case 2:
//					box2count++;
//					break;
//				case 3:
//					box3count++;
//					break;
//				case 4:
//					box4count++;
//					break;
//
//				default:
//					break;
//				}
//				correctAnswer = correctAnswer +fc.getCardAnswerCorrect();
//				asked = asked + fc.getCardAsked();
//			}
//		}
//		txtAboxCountStack1.setText("In Box 1 befinden sich "+ box1count+ " Karten\n" +
//				"In Box 2 befinden sich "+ box2count+ " Karten\n" +
//				"In Box 3 befinden sich "+ box3count+ " Karten\n" +
//				"In Box 4 befinden sich "+ box4count+ " Karten\n");
//		if(id==2){
//			txtAboxCountStack2.setText(txtAboxCountStack2.getText()+" ID!!!!");
//		}
//		DefaultPieDataset dataset = new DefaultPieDataset();
//		dataset.setValue("Korrekt", new Double(correctAnswer));
//		dataset.setValue("Falsch", new Double(asked-correctAnswer));
//		JFreeChart chart = ChartFactory.createPieChart(null, dataset,true, true, true);
//
//		
//		CP1 = new ChartPanel(chart);
//		CP1.setPreferredSize(new java.awt.Dimension(250,135));
//		txtAQuoteStack1.setText("Es wurden "+ correctAnswer+ "Karten von \n"+ asked+" gefragten Karten richtig beantwortet");
//		JPanel myPanel = new JPanel();
//
//		myPanel.add(CP1);
//		pnlStack1Stat.setLayout(new GridLayout(0,3));
//		pnlStack1Stat.add(txtAboxCountStack1);
//		pnlStack1Stat.add(myPanel);
//		pnlStack1Stat.add(txtAQuoteStack1);
//		
//	}
//	
//	public void initStack2Statistic(){
//		pnlStack2Stat = new JPanel();
//		txtAboxCountStack2 = new JTextArea();
//		txtAboxCountStack2.setEditable(false);
//		//txtAboxCountStack2.setLineWrap(true);
//		txtAboxCountStack2.setOpaque(false);
//		txtAQuoteStack2 = new JTextArea();
//		txtAQuoteStack2.setEditable(false);
//		txtAQuoteStack2.setLineWrap(true);
//		txtAQuoteStack2.setOpaque(false);
//		int box1count = 0;
//		int box2count = 0;
//		int box3count = 0;
//		int box4count = 0;
//		int correctAnswer = 0;
//		int asked = 0;
//		for(int i = 1; i<=4;i++ ){
//			List<FlashCard> flashcards = cardService.getFlashCards(getSelectedStack2ID(),i);
//			for(FlashCard fc : flashcards){
//				System.out.println(fc.toString());
//				switch (i) {
//				case 1:
//					box1count++;
//					break;
//				case 2:
//					box2count++;
//					break;
//				case 3:
//					box3count++;
//					break;
//				case 4:
//					box4count++;
//					break;
//
//				default:
//					break;
//				}
//				correctAnswer = correctAnswer +fc.getCardAnswerCorrect();
//				asked = asked + fc.getCardAsked();
//			}
//		}
//		txtAboxCountStack2.setText("In Box 1 befinden sich "+ box1count+ " Karten "
//				+ "\n" +
//				"In Box 2 befinden sich "+ box2count+ " Karten"
//				+ "\n" +
//				"In Box 3 befinden sich "+ box3count+ " Karten"
//						+ "\n" +
//				"In Box 4 befinden sich "+ box4count+ " Karten"
//						+ "\n"  );
//
//		
//		DefaultPieDataset dataset = new DefaultPieDataset();
//		dataset.setValue("Korrekt", new Double(correctAnswer));
//		dataset.setValue("Falsch", new Double(asked-correctAnswer));
//		JFreeChart chart = ChartFactory.createPieChart(null, dataset,true, true, true);
//
//		
//		CP2 = new ChartPanel(chart);
//		CP2.setPreferredSize(new java.awt.Dimension(250,135));
//	
//		txtAQuoteStack2.setText("Es wurden "+ correctAnswer+ " Karten(green) von \n"+ asked+" gefragten Karten richtig beantwortet");
//		JPanel myPanel = new JPanel();
//		myPanel.add(CP2);
//		//myPanel.setLayout(new BorderLayout());
//		//myPanel.add(new Chart(correctAnswer, asked-correctAnswer),BorderLayout.CENTER);
//		pnlStack2Stat.setLayout(new GridLayout(0,3));
//		
//		//pnlStack2Stat.add(new Chart(), BorderLayout.CENTER);
//		pnlStack2Stat.add(txtAboxCountStack2);
//		pnlStack2Stat.add(myPanel);
//		pnlStack2Stat.add(txtAQuoteStack2);
//		//pnlStack2Stat.add(new Chart(), BorderLayout.CENTER);
//	}
//	
//	private void initFrame() {
//	    JPanel mainPanel = new GradientPanel();
//	    
//	    mainPanel.setLayout(new GridBagLayout());
//	    GridBagConstraints c = new GridBagConstraints();
//	    //new Insets(0, 0, 0, 10);
//	    c.anchor = GridBagConstraints.BASELINE_LEADING;
//	    //-------------------------------------
//	    c.gridx = 0;
//	    c.gridy = 0;
//	    c.insets = new Insets(15, 0, 0, 20);
//	    mainPanel.add(new JLabel("choose_a_stack"), c);
//	    //-------------------------------------
//	    c.insets = new Insets(0,0,0,0);
//	    c.gridx = 1;
//	    mainPanel.add(pnlStack1Select, c);
//	    //-------------------------------------
//	    c.insets = new Insets(0, 0, 0, 20);
//	    c.gridx = 0;
//	    c.gridy = 1;
//	    mainPanel.add(new JLabel("Statistics"), c);
//	    //-------------------------------------
//	    c.insets = new Insets(0,0,0,0);
//	    c.gridx = 1;
//	    c.gridy = 1;
//	    mainPanel.add(pnlStack1Stat,c);
//	    //-------------------------------------
//	    c.gridx = 0;
//	    c.gridy = 2;
//	    mainPanel.add(new JLabel("choose_a_second_stack"), c);
//	    //-------------------------------------
//	    c.insets = new Insets(0,0,0,0);
//	    c.gridx = 1;
//	    mainPanel.add(pnlStack2Select, c);
//	    //-------------------------------------
//	    c.insets = new Insets(0, 0, 0, 20);
//	    c.gridx = 0;
//	    c.gridy = 3;
//	    mainPanel.add(new JLabel("Statistics"), c);
//	    //-------------------------------------
//	    c.insets = new Insets(0,0,0,0);
//	    c.gridx = 1;
//	    c.gridy = 3;
//	    mainPanel.add(pnlStack2Stat,c);
//	    //-------------------------------------
//	    
//	    stackStatistic.add(mainPanel);
//	    stackStatistic.setVisible(true);
//	    stackStatistic.setMinimumSize(new Dimension(730, 500));
//	    stackStatistic.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//	  }
//	
//	private int getSelectedStack1ID(){
//		return ((Stack)cmbStack1Select.getSelectedItem()).getStackId();
//	}
//	private int getSelectedStack2ID(){
//		return ((Stack)cmbStack2Select.getSelectedItem()).getStackId();
//	}
//	
//	private void updatePanel1(){
//		int box1count = 0;
//		int box2count = 0;
//		int box3count = 0;
//		int box4count = 0;
//		int correctAnswer = 0;
//		int asked = 0;
//		for(int i = 1; i<=4;i++ ){
//			List<FlashCard> flashcards = cardService.getFlashCards(getSelectedStack1ID(),i);
//			for(FlashCard fc : flashcards){
//				System.out.println(fc.toString());
//				switch (i) {
//				case 1:
//					box1count++;
//					break;
//				case 2:
//					box2count++;
//					break;
//				case 3:
//					box3count++;
//					break;
//				case 4:
//					box4count++;
//					break;
//
//				default:
//					break;
//				}
//				correctAnswer = correctAnswer +fc.getCardAnswerCorrect();
//				asked = asked + fc.getCardAsked();
//			}
//		}
//
//		txtAboxCountStack1.setText("In Box 1 befinden sich "+ box1count+ " Karten "
//				+ "\n" +
//				"In Box 2 befinden sich "+ box2count+ " Karten"
//				+ "\n" +
//				"In Box 3 befinden sich "+ box3count+ " Karten"
//						+ "\n" +
//				"In Box 4 befinden sich "+ box4count+ " Karten"
//						+ "\n" );		
//		txtAQuoteStack1.setText("Es wurden "+ correctAnswer+ " Karten von \n"+ asked+" gefragten Karten richtig beantwortet");
//		CP1.removeAll();
//		DefaultPieDataset dataset = new DefaultPieDataset();
//		dataset.setValue("Korrekt", new Double(correctAnswer));
//		dataset.setValue("Falsch", new Double(asked-correctAnswer));
//		JFreeChart chart = ChartFactory.createPieChart(null, dataset,true, true, true);
//		CP1.setChart(chart);
//	}
//	
//	private void updatePanel2(){
//		int box1count = 0;
//		int box2count = 0;
//		int box3count = 0;
//		int box4count = 0;
//		int correctAnswer = 0;
//		int asked = 0;
//		for(int i = 1; i<=4;i++ ){
//			List<FlashCard> flashcards = cardService.getFlashCards(getSelectedStack2ID(),i);
//			for(FlashCard fc : flashcards){
//				System.out.println(fc.toString());
//				switch (i) {
//				case 1:
//					box1count++;
//					break;
//				case 2:
//					box2count++;
//					break;
//				case 3:
//					box3count++;
//					break;
//				case 4:
//					box4count++;
//					break;
//
//				default:
//					break;
//				}
//				correctAnswer = correctAnswer +fc.getCardAnswerCorrect();
//				asked = asked + fc.getCardAsked();
//			}
//		}
//
//		txtAboxCountStack2.setText("In Box 2 befinden sich "+ box1count+ " Karten "
//				+ "\n" +
//				"In Box 2 befinden sich "+ box2count+ " Karten"
//				+ "\n" +
//				"In Box 3 befinden sich "+ box3count+ " Karten"
//						+ "\n" +
//				"In Box 4 befinden sich "+ box4count+ " Karten"
//						+ "\n" );
//		
//		txtAQuoteStack2.setText("Es wurden "+ correctAnswer+ " Karten von \n"+ asked+" gefragten Karten richtig beantwortet");
//		CP2.removeAll();
//		DefaultPieDataset dataset = new DefaultPieDataset();
//		dataset.setValue("Korrekt", new Double(correctAnswer));
//		dataset.setValue("Falsch", new Double(asked-correctAnswer));
//		JFreeChart chart = ChartFactory.createPieChart(null, dataset,true, true, true);
//		CP2.setChart(chart);
//	}
//	
//	private class UpdateTableActionListener implements ActionListener{
//		
//		@Override
//		public void actionPerformed(ActionEvent arg0) {
//			// TODO Auto-generated method stub
//			updatePanel1();
//			updatePanel2();
//			//pnlStack1Stat.repaint();
//			pnlStack2Stat.repaint();
//		}
//	}
//	
//	public static void main(String[] args) {
//		new FrmStatistic();
//	}
//}
