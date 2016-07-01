package de.htwsaar.flashcards.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import de.htwsaar.flashcards.dao.interfaces.FlashCardDao;
import de.htwsaar.flashcards.database.Database;
import de.htwsaar.flashcards.model.FlashCard;

public class FlashCardDaoImpl implements FlashCardDao {

	private String sqlBefehl = new String("");
	private Statement dbBefehl = null;
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

		List<FlashCard> liste = new ArrayList<FlashCard>();

		try {
			sqlBefehl = "SELECT * FROM Cards;";
			rsDatenmenge = dbBefehl.executeQuery(sqlBefehl);
			while (rsDatenmenge.next()) {

				int cardId = rsDatenmenge.getInt("Card_Id");
				String cardName = rsDatenmenge.getString("Card_Name");
				String cardQuestion = rsDatenmenge.getString("Card_Question");
				String cardAAnswer = rsDatenmenge.getString("Card_Answer");
				int boxCounter = rsDatenmenge.getInt("Card_Box_Counter");
				int stackId = rsDatenmenge.getInt("Card_Stack_ID");
				// int owner = rsDatenmenge.getInt("Card_Owner);
				String cardPicture = rsDatenmenge.getString("Card_Picture_Link");

				liste.add(new FlashCard(cardId, cardName, cardQuestion, cardAAnswer ,boxCounter ,stackId, null));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return liste;
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
