package genetics;

import helicopter.GUI;
import helicopter.Play;

public class Generate {
    public static void gen(GUI game) {
        Population popPop = new Population(Play.POP_SIZE, Play.CROSSOVER_RATE,
                Play.MUTATION_RATE, Play.CODON_SIZE, Play.GENE_LENGTH, game);

        System.out.println(popPop.getSortedPop());

        Chromosome c = popPop.selectWeighted();
        System.out.println(c);
        c.mutate();
        System.out.println(c);
    }
}
