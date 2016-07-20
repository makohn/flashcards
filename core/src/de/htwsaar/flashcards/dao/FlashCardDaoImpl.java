package de.htwsaar.flashcards.dao;

// Feick Martin
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import de.htwsaar.flashcards.dao.flashcard.pre.DeleteFlashCardDaoPreStatement;
import de.htwsaar.flashcards.dao.interfaces.FlashCardDao;
import de.htwsaar.flashcards.model.FlashCard;

public class FlashCardDaoImpl implements FlashCardDao {

	private String sqlCommand = new String("");
	private Connection con;
	private Statement dbCommand;
	private ResultSet results;

//	public FlashCardDaoImpl() throws ClassNotFoundException {
//		try {
//			con = SQLiteJDBC.getConnection();
//			dbCommand = con.createStatement();
//		} catch (SQLException ex) {
//			System.err.println("Fehler beim erstellen des Statements");
//		}
//	}
	
 	private JdbcTemplate jdbc; 
 
 	@Autowired 
 	public FlashCardDaoImpl(DataSource jdbc) { 
 		this.jdbc = new JdbcTemplate(jdbc); 
 	} 

	public FlashCardDaoImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void deleteCard(int flashcard) {
		jdbc.update(new DeleteFlashCardDaoPreStatement(flashcard));
	}

	@Override
	public void saveCard(FlashCard flashcard) {
		sqlCommand = String.format(
				"INSERT INTO Cards " + "(Card_Name, Card_Question, Card_Answer, Card_Stack_Id, Card_Picture_Link) "
						+ "VALUES (\"%s\", \"%s\", \"%s\" , %d, \"%s\")",
				flashcard.getCardName(), flashcard.getCardQuestion(), flashcard.getCardAnswer(), flashcard.getStackId(),
				flashcard.getCardPicture());
		System.out.println(sqlCommand);
		try {
			dbCommand.executeUpdate(sqlCommand);
		} catch (SQLException ex) {
			Logger.getLogger(FlashCardDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@Override
	public void updateCard(FlashCard flashcard) {
		sqlCommand = String.format("INSERT or replace INTO Cards VALUES (%d, \"%s\", \"%s\", \"%s\" , %d, %d, \"%s\")",
				flashcard.getCardId(), flashcard.getCardName(), flashcard.getCardQuestion(), flashcard.getCardAnswer(),
				flashcard.getBoxCounter(), flashcard.getStackId(), flashcard.getCardPicture());
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
				String cardAnswer = results.getString("Card_Answer");
				int boxCounter = results.getInt("Card_Box_Counter");
				int stackId = results.getInt("Card_Stack_ID");
				// int owner = results.getInt("Card_Owner");
				Date cardLastAccessDate = results.getDate("Card_LastAccessDate");
				Date cardNextAccessDate = results.getDate("Card_NextAccessDate");
				String cardPicture = results.getString("Card_Picture_Link");

				liste.add(new FlashCard(cardId, cardName, cardQuestion, cardAnswer, boxCounter, stackId,
						cardLastAccessDate, cardNextAccessDate, cardPicture));
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
				String cardAnswer = results.getString("Card_Answer");
				int boxCounter = results.getInt("Card_Box_Counter");
				int stackId = results.getInt("Card_Stack_ID");
				// int owner = results.getInt("Card_Owner");
				Date cardLastAccessDate = results.getDate("Stack_LastAccessDate");
				Date cardNextAccessDate = results.getDate("Stack_NextAccessDate");
				String cardPicture = results.getString("Card_Picture_Link");

				liste.add(new FlashCard(cardId, cardName, cardQuestion, cardAnswer, boxCounter, stackId,
						cardLastAccessDate, cardNextAccessDate, cardPicture));
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
			String cardAnswer = results.getString("Card_Answer");
			int boxCounter = results.getInt("Card_Box_Counter");
			int stackId = results.getInt("Card_Stack_ID");
			// int owner = results.getInt("Card_Owner");
			Date cardLastAccessDate = results.getDate("Stack_LastAccessDate");
			Date cardNextAccessDate = results.getDate("Stack_NextAccessDate");
			String cardPicture = results.getString("Card_Picture_Link");

			flashcard = new FlashCard(cardId, cardName, cardQuestion, cardAnswer, boxCounter, stackId,
					cardLastAccessDate, cardNextAccessDate, cardPicture);

		} catch (SQLException ex) {
			System.err.println("Fehler beim laden des Datensatzes!");
		}
		return flashcard;
	}

}