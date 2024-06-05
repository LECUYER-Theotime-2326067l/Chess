package chess.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

public class UserView extends HBox {

    public UserView(String username, int level, boolean isBottom) {

        super();
        setPrefWidth(640);
        setAlignment(Pos.CENTER_LEFT);
        setFillHeight(true);
        setSpacing(5);

        String userIconPath = isBottom ? "/images/white_user.png" : "/images/black_user.png";
        Image userImage = new Image(getClass().getResourceAsStream(userIconPath));
        ImageView userIcon = new ImageView(userImage);
        userIcon.setFitWidth(50);
        userIcon.setFitHeight(50);
        getChildren().add(userIcon);

        HBox labelsContainer = new HBox();
        labelsContainer.setSpacing(5);
        Label usernameLabel = new Label(username);
        usernameLabel.setStyle("-fx-text-fill: white; -fx-font-size: 18px; -fx-font-weight: bold;");
        Label levelLabel = new Label("(" + level + ")");
        levelLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #929292;");
        labelsContainer.getChildren().addAll(usernameLabel, levelLabel);

        VBox counter = new VBox();
        counter.setAlignment(Pos.BOTTOM_RIGHT);
        counter.setStyle("-fx-background-color: #989795; -fx-background-radius: 5;");
        Label timer = new Label("10:00");
        timer.setPrefWidth(100);
        timer.setTextAlignment(TextAlignment.RIGHT);
        timer.setStyle("-fx-text-fill: #605f5d; -fx-font-size: 18px; -fx-font-weight: bold;");
        timer.setPadding(new Insets(5,35,5,5));
        counter.getChildren().add(timer);

        VBox userPane = new VBox();
        userPane.setFillWidth(true);
        userPane.setPrefWidth(580);
        userPane.setSpacing(2);
        if (isBottom) userPane.getChildren().addAll(counter, labelsContainer);
        else userPane.getChildren().addAll(labelsContainer, counter);
        getChildren().add(userPane);
    }

}
