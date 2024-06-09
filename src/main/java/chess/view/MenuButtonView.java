package chess.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class MenuButtonView extends Button {

    /**
     * Vue d'un bouton du menu principal
     * @param bgColor couleur de fond
     * @param dropColor couleur de l'ombre
     * @param title titre du bouton
     * @param subtext sous-titre du bouton
     * @param iconPath chemin de l'icône
     */
    public MenuButtonView(String bgColor, String dropColor, String title, String subtext, String iconPath) {
        super();
        setStyle("-fx-background-color:" + bgColor + "; -fx-padding: 10px; -fx-effect: dropshadow(gaussian, rgba(" + dropColor + ",1), 0, 0, 0, 5); -fx-background-radius: 5px;");
        setPrefSize(350, 50);

        HBox buttonContent = new HBox();
        setGraphic(buttonContent);
        buttonContent.setAlignment(Pos.CENTER_LEFT);
        buttonContent.setSpacing(5);
        buttonContent.setPadding(new Insets(10));

        VBox buttonContentText = new VBox();
        buttonContentText.setAlignment(Pos.CENTER_LEFT);
        buttonContentText.setSpacing(5);

        // Création des titres
        Label titleLabel = new Label(title);
        titleLabel.setStyle("-fx-text-fill: white; -fx-font-size: 18px; -fx-font-weight: bold;");
        Label subtextLabel = new Label(subtext);
        subtextLabel.setStyle("-fx-text-fill: white; -fx-font-size: 14px;");
        buttonContentText.getChildren().addAll(titleLabel, subtextLabel);

        // Création de l'icône
        Image buttonIcon = new Image(getClass().getResourceAsStream(iconPath));
        ImageView buttonImageView = new ImageView(buttonIcon);
        buttonImageView.setFitWidth(50);
        buttonImageView.setFitHeight(50);

        buttonContent.getChildren().addAll(buttonImageView, buttonContentText);
    }

}
