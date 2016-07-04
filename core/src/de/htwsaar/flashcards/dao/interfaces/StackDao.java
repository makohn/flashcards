package de.htwsaar.flashcards.dao.interfaces;

import java.util.List;

import de.htwsaar.flashcards.model.Stack;

public interface StackDao {
	
	public void delete(Stack stack);
	public void save(Stack stack);
	public void update(Stack stack);
	public List<Stack> getStacks();
	public Stack get(int StackId);
	

}
