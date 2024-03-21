package dataAccess;

import model.GameData;

import java.util.ArrayList;

public interface GameDAO {
    public void addGame(GameData newGame) throws DataAccessException;
    public String getGameID(String gameName) throws DataAccessException;
    public Boolean getGameName(String gameName) throws DataAccessException;
    public GameData getGame(String gameID) throws DataAccessException;
    public Boolean setGame(GameData game, String playerColor, String userName) throws DataAccessException;
    public ArrayList<GameData> getList() throws DataAccessException;
    public void clearAllGames() throws DataAccessException;
}
