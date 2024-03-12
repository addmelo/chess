package model;

import chess.ChessGame;

import java.util.Objects;

public class GameData {
    private final String gameID;
    private String whiteUsername;
    private String blackUsername;
    private final String gameName;
    private ChessGame game;

    public GameData(String gameName, String gameID){
        this.gameID = gameID;
        this.whiteUsername = null;
        this.blackUsername = null;
        this.gameName = gameName;
        this.game = null;

    }

    public String getGameID(){
        return gameID;
    }

    public String getWhite(){
        return whiteUsername;
    }
    public String getBlack(){
        return blackUsername;
    }
    public void setWhite(String playerWhite){
        whiteUsername = playerWhite;
    }
    public void setBlack(String playerBlack){
        blackUsername = playerBlack;
    }

    public String getName(){
        return gameName;
    }

    public ChessGame getGame(){
        return game;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameData gameData = (GameData) o;
        return Objects.equals(gameName, gameData.gameName) && Objects.equals(gameID, gameData.gameID) && Objects.equals(whiteUsername, gameData.whiteUsername) && Objects.equals(blackUsername, gameData.blackUsername);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gameName, gameID, whiteUsername, blackUsername);
    }

    @Override
    public String toString() {
        return "GameData{" +
                "gameName='" + gameName + '\'' +
                ", gameID='" + gameID + '\'' +
                ", whiteUsername='" + whiteUsername + '\'' +
                ", blackUsername='" + blackUsername + '\'' +
                '}';
    }
}
