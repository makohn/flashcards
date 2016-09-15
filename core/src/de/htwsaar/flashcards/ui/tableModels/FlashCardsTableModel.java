package de.htwsaar.flashcards.ui.tableModels;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import de.htwsaar.flashcards.model.FlashCard;
import de.htwsaar.flashcards.properties.Messages;

public class FlashCardsTableModel implements TableModel {
	
	private static final ImageIcon ICN_HAS_IMG = new ImageIcon("res/images/tiny_tick.png");
	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM.yy (HH:mm)");
	
	private List<FlashCard> flashcards;
	private List<TableModelListener> listeners;
	
	private static final int COLUMN_COUNT = 4;

	public FlashCardsTableModel(List<FlashCard> flashCardList) {
		this.flashcards = flashCardList;
		listeners = new ArrayList<TableModelListener>();
	}
	
	@Override
	public int getRowCount() {
		return flashcards.size();
	}

	@Override
	public int getColumnCount() {
		return COLUMN_COUNT;
	}

	@Override
	public String getColumnName(int columnIndex) {
		switch(columnIndex) {
		case 0: return Messages.getString("label");
		case 1: return Messages.getString("box");
		case 2: return Messages.getString("last_acc");
		case 3: return Messages.getString("image");
		default: return null;
		}
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch(columnIndex) {
		case 0: return String.class;
		case 1: return Integer.class;
		case 2: return String.class;
		case 3: return ImageIcon.class;
		default: return null;
		}
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch(columnIndex) {
		case 0: return flashcards.get(rowIndex).getCardName();
		case 1: return flashcards.get(rowIndex).getBoxCounter();
		case 2: return DATE_FORMAT.format(flashcards.get(rowIndex).getCardLastAccessDate());
		case 3: return flashcards.get(rowIndex).hasPic() ? ICN_HAS_IMG : null;
		default: return null;
		}
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
	}

	@Override
	public void addTableModelListener(TableModelListener l) {
		listeners.add(l);
		
	}

	@Override
	public void removeTableModelListener(TableModelListener l) {
		listeners.remove(l);
	}
	
	public void setFlashCards(List<FlashCard> flashCardList) {
		this.flashcards = flashCardList;
	}
	
}
