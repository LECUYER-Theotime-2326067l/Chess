package chess.controller;

import chess.model.Board;
import chess.view.BoardView;

public class ChessController {

    public Board getBoard() {
        return board;
    }

    private final Board board;

    public BoardView getBoardView() {
        return boardView;
    }

    private final BoardView boardView;

    public boolean isWhiteTurn() {
        return isWhiteTurn;
    }

    private boolean isWhiteTurn;

    private boolean isWhiteInCheck;

    public boolean isWhiteInCheck() {
        return isWhiteInCheck;
    }

    public boolean isBlackInCheck() {
        return isBlackInCheck;
    }

    private boolean isBlackInCheck;

    public void toggleTurn() {
        isWhiteTurn = !isWhiteTurn;
    }

    public ChessController(Board board, BoardView boardView) {
        this.board = board;
        this.boardView = boardView;
        this.isWhiteTurn = true; // Le joueur blanc commence
        ClickController.setChessController(this);
    }

    public void updateCheck() {
        if (board.isKingInCheckMate(true) || board.isKingInCheckMate(false)) {
            System.out.println("Checkmate!");
            GameController.setGameRunning(false);
            GameController.endGame();
            if (board.isKingInCheckMate(true)) {
                FileController.writeToFile("Game" + GameController.getGameId() + ".txt", "Black wins!");
            } else {
                FileController.writeToFile("Game" + GameController.getGameId() + ".txt", "White wins!");
            }
            return;
        }
        isWhiteInCheck = board.isKingInCheck(true);
        isBlackInCheck = board.isKingInCheck(false);
    }

//    private void handleMouseClick(double x, double y) {
//        int col = (int) (x / (boardView.getWidth() / 8));
//        int row = (int) (y / (boardView.getHeight() / 8));
//
//        ClickController.handleTileClick(row, col);
//    }
}
