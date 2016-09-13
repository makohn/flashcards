package de.htwsaar.flashcards.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import de.htwsaar.flashcards.dao.interfaces.FlashCardDao;
import de.htwsaar.flashcards.model.FlashCard;
import de.htwsaar.flashcards.model.Stack;
import de.htwsaar.flashcards.util.FlashCardConstants;

/**
 * Die FlashCardDaoImpl Klasse verwaltet und verarbeitet Datenbankzugriffe
 * Hierfuer wurde das Spring Framework verwendet (Vermeidung SQLInjections usw.)
 * 
 * @author Martin Feick
 * 
 */

@Component("FlashCardDaoImpl")
public class FlashCardDaoImpl implements FlashCardDao {

	private NamedParameterJdbcTemplate jdbc;

	@Autowired
	public FlashCardDaoImpl(DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}

	public FlashCardDaoImpl() {
		this.jdbc = new NamedParameterJdbcTemplate(SQLiteJDBC.getConnection());
	}

	/**
	 * Methode zum Loeschen einer Karte
	 * 
	 * @param flashcard
	 *            - object
	 */
	@Override
	public void deleteCard(FlashCard flashcard) {
		int cardId = flashcard.getCardId();
		String delete = "Delete FROM Cards WHERE Card_Id = :Card_Id";

		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("Card_Id", cardId);

		jdbc.update(delete, paramSource);
	}

	/**
	 * Methode zum Speichern einer Karte
	 * 
	 * @param flashcard
	 *            - object
	 */
	@Override
	public void saveCard(FlashCard flashcard) {
		String insert = "INSERT INTO Cards (Card_Name, Card_Question, Card_Answer, Card_Stack_Id, Card_Picture_Link, Card_Asked, Card_AnswerCorrect)"
				+ "VALUES (:Card_Name, :Card_Question, :Card_Answer, :Card_Stack_Id, :Card_Picture_Link, :Card_Asked, :Card_AnswerCorrect)";

		MapSqlParameterSource paramSource = getFlashCardParameterSource(flashcard);
		
		jdbc.update(insert, paramSource);
	}

	/**
	 * Hilfsmethode zum Speichern einer Karte
	 * 
	 * @param flashcard
	 *            - object
	 */
	private MapSqlParameterSource getFlashCardParameterSource(FlashCard flashcard) {

		MapSqlParameterSource paramSource = new MapSqlParameterSource();

		paramSource.addValue("Card_Id", flashcard.getCardId());
		paramSource.addValue("Card_Name", flashcard.getCardName());
		paramSource.addValue("Card_Question", flashcard.getCardQuestion());
		paramSource.addValue("Card_Answer", flashcard.getCardAnswer());
		paramSource.addValue("Card_Box_Counter", flashcard.getBoxCounter());
		paramSource.addValue("Card_Stack_Id", flashcard.getStack());
		paramSource.addValue("Card_LastAccessDate", flashcard.getCardLastAccessDate());
		paramSource.addValue("Card_NextAccessDate", flashcard.getCardNextAccessDate());
		paramSource.addValue("Card_Picture_Link", flashcard.getCardPicture());
		paramSource.addValue("Card_Asked", flashcard.getCardAsked());
		paramSource.addValue("Card_AnswerCorrect", flashcard.getCardAnswerCorrect());

		return paramSource;
	}

	/**
	 * Methode zum Aktualisieren einer Karte
	 * 
	 * @param flashcard
	 *            - object
	 */
	@Override
	public void updateCard(FlashCard flashcard) {

		String query = "UPDATE Cards SET Card_Name = :Card_Name, Card_Question = :Card_Question, Card_Answer = :Card_Answer, "
				+ "Card_Picture_Link = :Card_Picture_Link, Card_Box_Counter = :Card_Box_Counter, Card_Asked = :Card_Asked, "
				+ "Card_AnswerCorrect = :Card_AnswerCorrect WHERE Card_Id = :Card_Id";

		MapSqlParameterSource paramSource = getFlashCardParameterSource(flashcard);
	
		jdbc.update(query, paramSource);
	}
	
	/**
	 * Methode zum Zuruecksetzen des BoxCounters
	 * 
	 * @param flashcard
	 *            - object
	 */
	@Override
	public void resetBoxCounter(Stack stack) {

		int box = FlashCardConstants.START_PHASE;
		String query = "UPDATE Cards SET Card_Box_Counter = :Card_Box_Counter WHERE Card_Stack_Id = :Card_Stack_Id";

		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("Card_Box_Counter", box);
		paramSource.addValue("Card_Stack_Id", stack.getStackId());

		jdbc.update(query, paramSource);
	}
	
