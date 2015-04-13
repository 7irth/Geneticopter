package genetics;

import helicopter.GUI;
import helicopter.Play;

public class Generate {
    @SuppressWarnings("UnusedAssignment")
    public static void gen(GUI game) {
        Population popPop = new Population(Play.POP_SIZE, Play.CROSSOVER_RATE,
                Play.MUTATION_RATE, Play.CODON_SIZE, Play.GENE_LENGTH, game);

//        String gameObs = game.getGame().writeObstacleLocations();
//        Chromosome bestChromo = popPop.getBest();
//
        for (int i = 0; i < Play.GENERATIONS; i++) {
            print("Gen", i, popPop);
//
//            Chromosome bestThisTime = popPop.getBest();
//            if (bestThisTime.getFitness() > bestChromo.getFitness()) {
//                print("NEW BEST AT GEN", i + ":", bestThisTime.getFitness());
//                bestChromo = new Chromosome(bestThisTime);
//                bestChromo.testFitness(game);
//            }
//
            popPop.nextGeneration();
        }
//
//        System.out.println('\n' + gameObs + '\n' + bestChromo);

        // testing single
//        String testObs = "12,149o8,1o4,118o15,109o12,19o7,47o6,26o14,77o6,137o6,8o9,73o7,110o6,1o8,63o8,121o6,72o7,74o7,123o7,21o15,104o15,40o12,47o13,74o14,75o13,32o14,106o7,81o4,70o14,86o15,38o3,35o5,90o4,120o8,140o13,89o7,76o4,8o5,18o9,126o11,28o15,34o5,74o14,45o8,8o12,135o7,26o8,30o6,93o12,3o8,147";
//        String testChromo = "11100001001111100011001110110001011100100011001100110110100011001001100010111000111010110100110001101001011000101100010111101100000011011011110110101001000101000011011010001111100111000100111101001101001101010000000101101100101001001101001111001101000011000100111010110101001100110011010110010001001001101001010110011010110001100001111100011011110010110010011000010000101111000101010110010111000010010101000101011101001011000111110000011101010100110110000111001110100011010011100100";
//
//        System.out.println(Chromosome.testChromo(testChromo, testObs, game));

//        String testObs50 = "3,112o7,46o15,51o14,128o3,25o10,37o6,147o14,124o10,86o13,86o15,7o7,50o14,44o6,128o13,41o11,40o8,121o5,88o9,8o7,43o4,127o9,74o11,30o3,101o4,66o10,91o5,149o14,103o7,38o4,137o11,27o3,88o9,136o10,66o14,122o15,135o4,93o13,126o13,21o12,11o9,67o9,109o5,64o4,128o13,81o9,61o6,63o7,29o14,6o13,118";
//        String testChromo50 = "011100010100011110001111000101111001110110010010100011100010110001010111101100011101010000111001000111100110101110011100010010101100010110010000010010110111100100011001010011111001111111100001101000010101111010001101110010000110011010100101010001000100010100101011000110011011111100011011110010100100100001011111110110001000011011111100111000011000100110011010011010010010011101001100111111110";
//
//        print(Chromosome.testChromo(testChromo50, testObs50, game));
    }

    // pythonify printing
    public static void print(Object... args) {
        for (Object arg : args) System.out.print(String.valueOf(arg) + " ");
        System.out.println();
    }
}
