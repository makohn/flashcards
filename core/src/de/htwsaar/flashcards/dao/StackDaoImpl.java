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

import de.htwsaar.flashcards.dao.interfaces.StackDao;
import de.htwsaar.flashcards.model.Stack;

/**
 * Die StackDaoImpl Klasse verwaltet und verarbeitet Datenbankzugriffe Hierfuer
 * wurde das Spring Framework verwendet (Vermeidung SQLInjections usw.)
 * 
 * @author Martin Feick
 * 
 */

@Component("StackDaoImpl")
public class StackDaoImpl implements StackDao {

	private NamedParameterJdbcTemplate jdbc;

	@Autowired
	public StackDaoImpl(DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}

	public StackDaoImpl() {
		this(SQLiteJDBC.getConnection());
	}

	/**
	 * Methode zum loeschen eines Stacks
	 * 
	 * @param stack
	 *            - object
	 */
	@Override
	public void deleteStack(Stack stack) {
		int stackId = stack.getStackId();
		String delete = "Delete FROM Stacks WHERE Stack_Id = :Stack_Id";

		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("Stack_Id", stackId);

		jdbc.update(delete, paramSource);
	}

	/**
	 * Methode zum Speichern eines neuen Stacks
	 * 
	 * @param stack
	 *            - object
	 */
	@Override
	public void saveStack(Stack stack) {

		String query = "INSERT INTO Stacks (Stack_Name, Stack_Typ, Stack_Subject, Stack_CreationDate, Stack_LastEditDate) "
				+ "VALUES (:Stack_Name, :Stack_Typ, :Stack_Subject, :Stack_CreationDate, :Stack_LastEditDate)";

		MapSqlParameterSource paramSource = getStackParameterSource(stack, 0);

		jdbc.update(query, paramSource);
	}

	/**
	 * Methode zum Aktualisieren eines Stacks
	 * 
	 * @param stack
	 *            - object
	 */
	@Override
	public void updateStack(Stack stack) {

		String query = "INSERT OR REPLACE INTO Stacks (Stack_Id, Stack_Name, Stack_Typ, Stack_Subject, Stack_CreationDate, Stack_LastEditDate) "
				+ "VALUES (:Stack_Id, :Stack_Name, :Stack_Typ, :Stack_Subject, :Stack_CreationDate, :Stack_LastEditDate)";

		MapSqlParameterSource paramSource = getStackParameterSource(stack, 1);

		jdbc.update(query, paramSource);
	}
	
	/**
	 * Methode zum Hinzufuegen eines Stacks
	 * 
	 * @param stack
	 *            - object
	 */
	@Override
	public void addStack(Stack stack) {

		String query = "INSERT OR IGNORE INTO Stacks (Stack_Id, Stack_Name, Stack_Typ, Stack_Subject, Stack_CreationDate, Stack_LastEditDate) "
				+ "VALUES (:Stack_Id, :Stack_Name, :Stack_Typ, :Stack_Subject, :Stack_CreationDate, :Stack_LastEditDate)";

		MapSqlParameterSource paramSource = getStackParameterSource(stack, 1);

		jdbc.update(query, paramSource);
	}

	/**
	 * Hilfsmethode zum Speichern eines neuen Stacks
	 * 
	 * @param stack
	 *            - object
	 * @param check
	 *            - integer zum zuordnen der aufrufenden Funktion
	 */
	private MapSqlParameterSource getStackParameterSource(Stack stack, int check) {

		MapSqlParameterSource paramSource = new MapSqlParameterSource();

		paramSource.addValue("Stack_Name", stack.getStackName());
		paramSource.addValue("Stack_Typ", stack.getTyp());
		paramSource.addValue("Stack_Subject", stack.getSubject());
		paramSource.addValue("Stack_CreationDate", stack.getCreationDate());
		paramSource.addValue("Stack_LastEditDate", stack.getLastEditDate());

		if (check == 1)
			paramSource.addValue("Stack_Id", stack.getStackId());

		return paramSource;
	}

	/**
	 * Methode, die alle Stacks ausgibt
	 * 
	 * @return Liste mit allen Stacks die zur Zeit in der DB vorhanden sind
	 */
	@Override
	public List<Stack> getStacks() {

		String query = "SELECT * FROM Stacks;";

		return jdbc.query(query, new StackRowMapper());
	}

	/**
	 * Methode, die den Stack zu einer bestimmten ID ausgibt
	 * 
	 * @param stackId
	 * @return stack - object welches gesucht wurde
	 */
	@Override
	public Stack getStack(int stackId) {

		String query = "SELECT * FROM Stacks WHERE Stack_Id = :Stack_Id";

		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("Stack_Id", stackId);

		try {
			return (Stack) jdbc.queryForObject(query, paramSource, new StackRowMapper());
		} catch (EmptyResultDataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Diese Klasse erstellt Stack-Objekte aus einem Resultset welches das
	 * Ergebnis einer Datenbankanfrage war
	 * 
	 * @return stack - object
	 */
	private class StackRowMapper implements RowMapper<Stack> {

		@Override
		public Stack mapRow(ResultSet results, int rowNum) throws SQLException {

			Stack stack = new Stack();

			try {
				stack.setStackId(results.getInt("Stack_Id"));
				stack.setStackName(results.getString("Stack_Name"));
				stack.setTyp(results.getInt("Stack_Typ"));
				stack.setSubject(results.getString("Stack_Subject"));
				stack.setCreationDate(results.getTimestamp("Stack_CreationDate"));
				stack.setLastEditDate(results.getTimestamp("Stack_LastEditDate"));
			} catch (Exception e) {
				e.printStackTrace();
			}
			return stack;
		}
	}
}