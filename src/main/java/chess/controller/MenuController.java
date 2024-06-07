package chess.controller;

import chess.App;
import chess.view.StartingView;

public class MenuController {

    public static void openVersusMenu() {
        App.removeMenu();
        App.addMenu(new StartingView());
    }

    public static void openComputerMenu() {
        App.removeMenu();
        App.addMenu(new StartingView());
    }

}
