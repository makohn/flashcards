package de.htwsaar.flashcards.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import de.htwsaar.flashcards.dao.interfaces.StackDao;

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

}
