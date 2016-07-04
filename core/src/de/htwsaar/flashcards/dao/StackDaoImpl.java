package de.htwsaar.flashcards.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import de.htwsaar.flashcards.dao.interfaces.StackDao;
import de.htwsaar.flashcards.model.Stack;

public class StackDaoImpl implements StackDao {
	
	private String sqlBefehl = new String("");
	private Connection con;
	private Statement dbBefehl;
	private ResultSet rsDatenmenge;
	
    public StackDaoImpl () throws ClassNotFoundException {
        try {
         con = SQLiteJDBC.getConnection();
            dbBefehl = con.createStatement();
        } catch (SQLException ex) {
            System.err.println("Fehler beim erstellen des Statements");
        }
    }

	@Override
	public void delete(Stack stack) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void save(Stack stack) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Stack stack) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Stack> getStacks() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Stack get(int StackId) {
		// TODO Auto-generated method stub
		return null;
	}

}
