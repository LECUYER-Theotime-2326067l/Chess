package chess.controller;

import chess.App;
import chess.view.StartingView;

public class MenuController {

    /**
     * Ouvre le menu de choix des pseudonymes des joueurs
     */
    public static void openVersusMenu() {
        App.removeMenu();
        App.addMenu(new StartingView());
    }
}
