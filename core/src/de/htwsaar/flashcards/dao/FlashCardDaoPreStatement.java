package de.htwsaar.flashcards.dao;

import java.sql.Connection; 
import java.sql.PreparedStatement; 
import java.sql.SQLException; 
 
import org.springframework.jdbc.core.PreparedStatementCreator; 


public class FlashCardDaoPreStatement implements PreparedStatementCreator {

	@Override
	public PreparedStatement createPreparedStatement(Connection connnection) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	
}