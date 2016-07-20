package de.htwsaar.flashcards.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
//Feick Martin

public class SQLiteJDBC {
	// System.getProperty("user.home") + "/" + "testdb.db";
    private static final String URL = "jdbc:sqlite:flashcardohneUser.db";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private static Connection con = null;
    
    public static Connection getConnection() throws ClassNotFoundException  {
        // load the sqlite-JDBC driver using the current class loader
		Class.forName("org.sqlite.JDBC");
    	
        if(con == null) {
            try {            	            	
                con = DriverManager.getConnection(URL, USER, PASSWORD);
                return con;               
            } catch (SQLException ex) {
                System.err.println("Fehler beim Verbinden zur Datenbank! Error: " + ex.toString());
            }
        } else {
            return con;          
        }
        return null;
    }
}