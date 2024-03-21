package dataAccess;

import model.GameData;

import java.util.ArrayList;

public class GameDataDAO {
    private ArrayList<GameData> gamesList =  new ArrayList<>();
    int gameCount = 1;

    public void addGame(GameData newGame) {
        gameCount += 1;
        gamesList.add(newGame);
    }

    public String getGameID(String gameName) {
        for (GameData game: gamesList) {
            if (game.getName().equals(gameName)){
                return game.getGameID();
            }
        }
        return null;
    }

    public Boolean getGameName(String gameName){
        for (GameData game:gamesList) {
            if (game.getName().equals(gameName)){
                return true;
            }
        }
        return false;
    }

    public GameData getGame(String gameID) {
        for (GameData game:gamesList){
            if (game.getGameID().equals(gameID)){
                return game;
            }
        }
        return null;
    }

    public Boolean setGame(GameData game, String playerColor, String userName){
        if (playerColor == null) {
            return true;
        }
        if (playerColor.equals("WHITE") && (game.getWhite() == null)){
            game.setWhite(userName);
            return true;
        }
        else if (playerColor.equals("BLACK") && (game.getBlack() == null)){
            game.setBlack(userName);
            return true;
        }
        else {
            return false;
        }
    }

    public ArrayList<GameData> getList(){
        return gamesList;
    }

    public void clearAllGames(){
        gamesList.clear();
    }
}
