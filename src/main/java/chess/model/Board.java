package chess.model;

import chess.model.pieces.*;

public class Board {

    public Piece[][] getBoard() {
        return board;
    }

    private final Piece[][] board;

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

    public Piece getPiece(int x, int y) {
        return board[x][y];
    }

    public void movePiece(int startX, int startY, int endX, int endY) {
        Piece piece = board[startX][startY];
        board[endX][endY] = piece;
        board[startX][startY] = null;
    }

    public boolean isKingInCheck(boolean isWhite) {
        int kingRow = -1;
        int kingCol = -1;

        // Trouver la position du roi
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Piece piece = getPiece(row, col);
                if (piece instanceof King && piece.isWhite() == isWhite) {
                    kingRow = row;
                    kingCol = col;
                    break;
                }
            }
        }

//        if (kingRow == -1 || kingCol == -1) {
//            throw new IllegalStateException("Roi non trouvé sur le plateau.");
//        }

        // Vérifier si une pièce adverse peut attaquer le roi
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Piece piece = getPiece(row, col);
                if (piece != null && piece.isWhite() != isWhite) {
                    if (piece.isValidMove(row, col, kingRow, kingCol, this)) {
                        System.out.println(String.valueOf(row) + col);
                        if (isWhite) {
                            System.out.println("White king is in check");
                        } else {
                            System.out.println("Black king is in check");
                        }
                        return true;
                    }
                }
            }
        }
        return false;
    }

//    public void isKingInCheck() {
//        for (Piece[] pieces : board) {
//            for (Piece piece : pieces) {
//                if (piece != null && piece.getClass().getSimpleName().equalsIgnoreCase("king")) {
//                    System.out.println("King found");
//                }
//            }
//        }
//    }

}
