package de.htwsaar.flashcards.ui.tableModels;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import de.htwsaar.flashcards.model.FlashCard;

public class FlashCardsTableModel implements TableModel {
	
	private static final ImageIcon ICN_HAS_IMG = new ImageIcon("res/images/tiny_tick.png");
	
	private List<FlashCard> flashcards;
	private List<TableModelListener> listeners;
	
	private static final int COLUMN_COUNT = 4;
	private static final String COL_FLASHCARD_NAME = "Bezeichnung";
	private static final String COL_BOX_COUNTER = "Box";
	private static final String COL_IMAGE = "Bild";
	private static final String COL_LAST_TIME = "Letzes Mal";
	
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
		case 0: return COL_FLASHCARD_NAME;
		case 1: return COL_BOX_COUNTER;
		case 2: return COL_LAST_TIME;
		case 3: return COL_IMAGE;
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
		case 2: return flashcards.get(rowIndex).getCardLastAccessDate();
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
