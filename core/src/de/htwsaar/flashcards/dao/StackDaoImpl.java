package de.htwsaar.flashcards.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import de.htwsaar.flashcards.dao.interfaces.StackDao;
import de.htwsaar.flashcards.model.FlashCard;
import de.htwsaar.flashcards.model.Stack;

public class StackDaoImpl implements StackDao {

	private String sqlBefehl = new String("");
	private Connection con;
	private Statement dbBefehl;
	private ResultSet rsDatenmenge;

	public StackDaoImpl() throws ClassNotFoundException {
		try {
			con = SQLiteJDBC.getConnection();
			dbBefehl = con.createStatement();
		} catch (SQLException ex) {
			System.err.println("Fehler beim erstellen des Statements");
		}
	}

	@Override
	public void deleteStack(Stack stack) {
		int stackId = stack.getStackId();
		sqlBefehl = "DELETE FROM Cards WHERE Card_Id = " + stackId;
        try {
            dbBefehl.executeUpdate(sqlBefehl);
        } catch (SQLException ex) {
            System.err.println("Fehler beim loeschen des Datensatzes!");
        }	

	}

	@Override
	public void saveStack(Stack stack) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateStack(Stack stack) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Stack> getStacks() {
		
       sqlBefehl = "select * FROM Stacks";
       
       List<Stack> stacks = new ArrayList<Stack>();
        
        try {
        	rsDatenmenge = dbBefehl.executeQuery(sqlBefehl); 
        	
			int stackId = rsDatenmenge.getInt("Stack_Id");
			String stackName = rsDatenmenge.getString("Stack_Name");
			int stackTyp = rsDatenmenge.getInt("Stack_Typ");
			String stackSubject = rsDatenmenge.getString("Stack_Subject");
			Date stackCreationDate = rsDatenmenge.getDate("Stack_CreationDate");
			Date stackLastEditDate = rsDatenmenge.getDate("Stack_LastEditDate");
			Date stackLastAccessDate = rsDatenmenge.getDate("Stack_LastAccessDate");
			Date stackNextAccessDate = rsDatenmenge.getDate("Stack_NextAccessDate");
			
			stacks.add(new Stack(stackId, stackName, stackTyp, stackSubject, stackCreationDate, stackLastEditDate, stackLastAccessDate, stackNextAccessDate));
			
        } catch (SQLException ex) {
            System.err.println("Fehler beim laden des Datensatzes!");
        }	
		return stacks;
	}


	@Override
	public Stack getStack(int StackId) {
        sqlBefehl = "select * FROM Stacks WHERE Stack_Id = " + StackId;
        
       Stack stacks = null;
        try {
        	rsDatenmenge = dbBefehl.executeQuery(sqlBefehl); 
        	
			int stackId = rsDatenmenge.getInt("Stack_Id");
			String stackName = rsDatenmenge.getString("Stack_Name");
			int stackTyp = rsDatenmenge.getInt("Stack_Typ");
			String stackSubject = rsDatenmenge.getString("Stack_Subject");
			Date stackCreationDate = rsDatenmenge.getDate("Stack_CreationDate");
			Date stackLastEditDate = rsDatenmenge.getDate("Stack_LastEditDate");
			Date stackLastAccessDate = rsDatenmenge.getDate("Stack_LastAccessDate");
			Date stackNextAccessDate = rsDatenmenge.getDate("Stack_NextAccessDate");
			
			stacks = new Stack(stackId, stackName, stackTyp, stackSubject, stackCreationDate, stackLastEditDate, stackLastAccessDate, stackNextAccessDate);
			
        } catch (SQLException ex) {
            System.err.println("Fehler beim laden des Datensatzes!");
        }	
		return stacks;
	}

}
