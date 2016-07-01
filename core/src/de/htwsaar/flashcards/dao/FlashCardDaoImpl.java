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

	private String sqlBefehl = new String("");
	private Connection con;
	private Statement dbBefehl = null;
	private ResultSet rsDatenmenge;
	
    public FlashCardDaoImpl () throws ClassNotFoundException {
        try {
         con = SQLiteJDBC.getConnection();
            dbBefehl = con.createStatement();
        } catch (SQLException ex) {
            System.err.println("Fehler beim erstellen des Statements");
        }
    }

	@Override
	public void delete(FlashCard flashcard) {
		int cardId = flashcard.getCardId();
	        String sql = "DELETE FROM Cards WHERE Card_Id = " + cardId;
	        try {
	            dbBefehl.executeUpdate(sql);
	        } catch (SQLException ex) {
	            System.err.println("Fehler beim l�schen des Datensatzes!");
	        }	    
	}

	@Override
	public void save(FlashCard flashcard) {
        String sql = String.format("INSERT or replace INTO Cards VALUES (%d, \"%s\", \"%s\", \"%s\" , %d, %d, \"%s\")", flashcard.getCardId(), flashcard.getCardName(), flashcard.getCardQuestion(), flashcard.getCardAnswer(), flashcard.getBoxCounter(), flashcard.getStackId(), flashcard.getCardPicture());
        System.out.println(sql);
        try {
            dbBefehl.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(FlashCardDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
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
				//int owner = rsDatenmenge.getInt("Card_Owner");
				String cardPicture = rsDatenmenge.getString("Card_Picture_Link");

				liste.add(new FlashCard(cardId, cardName, cardQuestion, cardAAnswer ,boxCounter ,stackId, cardPicture));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return liste;
	}

	@Override
	public FlashCard get(int id) {
        String sqlBefehl = "select FROM Cards WHERE Card_Id = " + id;
        
        FlashCard flashcard = null;
        try {
        	rsDatenmenge = dbBefehl.executeQuery(sqlBefehl); 
        	
			int cardId = rsDatenmenge.getInt("Card_Id");
			String cardName = rsDatenmenge.getString("Card_Name");
			String cardQuestion = rsDatenmenge.getString("Card_Question");
			String cardAAnswer = rsDatenmenge.getString("Card_Answer");
			int boxCounter = rsDatenmenge.getInt("Card_Box_Counter");
			int stackId = rsDatenmenge.getInt("Card_Stack_ID");
			//int owner = rsDatenmenge.getInt("Card_Owner");
			String cardPicture = rsDatenmenge.getString("Card_Picture_Link");
			
			flashcard = new FlashCard(cardId, cardName, cardQuestion, cardAAnswer ,boxCounter ,stackId, cardPicture);
			
        } catch (SQLException ex) {
            System.err.println("Fehler beim laden des Datensatzes!");
        }	
		return flashcard;
	}	
}