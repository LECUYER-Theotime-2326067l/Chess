package chess.model.pieces;

import chess.model.Board;
import chess.model.Piece;

public class Rook extends Piece {

    public Rook(boolean isWhite) {
        super(isWhite);
    }

    @Override
    public boolean isValidMove(int startX, int startY, int endX, int endY, Board board) {
        // La tour se déplace uniquement en ligne droite
        if (startX == endX || startY == endY) {
            return isPathClear(startX, startY, endX, endY, board);
        }
        return false;
    }

    /**
     * Vérifie si le chemin entre deux cases est libre
     * @param startX position x de départ
     * @param startY position y de départ
     * @param endX position x d'arrivée
     * @param endY position y d'arrivée
     * @param board plateau de jeu
     * @return true si le chemin est libre, false sinon
     */
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
