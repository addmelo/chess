package service;

import dataAccess.AuthDAO;
import dataAccess.DataAccessException;
import dataAccess.UserDAO;
import model.AuthData;
import model.UserData;

public class UserService {
    private UserDAO user;
    private AuthDAO auth;

    public UserService(UserDAO user, AuthDAO auth){
    }

    public AuthData register(UserData newUser) throws DataAccessException{
        AuthData authToken = null;
        String username = newUser.getName();
        String email = newUser.getEmail();
        String password = newUser.getPassword();
        // Check if there is user with username
        if (user.getUserWithUsername(username) != null) {
            throw new DataAccessException("Error: username already taken.");
        }
        // Check if there is user with email
        if (user.getUserWithEmail(email) != null) {
            throw new DataAccessException("Error: email already taken.");
        }
        if (username == null || email == null || password == null){
            throw  new DataAccessException("Error: need more information");
        }
        // Create new user and new authToken
        else{
            user.addUser(username, password, email);
            authToken = auth.addAuthToken(username);
        }
        return authToken;
    }

    public AuthData logIn(UserData newUser) throws DataAccessException {
        String username = newUser.getName();
        String password = newUser.getPassword();
        AuthData authToken = null;

        if(user.getUserWithUsername(username).getPassword().equals(password)){
            authToken = auth.findUserToken(username);
        }else {
            throw new DataAccessException("Error: unauthorized.");
        }
        return authToken;
    }

    public void logOut(UserData newUser) throws DataAccessException{
        String username = newUser.getName();
        AuthData authToken = auth.findUserToken(username);
        if (authToken == null){
            throw new DataAccessException("Error: unauthorized");
        }
        auth.removeAuthToken(authToken);

    }

    public void logOutAuth(String token) throws DataAccessException{
        AuthData authToken = auth.findToken(token);
        if (authToken == null){
            throw new DataAccessException("Error: unauthorized");
        }
        auth.removeAuthToken(authToken);
    }
}
