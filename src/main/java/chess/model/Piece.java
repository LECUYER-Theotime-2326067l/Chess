package chess.model;

public abstract class Piece {

    private boolean[][] validMoves;

    public Piece(boolean isWhite) {
        this.isWhite = isWhite;
        this.validMoves = new boolean[8][8];
    }

    private final boolean isWhite;

    public boolean[][] getValidMoves() {
        return validMoves;
    }

    public boolean isWhite() {
        return isWhite;
    }

    public void updateValidMoves(int x, int y, Board board) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                validMoves[i][j] = isValidMove(x, y, i, j, board);
            }
        }
    }

    public abstract boolean isValidMove(int startX, int startY, int endX, int endY, Board board);
}
