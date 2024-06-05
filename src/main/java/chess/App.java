package chess;

import chess.view.AppView;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import chess.model.Board;
import chess.view.BoardView;
import chess.controller.ChessController;

public class App extends Application {

    private static AppView appView;

    @Override
    public void start(Stage primaryStage) {
        Board board = new Board();
        BoardView boardView = new BoardView(board);
        ChessController controller = new ChessController(board, boardView);

        this.appView = new AppView(boardView);

        System.out.println(appView.getChildren());

//        Scene scene = new Scene(appView, 800, 800);
        Scene scene = new Scene(appView);
//        scene.getStylesheets().add("style/style.css");

        primaryStage.setTitle("Jeu d'Échecs");
        primaryStage.setScene(scene);
        primaryStage.show();

//        BorderPane root = new BorderPane();
//
//        // Créer le GridPane vide pour l'échiquier
//        GridPane chessBoard = new GridPane();
//        chessBoard.setStyle("-fx-background-color: #f0d9b5; -fx-grid-lines-visible: true;");
//        chessBoard.setPrefSize(640, 640); // Taille de l'échiquier
//        root.setCenter(chessBoard);
//
//        // Créer le VBox pour les boutons et le texte
//        VBox rightPane = new VBox(20); // Espacement de 20px entre les éléments
//        rightPane.setStyle("-fx-background-color: #333333; -fx-padding: 20px;");
//
//        Text title = new Text("Play Chess Online\non the #1 Site!");
//        title.setFill(Color.WHITE);
//        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
//
//        Text stats = new Text("14,364,073 Games Today\n121,425 Playing Now");
//        stats.setFill(Color.WHITE);
//        stats.setStyle("-fx-font-size: 14px;");
//
//        Button playOnlineButton = new Button("Play Online");
//        playOnlineButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-size: 18px; -fx-padding: 10px;");
//
//        Button playComputerButton = new Button("Play Computer");
//        playComputerButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-size: 18px; -fx-padding: 10px;");
//
//        rightPane.getChildren().addAll(title, stats, playOnlineButton, playComputerButton);
//        root.setRight(rightPane);
//
//        Scene scene = new Scene(root, 960, 640); // Taille totale de la scène
//        primaryStage.setTitle("Jeu d'Échecs");
//        primaryStage.setScene(scene);
//        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static void removeMenu() {
        appView.getChildren().remove(1);
    }

    public static void addMenu(Node node) {
        appView.getChildren().add(node);
    }
}
