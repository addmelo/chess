package dataAccess;

import model.AuthData;
import model.GameData;

public interface AuthDAO {
    public AuthData addAuthToken(String username) throws DataAccessException;
    public AuthData findUserToken(String username) throws DataAccessException;
    public AuthData findAuthToken(AuthData givenAuthToken) throws DataAccessException;
    public AuthData findToken(String givenAuthToken) throws DataAccessException;
    public void removeAuthToken(AuthData authToken) throws DataAccessException;
    public void clearAllAuth() throws DataAccessException;

}
