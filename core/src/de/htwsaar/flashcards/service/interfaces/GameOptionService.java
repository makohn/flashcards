package de.htwsaar.flashcards.service.interfaces;

import java.util.List;

import de.htwsaar.flashcards.model.GameOption;

/**
 * <code>GameOptionService</code> -Dienst fuer die (normale) Datenbankinteraktion bzgl. 
 * der Entitaet <code>GameOption</code>.
 * @author Marek Kohn
 *
 */
public interface GameOptionService {

	public List<GameOption> getGameOptions();
	public GameOption[] getGameOptionArray();
	public void saveOption(GameOption option);
}
