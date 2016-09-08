package de.htwsaar.flashcards.dao.interfaces;

import java.util.List;

import de.htwsaar.flashcards.model.GameOption;

public interface GameOptionDao {
	public void deleteGameOption(GameOption option);
	public void saveGameOption(GameOption option);
	public void updateGameOption(GameOption option);
	public List<GameOption> getGameOptions(); 
	public GameOption getGameOption(int id);
}
