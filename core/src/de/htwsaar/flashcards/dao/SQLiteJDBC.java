package de.htwsaar.flashcards.dao;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.sqlite.SQLiteConfig;
import org.sqlite.SQLiteConfig.Pragma;

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
			// Damit das Datum von SQLite <-> Java richtig uebersetzt wird:
			SQLiteConfig sqLiteConfig = new SQLiteConfig();
			Properties properties = sqLiteConfig.toProperties();
			properties.setProperty(Pragma.DATE_STRING_FORMAT.pragmaName, "yyyy-MM-dd HH:mm:ss");
			
			dataSource = new DriverManagerDataSource();
			dataSource.setDriverClassName(DRIVER);
			dataSource.setUrl(URL);
			dataSource.setUsername(USER);
			dataSource.setPassword(PASSWORD);
			dataSource.setConnectionProperties(properties);
		} catch (Exception e) {
			System.err.println("Es konnte keine Verbindung zur Datenbank hergestellt werden");
			e.printStackTrace();
		}
	}
	
	/**
	 * Zugriffsmethode, gibt eine Instanz dieser Klasse in Form eines Singletons zurueck.
	 * @return
	 */
	public static synchronized DataSource getConnection() {
		if (dataSource == null)
			initDataSource();

		return dataSource;
	}
}