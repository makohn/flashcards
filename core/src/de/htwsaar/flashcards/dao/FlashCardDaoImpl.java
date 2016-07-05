package de.htwsaar.flashcards.dao;
// Feick Martin
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import de.htwsaar.flashcards.dao.interfaces.FlashCardDao;
import de.htwsaar.flashcards.model.FlashCard;

public class FlashCardDaoImpl implements FlashCardDao {

	private String sqlCommand = new String("");
	private Connection con;
	private Statement dbCommand;
	private ResultSet results;
	
    public FlashCardDaoImpl () throws ClassNotFoundException {
        try {
         con = SQLiteJDBC.getConnection();
            dbCommand = con.createStatement();
        } catch (SQLException ex) {
            System.err.println("Fehler beim erstellen des Statements");
        }
    }

	@Override
	public void deleteCard(FlashCard flashcard) {
		int cardId = flashcard.getCardId();
			sqlCommand = "DELETE FROM Cards WHERE Card_Id = " + cardId;
	        try {
	            dbCommand.executeUpdate(sqlCommand);
	        } catch (SQLException ex) {
	            System.err.println("Fehler beim loeschen des Datensatzes!");
	        }	    
	}

	@Override
	public void saveCard(FlashCard flashcard) {
		sqlCommand = String.format("INSERT INTO Cards "
        + "(Card_Name, Card_Question, Card_Answer, Card_Stack_Id, Card_Picture_Link) " +
        "VALUES (\"%s\", \"%s\", \"%s\" , %d, \"%s\")", flashcard.getCardName(), flashcard.getCardQuestion(), flashcard.getCardAnswer(),  flashcard.getStackId(), flashcard.getCardPicture());
        System.out.println(sqlCommand);
        try {
            dbCommand.executeUpdate(sqlCommand);
        } catch (SQLException ex) {
            Logger.getLogger(FlashCardDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

	@Override
	public void updateCard(FlashCard flashcard) {
		sqlCommand = String.format("INSERT or replace INTO Cards VALUES (%d, \"%s\", \"%s\", \"%s\" , %d, %d, \"%s\")", flashcard.getCardId(), flashcard.getCardName(), flashcard.getCardQuestion(), flashcard.getCardAnswer(), flashcard.getBoxCounter(), flashcard.getStackId(), flashcard.getCardPicture());
        System.out.println(sqlCommand);
        try {
            dbCommand.executeUpdate(sqlCommand);
        } catch (SQLException ex) {
            Logger.getLogger(FlashCardDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
	}	
	
	@Override
	public List<FlashCard> getFlashCards() {

		List<FlashCard> liste = new ArrayList<FlashCard>();

		try {
			sqlCommand = "SELECT * FROM Cards;";
			results = dbCommand.executeQuery(sqlCommand);
			while (results.next()) {

				int cardId = results.getInt("Card_Id");
				String cardName = results.getString("Card_Name");
				String cardQuestion = results.getString("Card_Question");
				String cardAAnswer = results.getString("Card_Answer");
				int boxCounter = results.getInt("Card_Box_Counter");
				int stackId = results.getInt("Card_Stack_ID");
				//int owner = results.getInt("Card_Owner");
				String cardPicture = results.getString("Card_Picture_Link");

				liste.add(new FlashCard(cardId, cardName, cardQuestion, cardAAnswer ,boxCounter ,stackId, cardPicture));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return liste;
	}
	
	@Override
	public List<FlashCard> getFlashCards(int box) {

		List<FlashCard> liste = new ArrayList<FlashCard>();

		try {
			sqlCommand = "SELECT * FROM Cards WHERE Card_Box_Counter = " + box + ";";
			results = dbCommand.executeQuery(sqlCommand);
			while (results.next()) {

				int cardId = results.getInt("Card_Id");
				String cardName = results.getString("Card_Name");
				String cardQuestion = results.getString("Card_Question");
				String cardAAnswer = results.getString("Card_Answer");
				int boxCounter = results.getInt("Card_Box_Counter");
				int stackId = results.getInt("Card_Stack_ID");
				//int owner = results.getInt("Card_Owner");
				String cardPicture = results.getString("Card_Picture_Link");

				liste.add(new FlashCard(cardId, cardName, cardQuestion, cardAAnswer ,boxCounter ,stackId, cardPicture));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return liste;
	}

	@Override
	public FlashCard getCard(int id) {
        sqlCommand = "select * FROM Cards WHERE Card_Id = " + id;
        
        FlashCard flashcard = null;
        try {
        	results = dbCommand.executeQuery(sqlCommand); 
        	
			int cardId = results.getInt("Card_Id");
			String cardName = results.getString("Card_Name");
			String cardQuestion = results.getString("Card_Question");
			String cardAAnswer = results.getString("Card_Answer");
			int boxCounter = results.getInt("Card_Box_Counter");
			int stackId = results.getInt("Card_Stack_ID");
			//int owner = results.getInt("Card_Owner");
			String cardPicture = results.getString("Card_Picture_Link");
			
			flashcard = new FlashCard(cardId, cardName, cardQuestion, cardAAnswer ,boxCounter ,stackId, cardPicture);
			
        } catch (SQLException ex) {
            System.err.println("Fehler beim laden des Datensatzes!");
        }	
		return flashcard;
	}

	
}