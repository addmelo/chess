package service;

import dataAccess.AuthDAO;
import dataAccess.DataAccessException;
import dataAccess.GameDAO;
import model.AuthData;
import model.GameData;

import java.util.ArrayList;

public class GameService {
    private GameDAO game;
    private AuthDAO auth;

    public GameService(GameData game, AuthData auth){

    }
    public String registerGame(GameData currGame) throws DataAccessException {
        if (auth.findToken(currGame.getAuthToken()) == null){
            throw new DataAccessException("Error: unauthorized.");
        }

        if (game.getGameName(currGame.getName())){
            throw new DataAccessException("Error: game already exists!");
        }
        game.addGame(currGame);
        return currGame.getGameID();
    }

    public void joinGame(GameData currGame, AuthData authToken, String playerColor) throws DataAccessException {
        String gameID = currGame.getGameID();
        String gameName = currGame.getName();
        String username = authToken.getUsername();
        if (auth.findAuthToken(authToken) == null){
            throw new DataAccessException("Error: unauthorized.");
        }
        if (gameID == null){
            throw new DataAccessException("Error: no game with this ID.");
        }
        if (!game.setGame(currGame, playerColor, username)){
            throw new DataAccessException("Error: color already taken.");
        }
    }

    public ArrayList<GameData> listGames(AuthData authToken) throws DataAccessException{
        if (auth.findAuthToken(authToken) == null){
            throw new DataAccessException("Error: unauthorized.");
        }
        return game.getList();
    }

}
