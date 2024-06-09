package chess.model;

import chess.controller.FileController;
import chess.model.pieces.*;

public class Board {

    private final Piece[][] board;
    public Piece[][] getBoard() {
        return board;
    }

    public Board() {
        board = new Piece[8][8];
        setupBoard();
    }

    private void setupBoard() {
        board[0][0] = new Rook(false);
        board[0][1] = new Knight(false);
        board[0][2] = new Bishop(false);
        board[0][3] = new Queen(false);
        board[0][4] = new King(false);
        board[0][5] = new Bishop(false);
        board[0][6] = new Knight(false);
        board[0][7] = new Rook(false);

        for (int i = 0; i < 8; i++) {
            board[1][i] = new Pawn(false);
            board[6][i] = new Pawn(true);
        }

        board[7][0] = new Rook(true);
        board[7][1] = new Knight(true);
        board[7][2] = new Bishop(true);
        board[7][3] = new Queen(true);
        board[7][4] = new King(true);
        board[7][5] = new Bishop(true);
        board[7][6] = new Knight(true);
        board[7][7] = new Rook(true);
    }

    /**
     * Récupère la pièce à la position (x, y)
     * @param x ligne de la pièce
     * @param y colonne de la pièce
     * @return la pièce si elle existe, null sinon
     */
    public Piece getPiece(int x, int y) {
        return board[x][y];
    }

    /**
     * Déplace une pièce d'une position à une autre
     * @param startX ligne de départ
     * @param startY colonne de départ
     * @param endX ligne d'arrivée
     * @param endY colonne d'arrivée
     */
    public void movePiece(int startX, int startY, int endX, int endY) {
        Piece piece = board[startX][startY];
        FileController.writeToFile("log.txt", (piece.isWhite() ? "White " : "Black ") + piece.getClass().getSimpleName() + " moved from " + startX + "," + startY + " to " + endX + "," + endY);
        board[endX][endY] = piece;
        board[startX][startY] = null;
    }

    /**
     * Récupère la position du roi
     * @param isWhite couleur du roi
     * @return position du roi
     */
    public int[] getKingPosition(boolean isWhite) {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Piece piece = getPiece(row, col);
                if (piece instanceof King && piece.isWhite() == isWhite) {
                    return new int[]{row, col};
                }
            }
        }

        return new int[]{-1, -1};
    }

    /**
     * Vérifie si le roi est en échec
     * @param isWhite couleur du roi
     * @return true si le roi est en échec, false sinon
     */
    public boolean isKingInCheck(boolean isWhite) {
        int[] kingPosition = getKingPosition(isWhite);
        int kingRow = kingPosition[0];
        int kingCol = kingPosition[1];

        // Vérifie si une pièce adverse peut attaquer le roi
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Piece piece = getPiece(row, col);
                // Vérifie si la pièce est de la couleur opposée
                if (piece != null && piece.isWhite() != isWhite) {
                    if (piece.isValidMove(row, col, kingRow, kingCol, this)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Vérifie si le roi est en échec et mat
     * @param isWhite couleur du roi
     * @return true si le roi est en échec et mat, false sinon
     */
    public boolean isKingInCheckMate(boolean isWhite) {
        int[] kingPosition = getKingPosition(isWhite);
        int kingRow = kingPosition[0];
        int kingCol = kingPosition[1];

        // Vérifier si le roi est en échec
        if (!isKingInCheck(isWhite)) {
            return false;
        }

        // Vérifier si le roi peut se déplacer
        King king = (King) getPiece(kingRow, kingCol);
        boolean[][] validMoves = king.getValidMoves();
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if (validMoves[row][col]) {
                    // Si il y a un mouvement possible, alors le roi n'est pas en échec et mat
                    return false;
                }
            }
        }
        return true;
    }
}
