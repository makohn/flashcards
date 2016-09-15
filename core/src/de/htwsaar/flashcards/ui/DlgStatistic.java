package de.htwsaar.flashcards.ui;

import java.awt.Color;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import de.htwsaar.flashcards.dao.FlashCardDaoImpl;
import de.htwsaar.flashcards.dao.interfaces.FlashCardDao;
import de.htwsaar.flashcards.model.Stack;
import de.htwsaar.flashcards.service.FlashCardServiceImpl;
import de.htwsaar.flashcards.service.StackServiceImpl;
import de.htwsaar.flashcards.service.interfaces.FlashCardService;
import de.htwsaar.flashcards.service.interfaces.StackService;
import de.htwsaar.flashcards.ui.chart.BarChart2D;
import de.htwsaar.flashcards.ui.chart.PieChart2D;
import de.htwsaar.flashcards.ui.chart.PieChartFactory;
import de.htwsaar.flashcards.ui.component.GradientPanel;
import de.htwsaar.flashcards.util.size.DlgStatisticSizes;

/**
 * <code>DlgStatistic</code> - Dialog zum Anzeigen von Statistiken und
 * insbesondere dem Vergleichen von zwei Stacks. Angezeigt werden
 * 		- Quote richtige/falsche Antwort <code>PieChart2D</code>
 * 		- Anzahl Karten je Box <code>BarChart2D</code>
 * 
 * @author Ben Meder
 * @see PieChart2D, BarChart2D
 *
 */
public class DlgStatistic extends JDialog {

	private static final long serialVersionUID = -2758740201812060160L;
	
	private JPanel pnlStackSelect;
	private JPanel pnlStack2Select;
	private JComboBox<Stack> cmbStackSelect;
	private JComboBox<Stack> cmbStack2Select;
	private JFrame stackStatistic;
	
	private PieChart2D pieChart;
	private PieChart2D pieChart2;
	private BarChart2D barChart;
	
	private StackService stackService;
	private FlashCardService cardService;
	
	private FlashCardDao dao = new FlashCardDaoImpl();
	
	public DlgStatistic(Frame owner, boolean modal){
		super(owner, modal);
		stackStatistic = new JFrame();
		stackStatistic.setResizable(true);
		stackService = new StackServiceImpl();
		cardService = new FlashCardServiceImpl();
		initPieChartArea();
		initPieChart2Area();
		initBarChartArea();
		initFrame();
	}
	
	private void initPieChartArea() {
		pnlStackSelect = new JPanel();
		pnlStackSelect.setLayout(new BoxLayout(pnlStackSelect, BoxLayout.PAGE_AXIS));
		pnlStackSelect.setOpaque(false);
		cmbStackSelect = new JComboBox<Stack>(stackService.getStackArray());
		pieChart = PieChartFactory.createPieChart(new String[] {"Richtig","Falsch"},
				new double[] {80, 20},
				new Color[] {new Color(135, 180, 0),new Color(138, 30, 0)},
				DlgStatisticSizes.SIZE_PIE_CHART);

		pnlStackSelect.add(cmbStackSelect);
	    pnlStackSelect.add(pieChart);
	   
		cmbStackSelect.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Stack selection = (Stack)cmbStackSelect.getSelectedItem();
				int i = dao.getAnswerCorrectCount(selection);
				int j = dao.getAskedCount(selection);
				double quote = j > 0 ? i*100/j : 0;
				pieChart.update(new double[] {quote, 100-quote});
				barChart.setValues1(cardService.getCardsInBox(selection));
			}
		});
	}
	
	private void initPieChart2Area() {
		pnlStack2Select = new JPanel();
		pnlStack2Select.setLayout(new BoxLayout(pnlStack2Select, BoxLayout.PAGE_AXIS));
		pnlStack2Select.setOpaque(false);
		cmbStack2Select = new JComboBox<Stack>(stackService.getStackArray());
		pieChart2 = PieChartFactory.createPieChart(new String[] {"Richtig","Falsch"},
				new double[] {80, 20},
				new Color[] {new Color(135, 180, 0),new Color(138, 30, 0)},
				DlgStatisticSizes.SIZE_PIE_CHART);

		pnlStack2Select.add(cmbStack2Select);
	    pnlStack2Select.add(pieChart2);
	   
		cmbStack2Select.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Stack selection = (Stack)cmbStack2Select.getSelectedItem();
				int i = dao.getAnswerCorrectCount(selection);
				int j = dao.getAskedCount(selection);
				double quote = j > 0 ? i*100/j : 0;
				pieChart2.update(new double[] {quote, 100-quote});
				barChart.setValues2(cardService.getCardsInBox(selection));
			}
		});
	}
	
	private void initBarChartArea() {
		int[] values1 = cardService.getCardsInBox((Stack)cmbStackSelect.getSelectedItem());
		int[] values2 = cardService.getCardsInBox((Stack)cmbStack2Select.getSelectedItem());
		barChart = new BarChart2D(values1,
								  values2, 
								  DlgStatisticSizes.SIZE_BAR_CHART, 
								  DlgStatisticSizes.WIDTH_BAR);
	}
		
	
	
	private void initFrame() {
	    JPanel mainPanel = new GradientPanel();
	    
	    mainPanel.setLayout(new GridBagLayout());
	    GridBagConstraints c = new GridBagConstraints();
	    c.anchor = GridBagConstraints.PAGE_START;
	    c.fill = GridBagConstraints.HORIZONTAL;
	    //-------------------------------------
	    c.gridx = 0;
	    c.gridy = 0;
	    c.insets = DlgStatisticSizes.INSETS_LEFT_PIE;
	    mainPanel.add(pnlStackSelect,c);
	    c.gridx=1;
	    c.insets = DlgStatisticSizes.INSETS_RIGHT_PIE;
	    mainPanel.add(pnlStack2Select,c);
	    c.insets = new Insets(0,0,0,0);
	    c.gridx=0;
	    c.gridy++;
	    mainPanel.add(new JLabel("Karten in Box: "), c);
	    c.gridy++;
	    c.gridwidth=2;
	    mainPanel.add(barChart,c);
	    mainPanel.setBorder(BorderFactory.createTitledBorder("Vergleiche Stacks"));
	    
	    stackStatistic.add(mainPanel);
	    stackStatistic.setMinimumSize(DlgStatisticSizes.DIM_FRAME);
	    stackStatistic.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	    stackStatistic.setLocationRelativeTo(getOwner());
	    stackStatistic.setTitle("Statistik");
	    stackStatistic.setResizable(true);
	    stackStatistic.setVisible(true);
	  }
}
