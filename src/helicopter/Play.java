package helicopter;

import genetics.Generate;

public class Play {

    public static void main(String[] args) {

        // create game
        HelicopterGame game = new HelicopterGame(20, 150);
        GUI gameUI = new GUI(game);

        // start game
        javax.swing.SwingUtilities.invokeLater(gameUI::launchGame);

        // solve game
        Generate.gen(gameUI);
    }
}
