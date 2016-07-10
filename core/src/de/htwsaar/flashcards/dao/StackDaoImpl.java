package de.htwsaar.flashcards.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import de.htwsaar.flashcards.dao.interfaces.StackDao;
import de.htwsaar.flashcards.model.Stack;

public class StackDaoImpl implements StackDao {

	private String sqlCommand = new String("");
	private Connection con;
	private Statement dbCommand;
	private ResultSet results;

	public StackDaoImpl() throws ClassNotFoundException {
		try {
			con = SQLiteJDBC.getConnection();
			dbCommand = con.createStatement();
		} catch (SQLException ex) {
			System.err.println("Fehler beim erstellen des Statements");
		}
	}

	@Override
	public void deleteStack(Stack stack) {
		int stackId = stack.getStackId();
		sqlCommand = "DELETE FROM Cards WHERE Card_Id = " + stackId;
		try {
			dbCommand.executeUpdate(sqlCommand);
		} catch (SQLException ex) {
			System.err.println("Fehler beim loeschen des Datensatzes!");
		}

	}

	@Override
	public void saveStack(Stack stack) {
		sqlCommand = String.format(
				"INSERT INTO Stacks "
						+ "(Stack_Name, Stack_Typ, Stack_Subject, Stack_CreationDate, Stack_LastEditDate, Stack_LastAccessDate, Stack_NextAccessDate) "
						+ "VALUES (%d, \"%s\", %d, \"%s\", %tD, %tD, %tD, %tD)",
				stack.getStackName(), stack.getTyp(), stack.getSubject(), stack.getCreationDate(),
				stack.getLastEditDate(), stack.getLastAccessDate(), stack.getNextAccessDate());
		System.out.println(sqlCommand);
		try {
			dbCommand.executeUpdate(sqlCommand);
		} catch (SQLException ex) {
			Logger.getLogger(FlashCardDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

	@Override
	public void updateStack(Stack stack) {
		sqlCommand = String.format(
				"INSERT or replace INTO Stacks "
						+ "(Stack_Id, Stack_Name, Stack_Typ, Stack_Subject, Stack_CreationDate, Stack_LastEditDate, Stack_LastAccessDate, Stack_NextAccessDate) "
						+ "VALUES (%d, \"%s\", %d, \"%s\", %tD, %tD, %tD, %tD)",
				stack.getStackId(), stack.getStackName(), stack.getTyp(), stack.getSubject(), stack.getCreationDate(),
				stack.getLastEditDate(), stack.getLastAccessDate(), stack.getNextAccessDate());
		System.out.println(sqlCommand);
		try {
			dbCommand.executeUpdate(sqlCommand);
		} catch (SQLException ex) {
			Logger.getLogger(FlashCardDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

	@Override
	public List<Stack> getStacks() {

		sqlCommand = "select * FROM Stacks";

		List<Stack> stacks = new ArrayList<Stack>();

		try {
			results = dbCommand.executeQuery(sqlCommand);

			int stackId = results.getInt("Stack_Id");
			String stackName = results.getString("Stack_Name");
			int stackTyp = results.getInt("Stack_Typ");
			String stackSubject = results.getString("Stack_Subject");
			Date stackCreationDate = results.getDate("Stack_CreationDate");
			Date stackLastEditDate = results.getDate("Stack_LastEditDate");
			Date stackLastAccessDate = results.getDate("Stack_LastAccessDate");
			Date stackNextAccessDate = results.getDate("Stack_NextAccessDate");

			stacks.add(new Stack(stackId, stackName, stackTyp, stackSubject, stackCreationDate, stackLastEditDate,
					stackLastAccessDate, stackNextAccessDate));

		} catch (SQLException ex) {
			System.err.println("Fehler beim laden des Datensatzes!");
		}
		return stacks;
	}

	@Override
	public Stack getStack(int StackId) {
		sqlCommand = "select * FROM Stacks WHERE Stack_Id = " + StackId;

		Stack stacks = null;
		try {
			results = dbCommand.executeQuery(sqlCommand);

			int stackId = results.getInt("Stack_Id");
			String stackName = results.getString("Stack_Name");
			int stackTyp = results.getInt("Stack_Typ");
			String stackSubject = results.getString("Stack_Subject");
			Date stackCreationDate = results.getDate("Stack_CreationDate");
			Date stackLastEditDate = results.getDate("Stack_LastEditDate");
			Date stackLastAccessDate = results.getDate("Stack_LastAccessDate");
			Date stackNextAccessDate = results.getDate("Stack_NextAccessDate");

			stacks = new Stack(stackId, stackName, stackTyp, stackSubject, stackCreationDate, stackLastEditDate,
					stackLastAccessDate, stackNextAccessDate);

		} catch (SQLException ex) {
			System.err.println("Fehler beim laden des Datensatzes!");
		}
		return stacks;
	}

}
