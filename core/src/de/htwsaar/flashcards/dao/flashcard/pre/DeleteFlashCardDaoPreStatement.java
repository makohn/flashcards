package de.htwsaar.flashcards.dao.flashcard.pre;
//Feick Martin

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.jdbc.core.PreparedStatementCreator;

/**
 * Creates a PreparedStatement to query the cardId of a certain card.
 * 
 */

public class DeleteFlashCardDaoPreStatement implements PreparedStatementCreator {

	private String query;
	private int cardId;

	public DeleteFlashCardDaoPreStatement(int cardId) {
		this.query = String.format("Delete FROM Cards WHERE Card_Id = ?");
		this.cardId = cardId;
	}

	@Override
	public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {

		PreparedStatement ps = connection.prepareStatement(query);
		ps.setInt(1, cardId);
		return ps;

	}
}
