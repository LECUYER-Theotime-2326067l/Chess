package chess.controller;

import chess.App;
import chess.model.Board;
import chess.view.AppView;
import chess.view.BoardView;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class GameController {

    private static int gameId = 1;
    private static boolean gameRunning = false;
    private static StringProperty firstPlayerUsername = new SimpleStringProperty();

    public static boolean isGameRunning() {
        return gameRunning;
    }
    private static StringProperty secondPlayerUsername = new SimpleStringProperty();

    public static int getGameId() {
        return gameId;
    }

    public static void setGameId(int gameId) {
        GameController.gameId = gameId;
    }

    public static void setGameRunning(boolean gameRunning) {
        GameController.gameRunning = gameRunning;
    }

    public static String getFirstPlayerUsername() {
        return firstPlayerUsername.get();
    }

    public static void setFirstPlayerUsername(String firstPlayerUsername) {
        GameController.firstPlayerUsername.set(firstPlayerUsername);
    }

    public static StringProperty firstPlayerUsernameProperty() {
        return firstPlayerUsername;
    }

    public static String getSecondPlayerUsername() {
        return secondPlayerUsername.get();
    }

    public static void setSecondPlayerUsername(String secondPlayerUsername) {
        GameController.secondPlayerUsername.set(secondPlayerUsername);
    }

    public static StringProperty secondPlayerUsernameProperty() {
        return secondPlayerUsername;
    }

    public static void startVersusGame(String firstPlayerName, String secondPlayerName) {
        App.removeMenu();
        setFirstPlayerUsername(firstPlayerName);
        setSecondPlayerUsername(secondPlayerName);
        String gameFileName = "Game" + getGameId() + ".txt";
        while (FileController.doFileExist(gameFileName)) {
            setGameId(GameController.getGameId() + 1);
            gameFileName = "Game" + getGameId() + ".txt";
        }
        FileController.writeToFile(gameFileName, "Game started between " + firstPlayerName + " and " + secondPlayerName);
        setGameRunning(true);
    }

    public static void endGame() {
//        App.setAppView(new AppView(new BoardView(new Board())));
        Board board = new Board();
        BoardView boardView = new BoardView(board);
        ChessController controller = new ChessController(board, boardView);
        App.resetStage(new AppView(boardView));
//        App.main(new String[]{}); // Restart the application
    }

}
