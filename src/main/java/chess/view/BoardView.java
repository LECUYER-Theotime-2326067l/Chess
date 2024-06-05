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

    private void drawBoard() {
        setVgap(0);
        setHgap(0);
        // Dessiner le damier et ajouter des gestionnaires d'événements
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
//                System.out.println("Drawing tile: " + row + ", " + col);
                Color tileColor = (row + col) % 2 == 0 ? LIGHT_COLOR : DARK_COLOR;
                Rectangle tile = new Rectangle(TILE_SIZE, TILE_SIZE, tileColor);
                squares[row][col] = tile;
                add(tile, col, row);

                int finalRow = row;
                int finalCol = col;
                tile.setOnMouseClicked(event -> ClickController.handleClick(finalRow, finalCol));
            }
        }

        updatePieces(false,0,0);
    }

    public void updatePieces(boolean isUpdatedAfterMove, int newRow, int newCol) {
        System.out.println("Updating pieces");
        // Clear existing pieces
        getChildren().removeIf(node -> node instanceof PieceView);

        // Add pieces
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

    private PieceView createPieceView(Piece piece) {
        return new PieceView(piece);
    }

}
