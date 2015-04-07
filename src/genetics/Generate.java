package genetics;

import helicopter.GUI;
import helicopter.Play;

public class Generate {
    @SuppressWarnings("UnusedAssignment")
    public static void gen(GUI game) {
        String testObs = "5,17o14,53o4,20o8,41o4,126o7,75o3,3o15,133o7,62o12,36o10,25o14,129o12,6o9,142o15,125o3,89o10,76o14,148o15,63o12,97o10,116o3,39o4,64o15,15o15,14o4,50o5,60o7,8o14,0o8,54";
        String testChromo = "0110100010101001010101110011010010011010001100000101101111001000101010011111010000110010100111001110011110010110000111011110001001110010100000010";

        Population popPop = new Population(Play.POP_SIZE, Play.CROSSOVER_RATE,
                Play.MUTATION_RATE, Play.CODON_SIZE, Play.GENE_LENGTH, game);

        for (int i = 0; i < Play.GENERATIONS; i++) popPop.nextGeneration();

        testChromo = popPop.getBest().getStringDNA();
        testObs = game.getGame().writeObstacleLocations();

        System.out.println("\nREPLAY\n" + popPop.getBest().toString() +
                '\n' + testObs + '\n');

        new Population(testChromo, testObs, game);
    }
}
