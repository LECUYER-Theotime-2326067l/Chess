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

    /**
     * Gère les clics de l'utilisateur sur le plateau
     * @param row ligne de la case cliquée
     * @param col colonne de la case cliquée
     */
    public static void handleClick(int row, int col) {
        // Si la partie n'est pas en cours, on ne fait rien
        if (!GameController.isGameRunning()) return;
        // Si un pion de l'équipe en train de jouer est cliqué, on le sélectionne
        if (isPieceAt(row, col) && isPieceAllowed(row, col)) {
            // Si le pion cliqué n'est pas un roi alors que le roi est en échec, le pion doit être le roi
            if (whiteCheckAndKingNotSelected(row, col) || blackCheckAndKingNotSelected(row, col)) {
                return;
            }
            // Sinon, on sélectionne le pion
            selectPiece(row, col);
        }
        // Si un pion est sélectionné et qu'un mouvement valide est cliqué, on déplace le pion
        if (selectedPiece!= null && selectedPiece.isValidMove(selectedRow, selectedCol, row, col, board) && !isPieceAllowed(row, col)) {
            movePiece(row, col);
        }
    }

    /**
     * Séléctionne un pion et affiche les mouvements valides
     * @param row ligne du pion à sélectionner
     * @param col colonne du pion à sélectionner
     */
    public static void selectPiece(int row, int col) {
        Piece piece = board.getPiece(row, col);
        piece.updateValidMoves(row, col, board);
        selectedPiece = piece;
        selectedRow = row;
        selectedCol = col;
        HighlightController.highlightValidMoves(piece, row, col);
    }

    /**
     * Déplace un pion sauf si le roi reste en échec après le déplacement
     * @param row ligne de la case de destination
     * @param col colonne de la case de destination
     */
    public static void movePiece(int row, int col) {
        boolean prevWhiteCheck = chessController.isWhiteInCheck();
        boolean prevBlackCheck = chessController.isBlackInCheck();
        board.movePiece(selectedRow, selectedCol, row, col);
        chessController.updateCheck();
        // Si le roi reste en échec après le déplacement, on annule le déplacement
        if ((chessController.isWhiteInCheck() == prevWhiteCheck && prevWhiteCheck) || (chessController.isBlackInCheck() == prevBlackCheck && prevBlackCheck)) {
            board.movePiece(row, col, selectedRow, selectedCol);
        } else {
            HighlightController.clearHighlights();
            selectedPiece = null;
            boardView.updatePieces();
            chessController.toggleTurn();
        }
        chessController.updateCheck();

    }

    /**
     * Définit le contrôleur de jeu
     * @param chessController instance du contrôleur de jeu
     */
    public static void setChessController(ChessController chessController) {
        ClickController.chessController = chessController;
        ClickController.board = chessController.getBoard();
        ClickController.boardView = chessController.getBoardView();
    }

    /**
     * Vérifie si un pion est présent à une position donnée
     * @param row ligne de la position à vérifier
     * @param col colonne de la position à vérifier
     * @return true si un pion est présent, false sinon
     */
    public static boolean isPieceAt(int row, int col) {
        return board.getPiece(row, col) != null;
    }

    /**
     * Vérifie si le pion à une position donnée est de la couleur de l'équipe en train de jouer
     * @param row ligne de la position à vérifier
     * @param col colonne de la position à vérifier
     * @return true si le pion est de la couleur de l'équipe en train de jouer, false sinon
     */
    public static boolean isPieceAllowed(int row, int col) {
        if (board.getPiece(row, col) == null) return false;
        return board.getPiece(row, col).isWhite() == chessController.isWhiteTurn();
    }

    /**
     * Vérifie si le roi blanc est en échec et qu'un pion autre que le roi est sélectionné
     * @param row ligne du pion sélectionné
     * @param col colonne du pion sélectionné
     * @return true si le roi blanc est en échec et qu'un pion autre que le roi est sélectionné, false sinon
     */
    public static boolean whiteCheckAndKingNotSelected(int row, int col) {
        return chessController.isWhiteTurn() && chessController.isWhiteInCheck() && !board.getPiece(row, col).getClass().getSimpleName().equalsIgnoreCase("King");
    }

    /**
     * Vérifie si le roi noir est en échec et qu'un pion autre que le roi est sélectionné
     * @param row ligne du pion sélectionné
     * @param col colonne du pion sélectionné
     * @return true si le roi noir est en échec et qu'un pion autre que le roi est sélectionné, false sinon
     */
    public static boolean blackCheckAndKingNotSelected(int row, int col) {
        return !chessController.isWhiteTurn() && chessController.isBlackInCheck() && !board.getPiece(row, col).getClass().getSimpleName().equalsIgnoreCase("King");
    }

}
