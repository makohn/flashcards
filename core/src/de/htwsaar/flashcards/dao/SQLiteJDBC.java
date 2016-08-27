package de.htwsaar.flashcards.dao;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * Klasse zum herstellen der Verbindung zur Datenbank durch Unterst�tzung des
 * Springframeworks
 * 
 * @author Feick Martin
 * 
 */

public class SQLiteJDBC {
	// System.getProperty("user.home") + "/" + "testdb.db";
	private static final String DRIVER = "org.sqlite.JDBC";
	private static final String URL = "jdbc:sqlite:flashcardohneUser.db";
	private static final String USER = "root";
	private static final String PASSWORD = "";
	
	// Singleton
	private static DriverManagerDataSource dataSource;
	
	/**
	 * Initialisiert eine JDBC Connection
	 */
	private static void initDataSource() {
		try {
			dataSource = new DriverManagerDataSource();
			dataSource.setDriverClassName(DRIVER);
			dataSource.setUrl(URL);
			dataSource.setUsername(USER);
			dataSource.setPassword(PASSWORD);
		} catch (Exception e) {
			System.err.println("Es konnte keine Verbindung zur Datenbank hergestellt werden");
			e.printStackTrace();
		}
	}
	
	/**
	 * Zugriffsmethode
	 * @return
	 */
	public static synchronized DataSource getConnection() {
		if (dataSource == null)
			initDataSource();

		return dataSource;
	}
}