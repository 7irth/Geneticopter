package genetics;

import helicopter.GUI;
import helicopter.HelicopterGame;

import java.util.Random;

public class Chromosome {

    private Random rando;

    private String dna;
    private int codonSize;
    private int numberOfGenes;
    private int chromoLength;
    private double mutationRate;

    private int fitness = 0;

    public Chromosome(double[] geneInfo, String dna) {
        rando = new Random();

        codonSize = (int) geneInfo[0];
        numberOfGenes = (int) geneInfo[1];
        mutationRate = geneInfo[2];
        chromoLength = codonSize * numberOfGenes;

        this.dna = dna;

        if (dna.length() == 0) while (this.dna.length() < chromoLength)
            this.dna += rando.nextBoolean() ? "1" : "0";
    }

    // randomly generated dna
    public Chromosome(double[] geneInfo) {
        this(geneInfo, "");
    }

    // randomly generated and tested
    public Chromosome(double[] geneInfo, GUI window) {
        this(geneInfo, "");
        testFitness(window);
    }

    // for testing single chromosomes
    public Chromosome(String testDNA, GUI window) {
        rando = new Random();
        this.dna = testDNA;
        System.out.println(testFitness(window));
    }

    public int testFitness(GUI window) {

        for (char c : dna.toCharArray())
            try {
                if (c == '0') window.getGame().gas(false);
                else window.getGame().gas(true);

                window.update();
                fitness++;

            } catch (HelicopterGame.CollisionException e) {
                System.out.println(this);
                break;
            }
        return fitness;
    }

    public Chromosome mutate() {
        String newDNA = "";

        for (char c : dna.toCharArray())
            newDNA += rando.nextDouble() < mutationRate ?
                    (c + 1) % 2 : String.valueOf(c);

        dna = newDNA;
        fitness = 0;

        return this;
    }

    public int getCodonSize() {
        return codonSize;
    }

    public int getNumberOfGenes() {
        return numberOfGenes;
    }

    public int getChromoLength() {
        return chromoLength;
    }

    // could give back 0 fitness if it hasn't been tested yet
    public int getFitness() {
        return fitness;
    }

    public void upFitness() {
        fitness++;
    }

    public char[] getDNA() {
        return dna.toCharArray();
    }

    public String getStringDNA() {
        return dna;
    }

    @Override
    public String toString() {
        return String.format("%d: %s", fitness, dna.substring(0, fitness));
    }
}
