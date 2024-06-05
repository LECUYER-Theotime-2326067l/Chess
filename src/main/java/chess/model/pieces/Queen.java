package chess.model.pieces;

import chess.model.Board;
import chess.model.Piece;

public class Queen extends Piece {

    public Queen(boolean isWhite) {
        super(isWhite);
    }

    @Override
    public boolean isValidMove(int startX, int startY, int endX, int endY, Board board) {
        // Calculer les différences entre les coordonnées de départ et d'arrivée
        int deltaX = Math.abs(endX - startX);
        int deltaY = Math.abs(endY - startY);

        // Vérifier les mouvements valides pour la Reine
        // La Reine peut se déplacer verticalement, horizontalement et diagonalement
        if (deltaX == deltaY || startX == endX || startY == endY) {
            // Vérifier s'il y a des pièces sur le chemin
            return isPathClear(startX, startY, endX, endY, board);
        }

        return false;
    }

    private boolean isPathClear(int startX, int startY, int endX, int endY, Board board) {
        int xDirection = Integer.compare(endX, startX);
        int yDirection = Integer.compare(endY, startY);

        int x = startX + xDirection;
        int y = startY + yDirection;

        while (x != endX || y != endY) {
            if (board.getPiece(x, y) != null) {
                return false;
            }
            x += xDirection;
            y += yDirection;
        }

        return true;
    }
}
