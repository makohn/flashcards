package de.htwsaar.flashcards.builder;

import de.htwsaar.flashcards.dao.FlashCardDaoImpl;
import de.htwsaar.flashcards.dao.GameOptionDaoImpl;
import de.htwsaar.flashcards.dao.StackDaoImpl;
import de.htwsaar.flashcards.dao.interfaces.FlashCardDao;
import de.htwsaar.flashcards.dao.interfaces.GameOptionDao;
import de.htwsaar.flashcards.dao.interfaces.StackDao;

public class DaoObjectBuilder {
	/*
	 * FlashCardDao
	 */
	public static FlashCardDao getFlashCardDao() {
		return new FlashCardDaoImpl();
	}
	
	/*
	 * StackDao
	 */
	public static StackDao getStackDao() {
		return new StackDaoImpl();
	}
	
	/*
	 * GameOptionDao
	 */
	public static GameOptionDao getGameOptionDao() {
		return new GameOptionDaoImpl();
	}
}
