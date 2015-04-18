package helicopter;

import genetics.Chromosome;
import genetics.Generate;

public class Play {

    // "path\to\game.txt"
    public static final String SAVE_GAME =
            "C:\\Users\\Tirth\\Programming\\Geneticopter\\game.txt";

    // cave values
    public static final int ROWS = 20;
    public static final int COLUMNS = 150;
    public static final int OBSTACLES = 70;
    public static boolean GUI = false;

    // population values
    public static final int POP_SIZE = 200;
    public static final double CROSSOVER_RATE = 0.7;
    public static final double MUTATION_RATE = 0.001;
    public static final int CODON_SIZE = 1;
    public static final int GENE_LENGTH = 2000;
    public static final int GENERATIONS = 200;

    public static final boolean DEBUG = false;

    public static void main(String[] args) {

        // create game
        GUI gameUI = new GUI(new HelicopterGame());

        // start GUI
        if (GUI) javax.swing.SwingUtilities.invokeLater(gameUI::launchGame);

        // solve game
//        Generate.gen(gameUI);
        endMutation(gameUI);
        Generate.testChromo(gameUI);
    }

    public static void endMutation(GUI game) {
        Chromosome chosenOne = new Chromosome(new double[]{1, 5000, 0.001});

        for (int i = 0; i < 1000; i++) {
            chosenOne.testFitness(game);
            Generate.print(chosenOne.getFitness());
            chosenOne.mutateEnd(2);
        }

        Generate.saveGame(game.getGame().writeObstacleLocations(), chosenOne);
    }
}
