package helicopter;

import genetics.Generate;

public class Play {

    // cave values
    public static final int X_SIZE = 20;
    public static final int Y_SIZE = 150;
    public static final int OBSTACLES = 30;
    public static final boolean GUI = true;

    // population values
    public static final int POP_SIZE = 100;
    public static final double CROSSOVER_RATE = 0.7;
    public static final double MUTATION_RATE = 0.001;
    public static final int CODON_SIZE = 1;
    public static final int GENE_LENGTH = 1000;
    public static final int GENERATIONS = 10;

    public static final boolean DEBUG = false;

    public static void main(String[] args) {

        // create game
        GUI gameUI = new GUI(new HelicopterGame());

        // start GUI
        if (GUI) javax.swing.SwingUtilities.invokeLater(gameUI::launchGame);

        // solve game
        Generate.gen(gameUI);
    }
}
