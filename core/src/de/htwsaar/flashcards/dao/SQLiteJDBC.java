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

	public static DataSource getConnection() {

		try {
			DriverManagerDataSource dataSource = new DriverManagerDataSource();
			dataSource.setDriverClassName(DRIVER);
			dataSource.setUrl(URL);
			dataSource.setUsername(USER);
			dataSource.setPassword(PASSWORD);

			return dataSource;
		} catch (Exception e) {
			System.err.println("Es konnte keine Verbindung zur Datenbank hergestellt werden");
			e.printStackTrace();
		}
		return null;
	}
}