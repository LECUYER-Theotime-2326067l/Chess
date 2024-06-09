package chess.controller;

import chess.model.Board;
import chess.model.Piece;
import chess.view.BoardView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class HighlightController {

    private static BoardView boardView;
    public static void setBoardView(BoardView boardView) {
        HighlightController.boardView = boardView;
    }

    private static final Color HIGHLIGHT_COLOR = Color.YELLOW;

    public HighlightController(BoardView boardView) {
        super();
        HighlightController.boardView = boardView;
    }

    /**
     * Surligne les mouvements valides d'un pion
     * @param piece pion sélectionné
     * @param row ligne du pion
     * @param col colonne du pion
     */
    public static void highlightValidMoves(Piece piece, int row, int col) {
        clearHighlights();
        highlightTile(row, col);

        boolean[][] validMoves = piece.getValidMoves();
        Board board = boardView.getBoard();
        // Surligne chaque mouvement valide du pion
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (validMoves[i][j] && (board.getPiece(i, j) == null || board.getPiece(i, j).isWhite() != piece.isWhite())) {
                    highlightTile(i, j);
                }
            }
        }
    }

    /**
     * Supprime les surlignages de l'échiquier
     */
    public static void clearHighlights() {
        Rectangle[][] squares = boardView.getSquares();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Color tileColor = (i + j) % 2 == 0 ? BoardView.LIGHT_COLOR : BoardView.DARK_COLOR;
                squares[i][j].setFill(tileColor);
            }
        }
    }

    /**
     * Surligne une case de l'échiquier
     * @param row ligne
     * @param col colonne
     */
    public static void highlightTile(int row, int col) {
        Rectangle[][] squares = boardView.getSquares();
        squares[row][col].setFill(HIGHLIGHT_COLOR);
    }
}
