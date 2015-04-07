package genetics;

import helicopter.GUI;
import helicopter.Play;

public class Generate {
    public static void gen(GUI game) {
        String testObs = "6,63o5,92o3,34o14,117o4,48o9,36o15,68o3,53o7,55o4,25o13,104o3,81o8,72o6,11o13,55o10,134o3,119o13,146o7,102o11,67o15,64o5,149o4,87o14,53o11,130o12,69o9,128o7,147o13,12o7,101";
        String testChromo = "10001011100100110100111110111000000010111110100001001100000010111011101110011101001101010001011010101011010001100010000001101100010100101101011110100011100010011110011101010010000100110100000101011111100110011010011110100100";

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
