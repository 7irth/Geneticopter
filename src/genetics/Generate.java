package genetics;

import helicopter.GUI;
import helicopter.Play;

public class Generate {
    public static void gen(GUI game) {
        Population popPop = new Population(Play.POP_SIZE, Play.CROSSOVER_RATE,
                Play.MUTATION_RATE, Play.CODON_SIZE, Play.GENE_LENGTH, game);

        for (int i = 0; i < Play.GENERATIONS; i++) popPop.nextGeneration();
    }
}
