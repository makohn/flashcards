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

import de.htwsaar.flashcards.dao.interfaces.GameOptionDao;
import de.htwsaar.flashcards.model.GameOption;

/**
 * Die GameOptionDaoImpl Klasse verwaltet und verarbeitet Datenbankzugriffe
 * Hierfuer wurde das Spring Framework verwendet (Vermeidung SQLInjections usw.)
 * 
 * @author Martin Feick
 * 
 */
@Component("GameOptionDaoImpl")
public class GameOptionDaoImpl implements GameOptionDao {
	
	private NamedParameterJdbcTemplate jdbc;

	@Autowired
	public GameOptionDaoImpl(DataSource jdbc) {
			this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}

	public GameOptionDaoImpl() {
			this.jdbc = new NamedParameterJdbcTemplate(SQLiteJDBC.getConnection());
		}

	/**
	 * Loescht eine gespeicherte Spieloption.
	 * 
	 * @param options - Die Option die geloescht werden soll.
	 */
	@Override
	public void deleteGameOption(GameOption options) {
		int optionId = options.getId();
		String delete = "Delete FROM Cards WHERE GameOption_Id = :GameOption_Id";

		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("GameOption_Id", optionId);

		jdbc.update(delete, paramSource);
	}

	/**
	 * Speichert eine  Spieloption.
	 * 
	 * @param options - Die Option die gespeichert werden soll.
	 */
	@Override
	public void saveGameOption(GameOption options) {
		String insert = "INSERT INTO GameOptions (GameOption_Name, GameOption_Desc, GameOption_Time, GameOption_Box, GameOption_Sorted, GameOptions_Limit, GameOptions_DateSensitive, GameOptions_EvalType )"
				+ "VALUES (:GameOption_Name, :GameOption_Desc, :GameOption_Time, :GameOption_Box, :GameOption_Sorted, :GameOptions_Limit, :GameOptions_DateSensitive, :GameOptions_EvalType)";

		MapSqlParameterSource paramSource = getGameOptionsParameterSource(options);

		jdbc.update(insert, paramSource);
	}

	/**
	 * Hilfsmethode zum Speichern einer Karte. Fuegt die Attribute einer
	 * Parameterquelle hinzu.
	 * 
	 * @param options - Das Objekt, dessen Attribute als Grundlage dienen.
	 */
	private MapSqlParameterSource getGameOptionsParameterSource(GameOption options) {

		MapSqlParameterSource paramSource = new MapSqlParameterSource();

		paramSource.addValue("GameOption_Id", options.getId());
		paramSource.addValue("GameOption_Name", options.getName());
		paramSource.addValue("GameOption_Desc", options.getDescription());
		paramSource.addValue("GameOption_Time", options.getTime());
		paramSource.addValue("GameOption_Box", options.getBoxOption());
		paramSource.addValue("GameOption_Sorted", options.isSorted());
		paramSource.addValue("GameOption_Limit", options.getLimit());
		paramSource.addValue("GameOption_DateSensitive", options.isDateSensitive());
		paramSource.addValue("GameOption_EvalType", options.getEvalType());

		return paramSource;
	}

	/**
	 * Aktualisiert eine gespeicherte Spieloption.
	 * 
	 * @param options - Die Option die aktualisiert werden soll.
	 *            
	 */
	@Override
	public void updateGameOption(GameOption options) {

		String query = "UPDATE GameOptions SET GameOption_Name = :GameOption_Name, GameOption_Desc = :GameOption_Desc, GameOption_Time = :GameOption_Time, "
				+ "GameOption_Box = :GameOption_Box, GameOption_Sorted = :GameOption_Sorted, GameOption_Limit = :GameOption_Limit, "
				+ "GameOption_DateSensitive = :GameOption_DateSensitive, GameOption_EvalType = :GameOption_EvalType WHERE GameOption_Id = :GameOption_Id";

		MapSqlParameterSource paramSource = getGameOptionsParameterSource(options);

		jdbc.update(query, paramSource);
	}

	

	/**
	 * Gibt alle, in der Datenbank gespeicherten Spieloptionen zurueck
	 * 
	 * @return Liste mit allen Optionen die zur Zeit in der DB vorhanden sind
	 */
	@Override
	public List<GameOption> getGameOptions() {

		String query = "SELECT * FROM GameOptions;";

		return jdbc.query(query, new GameOptionRowMapper());
	}


	/**
	 * Gibt eine Option mit einer bestimmten ID zurueck.
	 * 
	 * @param id - Die eindeutige ID fuer eine Spieloption
	 */
	@Override
	public GameOption getGameOption(int id) {

		String query = "SELECT * FROM Cards WHERE GameOption_Id = :GameOption_Id";

		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("GameOption_Id", id);

		try {
			return (GameOption) jdbc.queryForObject(query, paramSource, new GameOptionRowMapper());
		} catch (EmptyResultDataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Diese Klasse erstellt GameOption-Objekte aus einem Resultset welches das
	 * Ergebnis einer Datenbankanfrage war
	 * 
	 */
	private class GameOptionRowMapper implements RowMapper<GameOption> {

		@Override
		public GameOption mapRow(ResultSet results, int rowNum) throws SQLException {

			GameOption options = new GameOption();

			try {
				options.setId(results.getInt("GameOption_Id"));
				options.setName(results.getString("GameOption_Name"));
				options.setDescription(results.getString("GameOption_Desc"));
				options.setTime(results.getInt("GameOption_Time"));
				options.setBoxOption(results.getInt("GameOption_Box"));
				options.setSorted(results.getBoolean("GameOption_Sorted"));
				options.setLimit(results.getInt("GameOption_Limit"));
				options.setDateSensitive(results.getBoolean("GameOption_DateSensitive"));
				options.setEvalType(results.getInt("GameOption_EvalType"));
			} catch (Exception e) {
				e.printStackTrace();
			}
			return options;
		}
	}
}

