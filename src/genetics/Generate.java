package genetics;

import helicopter.GUI;
import helicopter.Play;

public class Generate {
    @SuppressWarnings("UnusedAssignment")
    public static void gen(GUI game) {
        String testObs = "13,111o9,147o8,112o4,69o5,77o15,20o14,102o6,122o12,133o6,74o3,143o3,88o4,51o12,18o4,111o4,105o14,106o4,133o10,125o13,133o4,12o13,101o12,54o5,137o3,93o7,50o15,100o9,53o12,48o4,15";
        String testChromo = "10110010001010011000111011001100001110101111010100011100111111101111100111100001011111111110011001101001101101001110001100";

        Population popPop = new Population(Play.POP_SIZE, Play.CROSSOVER_RATE,
                Play.MUTATION_RATE, Play.CODON_SIZE, Play.GENE_LENGTH, game);

        for (int i = 0; i < Play.GENERATIONS; i++) popPop.nextGeneration();

        testChromo = popPop.getBest().getStringDNA();
        testObs = game.getGame().writeObstacleLocations();

        System.out.println("\nREPLAY: " + testObs + '\n' +
                popPop.getBest().toString() + '\n');

        System.out.println(Chromosome.testChromo(testChromo, testObs, game));
    }
}
