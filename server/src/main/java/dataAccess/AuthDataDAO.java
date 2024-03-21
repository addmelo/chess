package dataAccess;

import model.AuthData;

import java.util.ArrayList;

public class AuthDataDAO implements AuthDAO {
    private ArrayList<AuthData> authTokensList = new ArrayList<>();

    public AuthData addAuthToken(String username){
        AuthData newUser = new AuthData(username);
        authTokensList.add(newUser);
        return newUser;
    }

    public AuthData findUserToken(String username){
        for (AuthData authToken:authTokensList) {
            if (authToken.getUsername().equals(username)){
                return authToken;
            }
        }
        return null;
    }
    public AuthData findAuthToken(AuthData givenAuthToken){
        for (AuthData authToken:authTokensList) {
            if (authToken.equals(givenAuthToken)){
                return authToken;
            }
        }
        return null;
    }
    public AuthData findToken(String givenAuthToken){
        for (AuthData authToken:authTokensList) {
            if (authToken.getToken().equals(givenAuthToken)){
                return authToken;
            }
        }
        return null;
    }

    public void removeAuthToken(AuthData authToken){
        authTokensList.remove(authToken);
    }

    public void clearAllAuth() {
        authTokensList.clear();;
    }
}
