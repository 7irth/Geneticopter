package helicopter;

public class Play {

    public static void main(String[] args) {
        HelicopterGame game = new HelicopterGame();
        GUI gameUI = new GUI(game);

        javax.swing.SwingUtilities.invokeLater(gameUI::launchGame);
    }
}
