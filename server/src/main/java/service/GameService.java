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

    public GameService(GameDAO game, AuthDAO auth){
        this.game = game;
        this.auth = auth;
    }
    public String registerGame(GameData currGame, String token) throws DataAccessException {

        AuthData authToken = auth.findToken(token);
        if (auth.findAuthToken(authToken) == null) {
            throw new DataAccessException("Error: unauthorized.");
        }
        if (game.getGameName(currGame.getName())){
            throw new DataAccessException("Error: bad request.");
        }
        game.addGame(currGame);
        return currGame.getGameID();
    }

    public void joinGame(GameData currGame, String token) throws DataAccessException {
        String gameID = currGame.getGameID();
        String playerColor = currGame.getPlayerColor();
        AuthData authToken = auth.findToken(token);
        String username = authToken.getUsername();
        if (auth.findAuthToken(authToken) == null) {
            throw new DataAccessException("Error: unauthorized.");
        }
        if (gameID == null) {
            throw new DataAccessException("Error: no game with this ID.");
        }
        if (!game.setGame(currGame, playerColor, username)) {
            throw new DataAccessException("Error: color already taken.");
        }
        GameData gameToJoin = game.getGame(gameID);
        if (playerColor.equals("White")) {
            gameToJoin.setWhite(username);
        } else{
            gameToJoin.setBlack(username);
        }
    }

    public ArrayList<GameData> listGames(String token) throws DataAccessException{
        if (auth.findToken(token) == null){
            throw new DataAccessException("Error: unauthorized.");
        }
        return game.getList();
    }

}
