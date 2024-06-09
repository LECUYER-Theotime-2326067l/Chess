package chess.view;

import chess.controller.GameController;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class StartingView extends VBox {

    /**
     * Vue de démarrage d'une partie
     */
    public StartingView() {
        super();
        setAlignment(Pos.CENTER);
        setSpacing(10);

        // Crée le champ pour le nom du premier joueur
        VBox firstPlayer = new VBox();
        firstPlayer.setAlignment(Pos.CENTER_LEFT);
        firstPlayer.setSpacing(5);
        Label firstPlayerLabel = new Label("Player 1");
        firstPlayerLabel.setStyle("-fx-text-fill: white;");
        TextField firstPlayerName = new TextField();
        firstPlayer.getChildren().addAll(firstPlayerLabel, firstPlayerName);

        // Crée le champ pour le nom du second joueur
        VBox secondPlayer = new VBox();
        secondPlayer.setAlignment(Pos.CENTER_LEFT);
        secondPlayer.setSpacing(5);
        Label secondPlayerLabel = new Label("Player 2");
        secondPlayerLabel.setStyle("-fx-text-fill: white;");
        TextField secondPlayerName = new TextField();
        secondPlayer.getChildren().addAll(secondPlayerLabel, secondPlayerName);

        // Crée un bouton pour lancer la partie
        MenuButtonView startGameButton = new MenuButtonView("#81b64c", "69,117,60", "Start Game", "", "/images/play_online.png");
        startGameButton.setOnMouseClicked(e -> GameController.startVersusGame(firstPlayerName.getText(), secondPlayerName.getText()));

        getChildren().addAll(firstPlayer, secondPlayer, startGameButton);

    }

}
