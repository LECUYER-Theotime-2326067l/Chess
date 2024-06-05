package chess.view;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class StartingView extends VBox {

    public StartingView() {
        super();
        setAlignment(Pos.CENTER);

        VBox firstPlayer = new VBox();
        firstPlayer.setAlignment(Pos.CENTER_LEFT);
        firstPlayer.setSpacing(5);
        Label firstPlayerLabel = new Label("Player 1");
        TextField firstPlayerName = new TextField();
        firstPlayer.getChildren().addAll(firstPlayerLabel, firstPlayerName);

        VBox secondPlayer = new VBox();
        secondPlayer.setAlignment(Pos.CENTER_LEFT);
        secondPlayer.setSpacing(5);
        Label secondPlayerLabel = new Label("Player 2");
        TextField secondPlayerName = new TextField();
        secondPlayer.getChildren().addAll(secondPlayerLabel, secondPlayerName);

        getChildren().addAll(firstPlayer, secondPlayer);

    }

}
