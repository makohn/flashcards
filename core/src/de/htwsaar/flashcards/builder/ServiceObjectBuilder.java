package de.htwsaar.flashcards.builder;

import java.util.ListIterator;

import de.htwsaar.flashcards.model.FlashCard;
import de.htwsaar.flashcards.model.GameOption;
import de.htwsaar.flashcards.model.Stack;
import de.htwsaar.flashcards.service.EditFlashCardServiceImpl;
import de.htwsaar.flashcards.service.FlashCardServiceImpl;
import de.htwsaar.flashcards.service.GameOptionServiceImpl;
import de.htwsaar.flashcards.service.StackServiceImpl;
import de.htwsaar.flashcards.service.StudyServiceImpl;
import de.htwsaar.flashcards.service.interfaces.EditFlashCardService;
import de.htwsaar.flashcards.service.interfaces.FlashCardService;
import de.htwsaar.flashcards.service.interfaces.GameOptionService;
import de.htwsaar.flashcards.service.interfaces.StackService;
import de.htwsaar.flashcards.service.interfaces.StudyService;

public class ServiceObjectBuilder {
	/*
	 * EditFlashCardService
	 */
	public static EditFlashCardService getEditFlashCardService(ListIterator<FlashCard> flashcardIterator, int stackId) {
		return new EditFlashCardServiceImpl(flashcardIterator, stackId);
	}
	
	/*
	 * FlashCardService
	 */
	public static FlashCardService getFlashCardService() {
		return new FlashCardServiceImpl();
	}
	
	/*
	 * GameOptionService
	 */
	public static GameOptionService getGameOptionService() {
		return new GameOptionServiceImpl();
	}
	
	/*
	 * StackService
	 */
	public static StackService getStackService() {
		return new StackServiceImpl();
	}
	
	/*
	 * StudyService
	 */
	public static StudyService getStudyService(GameOption options, Stack stack) {
		return new StudyServiceImpl(options, stack);
	}
	
	
}
