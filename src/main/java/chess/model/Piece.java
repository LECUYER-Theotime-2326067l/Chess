package chess.model;

public abstract class Piece {

    private final boolean[][] validMoves;
    public boolean[][] getValidMoves() {
        return validMoves;
    }

    private final boolean isWhite;
    public boolean isWhite() {
        return isWhite;
    }

    public Piece(boolean isWhite) {
        this.isWhite = isWhite;
        this.validMoves = new boolean[8][8];
    }

    /**
     * Met à jour les mouvements valides du pion
     * @param x position x du pion
     * @param y position y du pion
     * @param board plateau de jeu
     */
    public void updateValidMoves(int x, int y, Board board) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                validMoves[i][j] = isValidMove(x, y, i, j, board);
            }
        }
    }

    /**
     * Vérifie si un mouvement est valide
     * @param startX position x de départ
     * @param startY position y de départ
     * @param endX position x d'arrivée
     * @param endY position y d'arrivée
     * @param board plateau de jeu
     * @return true si le mouvement est valide, false sinon
     */
    public abstract boolean isValidMove(int startX, int startY, int endX, int endY, Board board);
}
