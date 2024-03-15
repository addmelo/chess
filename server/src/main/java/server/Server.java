package server;

import com.google.gson.Gson;
import dataAccess.AuthDAO;
import dataAccess.DataAccessException;
import dataAccess.GameDAO;
import dataAccess.UserDAO;
import model.AuthData;
import model.GameData;
import model.UserData;
import service.ClearService;
import service.GameService;
import service.UserService;
import spark.*;

import java.util.Map;

public class Server {
    private UserDAO user;
    private AuthDAO auth;
    private GameDAO game;
    private UserService userService;
    private GameService gameService;
    private ClearService clearService;

    public int run(int desiredPort) {
        Spark.port(desiredPort);

        Spark.staticFiles.location("web");

        // Create endpoints and handle exceptions

        Spark.staticFiles.location("web");
        Spark.post("/user", this::registerHandler);
        Spark.post("/session", this::loginHandler);
        Spark.post(("/game"), this::createGameHandler);
//        Spark.put(("/game"), this::joinGameHandler);
//        Spark.get(("/game"), this::listGamesHandler);
//        Spark.delete("/db", this::clearApplicationHandler);
        Spark.delete("/session", this::logoutHandler);
        Spark.awaitInitialization();
        return Spark.port();

    }

    public void stop() {
        Spark.stop();
        Spark.awaitStop();
    }

    public Object registerHandler(Request req, Response res) throws DataAccessException {
        try {
            UserData request = new Gson().fromJson(req.body(), UserData.class);
            AuthData authToken = userService.register(request);
            res.status(200);
            return new Gson().toJson(authToken);
        } catch (DataAccessException e) {
            var message = e.getMessage();
            if (message.equals("Error: need more information")) {
                res.status(400);
            } else if (message.equals("Error: username already taken.") || message.equals("Error: email already taken.")) {
                res.status(403);
            } else {
                res.status(500);
            }
            return new Gson().toJson(Map.of("message", e.getMessage()));
        }
    }

    public Object loginHandler(Request req, Response res) throws DataAccessException {
        try {
            UserData request = new Gson().fromJson(req.body(), UserData.class);
            AuthData authToken = userService.logIn(request);
            res.status(200);
            return new Gson().toJson(authToken);
        } catch (DataAccessException e) {
            var message = e.getMessage();
            if (message.equals("Error: unauthorized")) {
                res.status(401);
            } else {
                res.status(500);
            }
            return new Gson().toJson(Map.of("message", e.getMessage()));
        }
    }
    public Object createGameHandler(Request req, Response res) throws DataAccessException {
        try {
//            String authToken = req.headers("Authorization");
            GameData request = new Gson().fromJson(req.body(), GameData.class);
            String gameID = gameService.registerGame(request);
            res.status(200);
            return new Gson().toJson(gameID);
        }
        catch (DataAccessException e){
            var message = e.getMessage();
            if (message.equals("Error: unauthorized")) {
                res.status(401);
            }
            if (message.equals("Error: bad request")) {
                res.status(400);
            }
            else {
                res.status(500);
            }
            return new Gson().toJson(Map.of("message", e.getMessage()));
        }
    }
//    public Object JoinGameHandler(Request req, Response res) {
//        try {
//            GameData request = new Gson().fromJson(req.body(), GameData.class);
//            gameService.joinGame(request);
//            res.status(200);
//            return "{}";
//        }
//        catch (DataAccessException e) {
//            var message = e.getMessage();
//            if (message.equals("Error: unauthorized")) {
//                res.status(401);
//                return new Gson().toJson(Map.of("message", e.getMessage()));
//            }
//            if (message.equals("Error: bad request")) {
//                res.status(400);
//                return new Gson().toJson(Map.of("message", e.getMessage()));
//            }
//            if (message.equals("Error: already taken")) {
//                res.status(403);
//                return new Gson().toJson(Map.of("message", e.getMessage()));
//            } else {
//                res.status(500);
//                return new Gson().toJson(Map.of("message", e.getMessage()));
//            }
//        }
//    }

    public Object logoutHandler(Request req, Response res) throws DataAccessException {
        try {
            UserData request = new Gson().fromJson(req.body(), UserData.class);
            userService.logOut(request);
            res.status(200);
            return "{}";
        } catch (DataAccessException e) {
            var message = e.getMessage();
            if (message.equals("Error: unauthorized")) {
                res.status(401);
            } else {
                res.status(500);
            }
            return new Gson().toJson(Map.of("message", e.getMessage()));
        }
    }
}
