package helicopter;

import genes.Population;

public class Play {

    public static void main(String[] args) {
        HelicopterGame game = new HelicopterGame(20, 150);
        GUI gameUI = new GUI(game);

        javax.swing.SwingUtilities.invokeLater(gameUI::launchGame);

        Population pop = new Population(2, 0.7, 0.001, 1, 30, gameUI);
        System.out.println(pop.getSortedPop());
    }
}
