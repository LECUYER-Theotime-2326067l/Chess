package chess.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import chess.model.Piece;

public class PieceView extends ImageView {
    private final Piece piece;

    public PieceView(Piece piece) {
        this.piece = piece;
        setImage(getPieceImage());
        setFitWidth(80);
        setFitHeight(80);
    }

    private Image getPieceImage() {
        String pieceType = piece.getClass().getSimpleName().toLowerCase();
        String color = piece.isWhite() ? "white" : "black";
        String imagePath = "/images/" + color + "_" + pieceType + ".png";
        return new Image(getClass().getResourceAsStream(imagePath));
    }

    public Piece getPiece() {
        return piece;
    }
}
