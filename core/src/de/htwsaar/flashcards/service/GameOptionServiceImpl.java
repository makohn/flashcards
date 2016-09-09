package de.htwsaar.flashcards.service;

import java.util.List;

import de.htwsaar.flashcards.builder.DaoObjectBuilder;
import de.htwsaar.flashcards.dao.interfaces.GameOptionDao;
import de.htwsaar.flashcards.model.GameOption;
import de.htwsaar.flashcards.service.interfaces.GameOptionService;
/**
 * * <code>GameOptionServiceImpl</code> - Kapselt den Zugriff auf DAO Methoden.
 * @author Marek Kohn
 *
 */
public class GameOptionServiceImpl implements GameOptionService {
	
	private GameOptionDao optionDao;
	
	public GameOptionServiceImpl() {
		optionDao = DaoObjectBuilder.getGameOptionDao();
	}
	/**
	 * Gibt eine Liste aller verfuegbaren Spieloptionen zurueck.
	 * @return - options - eine Liste aller Spieloptionen
	 */
	@Override
	public List<GameOption> getGameOptions() {
		List<GameOption> options = optionDao.getGameOptions();
		return options;
	}
	
	/**
	 * Gibt eine Liste aller verfuegbaren Spieltoptionen als Array 
	 * zurueck. (Fuer Comboboxen etc.)
	 * @return - ein Array aller Spieloptionen
	 */
	@Override
	public GameOption[] getGameOptionArray() {
		List<GameOption> options = optionDao.getGameOptions();
		return options.toArray(new GameOption[options.size()]);
	}
	
	/**
	 * Speichert bzw. aktualisiert eine Spieloption.
	 * @param option
	 */
	@Override
	public void saveOption(GameOption option) {
		optionDao.updateGameOption(option);
	}
}
