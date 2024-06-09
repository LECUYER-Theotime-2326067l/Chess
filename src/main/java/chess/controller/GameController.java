package chess.controller;

import chess.App;
import chess.model.Board;
import chess.view.AppView;
import chess.view.BoardView;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class GameController {

    private static int gameId = 1;
    public static int getGameId() {
        return gameId;
    }
    public static void setGameId(int gameId) {
        GameController.gameId = gameId;
    }

    private static boolean gameRunning = false;
    public static boolean isGameRunning() {
        return gameRunning;
    }
    public static void setGameRunning(boolean gameRunning) {
        GameController.gameRunning = gameRunning;
    }

    private static final StringProperty firstPlayerUsername = new SimpleStringProperty();
    public static StringProperty firstPlayerUsernameProperty() {
        return firstPlayerUsername;
    }
    public static void setFirstPlayerUsername(String firstPlayerUsername) {
        GameController.firstPlayerUsername.set(firstPlayerUsername);
    }

    private static final StringProperty secondPlayerUsername = new SimpleStringProperty();
    public static StringProperty secondPlayerUsernameProperty() {
        return secondPlayerUsername;
    }
    public static void setSecondPlayerUsername(String secondPlayerUsername) {
        GameController.secondPlayerUsername.set(secondPlayerUsername);
    }

    /**
     * Démarre une partie contre un joueur
     * @param firstPlayerName nom du joueur
     * @param secondPlayerName nom du joueur adverse
     */
    public static void startVersusGame(String firstPlayerName, String secondPlayerName) {
        // Cache le menu de choix des pseudonymes
        App.removeMenu();
        setFirstPlayerUsername(firstPlayerName);
        setSecondPlayerUsername(secondPlayerName);
        FileController.addPlayer(firstPlayerName);
        FileController.addPlayer(secondPlayerName);
        // Initialise le fichier journal de la partie
        // Si le fichier existe déjà, alors incrémenter l'identifiant de la partie
        String gameFileName = "Game" + getGameId() + ".txt";
        while (FileController.doFileExist(gameFileName)) {
            setGameId(GameController.getGameId() + 1);
            gameFileName = "Game" + getGameId() + ".txt";
        }
        FileController.writeToFile(gameFileName, "Game started between " + firstPlayerName + " and " + secondPlayerName);
        setGameRunning(true);
    }

    /**
     * Finit une partie
     */
    public static void endGame() {
        Board board = new Board();
        BoardView boardView = new BoardView(board);
        new ChessController(board, boardView);
        App.resetStage(new AppView(boardView));
    }
}
