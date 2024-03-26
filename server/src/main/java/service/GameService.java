package service;

import dataAccess.AuthDAO;
import dataAccess.DataAccessException;
import dataAccess.GameDAO;
import model.GameData;

import java.util.ArrayList;

public class GameService {
    private final GameDAO game;
    private final AuthDAO auth;

    public GameService(GameDAO game, AuthDAO auth){
        this.game = game;
        this.auth = auth;
    }
    public String registerGame(GameData currGame, String token) throws DataAccessException {

        if (auth.findToken(token) == null) {
            throw new DataAccessException("Error: unauthorized");
        }
        if (game.getGameName(currGame.getName())){
            throw new DataAccessException("Error: bad request");
        }
        game.addGame(currGame);
        return currGame.getGameID();
    }

    public void joinGame(GameData currGame, String token) throws DataAccessException {
        String gameID = currGame.getGameID();
        String playerColor = currGame.getPlayerColor();
        if (auth.findToken(token) == null) {
            throw new DataAccessException("Error: unauthorized");
        }
        if (game.getGame(gameID) == null) {
            throw new DataAccessException("Error: bad request");
        }
        String username = auth.findToken(token).getUsername();
        var joined = game.setGame(currGame, playerColor, username);
        if (!joined) {
            throw new DataAccessException("Error: already taken");
        }
    }

    public ArrayList<GameData> listGames(String token) throws DataAccessException{
        if (auth.findToken(token) == null){
            throw new DataAccessException("Error: unauthorized");
        }
        return game.getList();
    }

}
