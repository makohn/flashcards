package de.htwsaar.flashcards.ui;

import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import de.htwsaar.flashcards.model.GameOption;
import de.htwsaar.flashcards.model.Stack;
import de.htwsaar.flashcards.properties.Dimensions;
import de.htwsaar.flashcards.properties.Messages;
import de.htwsaar.flashcards.service.GameOptionServiceImpl;
import de.htwsaar.flashcards.service.StudyServiceImpl;
import de.htwsaar.flashcards.service.interfaces.GameOptionService;
import de.htwsaar.flashcards.ui.component.GradientPanel;
import de.htwsaar.flashcards.util.FlashCardButtonFactory;

/**
 * /**
 * <code>DlgGameOptions</code> - Dialog fuer die Auswahl einer gespeicherten Spieloption.
 * Die Option kann daraufin entweder bearbeitet werden oder es kann ein Spiel mit dieser
 * Option gestartet werden.
 * @author Marek Kohn
 */

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
		optionService = new GameOptionServiceImpl();
		options = optionService.getGameOptionArray();
		self = this;
		initButtons();
		initFrame();
	}
	
	private void initButtons() {
		
		btnGameOption = new JButton[MAX_OPTION_NR][MAX_BTN_NR];
		btnGameOption[0][OPTION] = FlashCardButtonFactory.createColouredButton(options[0].getName(), FlashCardButtonFactory.BTN_BLUE);
		btnGameOption[0][OPTION].addActionListener(new OptionButtonPressedListener(options[0]));
		btnGameOption[1][OPTION] = FlashCardButtonFactory.createColouredButton(options[1].getName(), FlashCardButtonFactory.BTN_YELLOW);
		btnGameOption[1][OPTION].addActionListener(new OptionButtonPressedListener(options[1]));
		btnGameOption[2][OPTION] = FlashCardButtonFactory.createColouredButton(options[2].getName(), FlashCardButtonFactory.BTN_GREEN);
		btnGameOption[2][OPTION].addActionListener(new OptionButtonPressedListener(options[2]));
		btnGameOption[3][OPTION] = FlashCardButtonFactory.createColouredButton(options[3].getName(), FlashCardButtonFactory.BTN_RED);
		btnGameOption[3][OPTION].addActionListener(new OptionButtonPressedListener(options[3]));

		for (JButton[] button : btnGameOption) {
			button[OPTION].setPreferredSize(Dimensions.getDimension("goptions.dim_button"));
			button[EDIT] = FlashCardButtonFactory.createImageButton(ICN_EDIT);
			button[EDIT].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					new FrmGameOptions();
					self.dispose();
					self.getOwner().toBack();
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
		
		mainPanel.setBorder(Dimensions.getBorder("goptions.border_main"));
		add(mainPanel);
		setTitle(Messages.getString("options")); 
		setSize(Dimensions.getDimension("goptions.dim_frame"));
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
			StudyServiceImpl service = new StudyServiceImpl(option, stack);
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
	