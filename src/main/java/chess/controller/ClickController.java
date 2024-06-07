package chess.controller;

import chess.model.Board;
import chess.model.Piece;
import chess.view.BoardView;

public class ClickController {

    private static Board board;

    private static BoardView boardView;

    private static ChessController chessController;

    private static Piece selectedPiece;
    private static int selectedRow;
    private static int selectedCol;

    public static void handleClick(int row, int col) {
        if (!GameController.isGameRunning()) return;
        System.out.println("Tile clicked: " + row + ", " + col);
        // If a piece from the playing team is clicked, select the piece
        if (isPieceAt(row, col) && isPieceAllowed(row, col)) {
            //If the piece clicked is not a king and the king is checked, the piece must be the king
            if (whiteCheckAndKingNotSelected(row, col) || blackCheckAndKingNotSelected(row, col)) {
                return;
            }
            selectPiece(row, col);
        }
        // If a piece is selected and a valid move is clicked, move the piece
        if (selectedPiece!= null && selectedPiece.isValidMove(selectedRow, selectedCol, row, col, board) && !isPieceAllowed(row, col)) {
            movePiece(row, col);
        }
    }

    public static void selectPiece(int row, int col) {
        Piece piece = board.getPiece(row, col);
        piece.updateValidMoves(row, col, board);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (piece.getValidMoves()[i][j]) {
                    System.out.println("Valid move: " + i + ", " + j);
                }
            }
        }
        selectedPiece = piece;
        selectedRow = row;
        selectedCol = col;
        HighlightController.highlightValidMoves(piece, row, col);
    }

    public static void movePiece(int row, int col) {
        boolean prevWhiteCheck = chessController.isWhiteInCheck();
        boolean prevBlackCheck = chessController.isBlackInCheck();
        board.movePiece(selectedRow, selectedCol, row, col);
        chessController.updateCheck();
        System.out.println("White in check: " + chessController.isWhiteInCheck());
        System.out.println("Previous white in check: " + prevWhiteCheck);
        System.out.println("Black in check: " + chessController.isBlackInCheck());
        System.out.println("Previous black in check: " + prevBlackCheck);
        if ((chessController.isWhiteInCheck() == prevWhiteCheck && prevWhiteCheck) || (chessController.isBlackInCheck() == prevBlackCheck && prevBlackCheck)) {
            board.movePiece(row, col, selectedRow, selectedCol);
        } else {
            HighlightController.clearHighlights();
            selectedPiece = null;
            boardView.updatePieces(true, row, col);
            chessController.toggleTurn();
        }
        chessController.updateCheck();

    }

    public static void setChessController(ChessController chessController) {
        ClickController.chessController = chessController;
        ClickController.board = chessController.getBoard();
        ClickController.boardView = chessController.getBoardView();
    }

    public static boolean isPieceAt(int row, int col) {
        return board.getPiece(row, col) != null;
    }

    public static boolean isPieceAllowed(int row, int col) {
        if (board.getPiece(row, col) == null) return false;
        return board.getPiece(row, col).isWhite() == chessController.isWhiteTurn();
    }

    public static boolean whiteCheckAndKingNotSelected(int row, int col) {
        return chessController.isWhiteTurn() && chessController.isWhiteInCheck() && !board.getPiece(row, col).getClass().getSimpleName().equalsIgnoreCase("King");
    }

    public static boolean blackCheckAndKingNotSelected(int row, int col) {
        return !chessController.isWhiteTurn() && chessController.isBlackInCheck() && !board.getPiece(row, col).getClass().getSimpleName().equalsIgnoreCase("King");
    }

}
