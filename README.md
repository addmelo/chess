# ♕ BYU CS 240 Chess

This project demonstrates mastery of proper software design, client/server architecture, networking using HTTP and WebSocket, database persistence, unit testing, serialization, and security.

## 10k Architecture Overview

The application implements a multiplayer chess server and a command line chess client.

[![Sequence Diagram](10k-architecture.png)](https://sequencediagram.org/index.html#initialData=C4S2BsFMAIGEAtIGckCh0AcCGAnUBjEbAO2DnBElIEZVs8RCSzYKrgAmO3AorU6AGVIOAG4jUAEyzAsAIyxIYAERnzFkdKgrFIuaKlaUa0ALQA+ISPE4AXNABWAexDFoAcywBbTcLEizS1VZBSVbbVc9HGgnADNYiN19QzZSDkCrfztHFzdPH1Q-Gwzg9TDEqJj4iuSjdmoMopF7LywAaxgvJ3FC6wCLaFLQyHCdSriEseSm6NMBurT7AFcMaWAYOSdcSRTjTka+7NaO6C6emZK1YdHI-Qma6N6ss3nU4Gpl1ZkNrZwdhfeByy9hwyBA7mIT2KAyGGhuSWi9wuc0sAI49nyMG6ElQQA)

## IntelliJ Support

Open the project directory in IntelliJ in order to develop, run, and debug your code using an IDE.

## Maven Support

You can use the following commands to build, test, package, and run your code.

| Command                    | Description                                     |
| -------------------------- | ----------------------------------------------- |
| `mvn compile`              | Builds the code                                 |
| `mvn package`              | Run the tests and build an Uber jar file        |
| `mvn package -DskipTests`  | Build an Uber jar file                          |
| `mvn install`              | Installs the packages into the local repository |
| `mvn test`                 | Run all the tests                               |
| `mvn -pl shared test`     | Run all the shared tests                        |
| `mvn -pl client exec:java` | Build and run the client `Main`                 |
| `mvn -pl server exec:java` | Build and run the server `Main`                 |

These commands are configured by the `pom.xml` (Project Object Model) files. There is a POM file in the root of the project, and one in each of the modules. The root POM defines any global dependencies and references the module POM files.

### Running the program using Java

Once you have compiled your project into an uber jar, you can execute it with the following command.

```sh
java -jar client/target/client-jar-with-dependencies.jar

♕ 240 Chess Client: chess.ChessPiece@7852e922
```

A copy of the sequence diagram for the server design can be found at https://sequencediagram.org/index.html#initialData=IYYwLg9gTgBAwgGwJYFMB2YBQAHYUxIhK4YwDKKUAbpTngUSWOZVYSnfoccKQCLAwwAIIgQKAM4TMAE0HAARsAkoYMhZkwBzKBACu2GAGI0wKgE8YAJRRakEsFEFIIaYwHcAFkjAdEqUgBaAD4WakoALhgAbQAFAHkyABUAXRgAej0VKAAdNABvLMpTAFsUABoYXCl3aBlKlBLgJAQAX0wKcNgQsLZxKKhbe18oAAoiqFKKquUJWqh6mEbmhABKDtZ2GB6BIVFxKSitFDAAVWzx7Kn13ZExSQlt0PUosgBRABk3uCSYCamYAAzXQlP7ZTC3fYPbY9Tp9FBRNB6BAIDbULY7eRQw4wECDQQoc6US7FYBlSrVOZ1G5Y+5SJ5qBRRACSADl3lZfv8ydNKfNFssWjA2Ul4mDaHCMaFIXSJFE8SgCcI9GBPCTJjyaXtZQyXsL2W9OeKNeSYMAVZ4khAANbofWis0WiG0g6PQKwzb9R2qq22tBo+Ew0JwyLey029AByhB+DIdBgKIAJgADMm8vlzT6I2h2ugZJodPpDEZoLxjjAPhA7G4jF4fH440Fg6xQ3FEqkMiopC40Onuaa+XV2iGoCFJf0EFXmWh1VMKbN+etxygQjLXUcTkSxv2UFq7q6Qnr3l8fsaAcCIKCJs7tQex56ERN4goAFYocAB9irl0PeX43yZmqO57tiEiHkycBWG8whJG8Ya+pGa4PO6wTLlEgEIf6y4oSO6EWphUajiE-jxkmqbphh2btJgeYFroBjGDoKB2pWWj6MwtbeL4mAkU2vStnwnxvLBaTpF2Eg9nklF+oR97ol6k5aPEKqjNJ6BLg+363r+xxgMqWZ+qp+HZiBsrgREx7fL8an+khUg4Q+EQ2Z+4hafuv4yCgCAnCgRkGepN7ufZwQvIJXywfB2ZAiCkUyYFoEOfJCIuSuck0FAESycRjYJjAKbJjRaD5to9HFoMMgVsMMAAOI8o8nH1jxOUMrhMTVcJolaDyUnGTJI5pewG5gB8wy1WUfnhn6GlJW5oERLp+mTegE2YaZB4hUylmns5dlgQN-TOdhwS7UNY2SKMa3IRtFlCaeXVlMyfCVHWvhbnOMAKAgoDWm9PKVPd6A8tFl41TytzxWZ+0IsgDgwBAgIwAD0jYWlkQw8w8OI3VWXBLxuX5emMAAERI0TUTRPkJM8o9ZMwAAjImADMAAslREy9hJXDyZNE0TbOfd9v1lDzfPEwDrI8hEvOtDAKS5kVdFFsY2B6FA2DefA-6qGdHhcQ2ATMB6XTkwkySdd1pi9eg6bizyw4tqOqGOYMdgOMStumjZ03wrNsrzSci2YStJkQ+tLxbdZVu2T+9lQ05Ucpb7664lrZ2jB7u6h1dLxshyvwZ2aRUwIMaCUASZ2PfaYpneDx0x3KukV3w6c8hLZSXbHTtJfN1N8JoKNd+lPcPX3-XZQbEQEwUAOPfLxWFgxRjmF5k7uDAABSEBIG4OtGALIDWk1BstQ7JunB26QAz1-m9gUeNwBAk5QP9vf210ceNzyLcj97X519phwFpR2DlNLOwVw63UjjfMBe1B6DUOppf+QUG4nDOhIb+KBHodzAtdCOWMR7A1BDXeQMDErwgiGgCAzAkQokTkguaL4t5oDTvfR+0AX4-1Iddde8Q2T4NUO4HwnhYwGwfk-QuFUZ593oX7T+I8MFt0zrtMhg1E6owyjjPGk9UyFXnqVRiThLCIEVLAYA2B1aEGcK4XWjU8Yn2NjEMKwk3iiXULJOB-QQDeTwBdOhJ0vEmN8co66TiIpbluOUYhQhKiB2zKQqGfj65cLcTI9ccjMHNwztglR-Q1GD0iJonK2iCq0SAA
