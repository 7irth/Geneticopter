package genetics;

import helicopter.GUI;
import helicopter.Play;

public class Generate {
    @SuppressWarnings("UnusedAssignment")
    public static void gen(GUI game) {
        Population popPop = new Population(Play.POP_SIZE, Play.CROSSOVER_RATE,
                Play.MUTATION_RATE, Play.CODON_SIZE, Play.GENE_LENGTH, game);

        String gameObs = game.getGame().writeObstacleLocations();
        Chromosome bestChromo = popPop.getBest();

        for (int i = 0; i < Play.GENERATIONS; i++) {
            System.out.println("Gen " + i + " " + popPop);

//            Chromosome bestThisTime = popPop.getBest();
//            if (bestThisTime.getFitness() > bestChromo.getFitness()) {
//                System.out.println("New best at gen " + i + ": " + bestThisTime.getFitness());
//                bestChromo = new Chromosome(bestThisTime);
//                bestChromo.testFitness(game);
//            }

            popPop.nextGeneration();
        }

        System.out.println('\n' + gameObs + '\n' + bestChromo);

        // testing single
//        String testObs = "12,149o8,1o4,118o15,109o12,19o7,47o6,26o14,77o6,137o6,8o9,73o7,110o6,1o8,63o8,121o6,72o7,74o7,123o7,21o15,104o15,40o12,47o13,74o14,75o13,32o14,106o7,81o4,70o14,86o15,38o3,35o5,90o4,120o8,140o13,89o7,76o4,8o5,18o9,126o11,28o15,34o5,74o14,45o8,8o12,135o7,26o8,30o6,93o12,3o8,147";
//        String testChromo = "11100001001111100011001110110001011100100011001100110110100011001001100010111000111010110100110001101001011000101100010111101100000011011011110110101001000101000011011010001111100111000100111101001101001101010000000101101100101001001101001111001101000011000100111010110101001100110011010110010001001001101001010110011010110001100001111100011011110010110010011000010000101111000101010110010111000010010101000101011101001011000111110000011101010100110110000111001110100011010011100100";
//
//        System.out.println(Chromosome.testChromo(testChromo, testObs, game));
    }
}
