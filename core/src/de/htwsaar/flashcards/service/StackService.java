package de.htwsaar.flashcards.service;

import java.util.List;

import de.htwsaar.flashcards.dao.StackDaoImpl;
import de.htwsaar.flashcards.dao.interfaces.StackDao;
import de.htwsaar.flashcards.model.Stack;

public class StackService {
	
	private StackDao stackDao;
	
	public StackService() {
		stackDao = new StackDaoImpl();
	}
	
	public List<Stack> getStacks() {
		return stackDao.getStacks();
	}
	
	public Stack[] getStackArray() {
		List<Stack> stacks = stackDao.getStacks();
		return stacks.toArray(new Stack[stacks.size()]);
	}
	
	public Stack getStack(int stackId) {
		return stackDao.getStack(stackId);
	}
	
	public void save(Stack stack) {
		stackDao.saveStack(stack);
	}
	
	public void deleteStack(Stack stack) {
		stackDao.deleteStack(stack);
	}
}