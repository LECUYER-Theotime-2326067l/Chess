package chess.controller;

import chess.model.Board;
import chess.view.BoardView;

public class ChessController {

    private final Board board;

    public Board getBoard() {
        return board;
    }

    private final BoardView boardView;

    public BoardView getBoardView() {
        return boardView;
    }

    private boolean isWhiteTurn;

    public boolean isWhiteTurn() {
        return isWhiteTurn;
    }

    private boolean isWhiteInCheck;
    private boolean isBlackInCheck;

    public boolean isWhiteInCheck() {
        return isWhiteInCheck;
    }
    public boolean isBlackInCheck() {
        return isBlackInCheck;
    }

    public void toggleTurn() {
        // Méthode pour changer le tour du joueur
        // (de blanc à noir ou de noir à blanc)
        isWhiteTurn = !isWhiteTurn;
    }

    public ChessController(Board board, BoardView boardView) {
        // Initialisation d'une partie d'échecs
        this.board = board; // Création d'un plateau
        this.boardView = boardView; // Création d'une vue du plateau
        this.isWhiteTurn = true; // Le joueur blanc commence
        ClickController.setChessController(this);
    }

    public void updateCheck() {
        // Méthode pour vérifier si un roi est en échec
        // Si un roi est en échec et mat, la partie est terminée
        if (board.isKingInCheckMate(true) || board.isKingInCheckMate(false)) {
            GameController.setGameRunning(false);
            GameController.endGame();
            // Écriture du résultat de la partie dans le fichier de la partie
            if (board.isKingInCheckMate(true)) {
                FileController.writeToFile("Game" + GameController.getGameId() + ".txt", "Black wins!");
            } else {
                FileController.writeToFile("Game" + GameController.getGameId() + ".txt", "White wins!");
            }
            return;
        }
        // Si un roi est en échec, on met à jour les variables
        isWhiteInCheck = board.isKingInCheck(true);
        isBlackInCheck = board.isKingInCheck(false);
    }
}
