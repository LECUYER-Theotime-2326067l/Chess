package chess.view;

import chess.controller.GameController;
import chess.controller.MenuController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class AppView extends HBox {

    public AppView(BoardView boardView) {
        super();
        setStyle("-fx-background-color:#302e2b");
//        getChildren().add(boardView);
        setHgrow(this, Priority.ALWAYS);
        setAlignment(Pos.CENTER);

        VBox gamePane = new VBox();
        gamePane.setPrefWidth(640);
        gamePane.setSpacing(5);
        gamePane.setAlignment(Pos.TOP_LEFT);
        UserView whiteUser = new UserView(GameController.firstPlayerUsernameProperty(), 3, true);
        UserView blackUser = new UserView(GameController.secondPlayerUsernameProperty(), 2, false);
        gamePane.getChildren().addAll(blackUser, boardView, whiteUser);
        getChildren().add(gamePane);

        VBox rightPane = new VBox();
        Label title = new Label("Play Chess Online\non the #1 Site!");
        title.setStyle("-fx-font-size: 37px; -fx-font-weight: bold; -fx-text-fill: white; -fx-text-alignment:center;");
        rightPane.getChildren().add(title);
        rightPane.setSpacing(30);
        rightPane.setFillWidth(false);
        rightPane.setAlignment(Pos.TOP_CENTER);

        GridPane buttons = new GridPane();
        buttons.setVgap(20);
        buttons.setHgap(20);
        buttons.setAlignment(Pos.CENTER);

        MenuButtonView playOnlineButton = new MenuButtonView("#81b64c", "69,117,60", "Play Versus", "Play against other players", "/images/play_online.png");
        playOnlineButton.setOnMouseClicked(e -> MenuController.openVersusMenu());
        MenuButtonView playComputerButton = new MenuButtonView("#454341", "62,60,58", "Play Computer", "Play against the computer", "/images/play_computer.png");

        buttons.add(playOnlineButton, 0, 0);
        buttons.add(playComputerButton, 0, 1);

        getChildren().add(rightPane);
        rightPane.getChildren().add(buttons);

        setMargin(gamePane, new Insets(20));
        setMargin(rightPane, new Insets(20));
    }

}
