package de.htwsaar.flashcards.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import de.htwsaar.flashcards.dao.interfaces.FlashCardDao;
import de.htwsaar.flashcards.database.Database;
import de.htwsaar.flashcards.model.FlashCard;

public class FlashCardDaoImpl implements FlashCardDao {
	
    private String sqlBefehl = new String("");
    private Statement dbBefehl = null;
    private Connection dbVerbindung = null;
    private ResultSet rsDatenmenge;

	@Override
	public void delete(FlashCard flashcard) {
		// TODO Auto-generated method stub
	}

	@Override
	public void save(FlashCard flashcard) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<FlashCard> getFlashCards() {
		return Database.selectSternFromFlashcard();
	}

	@Override
	public FlashCard get(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(FlashCard flashCard) {
		// TODO Auto-generated method stub
		
	}

	
}
