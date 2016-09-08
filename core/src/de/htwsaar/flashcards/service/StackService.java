package de.htwsaar.flashcards.service;

import java.util.List;

import de.htwsaar.flashcards.dao.StackDaoImpl;
import de.htwsaar.flashcards.dao.interfaces.StackDao;
import de.htwsaar.flashcards.model.Stack;

public class StackService {
	
	private StackDao stackDao;
	private List<Stack> stacks;
	
	public StackService() {
		stackDao = new StackDaoImpl();
		stacks = stackDao.getStacks();
	}
	
	public Stack[] getStackArray() {
		return stacks.toArray(new Stack[stacks.size()]);
	}
	
	public Stack getStack(int stackId) {
		return stackDao.getStack(stackId);
	}
}