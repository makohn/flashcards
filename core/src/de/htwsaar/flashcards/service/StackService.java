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
	
	public String[] getStackNames() {
		String[] stackNames = new String[stacks.size()];
		for(int i = 0; i < stackNames.length; i++) {
			stackNames[i] = stacks.get(i).getStackName();
		}
		return stackNames;
	}
}
