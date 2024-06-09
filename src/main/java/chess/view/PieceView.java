package chess.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import chess.model.Piece;

public class PieceView extends ImageView {
    private final Piece piece;

    /**
     * Crée une vue pour une pièce
     * @param piece pièce à afficher
     */
    public PieceView(Piece piece) {
        this.piece = piece;
        setImage(getPieceImage());
        setFitWidth(80);
        setFitHeight(80);
    }

    /**
     * Récupère l'image de la pièce
     * @return image de la pièce
     */
    private Image getPieceImage() {
        String pieceType = piece.getClass().getSimpleName().toLowerCase();
        String color = piece.isWhite() ? "white" : "black";
        String imagePath = "/images/" + color + "_" + pieceType + ".png";
        return new Image(getClass().getResourceAsStream(imagePath));
    }

    /**
     * Récupère la pièce associée à la vue
     * @return pièce associée
     */
    public Piece getPiece() {
        return piece;
    }
}
