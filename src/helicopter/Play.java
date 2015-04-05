package helicopter;

import genetics.Generate;

public class Play {

    // cave values
    public static final int X_SIZE = 20;
    public static final int Y_SIZE = 150;
    public static final int OBSTACLES = 30;

    // population values
    public static final int POP_SIZE = 5;
    public static final double CROSSOVER_RATE = 0.7;
    public static final double MUTATION_RATE = 0.001;
    public static final int CODON_SIZE = 1;
    public static final int GENE_LENGTH = 50;

    public static void main(String[] args) {

        // create game
        GUI gameUI = new GUI(new HelicopterGame());

        // start game
        javax.swing.SwingUtilities.invokeLater(gameUI::launchGame);

        // solve game
        Generate.gen(gameUI);
    }
}
