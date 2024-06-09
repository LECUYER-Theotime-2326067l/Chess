package chess.view;

import chess.controller.ClickController;
import chess.controller.HighlightController;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import chess.model.Board;
import chess.model.Piece;

public class BoardView extends GridPane {

    private final Board board;
    public Board getBoard() {
        return board;
    }

    private final Rectangle[][] squares;
    public Rectangle[][] getSquares() {
        return squares;
    }

    private static final int TILE_SIZE = 80;
    public static final Color LIGHT_COLOR = Color.web("#ebecd0");
    public static final Color DARK_COLOR = Color.web("#739552");


    public BoardView(Board board) {
        setStyle("-fx-background-color:#302e2b");
        this.board = board;
        this.squares = new Rectangle[8][8];
        HighlightController.setBoardView(this);
        drawBoard();
        setGridLinesVisible(false);
        setHgap(-1);
        setVgap(-1);
    }

    /**
     * Dessine la représentation de l'échiquier
     */
    private void drawBoard() {
        setVgap(0);
        setHgap(0);
        // Dessine le damier et ajoute des gestionnaires d'événements sur les cases
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Color tileColor = (row + col) % 2 == 0 ? LIGHT_COLOR : DARK_COLOR;
                Rectangle tile = new Rectangle(TILE_SIZE, TILE_SIZE, tileColor);
                squares[row][col] = tile;
                add(tile, col, row);

                int finalRow = row;
                int finalCol = col;
                tile.setOnMouseClicked(event -> ClickController.handleClick(finalRow, finalCol));
            }
        }

        updatePieces();
    }

    /**
     * Met à jour les pièces sur l'échiquier
     */
    public void updatePieces() {
        // Nettoie les pièces existantes
        getChildren().removeIf(node -> node instanceof PieceView);

        // Ajoute les nouvelles pièces
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Piece piece = board.getPiece(row, col);
                if (piece != null) {
                    PieceView pieceView = createPieceView(piece);
                    add(pieceView, col, row);

                    int finalRow = row;
                    int finalCol = col;
                    pieceView.setOnMouseClicked(event -> ClickController.handleClick(finalRow, finalCol));
                }
            }
        }
    }

    /**
     * Crée une vue pour une pièce
     * @param piece pièce à afficher
     * @return vue de la pièce
     */
    private PieceView createPieceView(Piece piece) {
        return new PieceView(piece);
    }

}
