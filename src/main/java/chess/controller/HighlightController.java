package chess.controller;

import chess.model.Board;
import chess.model.Piece;
import chess.view.BoardView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class HighlightController {

    public static BoardView getBoardView() {
        return boardView;
    }

    public static void setBoardView(BoardView boardView) {
        HighlightController.boardView = boardView;
    }

    private static BoardView boardView;

    private static final Color HIGHLIGHT_COLOR = Color.YELLOW;

    public HighlightController(BoardView boardView) {
        super();
        HighlightController.boardView = boardView;
    }

    public static void highlightValidMoves(Piece piece, int row, int col) {
        // Clear previous highlights
        clearHighlights();

        Rectangle[][] squares = boardView.getSquares();
        Board board = boardView.getBoard();

        // Highlight the selected piece's tile
        squares[row][col].setFill(HIGHLIGHT_COLOR);

        // Highlight the valid moves
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (piece.isValidMove(row, col, i, j, board) && (board.getPiece(i, j) == null || board.getPiece(i, j).isWhite() != piece.isWhite())) {
                    squares[i][j].setFill(HIGHLIGHT_COLOR);
                    System.out.println("Highlighting: " + i + ", " + j);
                }
            }
        }
    }

    public static void clearHighlights() {

        Rectangle[][] squares = boardView.getSquares();

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Color tileColor = (i + j) % 2 == 0 ? BoardView.LIGHT_COLOR : BoardView.DARK_COLOR;
                squares[i][j].setFill(tileColor);
            }
        }
    }

    public static void highlightTile(int row, int col) {
        Rectangle[][] squares = boardView.getSquares();
        squares[row][col].setFill(HIGHLIGHT_COLOR);
    }

}
