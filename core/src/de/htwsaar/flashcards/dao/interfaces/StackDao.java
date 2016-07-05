package de.htwsaar.flashcards.dao.interfaces;

import java.util.List;

import de.htwsaar.flashcards.model.Stack;

public interface StackDao {
	
	public void deleteStack(Stack stack);
	public void saveStack(Stack stack);
	public void updateStack(Stack stack);
	public List<Stack> getStacks();
	public Stack getStack(int StackId);
	
}