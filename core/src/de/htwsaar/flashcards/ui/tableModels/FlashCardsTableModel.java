package de.htwsaar.flashcards.ui.tableModels;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import de.htwsaar.flashcards.model.FlashCard;

public class FlashCardsTableModel implements TableModel {

	private List<FlashCard> flashcards;
	private List<TableModelListener> listeners;
	
	private static final int COLUMN_COUNT = 2;
	private static final String COL_FLASHCARD_NAME = "Bezeichnung";
	private static final String COL_BOX_COUNTER = "Box";
	
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
		default: return null;
		}
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch(columnIndex) {
		case 0: return String.class;
		case 1: return Integer.class;
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
