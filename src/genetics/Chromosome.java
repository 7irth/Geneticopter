package genetics;

import helicopter.GUI;
import helicopter.HelicopterGame;

import java.util.Random;

@SuppressWarnings("unused")
public class Chromosome {

    private final Random rando;

    private String dna;
    private final int codonSize;
    private final int numberOfGenes;
    private final int chromoLength;
    private final double mutationRate;

    private int fitness;

    public Chromosome(double[] geneInfo, String dna) {
        rando = new Random();

        codonSize = (int) geneInfo[0];
        numberOfGenes = (int) geneInfo[1];
        mutationRate = geneInfo[2];
        chromoLength = codonSize * numberOfGenes;

        this.dna = dna;

        if (dna.length() == 0) while (this.dna.length() < chromoLength)
            this.dna += rando.nextBoolean() ? "1" : "0";

        fitness = 0;
    }

    // randomly generated dna
    public Chromosome(double[] geneInfo) {
        this(geneInfo, "");
    }

    // copy chromosome
    public Chromosome(Chromosome chromosome) {
        this(chromosome.getGeneInfo(), chromosome.getStringDNA());
    }

    public static Chromosome testChromo(String testDNA, String testObs,
                                        GUI window) {
        Chromosome c = new Chromosome(new double[]{0, 0, 0}, testDNA);
        window.getGame().readObstacleLocations(testObs);
        c.testFitness(window);
        return c;
    }

    public int testFitness(GUI window) {
        fitness = 0;

        for (char c : dna.toCharArray())
            try {
                if (c == '0') window.getGame().gas(false);
                else window.getGame().gas(true);

                window.update(false);
                fitness++;

            } catch (HelicopterGame.CollisionException e) {
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

    public Chromosome mutateEnd(int mutateLength) {
        System.out.println("Starting " + dna);
        dna = dna.substring(0, dna.length() - mutateLength);

        while (dna.length() < chromoLength)
            this.dna += rando.nextBoolean() ? "1" : "0";

        System.out.println("Now      " + dna);
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

    public double[] getGeneInfo() {
        return new double[]{codonSize, numberOfGenes, mutationRate};
    }

    public int getFitness() {
        return fitness;
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
