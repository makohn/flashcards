package de.htwsaar.flashcards.service.interfaces;

import java.util.List;

import de.htwsaar.flashcards.model.Stack;

/**
 * <code>StackService</code> -Dienst fuer die (normale) Datenbankinteraktion bzgl. 
 * der Entitaet <code>Stack</code>.
 * @author Marek Kohn
 *
 */
public interface StackService {
	
	public List<Stack> getStacks();
	public Stack[] getStackArray();
	public Stack getStack(int stackId);
	public void save(Stack stack);
	public void deleteStack(Stack stack);
}
