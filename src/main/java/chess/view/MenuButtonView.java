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

    public MenuButtonView(String bgColor, String dropColor, String title, String subtext, String iconPath) {
        super();
        setStyle("-fx-background-color:" + bgColor + "; -fx-padding: 10px; -fx-effect: dropshadow(gaussian, rgba(" + dropColor + ",1), 0, 0, 0, 5); -fx-background-radius: 5px;");
        setPrefSize(350, 50);

        HBox buttonContent = new HBox();
        setGraphic(buttonContent);
        buttonContent.setAlignment(Pos.CENTER_LEFT);
        buttonContent.setSpacing(5);
        buttonContent.setPadding(new Insets(10));


        VBox playOnlineButtonContentText = new VBox();
        playOnlineButtonContentText.setAlignment(Pos.CENTER_LEFT);
        playOnlineButtonContentText.setSpacing(5);

        Label titleLabel = new Label(title);
        titleLabel.setStyle("-fx-text-fill: white; -fx-font-size: 18px; -fx-font-weight: bold;");
        Label subtextLabel = new Label(subtext);
        subtextLabel.setStyle("-fx-text-fill: white; -fx-font-size: 14px;");
        playOnlineButtonContentText.getChildren().addAll(titleLabel, subtextLabel);

        Image playOnlineButtonIcon = new Image(getClass().getResourceAsStream(iconPath));
        ImageView playOnlineButtonImageView = new ImageView(playOnlineButtonIcon);
        playOnlineButtonImageView.setFitWidth(50);
        playOnlineButtonImageView.setFitHeight(50);

        buttonContent.getChildren().addAll(playOnlineButtonImageView, playOnlineButtonContentText);
    }

}