	/**
	 * Methode zum Abfragen, wie oft eine Karte abgefragt wurde.
	 * 
	 * @param flashcard
	 *            - object
	 */
	@Override
	public int getAskedCount(Stack stack) {
		String query = "SELECT SUM(Card_Asked) FROM Cards WHERE Card_Stack_Id = :Card_Stack_Id";
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("Card_Stack_Id", stack.getStackId());

		return jdbc.queryForObject(query, paramSource, Integer.class);
	}

	/**
	 * Methode zum Abfragen, wie oft eine Karte richtig beantwortet wurde.
	 * 
	 * @param flashcard
	 *            - object
	 */
	@Override
	public int getAnswerCorrectCount(Stack stack) {
		String query = "SELECT SUM(Card_AnswerCorrect) FROM Cards WHERE Card_Stack_Id = :Card_Stack_Id";
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("Card_Stack_Id", stack.getStackId());

		return jdbc.queryForObject(query, paramSource, Integer.class);
	}
	
	/**
	 * Methode um die Anzahl der Karten eines Stacks je Box abzufragen
	 * 
	 * @param flashcard
	 *            - object
	 */
	@Override
	public int getNrCardsInBox(Stack stack, int box) {
		String query = "SELECT COUNT(*) FROM Cards WHERE Card_Stack_Id = :Card_Stack_Id "
				+ "AND Card_Box_Counter = :Card_Box_Counter";
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("Card_Stack_Id", stack.getStackId());
		paramSource.addValue("Card_Box_Counter", box);

		return jdbc.queryForObject(query, paramSource, Integer.class);
	}

	/**
	 * Gibt alle Karten zurueck.
	 * 
	 * @return Liste mit allen Karten die zur Zeit in der DB vorhanden sind
	 */
	@Override
	public List<FlashCard> getFlashCards() {

		String query = "SELECT * FROM Cards;";

		return jdbc.query(query, new FlashCardRowMapper());
	}
	
	/**
	 * Gibt alle Karten eines Stacks zurueck.
	 * 
	 * @return Liste mit allen Karten eines Stacks
	 */
	@Override
	public List<FlashCard> getFlashCards(int stackId) {
		String query = "SELECT * FROM Cards WHERE Card_Stack_Id = :Card_Stack_Id;";

		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("Card_Stack_Id", stackId);
		
		return jdbc.query(query, paramSource, new FlashCardRowMapper());
	}

	/**
	 * Methode die den Karten aus einer bestimmen Box zurueck gibt
	 * 
	 * @param box
	 *            - angabe der Box in der sich die Karte befindet
	 * @return flashcard - object
	 */
	@Override
	public List<FlashCard> getFlashCards(int stackId, int box) {

		String query = "SELECT * FROM Cards WHERE Card_Stack_Id = :Card_Stack_Id AND Card_Box_Counter = :Card_Box_Counter";

		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("Card_Stack_Id", stackId);
		paramSource.addValue("Card_Box_Counter", box);

		return jdbc.query(query, paramSource, new FlashCardRowMapper());

	}

	/**
	 * Methode, die die Karte zu einer bestimmten ID zurueck gibt
	 * 
	 * @param cardId
	 * @return flashcard - object
	 */
	@Override
	public FlashCard getCard(int cardId) {

		String query = "SELECT * FROM Cards WHERE Card_Id = :Card_Id";

		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("Card_Id", cardId);

		try {
			return (FlashCard) jdbc.queryForObject(query, paramSource, new FlashCardRowMapper());
		} catch (EmptyResultDataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Diese Klasse erstellt Card-Objekte aus einem Resultset welches das
	 * Ergebnis einer Datenbankanfrage war
	 * 
	 * @return flashcard - object
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
				flashcard.setStack(results.getInt("Card_Stack_Id"));
				flashcard.setCardLastAccessDate(results.getTimestamp("Card_LastAccessDate"));
				flashcard.setCardNextAccessDate(results.getTimestamp("Card_NextAccessDate"));
				flashcard.setCardPicture(results.getString("Card_Picture_Link"));
				flashcard.setCardAsked(results.getInt("Card_Asked"));
				flashcard.setCardAnswerCorrect(results.getInt("Card_AnswerCorrect"));
			} catch (Exception e) {
				e.printStackTrace();
			}
			return flashcard;
		}
	}
}