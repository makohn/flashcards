package de.htwsaar.flashcards.ui;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import de.htwsaar.flashcards.model.GameOption;
import de.htwsaar.flashcards.model.Stack;
import de.htwsaar.flashcards.properties.Messages;
import de.htwsaar.flashcards.service.GameOptionService;
import de.htwsaar.flashcards.service.StudyService;
import de.htwsaar.flashcards.ui.component.GradientPanel;
import de.htwsaar.flashcards.util.ButtonFactory;

public class DlgGameOptions extends JDialog {
	
	private static final long serialVersionUID = 2232482702828888995L;
	
	private static final ImageIcon ICN_EDIT = new ImageIcon("res/images/edit.png");
	private static final int MAX_OPTION_NR = 4;
	private static final int MAX_BTN_NR =  2;
	private static final int OPTION = 0;
	private static final int EDIT = 1;

	private JButton[][] btnGameOption;
	
	private JDialog self;
	
	private GameOption[] options;
	private GameOptionService optionService;

	private Stack stack;
	
	public DlgGameOptions(Frame owner, boolean modal,Stack stack) {
		super(owner, modal);
		this.stack = stack;
		optionService = new GameOptionService();
		options = optionService.getGameOptionArray();
		self = this;
		initButtons();
		initFrame();
	}
	
	private void initButtons() {
		
		btnGameOption = new JButton[MAX_OPTION_NR][MAX_BTN_NR];
		btnGameOption[0][OPTION] = ButtonFactory.createColouredButton(options[0].getName(), ButtonFactory.BTN_BLUE);
		btnGameOption[0][OPTION].addActionListener(new OptionButtonPressedListener(options[0]));
		btnGameOption[1][OPTION] = ButtonFactory.createColouredButton(options[1].getName(), ButtonFactory.BTN_YELLOW);
		btnGameOption[1][OPTION].addActionListener(new OptionButtonPressedListener(options[1]));
		btnGameOption[2][OPTION] = ButtonFactory.createColouredButton(options[2].getName(), ButtonFactory.BTN_GREEN);
		btnGameOption[2][OPTION].addActionListener(new OptionButtonPressedListener(options[2]));
		btnGameOption[3][OPTION] = ButtonFactory.createColouredButton(options[3].getName(), ButtonFactory.BTN_RED);
		btnGameOption[3][OPTION].addActionListener(new OptionButtonPressedListener(options[3]));

		for (JButton[] button : btnGameOption) {
			button[OPTION].setPreferredSize(new Dimension(280,65));
			button[EDIT] = ButtonFactory.createImageButton(ICN_EDIT);
			button[EDIT].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					new FrmGameOptions();
					self.dispose();
					self.getOwner().dispose();
				}
			});
		}
	}
	
	private void initFrame() {
		
		JPanel mainPanel = new GradientPanel();
		mainPanel.setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		gc.gridy = 0;
		gc.weighty = 1.1;
		for (JButton button[] : btnGameOption) {
			gc.gridx = 0;
			gc.weightx = 0.85;
			mainPanel.add(button[OPTION],gc);
			gc.gridx = 1;
			gc.weightx = 0.15;
			mainPanel.add(button[EDIT],gc);
			gc.gridy++;
		}
		
		mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
		add(mainPanel);
		setTitle(Messages.getString("options")); 
		setSize(500, 400);
		setResizable(false);
		setLocationRelativeTo(getOwner());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	
	private class OptionButtonPressedListener implements ActionListener {
		private GameOption option;
		public OptionButtonPressedListener(GameOption option) {
			this.option = option;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			StudyService service = new StudyService(option, stack);
			if(service.noFlashCardsInList()) {
				JOptionPane.showMessageDialog(self, Messages.getString("no_cards"));
			}
			else {
				switch(stack.getTyp()) {
				case 1: 
					new FrmStudy(service, new SelfEvalUIFactory());
					break;
				case 2: 
					new FrmStudy(service, new VocabUIFactory());
					break;
				}
				self.dispose();
				self.getOwner().dispose();
			}
		}	
	}
	
}
	