package de.htwsaar.flashcards.ui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import de.htwsaar.flashcards.model.Stack;
import de.htwsaar.flashcards.properties.Messages;
import de.htwsaar.flashcards.service.StackServiceImpl;
import de.htwsaar.flashcards.service.interfaces.StackService;
import de.htwsaar.flashcards.ui.component.GradientPanel;
import de.htwsaar.flashcards.util.FlashCardButtonFactory;

public class DlgCreateStack extends JDialog {
	
	private static final long serialVersionUID = 7027007772101016979L;

	private static final String[] STACK_TYPE = {Messages.getString("normal"), Messages.getString("vocab")};
	
	private JPanel pnlStackName;
	private JPanel pnlConfirm;
	
	private JTextField txtStackName;
	private JTextArea txtStackSubject;
	private JComboBox<String> cmbStackType;
	private JButton btnOk;
	private JButton btnCancel;
	
	private JDialog self;
	private StackService stackService;
	
	public DlgCreateStack(Frame owner, boolean modal) {
		super(owner, modal);
		self = this;
		stackService = new StackServiceImpl();
		initNameArea();
		initConfirmArea();
		initListeners();
		
		JPanel mainPanel = new GradientPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
		mainPanel.add(pnlStackName);
		mainPanel.add(pnlConfirm);
		add(mainPanel);
		setTitle(Messages.getString("edit_stack")); 
		setSize(340, 330);
		setResizable(false);
		setLocationRelativeTo(getOwner());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	
	private void initNameArea() {
		FlowLayout layout = new FlowLayout(FlowLayout.LEFT);
		pnlStackName = new JPanel(layout);
		pnlStackName.setOpaque(false);
		
		txtStackName = new JTextField();
		txtStackName.setPreferredSize(new Dimension(230,30));
		cmbStackType = new JComboBox<String>(STACK_TYPE);
		txtStackSubject = new JTextArea();
		txtStackSubject.setLineWrap(true);
		txtStackSubject.setWrapStyleWord(true);
		txtStackSubject.setPreferredSize(new Dimension(230,130));
		JLabel lblName = new JLabel(Messages.getString("name"));
		lblName.setPreferredSize(new Dimension(60,25));
		pnlStackName.add(lblName);
		pnlStackName.add(txtStackName);
		JLabel lblSubject = new JLabel(Messages.getString("subject"));
		lblSubject.setPreferredSize(lblName.getPreferredSize());
		pnlStackName.add(lblSubject);
		pnlStackName.add(txtStackSubject);
		JLabel lblTyp = new JLabel(Messages.getString("type"));
		lblTyp.setPreferredSize(lblName.getPreferredSize());
		pnlStackName.add(lblTyp);
		pnlStackName.add(cmbStackType);
		pnlStackName.setBorder(BorderFactory.createTitledBorder(Messages.getString("new_stack")));
	}
	
	private void initConfirmArea() {
		pnlConfirm = new JPanel(new GridLayout(1, 2,10,10));
		pnlConfirm.setOpaque(false);
		btnOk = FlashCardButtonFactory.createColouredButton(Messages.getString("ok"), FlashCardButtonFactory.BTN_GREEN);
		btnCancel = FlashCardButtonFactory.createColouredButton(Messages.getString("cancel"), FlashCardButtonFactory.BTN_RED);
		pnlConfirm.add(btnOk);
		pnlConfirm.add(btnCancel);
		pnlConfirm.setBorder(BorderFactory.createEmptyBorder(10,40,10,40));
		pnlConfirm.setMaximumSize(new Dimension(340,160));
	}
	
	private void initListeners() {
		btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String stackName = txtStackName.getText();
				String stackSubject = txtStackSubject.getText();
				int type = cmbStackType.getSelectedIndex() + 1;
				stackService.save(new Stack(stackName, type, stackSubject));
				self.dispose();
			}
		});
		
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				self.dispose();
			}
		});
	}
}
