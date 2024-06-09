package chess;

import chess.controller.ChessController;
import chess.model.Board;
import chess.view.AppView;
import chess.view.BoardView;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    private static AppView appView;

    private static Stage primaryStage;

    public static void resetStage(AppView appView) {
        // Méthode pour réinitialiser la scène
        primaryStage.setScene(new Scene(appView));
        primaryStage.show();
    }

    @Override
    public void start(Stage primaryStage) {
        App.primaryStage = primaryStage;
        Board board = new Board();
        BoardView boardView = new BoardView(board);
        new ChessController(board, boardView);

        appView = new AppView(boardView);

        System.out.println(appView.getChildren());

        Scene scene = new Scene(appView);

        primaryStage.setTitle("Jeu d'Échecs");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static void removeMenu() {
        // Méthode pour retirer le side menu
        appView.getChildren().remove(1);
    }

    public static void addMenu(Node node) {
        // Méthode pour ajouter un side menu
        appView.getChildren().add(node);
    }
}
