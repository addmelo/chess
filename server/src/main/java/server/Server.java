package server;

import spark.*;

public class Server {

    public int run(int desiredPort) {
        Spark.port(desiredPort);

        Spark.staticFiles.location("web");

        // Create endpoints and handle exceptions

        Spark.staticFiles.location("web");
        Spark.post("/user", this::registerHandler);
        Spark.post("/session", this::roginHandler);
        Spark.post(("/game"), this::createGameHandler);
        Spark.put(("/game"), this::joinGameHandler);
        Spark.get(("/game"), this::listGamesHandler);
        Spark.delete("/db", this::clearApplicationHandler);
        Spark.delete("/session", this::logoutHandler);
        Spark.awaitInitialization();
        return Spark.port();

        Spark.awaitInitialization();
        return Spark.port();
    }

    public void stop() {
        Spark.stop();
        Spark.awaitStop();
    }

    private
}
