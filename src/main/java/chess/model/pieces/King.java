package chess.model.pieces;

import chess.model.Board;
import chess.model.Piece;

public class King extends Piece {

    public King(boolean isWhite) {
        super(isWhite);
    }

    @Override
    public boolean isValidMove(int startX, int startY, int endX, int endY, Board board) {
        int deltaX = Math.abs(endX - startX);
        int deltaY = Math.abs(endY - startY);

        // Le Roi peut se déplacer d'une case dans n'importe quelle direction
        return (deltaX <= 1 && deltaY <= 1);
    }
}
