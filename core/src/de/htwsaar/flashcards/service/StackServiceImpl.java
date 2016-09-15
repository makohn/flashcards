package de.htwsaar.flashcards.service;

import java.util.List;

import de.htwsaar.flashcards.dao.StackDaoImpl;
import de.htwsaar.flashcards.dao.interfaces.StackDao;
import de.htwsaar.flashcards.model.Stack;
import de.htwsaar.flashcards.service.interfaces.StackService;
/**
 * * <code>StackServiceImpl</code> - Kapselt den Zugriff auf DAO Methoden.
 * @author Marek Kohn
 *
 */
public class StackServiceImpl implements StackService {
	
	private StackDao stackDao;
	
	public StackServiceImpl() {
		stackDao = new StackDaoImpl();
	}
	
	/**
	 * Gibt eine Liste aller Stacks zurueck, die sich in der
	 * Datenbank befinden.
	 */
	@Override
	public List<Stack> getStacks() {
		return stackDao.getStacks();
	}
	
	/**
	 * Gibt ein Array aller Stacks zurueck, die sich in der 
	 * Datenbank befinden.
	 */
	@Override
	public Stack[] getStackArray() {
		List<Stack> stacks = stackDao.getStacks();
		return stacks.toArray(new Stack[stacks.size()]);
	}
	
	/**
	 * Gibt einen bestimmten Stack zurueck.
	 * @param stackId - der Stack der zurueckgegeben werden soll.
	 * @return
	 */
	@Override
	public Stack getStack(int stackId) {
		return stackDao.getStack(stackId);
	}
	
	/**
	 * Speichert einen Stack in der Datenbank.
	 * @param stack - der zu speichernde Stack
	 */
	@Override
	public void save(Stack stack) {
		stackDao.saveStack(stack);
	}
	
	/**
	 * Aktualisiert einen Stack in der Datenbank.
	 * @param stack - der zu aktualisierende Stack
	 */
	@Override
	public void update(Stack stack) {
		stackDao.updateStack(stack);
	}
	
	/**
	 * Loescht einen Stack aus der Datenbank.
	 * @param stack - der zu loeschende Stack.
	 */
	@Override
	public void deleteStack(Stack stack) {
		stackDao.deleteStack(stack);
	}
}