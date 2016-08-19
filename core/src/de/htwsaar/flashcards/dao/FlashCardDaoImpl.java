package de.htwsaar.flashcards.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;

import de.htwsaar.flashcards.dao.interfaces.FlashCardDao;
import de.htwsaar.flashcards.model.FlashCard;

/**
 * The FlashCardDao Class encapsulates the database access
 * It provides methods for loading and storing flashcard objects.
 * 
 * @author Feick Martin
 * 
 */

@Component("FlashCardDaoImpl")
public class FlashCardDaoImpl implements FlashCardDao {

	private JdbcTemplate jdbc;

	@Autowired
	public FlashCardDaoImpl(DataSource jdbc) {
		this.jdbc = new JdbcTemplate(jdbc);
	}

	public FlashCardDaoImpl() {}

	@Override
	public void deleteCard(FlashCard flashcard) {
		int cardId = flashcard.getCardId();
		String delete = "Delete FROM Cards WHERE Card_Id = :Card_Id";

		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("Card_Id", cardId);

		jdbc.update(delete, paramSource);
	}

	@Override
	public void saveCard(FlashCard flashcard) {
		String insert = "INSERT INTO Cards (Card_Name, Card_Question, Card_Answer, Card_Stack_Id, Card_Picture_Link)"
						+ "VALUES (:Card_Name, :Card_Question, :Card_Answer, :Card_Stack_Id, :Card_Picture_Link)";
												
			MapSqlParameterSource paramSource = getFlashCardParameterSource(flashcard);
				
			jdbc.update(insert, paramSource);
	}

	private MapSqlParameterSource getFlashCardParameterSource(FlashCard flashcard) {

		MapSqlParameterSource paramSource = new MapSqlParameterSource();

		paramSource.addValue("Card_Id", flashcard.getCardId());
		paramSource.addValue("Card_Name", flashcard.getCardName());
		paramSource.addValue("Card_Question", flashcard.getCardQuestion());
		paramSource.addValue("Card_Answer", flashcard.getCardAnswer());
		paramSource.addValue("Card_Box_Counter", flashcard.getBoxCounter());
		paramSource.addValue("Card_Stack_ID", flashcard.getStackId());
		paramSource.addValue("Stack_LastAccessDate", flashcard.getCardLastAccessDate());
		paramSource.addValue("Stack_NextAccessDate", flashcard.getCardNextAccessDate());
		paramSource.addValue("Card_Picture_Link", flashcard.getCardPicture());

		return paramSource;
	}

	@Override
	public void updateCard(FlashCard flashcard) {
		
		int box = flashcard.getBoxCounter();
		int id = flashcard.getCardId();
		String query = "UPDATE Cards SET Card_Box_Counter = :Card_Box_Counter WHERE Card_Id = :Card_Id";
				
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("Card_Box_Counter", box);
		paramSource.addValue("Card_Id", id);
		
		jdbc.update(query, paramSource);
	}

	@Override
	public List<FlashCard> getFlashCards() {

		String query = "SELECT * FROM Cards;";

		return jdbc.query(query, new FlashCardRowMapper());
	}

	@Override
	public List<FlashCard> getFlashCards(int box) {
		
		String query = "SELECT * FROM Cards WHERE Card_Box_Counter = :Card_Box_Counter";

		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("Card_Box_Counter", box);

		return jdbc.query(query, paramSource, new FlashCardRowMapper()); 
	
	}

	@Override
	public FlashCard getCard(int cardId) {
		
		String query = "SELECT * FROM Cards WHERE Card_Id = :Card_Id";

		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("Card_Id", cardId);

		try {
			return (FlashCard) jdbc.queryForMap(query, paramSource, new FlashCardRowMapper());
		} 
		catch (EmptyResultDataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * This class serves as a utility to create Author Objects out of a
	 * ResultSet that is received from a database query.
	 */
	private class FlashCardRowMapper implements RowMapper<FlashCard> {

		@Override
		public FlashCard mapRow(ResultSet results, int rowNum) throws SQLException {

			FlashCard flashcard = new FlashCard();

			try {
				flashcard.setCardId(results.getInt("Card_Id"));
				flashcard.setCardName(results.getString("Card_Name"));
				flashcard.setCardQuestion(results.getString("Card_Question"));
				flashcard.setCardAnswer(results.getString("Card_Answer"));
				flashcard.setBoxCounter(results.getInt("Card_Box_Counter"));
				flashcard.setStackId(results.getInt("Card_Stack_ID"));
				flashcard.setCardLastAccessDate(results.getDate("Stack_LastAccessDate"));
				flashcard.setCardNextAccessDate(results.getDate("Stack_NextAccessDate"));
				flashcard.setCardPicture(results.getString("Card_Picture_Link"));
			} catch (Exception e) {
				e.printStackTrace();
			}
			return flashcard;
		}
	}
}