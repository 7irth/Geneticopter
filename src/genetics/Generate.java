package genetics;

import helicopter.GUI;
import helicopter.Play;

import java.io.*;
import java.util.Scanner;

public class Generate {

    static String infoPath = "C:\\Users\\Tirth\\Programming\\Geneticopter\\game.txt";

    public static void gen(GUI game) {
        Population popPop = new Population(Play.POP_SIZE, Play.CROSSOVER_RATE,
                Play.MUTATION_RATE, Play.CODON_SIZE, Play.GENE_LENGTH, game);

        String gameObs = game.getGame().writeObstacleLocations();
        Chromosome bestChromo = popPop.getBest();

        for (int i = 0; i < Play.GENERATIONS; i++) {
            print("Gen", i, popPop);

            Chromosome bestThisTime = popPop.getBest();
            if (bestThisTime.getFitness() > bestChromo.getFitness()) {
                print("NEW BEST AT GEN", i + ":", bestThisTime.getFitness());
                bestChromo = new Chromosome(bestThisTime);
                saveGame(infoPath, gameObs, bestChromo.getStringDNA());
            }

            popPop.nextGeneration();
        }

        // test chromosome from file
        testChromo(infoPath, game);
    }

    // pythonify printing
    public static void print(Object... args) {
        for (Object arg : args) System.out.print(String.valueOf(arg) + " ");
        System.out.println();
    }

    public static void saveGame(String infoPath, String obs, String chromo) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(infoPath));
            writer.write(obs + '\n' + chromo);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void testChromo(String infoPath, GUI game) {
        String testObs = "";
        String testChromo = "";
        try {
            Scanner scanner = new Scanner(new FileInputStream(infoPath));
            testObs = scanner.nextLine();
            testChromo = scanner.nextLine();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        print(Chromosome.testChromo(testChromo, testObs, game));
    }
}
