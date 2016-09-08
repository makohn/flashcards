package de.htwsaar.flashcards.service;

import java.util.List;

import de.htwsaar.flashcards.dao.GameOptionDaoImpl;
import de.htwsaar.flashcards.dao.interfaces.GameOptionDao;
import de.htwsaar.flashcards.model.GameOption;

public class GameOptionService {
	
	private GameOptionDao optionDao;
	
	public GameOptionService() {
		optionDao = new GameOptionDaoImpl();
	}
	
	public List<GameOption> getGameOptions() {
		List<GameOption> options = optionDao.getGameOptions();
		return options;
	}
	
	public GameOption[] getGameOptionArray() {
		List<GameOption> options = optionDao.getGameOptions();
		return options.toArray(new GameOption[options.size()]);
	}
	
	public void saveOption(GameOption option) {
		optionDao.updateGameOption(option);
	}
}
