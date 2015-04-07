package genetics;

import helicopter.GUI;
import helicopter.Play;

public class Generate {
    public static void gen(GUI game) {
        String testObs = "4,61o6,55o9,51o15,9o12,78o7,113o10,95o3,139o7,131o10,15o8,67o12,28o6,10o4,5o6,84o10,71o8,24o9,97o14,91o13,95o15,81o7,141o4,60o7,15o12,5o13,66o7,91o10,42o7,143o12,46";
        String testChromo = "101010001011000001010010100101111101010010111100001111101011100001111010101111011011010110110111010110110111010100111011001101000010011110010110100011111100011011010011100011010011";

        Population popPop = new Population(Play.POP_SIZE, Play.CROSSOVER_RATE,
                Play.MUTATION_RATE, Play.CODON_SIZE, Play.GENE_LENGTH, game);

        for (int i = 0; i < Play.GENERATIONS; i++) popPop.nextGeneration();

        testChromo = popPop.getBest().getStringDNA();
        testObs = game.getGame().writeObstacleLocations();

        System.out.println(popPop.getBest().toString() + '\n' + testObs);

        System.out.println("REPLAY");
        new Population(testChromo, testObs, game);
    }
}
