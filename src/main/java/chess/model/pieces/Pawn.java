package chess.model.pieces;

import chess.model.Board;
import chess.model.Piece;

public class Pawn extends Piece {

    public Pawn(boolean isWhite) {
        super(isWhite);
    }

    @Override
    public boolean isValidMove(int startX, int startY, int endX, int endY, Board board) {
        int direction = isWhite() ? -1 : 1;
        int startRow = isWhite() ? 6 : 1;

        // Le Pion ne peut pas se déplacer sur sa propre case
        if (startX == endX && startY == endY) {
            return false;
        }

        // Le Pion se déplace d'une case en avant ou de deux cases depuis sa position initiale
        if (startX + direction == endX && startY == endY && board.getPiece(endX, endY) == null) {
            return true;
        }

        if (startX == startRow && startX + 2 * direction == endX && startY == endY && board.getPiece(endX, endY) == null) {
            return true;
        }

        // Le Pion capture en diagonale
        return startX + direction == endX && Math.abs(endY - startY) == 1 && board.getPiece(endX, endY) != null && board.getPiece(endX, endY).isWhite() != isWhite();
    }
}
