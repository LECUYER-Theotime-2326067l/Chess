package chess.controller;

import chess.App;
import chess.view.StartingView;

public class MenuController {

    public static void startVersusGame() {
        App.removeMenu();
        App.addMenu(new StartingView());
    }

}
